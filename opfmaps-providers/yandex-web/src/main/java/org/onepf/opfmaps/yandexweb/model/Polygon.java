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

package org.onepf.opfmaps.yandexweb.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import org.onepf.opfmaps.yandexweb.jsi.JSYandexMapProxy;
import org.onepf.opfmaps.yandexweb.utils.ConvertUtils;
import org.onepf.opfutils.OPFLog;

import java.util.List;

import static org.onepf.opfmaps.yandexweb.jsi.JSYandexMapProxy.FILL_COLOR_OPTION;
import static org.onepf.opfmaps.yandexweb.jsi.JSYandexMapProxy.STROKE_COLOR_OPTION;
import static org.onepf.opfmaps.yandexweb.jsi.JSYandexMapProxy.STROKE_WIDTH_OPTION;
import static org.onepf.opfmaps.yandexweb.jsi.JSYandexMapProxy.VISIBLE_OPTION;
import static org.onepf.opfmaps.yandexweb.jsi.JSYandexMapProxy.Z_INDEX_OPTION;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class Polygon {

    @Nullable
    private WebView webView;
    @NonNull
    private final String id;
    @NonNull
    private List<LatLng> points;
    @Nullable
    private List<List<LatLng>> holes;
    private int fillColor;
    private int strokeColor;
    private float strokeWidth;
    private float zIndex;
    private boolean isVisible;

    //CHECKSTYLE:OFF
    public Polygon(@SuppressWarnings("NullableProblems") @NonNull final WebView webView,
                   @NonNull final List<LatLng> points,
                   @Nullable final List<List<LatLng>> holes,
                   final int fillColor,
                   final int strokeColor,
                   final float strokeWidth,
                   final float zIndex,
                   final boolean isVisible) {
        this.id = Integer.toString(hashCode());
        this.webView = webView;
        this.points = points;
        this.holes = holes;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
        this.zIndex = zIndex;
        this.isVisible = isVisible;
    }
    //CHECKSTYLE:ON

    @NonNull
    public String getId() {
        return id;
    }

    public int getFillColor() {
        return fillColor;
    }

    @Nullable
    public List<List<LatLng>> getHoles() {
        return holes;
    }

    @NonNull
    public List<LatLng> getPoints() {
        return points;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public float getZIndex() {
        return zIndex;
    }

    public boolean isGeodesic() {
        return false;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void remove() {
        if (webView != null) {
            JSYandexMapProxy.removeGeoObject(webView, id);
            webView = null;
        }
    }

    public void setFillColor(final int color) {
        this.fillColor = color;
        if (webView != null) {
            JSYandexMapProxy.setGeoObjectOption(webView, id, FILL_COLOR_OPTION, ConvertUtils.convertColor(color));
        }
    }

    public void setGeodesic(final boolean geodesic) {
        OPFLog.logStubCall(geodesic);
    }

    public void setHoles(@NonNull final List<List<LatLng>> holes) {
        this.holes = holes;
        if (webView != null) {
            JSYandexMapProxy.setGeoObjectCoordinates(webView, id, points, holes);
        }
    }

    public void setPoints(@NonNull final List<LatLng> points) {
        this.points = points;
        if (webView != null) {
            JSYandexMapProxy.setGeoObjectCoordinates(webView, id, points, holes);
        }
    }

    public void setStrokeColor(final int color) {
        this.strokeColor = color;
        if (webView != null) {
            JSYandexMapProxy.setGeoObjectOption(webView, id, STROKE_COLOR_OPTION, ConvertUtils.convertColor(color));
        }
    }

    public void setStrokeWidth(final float width) {
        this.strokeWidth = width;
        if (webView != null) {
            JSYandexMapProxy.setGeoObjectOption(webView, id, STROKE_WIDTH_OPTION, width);
        }
    }

    public void setVisible(final boolean visible) {
        this.isVisible = visible;
        if (webView != null) {
            JSYandexMapProxy.setGeoObjectOption(webView, id, VISIBLE_OPTION, visible);
        }
    }

    public void setZIndex(final float zIndex) {
        this.zIndex = zIndex;
        if (webView != null) {
            JSYandexMapProxy.setGeoObjectOption(webView, id, Z_INDEX_OPTION, zIndex);
        }
    }
}
