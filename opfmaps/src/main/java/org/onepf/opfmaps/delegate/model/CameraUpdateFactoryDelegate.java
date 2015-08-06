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

import android.graphics.Point;
import android.support.annotation.NonNull;
import org.onepf.opfmaps.model.OPFCameraPosition;
import org.onepf.opfmaps.model.OPFCameraUpdate;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFLatLngBounds;

/**
 * @author Roman Savin
 * @since 06.08.2015
 */
public interface CameraUpdateFactoryDelegate {

    @NonNull
    OPFCameraUpdate newCameraPosition(@NonNull final OPFCameraPosition cameraPosition);

    @NonNull
    OPFCameraUpdate newLatLng(@NonNull final OPFLatLng latLng);

    @NonNull
    OPFCameraUpdate newLatLngBounds(@NonNull final OPFLatLngBounds bounds, final int padding);

    @NonNull
    OPFCameraUpdate newLatLngBounds(@NonNull final OPFLatLngBounds bounds,
                                    final int width,
                                    final int height,
                                    final int padding);

    @NonNull
    OPFCameraUpdate newLatLngZoom(@NonNull final OPFLatLng latLng, final float zoom);

    @NonNull
    OPFCameraUpdate scrollBy(final float xPixel, final float yPixel);

    @NonNull
    OPFCameraUpdate zoomBy(final float amount, @NonNull final Point focus);

    @NonNull
    OPFCameraUpdate zoomBy(final float amount);

    @NonNull
    OPFCameraUpdate zoomIn();

    @NonNull
    OPFCameraUpdate zoomOut();

    @NonNull
    OPFCameraUpdate zoomTo(final float zoom);
}
