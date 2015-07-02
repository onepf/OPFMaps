package org.onepf.opfmaps;

/**
 * Created by akarimova on 01.07.15.
 */
public interface OPFOnMapConfigureListener {
    void onError(int error);

    void onConfigure(OPFMapProvider provider);
}
