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

package org.onepf.maps.google.delegate.model;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.maps.model.Tile;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;

import org.onepf.opfmaps.delegate.model.TileOverlayOptionsDelegate;
import org.onepf.opfmaps.model.OPFTile;
import org.onepf.opfmaps.model.OPFTileProvider;

/**
 * @author Roman Savin
 * @since 03.08.2015
 */
public final class GoogleTileOverlayOptionsDelegate implements TileOverlayOptionsDelegate {

    public static final Creator<GoogleTileOverlayOptionsDelegate> CREATOR = new Creator<GoogleTileOverlayOptionsDelegate>() {
        @Override
        public GoogleTileOverlayOptionsDelegate createFromParcel(final Parcel source) {
            return new GoogleTileOverlayOptionsDelegate(source);
        }

        @Override
        public GoogleTileOverlayOptionsDelegate[] newArray(final int size) {
            return new GoogleTileOverlayOptionsDelegate[size];
        }
    };

    @NonNull
    private final TileOverlayOptions tileOverlayOptions;

    public GoogleTileOverlayOptionsDelegate() {
        this.tileOverlayOptions = new TileOverlayOptions();
    }

    private GoogleTileOverlayOptionsDelegate(@NonNull final Parcel parcel) {
        this.tileOverlayOptions = parcel.readParcelable(TileOverlayOptions.class.getClassLoader());
    }

    @SuppressWarnings("PMD.BooleanGetMethodName")
    @Override
    public boolean getFadeIn() {
        return tileOverlayOptions.getFadeIn();
    }

    @Nullable
    @Override
    public OPFTileProvider getTileProvider() {
        final TileProvider tileProvider = tileOverlayOptions.getTileProvider();
        if (tileProvider == null) {
            return null;
        }
        return new OPFTileProvider() {
            @Nullable
            @Override
            public OPFTile getTile(final int x, final int y, final int zoom) {
                final Tile tile = tileProvider.getTile(x, y, zoom);
                if (tile == null) {
                    return null;
                }

                return new OPFTile(new GoogleTileDelegate(tile));
            }
        };
    }

    @Override
    public float getZIndex() {
        return tileOverlayOptions.getZIndex();
    }

    @Override
    public boolean isVisible() {
        return tileOverlayOptions.isVisible();
    }

    @NonNull
    @Override
    public GoogleTileOverlayOptionsDelegate fadeIn(final boolean fadeIn) {
        tileOverlayOptions.fadeIn(fadeIn);
        return this;
    }

    @NonNull
    @Override
    public GoogleTileOverlayOptionsDelegate tileProvider(@NonNull final OPFTileProvider tileProvider) {
        tileOverlayOptions.tileProvider(new TileProvider() {
            @Override
            public Tile getTile(final int x, final int y, final int zoom) {
                final OPFTile opfTile = tileProvider.getTile(x, y, zoom);
                if (opfTile == null) {
                    return null;
                }
                return new Tile(opfTile.getWidth(), opfTile.getHeight(), opfTile.getData());
            }
        });
        return this;
    }

    @NonNull
    @Override
    public GoogleTileOverlayOptionsDelegate visible(final boolean visible) {
        tileOverlayOptions.visible(visible);
        return this;
    }

    @NonNull
    @Override
    public GoogleTileOverlayOptionsDelegate zIndex(final float zIndex) {
        tileOverlayOptions.zIndex(zIndex);
        return this;
    }

    @Override
    public int describeContents() {
        return tileOverlayOptions.describeContents();
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(tileOverlayOptions, flags);
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof GoogleTileOverlayOptionsDelegate
                && tileOverlayOptions.equals(((GoogleTileOverlayOptionsDelegate) other).tileOverlayOptions));
    }

    @Override
    public int hashCode() {
        return tileOverlayOptions.hashCode();
    }

    @Override
    public String toString() {
        return tileOverlayOptions.toString();
    }
}
