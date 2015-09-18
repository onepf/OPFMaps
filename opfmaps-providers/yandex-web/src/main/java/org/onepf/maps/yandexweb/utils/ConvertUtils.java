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
import android.webkit.WebView;
import org.onepf.maps.yandexweb.jsi.JSMapStateInjector;
import org.onepf.maps.yandexweb.model.CameraPosition;
import org.onepf.maps.yandexweb.model.Circle;
import org.onepf.maps.yandexweb.model.LatLng;
import org.onepf.maps.yandexweb.model.Marker;
import org.onepf.maps.yandexweb.model.Polygon;
import org.onepf.maps.yandexweb.model.Polyline;
import org.onepf.maps.yandexweb.model.YaWebMapOptions;
import org.onepf.opfmaps.OPFMapOptions;
import org.onepf.opfmaps.model.OPFCameraPosition;
import org.onepf.opfmaps.model.OPFCircleOptions;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFMapType;
import org.onepf.opfmaps.model.OPFMarkerOptions;
import org.onepf.opfmaps.model.OPFPolygonOptions;
import org.onepf.opfmaps.model.OPFPolylineOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class ConvertUtils {

    private ConvertUtils() {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("PMD.NPathComplexity")
    @NonNull
    public static YaWebMapOptions convertMapOptions(@NonNull final OPFMapOptions opfOptions) {
        final YaWebMapOptions options = new YaWebMapOptions()
                .mapType(opfOptions.getMapType());

        if (opfOptions.getCompassEnabled() != null) {
            options.compassEnabled(opfOptions.getCompassEnabled());
        }
        if (opfOptions.getLiteMode() != null) {
            options.liteMode(opfOptions.getLiteMode());
        }
        if (opfOptions.getMapToolbarEnabled() != null) {
            options.mapToolbarEnabled(opfOptions.getMapToolbarEnabled());
        }
        if (opfOptions.getRotateGesturesEnabled() != null) {
            options.rotateGesturesEnabled(opfOptions.getRotateGesturesEnabled());
        }
        if (opfOptions.getScrollGesturesEnabled() != null) {
            options.scrollGesturesEnabled(opfOptions.getScrollGesturesEnabled());
        }
        if (opfOptions.getTiltGesturesEnabled() != null) {
            options.tiltGesturesEnabled(opfOptions.getTiltGesturesEnabled());
        }
        if (opfOptions.getUseViewLifecycleInFragment() != null) {
            options.useViewLifecycleInFragment(opfOptions.getUseViewLifecycleInFragment());
        }
        if (opfOptions.getZOrderOnTop() != null) {
            options.zOrderOnTop(opfOptions.getZOrderOnTop());
        }
        if (opfOptions.getZoomControlsEnabled() != null) {
            options.zoomControlsEnabled(opfOptions.getZoomControlsEnabled());
        }
        if (opfOptions.getZoomGesturesEnabled() != null) {
            options.zoomGesturesEnabled(opfOptions.getZoomGesturesEnabled());
        }

        final OPFCameraPosition opfCameraPosition = opfOptions.getCamera();
        if (opfCameraPosition != null) {
            options.camera(new CameraPosition(
                    new LatLng(opfCameraPosition.getTarget().getLat(), opfCameraPosition.getTarget().getLng()),
                    opfCameraPosition.getZoom(),
                    opfCameraPosition.getTilt(),
                    opfCameraPosition.getBearing()
            ));
        }

        return options;
    }

    @NonNull
    public static Circle convertCircleOptions(@NonNull final WebView webView, @NonNull final OPFCircleOptions options) {
        final OPFLatLng center = options.getCenter();
        if (center == null) {
            throw new IllegalArgumentException("Circle center can't be null");
        }

        return new Circle(
                webView,
                new LatLng(center.getLat(), center.getLng()),
                options.getFillColor(),
                options.getRadius(),
                options.getStrokeColor(),
                options.getStrokeWidth(),
                options.getZIndex(),
                options.isVisible()
        );
    }

    @NonNull
    public static Marker convertMarkerOptions(@NonNull final WebView webView,
                                              @Nullable final Map<String, Marker> markersByIds,
                                              @NonNull final OPFMarkerOptions options) {
        final OPFLatLng position = options.getPosition();
        if (position == null) {
            throw new IllegalArgumentException("Marker position can't be null");
        }

        return new Marker(
                webView,
                markersByIds,
                new LatLng(position.getLat(), position.getLng()),
                options.getTitle(),
                options.getSnippet(),
                options.isDraggable(),
                options.isVisible()
        );
    }

    @NonNull
    public static Polygon convertPolygonOptions(@NonNull final WebView webView,
                                                @NonNull final OPFPolygonOptions options) {
        final List<OPFLatLng> opfPoints = options.getPoints();
        final List<LatLng> points = new ArrayList<>(opfPoints.size());
        for (OPFLatLng opfPoint : opfPoints) {
            points.add(new LatLng(opfPoint.getLat(), opfPoint.getLng()));
        }

        final List<List<OPFLatLng>> opfHoles = options.getHoles();
        final List<List<LatLng>> holes = new ArrayList<>(opfHoles.size());
        for (List<OPFLatLng> opfHole : opfHoles) {
            final List<LatLng> hole = new ArrayList<>(opfHole.size());
            for (OPFLatLng opfHolePoint : opfHole) {
                hole.add(new LatLng(opfHolePoint.getLat(), opfHolePoint.getLng()));
            }
            holes.add(hole);
        }


        return new Polygon(
                webView,
                points,
                holes,
                options.getFillColor(),
                options.getStrokeColor(),
                options.getStrokeWidth(),
                options.getZIndex(),
                options.isVisible()
        );
    }

    @NonNull
    public static Polyline convertPolylineOptions(@NonNull final WebView webView,
                                                  @NonNull final OPFPolylineOptions options) {
        final List<OPFLatLng> opfPoints = options.getPoints();
        final List<LatLng> points = new ArrayList<>(opfPoints.size());
        for (OPFLatLng opfPoint : opfPoints) {
            points.add(new LatLng(opfPoint.getLat(), opfPoint.getLng()));
        }

        return new Polyline(
                webView,
                points,
                options.getColor(),
                options.getWidth(),
                options.getZIndex(),
                options.isVisible()
        );
    }

    //CHECKSTYLE:OFF
    @NonNull
    public static String convertColor(final int color) {
        return String.format("#%06X", 0xFFFFFF & color);
    }
    //CHECKSTYLE:ON

    @NonNull
    public static String convertMapTypeToJs(@NonNull final OPFMapType type) {
        switch (type) {
            case HYBRID:
                return JSMapStateInjector.JS_MAP_TYPE_HYBRID;
            case SATELLITE:
                return JSMapStateInjector.JS_MAP_TYPE_SATELLITE;
            default:
                return JSMapStateInjector.JS_MAP_TYPE_NORMAL;
        }
    }

    @NonNull
    public static OPFMapType convertMapTypeFromJs(@NonNull final String jsType) {
        switch (jsType) {
            case JSMapStateInjector.JS_MAP_TYPE_HYBRID:
                return OPFMapType.HYBRID;
            case JSMapStateInjector.JS_MAP_TYPE_SATELLITE:
                return OPFMapType.SATELLITE;
            default:
                return OPFMapType.NORMAL;
        }
    }
}
