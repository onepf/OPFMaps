package org.onepf.maps.google;

import android.app.Fragment;
import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import org.onepf.opfmaps.OPFAbstractMapProvider;
import org.onepf.opfmaps.utils.FeatureChecker;
import org.onepf.opfmaps.utils.MetaDataChecker;
import org.onepf.opfmaps.utils.PermissionChecker;

import static android.Manifest.permission.*;

/**
 * Created by akarimova on 24.06.15.
 */
public class GoogleMapProvider extends OPFAbstractMapProvider {
    @Override
    public boolean hasRequiredPermissions(Context context) {
        return
                PermissionChecker.permissionRequested(context, INTERNET) &&
                        PermissionChecker.permissionRequested(context, ACCESS_NETWORK_STATE) &&
                        PermissionChecker.permissionRequested(context, WRITE_EXTERNAL_STORAGE) &&
                        (PermissionChecker.permissionRequested(context, ACCESS_COARSE_LOCATION)
                                || PermissionChecker.permissionRequested(context, ACCESS_FINE_LOCATION));
    }

    @Override
    public boolean isAvailable(Context context) {
        //todo to prevent sdk kits check for a permission in the manifest
        int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        return googlePlayServicesAvailable == ConnectionResult.SUCCESS; //todo think about other codes
    }

    @Override
    public Fragment getFragment() {
        return OPFGoogleFragment.newInstance(new OPFGoogleMapOptions());
    }


    @Override
    public boolean isKeyPresented(Context context) {
        //todo look for a constant
        return MetaDataChecker.dataPresented(context, "com.google.android.maps.v2.API_KEY");
    }

    @Override
    public boolean hasRequestedFeatures(Context context) {
        return FeatureChecker.hasRequestedFeature(context, "tbd", 0x00020000);
    }

}
