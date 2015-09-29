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
 * Defines options for a circle.
 *
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

    /**
     * Creates a new set of circle options.
     */
    public OPFCircleOptions() {
        delegate = OPFMapHelper.getInstance().getDelegatesFactory().createCircleOptionsDelegate();
    }

    @SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes")
    private OPFCircleOptions(@NonNull final Parcel parcel) {
        try {
            delegate = parcel.readParcelable(Class.forName(parcel.readString()).getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the center using a {@link OPFLatLng}. This method is mandatory because there is no default center.
     *
     * @param center The geographic center as a {@link OPFLatLng}.
     * @return This {@link OPFCircleOptions} object
     */
    @NonNull
    @Override
    public OPFCircleOptions center(@NonNull final OPFLatLng center) {
        delegate.center(center);
        return this;
    }

    /**
     * Sets the fill color.
     * The fill color is the color inside the circle, in the integer format specified by {@link android.graphics.Color}.
     * By default the fill color is transparent {@code (0x00000000)}.
     *
     * @param color The color in the {@link android.graphics.Color} format.
     * @return This {@link OPFCircleOptions} object
     */
    @NonNull
    @Override
    public OPFCircleOptions fillColor(final int color) {
        delegate.fillColor(color);
        return this;
    }

    /**
     * Returns the center as a {@link OPFLatLng}.
     *
     * @return The geographic center as a {@link OPFLatLng}.
     */
    @Nullable
    @Override
    public OPFLatLng getCenter() {
        return delegate.getCenter();
    }

    /**
     * Returns the fill color.
     *
     * @return The color in the {@link android.graphics.Color} format.
     */
    @Override
    public int getFillColor() {
        return delegate.getFillColor();
    }

    /**
     * Returns the circle's radius, in meters.
     *
     * @return The radius in meters.
     */
    @Override
    public double getRadius() {
        return delegate.getRadius();
    }

    /**
     * Returns the stroke color.
     *
     * @return The color in the {@link android.graphics.Color} format.
     */
    @Override
    public int getStrokeColor() {
        return delegate.getStrokeColor();
    }

    /**
     * Returns the stroke width.
     *
     * @return The width in screen pixels.
     */
    @Override
    public float getStrokeWidth() {
        return delegate.getStrokeWidth();
    }

    /**
     * Returns the zIndex.
     *
     * @return The zIndex value.
     */
    @Override
    public float getZIndex() {
        return delegate.getZIndex();
    }

    /**
     * Checks whether the circle is visible.
     *
     * @return {@code true} if the circle is visible; {@code false} if it is invisible.
     */
    @Override
    public boolean isVisible() {
        return delegate.isVisible();
    }

    /**
     * Sets the radius in meters. The radius must be zero or greater. The default radius is zero.
     *
     * @param radius The radius in meters.
     * @return This {@link OPFCircleOptions} object.
     */
    @NonNull
    @Override
    public OPFCircleOptions radius(final double radius) {
        delegate.radius(radius);
        return this;
    }

    /**
     * Sets the stroke color.
     * The stroke color is the color of this circle's outline, in the integer format specified by {@link android.graphics.Color}.
     *
     * @param color The color in the {@link android.graphics.Color} format
     * @return This {@link OPFCircleOptions} object.
     */
    @NonNull
    @Override
    public OPFCircleOptions strokeColor(final int color) {
        delegate.strokeColor(color);
        return this;
    }

    /**
     * Sets the stroke width. The stroke width is the width (in screen pixels) of the circle's outline.
     * It must be zero or greater. If it is zero then no outline is drawn. The default width is 10 pixels.
     *
     * @param width The width in screen pixels
     * @return This {@link OPFCircleOptions} object.
     */
    @NonNull
    @Override
    public OPFCircleOptions strokeWidth(final float width) {
        delegate.strokeWidth(width);
        return this;
    }

    /**
     * Sets the visibility. If this circle is not visible then it is not drawn, but all other state is preserved.
     *
     * @param visible {@code false} to make this circle invisible, {@code true} otherwise.
     * @return This {@link OPFCircleOptions} object.
     */
    @NonNull
    @Override
    public OPFCircleOptions visible(final boolean visible) {
        delegate.visible(visible);
        return this;
    }

    /**
     * Sets the zIndex. Overlays (such as circles) with higher zIndices are drawn above those with lower indices.
     * By default the zIndex is 0.0.
     *
     * @param zIndex zIndex value
     * @return This {@link OPFCircleOptions} object.
     */
    @NonNull
    @Override
    public OPFCircleOptions zIndex(final float zIndex) {
        delegate.zIndex(zIndex);
        return this;
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OPFCircleOptions
                && delegate.equals(((OPFCircleOptions) other).delegate));
    }

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
