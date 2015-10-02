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

package org.onepf.opfmaps.yandexweb.delegate.model;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import org.onepf.opfmaps.yandexweb.model.BitmapDescriptorFactory;
import org.onepf.opfmaps.delegate.model.BitmapDescriptorFactoryDelegate;
import org.onepf.opfmaps.model.OPFBitmapDescriptor;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class YaWebBitmapDescriptorFactoryDelegate implements BitmapDescriptorFactoryDelegate {

    @NonNull
    @Override
    public OPFBitmapDescriptor defaultMarker() {
        return new OPFBitmapDescriptor(new YaWebBitmapDescriptorDelegate(BitmapDescriptorFactory.defaultMarker()));
    }

    @NonNull
    @Override
    public OPFBitmapDescriptor defaultMarker(final float hue) {
        return new OPFBitmapDescriptor(new YaWebBitmapDescriptorDelegate(BitmapDescriptorFactory.defaultMarker(hue)));
    }

    @NonNull
    @Override
    public OPFBitmapDescriptor fromAsset(@NonNull final String assetName) {
        return new OPFBitmapDescriptor(new YaWebBitmapDescriptorDelegate(BitmapDescriptorFactory.fromAsset(assetName)));
    }

    @NonNull
    @Override
    public OPFBitmapDescriptor fromBitmap(@NonNull final Bitmap image) {
        return new OPFBitmapDescriptor(new YaWebBitmapDescriptorDelegate(BitmapDescriptorFactory.fromBitmap(image)));
    }

    @NonNull
    @Override
    public OPFBitmapDescriptor fromFile(@NonNull final String fileName) {
        return new OPFBitmapDescriptor(new YaWebBitmapDescriptorDelegate(BitmapDescriptorFactory.fromFile(fileName)));
    }

    @NonNull
    @Override
    public OPFBitmapDescriptor fromPath(@NonNull final String absolutePath) {
        return new OPFBitmapDescriptor(new YaWebBitmapDescriptorDelegate(BitmapDescriptorFactory.fromPath(absolutePath)));
    }

    @NonNull
    @Override
    public OPFBitmapDescriptor fromResource(final int resourceId) {
        return new OPFBitmapDescriptor(new YaWebBitmapDescriptorDelegate(BitmapDescriptorFactory.fromResource(resourceId)));
    }
}
