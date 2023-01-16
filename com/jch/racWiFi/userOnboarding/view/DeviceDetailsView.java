package com.jch.racWiFi.userOnboarding.view;

import com.jch.racWiFi.genericModels.GenericResponse;
import okhttp3.ResponseBody;
import retrofit2.Response;

public interface DeviceDetailsView {
    void onDeviceNotRemoved(int i, GenericResponse.GenericErrorBody genericErrorBody);

    void onDeviceRemoved();

    void onDeviceRenamed(String str);

    void onRenamingFailed(Response<ResponseBody> response);
}
