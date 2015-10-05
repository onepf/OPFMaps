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

import org.onepf.opfmaps.delegate.model.GroundOverlayDelegate;

/**
 * A ground overlay is an image that is fixed to a map.
 *
 * @author Roman Savin
 * @since 15.06.2015
 */
public final class OPFGroundOverlay implements GroundOverlayDelegate {

    @NonNull
    private final GroundOverlayDelegate delegate;

    public OPFGroundOverlay(@NonNull final GroundOverlayDelegate delegate) {
        this.delegate = delegate;
    }

    /**
     * Gets the bearing of the ground overlay in degrees clockwise from north.
     *
     * @return The bearing of the ground overlay.
     */
    @Override
    public float getBearing() {
        return delegate.getBearing();
    }

    /**
     * Gets the bounds for the ground overlay. This ignores the rotation of the ground overlay.
     *
     * @return An {@link OPFLatLngBounds} that contains the ground overlay, ignoring rotation.
     */
    @NonNull
    @Override
    public OPFLatLngBounds getBounds() {
        return delegate.getBounds();
    }

    /**
     * Gets the height of the ground overlay.
     *
     * @return The height of the ground overlay in meters.
     */
    @Override
    public float getHeight() {
        return delegate.getHeight();
    }

    /**
     * Gets this ground overlay's id. The id will be unique amongst all {@link OPFGroundOverlay} on a map.
     *
     * @return The ground overlay's id.
     */
    @NonNull
    @Override
    public String getId() {
        return delegate.getId();
    }

    /**
     * Gets the location of the anchor point.
     *
     * @return The position on the map (an {@link OPFLatLng}).
     */
    @NonNull
    @Override
    public OPFLatLng getPosition() {
        return delegate.getPosition();
    }

    /**
     * Gets the transparency of this ground overlay.
     *
     * @return The transparency of this ground overlay.
     */
    @Override
    public float getTransparency() {
        return delegate.getTransparency();
    }

    /**
     * Gets the width of the ground overlay.
     *
     * @return The width of the ground overlay in meters.
     */
    @Override
    public float getWidth() {
        return delegate.getWidth();
    }

    /**
     * Gets the zIndex of this ground overlay.
     *
     * @return The zIndex of the ground overlay.
     */
    @Override
    public float getZIndex() {
        return delegate.getZIndex();
    }

    /**
     * Gets the visibility of this ground overlay. Note that this does not return whether the ground overlay is actually
     * on screen, but whether it will be drawn if it is contained in the camera's viewport.
     *
     * @return This ground overlay's visibility.
     */
    @Override
    public boolean isVisible() {
        return delegate.isVisible();
    }

    /**
     * Removes this ground overlay from the map.
     * After a ground overlay has been removed, the behavior of all its methods is undefined.
     */
    @Override
    public void remove() {
        delegate.remove();
    }

    /**
     * Sets the bearing of the ground overlay (the direction that the vertical axis of the ground overlay points)
     * in degrees clockwise from north. The rotation is performed about the anchor point.
     *
     * @param bearing The bearing in degrees clockwise from north.
     */
    @Override
    public void setBearing(final float bearing) {
        delegate.setBearing(bearing);
    }

    /**
     * Sets the width of the ground overlay. The height of the ground overlay will be adapted accordingly to preserve aspect ratio.
     *
     * @param width The width in meters.
     */
    @Override
    public void setDimensions(final float width) {
        delegate.setDimensions(width);
    }

    /**
     * Sets the dimensions of the ground overlay. The image will be stretched to fit the dimensions.
     *
     * @param width  The width in meters
     * @param height The height in meters.
     */
    @Override
    public void setDimensions(final float width, final float height) {
        delegate.setDimensions(width, height);
    }

    /**
     * Sets the image for the Ground Overlay. The new image will occupy the same bounds as the old image.
     *
     * @param image The {@link OPFBitmapDescriptor} to use for this ground overlay.
     */
    @Override
    public void setImage(@NonNull final OPFBitmapDescriptor image) {
        delegate.setImage(image);
    }

    /**
     * Sets the position of the ground overlay by changing the location of the anchor point.
     * Preserves all other properties of the image.
     *
     * @param position An {@link OPFLatLng} that is the new location to place the anchor point.
     */
    @Override
    public void setPosition(@NonNull final OPFLatLng position) {
        delegate.setPosition(position);
    }

    /**
     * Sets the position of the ground overlay by fitting it to the given {@link OPFLatLngBounds}.
     * This method will ignore the rotation (bearing) of the ground overlay when positioning it,
     * but the bearing will still be used when drawing it.
     *
     * @param bounds An {@link OPFLatLngBounds} in which to place the ground overlay.
     */
    @Override
    public void setPositionFromBounds(@NonNull final OPFLatLngBounds bounds) {
        delegate.setPositionFromBounds(bounds);
    }

    /**
     * Sets the transparency of this ground overlay.
     *
     * @param transparency A float in the range [0..1] where 0 means that the ground overlay is opaque and 1 means that
     *                     the ground overlay is transparent.
     */
    @Override
    public void setTransparency(final float transparency) {
        delegate.setTransparency(transparency);
    }

    /**
     * Sets the visibility of this ground overlay. When not visible, a ground overlay is not drawn,
     * but it keeps all of its other properties.
     *
     * @param visible If {@code true}, then the ground overlay is visible; if {@code false}, it is not.
     */
    @Override
    public void setVisible(final boolean visible) {
        delegate.setVisible(visible);
    }

    /**
     * Sets the zIndex of this ground overlay.
     *
     * @param zIndex The zIndex of this ground overlay.
     */
    @Override
    public void setZIndex(final float zIndex) {
        delegate.setZIndex(zIndex);
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OPFGroundOverlay
                && delegate.equals(((OPFGroundOverlay) other).delegate));
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
