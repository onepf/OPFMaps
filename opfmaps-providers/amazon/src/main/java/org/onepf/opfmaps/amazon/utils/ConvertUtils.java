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

package org.onepf.opfmaps.amazon.utils;

import android.support.annotation.NonNull;
import com.amazon.geo.mapsv2.AmazonMapOptions;
import com.amazon.geo.mapsv2.model.BitmapDescriptor;
import com.amazon.geo.mapsv2.model.CameraPosition;
import com.amazon.geo.mapsv2.model.CircleOptions;
import com.amazon.geo.mapsv2.model.LatLng;
import com.amazon.geo.mapsv2.model.MarkerOptions;
import com.amazon.geo.mapsv2.model.PolygonOptions;
import com.amazon.geo.mapsv2.model.PolylineOptions;
import org.onepf.opfmaps.OPFMapOptions;
import org.onepf.opfmaps.model.OPFBitmapDescriptor;
import org.onepf.opfmaps.model.OPFCameraPosition;
import org.onepf.opfmaps.model.OPFCircleOptions;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFMapType;
import org.onepf.opfmaps.model.OPFMarkerOptions;
import org.onepf.opfmaps.model.OPFPolygonOptions;
import org.onepf.opfmaps.model.OPFPolylineOptions;

import java.util.ArrayList;
import java.util.List;

import static com.amazon.geo.mapsv2.AmazonMap.MAP_TYPE_HYBRID;
import static com.amazon.geo.mapsv2.AmazonMap.MAP_TYPE_NONE;
import static com.amazon.geo.mapsv2.AmazonMap.MAP_TYPE_NORMAL;
import static com.amazon.geo.mapsv2.AmazonMap.MAP_TYPE_SATELLITE;
import static com.amazon.geo.mapsv2.AmazonMap.MAP_TYPE_TERRAIN;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
@SuppressWarnings("PMD.NPathComplexity")
public final class ConvertUtils {

    private ConvertUtils() {
        throw new UnsupportedOperationException();
    }

    public static AmazonMapOptions convertMapOptions(@NonNull final OPFMapOptions opfOptions) {
        final AmazonMapOptions options = new AmazonMapOptions()
                .mapType(convertMapType(opfOptions.getMapType()));

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

    public static CircleOptions convertCircleOptions(@NonNull final OPFCircleOptions opfOptions) {
        final CircleOptions options = new CircleOptions();
        final OPFLatLng center = opfOptions.getCenter();
        if (center != null) {
            options.center(new LatLng(center.getLat(), center.getLng()));
        }
        options.fillColor(opfOptions.getFillColor())
                .radius(opfOptions.getRadius())
                .strokeColor(opfOptions.getStrokeColor())
                .strokeWidth(opfOptions.getStrokeWidth())
                .visible(opfOptions.isVisible())
                .zIndex(opfOptions.getZIndex());
        return options;
    }

    public static MarkerOptions convertMarkerOptions(@NonNull final OPFMarkerOptions opfOptions) {
        final MarkerOptions options = new MarkerOptions()
                .alpha(opfOptions.getAlpha())
                .anchor(opfOptions.getAnchorU(), opfOptions.getAnchorV())
                .draggable(opfOptions.isDraggable())
                .flat(opfOptions.isFlat())
                .infoWindowAnchor(opfOptions.getInfoWindowAnchorU(), opfOptions.getInfoWindowAnchorV())
                .rotation(opfOptions.getRotation())
                .snippet(opfOptions.getSnippet())
                .title(opfOptions.getTitle())
                .visible(opfOptions.isVisible());

        final OPFBitmapDescriptor icon = opfOptions.getIcon();
        if (icon != null) {
            options.icon((BitmapDescriptor) icon.getDelegate().getBitmapDescriptor());
        }

        final OPFLatLng position = opfOptions.getPosition();
        if (position != null) {
            options.position(new LatLng(position.getLat(), position.getLng()));
        }

        return options;
    }

    public static PolygonOptions convertPolygonOptions(@NonNull final OPFPolygonOptions opfOptions) {
        final PolygonOptions options = new PolygonOptions()
                .fillColor(opfOptions.getFillColor())
                .geodesic(opfOptions.isGeodesic())
                .strokeColor(opfOptions.getStrokeColor())
                .strokeWidth(opfOptions.getStrokeWidth())
                .visible(opfOptions.isVisible())
                .zIndex(opfOptions.getZIndex());

        final List<OPFLatLng> opfPoints = opfOptions.getPoints();
        if (!opfPoints.isEmpty()) {
            final List<LatLng> points = new ArrayList<>(opfPoints.size());
            for (OPFLatLng opfPoint : opfPoints) {
                points.add(new LatLng(opfPoint.getLat(), opfPoint.getLng()));
            }
            options.addAll(points);
        }

        final List<List<OPFLatLng>> opfHoles = opfOptions.getHoles();
        if (!opfHoles.isEmpty()) {
            for (List<OPFLatLng> opfHole : opfHoles) {
                final List<LatLng> hole = new ArrayList<>(opfHole.size());
                for (OPFLatLng point : opfHole) {
                    hole.add(new LatLng(point.getLat(), point.getLng()));
                }
                options.addHole(hole);
            }
        }

        return options;
    }

    public static PolylineOptions convertPolylineOptions(@NonNull final OPFPolylineOptions opfOptions) {
        final PolylineOptions options = new PolylineOptions()
                .color(opfOptions.getColor())
                .geodesic(opfOptions.isGeodesic())
                .visible(opfOptions.isVisible())
                .width(opfOptions.getWidth())
                .zIndex(opfOptions.getZIndex());

        final List<OPFLatLng> opfPoints = opfOptions.getPoints();
        if (!opfPoints.isEmpty()) {
            final List<LatLng> points = new ArrayList<>(opfPoints.size());
            for (OPFLatLng opfPoint : opfPoints) {
                points.add(new LatLng(opfPoint.getLat(), opfPoint.getLng()));
            }
            options.addAll(points);
        }

        return options;
    }

    public static int convertMapType(@NonNull final OPFMapType type) {
        switch (type) {
            case HYBRID:
                return MAP_TYPE_HYBRID;
            case NONE:
                return MAP_TYPE_NONE;
            case SATELLITE:
                return MAP_TYPE_SATELLITE;
            case TERRAIN:
                return MAP_TYPE_TERRAIN;
            default:
                return MAP_TYPE_NORMAL;
        }
    }

    @NonNull
    public static OPFMapType convertMapType(final int type) {
        switch (type) {
            case MAP_TYPE_HYBRID:
                return OPFMapType.HYBRID;
            case MAP_TYPE_NONE:
                return OPFMapType.NONE;
            case MAP_TYPE_SATELLITE:
                return OPFMapType.SATELLITE;
            case MAP_TYPE_TERRAIN:
                return OPFMapType.TERRAIN;
            default:
                return OPFMapType.NORMAL;
        }
    }
}
