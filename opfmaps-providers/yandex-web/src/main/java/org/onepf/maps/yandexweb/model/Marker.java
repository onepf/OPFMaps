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
import org.onepf.opfutils.OPFLog;

import java.util.Map;

import static org.onepf.maps.yandexweb.jsi.JSYandexMapProxy.BALLOON_CONTENT_BODY_OPTION;
import static org.onepf.maps.yandexweb.jsi.JSYandexMapProxy.BALLOON_CONTENT_HEADER_OPTION;
import static org.onepf.maps.yandexweb.jsi.JSYandexMapProxy.DRAGGABLE_OPTION;
import static org.onepf.maps.yandexweb.jsi.JSYandexMapProxy.ICON_COLOR_OPTION;
import static org.onepf.maps.yandexweb.jsi.JSYandexMapProxy.VISIBLE_OPTION;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class Marker {

    @Nullable
    private WebView webView;
    @Nullable
    private Map<String, Marker> markersByIds;
    @NonNull
    private final String id;
    @NonNull
    private LatLng position;
    @Nullable
    private String title;
    @Nullable
    private String snippet;
    private boolean isDraggable;
    private boolean isInfoWindowShown;
    private boolean isVisible;

    public Marker(@Nullable WebView webView,
                  @Nullable Map<String, Marker> markersByIds,
                  @NonNull final LatLng position,
                  @Nullable final String title,
                  @Nullable final String snippet,
                  final boolean isDraggable,
                  final boolean isVisible) {
        this.id = Integer.toString(hashCode());
        this.webView = webView;
        this.markersByIds = markersByIds;
        this.position = position;
        this.title = title;
        this.snippet = snippet;
        this.isDraggable = isDraggable;
        this.isInfoWindowShown = false;
        this.isVisible = isVisible;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public float getAlpha() {
        return 1.0f;
    }

    @NonNull
    public LatLng getPosition() {
        return position;
    }

    public float getRotation() {
        return 0.0f;
    }

    @Nullable
    public String getSnippet() {
        return snippet;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    public boolean isDraggable() {
        return isDraggable;
    }

    public boolean isFlat() {
        return false;
    }

    public boolean isInfoWindowShown() {
        return isInfoWindowShown;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void showInfoWindow() {
        isInfoWindowShown = true;
        if (webView != null) {
            JSYandexMapProxy.showInfoWindow(webView, id);
        }
    }

    public void hideInfoWindow() {
        isInfoWindowShown = false;
        if (webView != null) {
            JSYandexMapProxy.hideInfoWindow(webView, id);
        }
    }

    public void changeIsInfoWindowShownValue(final boolean isInfoWindowShown) {
        this.isInfoWindowShown = isInfoWindowShown;
    }

    public void remove() {
        if (webView != null) {
            JSYandexMapProxy.removeGeoObject(webView, id);
            webView = null;
        }
        if (markersByIds != null) {
            markersByIds.remove(id);
            markersByIds = null;
        }
    }

    public void setAlpha(final float alpha) {
        OPFLog.logStubCall(alpha);
    }

    public void setAnchor(final float anchorU, final float anchorV) {
        OPFLog.logStubCall(anchorU, anchorV);
    }

    public void setDraggable(final boolean draggable) {
        this.isDraggable = draggable;
        if (webView != null){
            JSYandexMapProxy.setGeoObjectOption(webView, id, DRAGGABLE_OPTION, draggable);
        }
    }

    public void setFlat(final boolean flat) {
        OPFLog.logStubCall(flat);
    }

    public void setIcon(@NonNull final BitmapDescriptor icon) {
        if (webView != null) {
            JSYandexMapProxy.setGeoObjectOption(webView, id, ICON_COLOR_OPTION, icon.getRGBColor());
        }
    }

    public void setInfoWindowAnchor(final float anchorU, final float anchorV) {
        OPFLog.logStubCall(anchorU, anchorV);
    }

    public void changePositionValue(@NonNull final LatLng latLng) {
        this.position = latLng;
    }

    public void setPosition(@NonNull final LatLng latLng) {
        this.position = latLng;
        if (webView != null) {
            JSYandexMapProxy.setGeoObjectCoordinates(webView, id, latLng);
        }
    }

    public void setRotation(final float rotation) {
        OPFLog.logStubCall(rotation);
    }

    public void setSnippet(@NonNull final String snippet) {
        this.snippet = snippet;
        if (webView != null) {
            JSYandexMapProxy.setGeoObjectProperty(webView, id, BALLOON_CONTENT_BODY_OPTION, snippet);
        }
    }

    public void setTitle(@NonNull final String title) {
        this.title = title;
        if (webView != null) {
            JSYandexMapProxy.setGeoObjectProperty(webView, id, BALLOON_CONTENT_HEADER_OPTION, title);
        }
    }

    public void setVisible(final boolean visible) {
        this.isVisible = visible;
        if (webView != null) {
            JSYandexMapProxy.setGeoObjectOption(webView, id, VISIBLE_OPTION, visible);
        }
    }

    @Override
    public String toString() {
        return "marker : [position = " + position + ", title = " + title + ", snippet = " + snippet + "]";
    }
}
