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

package org.onepf.opfmaps.delegate.model;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import org.onepf.opfmaps.model.OPFTileProvider;

import javax.annotation.Nullable;

/**
 * @author Roman Savin
 * @since 30.07.2015
 */
public interface TileOverlayOptionsDelegate extends Parcelable {

    @NonNull
    TileOverlayOptionsDelegate fadeIn(final boolean fadeIn);

    boolean getFadeIn();

    @Nullable
    OPFTileProvider getTileProvider();

    float getZIndex();

    boolean isVisible();

    @NonNull
    TileOverlayOptionsDelegate tileProvider(@NonNull OPFTileProvider tileProvider);

    @NonNull
    TileOverlayOptionsDelegate visible(final boolean visible);

    @NonNull
    TileOverlayOptionsDelegate zIndex(final float zIndex);
}
