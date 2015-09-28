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
import android.support.annotation.NonNull;
import android.text.TextUtils;
import org.onepf.opfutils.OPFLog;

/**
 * Created by akarimova on 26.06.15.
 */
public final class MetaDataChecker {
    private MetaDataChecker() {
    }

    public static boolean dataPresented(@NonNull final Context context, @NonNull final String key) {
        if (TextUtils.isEmpty(key)) {
            throw new IllegalArgumentException("Meta data key can't be null or empty.");
        }

        try {
            final PackageInfo info = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            final Bundle metaData = info.applicationInfo.metaData;
            if (metaData != null && metaData.get(key) != null) {
                return true;
            } else {
                OPFLog.d("Meta data by key : %s is not presented.", key);
            }
        } catch (PackageManager.NameNotFoundException e) {
            OPFLog.w(e.getMessage());
        }
        return false;
    }
}
