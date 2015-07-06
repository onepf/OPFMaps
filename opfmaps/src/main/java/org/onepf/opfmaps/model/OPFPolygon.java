package org.onepf.opfmaps.model;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akarimova on 15.06.15.
 */
public class OPFPolygon extends OPFMutiPointsShape {
    private int fillColor = Color.TRANSPARENT;
    private int strokeColor = Color.BLACK;
    private float strokeWidth = 10.0f;
    private List<OPFLatLng> holes;

    private OPFPolygon() {
        holes = new ArrayList<>();
    }

    public int getFillColor() {
        return fillColor;
    }

    public void fillColor(int fillColor) {
        this.fillColor = fillColor;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public void strokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
    }

    public void addHole(Iterable<OPFLatLng> points) {
        ArrayList<OPFLatLng> hole = new ArrayList<>();
        for (OPFLatLng holePoint : points) {
            hole.add(holePoint);
        }
        this.holes.addAll(hole);
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void strokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }


    public static class Builder implements org.onepf.opfmaps.model.Builder<OPFPolygon> {
        private OPFPolygon polygon;

        public Builder() {
            polygon = new OPFPolygon();
        }

        public Builder setFillColor(int fillColor) {
            polygon.fillColor(fillColor);
            return this;
        }

        public Builder setStrokeColor(int strokeColor) {
            polygon.strokeColor(strokeColor);
            return this;
        }


        public Builder addPoint(OPFLatLng point) {
            polygon.add(point);
            return this;
        }

        public Builder addPoints(OPFLatLng... points) {
            polygon.add(points);
            return this;
        }

        public Builder setGeodesic(boolean geodesic) {
            polygon.geodesic(geodesic);
            return this;
        }

        public Builder setzIndex(float zIndex) {
            polygon.zIndex(zIndex);
            return this;
        }

        @Override
        public OPFPolygon build() {
            if (polygon.getPoints().size() == 0) {
                throw new IllegalStateException("OPFPolygon: no points were found");
            }
            return polygon;
        }

    }

}
