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

package org.onepf.opfmaps.model;

import android.support.annotation.NonNull;

import org.onepf.opfmaps.delegate.model.UiSettingsDelegate;

/**
 * Settings for the user interface of a {@link org.onepf.opfmaps.OPFMap}.
 * To obtain this interface, call {@link org.onepf.opfmaps.OPFMap#getUiSettings()}.
 *
 * @author Roman Savin
 * @since 06.08.2015
 */
public class OPFUiSettings implements UiSettingsDelegate {

    @NonNull
    private final UiSettingsDelegate delegate;

    public OPFUiSettings(@NonNull final UiSettingsDelegate delegate) {
        this.delegate = delegate;
    }

    /**
     * Gets whether the compass is enabled/disabled.
     *
     * @return {@code true} if the compass is enabled, {@code false} otherwise.
     */
    @Override
    public boolean isCompassEnabled() {
        return delegate.isCompassEnabled();
    }

    /**
     * Gets whether the indoor level picker is enabled/disabled.
     * That is, whether the level picker will appear when a building with indoor maps is focused.
     *
     * @return {@code true} if the level picker is enabled, {@code false} otherwise.
     */
    @Override
    public boolean isIndoorLevelPickerEnabled() {
        return delegate.isIndoorLevelPickerEnabled();
    }

    /**
     * Gets whether the Map Toolbar is enabled/disabled.
     *
     * @return {@code true} if the Map Toolbar is enabled, {@code false} otherwise.
     */
    @Override
    public boolean isMapToolbarEnabled() {
        return delegate.isMapToolbarEnabled();
    }

    /**
     * Gets whether the my-location button is enabled/disabled.
     *
     * @return {@code true} if the my-location button is enabled, {@code false} otherwise.
     */
    @Override
    public boolean isMyLocationButtonEnabled() {
        return delegate.isMyLocationButtonEnabled();
    }

    /**
     * Gets whether rotate gestures are enabled/disabled.
     *
     * @return {@code true} if scroll gestures are enabled, {@code false} otherwise.
     */
    @Override
    public boolean isRotateGesturesEnabled() {
        return delegate.isRotateGesturesEnabled();
    }

    /**
     * Gets whether scroll gestures are enabled/disabled.
     *
     * @return {@code true} if scroll gestures are enabled, {@code false} otherwise.
     */
    @Override
    public boolean isScrollGesturesEnabled() {
        return delegate.isScrollGesturesEnabled();
    }

    /**
     * Gets whether tilt gestures are enabled/disabled.
     *
     * @return {@code true} if tilt gestures are enabled, {@code false} otherwise.
     */
    @Override
    public boolean isTiltGesturesEnabled() {
        return delegate.isTiltGesturesEnabled();
    }

    /**
     * Gets whether the zoom controls are enabled/disabled.
     *
     * @return {@code true} if the zoom controls are enabled, {@code false} otherwise.
     */
    @Override
    public boolean isZoomControlsEnabled() {
        return delegate.isZoomControlsEnabled();
    }

    /**
     * Gets whether zoom gestures are enabled/disabled.
     *
     * @return {@code true} if zoom gestures are enabled, {@code false} otherwise.
     */
    @Override
    public boolean isZoomGesturesEnabled() {
        return delegate.isZoomGesturesEnabled();
    }

    /**
     * Sets the preference for whether all gestures should be enabled or disabled.
     * If enabled, all gestures are available; otherwise, all gestures are disabled.
     * This doesn't restrict users from tapping any on screen buttons to move the camera (e.g., compass or zoom controls),
     * nor does it restrict programmatic movements and animation.
     *
     * @param enabled {@code true} to enable all gestures; {@code false} to disable all gestures.
     */
    @Override
    public void setAllGesturesEnabled(final boolean enabled) {
        delegate.setAllGesturesEnabled(enabled);
    }

    /**
     * Enables or disables the compass. The compass is an icon on the map that indicates the direction of north on the map.
     * If enabled, it is only shown when the camera is tilted or rotated away from its default orientation (tilt of 0 and a bearing of 0).
     * When a user clicks the compass, the camera orients itself to its default orientation and fades away shortly after.
     * If disabled, the compass will never be displayed.
     *
     * @param enabled {@code true} to enable the compass; {@code false} to disable the compass.
     */
    @Override
    public void setCompassEnabled(final boolean enabled) {
        delegate.setCompassEnabled(enabled);
    }

    /**
     * Sets whether the indoor level picker is enabled when indoor mode is enabled.
     * If true, the level picker will appear when a building with indoor maps is focused.
     * If false, no level picker will appear - an application will need to provide its own way of selecting levels.
     * The default behaviour is to show the level picker.
     *
     * @param enabled {@code true} to show or {@code false} to hide the level picker.
     */
    @Override
    public void setIndoorLevelPickerEnabled(final boolean enabled) {
        delegate.setIndoorLevelPickerEnabled(enabled);
    }

    /**
     * Sets the preference for whether the Map Toolbar should be enabled or disabled.
     * If enabled, users will see a bar with various context-dependent actions,
     * including 'open this map in the Google Maps app' and 'find directions to the highlighted marker in the Google Maps app'.
     *
     * @param enabled {@code true} to enable the Map Toolbar; {@code false} to disable the Map Toolbar.
     */
    @Override
    public void setMapToolbarEnabled(final boolean enabled) {
        delegate.setMapToolbarEnabled(enabled);
    }

    /**
     * Enables or disables the my-location button. The my-location button causes the camera to move such that the user's
     * location is in the center of the map. If the button is enabled, it is only shown when the my-location layer is enabled.
     *
     * @param enabled {@code true} to enable the my-location button; {@code false} to disable the my-location button.
     */
    @Override
    public void setMyLocationButtonEnabled(final boolean enabled) {
        delegate.setMyLocationButtonEnabled(enabled);
    }

    /**
     * Sets the preference for whether rotate gestures should be enabled or disabled.
     * If enabled, users can use a two-finger rotate gesture to rotate the camera.
     * If disabled, users cannot rotate the camera via gestures. This setting doesn't restrict the user from tapping
     * the compass icon to reset the camera orientation, nor does it restrict programmatic movements and animation of the camera.
     *
     * @param enabled {@code true} to enable rotate gestures; {@code false} to disable rotate gestures.
     */
    @Override
    public void setRotateGesturesEnabled(final boolean enabled) {
        delegate.setRotateGesturesEnabled(enabled);
    }

    /**
     * Sets the preference for whether scroll gestures should be enabled or disabled.
     * If enabled, users can swipe to pan the camera. If disabled, swiping has no effect.
     * This setting doesn't restrict programmatic movement and animation of the camera.
     *
     * @param enabled {@code true} to enable scroll gestures; {@code false} to disable scroll gestures.
     */
    @Override
    public void setScrollGesturesEnabled(final boolean enabled) {
        delegate.setScrollGesturesEnabled(enabled);
    }

    /**
     * Sets the preference for whether tilt gestures should be enabled or disabled.
     * If enabled, users can use a two-finger vertical down swipe to tilt the camera.
     * If disabled, users cannot tilt the camera via gestures. This setting doesn't restrict users from tapping
     * the compass icon to reset the camera orientation, nor does it restrict programmatic movement and animation of the camera.
     *
     * @param enabled {@code true} to enable tilt gestures; {@code false} to disable tilt gestures.
     */
    @Override
    public void setTiltGesturesEnabled(final boolean enabled) {
        delegate.setTiltGesturesEnabled(enabled);
    }

    /**
     * Enables or disables the zoom controls. If enabled, the zoom controls are a pair of buttons
     * (one for zooming in, one for zooming out) that appear on the screen. When pressed, they cause the camera
     * to zoom in (or out) by one zoom level. If disabled, the zoom controls are not shown.
     *
     * @param enabled {@code true} to enable the zoom controls; {@code false} to disable the zoom controls.
     */
    @Override
    public void setZoomControlsEnabled(final boolean enabled) {
        delegate.setZoomControlsEnabled(enabled);
    }

    /**
     * Sets the preference for whether zoom gestures should be enabled or disabled. If enabled, users can either
     * double tap/two-finger tap or pinch to zoom the camera. If disabled, these gestures have no effect.
     * This setting doesn't affect the zoom buttons, nor does it restrict programmatic movement and animation of the camera.
     *
     * @param enabled {@code true} to enable zoom gestures; {@code false} to disable zoom gestures.
     */
    @Override
    public void setZoomGesturesEnabled(final boolean enabled) {
        delegate.setZoomGesturesEnabled(enabled);
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OPFUiSettings
                && delegate.equals(((OPFUiSettings) other).delegate));
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
