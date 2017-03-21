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

package org.onepf.opfmaps.osmdroid.delegate.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.onepf.opfmaps.delegate.model.CircleDelegate;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfutils.OPFLog;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Polygon;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
public final class OsmdroidCircleDelegate implements CircleDelegate {

    @Nullable
    private MapView mapView;

    @NonNull
    private final Polygon polygon;

    @NonNull
    private final String id;

    @NonNull
    private GeoPoint center;

    private double radius;

    public OsmdroidCircleDelegate(@SuppressWarnings("NullableProblems") @NonNull final MapView mapView,
                                  @NonNull final Polygon polygon,
                                  @NonNull final GeoPoint center,
                                  final double radius) {
        this.mapView = mapView;
        this.polygon = polygon;
        this.polygon.setPoints(Polygon.pointsAsCircle(center, radius));
        this.center = center;
        this.radius = radius;
        this.id = Integer.toString(this.polygon.hashCode());
    }

    @NonNull
    public Polygon getPolygon() {
        return polygon;
    }

    @NonNull
    @Override
    public OPFLatLng getCenter() {
        return new OPFLatLng(new OsmdroidLatLngDelegate(center));
    }

    @Override
    public int getFillColor() {
        return polygon.getFillColor();
    }

    @NonNull
    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getRadius() {
        return radius;
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
    public void setCenter(@NonNull final OPFLatLng center) {
        if (mapView != null) {
            this.center = new GeoPoint(center.getLat(), center.getLng());
            polygon.setPoints(Polygon.pointsAsCircle(this.center, radius));
            mapView.invalidate();
        }
    }

    @Override
    public void setFillColor(final int color) {
        if (mapView != null) {
            polygon.setFillColor(color);
            mapView.invalidate();
        }
    }

    @Override
    public void setRadius(final double radius) {
        if (mapView != null) {
            this.radius = radius;
            polygon.setPoints(Polygon.pointsAsCircle(center, this.radius));
            mapView.invalidate();
        }
    }

    @Override
    public void setStrokeColor(final int color) {
        if (mapView != null) {
            polygon.setStrokeColor(color);
            mapView.invalidate();
        }
    }

    @Override
    public void setStrokeWidth(final float width) {
        if (mapView != null) {
            polygon.setStrokeWidth(width);
            mapView.invalidate();
        }
    }

    @Override
    public void setVisible(final boolean visible) {
        if (mapView != null) {
            polygon.setVisible(visible);
            mapView.invalidate();
        }
    }

    @Override
    public void setZIndex(final float zIndex) {
        OPFLog.logStubCall(zIndex);
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OsmdroidCircleDelegate
                && polygon.equals(((OsmdroidCircleDelegate) other).polygon));
    }

    @Override
    public int hashCode() {
        return polygon.hashCode();
    }

    @Override
    public String toString() {
        return polygon.toString();
    }
}
