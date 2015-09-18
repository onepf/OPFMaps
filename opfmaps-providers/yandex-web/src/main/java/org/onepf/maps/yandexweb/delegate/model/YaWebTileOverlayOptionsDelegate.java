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

package org.onepf.maps.yandexweb.delegate.model;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.onepf.opfmaps.delegate.model.TileOverlayOptionsDelegate;
import org.onepf.opfmaps.model.OPFTile;
import org.onepf.opfmaps.model.OPFTileProvider;
import org.onepf.opfutils.OPFLog;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class YaWebTileOverlayOptionsDelegate implements TileOverlayOptionsDelegate {

    public static final Creator<YaWebTileOverlayOptionsDelegate> CREATOR = new Creator<YaWebTileOverlayOptionsDelegate>() {
        @Override
        public YaWebTileOverlayOptionsDelegate createFromParcel(final Parcel source) {
            return new YaWebTileOverlayOptionsDelegate();
        }

        @Override
        public YaWebTileOverlayOptionsDelegate[] newArray(final int size) {
            return new YaWebTileOverlayOptionsDelegate[size];
        }
    };

    public YaWebTileOverlayOptionsDelegate() {
        //nothing
    }

    @SuppressWarnings("PMD.BooleanGetMethodName")
    @Override
    public boolean getFadeIn() {
        OPFLog.logStubCall();
        return false;
    }

    @Nullable
    @Override
    public OPFTileProvider getTileProvider() {
        OPFLog.logStubCall();
        return new OPFTileProvider() {
            @Nullable
            @Override
            public OPFTile getTile(final int x, final int y, final int zoom) {
                return null;
            }
        };
    }

    @Override
    public float getZIndex() {
        OPFLog.logStubCall();
        return 0;
    }

    @Override
    public boolean isVisible() {
        OPFLog.logStubCall();
        return false;
    }

    @NonNull
    @Override
    public YaWebTileOverlayOptionsDelegate fadeIn(final boolean fadeIn) {
        OPFLog.logStubCall(fadeIn);
        return this;
    }

    @NonNull
    @Override
    public YaWebTileOverlayOptionsDelegate tileProvider(@NonNull final OPFTileProvider tileProvider) {
        OPFLog.logStubCall(tileProvider);
        return this;
    }

    @NonNull
    @Override
    public YaWebTileOverlayOptionsDelegate visible(final boolean visible) {
        OPFLog.logStubCall(visible);
        return this;
    }

    @NonNull
    @Override
    public YaWebTileOverlayOptionsDelegate zIndex(final float zIndex) {
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
