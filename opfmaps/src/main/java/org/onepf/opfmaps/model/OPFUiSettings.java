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
import org.onepf.opfmaps.delegate.model.UiSettingsDelegate;

/**
 * @author Roman Savin
 * @since 06.08.2015
 */
public class OPFUiSettings implements UiSettingsDelegate {

    @NonNull
    private final UiSettingsDelegate delegate;

    public OPFUiSettings(@NonNull final UiSettingsDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean isCompassEnabled() {
        return delegate.isCompassEnabled();
    }

    @Override
    public boolean isIndoorLevelPickerEnabled() {
        return delegate.isIndoorLevelPickerEnabled();
    }

    @Override
    public boolean isMapToolbarEnabled() {
        return delegate.isMapToolbarEnabled();
    }

    @Override
    public boolean isMyLocationButtonEnabled() {
        return delegate.isMyLocationButtonEnabled();
    }

    @Override
    public boolean isRotateGesturesEnabled() {
        return delegate.isRotateGesturesEnabled();
    }

    @Override
    public boolean isScrollGesturesEnabled() {
        return delegate.isScrollGesturesEnabled();
    }

    @Override
    public boolean isTiltGesturesEnabled() {
        return delegate.isTiltGesturesEnabled();
    }

    @Override
    public boolean isZoomControlsEnabled() {
        return delegate.isZoomControlsEnabled();
    }

    @Override
    public boolean isZoomGesturesEnabled() {
        return delegate.isZoomGesturesEnabled();
    }

    @Override
    public void setAllGesturesEnabled(final boolean enabled) {
        delegate.setAllGesturesEnabled(enabled);
    }

    @Override
    public void setCompassEnabled(final boolean enabled) {
        delegate.setCompassEnabled(enabled);
    }

    @Override
    public void setIndoorLevelPickerEnabled(final boolean enabled) {
        delegate.setIndoorLevelPickerEnabled(enabled);
    }

    @Override
    public void setMapToolbarEnabled(final boolean enabled) {
        delegate.setMapToolbarEnabled(enabled);
    }

    @Override
    public void setMyLocationButtonEnabled(final boolean enabled) {
        delegate.setMyLocationButtonEnabled(enabled);
    }

    @Override
    public void setRotateGesturesEnabled(final boolean enabled) {
        delegate.setRotateGesturesEnabled(enabled);
    }

    @Override
    public void setScrollGesturesEnabled(final boolean enabled) {
        delegate.setScrollGesturesEnabled(enabled);
    }

    @Override
    public void setTiltGesturesEnabled(final boolean enabled) {
        delegate.setTiltGesturesEnabled(enabled);
    }

    @Override
    public void setZoomControlsEnabled(final boolean enabled) {
        delegate.setZoomControlsEnabled(enabled);
    }

    @Override
    public void setZoomGesturesEnabled(final boolean enabled) {
        delegate.setZoomGesturesEnabled(enabled);
    }

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OPFUiSettings)) return false;

        return delegate.equals(((OPFUiSettings) other).delegate);
    }
    //CHECKSTYLE:ON

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public String toString() {
        return delegate.toString();
    }
}
