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

import com.amazon.geo.mapsv2.UiSettings;

import org.onepf.opfmaps.delegate.model.UiSettingsDelegate;
import org.onepf.opfutils.OPFLog;

/**
 * @author Roman Savin
 * @since 06.08.2015
 */
public final class AmazonUiSettingsDelegate implements UiSettingsDelegate {

    @NonNull
    private final UiSettings uiSettings;

    private boolean isZoomGesturesEnabled;

    public AmazonUiSettingsDelegate(@NonNull final UiSettings uiSettings) {
        this.uiSettings = uiSettings;
        this.isZoomGesturesEnabled = uiSettings.isZoomGesturesEnabled();
    }

    @Override
    public void setZoomControlsEnabled(final boolean enabled) {
        uiSettings.setZoomControlsEnabled(enabled);
    }

    @Override
    public void setCompassEnabled(final boolean enabled) {
        uiSettings.setCompassEnabled(enabled);
    }

    @Override
    public void setMyLocationButtonEnabled(final boolean enabled) {
        uiSettings.setMyLocationButtonEnabled(enabled);
    }

    @Override
    public void setIndoorLevelPickerEnabled(final boolean enabled) {
        OPFLog.logStubCall(enabled);
    }

    @Override
    public void setScrollGesturesEnabled(final boolean enabled) {
        uiSettings.setScrollGesturesEnabled(enabled);
    }

    @Override
    public void setZoomGesturesEnabled(final boolean enabled) {
        uiSettings.setZoomGesturesEnabled(enabled);
        isZoomGesturesEnabled = enabled;
    }

    @Override
    public void setTiltGesturesEnabled(final boolean enabled) {
        uiSettings.setTiltGesturesEnabled(enabled);
    }

    @Override
    public void setRotateGesturesEnabled(final boolean enabled) {
        uiSettings.setRotateGesturesEnabled(enabled);
    }

    @Override
    public void setAllGesturesEnabled(final boolean enabled) {
        uiSettings.setAllGesturesEnabled(enabled);
    }

    @Override
    public void setMapToolbarEnabled(final boolean enabled) {
        uiSettings.setMapToolbarEnabled(enabled);
    }

    @Override
    public boolean isZoomControlsEnabled() {
        return uiSettings.isZoomControlsEnabled();
    }

    @Override
    public boolean isCompassEnabled() {
        return uiSettings.isCompassEnabled();
    }

    @Override
    public boolean isMyLocationButtonEnabled() {
        return uiSettings.isMyLocationButtonEnabled();
    }

    @Override
    public boolean isIndoorLevelPickerEnabled() {
        return false;
    }

    @Override
    public boolean isScrollGesturesEnabled() {
        return uiSettings.isScrollGesturesEnabled();
    }

    @Override
    public boolean isZoomGesturesEnabled() {
        return isZoomGesturesEnabled;
    }

    @Override
    public boolean isTiltGesturesEnabled() {
        return uiSettings.isTiltGesturesEnabled();
    }

    @Override
    public boolean isRotateGesturesEnabled() {
        return uiSettings.isRotateGesturesEnabled();
    }

    @Override
    public boolean isMapToolbarEnabled() {
        return uiSettings.isMapToolbarEnabled();
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof AmazonUiSettingsDelegate
                && uiSettings.equals(((AmazonUiSettingsDelegate) other).uiSettings));
    }

    @Override
    public int hashCode() {
        return uiSettings.hashCode();
    }

    @Override
    public String toString() {
        return uiSettings.toString();
    }
}
