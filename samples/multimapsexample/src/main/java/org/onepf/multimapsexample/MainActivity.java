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
import android.support.annotation.Nullable;
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
import org.onepf.opfmaps.model.OPFCircle;
import org.onepf.opfmaps.model.OPFCircleOptions;
import org.onepf.opfmaps.model.OPFGroundOverlay;
import org.onepf.opfmaps.model.OPFGroundOverlayOptions;
import org.onepf.opfmaps.model.OPFInfoWindowAdapter;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFMarker;
import org.onepf.opfmaps.model.OPFMarkerOptions;
import org.onepf.opfmaps.model.OPFPolygon;
import org.onepf.opfmaps.model.OPFPolygonOptions;
import org.onepf.opfmaps.model.OPFPolyline;
import org.onepf.opfmaps.model.OPFPolylineOptions;
import org.onepf.opfmaps.model.OPFTile;
import org.onepf.opfmaps.model.OPFTileOverlayOptions;
import org.onepf.opfmaps.model.OPFTileProvider;
import org.onepf.opfutils.OPFLog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class MainActivity extends Activity implements OPFOnMapReadyCallback {

    //private OPFMapView mapView;

    private OPFPolygon polygon;
    private OPFCircle circle;
    private OPFPolyline polyline;
    private OPFMap opfMap;
    private OPFGroundOverlay groundOverlay;

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
    public void onButtonClick(final View view) {
        polygon.setPoints(Arrays.asList(new OPFLatLng(56.7597311265849, 37.71333884100602),
                new OPFLatLng(56.70860754839525, 37.69927662137808),
                new OPFLatLng(56.75862834496016, 37.513138401618356)));
        polygon.setHoles(Arrays.asList(
                Arrays.asList(
                        new OPFLatLng(56.739139488175816, 37.586989729090135),
                        new OPFLatLng(56.726197236786376, 37.7097639987773),
                        new OPFLatLng(56.70969983952349, 37.70240624420652)

                ),
                Arrays.asList(
                        new OPFLatLng(56.7522606417405, 37.645697095053656),
                        new OPFLatLng(56.75133838313202, 37.66817936044419),
                        new OPFLatLng(56.74298813628395, 37.6541842601019)
                )
        ));
        circle.setCenter(new OPFLatLng(56.752004, 37.617017));

        polyline.setPoints(Arrays.asList(
                new OPFLatLng(56.69661394384935, 36.608256084788515),
                new OPFLatLng(56.08224581388055, 38.05120657972254),
                new OPFLatLng(56.830126194643654, 38.90446672723731)
        ));
        polyline.setColor(Color.YELLOW);

        opfMap.setTrafficEnabled(!opfMap.isTrafficEnabled());

        groundOverlay.setBearing(90);
    }

    @SuppressWarnings("PMD.ExcessiveMethodLength")
    @Override
    public void onMapReady(@NonNull final OPFMap opfMap) {
        OPFLog.logMethod(opfMap);
        this.opfMap = opfMap;

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
        circle = opfMap.addCircle(new OPFCircleOptions()
                .center(new OPFLatLng(55.752004, 37.617017))
                .radius(100000.0)
                .fillColor(Color.CYAN)
                .strokeColor(Color.BLUE).zIndex(1.0f));

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
                Toast.makeText(MainActivity.this, "Marker click : " + marker.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

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
                final String snippet = marker.getSnippet();
                if (snippet != null && !snippet.equals("snippet #2")) {
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
                final String snippet = marker.getSnippet();
                if (snippet != null && !snippet.equals("snippet #1")) {
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

        opfMap.setOnMyLocationButtonClickListener(new OPFOnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                Toast.makeText(MainActivity.this, "My location button click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        opfMap.setOnCameraChangeListener(new OPFOnCameraChangeListener() {
            @Override
            public void onCameraChange(@NonNull final OPFCameraPosition position) {
                OPFLog.logMethod(position);
            }
        });

        polygon = opfMap.addPolygon(new OPFPolygonOptions()
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

        polyline = opfMap.addPolyline(new OPFPolylineOptions().add(
                new OPFLatLng(55.69661394384935, 36.608256084788515),
                new OPFLatLng(55.08224581388055, 38.05120657972254),
                new OPFLatLng(55.830126194643654, 38.90446672723731)
        ).color(Color.BLACK).width(3.0f).zIndex(1.0f));

        opfMap.addMarker(new OPFMarkerOptions().position(new OPFLatLng(0, 37.617017)).draggable(true).icon(OPFBitmapDescriptorFactory.defaultMarker(OPFBitmapDescriptorFactory.HUE_ORANGE)));
        opfMap.addMarker(new OPFMarkerOptions().position(new OPFLatLng(37.773975, 0)).draggable(true).icon(OPFBitmapDescriptorFactory.defaultMarker(OPFBitmapDescriptorFactory.HUE_VIOLET)));
        groundOverlay = opfMap.addGroundOverlay(new OPFGroundOverlayOptions()
                .image(OPFBitmapDescriptorFactory.fromAsset("doge.png"))
                .position(new OPFLatLng(55.752004, 37.617017), 5000000, 5000000));


        //addTileOverlay();
    }
    //CHECKSTYLE:ON

    private void addTileOverlay() {
        opfMap.addTileOverlay(new OPFTileOverlayOptions().tileProvider(new OPFTileProvider() {
                    @Nullable
                    @Override
                    public OPFTile getTile(final int x, final int y, final int zoom) {
                        try {
                            final InputStream inputStream = getAssets().open("doge.png");
                            final OPFTile opfTile = new OPFTile(100, 100, readInputStream(inputStream));
                            inputStream.close();
                            return opfTile;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        return null;
                    }
                })
                        .zIndex(4)
                        .fadeIn(false)
                //  .visible(false)
        /*.tileProvider(new OPFUrlTileProvider(100, 100, new OPFUrlTileProvider.TileUrlProvider() {
            @Nullable
            @Override
            public URL getTileUrl(final int x, final int y, final int zoom) {
                try {
                    return new URL("http://cs10232.userapi.com/u10520745/140967277/w_81f4aa52.jpg");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }))*/);
    }

    private byte[] readInputStream(@NonNull final InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();

        return buffer.toByteArray();
    }
}
