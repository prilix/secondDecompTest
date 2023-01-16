package com.jch.racWiFi.iduManagement.model;

import java.util.ArrayList;
import java.util.Iterator;

public class RacModelWiseConfigurationList extends ArrayList<RacModelWiseData> {
    public boolean containsRacConfiguration(String str) {
        Iterator it = iterator();
        while (it.hasNext()) {
            if (((RacModelWiseData) it.next()).getCloudId().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public RacModelWiseData getRacModelWiseDataBasedOnRacTypeId(String str) {
        Iterator it = iterator();
        while (it.hasNext()) {
            RacModelWiseData racModelWiseData = (RacModelWiseData) it.next();
            if (racModelWiseData.getCloudId().equals(str)) {
                return racModelWiseData;
            }
        }
        return null;
    }

    public RacModelWiseData getRacModelWiseDataBasedOnRacId(int i) {
        Iterator it = iterator();
        while (it.hasNext()) {
            RacModelWiseData racModelWiseData = (RacModelWiseData) it.next();
            if (racModelWiseData.getID() == ((long) i)) {
                return racModelWiseData;
            }
        }
        return null;
    }
}
