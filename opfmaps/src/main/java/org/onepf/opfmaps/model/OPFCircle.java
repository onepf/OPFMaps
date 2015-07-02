package org.onepf.opfmaps.model;

import android.graphics.Color;

/**
 * Created by akarimova on 10.06.15.
 */
public class OPFCircle extends OPFShape {
    private OPFLatLng center;
    private double radius;
    private int fillColor = Color.TRANSPARENT;
    private int strokeColor = Color.BLACK;
    private float strokeWidth = 10f;

    public OPFCircle() {
    }

    public OPFLatLng getCenter() {
        return center;
    }

    public void center(OPFLatLng center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void radius(double radius) {
        this.radius = radius;
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

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void strokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    @Override
    public void remove() {

    }
}
