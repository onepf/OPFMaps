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
import android.support.annotation.Nullable;
import org.onepf.opfmaps.model.OPFBitmapDescriptor;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFLatLngBounds;

/**
 * @author Roman Savin
 * @since 29.07.2015
 */
public interface GroundOverlayOptionsDelegate extends Parcelable {

    @NonNull
    GroundOverlayOptionsDelegate anchor(final float u, final float v);

    @NonNull
    GroundOverlayOptionsDelegate bearing(final float bearing);

    float getAnchorU();

    float getAnchorV();

    float getBearing();

    @Nullable
    OPFLatLngBounds getBounds();

    float getHeight();

    @Nullable
    OPFBitmapDescriptor getImage();

    @Nullable
    OPFLatLng getLocation();

    float getTransparency();

    float getWidth();

    float getZIndex();

    @NonNull
    GroundOverlayOptionsDelegate image(@NonNull final OPFBitmapDescriptor image);

    boolean isVisible();

    @NonNull
    GroundOverlayOptionsDelegate position(@NonNull final OPFLatLng location, final float width, final float height);

    @NonNull
    GroundOverlayOptionsDelegate position(@NonNull final OPFLatLng location, final float width);

    @NonNull
    GroundOverlayOptionsDelegate positionFromBounds(@NonNull final OPFLatLngBounds bounds);

    @NonNull
    GroundOverlayOptionsDelegate transparency(final float transparency);

    @NonNull
    GroundOverlayOptionsDelegate visible(final boolean visible);

    @NonNull
    GroundOverlayOptionsDelegate zIndex(final float zIndex);
}
