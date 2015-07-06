package org.onepf.opfmaps.model;

import android.graphics.Color;

/**
 * Created by akarimova on 09.06.15.
 */
public class OPFPolyline extends OPFMutiPointsShape {
    private int color = Color.BLACK;
    private float width = 10.0f;

    public OPFPolyline() {
    }

    public int getColor() {
        return color;
    }

    public void color(int color) {
        this.color = color;
    }

    public float getWidth() {
        return width;
    }

    public void width(float width) {
        this.width = width;
    }

    private OPFPolygon polygon;

    public static class Builder implements org.onepf.opfmaps.model.Builder<OPFPolyline> {
        private OPFPolyline polyline;

        public Builder() {
            polyline = new OPFPolyline();
        }

        public Builder setColor(int color) {
            polyline.color(color);
            return this;
        }

        public Builder addPoint(OPFLatLng point) {
            polyline.add(point);
            return this;
        }

        public Builder addPoints(OPFLatLng... points) {
            polyline.add(points);
            return this;
        }

        public Builder setGeodesic(boolean geodesic) {
            polyline.geodesic(geodesic);
            return this;
        }

        public Builder setzIndex(float zIndex) {
            polyline.zIndex(zIndex);
            return this;
        }

        @Override
        public OPFPolyline build() {
            if (polyline.getPoints().size() == 0) {
                throw new IllegalStateException("OPFPolyline: no points were found");
            }
            return polyline;
        }

    }
}
