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

package org.onepf.maps.yandexweb.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import org.onepf.opfutils.OPFLog;

import static org.onepf.maps.yandexweb.utils.CompareUtils.isEquals;

/**
 * @author Roman Savin
 * @since 17.08.2015
 */
public final class MarkerOptions implements Parcelable {

    public static final Creator<MarkerOptions> CREATOR = new Creator<MarkerOptions>() {
        @Override
        public MarkerOptions createFromParcel(final Parcel source) {
            return new MarkerOptions(source);
        }

        @Override
        public MarkerOptions[] newArray(final int size) {
            return new MarkerOptions[size];
        }
    };

    private static final float DEFAULT_ANCHOR_U = 0.5F;

    @Nullable
    private LatLng position;
    @Nullable
    private String title;
    @NonNull
    private BitmapDescriptor icon = BitmapDescriptorFactory.defaultMarker();
    @Nullable
    private String snippet;
    private boolean isDraggable;
    private boolean isVisible = true;

    public MarkerOptions() {
        //nothing
    }

    private MarkerOptions(@NonNull final Parcel parcel) {
        this.position = parcel.readParcelable(LatLng.class.getClassLoader());
        this.title = parcel.readString();
        this.icon = parcel.readParcelable(BitmapDescriptor.class.getClassLoader());
        this.snippet = parcel.readString();
        this.isDraggable = parcel.readByte() != 0;
        this.isVisible = parcel.readByte() != 0;
    }

    @NonNull
    public MarkerOptions alpha(final float alpha) {
        OPFLog.logStubCall(alpha);
        return this;
    }

    @NonNull
    public MarkerOptions anchor(final float u, final float v) {
        OPFLog.logStubCall(u, v);
        return this;
    }

    @NonNull
    public MarkerOptions draggable(final boolean draggable) {
        this.isDraggable = draggable;
        return this;
    }

    @NonNull
    public MarkerOptions flat(final boolean flat) {
        OPFLog.logStubCall(flat);
        return this;
    }

    public float getAlpha() {
        return 1.0f;
    }

    public float getAnchorU() {
        return DEFAULT_ANCHOR_U;
    }

    public float getAnchorV() {
        return 0.0f;
    }

    @NonNull
    public BitmapDescriptor getIcon() {
        return this.icon;
    }

    public float getInfoWindowAnchorU() {
        return DEFAULT_ANCHOR_U;
    }

    public float getInfoWindowAnchorV() {
        return 0.0f;
    }

    @Nullable
    public LatLng getPosition() {
        return this.position;
    }

    public float getRotation() {
        return 0.0f;
    }

    @Nullable
    public String getSnippet() {
        return this.snippet;
    }

    @Nullable
    public String getTitle() {
        return this.title;
    }

    @NonNull
    public MarkerOptions icon(@NonNull final BitmapDescriptor icon) {
        this.icon = icon;
        return this;
    }

    @NonNull
    public MarkerOptions infoWindowAnchor(final float u, final float v) {
        OPFLog.logStubCall(u, v);
        return this;
    }

    public boolean isDraggable() {
        return this.isDraggable;
    }

    public boolean isFlat() {
        return false;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    @NonNull
    public MarkerOptions position(@NonNull final LatLng position) {
        this.position = position;
        return this;
    }

    @NonNull
    public MarkerOptions rotation(final float rotation) {
        OPFLog.logStubCall(rotation);
        return this;
    }

    @NonNull
    public MarkerOptions snippet(@NonNull final String snippet) {
        this.snippet = snippet;
        return this;
    }

    @NonNull
    public MarkerOptions title(@NonNull final String title) {
        this.title = title;
        return this;
    }

    @NonNull
    public MarkerOptions visible(final boolean visible) {
        this.isVisible = visible;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(position, flags);
        dest.writeString(title);
        dest.writeParcelable(icon, flags);
        dest.writeString(snippet);
        dest.writeByte((byte) (isDraggable ? 1 : 0));
        dest.writeByte((byte) (isVisible ? 1 : 0));
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof MarkerOptions)) {
            return false;
        } else {
            final MarkerOptions other = (MarkerOptions) obj;

            return isEquals(this.position, other.position)
                    && isEquals(this.title, other.title)
                    && isEquals(this.icon, other.title)
                    && isEquals(this.snippet, other.snippet)
                    && this.isDraggable == other.isDraggable
                    && this.isVisible == other.isVisible;
        }
    }

    @SuppressWarnings("PMD.NPathComplexity")
    @Override
    public int hashCode() {
        int result = position != null ? position.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + icon.hashCode();
        result = 31 * result + (snippet != null ? snippet.hashCode() : 0);
        result = 31 * result + (isDraggable ? 1 : 0);
        result = 31 * result + (isVisible ? 1 : 0);
        return result;
    }
}
