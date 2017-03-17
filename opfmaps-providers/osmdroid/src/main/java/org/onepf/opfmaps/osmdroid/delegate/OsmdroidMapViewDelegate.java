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

package org.onepf.opfmaps.osmdroid.delegate;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.onepf.opfmaps.osmdroid.OsmdroidMapProvider;
import org.onepf.opfmaps.osmdroid.R;
import org.onepf.opfmaps.osmdroid.model.CameraPosition;
import org.onepf.opfmaps.osmdroid.model.OsmdroidMapOptions;
import org.onepf.opfmaps.osmdroid.overlay.ClickListenerOverlay;
import org.onepf.opfmaps.osmdroid.overlay.ClickableCompassOverlay;
import org.onepf.opfmaps.osmdroid.overlay.MyLocationOverlayWithButton;
import org.onepf.opfmaps.osmdroid.overlay.RotationGestureOverlay;
import org.onepf.opfmaps.osmdroid.overlay.ScrollGesturesOverlay;
import org.onepf.opfmaps.osmdroid.overlay.compass.CompassRotationOrientationProvider;
import org.onepf.opfmaps.osmdroid.overlay.listener.RotationObserver;
import org.onepf.opfmaps.osmdroid.utils.ConvertUtils;
import org.onepf.opfmaps.OPFMap;
import org.onepf.opfmaps.OPFMapHelper;
import org.onepf.opfmaps.delegate.MapViewDelegate;
import org.onepf.opfmaps.listener.OPFOnMapClickListener;
import org.onepf.opfmaps.listener.OPFOnMapLongClickListener;
import org.onepf.opfmaps.listener.OPFOnMapReadyCallback;
import org.onepf.opfmaps.listener.OPFOnMyLocationButtonClickListener;
import org.onepf.opfmaps.model.OPFMapType;
import org.onepf.opfutils.OPFLog;
import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.TileSystem;
import org.osmdroid.views.MapView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roman Savin
 * @since 04.08.2015
 */
public class OsmdroidMapViewDelegate extends MapView implements MapViewDelegate {

    private static final String TILE_SOURCE_BUNDLE_KEY = "org.onepf.maps.osmdroid.delegate.TILE_SOURCE_BUNDLE_KEY";
    private static final String CENTER_LAT_BUNDLE_KEY = "org.onepf.maps.osmdroid.delegate.CENTER_LAT_BUNDLE_KEY";
    private static final String CENTER_LNG_BUNDLE_KEY = "org.onepf.maps.osmdroid.delegate.CENTER_LNG_BUNDLE_KEY";
    private static final String ZOOM_LEVEL_BUNDLE_KEY = "org.onepf.maps.osmdroid.delegate.ZOOM_LEVEL_BUNDLE_KEY";
    private static final String MAP_ORIENTATION_BUNDLE_KEY = "org.onepf.maps.osmdroid.delegate.MAP_ORIENTATION_BUNDLE_KEY";
    private static final String IS_ZOOM_CONTROLS_ENABLED_BUNDLE_KEY = "org.onepf.maps.osmdroid.delegate.IS_ZOOM_CONTROLS_ENABLED_BUNDLE_KEY";
    private static final String IS_COMPASS_ENABLED_BUNDLE_KEY = "org.onepf.maps.osmdroid.delegate.IS_COMPASS_ENABLED_BUNDLE_KEY";
    private static final String IS_ROTATE_GESTURES_ENABLED_BUNDLE_KEY = "org.onepf.maps.osmdroid.delegate.IS_ROTATE_GESTURES_ENABLED_BUNDLE_KEY";
    private static final String IS_ZOOM_GESTURES_ENABLED_BUNDLE_KEY = "org.onepf.maps.osmdroid.delegate.IS_ZOOM_GESTURES_ENABLED_BUNDLE_KEY";
    private static final String IS_MY_LOCATION_ENABLED_BUNDLE_KEY = "org.onepf.maps.osmdroid.delegate.IS_MY_LOCATION_ENABLED_BUNDLE_KEY";
    private static final String IS_MY_LOCATION_BUTTON_ENABLED_BUNDLE_KEY =
            "org.onepf.maps.osmdroid.delegate.IS_MY_LOCATION_BUTTON_ENABLED_BUNDLE_KEY";

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

    @NonNull
    private OPFMapType mapType = OPFMapType.NORMAL;
    @Nullable
    private ClickableCompassOverlay compassOverlay;
    @Nullable
    private RotationGestureOverlay rotationGestureOverlay;
    @Nullable
    private ScrollGesturesOverlay scrollGesturesOverlay;
    @Nullable
    private ClickListenerOverlay clickListenerOverlay;
    @Nullable
    private MyLocationOverlayWithButton myLocationOverlay;

    public OsmdroidMapViewDelegate(final Context context) {
        this(context, null);
    }

    public OsmdroidMapViewDelegate(final Context context, @Nullable final OsmdroidMapOptions options) {
//        super(context, context.getResources().getInteger(R.integer.default_tile_size_pixels));
        super(context);
        this.options = options;
    }

    @Override
    public void getMapAsync(@NonNull final OPFOnMapReadyCallback callback) {
        initOverlays();
        callback.onMapReady(new OPFMap(new OsmdroidMapDelegate(this)));
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        initOverlays();

        if (savedInstanceState == null) {
            initOptions();
        } else {
            setMinZoomLevel(MIN_ZOOM_LEVEL);

            final String tileSourceName = savedInstanceState.getString(TILE_SOURCE_BUNDLE_KEY,
                    TileSourceFactory.DEFAULT_TILE_SOURCE.name());
            try {
                final ITileSource tileSource = TileSourceFactory.getTileSource(tileSourceName);
                setTileSource(tileSource);
            } catch (final IllegalArgumentException e) {
                setTileSource(((OsmdroidMapProvider) OPFMapHelper.getInstance().getCurrentProvider())
                        .getTileSourceMap().get(OPFMapType.NORMAL));
            }

            final IMapController controller = getController();
            controller.setCenter(new GeoPoint(
                    savedInstanceState.getDouble(CENTER_LAT_BUNDLE_KEY, 0.0),
                    savedInstanceState.getDouble(CENTER_LNG_BUNDLE_KEY, 0.0)
            ));
            controller.setZoom(savedInstanceState.getInt(ZOOM_LEVEL_BUNDLE_KEY, MIN_ZOOM_LEVEL));

            setBuiltInZoomControls(savedInstanceState.getBoolean(IS_ZOOM_CONTROLS_ENABLED_BUNDLE_KEY, true));
            setCompassEnabled(savedInstanceState.getBoolean(IS_COMPASS_ENABLED_BUNDLE_KEY, true));
            setRotateGesturesEnabled(savedInstanceState.getBoolean(IS_ROTATE_GESTURES_ENABLED_BUNDLE_KEY, true));
            setMultiTouchControls(savedInstanceState.getBoolean(IS_ZOOM_GESTURES_ENABLED_BUNDLE_KEY, true));
            setMyLocationEnabled(savedInstanceState.getBoolean(IS_MY_LOCATION_ENABLED_BUNDLE_KEY, true));
            setMyLocationButtonEnabled(savedInstanceState.getBoolean(IS_MY_LOCATION_BUTTON_ENABLED_BUNDLE_KEY, true));
            setMapOrientation(savedInstanceState.getFloat(MAP_ORIENTATION_BUNDLE_KEY, 0.0f));
        }
    }

    @Override
    public void onResume() {
        //nothing
    }

    @Override
    public void onPause() {
        //nothing
    }

    @Override
    public void onDestroy() {
        //nothing
    }

    @Override
    public void onSaveInstanceState(@Nullable final Bundle outState) {
        if (outState == null) {
            return;
        }
        outState.putString(TILE_SOURCE_BUNDLE_KEY, getTileProvider().getTileSource().name());
        outState.putDouble(CENTER_LAT_BUNDLE_KEY, getMapCenter().getLatitude());
        outState.putDouble(CENTER_LNG_BUNDLE_KEY, getMapCenter().getLongitude());
        outState.putInt(ZOOM_LEVEL_BUNDLE_KEY, getZoomLevel());
        outState.putFloat(MAP_ORIENTATION_BUNDLE_KEY, getMapOrientation());
        outState.putBoolean(IS_ZOOM_CONTROLS_ENABLED_BUNDLE_KEY, isZoomControlsEnabled);
        outState.putBoolean(IS_COMPASS_ENABLED_BUNDLE_KEY, isCompassEnabled);
        outState.putBoolean(IS_ROTATE_GESTURES_ENABLED_BUNDLE_KEY, isRotateGesturesEnabled);
        outState.putBoolean(IS_ZOOM_GESTURES_ENABLED_BUNDLE_KEY, isZoomGesturesEnabled);
        outState.putBoolean(IS_MY_LOCATION_ENABLED_BUNDLE_KEY, isMyLocationEnabled);
        if (myLocationOverlay != null) {
            outState.putBoolean(IS_MY_LOCATION_BUTTON_ENABLED_BUNDLE_KEY,
                    myLocationOverlay.isMyLocationButtonEnabled());
        }

        if (myLocationOverlay != null) {
            myLocationOverlay.disableMyLocation();
        }
        if (compassOverlay != null) {
            compassOverlay.disableCompass();
        }
        setBuiltInZoomControls(false);
        rotationObservers.clear();
    }

    @Override
    public void onLowMemory() {
        //nothing
    }

    @Override
    public void scrollTo(final int x, final int y) {
        final int worldSize = TileSystem.MapSize(this.getZoomLevel(false));
        final int newY;
        if (y < 0) { // when over north pole
            newY = 0; // scroll to north pole
        } else if (y + getHeight() >= worldSize) { // when over south pole
            newY = worldSize - getHeight() - 1; // scroll to south pole
        } else {
            newY = y;
        }

        super.scrollTo(x, newY);
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
        this.isZoomControlsEnabled = isZoomControllersEnabled;
    }

    @Override
    public void setMultiTouchControls(final boolean enabled) {
        super.setMultiTouchControls(enabled);
        this.isZoomGesturesEnabled = enabled;
    }

    public void setMapType(@NonNull final OPFMapType mapType) {
        this.mapType = mapType;
        setTileSource(ConvertUtils.convertMapTypeToTileSource(mapType));
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

    public void setScrollGesturesEnabled(final boolean isScrollGesturesEnabled) {
        if (scrollGesturesOverlay == null) {
            return;
        }

        scrollGesturesOverlay.setScrollGesturesEnabled(isScrollGesturesEnabled);
    }

    public void setOnMapClickListener(@Nullable final OPFOnMapClickListener onMapClickListener) {
        if (clickListenerOverlay == null) {
            return;
        }

        clickListenerOverlay.setOnMapClickListener(onMapClickListener);
    }

    public void setOnMapLongClickListener(@Nullable final OPFOnMapLongClickListener onMapLongClickListener) {
        if (clickListenerOverlay == null) {
            return;
        }

        clickListenerOverlay.setOnMapLongClickListener(onMapLongClickListener);
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

    public void setMyLocationButtonEnabled(final boolean isMyLocationButtonEnabled) {
        if (myLocationOverlay == null) {
            return;
        }

        if (isMyLocationButtonEnabled) {
            myLocationOverlay.enableMyLocationButton();
        } else {
            myLocationOverlay.disableMyLocationButton();
        }
    }

    public void setOnMyLocationButtonClickListener(@NonNull final OPFOnMyLocationButtonClickListener clickListener) {
        if (myLocationOverlay == null) {
            return;
        }

        myLocationOverlay.setOnMyLocationButtonClickListener(clickListener);
    }

    @NonNull
    public OPFMapType getMapType() {
        return mapType;
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

    public boolean isMyLocationButtonEnabled() {
        return myLocationOverlay != null && myLocationOverlay.isMyLocationButtonEnabled();
    }

    public boolean isScrollGesturesEnabled() {
        return scrollGesturesOverlay == null || scrollGesturesOverlay.isScrollGesturesEnabled();
    }

    private void initOverlays() {
        final Context context = getContext();

        //Scroll gestures
        if (scrollGesturesOverlay == null) {
            scrollGesturesOverlay = new ScrollGesturesOverlay(context);
            getOverlays().add(scrollGesturesOverlay);
        }

        //Click listener
        if (clickListenerOverlay == null) {
            clickListenerOverlay = new ClickListenerOverlay(context);
            getOverlays().add(clickListenerOverlay);
        }

        //Compass
        if (compassOverlay == null) {
            final CompassRotationOrientationProvider compassProvider = new CompassRotationOrientationProvider(context);
            compassOverlay = new ClickableCompassOverlay(
                    context,
                    compassProvider,
                    this
            );
            getOverlays().add(compassOverlay);
            rotationObservers.add(compassProvider);
        }

        //Rotate gestures
        if (rotationGestureOverlay == null) {
            rotationGestureOverlay = new RotationGestureOverlay(context, this);
        }

        //My Location
        if (myLocationOverlay == null) {
            myLocationOverlay = new MyLocationOverlayWithButton(context, this);
            getOverlays().add(myLocationOverlay);
        }
    }

    private void initOptions() {
        OPFLog.logMethod();
        if (options == null) {
            return;
        }
        setMinZoomLevel(MIN_ZOOM_LEVEL);

        setMapType(options.getMapType());

        setCompassEnabled(getBoolean(options.getCompassEnabled(), true));
        setRotateGesturesEnabled(getBoolean(options.getRotateGesturesEnabled(), true));
        setMultiTouchControls(getBoolean(options.getZoomGesturesEnabled(), true));
        setBuiltInZoomControls(getBoolean(options.getZoomControlsEnabled(), false));
        setScrollGesturesEnabled(getBoolean(options.getScrollGesturesEnabled(), true));

        final IMapController controller = getController();
        final CameraPosition cameraPosition = options.getCamera();
        if (cameraPosition != null) {
            controller.setCenter(cameraPosition.getTarget());
            controller.setZoom((int) cameraPosition.getZoom());
            setMapOrientation(cameraPosition.getBearing());
        }
    }

    private boolean getBoolean(@Nullable final Boolean value, final boolean defValue) {
        if (value == null) {
            return defValue;
        }
        return value;
    }
}
