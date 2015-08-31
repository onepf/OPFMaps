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

import android.support.annotation.NonNull;

import com.amazon.geo.mapsv2.model.TileOverlay;

import org.onepf.opfmaps.delegate.model.TileOverlayDelegate;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
public final class AmazonTileOverlayDelegate implements TileOverlayDelegate {

    @NonNull
    private final TileOverlay tileOverlay;

    public AmazonTileOverlayDelegate(@NonNull final TileOverlay tileOverlay) {
        this.tileOverlay = tileOverlay;
    }

    @Override
    public void clearTileCache() {
        tileOverlay.clearTileCache();
    }

    @SuppressWarnings("PMD.BooleanGetMethodName")
    @Override
    public boolean getFadeIn() {
        return tileOverlay.getFadeIn();
    }

    @NonNull
    @Override
    public String getId() {
        return tileOverlay.getId();
    }

    @Override
    public float getZIndex() {
        return tileOverlay.getZIndex();
    }

    @Override
    public boolean isVisible() {
        return tileOverlay.isVisible();
    }

    @Override
    public void remove() {
        tileOverlay.remove();
    }

    @Override
    public void setFadeIn(final boolean fadeIn) {
        tileOverlay.setFadeIn(fadeIn);
    }

    @Override
    public void setVisible(final boolean visible) {
        tileOverlay.setVisible(visible);
    }

    @Override
    public void setZIndex(final float zIndex) {
        tileOverlay.setZIndex(zIndex);
    }

    @Override
    public int hashCode() {
        return tileOverlay.hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof AmazonTileOverlayDelegate
                && tileOverlay.equals(((AmazonTileOverlayDelegate) other).tileOverlay));
    }

    @Override
    public String toString() {
        return tileOverlay.toString();
    }
}
