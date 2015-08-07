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

package org.onepf.opfmaps.factory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
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
import org.onepf.opfmaps.model.OPFCameraPosition;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFLatLngBounds;

/**
 * @author Roman Savin
 * @since 30.07.2015
 */
public interface DelegatesAbstractFactory {

    @NonNull
    MapViewDelegate createMapViewDelegate(@NonNull final Context context);

    @NonNull
    MapViewDelegate createMapViewDelegate(@NonNull final Context context, @NonNull final OPFMapOptions mapOptions);

    @NonNull
    MapViewDelegate createMapViewDelegate(@NonNull final Context context, @NonNull final AttributeSet attrs);

    @NonNull
    MapViewDelegate createMapViewDelegate(@NonNull final Context context,
                                          @NonNull final AttributeSet attrs,
                                          final int defStyleAttr);

    @NonNull
    CircleOptionsDelegate createCircleOptionsDelegate();

    @NonNull
    GroundOverlayOptionsDelegate createGroundOverlayOptionsDelegate();

    @NonNull
    LatLngDelegate createLatLngDelegate(final double latitude, final double longitude);

    @NonNull
    LatLngBoundsDelegate createLatLngBoundsDelegate(@NonNull final OPFLatLng southwest,
                                                    @NonNull final OPFLatLng northeast);

    @NonNull
    LatLngBoundsDelegate.Builder createLatLngBoundsBuilderDelegate();

    @NonNull
    MarkerOptionsDelegate createMarkerOptionsDelegate();

    @NonNull
    PolygonOptionsDelegate createPolygonOptionsDelegate();

    @NonNull
    PolylineOptionsDelegate createPolylineOptionsDelegate();

    @NonNull
    TileDelegate createTileDelegate(final int width, final int height, @NonNull final byte[] data);

    @NonNull
    TileOverlayOptionsDelegate createTileOverlayOptionDelegate();

    @NonNull
    UrlTileProviderDelegate createUrlTileProviderDelegate(final int width, final int height);

    @NonNull
    BitmapDescriptorFactoryDelegate createBitmapDescriptorFactory();

    @NonNull
    CameraPositionDelegate createCameraPositionDelegate(@NonNull final Context context,
                                                        @NonNull final AttributeSet attrs);

    @NonNull
    CameraPositionDelegate createCameraPositionDelegate(@NonNull final OPFLatLng target, float zoom);

    @NonNull
    CameraPositionDelegate createCameraPositionDelegate(@NonNull final OPFLatLng target,
                                                        final float zoom,
                                                        final float tilt,
                                                        final float bearing);

    @NonNull
    CameraPositionDelegate.Builder createCameraPositionBuilderDelegate();

    @NonNull
    CameraPositionDelegate.Builder createCameraPositionBuilderDelegate(@NonNull final OPFCameraPosition camera);

    @NonNull
    CameraUpdateFactoryDelegate createCameraUpdateFactory();

    @NonNull
    VisibleRegionDelegate createVisibleRegionDelegate(@NonNull final OPFLatLng nearLeft,
                                                      @NonNull final OPFLatLng nearRight,
                                                      @NonNull final OPFLatLng farLeft,
                                                      @NonNull final OPFLatLng farRight,
                                                      @NonNull final OPFLatLngBounds latLngBounds);

    @NonNull
    MapOptionsDelegate createMapOptionsDelegate(@NonNull final Context context, @NonNull final AttributeSet attrs);

    @NonNull
    MapOptionsDelegate createMapOptionsDelegate();
}
