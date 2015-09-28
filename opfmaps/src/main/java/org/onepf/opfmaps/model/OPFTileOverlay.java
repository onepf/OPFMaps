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
 * @author Anastasiia Karimova
 * @since 15.06.2015
 */
public final class OPFTileOverlay implements TileOverlayDelegate {

    @NonNull
    private final TileOverlayDelegate delegate;

    public OPFTileOverlay(@NonNull final TileOverlayDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void clearTileCache() {
        delegate.clearTileCache();
    }

    @SuppressWarnings("PMD.BooleanGetMethodName")
    @Override
    public boolean getFadeIn() {
        return delegate.getFadeIn();
    }

    @Override
    @NonNull
    public String getId() {
        return delegate.getId();
    }

    @Override
    public float getZIndex() {
        return delegate.getZIndex();
    }

    @Override
    public boolean isVisible() {
        return delegate.isVisible();
    }

    @Override
    public void remove() {
        delegate.remove();
    }

    @Override
    public void setFadeIn(final boolean fadeIn) {
        delegate.setFadeIn(fadeIn);
    }

    @Override
    public void setVisible(final boolean visible) {
        delegate.setVisible(visible);
    }

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
