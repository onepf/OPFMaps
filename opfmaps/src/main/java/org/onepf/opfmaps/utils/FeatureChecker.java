package org.onepf.opfmaps.utils;

import android.content.Context;
import android.content.pm.FeatureGroupInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;

/**
 * Created by akarimova on 02.07.15.
 */
public class FeatureChecker {
    private FeatureChecker() {

    }

    public static boolean hasRequestedFeature(Context context, String feature, int version) {
//        if (TextUtils.isEmpty(feature)) {
//            throw new IllegalArgumentException("Feature can't be null or empty.");
//        }

//        try {
//            final PackageInfo info = context.getPackageManager()
//                    .getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);
//
//            FeatureGroupInfo[] featureGroups = info.featureGroups;
//            for (FeatureGroupInfo featureGroupInfo : featureGroups) {
//                FeatureInfo[] features = featureGroupInfo.features;
//                for (FeatureInfo featureInfo : features) {
//                    if (featureInfo.name.equals(feature)) {
//                        return false;
//                    }
//                }
//            }
//
//        } catch (PackageManager.NameNotFoundException ignored) {
//        }
//        return false;
        return true;
    }
}
