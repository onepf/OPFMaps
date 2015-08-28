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

package org.onepf.maps.osmdroid;

import android.content.Context;
import android.support.annotation.NonNull;
import org.onepf.maps.osmdroid.delegate.OsmdroidMapOptionsDelegate;
import org.onepf.maps.osmdroid.delegate.OsmdroidMapViewDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidBitmapDescriptorFactoryDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidCameraPositionDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidCameraUpdateFactoryDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidCircleOptionsDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidGroundOverlayOptionsDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidLatLngBoundsDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidLatLngDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidMarkerOptionsDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidPolygonOptionsDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidPolylineOptionsDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidTileDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidTileOverlayOptionsDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidUrlTileProviderDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidVisibleRegionDelegate;
import org.onepf.maps.osmdroid.model.OsmdroidMapOptions;
import org.onepf.maps.osmdroid.utils.ConvertUtils;
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
import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.util.GeoPoint;

/**
 * @author Roman Savin
 * @since 12.08.2015
 */
public final class OsmdroidDelegatesFactory implements DelegatesAbstractFactory {

    @NonNull
    @Override
    public MapViewDelegate createMapViewDelegate(@NonNull final Context context) {
        return new OsmdroidMapViewDelegate(context);
    }

    @NonNull
    @Override
    public MapViewDelegate createMapViewDelegate(@NonNull final Context context,
                                                 @NonNull final OPFMapOptions mapOptions) {
        return new OsmdroidMapViewDelegate(context, ConvertUtils.convertMapOptions(mapOptions));
    }

    @NonNull
    @Override
    public CircleOptionsDelegate createCircleOptionsDelegate() {
        return new OsmdroidCircleOptionsDelegate();
    }

    @NonNull
    @Override
    public GroundOverlayOptionsDelegate createGroundOverlayOptionsDelegate() {
        return new OsmdroidGroundOverlayOptionsDelegate();
    }

    @NonNull
    @Override
    public LatLngDelegate createLatLngDelegate(final double latitude, final double longitude) {
        return new OsmdroidLatLngDelegate(new GeoPoint(latitude, longitude));
    }

    @NonNull
    @Override
    public LatLngBoundsDelegate createLatLngBoundsDelegate(@NonNull final OPFLatLng southwest,
                                                           @NonNull final OPFLatLng northeast) {
        return new OsmdroidLatLngBoundsDelegate(new BoundingBoxE6(
                northeast.getLat(),
                northeast.getLng(),
                southwest.getLat(),
                southwest.getLng()
        ));
    }

    @NonNull
    @Override
    public LatLngBoundsDelegate.Builder createLatLngBoundsBuilderDelegate() {
        return new OsmdroidLatLngBoundsDelegate.Builder();
    }

    @NonNull
    @Override
    public MarkerOptionsDelegate createMarkerOptionsDelegate() {
        return new OsmdroidMarkerOptionsDelegate();
    }

    @NonNull
    @Override
    public PolygonOptionsDelegate createPolygonOptionsDelegate() {
        return new OsmdroidPolygonOptionsDelegate();
    }

    @NonNull
    @Override
    public PolylineOptionsDelegate createPolylineOptionsDelegate() {
        return new OsmdroidPolylineOptionsDelegate();
    }

    @NonNull
    @Override
    public TileDelegate createTileDelegate(final int width, final int height, @NonNull final byte[] data) {
        return new OsmdroidTileDelegate();
    }

    @NonNull
    @Override
    public TileOverlayOptionsDelegate createTileOverlayOptionDelegate() {
        return new OsmdroidTileOverlayOptionsDelegate();
    }

    @NonNull
    @Override
    public UrlTileProviderDelegate createUrlTileProviderDelegate(
            final int width,
            final int height,
            @NonNull final TileUrlProvider tileUrlProvider
    ) {
        return new OsmdroidUrlTileProviderDelegate();
    }

    @NonNull
    @Override
    public BitmapDescriptorFactoryDelegate createBitmapDescriptorFactory() {
        return new OsmdroidBitmapDescriptorFactoryDelegate();
    }

    @NonNull
    @Override
    public CameraPositionDelegate createCameraPositionDelegate(@NonNull final OPFLatLng target, final float zoom) {
        return new OsmdroidCameraPositionDelegate(target, zoom);
    }

    @NonNull
    @Override
    public CameraPositionDelegate createCameraPositionDelegate(@NonNull final OPFLatLng target,
                                                               final float zoom,
                                                               final float tilt,
                                                               final float bearing) {
        return new OsmdroidCameraPositionDelegate(target, zoom, tilt, bearing);
    }

    @NonNull
    @Override
    public CameraPositionDelegate.Builder createCameraPositionBuilderDelegate() {
        return new OsmdroidCameraPositionDelegate.Builder();
    }

    @NonNull
    @Override
    public CameraPositionDelegate.Builder createCameraPositionBuilderDelegate(@NonNull final OPFCameraPosition camera) {
        return new OsmdroidCameraPositionDelegate.Builder(camera);
    }

    @NonNull
    @Override
    public CameraUpdateFactoryDelegate createCameraUpdateFactory() {
        return new OsmdroidCameraUpdateFactoryDelegate();
    }

    @NonNull
    @Override
    public VisibleRegionDelegate createVisibleRegionDelegate(@NonNull final OPFLatLng nearLeft,
                                                             @NonNull final OPFLatLng nearRight,
                                                             @NonNull final OPFLatLng farLeft,
                                                             @NonNull final OPFLatLng farRight,
                                                             @NonNull final OPFLatLngBounds latLngBounds) {
        return new OsmdroidVisibleRegionDelegate(nearLeft, nearRight, farLeft, farRight, latLngBounds);
    }

    @NonNull
    @Override
    public MapOptionsDelegate createMapOptionsDelegate() {
        return new OsmdroidMapOptionsDelegate(new OsmdroidMapOptions());
    }
}
