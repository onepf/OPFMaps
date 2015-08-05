/*
 * Copyright 2012-2015 One Platform Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onepf.opfmaps;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import org.onepf.opfmaps.delegate.MapFragmentDelegate;
import org.onepf.opfmaps.delegate.MapViewDelegate;
import org.onepf.opfmaps.listener.OPFOnMapReadyCallback;
import org.onepf.opfutils.OPFLog;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * @author Roman Savin
 * @since 30.07.2015
 */
public class OPFMapFragment extends Fragment implements MapFragmentDelegate {

    private MapViewDelegate mapViewDelegate;

    @NonNull
    private final List<OPFOnMapReadyCallback> pendingCallbacks = new ArrayList<>();

    public static OPFMapFragment newInstance() {
        return new OPFMapFragment();
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapViewDelegate = OPFMapHelper.getInstance().getDelegatesFactory().createMapViewDelegate(getActivity());
        mapViewDelegate.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.map_layout, container, false);
        ((FrameLayout) view.findViewById(R.id.map_frame)).addView((View) mapViewDelegate, MATCH_PARENT, MATCH_PARENT);

        return view;
    }

    @Override
    public void onResume() {
        mapViewDelegate.onResume();

        if (!pendingCallbacks.isEmpty()) {
            OPFLog.d("pendingCallbacks isn't empty");
            getMapAsync();
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        mapViewDelegate.onPause();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        pendingCallbacks.clear();
        super.onDestroyView();
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        mapViewDelegate.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        mapViewDelegate.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        mapViewDelegate.onLowMemory();
        super.onLowMemory();
    }

    @Override
    public void getMapAsync(@NonNull final OPFOnMapReadyCallback callback) {
        OPFLog.logMethod(callback);
        if (mapViewDelegate != null) {
            OPFLog.d("mapViewDelegate != null");
            mapViewDelegate.getMapAsync(callback);
        } else {
            OPFLog.d("mapViewDelegate == null");
            pendingCallbacks.add(callback);
        }
    }

    private void getMapAsync() {
        OPFLog.logMethod();
        if (mapViewDelegate != null) {
            OPFLog.d("mapViewDelegate != null");
            for (OPFOnMapReadyCallback callback : pendingCallbacks) {
                mapViewDelegate.getMapAsync(callback);
            }
            pendingCallbacks.clear();
        }
    }
}
