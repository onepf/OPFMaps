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

package org.onepf.maps.osmdroid.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.onepf.opfutils.OPFLog;
import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.util.GeoPoint;

import static org.onepf.maps.osmdroid.utils.CompareUtils.isEquals;

/**
 * @author Roman Savin
 * @since 17.08.2015
 */
public final class GroundOverlayOptions implements Parcelable {

    public static final Creator<GroundOverlayOptions> CREATOR = new Creator<GroundOverlayOptions>() {
        @Override
        public GroundOverlayOptions createFromParcel(final Parcel source) {
            return new GroundOverlayOptions(source);
        }

        @Override
        public GroundOverlayOptions[] newArray(final int size) {
            return new GroundOverlayOptions[size];
        }
    };

    private static final float CIRCLE_DEGREES = 360F;

    @Nullable
    private BoundingBoxE6 bounds;
    @Nullable
    private GeoPoint location;
    @Nullable
    private BitmapDescriptor image;
    private float bearing;
    private float height = -1.0F;
    private float transparency;
    private float width = 10.0F;
    private boolean isVisible = true;


    public GroundOverlayOptions() {
        //nothing
    }

    private GroundOverlayOptions(@NonNull final Parcel parcel) {
        this.bounds = parcel.readParcelable(BoundingBoxE6.class.getClassLoader());
        this.location = parcel.readParcelable(GeoPoint.class.getClassLoader());
        this.image = parcel.readParcelable(BitmapDescriptor.class.getClassLoader());
        this.bearing = parcel.readFloat();
        this.height = parcel.readFloat();
        this.transparency = parcel.readFloat();
        this.width = parcel.readFloat();
        this.isVisible = parcel.readByte() != 0;
    }

    @NonNull
    public GroundOverlayOptions anchor(final float u, final float v) {
        OPFLog.logStubCall(u, v);
        return this;
    }

    @NonNull
    public GroundOverlayOptions bearing(final float bearing) {
        this.bearing = bearing % CIRCLE_DEGREES;
        if (this.bearing == -0.0F) {
            this.bearing = 0.0F;
        } else if (this.bearing < 0.0F) {
            this.bearing += CIRCLE_DEGREES;
        }

        return this;
    }

    public float getAnchorU() {
        return 0.0f;
    }

    public float getAnchorV() {
        return 0.0f;
    }

    public float getBearing() {
        return this.bearing;
    }

    @Nullable
    public BoundingBoxE6 getBounds() {
        return this.bounds;
    }

    public float getHeight() {
        return this.height;
    }

    @Nullable
    public BitmapDescriptor getImage() {
        return this.image;
    }

    @Nullable
    public GeoPoint getLocation() {
        return this.location;
    }

    public float getTransparency() {
        return this.transparency;
    }

    public float getWidth() {
        return this.width;
    }

    public float getZIndex() {
        return 0.0f;
    }

    @NonNull
    public GroundOverlayOptions image(@NonNull final BitmapDescriptor image) {
        this.image = image;
        return this;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    @NonNull
    public GroundOverlayOptions position(@Nullable final GeoPoint location, final float width, final float height) {
        if (this.bounds != null) {
            throw new IllegalStateException("Position has already been set using positionFromBounds");
        } else if (location == null) {
            throw new IllegalArgumentException("Location must be specified");
        } else if (width < 0.0F) {
            throw new IllegalArgumentException("Width must be non-negative");
        } else if (height < 0.0F) {
            throw new IllegalArgumentException("Height must be non-negative");
        } else {
            this.location = location;
            this.width = width;
            this.height = height;
            return this;
        }
    }

    @NonNull
    public GroundOverlayOptions position(@Nullable final GeoPoint location, final float width) {
        if (this.bounds != null) {
            throw new IllegalStateException("Position has already been set using positionFromBounds");
        } else if (location == null) {
            throw new IllegalArgumentException("Location must be specified");
        } else if (width < 0.0F) {
            throw new IllegalArgumentException("Width must be non-negative");
        } else {
            this.location = location;
            this.width = width;
            this.height = -1.0F;
            return this;
        }
    }

    @NonNull
    public GroundOverlayOptions positionFromBounds(@Nullable final BoundingBoxE6 bounds) {
        if (this.location != null) {
            throw new IllegalStateException("Position has already been set using position: " + this.location);
        } else {
            this.bounds = bounds;
            return this;
        }
    }

    @NonNull
    public GroundOverlayOptions transparency(final float transparency) {
        if (transparency >= 0.0F && transparency <= 1.0F) {
            this.transparency = transparency;
            return this;
        } else {
            throw new IllegalArgumentException("Transparency must be in the range [0..1]");
        }
    }

    @NonNull
    public GroundOverlayOptions zIndex(float zIndex) {
        OPFLog.logStubCall(zIndex);
        return this;
    }

    @NonNull
    public GroundOverlayOptions visible(final boolean visible) {
        this.isVisible = visible;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(bounds, flags);
        dest.writeParcelable(location, flags);
        dest.writeParcelable(image, flags);
        dest.writeFloat(bearing);
        dest.writeFloat(height);
        dest.writeFloat(transparency);
        dest.writeFloat(width);
        dest.writeByte((byte) (isVisible ? 1 : 0));
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof GroundOverlayOptions)) {
            return false;
        } else {
            final GroundOverlayOptions other = (GroundOverlayOptions) obj;

            return isEquals(this.bounds, other.bounds)
                    && isEquals(this.location, other.location)
                    && isEquals(this.image, other.image)
                    && this.bearing == other.bearing
                    && this.height == other.height
                    && this.transparency == other.transparency
                    && this.width == other.width
                    && this.isVisible == other.isVisible;
        }
    }

    @SuppressWarnings("PMD.NPathComplexity")
    @Override
    public int hashCode() {
        int result = bounds != null ? bounds.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (bearing != +0.0f ? Float.floatToIntBits(bearing) : 0);
        result = 31 * result + (height != +0.0f ? Float.floatToIntBits(height) : 0);
        result = 31 * result + (transparency != +0.0f ? Float.floatToIntBits(transparency) : 0);
        result = 31 * result + (width != +0.0f ? Float.floatToIntBits(width) : 0);
        result = 31 * result + (isVisible ? 1 : 0);
        return result;
    }
}
