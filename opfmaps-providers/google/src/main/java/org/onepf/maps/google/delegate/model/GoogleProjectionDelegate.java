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

package org.onepf.maps.google.delegate.model;

import android.graphics.Point;
import android.support.annotation.NonNull;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.LatLng;
import org.onepf.opfmaps.delegate.model.ProjectionDelegate;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFVisibleRegion;

/**
 * @author Roman Savin
 * @since 06.08.2015
 */
public final class GoogleProjectionDelegate implements ProjectionDelegate {

    @NonNull
    private final Projection projection;

    public GoogleProjectionDelegate(@NonNull final Projection projection) {
        this.projection = projection;
    }

    @NonNull
    @Override
    public OPFLatLng fromScreenLocation(@NonNull final Point point) {
        return new OPFLatLng(new GoogleLatLngDelegate(projection.fromScreenLocation(point)));
    }

    @NonNull
    @Override
    public Point toScreenLocation(@NonNull final OPFLatLng location) {
        return projection.toScreenLocation(new LatLng(location.getLat(), location.getLng()));
    }

    @NonNull
    @Override
    public OPFVisibleRegion getVisibleRegion() {
        return new OPFVisibleRegion(new GoogleVisibleRegionDelegate(projection.getVisibleRegion()));
    }

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof GoogleProjectionDelegate)) return false;

        return projection.equals(((GoogleProjectionDelegate) other).projection);
    }
    //CHECKSTYLE:ON

    @Override
    public int hashCode() {
        return projection.hashCode();
    }

    @Override
    public String toString() {
        return projection.toString();
    }
}
