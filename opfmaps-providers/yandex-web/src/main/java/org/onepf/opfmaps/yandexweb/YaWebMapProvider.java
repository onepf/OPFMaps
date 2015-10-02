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

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import org.onepf.opfmaps.BaseOPFMapProvider;
import org.onepf.opfmaps.OPFMap;
import org.onepf.opfmaps.factory.DelegatesAbstractFactory;
import org.onepf.opfmaps.listener.OPFOnIndoorStateChangeListener;
import org.onepf.opfmaps.listener.OPFOnMapLoadedCallback;
import org.onepf.opfmaps.listener.OPFOnMarkerClickListener;
import org.onepf.opfmaps.listener.OPFSnapshotReadyCallback;
import org.onepf.opfmaps.model.OPFBitmapDescriptorFactory;
import org.onepf.opfmaps.model.OPFInfoWindowAdapter;
import org.onepf.opfmaps.model.OPFLocationSource;
import org.onepf.opfmaps.model.OPFMarker;
import org.onepf.opfmaps.model.OPFPolygon;
import org.onepf.opfmaps.model.OPFPolyline;
import org.onepf.opfmaps.model.OPFUiSettings;

/**
 * Yandex Web Map Provider.
 * <p/>
 * This provider doesn't support tilt and rotation gestures.
 * Also this provider doesn't support the following methods (stubs implemented):
 * <ul>
 * <ul>
 *     Markers:
 *     <li>{@link org.onepf.opfmaps.model.OPFMarker#setAlpha(float)}</li>
 *     <li>{@link OPFMarker#getAlpha()}</li>
 *     <li>{@link OPFMarker#setRotation(float)}</li>
 *     <li>{@link OPFMarker#getRotation()}</li>
 *     <li>{@link OPFMarker#setFlat(boolean)}</li>
 *     <li>{@link OPFMarker#isFlat()}</li>
 *     <li>{@link OPFMarker#setAnchor(float, float)}</li>
 *     <li>{@link OPFMarker#setInfoWindowAnchor(float, float)}</li>
 *     <li>{@link org.onepf.opfmaps.model.OPFMarkerOptions#alpha(float)}</li>
 *     <li>{@link org.onepf.opfmaps.model.OPFMarkerOptions#anchor(float, float)}</li>
 *     <li>{@link org.onepf.opfmaps.model.OPFMarkerOptions#flat(boolean)}</li>
 *     <li>{@link org.onepf.opfmaps.model.OPFMarkerOptions#infoWindowAnchor(float, float)}</li>
 *     <li>{@link org.onepf.opfmaps.model.OPFMarkerOptions#rotation(float)}</li>
 * </ul>
 * <ul>
 *     Ground overlays:
 *     <li>{@link org.onepf.opfmaps.model.OPFGroundOverlay} all methods</li>
 *     <li>{@link org.onepf.opfmaps.model.OPFGroundOverlayOptions} all methods</li>
 * </ul>
 * <ul>
 *     Tile overlays:
 *     <li>{@link org.onepf.opfmaps.model.OPFTileOverlay} all methods</li>
 *     <li>{@link org.onepf.opfmaps.model.OPFTileOverlayOptions} all methods</li>
 *     <li>{@link org.onepf.opfmaps.model.OPFTileProvider} all methods</li>
 *     <li>{@link org.onepf.opfmaps.model.OPFUrlTileProvider} all methods</li>
 *     <li>{@link org.onepf.opfmaps.model.OPFTile} all methods</li>
 * </ul>
 * <ul>
 *     Polygons:
 *     <li>{@link org.onepf.opfmaps.model.OPFPolygon#setGeodesic(boolean)}</li>
 *     <li>{@link OPFPolygon#isGeodesic()}</li>
 *     <li>{@link org.onepf.opfmaps.model.OPFPolygonOptions#geodesic(boolean)}</li>
 * </ul>
 * <ul>
 *     Polylines:
 *     <li>{@link org.onepf.opfmaps.model.OPFPolyline#setGeodesic(boolean)}</li>
 *     <li>{@link OPFPolyline#isGeodesic()}</li>
 *     <li>{@link org.onepf.opfmaps.model.OPFPolylineOptions#geodesic(boolean)}</li>
 * </ul>
 * <ul>
 *     CameraUpdateFactory:
 *     <li>{@link org.onepf.opfmaps.model.OPFCameraUpdateFactory#newCameraPosition(org.onepf.opfmaps.model.OPFCameraPosition)}
 *     tilt value has no effect.</li>
 *     <li>{@link org.onepf.opfmaps.model.OPFCameraUpdateFactory#newLatLngBounds(org.onepf.opfmaps.model.OPFLatLngBounds, int)}
 *     zoom value is not changed.</li>
 *     <li>{@link org.onepf.opfmaps.model.OPFCameraUpdateFactory#newLatLngBounds(org.onepf.opfmaps.model.OPFLatLngBounds, int, int, int)}
 *     zoom value is not changed.</li>
 * </ul>
 * <ul>
 *     BitmapDescriptor: supports only {@link OPFBitmapDescriptorFactory#defaultMarker()}
 *     and {@link OPFBitmapDescriptorFactory#defaultMarker(float)} methods.
 * </ul>
 * <ul>
 *     UiSettings:
 *     <li>{@link org.onepf.opfmaps.model.OPFUiSettings#setCompassEnabled(boolean)}</li>
 *     <li>{@link OPFUiSettings#isCompassEnabled()}</li>
 *     <li>{@link OPFUiSettings#setIndoorLevelPickerEnabled(boolean)}</li>
 *     <li>{@link OPFUiSettings#isIndoorLevelPickerEnabled()}</li>
 *     <li>{@link OPFUiSettings#setMapToolbarEnabled(boolean)}</li>
 *     <li>{@link OPFUiSettings#isMapToolbarEnabled()}</li>
 *     <li>{@link OPFUiSettings#setRotateGesturesEnabled(boolean)}</li>
 *     <li>{@link OPFUiSettings#isRotateGesturesEnabled()}</li>
 *     <li>{@link OPFUiSettings#setTiltGesturesEnabled(boolean)}</li>
 *     <li>{@link OPFUiSettings#isTiltGesturesEnabled()}</li>
 * </ul>
 * <ul>
 *     Map:
 *     <li>{@link org.onepf.opfmaps.OPFMap#addGroundOverlay(org.onepf.opfmaps.model.OPFGroundOverlayOptions)}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMap#addTileOverlay(org.onepf.opfmaps.model.OPFTileOverlayOptions)}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMap#animateCamera(org.onepf.opfmaps.model.OPFCameraUpdate, int,
 *     org.onepf.opfmaps.listener.OPFCancelableCallback)}
 *     duration value has no effect.</li>
 *     <li>{@link org.onepf.opfmaps.OPFMap#animateCamera(org.onepf.opfmaps.model.OPFCameraUpdate, org.onepf.opfmaps.listener.OPFCancelableCallback)}
 *     onFinished is called immediately.</li>
 *     <li>{@link OPFMap#stopAnimation()}</li>
 *     <li>{@link OPFMap#setContentDescription(String)}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMap#setIndoorEnabled(boolean)}</li>
 *     <li>{@link OPFMap#isIndoorEnabled()}</li>
 *     <li>{@link OPFMap#setOnIndoorStateChangeListener(OPFOnIndoorStateChangeListener)}</li>
 *     <li>{@link OPFMap#isBuildingsEnabled()}</li>
 *     <li>{@link OPFMap#isIndoorEnabled()}</li>
 *     <li>{@link OPFMap#getFocusedBuilding()}</li>
 *     <li>{@link OPFMap#snapshot(OPFSnapshotReadyCallback)}</li>
 *     <li>{@link OPFMap#snapshot(OPFSnapshotReadyCallback, Bitmap)}</li>
 *     <li>{@link OPFMap#setInfoWindowAdapter(OPFInfoWindowAdapter)}</li>
 *     <li>{@link OPFMap#setLocationSource(OPFLocationSource)}</li>
 *     <li>{@link OPFMap#setOnMarkerClickListener(OPFOnMarkerClickListener)}</li>
 *     <li>{@link OPFMap#setOnMapLoadedCallback(OPFOnMapLoadedCallback)} is called immediately.</li>
 *     <li>{@link OPFMap#setPadding(int, int, int, int)}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMapOptions#camera(org.onepf.opfmaps.model.OPFCameraPosition)} tilt value has no effect.</li>
 *     <li>{@link org.onepf.opfmaps.OPFMapOptions#compassEnabled(boolean)}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMapOptions#liteMode(boolean)}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMapOptions#mapToolbarEnabled(boolean)}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMapOptions#rotateGesturesEnabled(boolean)}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMapOptions#tiltGesturesEnabled(boolean)}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMapOptions#useViewLifecycleInFragment(boolean)}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMapOptions#liteMode(boolean)}</li>
 * </ul>
 * </ul>
 *
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class YaWebMapProvider extends BaseOPFMapProvider {

    public YaWebMapProvider() {
        super(YaWebMapProvider.class.getSimpleName(), null);
    }

    @NonNull
    @Override
    public DelegatesAbstractFactory getDelegatesFactory() {
        return new YaWebDelegatesFactory();
    }
}
