package com.jch.racWiFi.iduManagement.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavArgument;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.accord.common.utils.Logger;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jch.racWiFi.FragmentToActivityCallback;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.UserPermissions;
import com.jch.racWiFi.Utils.C1676Utils;
import com.jch.racWiFi.customViews.customDialogs.CopyScheduleConfirmationDialog;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.customViews.customWidgets.TriStateCheckbox;
import com.jch.racWiFi.databinding.WeeklyTimerCopyFrameBinding;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.iduManagement.model.CopySchedule;
import com.jch.racWiFi.iduManagement.model.CopyTimerScheduleModel;
import com.jch.racWiFi.iduManagement.model.CopyWeeklyTimerModel;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.ScheduleCount;
import com.jch.racWiFi.iduManagement.model.TimerEnabledModel;
import com.jch.racWiFi.iduManagement.model.WeeklyTimerModelV2;
import com.jch.racWiFi.iduManagement.viewModel.WeeklyTimerCopyTimerScheduleViewModel;
import com.jch_hitachi.aircloudglobal.R;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p015me.everything.android.p016ui.overscroll.OverScrollDecoratorHelper;

public class WeeklyTimerCopyTimerScheduleFragment extends GenericFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final boolean VALIDATION_APP_SIDE = true;
    /* access modifiers changed from: private */
    public static boolean isAllCheckBoxDisable = true;
    /* access modifiers changed from: private */
    public DeviceRecyclerViewAdapter adapter;
    private ArrayList<ScheduleCount> arrayList = new ArrayList<>();
    /* access modifiers changed from: private */
    public WeeklyTimerCopyFrameBinding binding;
    private ArrayList<CopyWeeklyTimerModel> copyWeeklyTimerModel = new ArrayList<>();
    private boolean isIgnoreOnCheckedChange;
    public long racIdFrom;
    /* access modifiers changed from: private */
    public String racName;
    /* access modifiers changed from: private */
    public WeeklyTimerCopyTimerScheduleViewModel viewModel;

    static class DeviceRecyclerViewAdapter extends RecyclerView.Adapter<DeviceViewHolder> {
        private Context context;
        /* access modifiers changed from: private */
        public List<CopyWeeklyTimerModel> copyWeeklyTimerModelList;
        /* access modifiers changed from: private */
        public SingleLiveEvent<Integer> isAllSelected;
        private FragmentToActivityCallback mFragmentToActivityCallback;
        /* access modifiers changed from: private */
        public long racTypeId;
        public ArrayList<WeeklyTimerModelV2.WeeklyTimerFactoryData> scheduledWeeklyTimerArrayList;

        public class DeviceViewHolder_ViewBinding implements Unbinder {
            private DeviceViewHolder target;
            private View view7f0a0597;

            public DeviceViewHolder_ViewBinding(final DeviceViewHolder deviceViewHolder, View view) {
                this.target = deviceViewHolder;
                deviceViewHolder.mDeviceName = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_device_name, "field 'mDeviceName'", TextView.class);
                deviceViewHolder.mDeviceStatus = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_device_status, "field 'mDeviceStatus'", TextView.class);
                deviceViewHolder.checkbox = (TriStateCheckbox) C0840Utils.findRequiredViewAsType(view, R.id.check_box_air_conditioner, "field 'checkbox'", TriStateCheckbox.class);
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
                    deviceViewHolder.mDeviceStatus = null;
                    deviceViewHolder.checkbox = null;
                    deviceViewHolder.mOuterLayout = null;
                    this.view7f0a0597.setOnClickListener((View.OnClickListener) null);
                    this.view7f0a0597 = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        private DeviceRecyclerViewAdapter(Context context2, List<CopyWeeklyTimerModel> list, FragmentToActivityCallback fragmentToActivityCallback) {
            this.isAllSelected = new SingleLiveEvent<>();
            this.scheduledWeeklyTimerArrayList = new ArrayList<>();
            this.context = context2;
            this.copyWeeklyTimerModelList = list;
            this.mFragmentToActivityCallback = fragmentToActivityCallback;
        }

        public DeviceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new DeviceViewHolder(LayoutInflater.from(this.context).inflate(R.layout.recycle_view_items_weekly_timer_devices, viewGroup, false));
        }

        public void onBindViewHolder(DeviceViewHolder deviceViewHolder, int i) {
            deviceViewHolder.bind(this.copyWeeklyTimerModelList.get(i), i);
        }

        public int getItemCount() {
            return this.copyWeeklyTimerModelList.size();
        }

        class DeviceViewHolder extends RecyclerView.ViewHolder {
            @BindView(2131362207)
            TriStateCheckbox checkbox;
            @BindView(2131364111)
            TextView mDeviceName;
            @BindView(2131364119)
            TextView mDeviceStatus;
            @BindView(2131363223)
            ConstraintLayout mOuterLayout;

            @OnClick({2131363223})
            public void onClickItem(ConstraintLayout constraintLayout) {
            }

            private DeviceViewHolder(View view) {
                super(view);
                ButterKnife.bind((Object) this, view);
                this.checkbox.setOnCheckedChangeListener(new C2021x4f518ed6(this));
            }

            /* renamed from: lambda$new$0$com-jch-racWiFi-iduManagement-view-WeeklyTimerCopyTimerScheduleFragment$DeviceRecyclerViewAdapter$DeviceViewHolder */
            public /* synthetic */ void mo30623x2f9a7972(CompoundButton compoundButton, boolean z) {
                ((CopyWeeklyTimerModel) DeviceRecyclerViewAdapter.this.copyWeeklyTimerModelList.get(getAdapterPosition())).setSelected(z);
                int i = 0;
                int i2 = 0;
                int i3 = 0;
                for (CopyWeeklyTimerModel copyWeeklyTimerModel : DeviceRecyclerViewAdapter.this.copyWeeklyTimerModelList) {
                    if (DeviceRecyclerViewAdapter.this.isSupportingModeAndTemperature(copyWeeklyTimerModel)) {
                        i3++;
                        if (copyWeeklyTimerModel.isSelected()) {
                            i2++;
                        }
                    }
                }
                if (i2 != 0) {
                    i = i2 != i3 ? 1 : 2;
                }
                DeviceRecyclerViewAdapter.this.isAllSelected.postValue(Integer.valueOf(i));
            }

            public void bind(CopyWeeklyTimerModel copyWeeklyTimerModel, int i) {
                this.mDeviceName.setText(copyWeeklyTimerModel.getName());
                this.mOuterLayout.setTag(copyWeeklyTimerModel);
                this.checkbox.setChecked(Boolean.valueOf(copyWeeklyTimerModel.isSelected()));
                if (DeviceRecyclerViewAdapter.this.racTypeId <= 0) {
                    return;
                }
                if (DeviceRecyclerViewAdapter.this.isSupportingModeAndTemperature(copyWeeklyTimerModel)) {
                    this.checkbox.setEnabled(true);
                    this.checkbox.setAlpha(1.0f);
                    this.mDeviceName.setAlpha(1.0f);
                    this.mDeviceStatus.setVisibility(8);
                    return;
                }
                this.checkbox.setEnabled(false);
                this.checkbox.setAlpha(0.5f);
                this.mDeviceName.setAlpha(0.5f);
                this.checkbox.setChecked(false);
                this.mDeviceStatus.setVisibility(0);
                WeeklyTimerCopyTimerScheduleFragment.isAllCheckBoxDisable = false;
            }
        }

        /* access modifiers changed from: private */
        public SingleLiveEvent<Integer> getIsAllSelected() {
            return this.isAllSelected;
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Removed duplicated region for block: B:3:0x001a  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean isSupportingModeAndTemperature(com.jch.racWiFi.iduManagement.model.CopyWeeklyTimerModel r11) {
            /*
                r10 = this;
                com.jch.racWiFi.FragmentToActivityCallback r0 = r10.mFragmentToActivityCallback
                java.lang.String r11 = r11.getRacCloudId()
                com.jch.racWiFi.iduManagement.model.RacModelWiseData r11 = r0.getRacModelWiseDataBasedOnRacTypeId(r11)
                com.jch.racWiFi.iduManagement.model.RacModelWiseData$RacModeDetailList r11 = r11.getRacModeDetails()
                java.util.ArrayList<com.jch.racWiFi.iduManagement.model.WeeklyTimerModelV2$WeeklyTimerFactoryData> r0 = r10.scheduledWeeklyTimerArrayList
                java.util.Iterator r0 = r0.iterator()
            L_0x0014:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x0045
                java.lang.Object r1 = r0.next()
                com.jch.racWiFi.iduManagement.model.WeeklyTimerModelV2$WeeklyTimerFactoryData r1 = (com.jch.racWiFi.iduManagement.model.WeeklyTimerModelV2.WeeklyTimerFactoryData) r1
                java.lang.String r2 = r1.mode
                com.jch.racWiFi.iduManagement.model.OperationMode r2 = com.jch.racWiFi.iduManagement.model.OperationMode.valueOf(r2)
                com.jch.racWiFi.iduManagement.model.RacModelWiseData$RacModeDetail r2 = r11.getRacModeDetailBasedOnMode(r2)
                r3 = 0
                if (r2 != 0) goto L_0x002e
                return r3
            L_0x002e:
                long r4 = r2.getMinTemperature()
                double r4 = (double) r4
                long r6 = r2.getMaxTemperature()
                double r6 = (double) r6
                double r8 = r1.temperature
                int r2 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
                if (r2 > 0) goto L_0x0044
                double r1 = r1.temperature
                int r4 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
                if (r4 <= 0) goto L_0x0014
            L_0x0044:
                return r3
            L_0x0045:
                r11 = 1
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.view.WeeklyTimerCopyTimerScheduleFragment.DeviceRecyclerViewAdapter.isSupportingModeAndTemperature(com.jch.racWiFi.iduManagement.model.CopyWeeklyTimerModel):boolean");
        }

        /* access modifiers changed from: private */
        public void setRacTypeIdFrom(String str) {
            this.racTypeId = this.mFragmentToActivityCallback.getRacModelWiseDataBasedOnRacTypeId(str).getRacTypeId();
        }

        /* access modifiers changed from: private */
        public void scheduledWeeklyTimer(ArrayList<WeeklyTimerModelV2.WeeklyTimerFactoryData> arrayList) {
            this.scheduledWeeklyTimerArrayList.clear();
            ArrayList arrayList2 = new ArrayList();
            Iterator<WeeklyTimerModelV2.WeeklyTimerFactoryData> it = arrayList.iterator();
            while (it.hasNext()) {
                WeeklyTimerModelV2.WeeklyTimerFactoryData next = it.next();
                if (!arrayList2.equals(next.mode)) {
                    arrayList2.add(next);
                }
            }
            this.scheduledWeeklyTimerArrayList.addAll(arrayList2);
        }

        /* access modifiers changed from: private */
        public List<CopyWeeklyTimerModel> getCopyWeeklyTimerModelList() {
            return this.copyWeeklyTimerModelList;
        }
    }

    public void onCreate(Bundle bundle) {
        List<DetailedIduModel> list;
        super.onCreate(bundle);
        NavArgument navArgument = this.mFragmentToActivityCallback.getNavController().getGraph().getArguments().get(DetailedIduModel.IDU_LIST);
        ArrayList parcelableArrayList = getArguments().getParcelableArrayList("SCHEDULE_COUNT");
        if (parcelableArrayList != null) {
            this.arrayList.addAll(parcelableArrayList);
        }
        if (navArgument != null && (list = (List) navArgument.getDefaultValue()) != null) {
            for (DetailedIduModel detailedIduModel : list) {
                if (UserPermissions.getInstance().getIduPermission(UserPermissions.UserFeatures.WEEKLY_TIMER, detailedIduModel.f454id) && !detailedIduModel.isInErrorState() && detailedIduModel.isOnline()) {
                    this.copyWeeklyTimerModel.add(new CopyWeeklyTimerModel(detailedIduModel.f454id.intValue(), detailedIduModel.name, detailedIduModel.modelTypeId, detailedIduModel.cloudId));
                }
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.binding = (WeeklyTimerCopyFrameBinding) DataBindingUtil.inflate(layoutInflater, R.layout.weekly_timer_copy_frame, viewGroup, false);
        WeeklyTimerCopyTimerScheduleViewModel weeklyTimerCopyTimerScheduleViewModel = (WeeklyTimerCopyTimerScheduleViewModel) ViewModelProviders.m35of((Fragment) this).get(WeeklyTimerCopyTimerScheduleViewModel.class);
        this.viewModel = weeklyTimerCopyTimerScheduleViewModel;
        this.binding.setWeeklyTimerCopyTimerScheduleViewModel(weeklyTimerCopyTimerScheduleViewModel);
        this.viewModel.init(this.copyWeeklyTimerModel, this.arrayList);
        this.binding.setLifecycleOwner(this);
        this.binding.include.layoutDeviceName.setOnClickListener(new WeeklyTimerCopyTimerScheduleFragment$$ExternalSyntheticLambda0(this));
        this.binding.imageButtonMenu.setOnClickListener(new WeeklyTimerCopyTimerScheduleFragment$$ExternalSyntheticLambda0(this));
        this.binding.textViewSave.setOnClickListener(new WeeklyTimerCopyTimerScheduleFragment$$ExternalSyntheticLambda0(this));
        this.binding.include.checkBoxAllAirConditioners.setOnCheckedChangeListener(this);
        initSpinnerDropDownList();
        initRecyclerView();
        liveDataObservers();
        this.adapter.getIsAllSelected().observe(getViewLifecycleOwner(), new WeeklyTimerCopyTimerScheduleFragment$$ExternalSyntheticLambda3(this));
        this.binding.include.spinnerDeviceName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (!WeeklyTimerCopyTimerScheduleFragment.this.binding.include.checkBoxAllAirConditioners.isChecked().booleanValue()) {
                    WeeklyTimerCopyTimerScheduleFragment.this.viewModel.selectAllItems(false);
                } else {
                    WeeklyTimerCopyTimerScheduleFragment.this.binding.include.checkBoxAllAirConditioners.setChecked(false);
                }
                WeeklyTimerCopyTimerScheduleFragment weeklyTimerCopyTimerScheduleFragment = WeeklyTimerCopyTimerScheduleFragment.this;
                weeklyTimerCopyTimerScheduleFragment.racIdFrom = (long) weeklyTimerCopyTimerScheduleFragment.viewModel.getSpinnerArrayList().get(i).getId();
                WeeklyTimerCopyTimerScheduleFragment.this.adapter.setRacTypeIdFrom(WeeklyTimerCopyTimerScheduleFragment.this.viewModel.getSpinnerArrayList().get(i).getRacCloudId());
                WeeklyTimerCopyTimerScheduleFragment.this.adapter.scheduledWeeklyTimerArrayList.clear();
                WeeklyTimerCopyTimerScheduleFragment weeklyTimerCopyTimerScheduleFragment2 = WeeklyTimerCopyTimerScheduleFragment.this;
                weeklyTimerCopyTimerScheduleFragment2.racName = weeklyTimerCopyTimerScheduleFragment2.viewModel.getSpinnerArrayList().get(i).getName();
                WeeklyTimerCopyTimerScheduleFragment.this.viewModel.refreshListExecptSelectedItem(WeeklyTimerCopyTimerScheduleFragment.this.racIdFrom);
                WeeklyTimerCopyTimerScheduleFragment.this.showPleaseWaitDialog();
                WeeklyTimerCopyTimerScheduleFragment.this.viewModel.getWeeklyTimerDataList(WeeklyTimerCopyTimerScheduleFragment.this.racIdFrom);
            }
        });
        return this.binding.getRoot();
    }

    /* renamed from: lambda$onCreateView$0$com-jch-racWiFi-iduManagement-view-WeeklyTimerCopyTimerScheduleFragment */
    public /* synthetic */ void mo30614x76c04362(Integer num) {
        this.isIgnoreOnCheckedChange = true;
        int intValue = num.intValue();
        if (intValue == 0) {
            this.binding.include.ivPartial.setVisibility(8);
            this.binding.include.checkBoxAllAirConditioners.setChecked(false);
        } else if (intValue == 1) {
            this.binding.include.checkBoxAllAirConditioners.setChecked(false);
            this.binding.include.ivPartial.setVisibility(0);
        } else if (intValue == 2) {
            this.binding.include.ivPartial.setVisibility(8);
            this.binding.include.checkBoxAllAirConditioners.setChecked(true);
        }
        this.isIgnoreOnCheckedChange = false;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (!this.isIgnoreOnCheckedChange) {
            this.viewModel.selectAllItems(z);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.image_button_menu) {
            this.iDrawerMenuFunctions.openMenuDrawer();
        } else if (id == R.id.layout_device_name) {
            this.binding.include.spinnerDeviceName.performClick();
        } else if (id == R.id.text_view_save) {
            if (this.viewModel.getSelectedItems().size() > 0) {
                confirmationForChanges();
            } else {
                alertDialog(getResources().getString(R.string.weeklyTimer_alert_atleastOneAcCopySch), false);
            }
        }
    }

    private void liveDataObservers() {
        this.viewModel.getRefreshedList().observe(getViewLifecycleOwner(), new Observer<ArrayList<CopyWeeklyTimerModel>>() {
            public void onChanged(ArrayList<CopyWeeklyTimerModel> arrayList) {
                boolean z;
                Iterator it = WeeklyTimerCopyTimerScheduleFragment.this.adapter.getCopyWeeklyTimerModelList().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    if (WeeklyTimerCopyTimerScheduleFragment.this.adapter.isSupportingModeAndTemperature((CopyWeeklyTimerModel) it.next())) {
                        z = true;
                        break;
                    }
                }
                WeeklyTimerCopyTimerScheduleFragment.this.enableDisableView(z);
                WeeklyTimerCopyTimerScheduleFragment.this.adapter.notifyDataSetChanged();
            }
        });
        this.viewModel.getCopyTimerScheduleLiveDataResponse().observe(getViewLifecycleOwner(), new WeeklyTimerCopyTimerScheduleFragment$$ExternalSyntheticLambda1(this));
        this.viewModel.getTimerEnabledResponseMutableLiveData().observe(requireActivity(), new WeeklyTimerCopyTimerScheduleFragment$$ExternalSyntheticLambda2(this));
        this.viewModel.getWeeklyTimerModelsMutableLiveData().observeSingleEvent(new Observer<WeeklyTimerModelV2.WeeklyTimerFetchResponse>() {
            public void onChanged(WeeklyTimerModelV2.WeeklyTimerFetchResponse weeklyTimerFetchResponse) {
                WeeklyTimerCopyTimerScheduleFragment.this.dismissPleaseWaitDialog();
                if (weeklyTimerFetchResponse.throwable != null) {
                    WeeklyTimerCopyTimerScheduleFragment.this.connectionFailedDialog();
                    return;
                }
                if (!weeklyTimerFetchResponse.isSuccessful()) {
                    int i = weeklyTimerFetchResponse.statusCode;
                    if (i == 400) {
                        WeeklyTimerCopyTimerScheduleFragment weeklyTimerCopyTimerScheduleFragment = WeeklyTimerCopyTimerScheduleFragment.this;
                        weeklyTimerCopyTimerScheduleFragment.showErrorPopUp(weeklyTimerCopyTimerScheduleFragment.getString(R.string.errorCode_alert_somethingWentWorng));
                    } else if (i == 401) {
                        WeeklyTimerCopyTimerScheduleFragment.this.showPleaseWaitDialog();
                        WeeklyTimerCopyTimerScheduleFragment.this.getCoreActivity().refreshToken(new CallBackListener() {
                            public void onFailure() {
                            }

                            public void onSuccess() {
                                WeeklyTimerCopyTimerScheduleFragment.this.viewModel.getWeeklyTimerDataList(WeeklyTimerCopyTimerScheduleFragment.this.racIdFrom);
                            }
                        }, false);
                    } else if (i == 404) {
                        WeeklyTimerCopyTimerScheduleFragment weeklyTimerCopyTimerScheduleFragment2 = WeeklyTimerCopyTimerScheduleFragment.this;
                        weeklyTimerCopyTimerScheduleFragment2.showErrorPopUp(weeklyTimerCopyTimerScheduleFragment2.getString(weeklyTimerFetchResponse.getErrorMessageStringId(String.valueOf(weeklyTimerFetchResponse.statusCode))));
                    } else if (weeklyTimerFetchResponse.statusCode == 404) {
                        WeeklyTimerCopyTimerScheduleFragment weeklyTimerCopyTimerScheduleFragment3 = WeeklyTimerCopyTimerScheduleFragment.this;
                        weeklyTimerCopyTimerScheduleFragment3.alertDialog(weeklyTimerCopyTimerScheduleFragment3.getResources().getString(R.string.common_alert_acNotExist), true);
                    } else if (weeklyTimerFetchResponse.statusCode == 412) {
                        WeeklyTimerCopyTimerScheduleFragment weeklyTimerCopyTimerScheduleFragment4 = WeeklyTimerCopyTimerScheduleFragment.this;
                        weeklyTimerCopyTimerScheduleFragment4.alertDialog(weeklyTimerCopyTimerScheduleFragment4.getResources().getString(R.string.weeklyTimer_alert_schNotFound), true);
                    }
                } else if (((WeeklyTimerModelV2.WeeklyTimerResponseData[]) weeklyTimerFetchResponse.response).length > 0) {
                    ArrayList arrayList = new ArrayList();
                    for (WeeklyTimerModelV2.WeeklyTimerResponseData copy : (WeeklyTimerModelV2.WeeklyTimerResponseData[]) weeklyTimerFetchResponse.response) {
                        WeeklyTimerModelV2.WeeklyTimerFactoryData weeklyTimerFactoryData = new WeeklyTimerModelV2.WeeklyTimerFactoryData();
                        weeklyTimerFactoryData.copy(copy);
                        arrayList.add(weeklyTimerFactoryData);
                    }
                    WeeklyTimerCopyTimerScheduleFragment.this.adapter.scheduledWeeklyTimer(arrayList);
                    WeeklyTimerCopyTimerScheduleFragment.this.viewModel.refreshListExecptSelectedItem(WeeklyTimerCopyTimerScheduleFragment.this.racIdFrom);
                }
            }
        });
    }

    /* renamed from: lambda$liveDataObservers$1$com-jch-racWiFi-iduManagement-view-WeeklyTimerCopyTimerScheduleFragment */
    public /* synthetic */ void mo30612xa37eee10(CopyTimerScheduleModel.CopyTimerScheduleResponse copyTimerScheduleResponse) {
        dismissPleaseWaitDialog();
        if (copyTimerScheduleResponse.throwable != null) {
            if (copyTimerScheduleResponse.throwable instanceof SocketException) {
                Logger.m49i("", "");
            } else {
                connectionFailedDialog();
            }
        } else if (copyTimerScheduleResponse.statusCode == 200) {
            alertDialog(getResources().getString(R.string.weeklyTimer_alert_copiedSuccess), true);
        } else {
            int i = copyTimerScheduleResponse.statusCode;
            if (i != 400) {
                if (i == 401) {
                    getCoreActivity().refreshToken(new CallBackListener() {
                        public void onFailure() {
                        }

                        public void onSuccess() {
                            WeeklyTimerCopyTimerScheduleFragment.this.showPleaseWaitDialog();
                            WeeklyTimerCopyTimerScheduleFragment.this.viewModel.copyTimerScheduleToOthersRacs(new CopySchedule.RacWise(WeeklyTimerCopyTimerScheduleFragment.this.racIdFrom, C1676Utils.convert(WeeklyTimerCopyTimerScheduleFragment.this.viewModel.getSelectedItems())));
                        }
                    }, false);
                } else if (i == 404) {
                    showErrorPopUp(getString(copyTimerScheduleResponse.getErrorMessageStringId(String.valueOf(copyTimerScheduleResponse.statusCode))));
                } else if (copyTimerScheduleResponse.statusCode == 207) {
                    try {
                        JSONObject jSONObject = new JSONObject(copyTimerScheduleResponse.response.toString());
                        if (!jSONObject.getBoolean("allSucceeded")) {
                            ArrayList arrayList2 = new ArrayList();
                            JSONArray jSONArray = jSONObject.getJSONArray("resultSet");
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                                int i3 = jSONObject2.getInt("racId");
                                Iterator<CopyWeeklyTimerModel> it = this.copyWeeklyTimerModel.iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        break;
                                    }
                                    CopyWeeklyTimerModel next = it.next();
                                    if (i3 == next.getId()) {
                                        if (!jSONObject2.getBoolean(FirebaseAnalytics.Param.SUCCESS)) {
                                            arrayList2.add(next.getName());
                                        }
                                    }
                                }
                            }
                            copyScheduleFailedAlertDialog(arrayList2);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (copyTimerScheduleResponse.statusCode == 406) {
                    alertDialog(getResources().getString(R.string.weeklyTimer_alert_maxSchReached), false);
                } else if (copyTimerScheduleResponse.statusCode == 409) {
                    alertDialog(getResources().getString(R.string.weeklyTimer_alert_conflictExistingSch), false);
                } else if (copyTimerScheduleResponse.statusCode >= 412) {
                    alertDialog(getResources().getString(R.string.weeklyTimer_alert_schNotFound), true);
                } else if (copyTimerScheduleResponse.statusCode >= 500) {
                    alertDialog(getResources().getString(R.string.common_alert_somethingWentWrong), false);
                } else {
                    alertDialog(getResources().getString(R.string.weeklyTimer_alert_failedToCopyWeeklyTimer), false);
                }
            } else {
                showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
            }
        }
    }

    /* renamed from: lambda$liveDataObservers$2$com-jch-racWiFi-iduManagement-view-WeeklyTimerCopyTimerScheduleFragment */
    public /* synthetic */ void mo30613x6a8ad511(TimerEnabledModel.TimerEnabledResponse timerEnabledResponse) {
        dismissPleaseWaitDialog();
        alertDialog(getResources().getString(R.string.weeklyTimer_alert_timerCopied), false);
    }

    /* access modifiers changed from: private */
    public void enableDisableView(boolean z) {
        if (z) {
            this.binding.include.checkBoxAllAirConditioners.setEnabled(true);
            this.binding.include.checkBoxAllAirConditioners.setAlpha(1.0f);
            this.binding.textViewSave.setEnabled(true);
            this.binding.textViewSave.setAlpha(1.0f);
            return;
        }
        this.binding.include.checkBoxAllAirConditioners.setEnabled(false);
        this.binding.include.checkBoxAllAirConditioners.setAlpha(0.5f);
        this.binding.textViewSave.setEnabled(false);
        this.binding.textViewSave.setAlpha(0.5f);
    }

    private void initSpinnerDropDownList() {
        ArrayAdapter arrayAdapter = new ArrayAdapter(requireActivity(), R.layout.spinner_item, this.viewModel.getItemForDropdown());
        arrayAdapter.setDropDownViewResource(R.layout.spinner_drop_down_item);
        this.binding.include.spinnerDeviceName.setAdapter(arrayAdapter);
    }

    private void initRecyclerView() {
        this.adapter = new DeviceRecyclerViewAdapter(getActivity(), this.viewModel.getRefreshedList().getValue(), this.mFragmentToActivityCallback);
        this.binding.include.recyclerViewDevices.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.binding.include.recyclerViewDevices.setHasFixedSize(true);
        this.binding.include.recyclerViewDevices.setAdapter(this.adapter);
        OverScrollDecoratorHelper.setUpOverScroll(this.binding.include.recyclerViewDevices, 0);
    }

    private void copyScheduleFailedAlertDialog(ArrayList<String> arrayList2) {
        String format = String.format(getResources().getString(R.string.weeklyTimer_alert_unableToCopyRACWise), new Object[]{arrayList2.toString()});
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(requireActivity());
        singleChoiceDialog.setTitleString(getActivity().getString(R.string.common_alert));
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setMessageString(format);
        singleChoiceDialog.setPositiveButton(getActivity().getString(R.string.common_btn_ok), new WeeklyTimerCopyTimerScheduleFragment$$ExternalSyntheticLambda7(this, singleChoiceDialog));
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            singleChoiceDialog.show();
        }
    }

    /* renamed from: lambda$copyScheduleFailedAlertDialog$3$com-jch-racWiFi-iduManagement-view-WeeklyTimerCopyTimerScheduleFragment */
    public /* synthetic */ boolean mo30611x8c370dc6(SingleChoiceDialog singleChoiceDialog, Dialog dialog, View view) {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
        singleChoiceDialog.dismiss();
        return false;
    }

    private void confirmationForChanges() {
        CopyScheduleConfirmationDialog copyScheduleConfirmationDialog = new CopyScheduleConfirmationDialog(requireActivity());
        copyScheduleConfirmationDialog.setDialogTitle(getActivity().getString(R.string.weeklyTimer_alert_copySchedules));
        copyScheduleConfirmationDialog.setDialogSubTitle(getActivity().getString(R.string.weeklyTimer_alert_confirmCopyDesc));
        copyScheduleConfirmationDialog.setCopyFromValueStr(this.racName);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.viewModel.getSelectedItems().size(); i++) {
            long longValue = this.viewModel.getSelectedItems().get(i).longValue();
            if (i == 0) {
                sb.append(this.viewModel.getRacName(longValue));
            } else {
                sb.append(", " + this.viewModel.getRacName(longValue));
            }
        }
        copyScheduleConfirmationDialog.setCopyToValueStr(sb.toString());
        copyScheduleConfirmationDialog.setCancelable(false);
        copyScheduleConfirmationDialog.setParentView((View) null);
        copyScheduleConfirmationDialog.setPositiveButton(getResources().getString(R.string.common_btn_continue), new WeeklyTimerCopyTimerScheduleFragment$$ExternalSyntheticLambda4(this));
        copyScheduleConfirmationDialog.setNegativeButton(getResources().getString(R.string.common_btn_cancel), WeeklyTimerCopyTimerScheduleFragment$$ExternalSyntheticLambda5.INSTANCE);
        copyScheduleConfirmationDialog.show();
    }

    /* renamed from: lambda$confirmationForChanges$4$com-jch-racWiFi-iduManagement-view-WeeklyTimerCopyTimerScheduleFragment */
    public /* synthetic */ boolean mo30610x69b0bb35(Dialog dialog, View view) {
        dialog.dismiss();
        showPleaseWaitDialog();
        this.viewModel.copyTimerScheduleToOthersRacs(new CopySchedule.RacWise(this.racIdFrom, C1676Utils.convert(this.viewModel.getSelectedItems())));
        return false;
    }

    /* access modifiers changed from: private */
    public void connectionFailedDialog() {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(requireActivity());
        singleChoiceDialog.setTitleString(getActivity().getString(R.string.common_alert));
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setMessageString(getActivity().getString(R.string.common_alert_unableToConnectToNw));
        singleChoiceDialog.setPositiveButton(getActivity().getString(R.string.common_btn_ok), new WeeklyTimerCopyTimerScheduleFragment$$ExternalSyntheticLambda6(singleChoiceDialog));
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            singleChoiceDialog.show();
        }
    }
}
