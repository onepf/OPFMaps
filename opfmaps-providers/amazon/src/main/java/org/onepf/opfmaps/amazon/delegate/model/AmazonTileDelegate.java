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

package org.onepf.opfmaps.amazon.delegate.model;

import android.os.Parcel;
import android.support.annotation.NonNull;
import org.onepf.opfmaps.delegate.model.TileDelegate;

/**
 * @author Roman Savin
 * @since 03.08.2015
 */
public final class AmazonTileDelegate implements TileDelegate {

    public static final Creator<AmazonTileDelegate> CREATOR = new Creator<AmazonTileDelegate>() {
        @Override
        public AmazonTileDelegate createFromParcel(final Parcel source) {
            return new AmazonTileDelegate();
        }

        @Override
        public AmazonTileDelegate[] newArray(final int size) {
            return new AmazonTileDelegate[size];
        }
    };

    @NonNull
    @Override
    public byte[] getData() {
        return new byte[0];
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getWidth() {
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
