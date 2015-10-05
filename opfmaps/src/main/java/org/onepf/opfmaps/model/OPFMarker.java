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

import android.support.annotation.Nullable;
import org.onepf.opfmaps.delegate.model.MarkerDelegate;

/**
 * An icon placed at a particular point on the map's surface. A marker icon is drawn oriented against the device's
 * screen rather than the map's surface; i.e., it will not necessarily change orientation due to map rotations, tilting,
 * or zooming.
 *
 * @author Roman Savin
 * @since 09.06.2015
 */
public final class OPFMarker implements MarkerDelegate {

    @NonNull
    private final MarkerDelegate delegate;

    public OPFMarker(@NonNull final MarkerDelegate delegate) {
        this.delegate = delegate;
    }

    /**
     * Gets the alpha of the marker.
     *
     * @return The alpha of the marker in the range [0, 1].
     */
    @Override
    public float getAlpha() {
        return delegate.getAlpha();
    }

    /**
     * Gets this marker's id. The id will be unique amongst all markers on a map.
     *
     * @return The marker's id.
     */
    @NonNull
    @Override
    public String getId() {
        return delegate.getId();
    }

    /**
     * Returns the position of the marker.
     *
     * @return The {@link OPFLatLng} object specifying the marker's current position.
     */
    @NonNull
    @Override
    public OPFLatLng getPosition() {
        return delegate.getPosition();
    }

    /**
     * Gets the rotation of the marker.
     *
     * @return The rotation of the marker in degrees clockwise from the default position.
     */
    @Override
    public float getRotation() {
        return delegate.getRotation();
    }

    /**
     * Gets the snippet of the marker.
     *
     * @return A string containing the marker's snippet.
     */
    @Nullable
    @Override
    public String getSnippet() {
        return delegate.getSnippet();
    }

    /**
     * Gets the title of the marker.
     *
     * @return A string containing the marker's title.
     */
    @Nullable
    @Override
    public String getTitle() {
        return delegate.getTitle();
    }

    /**
     * Hides the info window if it is shown from this marker.
     */
    @Override
    public void hideInfoWindow() {
        delegate.hideInfoWindow();
    }

    /**
     * Gets the draggability of the marker. When a marker is draggable,
     * it can be moved by the user by long pressing on the marker.
     *
     * @return {@code true} if the marker is draggable, {@code false} otherwise.
     */
    @Override
    public boolean isDraggable() {
        return delegate.isDraggable();
    }

    /**
     * Gets the flat setting of the marker.
     *
     * @return {@code true} if the marker is flat against the map; {@code false} if the marker should face the camera.
     */
    @Override
    public boolean isFlat() {
        return delegate.isFlat();
    }

    /**
     * Returns whether the info window is currently shown above this marker.
     * This does not consider whether or not the info window is actually visible on screen.
     *
     * @return {@code true} if the info window is shown, {@code false} otherwise.
     */
    @Override
    public boolean isInfoWindowShown() {
        return delegate.isInfoWindowShown();
    }

    /**
     * Checks whether the marker is visible.
     *
     * @return {@code true} if the marker is visible, {@code false} otherwise.
     */
    @Override
    public boolean isVisible() {
        return delegate.isVisible();
    }

    /**
     * Removes this marker from the map. After a marker has been removed, the behavior of all its methods is undefined.
     */
    @Override
    public void remove() {
        delegate.remove();
    }

    /**
     * Sets the alpha (opacity) of the marker. This is a value from 0 to 1, where 0 means the marker is completely transparent
     * and 1 means the marker is completely opaque.
     *
     * @param alpha The alpha value.
     */
    @Override
    public void setAlpha(final float alpha) {
        delegate.setAlpha(alpha);
    }

    /**
     * Sets the anchor point for the marker.
     * The anchor specifies the point in the icon image that is anchored to the marker's position on the Earth's surface.
     *
     * @param anchorU The u-coordinate of the anchor, as a ratio of the image width (in the range [0, 1]).
     * @param anchorV The v-coordinate of the anchor, as a ratio of the image height (in the range [0, 1]).
     */
    @Override
    public void setAnchor(final float anchorU, final float anchorV) {
        delegate.setAnchor(anchorU, anchorV);
    }

    /**
     * Sets the draggability of the marker. When a marker is draggable, it can be moved by the user by long pressing on the marker.
     *
     * @param draggable {@code true} if the marker should be draggable, {@code false} otherwise.
     */
    @Override
    public void setDraggable(final boolean draggable) {
        delegate.setDraggable(draggable);
    }

    /**
     * Sets whether this marker should be flat against the map or a billboard facing the camera.
     *
     * @param flat {@code true} if the should be flat, {@code false} otherwise.
     */
    @Override
    public void setFlat(final boolean flat) {
        delegate.setFlat(flat);
    }

    /**
     * Sets the icon for the marker.
     *
     * @param icon The icon of the marker. If {@code null}, the default marker is used.
     */
    @Override
    public void setIcon(final OPFBitmapDescriptor icon) {
        delegate.setIcon(icon);
    }

    /**
     * Specifies the point in the marker image at which to anchor the info window when it is displayed.
     * This is specified in the same coordinate system as the anchor.
     * The default is the top middle of the image.
     *
     * @param anchorU The u-coordinate of the info window anchor, as a ratio of the image width (in the range [0, 1]).
     * @param anchorV The v-coordinate of the info window anchor, as a ratio of the image height (in the range [0, 1]).
     */
    @Override
    public void setInfoWindowAnchor(final float anchorU, final float anchorV) {
        delegate.setInfoWindowAnchor(anchorU, anchorV);
    }

    /**
     * Sets the position of the marker.
     *
     * @param latLng The geographic position of the marker, specified as a {@link OPFLatLng}.
     */
    @Override
    public void setPosition(@NonNull final OPFLatLng latLng) {
        delegate.setPosition(latLng);
    }

    /**
     * Sets the rotation of the marker in degrees clockwise about the marker's anchor point.
     * The axis of rotation is perpendicular to the marker. A rotation of 0 corresponds to the default position of the marker.
     *
     * @param rotation New rotation of the marker.
     */
    @Override
    public void setRotation(final float rotation) {
        delegate.setRotation(rotation);
    }

    /**
     * Sets the snippet of the marker.
     *
     * @param snippet New snippet of the marker.
     */
    @Override
    public void setSnippet(@NonNull final String snippet) {
        delegate.setSnippet(snippet);
    }

    /**
     * Sets the title of the marker.
     *
     * @param title New title of the marker.
     */
    @Override
    public void setTitle(@NonNull final String title) {
        delegate.setTitle(title);
    }

    /**
     * Sets the visibility of this marker. If set to {@code false} and an info window is currently showing for this marker,
     * this will hide the info window.
     *
     * @param visible {@code false} to make this marker invisible, {@code true} otherwise.
     */
    @Override
    public void setVisible(final boolean visible) {
        delegate.setVisible(visible);
    }

    /**
     * Shows the info window of this marker on the map, if this marker is visible.
     */
    @Override
    public void showInfoWindow() {
        delegate.showInfoWindow();
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OPFMarker
                && delegate.equals(((OPFMarker) other).delegate));
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
