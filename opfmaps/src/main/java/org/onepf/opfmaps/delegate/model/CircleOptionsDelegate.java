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
import android.support.annotation.Nullable;
import org.onepf.opfmaps.model.OPFLatLng;

/**
 * @author Roman Savin
 * @since 29.07.2015
 */
public interface CircleOptionsDelegate extends Parcelable {

    @NonNull
    CircleOptionsDelegate center(@NonNull final OPFLatLng center);

    @NonNull
    CircleOptionsDelegate fillColor(final int color);

    @Nullable
    OPFLatLng getCenter();

    int getFillColor();

    double getRadius();

    int getStrokeColor();

    float getStrokeWidth();

    float getZIndex();

    boolean isVisible();

    @NonNull
    CircleOptionsDelegate radius(final double radius);

    @NonNull
    CircleOptionsDelegate strokeColor(final int color);

    @NonNull
    CircleOptionsDelegate strokeWidth(final float width);

    @NonNull
    CircleOptionsDelegate visible(final boolean visible);

    @NonNull
    CircleOptionsDelegate zIndex(final float zIndex);
}
