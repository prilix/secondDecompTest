package com.jch.racWiFi.userOnboarding.view.viewImpl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.amplitude.util.Screens;
import com.jch.racWiFi.databinding.ManageDevicesFrameBinding;
import com.jch.racWiFi.iduManagement.IduList;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.userOnboarding.model.IduDetailsModel;
import com.jch.racWiFi.userOnboarding.view.IManageDevicesView;
import com.jch.racWiFi.userOnboarding.view.uiComponents.recyclerAdapters.IduDetailsAdapter;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.List;
import p015me.everything.android.p016ui.overscroll.OverScrollDecoratorHelper;

public class ManageDevicesViewFragmentV2 extends GenericFragment implements IManageDevicesView {
    private IduDetailsAdapter iduDetailsAdapter;
    private ManageDevicesFrameBinding mBinding;
    private int mListSize;

    public void onFetchingFailed() {
    }

    public void onStartedFetching() {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (ManageDevicesFrameBinding) DataBindingUtil.inflate(layoutInflater, R.layout.manage_devices_frame, viewGroup, false);
        logEvent(Screens.SCREENS.name(), 4);
        logEvents(Events.MANAGE_AC_FREQUENCY.name(), 0);
        return this.mBinding.getRoot();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.iduDetailsAdapter = new IduDetailsAdapter(this);
        if (getActivity() != null) {
            this.mBinding.includedLayout.recyclerViewDevices.addItemDecoration(new DividerItemDecoration(getActivity(), 1));
            this.mBinding.includedLayout.recyclerViewDevices.setAdapter(this.iduDetailsAdapter);
            this.mBinding.includedLayout.recyclerViewDevices.setLayoutManager(new LinearLayoutManager(getActivity()));
            OverScrollDecoratorHelper.setUpOverScroll(this.mBinding.includedLayout.recyclerViewDevices, 0);
        }
    }

    public void onResume() {
        super.onResume();
        ArrayList arrayList = new ArrayList();
        IduList iduList = getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduList();
        for (int i = 0; i < iduList.size(); i++) {
            IduDetailsModel iduDetailsModel = new IduDetailsModel();
            iduDetailsModel.copyFromDetailIduModel((DetailedIduModel) iduList.get(i));
            arrayList.add(iduDetailsModel);
        }
        onFetchingSuccess(arrayList);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.includedLayout.layoutAddDevice.setOnClickListener(new ManageDevicesViewFragmentV2$$ExternalSyntheticLambda0(this));
        this.mBinding.imageButtonMenu.setOnClickListener(new ManageDevicesViewFragmentV2$$ExternalSyntheticLambda1(this));
    }

    /* renamed from: lambda$onViewCreated$0$com-jch-racWiFi-userOnboarding-view-viewImpl-ManageDevicesViewFragmentV2 */
    public /* synthetic */ void mo33548x9eca2541(View view) {
        onAddDevice();
    }

    /* renamed from: lambda$onViewCreated$1$com-jch-racWiFi-userOnboarding-view-viewImpl-ManageDevicesViewFragmentV2 */
    public /* synthetic */ void mo33549xb93b1e60(View view) {
        onClickMenu();
    }

    public void onAddDevice() {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_manageDevicesFragment_to_on_board_nav_graph);
    }

    public void onFetchingSuccess(List<IduDetailsModel> list) {
        this.mListSize = list.size();
        if (!getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.CREATED);
        }
        this.iduDetailsAdapter.setIduDetailsModels((ArrayList) list);
    }

    public void onClickMenu() {
        this.iDrawerMenuFunctions.openMenuDrawer();
    }
}
