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

    @Override
    @Nullable
    public OPFTile getTile(final int x, final int y, final int zoom) {
        return delegate.getTile(x, y, zoom);
    }

    @Nullable
    public URL getTileUrl(final int x, final int y, final int zoom) {
        return delegate.getTileUrl(x, y, zoom);
    }

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OPFUrlTileProvider)) return false;

        return delegate.equals(((OPFUrlTileProvider) other).delegate);
    }
    //CHECKSTYLE:ON

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public String toString() {
        return delegate.toString();
    }

    public interface TileUrlProvider {

        @Nullable
        URL getTileUrl(final int x, final int y, final int zoom);
    }
}
