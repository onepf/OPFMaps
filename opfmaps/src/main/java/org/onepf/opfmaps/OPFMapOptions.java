package org.onepf.opfmaps;

/**
 * Created by akarimova on 02.07.15.
 */
public class OPFMapOptions {
    private boolean rotateGesturesEnabled;
    private boolean compassEnabled;
    private boolean tiltGesturesEnabled;
    private boolean zoomGesturesEnabled;

    public OPFMapOptions rotateGesturesEnabled(boolean enabled) {
        this.rotateGesturesEnabled = enabled;
        return this;
    }

    public OPFMapOptions compassEnabled(boolean enabled) {
        this.compassEnabled = enabled;
        return this;
    }

    public OPFMapOptions tiltGesturesEnabled(boolean enabled) {
        this.tiltGesturesEnabled = enabled;
        return this;
    }

    public OPFMapOptions zoomGesturesEnabled(boolean enabled) {
        this.zoomGesturesEnabled = enabled;
        return this;
    }

    public boolean isRotateGesturesEnabled() {
        return rotateGesturesEnabled;
    }

    public boolean isCompassEnabled() {
        return compassEnabled;
    }

    public boolean isTiltGesturesEnabled() {
        return tiltGesturesEnabled;
    }

    public boolean isZoomGesturesEnabled() {
        return zoomGesturesEnabled;
    }
}
