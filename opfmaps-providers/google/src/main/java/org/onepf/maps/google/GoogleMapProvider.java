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

package org.onepf.maps.google;

import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMapOptions;
import org.onepf.opfmaps.OPFAbstractMapProvider;
import org.onepf.opfmaps.OPFMapOptions;
import org.onepf.opfmaps.utils.FeatureChecker;
import org.onepf.opfmaps.utils.MetaDataChecker;
import org.onepf.opfmaps.utils.PermissionChecker;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * Created by akarimova on 24.06.15.
 */
public class GoogleMapProvider extends OPFAbstractMapProvider {
    @Override
    public boolean hasRequiredPermissions(Context context) {
        return
                PermissionChecker.permissionRequested(context, INTERNET)
                        && PermissionChecker.permissionRequested(context, ACCESS_NETWORK_STATE)
                        && PermissionChecker.permissionRequested(context, WRITE_EXTERNAL_STORAGE)
                        && (PermissionChecker.permissionRequested(context, ACCESS_COARSE_LOCATION)
                        || PermissionChecker.permissionRequested(context, ACCESS_FINE_LOCATION));
    }

    @Override
    public boolean isAvailable(Context context) {
        //todo to prevent sdk kits check for a permission in the manifest
        int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        return googlePlayServicesAvailable == ConnectionResult.SUCCESS; //todo think about other codes
    }

    @NonNull
    @Override
    public Fragment getFragment(OPFMapOptions opfMapOptions) {
        return OPFGoogleFragment.newInstance(getConvert(opfMapOptions));
    }

    private GoogleMapOptions getConvert(OPFMapOptions mapOptions) {
        GoogleMapOptions googleMapOptions = new GoogleMapOptions();
        googleMapOptions.rotateGesturesEnabled(mapOptions.isRotateGesturesEnabled())
                .compassEnabled(mapOptions.isCompassEnabled())
                .tiltGesturesEnabled(mapOptions.isTiltGesturesEnabled())
                .zoomGesturesEnabled(mapOptions.isZoomGesturesEnabled());
        return googleMapOptions;
    }


    @Override
    public boolean isKeyPresented(Context context) {
        //todo look for a constant
        return MetaDataChecker.dataPresented(context, "com.google.android.maps.v2.API_KEY");
    }

    @Override
    public boolean hasRequestedFeatures(Context context) {
        //CHECKSTYLE:OFF
        return FeatureChecker.hasRequestedFeature(context, "tbd", 0x00020000);
        //CHECKSTYLE:ON
    }

}
