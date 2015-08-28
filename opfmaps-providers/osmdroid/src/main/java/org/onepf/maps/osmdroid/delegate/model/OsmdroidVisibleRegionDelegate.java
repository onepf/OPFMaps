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

import org.onepf.maps.osmdroid.model.VisibleRegion;
import org.onepf.opfmaps.delegate.model.VisibleRegionDelegate;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFLatLngBounds;
import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.util.GeoPoint;

/**
 * @author Roman Savin
 * @since 06.08.2015
 */
public final class OsmdroidVisibleRegionDelegate implements VisibleRegionDelegate {

    public static final Creator<OsmdroidVisibleRegionDelegate> CREATOR = new Creator<OsmdroidVisibleRegionDelegate>() {
        @Override
        public OsmdroidVisibleRegionDelegate createFromParcel(final Parcel source) {
            return new OsmdroidVisibleRegionDelegate(source);
        }

        @Override
        public OsmdroidVisibleRegionDelegate[] newArray(final int size) {
            return new OsmdroidVisibleRegionDelegate[size];
        }
    };

    @NonNull
    private final VisibleRegion visibleRegion;

    public OsmdroidVisibleRegionDelegate(@NonNull final OPFLatLng nearLeft,
                                         @NonNull final OPFLatLng nearRight,
                                         @NonNull final OPFLatLng farLeft,
                                         @NonNull final OPFLatLng farRight,
                                         @NonNull final OPFLatLngBounds latLngBounds) {
        this.visibleRegion = new VisibleRegion(
                new GeoPoint(nearLeft.getLat(), nearLeft.getLng()),
                new GeoPoint(nearRight.getLat(), nearRight.getLng()),
                new GeoPoint(farLeft.getLat(), farLeft.getLng()),
                new GeoPoint(farRight.getLat(), farRight.getLng()),
                new BoundingBoxE6(
                        latLngBounds.getNortheast().getLat(),
                        latLngBounds.getNortheast().getLng(),
                        latLngBounds.getSouthwest().getLat(),
                        latLngBounds.getSouthwest().getLng()
                )
        );
    }

    public OsmdroidVisibleRegionDelegate(@NonNull final VisibleRegion visibleRegion) {
        this.visibleRegion = visibleRegion;
    }

    private OsmdroidVisibleRegionDelegate(@NonNull final Parcel parcel) {
        this.visibleRegion = parcel.readParcelable(VisibleRegion.class.getClassLoader());
    }

    @NonNull
    @Override
    public OPFLatLng getFarLeft() {
        return new OPFLatLng(new OsmdroidLatLngDelegate(visibleRegion.getFarLeft()));
    }

    @NonNull
    @Override
    public OPFLatLng getFarRight() {
        return new OPFLatLng(new OsmdroidLatLngDelegate(visibleRegion.getFarRight()));
    }

    @NonNull
    @Override
    public OPFLatLngBounds getLatLngBounds() {
        return new OPFLatLngBounds(new OsmdroidLatLngBoundsDelegate(
                visibleRegion.getLatLngBounds()
        ));
    }

    @NonNull
    @Override
    public OPFLatLng getNearLeft() {
        return new OPFLatLng(new OsmdroidLatLngDelegate(visibleRegion.getNearLeft()));
    }

    @NonNull
    @Override
    public OPFLatLng getNearRight() {
        return new OPFLatLng(new OsmdroidLatLngDelegate(visibleRegion.getNearRight()));
    }

    @Override
    public int describeContents() {
        return visibleRegion.describeContents();
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(visibleRegion, flags);
    }

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OsmdroidVisibleRegionDelegate)) return false;

        return visibleRegion.equals(((OsmdroidVisibleRegionDelegate) other).visibleRegion);
    }
    //CHECKSTYLE:ON

    @Override
    public int hashCode() {
        return visibleRegion.hashCode();
    }

    @Override
    public String toString() {
        return visibleRegion.toString();
    }
}
