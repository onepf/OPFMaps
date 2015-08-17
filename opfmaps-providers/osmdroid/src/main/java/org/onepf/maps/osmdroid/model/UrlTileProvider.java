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

import java.net.URL;

/**
 * @author Roman Savin
 * @since 17.08.2015
 */
public abstract class UrlTileProvider implements TileProvider {

    private final static Tile NO_TILE = new Tile(0, 0, null);

    private final int width;
    private final int height;

    public UrlTileProvider(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public abstract URL getTileUrl(final int var1, final int var2, final int var3);

    public final Tile getTile(final int x, final int y, final int zoom) {
        return NO_TILE;
    }
}
