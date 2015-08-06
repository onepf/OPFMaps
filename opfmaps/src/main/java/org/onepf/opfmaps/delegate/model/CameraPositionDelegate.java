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

import android.os.Parcelable;
import android.support.annotation.NonNull;
import org.onepf.opfmaps.model.OPFCameraPosition;
import org.onepf.opfmaps.model.OPFLatLng;

/**
 * @author Roman Savin
 * @since 06.08.2015
 */
public interface CameraPositionDelegate extends Parcelable {

    float getBearing();

    @NonNull
    OPFLatLng getTarget();

    float getTilt();

    float getZoom();

    interface Builder {

        @NonNull
        Builder bearing(final float bearing);

        @NonNull
        Builder target(@NonNull final OPFLatLng target);

        @NonNull
        Builder tilt(final float tilt);

        @NonNull
        Builder zoom(final float zoom);

        @NonNull
        OPFCameraPosition build();
    }
}
