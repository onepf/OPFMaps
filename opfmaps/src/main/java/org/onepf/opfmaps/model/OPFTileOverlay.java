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

import org.onepf.opfmaps.delegate.model.TileOverlayDelegate;

/**
 * A Tile Overlay is a set of images which are displayed on top of the base map tiles.
 * These tiles may be transparent, allowing you to add features to existing maps.
 *
 * @author Roman Savin
 * @since 15.06.2015
 */
public final class OPFTileOverlay implements TileOverlayDelegate {

    @NonNull
    private final TileOverlayDelegate delegate;

    public OPFTileOverlay(@NonNull final TileOverlayDelegate delegate) {
        this.delegate = delegate;
    }

    /**
     * Clears the tile cache so that all tiles will be requested again from the {@link OPFTileProvider}.
     * The current tiles from this tile overlay will also be cleared from the map after calling this.
     */
    @Override
    public void clearTileCache() {
        delegate.clearTileCache();
    }

    /**
     * Gets whether the tiles should fade in.
     *
     * @return {@code true} if the tiles are to fade in, {@code false} otherwise.
     */
    @SuppressWarnings("PMD.BooleanGetMethodName")
    @Override
    public boolean getFadeIn() {
        return delegate.getFadeIn();
    }

    /**
     * Gets this tile overlay's id.
     *
     * @return The tile overlay's id.
     */
    @Override
    @NonNull
    public String getId() {
        return delegate.getId();
    }

    /**
     * Gets the zIndex of this tile overlay.
     *
     * @return The zIndex of the tile overlay.
     */
    @Override
    public float getZIndex() {
        return delegate.getZIndex();
    }

    /**
     * Gets the visibility of this tile overlay. Note that this does not return whether the tile overlay
     * is actually within the screen's viewport, but whether it will be drawn if it is contained in the screen's viewport.
     *
     * @return The tile overlay's visibility.
     */
    @Override
    public boolean isVisible() {
        return delegate.isVisible();
    }

    /**
     * Removes this tile overlay from the map.
     */
    @Override
    public void remove() {
        delegate.remove();
    }

    /**
     * Sets whether the tiles should fade in.
     *
     * @param fadeIn If {@code true}, then the tiles will fade in, {@code false} otherwise.
     */
    @Override
    public void setFadeIn(final boolean fadeIn) {
        delegate.setFadeIn(fadeIn);
    }

    /**
     * Sets the visibility of this tile overlay. When not visible, a tile overlay is not drawn, but it keeps all its other properties.
     *
     * @param visible {@code false} to make this tile overlay invisible, {@code true} otherwise.
     */
    @Override
    public void setVisible(final boolean visible) {
        delegate.setVisible(visible);
    }

    /**
     * Sets the zIndex of this tile overlay.
     *
     * @param zIndex The zIndex of the tile overlay.
     */
    @Override
    public void setZIndex(final float zIndex) {
        delegate.setZIndex(zIndex);
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OPFTileOverlay
                && delegate.equals(((OPFTileOverlay) other).delegate));
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public String toString() {
        return delegate.toString();
    }
}
