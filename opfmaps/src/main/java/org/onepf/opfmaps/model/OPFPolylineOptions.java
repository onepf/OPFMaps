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
import org.onepf.opfmaps.delegate.model.PolylineOptionsDelegate;

import java.util.List;

/**
 * Defines options for a polyline.
 *
 * @author Roman Savin
 * @since 30.07.2015
 */
public final class OPFPolylineOptions implements PolylineOptionsDelegate {

    public static final Creator<OPFPolylineOptions> CREATOR = new Creator<OPFPolylineOptions>() {
        @Override
        public OPFPolylineOptions createFromParcel(final Parcel source) {
            return new OPFPolylineOptions(source);
        }

        @Override
        public OPFPolylineOptions[] newArray(final int size) {
            return new OPFPolylineOptions[size];
        }
    };

    @NonNull
    private final PolylineOptionsDelegate delegate;

    /**
     * Creates polyline options.
     */
    public OPFPolylineOptions() {
        this.delegate = OPFMapHelper.getInstance().getDelegatesFactory().createPolylineOptionsDelegate();
    }

    @SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes")
    private OPFPolylineOptions(@NonNull final Parcel parcel) {
        try {
            this.delegate = parcel.readParcelable(Class.forName(parcel.readString()).getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a vertex to the end of the polyline being built.
     *
     * @param point The added point.
     * @return This {@link OPFPolylineOptions} object.
     */
    @NonNull
    @Override
    public OPFPolylineOptions add(@NonNull final OPFLatLng point) {
        delegate.add(point);
        return this;
    }

    /**
     * Adds vertices to the end of the polyline being built.
     *
     * @param points The added points.
     * @return This {@link OPFPolylineOptions} object.
     */
    @NonNull
    @Override
    public OPFPolylineOptions add(@NonNull final OPFLatLng... points) {
        delegate.add(points);
        return this;
    }

    /**
     * Adds vertices to the end of the polyline being built.
     *
     * @param points The added points.
     * @return This {@link OPFPolylineOptions} object.
     */
    @NonNull
    @Override
    public OPFPolylineOptions addAll(@NonNull final Iterable<OPFLatLng> points) {
        delegate.addAll(points);
        return this;
    }

    /**
     * Sets the color of the polyline as a 32-bit ARGB color. The default color is black ({@code 0xff000000}).
     *
     * @param color The polyline's color.
     * @return This {@link OPFPolylineOptions} object.
     */
    @NonNull
    @Override
    public OPFPolylineOptions color(final int color) {
        delegate.color(color);
        return this;
    }

    /**
     * Specifies whether to draw each segment of this polyline as a geodesic. The default value is {@code false}.
     *
     * @param geodesic The geodesic value.
     * @return This {@link OPFPolylineOptions} object.
     */
    @NonNull
    @Override
    public OPFPolylineOptions geodesic(final boolean geodesic) {
        delegate.geodesic(geodesic);
        return this;
    }

    /**
     * Gets the color set for this options object.
     *
     * @return The color of the polyline in ARGB format.
     */
    @Override
    public int getColor() {
        return delegate.getColor();
    }

    /**
     * Gets the points set for this options object.
     *
     * @return The list of {@link OPFLatLng} objects specifying the vertices of the polyline.
     */
    @NonNull
    @Override
    public List<OPFLatLng> getPoints() {
        return delegate.getPoints();
    }

    /**
     * Gets the width set for this options object.
     *
     * @return The width of the polyline in screen pixels.
     */
    @Override
    public float getWidth() {
        return delegate.getWidth();
    }

    /**
     * Gets the zIndex set for this options object.
     *
     * @return The zIndex of the polyline.
     */
    @Override
    public float getZIndex() {
        return delegate.getZIndex();
    }

    /**
     * Gets the geodesic setting for this options object.
     *
     * @return {@code true} if the polyline segments should be geodesics, {@code false} otherwise.
     */
    @Override
    public boolean isGeodesic() {
        return delegate.isGeodesic();
    }

    /**
     * Gets the visibility setting for this options object.
     *
     * @return {@code true} if the polyline is to be visible, {@code false} otherwise.
     */
    @Override
    public boolean isVisible() {
        return delegate.isVisible();
    }

    /**
     * Specifies the visibility for the polyline. The default visibility is {@code true}.
     *
     * @param visible {@code false} to make this polyline invisible, {@code true} otherwise.
     * @return This {@link OPFPolylineOptions} object.
     */
    @NonNull
    @Override
    public OPFPolylineOptions visible(final boolean visible) {
        delegate.visible(visible);
        return this;
    }

    /**
     * Sets the width of the polyline in screen pixels. The default is 10.
     *
     * @param width The width of the polyline.
     * @return This {@link OPFPolylineOptions} object.
     */
    @NonNull
    @Override
    public OPFPolylineOptions width(final float width) {
        delegate.width(width);
        return this;
    }

    /**
     * Specifies the polyline's zIndex, i.e., the order in which it will be drawn.
     *
     * @param zIndex The zIndex value.
     * @return This {@link OPFPolylineOptions} object.
     */
    @NonNull
    @Override
    public OPFPolylineOptions zIndex(final float zIndex) {
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

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OPFPolylineOptions
                && delegate.equals(((OPFPolylineOptions) other).delegate));
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public String toString() {
        return delegate.toString();
    }
}
