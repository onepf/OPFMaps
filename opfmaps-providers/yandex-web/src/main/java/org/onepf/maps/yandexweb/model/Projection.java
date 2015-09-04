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

package org.onepf.maps.yandexweb.model;

import android.graphics.Point;
import android.support.annotation.NonNull;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class Projection {

    Projection() {
        //todo implement
    }

    @NonNull
    public LatLng fromScreenLocation(@NonNull final Point point) {
        //todo implement
        return null;
    }

    @NonNull
    public Point toScreenLocation(@NonNull final LatLng location) {
        //todo implement
        return null;
    }

    @NonNull
    public VisibleRegion getVisibleRegion() {
        //todo implement
        return null;
    }

    @Override
    public boolean equals(final Object other) {
        //todo implement
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        //todo implement
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
        //todo implement
    }
}
