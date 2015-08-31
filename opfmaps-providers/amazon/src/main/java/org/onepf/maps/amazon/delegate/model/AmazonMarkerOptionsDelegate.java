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

package org.onepf.maps.amazon.delegate.model;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.amazon.geo.mapsv2.model.BitmapDescriptor;
import com.amazon.geo.mapsv2.model.LatLng;
import com.amazon.geo.mapsv2.model.MarkerOptions;
import org.onepf.opfmaps.delegate.model.MarkerOptionsDelegate;
import org.onepf.opfmaps.model.OPFBitmapDescriptor;
import org.onepf.opfmaps.model.OPFLatLng;

/**
 * @author Roman Savin
 * @since 03.08.2015
 */
public final class AmazonMarkerOptionsDelegate implements MarkerOptionsDelegate {

    public static final Creator<AmazonMarkerOptionsDelegate> CREATOR = new Creator<AmazonMarkerOptionsDelegate>() {
        @Override
        public AmazonMarkerOptionsDelegate createFromParcel(final Parcel source) {
            return new AmazonMarkerOptionsDelegate(source);
        }

        @Override
        public AmazonMarkerOptionsDelegate[] newArray(final int size) {
            return new AmazonMarkerOptionsDelegate[size];
        }
    };

    @NonNull
    private final MarkerOptions markerOptions;

    public AmazonMarkerOptionsDelegate() {
        this.markerOptions = new MarkerOptions();
    }

    private AmazonMarkerOptionsDelegate(@NonNull final Parcel parcel) {
        this.markerOptions = parcel.readParcelable(MarkerOptions.class.getClassLoader());
    }

    @NonNull
    @Override
    public AmazonMarkerOptionsDelegate alpha(final float alpha) {
        markerOptions.alpha(alpha);
        return this;
    }

    @NonNull
    @Override
    public AmazonMarkerOptionsDelegate anchor(final float u, final float v) {
        markerOptions.anchor(u, v);
        return this;
    }

    @NonNull
    @Override
    public AmazonMarkerOptionsDelegate draggable(final boolean draggable) {
        markerOptions.draggable(draggable);
        return this;
    }

    @NonNull
    @Override
    public AmazonMarkerOptionsDelegate flat(final boolean flat) {
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
        if (icon != null) {
            return new OPFBitmapDescriptor(new AmazonBitmapDescriptorDelegate(icon));
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
            return new OPFLatLng(new AmazonLatLngDelegate(position));
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
    public AmazonMarkerOptionsDelegate icon(@NonNull final OPFBitmapDescriptor icon) {
        markerOptions.icon((BitmapDescriptor) icon.getDelegate().getBitmapDescriptor());
        return this;
    }

    @NonNull
    @Override
    public AmazonMarkerOptionsDelegate infoWindowAnchor(final float u, final float v) {
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
    public AmazonMarkerOptionsDelegate position(@NonNull final OPFLatLng position) {
        markerOptions.position(new LatLng(position.getLat(), position.getLng()));
        return this;
    }

    @NonNull
    @Override
    public AmazonMarkerOptionsDelegate rotation(final float rotation) {
        markerOptions.rotation(rotation);
        return this;
    }

    @NonNull
    @Override
    public AmazonMarkerOptionsDelegate snippet(@NonNull final String snippet) {
        markerOptions.snippet(snippet);
        return this;
    }

    @NonNull
    @Override
    public AmazonMarkerOptionsDelegate title(@NonNull final String title) {
        markerOptions.title(title);
        return this;
    }

    @NonNull
    @Override
    public AmazonMarkerOptionsDelegate visible(final boolean visible) {
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

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof AmazonMarkerOptionsDelegate
                && markerOptions.equals(((AmazonMarkerOptionsDelegate) other).markerOptions));
    }

    @Override
    public int hashCode() {
        return markerOptions.hashCode();
    }

    @Override
    public String toString() {
        return markerOptions.toString();
    }
}
