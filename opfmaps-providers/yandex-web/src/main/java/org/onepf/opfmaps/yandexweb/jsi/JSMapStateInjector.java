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

package org.onepf.opfmaps.yandexweb.jsi;

import android.support.annotation.NonNull;
import org.onepf.opfmaps.yandexweb.delegate.YaWebMapViewDelegate;
import org.onepf.opfmaps.yandexweb.model.LatLng;
import org.onepf.opfmaps.yandexweb.utils.ConvertUtils;

import java.util.Locale;

/**
 * @author Roman Savin
 * @since 04.09.2015
 */
public final class JSMapStateInjector {

    public static final String JS_MAP_TYPE_NORMAL = "yandex#map";
    public static final String JS_MAP_TYPE_SATELLITE = "yandex#satellite";
    public static final String JS_MAP_TYPE_HYBRID = "yandex#hybrid";

    private static final String LOCALIZATION_STUB = "LOCALIZATION_STUB";
    private static final String MAP_TYPE_STUB = "MAP_TYPE_STUB";
    private static final String CENTER_LAT_STUB = "CENTER_LAT_STUB";
    private static final String CENTER_LNG_STUB = "CENTER_LNG_STUB";
    private static final String ZOOM_STUB = "ZOOM_STUB";
    private static final String DRAG_STUB = "DRAG_STUB";
    private static final String MULTI_TOUCH_STUB = "MULTI_TOUCH_STUB";
    private static final String DBL_CLICK_STUB = "DBL_CLICK_STUB";
    private static final String ZOOM_CONTROLS_ENABLED_STUB = "ZOOM_CONTROLS_ENABLED_STUB";
    private static final String MY_LOCATION_ENABLED_STUB = "MY_LOCATION_ENABLED_STUB";
    private static final String MY_LOCATION_BUTTON_ENABLED_STUB = "MY_LOCATION_BUTTON_ENABLED_STUB";

    private static final String OPTION_DISABLED_VALUE = "";

    private static final String DRAG_ENABLED_VALUE = "drag";
    private static final String MULTI_TOUCH_ENABLED_VALUE = "multiTouch";
    private static final String DBL_CLICK_ZOOM_ENABLED_VALUE = "dblClickZoom";

    private JSMapStateInjector() {
        throw new UnsupportedOperationException();
    }

    @NonNull
    public static String injectMapState(@NonNull final String html,
                                        @NonNull final YaWebMapViewDelegate.MapState mapState) {
        final LatLng center = mapState.getCenter();

        return html.replaceAll(LOCALIZATION_STUB, Locale.getDefault().toString())
                .replaceAll(MAP_TYPE_STUB, ConvertUtils.convertMapTypeToJs(mapState.getMapType()))
                .replaceAll(CENTER_LAT_STUB, Double.toString(center.getLat()))
                .replaceAll(CENTER_LNG_STUB, Double.toString(center.getLng()))
                .replaceAll(ZOOM_STUB, Float.toString(mapState.getZoomLevel()))
                .replaceAll(DRAG_STUB, mapState.isScrollGesturesEnabled() ? DRAG_ENABLED_VALUE : OPTION_DISABLED_VALUE)
                .replaceAll(MULTI_TOUCH_STUB, mapState.isZoomGesturesEnabled() ? MULTI_TOUCH_ENABLED_VALUE : OPTION_DISABLED_VALUE)
                .replaceAll(DBL_CLICK_STUB, mapState.isZoomGesturesEnabled() ? DBL_CLICK_ZOOM_ENABLED_VALUE : OPTION_DISABLED_VALUE)
                .replaceAll(ZOOM_CONTROLS_ENABLED_STUB, Boolean.toString(mapState.isZoomControlsEnabled()))
                .replaceAll(MY_LOCATION_ENABLED_STUB, Boolean.toString(mapState.isMyLocationEnabled()))
                .replaceAll(MY_LOCATION_BUTTON_ENABLED_STUB, Boolean.toString(mapState.isMyLocationButtonEnabled()));
    }
}
