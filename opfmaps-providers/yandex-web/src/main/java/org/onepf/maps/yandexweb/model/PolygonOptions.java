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
import org.onepf.opfutils.OPFLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Roman Savin
 * @since 17.08.2015
 */
public final class PolygonOptions implements Parcelable {

    public static final Creator<PolygonOptions> CREATOR = new Creator<PolygonOptions>() {
        @Override
        public PolygonOptions createFromParcel(final Parcel source) {
            return new PolygonOptions(source);
        }

        @Override
        public PolygonOptions[] newArray(final int size) {
            return new PolygonOptions[size];
        }
    };

    private static final int DEFAULT_STROKE_COLOR = -16777216;

    @NonNull
    private final List<List<LatLng>> holes = new ArrayList<>();
    @NonNull
    private final List<LatLng> points = new ArrayList<>();

    private int fillColor;
    private int strokeColor = DEFAULT_STROKE_COLOR;
    private float strokeWidth = 10.0F;
    private float zIndex;
    private boolean isVisible = true;

    public PolygonOptions() {
        //nothing
    }

    private PolygonOptions(Parcel source) {
        source.readList(this.holes, LatLng.class.getClassLoader());
        source.readList(this.points, LatLng.class.getClassLoader());
        this.fillColor = source.readInt();
        this.strokeColor = source.readInt();
        this.strokeWidth = source.readFloat();
        this.zIndex = source.readFloat();
        this.isVisible = source.readByte() != 0;
    }

    @NonNull
    public PolygonOptions add(@NonNull final LatLng point) {
        this.points.add(point);
        return this;
    }

    @NonNull
    public PolygonOptions add(@NonNull final LatLng... points) {
        this.addAll(Arrays.asList(points));
        return this;
    }

    @NonNull
    public PolygonOptions addAll(@NonNull final Iterable<LatLng> points) {
        for (LatLng point : points) {
            this.points.add(point);
        }

        return this;
    }

    @NonNull
    public PolygonOptions addHole(@NonNull final Iterable<LatLng> points) {
        final List<LatLng> hole = new ArrayList<>();

        for (LatLng point : points) {
            hole.add(point);
        }

        this.holes.add(hole);
        return this;
    }

    @NonNull
    public PolygonOptions fillColor(final int color) {
        this.fillColor = color;
        return this;
    }

    @NonNull
    public PolygonOptions geodesic(final boolean geodesic) {
        OPFLog.logStubCall(geodesic);
        return this;
    }

    public int getFillColor() {
        return fillColor;
    }

    @NonNull
    public List<List<LatLng>> getHoles() {
        return holes;
    }

    @NonNull
    public List<LatLng> getPoints() {
        return points;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public float getZIndex() {
        return zIndex;
    }

    public boolean isGeodesic() {
        return false;
    }

    public boolean isVisible() {
        return isVisible;
    }

    @NonNull
    public PolygonOptions strokeColor(final int color) {
        this.strokeColor = color;
        return this;
    }

    @NonNull
    public PolygonOptions strokeWidth(final float width) {
        this.strokeWidth = width;
        return this;
    }

    @NonNull
    public PolygonOptions visible(final boolean visible) {
        this.isVisible = visible;
        return this;
    }

    @NonNull
    public PolygonOptions zIndex(final float zIndex) {
        this.zIndex = zIndex;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof PolygonOptions)) {
            return false;
        } else {
            final PolygonOptions other = (PolygonOptions) obj;
            return this.fillColor == other.fillColor
                    && this.strokeColor == other.strokeColor
                    && this.strokeWidth == other.strokeWidth
                    && this.zIndex == other.zIndex
                    && this.isVisible == other.isVisible
                    && this.holes.equals(other.holes)
                    && this.points.equals(other.points);
        }
    }

    @SuppressWarnings("PMD.NPathComplexity")
    @Override
    public int hashCode() {
        int result = holes.hashCode();
        result = 31 * result + points.hashCode();
        result = 31 * result + fillColor;
        result = 31 * result + strokeColor;
        result = 31 * result + (strokeWidth != +0.0f ? Float.floatToIntBits(strokeWidth) : 0);
        result = 31 * result + (zIndex != +0.0f ? Float.floatToIntBits(zIndex) : 0);
        result = 31 * result + (isVisible ? 1 : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.fillColor);
        out.writeList(this.holes);
        out.writeList(this.points);
        out.writeInt(this.strokeColor);
        out.writeFloat(this.strokeWidth);
        out.writeFloat(this.zIndex);
        out.writeByte((byte) (this.isVisible ? 1 : 0));
    }
}
