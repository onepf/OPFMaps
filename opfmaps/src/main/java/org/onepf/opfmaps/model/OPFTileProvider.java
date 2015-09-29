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

import android.support.annotation.Nullable;

/**
 * An interface for a class that provides the tile images for a {@link OPFTileOverlay}.
 * Calls to methods in this interface might be made from multiple threads so implementations of this interface must be threadsafe.
 *
 * @author Roman Savin
 * @since 30.07.2015
 */
public interface OPFTileProvider {

    /**
     * Returns the tile to be used for this tile coordinate.
     *
     * @param x    The x coordinate of the tile. This will be in the range [0, 2zoom - 1] inclusive.
     * @param y    The y coordinate of the tile. This will be in the range [0, 2zoom - 1] inclusive.
     * @param zoom The zoom level of the tile.
     * @return The {@link OPFTile} to be used for this tile coordinate.
     */
    @Nullable
    OPFTile getTile(final int x, final int y, final int zoom);
}
