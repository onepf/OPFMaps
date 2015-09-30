/*
 * Copyright 2012-2015 One Platform Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onepf.opfmaps;

import android.content.Context;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import org.onepf.opfmaps.factory.DelegatesAbstractFactory;
import org.onepf.opfutils.OPFChecks;
import org.onepf.opfutils.OPFLog;
import org.onepf.opfutils.OPFUtils;
import org.onepf.opfutils.exception.InitException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The helper class to initialize the library before using.
 *
 * @author Roman Savin
 * @since 30.07.2015
 */
public final class OPFMapHelper {

    private static final class Holder {
        @SuppressWarnings("PMD.AccessorClassGeneration")
        public static final OPFMapHelper INSTANCE = new OPFMapHelper();
    }

    @Nullable
    private OPFMapProvider currentProvider;

    private OPFMapHelper() {
    }

    public static OPFMapHelper getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Initializes the OPF Map objects. Sets the current map provider, to which will be delegated all Map methods calls.
     * <p/>
     * The first available provider from the {@link OPFMapConfiguration#getProviders()} list will be chosen as the current provider.
     * If you set {@code true} value to the {@link org.onepf.opfmaps.OPFMapConfiguration.Builder#setSelectSystemPreferred(boolean)} method)
     * the system provider will be chosen (if it is available).
     * If there is no an available provider, the first provider from the list will be chosen as the current provider.
     *
     * @param context       The {@link Context} instance.
     * @param configuration The {@link OPFMapConfiguration} object, which will be used to select the current map provider.
     */
    @SuppressWarnings("PMD.NPathComplexity")
    @MainThread
    public void init(@NonNull final Context context,
                     @NonNull final OPFMapConfiguration configuration) {
        OPFChecks.checkThread(true);

        final List<OPFMapProvider> providers = new ArrayList<>(configuration.getProviders());
        if (providers.isEmpty()) {
            throw new IllegalArgumentException("Providers list can't be empty");
        }

        if (configuration.isSelectSystemPreferred()) {
            Collections.sort(providers, new Comparator<OPFMapProvider>() {
                @Override
                public int compare(final OPFMapProvider leftProvider, final OPFMapProvider rightProvider) {
                    final String leftProviderHostAppPackage = leftProvider.getHostAppPackage();
                    final String rightProviderHostAppPackage = rightProvider.getHostAppPackage();

                    final int leftWeight = leftProviderHostAppPackage != null && OPFUtils.isSystemApp(context, leftProviderHostAppPackage) ? 1 : 0;
                    final int rightWeight = rightProviderHostAppPackage != null && OPFUtils.isSystemApp(context, rightProviderHostAppPackage) ? 1 : 0;
                    return rightWeight - leftWeight;
                }
            });
        }

        OPFMapProvider availableProvider = null;
        //get first available provider
        for (OPFMapProvider provider : providers) {
            if (!provider.isAvailable(context)) {
                OPFLog.d("Provider %s is no available.", provider.getName());
            } else if (!provider.isKeyPresented(context)) {
                OPFLog.w("Key isn't presented for provider %s", provider.getName());
            } else {
                availableProvider = provider;
                break;
            }
        }

        currentProvider = availableProvider != null ? availableProvider : providers.get(0);
    }

    /**
     * Returns the current map provider. The current map provider is the first available provider among the providers list
     * which was contained by {@link OPFMapConfiguration} object (or system provider if you set {@code true} value to the
     * {@link org.onepf.opfmaps.OPFMapConfiguration.Builder#setSelectSystemPreferred(boolean)} method).
     * If there is no an available provider the first provider from the list will be chosen.
     * <p/>
     * NOTE. You must call {@link #init(Context, OPFMapConfiguration)} first. You must call this method in the main thread.
     *
     * @return The current map provider.
     */
    @MainThread
    @NonNull
    public OPFMapProvider getCurrentProvider() {
        OPFChecks.checkThread(true);
        if (currentProvider == null) {
            throw new InitException(false);
        }

        return currentProvider;
    }

    /**
     * Intended for the internal use, should never be called directly.
     *
     * @return The {@link DelegatesAbstractFactory} instance.
     */
    @MainThread
    @NonNull
    public DelegatesAbstractFactory getDelegatesFactory() {
        return getCurrentProvider().getDelegatesFactory();
    }
}
