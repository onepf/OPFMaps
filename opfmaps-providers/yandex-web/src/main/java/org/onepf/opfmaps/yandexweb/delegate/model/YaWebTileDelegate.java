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

import android.os.Parcel;
import android.support.annotation.NonNull;

import org.onepf.opfmaps.delegate.model.TileDelegate;
import org.onepf.opfutils.OPFLog;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class YaWebTileDelegate implements TileDelegate {

    public static final Creator<YaWebTileDelegate> CREATOR = new Creator<YaWebTileDelegate>() {
        @Override
        public YaWebTileDelegate createFromParcel(final Parcel source) {
            return new YaWebTileDelegate();
        }

        @Override
        public YaWebTileDelegate[] newArray(final int size) {
            return new YaWebTileDelegate[size];
        }
    };

    public YaWebTileDelegate() {
        //nothing
    }

    @NonNull
    @Override
    public byte[] getData() {
        OPFLog.logStubCall();
        return new byte[0];
    }

    @Override
    public int getHeight() {
        OPFLog.logStubCall();
        return 0;
    }

    @Override
    public int getWidth() {
        OPFLog.logStubCall();
        return 0;
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
