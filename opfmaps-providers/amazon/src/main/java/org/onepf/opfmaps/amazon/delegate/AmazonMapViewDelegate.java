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

package org.onepf.opfmaps.amazon.delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import com.amazon.geo.mapsv2.AmazonMap;
import com.amazon.geo.mapsv2.AmazonMapOptions;
import com.amazon.geo.mapsv2.MapView;
import com.amazon.geo.mapsv2.OnMapReadyCallback;
import org.onepf.opfmaps.OPFMap;
import org.onepf.opfmaps.delegate.MapViewDelegate;
import org.onepf.opfmaps.listener.OPFOnMapReadyCallback;

/**
 * @author Roman Savin
 * @since 04.08.2015
 */
public class AmazonMapViewDelegate extends MapView implements MapViewDelegate {

    public AmazonMapViewDelegate(final Context context) {
        super(context);
    }

    public AmazonMapViewDelegate(final Context context, final AmazonMapOptions options) {
        super(context, options);
    }

    @Override
    public void getMapAsync(@NonNull final OPFOnMapReadyCallback callback) {
        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final AmazonMap amazonMap) {
                callback.onMapReady(new OPFMap(new AmazonMapDelegate(amazonMap)));
            }
        });
    }
}
