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

package org.onepf.maps.osmdroid.overlay;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;

import org.onepf.maps.osmdroid.R;
import org.osmdroid.bonuspack.overlays.InfoWindow;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Overlay;

import java.util.List;

/**
 * @author Roman Savin
 * @since 26.08.2015
 */
public class MarkerInfoWindow extends InfoWindow {

    public MarkerInfoWindow(@NonNull final View view,
                            @NonNull final MapView mapView) {
        this(view, mapView, false);
    }

    public MarkerInfoWindow(@NonNull final View view,
                            @NonNull final MapView mapView,
                            final boolean isInfoContent) {
        super(R.layout.info_window_container, mapView);
        if (isInfoContent) {
            ((FrameLayout) mView).addView(view);
        } else {
            mView = view;
        }
    }

    @Override
    public void onOpen(final Object item) {
        closeAllInfoWindows(mMapView);
    }

    @Override
    public void onClose() {
        //nothing
    }

    public static void closeAllInfoWindows(@NonNull final MapView mapView) {
        final List<Overlay> overlayList = mapView.getOverlays();
        for (Overlay overlay : overlayList) {
            if (overlay instanceof Marker) {
                ((Marker) overlay).closeInfoWindow();
            }
        }
    }
}
