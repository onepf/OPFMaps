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

    @NonNull
    @Override
    public OPFTileOverlayOptions fadeIn(final boolean fadeIn) {
        delegate.fadeIn(fadeIn);
        return this;
    }

    @SuppressWarnings("PMD.BooleanGetMethodName")
    @Override
    public boolean getFadeIn() {
        return delegate.getFadeIn();
    }

    @Nullable
    @Override
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

    @NonNull
    @Override
    public OPFTileOverlayOptions tileProvider(@NonNull final OPFTileProvider tileProvider) {
        delegate.tileProvider(tileProvider);
        return this;
    }

    @NonNull
    @Override
    public OPFTileOverlayOptions visible(final boolean visible) {
        delegate.visible(visible);
        return this;
    }

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

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OPFTileOverlayOptions)) return false;

        return delegate.equals(((OPFTileOverlayOptions) other).delegate);
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
}
