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
import android.support.annotation.Nullable;

import org.onepf.maps.yandexweb.model.CircleOptions;
import org.onepf.maps.yandexweb.model.LatLng;
import org.onepf.opfmaps.delegate.model.CircleOptionsDelegate;
import org.onepf.opfmaps.model.OPFLatLng;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class YaWebCircleOptionsDelegate implements CircleOptionsDelegate {

    public static final Creator<YaWebCircleOptionsDelegate> CREATOR = new Creator<YaWebCircleOptionsDelegate>() {
        @Override
        public YaWebCircleOptionsDelegate createFromParcel(final Parcel source) {
            return new YaWebCircleOptionsDelegate(source);
        }

        @Override
        public YaWebCircleOptionsDelegate[] newArray(final int size) {
            return new YaWebCircleOptionsDelegate[size];
        }
    };

    @NonNull
    private final CircleOptions circleOptions;

    public YaWebCircleOptionsDelegate() {
        this.circleOptions = new CircleOptions();
    }

    private YaWebCircleOptionsDelegate(@NonNull final Parcel parcel) {
        this.circleOptions = parcel.readParcelable(CircleOptions.class.getClassLoader());
    }

    @NonNull
    @Override
    public YaWebCircleOptionsDelegate center(@NonNull final OPFLatLng center) {
        circleOptions.center(new LatLng(center.getLat(), center.getLng()));
        return this;
    }

    @NonNull
    @Override
    public YaWebCircleOptionsDelegate fillColor(final int color) {
        circleOptions.fillColor(color);
        return this;
    }

    @Nullable
    @Override
    public OPFLatLng getCenter() {
        final LatLng center = circleOptions.getCenter();
        if (center != null) {
            return new OPFLatLng(new YaWebLatLngDelegate(center));
        }
        return null;
    }

    @Override
    public int getFillColor() {
        return circleOptions.getFillColor();
    }

    @Override
    public double getRadius() {
        return circleOptions.getRadius();
    }

    @Override
    public int getStrokeColor() {
        return circleOptions.getStrokeColor();
    }

    @Override
    public float getStrokeWidth() {
        return circleOptions.getStrokeWidth();
    }

    @Override
    public float getZIndex() {
        return circleOptions.getZIndex();
    }

    @Override
    public boolean isVisible() {
        return circleOptions.isVisible();
    }

    @NonNull
    @Override
    public YaWebCircleOptionsDelegate radius(final double radius) {
        circleOptions.radius(radius);
        return this;
    }

    @NonNull
    @Override
    public YaWebCircleOptionsDelegate strokeColor(final int color) {
        circleOptions.strokeColor(color);
        return this;
    }

    @NonNull
    @Override
    public YaWebCircleOptionsDelegate strokeWidth(final float width) {
        circleOptions.strokeWidth(width);
        return this;
    }

    @NonNull
    @Override
    public YaWebCircleOptionsDelegate visible(final boolean visible) {
        circleOptions.visible(visible);
        return this;
    }

    @NonNull
    @Override
    public YaWebCircleOptionsDelegate zIndex(final float zIndex) {
        circleOptions.zIndex(zIndex);
        return this;
    }

    @Override
    public int describeContents() {
        return circleOptions.describeContents();
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(circleOptions, flags);
    }

    @Override
    public String toString() {
        return circleOptions.toString();
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof YaWebCircleOptionsDelegate
                && circleOptions.equals(((YaWebCircleOptionsDelegate) other).circleOptions));

    }

    @Override
    public int hashCode() {
        return circleOptions.hashCode();
    }
}
