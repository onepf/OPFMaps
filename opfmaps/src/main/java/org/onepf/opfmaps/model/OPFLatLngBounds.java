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
import org.onepf.opfmaps.delegate.model.LatLngBoundsDelegate;

/**
 * @author Roman Savin
 * @since 29.07.2015
 */
public final class OPFLatLngBounds implements LatLngBoundsDelegate {

    public static final Creator<OPFLatLngBounds> CREATOR = new Creator<OPFLatLngBounds>() {
        @Override
        public OPFLatLngBounds createFromParcel(final Parcel source) {
            return new OPFLatLngBounds(source);
        }

        @Override
        public OPFLatLngBounds[] newArray(final int size) {
            return new OPFLatLngBounds[size];
        }
    };

    public static Builder builder() {
        return new Builder(OPFMapHelper.getInstance().getDelegatesFactory().createLatLngBoundsBuilderDelegate());
    }

    @NonNull
    private LatLngBoundsDelegate delegate;

    public OPFLatLngBounds(@NonNull final OPFLatLng southwest, @NonNull final OPFLatLng northeast) {
        this.delegate = OPFMapHelper.getInstance().getDelegatesFactory().createLatLngBoundsDelegate(southwest, northeast);
    }

    public OPFLatLngBounds(@NonNull final LatLngBoundsDelegate delegate) {
        this.delegate = delegate;
    }

    private OPFLatLngBounds(@NonNull final Parcel parcel) {
        try {
            this.delegate = parcel.readParcelable(Class.forName(parcel.readString()).getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean contains(@NonNull final OPFLatLng point) {
        return delegate.contains(point);
    }

    @NonNull
    public OPFLatLng getCenter() {
        return delegate.getCenter();
    }

    @NonNull
    public OPFLatLngBounds including(@NonNull final OPFLatLng point) {
        return new OPFLatLngBounds(delegate.including(point));
    }

    @NonNull
    @Override
    public OPFLatLng getNortheast() {
        return delegate.getNortheast();
    }

    @NonNull
    @Override
    public OPFLatLng getSouthwest() {
        return delegate.getSouthwest();
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
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OPFLatLngBounds)) return false;

        return delegate.equals(((OPFLatLngBounds) other).delegate);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public String toString() {
        return delegate.toString();
    }

    public static class Builder implements LatLngBoundsDelegate.Builder {

        @NonNull
        private LatLngBoundsDelegate.Builder delegate;

        public Builder(@NonNull final LatLngBoundsDelegate.Builder delegate) {
            this.delegate = delegate;
        }

        @NonNull
        @Override
        public LatLngBoundsDelegate.Builder include(@NonNull final OPFLatLng latLng) {
            return delegate.include(latLng);
        }

        @NonNull
        @Override
        public OPFLatLngBounds build() {
            return delegate.build();
        }
    }
}
