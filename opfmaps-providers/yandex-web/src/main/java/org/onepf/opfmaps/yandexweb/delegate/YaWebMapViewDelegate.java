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

package org.onepf.opfmaps.yandexweb.delegate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import org.onepf.opfmaps.yandexweb.jsi.JSIOnCameraChangeListener;
import org.onepf.opfmaps.yandexweb.jsi.JSIOnInfoWindowChangeListener;
import org.onepf.opfmaps.yandexweb.jsi.JSIOnMapClickListener;
import org.onepf.opfmaps.yandexweb.jsi.JSIOnMapReadyCallback;
import org.onepf.opfmaps.yandexweb.jsi.JSIOnMapTypeChangeListener;
import org.onepf.opfmaps.yandexweb.jsi.JSIOnMarkerClickListener;
import org.onepf.opfmaps.yandexweb.jsi.JSIOnMarkerDragListener;
import org.onepf.opfmaps.yandexweb.jsi.JSIOnMyLocationButtonClickListener;
import org.onepf.opfmaps.yandexweb.jsi.JSIOnTrafficVisibilityChangeListener;
import org.onepf.opfmaps.yandexweb.jsi.JSMapStateInjector;
import org.onepf.opfmaps.yandexweb.jsi.JSYandexMapProxy;
import org.onepf.opfmaps.yandexweb.listener.OnCameraChangeListener;
import org.onepf.opfmaps.yandexweb.listener.OnInfoWindowChangeListener;
import org.onepf.opfmaps.yandexweb.listener.OnMapClickListener;
import org.onepf.opfmaps.yandexweb.listener.OnMapReadyCallback;
import org.onepf.opfmaps.yandexweb.listener.OnMapTypeChangeListener;
import org.onepf.opfmaps.yandexweb.listener.OnMarkerClickListener;
import org.onepf.opfmaps.yandexweb.listener.OnMarkerDragListener;
import org.onepf.opfmaps.yandexweb.listener.OnMyLocationButtonClickListener;
import org.onepf.opfmaps.yandexweb.listener.OnTrafficVisibilityChangeListener;
import org.onepf.opfmaps.yandexweb.model.CameraPosition;
import org.onepf.opfmaps.yandexweb.model.LatLng;
import org.onepf.opfmaps.yandexweb.model.YaWebMapOptions;
import org.onepf.opfmaps.OPFMap;
import org.onepf.opfmaps.delegate.MapViewDelegate;
import org.onepf.opfmaps.listener.OPFOnMapReadyCallback;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFMapType;
import org.onepf.opfmaps.model.OPFProjection;
import org.onepf.opfutils.OPFLog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public class YaWebMapViewDelegate extends WebView
        implements MapViewDelegate, OnMapReadyCallback, OnCameraChangeListener,
        OnMapTypeChangeListener, OnMarkerClickListener, OnMarkerDragListener, OnInfoWindowChangeListener,
        OnMapClickListener, OnMyLocationButtonClickListener, OnTrafficVisibilityChangeListener {

    private static final String MAP_HTML_FILE_NAME = "yandex-map.html";

    private static final String MAP_STATE_BUNDLE_KEY = "org.onepf.maps.yandexweb.delegate.MAP_STATE_BUNDLE_KEY";

    @NonNull
    private MapState mapState;

    @Nullable
    private YaWebMapDelegate yaWebMapDelegate;
    @Nullable
    private GestureDetector gestureDetector;

    @Nullable
    private final YaWebMapOptions options;
    @Nullable
    private OPFOnMapReadyCallback onMapReadyCallback;

    @Nullable
    private Bundle savedInstanceState;
    private boolean needLoad;
    private boolean isCreated;

    private boolean isTrafficEnabled;

    public YaWebMapViewDelegate(final Context context) {
        this(context, null);
    }

    public YaWebMapViewDelegate(@NonNull final Context context,
                                @Nullable final YaWebMapOptions options) {
        super(context);
        this.options = options;
        this.mapState = new MapState();
    }

    @Override
    public void getMapAsync(@NonNull final OPFOnMapReadyCallback callback) {
        this.onMapReadyCallback = callback;
        if (isCreated) {
            loadMap();
            needLoad = false;
        } else {
            needLoad = true;
        }
    }

    @SuppressLint("AddJavascriptInterface")
    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        addJavascriptInterface(new JSIOnCameraChangeListener(this), JSIOnCameraChangeListener.JS_INTERFACE_NAME);
        addJavascriptInterface(new JSIOnMapTypeChangeListener(this), JSIOnMapTypeChangeListener.JS_INTERFACE_NAME);
        addJavascriptInterface(new JSIOnMarkerClickListener(this), JSIOnMarkerClickListener.JS_INTERFACE_NAME);
        addJavascriptInterface(new JSIOnMarkerDragListener(this), JSIOnMarkerDragListener.JS_INTERFACE_NAME);
        addJavascriptInterface(new JSIOnInfoWindowChangeListener(this), JSIOnInfoWindowChangeListener.JS_INTERFACE_NAME);
        addJavascriptInterface(new JSIOnMapClickListener(this), JSIOnMapClickListener.JS_INTERFACE_NAME);
        addJavascriptInterface(new JSIOnMyLocationButtonClickListener(this), JSIOnMyLocationButtonClickListener.JS_INTERFACE_NAME);
        addJavascriptInterface(new JSIOnTrafficVisibilityChangeListener(this), JSIOnTrafficVisibilityChangeListener.JS_INTERFACE_NAME);

        gestureDetector = new GestureDetector(getContext(), new OnLongPressGestureListener());

        isCreated = true;
        if (needLoad) {
            loadMap();
            needLoad = false;
        }
    }

    @Override
    public void onDestroy() {
        isCreated = false;
        onMapReadyCallback = null;
        savedInstanceState = null;
        yaWebMapDelegate = null;
        gestureDetector = null;

        removeJavascriptInterface(JSIOnCameraChangeListener.JS_INTERFACE_NAME);
        removeJavascriptInterface(JSIOnMapTypeChangeListener.JS_INTERFACE_NAME);
        removeJavascriptInterface(JSIOnMarkerClickListener.JS_INTERFACE_NAME);
        removeJavascriptInterface(JSIOnMarkerDragListener.JS_INTERFACE_NAME);
        removeJavascriptInterface(JSIOnInfoWindowChangeListener.JS_INTERFACE_NAME);
        removeJavascriptInterface(JSIOnMapClickListener.JS_INTERFACE_NAME);
        removeJavascriptInterface(JSIOnMyLocationButtonClickListener.JS_INTERFACE_NAME);
        removeJavascriptInterface(JSIOnTrafficVisibilityChangeListener.JS_INTERFACE_NAME);
    }

    @Override
    public void onSaveInstanceState(@Nullable final Bundle outState) {
        if (outState == null) {
            return;
        }

        outState.putParcelable(MAP_STATE_BUNDLE_KEY, mapState);
    }

    @Override
    public void onLowMemory() {
        //nothing
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onMapReady(final double offsetX, final double offsetY) {
        if (onMapReadyCallback != null) {
            yaWebMapDelegate = new YaWebMapDelegate(this);
            yaWebMapDelegate.initProjection(new Rect(0, 0, getWidth(), getHeight()), getZoomLevel(), offsetX, offsetY);
            onMapReadyCallback.onMapReady(new OPFMap(yaWebMapDelegate));

            removeJavascriptInterface(JSIOnMapReadyCallback.JS_INTERFACE_NAME);
            onMapReadyCallback = null;

            //TODO: remove after fixing Projection on big zoom levels.
            setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(final View v, final MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        final OPFProjection projection = yaWebMapDelegate.getProjection();
                        final int x = (int) event.getX();
                        final int y = (int) event.getY();
                        final OPFLatLng latLng = projection.fromScreenLocation(new Point(x, y));
                        OPFLog.d("x = %s, y = %s, lat = %s, lng = %s", x, y, latLng.getLat(), latLng.getLng());
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public void onCameraChange(@NonNull final CameraPosition cameraPosition, final double offsetX, final double offsetY) {
        OPFLog.logMethod(cameraPosition);
        mapState.setCenter(cameraPosition.getTarget());
        mapState.setZoomLevel(cameraPosition.getZoom());

        if (yaWebMapDelegate != null) {
            yaWebMapDelegate.onCameraChange(cameraPosition);
            yaWebMapDelegate.updateProjectionZoomLevel(getZoomLevel(), offsetX, offsetY);
        }
    }

    @Override
    public void onTypeChange(@NonNull final OPFMapType type) {
        OPFLog.logMethod(type);
        mapState.setMapType(type);
    }

    @Override
    public void onMarkerClick(@NonNull final String markerId) {
        boolean isHandled = false;
        if (yaWebMapDelegate != null) {
            isHandled = yaWebMapDelegate.onMarkerClick(markerId);
        }
        if (!isHandled) {
            JSYandexMapProxy.toggleInfoWindow(this, markerId);
        }
    }

    @Override
    public void onMarkerDragStart(@NonNull final String markerId, final double lat, final double lng) {
        if (yaWebMapDelegate != null) {
            yaWebMapDelegate.onMarkerDragStart(markerId, lat, lng);
        }
    }

    @Override
    public void onMarkerDrag(@NonNull final String markerId, final double lat, final double lng) {
        if (yaWebMapDelegate != null) {
            yaWebMapDelegate.onMarkerDrag(markerId, lat, lng);
        }
    }

    @Override
    public void onMarkerDragEnd(@NonNull final String markerId, final double lat, final double lng) {
        if (yaWebMapDelegate != null) {
            yaWebMapDelegate.onMarkerDragEnd(markerId, lat, lng);
        }
    }

    @Override
    public void onInfoWindowOpen(final String markerId) {
        if (yaWebMapDelegate != null) {
            yaWebMapDelegate.onInfoWindowOpen(markerId);
        }
    }

    @Override
    public void onInfoWindowClose(final String markerId) {
        if (yaWebMapDelegate != null) {
            yaWebMapDelegate.onInfoWindowClose(markerId);
        }
    }

    @Override
    public void onMapClick(final LatLng latLng) {
        if (yaWebMapDelegate != null) {
            yaWebMapDelegate.onMapClick(latLng);
        }
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        if (gestureDetector != null) {
            gestureDetector.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onMyLocationButtonClick() {
        if (yaWebMapDelegate != null) {
            yaWebMapDelegate.onMyLocationButtonClick();
        }
    }

    @Override
    public void onTrafficShow() {
        this.isTrafficEnabled = true;
    }

    @Override
    public void onTrafficHide() {
        this.isTrafficEnabled = false;
    }

    @NonNull
    public OPFMapType getMapType() {
        return mapState.getMapType();
    }

    @NonNull
    public LatLng getCenter() {
        return mapState.getCenter();
    }

    public float getZoomLevel() {
        return mapState.getZoomLevel();
    }

    public boolean isMyLocationEnabled() {
        return mapState.isMyLocationEnabled();
    }

    public boolean isMyLocationButtonEnabled() {
        return mapState.isMyLocationButtonEnabled();
    }

    public boolean isScrollGesturesEnabled() {
        return mapState.isScrollGesturesEnabled();
    }

    public boolean isZoomControlsEnabled() {
        return mapState.isZoomControlsEnabled();
    }

    public boolean isZoomGesturesEnabled() {
        return mapState.isZoomGesturesEnabled();
    }

    public boolean isTrafficEnabled() {
        return isTrafficEnabled;
    }

    public void setMapType(@NonNull final OPFMapType mapType) {
        mapState.setMapType(mapType);
        JSYandexMapProxy.setMapType(this, mapType);
    }

    public void setCenter(@NonNull final LatLng center) {
        mapState.setCenter(center);
        JSYandexMapProxy.setMapCenter(this, center);
    }

    public void setZoomLevel(final float zoomLevel) {
        mapState.setZoomLevel(zoomLevel);
        JSYandexMapProxy.setZoomLevel(this, zoomLevel);
    }

    public void setMyLocationEnabled(final boolean isMyLocationEnabled) {
        mapState.setIsMyLocationEnabled(isMyLocationEnabled);
        JSYandexMapProxy.setMyLocationEnabled(this, isMyLocationEnabled);
    }

    public void setMyLocationButtonEnabled(final boolean isMyLocationButtonEnabled) {
        mapState.setIsMyLocationButtonEnabled(isMyLocationButtonEnabled);
        JSYandexMapProxy.setMyLocationButtonEnabled(this, isMyLocationButtonEnabled);
    }

    public void setScrollGesturesEnabled(final boolean isScrollGesturesEnabled) {
        mapState.setIsScrollGesturesEnabled(isScrollGesturesEnabled);
        JSYandexMapProxy.setScrollGesturesEnabled(this, isScrollGesturesEnabled);
    }

    public void setZoomControlsEnabled(final boolean isZoomControlsEnabled) {
        mapState.setIsZoomControlsEnabled(isZoomControlsEnabled);
        JSYandexMapProxy.setZoomControlsEnabled(this, isZoomControlsEnabled);
    }

    public void setZoomGesturesEnabled(final boolean isZoomGesturesEnabled) {
        mapState.setIsZoomGesturesEnabled(isZoomGesturesEnabled);
        JSYandexMapProxy.setZoomGesturesEnabled(this, isZoomGesturesEnabled);
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    private void loadMap() {
        initMapState();

        addJavascriptInterface(new JSIOnMapReadyCallback(this), JSIOnMapReadyCallback.JS_INTERFACE_NAME);
        setWebViewClient(new YaWebViewClient());

        final WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(false);
        settings.setDefaultTextEncodingName("UTF-8");

        final String dataString = getDataString();
        if (dataString != null) {
            loadData(JSMapStateInjector.injectMapState(dataString, mapState), "text/html", "UTF-8");
        }
    }

    @SuppressWarnings("PMD.NPathComplexity")
    private void initMapState() {
        if (savedInstanceState != null) {
            final MapState savedMapState = savedInstanceState.getParcelable(MAP_STATE_BUNDLE_KEY);
            if (savedMapState != null) {
                mapState = savedMapState;
            }
        } else if (options != null) {
            mapState.setMapType(options.getMapType());

            final CameraPosition cameraPosition = options.getCamera();
            if (cameraPosition != null) {
                mapState.setCenter(cameraPosition.getTarget());

                float zoom = cameraPosition.getZoom();
                zoom = zoom < YaWebMapDelegate.MIN_ZOOM_LEVEL ? YaWebMapDelegate.MIN_ZOOM_LEVEL
                        : zoom > YaWebMapDelegate.MAX_ZOOM_LEVEL ? YaWebMapDelegate.MAX_ZOOM_LEVEL : zoom;
                mapState.setZoomLevel(zoom);
            }

            mapState.setIsZoomControlsEnabled(options.getZoomControlsEnabled() == null ? false : options.getZoomControlsEnabled());
            mapState.setIsZoomGesturesEnabled(options.getZoomGesturesEnabled() == null ? true : options.getZoomGesturesEnabled());
            mapState.setIsScrollGesturesEnabled(options.getScrollGesturesEnabled() == null ? true : options.getScrollGesturesEnabled());
        }
    }

    @Nullable
    private String getDataString() {
        try {
            final InputStream inputStream = getResources().getAssets().open(MAP_HTML_FILE_NAME);
            final String dataString = convertStreamToString(inputStream);
            inputStream.close();
            return dataString;
        } catch (IOException e) {
            OPFLog.w(e.getMessage());
            return null;
        }
    }

    private String convertStreamToString(@NonNull final InputStream is) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        final StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append('\n');
        }
        reader.close();
        return sb.toString();
    }

    public static final class MapState implements Parcelable {

        public static final Creator<MapState> CREATOR = new Creator<MapState>() {
            @Override
            public MapState createFromParcel(final Parcel source) {
                return new MapState(source);
            }

            @Override
            public MapState[] newArray(final int size) {
                return new MapState[size];
            }
        };

        private static final float MIN_ZOOM_LEVEL = 3.0f;

        @NonNull
        private OPFMapType mapType;
        @NonNull
        private LatLng center;
        private float zoomLevel;
        private boolean isZoomControlsEnabled;
        private boolean isZoomGesturesEnabled;
        private boolean isScrollGesturesEnabled;
        private boolean isMyLocationEnabled;
        private boolean isMyLocationButtonEnabled;

        public MapState() {
            this.mapType = OPFMapType.NORMAL;
            this.center = new LatLng(0.0, 0.0);
            this.zoomLevel = MIN_ZOOM_LEVEL;
            this.isZoomControlsEnabled = false;
            this.isZoomGesturesEnabled = true;
            this.isScrollGesturesEnabled = true;
            this.isMyLocationEnabled = false;
            this.isMyLocationButtonEnabled = true;
        }

        private MapState(@NonNull final Parcel parcel) {
            this.mapType = OPFMapType.fromId(parcel.readInt());
            this.center = parcel.readParcelable(LatLng.class.getClassLoader());
            this.isZoomControlsEnabled = parcel.readByte() != 0;
            this.isZoomGesturesEnabled = parcel.readByte() != 0;
            this.isScrollGesturesEnabled = parcel.readByte() != 0;
            this.isMyLocationEnabled = parcel.readByte() != 0;
            this.isMyLocationButtonEnabled = parcel.readByte() != 0;
        }

        @NonNull
        public OPFMapType getMapType() {
            return mapType;
        }

        @NonNull
        public LatLng getCenter() {
            return center;
        }

        public float getZoomLevel() {
            return zoomLevel;
        }

        public boolean isZoomControlsEnabled() {
            return isZoomControlsEnabled;
        }

        public boolean isZoomGesturesEnabled() {
            return isZoomGesturesEnabled;
        }

        public boolean isScrollGesturesEnabled() {
            return isScrollGesturesEnabled;
        }

        public boolean isMyLocationEnabled() {
            return isMyLocationEnabled;
        }

        public boolean isMyLocationButtonEnabled() {
            return isMyLocationButtonEnabled;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @SuppressWarnings("PMD.NPathComplexity")
        @Override
        public void writeToParcel(final Parcel dest, final int flags) {
            dest.writeInt(mapType.getId());
            dest.writeParcelable(center, flags);
            dest.writeFloat(zoomLevel);
            dest.writeByte((byte) (isZoomControlsEnabled ? 1 : 0));
            dest.writeByte((byte) (isZoomGesturesEnabled ? 1 : 0));
            dest.writeByte((byte) (isScrollGesturesEnabled ? 1 : 0));
            dest.writeByte((byte) (isMyLocationEnabled ? 1 : 0));
            dest.writeByte((byte) (isMyLocationButtonEnabled ? 1 : 0));
        }

        private void setMapType(@NonNull final OPFMapType mapType) {
            this.mapType = mapType;
        }

        private void setCenter(@NonNull final LatLng center) {
            this.center = center;
        }

        private void setZoomLevel(final float zoomLevel) {
            this.zoomLevel = zoomLevel;
        }

        private void setIsZoomControlsEnabled(final boolean isZoomControlsEnabled) {
            this.isZoomControlsEnabled = isZoomControlsEnabled;
        }

        private void setIsZoomGesturesEnabled(final boolean isZoomGesturesEnabled) {
            this.isZoomGesturesEnabled = isZoomGesturesEnabled;
        }

        private void setIsScrollGesturesEnabled(final boolean isScrollGesturesEnabled) {
            this.isScrollGesturesEnabled = isScrollGesturesEnabled;
        }

        private void setIsMyLocationEnabled(final boolean isMyLocationEnabled) {
            this.isMyLocationEnabled = isMyLocationEnabled;
        }

        private void setIsMyLocationButtonEnabled(final boolean isMyLocationButtonEnabled) {
            this.isMyLocationButtonEnabled = isMyLocationButtonEnabled;
        }
    }

    private final class OnLongPressGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(final MotionEvent e) {
            return true;
        }

        @Override
        public void onLongPress(final MotionEvent e) {
            if (yaWebMapDelegate != null) {
                yaWebMapDelegate.onLongPress(new Point((int) e.getX(), (int) e.getY()));
            }
        }
    }

    private final class YaWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
            try {
                final Uri uri = Uri.parse(url);
                final Intent intent;
                if (url.startsWith("intent")) {
                    intent = Intent.parseUri(url, 0);
                } else {
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                }
                getContext().startActivity(intent);
            } catch (URISyntaxException e) {
                OPFLog.w(e.getMessage());
            }
            return true;
        }
    }
}
