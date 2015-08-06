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

package org.onepf.opfmaps;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
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
 * @since 30.07.2015
 */
@SuppressWarnings({"PMD.GodClass", "PMD.ExcessivePublicCount"})
public final class OPFMap implements MapDelegate {

    @NonNull
    private final MapDelegate delegate;

    public OPFMap(@NonNull final MapDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    @NonNull
    public OPFCircle addCircle(@NonNull final OPFCircleOptions options) {
        return delegate.addCircle(options);
    }

    @Override
    @NonNull
    public OPFGroundOverlay addGroundOverlay(@NonNull final OPFGroundOverlayOptions options) {
        return delegate.addGroundOverlay(options);
    }

    @Override
    @NonNull
    public OPFMarker addMarker(@NonNull final OPFMarkerOptions options) {
        return delegate.addMarker(options);
    }

    @Override
    @NonNull
    public OPFPolygon addPolygon(@NonNull final OPFPolygonOptions options) {
        return delegate.addPolygon(options);
    }

    @Override
    @NonNull
    public OPFPolyline addPolyline(@NonNull final OPFPolylineOptions options) {
        return delegate.addPolyline(options);
    }

    @Override
    @NonNull
    public OPFTileOverlay addTileOverlay(@NonNull final OPFTileOverlayOptions options) {
        return delegate.addTileOverlay(options);
    }

    @Override
    public void animateCamera(@NonNull final OPFCameraUpdate update,
                              final int durationMs,
                              @NonNull final OPFCancelableCallback callback) {
        delegate.animateCamera(update, durationMs, callback);
    }

    @Override
    public void animateCamera(@NonNull final OPFCameraUpdate update, @NonNull final OPFCancelableCallback callback) {
        delegate.animateCamera(update, callback);
    }

    @Override
    public void animateCamera(@NonNull final OPFCameraUpdate update) {
        delegate.animateCamera(update);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @NonNull
    @Override
    public OPFCameraPosition getCameraPosition() {
        return delegate.getCameraPosition();
    }

    @NonNull
    @Override
    public OPFIndoorBuilding getFocusedBuilding() {
        return delegate.getFocusedBuilding();
    }

    @NonNull
    @Override
    public OPFMapType getMapType() {
        return delegate.getMapType();
    }

    @Override
    public float getMaxZoomLevel() {
        return delegate.getMaxZoomLevel();
    }

    @Override
    public float getMinZoomLevel() {
        return delegate.getMinZoomLevel();
    }

    @NonNull
    @Override
    public OPFProjection getProjection() {
        return delegate.getProjection();
    }

    @NonNull
    @Override
    public OPFUiSettings getUiSettings() {
        return delegate.getUiSettings();
    }

    @Override
    public boolean isBuildingsEnabled() {
        return delegate.isBuildingsEnabled();
    }

    @Override
    public boolean isIndoorEnabled() {
        return delegate.isIndoorEnabled();
    }

    @Override
    public boolean isMyLocationEnabled() {
        return delegate.isMyLocationEnabled();
    }

    @Override
    public boolean isTrafficEnabled() {
        return delegate.isTrafficEnabled();
    }

    @Override
    public void moveCamera(@NonNull final OPFCameraUpdate update) {
        delegate.moveCamera(update);
    }

    @Override
    public void setBuildingsEnabled(final boolean enabled) {
        delegate.setBuildingsEnabled(enabled);
    }

    @Override
    public void setContentDescription(@NonNull final String description) {
        delegate.setContentDescription(description);
    }

    @Override
    public boolean setIndoorEnabled(final boolean enabled) {
        return delegate.setIndoorEnabled(enabled);
    }

    @Override
    public void setInfoWindowAdapter(@NonNull final OPFInfoWindowAdapter adapter) {
        delegate.setInfoWindowAdapter(adapter);
    }

    @Override
    public void setLocationSource(@NonNull final OPFLocationSource source) {
        delegate.setLocationSource(source);
    }

    @Override
    public void setMapType(@NonNull final OPFMapType type) {
        delegate.setMapType(type);
    }

    @Override
    public void setMyLocationEnabled(final boolean enabled) {
        delegate.setMyLocationEnabled(enabled);
    }

    @Override
    public void setOnCameraChangeListener(@NonNull final OPFOnCameraChangeListener listener) {
        delegate.setOnCameraChangeListener(listener);
    }

    @Override
    public void setOnIndoorStateChangeListener(@NonNull final OPFOnIndoorStateChangeListener listener) {
        delegate.setOnIndoorStateChangeListener(listener);
    }

    @Override
    public void setOnInfoWindowClickListener(@NonNull final OPFOnInfoWindowClickListener listener) {
        delegate.setOnInfoWindowClickListener(listener);
    }

    @Override
    public void setOnMapClickListener(@NonNull final OPFOnMapClickListener listener) {
        delegate.setOnMapClickListener(listener);
    }

    @Override
    public void setOnMapLoadedCallback(@NonNull final OPFOnMapLoadedCallback callback) {
        delegate.setOnMapLoadedCallback(callback);
    }

    @Override
    public void setOnMapLongClickListener(@NonNull final OPFOnMapLongClickListener listener) {
        delegate.setOnMapLongClickListener(listener);
    }

    @Override
    public void setOnMarkerClickListener(@NonNull final OPFOnMarkerClickListener listener) {
        delegate.setOnMarkerClickListener(listener);
    }

    @Override
    public void setOnMarkerDragListener(@NonNull final OPFOnMarkerDragListener listener) {
        delegate.setOnMarkerDragListener(listener);
    }

    @Override
    public void setOnMyLocationButtonClickListener(@NonNull final OPFOnMyLocationButtonClickListener listener) {
        delegate.setOnMyLocationButtonClickListener(listener);
    }

    @Override
    public void setPadding(final int left, final int top, final int right, final int bottom) {
        delegate.setPadding(left, top, right, bottom);
    }

    @Override
    public void setTrafficEnabled(final boolean enabled) {
        delegate.setTrafficEnabled(enabled);
    }

    @Override
    public void snapshot(@NonNull final OPFSnapshotReadyCallback callback, @NonNull final Bitmap bitmap) {
        delegate.snapshot(callback, bitmap);
    }

    @Override
    public void snapshot(@NonNull final OPFSnapshotReadyCallback callback) {
        delegate.snapshot(callback);
    }

    @Override
    public void stopAnimation() {
        delegate.stopAnimation();
    }

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OPFMap)) return false;

        return delegate.equals(((OPFMap) other).delegate);
    }
    //CHECKSTYLE:ON

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public String toString() {
        return delegate.toString();
    }
}
