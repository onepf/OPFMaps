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
 * Defines options for a polygon.
 *
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

    /**
     * Creates polygon options.
     */
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

    /**
     * Adds a vertex to the outline of the polygon being built.
     *
     * @param point The added point.
     * @return This {@link OPFPolygonOptions} object.
     */
    @NonNull
    @Override
    public OPFPolygonOptions add(@NonNull final OPFLatLng point) {
        delegate.add(point);
        return this;
    }

    /**
     * Adds vertices to the outline of the polygon being built.
     *
     * @param points The added points.
     * @return This {@link OPFPolygonOptions} object.
     */
    @NonNull
    @Override
    public OPFPolygonOptions add(@NonNull final OPFLatLng... points) {
        delegate.add(points);
        return this;
    }

    /**
     * Adds vertices to the outline of the polygon being built.
     *
     * @param points The added points.
     * @return This {@link OPFPolygonOptions} object.
     */
    @NonNull
    @Override
    public OPFPolygonOptions addAll(@NonNull final Iterable<OPFLatLng> points) {
        delegate.addAll(points);
        return this;
    }

    /**
     * Adds a hole to the polygon being built.
     *
     * @param points The added hole.
     * @return This {@link OPFPolygonOptions} object.
     */
    @NonNull
    @Override
    public OPFPolygonOptions addHole(@NonNull final Iterable<OPFLatLng> points) {
        delegate.addHole(points);
        return this;
    }

    /**
     * Specifies the polygon's fill color, as 32-bit ARGB. The default color is black ({@code 0xff000000}).
     *
     * @param color The polygon's fill color.
     * @return This {@link OPFPolygonOptions} object.
     */
    @NonNull
    @Override
    public OPFPolygonOptions fillColor(final int color) {
        delegate.fillColor(color);
        return this;
    }

    /**
     * Specifies whether to draw each segment of this polygon as a geodesic. The default setting is {@code false}.
     *
     * @param geodesic The geodesic value.
     * @return This {@link OPFPolygonOptions} object.
     */
    @NonNull
    @Override
    public OPFPolygonOptions geodesic(final boolean geodesic) {
        delegate.geodesic(geodesic);
        return this;
    }

    /**
     * Gets the fill color set for this Options object.
     *
     * @return The fill color of the polygon in screen pixels.
     */
    @Override
    public int getFillColor() {
        return delegate.getFillColor();
    }

    /**
     * Gets the holes set for this options object.
     *
     * @return The list of lists specifying the holes of the polygon.
     */
    @NonNull
    @Override
    public List<List<OPFLatLng>> getHoles() {
        return delegate.getHoles();
    }

    /**
     * Gets the outline set for this options object.
     *
     * @return the list of {@link OPFLatLng} objects specifying the vertices of the outline of the polygon.
     */
    @NonNull
    @Override
    public List<OPFLatLng> getPoints() {
        return delegate.getPoints();
    }

    /**
     * Gets the stroke color set for this options object.
     *
     * @return The stroke color of the polygon in screen pixels.
     */
    @Override
    public int getStrokeColor() {
        return delegate.getStrokeColor();
    }

    /**
     * Gets the stroke width set for this options object.
     *
     * @return The stroke width of the polygon in screen pixels.
     */
    @Override
    public float getStrokeWidth() {
        return delegate.getStrokeWidth();
    }

    /**
     * Gets the zIndex set for this options object.
     *
     * @return The zIndex of the polygon.
     */
    @Override
    public float getZIndex() {
        return delegate.getZIndex();
    }

    /**
     * Gets the geodesic setting for this options object.
     *
     * @return {@code true} if the polygon segments should be geodesics, {@code false} otherwise.
     */
    @Override
    public boolean isGeodesic() {
        return delegate.isGeodesic();
    }

    /**
     * Gets the visibility setting for this options object.
     *
     * @return {@code true} if the polygon is to be visible, {@code false} otherwise.
     */
    @Override
    public boolean isVisible() {
        return delegate.isVisible();
    }

    /**
     * Specifies the polygon's stroke color, as 32-bit ARGB. The default color is black ({@code 0xff000000}).
     *
     * @param color The stroke color of the polygon.
     * @return This {@link OPFPolygonOptions} object.
     */
    @NonNull
    @Override
    public OPFPolygonOptions strokeColor(final int color) {
        delegate.strokeColor(color);
        return this;
    }

    /**
     * Specifies the polygon's stroke width, in display pixels. The default width is 10.
     *
     * @param width The stroke width of the polygon.
     * @return This {@link OPFPolygonOptions} object.
     */
    @NonNull
    @Override
    public OPFPolygonOptions strokeWidth(final float width) {
        delegate.strokeWidth(width);
        return this;
    }

    /**
     * Specifies the visibility for the polygon. The default visibility is {@code true}.
     *
     * @param visible {@code false} to make this polygon invisible, {@code true} otherwise.
     * @return This {@link OPFPolygonOptions} object.
     */
    @NonNull
    @Override
    public OPFPolygonOptions visible(final boolean visible) {
        delegate.visible(visible);
        return this;
    }

    /**
     * Specifies the polygon's zIndex, i.e., the order in which it will be drawn.
     *
     * @param zIndex The zIndex value.
     * @return This {@link OPFPolygonOptions} object.
     */
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

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OPFPolygonOptions
                && delegate.equals(((OPFPolygonOptions) other).delegate));
    }

    @Override
    public String toString() {
        return delegate.toString();
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }
}
