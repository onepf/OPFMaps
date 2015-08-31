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
import android.support.annotation.Nullable;

import org.onepf.opfmaps.delegate.model.PolylineDelegate;

import java.util.List;

/**
 * Created by akarimova on 09.06.15.
 */
public final class OPFPolyline implements PolylineDelegate {

    @NonNull
    private final PolylineDelegate delegate;

    public OPFPolyline(@NonNull final PolylineDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public int getColor() {
        return delegate.getColor();
    }

    @NonNull
    @Override
    public String getId() {
        return delegate.getId();
    }

    @Nullable
    @Override
    public List<OPFLatLng> getPoints() {
        return delegate.getPoints();
    }

    @Override
    public float getWidth() {
        return delegate.getWidth();
    }

    @Override
    public float getZIndex() {
        return delegate.getZIndex();
    }

    @Override
    public boolean isGeodesic() {
        return delegate.isGeodesic();
    }

    @Override
    public boolean isVisible() {
        return delegate.isVisible();
    }

    @Override
    public void remove() {
        delegate.remove();
    }

    @Override
    public void setColor(final int color) {
        delegate.setColor(color);
    }

    @Override
    public void setGeodesic(final boolean geodesic) {
        delegate.setGeodesic(geodesic);
    }

    @Override
    public void setPoints(@NonNull final List<OPFLatLng> points) {
        delegate.setPoints(points);
    }

    @Override
    public void setVisible(final boolean visible) {
        delegate.setVisible(visible);
    }

    @Override
    public void setWidth(final float width) {
        delegate.setWidth(width);
    }

    @Override
    public void setZIndex(final float zIndex) {
        delegate.setZIndex(zIndex);
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OPFPolyline
                && delegate.equals(((OPFPolyline) other).delegate));
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public String toString() {
        return delegate.toString();
    }
}
