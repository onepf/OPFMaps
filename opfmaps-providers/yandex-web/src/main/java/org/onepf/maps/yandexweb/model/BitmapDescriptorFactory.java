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

package org.onepf.maps.yandexweb.model;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class BitmapDescriptorFactory {
    //todo implement
    private BitmapDescriptorFactory() {
        throw new UnsupportedOperationException();
    }

    @NonNull
    public static BitmapDescriptor defaultMarker() {
        return new BitmapDescriptor();
    }

    @NonNull
    public static BitmapDescriptor defaultMarker(final float hue) {
        return new BitmapDescriptor();
    }

    @NonNull
    public static BitmapDescriptor fromAsset(@NonNull final String assetName) {
        return new BitmapDescriptor();
    }

    @NonNull
    public static BitmapDescriptor fromBitmap(@NonNull final Bitmap image) {
        return new BitmapDescriptor();
    }

    @NonNull
    public static BitmapDescriptor fromFile(@NonNull final String fileName) {
        return new BitmapDescriptor();
    }

    @NonNull
    public static BitmapDescriptor fromPath(@NonNull final String absolutePath) {
        return new BitmapDescriptor();
    }

    @NonNull
    public static BitmapDescriptor fromResource(final int resourceId) {
        return new BitmapDescriptor();
    }
}
