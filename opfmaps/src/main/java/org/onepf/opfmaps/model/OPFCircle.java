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
import org.onepf.opfmaps.delegate.model.CircleDelegate;

/**
 * @author Roman Savin
 * @since 29.07.2015
 */
public final class OPFCircle implements CircleDelegate {

    @NonNull
    private final CircleDelegate delegate;

    public OPFCircle(@NonNull final CircleDelegate delegate) {
        this.delegate = delegate;
    }

    @NonNull
    @Override
    public OPFLatLng getCenter() {
        return delegate.getCenter();
    }

    @Override
    public int getFillColor() {
        return delegate.getFillColor();
    }

    @NonNull
    @Override
    public String getId() {
        return delegate.getId();
    }

    @Override
    public double getRadius() {
        return delegate.getRadius();
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
    public boolean isVisible() {
        return delegate.isVisible();
    }

    @Override
    public void remove() {
        delegate.remove();
    }

    @Override
    public void setCenter(@NonNull final OPFLatLng center) {
        delegate.setCenter(center);
    }

    @Override
    public void setFillColor(final int color) {
        delegate.setFillColor(color);
    }

    @Override
    public void setRadius(final double radius) {
        delegate.setRadius(radius);
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

    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OPFCircle)) return false;

        return delegate.equals(((OPFCircle) other).delegate);
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
