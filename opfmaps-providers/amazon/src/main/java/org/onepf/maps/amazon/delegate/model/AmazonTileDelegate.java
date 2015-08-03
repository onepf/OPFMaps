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
import com.amazon.geo.mapsv2.model.Tile;
import org.onepf.opfmaps.delegate.model.TileDelegate;

/**
 * @author Roman Savin
 * @since 03.08.2015
 */
public final class AmazonTileDelegate implements TileDelegate {

    public static final Creator<AmazonTileDelegate> CREATOR = new Creator<AmazonTileDelegate>() {
        @Override
        public AmazonTileDelegate createFromParcel(final Parcel source) {
            return new AmazonTileDelegate(source);
        }

        @Override
        public AmazonTileDelegate[] newArray(final int size) {
            return new AmazonTileDelegate[size];
        }
    };

    @NonNull
    private final Tile tile;

    public AmazonTileDelegate(final int width, final int height, @NonNull final byte[] data) {
        this.tile = new Tile(width, height, data);
    }

    public AmazonTileDelegate(@NonNull final Tile tile) {
        this.tile = tile;
    }

    private AmazonTileDelegate(@NonNull final Parcel parcel) {
        this.tile = parcel.readParcelable(Tile.class.getClassLoader());
    }

    @NonNull
    @Override
    public byte[] getData() {
        return tile.data;
    }

    @Override
    public int getHeight() {
        return tile.height;
    }

    @Override
    public int getWidth() {
        return tile.width;
    }

    @Override
    public int describeContents() {
        return tile.describeContents();
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(tile, flags);
    }

    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof AmazonTileDelegate)) return false;

        return tile.equals(((AmazonTileDelegate) other).tile);
    }

    @Override
    public int hashCode() {
        return tile.hashCode();
    }

    @Override
    public String toString() {
        return tile.toString();
    }
}
