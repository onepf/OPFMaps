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

package org.onepf.maps.amazon.delegate.model;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import org.onepf.opfmaps.delegate.model.TileOverlayOptionsDelegate;
import org.onepf.opfmaps.model.OPFTileProvider;
import org.onepf.opfutils.OPFLog;

/**
 * @author Roman Savin
 * @since 03.08.2015
 */
public final class AmazonTileOverlayOptionsDelegate implements TileOverlayOptionsDelegate {

    public static final Creator<AmazonTileOverlayOptionsDelegate> CREATOR = new Creator<AmazonTileOverlayOptionsDelegate>() {
        @Override
        public AmazonTileOverlayOptionsDelegate createFromParcel(final Parcel source) {
            return new AmazonTileOverlayOptionsDelegate();
        }

        @Override
        public AmazonTileOverlayOptionsDelegate[] newArray(final int size) {
            return new AmazonTileOverlayOptionsDelegate[size];
        }
    };

    @SuppressWarnings("PMD.BooleanGetMethodName")
    @Override
    public boolean getFadeIn() {
        return false;
    }

    @Nullable
    @Override
    public OPFTileProvider getTileProvider() {
        return null;
    }

    @Override
    public float getZIndex() {
        return 0.0f;
    }

    @Override
    public boolean isVisible() {
        return false;
    }

    @NonNull
    @Override
    public AmazonTileOverlayOptionsDelegate fadeIn(final boolean fadeIn) {
        OPFLog.logStubCall(fadeIn);
        return this;
    }

    @NonNull
    @Override
    public AmazonTileOverlayOptionsDelegate tileProvider(@NonNull final OPFTileProvider tileProvider) {
        OPFLog.logStubCall(tileProvider);
        return this;
    }

    @NonNull
    @Override
    public AmazonTileOverlayOptionsDelegate visible(final boolean visible) {
        OPFLog.logStubCall(visible);
        return this;
    }

    @NonNull
    @Override
    public AmazonTileOverlayOptionsDelegate zIndex(final float zIndex) {
        OPFLog.logStubCall(zIndex);
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        //nothing
    }
}
