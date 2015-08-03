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
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.onepf.opfmaps.delegate.MapFragmentDelegate;

/**
 * @author Roman Savin
 * @since 30.07.2015
 */
public class OPFMapFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.map_view, container, false);

        if (savedInstanceState == null) {
            final MapFragmentDelegate delegate = OPFMapHelper.getInstance().getDelegatesFactory().createMapFragmentDelegate();
            getChildFragmentManager().beginTransaction()
                    .add(R.id.map_frame, (Fragment) delegate, delegate.getClass().getSimpleName())
                    .commit();
        }

        return view;
    }
}
