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

import org.onepf.opfmaps.delegate.model.PolylineDelegate;

import java.util.List;

/**
 * A polyline is a list of points, where line segments are drawn between consecutive points.
 *
 * @author Anastasiia Karimova
 * @since 09.06.2015
 */
public final class OPFPolyline implements PolylineDelegate {

    @NonNull
    private final PolylineDelegate delegate;

    public OPFPolyline(@NonNull final PolylineDelegate delegate) {
        this.delegate = delegate;
    }

    /**
     * Gets the color of this polyline.
     *
     * @return The color in ARGB format.
     */
    @Override
    public int getColor() {
        return delegate.getColor();
    }

    /**
     * Gets this polyline's id. The id will be unique amongst all polylines on a map.
     *
     * @return the polyline's id.
     */
    @NonNull
    @Override
    public String getId() {
        return delegate.getId();
    }

    /**
     * Returns a snapshot of the vertices of this polyline at this time.
     * The list returned is a copy of the list of vertices and so changes to the polyline's vertices will not be reflected by this list,
     * nor will changes to this list be reflected by the polyline. To change the vertices of the polyline, call {@link #setPoints(List)}.
     *
     * @return The list of the polyline's points.
     */
    @Nullable
    @Override
    public List<OPFLatLng> getPoints() {
        return delegate.getPoints();
    }

    /**
     * Gets the width of this polyline.
     *
     * @return The width in screen pixels.
     */
    @Override
    public float getWidth() {
        return delegate.getWidth();
    }

    /**
     * Gets the zIndex of this polyline.
     *
     * @return The zIndex of the polyline.
     */
    @Override
    public float getZIndex() {
        return delegate.getZIndex();
    }

    /**
     * Gets whether each segment of the line is drawn as a geodesic or not.
     *
     * @return {@code true} if each segment is drawn as a geodesic; {@code false} if each segment is drawn as a straight line on the Mercator projection.
     */
    @Override
    public boolean isGeodesic() {
        return delegate.isGeodesic();
    }

    /**
     * Gets the visibility of this polyline.
     *
     * @return {@code true} if the polyline is visible, {@code false} otherwise.
     */
    @Override
    public boolean isVisible() {
        return delegate.isVisible();
    }

    /**
     * Removes this polyline from the map. After a polyline has been removed, the behavior of all its methods is undefined.
     */
    @Override
    public void remove() {
        delegate.remove();
    }

    /**
     * Sets the color of this polyline.
     *
     * @param color The color in ARGB format.
     */
    @Override
    public void setColor(final int color) {
        delegate.setColor(color);
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
     * Sets the points of this polyline.
     * This method will take a copy of the points, so further mutations to {@code points} will have no effect on this polyline.
     *
     * @param points The list of the {@link OPFLatLng} objects that are the vertices of the polyline.
     */
    @Override
    public void setPoints(@NonNull final List<OPFLatLng> points) {
        delegate.setPoints(points);
    }

    /**
     * Sets the visibility of this polyline. When not visible, a polyline is not drawn, but it keeps all its other properties.
     *
     * @param visible {@code false} to make this polyline invisible, {@code true} otherwise.
     */
    @Override
    public void setVisible(final boolean visible) {
        delegate.setVisible(visible);
    }

    /**
     * Sets the width of this polyline.
     *
     * @param width The width in screen pixels.
     */
    @Override
    public void setWidth(final float width) {
        delegate.setWidth(width);
    }

    /**
     * Sets the zIndex of this polyline. Polylines with higher zIndices are drawn above those with lower indices.
     *
     * @param zIndex The zIndex of this polyline.
     */
    @Override
    public void setZIndex(final float zIndex) {
        delegate.setZIndex(zIndex);
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OPFPolyline
                && delegate.equals(((OPFPolyline) other).delegate));
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
