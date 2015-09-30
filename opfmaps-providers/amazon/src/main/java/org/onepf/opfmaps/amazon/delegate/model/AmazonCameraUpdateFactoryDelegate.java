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

package org.onepf.opfmaps.amazon.delegate.model;

import android.graphics.Point;
import android.support.annotation.NonNull;
import com.amazon.geo.mapsv2.CameraUpdateFactory;
import com.amazon.geo.mapsv2.model.CameraPosition;
import com.amazon.geo.mapsv2.model.LatLng;
import com.amazon.geo.mapsv2.model.LatLngBounds;
import org.onepf.opfmaps.delegate.model.CameraUpdateFactoryDelegate;
import org.onepf.opfmaps.model.OPFCameraPosition;
import org.onepf.opfmaps.model.OPFCameraUpdate;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFLatLngBounds;

/**
 * @author Roman Savin
 * @since 06.08.2015
 */
public final class AmazonCameraUpdateFactoryDelegate implements CameraUpdateFactoryDelegate {

    @NonNull
    @Override
    public OPFCameraUpdate newCameraPosition(@NonNull final OPFCameraPosition cameraPosition) {
        return new OPFCameraUpdate(new AmazonCameraUpdateDelegate(CameraUpdateFactory.newCameraPosition(
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
        return new OPFCameraUpdate(new AmazonCameraUpdateDelegate(CameraUpdateFactory.newLatLng(
                new LatLng(latLng.getLat(), latLng.getLng())
        )));
    }

    @NonNull
    @Override
    public OPFCameraUpdate newLatLngBounds(@NonNull final OPFLatLngBounds bounds, final int padding) {
        return new OPFCameraUpdate(new AmazonCameraUpdateDelegate(CameraUpdateFactory.newLatLngBounds(
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

        return new OPFCameraUpdate(new AmazonCameraUpdateDelegate(CameraUpdateFactory.newLatLngBounds(
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
        return new OPFCameraUpdate(new AmazonCameraUpdateDelegate(CameraUpdateFactory.newLatLngZoom(
                new LatLng(latLng.getLat(), latLng.getLng()),
                zoom
        )));
    }

    @NonNull
    @Override
    public OPFCameraUpdate scrollBy(final float xPixel, final float yPixel) {
        return new OPFCameraUpdate(new AmazonCameraUpdateDelegate(CameraUpdateFactory.scrollBy(xPixel, yPixel)));
    }

    @NonNull
    @Override
    public OPFCameraUpdate zoomBy(final float amount, @NonNull final Point focus) {
        return new OPFCameraUpdate(new AmazonCameraUpdateDelegate(CameraUpdateFactory.zoomBy(amount, focus)));
    }

    @NonNull
    @Override
    public OPFCameraUpdate zoomBy(final float amount) {
        return new OPFCameraUpdate(new AmazonCameraUpdateDelegate(CameraUpdateFactory.zoomBy(amount)));
    }

    @NonNull
    @Override
    public OPFCameraUpdate zoomIn() {
        return new OPFCameraUpdate(new AmazonCameraUpdateDelegate(CameraUpdateFactory.zoomIn()));
    }

    @NonNull
    @Override
    public OPFCameraUpdate zoomOut() {
        return new OPFCameraUpdate(new AmazonCameraUpdateDelegate(CameraUpdateFactory.zoomOut()));
    }

    @NonNull
    @Override
    public OPFCameraUpdate zoomTo(final float zoom) {
        return new OPFCameraUpdate(new AmazonCameraUpdateDelegate(CameraUpdateFactory.zoomTo(zoom)));
    }
}
