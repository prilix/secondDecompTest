package com.jch.racWiFi.iduManagement.view;

import android.os.Bundle;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import java.util.Iterator;

public class IDUStateAwareFragment extends GenericFragment {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public boolean isIduAlreadyPresent(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        Iterator it = getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduList().iterator();
        while (it.hasNext()) {
            DetailedIduModel detailedIduModel = (DetailedIduModel) it.next();
            if (!detailedIduModel.getVendorThingId().equals(str) && str.equalsIgnoreCase(detailedIduModel.getName())) {
                return false;
            }
        }
        return true;
    }
}
