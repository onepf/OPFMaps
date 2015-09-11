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
import org.onepf.opfutils.OPFLog;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class GroundOverlay {

    public float getBearing() {
        return 0;
    }

    @NonNull
    public String getId() {
        return "";
    }

    @NonNull
    public LatLngBounds getBounds() {
        return new LatLngBounds(new LatLng(0, 0), new LatLng(0, 0));
    }

    public float getHeight() {
        return 0;
    }

    @NonNull
    public LatLng getPosition() {
        return new LatLng(0, 0);
    }

    public float getTransparency() {
        return 0;
    }

    public float getWidth() {
        return 0;
    }

    public float getZIndex() {
        return 0;
    }

    public boolean isVisible() {
        return false;
    }

    public void remove() {
        OPFLog.logStubCall();
    }

    public void setBearing(final float bearing) {
        OPFLog.logStubCall(bearing);
    }

    public void setDimensions(final float width) {
        OPFLog.logStubCall(width);
    }

    public void setDimensions(final float width, final float height) {
        OPFLog.logStubCall(width, height);
    }

    public void setImage(@NonNull final BitmapDescriptor image) {
        OPFLog.logStubCall(image);
    }

    public void setPosition(@NonNull final LatLng latLng) {
        OPFLog.logStubCall(latLng);
    }

    public void setPositionFromBounds(@NonNull final LatLngBounds bounds) {
        OPFLog.logStubCall(bounds);
    }

    public void setTransparency(final float transparency) {
        OPFLog.logStubCall(transparency);
    }

    public void setVisible(final boolean visible) {
        OPFLog.logStubCall(visible);
    }

    public void setZIndex(final float zIndex) {
        OPFLog.logStubCall(zIndex);
    }
}
