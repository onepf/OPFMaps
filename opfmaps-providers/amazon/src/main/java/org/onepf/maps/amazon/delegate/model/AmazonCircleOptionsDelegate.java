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
import android.support.annotation.Nullable;
import com.amazon.geo.mapsv2.model.CircleOptions;
import com.amazon.geo.mapsv2.model.LatLng;
import org.onepf.opfmaps.delegate.model.CircleOptionsDelegate;
import org.onepf.opfmaps.model.OPFLatLng;

/**
 * @author Roman Savin
 * @since 03.08.2015
 */
public final class AmazonCircleOptionsDelegate implements CircleOptionsDelegate {

    public static final Creator<AmazonCircleOptionsDelegate> CREATOR = new Creator<AmazonCircleOptionsDelegate>() {
        @Override
        public AmazonCircleOptionsDelegate createFromParcel(final Parcel source) {
            return new AmazonCircleOptionsDelegate(source);
        }

        @Override
        public AmazonCircleOptionsDelegate[] newArray(final int size) {
            return new AmazonCircleOptionsDelegate[size];
        }
    };

    @NonNull
    private final CircleOptions circleOptions;

    public AmazonCircleOptionsDelegate() {
        this.circleOptions = new CircleOptions();
    }

    private AmazonCircleOptionsDelegate(@NonNull final Parcel parcel) {
        this.circleOptions = parcel.readParcelable(CircleOptions.class.getClassLoader());
    }

    @NonNull
    @Override
    public AmazonCircleOptionsDelegate center(@NonNull final OPFLatLng center) {
        circleOptions.center(new LatLng(center.getLat(), center.getLng()));
        return this;
    }

    @NonNull
    @Override
    public AmazonCircleOptionsDelegate fillColor(final int color) {
        circleOptions.fillColor(color);
        return this;
    }

    @Nullable
    @Override
    public OPFLatLng getCenter() {
        return new OPFLatLng(new AmazonLatLngDelegate(circleOptions.getCenter()));
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
    public AmazonCircleOptionsDelegate radius(final double radius) {
        circleOptions.radius(radius);
        return this;
    }

    @NonNull
    @Override
    public AmazonCircleOptionsDelegate strokeColor(final int color) {
        circleOptions.strokeColor(color);
        return this;
    }

    @NonNull
    @Override
    public AmazonCircleOptionsDelegate strokeWidth(final float width) {
        circleOptions.strokeWidth(width);
        return this;
    }

    @NonNull
    @Override
    public AmazonCircleOptionsDelegate visible(final boolean visible) {
        circleOptions.visible(visible);
        return this;
    }

    @NonNull
    @Override
    public AmazonCircleOptionsDelegate zIndex(final float zIndex) {
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

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof AmazonCircleOptionsDelegate)) return false;

        return circleOptions.equals(((AmazonCircleOptionsDelegate) other).circleOptions);
    }
    //CHECKSTYLE:ON

    @Override
    public int hashCode() {
        return circleOptions.hashCode();
    }
}
