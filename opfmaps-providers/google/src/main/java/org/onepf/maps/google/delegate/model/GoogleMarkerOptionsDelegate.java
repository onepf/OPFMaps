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

package org.onepf.maps.google.delegate.model;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import org.onepf.opfmaps.delegate.model.MarkerOptionsDelegate;
import org.onepf.opfmaps.model.OPFBitmapDescriptor;
import org.onepf.opfmaps.model.OPFLatLng;

/**
 * @author Roman Savin
 * @since 03.08.2015
 */
public final class GoogleMarkerOptionsDelegate implements MarkerOptionsDelegate {

    public static final Creator<GoogleMarkerOptionsDelegate> CREATOR = new Creator<GoogleMarkerOptionsDelegate>() {
        @Override
        public GoogleMarkerOptionsDelegate createFromParcel(final Parcel source) {
            return new GoogleMarkerOptionsDelegate(source);
        }

        @Override
        public GoogleMarkerOptionsDelegate[] newArray(final int size) {
            return new GoogleMarkerOptionsDelegate[size];
        }
    };

    @NonNull
    private final MarkerOptions markerOptions;

    public GoogleMarkerOptionsDelegate() {
        this.markerOptions = new MarkerOptions();
    }

    private GoogleMarkerOptionsDelegate(@NonNull final Parcel parcel) {
        this.markerOptions = parcel.readParcelable(MarkerOptions.class.getClassLoader());
    }

    @NonNull
    @Override
    public GoogleMarkerOptionsDelegate alpha(final float alpha) {
        markerOptions.alpha(alpha);
        return this;
    }

    @NonNull
    @Override
    public GoogleMarkerOptionsDelegate anchor(final float u, final float v) {
        markerOptions.anchor(u, v);
        return this;
    }

    @NonNull
    @Override
    public GoogleMarkerOptionsDelegate draggable(final boolean draggable) {
        markerOptions.draggable(draggable);
        return this;
    }

    @NonNull
    @Override
    public GoogleMarkerOptionsDelegate flat(final boolean flat) {
        markerOptions.flat(flat);
        return this;
    }

    @Override
    public float getAlpha() {
        return markerOptions.getAlpha();
    }

    @Override
    public float getAnchorU() {
        return markerOptions.getAnchorU();
    }

    @Override
    public float getAnchorV() {
        return markerOptions.getAnchorV();
    }

    @Nullable
    @Override
    public OPFBitmapDescriptor getIcon() {
        final BitmapDescriptor icon = markerOptions.getIcon();
        if (icon == null) {
            return new OPFBitmapDescriptor(new GoogleBitmapDescriptorDelegate(icon));
        }
        return null;
    }

    @Override
    public float getInfoWindowAnchorU() {
        return markerOptions.getInfoWindowAnchorU();
    }

    @Override
    public float getInfoWindowAnchorV() {
        return markerOptions.getInfoWindowAnchorV();
    }

    @Nullable
    @Override
    public OPFLatLng getPosition() {
        final LatLng position = markerOptions.getPosition();
        if (position != null) {
            return new OPFLatLng(new GoogleLatLngDelegate(position));
        }
        return null;
    }

    @Override
    public float getRotation() {
        return markerOptions.getRotation();
    }

    @Nullable
    @Override
    public String getSnippet() {
        return markerOptions.getSnippet();
    }

    @Nullable
    @Override
    public String getTitle() {
        return markerOptions.getTitle();
    }

    @NonNull
    @Override
    public GoogleMarkerOptionsDelegate icon(@NonNull final OPFBitmapDescriptor icon) {
        markerOptions.icon((BitmapDescriptor) icon.getDelegate().getBitmapDescriptor());
        return this;
    }

    @NonNull
    @Override
    public GoogleMarkerOptionsDelegate infoWindowAnchor(final float u, final float v) {
        markerOptions.infoWindowAnchor(u, v);
        return this;
    }

    @Override
    public boolean isDraggable() {
        return markerOptions.isDraggable();
    }

    @Override
    public boolean isFlat() {
        return markerOptions.isFlat();
    }

    @Override
    public boolean isVisible() {
        return markerOptions.isVisible();
    }

    @NonNull
    @Override
    public GoogleMarkerOptionsDelegate position(@NonNull final OPFLatLng position) {
        markerOptions.position(new LatLng(position.getLat(), position.getLng()));
        return this;
    }

    @NonNull
    @Override
    public GoogleMarkerOptionsDelegate rotation(final float rotation) {
        markerOptions.rotation(rotation);
        return this;
    }

    @NonNull
    @Override
    public GoogleMarkerOptionsDelegate snippet(@NonNull final String snippet) {
        markerOptions.snippet(snippet);
        return this;
    }

    @NonNull
    @Override
    public GoogleMarkerOptionsDelegate title(@NonNull final String title) {
        markerOptions.title(title);
        return this;
    }

    @NonNull
    @Override
    public GoogleMarkerOptionsDelegate visible(final boolean visible) {
        markerOptions.visible(visible);
        return this;
    }

    @Override
    public int describeContents() {
        return markerOptions.describeContents();
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(markerOptions, flags);
    }

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof GoogleMarkerOptionsDelegate)) return false;

        return markerOptions.equals(((GoogleMarkerOptionsDelegate) other).markerOptions);
    }
    //CHECKSTYLE:ON

    @Override
    public int hashCode() {
        return markerOptions.hashCode();
    }

    @Override
    public String toString() {
        return markerOptions.toString();
    }
}
