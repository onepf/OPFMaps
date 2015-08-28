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

package org.onepf.maps.osmdroid;

import android.content.Context;
import android.support.annotation.NonNull;
import org.onepf.opfmaps.BaseOPFMapProvider;
import org.onepf.opfmaps.factory.DelegatesAbstractFactory;
import org.onepf.opfmaps.model.OPFMapType;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Roman Savin
 * @since 12.08.2015
 */
public final class OsmdroidMapProvider extends BaseOPFMapProvider {

    @NonNull
    private final Map<OPFMapType, ITileSource> tileSourceMap;

    public OsmdroidMapProvider() {
        this(createDefaultTileSourceMap());
    }

    public OsmdroidMapProvider(@NonNull final OPFMapType mapType, @NonNull final ITileSource tileSource) {
        final Map<OPFMapType, ITileSource> tileSourceMap = createDefaultTileSourceMap();
        tileSourceMap.put(mapType, tileSource);
        this.tileSourceMap = tileSourceMap;
    }

    public OsmdroidMapProvider(@NonNull final Map<OPFMapType, ITileSource> tileSourceMap) {
        this.tileSourceMap = tileSourceMap;
    }

    @NonNull
    @Override
    public DelegatesAbstractFactory getDelegatesFactory() {
        return new OsmdroidDelegatesFactory();
    }

    @NonNull
    @Override
    public String getHostAppPackage() {
        //todo return package
        return "";
    }

    @Override
    public boolean hasRequiredPermissions(final Context context) {
        //todo add checks
        return true;
    }

    @Override
    public boolean isAvailable(final Context context) {
        //todo add checks
        return true;
    }

    @Override
    public boolean isKeyPresented(final Context context) {
        //todo add checks
        return true;
    }

    @Override
    public boolean hasRequestedFeatures(final Context context) {
        //todo add checks
        return true;
    }

    @NonNull
    public Map<OPFMapType, ITileSource> getTileSourceMap() {
        return tileSourceMap;
    }

    private static Map<OPFMapType, ITileSource> createDefaultTileSourceMap() {
        final Map<OPFMapType, ITileSource> tileSourceMap = new HashMap<>();
        tileSourceMap.put(OPFMapType.NONE, TileSourceFactory.MAPNIK);
        tileSourceMap.put(OPFMapType.NORMAL, TileSourceFactory.MAPNIK);
        tileSourceMap.put(OPFMapType.SATELLITE, TileSourceFactory.MAPQUESTAERIAL);
        tileSourceMap.put(OPFMapType.TERRAIN, TileSourceFactory.MAPQUESTOSM);
        tileSourceMap.put(OPFMapType.HYBRID, TileSourceFactory.MAPQUESTOSM);
        return tileSourceMap;
    }
}
