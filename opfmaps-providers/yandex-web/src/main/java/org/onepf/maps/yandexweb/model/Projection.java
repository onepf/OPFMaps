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

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.TypedValue;

import static org.onepf.maps.yandexweb.delegate.YaWebMapDelegate.MAX_ZOOM_LEVEL;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class Projection {

    /**
     * Tile size for zero zoom level.
     */
    private static final int TILE_SIZE = 256;
    private static final double MIN_LATITUDE = -85.05112878;
    private static final double MAX_LATITUDE = 85.05112878;
    private static final double MIN_LONGITUDE = -180;
    private static final double MAX_LONGITUDE = 180;

    @NonNull
    private final Rect screenRect;
    private float zoomLevel;
    private float offsetX;
    private float offsetY;

    private final float tileSize;

    public Projection(@NonNull final Rect screenRect,
                      @NonNull final Context context,
                      final float zoomLevel,
                      final float offsetX,
                      final float offsetY) {
        this.tileSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, TILE_SIZE, context.getResources().getDisplayMetrics());

        this.screenRect = screenRect;
        this.zoomLevel = zoomLevel;

        final float halfMapSize = getMapSize() / 2;
        this.offsetX = offsetX - (halfMapSize - screenRect.right / 2);
        this.offsetY = offsetY - (halfMapSize - screenRect.bottom / 2);
    }

    //CHECKSTYLE:OFF
    @NonNull
    public LatLng fromScreenLocation(@NonNull final Point point) {
        final double mapSize = getMapSize();
        double x = clipWithInterval(point.x - offsetX, 0, mapSize - 1, mapSize);
        double y = clipWithInterval(point.y - offsetY, 0, mapSize - 1, mapSize);

        x = clip(x, 0, mapSize - 1) / mapSize - 0.5;
        y = 0.5 - (clip(y, 0, mapSize - 1) / mapSize);

        final double latitude = 90 - 360 * Math.atan(Math.exp(-y * 2 * Math.PI)) / Math.PI;
        final double longitude = 360 * x;

        return new LatLng(latitude, longitude);
    }

    //FIXME: Wrong y value on the big zoom levels (starting from 9 lvl)
    @NonNull
    public Point toScreenLocation(@NonNull final LatLng location) {
        final LatLng clippedLocation = new LatLng(
                clipWithInterval(location.getLat(), -90, 90, 180),
                clipWithInterval(location.getLng(), -180, 180, 360)
        );
        final Point point = new Point();

        final double latitude = clip(clippedLocation.getLat(), MIN_LATITUDE, MAX_LATITUDE);
        final double longitude = clip(clippedLocation.getLng(), MIN_LONGITUDE, MAX_LONGITUDE);

        double x = (longitude + 180) / 360;
        final double sinLatitude = Math.sin(latitude * Math.PI / 180);
        double y = 0.5 - Math.log((1 + sinLatitude) / (1 - sinLatitude)) / (4 * Math.PI);

        final float mapSize = getMapSize();

        x = clip(x * mapSize + 0.5, 0, mapSize - 1);
        y = clip(y * mapSize + 0.5, 0, mapSize - 1);

        point.x = (int) (x + offsetX);
        point.y = (int) (y + offsetY);
        return point;
    }
    //CHECKSTYLE:ON

    @NonNull
    public VisibleRegion getVisibleRegion() {
        final LatLng nearLeft = fromScreenLocation(new Point(screenRect.left, screenRect.bottom));
        final LatLng nearRight = fromScreenLocation(new Point(screenRect.right, screenRect.bottom));
        final LatLng farLeft = fromScreenLocation(new Point(screenRect.left, screenRect.top));
        final LatLng farRight = fromScreenLocation(new Point(screenRect.right, screenRect.top));


        final LatLng northEast = fromScreenLocation(new Point(screenRect.right, 0));
        final LatLng southWest = fromScreenLocation(new Point(0, screenRect.bottom));

        return new VisibleRegion(nearLeft, nearRight, farLeft, farRight, new LatLngBounds(southWest, northEast));
    }

    public void setZoomLevel(final float zoomLevel) {
        this.zoomLevel = zoomLevel;
    }

    /**
     * Set x offset in dip.
     *
     * @param offsetX The x offset in dip.
     */
    public void setOffsetX(final double offsetX) {
        final float halfMapSize = getMapSize() / 2;
        this.offsetX = (float) offsetX -  halfMapSize + screenRect.right / 2;
    }

    /**
     * Set y offset in dip.
     *
     * @param offsetY The y offset in dip.
     */
    public void setOffsetY(final double offsetY) {
        final float halfMapSize = getMapSize() / 2;
        this.offsetY = (float) offsetY - halfMapSize + screenRect.bottom / 2;
    }

    private float getMapSize() {
        return tileSize * (float) Math.pow(2, zoomLevel < MAX_ZOOM_LEVEL ? zoomLevel : MAX_ZOOM_LEVEL);
    }

    private static double clip(final double n, final double minValue, final double maxValue) {
        return Math.min(Math.max(n, minValue), maxValue);
    }

    private static double clipWithInterval(final double n, final double minValue, final double maxValue, final double interval) {
        if (n < minValue || n > maxValue) {
            final double reminder = n % interval;
            return reminder < minValue ? minValue + reminder : reminder > maxValue ? maxValue - reminder : reminder;
        }

        return n;
    }
}
