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
import android.webkit.WebView;
import org.onepf.maps.yandexweb.jsi.JSYandexMapProxy;
import org.onepf.maps.yandexweb.utils.ConvertUtils;
import org.onepf.opfutils.OPFLog;

import java.util.List;

import static org.onepf.maps.yandexweb.jsi.JSYandexMapProxy.STROKE_COLOR_OPTION;
import static org.onepf.maps.yandexweb.jsi.JSYandexMapProxy.STROKE_WIDTH_OPTION;
import static org.onepf.maps.yandexweb.jsi.JSYandexMapProxy.VISIBLE_OPTION;
import static org.onepf.maps.yandexweb.jsi.JSYandexMapProxy.Z_INDEX_OPTION;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class Polyline {

    @Nullable
    private final WebView webView;
    @NonNull
    private final String id;
    @NonNull
    private List<LatLng> points;
    private int color;
    private float width;
    private float zIndex;
    private boolean isVisible;

    public Polyline(@SuppressWarnings("NullableProblems") @NonNull final WebView webView,
                    @NonNull final List<LatLng> points,
                    final int color,
                    final float width,
                    final float zIndex,
                    final boolean isVisible) {
        this.id = Integer.toString(hashCode());
        this.webView = webView;
        this.points = points;
        this.color = color;
        this.width = width;
        this.zIndex = zIndex;
        this.isVisible = isVisible;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public int getColor() {
        return color;
    }

    @NonNull
    public List<LatLng> getPoints() {
        return points;
    }

    public float getWidth() {
        return width;
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
        }
    }

    public void setColor(final int color) {
        this.color = color;
        if (webView != null) {
            JSYandexMapProxy.setGeoObjectOption(webView, id, STROKE_COLOR_OPTION, ConvertUtils.convertColor(color));
        }
    }

    public void setGeodesic(final boolean geodesic) {
        OPFLog.logStubCall(geodesic);
    }

    public void setPoints(@NonNull final List<LatLng> points) {
        this.points = points;
        if (webView != null) {
            JSYandexMapProxy.setGeoObjectCoordinates(webView, id, points);
        }
    }

    public void setVisible(final boolean visible) {
        this.isVisible = visible;
        if (webView != null) {
            JSYandexMapProxy.setGeoObjectOption(webView, id, VISIBLE_OPTION, visible);
        }
    }

    public void setWidth(final float width) {
        this.width = width;
        if (webView != null) {
            JSYandexMapProxy.setGeoObjectOption(webView, id, STROKE_WIDTH_OPTION, width);
        }
    }

    public void setZIndex(final float zIndex) {
        this.zIndex = zIndex;
        if (webView != null) {
            JSYandexMapProxy.setGeoObjectOption(webView, id, Z_INDEX_OPTION, zIndex);
        }
    }
}
