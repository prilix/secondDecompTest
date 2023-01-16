package com.jch.racWiFi.userManagement.view;

import com.jch.racWiFi.userManagement.model.dto.AllPermissionDataDto;
import okhttp3.ResponseBody;
import retrofit2.Response;

public interface IDevicePermissionView {
    void fetchPermissionResponseDatas(Response<AllPermissionDataDto> response);

    void onAllCheckedStatusEvaluated(Boolean[] boolArr);

    void savePermissionResponseDatas(Response<ResponseBody> response);
}
