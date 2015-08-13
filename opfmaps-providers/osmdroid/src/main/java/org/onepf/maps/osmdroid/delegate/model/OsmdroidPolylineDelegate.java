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

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import org.onepf.opfmaps.delegate.model.PolylineDelegate;
import org.onepf.opfmaps.model.OPFLatLng;
import org.osmdroid.bonuspack.overlays.Polyline;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
public final class OsmdroidPolylineDelegate implements PolylineDelegate {

    @Nullable
    private MapView mapView;
    
    @NonNull
    private final Polyline polyline;
    
    @NonNull
    private final String id;

    public OsmdroidPolylineDelegate(@SuppressWarnings("NullableProblems") @NonNull final MapView mapView, 
                                    @NonNull final Polyline polyline) {
        this.mapView = mapView;
        this.polyline = polyline;
        this.id = Integer.toString(this.polyline.hashCode());
    }

    @Override
    public int getColor() {
        return polyline.getColor();
    }

    @NonNull
    @Override
    public String getId() {
        return id;
    }

    @Nullable
    @Override
    public List<OPFLatLng> getPoints() {
        final List<GeoPoint> osmdroidPoints = polyline.getPoints();
        if (osmdroidPoints == null) {
            return null;
        }

        final List<OPFLatLng> points = new ArrayList<>(osmdroidPoints.size());
        for (GeoPoint osmdroidPoint : osmdroidPoints) {
            points.add(new OPFLatLng(new OsmdroidLatLngDelegate(osmdroidPoint)));
        }

        return points;
    }

    @Override
    public float getWidth() {
        return polyline.getWidth();
    }

    @Override
    public float getZIndex() {
        return 0;
    }

    @Override
    public boolean isGeodesic() {
        return polyline.isGeodesic();
    }

    @Override
    public boolean isVisible() {
        return polyline.isVisible();
    }

    @Override
    public void remove() {
        if (mapView != null) {
            mapView.getOverlays().remove(polyline);
            mapView.invalidate();
            mapView = null;
        }
    }

    @Override
    public void setColor(final int color) {
        polyline.setColor(color);
    }

    @Override
    public void setGeodesic(final boolean geodesic) {
        polyline.setGeodesic(geodesic);
    }

    @Override
    public void setPoints(@NonNull final List<OPFLatLng> points) {
        final List<GeoPoint> osmdroidPoints = new ArrayList<>(points.size());
        for (OPFLatLng point : points) {
            osmdroidPoints.add(new GeoPoint(point.getLat(), point.getLng()));
        }

        polyline.setPoints(osmdroidPoints);
    }

    @Override
    public void setVisible(final boolean visible) {
        polyline.setVisible(visible);
    }

    @Override
    public void setWidth(final float width) {
        polyline.setWidth(width);
    }

    @Override
    public void setZIndex(final float zIndex) {
        //todo OPFLog.logMethodStub(zIndex)
    }

    @Override
    public String toString() {
        return polyline.toString();
    }

    @Override
    public int hashCode() {
        return polyline.hashCode();
    }

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OsmdroidPolylineDelegate)) return false;

        return polyline.equals(((OsmdroidPolylineDelegate) other).polyline);
    }
    //CHECKSTYLE:ON
}
