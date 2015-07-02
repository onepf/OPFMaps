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

    public void setColor(int color) {
        this.color = color;
    }

    public float getWidth() {
        return width;
    }

    public void width(float width) {
        this.width = width;
    }

    @Override
    public void remove() {

    }
}
