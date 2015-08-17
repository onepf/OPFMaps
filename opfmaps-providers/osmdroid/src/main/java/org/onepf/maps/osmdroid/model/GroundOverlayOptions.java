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

    @Nullable
    private BoundingBoxE6 bounds = null;
    @Nullable
    private GeoPoint location = null;
    @Nullable
    private BitmapDescriptor image = null;
    private float anchorU = 0.0F;
    private float anchorV = 0.0F;
    private float bearing = 0.0F;
    private float height = -1.0F;
    private float transparency = 0.0F;
    private float width = 10.0F;
    private float zIndex = 0.0F;
    private boolean isVisible = true;


    public GroundOverlayOptions() {
    }

    private GroundOverlayOptions(@NonNull final Parcel parcel) {
        this.bounds = parcel.readParcelable(BoundingBoxE6.class.getClassLoader());
        this.location = parcel.readParcelable(GeoPoint.class.getClassLoader());
        this.image = parcel.readParcelable(BitmapDescriptor.class.getClassLoader());
        this.anchorU = parcel.readFloat();
        this.anchorV = parcel.readFloat();
        this.bearing = parcel.readFloat();
        this.height = parcel.readFloat();
        this.transparency = parcel.readFloat();
        this.width = parcel.readFloat();
        this.zIndex = parcel.readFloat();
        this.isVisible = parcel.readByte() != 0;
    }

    @NonNull
    public GroundOverlayOptions anchor(final float u, final float v) {
        this.anchorU = u;
        this.anchorV = v;
        return this;
    }

    @NonNull
    public GroundOverlayOptions bearing(final float bearing) {
        this.bearing = bearing % 360.0F;
        if(this.bearing == -0.0F) {
            this.bearing = 0.0F;
        } else if(this.bearing < 0.0F) {
            this.bearing += 360.0F;
        }

        return this;
    }

    public float getAnchorU() {
        return this.anchorU;
    }

    public float getAnchorV() {
        return this.anchorV;
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
        return this.zIndex;
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
        if(this.bounds != null) {
            throw new IllegalStateException("Position has already been set using positionFromBounds");
        } else if(location == null) {
            throw new IllegalArgumentException("Location must be specified");
        } else if(width < 0.0F) {
            throw new IllegalArgumentException("Width must be non-negative");
        } else if(height < 0.0F) {
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
        if(this.bounds != null) {
            throw new IllegalStateException("Position has already been set using positionFromBounds");
        } else if(location == null) {
            throw new IllegalArgumentException("Location must be specified");
        } else if(width < 0.0F) {
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
        if(this.location != null) {
            throw new IllegalStateException("Position has already been set using position: " + this.location);
        } else {
            this.bounds = bounds;
            return this;
        }
    }

    @NonNull
    public GroundOverlayOptions transparency(final float transparency) {
        if(transparency >= 0.0F && transparency <= 1.0F) {
            this.transparency = transparency;
            return this;
        } else {
            throw new IllegalArgumentException("Transparency must be in the range [0..1]");
        }
    }

    @NonNull
    public GroundOverlayOptions zIndex(float zIndex) {
        this.zIndex = zIndex;
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
        dest.writeFloat(anchorU);
        dest.writeFloat(anchorV);
        dest.writeFloat(bearing);
        dest.writeFloat(height);
        dest.writeFloat(transparency);
        dest.writeFloat(width);
        dest.writeFloat(zIndex);
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
                    && this.anchorU == other.anchorU
                    && this.anchorV == other.anchorV
                    && this.bearing == other.bearing
                    && this.height == other.height
                    && this.transparency == other.transparency
                    && this.width == other.width
                    && this.zIndex == other.zIndex
                    && this.isVisible == other.isVisible;
        }
    }

    @Override
    public int hashCode() {
        int result = bounds != null ? bounds.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (anchorU != +0.0f ? Float.floatToIntBits(anchorU) : 0);
        result = 31 * result + (anchorV != +0.0f ? Float.floatToIntBits(anchorV) : 0);
        result = 31 * result + (bearing != +0.0f ? Float.floatToIntBits(bearing) : 0);
        result = 31 * result + (height != +0.0f ? Float.floatToIntBits(height) : 0);
        result = 31 * result + (transparency != +0.0f ? Float.floatToIntBits(transparency) : 0);
        result = 31 * result + (width != +0.0f ? Float.floatToIntBits(width) : 0);
        result = 31 * result + (zIndex != +0.0f ? Float.floatToIntBits(zIndex) : 0);
        result = 31 * result + (isVisible ? 1 : 0);
        return result;
    }
}
