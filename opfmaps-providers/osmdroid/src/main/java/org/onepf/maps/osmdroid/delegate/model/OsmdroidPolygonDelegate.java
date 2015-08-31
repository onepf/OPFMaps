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

import org.onepf.opfmaps.delegate.model.PolygonDelegate;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfutils.OPFLog;
import org.osmdroid.bonuspack.overlays.Polygon;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
public final class OsmdroidPolygonDelegate implements PolygonDelegate {

    @Nullable
    private MapView mapView;

    @NonNull
    private final Polygon polygon;

    @NonNull
    private final String id;

    public OsmdroidPolygonDelegate(@SuppressWarnings("NullableProblems") @NonNull final MapView mapView,
                                   @NonNull final Polygon polygon) {
        this.mapView = mapView;
        this.polygon = polygon;
        this.id = Integer.toString(this.polygon.hashCode());
    }

    @Override
    public int getFillColor() {
        return polygon.getFillColor();
    }

    @Nullable
    @Override
    public List<List<OPFLatLng>> getHoles() {
        final List<ArrayList<GeoPoint>> holes = polygon.getHoles();
        if (holes == null) {
            return null;
        }

        final List<List<OPFLatLng>> opfHoles = new ArrayList<>(holes.size());
        for (List<GeoPoint> hole : holes) {
            final List<OPFLatLng> opfHole = new ArrayList<>(hole.size());
            for (GeoPoint point : hole) {
                opfHole.add(new OPFLatLng(new OsmdroidLatLngDelegate(point)));
            }
            opfHoles.add(opfHole);
        }
        return opfHoles;
    }

    @NonNull
    @Override
    public String getId() {
        return id;
    }

    @NonNull
    @Override
    public List<OPFLatLng> getPoints() {
        final List<GeoPoint> points = polygon.getPoints();
        final List<OPFLatLng> opfPoints = new ArrayList<>(points.size());
        for (GeoPoint point : points) {
            opfPoints.add(new OPFLatLng(new OsmdroidLatLngDelegate(point)));
        }

        return opfPoints;
    }

    @Override
    public int getStrokeColor() {
        return polygon.getStrokeColor();
    }

    @Override
    public float getStrokeWidth() {
        return polygon.getStrokeWidth();
    }

    @Override
    public float getZIndex() {
        return 0;
    }

    @Override
    public boolean isGeodesic() {
        return false;
    }

    @Override
    public boolean isVisible() {
        return polygon.isVisible();
    }

    @Override
    public void remove() {
        if (mapView != null) {
            mapView.getOverlays().remove(polygon);
            mapView.invalidate();
            mapView = null;
        }
    }

    @Override
    public void setFillColor(final int color) {
        polygon.setFillColor(color);
    }

    @Override
    public void setGeodesic(final boolean geodesic) {
        OPFLog.logStubCall(geodesic);
    }

    @Override
    public void setHoles(@NonNull final List<? extends List<OPFLatLng>> holes) {
        final List<List<GeoPoint>> osmdroidHoles = new ArrayList<>(holes.size());
        for (List<OPFLatLng> hole : holes) {
            final List<GeoPoint> osmdroidHole = new ArrayList<>(hole.size());
            for (OPFLatLng point : hole) {
                osmdroidHole.add(new GeoPoint(point.getLat(), point.getLng()));
            }
            osmdroidHoles.add(osmdroidHole);
        }

        polygon.setHoles(osmdroidHoles);
    }

    @Override
    public void setPoints(@NonNull final List<OPFLatLng> points) {
        final List<GeoPoint> osmdroidPoints = new ArrayList<>(points.size());
        for (OPFLatLng point : points) {
            osmdroidPoints.add(new GeoPoint(point.getLat(), point.getLng()));
        }

        polygon.setPoints(osmdroidPoints);
    }

    @Override
    public void setStrokeColor(final int color) {
        polygon.setStrokeColor(color);
    }

    @Override
    public void setStrokeWidth(final float width) {
        polygon.setStrokeWidth(width);
    }

    @Override
    public void setVisible(final boolean visible) {
        polygon.setVisible(visible);
    }

    @Override
    public void setZIndex(final float zIndex) {
        OPFLog.logStubCall(zIndex);
    }

    @Override
    public String toString() {
        return polygon.toString();
    }

    @Override
    public int hashCode() {
        return polygon.hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OsmdroidPolygonDelegate
                && polygon.equals(((OsmdroidPolygonDelegate) other).polygon));
    }
}
