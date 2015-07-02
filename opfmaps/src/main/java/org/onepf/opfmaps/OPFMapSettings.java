package org.onepf.opfmaps;

import java.util.List;

/**
 * Created by akarimova on 11.06.15.
 */
public class OPFMapSettings {
    private List<OPFMapProvider> providers;

    public OPFMapSettings(List<OPFMapProvider> providers) {
        if (providers == null || providers.size() == 0) {
            //todo throw an exception
            throw new IllegalStateException("");
        }
        this.providers = providers;
    }

    public List<OPFMapProvider> getProviders() {
        return providers;
    }
}
