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
import android.webkit.WebView;
import org.onepf.maps.yandexweb.model.Circle;
import org.onepf.maps.yandexweb.model.LatLng;
import org.onepf.maps.yandexweb.model.Marker;
import org.onepf.maps.yandexweb.model.Polygon;
import org.onepf.maps.yandexweb.model.Polyline;
import org.onepf.maps.yandexweb.utils.ConvertUtils;
import org.onepf.opfmaps.model.OPFMapType;

import java.util.List;

import static java.util.Locale.US;

/**
 * @author Roman Savin
 * @since 08.09.2015
 */
public final class JSYandexMapProxy {

    public static final String FILL_COLOR_OPTION = "fillColor";
    public static final String STROKE_COLOR_OPTION = "strokeColor";
    public static final String STROKE_WIDTH_OPTION = "strokeWidth";
    public static final String Z_INDEX_OPTION = "zIndex";
    public static final String VISIBLE_OPTION = "visible";
    public static final String DRAGGABLE_OPTION = "draggable";
    public static final String ICON_COLOR_OPTION = "iconColor";
    public static final String BALLOON_CONTENT_HEADER_OPTION = "balloonContentHeader";
    public static final String BALLOON_CONTENT_BODY_OPTION = "balloonContentBody";

    private static final String SET_TYPE_FUNCTION_NAME = "setType";
    private static final String SET_CENTER_FUNCTION_NAME = "setCenter";
    private static final String SET_ZOOM_LEVEL_FUNCTION_NAME = "setZoom";
    private static final String SET_MY_LOCATION_ENABLED_FUNCTION_NAME = "setMyLocationEnabled";
    private static final String SET_MY_LOCATION_BUTTON_ENABLED_FUNCTION_NAME = "setMyLocationButtonEnabled";
    private static final String SET_SCROLL_GESTURES_ENABLED_FUNCTION_NAME = "setScrollGesturesEnabled";
    private static final String SET_ZOOM_CONTROLS_ENABLED_FUNCTION_NAME = "setZoomControlsEnabled";
    private static final String SET_ZOOM_GESTURES_ENABLED_FUNCTION_NAME = "setZoomGesturesEnabled";
    private static final String ADD_CIRCLE_FUNCTION_NAME = "addCircle";
    private static final String ADD_MARKER_FUNCTION_NAME = "addMarker";
    private static final String ADD_POLYGON_FUNCTION_NAME = "addPolygon";
    private static final String ADD_POLYLINE_FUNCTION_NAME = "addPolyline";
    private static final String HIDE_BALLOON_FUNCTION_NAME = "hideBalloon";
    private static final String SHOW_BALLOON_FUNCTION_NAME = "showBalloon";
    private static final String TOGGLE_BALLOON_FUNCTION_NAME = "toggleBalloon";
    private static final String SET_GEO_OBJECT_OPTION = "setGeoObjectOption";
    private static final String SET_GEO_OBJECT_PROPERTY = "setGeoObjectProperty";
    private static final String REMOVE_GEO_OBJECT_FUNCTION_NAME = "removeGeoObject";
    private static final String SET_GEO_OBJECT_COORDINATES_FUNCTION_NAME = "setGeoObjectCoordinates";
    private static final String SET_CIRCLE_RADIUS_FUNCTION_NAME = "setCircleRadius";
    private static final String CLEAR_MAP_FUNCTION_NAME = "clearMap";
    private static final String SET_TRAFFIC_ENABLED_FUNCTION_NAME = "setTrafficEnabled";

    private JSYandexMapProxy() {
        throw new UnsupportedOperationException();
    }

    public static void setMapType(@NonNull final WebView webView,
                                  @NonNull final OPFMapType mapType) {
        evaluateJSFunctionAsync(webView, SET_TYPE_FUNCTION_NAME, wrapToQuotes(ConvertUtils.convertMapTypeToJs(mapType)));
    }

    public static void setMapCenter(@NonNull final WebView webView,
                                    @NonNull final LatLng center) {
        evaluateJSFunctionAsync(webView, SET_CENTER_FUNCTION_NAME,
                Double.toString(center.getLat()), Double.toString(center.getLng()));
    }

    public static void setZoomLevel(@NonNull final WebView webView,
                                    final float zoomLevel) {
        evaluateJSFunctionAsync(webView, SET_ZOOM_LEVEL_FUNCTION_NAME, Float.toString(zoomLevel));
    }

    public static void setMyLocationEnabled(@NonNull final WebView webView,
                                            final boolean isEnabled) {
        evaluateJSFunctionAsync(webView, SET_MY_LOCATION_ENABLED_FUNCTION_NAME, Boolean.toString(isEnabled));
    }

    public static void setMyLocationButtonEnabled(@NonNull final WebView webView,
                                                  final boolean isEnabled) {
        evaluateJSFunctionAsync(webView, SET_MY_LOCATION_BUTTON_ENABLED_FUNCTION_NAME, Boolean.toString(isEnabled));
    }

    public static void setScrollGesturesEnabled(@NonNull final WebView webView,
                                                final boolean isEnabled) {
        evaluateJSFunctionAsync(webView, SET_SCROLL_GESTURES_ENABLED_FUNCTION_NAME, Boolean.toString(isEnabled));
    }

    public static void setZoomControlsEnabled(@NonNull final WebView webView,
                                              final boolean isEnabled) {
        evaluateJSFunctionAsync(webView, SET_ZOOM_CONTROLS_ENABLED_FUNCTION_NAME, Boolean.toString(isEnabled));
    }

    public static void setZoomGesturesEnabled(@NonNull final WebView webView,
                                              final boolean isEnabled) {
        evaluateJSFunctionAsync(webView, SET_ZOOM_GESTURES_ENABLED_FUNCTION_NAME, Boolean.toString(isEnabled));
    }

    public static void addCircle(@NonNull final WebView webView,
                                 @NonNull final Circle circle) {

        evaluateJSFunctionAsync(
                webView,
                ADD_CIRCLE_FUNCTION_NAME,
                wrapToQuotes(circle.getId()),
                Double.toString(circle.getCenter().getLat()),
                Double.toString(circle.getCenter().getLng()),
                Double.toString(circle.getRadius()),
                wrapToQuotes(ConvertUtils.convertColor(circle.getFillColor())),
                wrapToQuotes(ConvertUtils.convertColor(circle.getStrokeColor())),
                Float.toString(circle.getStrokeWidth()),
                Float.toString(circle.getZIndex()),
                Boolean.toString(circle.isVisible())
        );
    }

    public static void addMarker(@NonNull final WebView webView,
                                 @NonNull final Marker marker,
                                 @NonNull final String color) {
        evaluateJSFunctionAsync(
                webView,
                ADD_MARKER_FUNCTION_NAME,
                wrapToQuotes(marker.getId()),
                Double.toString(marker.getPosition().getLat()),
                Double.toString(marker.getPosition().getLng()),
                wrapToQuotes(marker.getTitle()),
                wrapToQuotes(marker.getSnippet()),
                Boolean.toString(marker.isVisible()),
                Boolean.toString(marker.isDraggable()),
                wrapToQuotes(color)
        );
    }

    public static void addPolygon(@NonNull final WebView webView, @NonNull final Polygon polygon) {
        final StringBuilder js3dArrayBuilder = new StringBuilder();
        build3DJSLatLngArray(js3dArrayBuilder, polygon.getPoints(), polygon.getHoles());
        evaluateJSFunctionAsync(
                webView,
                ADD_POLYGON_FUNCTION_NAME,
                wrapToQuotes(polygon.getId()),
                js3dArrayBuilder.toString(),
                wrapToQuotes(ConvertUtils.convertColor(polygon.getFillColor())),
                wrapToQuotes(ConvertUtils.convertColor(polygon.getStrokeColor())),
                Float.toString(polygon.getStrokeWidth()),
                Float.toString(polygon.getZIndex()),
                Boolean.toString(polygon.isVisible())
        );
    }

    public static void addPolyline(@NonNull final WebView webView, @NonNull final Polyline polyline) {
        final StringBuilder js2dArrayBuilder = new StringBuilder();
        build2DJSLatLngArray(js2dArrayBuilder, polyline.getPoints());
        evaluateJSFunctionAsync(
                webView,
                ADD_POLYLINE_FUNCTION_NAME,
                wrapToQuotes(polyline.getId()),
                js2dArrayBuilder.toString(),
                wrapToQuotes(ConvertUtils.convertColor(polyline.getColor())),
                Float.toString(polyline.getWidth()),
                Float.toString(polyline.getZIndex()),
                Boolean.toString(polyline.isVisible())
        );
    }

    public static void hideInfoWindow(@NonNull final WebView webView,
                                      @NonNull final String id) {
        evaluateJSFunctionAsync(webView, HIDE_BALLOON_FUNCTION_NAME, wrapToQuotes(id));
    }

    public static void showInfoWindow(@NonNull final WebView webView,
                                      @NonNull final String id) {
        evaluateJSFunctionAsync(webView, SHOW_BALLOON_FUNCTION_NAME, wrapToQuotes(id));
    }

    public static void toggleInfoWindow(@NonNull final WebView webView,
                                        @NonNull final String id) {
        evaluateJSFunctionAsync(webView, TOGGLE_BALLOON_FUNCTION_NAME, wrapToQuotes(id));
    }

    public static void setGeoObjectOption(@NonNull final WebView webView,
                                          @NonNull final String id,
                                          @NonNull final String option,
                                          @NonNull final Object value) {
        setGeoObjectOptionFormatted(webView, id, option, value.toString());
    }

    public static void setGeoObjectOption(@NonNull final WebView webView,
                                          @NonNull final String id,
                                          @NonNull final String option,
                                          @NonNull final String value) {
        setGeoObjectOptionFormatted(webView, id, option, wrapToQuotes(value));
    }

    public static void setGeoObjectProperty(@NonNull final WebView webView,
                                            @NonNull final String id,
                                            @NonNull final String option,
                                            @NonNull final String value) {
        setGeoObjectPropertyFormatted(webView, id, option, wrapToQuotes(value));
    }

    public static void setGeoObjectCoordinates(@NonNull final WebView webView,
                                               @NonNull final String id,
                                               @NonNull final LatLng center) {
        final StringBuilder jsLatLngArrayBuilder = new StringBuilder();
        buildJSLatLngArray(jsLatLngArrayBuilder, center);
        evaluateJSFunctionAsync(webView, SET_GEO_OBJECT_COORDINATES_FUNCTION_NAME,
                wrapToQuotes(id), jsLatLngArrayBuilder.toString());
    }

    public static void setGeoObjectCoordinates(@NonNull final WebView webView,
                                               @NonNull final String id,
                                               @NonNull final List<LatLng> points) {
        final StringBuilder js2dLatLngArrayBuilder = new StringBuilder();
        build2DJSLatLngArray(js2dLatLngArrayBuilder, points);
        evaluateJSFunctionAsync(webView, SET_GEO_OBJECT_COORDINATES_FUNCTION_NAME,
                wrapToQuotes(id), js2dLatLngArrayBuilder.toString());
    }

    public static void setGeoObjectCoordinates(@NonNull final WebView webView,
                                               @NonNull final String id,
                                               @NonNull final List<LatLng> points,
                                               @Nullable final List<List<LatLng>> holes) {
        final StringBuilder js3dLatLngArrayBuilder = new StringBuilder();
        build3DJSLatLngArray(js3dLatLngArrayBuilder, points, holes);
        evaluateJSFunctionAsync(webView, SET_GEO_OBJECT_COORDINATES_FUNCTION_NAME,
                wrapToQuotes(id), js3dLatLngArrayBuilder.toString());
    }

    public static void setCircleRadius(@NonNull final WebView webView,
                                       @NonNull final String id,
                                       final double radius) {
        evaluateJSFunctionAsync(webView, SET_CIRCLE_RADIUS_FUNCTION_NAME, wrapToQuotes(id), Double.toString(radius));
    }

    public static void removeGeoObject(@NonNull final WebView webView,
                                       @NonNull final String id) {
        evaluateJSFunctionAsync(webView, REMOVE_GEO_OBJECT_FUNCTION_NAME, wrapToQuotes(id));
    }

    public static void clearMap(@NonNull final WebView webView) {
        evaluateJSFunctionAsync(webView, CLEAR_MAP_FUNCTION_NAME);
    }

    public static void setTrafficEnabled(@NonNull final WebView webView, final boolean isEnabled) {
        evaluateJSFunctionAsync(webView, SET_TRAFFIC_ENABLED_FUNCTION_NAME, Boolean.toString(isEnabled));
    }

    private static void setGeoObjectOptionFormatted(@NonNull final WebView webView,
                                                    @NonNull final String id,
                                                    @NonNull final String option,
                                                    @NonNull final String value) {
        evaluateJSFunctionAsync(webView, SET_GEO_OBJECT_OPTION, wrapToQuotes(id), wrapToQuotes(option), value);
    }

    private static void setGeoObjectPropertyFormatted(@NonNull final WebView webView,
                                                      @NonNull final String id,
                                                      @NonNull final String property,
                                                      @NonNull final String value) {
        evaluateJSFunctionAsync(webView, SET_GEO_OBJECT_PROPERTY, wrapToQuotes(id), wrapToQuotes(property), value);
    }

    private static void evaluateJSFunctionAsync(@NonNull final WebView webView,
                                                @NonNull final String function,
                                                @Nullable final String... params) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.evaluateJavascript(formatScript(function, params), null);
        } else {
            webView.loadUrl("javascript:" + formatScript(function, params));
        }
    }

    private static void build3DJSLatLngArray(@NonNull final StringBuilder stringBuilder,
                                             @NonNull final List<LatLng> points,
                                             @Nullable final List<List<LatLng>> holes) {
        stringBuilder.append('[');
        build2DJSLatLngArray(stringBuilder, points);
        stringBuilder.append(',');

        if (holes != null) {
            for (List<LatLng> hole : holes) {
                build2DJSLatLngArray(stringBuilder, hole);
                stringBuilder.append(',');
            }
        }

        stringBuilder.append(']');
    }

    private static void build2DJSLatLngArray(@NonNull final StringBuilder stringBuilder,
                                             @NonNull final List<LatLng> points) {
        stringBuilder.append('[');

        for (LatLng point : points) {
            buildJSLatLngArray(stringBuilder, point);
            stringBuilder.append(',');
        }

        stringBuilder.append(']');
    }

    private static void buildJSLatLngArray(@NonNull final StringBuilder stringBuilder,
                                           @NonNull final LatLng latLng) {
        stringBuilder.append('[').append(latLng.getLat()).append(',').append(latLng.getLng()).append(']');
    }

    @NonNull
    private static String wrapToQuotes(@Nullable final String param) {
        return String.format(US, "'%s'", param != null ? param : "");
    }

    @NonNull
    private static String formatScript(@NonNull final String function,
                                       @Nullable final String[] params) {
        final StringBuilder builder = new StringBuilder(function).append('(');
        if (params != null) {
            final int length = params.length;
            for (int i = 0; i < length; ++i) {
                builder.append(params[i]);
                if (i != length - 1) {
                    builder.append(',');
                }
            }
        }

        builder.append(')');
        return builder.toString();
    }
}
