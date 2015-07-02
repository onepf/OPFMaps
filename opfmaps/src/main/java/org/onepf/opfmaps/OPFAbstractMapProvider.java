package org.onepf.opfmaps;

import android.support.annotation.NonNull;

/**
 * Created by akarimova on 24.06.15.
 */
public abstract class OPFAbstractMapProvider implements OPFMapProvider {

    @NonNull
    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

}
