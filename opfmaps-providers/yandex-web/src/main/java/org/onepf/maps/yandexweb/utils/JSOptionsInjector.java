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

package org.onepf.maps.yandexweb.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import org.onepf.maps.yandexweb.model.CameraPosition;
import org.onepf.maps.yandexweb.model.LatLng;
import org.onepf.maps.yandexweb.model.YaWebMapOptions;

/**
 * @author Roman Savin
 * @since 04.09.2015
 */
public final class JSOptionsInjector {

    public static final String JS_MAP_TYPE_NORMAL = "yandex#map";
    public static final String JS_MAP_TYPE_SATELLITE = "yandex#satellite";
    public static final String JS_MAP_TYPE_HYBRID = "yandex#hybrid";

    private static final String MAP_TYPE_STUB = "MAP_TYPE_STUB";
    private static final String CENTER_LAT_STUB = "CENTER_LAT_STUB";
    private static final String CENTER_LNG_STUB = "CENTER_LNG_STUB";
    private static final String ZOOM_STUB = "ZOOM_STUB";
    private static final String DRAG_STUB = "DRAG_STUB";
    private static final String MULTI_TOUCH_STUB = "MULTI_TOUCH_STUB";
    private static final String DBL_CLICK_STUB = "DBL_CLICK_STUB";
    private static final String ZOOM_CONTROLS_ENABLED_STUB = "ZOOM_CONTROLS_ENABLED_STUB";

    private static final String DEFAULT_MAP_TYPE = JS_MAP_TYPE_NORMAL;
    private static final String DEFAULT_CENTER_LAT = "0";
    private static final String DEFAULT_CENTER_LNG = "0";
    private static final String DEFAULT_ZOOM_CONTROLS_ENABLED = "true";
    private static final String OPTION_DISABLED_VALUE = "";

    private static final String DRAG_ENABLED_VALUE = "drag";
    private static final String MULTI_TOUCH_ENABLED_VALUE = "multiTouch";
    private static final String DBL_CLICK_ZOOM_ENABLED_VALUE = "dblClickZoom";

    private static final float MIN_ZOOM_LEVEL = 3.0f;

    private JSOptionsInjector() {
        throw new UnsupportedOperationException();
    }

    @NonNull
    public static String injectOptions(@NonNull final String html,
                                       @Nullable final YaWebMapOptions options) {
        if (options == null) {
            return injectDefaultOptions(html);
        }

        final String jsMapType = ConvertUtils.convertMapTypeToJs(options.getMapType());
        final String jsCenterLat;
        final String jsCenterLng;
        final String jsZoom;
        final CameraPosition cameraPosition = options.getCamera();
        if (cameraPosition != null) {
            final LatLng center = cameraPosition.getTarget();
            final float zoom = cameraPosition.getZoom() < MIN_ZOOM_LEVEL ? MIN_ZOOM_LEVEL : cameraPosition.getZoom();

            jsCenterLat = Double.toString(center.getLat());
            jsCenterLng = Double.toString(center.getLng());
            jsZoom = Float.toString(zoom);
        } else {
            jsCenterLat = DEFAULT_CENTER_LAT;
            jsCenterLng = DEFAULT_CENTER_LNG;
            jsZoom = Float.toString(MIN_ZOOM_LEVEL);
        }

        final boolean isScrollGesturesEnabled = options.getScrollGesturesEnabled() == null ? true : options.getScrollGesturesEnabled();
        final boolean isZoomGesturesEnabled = options.getZoomGesturesEnabled() == null ? true : options.getZoomGesturesEnabled();
        final boolean isZoomControlsEnabled = options.getZoomControlsEnabled() == null ? true : options.getZoomControlsEnabled();

        return html.replaceAll(MAP_TYPE_STUB, jsMapType)
                .replaceAll(CENTER_LAT_STUB, jsCenterLat)
                .replaceAll(CENTER_LNG_STUB, jsCenterLng)
                .replaceAll(ZOOM_STUB, jsZoom)
                .replaceAll(DRAG_STUB, isScrollGesturesEnabled ? DRAG_ENABLED_VALUE : OPTION_DISABLED_VALUE)
                .replaceAll(MULTI_TOUCH_STUB, isZoomGesturesEnabled ? MULTI_TOUCH_ENABLED_VALUE : OPTION_DISABLED_VALUE)
                .replaceAll(DBL_CLICK_STUB, isZoomGesturesEnabled ? DBL_CLICK_ZOOM_ENABLED_VALUE : OPTION_DISABLED_VALUE)
                .replaceAll(ZOOM_CONTROLS_ENABLED_STUB, Boolean.toString(isZoomControlsEnabled));
    }

    @NonNull
    private static String injectDefaultOptions(@NonNull final String html) {
        return html.replaceAll(MAP_TYPE_STUB, DEFAULT_MAP_TYPE)
                .replaceAll(CENTER_LAT_STUB, DEFAULT_CENTER_LAT)
                .replaceAll(CENTER_LNG_STUB, DEFAULT_CENTER_LNG)
                .replaceAll(ZOOM_STUB, Float.toString(MIN_ZOOM_LEVEL))
                .replaceAll(DRAG_STUB, DRAG_ENABLED_VALUE)
                .replaceAll(MULTI_TOUCH_STUB, MULTI_TOUCH_ENABLED_VALUE)
                .replaceAll(DBL_CLICK_STUB, DBL_CLICK_ZOOM_ENABLED_VALUE)
                .replaceAll(ZOOM_CONTROLS_ENABLED_STUB, DEFAULT_ZOOM_CONTROLS_ENABLED);
    }
}
