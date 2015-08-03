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

package org.onepf.opfmaps.model;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import org.onepf.opfmaps.OPFMapHelper;
import org.onepf.opfmaps.delegate.model.CircleOptionsDelegate;

/**
 * @author Roman Savin
 * @since 29.07.2015
 */
public final class OPFCircleOptions implements CircleOptionsDelegate {

    public static final Creator<OPFCircleOptions> CREATOR = new Creator<OPFCircleOptions>() {
        @Override
        public OPFCircleOptions createFromParcel(final Parcel source) {
            return new OPFCircleOptions(source);
        }

        @Override
        public OPFCircleOptions[] newArray(final int size) {
            return new OPFCircleOptions[size];
        }
    };

    @NonNull
    private final CircleOptionsDelegate delegate;

    public OPFCircleOptions() {
        delegate = OPFMapHelper.getInstance().getDelegatesFactory().createCircleOptionsDelegate();
    }

    @SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes")
    private OPFCircleOptions(@NonNull final Parcel parcel) {
        try {
            delegate = parcel.readParcelable(Class.forName(parcel.readString()).getClassLoader());
        } catch (ClassNotFoundException e) {
            //todo maybe fix
            throw new RuntimeException(e);
        }
    }

    @NonNull
    @Override
    public OPFCircleOptions center(@NonNull final OPFLatLng center) {
        delegate.center(center);
        return this;
    }

    @NonNull
    @Override
    public OPFCircleOptions fillColor(final int color) {
        delegate.fillColor(color);
        return this;
    }

    @Nullable
    @Override
    public OPFLatLng getCenter() {
        return delegate.getCenter();
    }

    @Override
    public int getFillColor() {
        return delegate.getFillColor();
    }

    @Override
    public double getRadius() {
        return delegate.getRadius();
    }

    @Override
    public int getStrokeColor() {
        return delegate.getStrokeColor();
    }

    @Override
    public float getStrokeWidth() {
        return delegate.getStrokeWidth();
    }

    @Override
    public float getZIndex() {
        return delegate.getZIndex();
    }

    @Override
    public boolean isVisible() {
        return delegate.isVisible();
    }

    @NonNull
    @Override
    public OPFCircleOptions radius(final double radius) {
        delegate.radius(radius);
        return this;
    }

    @NonNull
    @Override
    public OPFCircleOptions strokeColor(final int color) {
        delegate.strokeColor(color);
        return this;
    }

    @NonNull
    @Override
    public OPFCircleOptions strokeWidth(final float width) {
        delegate.strokeWidth(width);
        return this;
    }

    @NonNull
    @Override
    public OPFCircleOptions visible(final boolean visible) {
        delegate.visible(visible);
        return this;
    }

    @NonNull
    @Override
    public CircleOptionsDelegate zIndex(final float zIndex) {
        delegate.zIndex(zIndex);
        return this;
    }

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OPFCircleOptions)) return false;

        return delegate.equals(((OPFCircleOptions) other).delegate);
    }
    //CHECKSTYLE:ON

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public String toString() {
        return delegate.toString();
    }

    @Override
    public int describeContents() {
        return delegate.describeContents();
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(delegate.getClass().getCanonicalName());
        dest.writeParcelable(delegate, flags);
    }
}
