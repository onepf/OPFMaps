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

package org.onepf.maps.yandex;

import android.app.Fragment;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import org.onepf.opfmaps.OPFMap;
import org.onepf.opfmaps.OPFMapDelegate;
import org.onepf.opfmaps.OPFMapOptions;
import org.onepf.opfmaps.OPFOnMapClickListener;
import org.onepf.opfmaps.OPFOnMapLoadListener;
import org.onepf.opfmaps.OPFOnMarkerClickListener;
import org.onepf.opfmaps.OPFOnMarkerDragListener;
import org.onepf.opfmaps.OPFTBDInterface;
import org.onepf.opfmaps.model.OPFCircle;
import org.onepf.opfmaps.model.OPFGroundOverlay;
import org.onepf.opfmaps.model.OPFInfoWindowAdapter;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFMarker;
import org.onepf.opfmaps.model.OPFPolygon;
import org.onepf.opfmaps.model.OPFPolyline;
import org.onepf.opfmaps.model.OPFTileOverlay;

import java.util.Objects;

import ru.yandex.yandexmapkit.MapView;
import ru.yandex.yandexmapkit.OverlayManager;
import ru.yandex.yandexmapkit.overlay.Overlay;
import ru.yandex.yandexmapkit.overlay.OverlayItem;
import ru.yandex.yandexmapkit.overlay.drag.DragAndDropItem;
import ru.yandex.yandexmapkit.utils.GeoPoint;

/**
 * Created by akarimova on 09.07.15.
 */
public class OPFYandexFragment extends Fragment implements OPFMapDelegate, OPFTBDInterface<Object, Objects, Object, Overlay, GeoPoint> {
    private MapView yandexMap;

    public static OPFYandexFragment newInstance(Context context, OPFMapOptions options) {
        OPFYandexFragment opfYandexFragment = new OPFYandexFragment();
        Bundle bundle = new Bundle(1);
        opfYandexFragment.setArguments(bundle);
        return opfYandexFragment;
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void addMarker(OPFMarker opfMarker) {
        Overlay overlay = new Overlay(yandexMap.getMapController());
        GeoPoint geoPoint = latLng(opfMarker.getLatLng());
        OverlayManager overlayManager = yandexMap.getMapController().getOverlayManager();
        overlayManager.addOverlay(overlay);
        OverlayItem overlayItem;
        if (opfMarker.isDraggable()) {
            overlayItem = new DragAndDropItem(geoPoint, getResources().getDrawable(opfMarker.getIconId()));
        } else {
            overlayItem = new OverlayItem(geoPoint, getResources().getDrawable(opfMarker.getIconId()));
        }
        overlay.addOverlayItem(overlayItem);
    }

    @Override
    public void addPolyline(OPFPolyline opfPolyline) {
        //will be implemented later
    }

    @Override
    public void addCircle(OPFCircle opfCircle) {
        //will be implemented later
    }

    @Override
    public void addPolygon(OPFPolygon opfPolygon) {
        //will be implemented later
    }

    @Override
    public void zoom(float zoom) {
        //will be implemented later
    }

    @Override
    public void setInfoWindowAdapter(OPFInfoWindowAdapter adapter) {
        //will be implemented later
    }

    @Override
    public void setMyLocationEnabled(boolean enabled) {
        yandexMap.getMapController().getOverlayManager().getMyLocation().setEnabled(enabled);
    }

    @Override
    public void setTrafficEnabled(boolean enabled) {
        yandexMap.getMapController().setJamsVisible(enabled);
    }

    @Override
    public void clear() {
        //will be implemented later
    }

    @Override
    public void setMapType(OPFMap.MapType mapType) {
        //will be implemented later
    }

    @Override
    public OPFMap.MapType getMapType() {
        return null;
    }

    @Override
    public Location getMyLocation() {
        return null;
    }

    @Override
    public void addGroundOverlay(OPFGroundOverlay opfGroundOverlay) {
        //will be implemented later
    }

    @Override
    public void addTileOverlay(OPFTileOverlay opfTileOverlay) {
        //will be implemented later
    }

    @Override
    public void addPadding(int left, int top, int right, int bottom) {
        //will be implemented later
    }

    @Override
    public void setIndoorEnabled(boolean enabled) {
        Log.w(OPFYandexFragment.class.getSimpleName(), "setIndoorEnabled not supported by Yandex.Maps");
    }

    @Override
    public void setBuildingsEnabled(boolean enabled) {
        //will be implemented later
    }

    @Override
    public float getMaxZoomLevel() {
        return 0;
    }

    @Override
    public float getMinZoomLevel() {
        return 0;
    }

    @Override
    public void load(OPFOnMapLoadListener mapLoadedListener) {
        //will be implemented later
    }

    @Override
    public void setOnMarkerDragListener(OPFOnMarkerDragListener onMarkerDragListener) {
        //will be implemented later
    }

    @Override
    public void setOnMarkerClickListener(OPFOnMarkerClickListener onMarkerClickListener) {
        //will be implemented later
    }

    @Override
    public void setOnMapClickListener(OPFOnMapClickListener onMapClickListener) {
        //will be implemented later
    }

    @Override
    public Object polyline(OPFPolyline opfPolyline) {
        return null;
    }

    @Override
    public Objects polygon(OPFPolygon opfPolygon) {
        return null;
    }

    @Override
    public Object circle(OPFCircle opfCircle) {
        return null;
    }

    @Override
    public Overlay marker(OPFMarker opfMarker) {
        return null;
    }

    @Override
    public GeoPoint latLng(OPFLatLng opfLatLng) {
        return new GeoPoint(opfLatLng.getLatitude(), opfLatLng.getLongitude());
    }
}
