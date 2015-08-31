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
import com.amazon.geo.mapsv2.model.LatLng;
import com.amazon.geo.mapsv2.model.Marker;

import org.onepf.opfmaps.delegate.model.MarkerDelegate;
import org.onepf.opfmaps.model.OPFBitmapDescriptor;
import org.onepf.opfmaps.model.OPFLatLng;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
public final class AmazonMarkerDelegate implements MarkerDelegate {

    @NonNull
    private final Marker marker;

    public AmazonMarkerDelegate(@NonNull final Marker marker) {
        this.marker = marker;
    }

    @Override
    public float getAlpha() {
        return marker.getAlpha();
    }

    @NonNull
    @Override
    public String getId() {
        return marker.getId();
    }

    @NonNull
    @Override
    public OPFLatLng getPosition() {
        return new OPFLatLng(new AmazonLatLngDelegate(marker.getPosition()));
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
        marker.hideInfoWindow();
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
        return marker.isVisible();
    }

    @Override
    public void remove() {
        marker.remove();
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
        marker.setIcon((BitmapDescriptor) icon.getDelegate().getBitmapDescriptor());
    }

    @Override
    public void setInfoWindowAnchor(final float anchorU, final float anchorV) {
        marker.setInfoWindowAnchor(anchorU, anchorV);
    }

    @Override
    public void setPosition(@NonNull final OPFLatLng latLng) {
        marker.setPosition(new LatLng(latLng.getLat(), latLng.getLng()));
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
        marker.setVisible(visible);
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
                && (other == this || other instanceof AmazonMarkerDelegate
                && marker.equals(((AmazonMarkerDelegate) other).marker));
    }

    @Override
    public String toString() {
        return marker.toString();
    }
}
