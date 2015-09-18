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

package org.onepf.maps.yandexweb.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.onepf.opfmaps.model.OPFMapType;
import org.onepf.opfutils.OPFLog;

import static org.onepf.opfmaps.model.OPFMapType.NORMAL;

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class YaWebMapOptions implements Parcelable {

    public static final Creator<YaWebMapOptions> CREATOR = new Creator<YaWebMapOptions>() {
        @Override
        public YaWebMapOptions createFromParcel(final Parcel source) {
            return new YaWebMapOptions(source);
        }

        @Override
        public YaWebMapOptions[] newArray(final int size) {
            return new YaWebMapOptions[size];
        }
    };

    @NonNull
    private OPFMapType mapType = NORMAL;
    @Nullable
    private CameraPosition camera;
    @Nullable
    private Boolean isScrollGesturesEnabled;
    @Nullable
    private Boolean isZoomControlsEnabled;
    @Nullable
    private Boolean isZoomGesturesEnabled;

    public YaWebMapOptions() {
        //nothing
    }

    private YaWebMapOptions(@NonNull final Parcel parcel) {
        mapType = OPFMapType.valueOf(parcel.readString());
        camera = parcel.readParcelable(CameraPosition.class.getClassLoader());
        isScrollGesturesEnabled = readBoolean(parcel);
        isZoomControlsEnabled = readBoolean(parcel);
        isZoomGesturesEnabled = readBoolean(parcel);
    }

    @NonNull
    public YaWebMapOptions zOrderOnTop(final boolean zOrderOnTop) {
        OPFLog.logStubCall(zOrderOnTop);
        return this;
    }

    @NonNull
    public YaWebMapOptions useViewLifecycleInFragment(final boolean useViewLifecycleInFragment) {
        OPFLog.logStubCall(useViewLifecycleInFragment);
        return this;
    }

    @NonNull
    public YaWebMapOptions mapType(@NonNull final OPFMapType mapType) {
        this.mapType = mapType;
        return this;
    }

    @NonNull
    public YaWebMapOptions camera(@NonNull final CameraPosition camera) {
        this.camera = camera;
        return this;
    }

    @NonNull
    public YaWebMapOptions zoomControlsEnabled(final boolean enabled) {
        this.isZoomControlsEnabled = enabled;
        return this;
    }

    @NonNull
    public YaWebMapOptions compassEnabled(final boolean enabled) {
        OPFLog.logStubCall(enabled);
        return this;
    }

    @NonNull
    public YaWebMapOptions scrollGesturesEnabled(final boolean enabled) {
        this.isScrollGesturesEnabled = enabled;
        return this;
    }

    @NonNull
    public YaWebMapOptions zoomGesturesEnabled(final boolean enabled) {
        this.isZoomGesturesEnabled = enabled;
        return this;
    }

    @NonNull
    public YaWebMapOptions tiltGesturesEnabled(final boolean enabled) {
        OPFLog.logStubCall(enabled);
        return this;
    }

    @NonNull
    public YaWebMapOptions rotateGesturesEnabled(final boolean enabled) {
        OPFLog.logStubCall(enabled);
        return this;
    }

    @NonNull
    public YaWebMapOptions liteMode(final boolean enabled) {
        OPFLog.logStubCall(enabled);
        return this;
    }

    @NonNull
    public YaWebMapOptions mapToolbarEnabled(final boolean enabled) {
        OPFLog.logStubCall(enabled);
        return this;
    }

    @Nullable
    public Boolean getZOrderOnTop() {
        return false;
    }

    @Nullable
    public Boolean getUseViewLifecycleInFragment() {
        return false;
    }

    @NonNull
    public OPFMapType getMapType() {
        return mapType;
    }

    @Nullable
    public CameraPosition getCamera() {
        return camera;
    }

    @Nullable
    public Boolean getZoomControlsEnabled() {
        return isZoomControlsEnabled;
    }

    @Nullable
    public Boolean getCompassEnabled() {
        return false;
    }

    @Nullable
    public Boolean getScrollGesturesEnabled() {
        return isScrollGesturesEnabled;
    }

    @Nullable
    public Boolean getZoomGesturesEnabled() {
        return isZoomGesturesEnabled;
    }

    @Nullable
    public Boolean getTiltGesturesEnabled() {
        return false;
    }

    @Nullable
    public Boolean getRotateGesturesEnabled() {
        return false;
    }

    @Nullable
    public Boolean getLiteMode() {
        return false;
    }

    @Nullable
    public Boolean getMapToolbarEnabled() {
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(mapType.name());
        dest.writeParcelable(camera, flags);
        dest.writeValue(isScrollGesturesEnabled);
        dest.writeValue(isZoomControlsEnabled);
        dest.writeValue(isZoomGesturesEnabled);
    }

    private Boolean readBoolean(@NonNull final Parcel parcel) {
        return (Boolean) parcel.readValue(Boolean.class.getClassLoader());
    }
}
