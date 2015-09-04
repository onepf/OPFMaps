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
import org.onepf.opfmaps.delegate.model.LatLngDelegate;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class YaWebLatLngDelegate implements LatLngDelegate {

    public static final Creator<YaWebLatLngDelegate> CREATOR = new Creator<YaWebLatLngDelegate>() {
        @Override
        public YaWebLatLngDelegate createFromParcel(final Parcel source) {
            return new YaWebLatLngDelegate(source);
        }

        @Override
        public YaWebLatLngDelegate[] newArray(final int size) {
            return new YaWebLatLngDelegate[size];
        }
    };

    @NonNull
    private final LatLng latLng;

    public YaWebLatLngDelegate(@NonNull final LatLng latLng) {
        this.latLng = latLng;
    }

    private YaWebLatLngDelegate(@NonNull final Parcel parcel) {
        this.latLng = parcel.readParcelable(LatLng.class.getClassLoader());
    }

    @Override
    public double getLat() {
        return latLng.getLat();
    }

    @Override
    public double getLng() {
        return latLng.getLng();
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
                && (other == this || other instanceof YaWebLatLngDelegate
                && latLng.equals(((YaWebLatLngDelegate) other).latLng));
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
