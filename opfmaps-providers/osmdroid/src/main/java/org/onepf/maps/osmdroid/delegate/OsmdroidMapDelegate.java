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

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;

import org.onepf.maps.osmdroid.delegate.model.OsmdroidCircleDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidGroundOverlayDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidIndoorBuildingDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidMarkerDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidPolygonDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidPolylineDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidProjectionDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidTileOverlayDelegate;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidUiSettingsDelegate;
import org.onepf.maps.osmdroid.model.CameraUpdate;
import org.onepf.maps.osmdroid.model.UiSettings;
import org.onepf.maps.osmdroid.overlay.MarkerInfoWindow;
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
import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.overlays.GroundOverlay;
import org.osmdroid.bonuspack.overlays.InfoWindow;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.bonuspack.overlays.Polygon;
import org.osmdroid.bonuspack.overlays.Polyline;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Overlay;

import java.util.List;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
@SuppressWarnings({"PMD.GodClass", "PMD.ExcessivePublicCount", "PMD.TooManyMethods"})
public class OsmdroidMapDelegate implements MapDelegate {

    @NonNull
    private final OsmdroidMapViewDelegate map; //todo check for leak

    @Nullable
    private OPFOnMarkerClickListener opfOnMarkerClickListener;
    @Nullable
    private OPFOnMarkerDragListener opfOnMarkerDragListener;
    @Nullable
    private OPFInfoWindowAdapter opfInfoWindowAdapter;
    @Nullable
    private OPFOnInfoWindowClickListener opfOnInfoWindowClickListener;

    public OsmdroidMapDelegate(@NonNull final OsmdroidMapViewDelegate map) {
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

        final OPFMarker opfMarker = new OPFMarker(new OsmdroidMarkerDelegate(map, marker));

        marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker, final MapView mapView) {
                marker.showInfoWindow();
                mapView.getController().animateTo(marker.getPosition());

                if (opfOnMarkerClickListener != null) {
                    opfOnMarkerClickListener.onMarkerClick(opfMarker);
                }
                return true;
            }
        });

        marker.setOnMarkerDragListener(new Marker.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(final Marker marker) {
                if (opfOnMarkerDragListener != null) {
                    opfOnMarkerDragListener.onMarkerDrag(opfMarker);
                }
            }

            @Override
            public void onMarkerDragEnd(final Marker marker) {
                if (opfOnMarkerDragListener != null) {
                    opfOnMarkerDragListener.onMarkerDragEnd(opfMarker);
                }
            }

            @Override
            public void onMarkerDragStart(final Marker marker) {
                if (opfOnMarkerDragListener != null) {
                    opfOnMarkerDragListener.onMarkerDragStart(opfMarker);
                }
            }
        });

        initMarkerInfoWindows(marker, opfMarker);

        return opfMarker;
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
        return new OPFTileOverlay(new OsmdroidTileOverlayDelegate());
    }

    @Override
    public void animateCamera(@NonNull final OPFCameraUpdate update,
                              final int durationMs,
                              @NonNull final OPFCancelableCallback callback) {
        animateCamera(update);
        callback.onFinish();
    }

    @Override
    public void animateCamera(@NonNull final OPFCameraUpdate update, @NonNull final OPFCancelableCallback callback) {
        animateCamera(update);
        callback.onFinish();
    }

    @Override
    public void animateCamera(@NonNull final OPFCameraUpdate update) {
        final IMapController controller = map.getController();
        final CameraUpdate cameraUpdate = (CameraUpdate) update.getDelegate().getCameraUpdate();

        switch (cameraUpdate.getCameraUpdateSource()) {
            case CAMERA_POSITION:
                controller.animateTo(cameraUpdate.getCenter());
                map.setMapOrientation(cameraUpdate.getBearing());
                break;
            case GEOPOINT:
                controller.animateTo(cameraUpdate.getCenter());
                break;
            case BOUNDS_PADDING:
            case BOUNDS_WIDTH_HEIGHT_PADDING:
                //noinspection ConstantConditions
                controller.animateTo(cameraUpdate.getBounds().getCenter());
                break;
            case GEOPOINT_ZOOM:
                controller.setZoom((int) cameraUpdate.getZoom());
                controller.animateTo(cameraUpdate.getCenter());
                break;
            case SCROLL_BY:
                controller.scrollBy((int) cameraUpdate.getXPixel(), (int) cameraUpdate.getYPixel());
                break;
            case ZOOM_BY_FOCUS:
                zoomByFocus(cameraUpdate);
                break;
            case ZOOM_BY:
                zoomBy(cameraUpdate);
                break;
            case ZOOM_IN:
                controller.zoomIn();
                break;
            case ZOOM_OUT:
                controller.zoomOut();
                break;
            case ZOOM_TO:
                controller.setZoom((int) cameraUpdate.getZoom());
                break;
        }
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
        return new OPFIndoorBuilding(new OsmdroidIndoorBuildingDelegate());
    }

    @NonNull
    @Override
    public OPFMapType getMapType() {
        return map.getMapType();
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
        return new OPFUiSettings(new OsmdroidUiSettingsDelegate(new UiSettings(map)));
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
        return false;
    }

    @Override
    public void moveCamera(@NonNull final OPFCameraUpdate update) {
        final IMapController controller = map.getController();
        final CameraUpdate cameraUpdate = (CameraUpdate) update.getDelegate().getCameraUpdate();

        switch (cameraUpdate.getCameraUpdateSource()) {
            case CAMERA_POSITION:
                controller.setCenter(cameraUpdate.getCenter());
                map.setMapOrientation(cameraUpdate.getBearing());
                break;
            case GEOPOINT:
                controller.setCenter(cameraUpdate.getCenter());
                break;
            case BOUNDS_PADDING:
            case BOUNDS_WIDTH_HEIGHT_PADDING:
                //noinspection ConstantConditions
                controller.setCenter(cameraUpdate.getBounds().getCenter());
                break;
            case GEOPOINT_ZOOM:
                controller.setZoom((int) cameraUpdate.getZoom());
                controller.setCenter(cameraUpdate.getCenter());
                break;
            case SCROLL_BY:
                controller.scrollBy((int) cameraUpdate.getXPixel(), (int) cameraUpdate.getYPixel());
                break;
            case ZOOM_BY_FOCUS:
                zoomByFocus(cameraUpdate);
                break;
            case ZOOM_BY:
                zoomBy(cameraUpdate);
                break;
            case ZOOM_IN:
                controller.zoomIn();
                break;
            case ZOOM_OUT:
                controller.zoomOut();
                break;
            case ZOOM_TO:
                controller.setZoom((int) cameraUpdate.getZoom());
                break;
        }
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
        this.opfInfoWindowAdapter = adapter;

        final List<Overlay> overlayList = map.getOverlays();
        for (Overlay overlay : overlayList) {
            if (overlay instanceof Marker) {
                final Marker marker = (Marker) overlay;
                initMarkerInfoWindows(marker, new OPFMarker(new OsmdroidMarkerDelegate(map, marker)));
            }
        }
    }

    @Override
    public void setLocationSource(@NonNull final OPFLocationSource source) {
        OPFLog.logStubCall(source);
    }

    @Override
    public void setMapType(@NonNull final OPFMapType type) {
        map.setMapType(type);
    }

    @Override
    public void setMyLocationEnabled(final boolean enabled) {
        map.setMyLocationEnabled(enabled);
    }

    @Override
    public void setOnCameraChangeListener(@NonNull final OPFOnCameraChangeListener listener) {
        OPFLog.logStubCall(listener);
    }

    @Override
    public void setOnIndoorStateChangeListener(@NonNull final OPFOnIndoorStateChangeListener listener) {
        OPFLog.logStubCall(listener);
    }

    @Override
    public void setOnInfoWindowClickListener(@NonNull final OPFOnInfoWindowClickListener listener) {
        this.opfOnInfoWindowClickListener = listener;
    }

    @Override
    public void setOnMapClickListener(@NonNull final OPFOnMapClickListener listener) {
        map.setOnMapClickListener(listener);
    }

    @Override
    public void setOnMapLoadedCallback(@NonNull final OPFOnMapLoadedCallback callback) {
        callback.onMapLoaded();
    }

    @Override
    public void setOnMapLongClickListener(@NonNull final OPFOnMapLongClickListener listener) {
        map.setOnMapLongClickListener(listener);
    }

    @Override
    public void setOnMarkerClickListener(@NonNull final OPFOnMarkerClickListener listener) {
        this.opfOnMarkerClickListener = listener;
    }

    @Override
    public void setOnMarkerDragListener(@NonNull final OPFOnMarkerDragListener listener) {
        this.opfOnMarkerDragListener = listener;
    }

    @Override
    public void setOnMyLocationButtonClickListener(@NonNull final OPFOnMyLocationButtonClickListener listener) {
        map.setOnMyLocationButtonClickListener(listener);
    }

    @Override
    public void setPadding(final int left, final int top, final int right, final int bottom) {
        map.setPadding(left, top, right, bottom);
    }

    @Override
    public void setTrafficEnabled(final boolean enabled) {
        OPFLog.logStubCall(enabled);
    }

    @Override
    public void snapshot(@NonNull final OPFSnapshotReadyCallback callback, @NonNull final Bitmap bitmap) {
        OPFLog.logStubCall(callback, bitmap);
    }

    @Override
    public void snapshot(@NonNull final OPFSnapshotReadyCallback callback) {
        OPFLog.logStubCall(callback);
    }

    @Override
    public void stopAnimation() {
        map.getController().stopAnimation(false);
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

    private void zoomByFocus(@NonNull final CameraUpdate cameraUpdate) {
        final Point focus = cameraUpdate.getFocus();
        if (focus == null) {
            return;
        }

        final IMapController controller = map.getController();
        final int zoomAmount = Math.abs((int) cameraUpdate.getZoom());
        final boolean isZoomIn = cameraUpdate.getZoom() > 0;

        //noinspection ConstantConditions
        final int x = focus.x;
        final int y = focus.y;
        for (int i = 0; i < zoomAmount; ++i) {
            if (isZoomIn) {
                controller.zoomInFixing(x, y);
            } else {
                controller.zoomOutFixing(x, y);
            }
        }
    }

    private void zoomBy(@NonNull final CameraUpdate cameraUpdate) {
        final IMapController controller = map.getController();
        final int currentZoomLevel = map.getZoomLevel();
        final int zoomAmount = (int) cameraUpdate.getZoom();
        controller.setZoom(currentZoomLevel + zoomAmount);
    }

    private void initMarkerInfoWindows(@NonNull final Marker marker, @NonNull final OPFMarker opfMarker) {
        //Set custom info window
        if (opfInfoWindowAdapter != null) {
            final View infoWindowView = opfInfoWindowAdapter.getInfoWindow(opfMarker);
            final View infoContentsView = opfInfoWindowAdapter.getInfoContents(opfMarker);
            if (infoWindowView != null) {
                marker.setInfoWindow(new MarkerInfoWindow(infoWindowView, map));
            } else if (infoContentsView != null) {
                marker.setInfoWindow(new MarkerInfoWindow(infoContentsView, map, true));
            }
        }

        //Set info window click listener
        final InfoWindow infoWindow = marker.getInfoWindow();
        if (infoWindow != null) {
            infoWindow.getView().setOnTouchListener(new View.OnTouchListener() {

                @SuppressLint("ClickableViewAccessibility")
                @Override
                public boolean onTouch(final View v, final MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP && opfOnInfoWindowClickListener != null) {
                        opfOnInfoWindowClickListener.onInfoWindowClick(opfMarker);
                    }
                    return true;
                }
            });
        }
    }
}
