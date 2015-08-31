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

package org.onepf.maps.google.delegate;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import org.onepf.maps.google.delegate.model.GoogleCameraPositionDelegate;
import org.onepf.maps.google.utils.ConvertUtils;
import org.onepf.opfmaps.delegate.MapOptionsDelegate;
import org.onepf.opfmaps.model.OPFCameraPosition;
import org.onepf.opfmaps.model.OPFMapType;

/**
 * @author Roman Savin
 * @since 06.08.2015
 */
public final class GoogleMapOptionsDelegate implements MapOptionsDelegate {

    public static final Creator<GoogleMapOptionsDelegate> CREATOR = new Creator<GoogleMapOptionsDelegate>() {
        @Override
        public GoogleMapOptionsDelegate createFromParcel(final Parcel source) {
            return new GoogleMapOptionsDelegate(source);
        }

        @Override
        public GoogleMapOptionsDelegate[] newArray(final int size) {
            return new GoogleMapOptionsDelegate[size];
        }
    };

    @NonNull
    private final GoogleMapOptions mapOptions;

    public GoogleMapOptionsDelegate(@NonNull final GoogleMapOptions mapOptions) {
        this.mapOptions = mapOptions;
    }

    private GoogleMapOptionsDelegate(@NonNull final Parcel parcel) {
        this.mapOptions = parcel.readParcelable(GoogleMapOptions.class.getClassLoader());
    }

    @NonNull
    @Override
    public GoogleMapOptionsDelegate zOrderOnTop(final boolean zOrderOnTop) {
        mapOptions.zOrderOnTop(zOrderOnTop);
        return this;
    }

    @NonNull
    @Override
    public GoogleMapOptionsDelegate useViewLifecycleInFragment(final boolean useViewLifecycleInFragment) {
        mapOptions.useViewLifecycleInFragment(useViewLifecycleInFragment);
        return this;
    }

    @NonNull
    @Override
    public GoogleMapOptionsDelegate mapType(@NonNull final OPFMapType mapType) {
        mapOptions.mapType(ConvertUtils.convertMapType(mapType));
        return this;
    }

    @NonNull
    @Override
    public GoogleMapOptionsDelegate camera(@NonNull final OPFCameraPosition camera) {
        mapOptions.camera(new CameraPosition(
                new LatLng(camera.getTarget().getLat(), camera.getTarget().getLng()),
                camera.getZoom(),
                camera.getTilt(),
                camera.getBearing()
        ));
        return this;
    }

    @NonNull
    @Override
    public GoogleMapOptionsDelegate zoomControlsEnabled(final boolean enabled) {
        mapOptions.zoomControlsEnabled(enabled);
        return this;
    }

    @NonNull
    @Override
    public GoogleMapOptionsDelegate compassEnabled(final boolean enabled) {
        mapOptions.compassEnabled(enabled);
        return this;
    }

    @NonNull
    @Override
    public GoogleMapOptionsDelegate scrollGesturesEnabled(final boolean enabled) {
        mapOptions.scrollGesturesEnabled(enabled);
        return this;
    }

    @NonNull
    @Override
    public GoogleMapOptionsDelegate zoomGesturesEnabled(final boolean enabled) {
        mapOptions.zoomGesturesEnabled(enabled);
        return this;
    }

    @NonNull
    @Override
    public GoogleMapOptionsDelegate tiltGesturesEnabled(final boolean enabled) {
        mapOptions.tiltGesturesEnabled(enabled);
        return this;
    }

    @NonNull
    @Override
    public GoogleMapOptionsDelegate rotateGesturesEnabled(final boolean enabled) {
        mapOptions.rotateGesturesEnabled(enabled);
        return this;
    }

    @NonNull
    @Override
    public GoogleMapOptionsDelegate liteMode(final boolean enabled) {
        mapOptions.liteMode(enabled);
        return this;
    }

    @NonNull
    @Override
    public GoogleMapOptionsDelegate mapToolbarEnabled(final boolean enabled) {
        mapOptions.mapToolbarEnabled(enabled);
        return this;
    }

    @Nullable
    @Override
    public Boolean getZOrderOnTop() {
        return mapOptions.getZOrderOnTop();
    }

    @Nullable
    @Override
    public Boolean getUseViewLifecycleInFragment() {
        return mapOptions.getUseViewLifecycleInFragment();
    }

    @NonNull
    @Override
    public OPFMapType getMapType() {
        return ConvertUtils.convertMapType(mapOptions.getMapType());
    }

    @Nullable
    @Override
    public OPFCameraPosition getCamera() {
        final CameraPosition cameraPosition = mapOptions.getCamera();
        if (cameraPosition != null) {
            return new OPFCameraPosition(new GoogleCameraPositionDelegate(cameraPosition));
        }
        return null;
    }

    @Nullable
    @Override
    public Boolean getZoomControlsEnabled() {
        return mapOptions.getZoomControlsEnabled();
    }

    @Nullable
    @Override
    public Boolean getCompassEnabled() {
        return mapOptions.getCompassEnabled();
    }

    @Nullable
    @Override
    public Boolean getScrollGesturesEnabled() {
        return mapOptions.getScrollGesturesEnabled();
    }

    @Nullable
    @Override
    public Boolean getZoomGesturesEnabled() {
        return mapOptions.getZoomGesturesEnabled();
    }

    @Nullable
    @Override
    public Boolean getTiltGesturesEnabled() {
        return mapOptions.getTiltGesturesEnabled();
    }

    @Nullable
    @Override
    public Boolean getRotateGesturesEnabled() {
        return mapOptions.getRotateGesturesEnabled();
    }

    @Nullable
    @Override
    public Boolean getLiteMode() {
        return mapOptions.getLiteMode();
    }

    @Nullable
    @Override
    public Boolean getMapToolbarEnabled() {
        return mapOptions.getMapToolbarEnabled();
    }

    @Override
    public int describeContents() {
        return mapOptions.describeContents();
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(mapOptions, flags);
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof GoogleMapOptionsDelegate
                && mapOptions.equals(((GoogleMapOptionsDelegate) other).mapOptions));
    }

    @Override
    public int hashCode() {
        return mapOptions.hashCode();
    }

    @Override
    public String toString() {
        return mapOptions.toString();
    }
}
