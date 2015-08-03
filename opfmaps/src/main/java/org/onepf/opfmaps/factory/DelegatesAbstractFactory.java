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

package org.onepf.opfmaps.factory;

import android.support.annotation.NonNull;
import org.onepf.opfmaps.delegate.MapFragmentDelegate;
import org.onepf.opfmaps.delegate.model.BitmapDescriptorFactoryDelegate;
import org.onepf.opfmaps.delegate.model.CircleOptionsDelegate;
import org.onepf.opfmaps.delegate.model.GroundOverlayOptionsDelegate;
import org.onepf.opfmaps.delegate.model.LatLngBoundsDelegate;
import org.onepf.opfmaps.delegate.model.LatLngDelegate;
import org.onepf.opfmaps.delegate.model.MarkerOptionsDelegate;
import org.onepf.opfmaps.delegate.model.PolygonOptionsDelegate;
import org.onepf.opfmaps.delegate.model.PolylineOptionsDelegate;
import org.onepf.opfmaps.delegate.model.TileDelegate;
import org.onepf.opfmaps.delegate.model.TileOverlayOptionsDelegate;
import org.onepf.opfmaps.delegate.model.UrlTileProviderDelegate;
import org.onepf.opfmaps.model.OPFLatLng;

/**
 * @author Roman Savin
 * @since 30.07.2015
 */
public abstract class DelegatesAbstractFactory {

    @NonNull
    public abstract MapFragmentDelegate createMapFragmentDelegate();

    @NonNull
    public abstract CircleOptionsDelegate createCircleOptionsDelegate();

    @NonNull
    public abstract GroundOverlayOptionsDelegate createGroundOverlayOptionsDelegate();

    @NonNull
    public abstract LatLngDelegate createLatLngDelegate(final double latitude, final double longitude);

    @NonNull
    public abstract LatLngBoundsDelegate createLatLngBoundsDelegate(@NonNull final OPFLatLng southwest,
                                                                    @NonNull final OPFLatLng northeast);

    @NonNull
    public abstract LatLngBoundsDelegate.Builder createLatLngBoundsBuilderDelegate();

    @NonNull
    public abstract MarkerOptionsDelegate createMarkerOptionsDelegate();

    @NonNull
    public abstract PolygonOptionsDelegate createPolygonOptionsDelegate();

    @NonNull
    public abstract PolylineOptionsDelegate createPolylineOptionsDelegate();

    @NonNull
    public abstract TileDelegate createTileDelegate(final int width, final int height, @NonNull final byte[] data);

    @NonNull
    public abstract TileOverlayOptionsDelegate createTileOverlayOptionDelegate();

    @NonNull
    public abstract UrlTileProviderDelegate createUrlTileProviderDelegate(final int width, final int height);

    @NonNull
    public abstract BitmapDescriptorFactoryDelegate createBitmapDescriptorFactory();
}
