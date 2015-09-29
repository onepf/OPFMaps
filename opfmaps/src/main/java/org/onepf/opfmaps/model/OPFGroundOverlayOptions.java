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
import org.onepf.opfmaps.delegate.model.GroundOverlayOptionsDelegate;

/**
 * Defines options for a ground overlay.
 *
 * @author Roman Savin
 * @since 29.07.2015
 */
public final class OPFGroundOverlayOptions implements GroundOverlayOptionsDelegate {

    public static final Creator<OPFGroundOverlayOptions> CREATOR = new Creator<OPFGroundOverlayOptions>() {
        @Override
        public OPFGroundOverlayOptions createFromParcel(final Parcel source) {
            return new OPFGroundOverlayOptions(source);
        }

        @Override
        public OPFGroundOverlayOptions[] newArray(final int size) {
            return new OPFGroundOverlayOptions[size];
        }
    };

    @NonNull
    private final GroundOverlayOptionsDelegate delegate;

    /**
     * Creates a new set of ground overlay options.
     */
    public OPFGroundOverlayOptions() {
        this.delegate = OPFMapHelper.getInstance().getDelegatesFactory().createGroundOverlayOptionsDelegate();
    }

    @SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes")
    private OPFGroundOverlayOptions(@NonNull final Parcel parcel) {
        try {
            this.delegate = parcel.readParcelable(Class.forName(parcel.readString()).getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Specifies the anchor. The anchor aligns with the ground overlay's location.
     * The anchor point is specified in 2D continuous space where (0,0), (1,0), (0,1) and (1,1) denote the top-left,
     * top-right, bottom-left and bottom-right corners respectively.
     *
     * @param u The u-coordinate of the anchor.
     * @param v The v-coordinate of the anchor.
     * @return This {@link OPFGroundOverlayOptions} object.
     */
    @NonNull
    @Override
    public OPFGroundOverlayOptions anchor(final float u, final float v) {
        delegate.anchor(u, v);
        return this;
    }

    /**
     * Specifies the bearing of the ground overlay in degrees clockwise from north.
     * The rotation is performed about the anchor point. If not specified, the default is 0 (i.e., up on the image points north).
     *
     * @param bearing The bearing in degrees clockwise from north. Values outside the range [0, 360) will be normalized.
     * @return This {@link OPFGroundOverlayOptions} object.
     */
    @NonNull
    @Override
    public OPFGroundOverlayOptions bearing(final float bearing) {
        delegate.bearing(bearing);
        return this;
    }

    /**
     * Horizontal relative anchor; 0.0 and 1.0 denote left and right edges respectively.
     * Other anchor values are interpolated accordingly.
     *
     * @return The horizontal edge-relative anchor location.
     */
    @Override
    public float getAnchorU() {
        return delegate.getAnchorU();
    }

    /**
     * Vertical relative anchor; 0.0 and 1.0 denote top and bottom edges respectively.
     * Other anchor values are interpolated accordingly.
     *
     * @return The vertical edge-relative anchor location.
     */
    @Override
    public float getAnchorV() {
        return delegate.getAnchorV();
    }

    /**
     * Gets the bearing set for this options object.
     *
     * @return The bearing of the ground overlay.
     */
    @Override
    public float getBearing() {
        return delegate.getBearing();
    }

    /**
     * Gets the bounds set for this options object.
     *
     * @return The bounds of the ground overlay. This will be {@code null} if the position was set using
     * {@link #position(OPFLatLng, float)} or {@link #position(OPFLatLng, float, float)}.
     */
    @Nullable
    @Override
    public OPFLatLngBounds getBounds() {
        return delegate.getBounds();
    }

    /**
     * Gets the height set for this options object.
     *
     * @return The height of the ground overlay.
     */
    @Override
    public float getHeight() {
        return delegate.getHeight();
    }

    /**
     * Gets the image set for this options object.
     *
     * @return The image of the ground overlay.
     */
    @Override
    public OPFBitmapDescriptor getImage() {
        return delegate.getImage();
    }

    /**
     * Gets the location set for this options object.
     *
     * @return The location to place the anchor of the ground overlay. This will be null if the position was set
     * using {@link #positionFromBounds(OPFLatLngBounds)}.
     */
    @Override
    public OPFLatLng getLocation() {
        return delegate.getLocation();
    }

    /**
     * Gets the transparency set for this options object.
     *
     * @return The transparency of the ground overlay.
     */
    @Override
    public float getTransparency() {
        return delegate.getTransparency();
    }

    /**
     * Gets the width set for this options object.
     *
     * @return The width of the ground overlay.
     */
    @Override
    public float getWidth() {
        return delegate.getWidth();
    }

    /**
     * Gets the zIndex set for this options object.
     *
     * @return The zIndex of the ground overlay.
     */
    @Override
    public float getZIndex() {
        return delegate.getZIndex();
    }

    /**
     * Specifies the image for this ground overlay.
     *
     * @param image The {@link OPFBitmapDescriptor} to use for this ground overlay.
     * @return This {@link OPFGroundOverlayOptions} object.
     */
    @NonNull
    @Override
    public OPFGroundOverlayOptions image(@NonNull final OPFBitmapDescriptor image) {
        delegate.image(image);
        return this;
    }

    /**
     * Gets the visibility setting for this options object.
     *
     * @return {@code true} if the ground overlay is to be visible; {@code false} if it is not.
     */
    @Override
    public boolean isVisible() {
        return delegate.isVisible();
    }

    /**
     * Specifies the position for this ground overlay using an anchor point (a {@link OPFLatLng}),
     * width and height (both in meters). When rendered, the image will be scaled to fit the dimensions specified.
     *
     * @param location The location on the map {@link OPFLatLng} to which the anchor point in the given image will remain fixed.
     *                 The anchor will remain fixed to the position on the ground when transformations
     *                 are applied (e.g., setDimensions, setBearing, etc.).
     * @param width    The width of the overlay (in meters).
     * @param height   The height of the overlay (in meters)
     * @return This {@link OPFGroundOverlayOptions} object.
     */
    @NonNull
    @Override
    public OPFGroundOverlayOptions position(@NonNull final OPFLatLng location, final float width, final float height) {
        delegate.position(location, width, height);
        return this;
    }

    /**
     * Specifies the position for this ground overlay using an anchor point (a {@link OPFLatLng}) and the width (in meters).
     * The height will be adapted accordingly to preserve aspect ratio.
     *
     * @param location The location on the map {@link OPFLatLng} to which the anchor point in the given image will remain fixed.
     *                 The anchor will remain fixed to the position on the ground when transformations
     *                 are applied (e.g., setDimensions, setBearing, etc.).
     * @param width    The width of the overlay (in meters). The height will be determined automatically based on the image aspect ratio.
     * @return This {@link OPFGroundOverlayOptions} object.
     */
    @NonNull
    @Override
    public OPFGroundOverlayOptions position(@NonNull final OPFLatLng location, final float width) {
        delegate.position(location, width);
        return this;
    }

    /**
     * Specifies the position for this ground overlay.
     *
     * @param bounds An {@link OPFLatLngBounds} in which to place the ground overlay
     * @return This {@link OPFGroundOverlayOptions} object.
     */
    @NonNull
    @Override
    public OPFGroundOverlayOptions positionFromBounds(@NonNull final OPFLatLngBounds bounds) {
        delegate.positionFromBounds(bounds);
        return this;
    }

    /**
     * Specifies the transparency of the ground overlay. The default transparency is {@code 0} (opaque).
     *
     * @param transparency A float in the range [0..1] where 0 means that the ground overlay is opaque and 1 means
     *                     that the ground overlay is transparent.
     * @return This {@link OPFGroundOverlayOptions} object.
     */
    @NonNull
    @Override
    public OPFGroundOverlayOptions transparency(final float transparency) {
        delegate.transparency(transparency);
        return this;
    }

    /**
     * Specifies the visibility for the ground overlay. The default visibility is {@code true}.
     *
     * @param visible {@code false} to make this circle invisible, {@code true} otherwise.
     * @return This {@link OPFGroundOverlayOptions} object.
     */
    @NonNull
    @Override
    public OPFGroundOverlayOptions visible(final boolean visible) {
        delegate.visible(visible);
        return this;
    }

    /**
     * Specifies the ground overlay's zIndex, i.e., the order in which it will be drawn.
     *
     * @param zIndex zIndex value.
     * @return This {@link OPFGroundOverlayOptions} object.
     */
    @NonNull
    @Override
    public OPFGroundOverlayOptions zIndex(final float zIndex) {
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
                && (other == this || other instanceof OPFGroundOverlayOptions
                && delegate.equals(((OPFGroundOverlayOptions) other).delegate));
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
