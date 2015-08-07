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
import com.amazon.geo.mapsv2.model.Tile;
import com.amazon.geo.mapsv2.model.TileOverlayOptions;
import com.amazon.geo.mapsv2.model.TileProvider;
import org.onepf.opfmaps.delegate.model.TileOverlayOptionsDelegate;
import org.onepf.opfmaps.model.OPFTile;
import org.onepf.opfmaps.model.OPFTileProvider;

/**
 * @author Roman Savin
 * @since 03.08.2015
 */
public final class AmazonTileOverlayOptionsDelegate implements TileOverlayOptionsDelegate {

    public static final Creator<AmazonTileOverlayOptionsDelegate> CREATOR = new Creator<AmazonTileOverlayOptionsDelegate>() {
        @Override
        public AmazonTileOverlayOptionsDelegate createFromParcel(final Parcel source) {
            return new AmazonTileOverlayOptionsDelegate(source);
        }

        @Override
        public AmazonTileOverlayOptionsDelegate[] newArray(final int size) {
            return new AmazonTileOverlayOptionsDelegate[size];
        }
    };

    @NonNull
    private final TileOverlayOptions tileOverlayOptions;

    public AmazonTileOverlayOptionsDelegate() {
        this.tileOverlayOptions = new TileOverlayOptions();
    }

    private AmazonTileOverlayOptionsDelegate(@NonNull final Parcel parcel) {
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

                return new OPFTile(new AmazonTileDelegate(tile));
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
    public AmazonTileOverlayOptionsDelegate fadeIn(final boolean fadeIn) {
        tileOverlayOptions.fadeIn(fadeIn);
        return this;
    }

    @NonNull
    @Override
    public AmazonTileOverlayOptionsDelegate tileProvider(@NonNull final OPFTileProvider tileProvider) {
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
    public AmazonTileOverlayOptionsDelegate visible(final boolean visible) {
        tileOverlayOptions.visible(visible);
        return this;
    }

    @NonNull
    @Override
    public AmazonTileOverlayOptionsDelegate zIndex(final float zIndex) {
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

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof AmazonTileOverlayOptionsDelegate)) return false;

        return tileOverlayOptions.equals(((AmazonTileOverlayOptionsDelegate) other).tileOverlayOptions);
    }
    //CHECKSTYLE:ON

    @Override
    public int hashCode() {
        return tileOverlayOptions.hashCode();
    }

    @Override
    public String toString() {
        return tileOverlayOptions.toString();
    }
}
