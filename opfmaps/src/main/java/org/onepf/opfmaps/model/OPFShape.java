package org.onepf.opfmaps.model;

/**
 * Created by akarimova on 15.06.15.
 */
public class OPFShape extends OPFMapObject {
    private float zIndex = 0.0f;

    public void zIndex(float zIndex) {
        this.zIndex = zIndex;
    }

    public float getzIndex() {
        return zIndex;
    }

    //todo remove or not remove
    public void remove() {

    }
}
