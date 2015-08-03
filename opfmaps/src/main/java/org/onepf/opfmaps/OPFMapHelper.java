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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import org.onepf.opfmaps.factory.DelegatesAbstractFactory;
import org.onepf.opfutils.OPFUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
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

    @SuppressWarnings("PMD.NPathComplexity")
    public void init(@NonNull final Context context,
                     @NonNull final OPFMapConfiguration configuration) {
        //todo call only from main thread

        final List<OPFMapProvider> providers = new ArrayList<>(configuration.getProviders());
        //todo if there aren't available provider, choose first
        if (configuration.isSelectSystemPreferred()) {
            Collections.sort(providers, new Comparator<OPFMapProvider>() {
                @Override
                public int compare(final OPFMapProvider leftProvider, final OPFMapProvider rightProvider) {
                    final int leftWeight = OPFUtils.isSystemApp(context, leftProvider.getHostAppPackage()) ? 1 : 0;
                    final int rightWeight = OPFUtils.isSystemApp(context, rightProvider.getHostAppPackage()) ? 1 : 0;
                    return rightWeight - leftWeight;
                }
            });
        }

        //get first available provider
        for (OPFMapProvider provider : providers) {
            if (provider.isAvailable(context) && provider.hasRequiredPermissions(context)
                    && provider.isKeyPresented(context) && provider.hasRequestedFeatures(context)) {
                currentProvider = provider;
            }
        }

        //todo check
        if (currentProvider == null) {
            currentProvider = providers.get(0);
        }
    }

    @SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes") //todo remove
    @NonNull
    public DelegatesAbstractFactory getDelegatesFactory() {
        if (currentProvider == null) {
            //todo throw init exception
            throw new RuntimeException("Init");
        }

        return currentProvider.getDelegatesFactory();
    }
}
