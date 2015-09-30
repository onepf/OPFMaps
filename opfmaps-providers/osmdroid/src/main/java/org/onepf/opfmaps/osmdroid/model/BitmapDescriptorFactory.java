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

package org.onepf.opfmaps.osmdroid.model;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import static org.onepf.opfmaps.osmdroid.model.BitmapDescriptor.BitmapSource.ABS_PATH;
import static org.onepf.opfmaps.osmdroid.model.BitmapDescriptor.BitmapSource.ASSET;
import static org.onepf.opfmaps.osmdroid.model.BitmapDescriptor.BitmapSource.BITMAP;
import static org.onepf.opfmaps.osmdroid.model.BitmapDescriptor.BitmapSource.DEFAULT;
import static org.onepf.opfmaps.osmdroid.model.BitmapDescriptor.BitmapSource.DEFAULT_HUE;
import static org.onepf.opfmaps.osmdroid.model.BitmapDescriptor.BitmapSource.FILE_NAME;
import static org.onepf.opfmaps.osmdroid.model.BitmapDescriptor.BitmapSource.RES_ID;

/**
 * @author Roman Savin
 * @since 20.08.2015
 */
public final class BitmapDescriptorFactory {

    private BitmapDescriptorFactory() {
        throw new UnsupportedOperationException();
    }

    @NonNull
    public static BitmapDescriptor defaultMarker() {
        return new BitmapDescriptor(DEFAULT, null, null, 0, 0);
    }

    @NonNull
    public static BitmapDescriptor defaultMarker(final float hue) {
        return new BitmapDescriptor(DEFAULT_HUE, null, null, hue, 0);
    }

    @NonNull
    public static BitmapDescriptor fromAsset(@NonNull final String assetName) {
        return new BitmapDescriptor(ASSET, null, assetName, 0, 0);
    }

    @NonNull
    public static BitmapDescriptor fromBitmap(@NonNull final Bitmap image) {
        return new BitmapDescriptor(BITMAP, image, null, 0, 0);
    }

    @NonNull
    public static BitmapDescriptor fromFile(@NonNull final String fileName) {
        return new BitmapDescriptor(FILE_NAME, null, fileName, 0, 0);
    }

    @NonNull
    public static BitmapDescriptor fromPath(@NonNull final String absolutePath) {
        return new BitmapDescriptor(ABS_PATH, null, absolutePath, 0, 0);
    }

    @NonNull
    public static BitmapDescriptor fromResource(final int resourceId) {
        return new BitmapDescriptor(RES_ID, null, null, 0, resourceId);
    }
}
