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

package org.onepf.opfmaps.google.delegate.model;

import android.graphics.Point;
import android.support.annotation.NonNull;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import org.onepf.opfmaps.delegate.model.CameraUpdateFactoryDelegate;
import org.onepf.opfmaps.model.OPFCameraPosition;
import org.onepf.opfmaps.model.OPFCameraUpdate;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFLatLngBounds;

/**
 * @author Roman Savin
 * @since 06.08.2015
 */
public final class GoogleCameraUpdateFactoryDelegate implements CameraUpdateFactoryDelegate {

    @NonNull
    @Override
    public OPFCameraUpdate newCameraPosition(@NonNull final OPFCameraPosition cameraPosition) {
        return new OPFCameraUpdate(new GoogleCameraUpdateDelegate(CameraUpdateFactory.newCameraPosition(
                new CameraPosition(
                        new LatLng(cameraPosition.getTarget().getLat(), cameraPosition.getTarget().getLng()),
                        cameraPosition.getZoom(),
                        cameraPosition.getTilt(),
                        cameraPosition.getBearing()
                )
        )));
    }

    @NonNull
    @Override
    public OPFCameraUpdate newLatLng(@NonNull final OPFLatLng latLng) {
        return new OPFCameraUpdate(new GoogleCameraUpdateDelegate(CameraUpdateFactory.newLatLng(
                new LatLng(latLng.getLat(), latLng.getLng())
        )));
    }

    @NonNull
    @Override
    public OPFCameraUpdate newLatLngBounds(@NonNull final OPFLatLngBounds bounds, final int padding) {
        return new OPFCameraUpdate(new GoogleCameraUpdateDelegate(CameraUpdateFactory.newLatLngBounds(
                new LatLngBounds(
                        new LatLng(bounds.getSouthwest().getLat(), bounds.getSouthwest().getLng()),
                        new LatLng(bounds.getNortheast().getLat(), bounds.getNortheast().getLng())
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

        return new OPFCameraUpdate(new GoogleCameraUpdateDelegate(CameraUpdateFactory.newLatLngBounds(
                new LatLngBounds(
                        new LatLng(bounds.getSouthwest().getLat(), bounds.getSouthwest().getLng()),
                        new LatLng(bounds.getNortheast().getLat(), bounds.getNortheast().getLng())
                ),
                width,
                height,
                padding
        )));
    }

    @NonNull
    @Override
    public OPFCameraUpdate newLatLngZoom(@NonNull final OPFLatLng latLng, final float zoom) {
        return new OPFCameraUpdate(new GoogleCameraUpdateDelegate(CameraUpdateFactory.newLatLngZoom(
                new LatLng(latLng.getLat(), latLng.getLng()),
                zoom
        )));
    }

    @NonNull
    @Override
    public OPFCameraUpdate scrollBy(final float xPixel, final float yPixel) {
        return new OPFCameraUpdate(new GoogleCameraUpdateDelegate(CameraUpdateFactory.scrollBy(xPixel, yPixel)));
    }

    @NonNull
    @Override
    public OPFCameraUpdate zoomBy(final float amount, @NonNull final Point focus) {
        return new OPFCameraUpdate(new GoogleCameraUpdateDelegate(CameraUpdateFactory.zoomBy(amount, focus)));
    }

    @NonNull
    @Override
    public OPFCameraUpdate zoomBy(final float amount) {
        return new OPFCameraUpdate(new GoogleCameraUpdateDelegate(CameraUpdateFactory.zoomBy(amount)));
    }

    @NonNull
    @Override
    public OPFCameraUpdate zoomIn() {
        return new OPFCameraUpdate(new GoogleCameraUpdateDelegate(CameraUpdateFactory.zoomIn()));
    }

    @NonNull
    @Override
    public OPFCameraUpdate zoomOut() {
        return new OPFCameraUpdate(new GoogleCameraUpdateDelegate(CameraUpdateFactory.zoomOut()));
    }

    @NonNull
    @Override
    public OPFCameraUpdate zoomTo(final float zoom) {
        return new OPFCameraUpdate(new GoogleCameraUpdateDelegate(CameraUpdateFactory.zoomTo(zoom)));
    }
}
