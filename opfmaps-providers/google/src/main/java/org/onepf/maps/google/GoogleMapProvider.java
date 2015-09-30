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

import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import org.onepf.opfmaps.BaseOPFMapProvider;
import org.onepf.opfmaps.factory.DelegatesAbstractFactory;
import org.onepf.opfutils.OPFChecks;
import org.onepf.opfutils.OPFLog;

/**
 * Google Map Provider.
 *
 * @author Anastasiia Karimova
 * @since 24.06.2015
 */
public class GoogleMapProvider extends BaseOPFMapProvider {

    private static final String HOST_APP_PACKAGE = "com.android.vending";

    public GoogleMapProvider() {
        super(GoogleMapProvider.class.getSimpleName(), HOST_APP_PACKAGE);
    }

    @NonNull
    @Override
    public DelegatesAbstractFactory getDelegatesFactory() {
        return new GoogleDelegatesFactory();
    }

    @Override
    public boolean isAvailable(@NonNull final Context context) {
        if (super.isAvailable(context)) {
            final int conResult = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
            if (conResult == ConnectionResult.SUCCESS) {
                return true;
            } else {
                OPFLog.d("Google Play Services not available. Reason: '%s'.", GooglePlayServicesUtil.getErrorString(conResult));
            }
        }
        return false;
    }

    @Override
    public boolean isKeyPresented(@NonNull final Context context) {
        return OPFChecks.hasMetadata(context, "com.google.android.maps.v2.API_KEY");
    }
}
