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

/**
 * @author Roman Savin
 * @since 29.07.2015
 */
public interface MarkerOptionsDelegate extends Parcelable {

    @NonNull
    MarkerOptionsDelegate alpha(final float alpha);

    @NonNull
    MarkerOptionsDelegate anchor(final float u, final float v);

    @NonNull
    MarkerOptionsDelegate draggable(final boolean draggable);

    @NonNull
    MarkerOptionsDelegate flat(final boolean flat);

    float getAlpha();

    float getAnchorU();

    float getAnchorV();

    @Nullable
    OPFBitmapDescriptor getIcon();

    float getInfoWindowAnchorU();

    float getInfoWindowAnchorV();

    @Nullable
    OPFLatLng getPosition();

    float getRotation();

    @Nullable
    String getSnippet();

    @Nullable
    String getTitle();

    @NonNull
    MarkerOptionsDelegate icon(@NonNull final OPFBitmapDescriptor icon);

    @NonNull
    MarkerOptionsDelegate infoWindowAnchor(final float u, final float v);

    boolean isDraggable();

    boolean isFlat();

    boolean isVisible();

    @NonNull
    MarkerOptionsDelegate position(@NonNull final OPFLatLng position);

    @NonNull
    MarkerOptionsDelegate rotation(final float rotation);

    @NonNull
    MarkerOptionsDelegate snippet(@NonNull final String snippet);

    @NonNull
    MarkerOptionsDelegate title(@NonNull final String title);

    @NonNull
    MarkerOptionsDelegate visible(final boolean visible);
}
