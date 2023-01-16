package com.jch.racWiFi.IduAccess.presenter;

import com.jch.racWiFi.IduAccess.model.IduAccessModel;

public interface IduAccessPresenter {
    void fetchFunctionalAccessDatas();

    Boolean hasAccess(Integer num);

    void updatePermissionsMap(IduAccessModel iduAccessModel);
}
