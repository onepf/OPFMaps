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

package org.onepf.maps.amazon;

import android.content.Context;
import android.support.annotation.NonNull;

import com.amazon.geo.mapsv2.AmazonMapOptions;
import com.amazon.geo.mapsv2.model.LatLng;
import com.amazon.geo.mapsv2.model.LatLngBounds;

import org.onepf.maps.amazon.delegate.AmazonMapOptionsDelegate;
import org.onepf.maps.amazon.delegate.AmazonMapViewDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonBitmapDescriptorFactoryDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonCameraPositionDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonCameraUpdateFactoryDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonCircleOptionsDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonGroundOverlayOptionsDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonLatLngBoundsDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonLatLngDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonMarkerOptionsDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonPolygonOptionsDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonPolylineOptionsDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonTileDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonTileOverlayOptionsDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonUrlTileProviderDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonVisibleRegionDelegate;
import org.onepf.maps.amazon.utils.ConvertUtils;
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
public final class AmazonDelegatesFactory implements DelegatesAbstractFactory {

    @NonNull
    @Override
    public MapViewDelegate createMapViewDelegate(@NonNull final Context context) {
        return new AmazonMapViewDelegate(context);
    }

    @NonNull
    @Override
    public MapViewDelegate createMapViewDelegate(@NonNull final Context context, @NonNull final OPFMapOptions mapOptions) {
        return new AmazonMapViewDelegate(context, ConvertUtils.convertMapOptions(mapOptions));
    }

    @NonNull
    @Override
    public CircleOptionsDelegate createCircleOptionsDelegate() {
        return new AmazonCircleOptionsDelegate();
    }

    @NonNull
    @Override
    public GroundOverlayOptionsDelegate createGroundOverlayOptionsDelegate() {
        return new AmazonGroundOverlayOptionsDelegate();
    }

    @NonNull
    @Override
    public LatLngDelegate createLatLngDelegate(final double latitude, final double longitude) {
        return new AmazonLatLngDelegate(new LatLng(latitude, longitude));
    }

    @NonNull
    @Override
    public LatLngBoundsDelegate createLatLngBoundsDelegate(@NonNull final OPFLatLng southwest,
                                                           @NonNull final OPFLatLng northeast) {
        return new AmazonLatLngBoundsDelegate(new LatLngBounds(
                new LatLng(southwest.getLat(), southwest.getLng()),
                new LatLng(northeast.getLat(), northeast.getLng())
        ));
    }

    @NonNull
    @Override
    public LatLngBoundsDelegate.Builder createLatLngBoundsBuilderDelegate() {
        return new AmazonLatLngBoundsDelegate.Builder();
    }

    @NonNull
    @Override
    public MarkerOptionsDelegate createMarkerOptionsDelegate() {
        return new AmazonMarkerOptionsDelegate();
    }

    @NonNull
    @Override
    public PolygonOptionsDelegate createPolygonOptionsDelegate() {
        return new AmazonPolygonOptionsDelegate();
    }

    @NonNull
    @Override
    public PolylineOptionsDelegate createPolylineOptionsDelegate() {
        return new AmazonPolylineOptionsDelegate();
    }

    @NonNull
    @Override
    public TileDelegate createTileDelegate(final int width, final int height, @NonNull final byte[] data) {
        return new AmazonTileDelegate(width, height, data);
    }

    @NonNull
    @Override
    public TileOverlayOptionsDelegate createTileOverlayOptionDelegate() {
        return new AmazonTileOverlayOptionsDelegate();
    }

    @NonNull
    @Override
    public UrlTileProviderDelegate createUrlTileProviderDelegate(
            final int width,
            final int height,
            @NonNull final TileUrlProvider tileUrlProvider
    ) {
        return new AmazonUrlTileProviderDelegate(width, height, tileUrlProvider);
    }

    @NonNull
    @Override
    public BitmapDescriptorFactoryDelegate createBitmapDescriptorFactory() {
        return new AmazonBitmapDescriptorFactoryDelegate();
    }

    @NonNull
    @Override
    public CameraPositionDelegate createCameraPositionDelegate(@NonNull final OPFLatLng target, final float zoom) {
        return new AmazonCameraPositionDelegate(target, zoom);
    }

    @NonNull
    @Override
    public CameraPositionDelegate createCameraPositionDelegate(@NonNull final OPFLatLng target,
                                                               final float zoom,
                                                               final float tilt,
                                                               final float bearing) {
        return new AmazonCameraPositionDelegate(target, zoom, tilt, bearing);
    }

    @NonNull
    @Override
    public CameraPositionDelegate.Builder createCameraPositionBuilderDelegate() {
        return new AmazonCameraPositionDelegate.Builder();
    }

    @NonNull
    @Override
    public CameraPositionDelegate.Builder createCameraPositionBuilderDelegate(@NonNull final OPFCameraPosition camera) {
        return new AmazonCameraPositionDelegate.Builder(camera);
    }

    @NonNull
    @Override
    public CameraUpdateFactoryDelegate createCameraUpdateFactory() {
        return new AmazonCameraUpdateFactoryDelegate();
    }

    @NonNull
    @Override
    public VisibleRegionDelegate createVisibleRegionDelegate(@NonNull final OPFLatLng nearLeft,
                                                             @NonNull final OPFLatLng nearRight,
                                                             @NonNull final OPFLatLng farLeft,
                                                             @NonNull final OPFLatLng farRight,
                                                             @NonNull final OPFLatLngBounds latLngBounds) {
        return new AmazonVisibleRegionDelegate(nearLeft, nearRight, farLeft, farRight, latLngBounds);
    }

    @NonNull
    @Override
    public MapOptionsDelegate createMapOptionsDelegate() {
        return new AmazonMapOptionsDelegate(new AmazonMapOptions());
    }
}
