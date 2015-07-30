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

import android.support.annotation.NonNull;
import org.onepf.opfmaps.model.OPFBitmapDescriptor;
import org.onepf.opfmaps.model.OPFLatLng;

/**
 * @author Roman Savin
 * @since 29.07.2015
 */
public interface MarkerDelegate {

    float getAlpha();

    @NonNull
    String getId();

    @NonNull
    OPFLatLng getPosition();

    float getRotation();

    @NonNull
    String getSnippet();

    @NonNull
    String getTitle();

    void hideInfoWindow();

    boolean isDraggable();

    boolean isFlat();

    boolean isInfoWindowShown();

    boolean isVisible();

    void remove();

    void setAlpha(final float alpha);

    void setAnchor(final float anchorU, final float anchorV);

    void setDraggable(final boolean draggable);

    void setFlat(final boolean flat);

    void setIcon(final OPFBitmapDescriptor icon);

    void setInfoWindowAnchor(final float anchorU, final float anchorV);

    void setPosition(@NonNull final OPFLatLng latLng);

    void setRotation(final float rotation);

    void setSnippet(@NonNull final String snippet);

    void setTitle(@NonNull final String title);

    void setVisible(final boolean visible);

    void showInfoWindow();
}
