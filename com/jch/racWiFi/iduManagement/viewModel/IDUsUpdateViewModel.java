package com.jch.racWiFi.iduManagement.viewModel;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.genericViewModel.GenericViewModel;
import com.jch.racWiFi.iduManagement.IduList;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;

public class IDUsUpdateViewModel extends GenericViewModel {
    private SingleLiveEvent<IduList> iduListSingleLiveEvent = new SingleLiveEvent<>(new IduList());
    private SingleLiveEvent<DetailedIduModel> individualIduUpdateSingleLiveEvent = new SingleLiveEvent<>();
    public String tag;

    public SingleLiveEvent<DetailedIduModel> getIndividualIduUpdateSingleLiveEvent() {
        return this.individualIduUpdateSingleLiveEvent;
    }

    public SingleLiveEvent<IduList> getIduListSingleLiveEvent() {
        return this.iduListSingleLiveEvent;
    }

    public IduList getIduList() {
        return getIduListSingleLiveEvent().getValue();
    }

    public void updateListAndPost(IduList iduList) {
        getIduList().updateIduList(iduList);
        getIduListSingleLiveEvent().postValue(getIduList());
    }

    public void updateIndividualIduAndPost(DetailedIduModel detailedIduModel) {
        getIduList().updateIndividualIduData(detailedIduModel);
        getIduListSingleLiveEvent().postValue(getIduList());
        getIndividualIduUpdateSingleLiveEvent().postValue(detailedIduModel);
    }
}
