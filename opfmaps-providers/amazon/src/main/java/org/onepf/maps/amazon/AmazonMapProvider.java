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

package org.onepf.maps.amazon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.amazon.geo.mapsv2.util.AmazonMapsRuntimeUtil;
import com.amazon.geo.mapsv2.util.ConnectionResult;

import org.onepf.opfmaps.BaseOPFMapProvider;
import org.onepf.opfmaps.factory.DelegatesAbstractFactory;
import org.onepf.opfmaps.utils.PermissionChecker;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * Created by akarimova on 24.06.15.
 */
public class AmazonMapProvider extends BaseOPFMapProvider {
    private static final String TAG = AmazonMapProvider.class.getSimpleName();

    @NonNull
    @Override
    public DelegatesAbstractFactory getDelegatesFactory() {
        return new AmazonDelegatesFactory();
    }

    @NonNull
    @Override
    public String getHostAppPackage() {
        //todo return host app package
        return "";
    }

    @Override
    public boolean hasRequiredPermissions(Context context) {
        return
                PermissionChecker.permissionRequested(context, INTERNET)
                        && PermissionChecker.permissionRequested(context, ACCESS_NETWORK_STATE)
                        && PermissionChecker.permissionRequested(context, WRITE_EXTERNAL_STORAGE)
                        && PermissionChecker.permissionRequested(context, ACCESS_COARSE_LOCATION)
                        && PermissionChecker.permissionRequested(context, ACCESS_FINE_LOCATION);
    }

    @Override
    public boolean isAvailable(Context context) {
        int amazonMapsRuntimeAvailable = AmazonMapsRuntimeUtil
                .isAmazonMapsRuntimeAvailable(context);
        if (amazonMapsRuntimeAvailable == ConnectionResult.SUCCESS) {
            Log.d(TAG, "Amazon maps are available");
            return true;
        } else {
            Log.w(TAG, "Connection result = " + amazonMapsRuntimeAvailable);
            return false;
        }
    }

    @Override
    public boolean isKeyPresented(Context context) {
        //always true
        return true;
    }

    @Override
    public boolean hasRequestedFeatures(Context context) {
        //always true
        return true;
    }
}
