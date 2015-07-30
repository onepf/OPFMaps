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
import android.location.Location;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import org.onepf.opfmaps.listener.OPFOnMapClickListener;
import org.onepf.opfmaps.listener.OPFOnMarkerClickListener;
import org.onepf.opfmaps.listener.OPFOnMarkerDragListener;
import org.onepf.opfmaps.model.OPFCircle;
import org.onepf.opfmaps.model.OPFGroundOverlay;
import org.onepf.opfmaps.model.OPFInfoWindowAdapter;
import org.onepf.opfmaps.model.OPFMarker;
import org.onepf.opfmaps.model.OPFPolygon;
import org.onepf.opfmaps.model.OPFPolyline;
import org.onepf.opfmaps.model.OPFTileOverlay;

import java.util.List;


/**
 * Created by akarimova on 08.06.15.
 */

//todo remove
public class OPFMap extends FrameLayout implements OPFMapDelegate, OPFOnMapLoadListener {
    public enum MapType {
        HYBRID,
        NONE,
        NORMAL,
        SATELLITE,
        TERRAIN,
        UNKNOWN
    }

    public enum InitializationErrors {
        PROVIDER_LIST_EMPTY
    }

    private OPFMapDelegate mapDelegate;
    private OPFMapProvider currentProvider;


    private OPFOnMapLoadListener onMapLoadListener;

    @SuppressWarnings("PMD.SingularField") //TODO: fix this PMD issue
    private OPFOnMapConfigureListener onMapConfigureListener;

    private Boolean isReady;

    public OPFMap(Context context) {
        super(context);
        setupView();
    }

    public OPFMap(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView();
    }

    public OPFMap(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView();
    }

    private void setupView() {
        LayoutInflater.from(getContext()).inflate(R.layout.map_view, this);
    }


    public void init(android.app.FragmentManager fragmentManager,
                     OPFMapSettings mapSettings,
                     OPFOnMapConfigureListener listener, //TODO: add @NonNull annotation
                     OPFMapOptions opfMapOptions) {
        this.onMapConfigureListener = listener;
        List<OPFMapProvider> providers = mapSettings.getProviders();
        if (providers == null || providers.isEmpty()) { //TODO: it's impossible case
            if (onMapConfigureListener != null) {
                onMapConfigureListener.onError(0); //empty provider list //TODO: add enum or constants of errors
                onMapConfigureListener = null;
            }
            return;
        }
        currentProvider = findBestProvider(getContext(), providers);

        if (currentProvider != null) {
            android.app.Fragment fragment = currentProvider.getFragment(opfMapOptions);
            this.mapDelegate = (OPFMapDelegate) fragment;
            fragmentManager.beginTransaction()
                    .replace(getId(), (android.app.Fragment) this.mapDelegate)
                    .commit();
            fragmentManager.executePendingTransactions();
            onMapConfigureListener.onConfigure(currentProvider);
        } else {
            onMapConfigureListener.onError(1); //provider not found //TODO: add enum or constants of errors
        }
    }

    private static OPFMapProvider findBestProvider(Context context, List<OPFMapProvider> providers) {
        for (OPFMapProvider mapProvider : providers) {
            if (mapProvider.isAvailable(context) && mapProvider.hasRequiredPermissions(context)
                    && mapProvider.isKeyPresented(context) && mapProvider.hasRequestedFeatures(context)) {
                return mapProvider;
            }
        }
        return null;
    }

    public OPFMapProvider getCurrentProvider() {
        return currentProvider;
    }

    public void setMyLocationEnabled(boolean enabled) {
        checkInitialization();
        mapDelegate.setMyLocationEnabled(enabled);
    }

    @Override
    public void setTrafficEnabled(boolean enabled) {
        checkInitialization();
        mapDelegate.setMyLocationEnabled(enabled);
    }

    @Override
    public void clear() {
        checkInitialization();
        mapDelegate.clear();
    }

    @Override
    public void setMapType(MapType mapType) {
        checkInitialization();
        mapDelegate.setMapType(mapType);
    }

    @Override
    public MapType getMapType() {
        checkInitialization();
        return mapDelegate.getMapType();
    }

    @Override
    public Location getMyLocation() {
        checkInitialization();
        return mapDelegate.getMyLocation();
    }


    @Override
    public void addGroundOverlay(OPFGroundOverlay opfGroundOverlay) {
        checkInitialization();

    }

    @Override
    public void addTileOverlay(OPFTileOverlay opfTileOverlay) {
        checkInitialization();

    }

    @Override
    public void addPadding(int left, int top, int right, int bottom) {
        checkInitialization();

    }

    @Override
    public void setIndoorEnabled(boolean enabled) {
        checkInitialization();
        mapDelegate.setIndoorEnabled(enabled);
    }

    @Override
    public void setBuildingsEnabled(boolean enabled) {
        checkInitialization();

    }

    @Override
    public float getMaxZoomLevel() {
        checkInitialization();
        return mapDelegate.getMaxZoomLevel();
    }

    @Override
    public float getMinZoomLevel() {
        checkInitialization();
        return mapDelegate.getMinZoomLevel();
    }

    @Override
    public void load(OPFOnMapLoadListener mapLoadedListener) {
        this.onMapLoadListener = mapLoadedListener;
        this.mapDelegate.load(this);
    }

    @Override
    public void setOnMarkerDragListener(OPFOnMarkerDragListener onMarkerDragListener) {
        checkInitialization();
        this.mapDelegate.setOnMarkerDragListener(onMarkerDragListener);
    }

    @Override
    public void setOnMarkerClickListener(OPFOnMarkerClickListener onMarkerClickListener) {
        checkInitialization();
        this.mapDelegate.setOnMarkerClickListener(onMarkerClickListener);
    }

    @Override
    public void setOnMapClickListener(OPFOnMapClickListener onMapClickListener) {
        checkInitialization();
        this.mapDelegate.setOnMapClickListener(onMapClickListener);
    }

    @Override
    public boolean isReady() {
        checkInitialization();
        //todo add conditions!!!
        return mapDelegate != null;
    }

    public void addMarker(OPFMarker marker) {
        checkInitialization();
        mapDelegate.addMarker(marker);
    }

    @Override
    public void addPolyline(OPFPolyline opfPolyline) {
        checkInitialization();
        mapDelegate.addPolyline(opfPolyline);
    }

    public void addCircle(OPFCircle circle) {
        checkInitialization();
        mapDelegate.addCircle(circle);
    }

    @Override
    public void addPolygon(OPFPolygon opfPolygon) {
        checkInitialization();
        mapDelegate.addPolygon(opfPolygon);
    }

    @Override
    public void zoom(float zoom) {
        checkInitialization();
        mapDelegate.zoom(zoom);
    }

    @Override
    public void setInfoWindowAdapter(OPFInfoWindowAdapter adapter) {
        checkInitialization();
        mapDelegate.setInfoWindowAdapter(adapter);
    }

    private void checkInitialization() {
        if (isReady == null) {
            throw new IllegalStateException("Was not initialized.");

        } else if (!isReady) {
            throw new IllegalStateException("Initialized with error.");
        }
    }


    @Override
    public void onMapLoad() {
        isReady = true;
        if (onMapLoadListener != null) {
            onMapLoadListener.onMapLoad();
        }
    }

    @Override
    public void onError() {
        isReady = false;
        if (onMapLoadListener != null) {
            onMapLoadListener.onError();
        }
    }

}
