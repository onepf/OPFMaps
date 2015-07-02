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

    public OPFPolygon() {
        holes = new ArrayList<>();
    }

    @Override
    public void remove() {

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


}
