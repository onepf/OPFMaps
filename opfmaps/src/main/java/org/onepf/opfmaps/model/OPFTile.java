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

import org.onepf.opfmaps.OPFMapHelper;
import org.onepf.opfmaps.delegate.model.TileDelegate;

/**
 * @author Roman Savin
 * @since 30.07.2015
 */
public final class OPFTile implements TileDelegate {

    public static final Creator<OPFTile> CREATOR = new Creator<OPFTile>() {
        @Override
        public OPFTile createFromParcel(final Parcel source) {
            return new OPFTile(source);
        }

        @Override
        public OPFTile[] newArray(final int size) {
            return new OPFTile[size];
        }
    };

    @NonNull
    private final TileDelegate delegate;

    public OPFTile(final int width, final int height, @NonNull final byte[] data) {
        this.delegate = OPFMapHelper.getInstance().getDelegatesFactory().createTileDelegate(width, height, data);
    }

    public OPFTile(@NonNull final TileDelegate delegate) {
        this.delegate = delegate;
    }

    @SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes")
    private OPFTile(@NonNull final Parcel parcel) {
        try {
            this.delegate = parcel.readParcelable(Class.forName(parcel.readString()).getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @NonNull
    public byte[] getData() {
        return delegate.getData();
    }

    @Override
    public int getHeight() {
        return delegate.getHeight();
    }

    @Override
    public int getWidth() {
        return delegate.getWidth();
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
                && (other == this || other instanceof OPFTile
                && delegate.equals(((OPFTile) other).delegate));
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
