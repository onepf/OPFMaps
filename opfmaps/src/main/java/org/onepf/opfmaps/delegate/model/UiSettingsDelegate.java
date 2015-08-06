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

package org.onepf.opfmaps.delegate.model;

/**
 * @author Roman Savin
 * @since 06.08.2015
 */
public interface UiSettingsDelegate {

    boolean isCompassEnabled();

    boolean isIndoorLevelPickerEnabled();

    boolean isMapToolbarEnabled();

    boolean isMyLocationButtonEnabled();

    boolean isRotateGesturesEnabled();

    boolean isScrollGesturesEnabled();

    boolean isTiltGesturesEnabled();

    boolean isZoomControlsEnabled();

    boolean isZoomGesturesEnabled();

    void setAllGesturesEnabled(final boolean enabled);

    void setCompassEnabled(final boolean enabled);

    void setIndoorLevelPickerEnabled(final boolean enabled);

    void setMapToolbarEnabled(final boolean enabled);

    void setMyLocationButtonEnabled(final boolean enabled);

    void setRotateGesturesEnabled(final boolean enabled);

    void setScrollGesturesEnabled(final boolean enabled);

    void setTiltGesturesEnabled(final boolean enabled);

    void setZoomControlsEnabled(final boolean enabled);

    void setZoomGesturesEnabled(final boolean enabled);
}
