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

package org.onepf.maps.amazon.delegate.model;

import android.graphics.Point;
import android.support.annotation.NonNull;

import android.support.annotation.Nullable;
import com.amazon.geo.mapsv2.Projection;
import com.amazon.geo.mapsv2.model.LatLng;

import org.onepf.opfmaps.delegate.model.ProjectionDelegate;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFVisibleRegion;

/**
 * @author Roman Savin
 * @since 06.08.2015
 */
public final class AmazonProjectionDelegate implements ProjectionDelegate {

    @NonNull
    private final Projection projection;

    public AmazonProjectionDelegate(@NonNull final Projection projection) {
        this.projection = projection;
    }

    @Nullable
    @Override
    public OPFLatLng fromScreenLocation(@NonNull final Point point) {
        final LatLng latLng = projection.fromScreenLocation(point);
        if (latLng != null) {
            return new OPFLatLng(new AmazonLatLngDelegate(latLng));
        }
        return null;
    }

    @NonNull
    @Override
    public Point toScreenLocation(@NonNull final OPFLatLng location) {
        return projection.toScreenLocation(new LatLng(location.getLat(), location.getLng()));
    }

    @NonNull
    @Override
    public OPFVisibleRegion getVisibleRegion() {
        return new OPFVisibleRegion(new AmazonVisibleRegionDelegate(projection.getVisibleRegion()));
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof AmazonProjectionDelegate
                && projection.equals(((AmazonProjectionDelegate) other).projection));
    }

    @Override
    public int hashCode() {
        return projection.hashCode();
    }

    @Override
    public String toString() {
        return projection.toString();
    }
}
