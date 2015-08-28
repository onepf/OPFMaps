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

package org.onepf.maps.osmdroid.delegate.model;

import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.NonNull;

import org.onepf.maps.osmdroid.model.VisibleRegion;
import org.onepf.opfmaps.delegate.model.ProjectionDelegate;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFVisibleRegion;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.Projection;

/**
 * @author Roman Savin
 * @since 06.08.2015
 */
public final class OsmdroidProjectionDelegate implements ProjectionDelegate {

    @NonNull
    private final Projection projection;

    public OsmdroidProjectionDelegate(@NonNull final Projection projection) {
        this.projection = projection;
    }

    @NonNull
    @Override
    public OPFLatLng fromScreenLocation(@NonNull final Point point) {
        final IGeoPoint iGeoPoint = projection.fromPixels(point.x, point.y);
        return new OPFLatLng(new OsmdroidLatLngDelegate(new GeoPoint(iGeoPoint.getLatitude(), iGeoPoint.getLongitude())));
    }

    @NonNull
    @Override
    public Point toScreenLocation(@NonNull final OPFLatLng location) {
        return projection.toPixels(new GeoPoint(location.getLat(), location.getLng()), null);
    }

    @NonNull
    @Override
    public OPFVisibleRegion getVisibleRegion() {
        final Rect rect = projection.getScreenRect();
        final IGeoPoint nearLeft = projection.fromPixels(rect.left, rect.bottom);
        final IGeoPoint nearRight = projection.fromPixels(rect.right, rect.bottom);
        final IGeoPoint farLeft = projection.fromPixels(rect.left, rect.top);
        final IGeoPoint farRight = projection.fromPixels(rect.right, rect.top);

        return new OPFVisibleRegion(new OsmdroidVisibleRegionDelegate(new VisibleRegion(
                new GeoPoint(nearLeft.getLatitude(), nearLeft.getLongitude()),
                new GeoPoint(nearRight.getLatitude(), nearRight.getLongitude()),
                new GeoPoint(farLeft.getLatitude(), farLeft.getLongitude()),
                new GeoPoint(farRight.getLatitude(), farRight.getLongitude()),
                projection.getBoundingBox()
        )));
    }

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OsmdroidProjectionDelegate)) return false;

        return projection.equals(((OsmdroidProjectionDelegate) other).projection);
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
