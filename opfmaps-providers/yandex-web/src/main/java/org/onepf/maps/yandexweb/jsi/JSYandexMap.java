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

package org.onepf.maps.yandexweb.jsi;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import org.onepf.maps.yandexweb.model.LatLng;
import org.onepf.maps.yandexweb.utils.ConvertUtils;
import org.onepf.opfmaps.model.OPFMapType;

/**
 * @author Roman Savin
 * @since 08.09.2015
 */
public final class JSYandexMap {

    private static final String SET_TYPE_FUNCTION_NAME = "setType";
    private static final String SET_CENTER_FUNCTION_NAME = "setCenter";
    private static final String SET_ZOOM_LEVEL_FUNCTION_NAME = "setZoom";
    private static final String SET_MY_LOCATION_ENABLED_FUNCTION_NAME = "setMyLocationEnabled";
    private static final String SET_MY_LOCATION_BUTTON_ENABLED_FUNCTION_NAME = "setMyLocationButtonEnabled";
    private static final String SET_SCROLL_GESTURES_ENABLED_FUNCTION_NAME = "setScrollGesturesEnabled";
    private static final String SET_ZOOM_CONTROLS_ENABLED = "setZoomControlsEnabled";
    private static final String SET_ZOOM_GESTURES_ENABLED = "setZoomGesturesEnabled";

    private JSYandexMap() {
        throw new UnsupportedOperationException();
    }

    public static void setType(@NonNull final WebView webView,
                               @NonNull final OPFMapType mapType) {
        evaluateJSFunctionAsync(webView, null, SET_TYPE_FUNCTION_NAME, ConvertUtils.convertMapTypeToJs(mapType));
    }

    public static void setCenter(@NonNull final WebView webView,
                                 @NonNull final LatLng center) {
        evaluateJSFunctionAsync(webView, null, SET_CENTER_FUNCTION_NAME,
                Double.toString(center.getLat()), Double.toString(center.getLng()));
    }

    public static void setZoomLevel(@NonNull final WebView webView,
                                    final float zoomLevel) {
        evaluateJSFunctionAsync(webView, null, SET_ZOOM_LEVEL_FUNCTION_NAME, Float.toString(zoomLevel));
    }

    public static void setMyLocationEnabled(@NonNull final WebView webView,
                                            final boolean isEnabled) {
        evaluateJSFunctionAsync(webView, null, SET_MY_LOCATION_ENABLED_FUNCTION_NAME, Boolean.toString(isEnabled));
    }

    public static void setMyLocationButtonEnabled(@NonNull final WebView webView,
                                                  final boolean isEnabled) {
        evaluateJSFunctionAsync(webView, null, SET_MY_LOCATION_BUTTON_ENABLED_FUNCTION_NAME, Boolean.toString(isEnabled));
    }

    public static void setScrollGesturesEnabled(@NonNull final WebView webView,
                                                final boolean isEnabled) {
        evaluateJSFunctionAsync(webView, null, SET_SCROLL_GESTURES_ENABLED_FUNCTION_NAME, Boolean.toString(isEnabled));
    }

    public static void setZoomControlsEnabled(@NonNull final WebView webView,
                                              final boolean isEnabled) {
        evaluateJSFunctionAsync(webView, null, SET_ZOOM_CONTROLS_ENABLED, Boolean.toString(isEnabled));
    }

    public static void setZoomGesturesEnabled(@NonNull final WebView webView,
                                              final boolean isEnabled) {
        evaluateJSFunctionAsync(webView, null, SET_ZOOM_GESTURES_ENABLED, Boolean.toString(isEnabled));
    }

    private static void evaluateJSFunctionAsync(@NonNull final WebView webView,
                                               @Nullable final ValueCallback<String> resultCallback,
                                               @NonNull final String function,
                                               @Nullable final String... params) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.evaluateJavascript(formatScript(function, params), resultCallback);
        } else {
            //todo make in worker thread
            webView.loadUrl("javascript:" + formatScript(function, params));
        }
    }

    @NonNull
    private static String formatScript(@NonNull final String function,
                                       @Nullable final String[] params) {
        final StringBuilder builder = new StringBuilder(function).append('(');
        if (params != null) {
            final int length = params.length;
            for (int i = 0; i < length; ++i) {
                builder.append('\'').append(params[i]).append('\'');
                if (i != length - 1) {
                    builder.append(',');
                }
            }
        }

        builder.append(')');
        return builder.toString();
    }
}
