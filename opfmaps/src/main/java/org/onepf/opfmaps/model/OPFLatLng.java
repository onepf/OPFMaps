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

package org.onepf.opfmaps.model;

import android.os.Parcel;
import android.support.annotation.NonNull;

import org.onepf.opfmaps.OPFMapHelper;
import org.onepf.opfmaps.delegate.model.LatLngDelegate;

/**
 * An immutable class representing a pair of latitude and longitude coordinates, stored as degrees.
 *
 * @author Roman Savin
 * @since 09.06.2015
 */
public final class OPFLatLng implements LatLngDelegate {

    public static final Creator<OPFLatLng> CREATOR = new Creator<OPFLatLng>() {
        @Override
        public OPFLatLng createFromParcel(final Parcel source) {
            return new OPFLatLng(source);
        }

        @Override
        public OPFLatLng[] newArray(final int size) {
            return new OPFLatLng[size];
        }
    };

    @NonNull
    private final LatLngDelegate delegate;

    /**
     * Constructs an {@link OPFLatLng} with the given latitude and longitude, measured in degrees.
     *
     * @param latitude  The point's latitude. This will be clamped to between -90 degrees and +90 degrees inclusive.
     * @param longitude The point's longitude. This will be normalized to be within -180 degrees inclusive and +180 degrees exclusive.
     */
    public OPFLatLng(final double latitude, final double longitude) {
        this.delegate = OPFMapHelper.getInstance().getDelegatesFactory().createLatLngDelegate(latitude, longitude);
    }

    public OPFLatLng(@NonNull final LatLngDelegate delegate) {
        this.delegate = delegate;
    }

    @SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes")
    private OPFLatLng(@NonNull final Parcel parcel) {
        try {
            this.delegate = parcel.readParcelable(Class.forName(parcel.readString()).getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns latitude, in degrees.
     *
     * @return The latitude, in degrees. This value is in the range [-90, 90].
     */
    @Override
    public double getLat() {
        return delegate.getLat();
    }

    /**
     * Returns longitude, in degrees.
     *
     * @return The longitude, in degrees. This value is in the range [-180, 180).
     */
    @Override
    public double getLng() {
        return delegate.getLng();
    }

    @Override
    public int describeContents() {
        return delegate.describeContents();
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(delegate.getClass().getCanonicalName());
        dest.writeParcelable(delegate, flags);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public String toString() {
        return delegate.toString();
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OPFLatLng
                && delegate.equals(((OPFLatLng) other).delegate));
    }
}
