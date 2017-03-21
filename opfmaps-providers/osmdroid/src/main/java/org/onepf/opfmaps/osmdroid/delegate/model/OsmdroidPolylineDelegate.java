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

import org.onepf.opfmaps.delegate.model.PolylineDelegate;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfutils.OPFLog;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Polyline;

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
        if (mapView != null) {
            polyline.setColor(color);
            mapView.invalidate();
        }
    }

    @Override
    public void setGeodesic(final boolean geodesic) {
        if (mapView != null) {
            polyline.setGeodesic(geodesic);
            mapView.invalidate();
        }
    }

    @Override
    public void setPoints(@NonNull final List<OPFLatLng> points) {
        if (mapView != null) {
            final List<GeoPoint> osmdroidPoints = new ArrayList<>(points.size());
            for (OPFLatLng point : points) {
                osmdroidPoints.add(new GeoPoint(point.getLat(), point.getLng()));
            }

            polyline.setPoints(osmdroidPoints);
            mapView.invalidate();
        }
    }

    @Override
    public void setVisible(final boolean visible) {
        if (mapView != null) {
            polyline.setVisible(visible);
            mapView.invalidate();
        }
    }

    @Override
    public void setWidth(final float width) {
        if (mapView != null) {
            polyline.setWidth(width);
            mapView.invalidate();
        }
    }

    @Override
    public void setZIndex(final float zIndex) {
        OPFLog.logStubCall(zIndex);
    }

    @Override
    public String toString() {
        return polyline.toString();
    }

    @Override
    public int hashCode() {
        return polyline.hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OsmdroidPolylineDelegate
                && polyline.equals(((OsmdroidPolylineDelegate) other).polyline));
    }
}
