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

package org.onepf.maps.google.delegate.model;

import android.support.annotation.NonNull;

import com.google.android.gms.maps.CameraUpdate;

import org.onepf.opfmaps.delegate.model.CameraUpdateDelegate;

/**
 * @author Roman Savin
 * @since 06.08.2015
 */
public final class GoogleCameraUpdateDelegate implements CameraUpdateDelegate<CameraUpdate> {

    @NonNull
    private final CameraUpdate cameraUpdate;

    public GoogleCameraUpdateDelegate(@NonNull final CameraUpdate cameraUpdate) {
        this.cameraUpdate = cameraUpdate;
    }

    @NonNull
    @Override
    public CameraUpdate getCameraUpdate() {
        return cameraUpdate;
    }

    @Override
    public boolean equals(final Object other) {
        return other != null
                && (other == this || other instanceof GoogleCameraUpdateDelegate
                && cameraUpdate.equals(((GoogleCameraUpdateDelegate) other).cameraUpdate));
    }

    @Override
    public int hashCode() {
        return cameraUpdate.hashCode();
    }

    @Override
    public String toString() {
        return cameraUpdate.toString();
    }
}
