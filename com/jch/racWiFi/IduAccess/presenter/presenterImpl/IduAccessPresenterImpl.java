package com.jch.racWiFi.IduAccess.presenter.presenterImpl;

import com.jch.racWiFi.IduAccess.model.IduAccessModel;
import com.jch.racWiFi.IduAccess.network.IduAccessNetworkHelper;
import com.jch.racWiFi.IduAccess.presenter.IduAccessPresenter;
import com.jch.racWiFi.UserPermissions;
import com.jch.racWiFi.iduManagement.view.IHomePageView;
import com.jch.racWiFi.userManagement.model.dataProvider.ConfigurationDataProvider;
import java.util.HashMap;

public class IduAccessPresenterImpl implements IduAccessPresenter {
    private IHomePageView homePageView;
    private IduAccessModel iduAccessModelData;
    private PermissionAdapter permissionAdapter = new PermissionAdapter();

    public Boolean hasAccess(Integer num) {
        return null;
    }

    public IduAccessPresenterImpl(IHomePageView iHomePageView) {
        this.homePageView = iHomePageView;
    }

    public void fetchFunctionalAccessDatas() {
        IduAccessNetworkHelper.getInstance().fetchIduAccess();
        this.iduAccessModelData = ConfigurationDataProvider.getInstance().getIduAccessModelData();
    }

    public void updatePermissionsMap(IduAccessModel iduAccessModel) {
        HashMap<String, Boolean> permissionContainer = UserPermissions.getInstance().getPermissionContainer();
        if (iduAccessModel.getMap() != null) {
            this.permissionAdapter.updatePermissionContainer(permissionContainer, iduAccessModel);
            this.homePageView.onPermissionsUpdated();
        }
    }

    private static class PermissionAdapter {
        private PermissionAdapter() {
        }

        public void updatePermissionContainer(HashMap<String, Boolean> hashMap, IduAccessModel iduAccessModel) {
            UserPermissions.getInstance().setPermissions(iduAccessModel.getMap());
        }
    }
}
