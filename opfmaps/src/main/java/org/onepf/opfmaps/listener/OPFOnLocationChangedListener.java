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

package org.onepf.opfmaps.listener;

import android.location.Location;
import android.support.annotation.NonNull;

/**
 * Interface definition for a callback to be invoked when a location is changed.
 *
 * @author Roman Savin
 * @since 06.08.2015
 */
public interface OPFOnLocationChangedListener {

    /**
     * Called when a new user location is known.
     *
     * @param location New location.
     */
    void onLocationChanged(@NonNull final Location location);
}
