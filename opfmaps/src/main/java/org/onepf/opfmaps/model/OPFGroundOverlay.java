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

import android.support.annotation.NonNull;
import org.onepf.opfmaps.delegate.model.GroundOverlayDelegate;

/**
 * Created by akarimova on 15.06.15.
 */
public final class OPFGroundOverlay implements GroundOverlayDelegate {

    @NonNull
    private final GroundOverlayDelegate delegate;

    public OPFGroundOverlay(@NonNull final GroundOverlayDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public float getBearing() {
        return delegate.getBearing();
    }

    @NonNull
    @Override
    public OPFLatLngBounds getBounds() {
        return delegate.getBounds();
    }

    @Override
    public float getHeight() {
        return delegate.getHeight();
    }

    @NonNull
    @Override
    public String getId() {
        return delegate.getId();
    }

    @NonNull
    @Override
    public OPFLatLng getPosition() {
        return delegate.getPosition();
    }

    @Override
    public float getTransparency() {
        return delegate.getTransparency();
    }

    @Override
    public float getWidth() {
        return delegate.getWidth();
    }

    @Override
    public float getZIndex() {
        return delegate.getZIndex();
    }

    @Override
    public boolean isVisible() {
        return delegate.isVisible();
    }

    @Override
    public void remove() {
        delegate.remove();
    }

    @Override
    public void setBearing(final float bearing) {
        delegate.setBearing(bearing);
    }

    @Override
    public void setDimensions(final float width) {
        delegate.setDimensions(width);
    }

    @Override
    public void setDimensions(final float width, final float height) {
        delegate.setDimensions(width, height);
    }

    @Override
    public void setImage(@NonNull final OPFBitmapDescriptor image) {
        delegate.setImage(image);
    }

    @Override
    public void setPosition(@NonNull final OPFLatLng position) {
        delegate.setPosition(position);
    }

    @Override
    public void setPositionFromBounds(@NonNull final OPFLatLngBounds bounds) {
        delegate.setPositionFromBounds(bounds);
    }

    @Override
    public void setTransparency(final boolean transparency) {
        delegate.setTransparency(transparency);
    }

    @Override
    public void setVisible(final boolean visible) {
        delegate.setVisible(visible);
    }

    @Override
    public void setZIndex(final float zIndex) {
        delegate.setZIndex(zIndex);
    }

    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OPFGroundOverlay)) return false;

        return delegate.equals(((OPFGroundOverlay) other).delegate);
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
