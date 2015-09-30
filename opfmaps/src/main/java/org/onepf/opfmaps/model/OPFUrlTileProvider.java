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

package org.onepf.opfmaps.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.onepf.opfmaps.OPFMapHelper;
import org.onepf.opfmaps.delegate.model.UrlTileProviderDelegate;

import java.net.URL;

/**
 * A partial implementation of {@link OPFTileProvider} that only requires a URL that points to an image to be provided.
 * Note that this class requires that all the images have the same dimensions.
 *
 * @author Roman Savin
 * @since 30.07.2015
 */
public class OPFUrlTileProvider implements UrlTileProviderDelegate {

    @NonNull
    private final UrlTileProviderDelegate delegate;

    public OPFUrlTileProvider(final int width, final int height, @NonNull final TileUrlProvider tileUrlProvider) {
        this.delegate = OPFMapHelper.getInstance().getDelegatesFactory()
                .createUrlTileProviderDelegate(width, height, tileUrlProvider);
    }

    /**
     * Returns the tile to be used for this tile coordinate.
     *
     * @param x    The x coordinate of the tile. This will be in the range [0, 2zoom - 1] inclusive.
     * @param y    The y coordinate of the tile. This will be in the range [0, 2zoom - 1] inclusive.
     * @param zoom The zoom level of the tile.
     * @return The {@link OPFTile} to be used for this tile coordinate.
     * If the tile could not be found at this point in time, return null and further requests might be made with an exponential backoff.
     */
    @Override
    @Nullable
    public OPFTile getTile(final int x, final int y, final int zoom) {
        return delegate.getTile(x, y, zoom);
    }

    /**
     * Returns a {@link URL} that points to the image to be used for this tile.
     * If no image is found on the initial request, further requests will be made with an exponential backoff.
     * If you do not wish to provide an image for this tile coordinate, return null.
     *
     * @param x    The x coordinate of the tile. This will be in the range [0, 2zoom - 1] inclusive.
     * @param y    The y coordinate of the tile. This will be in the range [0, 2zoom - 1] inclusive.
     * @param zoom The zoom level of the tile.
     * @return The {@link URL} that points to the image to be used for this tile.
     */
    @Nullable
    public URL getTileUrl(final int x, final int y, final int zoom) {
        return delegate.getTileUrl(x, y, zoom);
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OPFUrlTileProvider
                && delegate.equals(((OPFUrlTileProvider) other).delegate));
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public String toString() {
        return delegate.toString();
    }

    /**
     * An interface for a class that provides the by tiles coordinates.
     */
    public interface TileUrlProvider {

        /**
         * Returns a {@link URL} that points to the image to be used for this tile.
         * If no image is found on the initial request, further requests will be made with an exponential backoff.
         * If you do not wish to provide an image for this tile coordinate, return null.
         *
         * @param x    The x coordinate of the tile. This will be in the range [0, 2zoom - 1] inclusive.
         * @param y    The y coordinate of the tile. This will be in the range [0, 2zoom - 1] inclusive.
         * @param zoom The zoom level of the tile.
         * @return The {@link URL} that points to the image to be used for this tile.
         * If you do not wish to provide an image for this tile coordinate, return null.
         */
        @Nullable
        URL getTileUrl(final int x, final int y, final int zoom);
    }
}
