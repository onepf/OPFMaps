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

package org.onepf.maps.yandexweb.delegate;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import org.onepf.maps.yandexweb.delegate.model.YaWebCameraPositionDelegate;
import org.onepf.maps.yandexweb.delegate.model.YaWebCircleDelegate;
import org.onepf.maps.yandexweb.delegate.model.YaWebGroundOverlayDelegate;
import org.onepf.maps.yandexweb.delegate.model.YaWebIndoorBuildingDelegate;
import org.onepf.maps.yandexweb.delegate.model.YaWebLatLngDelegate;
import org.onepf.maps.yandexweb.delegate.model.YaWebMarkerDelegate;
import org.onepf.maps.yandexweb.delegate.model.YaWebPolygonDelegate;
import org.onepf.maps.yandexweb.delegate.model.YaWebPolylineDelegate;
import org.onepf.maps.yandexweb.delegate.model.YaWebProjectionDelegate;
import org.onepf.maps.yandexweb.delegate.model.YaWebTileOverlayDelegate;
import org.onepf.maps.yandexweb.delegate.model.YaWebUiSettingsDelegate;
import org.onepf.maps.yandexweb.jsi.JSYandexMapProxy;
import org.onepf.maps.yandexweb.model.BitmapDescriptor;
import org.onepf.maps.yandexweb.model.BitmapDescriptorFactory;
import org.onepf.maps.yandexweb.model.CameraPosition;
import org.onepf.maps.yandexweb.model.CameraUpdate;
import org.onepf.maps.yandexweb.model.Circle;
import org.onepf.maps.yandexweb.model.GroundOverlay;
import org.onepf.maps.yandexweb.model.LatLng;
import org.onepf.maps.yandexweb.model.Marker;
import org.onepf.maps.yandexweb.model.Polygon;
import org.onepf.maps.yandexweb.model.Polyline;
import org.onepf.maps.yandexweb.model.Projection;
import org.onepf.maps.yandexweb.model.UiSettings;
import org.onepf.maps.yandexweb.utils.ConvertUtils;
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
import org.onepf.opfmaps.model.OPFBitmapDescriptor;
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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
@SuppressWarnings({"PMD.ExcessivePublicCount", "PMD.TooManyMethods"})
public class YaWebMapDelegate implements MapDelegate {

    public static final float MAX_ZOOM_LEVEL = 23.0f;
    public static final float MIN_ZOOM_LEVEL = 2.0f;

    @NonNull
    private final YaWebMapViewDelegate map;

    @Nullable
    private Projection projection;

    @Nullable
    private OPFOnMarkerClickListener opfOnMarkerClickListener;
    @Nullable
    private OPFOnMarkerDragListener opfOnMarkerDragListener;
    @Nullable
    private OPFOnMapClickListener opfOnMapClickListener;
    @Nullable
    private OPFOnCameraChangeListener opfOnCameraChangeListener;
    @Nullable
    private OPFOnMapLongClickListener opfOnMapLongClickListener;
    @Nullable
    private OPFOnMyLocationButtonClickListener opfOnMyLocationButtonClickListener;

    @Nullable
    private Marker currentDragMarker;

    @NonNull
    private final Map<String, Marker> markersByIds = new HashMap<>();

    public YaWebMapDelegate(@NonNull final YaWebMapViewDelegate map) {
        this.map = map;
    }

    @NonNull
    @Override
    public OPFCircle addCircle(@NonNull final OPFCircleOptions options) {
        final Circle circle = ConvertUtils.convertCircleOptions(map, options);

        JSYandexMapProxy.addCircle(map, circle);
        return new OPFCircle(new YaWebCircleDelegate(circle));
    }

    @NonNull
    @Override
    public OPFGroundOverlay addGroundOverlay(@NonNull final OPFGroundOverlayOptions options) {
        OPFLog.logStubCall(options);
        return new OPFGroundOverlay(new YaWebGroundOverlayDelegate(new GroundOverlay()));
    }

    @NonNull
    @Override
    public OPFMarker addMarker(@NonNull final OPFMarkerOptions options) {
        final OPFBitmapDescriptor opfBitmapDescriptor = options.getIcon();
        final BitmapDescriptor bitmapDescriptor = opfBitmapDescriptor != null
                ? (BitmapDescriptor) opfBitmapDescriptor.getDelegate().getBitmapDescriptor()
                : BitmapDescriptorFactory.defaultMarker();

        final Marker marker = ConvertUtils.convertMarkerOptions(map, markersByIds, options);

        markersByIds.put(marker.getId(), marker);

        JSYandexMapProxy.addMarker(map, marker, bitmapDescriptor.getRGBColor());
        return new OPFMarker(new YaWebMarkerDelegate(marker));
    }

    @NonNull
    @Override
    public OPFPolygon addPolygon(@NonNull final OPFPolygonOptions options) {
        final Polygon polygon = ConvertUtils.convertPolygonOptions(map, options);

        JSYandexMapProxy.addPolygon(map, polygon);
        return new OPFPolygon(new YaWebPolygonDelegate(polygon));
    }

    @NonNull
    @Override
    public OPFPolyline addPolyline(@NonNull final OPFPolylineOptions options) {
        final Polyline polyline = ConvertUtils.convertPolylineOptions(map, options);

        JSYandexMapProxy.addPolyline(map, polyline);
        return new OPFPolyline(new YaWebPolylineDelegate(polyline));
    }

    @NonNull
    @Override
    public OPFTileOverlay addTileOverlay(@NonNull final OPFTileOverlayOptions options) {
        return new OPFTileOverlay(new YaWebTileOverlayDelegate());
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
        moveCamera(update);
    }

    @Override
    public void clear() {
        JSYandexMapProxy.clearMap(map);
        markersByIds.clear();
    }

    @NonNull
    @Override
    public OPFCameraPosition getCameraPosition() {
        return new OPFCameraPosition(new YaWebCameraPositionDelegate(
                CameraPosition.fromLatLngZoom(map.getCenter(), map.getZoomLevel())
        ));
    }

    @NonNull
    @Override
    public OPFIndoorBuilding getFocusedBuilding() {
        OPFLog.logStubCall();
        return new OPFIndoorBuilding(new YaWebIndoorBuildingDelegate());
    }

    @NonNull
    @Override
    public OPFMapType getMapType() {
        return map.getMapType();
    }

    @Override
    public float getMaxZoomLevel() {
        return MAX_ZOOM_LEVEL;
    }

    @Override
    public float getMinZoomLevel() {
        return MIN_ZOOM_LEVEL;
    }

    @NonNull
    @Override
    public OPFProjection getProjection() {
        if (projection == null) {
            this.projection = new Projection(new Rect(0, 0, 0, 0), map.getContext(), MIN_ZOOM_LEVEL, 0, 0);
        }

        return new OPFProjection(new YaWebProjectionDelegate(projection));
    }

    @NonNull
    @Override
    public OPFUiSettings getUiSettings() {
        return new OPFUiSettings(new YaWebUiSettingsDelegate(new UiSettings(map)));
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
        final CameraUpdate cameraUpdate = (CameraUpdate) update.getDelegate().getCameraUpdate();

        switch (cameraUpdate.getCameraUpdateSource()) {
            case CAMERA_POSITION:
            case GEOPOINT:
                if (cameraUpdate.getCenter() != null) {
                    map.setCenter(cameraUpdate.getCenter());
                }
                break;
            case BOUNDS_PADDING:
            case BOUNDS_WIDTH_HEIGHT_PADDING:
                if (cameraUpdate.getBounds() != null) {
                    map.setCenter(cameraUpdate.getBounds().getCenter());
                }
                break;
            case GEOPOINT_ZOOM:
                map.setZoomLevel(cameraUpdate.getZoom());
                if (cameraUpdate.getCenter() != null) {
                    map.setCenter(cameraUpdate.getCenter());
                }
                break;
            case SCROLL_BY:
                scrollBy(cameraUpdate);
                break;
            case ZOOM_BY_FOCUS:
                zoomByFocus(cameraUpdate);
                break;
            case ZOOM_BY:
                zoomBy(cameraUpdate);
                break;
            case ZOOM_IN:
                map.setZoomLevel(map.getZoomLevel() + 1);
                break;
            case ZOOM_OUT:
                map.setZoomLevel(map.getZoomLevel() - 1);
                break;
            case ZOOM_TO:
                map.setZoomLevel(cameraUpdate.getZoom());
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
        OPFLog.logStubCall(adapter);
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
        this.opfOnCameraChangeListener = listener;
    }

    @Override
    public void setOnIndoorStateChangeListener(@NonNull final OPFOnIndoorStateChangeListener listener) {
        OPFLog.logStubCall(listener);
    }

    @Override
    public void setOnInfoWindowClickListener(@NonNull final OPFOnInfoWindowClickListener listener) {
        OPFLog.logStubCall(listener);
    }

    @Override
    public void setOnMapClickListener(@NonNull final OPFOnMapClickListener listener) {
        this.opfOnMapClickListener = listener;
    }

    @Override
    public void setOnMapLoadedCallback(@NonNull final OPFOnMapLoadedCallback callback) {
        callback.onMapLoaded();
    }

    @Override
    public void setOnMapLongClickListener(@NonNull final OPFOnMapLongClickListener listener) {
        this.opfOnMapLongClickListener = listener;
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
        this.opfOnMyLocationButtonClickListener = listener;
    }

    @Override
    public void setPadding(final int left, final int top, final int right, final int bottom) {
        OPFLog.logStubCall(left, top, right, bottom);
    }

    @Override
    public void setTrafficEnabled(final boolean enabled) {
        JSYandexMapProxy.setTrafficEnabled(map, enabled);
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
        OPFLog.logStubCall();
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof YaWebMapDelegate
                && map.equals(((YaWebMapDelegate) other).map));
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }

    @Override
    public String toString() {
        return map.toString();
    }

    boolean onMarkerClick(@NonNull final String markerId) {
        //noinspection SimplifiableIfStatement
        if (opfOnMarkerClickListener != null && markersByIds.containsKey(markerId)) {
            return opfOnMarkerClickListener.onMarkerClick(new OPFMarker(new YaWebMarkerDelegate(markersByIds.get(markerId))));
        }

        return false;
    }

    void onMarkerDragStart(@NonNull final String markerId, final double lat, final double lng) {
        if (opfOnMarkerDragListener != null && markersByIds.containsKey(markerId)) {
            currentDragMarker = createDraggableMarker(markerId, lat, lng);
            opfOnMarkerDragListener.onMarkerDragStart(new OPFMarker(new YaWebMarkerDelegate(currentDragMarker)));
        }
    }

    void onMarkerDrag(@NonNull final String markerId, final double lat, final double lng) {
        if (opfOnMarkerDragListener != null) {
            if (currentDragMarker != null && currentDragMarker.getId().equals(markerId)) {
                currentDragMarker.changePositionValue(new LatLng(lat, lng));
                opfOnMarkerDragListener.onMarkerDrag(new OPFMarker(new YaWebMarkerDelegate(currentDragMarker)));
            } else if (markersByIds.containsKey(markerId)) {
                currentDragMarker = createDraggableMarker(markerId, lat, lng);
                opfOnMarkerDragListener.onMarkerDrag(new OPFMarker(new YaWebMarkerDelegate(currentDragMarker)));
            }
        }
    }

    void onMarkerDragEnd(@NonNull final String markerId, final double lat, final double lng) {
        if (opfOnMarkerDragListener != null) {
            if (currentDragMarker != null && currentDragMarker.getId().equals(markerId)) {
                currentDragMarker.changePositionValue(new LatLng(lat, lng));
                opfOnMarkerDragListener.onMarkerDragEnd(new OPFMarker(new YaWebMarkerDelegate(currentDragMarker)));
            } else if (markersByIds.containsKey(markerId)) {
                opfOnMarkerDragListener.onMarkerDragEnd(new OPFMarker(new YaWebMarkerDelegate(createDraggableMarker(markerId, lat, lng))));
            }
        }
        currentDragMarker = null;
    }

    void onInfoWindowOpen(@NonNull final String markerId) {
        if (opfOnMarkerClickListener != null && markersByIds.containsKey(markerId)) {
            markersByIds.get(markerId).changeIsInfoWindowShownValue(true);
        }
    }

    void onInfoWindowClose(@NonNull final String markerId) {
        if (opfOnMarkerClickListener != null && markersByIds.containsKey(markerId)) {
            markersByIds.get(markerId).changeIsInfoWindowShownValue(false);
        }
    }

    void onMapClick(@NonNull final LatLng latLng) {
        if (opfOnMapClickListener != null) {
            opfOnMapClickListener.onMapClick(new OPFLatLng(new YaWebLatLngDelegate(latLng)));
        }
    }

    void onCameraChange(@NonNull final CameraPosition cameraPosition) {
        if (opfOnCameraChangeListener != null) {
            opfOnCameraChangeListener.onCameraChange(new OPFCameraPosition(new YaWebCameraPositionDelegate(cameraPosition)));
        }
    }

    void onLongPress(final Point point) {
        if (opfOnMapLongClickListener != null && projection != null) {
            final LatLng latLng = projection.fromScreenLocation(point);
            opfOnMapLongClickListener.onMapLongClick(new OPFLatLng(new YaWebLatLngDelegate(latLng)));
        }
    }

    void onMyLocationButtonClick() {
        if (opfOnMyLocationButtonClickListener != null) {
            opfOnMyLocationButtonClickListener.onMyLocationButtonClick();
        }
    }

    void initProjection(@NonNull final Rect screenRect, final float zoomLevel, final double offsetX, final double offsetY) {
        final DisplayMetrics displayMetrics = map.getResources().getDisplayMetrics();
        projection = new Projection(
                screenRect,
                map.getContext(),
                zoomLevel,
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) offsetX, displayMetrics),
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) offsetY, displayMetrics)
        );
    }

    void updateProjectionZoomLevel(final float zoomLevel, final double offsetX, final double offsetY) {
        OPFLog.logMethod(zoomLevel, offsetX, offsetY);
        if (projection != null) {
            projection.setZoomLevel(zoomLevel);

            final DisplayMetrics displayMetrics = map.getResources().getDisplayMetrics();
            projection.setOffsetX(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) offsetX, displayMetrics));
            projection.setOffsetY(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) offsetY, displayMetrics));
        }
    }

    private Marker createDraggableMarker(@NonNull final String markerId, final double lat, final double lng) {
        final Marker marker = markersByIds.get(markerId);
        marker.changePositionValue(new LatLng(lat, lng));
        return marker;
    }

    private void scrollBy(@NonNull final CameraUpdate cameraUpdate) {
        if (projection != null) {
            final Point centerPoint = projection.toScreenLocation(map.getCenter());
            centerPoint.offset((int) cameraUpdate.getXPixel(), (int) cameraUpdate.getYPixel());
            map.setCenter(projection.fromScreenLocation(centerPoint));
        }
    }

    private void zoomByFocus(@NonNull final CameraUpdate cameraUpdate) {
        if (projection == null) {
            return;
        }

        final Point focus = cameraUpdate.getFocus();
        if (focus == null) {
            return;
        }

        final LatLng center = projection.fromScreenLocation(focus);
        map.setZoomLevel(map.getZoomLevel() + cameraUpdate.getZoom());
        map.setCenter(center);
    }

    private void zoomBy(@NonNull final CameraUpdate cameraUpdate) {
        final float currentZoomLevel = map.getZoomLevel();
        final int zoomAmount = (int) cameraUpdate.getZoom();
        map.setZoomLevel(currentZoomLevel + zoomAmount);
    }
}
