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

import org.onepf.opfmaps.delegate.model.PolygonDelegate;

import java.util.List;

/**
 * A polygon on the earth's surface. A polygon can be convex or concave,
 * it may span the 180 meridian and it can have holes that are not filled in.
 *
 * @author Roman Savin
 * @since 15.06.2015
 */
public final class OPFPolygon implements PolygonDelegate {

    @NonNull
    private final PolygonDelegate delegate;

    public OPFPolygon(@NonNull final PolygonDelegate delegate) {
        this.delegate = delegate;
    }

    /**
     * Gets the fill color of this polygon.
     *
     * @return The color in ARGB format.
     */
    @Override
    public int getFillColor() {
        return delegate.getFillColor();
    }

    /**
     * Returns a snapshot of the holes of this polygon at this time .
     * The list returned is a copy of the list of holes and so changes to the polygon's holes will not be reflected by this list,
     * nor will changes to this list be reflected by the polygon.
     *
     * @return The list of the holes.
     */
    @Nullable
    @Override
    public List<List<OPFLatLng>> getHoles() {
        return delegate.getHoles();
    }

    /**
     * Gets this polygon's id. The id will be unique amongst all polygons on a map.
     *
     * @return The polygon's id.
     */
    @NonNull
    @Override
    public String getId() {
        return delegate.getId();
    }

    /**
     * Returns a snapshot of the vertices of this polygon at this time.
     * The list returned is a copy of the list of vertices and so changes to the polygon's vertices will not be reflected
     * by this list, nor will changes to this list be reflected by the polygon.
     * To change the vertices of the polygon, call {@link #setPoints(List)).
     *
     * @return The list of the points.
     */
    @NonNull
    @Override
    public List<OPFLatLng> getPoints() {
        return delegate.getPoints();
    }

    /**
     * Gets the stroke color of this polygon.
     *
     * @return The color in ARGB format.
     */
    @Override
    public int getStrokeColor() {
        return delegate.getStrokeColor();
    }

    /**
     * Gets the stroke width of this polygon.
     *
     * @return The width in screen pixels.
     */
    @Override
    public float getStrokeWidth() {
        return delegate.getStrokeWidth();
    }

    /**
     * Gets the zIndex of this polygon.
     *
     * @return The zIndex of the polygon.
     */
    @Override
    public float getZIndex() {
        return delegate.getZIndex();
    }

    /**
     * Gets whether each segment of the line is drawn as a geodesic or not.
     *
     * @return {@code true} if each segment is drawn as a geodesic;
     * {@code false} if each segment is drawn as a straight line on the Mercator projection.
     */
    @Override
    public boolean isGeodesic() {
        return delegate.isGeodesic();
    }

    /**
     * Gets the visibility of this polygon.
     *
     * @return {@code true} if the polygon is visible, {@code false} otherwise.
     */
    @Override
    public boolean isVisible() {
        return delegate.isVisible();
    }

    /**
     * Removes the polygon from the map.
     */
    @Override
    public void remove() {
        delegate.remove();
    }

    /**
     * Sets the fill color of this polygon.
     *
     * @param color The color in ARGB format.
     */
    @Override
    public void setFillColor(final int color) {
        delegate.setFillColor(color);
    }

    /**
     * Sets whether to draw each segment of the line as a geodesic or not.
     *
     * @param geodesic If {@code true}, then each segment is drawn as a geodesic;
     *                 if {@code false}, each segment is drawn as a straight line on the Mercator projection.
     */
    @Override
    public void setGeodesic(final boolean geodesic) {
        delegate.setGeodesic(geodesic);
    }

    /**
     * Sets the holes of this polygon. This method will take a copy of the holes, so further mutations to {@code holes}
     * will have no effect on this polygon.
     *
     * @param holes The list of holes, where a hole is a list of {@link OPFLatLng} objects.
     */
    @Override
    public void setHoles(@NonNull final List<? extends List<OPFLatLng>> holes) {
        delegate.setHoles(holes);
    }

    /**
     * Sets the points of this polygon. This method will take a copy of the points, so further mutations to {@code points}
     * will have no effect on this polygon.
     *
     * @param points The list of {@link OPFLatLng} objects that are the vertices of the polygon.
     */
    @Override
    public void setPoints(@NonNull final List<OPFLatLng> points) {
        delegate.setPoints(points);
    }

    /**
     * Sets the stroke color of this polygon.
     *
     * @param color The color in ARGB format.
     */
    @Override
    public void setStrokeColor(final int color) {
        delegate.setStrokeColor(color);
    }

    /**
     * Sets the stroke width of this polygon.
     *
     * @param width The width in display pixels.
     */
    @Override
    public void setStrokeWidth(final float width) {
        delegate.setStrokeWidth(width);
    }

    /**
     * Sets the visibility of this polygon. When not visible, a polygon is not drawn, but it keeps all its other properties.
     *
     * @param visible {@code false} to make this polygon invisible, {@code true} otherwise.
     */
    @Override
    public void setVisible(final boolean visible) {
        delegate.setVisible(visible);
    }

    /**
     * Sets the zIndex of this polygon. Polygons with higher zIndices are drawn above those with lower indices.
     *
     * @param zIndex The zIndex of this polygon.
     */
    @Override
    public void setZIndex(final float zIndex) {
        delegate.setZIndex(zIndex);
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OPFPolygon
                && delegate.equals(((OPFPolygon) other).delegate));
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
