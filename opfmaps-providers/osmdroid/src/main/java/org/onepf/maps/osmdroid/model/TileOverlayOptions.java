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

import static org.onepf.maps.osmdroid.utils.CompareUtils.isEquals;

/**
 * @author Roman Savin
 * @since 17.08.2015
 */
public final class TileOverlayOptions implements Parcelable {

    public static final Creator<TileOverlayOptions> CREATOR = new Creator<TileOverlayOptions>() {
        @Override
        public TileOverlayOptions createFromParcel(final Parcel source) {
            return new TileOverlayOptions(source);
        }

        @Override
        public TileOverlayOptions[] newArray(final int size) {
            return new TileOverlayOptions[size];
        }
    };

    @Nullable
    private TileProvider tileProvider = null;
    private boolean isVisible = true;
    private boolean isFadeIn = true;
    private float zIndex = 0.0F;

    public TileOverlayOptions() {
    }

    private TileOverlayOptions(@NonNull final Parcel source) {
        this.isFadeIn = source.readByte() != 0;
        this.zIndex = source.readFloat();
        this.isVisible = source.readByte() != 0;
    }

    public boolean getFadeIn() {
        return this.isFadeIn;
    }

    @Nullable
    public TileProvider getTileProvider() {
        return this.tileProvider;
    }

    public float getZIndex() {
        return this.zIndex;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    @NonNull
    public TileOverlayOptions fadeIn(final boolean fadeIn) {
        this.isFadeIn = fadeIn;
        return this;
    }

    @NonNull
    public TileOverlayOptions tileProvider(@NonNull final TileProvider tileProvider) {
        this.tileProvider = tileProvider;
        return this;
    }

    @NonNull
    public TileOverlayOptions visible(final boolean visible) {
        this.isVisible = visible;
        return this;
    }

    @NonNull
    public TileOverlayOptions zIndex(final float zIndex) {
        this.zIndex = zIndex;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeByte((byte) (isFadeIn ? 1 : 0));
        out.writeFloat(zIndex);
        out.writeByte((byte) (isVisible ? 1 : 0));
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof TileOverlayOptions)) {
            return false;
        } else {
            final TileOverlayOptions other = (TileOverlayOptions) obj;
            return isEquals(this.tileProvider, other.tileProvider)
                    && this.isFadeIn == other.isFadeIn
                    && this.zIndex == other.zIndex
                    && this.isVisible == other.isVisible;
        }
    }

    @Override
    public int hashCode() {
        int result = tileProvider != null ? tileProvider.hashCode() : 0;
        result = 31 * result + (isVisible ? 1 : 0);
        result = 31 * result + (isFadeIn ? 1 : 0);
        result = 31 * result + (zIndex != +0.0f ? Float.floatToIntBits(zIndex) : 0);
        return result;
    }
}
