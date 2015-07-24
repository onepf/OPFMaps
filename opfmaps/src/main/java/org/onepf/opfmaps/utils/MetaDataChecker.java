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
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;

/**
 * Created by akarimova on 26.06.15.
 */
public class MetaDataChecker {
    private MetaDataChecker() {

    }

    public static boolean dataPresented(Context context, String key) {
        return MetaDataChecker.dataPresented(context, key, null);
    }


    //todo process the pattern
    private static boolean dataPresented(Context context, String key, String pattern) {
        if (TextUtils.isEmpty(key)) {
            throw new IllegalArgumentException("Meta data key can't be null or empty.");
        }

        try {
            final PackageInfo info = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle metaData = info.applicationInfo.metaData;
            if (metaData != null) {
                Object metaObject = metaData.get(key);
                if (metaObject != null) {
                    if (pattern != null) {
                      //todo
                    } else {
                        return true;
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return false;
    }
}
