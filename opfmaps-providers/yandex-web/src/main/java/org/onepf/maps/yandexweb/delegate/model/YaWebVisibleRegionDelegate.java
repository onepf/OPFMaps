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
import org.onepf.maps.yandexweb.model.VisibleRegion;
import org.onepf.opfmaps.delegate.model.VisibleRegionDelegate;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFLatLngBounds;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class YaWebVisibleRegionDelegate implements VisibleRegionDelegate {

    public static final Creator<YaWebVisibleRegionDelegate> CREATOR = new Creator<YaWebVisibleRegionDelegate>() {
        @Override
        public YaWebVisibleRegionDelegate createFromParcel(final Parcel source) {
            return new YaWebVisibleRegionDelegate(source);
        }

        @Override
        public YaWebVisibleRegionDelegate[] newArray(final int size) {
            return new YaWebVisibleRegionDelegate[size];
        }
    };

    @NonNull
    private final VisibleRegion visibleRegion;

    public YaWebVisibleRegionDelegate(@NonNull final OPFLatLng nearLeft,
                                      @NonNull final OPFLatLng nearRight,
                                      @NonNull final OPFLatLng farLeft,
                                      @NonNull final OPFLatLng farRight,
                                      @NonNull final OPFLatLngBounds latLngBounds) {
        this.visibleRegion = new VisibleRegion(
                new LatLng(nearLeft.getLat(), nearLeft.getLng()),
                new LatLng(nearRight.getLat(), nearRight.getLng()),
                new LatLng(farLeft.getLat(), farLeft.getLng()),
                new LatLng(farRight.getLat(), farRight.getLng()),
                new LatLngBounds(
                        new LatLng(latLngBounds.getSouthwest().getLat(), latLngBounds.getSouthwest().getLng()),
                        new LatLng(latLngBounds.getNortheast().getLat(), latLngBounds.getNortheast().getLng())
                )
        );
    }

    public YaWebVisibleRegionDelegate(@NonNull final VisibleRegion visibleRegion) {
        this.visibleRegion = visibleRegion;
    }

    private YaWebVisibleRegionDelegate(@NonNull final Parcel parcel) {
        this.visibleRegion = parcel.readParcelable(VisibleRegion.class.getClassLoader());
    }

    @NonNull
    @Override
    public OPFLatLng getFarLeft() {
        return new OPFLatLng(new YaWebLatLngDelegate(visibleRegion.getFarLeft()));
    }

    @NonNull
    @Override
    public OPFLatLng getFarRight() {
        return new OPFLatLng(new YaWebLatLngDelegate(visibleRegion.getFarRight()));
    }

    @NonNull
    @Override
    public OPFLatLngBounds getLatLngBounds() {
        return new OPFLatLngBounds(new YaWebLatLngBoundsDelegate(
                new LatLngBounds(
                        visibleRegion.getLatLngBounds().getSouthwest(),
                        visibleRegion.getLatLngBounds().getNortheast()
                )
        ));
    }

    @NonNull
    @Override
    public OPFLatLng getNearLeft() {
        return new OPFLatLng(new YaWebLatLngDelegate(visibleRegion.getNearLeft()));
    }

    @NonNull
    @Override
    public OPFLatLng getNearRight() {
        return new OPFLatLng(new YaWebLatLngDelegate(visibleRegion.getNearRight()));
    }

    @Override
    public int describeContents() {
        return visibleRegion.describeContents();
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(visibleRegion, flags);
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof YaWebVisibleRegionDelegate
                && visibleRegion.equals(((YaWebVisibleRegionDelegate) other).visibleRegion));
    }

    @Override
    public int hashCode() {
        return visibleRegion.hashCode();
    }

    @Override
    public String toString() {
        return visibleRegion.toString();
    }
}
