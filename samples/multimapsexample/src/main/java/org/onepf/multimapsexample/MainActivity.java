package org.onepf.multimapsexample;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.onepf.maps.amazon.AmazonMapProvider;
import org.onepf.maps.google.GoogleMapProvider;
import org.onepf.opfmaps.OPFOnMapClickListener;
import org.onepf.opfmaps.OPFOnMapConfigureListener;
import org.onepf.opfmaps.OPFMapProvider;
import org.onepf.opfmaps.OPFMapSettings;
import org.onepf.opfmaps.OPFMap;
import org.onepf.opfmaps.OPFOnMapLoadListener;
import org.onepf.opfmaps.OPFOnMarkerClickListener;
import org.onepf.opfmaps.OPFOnMarkerDragListener;
import org.onepf.opfmaps.model.OPFCircle;
import org.onepf.opfmaps.model.OPFLatLng;
import org.onepf.opfmaps.model.OPFMarker;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private OPFMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        map = (OPFMap) findViewById(R.id.map);
        List<OPFMapProvider> providers = new ArrayList<OPFMapProvider>();
        providers.add(new GoogleMapProvider());
        providers.add(new AmazonMapProvider());
        ;
        OPFMapSettings opfMapSettings = new OPFMapSettings(providers);
        map.init(getFragmentManager(), opfMapSettings, new OPFOnMapConfigureListener() {
            @Override
            public void onError(int r) {

            }

            @Override
            public void onConfigure(OPFMapProvider provider) {
                map.load(new OPFOnMapLoadListener() {
                    @Override
                    public void onMapLoad() {
                        map.setMyLocationEnabled(true);
                        map.setBuildingsEnabled(true);
//                        map.zoom((map.getMaxZoomLevel() + map.getMinZoomLevel()) / 2f);

                        //markers
                        OPFMarker opfMarker1 = new OPFMarker(new OPFLatLng(37.773975, -122.40205),
                                R.drawable.ic_plusone_tall_off_client);
                        opfMarker1.title("marker #1");
                        opfMarker1.snippet("snippet #1");
                        opfMarker1.draggable(true);
                        map.addMarker(opfMarker1);
                        OPFMarker opfMarker2 = new OPFMarker(new OPFLatLng(55.752004, 37.617017),
                                R.drawable.ic_plusone_tall_off_client);
                        opfMarker2.title("marker #2");
                        opfMarker2.snippet("snippet #2");
                        map.addMarker(opfMarker2);

                        //circles
                        OPFCircle circle = new OPFCircle();
                        circle.center(new OPFLatLng(55.752004, 37.617017));
                        circle.radius(1000000.0);
                        circle.fillColor(Color.LTGRAY);
                        circle.strokeColor(Color.BLUE);
                        map.addCircle(circle);

                        map.setOnMarkerDragListener(new OPFOnMarkerDragListener() {
                            @Override
                            public void onMarkerDragStart(OPFMarker marker) {
                            }

                            @Override
                            public void onMarkerDrag(OPFMarker marker) {
                            }

                            @Override
                            public void onMarkerDragEnd(OPFMarker marker) {
                            }
                        });

                        map.setOnMarkerClickListener(new OPFOnMarkerClickListener() {
                            @Override
                            public void onMarkerClick(OPFMarker marker) {
                            }
                        });

                        map.setOnMapClickListener(new OPFOnMapClickListener() {
                            @Override
                            public void onMapClick(OPFLatLng latLng) {
                            }
                        });

                    }

                    @Override
                    public void onError() {
                    }
                });
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
