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

import android.content.Context;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import org.onepf.opfmaps.delegate.model.CameraPositionDelegate;
import org.onepf.opfmaps.model.OPFCameraPosition;
import org.onepf.opfmaps.model.OPFLatLng;

/**
 * @author Roman Savin
 * @since 06.08.2015
 */
public final class OsmdroidCameraPositionDelegate implements CameraPositionDelegate {

    public static final Creator<OsmdroidCameraPositionDelegate> CREATOR = new Creator<OsmdroidCameraPositionDelegate>() {
        @Override
        public OsmdroidCameraPositionDelegate createFromParcel(final Parcel source) {
            return new OsmdroidCameraPositionDelegate(source);
        }

        @Override
        public OsmdroidCameraPositionDelegate[] newArray(final int size) {
            return new OsmdroidCameraPositionDelegate[size];
        }
    };

    @NonNull
    private final CameraPosition cameraPosition;

    public OsmdroidCameraPositionDelegate(@NonNull final Context context, @NonNull final AttributeSet attrs) {
        this.cameraPosition = CameraPosition.createFromAttributes(context, attrs);
    }

    public OsmdroidCameraPositionDelegate(@NonNull final OPFLatLng target, final float zoom) {
        this.cameraPosition = CameraPosition.fromLatLngZoom(new LatLng(target.getLat(), target.getLng()), zoom);
    }

    public OsmdroidCameraPositionDelegate(@NonNull final OPFLatLng target,
                                          final float zoom,
                                          final float tilt,
                                          final float bearing) {
        this.cameraPosition = new CameraPosition(new LatLng(target.getLat(), target.getLng()), zoom, tilt, bearing);
    }

    public OsmdroidCameraPositionDelegate(@NonNull final CameraPosition cameraPosition) {
        this.cameraPosition = cameraPosition;
    }

    private OsmdroidCameraPositionDelegate(@NonNull final Parcel parcel) {
        this.cameraPosition = parcel.readParcelable(CameraPosition.class.getClassLoader());
    }

    @Override
    public float getBearing() {
        return cameraPosition.bearing;
    }

    @NonNull
    @Override
    public OPFLatLng getTarget() {
        return new OPFLatLng(new OsmdroidLatLngDelegate(cameraPosition.target));
    }

    @Override
    public float getTilt() {
        return cameraPosition.tilt;
    }

    @Override
    public float getZoom() {
        return cameraPosition.zoom;
    }

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OsmdroidCameraPositionDelegate)) return false;

        return cameraPosition.equals(((OsmdroidCameraPositionDelegate) other).cameraPosition);
    }
    //CHECKSTYLE:ON

    @Override
    public String toString() {
        return cameraPosition.toString();
    }

    @Override
    public int hashCode() {
        return cameraPosition.hashCode();
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(cameraPosition, flags);
    }

    @Override
    public int describeContents() {
        return cameraPosition.describeContents();
    }

    public static class Builder implements CameraPositionDelegate.Builder {

        @NonNull
        private final CameraPosition.Builder delegate;

        public Builder() {
            this.delegate = CameraPosition.builder();
        }

        public Builder(@NonNull final OPFCameraPosition cameraPosition) {
            this.delegate = CameraPosition.builder(new CameraPosition(
                    new LatLng(cameraPosition.getTarget().getLat(), cameraPosition.getTarget().getLng()),
                    cameraPosition.getZoom(),
                    cameraPosition.getTilt(),
                    cameraPosition.getBearing()
            ));
        }

        @NonNull
        @Override
        public CameraPositionDelegate.Builder bearing(final float bearing) {
            delegate.bearing(bearing);
            return this;
        }

        @NonNull
        @Override
        public CameraPositionDelegate.Builder target(@NonNull final OPFLatLng target) {
            delegate.target(new LatLng(target.getLat(), target.getLng()));
            return this;
        }

        @NonNull
        @Override
        public CameraPositionDelegate.Builder tilt(final float tilt) {
            delegate.tilt(tilt);
            return this;
        }

        @NonNull
        @Override
        public CameraPositionDelegate.Builder zoom(final float zoom) {
            delegate.zoom(zoom);
            return this;
        }

        @SuppressWarnings("PMD.AccessorClassGeneration")
        @NonNull
        @Override
        public OPFCameraPosition build() {
            return new OPFCameraPosition(new OsmdroidCameraPositionDelegate(delegate.build()));
        }
    }
}
