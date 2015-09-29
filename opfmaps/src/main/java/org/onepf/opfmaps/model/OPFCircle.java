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

import org.onepf.opfmaps.delegate.model.CircleDelegate;

/**
 * A circle on the earth's surface (spherical cap).
 *
 * @author Roman Savin
 * @since 29.07.2015
 */
public final class OPFCircle implements CircleDelegate {

    @NonNull
    private final CircleDelegate delegate;

    public OPFCircle(@NonNull final CircleDelegate delegate) {
        this.delegate = delegate;
    }

    /**
     * Returns the center as a {@link OPFLatLng}.
     *
     * @return The geographic center as a {@link OPFLatLng}.
     */
    @NonNull
    @Override
    public OPFLatLng getCenter() {
        return delegate.getCenter();
    }

    /**
     * Returns the fill color of this circle.
     *
     * @return The fill color of the circle in ARGB format.
     */
    @Override
    public int getFillColor() {
        return delegate.getFillColor();
    }

    /**
     * Gets this circle's id. The id will be unique amongst all circles on a map.
     *
     * @return The circle's id.
     */
    @NonNull
    @Override
    public String getId() {
        return delegate.getId();
    }

    /**
     * Returns the circle's radius, in meters.
     *
     * @return The radius in meters.
     */
    @Override
    public double getRadius() {
        return delegate.getRadius();
    }

    /**
     * Returns the stroke color.
     *
     * @return The color of the circle in ARGB format.
     */
    @Override
    public int getStrokeColor() {
        return delegate.getStrokeColor();
    }

    /**
     * Returns the stroke width.
     *
     * @return The width in screen pixels.
     */
    @Override
    public float getStrokeWidth() {
        return delegate.getStrokeWidth();
    }

    /**
     * Returns the zIndex.
     *
     * @return The zIndex of this circle.
     */
    @Override
    public float getZIndex() {
        return delegate.getZIndex();
    }

    /**
     * Checks whether the circle is visible.
     *
     * @return {@code true} if the circle is visible, {@code false} otherwise.
     */
    @Override
    public boolean isVisible() {
        return delegate.isVisible();
    }

    /**
     * Removes this circle from the map.
     */
    @Override
    public void remove() {
        delegate.remove();
    }

    /**
     * Sets the center using a {@link OPFLatLng}.
     *
     * @param center The geographic center of the circle, specified as a {@link OPFLatLng}.
     */
    @Override
    public void setCenter(@NonNull final OPFLatLng center) {
        delegate.setCenter(center);
    }

    /**
     * Sets the fill color.
     * The fill color is the color inside the circle, in the integer format specified by {@link android.graphics.Color}.
     *
     * @param color The color in the {@link android.graphics.Color} format.
     */
    @Override
    public void setFillColor(final int color) {
        delegate.setFillColor(color);
    }

    /**
     * Sets the radius in meters. The radius must be zero or greater.
     *
     * @param radius The radius, in meters.
     */
    @Override
    public void setRadius(final double radius) {
        delegate.setRadius(radius);
    }

    /**
     * Sets the stroke color.
     * The stroke color is the color of this circle's outline, in the integer format specified by {@link android.graphics.Color}.
     *
     * @param color The stroke color in the {@link android.graphics.Color} format.
     */
    @Override
    public void setStrokeColor(final int color) {
        delegate.setStrokeColor(color);
    }

    /**
     * Sets the stroke width. The stroke width is the width (in screen pixels) of the circle's outline.
     * It must be zero or greater. If it is zero then no outline is drawn. The default value is 10.
     *
     * @param width The stroke width, in screen pixels.
     */
    @Override
    public void setStrokeWidth(final float width) {
        delegate.setStrokeWidth(width);
    }

    /**
     * Sets the visibility of the circle. If this circle is not visible then it will not be drawn.
     * All other state is preserved. Defaults to {@code true}.
     *
     * @param visible {@code false} to make this circle invisible, {@code true} otherwise.
     */
    @Override
    public void setVisible(final boolean visible) {
        delegate.setVisible(visible);
    }

    /**
     * Sets the zIndex. Overlays (such as circles) with higher zIndices are drawn above those with lower indices.
     *
     * @param zIndex The zIndex value.
     */
    @Override
    public void setZIndex(final float zIndex) {
        delegate.setZIndex(zIndex);
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OPFCircle
                && delegate.equals(((OPFCircle) other).delegate));
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
