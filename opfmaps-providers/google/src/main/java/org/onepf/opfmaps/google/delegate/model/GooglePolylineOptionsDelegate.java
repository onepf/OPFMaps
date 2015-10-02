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

package org.onepf.opfmaps.google.delegate.model;

import android.os.Parcel;
import android.support.annotation.NonNull;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import org.onepf.opfmaps.delegate.model.PolylineOptionsDelegate;
import org.onepf.opfmaps.model.OPFLatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Roman Savin
 * @since 03.08.2015
 */
public final class GooglePolylineOptionsDelegate implements PolylineOptionsDelegate {

    public static final Creator<GooglePolylineOptionsDelegate> CREATOR = new Creator<GooglePolylineOptionsDelegate>() {
        @Override
        public GooglePolylineOptionsDelegate createFromParcel(final Parcel source) {
            return new GooglePolylineOptionsDelegate(source);
        }

        @Override
        public GooglePolylineOptionsDelegate[] newArray(final int size) {
            return new GooglePolylineOptionsDelegate[size];
        }
    };

    @NonNull
    private final PolylineOptions polylineOptions;

    public GooglePolylineOptionsDelegate() {
        this.polylineOptions = new PolylineOptions();
    }

    private GooglePolylineOptionsDelegate(@NonNull final Parcel parcel) {
        this.polylineOptions = parcel.readParcelable(PolylineOptions.class.getClassLoader());
    }

    @NonNull
    @Override
    public GooglePolylineOptionsDelegate add(@NonNull final OPFLatLng point) {
        polylineOptions.add(new LatLng(point.getLat(), point.getLng()));
        return this;
    }

    @NonNull
    @Override
    public GooglePolylineOptionsDelegate add(@NonNull final OPFLatLng... points) {
        return addAll(Arrays.asList(points));
    }

    @NonNull
    @Override
    public GooglePolylineOptionsDelegate addAll(@NonNull final Iterable<OPFLatLng> points) {
        final List<LatLng> googlePoints = new ArrayList<>();
        for (OPFLatLng point : points) {
            googlePoints.add(new LatLng(point.getLat(), point.getLng()));
        }
        polylineOptions.addAll(googlePoints);
        return this;
    }

    @NonNull
    @Override
    public GooglePolylineOptionsDelegate color(final int color) {
        polylineOptions.color(color);
        return this;
    }

    @NonNull
    @Override
    public GooglePolylineOptionsDelegate geodesic(final boolean geodesic) {
        polylineOptions.geodesic(geodesic);
        return this;
    }

    @Override
    public int getColor() {
        return polylineOptions.getColor();
    }

    @NonNull
    @Override
    public List<OPFLatLng> getPoints() {
        final List<LatLng> points = polylineOptions.getPoints();
        final List<OPFLatLng> opfPoints = new ArrayList<>(points.size());
        for (LatLng point : points) {
            opfPoints.add(new OPFLatLng(new GoogleLatLngDelegate(point)));
        }

        return opfPoints;
    }

    @Override
    public float getWidth() {
        return polylineOptions.getWidth();
    }

    @Override
    public float getZIndex() {
        return polylineOptions.getZIndex();
    }

    @Override
    public boolean isGeodesic() {
        return polylineOptions.isGeodesic();
    }

    @Override
    public boolean isVisible() {
        return polylineOptions.isVisible();
    }

    @NonNull
    @Override
    public GooglePolylineOptionsDelegate visible(final boolean visible) {
        polylineOptions.visible(visible);
        return this;
    }

    @NonNull
    @Override
    public GooglePolylineOptionsDelegate width(final float width) {
        polylineOptions.width(width);
        return this;
    }

    @NonNull
    @Override
    public GooglePolylineOptionsDelegate zIndex(final float zIndex) {
        polylineOptions.zIndex(zIndex);
        return this;
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof GooglePolylineOptionsDelegate
                && polylineOptions.equals(((GooglePolylineOptionsDelegate) other).polylineOptions));
    }

    @Override
    public int hashCode() {
        return polylineOptions.hashCode();
    }

    @Override
    public String toString() {
        return polylineOptions.toString();
    }

    @Override
    public int describeContents() {
        return polylineOptions.describeContents();
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(polylineOptions, flags);
    }
}
