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
import org.onepf.opfmaps.OPFMap;
import org.onepf.opfmaps.OPFMapHelper;
import org.onepf.opfmaps.delegate.model.MarkerOptionsDelegate;

/**
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

    public OPFMarkerOptions() {
        this.delegate = OPFMapHelper.getInstance().getDelegatesFactory().createMarkerOptionsDelegate();
    }

    private OPFMarkerOptions(@NonNull final Parcel parcel) {
        try {
            this.delegate = parcel.readParcelable(Class.forName(parcel.readString()).getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    @Override
    public OPFMarkerOptions alpha(final float alpha) {
        delegate.alpha(alpha);
        return this;
    }

    @NonNull
    @Override
    public OPFMarkerOptions anchor(final float u, final float v) {
        delegate.anchor(u, v);
        return this;
    }

    @NonNull
    @Override
    public OPFMarkerOptions draggable(final boolean draggable) {
        delegate.draggable(draggable);
        return this;
    }

    @NonNull
    @Override
    public OPFMarkerOptions flat(final boolean flat) {
        delegate.flat(flat);
        return this;
    }

    @Override
    public float getAlpha() {
        return delegate.getAlpha();
    }

    @Override
    public float getAnchorU() {
        return delegate.getAnchorU();
    }

    @Override
    public float getAnchorV() {
        return delegate.getAnchorV();
    }

    @Nullable
    @Override
    public OPFBitmapDescriptor getIcon() {
        return delegate.getIcon();
    }

    @Override
    public float getInfoWindowAnchorU() {
        return delegate.getInfoWindowAnchorU();
    }

    @Override
    public float getInfoWindowAnchorV() {
        return delegate.getInfoWindowAnchorV();
    }

    @Nullable
    @Override
    public OPFLatLng getPosition() {
        return delegate.getPosition();
    }

    @Override
    public float getRotation() {
        return delegate.getRotation();
    }

    @Nullable
    @Override
    public String getSnippet() {
        return delegate.getSnippet();
    }

    @Nullable
    @Override
    public String getTitle() {
        return delegate.getTitle();
    }

    @NonNull
    @Override
    public OPFMarkerOptions icon(@NonNull final OPFBitmapDescriptor icon) {
        delegate.icon(icon);
        return this;
    }

    @NonNull
    @Override
    public OPFMarkerOptions infoWindowAnchor(final float u, final float v) {
        delegate.infoWindowAnchor(u, v);
        return this;
    }

    @Override
    public boolean isDraggable() {
        return delegate.isDraggable();
    }

    @Override
    public boolean isFlat() {
        return delegate.isFlat();
    }

    @Override
    public boolean isVisible() {
        return delegate.isVisible();
    }

    @NonNull
    @Override
    public OPFMarkerOptions position(@NonNull final OPFLatLng position) {
        delegate.position(position);
        return this;
    }

    @NonNull
    @Override
    public OPFMarkerOptions rotation(final float rotation) {
        delegate.rotation(rotation);
        return this;
    }

    @NonNull
    @Override
    public OPFMarkerOptions snippet(@NonNull final String snippet) {
        delegate.snippet(snippet);
        return this;
    }

    @NonNull
    @Override
    public OPFMarkerOptions title(@NonNull final String title) {
        delegate.title(title);
        return this;
    }

    @NonNull
    @Override
    public MarkerOptionsDelegate visible(final boolean visible) {
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
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OPFMarkerOptions)) return false;

        return delegate.equals(((OPFMarkerOptions) other).delegate);
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
