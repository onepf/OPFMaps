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
import org.onepf.opfmaps.delegate.model.LatLngDelegate;

/**
 * Created by akarimova on 09.06.15.
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

    //todo OPFLatLng(double latitude, double longitude)

    public OPFLatLng(@NonNull final LatLngDelegate delegate) {
        this.delegate = delegate;
    }

    private OPFLatLng(@NonNull final Parcel parcel) {
        try {
            this.delegate = parcel.readParcelable(Class.forName(parcel.readString()).getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double getLat() {
        return delegate.getLat();
    }

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
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OPFLatLng)) return false;

        return delegate.equals(((OPFLatLng) other).delegate);
    }
}
