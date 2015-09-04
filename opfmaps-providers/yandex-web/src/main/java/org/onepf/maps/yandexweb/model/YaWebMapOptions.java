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

/**
 * @author Roman Savin
 * @since 02.09.2015
 */
public final class YaWebMapOptions implements Parcelable {

    public Creator<YaWebMapOptions> CREATOR = new Creator<YaWebMapOptions>() {
        @Override
        public YaWebMapOptions createFromParcel(final Parcel source) {
            return new YaWebMapOptions(source);
        }

        @Override
        public YaWebMapOptions[] newArray(final int size) {
            return new YaWebMapOptions[size];
        }
    };

    public YaWebMapOptions() {
        //nothing
    }

    private YaWebMapOptions(@NonNull final Parcel source) {
        //todo implement
    }

    @NonNull
    public YaWebMapOptions zOrderOnTop(final boolean zOrderOnTop) {
        //todo implement
        return this;
    }

    @NonNull
    public YaWebMapOptions useViewLifecycleInFragment(final boolean useViewLifecycleInFragment) {
        //todo implement
        return this;
    }

    @NonNull
    public YaWebMapOptions mapType(@NonNull final OPFMapType mapType) {
        //todo implement
        return this;
    }

    @NonNull
    public YaWebMapOptions camera(@NonNull final CameraPosition camera) {
        //todo implement
        return this;
    }

    @NonNull
    public YaWebMapOptions zoomControlsEnabled(final boolean enabled) {
        //todo implement
        return this;
    }

    @NonNull
    public YaWebMapOptions compassEnabled(final boolean enabled) {
        //todo implement
        return this;
    }

    @NonNull
    public YaWebMapOptions scrollGesturesEnabled(final boolean enabled) {
        //todo implement
        return this;
    }

    @NonNull
    public YaWebMapOptions zoomGesturesEnabled(final boolean enabled) {
        //todo implement
        return this;
    }

    @NonNull
    public YaWebMapOptions tiltGesturesEnabled(final boolean enabled) {
        //todo implement
        return this;
    }

    @NonNull
    public YaWebMapOptions rotateGesturesEnabled(final boolean enabled) {
        //todo implement
        return this;
    }

    @NonNull
    public YaWebMapOptions liteMode(final boolean enabled) {
        //todo implement
        return this;
    }

    @NonNull
    public YaWebMapOptions mapToolbarEnabled(final boolean enabled) {
        //todo implement
        return this;
    }

    @Nullable
    public Boolean getZOrderOnTop() {
        return false;
        //todo implement
    }

    @Nullable
    public Boolean getUseViewLifecycleInFragment() {
        //todo implement
        return false;
    }

    @NonNull
    public OPFMapType getMapType() {
        //todo implement
        return OPFMapType.NORMAL;
    }

    @Nullable
    public CameraPosition getCamera() {
        //todo implement
        return null;
    }

    @Nullable
    public Boolean getZoomControlsEnabled() {
        //todo implement
        return false;
    }

    @Nullable
    public Boolean getCompassEnabled() {
        //todo implement
        return false;
    }

    @Nullable
    public Boolean getScrollGesturesEnabled() {
        //todo implement
        return false;
    }

    @Nullable
    public Boolean getZoomGesturesEnabled() {
        //todo implement
        return false;
    }

    @Nullable
    public Boolean getTiltGesturesEnabled() {
        //todo implement
        return false;
    }

    @Nullable
    public Boolean getRotateGesturesEnabled() {
        //todo implement
        return false;
    }

    @Nullable
    public Boolean getLiteMode() {
        //todo implement
        return false;
    }

    @Nullable
    public Boolean getMapToolbarEnabled() {
        //todo implement
        return false;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        //todo implement
    }

    @Override
    public boolean equals(final Object other) {
        //todo implement
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        //todo implement
        return super.hashCode();
    }

    @Override
    public String toString() {
        //todo implement
        return super.toString();
    }
}
