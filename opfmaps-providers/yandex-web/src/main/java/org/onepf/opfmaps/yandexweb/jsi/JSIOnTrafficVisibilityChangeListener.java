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

package org.onepf.opfmaps.yandexweb.jsi;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.webkit.JavascriptInterface;
import org.onepf.opfmaps.yandexweb.listener.OnTrafficVisibilityChangeListener;

/**
 * @author Roman Savin
 * @since 17.09.2015
 */
public final class JSIOnTrafficVisibilityChangeListener {

    public static final String JS_INTERFACE_NAME = "OnTrafficVisibilityChangeListener";

    @NonNull
    private final OnTrafficVisibilityChangeListener listener;

    @NonNull
    private final Handler handler = new Handler(Looper.getMainLooper());

    public JSIOnTrafficVisibilityChangeListener(@NonNull final OnTrafficVisibilityChangeListener listener) {
        this.listener = listener;
    }

    @JavascriptInterface
    public void onTrafficShow() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                listener.onTrafficShow();
            }
        });
    }

    @JavascriptInterface
    public void onTrafficHide() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                listener.onTrafficHide();
            }
        });
    }
}
