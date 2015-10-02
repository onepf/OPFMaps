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

package org.onepf.opfmaps.yandexweb.delegate.model;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import org.onepf.opfmaps.delegate.model.GroundOverlayOptionsDelegate;
import org.onepf.opfmaps.model.OPFBitmapDescriptor;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFLatLngBounds;
import org.onepf.opfutils.OPFLog;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class YaWebGroundOverlayOptionsDelegate implements GroundOverlayOptionsDelegate {

    public static final Creator<YaWebGroundOverlayOptionsDelegate> CREATOR = new Creator<YaWebGroundOverlayOptionsDelegate>() {
        @Override
        public YaWebGroundOverlayOptionsDelegate createFromParcel(final Parcel source) {
            return new YaWebGroundOverlayOptionsDelegate();
        }

        @Override
        public YaWebGroundOverlayOptionsDelegate[] newArray(final int size) {
            return new YaWebGroundOverlayOptionsDelegate[size];
        }
    };

    @NonNull
    @Override
    public YaWebGroundOverlayOptionsDelegate anchor(final float u, final float v) {
        OPFLog.logStubCall(u, v);
        return this;
    }

    @NonNull
    @Override
    public YaWebGroundOverlayOptionsDelegate bearing(final float bearing) {
        OPFLog.logStubCall(bearing);
        return this;
    }

    @Override
    public float getAnchorU() {
        return 0.0f;
    }

    @Override
    public float getAnchorV() {
        return 0.0f;
    }

    @Override
    public float getBearing() {
        return 0.0f;
    }

    @Nullable
    @Override
    public OPFLatLngBounds getBounds() {
        return null;
    }

    @Override
    public float getHeight() {
        return 0.0f;
    }

    @Nullable
    @Override
    public OPFBitmapDescriptor getImage() {
        return null;
    }

    @Nullable
    @Override
    public OPFLatLng getLocation() {
        return null;
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

    @NonNull
    @Override
    public YaWebGroundOverlayOptionsDelegate image(@NonNull final OPFBitmapDescriptor image) {
        OPFLog.logStubCall(image);
        return this;
    }

    @Override
    public boolean isVisible() {
        return false;
    }

    @NonNull
    @Override
    public YaWebGroundOverlayOptionsDelegate position(@NonNull final OPFLatLng location,
                                                      final float width,
                                                      final float height) {
        OPFLog.logStubCall(location, width, height);
        return this;
    }

    @NonNull
    @Override
    public YaWebGroundOverlayOptionsDelegate position(@NonNull final OPFLatLng location, final float width) {
        OPFLog.logStubCall(location, width);
        return this;
    }

    @NonNull
    @Override
    public YaWebGroundOverlayOptionsDelegate positionFromBounds(@NonNull final OPFLatLngBounds bounds) {
        OPFLog.logStubCall(bounds);
        return this;
    }

    @NonNull
    @Override
    public YaWebGroundOverlayOptionsDelegate transparency(final float transparency) {
        OPFLog.logStubCall(transparency);
        return this;
    }

    @NonNull
    @Override
    public YaWebGroundOverlayOptionsDelegate visible(final boolean visible) {
        OPFLog.logStubCall(visible);
        return this;
    }

    @NonNull
    @Override
    public YaWebGroundOverlayOptionsDelegate zIndex(final float zIndex) {
        OPFLog.logStubCall(zIndex);
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        //nothing
    }
}
