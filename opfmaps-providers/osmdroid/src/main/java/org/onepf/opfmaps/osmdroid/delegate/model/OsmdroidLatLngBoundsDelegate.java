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

import android.os.Parcel;
import android.support.annotation.NonNull;

import org.onepf.opfmaps.delegate.model.LatLngBoundsDelegate;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFLatLngBounds;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
public final class OsmdroidLatLngBoundsDelegate implements LatLngBoundsDelegate {

    public static final Creator<OsmdroidLatLngBoundsDelegate> CREATOR = new Creator<OsmdroidLatLngBoundsDelegate>() {
        @Override
        public OsmdroidLatLngBoundsDelegate createFromParcel(final Parcel source) {
            return new OsmdroidLatLngBoundsDelegate(source);
        }

        @Override
        public OsmdroidLatLngBoundsDelegate[] newArray(final int size) {
            return new OsmdroidLatLngBoundsDelegate[size];
        }
    };

    @NonNull
    private final BoundingBox bounds;

    public OsmdroidLatLngBoundsDelegate(@NonNull final BoundingBox bounds) {
        this.bounds = bounds;
    }

    private OsmdroidLatLngBoundsDelegate(@NonNull final Parcel parcel) {
        this.bounds = parcel.readParcelable(BoundingBox.class.getClassLoader());
    }

    @Override
    public boolean contains(@NonNull final OPFLatLng point) {
        return bounds.contains(new GeoPoint(point.getLat(), point.getLng()));
    }

    @NonNull
    @Override
    public OPFLatLng getCenter() {
        return new OPFLatLng(new OsmdroidLatLngDelegate(bounds.getCenter()));
    }

    @NonNull
    @Override
    public OPFLatLngBounds including(@NonNull final OPFLatLng point) {
        final ArrayList<GeoPoint> geoPoints = new ArrayList<>(3);
        geoPoints.add(new GeoPoint(bounds.getLatNorth(), bounds.getLonEast()));
        geoPoints.add(new GeoPoint(bounds.getLatSouth(), bounds.getLonWest()));
        geoPoints.add(new GeoPoint(point.getLat(), point.getLng()));
        return new OPFLatLngBounds(new OsmdroidLatLngBoundsDelegate(BoundingBox.fromGeoPoints(geoPoints)));
    }

    @NonNull
    @Override
    public OPFLatLng getNortheast() {
        return new OPFLatLng(new OsmdroidLatLngDelegate(new GeoPoint(bounds.getLatNorth(), bounds.getLonEast())));
    }

    @NonNull
    @Override
    public OPFLatLng getSouthwest() {
        return new OPFLatLng(new OsmdroidLatLngDelegate(new GeoPoint(bounds.getLatSouth(), bounds.getLonWest())));
    }

    @Override
    public int describeContents() {
        return bounds.describeContents();
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(bounds, flags);
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OsmdroidLatLngBoundsDelegate
                && bounds.equals(((OsmdroidLatLngBoundsDelegate) other).bounds));
    }

    @Override
    public int hashCode() {
        return bounds.hashCode();
    }

    @Override
    public String toString() {
        return bounds.toString();
    }

    public static class Builder implements LatLngBoundsDelegate.Builder {

        @NonNull
        private final List<GeoPoint> geoPoints = new ArrayList<>();

        @NonNull
        @Override
        public LatLngBoundsDelegate.Builder include(@NonNull final OPFLatLng latLng) {
            geoPoints.add(new GeoPoint(latLng.getLat(), latLng.getLng()));
            return this;
        }

        @SuppressWarnings("PMD.AccessorClassGeneration")
        @NonNull
        @Override
        public OPFLatLngBounds build() {
            return new OPFLatLngBounds(new OsmdroidLatLngBoundsDelegate(BoundingBox.fromGeoPoints(
                    new ArrayList<>(geoPoints)
            )));
        }
    }
}
