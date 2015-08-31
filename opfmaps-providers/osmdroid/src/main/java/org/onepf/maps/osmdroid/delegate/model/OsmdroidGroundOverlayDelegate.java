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

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.onepf.opfmaps.delegate.model.GroundOverlayDelegate;
import org.onepf.opfmaps.model.OPFBitmapDescriptor;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFLatLngBounds;
import org.onepf.opfutils.OPFLog;
import org.osmdroid.bonuspack.overlays.GroundOverlay;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
public final class OsmdroidGroundOverlayDelegate implements GroundOverlayDelegate {

    @Nullable
    private MapView mapView;

    @NonNull
    private final GroundOverlay groundOverlay;

    @NonNull
    private final String id;

    public OsmdroidGroundOverlayDelegate(@SuppressWarnings("NullableProblems") @NonNull final MapView mapView,
                                         @NonNull final GroundOverlay groundOverlay) {
        this.mapView = mapView;
        this.groundOverlay = groundOverlay;
        this.id = Integer.toString(this.groundOverlay.hashCode());
    }

    @Override
    public float getBearing() {
        return groundOverlay.getBearing();
    }

    @NonNull
    @Override
    public OPFLatLngBounds getBounds() {
        return new OPFLatLngBounds(new OsmdroidLatLngBoundsDelegate(groundOverlay.getBoundingBox()));
    }

    @Override
    public float getHeight() {
        return groundOverlay.getHeight();
    }

    @NonNull
    @Override
    public String getId() {
        return id;
    }

    @NonNull
    @Override
    public OPFLatLng getPosition() {
        return new OPFLatLng(new OsmdroidLatLngDelegate(groundOverlay.getPosition()));
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
        return 0;
    }

    @Override
    public boolean isVisible() {
        return groundOverlay.isEnabled();
    }

    @Override
    public void remove() {
        if (mapView != null) {
            mapView.getOverlays().remove(groundOverlay);
            mapView.invalidate();
            mapView = null;
        }
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
        groundOverlay.setImage((Drawable) image.getDelegate().getBitmapDescriptor());
    }

    @Override
    public void setPosition(@NonNull final OPFLatLng latLng) {
        groundOverlay.setPosition(new GeoPoint(latLng.getLat(), latLng.getLng()));
    }

    @Override
    public void setPositionFromBounds(@NonNull final OPFLatLngBounds bounds) {
        groundOverlay.setPosition(new GeoPoint(bounds.getCenter().getLat(), bounds.getCenter().getLng()));
    }

    @Override
    public void setTransparency(final float transparency) {
        groundOverlay.setTransparency(transparency);
    }

    @Override
    public void setVisible(final boolean visible) {
        groundOverlay.setEnabled(visible);
    }

    @Override
    public void setZIndex(final float zIndex) {
        OPFLog.logStubCall(zIndex);
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OsmdroidGroundOverlayDelegate
                && groundOverlay.equals(((OsmdroidGroundOverlayDelegate) other).groundOverlay));
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
