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
import org.onepf.opfmaps.delegate.model.MarkerOptionsDelegate;

/**
 * Defines options for a marker.
 *
 * @author Roman Savin
 * @since 29.07.2015
 */
public final class OPFMarkerOptions implements MarkerOptionsDelegate {

    public static final Creator<OPFMarkerOptions> CREATOR = new Creator<OPFMarkerOptions>() {
        @Override
        public OPFMarkerOptions createFromParcel(final Parcel source) {
            return new OPFMarkerOptions(source);
        }

        @Override
        public OPFMarkerOptions[] newArray(final int size) {
            return new OPFMarkerOptions[size];
        }
    };

    @NonNull
    private final MarkerOptionsDelegate delegate;

    /**
     * Creates a new set of marker options.
     */
    public OPFMarkerOptions() {
        this.delegate = OPFMapHelper.getInstance().getDelegatesFactory().createMarkerOptionsDelegate();
    }

    @SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes")
    private OPFMarkerOptions(@NonNull final Parcel parcel) {
        try {
            this.delegate = parcel.readParcelable(Class.forName(parcel.readString()).getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the alpha (opacity) of the marker. This is a value from 0 to 1, where 0 means the marker is completely transparent
     * and 1 means the marker is completely opaque.
     *
     * @param alpha The alpha value.
     * @return This {@link OPFMarkerOptions} object.
     */
    @NonNull
    @Override
    public OPFMarkerOptions alpha(final float alpha) {
        delegate.alpha(alpha);
        return this;
    }

    /**
     * Specifies the anchor to be at a particular point in the marker image.
     * The anchor specifies the point in the icon image that is anchored to the marker's position on the Earth's surface.
     *
     * @param u The u-coordinate of the anchor, as a ratio of the image width (in the range [0, 1]).
     * @param v The v-coordinate of the anchor, as a ratio of the image height (in the range [0, 1]).
     * @return This {@link OPFMarkerOptions} object.
     */
    @NonNull
    @Override
    public OPFMarkerOptions anchor(final float u, final float v) {
        delegate.anchor(u, v);
        return this;
    }

    /**
     * Sets the draggability for the marker.
     *
     * @param draggable {@code true} if marker should be draggable, {@code false} otherwise.
     * @return This {@link OPFMarkerOptions} object.
     */
    @NonNull
    @Override
    public OPFMarkerOptions draggable(final boolean draggable) {
        delegate.draggable(draggable);
        return this;
    }

    /**
     * Sets whether this marker should be flat against the map or a billboard facing the camera .
     * If the marker is flat against the map, it will remain stuck to the map as the camera rotates
     * and tilts but will still remain the same size as the camera zooms, unlike a {@link OPFGroundOverlay}.
     * If the marker is a billboard, it will always be drawn facing the camera and will rotate and tilt with the camera.
     * The default value is {@code false}.
     *
     * @param flat {@code true} if marker should be flat, {@code false} otherwise.
     * @return This {@link OPFMarkerOptions} object.
     */
    @NonNull
    @Override
    public OPFMarkerOptions flat(final boolean flat) {
        delegate.flat(flat);
        return this;
    }

    /**
     * Gets the alpha set for this marker options object.
     *
     * @return The alpha of the marker in the range [0, 1].
     */
    @Override
    public float getAlpha() {
        return delegate.getAlpha();
    }

    /**
     * Horizontal distance, normalized to [0, 1], of the anchor from the left edge.
     *
     * @return The u value of the anchor.
     */
    @Override
    public float getAnchorU() {
        return delegate.getAnchorU();
    }

    /**
     * Vertical distance, normalized to [0, 1], of the anchor from the top edge.
     *
     * @return The v value of the anchor.
     */
    @Override
    public float getAnchorV() {
        return delegate.getAnchorV();
    }

    /**
     * Gets the custom icon set for this marker options object.
     *
     * @return An {@link OPFBitmapDescriptor} representing the custom icon, or {@code null} if no custom icon is set.
     */
    @Nullable
    @Override
    public OPFBitmapDescriptor getIcon() {
        return delegate.getIcon();
    }

    /**
     * Horizontal distance, normalized to [0, 1], of the info window anchor from the left edge.
     *
     * @return The u value of the info window anchor.
     */
    @Override
    public float getInfoWindowAnchorU() {
        return delegate.getInfoWindowAnchorU();
    }

    /**
     * Vertical distance, normalized to [0, 1], of the info window anchor from the top edge.
     *
     * @return The v value of the info window anchor.
     */
    @Override
    public float getInfoWindowAnchorV() {
        return delegate.getInfoWindowAnchorV();
    }

    /**
     * Returns the position set for this marker options object.
     *
     * @return An {@link OPFLatLng} object specifying the marker's current position.
     */
    @Nullable
    @Override
    public OPFLatLng getPosition() {
        return delegate.getPosition();
    }

    /**
     * Gets the rotation set for this marker options object.
     *
     * @return The rotation of the marker in degrees clockwise from the default position.
     */
    @Override
    public float getRotation() {
        return delegate.getRotation();
    }

    /**
     * Gets the snippet set for this marker options object.
     *
     * @return A string containing the marker's snippet.
     */
    @Nullable
    @Override
    public String getSnippet() {
        return delegate.getSnippet();
    }

    /**
     * Gets the title set for this marker options object.
     *
     * @return A string containing the marker's title.
     */
    @Nullable
    @Override
    public String getTitle() {
        return delegate.getTitle();
    }

    /**
     * Sets the icon for the marker.
     *
     * @param icon The icon of the marker.
     * @return This {@link OPFMarkerOptions} object.
     */
    @NonNull
    @Override
    public OPFMarkerOptions icon(@NonNull final OPFBitmapDescriptor icon) {
        delegate.icon(icon);
        return this;
    }

    /**
     * Specifies the anchor point of the info window on the marker image. This is specified in the same coordinate system as the anchor.
     * The default is the top middle of the image.
     *
     * @param u The u-coordinate of the info window anchor, as a ratio of the image width (in the range [0, 1]).
     * @param v The v-coordinate of the info window anchor, as a ratio of the image height (in the range [0, 1]).
     * @return This {@link OPFMarkerOptions} object.
     */
    @NonNull
    @Override
    public OPFMarkerOptions infoWindowAnchor(final float u, final float v) {
        delegate.infoWindowAnchor(u, v);
        return this;
    }

    /**
     * Gets the draggability setting for this marker options object.
     *
     * @return {@code true} if the marker is draggable, {@code false} otherwise.
     */
    @Override
    public boolean isDraggable() {
        return delegate.isDraggable();
    }

    /**
     * Gets the flat setting for this marker options object.
     *
     * @return {@code true} if the marker is flat against the map; {@code false} if the marker should face the camera.
     */
    @Override
    public boolean isFlat() {
        return delegate.isFlat();
    }

    /**
     * Gets the visibility setting for this marker options object.
     *
     * @return {@code true} if the marker is visible, {@code false} otherwise.
     */
    @Override
    public boolean isVisible() {
        return delegate.isVisible();
    }

    /**
     * Sets the location for the marker.
     *
     * @param position The geographic position of the marker, specified as a {@link OPFLatLng}.
     * @return This {@link OPFMarkerOptions} object.
     */
    @NonNull
    @Override
    public OPFMarkerOptions position(@NonNull final OPFLatLng position) {
        delegate.position(position);
        return this;
    }

    /**
     * Sets the rotation of the marker in degrees clockwise about the marker's anchor point. The axis of rotation is
     * perpendicular to the marker. A rotation of 0 corresponds to the default position of the marker.
     * When the marker is flat on the map, the default position is North aligned and the rotation is such that
     * the marker always remains flat on the map. When the marker is a billboard, the default position is pointing up and
     * the rotation is such that the marker is always facing the camera. The default value is 0.
     *
     * @param rotation The rotation of the marker.
     * @return This {@link OPFMarkerOptions} object.
     */
    @NonNull
    @Override
    public OPFMarkerOptions rotation(final float rotation) {
        delegate.rotation(rotation);
        return this;
    }

    /**
     * Sets the snippet for the marker.
     *
     * @param snippet The snippet of the marker.
     * @return This {@link OPFMarkerOptions} object.
     */
    @NonNull
    @Override
    public OPFMarkerOptions snippet(@NonNull final String snippet) {
        delegate.snippet(snippet);
        return this;
    }

    /**
     * Sets the title for the marker.
     *
     * @param title The title of the marker.
     * @return This {@link OPFMarkerOptions} object.
     */
    @NonNull
    @Override
    public OPFMarkerOptions title(@NonNull final String title) {
        delegate.title(title);
        return this;
    }

    /**
     * Sets the visibility for the marker.
     *
     * @param visible {@code false} to make this marker invisible, {@code true} otherwise.
     * @return This {@link OPFMarkerOptions} object.
     */
    @NonNull
    @Override
    public OPFMarkerOptions visible(final boolean visible) {
        delegate.visible(visible);
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
                && (other == this || other instanceof OPFMarkerOptions
                && delegate.equals(((OPFMarkerOptions) other).delegate));
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
