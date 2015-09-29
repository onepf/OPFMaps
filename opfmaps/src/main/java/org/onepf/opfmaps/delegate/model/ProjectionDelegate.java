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

import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFVisibleRegion;

/**
 * @author Roman Savin
 * @since 06.08.2015
 */
public interface ProjectionDelegate {

    @Nullable
    OPFLatLng fromScreenLocation(@NonNull final Point point);

    @NonNull
    OPFVisibleRegion getVisibleRegion();

    @NonNull
    Point toScreenLocation(@NonNull final OPFLatLng location);
}
