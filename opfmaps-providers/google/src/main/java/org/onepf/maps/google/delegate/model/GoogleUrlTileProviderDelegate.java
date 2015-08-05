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

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.maps.model.UrlTileProvider;
import org.onepf.opfmaps.delegate.model.UrlTileProviderDelegate;
import org.onepf.opfmaps.model.OPFTile;

import java.net.URL;

/**
 * @author Roman Savin
 * @since 03.08.2015
 */
public abstract class GoogleUrlTileProviderDelegate implements UrlTileProviderDelegate {

    @NonNull
    private final UrlTileProvider urlTileProvider;

    public GoogleUrlTileProviderDelegate(final int width, final int height) {
        this.urlTileProvider = new UrlTileProvider(width, height) {

            @Override
            public URL getTileUrl(final int i, final int i1, final int i2) {
                //stub
                return null;
            }
        };
    }

    @NonNull
    @Override
    public abstract URL getTileUrl(final int x, final int y, final int zoom);

    @Nullable
    @Override
    public OPFTile getTile(final int x, final int y, final int zoom) {
        return new OPFTile(new GoogleTileDelegate(urlTileProvider.getTile(x, y, zoom)));
    }

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof GoogleUrlTileProviderDelegate)) return false;

        return urlTileProvider.equals(((GoogleUrlTileProviderDelegate) other).urlTileProvider);
    }
    //CHECKSTYLE:ON

    @Override
    public int hashCode() {
        return urlTileProvider.hashCode();
    }

    @Override
    public String toString() {
        return urlTileProvider.toString();
    }
}
