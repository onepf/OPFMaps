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

package org.onepf.maps.yandexweb.delegate;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import org.onepf.maps.yandexweb.jsi.JSIOnMapReadyCallback;
import org.onepf.maps.yandexweb.listener.OnMapReadyCallback;
import org.onepf.maps.yandexweb.model.YaWebMapOptions;
import org.onepf.maps.yandexweb.utils.ConvertUtils;
import org.onepf.maps.yandexweb.utils.JSEvaluator;
import org.onepf.maps.yandexweb.utils.JSOptionsInjector;
import org.onepf.opfmaps.OPFMap;
import org.onepf.opfmaps.delegate.MapViewDelegate;
import org.onepf.opfmaps.listener.OPFOnMapReadyCallback;
import org.onepf.opfmaps.model.OPFMapType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public class YaWebMapViewDelegate extends WebView implements MapViewDelegate, OnMapReadyCallback {

    private static final String MAP_HTML_FILE_NAME = "yandex-map.html";

    @NonNull
    private OPFMapType mapType = OPFMapType.NORMAL;

    @Nullable
    private final YaWebMapOptions options;
    @Nullable
    private OPFOnMapReadyCallback onMapReadyCallback;
    @Nullable
    private Bundle savedInstanceState;

    private boolean isMapReady;

    public YaWebMapViewDelegate(final Context context) {
        this(context, null);
    }

    public YaWebMapViewDelegate(@NonNull final Context context,
                                @Nullable final YaWebMapOptions options) {
        super(context);
        this.options = options;
    }

    @Override
    public void getMapAsync(@NonNull final OPFOnMapReadyCallback callback) {
        this.isMapReady = false;
        this.onMapReadyCallback = callback;
        addJavascriptInterface(new JSIOnMapReadyCallback(this), JSIOnMapReadyCallback.JS_INTERFACE_NAME);

        setWebViewClient(new WebViewClient());
        final WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(false);
        settings.setDefaultTextEncodingName("UTF-8");
        loadData(JSOptionsInjector.injectOptions(getDataString(), options), "text/html", "UTF-8");
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        if (isMapReady && savedInstanceState != null) {
            initMapState();
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(@Nullable final Bundle outState) {

    }

    @Override
    public void onLowMemory() {

    }

    @Override
    public void onMapReady() {
        if (onMapReadyCallback != null) {
            isMapReady = true;
            initMapState();
            onMapReadyCallback.onMapReady(new OPFMap(new YaWebMapDelegate(this)));

            removeJavascriptInterface(JSIOnMapReadyCallback.JS_INTERFACE_NAME);
            onMapReadyCallback = null;
        }
    }

    public void setMapType(@NonNull final OPFMapType mapType) {
        this.mapType = mapType;
        JSEvaluator.evaluateJSFunction(this, null, "setType", ConvertUtils.convertMapTypeToJs(mapType));
    }

    @NonNull
    public OPFMapType getMapType() {
        return mapType;
    }

    private void initMapState() {
        if (savedInstanceState != null) {
            //todo init from savedInstanceState
            savedInstanceState = null;
        }
    }

    private String getDataString() {
        try {
            final InputStream inputStream = getResources().getAssets().open(MAP_HTML_FILE_NAME);
            final String dataString = convertStreamToString(inputStream);
            inputStream.close();
            return dataString;
        } catch (IOException e) {
            throw new RuntimeException("unable to load asset " + MAP_HTML_FILE_NAME);
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
}
