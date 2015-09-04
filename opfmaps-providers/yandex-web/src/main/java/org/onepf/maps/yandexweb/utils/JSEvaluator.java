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

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.webkit.ValueCallback;
import android.webkit.WebView;

/**
 * @author Roman Savin
 * @since 04.09.2015
 */
public final class JSEvaluator {

    private JSEvaluator() {
        throw new UnsupportedOperationException();
    }

    public static void evaluateJSFunction(@NonNull final WebView webView,
                                          @Nullable final ValueCallback<String> resultCallback,
                                          @NonNull final String function,
                                          @Nullable final String... params) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.evaluateJavascript(formatScript(function, params), resultCallback);
        } else {
            //todo make in worker thread
            webView.loadUrl("javascript:" + formatScript(function, params));
        }
    }

    @NonNull
    private static String formatScript(@NonNull final String function,
                                       @Nullable final String[] params) {
        final StringBuilder builder = new StringBuilder(function).append('(');
        if (params != null) {
            final int length = params.length;
            for (int i = 0; i < length; ++i) {
                builder.append('\'').append(params[i]).append('\'');
                if (i != length - 1) {
                    builder.append(',');
                }
            }
        }

        builder.append(')');
        return builder.toString();
    }
}
