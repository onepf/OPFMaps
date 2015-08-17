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

import android.os.Parcel;
import android.support.annotation.NonNull;
import org.onepf.maps.osmdroid.model.PolylineOptions;
import org.onepf.opfmaps.delegate.model.PolylineOptionsDelegate;
import org.onepf.opfmaps.model.OPFLatLng;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Roman Savin
 * @since 03.08.2015
 */
public final class OsmdroidPolylineOptionsDelegate implements PolylineOptionsDelegate {

    public static final Creator<OsmdroidPolylineOptionsDelegate> CREATOR = new Creator<OsmdroidPolylineOptionsDelegate>() {
        @Override
        public OsmdroidPolylineOptionsDelegate createFromParcel(final Parcel source) {
            return new OsmdroidPolylineOptionsDelegate(source);
        }

        @Override
        public OsmdroidPolylineOptionsDelegate[] newArray(final int size) {
            return new OsmdroidPolylineOptionsDelegate[size];
        }
    };

    @NonNull
    private final PolylineOptions polylineOptions;

    public OsmdroidPolylineOptionsDelegate() {
        this.polylineOptions = new PolylineOptions();
    }

    private OsmdroidPolylineOptionsDelegate(@NonNull final Parcel parcel) {
        this.polylineOptions = parcel.readParcelable(PolylineOptions.class.getClassLoader());
    }

    @NonNull
    @Override
    public OsmdroidPolylineOptionsDelegate add(@NonNull final OPFLatLng point) {
        polylineOptions.add(new GeoPoint(point.getLat(), point.getLng()));
        return this;
    }

    @NonNull
    @Override
    public OsmdroidPolylineOptionsDelegate add(@NonNull final OPFLatLng... points) {
        return addAll(Arrays.asList(points));
    }

    @NonNull
    @Override
    public OsmdroidPolylineOptionsDelegate addAll(@NonNull final Iterable<OPFLatLng> points) {
        final List<GeoPoint> osmdroidPoints = new ArrayList<>();
        for (OPFLatLng point : points) {
            osmdroidPoints.add(new GeoPoint(point.getLat(), point.getLng()));
        }
        polylineOptions.addAll(osmdroidPoints);
        return this;
    }

    @NonNull
    @Override
    public OsmdroidPolylineOptionsDelegate color(final int color) {
        polylineOptions.color(color);
        return this;
    }

    @NonNull
    @Override
    public OsmdroidPolylineOptionsDelegate geodesic(final boolean geodesic) {
        polylineOptions.geodesic(geodesic);
        return this;
    }

    @Override
    public int getColor() {
        return polylineOptions.getColor();
    }

    @NonNull
    @Override
    public List<OPFLatLng> getPoints() {
        final List<GeoPoint> points = polylineOptions.getPoints();
        final List<OPFLatLng> opfPoints = new ArrayList<>(points.size());
        for (GeoPoint point : points) {
            opfPoints.add(new OPFLatLng(new OsmdroidLatLngDelegate(point)));
        }

        return opfPoints;
    }

    @Override
    public float getWidth() {
        return polylineOptions.getWidth();
    }

    @Override
    public float getZIndex() {
        return polylineOptions.getZIndex();
    }

    @Override
    public boolean isGeodesic() {
        return polylineOptions.isGeodesic();
    }

    @Override
    public boolean isVisible() {
        return polylineOptions.isVisible();
    }

    @NonNull
    @Override
    public OsmdroidPolylineOptionsDelegate visible(final boolean visible) {
        polylineOptions.visible(visible);
        return this;
    }

    @NonNull
    @Override
    public OsmdroidPolylineOptionsDelegate width(final float width) {
        polylineOptions.width(width);
        return this;
    }

    @NonNull
    @Override
    public OsmdroidPolylineOptionsDelegate zIndex(final float zIndex) {
        polylineOptions.zIndex(zIndex);
        return this;
    }

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OsmdroidPolylineOptionsDelegate)) return false;

        return polylineOptions.equals(((OsmdroidPolylineOptionsDelegate) other).polylineOptions);
    }
    //CHECKSTYLE:ON

    @Override
    public int hashCode() {
        return polylineOptions.hashCode();
    }

    @Override
    public String toString() {
        return polylineOptions.toString();
    }

    @Override
    public int describeContents() {
        return polylineOptions.describeContents();
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(polylineOptions, flags);
    }
}
