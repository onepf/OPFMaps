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

package org.onepf.opfmaps.amazon.delegate.model;

import android.os.Parcel;
import android.support.annotation.NonNull;

import com.amazon.geo.mapsv2.model.LatLng;

import org.onepf.opfmaps.delegate.model.LatLngDelegate;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
public final class AmazonLatLngDelegate implements LatLngDelegate {

    public static final Creator<AmazonLatLngDelegate> CREATOR = new Creator<AmazonLatLngDelegate>() {
        @Override
        public AmazonLatLngDelegate createFromParcel(final Parcel source) {
            return new AmazonLatLngDelegate(source);
        }

        @Override
        public AmazonLatLngDelegate[] newArray(final int size) {
            return new AmazonLatLngDelegate[size];
        }
    };

    @NonNull
    private final LatLng latLng;

    public AmazonLatLngDelegate(@NonNull final LatLng latLng) {
        this.latLng = latLng;
    }

    private AmazonLatLngDelegate(@NonNull final Parcel parcel) {
        this.latLng = parcel.readParcelable(LatLng.class.getClassLoader());
    }

    @Override
    public double getLat() {
        return latLng.latitude;
    }

    @Override
    public double getLng() {
        return latLng.longitude;
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
                && (other == this || other instanceof AmazonLatLngDelegate
                && latLng.equals(((AmazonLatLngDelegate) other).latLng));
    }

    @Override
    public int hashCode() {
        return latLng.hashCode();
    }

    @Override
    public String toString() {
        return latLng.toString();
    }
}
