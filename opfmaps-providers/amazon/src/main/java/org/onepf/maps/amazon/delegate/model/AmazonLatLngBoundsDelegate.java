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

package org.onepf.maps.amazon.delegate.model;

import android.os.Parcel;
import android.support.annotation.NonNull;
import com.amazon.geo.mapsv2.model.LatLng;
import com.amazon.geo.mapsv2.model.LatLngBounds;
import org.onepf.opfmaps.delegate.model.LatLngBoundsDelegate;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFLatLngBounds;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
public final class AmazonLatLngBoundsDelegate implements LatLngBoundsDelegate {

    public static final Creator<AmazonLatLngBoundsDelegate> CREATOR = new Creator<AmazonLatLngBoundsDelegate>() {
        @Override
        public AmazonLatLngBoundsDelegate createFromParcel(final Parcel source) {
            return new AmazonLatLngBoundsDelegate(source);
        }

        @Override
        public AmazonLatLngBoundsDelegate[] newArray(final int size) {
            return new AmazonLatLngBoundsDelegate[size];
        }
    };

    @NonNull
    private final LatLngBounds bounds;

    public AmazonLatLngBoundsDelegate(@NonNull final LatLngBounds bounds) {
        this.bounds = bounds;
    }

    private AmazonLatLngBoundsDelegate(@NonNull final Parcel parcel) {
        this.bounds = parcel.readParcelable(LatLngBounds.class.getClassLoader());
    }

    @Override
    public boolean contains(@NonNull final OPFLatLng point) {
        return bounds.contains(new LatLng(point.getLat(), point.getLng()));
    }

    @NonNull
    @Override
    public OPFLatLng getCenter() {
        return new OPFLatLng(new AmazonLatLngDelegate(bounds.getCenter()));
    }

    @NonNull
    @Override
    public OPFLatLngBounds including(@NonNull final OPFLatLng point) {
        return new OPFLatLngBounds(new AmazonLatLngBoundsDelegate(bounds.including(new LatLng(point.getLat(), point.getLng()))));
    }

    @NonNull
    @Override
    public OPFLatLng getNortheast() {
        return new OPFLatLng(new AmazonLatLngDelegate(bounds.northeast));
    }

    @NonNull
    @Override
    public OPFLatLng getSouthwest() {
        return new OPFLatLng(new AmazonLatLngDelegate(bounds.southwest));
    }

    @Override
    public int describeContents() {
        return bounds.describeContents();
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(bounds, flags);
    }

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof AmazonLatLngBoundsDelegate)) return false;

        return bounds.equals(((AmazonLatLngBoundsDelegate) other).bounds);
    }
    //CHECKSTYLE:ON

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
            return new OPFLatLngBounds(new AmazonLatLngBoundsDelegate(builder.build()));
        }
    }
}
