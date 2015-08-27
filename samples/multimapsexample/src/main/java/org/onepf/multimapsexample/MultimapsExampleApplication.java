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

package org.onepf.multimapsexample;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import org.onepf.maps.amazon.AmazonMapProvider;
import org.onepf.maps.google.GoogleMapProvider;
import org.onepf.maps.osmdroid.OsmdroidMapProvider;
import org.onepf.opfmaps.OPFMapConfiguration;
import org.onepf.opfmaps.OPFMapHelper;
import org.onepf.opfutils.OPFLog;

/**
 * @author Roman Savin
 * @since 03.08.2015
 */
public class MultimapsExampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);

        OPFLog.setEnabled(BuildConfig.DEBUG, true);
        final OPFMapConfiguration configuration = new OPFMapConfiguration.Builder()
                .addProviders(new OsmdroidMapProvider(), new GoogleMapProvider(), new AmazonMapProvider())
                .setSelectSystemPreferred(true)
                .build();
        OPFMapHelper.getInstance().init(this, configuration);
    }
}
