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

package org.onepf.maps.osmdroid.delegate;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import org.onepf.maps.osmdroid.model.OsmdroidMapOptions;
import org.onepf.opfmaps.delegate.MapOptionsDelegate;
import org.onepf.opfmaps.model.OPFCameraPosition;
import org.onepf.opfmaps.model.OPFMapType;
import org.onepf.opfutils.OPFLog;

/**
 * @author Roman Savin
 * @since 06.08.2015
 */
public final class OsmdroidMapOptionsDelegate implements MapOptionsDelegate {

    public static final Creator<OsmdroidMapOptionsDelegate> CREATOR = new Creator<OsmdroidMapOptionsDelegate>() {
        @Override
        public OsmdroidMapOptionsDelegate createFromParcel(final Parcel source) {
            return new OsmdroidMapOptionsDelegate(source);
        }

        @Override
        public OsmdroidMapOptionsDelegate[] newArray(final int size) {
            return new OsmdroidMapOptionsDelegate[size];
        }
    };


    @NonNull
    private final OsmdroidMapOptions mapOptions;

    public OsmdroidMapOptionsDelegate(@NonNull final OsmdroidMapOptions mapOptions) {
        this.mapOptions = mapOptions;
    }

    private OsmdroidMapOptionsDelegate(@NonNull final Parcel parcel) {
        this.mapOptions = parcel.readParcelable(OsmdroidMapOptions.class.getClassLoader());
    }

    @NonNull
    @Override
    public OsmdroidMapOptionsDelegate zOrderOnTop(final boolean zOrderOnTop) {
        OPFLog.logStubCall(zOrderOnTop);
        return this;
    }

    @NonNull
    @Override
    public OsmdroidMapOptionsDelegate useViewLifecycleInFragment(final boolean useViewLifecycleInFragment) {
        //todo implement
        /*mapOptions.useViewLifecycleInFragment(useViewLifecycleInFragment);*/
        return this;
    }

    @NonNull
    @Override
    public OsmdroidMapOptionsDelegate mapType(@NonNull final OPFMapType mapType) {
        //todo implement
        /*mapOptions.mapType(ConvertUtils.convertMapType(mapType));*/
        return this;
    }

    @NonNull
    @Override
    public OsmdroidMapOptionsDelegate camera(@NonNull final OPFCameraPosition camera) {
        //todo implement
        /*mapOptions.camera(new CameraPosition(
                new LatLng(camera.getTarget().getLat(), camera.getTarget().getLng()),
                camera.getZoom(),
                camera.getTilt(),
                camera.getBearing()
        ));*/
        return this;
    }

    @NonNull
    @Override
    public OsmdroidMapOptionsDelegate zoomControlsEnabled(final boolean enabled) {
        //todo implement
        /*mapOptions.zoomControlsEnabled(enabled);*/
        return this;
    }

    @NonNull
    @Override
    public OsmdroidMapOptionsDelegate compassEnabled(final boolean enabled) {
        //todo implement
        /*mapOptions.compassEnabled(enabled);*/
        return this;
    }

    @NonNull
    @Override
    public OsmdroidMapOptionsDelegate scrollGesturesEnabled(final boolean enabled) {
        //todo implement
        /*mapOptions.scrollGesturesEnabled(enabled);*/
        return this;
    }

    @NonNull
    @Override
    public OsmdroidMapOptionsDelegate zoomGesturesEnabled(final boolean enabled) {
        //todo implement
        /*mapOptions.zoomGesturesEnabled(enabled);*/
        return this;
    }

    @NonNull
    @Override
    public OsmdroidMapOptionsDelegate tiltGesturesEnabled(final boolean enabled) {
        //todo imeplement
        /*mapOptions.tiltGesturesEnabled(enabled);*/
        return this;
    }

    @NonNull
    @Override
    public OsmdroidMapOptionsDelegate rotateGesturesEnabled(final boolean enabled) {
        //todo implement
        /*mapOptions.rotateGesturesEnabled(enabled);*/
        return this;
    }

    @NonNull
    @Override
    public OsmdroidMapOptionsDelegate liteMode(final boolean enabled) {
        //todo implement
        /*mapOptions.liteMode(enabled);*/
        return this;
    }

    @NonNull
    @Override
    public OsmdroidMapOptionsDelegate mapToolbarEnabled(final boolean enabled) {
        //todo implement
        /*mapOptions.mapToolbarEnabled(enabled);*/
        return this;
    }

    @Nullable
    @Override
    public Boolean getZOrderOnTop() {
        //todo implement
        return null;
        /*return mapOptions.getZOrderOnTop();*/
    }

    @Nullable
    @Override
    public Boolean getUseViewLifecycleInFragment() {
        //todo implement
        return null;
        /*return mapOptions.getUseViewLifecycleInFragment();*/
    }

    @NonNull
    @Override
    public OPFMapType getMapType() {
        //todo check other types in osmdroid
        return OPFMapType.NORMAL;
    }

    @Nullable
    @Override
    public OPFCameraPosition getCamera() {
        //todo implement
        /*final CameraPosition cameraPosition = mapOptions.getCamera();
        if (cameraPosition != null) {
            return new OPFCameraPosition(new GoogleCameraPositionDelegate(cameraPosition));
        }*/
        return null;
    }

    @Nullable
    @Override
    public Boolean getZoomControlsEnabled() {
        //todo implement
        return null;
        /*return mapOptions.getZoomControlsEnabled();*/
    }

    @Nullable
    @Override
    public Boolean getCompassEnabled() {
        //todo implement
        return null;
        /*return mapOptions.getCompassEnabled();*/
    }

    @Nullable
    @Override
    public Boolean getScrollGesturesEnabled() {
        //todo implement
        return null;
        /*return mapOptions.getScrollGesturesEnabled();*/
    }

    @Nullable
    @Override
    public Boolean getZoomGesturesEnabled() {
        //todo implement
        return null;
        /*return mapOptions.getZoomGesturesEnabled();*/
    }

    @Nullable
    @Override
    public Boolean getTiltGesturesEnabled() {
        //todo implement
        return null;
        /*return mapOptions.getTiltGesturesEnabled();*/
    }

    @Nullable
    @Override
    public Boolean getRotateGesturesEnabled() {
        //todo implement
        return null;
        /*return mapOptions.getRotateGesturesEnabled();*/
    }

    @Nullable
    @Override
    public Boolean getLiteMode() {
        //todo implement
        return null;
        /*return mapOptions.getLiteMode();*/
    }

    @Nullable
    @Override
    public Boolean getMapToolbarEnabled() {
        //todo implement
        return null;
        /*return mapOptions.getMapToolbarEnabled();*/
    }

    @Override
    public int describeContents() {
        return mapOptions.describeContents();
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeParcelable(mapOptions, flags);
    }

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof OsmdroidMapOptionsDelegate)) return false;

        return mapOptions.equals(((OsmdroidMapOptionsDelegate) other).mapOptions);
    }
    //CHECKSTYLE:ON

    @Override
    public int hashCode() {
        return mapOptions.hashCode();
    }

    @Override
    public String toString() {
        return mapOptions.toString();
    }
}
