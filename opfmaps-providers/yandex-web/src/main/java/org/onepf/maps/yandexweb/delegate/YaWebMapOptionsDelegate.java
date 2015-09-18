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

package org.onepf.maps.yandexweb.delegate;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.onepf.maps.yandexweb.delegate.model.YaWebCameraPositionDelegate;
import org.onepf.maps.yandexweb.model.CameraPosition;
import org.onepf.maps.yandexweb.model.LatLng;
import org.onepf.maps.yandexweb.model.YaWebMapOptions;
import org.onepf.opfmaps.delegate.MapOptionsDelegate;
import org.onepf.opfmaps.model.OPFCameraPosition;
import org.onepf.opfmaps.model.OPFMapType;
import org.onepf.opfutils.OPFLog;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class YaWebMapOptionsDelegate implements MapOptionsDelegate {

    public static final Creator<YaWebMapOptionsDelegate> CREATOR = new Creator<YaWebMapOptionsDelegate>() {
        @Override
        public YaWebMapOptionsDelegate createFromParcel(final Parcel source) {
            return new YaWebMapOptionsDelegate(source);
        }

        @Override
        public YaWebMapOptionsDelegate[] newArray(final int size) {
            return new YaWebMapOptionsDelegate[size];
        }
    };


    @NonNull
    private final YaWebMapOptions mapOptions;

    public YaWebMapOptionsDelegate(@NonNull final YaWebMapOptions mapOptions) {
        this.mapOptions = mapOptions;
    }

    private YaWebMapOptionsDelegate(@NonNull final Parcel parcel) {
        this.mapOptions = parcel.readParcelable(YaWebMapOptions.class.getClassLoader());
    }

    @NonNull
    @Override
    public YaWebMapOptionsDelegate zOrderOnTop(final boolean zOrderOnTop) {
        OPFLog.logStubCall(zOrderOnTop);
        return this;
    }

    @NonNull
    @Override
    public YaWebMapOptionsDelegate useViewLifecycleInFragment(final boolean useViewLifecycleInFragment) {
        mapOptions.useViewLifecycleInFragment(useViewLifecycleInFragment);
        return this;
    }

    @NonNull
    @Override
    public YaWebMapOptionsDelegate mapType(@NonNull final OPFMapType mapType) {
        mapOptions.mapType(mapType);
        return this;
    }

    @NonNull
    @Override
    public YaWebMapOptionsDelegate camera(@NonNull final OPFCameraPosition camera) {
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
    public YaWebMapOptionsDelegate zoomControlsEnabled(final boolean enabled) {
        mapOptions.zoomControlsEnabled(enabled);
        return this;
    }

    @NonNull
    @Override
    public YaWebMapOptionsDelegate compassEnabled(final boolean enabled) {
        mapOptions.compassEnabled(enabled);
        return this;
    }

    @NonNull
    @Override
    public YaWebMapOptionsDelegate scrollGesturesEnabled(final boolean enabled) {
        mapOptions.scrollGesturesEnabled(enabled);
        return this;
    }

    @NonNull
    @Override
    public YaWebMapOptionsDelegate zoomGesturesEnabled(final boolean enabled) {
        mapOptions.zoomGesturesEnabled(enabled);
        return this;
    }

    @NonNull
    @Override
    public YaWebMapOptionsDelegate tiltGesturesEnabled(final boolean enabled) {
        mapOptions.tiltGesturesEnabled(enabled);
        return this;
    }

    @NonNull
    @Override
    public YaWebMapOptionsDelegate rotateGesturesEnabled(final boolean enabled) {
        mapOptions.rotateGesturesEnabled(enabled);
        return this;
    }

    @NonNull
    @Override
    public YaWebMapOptionsDelegate liteMode(final boolean enabled) {
        mapOptions.liteMode(enabled);
        return this;
    }

    @NonNull
    @Override
    public YaWebMapOptionsDelegate mapToolbarEnabled(final boolean enabled) {
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
        return mapOptions.getMapType();
    }

    @Nullable
    @Override
    public OPFCameraPosition getCamera() {
        final CameraPosition cameraPosition = mapOptions.getCamera();
        if (cameraPosition != null) {
            return new OPFCameraPosition(new YaWebCameraPositionDelegate(cameraPosition));
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
                && (other == this || other instanceof YaWebMapOptionsDelegate
                && mapOptions.equals(((YaWebMapOptionsDelegate) other).mapOptions));
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
