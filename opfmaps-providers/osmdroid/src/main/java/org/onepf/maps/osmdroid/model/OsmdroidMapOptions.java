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

package org.onepf.maps.osmdroid.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.onepf.opfmaps.model.OPFMapType;
import org.onepf.opfutils.OPFLog;

import static org.onepf.opfmaps.model.OPFMapType.NORMAL;

/**
 * @author Roman Savin
 * @since 17.08.2015
 */
public final class OsmdroidMapOptions implements Parcelable {

    public static final Creator<OsmdroidMapOptions> CREATOR = new Creator<OsmdroidMapOptions>() {
        @Override
        public OsmdroidMapOptions createFromParcel(final Parcel source) {
            return new OsmdroidMapOptions(source);
        }

        @Override
        public OsmdroidMapOptions[] newArray(final int size) {
            return new OsmdroidMapOptions[size];
        }
    };

    @NonNull
    private OPFMapType mapType = NORMAL;
    @Nullable
    private CameraPosition camera;
    @Nullable
    private Boolean isCompassEnabled;
    @Nullable
    private Boolean isRotateGesturesEnabled;
    @Nullable
    private Boolean isScrollGesturesEnabled;
    @Nullable
    private Boolean isZoomControlsEnabled;
    @Nullable
    private Boolean isZoomGesturesEnabled;

    public OsmdroidMapOptions() {
        //nothing
    }

    private OsmdroidMapOptions(@NonNull final Parcel parcel) {
        mapType = OPFMapType.valueOf(parcel.readString());
        camera = parcel.readParcelable(CameraPosition.class.getClassLoader());
        isCompassEnabled = readBoolean(parcel);
        isRotateGesturesEnabled = readBoolean(parcel);
        isScrollGesturesEnabled = readBoolean(parcel);
        isZoomControlsEnabled = readBoolean(parcel);
        isZoomGesturesEnabled = readBoolean(parcel);
    }

    @NonNull
    public OsmdroidMapOptions camera(@NonNull final CameraPosition camera) {
        this.camera = camera;
        return this;
    }

    @NonNull
    public OsmdroidMapOptions compassEnabled(final boolean enabled) {
        this.isCompassEnabled = enabled;
        return this;
    }

    @Nullable
    public CameraPosition getCamera() {
        return this.camera;
    }

    @Nullable
    public Boolean getCompassEnabled() {
        return this.isCompassEnabled;
    }

    @Nullable
    public Boolean getMapToolbarEnabled() {
        return null;
    }

    @NonNull
    public OPFMapType getMapType() {
        return this.mapType;
    }

    @Nullable
    public Boolean getLiteMode() {
        return null;
    }

    @Nullable
    public Boolean getRotateGesturesEnabled() {
        return this.isRotateGesturesEnabled;
    }

    @Nullable
    public Boolean getScrollGesturesEnabled() {
        return this.isScrollGesturesEnabled;
    }

    @Nullable
    public Boolean getTiltGesturesEnabled() {
        return false;
    }

    @Nullable
    public Boolean getUseViewLifecycleInFragment() {
        return null;
    }

    @Nullable
    public Boolean getZOrderOnTop() {
        return null;
    }

    @Nullable
    public Boolean getZoomControlsEnabled() {
        return this.isZoomControlsEnabled;
    }

    @Nullable
    public Boolean getZoomGesturesEnabled() {
        return this.isZoomGesturesEnabled;
    }

    @NonNull
    public OsmdroidMapOptions mapToolbarEnabled(final boolean enabled) {
        OPFLog.logStubCall(enabled);
        return this;
    }

    @NonNull
    public OsmdroidMapOptions mapType(@NonNull final OPFMapType mapType) {
        this.mapType = mapType;
        return this;
    }

    @NonNull
    public OsmdroidMapOptions liteMode(final boolean enabled) {
        OPFLog.logStubCall(enabled);
        return this;
    }

    @NonNull
    public OsmdroidMapOptions rotateGesturesEnabled(final boolean enabled) {
        this.isRotateGesturesEnabled = enabled;
        return this;
    }

    @NonNull
    public OsmdroidMapOptions scrollGesturesEnabled(final boolean enabled) {
        this.isScrollGesturesEnabled = enabled;
        return this;
    }

    @NonNull
    public OsmdroidMapOptions tiltGesturesEnabled(final boolean enabled) {
        OPFLog.logStubCall(enabled);
        return this;
    }

    @NonNull
    public OsmdroidMapOptions useViewLifecycleInFragment(final boolean useViewLifecycleInFragment) {
        OPFLog.logStubCall(useViewLifecycleInFragment);
        return this;
    }

    @NonNull
    public OsmdroidMapOptions zOrderOnTop(final boolean zOrderOnTop) {
        OPFLog.logStubCall(zOrderOnTop);
        return this;
    }

    @NonNull
    public OsmdroidMapOptions zoomControlsEnabled(final boolean enabled) {
        this.isZoomControlsEnabled = enabled;
        return this;
    }

    @NonNull
    public OsmdroidMapOptions zoomGesturesEnabled(final boolean enabled) {
        this.isZoomGesturesEnabled = enabled;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(mapType.name());
        dest.writeParcelable(camera, flags);
        dest.writeValue(isCompassEnabled);
        dest.writeValue(isRotateGesturesEnabled);
        dest.writeValue(isScrollGesturesEnabled);
        dest.writeValue(isZoomControlsEnabled);
        dest.writeValue(isZoomGesturesEnabled);
    }

    private Boolean readBoolean(@NonNull final Parcel parcel) {
        return (Boolean) parcel.readValue(Boolean.class.getClassLoader());
    }
}
