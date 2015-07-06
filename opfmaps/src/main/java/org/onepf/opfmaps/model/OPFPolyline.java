package org.onepf.opfmaps.model;

import android.graphics.Color;

/**
 * Created by akarimova on 09.06.15.
 */
public class OPFPolyline extends OPFMutiPointsShape {
    private int color = Color.BLACK;
    private float width = 10.0f;

    private OPFPolyline() {
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

    public static class Builder extends OPFMutiPointsShape.Builder<OPFPolyline> {

        public Builder setColor(int color) {
            getShape().color(color);
            return this;
        }


        public Builder setWidthr(float width) {
            getShape().width(width);
            return this;
        }

        @Override
        public OPFPolyline build() {
            if (getShape().getPoints().size() == 0) {
                throw new IllegalStateException("OPFPolyline: no points were found");
            }
            return getShape();
        }

        @Override
        protected OPFPolyline createShape() {
            return new OPFPolyline();
        }

    }
}
