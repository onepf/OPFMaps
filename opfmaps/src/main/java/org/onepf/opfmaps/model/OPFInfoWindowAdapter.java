package org.onepf.opfmaps.model;

import android.view.View;

/**
 * Created by akarimova on 15.06.15.
 */
public interface OPFInfoWindowAdapter {
    public View getInfoWindow(OPFMarker marker);

    public View getInfoContents(OPFMarker marker);
}
