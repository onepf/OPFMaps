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

package org.onepf.opfmaps.google.delegate.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;

import org.onepf.opfmaps.delegate.model.PolygonDelegate;
import org.onepf.opfmaps.model.OPFLatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
public final class GooglePolygonDelegate implements PolygonDelegate {

    @NonNull
    private final Polygon polygon;

    public GooglePolygonDelegate(@NonNull final Polygon polygon) {
        this.polygon = polygon;
    }

    @Override
    public int getFillColor() {
        return polygon.getFillColor();
    }

    @Nullable
    @Override
    public List<List<OPFLatLng>> getHoles() {
        final List<List<LatLng>> holes = polygon.getHoles();
        if (holes == null) {
            return null;
        }

        final List<List<OPFLatLng>> opfHoles = new ArrayList<>(holes.size());
        for (List<LatLng> hole : holes) {
            final List<OPFLatLng> opfHole = new ArrayList<>(hole.size());
            for (LatLng point : hole) {
                opfHole.add(new OPFLatLng(new GoogleLatLngDelegate(point)));
            }
            opfHoles.add(opfHole);
        }
        return opfHoles;
    }

    @NonNull
    @Override
    public String getId() {
        return polygon.getId();
    }

    @NonNull
    @Override
    public List<OPFLatLng> getPoints() {
        final List<LatLng> points = polygon.getPoints();
        final List<OPFLatLng> opfPoints = new ArrayList<>(points.size());
        for (LatLng point : points) {
            opfPoints.add(new OPFLatLng(new GoogleLatLngDelegate(point)));
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
        return polygon.getZIndex();
    }

    @Override
    public boolean isGeodesic() {
        return polygon.isGeodesic();
    }

    @Override
    public boolean isVisible() {
        return polygon.isVisible();
    }

    @Override
    public void remove() {
        polygon.remove();
    }

    @Override
    public void setFillColor(final int color) {
        polygon.setFillColor(color);
    }

    @Override
    public void setGeodesic(final boolean geodesic) {
        polygon.setGeodesic(geodesic);
    }

    @Override
    public void setHoles(@NonNull final List<? extends List<OPFLatLng>> holes) {
        final List<List<LatLng>> googleHoles = new ArrayList<>(holes.size());
        for (List<OPFLatLng> hole : holes) {
            final List<LatLng> googleHole = new ArrayList<>(hole.size());
            for (OPFLatLng point : hole) {
                googleHole.add(new LatLng(point.getLat(), point.getLng()));
            }
            googleHoles.add(googleHole);
        }

        polygon.setHoles(googleHoles);
    }

    @Override
    public void setPoints(@NonNull final List<OPFLatLng> points) {
        final List<LatLng> googlePoints = new ArrayList<>(points.size());
        for (OPFLatLng point : points) {
            googlePoints.add(new LatLng(point.getLat(), point.getLng()));
        }

        polygon.setPoints(googlePoints);
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
        polygon.setZIndex(zIndex);
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
                && (other == this || other instanceof GooglePolygonDelegate
                && polygon.equals(((GooglePolygonDelegate) other).polygon));
    }
}
