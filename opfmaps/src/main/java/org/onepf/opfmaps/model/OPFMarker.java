package org.onepf.opfmaps.model;

import android.graphics.Bitmap;

/**
 * Created by akarimova on 09.06.15.
 */
public class OPFMarker extends OPFMapObject {
    private OPFLatLng latLng;
    private String title;
    private String snippet;
    private Bitmap bitmap;
    private int iconId;
    private boolean draggable;
    private boolean flat;
    private float alpha = 1.0f;
    private float rotation;
    private float u;
    private float v;

    //todo bitmapdescriptor
    public OPFMarker(OPFLatLng latLng, int iconId) {
        latLng(latLng);
        iconId(iconId);
    }

    public String getTitle() {
        return title;
    }

    public void title(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void snippet(String snippet) {
        this.snippet = snippet;
    }

    public OPFLatLng getLatLng() {
        return latLng;
    }

    public void latLng(OPFLatLng latLng) {
        this.latLng = latLng;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void bitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getIconId() {
        return iconId;
    }

    public void iconId(int iconId) {
        this.iconId = iconId;
    }

    public float getAlpha() {
        return alpha;
    }

    public void alpha(float alpha) {
        this.alpha = alpha;
    }

    public float getRotation() {
        return rotation;
    }

    public void rotation(float rotation) {
        this.rotation = rotation;
    }

    public void anchor(float u, float v) {
        this.u = u;
        this.v = v;
    }

    public float getAnchorU() {
        return u;
    }

    public float getAnchorV() {
        return v;
    }

    public boolean isDraggable() {
        return draggable;
    }

    public void draggable(boolean draggable) {
        this.draggable = draggable;
    }

    public boolean isFlat() {
        return flat;
    }

    public void flat(boolean flat) {
        this.flat = flat;
    }
}
