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
import org.onepf.opfmaps.listener.OPFOnLocationChangedListener;

/**
 * Defines an interface for providing location data, typically to a {@link org.onepf.opfmaps.OPFMap} object.
 *
 * @author Roman Savin
 * @since 06.08.2015
 */
public interface OPFLocationSource {

    /**
     * Activates this provider. This provider will notify the supplied listener periodically,
     * until you call {@link #deactivate()}. Notifications will be broadcast on the main thread.
     *
     * @param listener The listener that's called when a new location is available.
     */
    void activate(@NonNull final OPFOnLocationChangedListener listener);

    /**
     * Deactivates this provider. The previously-registered callback is not notified of any further updates.
     */
    void deactivate();
}
