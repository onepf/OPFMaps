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

package org.onepf.opfmaps.osmdroid.model;

import android.support.annotation.NonNull;

import org.onepf.opfmaps.osmdroid.delegate.OsmdroidMapViewDelegate;
import org.onepf.opfutils.OPFLog;

/**
 * @author Roman Savin
 * @since 17.08.2015
 */
public final class UiSettings {

    @NonNull
    private final OsmdroidMapViewDelegate mapView;

    public UiSettings(@NonNull final OsmdroidMapViewDelegate mapView) {
        this.mapView = mapView;
    }

    public boolean isCompassEnabled() {
        return mapView.isCompassEnabled();
    }

    public boolean isIndoorLevelPickerEnabled() {
        return false;
    }

    public boolean isMapToolbarEnabled() {
        return false;
    }

    public boolean isMyLocationButtonEnabled() {
        return mapView.isMyLocationButtonEnabled();
    }

    public boolean isRotateGesturesEnabled() {
        return mapView.isRotateGesturesEnabled();
    }

    public boolean isScrollGesturesEnabled() {
        return mapView.isScrollGesturesEnabled();
    }

    public boolean isTiltGesturesEnabled() {
        return false;
    }

    public boolean isZoomControlsEnabled() {
        return mapView.isZoomControlsEnabled();
    }

    public boolean isZoomGesturesEnabled() {
        return mapView.isZoomGesturesEnabled();
    }

    public void setAllGesturesEnabled(final boolean enabled) {
        mapView.setRotateGesturesEnabled(enabled);
        mapView.setMultiTouchControls(enabled);
        mapView.setScrollGesturesEnabled(enabled);
    }

    public void setCompassEnabled(final boolean enabled) {
        mapView.setCompassEnabled(enabled);
    }

    public void setIndoorLevelPickerEnabled(final boolean enabled) {
        OPFLog.logStubCall(enabled);
    }

    public void setMapToolbarEnabled(final boolean enabled) {
        OPFLog.logStubCall(enabled);
    }

    public void setMyLocationButtonEnabled(final boolean enabled) {
        mapView.setMyLocationButtonEnabled(enabled);
    }

    public void setRotateGesturesEnabled(final boolean enabled) {
        mapView.setRotateGesturesEnabled(enabled);
    }

    public void setScrollGesturesEnabled(final boolean enabled) {
        mapView.setScrollGesturesEnabled(enabled);
    }

    public void setTiltGesturesEnabled(final boolean enabled) {
        OPFLog.logStubCall(enabled);
    }

    public void setZoomControlsEnabled(final boolean enabled) {
        mapView.setBuiltInZoomControls(enabled);
    }

    public void setZoomGesturesEnabled(final boolean enabled) {
        mapView.setMultiTouchControls(enabled);
    }
}
