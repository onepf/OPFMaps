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
import org.onepf.opfmaps.delegate.model.PolygonDelegate;

import java.util.List;

/**
 * Created by akarimova on 15.06.15.
 */
public final class OPFPolygon implements PolygonDelegate {

    @NonNull
    private final PolygonDelegate delegate;

    public OPFPolygon(@NonNull final PolygonDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public int getFillColor() {
        return delegate.getFillColor();
    }

    @Nullable
    @Override
    public List<List<OPFLatLng>> getHoles() {
        return delegate.getHoles();
    }

    @NonNull
    @Override
    public String getId() {
        return delegate.getId();
    }

    @NonNull
    @Override
    public List<OPFLatLng> getPoints() {
        return delegate.getPoints();
    }

    @Override
    public int getStrokeColor() {
        return delegate.getStrokeColor();
    }

    @Override
    public float getStrokeWidth() {
        return delegate.getStrokeWidth();
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
    public void setFillColor(final int color) {
        delegate.setFillColor(color);
    }

    @Override
    public void setGeodesic(final boolean geodesic) {
        delegate.setGeodesic(geodesic);
    }

    @Override
    public void setHoles(@NonNull final List<? extends List<OPFLatLng>> holes) {
        delegate.setHoles(holes);
    }

    @Override
    public void setPoints(@NonNull final List<OPFLatLng> points) {
        delegate.setPoints(points);
    }

    @Override
    public void setStrokeColor(final int color) {
        delegate.setStrokeColor(color);
    }

    @Override
    public void setStrokeWidth(final float width) {
        delegate.setStrokeWidth(width);
    }

    @Override
    public void setVisible(final boolean visible) {
        delegate.setVisible(visible);
    }

    @Override
    public void setZIndex(final float zIndex) {
        delegate.setZIndex(zIndex);
    }

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OPFPolygon)) return false;

        return delegate.equals(((OPFPolygon) other).delegate);
    }
    //CHECKSTYLE:ON

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public String toString() {
        return delegate.toString();
    }
}
