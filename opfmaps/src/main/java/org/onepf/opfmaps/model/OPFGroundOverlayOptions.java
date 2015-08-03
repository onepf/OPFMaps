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

    @NonNull
    @Override
    public OPFGroundOverlayOptions anchor(final float u, final float v) {
        delegate.anchor(u, v);
        return this;
    }

    @NonNull
    @Override
    public OPFGroundOverlayOptions bearing(final float bearing) {
        delegate.bearing(bearing);
        return this;
    }

    @Override
    public float getAnchorU() {
        return delegate.getAnchorU();
    }

    @Override
    public float getAnchorV() {
        return delegate.getAnchorV();
    }

    @Override
    public float getBearing() {
        return delegate.getBearing();
    }

    @Nullable
    @Override
    public OPFLatLngBounds getBounds() {
        return delegate.getBounds();
    }

    @Override
    public float getHeight() {
        return delegate.getHeight();
    }

    @Override
    public OPFBitmapDescriptor getImage() {
        return delegate.getImage();
    }

    @Override
    public OPFLatLng getLocation() {
        return delegate.getLocation();
    }

    @Override
    public float getTransparency() {
        return delegate.getTransparency();
    }

    @Override
    public float getWidth() {
        return delegate.getWidth();
    }

    @Override
    public float getZIndex() {
        return delegate.getZIndex();
    }

    @NonNull
    @Override
    public OPFGroundOverlayOptions image(@NonNull final OPFBitmapDescriptor image) {
        delegate.image(image);
        return this;
    }

    @Override
    public boolean isVisible() {
        return delegate.isVisible();
    }

    @NonNull
    @Override
    public OPFGroundOverlayOptions position(@NonNull final OPFLatLng location, final float width, final float height) {
        delegate.position(location, width, height);
        return this;
    }

    @NonNull
    @Override
    public OPFGroundOverlayOptions position(@NonNull final OPFLatLng location, final float width) {
        delegate.position(location, width);
        return this;
    }

    @NonNull
    @Override
    public OPFGroundOverlayOptions positionFromBounds(@NonNull final OPFLatLngBounds bounds) {
        delegate.positionFromBounds(bounds);
        return this;
    }

    @NonNull
    @Override
    public OPFGroundOverlayOptions transparency(final float transparency) {
        delegate.transparency(transparency);
        return this;
    }

    @NonNull
    @Override
    public OPFGroundOverlayOptions visible(final boolean visible) {
        delegate.visible(visible);
        return this;
    }

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

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OPFGroundOverlayOptions)) return false;

        return delegate.equals(((OPFGroundOverlayOptions) other).delegate);
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
}
