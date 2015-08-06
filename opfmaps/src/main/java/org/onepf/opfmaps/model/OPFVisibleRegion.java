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
import org.onepf.opfmaps.delegate.model.VisibleRegionDelegate;

/**
 * @author Roman Savin
 * @since 06.08.2015
 */
public class OPFVisibleRegion implements VisibleRegionDelegate {

    public static final Creator<OPFVisibleRegion> CREATOR = new Creator<OPFVisibleRegion>() {
        @Override
        public OPFVisibleRegion createFromParcel(final Parcel source) {
            return new OPFVisibleRegion(source);
        }

        @Override
        public OPFVisibleRegion[] newArray(final int size) {
            return new OPFVisibleRegion[size];
        }
    };

    @NonNull
    private final VisibleRegionDelegate delegate;

    public OPFVisibleRegion(@NonNull final OPFLatLng nearLeft,
                            @NonNull final OPFLatLng nearRight,
                            @NonNull final OPFLatLng farLeft,
                            @NonNull final OPFLatLng farRight,
                            @NonNull final OPFLatLngBounds latLngBounds) {
        this.delegate = OPFMapHelper.getInstance().getDelegatesFactory()
                .createVisibleRegionDelegate(nearLeft, nearRight, farLeft, farRight, latLngBounds);
    }

    public OPFVisibleRegion(@NonNull final VisibleRegionDelegate delegate) {
        this.delegate = delegate;
    }

    @SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes")
    private OPFVisibleRegion(@NonNull final Parcel parcel) {
        try {
            this.delegate = parcel.readParcelable(Class.forName(parcel.readString()).getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @NonNull
    public OPFLatLng getFarLeft() {
        return delegate.getFarLeft();
    }

    @Override
    @NonNull
    public OPFLatLng getFarRight() {
        return delegate.getFarRight();
    }

    @Override
    @NonNull
    public OPFLatLngBounds getLatLngBounds() {
        return delegate.getLatLngBounds();
    }

    @Override
    @NonNull
    public OPFLatLng getNearLeft() {
        return delegate.getNearLeft();
    }

    @Override
    @NonNull
    public OPFLatLng getNearRight() {
        return delegate.getNearRight();
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
        if (!(other instanceof OPFVisibleRegion)) return false;

        return delegate.equals(((OPFVisibleRegion) other).delegate);
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
