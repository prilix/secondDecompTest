package com.jch.racWiFi.fcm.util;

import com.jch.racWiFi.fcm.model.AcActivity;
import java.util.ArrayList;

public class AcActivitiesList extends ArrayList<AcActivity> {
    public boolean add(AcActivity acActivity) {
        if (!contains(acActivity)) {
            return super.add(acActivity);
        }
        return false;
    }
}
