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

package org.onepf.maps.osmdroid.delegate.model;

import android.os.Parcel;
import android.support.annotation.NonNull;
import com.google.android.gms.maps.model.Tile;
import org.onepf.opfmaps.delegate.model.TileDelegate;

/**
 * @author Roman Savin
 * @since 03.08.2015
 */
public final class OsmdroidTileDelegate implements TileDelegate {

    public static final Creator<OsmdroidTileDelegate> CREATOR = new Creator<OsmdroidTileDelegate>() {
        @Override
        public OsmdroidTileDelegate createFromParcel(final Parcel source) {
            return new OsmdroidTileDelegate(source);
        }

        @Override
        public OsmdroidTileDelegate[] newArray(final int size) {
            return new OsmdroidTileDelegate[size];
        }
    };

    @NonNull
    private final Tile tile;

    public OsmdroidTileDelegate(final int width, final int height, @NonNull final byte[] data) {
        this.tile = new Tile(width, height, data);
    }

    public OsmdroidTileDelegate(@NonNull final Tile tile) {
        this.tile = tile;
    }

    private OsmdroidTileDelegate(@NonNull final Parcel parcel) {
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

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OsmdroidTileDelegate)) return false;

        return tile.equals(((OsmdroidTileDelegate) other).tile);
    }
    //CHECKSTYLE:ON

    @Override
    public int hashCode() {
        return tile.hashCode();
    }

    @Override
    public String toString() {
        return tile.toString();
    }
}
