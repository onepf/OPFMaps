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

package org.onepf.opfmaps.delegate;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import org.onepf.opfmaps.model.OPFCameraPosition;
import org.onepf.opfmaps.model.OPFMapType;

/**
 * @author Roman Savin
 * @since 06.08.2015
 */
public interface MapOptionsDelegate extends Parcelable {

    @NonNull
    MapOptionsDelegate camera(@NonNull final OPFCameraPosition camera);

    @NonNull
    MapOptionsDelegate compassEnabled(final boolean enabled);

    @Nullable
    OPFCameraPosition getCamera();

    @Nullable
    Boolean getCompassEnabled();

    @Nullable
    Boolean getLiteMode();

    @Nullable
    Boolean getMapToolbarEnabled();

    @NonNull
    OPFMapType getMapType();

    @Nullable
    Boolean getRotateGesturesEnabled();

    @Nullable
    Boolean getScrollGesturesEnabled();

    @Nullable
    Boolean getTiltGesturesEnabled();

    @Nullable
    Boolean getUseViewLifecycleInFragment();

    @Nullable
    Boolean getZOrderOnTop();

    @Nullable
    Boolean getZoomControlsEnabled();

    @Nullable
    Boolean getZoomGesturesEnabled();

    @NonNull
    MapOptionsDelegate liteMode(final boolean enabled);

    @NonNull
    MapOptionsDelegate mapToolbarEnabled(final boolean enabled);

    @NonNull
    MapOptionsDelegate mapType(@NonNull final OPFMapType mapType);

    @NonNull
    MapOptionsDelegate rotateGesturesEnabled(final boolean enabled);

    @NonNull
    MapOptionsDelegate scrollGesturesEnabled(final boolean enabled);

    @NonNull
    MapOptionsDelegate tiltGesturesEnabled(final boolean enabled);

    @NonNull
    MapOptionsDelegate useViewLifecycleInFragment(final boolean useViewLifecycleInFragment);

    @NonNull
    MapOptionsDelegate zOrderOnTop(final boolean zOrderOnTop);

    @NonNull
    MapOptionsDelegate zoomControlsEnabled(final boolean enabled);

    @NonNull
    MapOptionsDelegate zoomGesturesEnabled(final boolean enabled);
}
