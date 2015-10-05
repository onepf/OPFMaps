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

/**
 * An interface which represents the provider of the map.
 *
 * @author Roman Savin
 * @since 23.06.2015
 */
public interface OPFMapProvider {

    /**
     * Returns the name of the provider. It usually matches to this provider class name.
     *
     * @return The name of the provider.
     */
    @NonNull
    String getName();

    /**
     * Intended for the internal use, should never be called directly.
     *
     * @return The {@link DelegatesAbstractFactory} instance.
     */
    @NonNull
    DelegatesAbstractFactory getDelegatesFactory();

    /**
     * Returns the host app package of this provider or null if the provider hasn't host app.
     *
     * @return The host app package.
     */
    @Nullable
    String getHostAppPackage();

    /**
     * Returns {@code true} if this provider is available, {@code false} otherwise.
     *
     * @param context The {@link Context} instance.
     * @return {@code true} if this provider is available, {@code false} otherwise.
     */
    boolean isAvailable(@NonNull final Context context);

    /**
     * Returns {@code true} if api key is presented or there is no api key for this provider, {@code false} otherwise.
     *
     * @param context The {@link Context} instance.
     * @return {@code true} if api key is presented or there is no api key for this provider, {@code false} otherwise.
     */
    boolean isKeyPresented(@NonNull final Context context);
}
