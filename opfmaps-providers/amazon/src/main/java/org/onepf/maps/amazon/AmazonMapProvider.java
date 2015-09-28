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
import android.os.Build;
import android.support.annotation.NonNull;
import com.amazon.geo.mapsv2.util.AmazonMapsRuntimeUtil;
import com.amazon.geo.mapsv2.util.ConnectionResult;
import org.onepf.opfmaps.BaseOPFMapProvider;
import org.onepf.opfmaps.factory.DelegatesAbstractFactory;
import org.onepf.opfutils.OPFLog;

/**
 * @author Anastasiia Karimova
 * @since 24.06.2015
 */
public class AmazonMapProvider extends BaseOPFMapProvider {

    private static final String HOST_APP_PACKAGE = "com.amazon.venezia";
    private static final String AMAZON_MANUFACTURER = "Amazon";

    public AmazonMapProvider() {
        super(AmazonMapProvider.class.getSimpleName(), HOST_APP_PACKAGE);
    }

    @NonNull
    @Override
    public DelegatesAbstractFactory getDelegatesFactory() {
        return new AmazonDelegatesFactory();
    }

    @Override
    public boolean isAvailable(@NonNull final Context context) {
        if (super.isAvailable(context)) {
            int availabilityResult = AmazonMapsRuntimeUtil.isAmazonMapsRuntimeAvailable(context);
            if (availabilityResult == ConnectionResult.SUCCESS) {
                return Build.MANUFACTURER.equals(AMAZON_MANUFACTURER);
            } else {
                OPFLog.d(AmazonMapsRuntimeUtil.getErrorString(availabilityResult));
            }
        }
        return false;
    }
}
