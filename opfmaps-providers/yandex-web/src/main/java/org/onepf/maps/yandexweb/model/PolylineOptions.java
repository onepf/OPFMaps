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

package org.onepf.maps.yandexweb.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Roman Savin
 * @since 17.08.2015
 */
public final class PolylineOptions implements Parcelable {

    public static final Creator<PolylineOptions> CREATOR = new Creator<PolylineOptions>() {
        @Override
        public PolylineOptions createFromParcel(final Parcel source) {
            return new PolylineOptions(source);
        }

        @Override
        public PolylineOptions[] newArray(final int size) {
            return new PolylineOptions[size];
        }
    };

    private static final int DEFAULT_COLOR = -16777216;

    @NonNull
    private final List<LatLng> points = new ArrayList<>();
    private int color = DEFAULT_COLOR;
    private float width = 10.0F;
    private float zIndex;
    private boolean isGeodesic;
    private boolean isVisible = true;

    public PolylineOptions() {
        //nothing
    }

    private PolylineOptions(@NonNull final Parcel source) {
        source.readList(this.points, LatLng.class.getClassLoader());
        this.color = source.readInt();
        this.width = source.readFloat();
        this.zIndex = source.readFloat();
        this.isGeodesic = source.readByte() != 0;
        this.isVisible = source.readByte() != 0;
    }

    @NonNull
    public PolylineOptions add(@NonNull final LatLng point) {
        this.points.add(point);
        return this;
    }

    @NonNull
    public PolylineOptions add(@NonNull final LatLng... points) {
        this.addAll(Arrays.asList(points));
        return this;
    }

    @NonNull
    public PolylineOptions addAll(@NonNull final Iterable<LatLng> points) {
        for (LatLng point : points) {
            this.points.add(point);
        }

        return this;
    }

    @NonNull
    public PolylineOptions color(final int color) {
        this.color = color;
        return this;
    }

    @NonNull
    public PolylineOptions geodesic(final boolean geodesic) {
        this.isGeodesic = geodesic;
        return this;
    }

    public int getColor() {
        return this.color;
    }

    @NonNull
    public List<LatLng> getPoints() {
        return this.points;
    }

    public float getWidth() {
        return this.width;
    }

    public float getZIndex() {
        return this.zIndex;
    }

    public boolean isGeodesic() {
        return this.isGeodesic;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    @NonNull
    public PolylineOptions visible(final boolean visible) {
        this.isVisible = visible;
        return this;
    }

    @NonNull
    public PolylineOptions width(final float width) {
        this.width = width;
        return this;
    }

    @NonNull
    public PolylineOptions zIndex(final float zIndex) {
        this.zIndex = zIndex;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof PolylineOptions)) {
            return false;
        } else {
            PolylineOptions other = (PolylineOptions) obj;
            return this.color == other.color
                    && this.width == other.width
                    && this.zIndex == other.zIndex
                    && this.isGeodesic == other.isGeodesic
                    && this.isVisible == other.isVisible
                    && this.points.equals(other.points);
        }
    }

    @SuppressWarnings("PMD.NPathComplexity")
    @Override
    public int hashCode() {
        int result = points.hashCode();
        result = 31 * result + color;
        result = 31 * result + (width != +0.0f ? Float.floatToIntBits(width) : 0);
        result = 31 * result + (zIndex != +0.0f ? Float.floatToIntBits(zIndex) : 0);
        result = 31 * result + (isGeodesic ? 1 : 0);
        result = 31 * result + (isVisible ? 1 : 0);
        return result;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeList(this.points);
        out.writeInt(this.color);
        out.writeFloat(this.width);
        out.writeFloat(this.zIndex);
        out.writeByte((byte) (this.isGeodesic ? 1 : 0));
        out.writeByte((byte) (this.isVisible ? 1 : 0));
    }
}
