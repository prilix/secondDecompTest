package com.jch.racWiFi.iduManagement.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.IduAccess.model.IduAccessModel;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.UserPermissions;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.amplitude.util.Screens;
import com.jch.racWiFi.customViews.customDialogs.ConfirmationDialog;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.databinding.WeeklyTimerMainFrameBinding;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.iduManagement.IduList;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.HolidayModeModel;
import com.jch.racWiFi.iduManagement.model.RacModelWiseConfigurationList;
import com.jch.racWiFi.iduManagement.model.ScheduleCount;
import com.jch.racWiFi.iduManagement.model.WeeklyTimerMode;
import com.jch.racWiFi.iduManagement.viewModel.HolidayModeViewModel;
import com.jch.racWiFi.iduManagement.viewModel.WeeklyTimerSelectDeviceViewModel;
import com.jch.racWiFi.userManagement.model.dataProvider.ConfigurationDataProvider;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import p015me.everything.android.p016ui.overscroll.OverScrollDecoratorHelper;

public class WeeklyTimerDevicesFragmentV2 extends GenericFragment implements View.OnClickListener {
    private DeviceRecyclerViewAdapter adapter;
    /* access modifiers changed from: private */
    public ArrayList<ScheduleCount> arrayList = new ArrayList<>();
    private WeeklyTimerMainFrameBinding binding;
    /* access modifiers changed from: private */
    public List<DetailedIduModel> detailedIduModelsList = new ArrayList();
    /* access modifiers changed from: private */
    public WeeklyTimerSelectDeviceViewModel viewModel;

    class DeviceRecyclerViewAdapter extends RecyclerView.Adapter<DeviceViewHolder> {
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public DetailedIduModel detailedIduModel;
        private List<DetailedIduModel> deviceRecyclerItemModelItemList;
        /* access modifiers changed from: private */
        public NavController mNavController;
        private DetailedIduModel mSelectedDevice;

        public class DeviceViewHolder_ViewBinding implements Unbinder {
            private DeviceViewHolder target;
            private View view7f0a0597;

            public DeviceViewHolder_ViewBinding(final DeviceViewHolder deviceViewHolder, View view) {
                this.target = deviceViewHolder;
                deviceViewHolder.mDeviceName = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_device_name, "field 'mDeviceName'", TextView.class);
                View findRequiredView = C0840Utils.findRequiredView(view, R.id.layout_manage_devices, "field 'mOuterLayout' and method 'onClickItem'");
                deviceViewHolder.mOuterLayout = (ConstraintLayout) C0840Utils.castView(findRequiredView, R.id.layout_manage_devices, "field 'mOuterLayout'", ConstraintLayout.class);
                this.view7f0a0597 = findRequiredView;
                findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                    public void doClick(View view) {
                        deviceViewHolder.onClickItem((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "onClickItem", 0, ConstraintLayout.class));
                    }
                });
            }

            public void unbind() {
                DeviceViewHolder deviceViewHolder = this.target;
                if (deviceViewHolder != null) {
                    this.target = null;
                    deviceViewHolder.mDeviceName = null;
                    deviceViewHolder.mOuterLayout = null;
                    this.view7f0a0597.setOnClickListener((View.OnClickListener) null);
                    this.view7f0a0597 = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        public DetailedIduModel getSelectedDevice() {
            return this.mSelectedDevice;
        }

        private DeviceRecyclerViewAdapter(Context context2, List<DetailedIduModel> list, NavController navController) {
            this.context = context2;
            this.deviceRecyclerItemModelItemList = list;
            this.mNavController = navController;
        }

        public DeviceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new DeviceViewHolder(LayoutInflater.from(this.context).inflate(R.layout.recycler_view_items_manage_devices, viewGroup, false));
        }

        public void onBindViewHolder(DeviceViewHolder deviceViewHolder, int i) {
            deviceViewHolder.bind(this.deviceRecyclerItemModelItemList.get(i));
        }

        public int getItemCount() {
            return this.deviceRecyclerItemModelItemList.size();
        }

        class DeviceViewHolder extends RecyclerView.ViewHolder {
            @BindView(2131364111)
            TextView mDeviceName;
            @BindView(2131363223)
            ConstraintLayout mOuterLayout;

            public DeviceViewHolder(View view) {
                super(view);
                ButterKnife.bind((Object) this, view);
            }

            @OnClick({2131363223})
            public void onClickItem(ConstraintLayout constraintLayout) {
                DeviceRecyclerViewAdapter.this.detailedIduModel = (DetailedIduModel) constraintLayout.getTag();
                if (!(Constants.IS_DEMO_MODE ? true : UserPermissions.getInstance().getIduPermission(UserPermissions.UserFeatures.WEEKLY_TIMER, DeviceRecyclerViewAdapter.this.detailedIduModel.f454id))) {
                    final SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(DeviceRecyclerViewAdapter.this.context);
                    singleChoiceDialog.setTitleString(DeviceRecyclerViewAdapter.this.context.getString(R.string.common_alert));
                    singleChoiceDialog.setMessageString(DeviceRecyclerViewAdapter.this.context.getString(R.string.common_alert_noPermissionToAccess));
                    singleChoiceDialog.setCancelable(false);
                    singleChoiceDialog.setPositiveButton(DeviceRecyclerViewAdapter.this.context.getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
                        public boolean onButtonClickListener(Dialog dialog, View view) {
                            singleChoiceDialog.dismiss();
                            return false;
                        }
                    });
                    singleChoiceDialog.show();
                } else if (DeviceRecyclerViewAdapter.this.detailedIduModel.isInErrorState()) {
                    SingleChoiceDialog singleChoiceDialog2 = new SingleChoiceDialog(WeeklyTimerDevicesFragmentV2.this.requireActivity());
                    singleChoiceDialog2.setTitleString(WeeklyTimerDevicesFragmentV2.this.getString(R.string.common_alert));
                    singleChoiceDialog2.setMessageString(String.format(WeeklyTimerDevicesFragmentV2.this.getString(R.string.notification_lbl_criticalErrorNotify), new Object[]{DeviceRecyclerViewAdapter.this.detailedIduModel.getName()}));
                    singleChoiceDialog2.setCancelable(false);
                    singleChoiceDialog2.setPositiveButton(WeeklyTimerDevicesFragmentV2.this.getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
                        public boolean onButtonClickListener(Dialog dialog, View view) {
                            return true;
                        }
                    });
                    singleChoiceDialog2.show();
                } else if (!DeviceRecyclerViewAdapter.this.detailedIduModel.isOnline()) {
                    SingleChoiceDialog singleChoiceDialog3 = new SingleChoiceDialog(WeeklyTimerDevicesFragmentV2.this.requireActivity());
                    singleChoiceDialog3.setTitleString(WeeklyTimerDevicesFragmentV2.this.getString(R.string.common_alert));
                    singleChoiceDialog3.setMessageString(WeeklyTimerDevicesFragmentV2.this.getString(R.string.errorCode_alert_PCF009));
                    singleChoiceDialog3.setCancelable(false);
                    singleChoiceDialog3.setPositiveButton(WeeklyTimerDevicesFragmentV2.this.getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
                        public boolean onButtonClickListener(Dialog dialog, View view) {
                            return true;
                        }
                    });
                    singleChoiceDialog3.show();
                } else if (DeviceRecyclerViewAdapter.this.detailedIduModel.isHolidayModeEnabled()) {
                    ConfirmationDialog confirmationDialog = new ConfirmationDialog(WeeklyTimerDevicesFragmentV2.this.getActivity());
                    confirmationDialog.setMessageString(WeeklyTimerDevicesFragmentV2.this.getResources().getString(R.string.holidayMode_alert_acRunningInHolidayMode));
                    confirmationDialog.setTitleString(WeeklyTimerDevicesFragmentV2.this.getActivity().getString(R.string.holidayMode_alert_stopHolidayMode));
                    confirmationDialog.setPositiveButton(WeeklyTimerDevicesFragmentV2.this.getActivity().getString(R.string.common_btn_yes), new C2030xe677cf64(this));
                    confirmationDialog.setNegativeButton(WeeklyTimerDevicesFragmentV2.this.getActivity().getString(R.string.common_btn_no), C2031xe677cf65.INSTANCE);
                    confirmationDialog.setParentView((View) null);
                    confirmationDialog.show();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(DetailedIduModel.PARCEL_KEY, DeviceRecyclerViewAdapter.this.detailedIduModel);
                    DeviceRecyclerViewAdapter.this.mNavController.navigate((int) R.id.action_weeklyTimerDevicesFragment_to_weeklyTimerFragmentV3, bundle);
                    DeviceRecyclerViewAdapter.this.notifyDataSetChanged();
                }
            }

            /* renamed from: lambda$onClickItem$0$com-jch-racWiFi-iduManagement-view-WeeklyTimerDevicesFragmentV2$DeviceRecyclerViewAdapter$DeviceViewHolder */
            public /* synthetic */ boolean mo30636x91c8304(Dialog dialog, View view) {
                dialog.dismiss();
                WeeklyTimerDevicesFragmentV2.this.interrputHolidayMode(DeviceRecyclerViewAdapter.this.detailedIduModel);
                return false;
            }

            public void bind(DetailedIduModel detailedIduModel) {
                this.mDeviceName.setText(detailedIduModel.getName());
                this.mOuterLayout.setTag(detailedIduModel);
            }
        }

        public DetailedIduModel getDetailedIduModel() {
            return this.detailedIduModel;
        }
    }

    public void onCreate(Bundle bundle) {
        IduList iduList;
        super.onCreate(bundle);
        if (this.mFragmentToActivityCallback.getNavController().getGraph().getArguments().get(DetailedIduModel.IDU_LIST) != null && (iduList = getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduList()) != null) {
            RacModelWiseConfigurationList racModelWiseConfigurationList = this.mFragmentToActivityCallback.getRacModelWiseConfigurationList();
            Iterator it = iduList.iterator();
            while (it.hasNext()) {
                DetailedIduModel detailedIduModel = (DetailedIduModel) it.next();
                if (racModelWiseConfigurationList.containsRacConfiguration(detailedIduModel.cloudId) && racModelWiseConfigurationList.getRacModelWiseDataBasedOnRacTypeId(detailedIduModel.cloudId).getEnableOptions().getEnableOption0().isWeeklyTimer()) {
                    this.detailedIduModelsList.add(detailedIduModel);
                }
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = (WeeklyTimerMainFrameBinding) DataBindingUtil.inflate(layoutInflater, R.layout.weekly_timer_main_frame, viewGroup, false);
        WeeklyTimerSelectDeviceViewModel weeklyTimerSelectDeviceViewModel = (WeeklyTimerSelectDeviceViewModel) ViewModelProviders.m35of((Fragment) this).get(WeeklyTimerSelectDeviceViewModel.class);
        this.viewModel = weeklyTimerSelectDeviceViewModel;
        this.binding.setWeeklyTimerSelcetDeviceViewModel(weeklyTimerSelectDeviceViewModel);
        this.binding.setLifecycleOwner(this);
        this.binding.imageButtonCopyWeeklyTimer.setOnClickListener(new WeeklyTimerDevicesFragmentV2$$ExternalSyntheticLambda0(this));
        this.binding.imageButtonMenu.setOnClickListener(new WeeklyTimerDevicesFragmentV2$$ExternalSyntheticLambda0(this));
        enableDisableCopyOptions(false);
        if (this.detailedIduModelsList.size() > 1) {
            showPleaseWaitDialog();
            this.viewModel.getWeeklyTimerScheduleCount();
        }
        initRecyclerView();
        liveDataObservers();
        logEvent(Screens.SCREENS.name(), 6);
        logEvents(Events.WEEKLY_TIMER_FREQUENCY.name(), 0);
        return this.binding.getRoot();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.image_button_copy_weekly_timer) {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("SCHEDULE_COUNT", this.arrayList);
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_weeklyTimerDevicesFragmentV2_to_weeklyTimerCopyTimerScheduleFragment, bundle);
        } else if (id == R.id.image_button_menu) {
            this.iDrawerMenuFunctions.openMenuDrawer();
        }
    }

    private void liveDataObservers() {
        this.viewModel.getScheduleCountMutableLiveData().observe(getActivity(), new WeeklyTimerDevicesFragmentV2$$ExternalSyntheticLambda2(this));
        try {
            ConfigurationDataProvider.getInstance().iduAccessModelMutableLiveData.observe(getViewLifecycleOwner(), new Observer<IduAccessModel>() {
                public void onChanged(IduAccessModel iduAccessModel) {
                    if (WeeklyTimerDevicesFragmentV2.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                        WeeklyTimerDevicesFragmentV2.this.dismissPleaseWaitDialog();
                        UserPermissions.getInstance().setPermissions(iduAccessModel.getMap());
                        int i = 0;
                        int i2 = 0;
                        for (DetailedIduModel detailedIduModel : WeeklyTimerDevicesFragmentV2.this.detailedIduModelsList) {
                            if (UserPermissions.getInstance().getIduPermission(UserPermissions.UserFeatures.WEEKLY_TIMER, detailedIduModel.f454id)) {
                                i++;
                                if (i2 == 0) {
                                    Iterator it = WeeklyTimerDevicesFragmentV2.this.arrayList.iterator();
                                    while (it.hasNext()) {
                                        ScheduleCount scheduleCount = (ScheduleCount) it.next();
                                        if (((long) detailedIduModel.f454id.intValue()) == scheduleCount.racId && scheduleCount.count > 0) {
                                            i2 = scheduleCount.count;
                                        }
                                    }
                                }
                            }
                        }
                        if (i < 2 || i2 <= 0) {
                            WeeklyTimerDevicesFragmentV2.this.enableDisableCopyOptions(false);
                        } else {
                            WeeklyTimerDevicesFragmentV2.this.enableDisableCopyOptions(true);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIndividualIduUpdateSingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), new WeeklyTimerDevicesFragmentV2$$ExternalSyntheticLambda1(this));
    }

    /* renamed from: lambda$liveDataObservers$0$com-jch-racWiFi-iduManagement-view-WeeklyTimerDevicesFragmentV2 */
    public /* synthetic */ void mo30627xfdc751c1(ScheduleCount.ScheduleCountResponse scheduleCountResponse) {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            if (scheduleCountResponse.throwable != null) {
                Toaster.makeToast(getActivity(), getResources().getString(R.string.common_alert_unableToConnectToNw), 0);
                dismissPleaseWaitDialog();
            } else if (scheduleCountResponse.isSuccessful()) {
                this.arrayList.clear();
                this.arrayList.addAll(Arrays.asList((ScheduleCount[]) scheduleCountResponse.response));
                this.viewModel.checkPermission();
            } else {
                dismissPleaseWaitDialog();
                int i = scheduleCountResponse.statusCode;
                if (i == 400) {
                    showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
                } else if (i == 401) {
                    showPleaseWaitDialog();
                    getCoreActivity().refreshToken(new CallBackListener() {
                        public void onFailure() {
                        }

                        public void onSuccess() {
                            WeeklyTimerDevicesFragmentV2.this.viewModel.getWeeklyTimerScheduleCount();
                        }
                    }, false);
                } else if (i != 404) {
                    showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
                } else {
                    showErrorPopUp(getString(scheduleCountResponse.getErrorMessageStringId(String.valueOf(scheduleCountResponse.statusCode))));
                }
            }
        }
    }

    /* renamed from: lambda$liveDataObservers$1$com-jch-racWiFi-iduManagement-view-WeeklyTimerDevicesFragmentV2 */
    public /* synthetic */ void mo30628x4b86c9c2(DetailedIduModel detailedIduModel) {
        DeviceRecyclerViewAdapter deviceRecyclerViewAdapter;
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED) && (deviceRecyclerViewAdapter = this.adapter) != null && deviceRecyclerViewAdapter.getDetailedIduModel() != null && this.adapter.getDetailedIduModel().equals(detailedIduModel)) {
            this.adapter.getDetailedIduModel().scheduletype = detailedIduModel.scheduletype;
        }
    }

    private void initRecyclerView() {
        this.adapter = new DeviceRecyclerViewAdapter(getActivity(), this.detailedIduModelsList, this.mFragmentToActivityCallback.getNavController());
        this.binding.include.recyclerViewDevices.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.binding.include.recyclerViewDevices.setHasFixedSize(true);
        this.binding.include.recyclerViewDevices.setAdapter(this.adapter);
        OverScrollDecoratorHelper.setUpOverScroll(this.binding.include.recyclerViewDevices, 0);
    }

    /* access modifiers changed from: private */
    public void enableDisableCopyOptions(boolean z) {
        if (z) {
            this.binding.imageButtonCopyWeeklyTimer.setEnabled(true);
            this.binding.imageButtonCopyWeeklyTimer.setAlpha(1.0f);
            return;
        }
        this.binding.imageButtonCopyWeeklyTimer.setEnabled(false);
        this.binding.imageButtonCopyWeeklyTimer.setAlpha(0.2f);
    }

    /* access modifiers changed from: private */
    public void interrputHolidayMode(final DetailedIduModel detailedIduModel) {
        HolidayModeViewModel holidayModeViewModel = (HolidayModeViewModel) ViewModelProviders.m35of((Fragment) this).get(HolidayModeViewModel.class);
        showPleaseWaitDialog();
        holidayModeViewModel.interrputToHolidayMode(detailedIduModel);
        holidayModeViewModel.interruptHolidayModeSinglLiveEvent().observeSingleEvent(getViewLifecycleOwner(), new Observer<HolidayModeModel.HolidayModeInterruptResponse>() {
            public void onChanged(HolidayModeModel.HolidayModeInterruptResponse holidayModeInterruptResponse) {
                if (WeeklyTimerDevicesFragmentV2.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                    WeeklyTimerDevicesFragmentV2.this.dismissPleaseWaitDialog();
                    if (holidayModeInterruptResponse.isSuccessful()) {
                        WeeklyTimerDevicesFragmentV2.this.mFragmentToActivityCallback.refreshIndividualIduState(WeeklyTimerDevicesFragmentV2.this.getCoreActivity(), detailedIduModel.f454id.intValue());
                        Toaster.makeToast(WeeklyTimerDevicesFragmentV2.this.getActivity(), WeeklyTimerDevicesFragmentV2.this.getResources().getString(R.string.holidayMode_alert_disabledSuccessFully), 1);
                        if (Constants.IS_DEMO_MODE) {
                            detailedIduModel.scheduletype = WeeklyTimerMode.SCHEDULE_DISABLED.name();
                            return;
                        }
                        return;
                    }
                    int i = holidayModeInterruptResponse.statusCode;
                    if (i == 401) {
                        WeeklyTimerDevicesFragmentV2.this.getCoreActivity().refreshToken(new CallBackListener() {
                            public void onFailure() {
                            }

                            public void onSuccess() {
                                WeeklyTimerDevicesFragmentV2.this.interrputHolidayMode(detailedIduModel);
                            }
                        }, false);
                    } else if (i == 403) {
                        WeeklyTimerDevicesFragmentV2 weeklyTimerDevicesFragmentV2 = WeeklyTimerDevicesFragmentV2.this;
                        weeklyTimerDevicesFragmentV2.showErrorPopUp(weeklyTimerDevicesFragmentV2.getString(R.string.errorCode_alert_FAE007));
                    } else if (i != 404) {
                        WeeklyTimerDevicesFragmentV2 weeklyTimerDevicesFragmentV22 = WeeklyTimerDevicesFragmentV2.this;
                        weeklyTimerDevicesFragmentV22.showErrorPopUp(weeklyTimerDevicesFragmentV22.getString(R.string.errorCode_alert_somethingWentWorng));
                    } else {
                        WeeklyTimerDevicesFragmentV2 weeklyTimerDevicesFragmentV23 = WeeklyTimerDevicesFragmentV2.this;
                        weeklyTimerDevicesFragmentV23.showErrorPopUp(weeklyTimerDevicesFragmentV23.getString(holidayModeInterruptResponse.getErrorMessageStringId(String.valueOf(holidayModeInterruptResponse.statusCode))));
                    }
                }
            }
        });
    }
}
