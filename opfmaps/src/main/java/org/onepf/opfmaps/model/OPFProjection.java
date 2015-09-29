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

import android.graphics.Point;
import android.support.annotation.NonNull;

import android.support.annotation.Nullable;
import org.onepf.opfmaps.delegate.model.ProjectionDelegate;

/**
 * A projection is used to translate between on screen location and geographic coordinates on the surface of the Earth
 * ({@link OPFLatLng}). Screen location is in screen pixels (not display pixels) with respect to the top left corner of the map
 * (and not necessarily of the whole screen).
 *
 * @author Roman Savin
 * @since 06.08.2015
 */
public class OPFProjection implements ProjectionDelegate {

    @NonNull
    private final ProjectionDelegate delegate;

    public OPFProjection(@NonNull final ProjectionDelegate delegate) {
        this.delegate = delegate;
    }

    /**
     * Returns the geographic location that corresponds to a screen location. The screen location is specified
     * in screen pixels (not display pixels) relative to the top left of the map (not the top left of the whole screen).
     *
     * @param point A {@link Point} on the screen in screen pixels.
     * @return The {@link OPFLatLng} corresponding to the point on the screen, or null if the ray through the given screen
     * point does not intersect the ground plane (this might be the case if the map is heavily tilted).
     */
    @Override
    @Nullable
    public OPFLatLng fromScreenLocation(@NonNull final Point point) {
        return delegate.fromScreenLocation(point);
    }

    /**
     * Gets a projection of the viewing frustum for converting between screen coordinates and geo-latitude/longitude coordinates.
     *
     * @return The projection of the viewing frustum in its current state.
     */
    @Override
    @NonNull
    public OPFVisibleRegion getVisibleRegion() {
        return delegate.getVisibleRegion();
    }

    /**
     * Returns a screen location that corresponds to a geographical coordinate ({@link OPFLatLng}).
     * The screen location is in screen pixels (not display pixels) relative to the top left of the map (not of the whole screen).
     *
     * @param location An {@link OPFLatLng} on the map to convert to a screen location.
     * @return A {@link Point} representing the screen location in screen pixels.
     */
    @Override
    @NonNull
    public Point toScreenLocation(@NonNull final OPFLatLng location) {
        return delegate.toScreenLocation(location);
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OPFProjection
                && delegate.equals(((OPFProjection) other).delegate));
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
