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

package org.onepf.maps.osmdroid.model;

import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.onepf.opfmaps.utils.CompareUtils;
import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.util.GeoPoint;

/**
 * @author Roman Savin
 * @since 17.08.2015
 */
@SuppressWarnings("PMD.MissingStaticMethodInNonInstantiatableClass")
public final class CameraUpdate {

    public enum CameraUpdateSource {
        CAMERA_POSITION,
        GEOPOINT,
        BOUNDS_PADDING,
        BOUNDS_WIDTH_HEIGHT_PADDING,
        GEOPOINT_ZOOM,
        SCROLL_BY,
        ZOOM_BY_FOCUS,
        ZOOM_BY,
        ZOOM_IN,
        ZOOM_OUT,
        ZOOM_TO
    }

    @NonNull
    private final CameraUpdateSource cameraUpdateSource;
    @Nullable
    private final GeoPoint center;
    @Nullable
    private final BoundingBoxE6 bounds;
    @Nullable
    private final Point focus;
    private final float zoom;
    private final float bearing;
    private final int width;
    private final int height;
    private final int padding;
    private final float xPixel;
    private final float yPixel;

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.ExcessiveParameterList")
    private CameraUpdate(@NonNull final CameraUpdateSource cameraUpdateSource,
                         @Nullable final GeoPoint center,
                         @Nullable final BoundingBoxE6 bounds,
                         @Nullable final Point focus,
                         final float zoom,
                         final float bearing,
                         final int width,
                         final int height,
                         final int padding,
                         final float xPixel,
                         final float yPixel) {
        this.cameraUpdateSource = cameraUpdateSource;
        this.center = center;
        this.bounds = bounds;
        this.focus = focus;
        this.zoom = zoom;
        this.bearing = bearing;
        this.width = width;
        this.height = height;
        this.padding = padding;
        this.xPixel = xPixel;
        this.yPixel = yPixel;
    }
    //CHECKSTYLE:ON

    @NonNull
    public CameraUpdateSource getCameraUpdateSource() {
        return cameraUpdateSource;
    }

    @Nullable
    public GeoPoint getCenter() {
        return center;
    }

    @Nullable
    public BoundingBoxE6 getBounds() {
        return bounds;
    }

    @Nullable
    public Point getFocus() {
        return focus;
    }

    public float getZoom() {
        return zoom;
    }

    public float getBearing() {
        return bearing;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPadding() {
        return padding;
    }

    public float getXPixel() {
        return xPixel;
    }

    public float getYPixel() {
        return yPixel;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            final CameraUpdate other = (CameraUpdate) obj;

            return CompareUtils.isEquals(this.cameraUpdateSource, other.cameraUpdateSource)
                    && CompareUtils.isEquals(this.center, other.center)
                    && CompareUtils.isEquals(this.bounds, other.bounds)
                    && CompareUtils.isEquals(this.focus, other.focus)
                    && this.zoom == other.zoom
                    && this.bearing == other.bearing
                    && this.width == other.width
                    && this.height == other.height
                    && this.padding == other.padding
                    && this.xPixel == other.xPixel
                    && this.yPixel == other.yPixel;
        }
    }

    @SuppressWarnings("PMD.NPathComplexity")
    @Override
    public int hashCode() {
        int result = cameraUpdateSource.hashCode();
        result = 31 * result + (center != null ? center.hashCode() : 0);
        result = 31 * result + (bounds != null ? bounds.hashCode() : 0);
        result = 31 * result + (focus != null ? focus.hashCode() : 0);
        result = 31 * result + (zoom != +0.0f ? Float.floatToIntBits(zoom) : 0);
        result = 31 * result + (bearing != +0.0f ? Float.floatToIntBits(bearing) : 0);
        result = 31 * result + width;
        result = 31 * result + height;
        result = 31 * result + padding;
        result = 31 * result + (xPixel != +0.0f ? Float.floatToIntBits(xPixel) : 0);
        result = 31 * result + (yPixel != +0.0f ? Float.floatToIntBits(yPixel) : 0);
        return result;
    }

    static final class Builder {

        @NonNull
        private final CameraUpdateSource cameraUpdateSource;
        @Nullable
        private GeoPoint center;
        @Nullable
        private BoundingBoxE6 bounds;
        @Nullable
        private Point focus;
        private float zoom;
        private float bearing;
        private int width;
        private int height;
        private int padding;
        private float xPixel;
        private float yPixel;

        Builder(@NonNull final CameraUpdateSource cameraUpdateSource) {
            this.cameraUpdateSource = cameraUpdateSource;
        }

        @NonNull
        Builder setCenter(@NonNull final GeoPoint center) {
            this.center = center;
            return this;
        }

        @NonNull
        Builder setBounds(@NonNull final BoundingBoxE6 bounds) {
            this.bounds = bounds;
            return this;
        }

        @NonNull
        Builder setFocus(@NonNull final Point focus) {
            this.focus = focus;
            return this;
        }

        @NonNull
        Builder setZoom(final float zoom) {
            this.zoom = zoom;
            return this;
        }

        @NonNull
        Builder setBearing(final float bearing) {
            this.bearing = bearing;
            return this;
        }

        @NonNull
        Builder setWidth(final int width) {
            this.width = width;
            return this;
        }

        @NonNull
        Builder setHeight(final int height) {
            this.height = height;
            return this;
        }

        @NonNull
        Builder setPadding(final int padding) {
            this.padding = padding;
            return this;
        }

        @NonNull
        Builder setXPixel(final float xPixel) {
            this.xPixel = xPixel;
            return this;
        }

        @NonNull
        Builder setYPixel(final float yPixel) {
            this.yPixel = yPixel;
            return this;
        }

        @SuppressWarnings("PMD.AccessorClassGeneration")
        @NonNull
        CameraUpdate build() {
            return new CameraUpdate(
                    cameraUpdateSource,
                    center,
                    bounds,
                    focus,
                    zoom,
                    bearing,
                    width,
                    height,
                    padding,
                    xPixel,
                    yPixel
            );
        }
    }
}
