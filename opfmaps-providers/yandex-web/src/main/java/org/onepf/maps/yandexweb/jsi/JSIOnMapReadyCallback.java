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

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import org.onepf.maps.yandexweb.delegate.YaWebMapDelegate;
import org.onepf.maps.yandexweb.delegate.YaWebMapViewDelegate;
import org.onepf.opfmaps.OPFMap;
import org.onepf.opfmaps.listener.OPFOnMapReadyCallback;

/**
 * @author Roman Savin
 * @since 04.09.2015
 */
public final class JSIOnMapReadyCallback {

    public final static String JS_INTERFACE_NAME = "OnMapReadyCallback";

    @Nullable
    private OPFOnMapReadyCallback callback;

    @Nullable
    private WebView webView;

    @Nullable
    private YaWebMapViewDelegate delegate;

    @SuppressWarnings("NullableProblems")
    public JSIOnMapReadyCallback(@NonNull final OPFOnMapReadyCallback callback,
                                 @NonNull final WebView webView,
                                 @NonNull final YaWebMapViewDelegate delegate) {
        this.callback = callback;
        this.webView = webView;
        this.delegate = delegate;
    }

    @JavascriptInterface
    public void onMapReady() {
        if (callback != null && webView != null && delegate != null) {
            callback.onMapReady(new OPFMap(new YaWebMapDelegate(delegate)));
            webView.removeJavascriptInterface(JS_INTERFACE_NAME);
            callback = null;
            webView = null;
            delegate = null;
        }
    }
}
