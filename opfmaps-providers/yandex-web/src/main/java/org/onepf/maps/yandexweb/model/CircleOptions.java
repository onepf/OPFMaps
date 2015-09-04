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
import android.support.annotation.Nullable;

import org.onepf.maps.yandexweb.utils.CompareUtils;
import org.onepf.opfmaps.model.OPFLatLng;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class CircleOptions implements Parcelable {

    public static final Creator<CircleOptions> CREATOR = new Creator<CircleOptions>() {
        @Override
        public CircleOptions createFromParcel(final Parcel source) {
            return new CircleOptions(source);
        }

        @Override
        public CircleOptions[] newArray(final int size) {
            return new CircleOptions[size];
        }
    };

    private static final int DEFAULT_STROKE_COLOR = -16777216;

    @Nullable
    private LatLng center;
    private int fillColor;
    private double radius;
    private int strokeColor = DEFAULT_STROKE_COLOR;
    private float strokeWidth = 10.0F;
    private float zIndex;
    private boolean isVisible = true;

    public CircleOptions() {
        //nothing
    }

    private CircleOptions(@NonNull final Parcel parcel) {
        this.center = parcel.readParcelable(OPFLatLng.class.getClassLoader());
        this.fillColor = parcel.readInt();
        this.radius = parcel.readDouble();
        this.strokeColor = parcel.readInt();
        this.strokeWidth = parcel.readFloat();
        this.zIndex = parcel.readFloat();
        this.isVisible = parcel.readByte() != 0;
    }

    @NonNull
    public CircleOptions center(@NonNull final LatLng center) {
        this.center = center;
        return this;
    }

    @NonNull
    public CircleOptions fillColor(final int color) {
        this.fillColor = color;
        return this;
    }

    @Nullable
    public LatLng getCenter() {
        return center;
    }

    public int getFillColor() {
        return fillColor;
    }

    public double getRadius() {
        return radius;
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

    public boolean isVisible() {
        return isVisible;
    }

    @NonNull
    public CircleOptions radius(final double radius) {
        this.radius = radius;
        return this;
    }

    @NonNull
    public CircleOptions strokeColor(final int color) {
        this.strokeColor = color;
        return this;
    }

    @NonNull
    public CircleOptions strokeWidth(final float width) {
        this.strokeWidth = width;
        return this;
    }

    @NonNull
    public CircleOptions visible(final boolean visible) {
        this.isVisible = visible;
        return this;
    }

    @NonNull
    public CircleOptions zIndex(final float zIndex) {
        this.zIndex = zIndex;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(center, flags);
        dest.writeInt(fillColor);
        dest.writeDouble(radius);
        dest.writeInt(strokeColor);
        dest.writeFloat(strokeWidth);
        dest.writeFloat(zIndex);
        dest.writeByte((byte) (isVisible ? 1 : 0));
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof CircleOptions)) {
            return false;
        } else {
            final CircleOptions other = (CircleOptions) obj;

            return CompareUtils.isEquals(this.center, other.center)
                    && this.fillColor == other.fillColor
                    && this.radius == other.radius
                    && this.strokeColor == other.strokeColor
                    && this.strokeWidth == other.strokeWidth
                    && this.zIndex == other.zIndex
                    && this.isVisible == other.isVisible;
        }
    }

    @SuppressWarnings("PMD.NPathComplexity")
    @Override
    public int hashCode() {
        int result;
        final long temp;
        result = center != null ? center.hashCode() : 0;
        result = 31 * result + fillColor;
        temp = Double.doubleToLongBits(radius);
        //CHECKSTYLE:OFF
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        //CHECKSTYLE:ON
        result = 31 * result + strokeColor;
        result = 31 * result + (strokeWidth != +0.0f ? Float.floatToIntBits(strokeWidth) : 0);
        result = 31 * result + (zIndex != +0.0f ? Float.floatToIntBits(zIndex) : 0);
        result = 31 * result + (isVisible ? 1 : 0);
        return result;
    }
}
