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

import org.onepf.maps.yandexweb.delegate.YaWebMapViewDelegate;

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
        //todo implement
        return false;
    }

    public boolean isIndoorLevelPickerEnabled() {
        //todo implement
        return false;
    }

    public boolean isMapToolbarEnabled() {
        //todo implement
        return false;
    }

    public boolean isMyLocationButtonEnabled() {
        //todo implement
        return false;
    }

    public boolean isRotateGesturesEnabled() {
        //todo implement
        return false;
    }

    public boolean isScrollGesturesEnabled() {
        //todo implement
        return false;
    }

    public boolean isTiltGesturesEnabled() {
        //todo implement
        return false;
    }

    public boolean isZoomControlsEnabled() {
        //todo implement
        return false;
    }

    public boolean isZoomGesturesEnabled() {
        //todo implement
        return false;
    }

    public void setAllGesturesEnabled(final boolean enabled) {
        //todo implement
    }

    public void setCompassEnabled(final boolean enabled) {
        //todo implement
    }

    public void setIndoorLevelPickerEnabled(final boolean enabled) {
        //todo implement
    }

    public void setMapToolbarEnabled(final boolean enabled) {
        //todo implement
    }

    public void setMyLocationButtonEnabled(final boolean enabled) {
        //todo implement
    }

    public void setRotateGesturesEnabled(final boolean enabled) {
        //todo implement
    }

    public void setScrollGesturesEnabled(final boolean enabled) {
        //todo implement
    }

    public void setTiltGesturesEnabled(final boolean enabled) {
        //todo implement
    }

    public void setZoomControlsEnabled(final boolean enabled) {
        //todo implement
    }

    public void setZoomGesturesEnabled(final boolean enabled) {
        //todo implement
    }
}
