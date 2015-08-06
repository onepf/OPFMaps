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

package org.onepf.opfmaps.model;

import android.graphics.Point;
import android.support.annotation.NonNull;
import org.onepf.opfmaps.OPFMapHelper;

/**
 * @author Roman Savin
 * @since 06.08.2015
 */
public final class OPFCameraUpdateFactory {

    private OPFCameraUpdateFactory() {
        throw new UnsupportedOperationException();
    }

    @NonNull
    public static OPFCameraUpdate newCameraPosition(@NonNull final OPFCameraPosition cameraPosition) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createCameraUpdateFactory()
                .newCameraPosition(cameraPosition);
    }

    @NonNull
    public static OPFCameraUpdate newLatLng(@NonNull final OPFLatLng latLng) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createCameraUpdateFactory().newLatLng(latLng);
    }

    @NonNull
    public static OPFCameraUpdate newLatLngBounds(@NonNull final OPFLatLngBounds bounds, final int padding) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createCameraUpdateFactory()
                .newLatLngBounds(bounds, padding);
    }

    @NonNull
    public static OPFCameraUpdate newLatLngBounds(@NonNull final OPFLatLngBounds bounds,
                                                  final int width,
                                                  final int height,
                                                  final int padding) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createCameraUpdateFactory()
                .newLatLngBounds(bounds, width, height, padding);
    }

    @NonNull
    public static OPFCameraUpdate newLatLngZoom(@NonNull final OPFLatLng latLng, final float zoom) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createCameraUpdateFactory().newLatLngZoom(latLng, zoom);
    }

    @NonNull
    public static OPFCameraUpdate scrollBy(final float xPixel, final float yPixel) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createCameraUpdateFactory().scrollBy(xPixel, yPixel);
    }

    @NonNull
    public static OPFCameraUpdate zoomBy(final float amount, @NonNull final Point focus) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createCameraUpdateFactory().zoomBy(amount, focus);
    }

    @NonNull
    public static OPFCameraUpdate zoomBy(final float amount) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createCameraUpdateFactory().zoomBy(amount);
    }

    @NonNull
    public static OPFCameraUpdate zoomIn() {
        return OPFMapHelper.getInstance().getDelegatesFactory().createCameraUpdateFactory().zoomIn();
    }

    @NonNull
    public static OPFCameraUpdate zoomOut() {
        return OPFMapHelper.getInstance().getDelegatesFactory().createCameraUpdateFactory().zoomOut();
    }

    @NonNull
    public static OPFCameraUpdate zoomTo(final float zoom) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createCameraUpdateFactory().zoomTo(zoom);
    }
}
