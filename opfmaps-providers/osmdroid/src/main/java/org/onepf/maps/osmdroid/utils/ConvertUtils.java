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

package org.onepf.maps.osmdroid.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import org.onepf.maps.osmdroid.OsmdroidMapProvider;
import org.onepf.maps.osmdroid.delegate.model.OsmdroidCircleDelegate;
import org.onepf.maps.osmdroid.model.BitmapDescriptor;
import org.onepf.maps.osmdroid.model.CameraPosition;
import org.onepf.maps.osmdroid.model.OsmdroidMapOptions;
import org.onepf.opfmaps.OPFMapHelper;
import org.onepf.opfmaps.OPFMapOptions;
import org.onepf.opfmaps.model.OPFBitmapDescriptor;
import org.onepf.opfmaps.model.OPFCameraPosition;
import org.onepf.opfmaps.model.OPFCircleOptions;
import org.onepf.opfmaps.model.OPFGroundOverlayOptions;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFLatLngBounds;
import org.onepf.opfmaps.model.OPFMapType;
import org.onepf.opfmaps.model.OPFMarkerOptions;
import org.onepf.opfmaps.model.OPFPolygonOptions;
import org.onepf.opfmaps.model.OPFPolylineOptions;
import org.osmdroid.bonuspack.overlays.GroundOverlay;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.bonuspack.overlays.Polygon;
import org.osmdroid.bonuspack.overlays.Polyline;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman Savin
 * @since 12.08.2015
 */
public final class ConvertUtils {

    private ConvertUtils() {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("PMD.NPathComplexity")
    @NonNull
    public static OsmdroidMapOptions convertMapOptions(@NonNull final OPFMapOptions opfOptions) {
        final OsmdroidMapOptions options = new OsmdroidMapOptions()
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
                    new GeoPoint(opfCameraPosition.getTarget().getLat(), opfCameraPosition.getTarget().getLng()),
                    opfCameraPosition.getZoom(),
                    opfCameraPosition.getTilt(),
                    opfCameraPosition.getBearing()
            ));
        }

        return options;
    }

    @NonNull
    public static OsmdroidCircleDelegate convertCircleOptions(@NonNull final MapView mapView,
                                                              @NonNull final OPFCircleOptions options) {
        final OPFLatLng opfCenter = options.getCenter();
        if (opfCenter == null) {
            throw new IllegalArgumentException("no center in circle options");
        }

        final OsmdroidCircleDelegate circleDelegate = new OsmdroidCircleDelegate(
                mapView,
                new Polygon(mapView.getContext()),
                new GeoPoint(opfCenter.getLat(), opfCenter.getLng()),
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

        final OPFLatLngBounds bounds = options.getBounds();
        final OPFLatLng location = options.getLocation();

        if (location != null) {
            groundOverlay.setPosition(new GeoPoint(location.getLat(), location.getLng()));
        } else if (bounds != null) {
            final OPFLatLng boundsCenter = bounds.getCenter();
            groundOverlay.setPosition(new GeoPoint(boundsCenter.getLat(), boundsCenter.getLng()));
        } else {
            throw new IllegalArgumentException("no position in ground overlay options");
        }
        groundOverlay.setDimensions(options.getWidth(), options.getHeight());
        groundOverlay.setEnabled(options.isVisible());

        final OPFBitmapDescriptor opfBitmapDescriptor = options.getImage();
        if (opfBitmapDescriptor == null) {
            throw new IllegalArgumentException("no image in ground overlay options");
        }
        groundOverlay.setImage(((BitmapDescriptor) options.getImage()
                .getDelegate().getBitmapDescriptor()).createDrawable(context));

        return groundOverlay;
    }

    @NonNull
    public static Marker convertMarkerOptions(@NonNull final MapView map, @NonNull final OPFMarkerOptions options) {
        final OPFLatLng position = options.getPosition();
        if (position == null) {
            throw new IllegalArgumentException("no position in marker options");
        }

        final Marker marker = new Marker(map);
        marker.setPosition(new GeoPoint(position.getLat(), position.getLng()));
        marker.setAlpha(options.getAlpha());
        marker.setAnchor(options.getAnchorU(), options.getAnchorV());
        marker.setDraggable(options.isDraggable());
        marker.setFlat(options.isFlat());
        marker.setInfoWindowAnchor(options.getInfoWindowAnchorU(), options.getInfoWindowAnchorV());
        marker.setRotation(options.getRotation());
        marker.setSnippet(options.getSnippet());
        marker.setTitle(options.getTitle());
        marker.setEnabled(options.isVisible());

        final OPFBitmapDescriptor opfBitmapDescriptor = options.getIcon();
        if (opfBitmapDescriptor != null) {
            marker.setIcon(((BitmapDescriptor) options.getIcon()
                    .getDelegate().getBitmapDescriptor()).createDrawable(map.getContext()));
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
            final List<List<GeoPoint>> osmdroidHoles = new ArrayList<>(opfHoles.size());
            for (List<OPFLatLng> hole : opfHoles) {
                final List<GeoPoint> osmdroidHole = new ArrayList<>(hole.size());
                for (OPFLatLng point : hole) {
                    osmdroidHole.add(new GeoPoint(point.getLat(), point.getLng()));
                }
                osmdroidHoles.add(osmdroidHole);
            }
            polygon.setHoles(osmdroidHoles);
        }

        final List<OPFLatLng> opfPoints = options.getPoints();
        if (!opfPoints.isEmpty()) {
            final List<GeoPoint> osmdroidPoints = new ArrayList<>(opfPoints.size());
            for (OPFLatLng point : opfPoints) {
                osmdroidPoints.add(new GeoPoint(point.getLat(), point.getLng()));
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
            final List<GeoPoint> points = new ArrayList<>(opfPoints.size());
            for (OPFLatLng opfPoint : opfPoints) {
                points.add(new GeoPoint(opfPoint.getLat(), opfPoint.getLng()));
            }
            polyline.setPoints(points);
        }

        return polyline;
    }

    @NonNull
    public static ITileSource convertMapTypeToTileSource(@NonNull final OPFMapType type) {
        return ((OsmdroidMapProvider) OPFMapHelper.getInstance().getCurrentProvider()).getTileSourceMap().get(type);
    }
}
