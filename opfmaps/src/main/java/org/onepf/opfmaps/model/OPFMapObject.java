package org.onepf.opfmaps.model;

/**
 * Created by akarimova on 11.06.15.
 */
public abstract class OPFMapObject {
    private boolean visible = true;

    public void visible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }
}
