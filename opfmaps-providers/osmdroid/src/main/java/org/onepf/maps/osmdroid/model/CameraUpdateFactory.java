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

package org.onepf.maps.osmdroid.model;

import android.graphics.Point;
import android.support.annotation.NonNull;

import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.util.GeoPoint;

import static org.onepf.maps.osmdroid.model.CameraUpdate.CameraUpdateSource.BOUNDS_PADDING;
import static org.onepf.maps.osmdroid.model.CameraUpdate.CameraUpdateSource.BOUNDS_WIDTH_HEIGHT_PADDING;
import static org.onepf.maps.osmdroid.model.CameraUpdate.CameraUpdateSource.CAMERA_POSITION;
import static org.onepf.maps.osmdroid.model.CameraUpdate.CameraUpdateSource.GEOPOINT;
import static org.onepf.maps.osmdroid.model.CameraUpdate.CameraUpdateSource.GEOPOINT_ZOOM;
import static org.onepf.maps.osmdroid.model.CameraUpdate.CameraUpdateSource.SCROLL_BY;
import static org.onepf.maps.osmdroid.model.CameraUpdate.CameraUpdateSource.ZOOM_BY;
import static org.onepf.maps.osmdroid.model.CameraUpdate.CameraUpdateSource.ZOOM_BY_FOCUS;
import static org.onepf.maps.osmdroid.model.CameraUpdate.CameraUpdateSource.ZOOM_IN;
import static org.onepf.maps.osmdroid.model.CameraUpdate.CameraUpdateSource.ZOOM_OUT;
import static org.onepf.maps.osmdroid.model.CameraUpdate.CameraUpdateSource.ZOOM_TO;

/**
 * @author Roman Savin
 * @since 17.08.2015
 */
public final class CameraUpdateFactory {

    private CameraUpdateFactory() {
        throw new UnsupportedOperationException();
    }

    @NonNull
    public static CameraUpdate newCameraPosition(@NonNull final CameraPosition cameraPosition) {
        return new CameraUpdate.Builder(CAMERA_POSITION)
                .setCenter(cameraPosition.target)
                .setBearing(cameraPosition.bearing)
                .build();
    }

    @NonNull
    public static CameraUpdate newLatLng(@NonNull final GeoPoint center) {
        return new CameraUpdate.Builder(GEOPOINT).setCenter(center).build();
    }

    @NonNull
    public static CameraUpdate newLatLngBounds(@NonNull final BoundingBoxE6 bounds,
                                               final int padding) {
        return new CameraUpdate.Builder(BOUNDS_PADDING)
                .setBounds(bounds)
                .setPadding(padding)
                .build();
    }

    @NonNull
    public static CameraUpdate newLatLngBounds(@NonNull final BoundingBoxE6 bounds,
                                               final int width,
                                               final int height,
                                               final int padding) {
        return new CameraUpdate.Builder(BOUNDS_WIDTH_HEIGHT_PADDING)
                .setBounds(bounds)
                .setPadding(padding)
                .setWidth(width)
                .setHeight(height)
                .build();
    }

    @NonNull
    public static CameraUpdate newLatLngZoom(@NonNull final GeoPoint latLng,
                                             final float zoom) {
        return new CameraUpdate.Builder(GEOPOINT_ZOOM).setCenter(latLng).setZoom(zoom).build();
    }

    @NonNull
    public static CameraUpdate scrollBy(final float xPixel, final float yPixel) {
        return new CameraUpdate.Builder(SCROLL_BY).setXPixel(xPixel).setYPixel(yPixel).build();
    }

    @NonNull
    public static CameraUpdate zoomBy(final float amount, @NonNull final Point focus) {
        return new CameraUpdate.Builder(ZOOM_BY_FOCUS).setZoom(amount).setFocus(focus).build();
    }

    @NonNull
    public static CameraUpdate zoomBy(final float amount) {
        return new CameraUpdate.Builder(ZOOM_BY).setZoom(amount).build();
    }

    @NonNull
    public static CameraUpdate zoomIn() {
        return new CameraUpdate.Builder(ZOOM_IN).build();
    }

    @NonNull
    public static CameraUpdate zoomOut() {
        return new CameraUpdate.Builder(ZOOM_OUT).build();
    }

    @NonNull
    public static CameraUpdate zoomTo(final float zoom) {
        return new CameraUpdate.Builder(ZOOM_TO).setZoom(zoom).build();
    }
}
