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

package org.onepf.maps.osmdroid.delegate.model;

import android.graphics.Point;
import android.support.annotation.NonNull;

import org.onepf.maps.osmdroid.model.CameraPosition;
import org.onepf.maps.osmdroid.model.CameraUpdateFactory;
import org.onepf.opfmaps.delegate.model.CameraUpdateFactoryDelegate;
import org.onepf.opfmaps.model.OPFCameraPosition;
import org.onepf.opfmaps.model.OPFCameraUpdate;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFLatLngBounds;
import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.util.GeoPoint;

/**
 * @author Roman Savin
 * @since 06.08.2015
 */
public final class OsmdroidCameraUpdateFactoryDelegate implements CameraUpdateFactoryDelegate {

    @NonNull
    @Override
    public OPFCameraUpdate newCameraPosition(@NonNull final OPFCameraPosition cameraPosition) {
        return new OPFCameraUpdate(new OsmdroidCameraUpdateDelegate(CameraUpdateFactory.newCameraPosition(
                new CameraPosition(
                        new GeoPoint(cameraPosition.getTarget().getLat(), cameraPosition.getTarget().getLng()),
                        cameraPosition.getZoom(),
                        cameraPosition.getTilt(),
                        cameraPosition.getBearing()
                )
        )));
    }

    @NonNull
    @Override
    public OPFCameraUpdate newLatLng(@NonNull final OPFLatLng latLng) {
        return new OPFCameraUpdate(new OsmdroidCameraUpdateDelegate(CameraUpdateFactory.newLatLng(
                new GeoPoint(latLng.getLat(), latLng.getLng())
        )));
    }

    @NonNull
    @Override
    public OPFCameraUpdate newLatLngBounds(@NonNull final OPFLatLngBounds bounds, final int padding) {
        return new OPFCameraUpdate(new OsmdroidCameraUpdateDelegate(CameraUpdateFactory.newLatLngBounds(
                new BoundingBoxE6(
                        bounds.getNortheast().getLat(),
                        bounds.getNortheast().getLng(),
                        bounds.getSouthwest().getLat(),
                        bounds.getSouthwest().getLng()
                ),
                padding
        )));
    }

    @NonNull
    @Override
    public OPFCameraUpdate newLatLngBounds(@NonNull final OPFLatLngBounds bounds,
                                           final int width,
                                           final int height,
                                           final int padding) {
        return new OPFCameraUpdate(new OsmdroidCameraUpdateDelegate(CameraUpdateFactory.newLatLngBounds(
                new BoundingBoxE6(
                        bounds.getNortheast().getLat(),
                        bounds.getNortheast().getLng(),
                        bounds.getSouthwest().getLat(),
                        bounds.getSouthwest().getLng()
                ),
                width,
                height,
                padding
        )));
    }

    @NonNull
    @Override
    public OPFCameraUpdate newLatLngZoom(@NonNull final OPFLatLng latLng, final float zoom) {
        return new OPFCameraUpdate(new OsmdroidCameraUpdateDelegate(CameraUpdateFactory.newLatLngZoom(
                new GeoPoint(latLng.getLat(), latLng.getLng()),
                zoom
        )));
    }

    @NonNull
    @Override
    public OPFCameraUpdate scrollBy(final float xPixel, final float yPixel) {
        return new OPFCameraUpdate(new OsmdroidCameraUpdateDelegate(CameraUpdateFactory.scrollBy(xPixel, yPixel)));
    }

    @NonNull
    @Override
    public OPFCameraUpdate zoomBy(final float amount, @NonNull final Point focus) {
        return new OPFCameraUpdate(new OsmdroidCameraUpdateDelegate(CameraUpdateFactory.zoomBy(amount, focus)));
    }

    @NonNull
    @Override
    public OPFCameraUpdate zoomBy(final float amount) {
        return new OPFCameraUpdate(new OsmdroidCameraUpdateDelegate(CameraUpdateFactory.zoomBy(amount)));
    }

    @NonNull
    @Override
    public OPFCameraUpdate zoomIn() {
        return new OPFCameraUpdate(new OsmdroidCameraUpdateDelegate(CameraUpdateFactory.zoomIn()));
    }

    @NonNull
    @Override
    public OPFCameraUpdate zoomOut() {
        return new OPFCameraUpdate(new OsmdroidCameraUpdateDelegate(CameraUpdateFactory.zoomOut()));
    }

    @NonNull
    @Override
    public OPFCameraUpdate zoomTo(final float zoom) {
        return new OPFCameraUpdate(new OsmdroidCameraUpdateDelegate(CameraUpdateFactory.zoomTo(zoom)));
    }
}
