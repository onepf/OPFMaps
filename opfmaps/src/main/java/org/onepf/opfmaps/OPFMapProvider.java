package org.onepf.opfmaps;

import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by akarimova on 23.06.15.
 */
public interface OPFMapProvider {

    boolean hasRequiredPermissions(Context context);

    boolean isAvailable(Context context);

    @NonNull
    String getName();

    Fragment getFragment();

    boolean isKeyPresented(Context context);

    boolean hasRequestedFeatures(Context context);
}
