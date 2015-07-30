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
import org.onepf.opfmaps.delegate.model.TileOverlayOptionsDelegate;

import javax.annotation.Nullable;

/**
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

    //todo default constructor

    public OPFTileOverlayOptions(@NonNull final TileOverlayOptionsDelegate delegate) {
        this.delegate = delegate;
    }

    private OPFTileOverlayOptions(@NonNull final Parcel parcel) {
        try {
            this.delegate = parcel.readParcelable(Class.forName(parcel.readString()).getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @NonNull
    public OPFTileOverlayOptions fadeIn(final boolean fadeIn) {
        delegate.fadeIn(fadeIn);
        return this;
    }

    @Override
    public boolean getFadeIn() {
        return delegate.getFadeIn();
    }

    @Override
    @Nullable
    public OPFTileProvider getTileProvider() {
        return delegate.getTileProvider();
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
    @NonNull
    public OPFTileOverlayOptions tileProvider(@NonNull final OPFTileProvider tileProvider) {
        delegate.tileProvider(tileProvider);
        return this;
    }

    @Override
    @NonNull
    public OPFTileOverlayOptions visible(final boolean visible) {
        delegate.visible(visible);
        return this;
    }

    @Override
    @NonNull
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
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OPFTileOverlayOptions)) return false;

        return delegate.equals(((OPFTileOverlayOptions) other).delegate);
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
