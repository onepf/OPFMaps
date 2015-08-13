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
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import org.onepf.opfmaps.delegate.model.GroundOverlayOptionsDelegate;
import org.onepf.opfmaps.model.OPFBitmapDescriptor;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFLatLngBounds;

/**
 * @author Roman Savin
 * @since 03.08.2015
 */
public final class OsmdroidGroundOverlayOptionsDelegate implements GroundOverlayOptionsDelegate {

    public static final Creator<OsmdroidGroundOverlayOptionsDelegate> CREATOR = new Creator<OsmdroidGroundOverlayOptionsDelegate>() {
        @Override
        public OsmdroidGroundOverlayOptionsDelegate createFromParcel(final Parcel source) {
            return new OsmdroidGroundOverlayOptionsDelegate(source);
        }

        @Override
        public OsmdroidGroundOverlayOptionsDelegate[] newArray(final int size) {
            return new OsmdroidGroundOverlayOptionsDelegate[size];
        }
    };

    @NonNull
    private final GroundOverlayOptions groundOverlayOptions;

    public OsmdroidGroundOverlayOptionsDelegate() {
        this.groundOverlayOptions = new GroundOverlayOptions();
    }

    private OsmdroidGroundOverlayOptionsDelegate(@NonNull final Parcel parcel) {
        this.groundOverlayOptions = parcel.readParcelable(GroundOverlayOptions.class.getClassLoader());
    }

    @NonNull
    @Override
    public OsmdroidGroundOverlayOptionsDelegate anchor(final float u, final float v) {
        groundOverlayOptions.anchor(u, v);
        return this;
    }

    @NonNull
    @Override
    public OsmdroidGroundOverlayOptionsDelegate bearing(final float bearing) {
        groundOverlayOptions.bearing(bearing);
        return this;
    }

    @Override
    public float getAnchorU() {
        return groundOverlayOptions.getAnchorU();
    }

    @Override
    public float getAnchorV() {
        return groundOverlayOptions.getAnchorV();
    }

    @Override
    public float getBearing() {
        return groundOverlayOptions.getBearing();
    }

    @Nullable
    @Override
    public OPFLatLngBounds getBounds() {
        final LatLngBounds bounds = groundOverlayOptions.getBounds();
        if (bounds != null) {
            return new OPFLatLngBounds(new OsmdroidLatLngBoundsDelegate(bounds));
        }
        return null;
    }

    @Override
    public float getHeight() {
        return groundOverlayOptions.getHeight();
    }

    @Nullable
    @Override
    public OPFBitmapDescriptor getImage() {
        final BitmapDescriptor image = groundOverlayOptions.getImage();
        if (image != null) {
            return new OPFBitmapDescriptor(new OsmdroidBitmapDescriptorDelegate(image));
        }
        return null;
    }

    @Nullable
    @Override
    public OPFLatLng getLocation() {
        final LatLng location = groundOverlayOptions.getLocation();
        if (location != null) {
            return new OPFLatLng(new OsmdroidLatLngDelegate(location));
        }
        return null;
    }

    @Override
    public float getTransparency() {
        return groundOverlayOptions.getTransparency();
    }

    @Override
    public float getWidth() {
        return groundOverlayOptions.getWidth();
    }

    @Override
    public float getZIndex() {
        return groundOverlayOptions.getZIndex();
    }

    @NonNull
    @Override
    public OsmdroidGroundOverlayOptionsDelegate image(@NonNull final OPFBitmapDescriptor image) {
        groundOverlayOptions.image((BitmapDescriptor) image.getDelegate().getBitmapDescriptor());
        return this;
    }

    @Override
    public boolean isVisible() {
        return groundOverlayOptions.isVisible();
    }

    @NonNull
    @Override
    public OsmdroidGroundOverlayOptionsDelegate position(@NonNull final OPFLatLng location,
                                                       final float width,
                                                       final float height) {
        groundOverlayOptions.position(new LatLng(location.getLat(), location.getLng()), width, height);
        return this;
    }

    @NonNull
    @Override
    public OsmdroidGroundOverlayOptionsDelegate position(@NonNull final OPFLatLng location, final float width) {
        groundOverlayOptions.position(new LatLng(location.getLat(), location.getLng()), width);
        return this;
    }

    @NonNull
    @Override
    public OsmdroidGroundOverlayOptionsDelegate positionFromBounds(@NonNull final OPFLatLngBounds bounds) {
        groundOverlayOptions.positionFromBounds(new LatLngBounds(
                new LatLng(bounds.getSouthwest().getLat(), bounds.getNortheast().getLng()),
                new LatLng(bounds.getNortheast().getLat(), bounds.getNortheast().getLng())
        ));
        return this;
    }

    @NonNull
    @Override
    public OsmdroidGroundOverlayOptionsDelegate transparency(final float transparency) {
        groundOverlayOptions.transparency(transparency);
        return this;
    }

    @NonNull
    @Override
    public OsmdroidGroundOverlayOptionsDelegate visible(final boolean visible) {
        groundOverlayOptions.visible(visible);
        return this;
    }

    @NonNull
    @Override
    public OsmdroidGroundOverlayOptionsDelegate zIndex(final float zIndex) {
        groundOverlayOptions.zIndex(zIndex);
        return this;
    }

    @Override
    public int describeContents() {
        return groundOverlayOptions.describeContents();
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(groundOverlayOptions, flags);
    }

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OsmdroidGroundOverlayOptionsDelegate)) return false;

        return groundOverlayOptions.equals(((OsmdroidGroundOverlayOptionsDelegate) other).groundOverlayOptions);
    }
    //CHECKSTYLE:ON

    @Override
    public int hashCode() {
        return groundOverlayOptions.hashCode();
    }

    @Override
    public String toString() {
        return groundOverlayOptions.toString();
    }
}
