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

package org.onepf.opfmaps.utils;

import android.content.Context;

/**
 * Created by akarimova on 02.07.15.
 */
public final class FeatureChecker {
    private FeatureChecker() {
    }

    public static boolean hasRequestedFeature(Context context, String feature, int version) {
//        if (TextUtils.isEmpty(feature)) {
//            throw new IllegalArgumentException("Feature can't be null or empty.");
//        }

//        try {
//            final PackageInfo info = context.getPackageManager()
//                    .getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);
//
//            FeatureGroupInfo[] featureGroups = info.featureGroups;
//            for (FeatureGroupInfo featureGroupInfo : featureGroups) {
//                FeatureInfo[] features = featureGroupInfo.features;
//                for (FeatureInfo featureInfo : features) {
//                    if (featureInfo.name.equals(feature)) {
//                        return false;
//                    }
//                }
//            }
//
//        } catch (PackageManager.NameNotFoundException ignored) {
//        }
//        return false;
        return true;
    }
}
