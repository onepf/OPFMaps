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

package org.onepf.maps.osmdroid.delegate.model;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import org.onepf.opfmaps.delegate.model.CircleOptionsDelegate;
import org.onepf.opfmaps.model.OPFLatLng;

/**
 * @author Roman Savin
 * @since 03.08.2015
 */
public final class OsmdroidCircleOptionsDelegate implements CircleOptionsDelegate {

    public static final Creator<OsmdroidCircleOptionsDelegate> CREATOR = new Creator<OsmdroidCircleOptionsDelegate>() {
        @Override
        public OsmdroidCircleOptionsDelegate createFromParcel(final Parcel source) {
            return new OsmdroidCircleOptionsDelegate(source);
        }

        @Override
        public OsmdroidCircleOptionsDelegate[] newArray(final int size) {
            return new OsmdroidCircleOptionsDelegate[size];
        }
    };

    @NonNull
    private final CircleOptions circleOptions;

    public OsmdroidCircleOptionsDelegate() {
        this.circleOptions = new CircleOptions();
    }

    private OsmdroidCircleOptionsDelegate(@NonNull final Parcel parcel) {
        this.circleOptions = parcel.readParcelable(CircleOptions.class.getClassLoader());
    }

    @NonNull
    @Override
    public OsmdroidCircleOptionsDelegate center(@NonNull final OPFLatLng center) {
        circleOptions.center(new LatLng(center.getLat(), center.getLng()));
        return this;
    }

    @NonNull
    @Override
    public OsmdroidCircleOptionsDelegate fillColor(final int color) {
        circleOptions.fillColor(color);
        return this;
    }

    @Nullable
    @Override
    public OPFLatLng getCenter() {
        final LatLng center = circleOptions.getCenter();
        if (center != null) {
            return new OPFLatLng(new OsmdroidLatLngDelegate(center));
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
    public OsmdroidCircleOptionsDelegate radius(final double radius) {
        circleOptions.radius(radius);
        return this;
    }

    @NonNull
    @Override
    public OsmdroidCircleOptionsDelegate strokeColor(final int color) {
        circleOptions.strokeColor(color);
        return this;
    }

    @NonNull
    @Override
    public OsmdroidCircleOptionsDelegate strokeWidth(final float width) {
        circleOptions.strokeWidth(width);
        return this;
    }

    @NonNull
    @Override
    public OsmdroidCircleOptionsDelegate visible(final boolean visible) {
        circleOptions.visible(visible);
        return this;
    }

    @NonNull
    @Override
    public OsmdroidCircleOptionsDelegate zIndex(final float zIndex) {
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
        if (!(other instanceof OsmdroidCircleOptionsDelegate)) return false;

        return circleOptions.equals(((OsmdroidCircleOptionsDelegate) other).circleOptions);
    }
    //CHECKSTYLE:ON

    @Override
    public int hashCode() {
        return circleOptions.hashCode();
    }
}
