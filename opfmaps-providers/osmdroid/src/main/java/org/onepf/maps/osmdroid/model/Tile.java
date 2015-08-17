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

package org.onepf.maps.osmdroid.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author Roman Savin
 * @since 17.08.2015
 */
public final class Tile implements Parcelable {

    public static final Creator<Tile> CREATOR = new Creator<Tile>() {
        @Override
        public Tile createFromParcel(final Parcel source) {
            return new Tile(source);
        }

        @Override
        public Tile[] newArray(final int size) {
            return new Tile[size];
        }
    };

    @Nullable
    public final byte[] data;
    public final int height;
    public final int width;

    public Tile(final int width, final int height, @Nullable final byte[] data) {
        this.width = width;
        this.height = height;
        this.data = data;
    }

    private Tile(@NonNull final Parcel parcel) {
        this.data = new byte[parcel.readInt()];
        parcel.readByteArray(this.data);
        this.height = parcel.readInt();
        this.width = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeInt(data.length);
        dest.writeByteArray(data);
        dest.writeInt(height);
        dest.writeInt(width);
    }
}
