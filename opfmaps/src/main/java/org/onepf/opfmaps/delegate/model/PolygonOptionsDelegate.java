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

import java.util.List;

/**
 * @author Roman Savin
 * @since 30.07.2015
 */
public interface PolygonOptionsDelegate extends Parcelable {

    @NonNull
    PolygonOptionsDelegate add(@NonNull final OPFLatLng point);

    @NonNull
    PolygonOptionsDelegate add(@NonNull final OPFLatLng... points);

    @NonNull
    PolygonOptionsDelegate addAll(@NonNull final Iterable<OPFLatLng> points);

    @NonNull
    PolygonOptionsDelegate addHole(@NonNull final Iterable<OPFLatLng> points);

    @NonNull
    PolygonOptionsDelegate fillColor(final int color);

    @NonNull
    PolygonOptionsDelegate geodesic(final boolean geodesic);

    int getFillColor();

    @NonNull
    List<List<OPFLatLng>> getHoles();

    @NonNull
    List<OPFLatLng> getPoints();

    int getStrokeColor();

    float getStrokeWidth();

    float getZIndex();

    boolean isGeodesic();

    boolean isVisible();

    @NonNull
    PolygonOptionsDelegate strokeColor(final int color);

    @NonNull
    PolygonOptionsDelegate strokeWidth(final float width);

    @NonNull
    PolygonOptionsDelegate visible(final boolean visible);

    @NonNull
    PolygonOptionsDelegate zIndex(final float zIndex);

}
