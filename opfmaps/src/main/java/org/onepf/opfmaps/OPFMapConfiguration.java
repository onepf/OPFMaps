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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The instance of this class is used as an argument of the {@link OPFMapHelper#init(Context, OPFMapConfiguration)}
 * method for configuring the {@link OPFMapHelper}.
 *
 * @author Roman Savin
 * @since 11.06.2015
 */
@SuppressWarnings("PMD.MissingStaticMethodInNonInstantiatableClass")
public final class OPFMapConfiguration {

    @NonNull
    private final List<OPFMapProvider> providers;

    private final boolean isSelectSystemPreferred;

    private OPFMapConfiguration(@NonNull final Collection<? extends OPFMapProvider> providers,
                                final boolean isSelectSystemPreferred) {
        this.providers = Collections.unmodifiableList(new ArrayList<>(providers));
        this.isSelectSystemPreferred = isSelectSystemPreferred;
    }

    /**
     * Returns all added map providers.
     *
     * @return All added map providers.
     */
    @NonNull
    public List<OPFMapProvider> getProviders() {
        return providers;
    }

    /**
     * Returns {@code true} if the system map provider is referred, false otherwise.
     *
     * @return {@code true} if the system map provider is referred, false otherwise.
     */
    public boolean isSelectSystemPreferred() {
        return isSelectSystemPreferred;
    }

    /**
     * The builder class that creates an instance of the {@link OPFMapConfiguration} class.
     */
    public static class Builder {

        @Nullable
        private Map<String, OPFMapProvider> providersMap;

        private boolean isSelectSystemPreferred;

        /**
         * See {@link #addProviders(List)})}
         *
         * @param providers Added providers.
         * @return This {@link org.onepf.opfmaps.OPFMapConfiguration.Builder} object.
         */
        public Builder addProviders(@NonNull final OPFMapProvider... providers) {
            return addProviders(Arrays.asList(providers));
        }

        /**
         * Add push providers to the configuration. The priority of the providers corresponds to the order in which they
         * were added.
         *
         * @param providers Added providers.
         * @return This {@link org.onepf.opfmaps.OPFMapConfiguration.Builder} object.
         */
        public Builder addProviders(@NonNull final List<? extends OPFMapProvider> providers) {
            if (providers.isEmpty()) {
                return this;
            }

            if (this.providersMap == null) {
                this.providersMap = new LinkedHashMap<>();
            }

            for (OPFMapProvider provider : providers) {
                final String providerName = provider.getName();
                if (this.providersMap.containsKey(providerName)) {
                    throw new IllegalArgumentException(
                            String.format("Provider '%s' already added.", provider)
                    );
                } else {
                    this.providersMap.put(providerName, provider);
                }
            }
            return this;
        }

        /**
         * If you set {@code true}, the system push provider will get the highest priority.
         * For Google devices with preinstalled Google Play app the system provider is Google Maps.
         * For Kindle devices - Amazon Maps.
         * Default value is {@code false}.
         *
         * @param isSelectSystemPreferred {@code true} if the system provider is preferred, {@code false} otherwise.
         * @return This {@link org.onepf.opfmaps.OPFMapConfiguration.Builder} object.
         */
        public Builder setSelectSystemPreferred(final boolean isSelectSystemPreferred) {
            this.isSelectSystemPreferred = isSelectSystemPreferred;
            return this;
        }

        /**
         * Creates the instance of the {@link OPFMapConfiguration} class.
         *
         * @return The new {@link OPFMapConfiguration} object.
         * @throws IllegalArgumentException If there are no any added providers.
         */
        @SuppressWarnings("PMD.AccessorClassGeneration")
        public OPFMapConfiguration build() {
            if (providersMap == null) {
                throw new IllegalArgumentException("You must add at least one opfmap provider.");
            }

            return new OPFMapConfiguration(providersMap.values(), isSelectSystemPreferred);
        }
    }
}
