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

package org.onepf.opfmaps.osmdroid;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import org.onepf.opfmaps.BaseOPFMapProvider;
import org.onepf.opfmaps.OPFMap;
import org.onepf.opfmaps.factory.DelegatesAbstractFactory;
import org.onepf.opfmaps.listener.OPFOnMapLoadedCallback;
import org.onepf.opfmaps.listener.OPFSnapshotReadyCallback;
import org.onepf.opfmaps.model.OPFLocationSource;
import org.onepf.opfmaps.model.OPFMapType;
import org.onepf.opfmaps.model.OPFPolygon;
import org.onepf.opfmaps.model.OPFUiSettings;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Osmdroid Map Provider.
 * <p/>
 * This provider doesn't support tilt gestures.
 * zIndex is not supported by all map objects. All setZIndex() methods have no results. All getZIndex() return {@code 0.0f}
 * Also this provider doesn't support the following methods (stubs implemented):
 * <ul>
 * <ul>
 *     Ground overlays:
 *     <li>{@link org.onepf.opfmaps.model.OPFGroundOverlayOptions#anchor(float, float)}</li>
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
 *     UiSettings:
 *     <li>{@link org.onepf.opfmaps.model.OPFUiSettings#setIndoorLevelPickerEnabled(boolean)}</li>
 *     <li>{@link OPFUiSettings#isIndoorLevelPickerEnabled()}</li>
 *     <li>{@link OPFUiSettings#setMapToolbarEnabled(boolean)}</li>
 *     <li>{@link OPFUiSettings#isMapToolbarEnabled()}</li>
 *     <li>{@link OPFUiSettings#setTiltGesturesEnabled(boolean)}</li>
 *     <li>{@link OPFUiSettings#isTiltGesturesEnabled()}</li>
 * </ul>
 * <ul>
 *     Map:
 *     <li>{@link org.onepf.opfmaps.OPFMap#addTileOverlay(org.onepf.opfmaps.model.OPFTileOverlayOptions)}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMap#animateCamera(
 *     org.onepf.opfmaps.model.OPFCameraUpdate, int, org.onepf.opfmaps.listener.OPFCancelableCallback)} duration value has no effect.</li>
 *     <li>{@link org.onepf.opfmaps.OPFMap#animateCamera(org.onepf.opfmaps.model.OPFCameraUpdate, org.onepf.opfmaps.listener.OPFCancelableCallback)}
 *     onFinished is called immediately.</li>
 *     <li>{@link org.onepf.opfmaps.OPFMap#setOnCameraChangeListener(org.onepf.opfmaps.listener.OPFOnCameraChangeListener)}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMap#setContentDescription(String)}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMap#setIndoorEnabled(boolean)}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMap#isIndoorEnabled()}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMap#setOnIndoorStateChangeListener(org.onepf.opfmaps.listener.OPFOnIndoorStateChangeListener)}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMap#isBuildingsEnabled()}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMap#isIndoorEnabled()}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMap#getFocusedBuilding()}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMap#setTrafficEnabled(boolean)}</li>
 *     <li>{@link OPFMap#isTrafficEnabled()}</li>
 *     <li>{@link OPFMap#snapshot(OPFSnapshotReadyCallback)}</li>
 *     <li>{@link OPFMap#snapshot(OPFSnapshotReadyCallback, Bitmap)}</li>
 *     <li>{@link OPFMap#setLocationSource(OPFLocationSource)}</li>
 *     <li>{@link OPFMap#setOnMapLoadedCallback(OPFOnMapLoadedCallback)} is called immediately.</li>
 *     <li>{@link OPFMap#setPadding(int, int, int, int)}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMapOptions#camera(org.onepf.opfmaps.model.OPFCameraPosition)} tilt value has no effect.</li>
 *     <li>{@link org.onepf.opfmaps.OPFMapOptions#liteMode(boolean)}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMapOptions#mapToolbarEnabled(boolean)}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMapOptions#tiltGesturesEnabled(boolean)}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMapOptions#useViewLifecycleInFragment(boolean)}</li>
 *     <li>{@link org.onepf.opfmaps.OPFMapOptions#liteMode(boolean)}</li>
 * </ul>
 * </ul>
 *
 * @author Roman Savin
 * @since 12.08.2015
 */
public final class OsmdroidMapProvider extends BaseOPFMapProvider {

    @NonNull
    private final Map<OPFMapType, ITileSource> tileSourceMap;

    public OsmdroidMapProvider() {
        this(createDefaultTileSourceMap());
    }

    public OsmdroidMapProvider(@NonNull final OPFMapType mapType, @NonNull final ITileSource tileSource) {
        super(OsmdroidMapProvider.class.getSimpleName(), null);
        final Map<OPFMapType, ITileSource> tileSourceMap = createDefaultTileSourceMap();
        tileSourceMap.put(mapType, tileSource);
        this.tileSourceMap = tileSourceMap;
    }

    public OsmdroidMapProvider(@NonNull final Map<OPFMapType, ITileSource> tileSourceMap) {
        super(OsmdroidMapProvider.class.getSimpleName(), null);
        this.tileSourceMap = tileSourceMap;
    }

    @NonNull
    @Override
    public DelegatesAbstractFactory getDelegatesFactory() {
        return new OsmdroidDelegatesFactory();
    }

    @NonNull
    public Map<OPFMapType, ITileSource> getTileSourceMap() {
        return tileSourceMap;
    }

    private static Map<OPFMapType, ITileSource> createDefaultTileSourceMap() {
        final Map<OPFMapType, ITileSource> tileSourceMap = new HashMap<>();
        tileSourceMap.put(OPFMapType.NONE, TileSourceFactory.MAPNIK);
        tileSourceMap.put(OPFMapType.NORMAL, TileSourceFactory.MAPNIK);
        tileSourceMap.put(OPFMapType.SATELLITE, TileSourceFactory.MAPQUESTAERIAL);
        tileSourceMap.put(OPFMapType.TERRAIN, TileSourceFactory.MAPQUESTOSM);
        tileSourceMap.put(OPFMapType.HYBRID, TileSourceFactory.MAPQUESTOSM);
        return tileSourceMap;
    }
}
