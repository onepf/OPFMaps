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
 * Used to create a definition of an image, used for marker icons and ground overlays.
 *
 * @author Roman Savin
 * @since 03.08.2015
 */
public final class OPFBitmapDescriptorFactory {

    /**
     * Constant Value: 0.0
     */
    public static final float HUE_RED = 0.0F;

    /**
     * Constant Value: 30.0
     */
    public static final float HUE_ORANGE = 30.0F;

    /**
     * Constant Value: 60.0
     */
    public static final float HUE_YELLOW = 60.0F;

    /**
     * Constant Value: 120.0
     */
    public static final float HUE_GREEN = 120.0F;

    /**
     * Constant Value: 180.0
     */
    public static final float HUE_CYAN = 180.0F;

    /**
     * Constant Value: 210.0
     */
    public static final float HUE_AZURE = 210.0F;

    /**
     * Constant Value: 240.0
     */
    public static final float HUE_BLUE = 240.0F;

    /**
     * Constant Value: 270.0
     */
    public static final float HUE_VIOLET = 270.0F;

    /**
     * Constant Value: 300.0
     */
    public static final float HUE_MAGENTA = 300.0F;

    /**
     * Constant Value: 330.0
     */
    public static final float HUE_ROSE = 330.0F;

    private OPFBitmapDescriptorFactory() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a bitmap descriptor that refers to the default marker image.
     *
     * @return The {@link OPFBitmapDescriptor} instance.
     */
    @NonNull
    public static OPFBitmapDescriptor defaultMarker() {
        return OPFMapHelper.getInstance().getDelegatesFactory().createBitmapDescriptorFactory().defaultMarker();
    }

    /**
     * Creates a bitmap descriptor that refers to a colorization of the default marker image.
     * For convenience, there is a predefined set of hue values.
     *
     * @param hue The hue of the marker. Value must be greater or equal to 0 and less than 360.
     * @return The {@link OPFBitmapDescriptor} instance.
     */
    @NonNull
    public static OPFBitmapDescriptor defaultMarker(final float hue) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createBitmapDescriptorFactory().defaultMarker(hue);
    }

    /**
     * Creates a bitmap descriptor using the name of an image in the assets directory.
     *
     * @param assetName The name of an image in the assets directory.
     * @return The {@link OPFBitmapDescriptor} instance.
     */
    @NonNull
    public static OPFBitmapDescriptor fromAsset(@NonNull final String assetName) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createBitmapDescriptorFactory().fromAsset(assetName);
    }

    /**
     * Creates a bitmap descriptor from a given image.
     *
     * @param image The image of a bitmap descriptor.
     * @return The {@link OPFBitmapDescriptor} instance.
     */
    @NonNull
    public static OPFBitmapDescriptor fromBitmap(@NonNull final Bitmap image) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createBitmapDescriptorFactory().fromBitmap(image);
    }

    /**
     * Creates a bitmap descriptor using the name of an image file located in the internal storage.
     *
     * @param fileName The name of the image file.
     * @return The {@link OPFBitmapDescriptor} instance.
     */
    @NonNull
    public static OPFBitmapDescriptor fromFile(@NonNull final String fileName) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createBitmapDescriptorFactory().fromFile(fileName);
    }

    /**
     * Creates a bitmap descriptor from an absolute file path.
     *
     * @param absolutePath The absolute path of the image.
     * @return The {@link OPFBitmapDescriptor} instance.
     */
    @NonNull
    public static OPFBitmapDescriptor fromPath(@NonNull final String absolutePath) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createBitmapDescriptorFactory().fromPath(absolutePath);
    }

    /**
     * Creates a bitmap descriptor using the resource id of an image.
     *
     * @param resourceId The resource id of an image.
     * @return The {@link OPFBitmapDescriptor} instance.
     */
    @NonNull
    public static OPFBitmapDescriptor fromResource(final int resourceId) {
        return OPFMapHelper.getInstance().getDelegatesFactory().createBitmapDescriptorFactory().fromResource(resourceId);
    }
}
