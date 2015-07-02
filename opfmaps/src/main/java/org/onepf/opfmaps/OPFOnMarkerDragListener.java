package org.onepf.opfmaps;

import org.onepf.opfmaps.model.OPFMarker;

/**
 * Created by akarimova on 02.07.15.
 */
public interface OPFOnMarkerDragListener {
    void onMarkerDragStart(OPFMarker marker);

    void onMarkerDrag(OPFMarker marker);

    void onMarkerDragEnd(OPFMarker marker);
}
