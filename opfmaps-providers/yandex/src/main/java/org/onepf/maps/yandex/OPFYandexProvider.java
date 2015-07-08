package org.onepf.maps.yandex;

import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;

import org.onepf.opfmaps.OPFAbstractMapProvider;
import org.onepf.opfmaps.OPFMapOptions;

/**
 * Created by akarimova on 09.07.15.
 */
public class OPFYandexProvider extends OPFAbstractMapProvider {
    @NonNull
    @Override
    public Fragment getFragment(OPFMapOptions opfMapOptions) {
        return null;
    }

    @Override
    public boolean hasRequiredPermissions(Context context) {
        return false;
    }

    @Override
    public boolean isAvailable(Context context) {
        return false;
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
