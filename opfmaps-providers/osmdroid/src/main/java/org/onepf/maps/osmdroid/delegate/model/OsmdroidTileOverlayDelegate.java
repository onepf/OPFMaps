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

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import org.onepf.opfmaps.delegate.model.TileOverlayDelegate;
import org.osmdroid.tileprovider.MapTileProviderBase;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.TilesOverlay;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
public final class OsmdroidTileOverlayDelegate implements TileOverlayDelegate {

    @Nullable
    private MapView mapView;

    @NonNull
    private final TilesOverlay tileOverlay;

    @NonNull
    private final MapTileProviderBase tileProvider;

    @NonNull
    private final String id;

    public OsmdroidTileOverlayDelegate(@NonNull final MapView mapView, @NonNull final MapTileProviderBase tileProvider) {
        this.mapView = mapView;
        this.tileProvider = tileProvider;
        this.tileOverlay = new TilesOverlay(tileProvider, mapView.getContext());
        this.id = Integer.toString(this.tileOverlay.hashCode());
    }

    @NonNull
    public TilesOverlay getTileOverlay() {
        return tileOverlay;
    }

    @Override
    public void clearTileCache() {
        tileProvider.clearTileCache();
    }

    @SuppressWarnings("PMD.BooleanGetMethodName")
    @Override
    public boolean getFadeIn() {
        //todo is it true by default
        return true;
    }

    @NonNull
    @Override
    public String getId() {
        return id;
    }

    @Override
    public float getZIndex() {
        return 0;
    }

    @Override
    public boolean isVisible() {
        return tileOverlay.isEnabled();
    }

    @Override
    public void remove() {
        if (mapView != null) {
            mapView.getOverlays().remove(tileOverlay);
            mapView.invalidate();
            mapView = null;
        }
    }

    @Override
    public void setFadeIn(final boolean fadeIn) {
        //todo OPFLog.logMethodStub(fadeIn);
    }

    @Override
    public void setVisible(final boolean visible) {
        tileOverlay.setEnabled(visible);
    }

    @Override
    public void setZIndex(final float zIndex) {
        //todo OPFLog.logMethodStub(zIndex)
    }

    @Override
    public int hashCode() {
        return tileOverlay.hashCode();
    }

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OsmdroidTileOverlayDelegate)) return false;

        return tileOverlay.equals(((OsmdroidTileOverlayDelegate) other).tileOverlay);
    }
    //CHECKSTYLE:ON

    @Override
    public String toString() {
        return tileOverlay.toString();
    }
}
