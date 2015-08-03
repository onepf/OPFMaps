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
 * Created by akarimova on 11.06.15.
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

    @NonNull
    public List<OPFMapProvider> getProviders() {
        return providers;
    }

    public boolean isSelectSystemPreferred() {
        return isSelectSystemPreferred;
    }

    public static class Builder {

        @Nullable
        private Map<String, OPFMapProvider> providersMap;

        private boolean isSelectSystemPreferred;

        public Builder addProviders(@NonNull final OPFMapProvider... providers) {
            return addProviders(Arrays.asList(providers));
        }

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

        public Builder setSelectSystemPreferred(final boolean isSelectSystemPreferred) {
            this.isSelectSystemPreferred = isSelectSystemPreferred;
            return this;
        }

        @SuppressWarnings("PMD.AccessorClassGeneration")
        public OPFMapConfiguration build() {
            if (providersMap == null) {
                throw new IllegalArgumentException("You must add at least one opfmap provider.");
            }

            return new OPFMapConfiguration(providersMap.values(), isSelectSystemPreferred);
        }
    }
}
