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

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import org.onepf.opfmaps.delegate.model.PolylineDelegate;
import org.onepf.opfmaps.model.OPFLatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
public final class GooglePolylineDelegate implements PolylineDelegate {

    @NonNull
    private final Polyline polyline;

    public GooglePolylineDelegate(@NonNull final Polyline polyline) {
        this.polyline = polyline;
    }

    @Override
    public int getColor() {
        return polyline.getColor();
    }

    @NonNull
    @Override
    public String getId() {
        return polyline.getId();
    }

    @Nullable
    @Override
    public List<OPFLatLng> getPoints() {
        final List<LatLng> amazonPoints = polyline.getPoints();
        if (amazonPoints == null) {
            return null;
        }

        final List<OPFLatLng> points = new ArrayList<>(amazonPoints.size());
        for (LatLng amazonPoint : amazonPoints) {
            points.add(new OPFLatLng(new GoogleLatLngDelegate(amazonPoint)));
        }

        return points;
    }

    @Override
    public float getWidth() {
        return polyline.getWidth();
    }

    @Override
    public float getZIndex() {
        return polyline.getZIndex();
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
        polyline.remove();
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
        final List<LatLng> amazonPoints = new ArrayList<>(points.size());
        for (OPFLatLng point : points) {
            amazonPoints.add(new LatLng(point.getLat(), point.getLng()));
        }

        polyline.setPoints(amazonPoints);
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
        polyline.setZIndex(zIndex);
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
        if (!(other instanceof GooglePolylineDelegate)) return false;

        return polyline.equals(((GooglePolylineDelegate) other).polyline);
    }
    //CHECKSTYLE:ON
}
