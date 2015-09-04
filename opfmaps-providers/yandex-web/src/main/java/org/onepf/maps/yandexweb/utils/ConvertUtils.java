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

import org.onepf.maps.yandexweb.model.CameraPosition;
import org.onepf.maps.yandexweb.model.LatLng;
import org.onepf.maps.yandexweb.model.YaWebMapOptions;
import org.onepf.opfmaps.OPFMapOptions;
import org.onepf.opfmaps.model.OPFCameraPosition;

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
        //todo implement

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

 /*   @NonNull
    public static YaWebCircleDelegate convertCircleOptions(@NonNull final MapView mapView,
                                                           @NonNull final OPFCircleOptions options) {
        final OPFLatLng opfCenter = options.getCenter();
        if (opfCenter == null) {
            throw new IllegalArgumentException("Center is not specified");
        }

        final YaWebCircleDelegate circleDelegate = new YaWebCircleDelegate(
                mapView,
                new Polygon(mapView.getContext()),
                new LatLng(opfCenter.getLat(), opfCenter.getLng()),
                options.getRadius()
        );

        circleDelegate.setFillColor(options.getFillColor());
        circleDelegate.setStrokeColor(options.getStrokeColor());
        circleDelegate.setStrokeWidth(options.getStrokeWidth());
        circleDelegate.setVisible(options.isVisible());

        return circleDelegate;
    }

    @NonNull
    public static GroundOverlay convertGroundOverlayOptions(@NonNull final Context context,
                                                            @NonNull final OPFGroundOverlayOptions options) {
        final GroundOverlay groundOverlay = new GroundOverlay(context);
        groundOverlay.setBearing(options.getBearing());
        groundOverlay.setTransparency(options.getTransparency());

        final OPFBitmapDescriptor opfBitmapDescriptor = options.getImage();
        if (opfBitmapDescriptor != null) {
            groundOverlay.setImage(((BitmapDescriptor) options.getImage()
                    .getDelegate().getBitmapDescriptor()).createDrawable(context));
        }

        final OPFLatLng location = options.getLocation();
        if (location != null) {
            groundOverlay.setPosition(new LatLng(location.getLat(), location.getLng()));
        }

        return groundOverlay;
    }

    @NonNull
    public static Marker convertMarkerOptions(@NonNull final MapView map, @NonNull final OPFMarkerOptions options) {
        final Marker marker = new Marker(map);
        marker.setAlpha(options.getAlpha());
        marker.setAnchor(options.getAnchorU(), options.getAnchorV());
        marker.setDraggable(options.isDraggable());
        marker.setFlat(options.isFlat());
        marker.setInfoWindowAnchor(options.getInfoWindowAnchorU(), options.getInfoWindowAnchorV());
        marker.setRotation(options.getRotation());
        marker.setSnippet(options.getSnippet());
        marker.setTitle(options.getTitle());

        final OPFBitmapDescriptor opfBitmapDescriptor = options.getIcon();
        if (opfBitmapDescriptor != null) {
            marker.setIcon(((BitmapDescriptor) options.getIcon()
                    .getDelegate().getBitmapDescriptor()).createDrawable(map.getContext()));
        }

        final OPFLatLng position = options.getPosition();
        if (position != null) {
            marker.setPosition(new LatLng(position.getLat(), position.getLng()));
        }

        return marker;
    }

    @NonNull
    public static Polygon convertPolygonOptions(@NonNull final Context context, @NonNull final OPFPolygonOptions options) {
        final Polygon polygon = new Polygon(context);
        polygon.setFillColor(options.getFillColor());
        polygon.setStrokeColor(options.getStrokeColor());
        polygon.setStrokeWidth(options.getStrokeWidth());
        polygon.setVisible(options.isVisible());

        final List<List<OPFLatLng>> opfHoles = options.getHoles();
        if (!opfHoles.isEmpty()) {
            final List<List<LatLng>> osmdroidHoles = new ArrayList<>(opfHoles.size());
            for (List<OPFLatLng> hole : opfHoles) {
                final List<LatLng> osmdroidHole = new ArrayList<>(hole.size());
                for (OPFLatLng point : hole) {
                    osmdroidHole.add(new LatLng(point.getLat(), point.getLng()));
                }
                osmdroidHoles.add(osmdroidHole);
            }
            polygon.setHoles(osmdroidHoles);
        }

        final List<OPFLatLng> opfPoints = options.getPoints();
        if (!opfPoints.isEmpty()) {
            final List<LatLng> osmdroidPoints = new ArrayList<>(opfPoints.size());
            for (OPFLatLng point : opfPoints) {
                osmdroidPoints.add(new LatLng(point.getLat(), point.getLng()));
            }
            polygon.setPoints(osmdroidPoints);
        }

        return polygon;
    }

    @NonNull
    public static Polyline convertPolylineOptions(@NonNull final Context context,
                                                  @NonNull final OPFPolylineOptions options) {
        final Polyline polyline = new Polyline(context);
        polyline.setColor(options.getColor());
        polyline.setGeodesic(options.isGeodesic());
        polyline.setVisible(options.isVisible());
        polyline.setWidth(options.getWidth());

        final List<OPFLatLng> opfPoints = options.getPoints();
        if (!opfPoints.isEmpty()) {
            final List<LatLng> points = new ArrayList<>(opfPoints.size());
            for (OPFLatLng opfPoint : opfPoints) {
                points.add(new LatLng(opfPoint.getLat(), opfPoint.getLng()));
            }
            polyline.setPoints(points);
        }

        return polyline;
    }
*/
}
