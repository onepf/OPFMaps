package org.onepf.maps.amazon;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amazon.geo.mapsv2.AmazonMap;
import com.amazon.geo.mapsv2.AmazonMapOptions;
import com.amazon.geo.mapsv2.CameraUpdateFactory;
import com.amazon.geo.mapsv2.MapFragment;
import com.amazon.geo.mapsv2.OnMapReadyCallback;
import com.amazon.geo.mapsv2.model.BitmapDescriptorFactory;
import com.amazon.geo.mapsv2.model.CircleOptions;
import com.amazon.geo.mapsv2.model.LatLng;
import com.amazon.geo.mapsv2.model.Marker;
import com.amazon.geo.mapsv2.model.MarkerOptions;
import com.amazon.geo.mapsv2.model.PolygonOptions;
import com.amazon.geo.mapsv2.model.PolylineOptions;

import org.onepf.opfmaps.OPFMapDelegate;
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

import java.util.List;

/**
 * Created by akarimova on 09.06.15.
 */
public class OPFAmazonFragment extends MapFragment implements OPFMapDelegate, OPFTBDInterface<PolylineOptions, PolygonOptions, CircleOptions, MarkerOptions, LatLng> {
    private AmazonMap amazonMap;
    private boolean initialized;

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    public static OPFAmazonFragment newInstance() {
//        GoogleMapOptions mapOptions;
//        mapOptions.camera();
//        mapOptions.compassEnabled();
//        mapOptions.liteMode();
//        mapOptions.mapToolbarEnabled();
//        mapOptions.zOrderOnTop();
//        mapOptions.zoomGesturesEnabled();
//        mapOptions.useViewLifecycleInFragment();
//        mapOptions.rotateGesturesEnabled();
//        mapOptions.mapToolbarEnabled();
//        mapOptions.tiltGesturesEnabled();
//        opfAmazonFragment.setArguments(options.toBundle());
        AmazonMapOptions amazonMapOptions = new AmazonMapOptions();
        amazonMapOptions.tiltGesturesEnabled(true);
        amazonMapOptions.mapToolbarEnabled(true);
        amazonMapOptions.compassEnabled(true);
        amazonMapOptions.rotateGesturesEnabled(true);
        amazonMapOptions.scrollGesturesEnabled(true);

        OPFAmazonFragment opfAmazonFragment = new OPFAmazonFragment();

        Bundle bundle = new Bundle(1);
        bundle.putParcelable("MapOptions", amazonMapOptions);
        opfAmazonFragment.setArguments(bundle);
        return opfAmazonFragment;
    }

    @Override
    public void addMarker(OPFMarker opfMarker) {
        amazonMap.addMarker(marker(opfMarker));
    }

    @Override
    public void addPolyline(OPFPolyline opfPolyline) {
        PolylineOptions polylineOptions = polyline(opfPolyline);
        amazonMap.addPolyline(polylineOptions);
    }

    @Override
    public void addCircle(OPFCircle opfCircle) {
        CircleOptions circleOptions = circle(opfCircle);
        amazonMap.addCircle(circleOptions);
    }

    @Override
    public void addPolygon(OPFPolygon opfPolygon) {
        PolygonOptions polygon = polygon(opfPolygon);
        amazonMap.addPolygon(polygon);
    }

    @Override
    public void zoom(float zoom) {
        amazonMap.animateCamera(CameraUpdateFactory.zoomTo(zoom));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setInfoWindowAdapter(OPFInfoWindowAdapter adapter) {

    }

    @Override
    public void setMyLocationEnabled(boolean enabled) {
        amazonMap.setMyLocationEnabled(enabled);
    }

    @Override
    public void setTrafficEnabled(boolean enabled) {
        amazonMap.setTrafficEnabled(enabled);
    }

    @Override
    public void clear() {
        amazonMap.clear();
    }

    @Override
    public void setMapType(int mapType) {

    }

    @Override
    public int getMapType() {
        return 0;
    }

    @Override
    public Location getMyLocation() {
        return amazonMap.getMyLocation();
    }

    @Override
    public void addGroundOverlay(OPFGroundOverlay opfGroundOverlay) {

    }

    @Override
    public void addTileOverlay(OPFTileOverlay opfTileOverlay) {

    }

    @Override
    public void addPadding(int bottom, int left, int top, int right) {

    }

    @Override
    public void setIndoorEnabled(boolean enabled) {
        amazonMap.setIndoorEnabled(enabled);
    }

    @Override
    public void setBuildingsEnabled(boolean enabled) {
        amazonMap.setBuildingsEnabled(enabled);
    }

    @Override
    public float getMaxZoomLevel() {
        return amazonMap.getMaxZoomLevel();
    }

    @Override
    public float getMinZoomLevel() {
        return amazonMap.getMinZoomLevel();
    }

    @Override
    public void load(final OPFOnMapLoadListener mapLoadedListener) {
        getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(AmazonMap amazonMap) {
                if (amazonMap != null && getActivity() != null) {
                    OPFAmazonFragment.this.amazonMap = amazonMap;
                    OPFAmazonFragment.this.amazonMap.setInfoWindowAdapter(new AmazonMap.InfoWindowAdapter() {
                        @Override
                        public View getInfoContents(Marker marker) {
                            View inflate = LayoutInflater.from(OPFAmazonFragment.this.getActivity()).inflate(R.layout.infowindow_view, null);
                            TextView textView = (TextView) inflate.findViewById(R.id.title);
                            textView.setText(marker.getTitle());
                            return inflate;
                        }

                        @Override
                        public View getInfoWindow(Marker marker) {
                            return null;
                        }
                    });

                    if (mapLoadedListener != null) {
                        mapLoadedListener.onMapLoad();
                    }
                    initialized = true;

                } else {
                    if (mapLoadedListener != null) {
                        mapLoadedListener.onError();
                        initialized = false;
                    }
                }
            }
        });
    }

    @Override
    public void setOnMarkerDragListener(final OPFOnMarkerDragListener onMarkerDragListener) {
        amazonMap.setOnMarkerDragListener(new AmazonMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(com.amazon.geo.mapsv2.model.Marker marker) {
                OPFMarker opfMarker = makeOPFMarker(marker);
                onMarkerDragListener.onMarkerDragStart(opfMarker);
            }

            @Override
            public void onMarkerDragEnd(com.amazon.geo.mapsv2.model.Marker marker) {
                OPFMarker opfMarker = makeOPFMarker(marker);
                onMarkerDragListener.onMarkerDragEnd(opfMarker);
            }

            @Override
            public void onMarkerDragStart(com.amazon.geo.mapsv2.model.Marker marker) {
                OPFMarker opfMarker = makeOPFMarker(marker);
                onMarkerDragListener.onMarkerDragStart(opfMarker);
            }
        });
    }

    private static OPFMarker makeOPFMarker(Marker marker) {
        OPFMarker.Builder markerBuilder = new OPFMarker.Builder();
        markerBuilder.setLatLng(new OPFLatLng(marker.getPosition().latitude, marker.getPosition().longitude))
                .setIcon(10000) //todo
                .setAlpha(marker.getAlpha())
                .setRotation(marker.getRotation())
                .setTitle(marker.getTitle())
                .setSnippet(marker.getSnippet())
                .setDraggable(marker.isDraggable())
                .setFlat(marker.isFlat())
                .setVisible(marker.isVisible());
        return markerBuilder.build();
    }

    @Override
    public void setOnMarkerClickListener(final OPFOnMarkerClickListener onMarkerClickListener) {
        amazonMap.setOnMarkerClickListener(new AmazonMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(com.amazon.geo.mapsv2.model.Marker marker) {
                OPFMarker opfMarker = makeOPFMarker(marker);
                onMarkerClickListener.onMarkerClick(opfMarker);//todo
                return false; //if true info window not showing
            }
        });
    }

    @Override
    public void setOnMapClickListener(final OPFOnMapClickListener onMapClickListener) {
        amazonMap.setOnMapClickListener(new AmazonMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                onMapClickListener.onMapClick(new OPFLatLng(latLng.latitude, latLng.longitude));
            }
        });
    }

    @Override
    public PolylineOptions polyline(OPFPolyline opfPolyline) {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.width(opfPolyline.getWidth());
        polylineOptions.geodesic(opfPolyline.isGeodesic());
        polylineOptions.visible(opfPolyline.isVisible());
        polylineOptions.color(opfPolyline.getColor());
        polylineOptions.zIndex(opfPolyline.getzIndex());
        List<OPFLatLng> points = opfPolyline.getPoints();
        for (OPFLatLng opfLatLng : points) {
            LatLng latLng = latLng(opfLatLng);
            polylineOptions.add(latLng);
        }
        return polylineOptions;
    }

    @Override
    public OPFPolyline opfPolyline(PolylineOptions polyline) {
        return null;
    }

    @Override
    public PolygonOptions polygon(OPFPolygon polygon) {
        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.geodesic(polygon.isGeodesic());
        polygonOptions.visible(polygon.isVisible());
        polygonOptions.zIndex(polygon.getzIndex());
        polygonOptions.fillColor(polygon.getFillColor());
        polygonOptions.strokeColor(polygon.getStrokeColor());
        polygonOptions.strokeWidth(polygon.getStrokeWidth());
        for (OPFLatLng latLng : polygon.getPoints()) {
            polygonOptions.add(latLng(latLng));
        }
        return polygonOptions;
    }

    @Override
    public OPFPolygon opfPolygon(PolygonOptions polygon) {
        return null;
    }

    @Override
    public CircleOptions circle(OPFCircle opfCircle) {
        CircleOptions circleOptions = new CircleOptions();
        OPFLatLng center = opfCircle.getCenter();
        LatLng latLng = latLng(center);
        circleOptions.center(latLng);
        circleOptions.radius(opfCircle.getRadius());
        circleOptions.zIndex(opfCircle.getzIndex());
        circleOptions.fillColor(opfCircle.getFillColor());
        circleOptions.strokeColor(opfCircle.getStrokeColor());
        circleOptions.strokeWidth(opfCircle.getStrokeWidth());
        circleOptions.visible(opfCircle.isVisible());
        return circleOptions;
    }

    @Override
    public OPFCircle opfCircle(CircleOptions circle) {
        return null;
    }

    @Override
    public MarkerOptions marker(OPFMarker opfMarker) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng(opfMarker.getLatLng()));
        markerOptions.title(opfMarker.getTitle());
        markerOptions.snippet(opfMarker.getSnippet());
        markerOptions.icon(BitmapDescriptorFactory.fromResource(opfMarker.getIconId()));
        markerOptions.visible(opfMarker.isVisible());
        markerOptions.draggable(opfMarker.isDraggable());
        markerOptions.alpha(opfMarker.getAlpha());
        markerOptions.rotation(opfMarker.getRotation());
//        markerOptions.anchor(opfMarker.getAnchorU(), opfMarker.getAnchorV());
        markerOptions.flat(opfMarker.isFlat());
        return markerOptions;
    }


    @Override
    public LatLng latLng(OPFLatLng opfLatLng) {
        return new LatLng(opfLatLng.getLatitude(), opfLatLng.getLongitude());
    }

    @Override
    public OPFLatLng opfLatLng(LatLng latLng) {
        return new OPFLatLng(latLng.latitude, latLng.longitude);
    }
}
