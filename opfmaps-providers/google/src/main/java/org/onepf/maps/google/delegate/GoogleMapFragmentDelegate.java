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

package org.onepf.maps.google.delegate;

import android.support.annotation.NonNull;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import org.onepf.opfmaps.OPFMap;
import org.onepf.opfmaps.delegate.MapFragmentDelegate;
import org.onepf.opfmaps.listener.OPFOnMapReadyCallback;

/**
 * @author Roman Savin
 * @since 31.07.2015
 */
public class GoogleMapFragmentDelegate extends SupportMapFragment implements MapFragmentDelegate {

    public static GoogleMapFragmentDelegate newInstance() {
        return new GoogleMapFragmentDelegate();
    }

    @Override
    public void getMapAsync(@NonNull final OPFOnMapReadyCallback callback) {
        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                callback.onMapReady(new OPFMap(new GoogleMapDelegate(googleMap)));
            }
        });
    }
}
