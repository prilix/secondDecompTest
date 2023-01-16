package com.jch.racWiFi.iduManagement;

import com.jch.racWiFi.iduManagement.WebSocketNotification;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.Power;
import com.jch.racWiFi.iduManagement.model.RacModelWiseConfigurationList;
import com.jch.racWiFi.iduManagement.model.RacModelWiseData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class IduList extends CopyOnWriteArrayList<DetailedIduModel> {
    private boolean updateIduListInProgress = false;
    private boolean updateIndividualIduDataInProgress = false;

    public void copyRequestTypeToDetailIduModels(WebSocketNotification.RequestType requestType) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((DetailedIduModel) it.next()).setRequestTypeString(requestType.name());
        }
    }

    public void updateIduList(List<DetailedIduModel> list) {
        this.updateIduListInProgress = true;
        if (!this.updateIndividualIduDataInProgress) {
            clear();
            addAll(list);
            removeDuplicates();
            this.updateIduListInProgress = false;
        }
    }

    public void updateIndividualIduData(DetailedIduModel detailedIduModel) {
        this.updateIndividualIduDataInProgress = true;
        if (!this.updateIduListInProgress) {
            int indexOf = indexOf(detailedIduModel);
            if (indexOf == -1) {
                add(detailedIduModel);
            } else {
                set(indexOf, detailedIduModel);
            }
            removeDuplicates();
            this.updateIndividualIduDataInProgress = false;
        }
    }

    private void removeDuplicates() {
        ArrayList arrayList = new ArrayList();
        Iterator it = iterator();
        while (it.hasNext()) {
            DetailedIduModel detailedIduModel = (DetailedIduModel) it.next();
            if (!arrayList.contains(detailedIduModel)) {
                arrayList.add(detailedIduModel);
            }
        }
        clear();
        addAll(arrayList);
    }

    public DetailedIduModel getDetailedIduModelFromRacId(int i) {
        Iterator it = iterator();
        DetailedIduModel detailedIduModel = null;
        while (it.hasNext()) {
            DetailedIduModel detailedIduModel2 = (DetailedIduModel) it.next();
            if (detailedIduModel2.f454id.intValue() == i) {
                detailedIduModel = detailedIduModel2;
            }
        }
        return detailedIduModel;
    }

    public DetailedIduModel getDetailedIduModelFromCloudId(String str) {
        Iterator it = iterator();
        DetailedIduModel detailedIduModel = null;
        while (it.hasNext()) {
            DetailedIduModel detailedIduModel2 = (DetailedIduModel) it.next();
            if (detailedIduModel2.cloudId.equals(str)) {
                detailedIduModel = detailedIduModel2;
            }
        }
        return detailedIduModel;
    }

    public DetailedIduModel getCopyOfDetailedIduModelFromRacId(int i) {
        DetailedIduModel detailedIduModel = new DetailedIduModel();
        Iterator it = iterator();
        while (it.hasNext()) {
            DetailedIduModel detailedIduModel2 = (DetailedIduModel) it.next();
            if (detailedIduModel2.f454id.intValue() == i) {
                detailedIduModel.copy(detailedIduModel2);
            }
        }
        return detailedIduModel;
    }

    public static List<DetailedIduModel> createStartAllStopAllUnitBody(boolean z, List<DetailedIduModel> list, RacModelWiseConfigurationList racModelWiseConfigurationList) {
        ArrayList arrayList = new ArrayList();
        for (DetailedIduModel next : list) {
            DetailedIduModel detailedIduModel = new DetailedIduModel();
            detailedIduModel.copy(next);
            RacModelWiseData racModelWiseDataBasedOnRacTypeId = racModelWiseConfigurationList.getRacModelWiseDataBasedOnRacTypeId(next.cloudId);
            detailedIduModel.power = (z ? Power.ON : Power.OFF).name();
            if (racModelWiseDataBasedOnRacTypeId != null) {
                if (detailedIduModel.isInSpecialMode()) {
                    detailedIduModel.copyDefaults(racModelWiseDataBasedOnRacTypeId);
                }
                detailedIduModel.updateCommandBasedOnRacModelWiseRestrictions(racModelWiseDataBasedOnRacTypeId);
            }
            arrayList.add(detailedIduModel);
        }
        return arrayList;
    }

    public static List<String> getListOfCloudIds(List<DetailedIduModel> list) {
        ArrayList arrayList = new ArrayList();
        for (DetailedIduModel detailedIduModel : list) {
            arrayList.add(detailedIduModel.cloudId);
        }
        return arrayList;
    }
}
