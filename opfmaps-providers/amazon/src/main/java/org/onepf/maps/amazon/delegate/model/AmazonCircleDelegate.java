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

package org.onepf.maps.amazon.delegate.model;

import android.support.annotation.NonNull;

import com.amazon.geo.mapsv2.model.Circle;
import com.amazon.geo.mapsv2.model.LatLng;

import org.onepf.opfmaps.delegate.model.CircleDelegate;
import org.onepf.opfmaps.model.OPFLatLng;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
public final class AmazonCircleDelegate implements CircleDelegate {

    @NonNull
    private final Circle circle;

    public AmazonCircleDelegate(@NonNull final Circle circle) {
        this.circle = circle;
    }

    @NonNull
    @Override
    public OPFLatLng getCenter() {
        return new OPFLatLng(new AmazonLatLngDelegate(circle.getCenter()));
    }

    @Override
    public int getFillColor() {
        return circle.getFillColor();
    }

    @NonNull
    @Override
    public String getId() {
        return circle.getId();
    }

    @Override
    public double getRadius() {
        return circle.getRadius();
    }

    @Override
    public int getStrokeColor() {
        return circle.getStrokeColor();
    }

    @Override
    public float getStrokeWidth() {
        return circle.getStrokeWidth();
    }

    @Override
    public float getZIndex() {
        return circle.getZIndex();
    }

    @Override
    public boolean isVisible() {
        return circle.isVisible();
    }

    @Override
    public void remove() {
        circle.remove();
    }

    @Override
    public void setCenter(@NonNull final OPFLatLng center) {
        circle.setCenter(new LatLng(center.getLat(), center.getLng()));
    }

    @Override
    public void setFillColor(final int color) {
        circle.setFillColor(color);
    }

    @Override
    public void setRadius(final double radius) {
        circle.setRadius(radius);
    }

    @Override
    public void setStrokeColor(final int color) {
        circle.setStrokeColor(color);
    }

    @Override
    public void setStrokeWidth(final float width) {
        circle.setStrokeWidth(width);
    }

    @Override
    public void setVisible(final boolean visible) {
        circle.setVisible(visible);
    }

    @Override
    public void setZIndex(final float zIndex) {
        circle.setZIndex(zIndex);
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof AmazonCircleDelegate
                && circle.equals(((AmazonCircleDelegate) other).circle));
    }

    @Override
    public int hashCode() {
        return circle.hashCode();
    }

    @Override
    public String toString() {
        return circle.toString();
    }
}
