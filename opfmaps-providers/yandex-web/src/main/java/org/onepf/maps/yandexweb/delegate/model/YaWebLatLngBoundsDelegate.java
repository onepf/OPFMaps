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

package org.onepf.maps.yandexweb.delegate.model;

import android.os.Parcel;
import android.support.annotation.NonNull;

import org.onepf.maps.yandexweb.model.LatLng;
import org.onepf.maps.yandexweb.model.LatLngBounds;
import org.onepf.opfmaps.delegate.model.LatLngBoundsDelegate;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFLatLngBounds;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class YaWebLatLngBoundsDelegate implements LatLngBoundsDelegate {

    public static final Creator<YaWebLatLngBoundsDelegate> CREATOR = new Creator<YaWebLatLngBoundsDelegate>() {
        @Override
        public YaWebLatLngBoundsDelegate createFromParcel(final Parcel source) {
            return new YaWebLatLngBoundsDelegate(source);
        }

        @Override
        public YaWebLatLngBoundsDelegate[] newArray(final int size) {
            return new YaWebLatLngBoundsDelegate[size];
        }
    };

    @NonNull
    private final LatLngBounds bounds;

    public YaWebLatLngBoundsDelegate(@NonNull final LatLngBounds bounds) {
        this.bounds = bounds;
    }

    private YaWebLatLngBoundsDelegate(@NonNull final Parcel parcel) {
        this.bounds = parcel.readParcelable(LatLngBounds.class.getClassLoader());
    }

    @Override
    public boolean contains(@NonNull final OPFLatLng point) {
        return bounds.contains(new LatLng(point.getLat(), point.getLng()));
    }

    @NonNull
    @Override
    public OPFLatLng getCenter() {
        return new OPFLatLng(new YaWebLatLngDelegate(bounds.getCenter()));
    }

    @NonNull
    @Override
    public OPFLatLngBounds including(@NonNull final OPFLatLng point) {
        return new OPFLatLngBounds(new YaWebLatLngBoundsDelegate(bounds.including(new LatLng(point.getLat(), point.getLng()))));
    }

    @NonNull
    @Override
    public OPFLatLng getNortheast() {
        return new OPFLatLng(new YaWebLatLngDelegate(bounds.getNortheast()));
    }

    @NonNull
    @Override
    public OPFLatLng getSouthwest() {
        return new OPFLatLng(new YaWebLatLngDelegate(bounds.getSouthwest()));
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
                && (other == this || other instanceof YaWebLatLngBoundsDelegate
                && bounds.equals(((YaWebLatLngBoundsDelegate) other).bounds));
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
            return new OPFLatLngBounds(new YaWebLatLngBoundsDelegate(builder.build()));
        }
    }
}
