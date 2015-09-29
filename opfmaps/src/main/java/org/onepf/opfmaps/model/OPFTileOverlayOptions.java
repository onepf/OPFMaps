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

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.onepf.opfmaps.OPFMapHelper;
import org.onepf.opfmaps.delegate.model.TileOverlayOptionsDelegate;

/**
 * Defines options for a TileOverlay.
 *
 * @author Roman Savin
 * @since 30.07.2015
 */
public final class OPFTileOverlayOptions implements TileOverlayOptionsDelegate {

    public static final Creator<OPFTileOverlayOptions> CREATOR = new Creator<OPFTileOverlayOptions>() {
        @Override
        public OPFTileOverlayOptions createFromParcel(final Parcel source) {
            return new OPFTileOverlayOptions(source);
        }

        @Override
        public OPFTileOverlayOptions[] newArray(final int size) {
            return new OPFTileOverlayOptions[size];
        }
    };

    @NonNull
    private final TileOverlayOptionsDelegate delegate;

    /**
     * Creates a new set of tile overlay options.
     */
    public OPFTileOverlayOptions() {
        this.delegate = OPFMapHelper.getInstance().getDelegatesFactory().createTileOverlayOptionDelegate();
    }

    @SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes")
    private OPFTileOverlayOptions(@NonNull final Parcel parcel) {
        try {
            this.delegate = parcel.readParcelable(Class.forName(parcel.readString()).getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Specifies whether the tiles should fade in. The default is {@code true}.
     *
     * @param fadeIn The fadeIn value.
     * @return This {@link OPFTileOverlayOptions} object.
     */
    @NonNull
    @Override
    public OPFTileOverlayOptions fadeIn(final boolean fadeIn) {
        delegate.fadeIn(fadeIn);
        return this;
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
     * Gets the tile provider set for this {@link OPFTileOverlayOptions} object.
     *
     * @return The {@link OPFTileProvider} of the tile overlay.
     */
    @Nullable
    @Override
    public OPFTileProvider getTileProvider() {
        return delegate.getTileProvider();
    }

    /**
     * Gets the zIndex set for this {@link OPFTileOverlayOptions} object.
     *
     * @return The zIndex of the tile overlay.
     */
    @Override
    public float getZIndex() {
        return delegate.getZIndex();
    }

    /**
     * Gets the visibility setting for this {@link OPFTileOverlayOptions} object.
     *
     * @return {@code true} if the tile overlay is to be visible, {@code false} otherwise.
     */
    @Override
    public boolean isVisible() {
        return delegate.isVisible();
    }

    /**
     * Specifies the tile provider to use for this tile overlay.
     *
     * @param tileProvider The {@link OPFTileProvider} to use for this tile overlay.
     * @return This {@link OPFTileOverlayOptions} object.
     */
    @NonNull
    @Override
    public OPFTileOverlayOptions tileProvider(@NonNull final OPFTileProvider tileProvider) {
        delegate.tileProvider(tileProvider);
        return this;
    }

    /**
     * Specifies the visibility for the tile overlay. The default visibility is {@code true}.
     *
     * @param visible {@code false} to make the tile overlay invisible, {@code true} otherwise.
     * @return This {@link OPFTileOverlayOptions} object.
     */
    @NonNull
    @Override
    public OPFTileOverlayOptions visible(final boolean visible) {
        delegate.visible(visible);
        return this;
    }

    /**
     * Specifies the tile overlay's zIndex, i.e., the order in which it will be drawn where overlays with
     * larger values are drawn above those with lower values.
     *
     * @param zIndex The zIndex value.
     * @return This {@link OPFTileOverlayOptions} object.
     */
    @NonNull
    @Override
    public OPFTileOverlayOptions zIndex(final float zIndex) {
        delegate.zIndex(zIndex);
        return this;
    }

    @Override
    public int describeContents() {
        return delegate.describeContents();
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(delegate.getClass().getCanonicalName());
        dest.writeParcelable(delegate, flags);
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof OPFTileOverlayOptions
                && delegate.equals(((OPFTileOverlayOptions) other).delegate));
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
