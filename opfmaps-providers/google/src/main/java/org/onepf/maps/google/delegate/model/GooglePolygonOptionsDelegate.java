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
import com.google.android.gms.maps.model.PolygonOptions;

import org.onepf.opfmaps.delegate.model.PolygonOptionsDelegate;
import org.onepf.opfmaps.model.OPFLatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Roman Savin
 * @since 03.08.2015
 */
public final class GooglePolygonOptionsDelegate implements PolygonOptionsDelegate {

    public static final Creator<GooglePolygonOptionsDelegate> CREATOR = new Creator<GooglePolygonOptionsDelegate>() {
        @Override
        public GooglePolygonOptionsDelegate createFromParcel(final Parcel source) {
            return new GooglePolygonOptionsDelegate(source);
        }

        @Override
        public GooglePolygonOptionsDelegate[] newArray(final int size) {
            return new GooglePolygonOptionsDelegate[size];
        }
    };

    @NonNull
    private final PolygonOptions polygonOptions;

    public GooglePolygonOptionsDelegate() {
        this.polygonOptions = new PolygonOptions();
    }

    private GooglePolygonOptionsDelegate(@NonNull final Parcel parcel) {
        this.polygonOptions = parcel.readParcelable(PolygonOptions.class.getClassLoader());
    }

    @NonNull
    @Override
    public PolygonOptionsDelegate add(@NonNull final OPFLatLng point) {
        polygonOptions.add(new LatLng(point.getLat(), point.getLng()));
        return this;
    }

    @NonNull
    @Override
    public PolygonOptionsDelegate add(@NonNull final OPFLatLng... points) {
        return addAll(Arrays.asList(points));
    }

    @NonNull
    @Override
    public PolygonOptionsDelegate addAll(@NonNull final Iterable<OPFLatLng> points) {
        final List<LatLng> googlePoints = new ArrayList<>();
        for (OPFLatLng point : points) {
            googlePoints.add(new LatLng(point.getLat(), point.getLng()));
        }
        polygonOptions.addAll(googlePoints);
        return this;
    }

    @NonNull
    @Override
    public PolygonOptionsDelegate addHole(@NonNull final Iterable<OPFLatLng> points) {
        final List<LatLng> googlePoints = new ArrayList<>();
        for (OPFLatLng point : points) {
            googlePoints.add(new LatLng(point.getLat(), point.getLng()));
        }
        polygonOptions.addHole(googlePoints);
        return this;
    }

    @NonNull
    @Override
    public PolygonOptionsDelegate fillColor(final int color) {
        polygonOptions.fillColor(color);
        return this;
    }

    @NonNull
    @Override
    public PolygonOptionsDelegate geodesic(final boolean geodesic) {
        polygonOptions.geodesic(geodesic);
        return this;
    }

    @Override
    public int getFillColor() {
        return polygonOptions.getFillColor();
    }

    @NonNull
    @Override
    public List<List<OPFLatLng>> getHoles() {
        final List<List<LatLng>> holes = polygonOptions.getHoles();
        final List<List<OPFLatLng>> opfHoles = new ArrayList<>(holes.size());
        for (List<LatLng> hole : holes) {
            final List<OPFLatLng> opfHole = new ArrayList<>(hole.size());
            for (LatLng point : hole) {
                opfHole.add(new OPFLatLng(new GoogleLatLngDelegate(point)));
            }
            opfHoles.add(opfHole);
        }

        return opfHoles;
    }

    @NonNull
    @Override
    public List<OPFLatLng> getPoints() {
        final List<LatLng> points = polygonOptions.getPoints();
        final List<OPFLatLng> opfPoints = new ArrayList<>(points.size());
        for (LatLng point : points) {
            opfPoints.add(new OPFLatLng(new GoogleLatLngDelegate(point)));
        }

        return opfPoints;
    }

    @Override
    public int getStrokeColor() {
        return polygonOptions.getStrokeColor();
    }

    @Override
    public float getStrokeWidth() {
        return polygonOptions.getStrokeWidth();
    }

    @Override
    public float getZIndex() {
        return polygonOptions.getZIndex();
    }

    @Override
    public boolean isGeodesic() {
        return polygonOptions.isGeodesic();
    }

    @Override
    public boolean isVisible() {
        return polygonOptions.isVisible();
    }

    @NonNull
    @Override
    public PolygonOptionsDelegate strokeColor(final int color) {
        polygonOptions.strokeColor(color);
        return this;
    }

    @NonNull
    @Override
    public PolygonOptionsDelegate strokeWidth(final float width) {
        polygonOptions.strokeWidth(width);
        return this;
    }

    @NonNull
    @Override
    public PolygonOptionsDelegate visible(final boolean visible) {
        polygonOptions.visible(visible);
        return this;
    }

    @NonNull
    @Override
    public PolygonOptionsDelegate zIndex(final float zIndex) {
        polygonOptions.zIndex(zIndex);
        return this;
    }

    @Override
    public int describeContents() {
        return polygonOptions.describeContents();
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(polygonOptions, flags);
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof GooglePolygonOptionsDelegate
                && polygonOptions.equals(((GooglePolygonOptionsDelegate) other).polygonOptions));
    }

    @Override
    public int hashCode() {
        return polygonOptions.hashCode();
    }

    @Override
    public String toString() {
        return polygonOptions.toString();
    }
}
