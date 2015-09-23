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

package org.onepf.maps.google.delegate;

import android.graphics.Bitmap;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.IndoorBuilding;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.TileOverlay;

import org.onepf.maps.google.delegate.model.GoogleCameraPositionDelegate;
import org.onepf.maps.google.delegate.model.GoogleCircleDelegate;
import org.onepf.maps.google.delegate.model.GoogleGroundOverlayDelegate;
import org.onepf.maps.google.delegate.model.GoogleIndoorBuildingDelegate;
import org.onepf.maps.google.delegate.model.GoogleLatLngDelegate;
import org.onepf.maps.google.delegate.model.GoogleMarkerDelegate;
import org.onepf.maps.google.delegate.model.GooglePolygonDelegate;
import org.onepf.maps.google.delegate.model.GooglePolylineDelegate;
import org.onepf.maps.google.delegate.model.GoogleProjectionDelegate;
import org.onepf.maps.google.delegate.model.GoogleTileOverlayDelegate;
import org.onepf.maps.google.delegate.model.GoogleUiSettingsDelegate;
import org.onepf.maps.google.utils.ConvertUtils;
import org.onepf.opfmaps.delegate.MapDelegate;
import org.onepf.opfmaps.listener.OPFCancelableCallback;
import org.onepf.opfmaps.listener.OPFOnCameraChangeListener;
import org.onepf.opfmaps.listener.OPFOnIndoorStateChangeListener;
import org.onepf.opfmaps.listener.OPFOnInfoWindowClickListener;
import org.onepf.opfmaps.listener.OPFOnLocationChangedListener;
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

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
@SuppressWarnings({"PMD.ExcessivePublicCount", "PMD.TooManyMethods"})
public class GoogleMapDelegate implements MapDelegate {

    @NonNull
    private final GoogleMap map;

    public GoogleMapDelegate(@NonNull final GoogleMap map) {
        this.map = map;
    }

    @NonNull
    @Override
    public OPFCircle addCircle(@NonNull final OPFCircleOptions options) {
        final Circle circle = map.addCircle(ConvertUtils.convertCircleOptions(options));
        return new OPFCircle(new GoogleCircleDelegate(circle));
    }

    @NonNull
    @Override
    public OPFGroundOverlay addGroundOverlay(@NonNull final OPFGroundOverlayOptions options) {
        final GroundOverlay groundOverlay = map.addGroundOverlay(ConvertUtils.convertGroundOverlayOptions(options));
        return new OPFGroundOverlay(new GoogleGroundOverlayDelegate(groundOverlay));
    }

    @NonNull
    @Override
    public OPFMarker addMarker(@NonNull final OPFMarkerOptions options) {
        final Marker marker = map.addMarker(ConvertUtils.convertMarkerOptions(options));
        return new OPFMarker(new GoogleMarkerDelegate(marker));
    }

    @NonNull
    @Override
    public OPFPolygon addPolygon(@NonNull final OPFPolygonOptions options) {
        final Polygon polygon = map.addPolygon(ConvertUtils.convertPolygonOptions(options));
        return new OPFPolygon(new GooglePolygonDelegate(polygon));
    }

    @NonNull
    @Override
    public OPFPolyline addPolyline(@NonNull final OPFPolylineOptions options) {
        final Polyline polyline = map.addPolyline(ConvertUtils.convertPolylineOptions(options));
        return new OPFPolyline(new GooglePolylineDelegate(polyline));
    }

    @NonNull
    @Override
    public OPFTileOverlay addTileOverlay(@NonNull final OPFTileOverlayOptions options) {
        final TileOverlay tileOverlay = map.addTileOverlay(ConvertUtils.convertTileOverlayOptions(options));
        return new OPFTileOverlay(new GoogleTileOverlayDelegate(tileOverlay));
    }

    @Override
    public void animateCamera(@NonNull final OPFCameraUpdate update,
                              final int durationMs,
                              @NonNull final OPFCancelableCallback callback) {
        map.animateCamera(
                (CameraUpdate) update.getDelegate().getCameraUpdate(),
                durationMs,
                new GoogleMap.CancelableCallback() {
                    @Override
                    public void onFinish() {
                        callback.onFinish();
                    }

                    @Override
                    public void onCancel() {
                        callback.onCancel();
                    }
                }
        );
    }

    @Override
    public void animateCamera(@NonNull final OPFCameraUpdate update, @NonNull final OPFCancelableCallback callback) {
        map.animateCamera(
                (CameraUpdate) update.getDelegate().getCameraUpdate(),
                new GoogleMap.CancelableCallback() {
                    @Override
                    public void onFinish() {
                        callback.onFinish();
                    }

                    @Override
                    public void onCancel() {
                        callback.onCancel();
                    }
                }
        );
    }

    @Override
    public void animateCamera(@NonNull final OPFCameraUpdate update) {
        map.animateCamera((CameraUpdate) update.getDelegate().getCameraUpdate());
    }

    @Override
    public void clear() {
        map.clear();
    }

    @NonNull
    @Override
    public OPFCameraPosition getCameraPosition() {
        return new OPFCameraPosition(new GoogleCameraPositionDelegate(map.getCameraPosition()));
    }

    @Nullable
    @Override
    public OPFIndoorBuilding getFocusedBuilding() {
        final IndoorBuilding focusedBuilding = map.getFocusedBuilding();
        if (focusedBuilding != null) {
            return new OPFIndoorBuilding(new GoogleIndoorBuildingDelegate(map.getFocusedBuilding()));
        }
        return null;
    }

    @NonNull
    @Override
    public OPFMapType getMapType() {
        return ConvertUtils.convertMapType(map.getMapType());
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
        return new OPFProjection(new GoogleProjectionDelegate(map.getProjection()));
    }

    @NonNull
    @Override
    public OPFUiSettings getUiSettings() {
        return new OPFUiSettings(new GoogleUiSettingsDelegate(map.getUiSettings()));
    }

    @Override
    public boolean isBuildingsEnabled() {
        return map.isBuildingsEnabled();
    }

    @Override
    public boolean isIndoorEnabled() {
        return map.isIndoorEnabled();
    }

    @Override
    public boolean isMyLocationEnabled() {
        return map.isMyLocationEnabled();
    }

    @Override
    public boolean isTrafficEnabled() {
        return map.isTrafficEnabled();
    }

    @Override
    public void moveCamera(@NonNull final OPFCameraUpdate update) {
        map.moveCamera((CameraUpdate) update.getDelegate().getCameraUpdate());
    }

    @Override
    public void setBuildingsEnabled(final boolean enabled) {
        map.setBuildingsEnabled(enabled);
    }

    @Override
    public void setContentDescription(@NonNull final String description) {
        map.setContentDescription(description);
    }

    @Override
    public boolean setIndoorEnabled(final boolean enabled) {
        return map.setIndoorEnabled(enabled);
    }

    @Override
    public void setInfoWindowAdapter(@NonNull final OPFInfoWindowAdapter adapter) {
        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoContents(final Marker marker) {
                return adapter.getInfoContents(new OPFMarker(new GoogleMarkerDelegate(marker)));
            }

            @Override
            public View getInfoWindow(final Marker marker) {
                return adapter.getInfoWindow(new OPFMarker(new GoogleMarkerDelegate(marker)));
            }
        });
    }

    @Override
    public void setLocationSource(@NonNull final OPFLocationSource source) {
        map.setLocationSource(new LocationSource() {
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
        });
    }

    @Override
    public void setMapType(@NonNull final OPFMapType type) {
        map.setMapType(ConvertUtils.convertMapType(type));
    }

    @Override
    public void setMyLocationEnabled(final boolean enabled) {
        map.setMyLocationEnabled(enabled);
    }

    @Override
    public void setOnCameraChangeListener(@NonNull final OPFOnCameraChangeListener listener) {
        map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(final CameraPosition cameraPosition) {
                listener.onCameraChange(new OPFCameraPosition(new GoogleCameraPositionDelegate(cameraPosition)));
            }
        });
    }

    @Override
    public void setOnIndoorStateChangeListener(@NonNull final OPFOnIndoorStateChangeListener listener) {
        map.setOnIndoorStateChangeListener(new GoogleMap.OnIndoorStateChangeListener() {

            @Override
            public void onIndoorBuildingFocused() {
                listener.onIndoorBuildingFocused();
            }

            @Override
            public void onIndoorLevelActivated(final IndoorBuilding indoorBuilding) {
                listener.onIndoorLevelActivated(new OPFIndoorBuilding(new GoogleIndoorBuildingDelegate(indoorBuilding)));
            }
        });
    }

    @Override
    public void setOnInfoWindowClickListener(@NonNull final OPFOnInfoWindowClickListener listener) {
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(final Marker marker) {
                listener.onInfoWindowClick(new OPFMarker(new GoogleMarkerDelegate(marker)));
            }
        });
    }

    @Override
    public void setOnMapClickListener(@NonNull final OPFOnMapClickListener listener) {
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(final LatLng latLng) {
                listener.onMapClick(new OPFLatLng(new GoogleLatLngDelegate(latLng)));
            }
        });
    }

    @Override
    public void setOnMapLoadedCallback(@NonNull final OPFOnMapLoadedCallback callback) {
        map.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                callback.onMapLoaded();
            }
        });
    }

    @Override
    public void setOnMapLongClickListener(@NonNull final OPFOnMapLongClickListener listener) {
        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(final LatLng latLng) {
                listener.onMapLongClick(new OPFLatLng(new GoogleLatLngDelegate(latLng)));
            }
        });
    }

    @Override
    public void setOnMarkerClickListener(@NonNull final OPFOnMarkerClickListener listener) {
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                return listener.onMarkerClick(new OPFMarker(new GoogleMarkerDelegate(marker)));
            }
        });
    }

    @Override
    public void setOnMarkerDragListener(@NonNull final OPFOnMarkerDragListener listener) {
        map.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {

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
        });
    }

    @Override
    public void setOnMyLocationButtonClickListener(@NonNull final OPFOnMyLocationButtonClickListener listener) {
        map.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                return listener.onMyLocationButtonClick();
            }
        });
    }

    @Override
    public void setPadding(final int left, final int top, final int right, final int bottom) {
        map.setPadding(left, top, right, bottom);
    }

    @Override
    public void setTrafficEnabled(final boolean enabled) {
        map.setTrafficEnabled(enabled);
    }

    @Override
    public void snapshot(@NonNull final OPFSnapshotReadyCallback callback, @NonNull final Bitmap bitmap) {
        map.snapshot(
                new GoogleMap.SnapshotReadyCallback() {
                    @Override
                    public void onSnapshotReady(final Bitmap bitmap) {
                        callback.onSnapshotReady(bitmap);
                    }
                },
                bitmap
        );
    }

    @Override
    public void snapshot(@NonNull final OPFSnapshotReadyCallback callback) {
        map.snapshot(new GoogleMap.SnapshotReadyCallback() {
            @Override
            public void onSnapshotReady(final Bitmap bitmap) {
                callback.onSnapshotReady(bitmap);
            }
        });
    }

    @Override
    public void stopAnimation() {
        map.stopAnimation();
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof GoogleMapDelegate
                && map.equals(((GoogleMapDelegate) other).map));
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
