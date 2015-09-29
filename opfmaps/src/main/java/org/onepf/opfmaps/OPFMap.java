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

import android.support.annotation.Nullable;
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
 * This is the main class of the OPF Maps and is the entry point for all methods related to the map.
 * You cannot instantiate a {@link OPFMap} object directly, rather, you must obtain one from the {@code getMapAsync()}
 * method on a {@link OPFMapFragment} or {@link OPFMapView} that you have added to your application.
 * <p/>
 * Note: {@link OPFMap} can only be read and modified from the main thread.
 * Calling {@link OPFMap} methods from another thread will result in an exception.
 *
 * @author Roman Savin
 * @since 30.07.2015
 */
@SuppressWarnings("PMD.ExcessivePublicCount")
public final class OPFMap implements MapDelegate {

    @NonNull
    private final MapDelegate delegate;

    public OPFMap(@NonNull final MapDelegate delegate) {
        this.delegate = delegate;
    }

    /**
     * Add a circle to this map.
     *
     * @param options A circle options object that defines how to render the circle
     * @return The {@link OPFCircle} object that was added to the map.
     */
    @Override
    @NonNull
    public OPFCircle addCircle(@NonNull final OPFCircleOptions options) {
        return delegate.addCircle(options);
    }

    /**
     * Adds an image to this map.
     *
     * @param options A ground-overlay options object that defines how to render the overlay.
     *                Options must have an image (AnchoredBitmap) and position specified.
     * @return The {@link OPFGroundOverlay} object that was added to the map.
     */
    @Override
    @NonNull
    public OPFGroundOverlay addGroundOverlay(@NonNull final OPFGroundOverlayOptions options) {
        return delegate.addGroundOverlay(options);
    }

    /**
     * Adds a marker to this map.
     *
     * @param options A marker options object that defines how to render the marker.
     * @return The {@link OPFMarker} object that was added to the map.
     */
    @Override
    @NonNull
    public OPFMarker addMarker(@NonNull final OPFMarkerOptions options) {
        return delegate.addMarker(options);
    }

    /**
     * Adds a polygon to this map.
     *
     * @param options A polygon options object that defines how to render the polygon.
     * @return The {@link OPFPolygon} object that was added to the map.
     */
    @Override
    @NonNull
    public OPFPolygon addPolygon(@NonNull final OPFPolygonOptions options) {
        return delegate.addPolygon(options);
    }

    /**
     * Adds a polyline to this map.
     *
     * @param options A polyline options object that defines how to render the polyline.
     * @return The {@link OPFPolyline} object that was added to the map.
     */
    @Override
    @NonNull
    public OPFPolyline addPolyline(@NonNull final OPFPolylineOptions options) {
        return delegate.addPolyline(options);
    }

    /**
     * Adds a tile overlay to this map.
     * <p/>
     * Note that unlike other overlays, if the map is recreated, tile overlays are not automatically restored and must be re-added manually.
     *
     * @param options A tile-overlay options object that defines how to render the overlay.
     *                Options must have a {@link org.onepf.opfmaps.model.OPFTileProvider} specified,
     *                otherwise an {@link IllegalArgumentException} will be thrown.
     * @return The {@link OPFTileOverlay} object that was added to the map.
     */
    @Override
    @NonNull
    public OPFTileOverlay addTileOverlay(@NonNull final OPFTileOverlayOptions options) {
        return delegate.addTileOverlay(options);
    }

    /**
     * Moves the map according to the update with an animation over a specified duration, and calls an optional callback on completion.
     *
     * @param update     The change that should be applied to the camera.
     * @param durationMs The duration of the animation in milliseconds.
     *                   This must be strictly positive, otherwise an {@link IllegalArgumentException} will be thrown.
     * @param callback   An optional callback to be notified from the main thread when the animation stops.
     *                   If the animation stops due to its natural completion, the callback will be notified with
     *                   {@link OPFCancelableCallback#onFinish()}. If the animation stops due to interruption by a later
     *                   camera movement or a user gesture, {@link OPFCancelableCallback#onCancel()} will be called.
     *                   The callback should not attempt to move or animate the camera in its cancellation method.
     *                   If a callback isn't required, leave it as null.
     */
    @Override
    public void animateCamera(@NonNull final OPFCameraUpdate update,
                              final int durationMs,
                              @Nullable final OPFCancelableCallback callback) {
        delegate.animateCamera(update, durationMs, callback);
    }

    /**
     * Animates the movement of the camera from the current position to the position defined in the update and call
     * an optional callback on completion.
     *
     * @param update   The change that should be applied to the camera.
     * @param callback The callback to invoke from the main thread when the animation stops.
     *                 If the animation completes normally, {@link OPFCancelableCallback#onFinish()} is called;
     *                 otherwise, {@link OPFCancelableCallback#onCancel()} is called.
     *                 Do not update or animate the camera from within {@link OPFCancelableCallback#onCancel()).
     */
    @Override
    public void animateCamera(@NonNull final OPFCameraUpdate update, @Nullable final OPFCancelableCallback callback) {
        delegate.animateCamera(update, callback);
    }

    /**
     * Animates the movement of the camera from the current position to the position defined in the update.
     *
     * @param update The change that should be applied to the camera.
     */
    @Override
    public void animateCamera(@NonNull final OPFCameraUpdate update) {
        delegate.animateCamera(update);
    }

    /**
     * Removes all markers, polylines, polygons, overlays, etc from the map.
     */
    @Override
    public void clear() {
        delegate.clear();
    }

    /**
     * Gets the current position of the camera.
     * The {@link OPFCameraPosition} returned is a snapshot of the current position, and will not automatically update when the camera moves.
     *
     * @return The current position of the camera.
     */
    @NonNull
    @Override
    public OPFCameraPosition getCameraPosition() {
        return delegate.getCameraPosition();
    }

    /**
     * Gets the currently focused building.
     *
     * @return The current focused building or {@code null} if no building is focused.
     */
    @Nullable
    @Override
    public OPFIndoorBuilding getFocusedBuilding() {
        return delegate.getFocusedBuilding();
    }

    /**
     * Gets the type of map that's currently displayed.
     *
     * @return The map type.
     */
    @NonNull
    @Override
    public OPFMapType getMapType() {
        return delegate.getMapType();
    }

    /**
     * Returns the maximum zoom level for the current camera position.
     * This takes into account what map type is currently being used, e.g., satellite or terrain may have
     * a lower max zoom level than the base map tiles.
     *
     * @return The maximum zoom level available at the current camera position.
     */
    @Override
    public float getMaxZoomLevel() {
        return delegate.getMaxZoomLevel();
    }

    /**
     * Returns the minimum zoom level. This is the same for every location (unlike the maximum zoom level) but may
     * vary between devices and map sizes.
     *
     * @return The minimum zoom level available.
     */
    @Override
    public float getMinZoomLevel() {
        return delegate.getMinZoomLevel();
    }

    /**
     * Returns a {@link OPFProjection} object that you can use to convert between screen coordinates and latitude/longitude coordinates.
     * The {@link OPFProjection} returned is a snapshot of the current projection, and will not automatically update
     * when the camera moves. As this operation is expensive, you should get the projection only once per screen.
     *
     * @return The {@link OPFProjection} of the map in its current state.
     */
    @NonNull
    @Override
    public OPFProjection getProjection() {
        return delegate.getProjection();
    }

    /**
     * Gets the user interface settings for the map.
     *
     * @return The {@link OPFUiSettings} for this map.
     */
    @NonNull
    @Override
    public OPFUiSettings getUiSettings() {
        return delegate.getUiSettings();
    }

    /**
     * Returns whether 3D buildings layer is enabled.
     *
     * @return {@code true} if buildings are enabled, {@code false} otherwise.
     */
    @Override
    public boolean isBuildingsEnabled() {
        return delegate.isBuildingsEnabled();
    }

    /**
     * Gets whether indoor maps are currently enabled.
     *
     * @return {@code true} if indoor maps are enabled, {@code false} otherwise.
     */
    @Override
    public boolean isIndoorEnabled() {
        return delegate.isIndoorEnabled();
    }

    /**
     * Gets the status of the my-location layer.
     *
     * @return {@code true} if the my-location layer is enabled, {@code false} otherwise.
     */
    @Override
    public boolean isMyLocationEnabled() {
        return delegate.isMyLocationEnabled();
    }

    /**
     * Checks whether the map is drawing traffic data. This is subject to the availability of traffic data.
     *
     * @return {@code true} if traffic data is enabled, {@code false} otherwise.
     */
    @Override
    public boolean isTrafficEnabled() {
        return delegate.isTrafficEnabled();
    }

    /**
     * Repositions the camera according to the instructions defined in the update.
     *
     * @param update The change that should be applied to the camera.
     */
    @Override
    public void moveCamera(@NonNull final OPFCameraUpdate update) {
        delegate.moveCamera(update);
    }

    /**
     * Turns the 3D buildings layer on or off.
     *
     * @param enabled {@code true} to enable the 3D buildings layer, {@code false} otherwise.
     */
    @Override
    public void setBuildingsEnabled(final boolean enabled) {
        delegate.setBuildingsEnabled(enabled);
    }

    /**
     * Sets a contentDescription for the map.
     *
     * @param description A string to use as a description.
     */
    @Override
    public void setContentDescription(@NonNull final String description) {
        delegate.setContentDescription(description);
    }

    /**
     * Sets whether indoor maps should be enabled.
     *
     * @param enabled {@code true} to try to enable indoor maps; {@code false} to disable indoor maps.
     * @return Whether it was possible to enable indoor maps.
     */
    @Override
    public boolean setIndoorEnabled(final boolean enabled) {
        return delegate.setIndoorEnabled(enabled);
    }

    /**
     * Sets a custom renderer for the contents of info windows.
     *
     * @param adapter The adapter to use for info window contents, or null to use the default content rendering in info windows.
     */
    @Override
    public void setInfoWindowAdapter(@NonNull final OPFInfoWindowAdapter adapter) {
        delegate.setInfoWindowAdapter(adapter);
    }

    /**
     * Replaces the location source of the my-location layer.
     *
     * @param source A location source to use in the my-location layer. Set to {@code null} to use the default location source.
     */
    @Override
    public void setLocationSource(@NonNull final OPFLocationSource source) {
        delegate.setLocationSource(source);
    }

    /**
     * Sets the type of map tiles that should be displayed.
     *
     * @param type The type of map to display.
     */
    @Override
    public void setMapType(@NonNull final OPFMapType type) {
        delegate.setMapType(type);
    }

    /**
     * Enables or disables the my-location layer.
     *
     * @param enabled {@code true} to enable; {@code false} to disable.
     */
    @Override
    public void setMyLocationEnabled(final boolean enabled) {
        delegate.setMyLocationEnabled(enabled);
    }

    /**
     * Sets a callback that's invoked when the camera changes.
     *
     * @param listener The callback that's invoked when the camera changes. To unset the callback, use {@code null}.
     */
    @Override
    public void setOnCameraChangeListener(@Nullable final OPFOnCameraChangeListener listener) {
        delegate.setOnCameraChangeListener(listener);
    }

    /**
     * Sets or clears the listener for indoor events. Only one listener can ever be set.
     * Setting a new listener will remove the previous listener.
     *
     * @param listener The listener for indoor events if non-null; otherwise, clears the listener
     */
    @Override
    public void setOnIndoorStateChangeListener(@Nullable final OPFOnIndoorStateChangeListener listener) {
        delegate.setOnIndoorStateChangeListener(listener);
    }

    /**
     * Sets a callback that's invoked when a marker info window is clicked.
     *
     * @param listener The callback that's invoked when a marker info window is clicked. To unset the callback, use {@code null}.
     */
    @Override
    public void setOnInfoWindowClickListener(@Nullable final OPFOnInfoWindowClickListener listener) {
        delegate.setOnInfoWindowClickListener(listener);
    }

    /**
     * Sets a callback that's invoked when the map is tapped.
     *
     * @param listener The callback that's invoked when the map is tapped. To unset the callback, use {@code null}.
     */
    @Override
    public void setOnMapClickListener(@Nullable final OPFOnMapClickListener listener) {
        delegate.setOnMapClickListener(listener);
    }

    /**
     * Sets a callback that is invoked when this map has finished rendering. The callback will only be invoked once.
     * If this method is called when the map is fully rendered, the callback will be invoked immediately.
     * This event will not fire if the map never loads due to connectivity issues,
     * or if the map is continuously changing and never completes loading due to the user constantly interacting with the map.
     *
     * @param callback The callback invoked when the map has finished rendering. To unset the callback, use {@code null}.
     */
    @Override
    public void setOnMapLoadedCallback(@Nullable final OPFOnMapLoadedCallback callback) {
        delegate.setOnMapLoadedCallback(callback);
    }

    /**
     * Sets a callback that's invoked when the map is long pressed.
     *
     * @param listener The callback that's invoked when the map is long pressed. To unset the callback, use {@code null}.
     */
    @Override
    public void setOnMapLongClickListener(@Nullable final OPFOnMapLongClickListener listener) {
        delegate.setOnMapLongClickListener(listener);
    }

    /**
     * Sets a callback that's invoked when a marker is clicked.
     *
     * @param listener The callback that's invoked when a marker is clicked. To unset the callback, use {@code null}.
     */
    @Override
    public void setOnMarkerClickListener(@Nullable final OPFOnMarkerClickListener listener) {
        delegate.setOnMarkerClickListener(listener);
    }

    /**
     * Sets a callback that's invoked when a marker is dragged.
     *
     * @param listener The callback that's invoked on marker drag events. To unset the callback, use {@code null}.
     */
    @Override
    public void setOnMarkerDragListener(@Nullable final OPFOnMarkerDragListener listener) {
        delegate.setOnMarkerDragListener(listener);
    }

    /**
     * Sets a callback that's invoked when the my location button is clicked.
     * <p/>
     * If the {@code listener} returns {@code true}, the event is consumed and the default behavior will not occur.
     * If it returns {@code false}, the default behavior will occur (i.e. The camera moves such that it is centered on the user's location).
     *
     * @param listener The callback that's invoked when the My Location button is clicked.
     */
    @Override
    public void setOnMyLocationButtonClickListener(@Nullable final OPFOnMyLocationButtonClickListener listener) {
        delegate.setOnMyLocationButtonClickListener(listener);
    }

    /**
     * Sets padding on the map. This method allows you to define a visible region on the map,
     * to signal to the map that portions of the map around the edges may be obscured,
     * by setting padding on each of the four edges of the map.
     *
     * @param left   The number of pixels of padding to be added on the left of the map.
     * @param top    The number of pixels of padding to be added on the top of the map.
     * @param right  The number of pixels of padding to be added on the right of the map.
     * @param bottom The number of pixels of padding to be added on the bottom of the map.
     */
    @Override
    public void setPadding(final int left, final int top, final int right, final int bottom) {
        delegate.setPadding(left, top, right, bottom);
    }

    /**
     * Turns the traffic layer on or off.
     *
     * @param enabled {@code true} if the traffic overlay should be enabled, {@code false} otherwise.
     */
    @Override
    public void setTrafficEnabled(final boolean enabled) {
        delegate.setTrafficEnabled(enabled);
    }

    /**
     * Takes a snapshot of the map.
     *
     * @param callback Callback method invoked when the snapshot is taken.
     * @param bitmap   A preallocated bitmap. If {@code null}, behaves like {@link #snapshot(OPFSnapshotReadyCallback)}.
     */
    @Override
    public void snapshot(@NonNull final OPFSnapshotReadyCallback callback, @NonNull final Bitmap bitmap) {
        delegate.snapshot(callback, bitmap);
    }

    /**
     * Takes a snapshot of the map.
     *
     * @param callback Callback method invoked when the snapshot is taken.
     */
    @Override
    public void snapshot(@NonNull final OPFSnapshotReadyCallback callback) {
        delegate.snapshot(callback);
    }

    /**
     * Stops the camera animation if there is one in progress. When the method is called,
     * the camera stops moving immediately and remains in that position.
     */
    @Override
    public void stopAnimation() {
        delegate.stopAnimation();
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OPFMap
                && delegate.equals(((OPFMap) other).delegate));
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public String toString() {
        return delegate.toString();
    }
}
