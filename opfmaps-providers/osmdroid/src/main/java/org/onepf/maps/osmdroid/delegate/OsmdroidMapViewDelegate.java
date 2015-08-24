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

package org.onepf.maps.osmdroid.delegate;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import org.onepf.maps.osmdroid.model.CameraPosition;
import org.onepf.maps.osmdroid.model.OsmdroidMapOptions;
import org.onepf.maps.osmdroid.overlay.ClickableCompassOverlay;
import org.onepf.maps.osmdroid.overlay.RotationGestureOverlay;
import org.onepf.maps.osmdroid.overlay.compass.CompassRotationOrientationProvider;
import org.onepf.maps.osmdroid.overlay.listener.RotationObserver;
import org.onepf.opfmaps.OPFMap;
import org.onepf.opfmaps.delegate.MapViewDelegate;
import org.onepf.opfmaps.listener.OPFOnMapReadyCallback;
import org.onepf.opfutils.OPFLog;
import org.osmdroid.api.IMapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman Savin
 * @since 04.08.2015
 */
public class OsmdroidMapViewDelegate extends MapView implements MapViewDelegate {

    private static final int MIN_ZOOM_LEVEL = 3;

    @NonNull
    private final List<RotationObserver> rotationObservers = new ArrayList<>();

    @Nullable
    private final OsmdroidMapOptions options;

    private boolean isZoomControlsEnabled;
    private boolean isCompassEnabled;
    private boolean isRotateGesturesEnabled;
    private boolean isZoomGesturesEnabled;
    private boolean isMyLocationEnabled;

    @Nullable
    private ClickableCompassOverlay compassOverlay;
    @Nullable
    private RotationGestureOverlay rotationGestureOverlay;
    @Nullable
    private MyLocationNewOverlay myLocationOverlay;

    public OsmdroidMapViewDelegate(final Context context) {
        this(context, null);
    }

    public OsmdroidMapViewDelegate(final Context context, @Nullable final OsmdroidMapOptions options) {
        super(context, 256);
        this.options = options;
    }

    @Override
    public void getMapAsync(@NonNull final OPFOnMapReadyCallback callback) {
        initOverlays();
        initOptions();
        callback.onMapReady(new OPFMap(new OsmdroidMapDelegate(this)));
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        //todo implement
    }

    @Override
    public void onResume() {
        //todo implement
    }

    @Override
    public void onPause() {
        //todo implement
    }

    @Override
    public void onDestroy() {
        //todo implement
    }

    @Override
    public void onSaveInstanceState(@Nullable final Bundle outState) {
        //todo implement
    }

    @Override
    public void onLowMemory() {
        //todo implement
    }

    @Override
    public void setMapOrientation(final float degrees) {
        super.setMapOrientation(degrees);
        for (RotationObserver rotationObserver : rotationObservers) {
            rotationObserver.onRotate(degrees);
        }
    }

    @Override
    public void setBuiltInZoomControls(final boolean isZoomControllersEnabled) {
        super.setBuiltInZoomControls(isZoomControllersEnabled);
        this.isZoomControlsEnabled = true;
    }

    @Override
    public void setMultiTouchControls(final boolean enabled) {
        super.setMultiTouchControls(enabled);
        this.isZoomGesturesEnabled = enabled;
    }

    public void setCompassEnabled(final boolean isCompassEnabled) {
        if (compassOverlay == null) {
            return;
        }

        this.isCompassEnabled = isCompassEnabled;
        if (isCompassEnabled) {
            compassOverlay.enableCompass();
        } else {
            compassOverlay.disableCompass();
        }
    }

    public void setRotateGesturesEnabled(final boolean isRotateGesturesEnabled) {
        if (rotationGestureOverlay == null) {
            return;
        }

        this.isRotateGesturesEnabled = isRotateGesturesEnabled;
        if (isRotateGesturesEnabled) {
            getOverlays().add(rotationGestureOverlay);
        } else {
            getOverlays().remove(rotationGestureOverlay);
        }
    }

    public void setMyLocationEnabled(final boolean isMyLocationEnabled) {
        if (myLocationOverlay == null) {
            return;
        }

        this.isMyLocationEnabled = isMyLocationEnabled;
        if (isMyLocationEnabled) {
            myLocationOverlay.enableMyLocation();
        } else {
            myLocationOverlay.disableMyLocation();
        }
    }

    public boolean isZoomControlsEnabled() {
        return isZoomControlsEnabled;
    }


    public boolean isCompassEnabled() {
        return isCompassEnabled;
    }

    public boolean isRotateGesturesEnabled() {
        return isRotateGesturesEnabled;
    }

    public boolean isZoomGesturesEnabled() {
        return isZoomGesturesEnabled;
    }

    public boolean isMyLocationEnabled() {
        return isMyLocationEnabled;
    }

    private void initOverlays() {
        final Context context = getContext();

        //Compass
        final CompassRotationOrientationProvider compassProvider = new CompassRotationOrientationProvider(context);
        compassOverlay = new ClickableCompassOverlay(
                context,
                compassProvider,
                this
        );
        getOverlays().add(compassOverlay);
        rotationObservers.add(compassProvider);

        //Rotate gestures
        rotationGestureOverlay = new RotationGestureOverlay(context, this);

        //My Location
        myLocationOverlay = new MyLocationNewOverlay(context, this);
        getOverlays().add(myLocationOverlay);
    }

    private void initOptions() {
        OPFLog.logMethod();
        if (options == null) {
            return;
        }
        setMinZoomLevel(MIN_ZOOM_LEVEL);

        final IMapController controller = getController();
        final CameraPosition cameraPosition = options.getCamera();
        if (cameraPosition != null) {
            //todo implement zoom convertion
            //todo is it tilt in osm
            controller.setCenter(cameraPosition.target);
            controller.setZoom((int) cameraPosition.zoom);
            setMapOrientation(cameraPosition.bearing);
        }

        setCompassEnabled(options.getCompassEnabled() == null ? true : options.getCompassEnabled());
        setRotateGesturesEnabled(options.getRotateGesturesEnabled() == null ? true : options.getRotateGesturesEnabled());
        setMultiTouchControls(options.getZoomGesturesEnabled() == null ? true : options.getZoomGesturesEnabled());
        setBuiltInZoomControls(options.getZoomControlsEnabled() == null ? true : options.getZoomControlsEnabled());

        final Boolean isScrollGesturesEnabled = options.getScrollGesturesEnabled();
        if (isScrollGesturesEnabled == null || isScrollGesturesEnabled) {
            //todo implement for disable
        }

        final Boolean isTiltGesturesEnabled = options.getTiltGesturesEnabled();
        if (isTiltGesturesEnabled == null || isTiltGesturesEnabled) {
            //todo check is it enabled in osm
        }

        invalidate();
    }
}
