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

package org.onepf.opfmaps.model;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import org.onepf.opfmaps.OPFMapHelper;

/**
 * @author Roman Savin
 * @since 03.08.2015
 */
public final class OPFBitmapDescriptorFactory {

    public static final float HUE_RED = 0.0F;
    public static final float HUE_ORANGE = 30.0F;
    public static final float HUE_YELLOW = 60.0F;
    public static final float HUE_GREEN = 120.0F;
    public static final float HUE_CYAN = 180.0F;
    public static final float HUE_AZURE = 210.0F;
    public static final float HUE_BLUE = 240.0F;
    public static final float HUE_VIOLET = 270.0F;
    public static final float HUE_MAGENTA = 300.0F;
    public static final float HUE_ROSE = 330.0F;

    private OPFBitmapDescriptorFactory() {
        throw new UnsupportedOperationException();
    }

    @NonNull
    public static OPFBitmapDescriptor defaultMarker() {
        return OPFMapHelper.getInstance().getDelegatesFactory().createBitmapDescriptorFactory().defaultMarker();
    }

    @NonNull
    public static OPFBitmapDescriptor defaultMarker(final float hue) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createBitmapDescriptorFactory().defaultMarker(hue);
    }

    @NonNull
    public static OPFBitmapDescriptor fromAsset(@NonNull final String assetName) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createBitmapDescriptorFactory().fromAsset(assetName);
    }

    @NonNull
    public static OPFBitmapDescriptor fromBitmap(@NonNull final Bitmap image) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createBitmapDescriptorFactory().fromBitmap(image);
    }

    @NonNull
    public static OPFBitmapDescriptor fromFile(@NonNull final String fileName) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createBitmapDescriptorFactory().fromFile(fileName);
    }

    @NonNull
    public static OPFBitmapDescriptor fromPath(@NonNull final String absolutePath) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createBitmapDescriptorFactory().fromPath(absolutePath);
    }

    @NonNull
    public static OPFBitmapDescriptor fromResource(final int resourceId) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createBitmapDescriptorFactory().fromResource(resourceId);
    }
}
