package org.onepf.maps.amazon;

import android.app.Fragment;
import android.content.Context;

import org.onepf.opfmaps.OPFAbstractMapProvider;

/**
 * Created by akarimova on 24.06.15.
 */
public class AmazonMapProvider extends OPFAbstractMapProvider {
    @Override
    public boolean hasRequiredPermissions(Context context) {
        return false;
    }

    @Override
    public boolean isAvailable(Context context) {
        return false;
    }

    @Override
    public Fragment getFragment() {
        return null;
    }

    @Override
    public boolean isKeyPresented(Context context) {
        return false;
    }

    @Override
    public boolean hasRequestedFeatures(Context context) {
        return false;
    }

}
