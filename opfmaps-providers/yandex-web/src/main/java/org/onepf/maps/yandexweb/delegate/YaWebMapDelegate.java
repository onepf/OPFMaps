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
import android.support.annotation.NonNull;

import android.webkit.WebView;
import org.onepf.maps.yandexweb.delegate.model.YaWebCircleDelegate;
import org.onepf.maps.yandexweb.delegate.model.YaWebIndoorBuildingDelegate;
import org.onepf.maps.yandexweb.delegate.model.YaWebTileOverlayDelegate;
import org.onepf.maps.yandexweb.delegate.model.YaWebUiSettingsDelegate;
import org.onepf.maps.yandexweb.jsi.JSYandexMapProxy;
import org.onepf.maps.yandexweb.model.Circle;
import org.onepf.maps.yandexweb.model.LatLng;
import org.onepf.maps.yandexweb.model.UiSettings;
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

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
@SuppressWarnings({"PMD.ExcessivePublicCount", "PMD.TooManyMethods"})
public class YaWebMapDelegate implements MapDelegate {

    private static final float MAX_ZOOM_LEVEL = 23.0f;
    private static final float MIN_ZOOM_LEVEL = 3.0f;

    @NonNull
    private final YaWebMapViewDelegate map;

    public YaWebMapDelegate(@NonNull final YaWebMapViewDelegate map) {
        this.map = map;
    }

    @NonNull
    @Override
    public OPFCircle addCircle(@NonNull final OPFCircleOptions options) {
        final OPFLatLng center = options.getCenter();
        if (center == null) {
            throw new IllegalArgumentException("Circle center can't be null");
        }

        final Circle circle = new Circle(
                map,
                new LatLng(center.getLat(), center.getLng()),
                options.getFillColor(),
                options.getRadius(),
                options.getStrokeColor(),
                options.getStrokeWidth(),
                options.getZIndex(),
                options.isVisible()
        );

        JSYandexMapProxy.addCircle(map, circle);
        return new OPFCircle(new YaWebCircleDelegate(circle));
    }

    @NonNull
    @Override
    public OPFGroundOverlay addGroundOverlay(@NonNull final OPFGroundOverlayOptions options) {
        //todo implement
        return null;
    }

    @NonNull
    @Override
    public OPFMarker addMarker(@NonNull final OPFMarkerOptions options) {
        //todo implement
        return null;
    }

    @NonNull
    @Override
    public OPFPolygon addPolygon(@NonNull final OPFPolygonOptions options) {
        //todo implement
        return null;
    }

    @NonNull
    @Override
    public OPFPolyline addPolyline(@NonNull final OPFPolylineOptions options) {
        //todo implement
        return null;
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
        //todo implement
    }

    @NonNull
    @Override
    public OPFCameraPosition getCameraPosition() {
        //todo implement
        return null;
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
        //todo implement
        return null;
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
        //todo implement
        OPFLog.logStubCall(listener);
    }

    @Override
    public void setOnIndoorStateChangeListener(@NonNull final OPFOnIndoorStateChangeListener listener) {
        OPFLog.logStubCall(listener);
    }

    @Override
    public void setOnInfoWindowClickListener(@NonNull final OPFOnInfoWindowClickListener listener) {
        //todo implement
    }

    @Override
    public void setOnMapClickListener(@NonNull final OPFOnMapClickListener listener) {
        //todo implement
    }

    @Override
    public void setOnMapLoadedCallback(@NonNull final OPFOnMapLoadedCallback callback) {
        callback.onMapLoaded();
    }

    @Override
    public void setOnMapLongClickListener(@NonNull final OPFOnMapLongClickListener listener) {
        //todo implement
    }

    @Override
    public void setOnMarkerClickListener(@NonNull final OPFOnMarkerClickListener listener) {
        //todo implement
    }

    @Override
    public void setOnMarkerDragListener(@NonNull final OPFOnMarkerDragListener listener) {
        //todo implement
    }

    @Override
    public void setOnMyLocationButtonClickListener(@NonNull final OPFOnMyLocationButtonClickListener listener) {
        //todo implement
    }

    @Override
    public void setPadding(final int left, final int top, final int right, final int bottom) {
        //todo implement
    }

    @Override
    public void setTrafficEnabled(final boolean enabled) {
        //todo implement
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
}
