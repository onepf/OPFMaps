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

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.onepf.opfmaps.OPFMap;
import org.onepf.opfmaps.OPFMapFragment;
import org.onepf.opfmaps.listener.OPFOnCameraChangeListener;
import org.onepf.opfmaps.listener.OPFOnInfoWindowClickListener;
import org.onepf.opfmaps.listener.OPFOnMapClickListener;
import org.onepf.opfmaps.listener.OPFOnMapLongClickListener;
import org.onepf.opfmaps.listener.OPFOnMapReadyCallback;
import org.onepf.opfmaps.listener.OPFOnMarkerClickListener;
import org.onepf.opfmaps.listener.OPFOnMarkerDragListener;
import org.onepf.opfmaps.listener.OPFOnMyLocationButtonClickListener;
import org.onepf.opfmaps.model.OPFBitmapDescriptorFactory;
import org.onepf.opfmaps.model.OPFCameraPosition;
import org.onepf.opfmaps.model.OPFCircleOptions;
import org.onepf.opfmaps.model.OPFInfoWindowAdapter;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFMarker;
import org.onepf.opfmaps.model.OPFMarkerOptions;
import org.onepf.opfutils.OPFLog;

public class MainActivity extends Activity implements OPFOnMapReadyCallback {

    //private OPFMapView mapView;

    @SuppressWarnings("PMD.ExcessiveMethodLength")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OPFLog.logMethod(savedInstanceState);

        /*mapView = (OPFMapView) findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);*/
        final OPFMapFragment mapFragment = (OPFMapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        OPFLog.logMethod();
    }

    @Override
    protected void onResume() {
        super.onResume();
        OPFLog.logMethod();
        //mapView.onResume();
    }

    /*@Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }*/

    //CHECKSTYLE:OFF
    @Override
    public void onMapReady(@NonNull final OPFMap opfMap) {
        OPFLog.logMethod(opfMap);

        opfMap.setMyLocationEnabled(true);
        opfMap.setBuildingsEnabled(true);

        opfMap.setOnMyLocationButtonClickListener(new OPFOnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                Toast.makeText(MainActivity.this, "My location click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //circle
        opfMap.addCircle(new OPFCircleOptions()
                .center(new OPFLatLng(55.752004, 37.617017))
                .radius(100000.0)
                .fillColor(Color.CYAN)
                .strokeColor(Color.BLUE));

        //markers
        opfMap.addMarker(new OPFMarkerOptions()
                .visible(true)
                .position(new OPFLatLng(37.773975, -122.40205))
                .title("marker #1")
                .snippet("snippet #1")
                .icon(OPFBitmapDescriptorFactory.defaultMarker(OPFBitmapDescriptorFactory.HUE_AZURE))
                .draggable(true));

        opfMap.addMarker(new OPFMarkerOptions()
                .visible(true)
                .position(new OPFLatLng(55.752004, -122.40205))
                .title("marker #3")
                .snippet("snippet #3")
                .icon(OPFBitmapDescriptorFactory.defaultMarker(OPFBitmapDescriptorFactory.HUE_YELLOW))
                .draggable(true));

        opfMap.addMarker(new OPFMarkerOptions()
                .visible(true)
                .position(new OPFLatLng(55.752004, 37.617017))
                .title("marker #2")
                .snippet("snippet #2")
                .icon(OPFBitmapDescriptorFactory.defaultMarker())
                .draggable(true));

        opfMap.setOnMarkerDragListener(new OPFOnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(@NonNull final OPFMarker marker) {
                OPFLog.logMethod(marker);
            }

            @Override
            public void onMarkerDrag(@NonNull final OPFMarker marker) {
                OPFLog.logMethod(marker);
            }

            @Override
            public void onMarkerDragEnd(@NonNull final OPFMarker marker) {
                OPFLog.logMethod(marker);
            }
        });

        opfMap.setOnMarkerClickListener(new OPFOnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull final OPFMarker marker) {
                OPFLog.logMethod(marker);
                Toast.makeText(MainActivity.this, "Marker click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        opfMap.setOnMapClickListener(new OPFOnMapClickListener() {
            @Override
            public void onMapClick(@NonNull final OPFLatLng latLng) {
                OPFLog.logMethod(latLng);
                Toast.makeText(MainActivity.this, "Map click position : " + latLng, Toast.LENGTH_SHORT).show();
            }
        });

        opfMap.setOnMapLongClickListener(new OPFOnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull final OPFLatLng latLng) {
                OPFLog.logMethod(latLng);
                Toast.makeText(MainActivity.this, "Map long click position : " + latLng, Toast.LENGTH_SHORT).show();
            }
        });

        opfMap.setOnInfoWindowClickListener(new OPFOnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(@NonNull final OPFMarker marker) {
                OPFLog.logMethod(marker);
                Toast.makeText(MainActivity.this, "Info window click : " + marker, Toast.LENGTH_SHORT).show();
            }
        });

        opfMap.setInfoWindowAdapter(new OPFInfoWindowAdapter() {
            @Override
            public View getInfoWindow(@NonNull final OPFMarker marker) {
                if (!marker.getSnippet().equals("snippet #2")) {
                    return null;
                }

                //noinspection InflateParams
                final View inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.info_window, null);
                final TextView title = (TextView) inflate.findViewById(R.id.title);
                title.setText(marker.getTitle());
                final TextView snippet = (TextView) inflate.findViewById(R.id.snippet);
                snippet.setText(marker.getSnippet());
                return inflate;
            }

            @Override
            public View getInfoContents(@NonNull final OPFMarker marker) {
                if (!marker.getSnippet().equals("snippet #1")) {
                    return null;
                }

                final View inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.info_window, null);
                final TextView title = (TextView) inflate.findViewById(R.id.title);
                title.setText(marker.getTitle());
                final TextView snippet = (TextView) inflate.findViewById(R.id.snippet);
                snippet.setText(marker.getSnippet());
                return inflate;
            }
        });

        opfMap.setOnCameraChangeListener(new OPFOnCameraChangeListener() {
            @Override
            public void onCameraChange(@NonNull final OPFCameraPosition position) {
                OPFLog.logMethod(position);
            }
        });
    }
    //CHECKSTYLE:ON
}
