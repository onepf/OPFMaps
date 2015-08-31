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

import android.support.annotation.NonNull;

import com.amazon.geo.mapsv2.model.BitmapDescriptor;
import com.amazon.geo.mapsv2.model.GroundOverlay;
import com.amazon.geo.mapsv2.model.LatLng;
import com.amazon.geo.mapsv2.model.LatLngBounds;

import org.onepf.opfmaps.delegate.model.GroundOverlayDelegate;
import org.onepf.opfmaps.model.OPFBitmapDescriptor;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFLatLngBounds;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
public final class AmazonGroundOverlayDelegate implements GroundOverlayDelegate {

    @NonNull
    private final GroundOverlay groundOverlay;

    public AmazonGroundOverlayDelegate(@NonNull final GroundOverlay groundOverlay) {
        this.groundOverlay = groundOverlay;
    }

    @Override
    public float getBearing() {
        return groundOverlay.getBearing();
    }

    @NonNull
    @Override
    public OPFLatLngBounds getBounds() {
        return new OPFLatLngBounds(new AmazonLatLngBoundsDelegate(groundOverlay.getBounds()));
    }

    @Override
    public float getHeight() {
        return groundOverlay.getHeight();
    }

    @NonNull
    @Override
    public String getId() {
        return groundOverlay.getId();
    }

    @NonNull
    @Override
    public OPFLatLng getPosition() {
        return new OPFLatLng(new AmazonLatLngDelegate(groundOverlay.getPosition()));
    }

    @Override
    public float getTransparency() {
        return groundOverlay.getTransparency();
    }

    @Override
    public float getWidth() {
        return groundOverlay.getWidth();
    }

    @Override
    public float getZIndex() {
        return groundOverlay.getZIndex();
    }

    @Override
    public boolean isVisible() {
        return groundOverlay.isVisible();
    }

    @Override
    public void remove() {
        groundOverlay.remove();
    }

    @Override
    public void setBearing(final float bearing) {
        groundOverlay.setBearing(bearing);
    }

    @Override
    public void setDimensions(final float width) {
        groundOverlay.setDimensions(width);
    }

    @Override
    public void setDimensions(final float width, final float height) {
        groundOverlay.setDimensions(width, height);
    }

    @Override
    public void setImage(@NonNull final OPFBitmapDescriptor image) {
        groundOverlay.setImage((BitmapDescriptor) image.getDelegate().getBitmapDescriptor());
    }

    @Override
    public void setPosition(@NonNull final OPFLatLng latLng) {
        groundOverlay.setPosition(new LatLng(latLng.getLat(), latLng.getLng()));
    }

    @Override
    public void setPositionFromBounds(@NonNull final OPFLatLngBounds bounds) {
        groundOverlay.setPositionFromBounds(new LatLngBounds(
                new LatLng(bounds.getSouthwest().getLat(), bounds.getSouthwest().getLng()),
                new LatLng(bounds.getNortheast().getLat(), bounds.getNortheast().getLng())
        ));
    }

    @Override
    public void setTransparency(final float transparency) {
        groundOverlay.setTransparency(transparency);
    }

    @Override
    public void setVisible(final boolean visible) {
        groundOverlay.setVisible(visible);
    }

    @Override
    public void setZIndex(final float zIndex) {
        groundOverlay.setZIndex(zIndex);
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof AmazonGroundOverlayDelegate
                && groundOverlay.equals(((AmazonGroundOverlayDelegate) other).groundOverlay));
    }

    @Override
    public int hashCode() {
        return groundOverlay.hashCode();
    }

    @Override
    public String toString() {
        return groundOverlay.toString();
    }
}
