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

import org.onepf.opfmaps.delegate.model.LatLngDelegate;
import org.osmdroid.util.GeoPoint;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
public final class OsmdroidLatLngDelegate implements LatLngDelegate {

    public static final Creator<OsmdroidLatLngDelegate> CREATOR = new Creator<OsmdroidLatLngDelegate>() {
        @Override
        public OsmdroidLatLngDelegate createFromParcel(final Parcel source) {
            return new OsmdroidLatLngDelegate(source);
        }

        @Override
        public OsmdroidLatLngDelegate[] newArray(final int size) {
            return new OsmdroidLatLngDelegate[size];
        }
    };

    @NonNull
    private final GeoPoint latLng;

    public OsmdroidLatLngDelegate(@NonNull final GeoPoint latLng) {
        this.latLng = latLng;
    }

    private OsmdroidLatLngDelegate(@NonNull final Parcel parcel) {
        this.latLng = parcel.readParcelable(GeoPoint.class.getClassLoader());
    }

    @Override
    public double getLat() {
        return latLng.getLatitude();
    }

    @Override
    public double getLng() {
        return latLng.getLongitude();
    }

    @Override
    public int describeContents() {
        return latLng.describeContents();
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(latLng, flags);
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OsmdroidLatLngDelegate
                && latLng.equals(((OsmdroidLatLngDelegate) other).latLng));
    }

    @Override
    public int hashCode() {
        return latLng.hashCode();
    }

    @Override
    public String toString() {
        return "lat/lng: (" + latLng.getLatitude() + "," + latLng.getLongitude() + ")";
    }
}
