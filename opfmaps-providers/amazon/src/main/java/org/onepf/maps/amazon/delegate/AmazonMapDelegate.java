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

package org.onepf.maps.amazon.delegate;

import android.graphics.Bitmap;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.amazon.geo.mapsv2.AmazonMap;
import com.amazon.geo.mapsv2.CameraUpdate;
import com.amazon.geo.mapsv2.LocationSource;
import com.amazon.geo.mapsv2.model.CameraPosition;
import com.amazon.geo.mapsv2.model.Circle;
import com.amazon.geo.mapsv2.model.LatLng;
import com.amazon.geo.mapsv2.model.Marker;
import com.amazon.geo.mapsv2.model.Polygon;
import com.amazon.geo.mapsv2.model.Polyline;
import org.onepf.maps.amazon.delegate.model.AmazonCameraPositionDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonCircleDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonGroundOverlayDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonLatLngDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonMarkerDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonPolygonDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonPolylineDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonProjectionDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonTileOverlayDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonUiSettingsDelegate;
import org.onepf.maps.amazon.utils.ConvertUtils;
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
import org.onepf.opfutils.OPFLog;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
@SuppressWarnings({"PMD.ExcessivePublicCount", "PMD.TooManyMethods"})
public class AmazonMapDelegate implements MapDelegate {

    @NonNull
    private final AmazonMap map;

    @NonNull
    private final OPFUiSettings opfUiSettings;

    public AmazonMapDelegate(@NonNull final AmazonMap map) {
        this.map = map;
        this.opfUiSettings = new OPFUiSettings(new AmazonUiSettingsDelegate(map.getUiSettings()));
    }

    @NonNull
    @Override
    public OPFCircle addCircle(@NonNull final OPFCircleOptions options) {
        final Circle circle = map.addCircle(ConvertUtils.convertCircleOptions(options));
        return new OPFCircle(new AmazonCircleDelegate(circle));
    }

    @NonNull
    @Override
    public OPFGroundOverlay addGroundOverlay(@NonNull final OPFGroundOverlayOptions options) {
        OPFLog.logStubCall(options);
        return new OPFGroundOverlay(new AmazonGroundOverlayDelegate());
    }

    @NonNull
    @Override
    public OPFMarker addMarker(@NonNull final OPFMarkerOptions options) {
        final Marker marker = map.addMarker(ConvertUtils.convertMarkerOptions(options));
        return new OPFMarker(new AmazonMarkerDelegate(marker));
    }

    @NonNull
    @Override
    public OPFPolygon addPolygon(@NonNull final OPFPolygonOptions options) {
        final Polygon polygon = map.addPolygon(ConvertUtils.convertPolygonOptions(options));
        return new OPFPolygon(new AmazonPolygonDelegate(polygon));
    }

    @NonNull
    @Override
    public OPFPolyline addPolyline(@NonNull final OPFPolylineOptions options) {
        final Polyline polyline = map.addPolyline(ConvertUtils.convertPolylineOptions(options));
        return new OPFPolyline(new AmazonPolylineDelegate(polyline));
    }

    @NonNull
    @Override
    public OPFTileOverlay addTileOverlay(@NonNull final OPFTileOverlayOptions options) {
        return new OPFTileOverlay(new AmazonTileOverlayDelegate());
    }

    @Override
    public void animateCamera(@NonNull final OPFCameraUpdate update,
                              final int durationMs,
                              @NonNull final OPFCancelableCallback callback) {
        map.animateCamera(
                (CameraUpdate) update.getDelegate().getCameraUpdate(),
                durationMs,
                new AmazonMap.CancelableCallback() {
                    @Override
                    public void onCancel() {
                        callback.onCancel();
                    }

                    @Override
                    public void onFinish() {
                        callback.onFinish();
                    }
                }
        );
    }

    @Override
    public void animateCamera(@NonNull final OPFCameraUpdate update, @NonNull final OPFCancelableCallback callback) {
        map.animateCamera(
                (CameraUpdate) update.getDelegate().getCameraUpdate(),
                new AmazonMap.CancelableCallback() {
                    @Override
                    public void onCancel() {
                        callback.onCancel();
                    }

                    @Override
                    public void onFinish() {
                        callback.onFinish();
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
        return new OPFCameraPosition(new AmazonCameraPositionDelegate(map.getCameraPosition()));
    }

    @Nullable
    @Override
    public OPFIndoorBuilding getFocusedBuilding() {
        OPFLog.logStubCall();
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
        return new OPFProjection(new AmazonProjectionDelegate(map.getProjection()));
    }

    @NonNull
    @Override
    public OPFUiSettings getUiSettings() {
        return opfUiSettings;
    }

    @Override
    public boolean isBuildingsEnabled() {
        return false;
    }

    @Override
    public boolean isIndoorEnabled() {
        return false;
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
        OPFLog.logStubCall(enabled);
    }

    @Override
    public void setContentDescription(@NonNull final String description) {
        OPFLog.logStubCall(description);
    }

    @Override
    public boolean setIndoorEnabled(final boolean enabled) {
        OPFLog.logStubCall(enabled);
        return false;
    }

    @Override
    public void setInfoWindowAdapter(@NonNull final OPFInfoWindowAdapter adapter) {
        map.setInfoWindowAdapter(new AmazonMap.InfoWindowAdapter() {
            @Override
            public View getInfoContents(final Marker marker) {
                return adapter.getInfoContents(new OPFMarker(new AmazonMarkerDelegate(marker)));
            }

            @Override
            public View getInfoWindow(final Marker marker) {
                return adapter.getInfoWindow(new OPFMarker(new AmazonMarkerDelegate(marker)));
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
        map.setOnCameraChangeListener(new AmazonMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(final CameraPosition cameraPosition) {
                listener.onCameraChange(new OPFCameraPosition(new AmazonCameraPositionDelegate(cameraPosition)));
            }
        });
    }

    @Override
    public void setOnIndoorStateChangeListener(@NonNull final OPFOnIndoorStateChangeListener listener) {
        OPFLog.logStubCall(listener);
    }

    @Override
    public void setOnInfoWindowClickListener(@NonNull final OPFOnInfoWindowClickListener listener) {
        map.setOnInfoWindowClickListener(new AmazonMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(final Marker marker) {
                listener.onInfoWindowClick(new OPFMarker(new AmazonMarkerDelegate(marker)));
            }
        });
    }

    @Override
    public void setOnMapClickListener(@NonNull final OPFOnMapClickListener listener) {
        map.setOnMapClickListener(new AmazonMap.OnMapClickListener() {
            @Override
            public void onMapClick(final LatLng latLng) {
                listener.onMapClick(new OPFLatLng(new AmazonLatLngDelegate(latLng)));
            }
        });
    }

    @Override
    public void setOnMapLoadedCallback(@NonNull final OPFOnMapLoadedCallback callback) {
        map.setOnMapLoadedCallback(new AmazonMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                callback.onMapLoaded();
            }
        });
    }

    @Override
    public void setOnMapLongClickListener(@NonNull final OPFOnMapLongClickListener listener) {
        map.setOnMapLongClickListener(new AmazonMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(final LatLng latLng) {
                listener.onMapLongClick(new OPFLatLng(new AmazonLatLngDelegate(latLng)));
            }
        });
    }

    @Override
    public void setOnMarkerClickListener(@NonNull final OPFOnMarkerClickListener listener) {
        map.setOnMarkerClickListener(new AmazonMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                return listener.onMarkerClick(new OPFMarker(new AmazonMarkerDelegate(marker)));
            }
        });
    }

    @Override
    public void setOnMarkerDragListener(@NonNull final OPFOnMarkerDragListener listener) {
        OPFLog.logStubCall(listener);
    }

    @Override
    public void setOnMyLocationButtonClickListener(@NonNull final OPFOnMyLocationButtonClickListener listener) {
        map.setOnMyLocationButtonClickListener(new AmazonMap.OnMyLocationButtonClickListener() {
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
                new AmazonMap.SnapshotReadyCallback() {
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
        map.snapshot(
                new AmazonMap.SnapshotReadyCallback() {
                    @Override
                    public void onSnapshotReady(final Bitmap bitmap) {
                        callback.onSnapshotReady(bitmap);
                    }
                }
        );
    }

    @Override
    public void stopAnimation() {
        map.stopAnimation();
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof AmazonMapDelegate
                && map.equals(((AmazonMapDelegate) other).map));
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
