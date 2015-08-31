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

import org.onepf.opfmaps.delegate.model.MarkerDelegate;
import org.onepf.opfmaps.model.OPFBitmapDescriptor;
import org.onepf.opfmaps.model.OPFLatLng;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
public final class OsmdroidMarkerDelegate implements MarkerDelegate {

    @Nullable
    private MapView mapView;

    @NonNull
    private final Marker marker;

    @NonNull
    private final String id;

    @SuppressWarnings("NullableProblems")
    public OsmdroidMarkerDelegate(@NonNull final MapView mapView, @NonNull final Marker marker) {
        this.marker = marker;
        this.mapView = mapView;
        this.id = Integer.toString(this.marker.hashCode());
    }

    @Override
    public float getAlpha() {
        return marker.getAlpha();
    }

    @NonNull
    @Override
    public String getId() {
        return id;
    }

    @NonNull
    @Override
    public OPFLatLng getPosition() {
        return new OPFLatLng(new OsmdroidLatLngDelegate(marker.getPosition()));
    }

    @Override
    public float getRotation() {
        return marker.getRotation();
    }

    @NonNull
    @Override
    public String getSnippet() {
        return marker.getSnippet();
    }

    @NonNull
    @Override
    public String getTitle() {
        return marker.getTitle();
    }

    @Override
    public void hideInfoWindow() {
        marker.closeInfoWindow();
    }

    @Override
    public boolean isDraggable() {
        return marker.isDraggable();
    }

    @Override
    public boolean isFlat() {
        return marker.isFlat();
    }

    @Override
    public boolean isInfoWindowShown() {
        return marker.isInfoWindowShown();
    }

    @Override
    public boolean isVisible() {
        return marker.isEnabled();
    }

    @Override
    public void remove() {
        if (mapView != null) {
            marker.remove(mapView);
            mapView = null;
        }
    }

    @Override
    public void setAlpha(final float alpha) {
        marker.setAlpha(alpha);
    }

    @Override
    public void setAnchor(final float anchorU, final float anchorV) {
        marker.setAnchor(anchorU, anchorV);
    }

    @Override
    public void setDraggable(final boolean draggable) {
        marker.setDraggable(draggable);
    }

    @Override
    public void setFlat(final boolean flat) {
        marker.setFlat(flat);
    }

    @Override
    public void setIcon(@NonNull final OPFBitmapDescriptor icon) {
        marker.setIcon((Drawable) icon.getDelegate().getBitmapDescriptor());
    }

    @Override
    public void setInfoWindowAnchor(final float anchorU, final float anchorV) {
        marker.setInfoWindowAnchor(anchorU, anchorV);
    }

    @Override
    public void setPosition(@NonNull final OPFLatLng latLng) {
        marker.setPosition(new GeoPoint(latLng.getLat(), latLng.getLng()));
    }

    @Override
    public void setRotation(final float rotation) {
        marker.setRotation(rotation);
    }

    @Override
    public void setSnippet(@NonNull final String snippet) {
        marker.setSnippet(snippet);
    }

    @Override
    public void setTitle(@NonNull final String title) {
        marker.setTitle(title);
    }

    @Override
    public void setVisible(final boolean visible) {
        marker.setEnabled(visible);
    }

    @Override
    public void showInfoWindow() {
        marker.showInfoWindow();
    }

    @Override
    public int hashCode() {
        return marker.hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OsmdroidMarkerDelegate
                && marker.equals(((OsmdroidMarkerDelegate) other).marker));
    }

    @Override
    public String toString() {
        return marker.toString();
    }
}
