package org.onepf.opfmaps.model;

import android.graphics.Bitmap;

/**
 * Created by akarimova on 09.06.15.
 */
public final class OPFMarker extends OPFMapObject {
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
    private String id;

    private OPFMarker() {
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

    private Bitmap getBitmap() {
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

    public int getId() {
        return iconId;
    }

    public static class Builder implements org.onepf.opfmaps.model.Builder<OPFMarker> {
        private OPFMarker opfMarker;

        public Builder() {
            opfMarker = new OPFMarker();
        }

        public Builder setTitle(String title) {
            opfMarker.title(title);
            return this;
        }

        public Builder setSnippet(String snippet) {
            opfMarker.snippet(snippet);
            return this;
        }

        public Builder setLatLng(OPFLatLng opfLatLng) {
            opfMarker.latLng(opfLatLng);
            return this;
        }

        public Builder setBitmap(Bitmap bitmap) {
            opfMarker.bitmap(bitmap);
            return this;
        }

        public Builder setIcon(int iconId) {
            opfMarker.iconId(iconId);
            return this;
        }

        public Builder setAlpha(float alpha) {
            opfMarker.alpha(alpha);
            return this;
        }

        public Builder setRotation(float rotation) {
            opfMarker.rotation(rotation);
            return this;
        }

        public Builder setAnchor(float u, float v) {
            opfMarker.anchor(u, v);
            return this;
        }

        public Builder setDraggable(boolean draggable) {
            opfMarker.draggable(draggable);
            return this;
        }

        public Builder setFlat(boolean flat) {
            opfMarker.flat(flat);
            return this;
        }

        public Builder setVisible(boolean visible) {
            opfMarker.visible(visible);
            return this;
        }

        public OPFMarker build() {
            if (opfMarker.getLatLng() == null) {
                throw new IllegalStateException("OPFMarker: no coordinates!");
            }
            //todo
//            if (opfMarker.getIconId() <= 0 && opfMarker.getBitmap() == null) {
//                throw new IllegalStateException("OPFMarker: no icon!");
//            }
            return opfMarker;
        }

    }

}
