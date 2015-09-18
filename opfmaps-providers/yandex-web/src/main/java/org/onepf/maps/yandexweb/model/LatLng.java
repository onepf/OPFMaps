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

package org.onepf.maps.yandexweb.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class LatLng implements Parcelable {

    public static final Creator<LatLng> CREATOR = new Creator<LatLng>() {
        @Override
        public LatLng createFromParcel(final Parcel source) {
            return new LatLng(source);
        }

        @Override
        public LatLng[] newArray(final int size) {
            return new LatLng[size];
        }
    };

    private static final double LAT_MIN = -90.0D;
    private static final double LAT_MAX = 90.0D;
    private static final double LNG_MIN = -180.0D;
    private static final double LNG_MAX = 180.0D;
    private static final double DEGREES_CIRCLE = 360.0D;

    private final double latitude;
    private final double longitude;

    public LatLng(final double latitude, final double longitude) {
        this.latitude = clamp(latitude, LAT_MIN, LAT_MAX);
        this.longitude = normalizeLng(longitude);
    }

    private LatLng(@NonNull final Parcel parcel) {
        this.latitude = parcel.readDouble();
        this.longitude = parcel.readDouble();
    }

    public double getLat() {
        return latitude;
    }

    public double getLng() {
        return longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof LatLng)) {
            return false;
        } else {
            final LatLng other = (LatLng) obj;
            return this.latitude == other.latitude && this.longitude == other.longitude;
        }
    }

    //CHECKSTYLE:OFF
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
    //CHECKSTYLE:ON

    @Override
    public String toString() {
        return "lat/lng: (" + this.latitude + "," + this.longitude + ")";
    }

    private static double normalizeLng(double lng) {
        return LNG_MIN <= lng && lng < LNG_MAX ? lng : ((lng - LNG_MAX) % DEGREES_CIRCLE + DEGREES_CIRCLE)
                % DEGREES_CIRCLE - LNG_MAX;
    }

    private static double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }
}
