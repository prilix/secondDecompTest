package com.jch.racWiFi.genericViewModel;

import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.genericModels.GenericResponse;

public class GenericNetworkCallViewModel extends GenericViewModel {
    private SingleLiveEvent<GenericResponse.GenericErrorResponse> genericErrorResponseSingleLiveEvent = new SingleLiveEvent<>();
    private SingleLiveEvent<GenericResponse> genericResponseSingleLiveEvent = new SingleLiveEvent<>();

    public SingleLiveEvent<GenericResponse> getGenericResponseSingleLiveEvent() {
        return this.genericResponseSingleLiveEvent;
    }

    public SingleLiveEvent<GenericResponse.GenericErrorResponse> getGenericErrorResponseSingleLiveEvent() {
        return this.genericErrorResponseSingleLiveEvent;
    }
}
