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
import org.onepf.opfmaps.delegate.model.PolylineOptionsDelegate;

import java.util.List;

/**
 * @author Roman Savin
 * @since 30.07.2015
 */
public final class OPFPolylineOptions implements PolylineOptionsDelegate {

    public static final Creator<OPFPolylineOptions> CREATOR = new Creator<OPFPolylineOptions>() {
        @Override
        public OPFPolylineOptions createFromParcel(final Parcel source) {
            return new OPFPolylineOptions(source);
        }

        @Override
        public OPFPolylineOptions[] newArray(final int size) {
            return new OPFPolylineOptions[size];
        }
    };

    @NonNull
    private final PolylineOptionsDelegate delegate;

    //todo default constructor

    public OPFPolylineOptions(@NonNull final PolylineOptionsDelegate delegate) {
        this.delegate = delegate;
    }

    private OPFPolylineOptions(@NonNull final Parcel parcel) {
        try {
            this.delegate = parcel.readParcelable(Class.forName(parcel.readString()).getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    @Override
    public OPFPolylineOptions add(@NonNull final OPFLatLng point) {
        delegate.add(point);
        return this;
    }

    @NonNull
    @Override
    public OPFPolylineOptions add(@NonNull final OPFLatLng... points) {
        delegate.add(points);
        return this;
    }

    @NonNull
    @Override
    public OPFPolylineOptions addAll(@NonNull final Iterable<OPFLatLng> points) {
        delegate.addAll(points);
        return this;
    }

    @NonNull
    @Override
    public OPFPolylineOptions color(final int color) {
        delegate.color(color);
        return this;
    }

    @NonNull
    @Override
    public OPFPolylineOptions geodesic(final boolean geodesic) {
        delegate.geodesic(geodesic);
        return this;
    }

    @Override
    public int getColor() {
        return delegate.getColor();
    }

    @NonNull
    @Override
    public List<OPFLatLng> getPoints() {
        return delegate.getPoints();
    }

    @Override
    public float getWidth() {
        return delegate.getWidth();
    }

    @Override
    public float getZIndex() {
        return delegate.getZIndex();
    }

    @Override
    public boolean isGeodesic() {
        return delegate.isGeodesic();
    }

    @Override
    public boolean isVisible() {
        return delegate.isVisible();
    }

    @NonNull
    @Override
    public OPFPolylineOptions visible(final boolean visible) {
        delegate.visible(visible);
        return this;
    }

    @NonNull
    @Override
    public OPFPolylineOptions width(final float width) {
        delegate.width(width);
        return this;
    }

    @NonNull
    @Override
    public OPFPolylineOptions zIndex(final float zIndex) {
        delegate.zIndex(zIndex);
        return this;
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
        if (!(other instanceof OPFPolylineOptions)) return false;

        return delegate.equals(((OPFPolylineOptions) other).delegate);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public String toString() {
        return delegate.toString();
    }
}
