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

import android.support.annotation.NonNull;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class GroundOverlay {

    GroundOverlay() {
        //todo implement
    }

    public float getBearing() {
        return 0;
    }

    @NonNull
    public String getId() {
        //todo implement
        return null;
    }

    @NonNull
    public LatLngBounds getBounds() {
        //todo implement
        return null;
    }

    public float getHeight() {
        //todo implement
        return 0;
    }

    @NonNull
    public LatLng getPosition() {
        //todo implement
        return null;
    }

    public float getTransparency() {
        //todo implement
        return 0;
    }

    public float getWidth() {
        //todo implement
        return 0;
    }

    public float getZIndex() {
        //todo implement
        return 0;
    }

    public boolean isVisible() {
        //todo implement
        return false;
    }

    public void remove() {
        //todo implement
    }

    public void setBearing(final float bearing) {
        //todo implement
    }

    public void setDimensions(final float width) {
        //todo implement
    }

    public void setDimensions(final float width, final float height) {
        //todo implement
    }

    public void setImage(@NonNull final BitmapDescriptor image) {
        //todo implement
    }

    public void setPosition(@NonNull final LatLng latLng) {
        //todo implement
    }

    public void setPositionFromBounds(@NonNull final LatLngBounds bounds) {
        //todo implement
    }

    public void setTransparency(final float transparency) {
        //todo implement
    }

    public void setVisible(final boolean visible) {
        //todo implement
    }

    public void setZIndex(final float zIndex) {
        //todo implement
    }

    @Override
    public boolean equals(final Object other) {
        //todo implement
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        //todo implement
        return super.hashCode();
    }

    @Override
    public String toString() {
        //todo implement
        return super.toString();
    }
}
