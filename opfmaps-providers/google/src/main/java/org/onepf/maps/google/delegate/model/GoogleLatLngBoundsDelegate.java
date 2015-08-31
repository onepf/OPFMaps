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

package org.onepf.maps.google.delegate.model;

import android.os.Parcel;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import org.onepf.opfmaps.delegate.model.LatLngBoundsDelegate;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFLatLngBounds;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
public final class GoogleLatLngBoundsDelegate implements LatLngBoundsDelegate {

    public static final Creator<GoogleLatLngBoundsDelegate> CREATOR = new Creator<GoogleLatLngBoundsDelegate>() {
        @Override
        public GoogleLatLngBoundsDelegate createFromParcel(final Parcel source) {
            return new GoogleLatLngBoundsDelegate(source);
        }

        @Override
        public GoogleLatLngBoundsDelegate[] newArray(final int size) {
            return new GoogleLatLngBoundsDelegate[size];
        }
    };

    @NonNull
    private final LatLngBounds bounds;

    public GoogleLatLngBoundsDelegate(@NonNull final LatLngBounds bounds) {
        this.bounds = bounds;
    }

    private GoogleLatLngBoundsDelegate(@NonNull final Parcel parcel) {
        this.bounds = parcel.readParcelable(LatLngBounds.class.getClassLoader());
    }

    @Override
    public boolean contains(@NonNull final OPFLatLng point) {
        return bounds.contains(new LatLng(point.getLat(), point.getLng()));
    }

    @NonNull
    @Override
    public OPFLatLng getCenter() {
        return new OPFLatLng(new GoogleLatLngDelegate(bounds.getCenter()));
    }

    @NonNull
    @Override
    public OPFLatLngBounds including(@NonNull final OPFLatLng point) {
        return new OPFLatLngBounds(new GoogleLatLngBoundsDelegate(bounds.including(new LatLng(point.getLat(), point.getLng()))));
    }

    @NonNull
    @Override
    public OPFLatLng getNortheast() {
        return new OPFLatLng(new GoogleLatLngDelegate(bounds.northeast));
    }

    @NonNull
    @Override
    public OPFLatLng getSouthwest() {
        return new OPFLatLng(new GoogleLatLngDelegate(bounds.southwest));
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
                && (other == this || other instanceof GoogleLatLngBoundsDelegate
                && bounds.equals(((GoogleLatLngBoundsDelegate) other).bounds));
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

        @SuppressWarnings("PMD.AvoidFieldNameMatchingTypeName")
        @NonNull
        private final LatLngBounds.Builder builder;

        public Builder() {
            this.builder = LatLngBounds.builder();
        }

        @NonNull
        @Override
        public LatLngBoundsDelegate.Builder include(@NonNull final OPFLatLng latLng) {
            builder.include(new LatLng(latLng.getLat(), latLng.getLng()));
            return this;
        }

        @SuppressWarnings("PMD.AccessorClassGeneration")
        @NonNull
        @Override
        public OPFLatLngBounds build() {
            return new OPFLatLngBounds(new GoogleLatLngBoundsDelegate(builder.build()));
        }
    }
}
