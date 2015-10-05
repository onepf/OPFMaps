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

package org.onepf.opfmaps;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import org.onepf.opfutils.OPFLog;
import org.onepf.opfutils.OPFUtils;

/**
 * @author Roman Savin
 * @since 24.06.2015
 */
public abstract class BaseOPFMapProvider implements OPFMapProvider {

    @NonNull
    private final String name;

    @Nullable
    private final String hostAppPackage;

    protected BaseOPFMapProvider(@NonNull final String name,
                                 @Nullable final String hostAppPackage) {
        this.name = name;
        this.hostAppPackage = hostAppPackage;
    }

    @Override
    public boolean isAvailable(@NonNull final Context context) {
        if (hostAppPackage != null) {
            final boolean isInstalled = OPFUtils.isInstalled(context, hostAppPackage);
            if (!isInstalled) {
                OPFLog.d("Host app package : %s of provider %s isn't installed", hostAppPackage, name);
            }
            return isInstalled;
        }
        return true;
    }

    @NonNull
    @Override
    public String getName() {
        return name;
    }

    @Nullable
    @Override
    public String getHostAppPackage() {
        return hostAppPackage;
    }

    @Override
    public boolean isKeyPresented(@NonNull final Context context) {
        return true;
    }
}
