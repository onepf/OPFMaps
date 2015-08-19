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
import org.onepf.maps.osmdroid.utils.CompareUtils;
import org.osmdroid.util.GeoPoint;

/**
 * @author Roman Savin
 * @since 17.08.2015
 */
public final class CameraPosition implements Parcelable {

    public static final Creator<CameraPosition> CREATOR = new Creator<CameraPosition>() {
        @Override
        public CameraPosition createFromParcel(final Parcel source) {
            return new CameraPosition(source);
        }

        @Override
        public CameraPosition[] newArray(final int size) {
            return new CameraPosition[size];
        }
    };

    @NonNull
    public static CameraPosition.Builder builder() {
        return new CameraPosition.Builder();
    }

    @NonNull
    public static CameraPosition.Builder builder(@NonNull final CameraPosition camera) {
        return new CameraPosition.Builder(camera);
    }

    @NonNull
    public static CameraPosition fromLatLngZoom(@NonNull final GeoPoint target, final float zoom) {
        return new CameraPosition(target, zoom, 0.0F, 0.0F);
    }

    @NonNull
    public final GeoPoint target;
    public final float zoom;
    public final float tilt;
    public final float bearing;

    public CameraPosition(@Nullable final GeoPoint target, final float zoom, final float tilt, final float bearing) {
        if (target == null) {
            throw new NullPointerException("null camera target");
        } else {
            this.target = target;
            this.zoom = zoom;
            this.tilt = tilt;
            this.bearing = bearing;
        }
    }

    private CameraPosition(@NonNull final Parcel parcel) {
        this.target = parcel.readParcelable(GeoPoint.class.getClassLoader());
        this.zoom = parcel.readFloat();
        this.tilt = parcel.readFloat();
        this.bearing = parcel.readFloat();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(target, flags);
        dest.writeFloat(zoom);
        dest.writeFloat(tilt);
        dest.writeFloat(bearing);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof CameraPosition)) {
            return false;
        } else {
            final CameraPosition other = (CameraPosition) obj;

            return CompareUtils.isEquals(this.target, other.target)
                    && this.bearing == other.bearing
                    && this.tilt == other.tilt
                    && this.zoom == other.zoom;
        }
    }

    @Override
    public int hashCode() {
        int result = target.hashCode();
        result = 31 * result + (bearing != +0.0f ? Float.floatToIntBits(bearing) : 0);
        result = 31 * result + (tilt != +0.0f ? Float.floatToIntBits(tilt) : 0);
        result = 31 * result + (zoom != +0.0f ? Float.floatToIntBits(zoom) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CameraPosition{target=" + this.target
                + ", zoom=" + this.zoom
                + ", tilt=" + this.tilt
                + ", bearing=" + this.bearing + "}";
    }

    public static final class Builder {

        @Nullable
        private GeoPoint target = null;
        private float zoom = 0.0F;
        private float tilt = 0.0F;
        private float bearing = 0.0F;

        public Builder() {
        }

        public Builder(@NonNull final CameraPosition previous) {
            this.target = previous.target;
            this.zoom = previous.zoom;
            this.tilt = previous.tilt;
            this.bearing = previous.bearing;
        }

        public CameraPosition.Builder target(@NonNull final GeoPoint location) {
            this.target = location;
            return this;
        }

        public CameraPosition.Builder zoom(final float zoom) {
            this.zoom = zoom;
            return this;
        }

        public CameraPosition.Builder tilt(final float tilt) {
            this.tilt = tilt;
            return this;
        }

        public CameraPosition.Builder bearing(final float bearing) {
            this.bearing = bearing;
            return this;
        }

        public CameraPosition build() {
            return new CameraPosition(target, zoom, tilt, bearing);
        }
    }
}
