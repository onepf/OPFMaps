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

import android.support.annotation.NonNull;
import android.view.View;
import com.amazon.geo.mapsv2.AmazonMap;
import com.amazon.geo.mapsv2.model.CameraPosition;
import com.amazon.geo.mapsv2.model.Circle;
import com.amazon.geo.mapsv2.model.GroundOverlay;
import com.amazon.geo.mapsv2.model.IndoorBuilding;
import com.amazon.geo.mapsv2.model.LatLng;
import com.amazon.geo.mapsv2.model.Marker;
import com.amazon.geo.mapsv2.model.Polygon;
import com.amazon.geo.mapsv2.model.Polyline;
import com.amazon.geo.mapsv2.model.TileOverlay;
import org.onepf.maps.amazon.delegate.model.AmazonCircleDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonGroundOverlayDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonIndoorBuildingDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonLatLngDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonMarkerDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonPolygonDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonPolylineDelegate;
import org.onepf.maps.amazon.delegate.model.AmazonTileOverlayDelegate;
import org.onepf.maps.amazon.utils.ConvertUtils;
import org.onepf.opfmaps.delegate.MapDelegate;
import org.onepf.opfmaps.listener.OPFOnCameraChangeListener;
import org.onepf.opfmaps.listener.OPFOnIndoorStateChangeListener;
import org.onepf.opfmaps.listener.OPFOnInfoWindowClickListener;
import org.onepf.opfmaps.listener.OPFOnMapClickListener;
import org.onepf.opfmaps.listener.OPFOnMapLoadedCallback;
import org.onepf.opfmaps.listener.OPFOnMapLongClickListener;
import org.onepf.opfmaps.listener.OPFOnMarkerClickListener;
import org.onepf.opfmaps.listener.OPFOnMarkerDragListener;
import org.onepf.opfmaps.listener.OPFOnMyLocationButtonClickListener;
import org.onepf.opfmaps.model.OPFCircle;
import org.onepf.opfmaps.model.OPFCircleOptions;
import org.onepf.opfmaps.model.OPFGroundOverlay;
import org.onepf.opfmaps.model.OPFGroundOverlayOptions;
import org.onepf.opfmaps.model.OPFIndoorBuilding;
import org.onepf.opfmaps.model.OPFInfoWindowAdapter;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFMapType;
import org.onepf.opfmaps.model.OPFMarker;
import org.onepf.opfmaps.model.OPFMarkerOptions;
import org.onepf.opfmaps.model.OPFPolygon;
import org.onepf.opfmaps.model.OPFPolygonOptions;
import org.onepf.opfmaps.model.OPFPolyline;
import org.onepf.opfmaps.model.OPFPolylineOptions;
import org.onepf.opfmaps.model.OPFTileOverlay;
import org.onepf.opfmaps.model.OPFTileOverlayOptions;
import org.onepf.opfutils.OPFLog;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
@SuppressWarnings({"PMD.GodClass", "PMD.ExcessivePublicCount"})
public class AmazonMapDelegate implements MapDelegate {

    @NonNull
    private final AmazonMap map;

    public AmazonMapDelegate(@NonNull final AmazonMap map) {
        this.map = map;
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
        final GroundOverlay groundOverlay = map.addGroundOverlay(ConvertUtils.convertGroundOverlayOptions(options));
        return new OPFGroundOverlay(new AmazonGroundOverlayDelegate(groundOverlay));
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
        final TileOverlay tileOverlay = map.addTileOverlay(ConvertUtils.convertTileOverlayOptions(options));
        return new OPFTileOverlay(new AmazonTileOverlayDelegate(tileOverlay));
    }

    @Override
    public void clear() {
        map.clear();
    }

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
    public void setBuildingsEnabled(final boolean enabled) {
        map.setBuildingsEnabled(enabled);
    }

    @Override
    public void setContentDescription(@NonNull final String description) {
        OPFLog.i("Stub setContentDescription()");
    }

    @Override
    public boolean setIndoorEnabled(final boolean enabled) {
        return map.setIndoorEnabled(enabled);
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
                //todo implement
            }
        });
    }

    @Override
    public void setOnIndoorStateChangeListener(@NonNull final OPFOnIndoorStateChangeListener listener) {
        map.setOnIndoorStateChangeListener(new AmazonMap.OnIndoorStateChangeListener() {
            @Override
            public void onIndoorBuildingsFocused() {
                listener.onIndoorBuildingFocused();
            }

            @Override
            public void onIndoorLevelActivated(final IndoorBuilding indoorBuilding) {
                listener.onIndoorLevelActivated(new OPFIndoorBuilding(new AmazonIndoorBuildingDelegate(indoorBuilding)));
            }
        });
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
        map.setOnMarkerDragListener(new AmazonMap.OnMarkerDragListener() {

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
                    opfMarker = new OPFMarker(new AmazonMarkerDelegate(marker));
                } else {
                    opfMarker.setPosition(new OPFLatLng(new AmazonLatLngDelegate(marker.getPosition())));
                }
            }
        });
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
    public void stopAnimation() {
        map.stopAnimation();
    }

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof AmazonMapDelegate)) return false;

        return map.equals(((AmazonMapDelegate) other).map);
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
