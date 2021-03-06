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

package org.onepf.opfmaps.google;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import org.onepf.opfmaps.google.delegate.GoogleMapOptionsDelegate;
import org.onepf.opfmaps.google.delegate.GoogleMapViewDelegate;
import org.onepf.opfmaps.google.delegate.model.GoogleBitmapDescriptorFactoryDelegate;
import org.onepf.opfmaps.google.delegate.model.GoogleCameraPositionDelegate;
import org.onepf.opfmaps.google.delegate.model.GoogleCameraUpdateFactoryDelegate;
import org.onepf.opfmaps.google.delegate.model.GoogleCircleOptionsDelegate;
import org.onepf.opfmaps.google.delegate.model.GoogleGroundOverlayOptionsDelegate;
import org.onepf.opfmaps.google.delegate.model.GoogleLatLngBoundsDelegate;
import org.onepf.opfmaps.google.delegate.model.GoogleLatLngDelegate;
import org.onepf.opfmaps.google.delegate.model.GoogleMarkerOptionsDelegate;
import org.onepf.opfmaps.google.delegate.model.GooglePolygonOptionsDelegate;
import org.onepf.opfmaps.google.delegate.model.GooglePolylineOptionsDelegate;
import org.onepf.opfmaps.google.delegate.model.GoogleTileDelegate;
import org.onepf.opfmaps.google.delegate.model.GoogleTileOverlayOptionsDelegate;
import org.onepf.opfmaps.google.delegate.model.GoogleUrlTileProviderDelegate;
import org.onepf.opfmaps.google.delegate.model.GoogleVisibleRegionDelegate;
import org.onepf.opfmaps.google.utils.ConvertUtils;
import org.onepf.opfmaps.OPFMapOptions;
import org.onepf.opfmaps.delegate.MapOptionsDelegate;
import org.onepf.opfmaps.delegate.MapViewDelegate;
import org.onepf.opfmaps.delegate.model.BitmapDescriptorFactoryDelegate;
import org.onepf.opfmaps.delegate.model.CameraPositionDelegate;
import org.onepf.opfmaps.delegate.model.CameraUpdateFactoryDelegate;
import org.onepf.opfmaps.delegate.model.CircleOptionsDelegate;
import org.onepf.opfmaps.delegate.model.GroundOverlayOptionsDelegate;
import org.onepf.opfmaps.delegate.model.LatLngBoundsDelegate;
import org.onepf.opfmaps.delegate.model.LatLngDelegate;
import org.onepf.opfmaps.delegate.model.MarkerOptionsDelegate;
import org.onepf.opfmaps.delegate.model.PolygonOptionsDelegate;
import org.onepf.opfmaps.delegate.model.PolylineOptionsDelegate;
import org.onepf.opfmaps.delegate.model.TileDelegate;
import org.onepf.opfmaps.delegate.model.TileOverlayOptionsDelegate;
import org.onepf.opfmaps.delegate.model.UrlTileProviderDelegate;
import org.onepf.opfmaps.delegate.model.VisibleRegionDelegate;
import org.onepf.opfmaps.factory.DelegatesAbstractFactory;
import org.onepf.opfmaps.model.OPFCameraPosition;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFLatLngBounds;
import org.onepf.opfmaps.model.OPFUrlTileProvider.TileUrlProvider;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
@SuppressWarnings("PMD.CouplingBetweenObjects")
public final class GoogleDelegatesFactory implements DelegatesAbstractFactory {

    @NonNull
    @Override
    public MapViewDelegate createMapViewDelegate(@NonNull final Context context) {
        return new GoogleMapViewDelegate(context);
    }

    @NonNull
    @Override
    public MapViewDelegate createMapViewDelegate(@NonNull final Context context, @NonNull final OPFMapOptions mapOptions) {
        return new GoogleMapViewDelegate(context, ConvertUtils.convertMapOptions(mapOptions));
    }

    @NonNull
    @Override
    public CircleOptionsDelegate createCircleOptionsDelegate() {
        return new GoogleCircleOptionsDelegate();
    }

    @NonNull
    @Override
    public GroundOverlayOptionsDelegate createGroundOverlayOptionsDelegate() {
        return new GoogleGroundOverlayOptionsDelegate();
    }

    @NonNull
    @Override
    public LatLngDelegate createLatLngDelegate(final double latitude, final double longitude) {
        return new GoogleLatLngDelegate(new LatLng(latitude, longitude));
    }

    @NonNull
    @Override
    public LatLngBoundsDelegate createLatLngBoundsDelegate(@NonNull final OPFLatLng southwest,
                                                           @NonNull final OPFLatLng northeast) {
        return new GoogleLatLngBoundsDelegate(new LatLngBounds(
                new LatLng(southwest.getLat(), southwest.getLng()),
                new LatLng(northeast.getLat(), northeast.getLng())
        ));
    }

    @NonNull
    @Override
    public LatLngBoundsDelegate.Builder createLatLngBoundsBuilderDelegate() {
        return new GoogleLatLngBoundsDelegate.Builder();
    }

    @NonNull
    @Override
    public MarkerOptionsDelegate createMarkerOptionsDelegate() {
        return new GoogleMarkerOptionsDelegate();
    }

    @NonNull
    @Override
    public PolygonOptionsDelegate createPolygonOptionsDelegate() {
        return new GooglePolygonOptionsDelegate();
    }

    @NonNull
    @Override
    public PolylineOptionsDelegate createPolylineOptionsDelegate() {
        return new GooglePolylineOptionsDelegate();
    }

    @NonNull
    @Override
    public TileDelegate createTileDelegate(final int width, final int height, @NonNull final byte[] data) {
        return new GoogleTileDelegate(width, height, data);
    }

    @NonNull
    @Override
    public TileOverlayOptionsDelegate createTileOverlayOptionDelegate() {
        return new GoogleTileOverlayOptionsDelegate();
    }

    @NonNull
    @Override
    public UrlTileProviderDelegate createUrlTileProviderDelegate(
            final int width,
            final int height,
            @NonNull final TileUrlProvider tileUrlProvider
    ) {
        return new GoogleUrlTileProviderDelegate(width, height, tileUrlProvider);
    }

    @NonNull
    @Override
    public BitmapDescriptorFactoryDelegate createBitmapDescriptorFactory() {
        return new GoogleBitmapDescriptorFactoryDelegate();
    }

    @NonNull
    @Override
    public CameraPositionDelegate createCameraPositionDelegate(@NonNull final OPFLatLng target, final float zoom) {
        return new GoogleCameraPositionDelegate(target, zoom);
    }

    @NonNull
    @Override
    public CameraPositionDelegate createCameraPositionDelegate(@NonNull final OPFLatLng target,
                                                               final float zoom,
                                                               final float tilt,
                                                               final float bearing) {
        return new GoogleCameraPositionDelegate(target, zoom, tilt, bearing);
    }

    @NonNull
    @Override
    public CameraPositionDelegate.Builder createCameraPositionBuilderDelegate() {
        return new GoogleCameraPositionDelegate.Builder();
    }

    @NonNull
    @Override
    public CameraPositionDelegate.Builder createCameraPositionBuilderDelegate(@NonNull final OPFCameraPosition camera) {
        return new GoogleCameraPositionDelegate.Builder(camera);
    }

    @NonNull
    @Override
    public CameraUpdateFactoryDelegate createCameraUpdateFactory() {
        return new GoogleCameraUpdateFactoryDelegate();
    }

    @NonNull
    @Override
    public VisibleRegionDelegate createVisibleRegionDelegate(@NonNull final OPFLatLng nearLeft,
                                                             @NonNull final OPFLatLng nearRight,
                                                             @NonNull final OPFLatLng farLeft,
                                                             @NonNull final OPFLatLng farRight,
                                                             @NonNull final OPFLatLngBounds latLngBounds) {
        return new GoogleVisibleRegionDelegate(nearLeft, nearRight, farLeft, farRight, latLngBounds);
    }

    @NonNull
    @Override
    public MapOptionsDelegate createMapOptionsDelegate() {
        return new GoogleMapOptionsDelegate(new GoogleMapOptions());
    }
}
