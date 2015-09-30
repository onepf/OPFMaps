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

package org.onepf.opfmaps.amazon.delegate.model;

import android.support.annotation.NonNull;
import com.amazon.geo.mapsv2.model.LatLng;
import com.amazon.geo.mapsv2.model.LatLngBounds;
import org.onepf.opfmaps.delegate.model.GroundOverlayDelegate;
import org.onepf.opfmaps.model.OPFBitmapDescriptor;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFLatLngBounds;
import org.onepf.opfutils.OPFLog;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
public final class AmazonGroundOverlayDelegate implements GroundOverlayDelegate {

    @Override
    public float getBearing() {
        return 0.0f;
    }

    @NonNull
    @Override
    public OPFLatLngBounds getBounds() {
        return new OPFLatLngBounds(new AmazonLatLngBoundsDelegate(new LatLngBounds(new LatLng(0, 0), new LatLng(0, 0))));
    }

    @Override
    public float getHeight() {
        return 0.0f;
    }

    @NonNull
    @Override
    public String getId() {
        return "";
    }

    @NonNull
    @Override
    public OPFLatLng getPosition() {
        return new OPFLatLng(new AmazonLatLngDelegate(new LatLng(0, 0)));
    }

    @Override
    public float getTransparency() {
        return 0.0f;
    }

    @Override
    public float getWidth() {
        return 0.0f;
    }

    @Override
    public float getZIndex() {
        return 0.0f;
    }

    @Override
    public boolean isVisible() {
        return false;
    }

    @Override
    public void remove() {
        OPFLog.logStubCall();
    }

    @Override
    public void setBearing(final float bearing) {
        OPFLog.logStubCall(bearing);
    }

    @Override
    public void setDimensions(final float width) {
        OPFLog.logStubCall(width);
    }

    @Override
    public void setDimensions(final float width, final float height) {
        OPFLog.logStubCall(width, height);
    }

    @Override
    public void setImage(@NonNull final OPFBitmapDescriptor image) {
        OPFLog.logStubCall(image);
    }

    @Override
    public void setPosition(@NonNull final OPFLatLng latLng) {
        OPFLog.logStubCall(latLng);
    }

    @Override
    public void setPositionFromBounds(@NonNull final OPFLatLngBounds bounds) {
        OPFLog.logStubCall(bounds);
    }

    @Override
    public void setTransparency(final float transparency) {
        OPFLog.logStubCall(transparency);
    }

    @Override
    public void setVisible(final boolean visible) {
        OPFLog.logStubCall(visible);
    }

    @Override
    public void setZIndex(final float zIndex) {
        OPFLog.logStubCall(zIndex);
    }
}
