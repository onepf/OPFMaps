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

package org.onepf.maps.amazon.delegate;

import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.amazon.geo.mapsv2.AmazonMapOptions;
import com.amazon.geo.mapsv2.model.CameraPosition;
import com.amazon.geo.mapsv2.model.LatLng;

import org.onepf.maps.amazon.delegate.model.AmazonCameraPositionDelegate;
import org.onepf.maps.amazon.utils.ConvertUtils;
import org.onepf.opfmaps.delegate.MapOptionsDelegate;
import org.onepf.opfmaps.model.OPFCameraPosition;
import org.onepf.opfmaps.model.OPFMapType;

/**
 * @author Roman Savin
 * @since 06.08.2015
 */
public final class AmazonMapOptionsDelegate implements MapOptionsDelegate {

    public static final Creator<AmazonMapOptionsDelegate> CREATOR = new Creator<AmazonMapOptionsDelegate>() {
        @Override
        public AmazonMapOptionsDelegate createFromParcel(final Parcel source) {
            return new AmazonMapOptionsDelegate(source);
        }

        @Override
        public AmazonMapOptionsDelegate[] newArray(final int size) {
            return new AmazonMapOptionsDelegate[size];
        }
    };

    @NonNull
    private final AmazonMapOptions mapOptions;

    public AmazonMapOptionsDelegate(@NonNull final AmazonMapOptions mapOptions) {
        this.mapOptions = mapOptions;
    }

    private AmazonMapOptionsDelegate(@NonNull final Parcel parcel) {
        this.mapOptions = parcel.readParcelable(AmazonMapOptions.class.getClassLoader());
    }

    @NonNull
    @Override
    public AmazonMapOptionsDelegate zOrderOnTop(final boolean zOrderOnTop) {
        mapOptions.zOrderOnTop(zOrderOnTop);
        return this;
    }

    @NonNull
    @Override
    public AmazonMapOptionsDelegate useViewLifecycleInFragment(final boolean useViewLifecycleInFragment) {
        mapOptions.useViewLifecycleInFragment(useViewLifecycleInFragment);
        return this;
    }

    @NonNull
    @Override
    public AmazonMapOptionsDelegate mapType(@NonNull final OPFMapType mapType) {
        mapOptions.mapType(ConvertUtils.convertMapType(mapType));
        return this;
    }

    @NonNull
    @Override
    public AmazonMapOptionsDelegate camera(@NonNull final OPFCameraPosition camera) {
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
    public AmazonMapOptionsDelegate zoomControlsEnabled(final boolean enabled) {
        mapOptions.zoomControlsEnabled(enabled);
        return this;
    }

    @NonNull
    @Override
    public AmazonMapOptionsDelegate compassEnabled(final boolean enabled) {
        mapOptions.compassEnabled(enabled);
        return this;
    }

    @NonNull
    @Override
    public AmazonMapOptionsDelegate scrollGesturesEnabled(final boolean enabled) {
        mapOptions.scrollGesturesEnabled(enabled);
        return this;
    }

    @NonNull
    @Override
    public AmazonMapOptionsDelegate zoomGesturesEnabled(final boolean enabled) {
        mapOptions.zoomGesturesEnabled(enabled);
        return this;
    }

    @NonNull
    @Override
    public AmazonMapOptionsDelegate tiltGesturesEnabled(final boolean enabled) {
        mapOptions.tiltGesturesEnabled(enabled);
        return this;
    }

    @NonNull
    @Override
    public AmazonMapOptionsDelegate rotateGesturesEnabled(final boolean enabled) {
        mapOptions.rotateGesturesEnabled(enabled);
        return this;
    }

    @NonNull
    @Override
    public AmazonMapOptionsDelegate liteMode(final boolean enabled) {
        mapOptions.liteMode(enabled);
        return this;
    }

    @NonNull
    @Override
    public AmazonMapOptionsDelegate mapToolbarEnabled(final boolean enabled) {
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
            return new OPFCameraPosition(new AmazonCameraPositionDelegate(cameraPosition));
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
                && (other == this || other instanceof AmazonMapOptionsDelegate
                && mapOptions.equals(((AmazonMapOptionsDelegate) other).mapOptions));
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
