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

package org.onepf.maps.yandex;

import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;

import org.onepf.opfmaps.BaseOPFMapProvider;
import org.onepf.opfmaps.OPFMapOptions;

/**
 * Created by akarimova on 09.07.15.
 */
public class OPFYandexProvider extends BaseOPFMapProvider {
    @NonNull
    @Override
    public Fragment getFragment(OPFMapOptions opfMapOptions) {
        return null;
    }

    @Override
    public boolean hasRequiredPermissions(Context context) {
        return false;
    }

    @Override
    public boolean isAvailable(Context context) {
        return false;
    }

    @Override
    public boolean isKeyPresented(Context context) {
        return false;
    }

    @Override
    public boolean hasRequestedFeatures(Context context) {
        return false;
    }
}
