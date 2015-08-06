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

package org.onepf.maps.amazon.delegate.model;

import android.support.annotation.NonNull;
import com.amazon.geo.mapsv2.CameraUpdate;
import org.onepf.opfmaps.delegate.model.CameraUpdateDelegate;

/**
 * @author Roman Savin
 * @since 06.08.2015
 */
public final class AmazonCameraUpdateDelegate implements CameraUpdateDelegate<CameraUpdate> {

    @NonNull
    private final CameraUpdate cameraUpdate;

    public AmazonCameraUpdateDelegate(@NonNull final CameraUpdate cameraUpdate) {
        this.cameraUpdate = cameraUpdate;
    }

    @NonNull
    @Override
    public CameraUpdate getCameraUpdate() {
        return cameraUpdate;
    }

    //CHECKSTYLE:OFF
    @SuppressWarnings("PMD.IfStmtsMustUseBraces")
    @Override
    public boolean equals(final Object other) {
        if (other == null) return false;
        if (other == this) return true;
        //noinspection SimplifiableIfStatement
        if (!(other instanceof AmazonCameraUpdateDelegate)) return false;

        return cameraUpdate.equals(((AmazonCameraUpdateDelegate) other).cameraUpdate);
    }
    //CHECKSTYLE:ON

    @Override
    public int hashCode() {
        return cameraUpdate.hashCode();
    }

    @Override
    public String toString() {
        return cameraUpdate.toString();
    }
}