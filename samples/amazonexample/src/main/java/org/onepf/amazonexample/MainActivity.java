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

package org.onepf.amazonexample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amazon.geo.mapsv2.AmazonMap;
import com.amazon.geo.mapsv2.OnMapReadyCallback;
import com.amazon.geo.mapsv2.SupportMapFragment;
import com.amazon.geo.mapsv2.model.BitmapDescriptorFactory;
import com.amazon.geo.mapsv2.model.LatLng;
import com.amazon.geo.mapsv2.model.Marker;
import com.amazon.geo.mapsv2.model.MarkerOptions;

import org.onepf.opfutils.OPFLog;


public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    @SuppressWarnings("PMD.ExcessiveMethodLength")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OPFLog.logMethod(savedInstanceState);

        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
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
    }

    //CHECKSTYLE:OFF
    @Override
    public void onMapReady(@NonNull final AmazonMap amazonMap) {
        OPFLog.logMethod(amazonMap);

        amazonMap.setMapType(AmazonMap.MAP_TYPE_HYBRID);
        amazonMap.setMyLocationEnabled(true);
        amazonMap.setBuildingsEnabled(true);
        amazonMap.setMyLocationEnabled(true);
        //map.zoom((map.getMaxZoomLevel() + map.getMinZoomLevel()) / 2f);

        //markers
        amazonMap.addMarker(new MarkerOptions()
                .visible(true)
                .position(new LatLng(37.773975, -122.40205))
                .title("marker #1")
                .snippet("snippet #1")
                .icon(BitmapDescriptorFactory.defaultMarker())
                .draggable(true));


        amazonMap.addMarker(new MarkerOptions()
                .visible(true)
                .position(new LatLng(55.752004, 37.617017))
                .title("marker #2")
                .snippet("snippet #2")
                .icon(BitmapDescriptorFactory.defaultMarker())
                .draggable(true));

        amazonMap.setOnMarkerDragListener(new AmazonMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(@NonNull final Marker marker) {
                OPFLog.logMethod(marker);
            }

            @Override
            public void onMarkerDrag(@NonNull final Marker marker) {
                OPFLog.logMethod(marker);
            }

            @Override
            public void onMarkerDragEnd(@NonNull final Marker marker) {
                OPFLog.logMethod(marker);
            }
        });

        amazonMap.setOnMarkerClickListener(new AmazonMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull final Marker marker) {
                OPFLog.logMethod(marker);
                Toast.makeText(MainActivity.this, "Marker click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        amazonMap.setOnMapClickListener(new AmazonMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull final LatLng latLng) {
                OPFLog.logMethod(latLng);
                Toast.makeText(MainActivity.this, "Map click position : " + latLng, Toast.LENGTH_SHORT).show();
            }
        });

        amazonMap.setInfoWindowAdapter(new AmazonMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(@NonNull final Marker marker) {
                //noinspection InflateParams
                final View inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.info_window, null);
                final TextView title = (TextView) inflate.findViewById(R.id.title);
                title.setText(marker.getTitle());
                final TextView snippet = (TextView) inflate.findViewById(R.id.snippet);
                snippet.setText(marker.getSnippet());
                return inflate;
            }

            @Override
            public View getInfoContents(@NonNull final Marker marker) {
                return null;
            }
        });
    }
    //CHECKSTYLE:ON
}
