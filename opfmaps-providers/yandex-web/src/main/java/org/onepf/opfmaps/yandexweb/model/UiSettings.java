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

package org.onepf.opfmaps.yandexweb.model;

import android.support.annotation.NonNull;

import org.onepf.opfmaps.yandexweb.delegate.YaWebMapViewDelegate;
import org.onepf.opfutils.OPFLog;

/**
 * @author Roman Savin
 * @since 17.08.2015
 */
public final class UiSettings {

    @NonNull
    private final YaWebMapViewDelegate map;

    public UiSettings(@NonNull final YaWebMapViewDelegate map) {
        this.map = map;
    }

    public boolean isCompassEnabled() {
        return false;
    }

    public boolean isIndoorLevelPickerEnabled() {
        return false;
    }

    public boolean isMapToolbarEnabled() {
        return false;
    }

    public boolean isMyLocationButtonEnabled() {
        return map.isMyLocationButtonEnabled();
    }

    public boolean isRotateGesturesEnabled() {
        return false;
    }

    public boolean isScrollGesturesEnabled() {
        return map.isScrollGesturesEnabled();
    }

    public boolean isTiltGesturesEnabled() {
        return false;
    }

    public boolean isZoomControlsEnabled() {
        return map.isZoomControlsEnabled();
    }

    public boolean isZoomGesturesEnabled() {
        return map.isZoomGesturesEnabled();
    }

    public void setAllGesturesEnabled(final boolean enabled) {
        map.setScrollGesturesEnabled(enabled);
        map.setZoomGesturesEnabled(enabled);
    }

    public void setCompassEnabled(final boolean enabled) {
        OPFLog.logStubCall(enabled);
    }

    public void setIndoorLevelPickerEnabled(final boolean enabled) {
        OPFLog.logStubCall(enabled);
    }

    public void setMapToolbarEnabled(final boolean enabled) {
        OPFLog.logStubCall(enabled);
    }

    public void setMyLocationButtonEnabled(final boolean enabled) {
        map.setMyLocationButtonEnabled(enabled);
    }

    public void setRotateGesturesEnabled(final boolean enabled) {
        OPFLog.logStubCall(enabled);
    }

    public void setScrollGesturesEnabled(final boolean enabled) {
        map.setScrollGesturesEnabled(enabled);
    }

    public void setTiltGesturesEnabled(final boolean enabled) {
        OPFLog.logStubCall(enabled);
    }

    public void setZoomControlsEnabled(final boolean enabled) {
        map.setZoomControlsEnabled(enabled);
    }

    public void setZoomGesturesEnabled(final boolean enabled) {
        map.setZoomGesturesEnabled(enabled);
    }
}
