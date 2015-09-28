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
import android.graphics.Point;
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
import org.onepf.opfmaps.model.OPFGroundOverlayOptions;
import org.onepf.opfmaps.model.OPFInfoWindowAdapter;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFMarker;
import org.onepf.opfmaps.model.OPFMarkerOptions;
import org.onepf.opfmaps.model.OPFPolygonOptions;
import org.onepf.opfmaps.model.OPFPolylineOptions;
import org.onepf.opfutils.OPFLog;

import java.util.Arrays;

public class MainActivity extends Activity implements OPFOnMapReadyCallback {

    @SuppressWarnings("PMD.ExcessiveMethodLength")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OPFLog.logMethod(savedInstanceState);

        final OPFMapFragment mapFragment = (OPFMapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

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

        initMarkers(opfMap);
        addCircle(opfMap);
        addPolygon(opfMap);
        addPolyline(opfMap);
        addGroundOverlay(opfMap);

        opfMap.setOnMapClickListener(new OPFOnMapClickListener() {
            @Override
            public void onMapClick(@NonNull final OPFLatLng latLng) {
                OPFLog.logMethod(latLng);
                Toast.makeText(MainActivity.this, "Map click position : " + latLng, Toast.LENGTH_SHORT).show();
                final Point point = opfMap.getProjection().toScreenLocation(latLng);
                OPFLog.d("clickX = %s, clickY = %s", point.x, point.y);
            }
        });

        opfMap.setOnMapLongClickListener(new OPFOnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull final OPFLatLng latLng) {
                OPFLog.logMethod(latLng);
                Toast.makeText(MainActivity.this, "Map long click position : " + latLng, Toast.LENGTH_SHORT).show();
                opfMap.addMarker(new OPFMarkerOptions().position(latLng));
            }
        });

        opfMap.setOnCameraChangeListener(new OPFOnCameraChangeListener() {
            @Override
            public void onCameraChange(@NonNull final OPFCameraPosition position) {
                OPFLog.logMethod(position);
            }
        });
    }

    private void addGroundOverlay(@NonNull final OPFMap opfMap) {
        opfMap.addGroundOverlay(new OPFGroundOverlayOptions()
                .image(OPFBitmapDescriptorFactory.fromAsset("doge.png"))
                .position(new OPFLatLng(55.752004, 37.617017), 5000000, 5000000));
    }

    private void addPolyline(@NonNull final OPFMap opfMap) {
        opfMap.addPolyline(new OPFPolylineOptions().add(
                new OPFLatLng(55.69661394384935, 36.608256084788515),
                new OPFLatLng(55.08224581388055, 38.05120657972254),
                new OPFLatLng(55.830126194643654, 38.90446672723731)
        ).color(Color.BLACK).width(3.0f).zIndex(1.0f));
    }

    private void addPolygon(@NonNull final OPFMap opfMap) {
        opfMap.addPolygon(new OPFPolygonOptions()
                        .add(
                                new OPFLatLng(37.773975, -122.40205),
                                new OPFLatLng(55.752004, -122.40205),
                                new OPFLatLng(55.752004, 37.617017)
                        ).addHole(
                                Arrays.asList(
                                        new OPFLatLng(52.83153846941036, -104.87376610724503),
                                        new OPFLatLng(44.760517600395474, -105.87971035044585),
                                        new OPFLatLng(54.35459330167125, -84.07064501079975)
                                )
                        ).addHole(
                                Arrays.asList(
                                        new OPFLatLng(51.29171452642614, -71.25262498180511),
                                        new OPFLatLng(50.836485416038634, -69.86148826228197),
                                        new OPFLatLng(52.71736429812524, -64.15429716201099)
                                )
                        ).fillColor(Color.RED).strokeColor(Color.GREEN).strokeWidth(5).zIndex(3)
        );
    }

    private void initMarkers(@NonNull final OPFMap opfMap) {
        final OPFMarker marker1 = opfMap.addMarker(new OPFMarkerOptions()
                .visible(true)
                .position(new OPFLatLng(37.773975, -122.40205))
                .title("marker #1")
                .snippet("snippet #1")
                .icon(OPFBitmapDescriptorFactory.defaultMarker(OPFBitmapDescriptorFactory.HUE_AZURE))
                .draggable(true));


        final OPFMarker marker2 = opfMap.addMarker(new OPFMarkerOptions()
                .visible(true)
                .position(new OPFLatLng(55.752004, 37.617017))
                .title("marker #2")
                .snippet("snippet #2")
                .icon(OPFBitmapDescriptorFactory.defaultMarker())
                .draggable(true));

        opfMap.addMarker(new OPFMarkerOptions()
                .visible(true)
                .position(new OPFLatLng(55.752004, -122.40205))
                .title("marker #3")
                .snippet("snippet #3")
                .icon(OPFBitmapDescriptorFactory.defaultMarker(OPFBitmapDescriptorFactory.HUE_YELLOW))
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
                Toast.makeText(MainActivity.this, "Marker click : " + marker.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        opfMap.setInfoWindowAdapter(new OPFInfoWindowAdapter() {
            @Override
            public View getInfoWindow(@NonNull final OPFMarker marker) {
                final String id = marker.getId();
                if (!id.equals(marker2.getId())) {
                    return null;
                }

                //noinspection InflateParams
                final View inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.info_window, null);
                final TextView titleTextView = (TextView) inflate.findViewById(R.id.title);
                titleTextView.setText(marker.getTitle());
                final TextView snippetTextView = (TextView) inflate.findViewById(R.id.snippet);
                snippetTextView.setText(marker.getSnippet());
                return inflate;
            }

            @Override
            public View getInfoContents(@NonNull final OPFMarker marker) {
                final String id = marker.getId();
                if (!id.equals(marker1.getId())) {
                    return null;
                }

                //noinspection InflateParams
                final View inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.info_window, null);
                final TextView titleTextView = (TextView) inflate.findViewById(R.id.title);
                titleTextView.setText(marker.getTitle());
                final TextView snippetTextView = (TextView) inflate.findViewById(R.id.snippet);
                snippetTextView.setText(marker.getSnippet());
                return inflate;
            }
        });

        opfMap.setOnInfoWindowClickListener(new OPFOnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(@NonNull final OPFMarker marker) {
                OPFLog.logMethod(marker);
                Toast.makeText(MainActivity.this, "Info window click : " + marker, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addCircle(@NonNull final OPFMap opfMap) {
        opfMap.addCircle(new OPFCircleOptions()
                .center(new OPFLatLng(55.752004, 37.617017))
                .radius(100000.0)
                .fillColor(Color.CYAN)
                .strokeColor(Color.BLUE).zIndex(1.0f));
    }
}
