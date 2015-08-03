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
import org.onepf.opfmaps.OPFMapHelper;
import org.onepf.opfmaps.delegate.model.PolygonOptionsDelegate;

import java.util.List;

/**
 * @author Roman Savin
 * @since 30.07.2015
 */
public final class OPFPolygonOptions implements PolygonOptionsDelegate {

    public static final Creator<OPFPolygonOptions> CREATOR = new Creator<OPFPolygonOptions>() {
        @Override
        public OPFPolygonOptions createFromParcel(final Parcel source) {
            return new OPFPolygonOptions(source);
        }

        @Override
        public OPFPolygonOptions[] newArray(final int size) {
            return new OPFPolygonOptions[size];
        }
    };

    @NonNull
    private final PolygonOptionsDelegate delegate;

    public OPFPolygonOptions() {
        this.delegate = OPFMapHelper.getInstance().getDelegatesFactory().createPolygonOptionsDelegate();
    }

    @SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes")
    private OPFPolygonOptions(@NonNull final Parcel parcel) {
        try {
            this.delegate = parcel.readParcelable(Class.forName(parcel.readString()).getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    @Override
    public OPFPolygonOptions add(@NonNull final OPFLatLng point) {
        delegate.add(point);
        return this;
    }

    @NonNull
    @Override
    public OPFPolygonOptions add(@NonNull final OPFLatLng... points) {
        delegate.add(points);
        return this;
    }

    @NonNull
    @Override
    public OPFPolygonOptions addAll(@NonNull final Iterable<OPFLatLng> points) {
        delegate.addAll(points);
        return this;
    }

    @NonNull
    @Override
    public OPFPolygonOptions addHole(@NonNull final Iterable<OPFLatLng> points) {
        delegate.addHole(points);
        return this;
    }

    @NonNull
    @Override
    public OPFPolygonOptions fillColor(final int color) {
        delegate.fillColor(color);
        return this;
    }

    @NonNull
    @Override
    public OPFPolygonOptions geodesic(final boolean geodesic) {
        delegate.geodesic(geodesic);
        return this;
    }

    @Override
    public int getFillColor() {
        return delegate.getFillColor();
    }

    @NonNull
    @Override
    public List<List<OPFLatLng>> getHoles() {
        return delegate.getHoles();
    }

    @NonNull
    @Override
    public List<OPFLatLng> getPoints() {
        return delegate.getPoints();
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
    public boolean isGeodesic() {
        return delegate.isGeodesic();
    }

    @Override
    public boolean isVisible() {
        return delegate.isVisible();
    }

    @NonNull
    @Override
    public OPFPolygonOptions strokeColor(final int color) {
        delegate.strokeColor(color);
        return this;
    }

    @NonNull
    @Override
    public OPFPolygonOptions strokeWidth(final float width) {
        delegate.strokeWidth(width);
        return this;
    }

    @NonNull
    @Override
    public OPFPolygonOptions visible(final boolean visible) {
        delegate.visible(visible);
        return this;
    }

    @NonNull
    @Override
    public OPFPolygonOptions zIndex(final float zIndex) {
        delegate.zIndex(zIndex);
        return this;
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

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OPFPolygonOptions)) return false;

        return delegate.equals(((OPFPolygonOptions) other).delegate);
    }
    //CHECKSTYLE:ON

    @Override
    public String toString() {
        return delegate.toString();
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }
}
