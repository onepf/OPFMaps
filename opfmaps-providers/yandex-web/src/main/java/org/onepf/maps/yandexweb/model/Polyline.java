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

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class Polyline {

    Polyline() {
        //todo implement
    }

    @NonNull
    public String getId() {
        //todo implement
        return null;
    }

    public int getColor() {
        //todo implement
        return 0;
    }

    @Nullable
    public List<LatLng> getPoints() {
        //todo implement
        return null;
    }

    public float getWidth() {
        //todo implement
        return 0;
    }

    public float getZIndex() {
        //todo implement
        return 0;
    }

    public boolean isGeodesic() {
        //todo implement
        return false;
    }

    public boolean isVisible() {
        //todo implement
        return false;
    }

    public void remove() {
        //todo implement
    }

    public void setColor(final int color) {
        //todo implement
    }

    public void setGeodesic(final boolean geodesic) {
        //todo implement
    }

    public void setPoints(@NonNull final List<LatLng> points) {
        //todo implement
    }

    public void setVisible(final boolean visible) {
        //todo implement
    }

    public void setWidth(final float width) {
        //todo implement
    }

    public void setZIndex(final float zIndex) {
        //todo implement
    }

    @Override
    public String toString() {
        //todo implement
        return super.toString();
    }

    @Override
    public int hashCode() {
        //todo implement
        return super.hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        //todo implement
        return super.equals(other);
    }
}
