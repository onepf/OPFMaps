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

import android.content.Context;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import org.onepf.opfmaps.OPFMapHelper;
import org.onepf.opfmaps.delegate.model.CameraPositionDelegate;

/**
 * @author Roman Savin
 * @since 06.08.2015
 */
public final class OPFCameraPosition implements CameraPositionDelegate {

    public static final Creator<OPFCameraPosition> CREATOR = new Creator<OPFCameraPosition>() {
        @Override
        public OPFCameraPosition createFromParcel(final Parcel source) {
            return new OPFCameraPosition(source);
        }

        @Override
        public OPFCameraPosition[] newArray(final int size) {
            return new OPFCameraPosition[size];
        }
    };

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    @NonNull
    public static Builder builder(@NonNull final OPFCameraPosition camera) {
        return new Builder(camera);
    }

    @NonNull
    public static OPFCameraPosition createFromAttributes(@NonNull final Context context,
                                                         @NonNull final AttributeSet attrs) {
        return new OPFCameraPosition(OPFMapHelper.getInstance().getDelegatesFactory()
                .createCameraPositionDelegate(context, attrs));
    }

    @NonNull
    public static OPFCameraPosition fromLatLngZoom(@NonNull final OPFLatLng target, final float zoom) {
        return new OPFCameraPosition(OPFMapHelper.getInstance().getDelegatesFactory()
                .createCameraPositionDelegate(target, zoom));
    }

    @NonNull
    private final CameraPositionDelegate delegate;

    public OPFCameraPosition(@NonNull final OPFLatLng target,
                             final float zoom,
                             final float tilt,
                             final float bearing) {
        this.delegate = OPFMapHelper.getInstance().getDelegatesFactory()
                .createCameraPositionDelegate(target, zoom, tilt, bearing);
    }

    public OPFCameraPosition(@NonNull final CameraPositionDelegate delegate) {
        this.delegate = delegate;
    }

    @SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes")
    private OPFCameraPosition(@NonNull final Parcel parcel) {
        try {
            this.delegate = parcel.readParcelable(Class.forName(parcel.readString()).getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public float getBearing() {
        return delegate.getBearing();
    }

    @Override
    @NonNull
    public OPFLatLng getTarget() {
        return delegate.getTarget();
    }

    @Override
    public float getTilt() {
        return delegate.getTilt();
    }

    @Override
    public float getZoom() {
        return delegate.getZoom();
    }

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OPFCameraPosition)) return false;

        return delegate.equals(((OPFCameraPosition) other).delegate);
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

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(delegate.getClass().getCanonicalName());
        dest.writeParcelable(delegate, flags);
    }

    @Override
    public int describeContents() {
        return delegate.describeContents();
    }

    public static class Builder implements CameraPositionDelegate.Builder {

        @NonNull
        private final CameraPositionDelegate.Builder delegate;

        public Builder() {
            this.delegate = OPFMapHelper.getInstance().getDelegatesFactory().createCameraPositionBuilderDelegate();
        }

        public Builder(@NonNull final OPFCameraPosition camera) {
            this.delegate = OPFMapHelper.getInstance().getDelegatesFactory().createCameraPositionBuilderDelegate(camera);
        }

        @NonNull
        @Override
        public Builder bearing(final float bearing) {
            delegate.bearing(bearing);
            return this;
        }

        @NonNull
        @Override
        public Builder target(@NonNull final OPFLatLng target) {
            delegate.target(target);
            return this;
        }

        @NonNull
        @Override
        public Builder tilt(final float tilt) {
            delegate.tilt(tilt);
            return this;
        }

        @NonNull
        @Override
        public Builder zoom(final float zoom) {
            delegate.zoom(zoom);
            return this;
        }

        @NonNull
        @Override
        public OPFCameraPosition build() {
            return delegate.build();
        }
    }
}
