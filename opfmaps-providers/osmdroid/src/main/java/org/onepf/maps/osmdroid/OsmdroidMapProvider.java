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

package org.onepf.maps.osmdroid;

import android.content.Context;
import android.support.annotation.NonNull;
import org.onepf.opfmaps.BaseOPFMapProvider;
import org.onepf.opfmaps.factory.DelegatesAbstractFactory;

/**
 * @author Roman Savin
 * @since 12.08.2015
 */
public final class OsmdroidMapProvider extends BaseOPFMapProvider {

    @NonNull
    @Override
    public DelegatesAbstractFactory getDelegatesFactory() {
        return new OsmdroidDelegatesFactory();
    }

    @NonNull
    @Override
    public String getHostAppPackage() {
        //todo return package
        return "";
    }

    @Override
    public boolean hasRequiredPermissions(final Context context) {
        //todo add checks
        return true;
    }

    @Override
    public boolean isAvailable(final Context context) {
        //todo add checks
        return true;
    }

    @Override
    public boolean isKeyPresented(final Context context) {
        //todo add checks
        return true;
    }

    @Override
    public boolean hasRequestedFeatures(final Context context) {
        //todo add checks
        return true;
    }
}
