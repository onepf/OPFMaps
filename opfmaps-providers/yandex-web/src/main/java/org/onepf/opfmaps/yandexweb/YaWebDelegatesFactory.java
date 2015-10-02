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

package org.onepf.opfmaps.yandexweb;

import android.content.Context;
import android.support.annotation.NonNull;

import org.onepf.opfmaps.yandexweb.delegate.YaWebMapOptionsDelegate;
import org.onepf.opfmaps.yandexweb.delegate.YaWebMapViewDelegate;
import org.onepf.opfmaps.yandexweb.delegate.model.YaWebBitmapDescriptorFactoryDelegate;
import org.onepf.opfmaps.yandexweb.delegate.model.YaWebCameraPositionDelegate;
import org.onepf.opfmaps.yandexweb.delegate.model.YaWebCameraUpdateFactoryDelegate;
import org.onepf.opfmaps.yandexweb.delegate.model.YaWebCircleOptionsDelegate;
import org.onepf.opfmaps.yandexweb.delegate.model.YaWebGroundOverlayOptionsDelegate;
import org.onepf.opfmaps.yandexweb.delegate.model.YaWebLatLngBoundsDelegate;
import org.onepf.opfmaps.yandexweb.delegate.model.YaWebLatLngDelegate;
import org.onepf.opfmaps.yandexweb.delegate.model.YaWebMarkerOptionsDelegate;
import org.onepf.opfmaps.yandexweb.delegate.model.YaWebPolygonOptionsDelegate;
import org.onepf.opfmaps.yandexweb.delegate.model.YaWebPolylineOptionsDelegate;
import org.onepf.opfmaps.yandexweb.delegate.model.YaWebTileDelegate;
import org.onepf.opfmaps.yandexweb.delegate.model.YaWebTileOverlayOptionsDelegate;
import org.onepf.opfmaps.yandexweb.delegate.model.YaWebUrlTileProviderDelegate;
import org.onepf.opfmaps.yandexweb.delegate.model.YaWebVisibleRegionDelegate;
import org.onepf.opfmaps.yandexweb.model.LatLng;
import org.onepf.opfmaps.yandexweb.model.LatLngBounds;
import org.onepf.opfmaps.yandexweb.model.YaWebMapOptions;
import org.onepf.opfmaps.yandexweb.utils.ConvertUtils;
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
 * @since 02.09.2015
 */
public final class YaWebDelegatesFactory implements DelegatesAbstractFactory {

    @NonNull
    @Override
    public MapViewDelegate createMapViewDelegate(@NonNull final Context context) {
        return new YaWebMapViewDelegate(context);
    }

    @NonNull
    @Override
    public MapViewDelegate createMapViewDelegate(@NonNull final Context context,
                                                 @NonNull final OPFMapOptions mapOptions) {
        return new YaWebMapViewDelegate(context, ConvertUtils.convertMapOptions(mapOptions));
    }

    @NonNull
    @Override
    public CircleOptionsDelegate createCircleOptionsDelegate() {
        return new YaWebCircleOptionsDelegate();
    }

    @NonNull
    @Override
    public GroundOverlayOptionsDelegate createGroundOverlayOptionsDelegate() {
        return new YaWebGroundOverlayOptionsDelegate();
    }

    @NonNull
    @Override
    public LatLngDelegate createLatLngDelegate(final double latitude, final double longitude) {
        return new YaWebLatLngDelegate(new LatLng(latitude, longitude));
    }

    @NonNull
    @Override
    public LatLngBoundsDelegate createLatLngBoundsDelegate(@NonNull final OPFLatLng southwest,
                                                           @NonNull final OPFLatLng northeast) {
        return new YaWebLatLngBoundsDelegate(new LatLngBounds(
                new LatLng(southwest.getLat(), southwest.getLng()),
                new LatLng(northeast.getLat(), northeast.getLng())
        ));
    }

    @NonNull
    @Override
    public LatLngBoundsDelegate.Builder createLatLngBoundsBuilderDelegate() {
        return new YaWebLatLngBoundsDelegate.Builder();
    }

    @NonNull
    @Override
    public MarkerOptionsDelegate createMarkerOptionsDelegate() {
        return new YaWebMarkerOptionsDelegate();
    }

    @NonNull
    @Override
    public PolygonOptionsDelegate createPolygonOptionsDelegate() {
        return new YaWebPolygonOptionsDelegate();
    }

    @NonNull
    @Override
    public PolylineOptionsDelegate createPolylineOptionsDelegate() {
        return new YaWebPolylineOptionsDelegate();
    }

    @NonNull
    @Override
    public TileDelegate createTileDelegate(final int width, final int height, @NonNull final byte[] data) {
        return new YaWebTileDelegate();
    }

    @NonNull
    @Override
    public TileOverlayOptionsDelegate createTileOverlayOptionDelegate() {
        return new YaWebTileOverlayOptionsDelegate();
    }

    @NonNull
    @Override
    public UrlTileProviderDelegate createUrlTileProviderDelegate(
            final int width,
            final int height,
            @NonNull final TileUrlProvider tileUrlProvider
    ) {
        return new YaWebUrlTileProviderDelegate();
    }

    @NonNull
    @Override
    public BitmapDescriptorFactoryDelegate createBitmapDescriptorFactory() {
        return new YaWebBitmapDescriptorFactoryDelegate();
    }

    @NonNull
    @Override
    public CameraPositionDelegate createCameraPositionDelegate(@NonNull final OPFLatLng target, final float zoom) {
        return new YaWebCameraPositionDelegate(target, zoom);
    }

    @NonNull
    @Override
    public CameraPositionDelegate createCameraPositionDelegate(@NonNull final OPFLatLng target,
                                                               final float zoom,
                                                               final float tilt,
                                                               final float bearing) {
        return new YaWebCameraPositionDelegate(target, zoom, tilt, bearing);
    }

    @NonNull
    @Override
    public CameraPositionDelegate.Builder createCameraPositionBuilderDelegate() {
        return new YaWebCameraPositionDelegate.Builder();
    }

    @NonNull
    @Override
    public CameraPositionDelegate.Builder createCameraPositionBuilderDelegate(@NonNull final OPFCameraPosition camera) {
        return new YaWebCameraPositionDelegate.Builder(camera);
    }

    @NonNull
    @Override
    public CameraUpdateFactoryDelegate createCameraUpdateFactory() {
        return new YaWebCameraUpdateFactoryDelegate();
    }

    @NonNull
    @Override
    public VisibleRegionDelegate createVisibleRegionDelegate(@NonNull final OPFLatLng nearLeft,
                                                             @NonNull final OPFLatLng nearRight,
                                                             @NonNull final OPFLatLng farLeft,
                                                             @NonNull final OPFLatLng farRight,
                                                             @NonNull final OPFLatLngBounds latLngBounds) {
        return new YaWebVisibleRegionDelegate(nearLeft, nearRight, farLeft, farRight, latLngBounds);
    }

    @NonNull
    @Override
    public MapOptionsDelegate createMapOptionsDelegate() {
        return new YaWebMapOptionsDelegate(new YaWebMapOptions());
    }
}
