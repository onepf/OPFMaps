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

package org.onepf.maps.osmdroid.delegate;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import org.onepf.maps.osmdroid.model.OsmdroidMapOptions;
import org.onepf.opfmaps.delegate.MapViewDelegate;
import org.onepf.opfmaps.listener.OPFOnMapReadyCallback;
import org.osmdroid.views.MapView;

/**
 * @author Roman Savin
 * @since 04.08.2015
 */
public class OsmdroidMapViewDelegate extends MapView implements MapViewDelegate {

    public OsmdroidMapViewDelegate(final Context context) {
        super(context, 256);
    }

    public OsmdroidMapViewDelegate(final Context context, final OsmdroidMapOptions options) {
        super(context, 256);
        //todo set options
    }

    @Override
    public void getMapAsync(@NonNull final OPFOnMapReadyCallback callback) {
        //todo implement
        /*getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                callback.onMapReady(new OPFMap(new OsmdroidMapDelegate(googleMap)));
            }
        });*/
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        //todo implement
    }

    @Override
    public void onResume() {
        //todo implement
    }

    @Override
    public void onPause() {
        //todo implement
    }

    @Override
    public void onDestroy() {
        //todo implement
    }

    @Override
    public void onSaveInstanceState(@Nullable final Bundle outState) {
        //todo implement
    }

    @Override
    public void onLowMemory() {
        //todo implement
    }
}
