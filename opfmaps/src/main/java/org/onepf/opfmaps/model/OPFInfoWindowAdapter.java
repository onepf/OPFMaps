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
import android.view.View;

/**
 * Provides views for customized rendering of info windows.
 *
 * @author Anastasiia Karimova
 * @since 15.06.2015
 */
public interface OPFInfoWindowAdapter {

    /**
     * Provides a custom info window for a marker. If this method returns a view, it is used for the entire info window.
     * If you change this view after this method is called, those changes will not necessarily be reflected in the rendered
     * info window. If this method returns {@code null}, the default info window frame will be used,
     * with contents provided by {@link #getInfoContents(OPFMarker)}.
     *
     * @param marker The marker for which an info window is being populated.
     * @return A custom info window for marker, or {@code null} to use the default info window frame with custom contents.
     */
    View getInfoWindow(@NonNull final OPFMarker marker);

    /**
     * Provides custom contents for the default info window frame of a marker.
     * This method is only called if {@link #getInfoWindow(OPFMarker)} first returns {@code null}.
     * If this method returns a view, it will be placed inside the default info window frame.
     * If you change this view after this method is called, those changes will not necessarily be reflected in the
     * rendered info window. If this method returns {@code null}, the default rendering will be used instead.
     *
     * @param marker The marker for which an info window is being populated.
     * @return A custom view to display as contents in the info window for marker, or {@code null} to use the default content rendering instead.
     */
    View getInfoContents(@NonNull final OPFMarker marker);
}
