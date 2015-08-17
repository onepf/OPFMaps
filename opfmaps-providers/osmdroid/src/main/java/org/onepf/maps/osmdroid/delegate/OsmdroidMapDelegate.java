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

package org.onepf.maps.osmdroid.delegate;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidCircleDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidGroundOverlayDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidMarkerDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidPolygonDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidPolylineDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidProjectionDelegate;
import org.onepf.maps.osmdroid.utils.ConvertUtils;
import org.onepf.opfmaps.delegate.MapDelegate;
import org.onepf.opfmaps.listener.OPFCancelableCallback;
import org.onepf.opfmaps.listener.OPFOnCameraChangeListener;
import org.onepf.opfmaps.listener.OPFOnIndoorStateChangeListener;
import org.onepf.opfmaps.listener.OPFOnInfoWindowClickListener;
import org.onepf.opfmaps.listener.OPFOnMapClickListener;
import org.onepf.opfmaps.listener.OPFOnMapLoadedCallback;
import org.onepf.opfmaps.listener.OPFOnMapLongClickListener;
import org.onepf.opfmaps.listener.OPFOnMarkerClickListener;
import org.onepf.opfmaps.listener.OPFOnMarkerDragListener;
import org.onepf.opfmaps.listener.OPFOnMyLocationButtonClickListener;
import org.onepf.opfmaps.listener.OPFSnapshotReadyCallback;
import org.onepf.opfmaps.model.OPFCameraPosition;
import org.onepf.opfmaps.model.OPFCameraUpdate;
import org.onepf.opfmaps.model.OPFCircle;
import org.onepf.opfmaps.model.OPFCircleOptions;
import org.onepf.opfmaps.model.OPFGroundOverlay;
import org.onepf.opfmaps.model.OPFGroundOverlayOptions;
import org.onepf.opfmaps.model.OPFIndoorBuilding;
import org.onepf.opfmaps.model.OPFInfoWindowAdapter;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFLocationSource;
import org.onepf.opfmaps.model.OPFMapType;
import org.onepf.opfmaps.model.OPFMarker;
import org.onepf.opfmaps.model.OPFMarkerOptions;
import org.onepf.opfmaps.model.OPFPolygon;
import org.onepf.opfmaps.model.OPFPolygonOptions;
import org.onepf.opfmaps.model.OPFPolyline;
import org.onepf.opfmaps.model.OPFPolylineOptions;
import org.onepf.opfmaps.model.OPFProjection;
import org.onepf.opfmaps.model.OPFTileOverlay;
import org.onepf.opfmaps.model.OPFTileOverlayOptions;
import org.onepf.opfmaps.model.OPFUiSettings;
import org.onepf.opfutils.OPFLog;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.bonuspack.overlays.GroundOverlay;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.bonuspack.overlays.Polygon;
import org.osmdroid.bonuspack.overlays.Polyline;
import org.osmdroid.views.MapView;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
@SuppressWarnings({"PMD.GodClass", "PMD.ExcessivePublicCount", "PMD.TooManyMethods"})
public class OsmdroidMapDelegate implements MapDelegate {

    @NonNull
    private final MapView map; //todo check for leak

    public OsmdroidMapDelegate(@NonNull final MapView map) {
        this.map = map;
    }

    @NonNull
    @Override
    public OPFCircle addCircle(@NonNull final OPFCircleOptions options) {
        final OsmdroidCircleDelegate circleDelegate = ConvertUtils.convertCircleOptions(map, options);
        map.getOverlays().add(circleDelegate.getPolygon());
        map.invalidate();
        return new OPFCircle(circleDelegate);
    }

    @NonNull
    @Override
    public OPFGroundOverlay addGroundOverlay(@NonNull final OPFGroundOverlayOptions options) {
        final GroundOverlay groundOverlay = ConvertUtils.convertGroundOverlayOptions(map.getContext(), options);
        map.getOverlays().add(groundOverlay);
        map.invalidate();
        return new OPFGroundOverlay(new OsmdroidGroundOverlayDelegate(map, groundOverlay));
    }

    @NonNull
    @Override
    public OPFMarker addMarker(@NonNull final OPFMarkerOptions options) {
        final Marker marker = ConvertUtils.convertMarkerOptions(map, options);
        map.getOverlays().add(marker);
        map.invalidate();
        return new OPFMarker(new OsmdroidMarkerDelegate(map, marker));
    }

    @NonNull
    @Override
    public OPFPolygon addPolygon(@NonNull final OPFPolygonOptions options) {
        final Polygon polygon = ConvertUtils.convertPolygonOptions(map.getContext(), options);
        map.getOverlays().add(polygon);
        map.invalidate();
        return new OPFPolygon(new OsmdroidPolygonDelegate(map, polygon));
    }

    @NonNull
    @Override
    public OPFPolyline addPolyline(@NonNull final OPFPolylineOptions options) {
        final Polyline polyline = ConvertUtils.convertPolylineOptions(map.getContext(), options);
        map.getOverlays().add(polyline);
        map.invalidate();
        return new OPFPolyline(new OsmdroidPolylineDelegate(map, polyline));
    }

    @NonNull
    @Override
    public OPFTileOverlay addTileOverlay(@NonNull final OPFTileOverlayOptions options) {
        //todo implement
        return null;
    }

    @Override
    public void animateCamera(@NonNull final OPFCameraUpdate update,
                              final int durationMs,
                              @NonNull final OPFCancelableCallback callback) {
        //todo implement
    }

    @Override
    public void animateCamera(@NonNull final OPFCameraUpdate update, @NonNull final OPFCancelableCallback callback) {
        //todo implement
    }

    @Override
    public void animateCamera(@NonNull final OPFCameraUpdate update) {
        //todo implement
    }

    @Override
    public void clear() {
        map.getOverlays().clear();
        map.invalidate();
    }

    @NonNull
    @Override
    public OPFCameraPosition getCameraPosition() {
        final IGeoPoint center = map.getMapCenter();
        final int zoom = map.getZoomLevel();
        return OPFCameraPosition.fromLatLngZoom(new OPFLatLng(center.getLatitude(), center.getLongitude()), zoom);
    }

    @NonNull
    @Override
    public OPFIndoorBuilding getFocusedBuilding() {
        OPFLog.logStubCall();
        //todo implement stub
        return null;
    }

    @NonNull
    @Override
    public OPFMapType getMapType() {
        return OPFMapType.NORMAL;
    }

    @Override
    public float getMaxZoomLevel() {
        return map.getMaxZoomLevel();
    }

    @Override
    public float getMinZoomLevel() {
        return map.getMinZoomLevel();
    }

    @NonNull
    @Override
    public OPFProjection getProjection() {
        return new OPFProjection(new OsmdroidProjectionDelegate(map.getProjection()));
    }

    @NonNull
    @Override
    public OPFUiSettings getUiSettings() {
        //todo implemnt
        return null;
    }

    @Override
    public boolean isBuildingsEnabled() {
        //todo check
        return false;
    }

    @Override
    public boolean isIndoorEnabled() {
        return false;
    }

    @Override
    public boolean isMyLocationEnabled() {
        //todo check
        return true;
    }

    @Override
    public boolean isTrafficEnabled() {
        //todo check
        return false;
    }

    @Override
    public void moveCamera(@NonNull final OPFCameraUpdate update) {
        //todo implement
    }

    @Override
    public void setBuildingsEnabled(final boolean enabled) {
        OPFLog.logStubCall(enabled);
    }

    @Override
    public void setContentDescription(@NonNull final String description) {
        map.setContentDescription(description);
    }

    @Override
    public boolean setIndoorEnabled(final boolean enabled) {
        OPFLog.logStubCall(enabled);
        return false;
    }

    @Override
    public void setInfoWindowAdapter(@NonNull final OPFInfoWindowAdapter adapter) {
        OPFLog.logStubCall(adapter);
    }

    @Override
    public void setLocationSource(@NonNull final OPFLocationSource source) {
        //todo implment
        /*map.setLocationSource(new LocationSource() {
            @Override
            public void activate(final OnLocationChangedListener onLocationChangedListener) {
                source.activate(new OPFOnLocationChangedListener() {
                    @Override
                    public void onLocationChanged(@NonNull final Location location) {
                        onLocationChangedListener.onLocationChanged(location);
                    }
                });
            }

            @Override
            public void deactivate() {
                source.deactivate();
            }
        });*/
    }

    @Override
    public void setMapType(@NonNull final OPFMapType type) {
        OPFLog.logStubCall(type);
        //todo check is there any types in osmdroid
    }

    @Override
    public void setMyLocationEnabled(final boolean enabled) {
        //todo implement
        /*map.setMyLocationEnabled(enabled);*/
    }

    @Override
    public void setOnCameraChangeListener(@NonNull final OPFOnCameraChangeListener listener) {
        /*map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(final CameraPosition cameraPosition) {
                //todo implement
            }
        });*/
    }

    @Override
    public void setOnIndoorStateChangeListener(@NonNull final OPFOnIndoorStateChangeListener listener) {
        //todo implement
        /*map.setOnIndoorStateChangeListener(new GoogleMap.OnIndoorStateChangeListener() {

            @Override
            public void onIndoorBuildingFocused() {
                listener.onIndoorBuildingFocused();
            }

            @Override
            public void onIndoorLevelActivated(final IndoorBuilding indoorBuilding) {
                listener.onIndoorLevelActivated(new OPFIndoorBuilding(new GoogleIndoorBuildingDelegate(indoorBuilding)));
            }
        });*/
    }

    @Override
    public void setOnInfoWindowClickListener(@NonNull final OPFOnInfoWindowClickListener listener) {
        //todo implement
        /*map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(final Marker marker) {
                listener.onInfoWindowClick(new OPFMarker(new GoogleMarkerDelegate(marker)));
            }
        });*/
    }

    @Override
    public void setOnMapClickListener(@NonNull final OPFOnMapClickListener listener) {
        //todo implement
       /* map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(final LatLng latLng) {
                listener.onMapClick(new OPFLatLng(new GoogleLatLngDelegate(latLng)));
            }
        });*/
    }

    @Override
    public void setOnMapLoadedCallback(@NonNull final OPFOnMapLoadedCallback callback) {
        //todo implement
        /*map.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                callback.onMapLoaded();
            }
        });*/
    }

    @Override
    public void setOnMapLongClickListener(@NonNull final OPFOnMapLongClickListener listener) {
        //todo implement
        /*map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(final LatLng latLng) {
                listener.onMapLongClick(new OPFLatLng(new GoogleLatLngDelegate(latLng)));
            }
        });*/
    }

    @Override
    public void setOnMarkerClickListener(@NonNull final OPFOnMarkerClickListener listener) {
        //todo implement
        /*map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                return listener.onMarkerClick(new OPFMarker(new GoogleMarkerDelegate(marker)));
            }
        });*/
    }

    @Override
    public void setOnMarkerDragListener(@NonNull final OPFOnMarkerDragListener listener) {
        //todo implement
        /*map.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

            private OPFMarker opfMarker;

            @Override
            public void onMarkerDrag(final Marker marker) {
                updateOPFMarker(marker);
                listener.onMarkerDrag(opfMarker);
            }

            @Override
            public void onMarkerDragEnd(final Marker marker) {
                updateOPFMarker(marker);
                listener.onMarkerDragEnd(opfMarker);
            }

            @Override
            public void onMarkerDragStart(final Marker marker) {
                updateOPFMarker(marker);
                listener.onMarkerDragStart(opfMarker);
            }

            private void updateOPFMarker(@NonNull final Marker marker) {
                if (opfMarker == null) {
                    opfMarker = new OPFMarker(new GoogleMarkerDelegate(marker));
                } else {
                    opfMarker.setPosition(new OPFLatLng(new GoogleLatLngDelegate(marker.getPosition())));
                }
            }
        });*/
    }

    @Override
    public void setOnMyLocationButtonClickListener(@NonNull final OPFOnMyLocationButtonClickListener listener) {
        //todo implement
        /*map.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                return listener.onMyLocationButtonClick();
            }
        });*/
    }

    @Override
    public void setPadding(final int left, final int top, final int right, final int bottom) {
        map.setPadding(left, top, right, bottom);
    }

    @Override
    public void setTrafficEnabled(final boolean enabled) {
        //todo implement
        /*map.setTrafficEnabled(enabled);*/
    }

    @Override
    public void snapshot(@NonNull final OPFSnapshotReadyCallback callback, @NonNull final Bitmap bitmap) {
        //todo implement
        /*map.snapshot(
                new GoogleMap.SnapshotReadyCallback() {
                    @Override
                    public void onSnapshotReady(final Bitmap bitmap) {
                        callback.onSnapshotReady(bitmap);
                    }
                },
                bitmap
        );*/
    }

    @Override
    public void snapshot(@NonNull final OPFSnapshotReadyCallback callback) {
        //todo implement
        /*map.snapshot(new GoogleMap.SnapshotReadyCallback() {
            @Override
            public void onSnapshotReady(final Bitmap bitmap) {
                callback.onSnapshotReady(bitmap);
            }
        });*/
    }

    @Override
    public void stopAnimation() {
        //todo implement
        /*map.stopAnimation();*/
    }

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OsmdroidMapDelegate)) return false;

        return map.equals(((OsmdroidMapDelegate) other).map);
    }
    //CHECKSTYLE:ON

    @Override
    public int hashCode() {
        return map.hashCode();
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
