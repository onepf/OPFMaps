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
import android.support.annotation.MainThread;
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
 * A Map component in an app. This fragment is the simplest way to place a map in an application.
 * It's a wrapper around a view of a map to automatically handle the necessary life cycle needs.
 *
 * @author Roman Savin
 * @since 30.07.2015
 */
public class OPFSupportMapFragment extends Fragment implements MapFragmentDelegate {

    private static final String MAP_OPTIONS_BUNDLE_KEY = "MAP_OPTIONS_BUNDLE_KEY";

    private MapViewDelegate mapViewDelegate;

    @NonNull
    private final List<OPFOnMapReadyCallback> pendingCallbacks = new ArrayList<>();

    /**
     * Creates a map fragment, using default options.
     *
     * @return The created {@link OPFSupportMapFragment} instance.
     */
    public static OPFSupportMapFragment newInstance() {
        return new OPFSupportMapFragment();
    }

    /**
     * Creates a map fragment with the given options.
     *
     * @param options The {@link OPFMapOptions} instance.
     * @return The created {@link OPFSupportMapFragment} instance.
     */
    public static OPFSupportMapFragment newInstance(@NonNull final OPFMapOptions options) {
        final OPFSupportMapFragment fragment = new OPFSupportMapFragment();
        final Bundle arguments = new Bundle();

        arguments.putParcelable(MAP_OPTIONS_BUNDLE_KEY, options);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.map_layout, container, false);
        final OPFMapOptions options = getMapOptions();
        if (options != null) {
            mapViewDelegate = OPFMapHelper.getInstance().getDelegatesFactory().createMapViewDelegate(getActivity(), options);
        } else {
            mapViewDelegate = OPFMapHelper.getInstance().getDelegatesFactory().createMapViewDelegate(getActivity());
        }
        mapViewDelegate.onCreate(savedInstanceState);

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
        mapViewDelegate.onDestroy();
        super.onDestroyView();
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        mapViewDelegate.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        mapViewDelegate.onLowMemory();
        super.onLowMemory();
    }

    /**
     * Sets a callback object which will be triggered when the {@link OPFMap} instance is ready to be used.
     *
     * @param callback The callback object that will be triggered when the map is ready to be used.
     */
    @MainThread
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

    @Nullable
    private OPFMapOptions getMapOptions() {
        final Bundle arguments = getArguments();
        if (arguments != null) {
            return arguments.getParcelable(MAP_OPTIONS_BUNDLE_KEY);
        }

        return null;
    }
}
