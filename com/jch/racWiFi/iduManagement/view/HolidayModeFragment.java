package com.jch.racWiFi.iduManagement.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.Utils.DateAndTimeUtils;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.amplitude.util.Screens;
import com.jch.racWiFi.customViews.customDialogs.ConfirmationDialog;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.customViews.customWidgets.TriStateCheckbox;
import com.jch.racWiFi.databinding.LayoutHolidayModeBinding;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.iduManagement.IduList;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.HolidayModeModel;
import com.jch.racWiFi.iduManagement.view.SetTemperatureHolidayModeDialog;
import com.jch.racWiFi.iduManagement.viewModel.HolidayModeViewModel;
import com.jch.racWiFi.settings.model.TemperatureUnit;
import com.jch_hitachi.aircloudglobal.R;
import com.suke.widget.SwitchButton;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class HolidayModeFragment extends GenericFragment implements View.OnClickListener {
    private final Float DEFAULT_TEMPERATURE_VALUE;
    /* access modifiers changed from: private */
    public DeviceRecyclerViewAdapter adapter;
    /* access modifiers changed from: private */
    public LayoutHolidayModeBinding binding;
    private boolean isIgnoreOnCheckedChange;
    /* access modifiers changed from: private */
    public Temperature mTemperature;
    /* access modifiers changed from: private */
    public HolidayModeViewModel viewModel;

    public enum HolidayMode {
        HOLIDAY_MODE_ENABLED,
        SCHEDULE_DISABLED
    }

    static class DeviceRecyclerViewAdapter extends RecyclerView.Adapter<DeviceViewHolder> {
        private List<Integer> arrayInteger;
        private Context context;
        /* access modifiers changed from: private */
        public List<HolidayModeModel> holidayModeModelList;
        private SingleLiveEvent<Boolean> isAllSelected;
        /* access modifiers changed from: private */
        public boolean isHolidayModeDisable;

        public class DeviceViewHolder_ViewBinding implements Unbinder {
            private DeviceViewHolder target;
            private View view7f0a0597;

            public DeviceViewHolder_ViewBinding(final DeviceViewHolder deviceViewHolder, View view) {
                this.target = deviceViewHolder;
                deviceViewHolder.mDeviceName = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_device_name, "field 'mDeviceName'", TextView.class);
                deviceViewHolder.mImageAcs = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_acs, "field 'mImageAcs'", ImageView.class);
                deviceViewHolder.checkbox = (TriStateCheckbox) C0840Utils.findRequiredViewAsType(view, R.id.cb_member_permission, "field 'checkbox'", TriStateCheckbox.class);
                View findRequiredView = C0840Utils.findRequiredView(view, R.id.layout_manage_devices, "field 'mOuterLayout' and method 'onClickItem'");
                deviceViewHolder.mOuterLayout = (ConstraintLayout) C0840Utils.castView(findRequiredView, R.id.layout_manage_devices, "field 'mOuterLayout'", ConstraintLayout.class);
                this.view7f0a0597 = findRequiredView;
                findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                    public void doClick(View view) {
                        deviceViewHolder.onClickItem((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "onClickItem", 0, ConstraintLayout.class));
                    }
                });
                deviceViewHolder.mTextViewFeatureNotSupperated = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_does_not_support_featue, "field 'mTextViewFeatureNotSupperated'", TextView.class);
            }

            public void unbind() {
                DeviceViewHolder deviceViewHolder = this.target;
                if (deviceViewHolder != null) {
                    this.target = null;
                    deviceViewHolder.mDeviceName = null;
                    deviceViewHolder.mImageAcs = null;
                    deviceViewHolder.checkbox = null;
                    deviceViewHolder.mOuterLayout = null;
                    deviceViewHolder.mTextViewFeatureNotSupperated = null;
                    this.view7f0a0597.setOnClickListener((View.OnClickListener) null);
                    this.view7f0a0597 = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        private DeviceRecyclerViewAdapter(Context context2, List<HolidayModeModel> list) {
            this.isAllSelected = new SingleLiveEvent<>();
            this.arrayInteger = new ArrayList();
            this.context = context2;
            this.holidayModeModelList = list;
        }

        public DeviceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new DeviceViewHolder(LayoutInflater.from(this.context).inflate(R.layout.recycler_view_items_holidaymode, viewGroup, false));
        }

        public void onBindViewHolder(DeviceViewHolder deviceViewHolder, int i) {
            deviceViewHolder.bind(this.holidayModeModelList.get(i), i);
        }

        public int getItemCount() {
            return this.holidayModeModelList.size();
        }

        class DeviceViewHolder extends RecyclerView.ViewHolder {
            @BindView(2131362197)
            TriStateCheckbox checkbox;
            @BindView(2131364111)
            TextView mDeviceName;
            @BindView(2131362834)
            ImageView mImageAcs;
            @BindView(2131363223)
            ConstraintLayout mOuterLayout;
            @BindView(2131364133)
            TextView mTextViewFeatureNotSupperated;

            @OnClick({2131363223})
            public void onClickItem(ConstraintLayout constraintLayout) {
            }

            private DeviceViewHolder(View view) {
                super(view);
                ButterKnife.bind((Object) this, view);
                this.checkbox.setOnCheckedChangeListener(new C1976xe2cb6c9e(this));
            }

            /* renamed from: lambda$new$0$com-jch-racWiFi-iduManagement-view-HolidayModeFragment$DeviceRecyclerViewAdapter$DeviceViewHolder */
            public /* synthetic */ void mo30488x3a229884(CompoundButton compoundButton, boolean z) {
                ((HolidayModeModel) DeviceRecyclerViewAdapter.this.holidayModeModelList.get(getAdapterPosition())).isSelected = z;
                DeviceRecyclerViewAdapter.this.checksForAllSelected();
            }

            public void bind(HolidayModeModel holidayModeModel, int i) {
                this.mDeviceName.setText(holidayModeModel.name);
                this.mOuterLayout.setTag(holidayModeModel);
                if (Constants.IS_DEMO_MODE) {
                    this.mTextViewFeatureNotSupperated.setVisibility(8);
                    this.checkbox.setChecked(Boolean.valueOf(holidayModeModel.isSelected));
                } else if (!holidayModeModel.isHolidayModeSupport) {
                    this.checkbox.setChecked(false);
                    this.checkbox.setEnabled(false);
                    this.mImageAcs.setAlpha(0.2f);
                    this.mDeviceName.setAlpha(0.2f);
                    this.checkbox.setAlpha(0.2f);
                    this.mTextViewFeatureNotSupperated.setAlpha(0.2f);
                    this.mTextViewFeatureNotSupperated.setVisibility(0);
                } else {
                    this.checkbox.setChecked(Boolean.valueOf(holidayModeModel.isSelected));
                    this.checkbox.setEnabled(true);
                    this.mImageAcs.setAlpha(1.0f);
                    this.mDeviceName.setAlpha(1.0f);
                    this.checkbox.setAlpha(1.0f);
                    this.mTextViewFeatureNotSupperated.setVisibility(8);
                }
                if (DeviceRecyclerViewAdapter.this.isHolidayModeDisable) {
                    this.checkbox.setVisibility(0);
                } else {
                    this.checkbox.setVisibility(4);
                }
                DeviceRecyclerViewAdapter.this.checksForAllSelected();
            }
        }

        /* access modifiers changed from: private */
        public void checksForAllSelected() {
            int i = 0;
            int i2 = 0;
            for (HolidayModeModel next : this.holidayModeModelList) {
                if (Constants.IS_DEMO_MODE) {
                    i2++;
                    if (!next.isSelected) {
                    }
                } else if (next.isHolidayModeSupport) {
                    i2++;
                    if (!next.isSelected) {
                    }
                }
                i++;
            }
            if (i == 0) {
                this.isAllSelected.postValue(false);
            } else if (i == i2) {
                this.isAllSelected.postValue(true);
            } else {
                this.isAllSelected.postValue(null);
            }
        }

        /* access modifiers changed from: private */
        public SingleLiveEvent<Boolean> getIsAllSelected() {
            return this.isAllSelected;
        }

        public void holidayModeEnableDisableStatus(boolean z) {
            this.isHolidayModeDisable = z;
            notifyDataSetChanged();
        }
    }

    public HolidayModeFragment() {
        Float valueOf = Float.valueOf(10.0f);
        this.DEFAULT_TEMPERATURE_VALUE = valueOf;
        this.mTemperature = new Temperature(valueOf.floatValue());
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        IduList iduList;
        this.binding = (LayoutHolidayModeBinding) DataBindingUtil.inflate(layoutInflater, R.layout.layout_holiday_mode, viewGroup, false);
        HolidayModeViewModel holidayModeViewModel = (HolidayModeViewModel) ViewModelProviders.m35of((Fragment) this).get(HolidayModeViewModel.class);
        this.viewModel = holidayModeViewModel;
        this.binding.setHolidayModeViewModel(holidayModeViewModel);
        this.viewModel.init(this.mFragmentToActivityCallback);
        this.binding.setLifecycleOwner(this);
        this.binding.imageButtonPickEndDate.setOnClickListener(new HolidayModeFragment$$ExternalSyntheticLambda1(this));
        this.binding.layoutSetTemprature.setOnClickListener(new HolidayModeFragment$$ExternalSyntheticLambda1(this));
        this.binding.textViewSave.setOnClickListener(new HolidayModeFragment$$ExternalSyntheticLambda1(this));
        this.binding.imageButtonMenu.setOnClickListener(new HolidayModeFragment$$ExternalSyntheticLambda1(this));
        if (!(this.mFragmentToActivityCallback.getNavController().getGraph().getArguments().get(DetailedIduModel.IDU_LIST) == null || (iduList = getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduList()) == null)) {
            this.viewModel.setHolidayModeModelList(iduList);
        }
        this.binding.switchHolidayMode.setOnCheckedChangeListener(new HolidayModeFragment$$ExternalSyntheticLambda9(this));
        this.binding.cbAllDevices.setOnCheckedChangeListener(new HolidayModeFragment$$ExternalSyntheticLambda2(this));
        initRecyclerView();
        liveDataObservers();
        setTemperature();
        disableSaveButton();
        switchButtonOnOff(false);
        setEndDate(DateAndTimeUtils.getDefaultDateForHolidayModeDate());
        if (Constants.IS_DEMO_MODE) {
            this.mTemperature.temperature = 12.0f;
            setTemperature();
            disableSaveButton();
            setEndDate(DateAndTimeUtils.getDefaultDateForHolidayModeDate());
        }
        showPleaseWaitDialog();
        this.viewModel.getHolidayModeData(getActivity());
        logEvent(Screens.SCREENS.name(), 7);
        logEvents(Events.HOLIDAY_MODE_FREQUENCY.name(), 0);
        return this.binding.getRoot();
    }

    /* renamed from: lambda$onCreateView$0$com-jch-racWiFi-iduManagement-view-HolidayModeFragment */
    public /* synthetic */ void mo30478x6aa9125c(SwitchButton switchButton, boolean z) {
        enableDisableRootView(z);
        enableDisableSaveButton();
    }

    /* renamed from: lambda$onCreateView$1$com-jch-racWiFi-iduManagement-view-HolidayModeFragment */
    public /* synthetic */ void mo30479x6bdf653b(CompoundButton compoundButton, boolean z) {
        if (!this.isIgnoreOnCheckedChange) {
            for (HolidayModeModel next : this.viewModel.getHolidayModeModelList()) {
                if (Constants.IS_DEMO_MODE) {
                    next.isSelected = z;
                } else if (next.isHolidayModeSupport) {
                    next.isSelected = z;
                }
            }
            this.adapter.notifyDataSetChanged();
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void backToMenu() {
        this.iDrawerMenuFunctions.openMenuDrawer();
    }

    /* access modifiers changed from: private */
    public HolidayModeModel.HolidayModeRequestDataV2 holidayModeRequestData() {
        HolidayModeModel.HolidayModeRequestDataV2 holidayModeRequestDataV2 = new HolidayModeModel.HolidayModeRequestDataV2();
        if (this.binding.switchHolidayMode.isChecked()) {
            holidayModeRequestDataV2.scheduleType = HolidayMode.HOLIDAY_MODE_ENABLED.name();
        } else {
            holidayModeRequestDataV2.scheduleType = HolidayMode.SCHEDULE_DISABLED.name();
        }
        holidayModeRequestDataV2.endDate = DateAndTimeUtils.convertDateFormatAsPerServerRequest(this.binding.editTextEndDate.getText().toString());
        holidayModeRequestDataV2.iduList.clear();
        for (HolidayModeModel next : this.viewModel.getHolidayModeModelList()) {
            if (Constants.IS_DEMO_MODE) {
                holidayModeRequestDataV2.iduList.put(Integer.valueOf(next.racId), Boolean.valueOf(next.isSelected));
            } else if (next.isHolidayModeSupport && next.isSelected) {
                holidayModeRequestDataV2.iduList.put(Integer.valueOf(next.racId), Boolean.valueOf(next.isSelected));
            }
        }
        holidayModeRequestDataV2.temperature = this.mTemperature.temperature;
        if (this.viewModel.isHolidayModeDataExist()) {
            holidayModeRequestDataV2.holidayModeId = this.viewModel.getSaveLastScheduledOfHolidayMode().get(0).holidayModeId;
        }
        return holidayModeRequestDataV2;
    }

    public boolean isHolidayModeEnable() {
        return this.binding.switchHolidayMode.isChecked();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_button_menu:
                if (this.viewModel.isHolidayModeDataExist()) {
                    if (!checkForUpdateDataValue() || !this.viewModel.checkIfAtleastOneItemSelected()) {
                        backToMenu();
                        return;
                    } else {
                        confirmationForSaveChanges();
                        return;
                    }
                } else if (this.viewModel.checkIfAtleastOneItemSelected()) {
                    confirmationForSaveChanges();
                    return;
                } else {
                    backToMenu();
                    return;
                }
            case R.id.image_button_pick_end_date:
                Calendar instance = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new HolidayModeFragment$$ExternalSyntheticLambda0(this), instance.get(1), instance.get(2), instance.get(5));
                datePickerDialog.getDatePicker().setMinDate(DateAndTimeUtils.addOneDayInCurrentDate());
                datePickerDialog.show();
                return;
            case R.id.layout_set_temprature:
                SetTemperatureHolidayModeDialog setTemperatureHolidayModeDialog = new SetTemperatureHolidayModeDialog(getActivity(), this.mTemperature);
                setTemperatureHolidayModeDialog.setOnSaveClickListener(new SetTemperatureHolidayModeDialog.SetOnSaveClickListener() {
                    public void onClick() {
                        HolidayModeFragment.this.setTemperature();
                        HolidayModeFragment.this.enableDisableSaveButton();
                    }
                });
                setTemperatureHolidayModeDialog.setParentView(this.binding.parent);
                setTemperatureHolidayModeDialog.show();
                return;
            case R.id.text_view_save:
                confirmationForSaveChanges();
                return;
            default:
                return;
        }
    }

    /* renamed from: lambda$onClick$2$com-jch-racWiFi-iduManagement-view-HolidayModeFragment */
    public /* synthetic */ void mo30477xb6ad6ab(DatePicker datePicker, int i, int i2, int i3) {
        setEndDate(DateAndTimeUtils.convertDateIntoLocaleDateFormat(i + "-" + DateAndTimeUtils.convertDateInTwoDigit(i2 + 1) + "-" + DateAndTimeUtils.convertDateInTwoDigit(i3)));
        enableDisableSaveButton();
    }

    private void liveDataObservers() {
        this.adapter.getIsAllSelected().observeSingleEvent(new HolidayModeFragment$$ExternalSyntheticLambda4(this));
        this.viewModel.getHolidayModeDataV2SingleLiveEvent().observeSingleEvent(getViewLifecycleOwner(), new Observer<HolidayModeModel.HolidayModeFetchResponse>() {
            public void onChanged(HolidayModeModel.HolidayModeFetchResponse holidayModeFetchResponse) {
                HolidayModeFragment.this.dismissPleaseWaitDialog();
                if (holidayModeFetchResponse.throwable != null) {
                    Toaster.makeToast(HolidayModeFragment.this.getActivity(), HolidayModeFragment.this.getResources().getString(R.string.common_alert_unableToConnectToNw), 0);
                } else if (holidayModeFetchResponse.isSuccessful()) {
                    if (((HolidayModeModel.HolidayModeResponseData[]) holidayModeFetchResponse.response).length > 0) {
                        HolidayModeFragment.this.viewModel.setHolidayModeResponseData((HolidayModeModel.HolidayModeResponseData[]) holidayModeFetchResponse.response);
                        if (DateAndTimeUtils.subtractDateFromCurrentDate(HolidayModeFragment.this.viewModel.getSaveLastScheduledOfHolidayMode().get(0).endsAt) > 0) {
                            HolidayModeFragment holidayModeFragment = HolidayModeFragment.this;
                            holidayModeFragment.setEndDate(DateAndTimeUtils.convertDateIntoLocaleDateFormat(holidayModeFragment.viewModel.getSaveLastScheduledOfHolidayMode().get(0).endsAt));
                        } else {
                            HolidayModeFragment.this.setEndDate(DateAndTimeUtils.getDefaultDateForHolidayModeDate());
                        }
                        HolidayModeFragment.this.mTemperature.temperature = HolidayModeFragment.this.viewModel.getSaveLastScheduledOfHolidayMode().get(0).temperature;
                        HolidayModeFragment.this.setTemperature();
                        HolidayModeFragment.this.viewModel.setHolidayModeDataInAdapter();
                        HolidayModeFragment.this.adapter.notifyDataSetChanged();
                        HolidayModeFragment.this.switchButtonOnOff(true);
                    } else {
                        HolidayModeFragment.this.viewModel.deSelectAllRacs();
                        HolidayModeFragment.this.adapter.notifyDataSetChanged();
                    }
                    HolidayModeFragment.this.disableSaveButton();
                } else {
                    int i = holidayModeFetchResponse.statusCode;
                    if (i == 400) {
                        HolidayModeFragment holidayModeFragment2 = HolidayModeFragment.this;
                        holidayModeFragment2.showErrorPopUp(holidayModeFragment2.getString(R.string.errorCode_alert_somethingWentWorng));
                    } else if (i == 401) {
                        HolidayModeFragment.this.showPleaseWaitDialog();
                        HolidayModeFragment.this.getCoreActivity().refreshToken(new CallBackListener() {
                            public void onFailure() {
                            }

                            public void onSuccess() {
                                HolidayModeFragment.this.showPleaseWaitDialog();
                                HolidayModeFragment.this.viewModel.getHolidayModeData(HolidayModeFragment.this.getActivity());
                            }
                        }, false);
                    } else if (i != 404) {
                        HolidayModeFragment holidayModeFragment3 = HolidayModeFragment.this;
                        holidayModeFragment3.showErrorPopUp(holidayModeFragment3.getString(R.string.errorCode_alert_somethingWentWorng));
                    } else {
                        HolidayModeFragment holidayModeFragment4 = HolidayModeFragment.this;
                        holidayModeFragment4.showErrorPopUp(holidayModeFragment4.getString(holidayModeFetchResponse.getErrorMessageStringId(String.valueOf(holidayModeFetchResponse.statusCode))));
                    }
                }
            }
        });
        this.viewModel.updateHolidayModeSinglLiveEvent().observeSingleEvent(getViewLifecycleOwner(), new HolidayModeFragment$$ExternalSyntheticLambda3(this));
    }

    /* renamed from: lambda$liveDataObservers$3$com-jch-racWiFi-iduManagement-view-HolidayModeFragment */
    public /* synthetic */ void mo30475xf77563ac(Boolean bool) {
        this.isIgnoreOnCheckedChange = true;
        this.binding.cbAllDevices.setChecked(bool);
        this.isIgnoreOnCheckedChange = false;
        enableDisableSaveButton();
    }

    /* renamed from: lambda$liveDataObservers$4$com-jch-racWiFi-iduManagement-view-HolidayModeFragment */
    public /* synthetic */ void mo30476xf8abb68b(HolidayModeModel.HolidayModeUpdateResponseDataV2 holidayModeUpdateResponseDataV2) {
        String str;
        dismissPleaseWaitDialog();
        if (holidayModeUpdateResponseDataV2.statusCode == 200) {
            ArrayList arrayList = new ArrayList();
            boolean z = true;
            for (HolidayModeModel.Response response : ((HolidayModeModel.HolidayModeResponse) holidayModeUpdateResponseDataV2.response).result) {
                Iterator<HolidayModeModel> it = this.viewModel.getHolidayModeModelList().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    HolidayModeModel next = it.next();
                    if (next.isSelected && next.racId == response.racId && !response.message.equalsIgnoreCase("Success")) {
                        arrayList.add(next.name);
                        z = false;
                        break;
                    }
                }
            }
            if (z) {
                if (isHolidayModeEnable()) {
                    str = getResources().getString(R.string.holidayMode_alert_enabledSuccessFully);
                } else {
                    str = getResources().getString(R.string.holidayMode_alert_disabledSuccessFully);
                }
                copyScheduleFailedAlertDialog(str, true);
            } else {
                String format = String.format(getResources().getString(R.string.holidayMode_alert_unableToUpdateHoliday), new Object[]{arrayList.toString()});
                if (((HolidayModeModel.HolidayModeResponse) holidayModeUpdateResponseDataV2.response).result.length == arrayList.size()) {
                    copyScheduleFailedAlertDialog(format, false);
                } else {
                    copyScheduleFailedAlertDialog(format, true);
                }
            }
            this.mFragmentToActivityCallback.refreshAllIduStates();
            return;
        }
        int i = holidayModeUpdateResponseDataV2.statusCode;
        if (i == 400) {
            showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
        } else if (i == 401) {
            showPleaseWaitDialog();
            getCoreActivity().refreshToken(new CallBackListener() {
                public void onFailure() {
                }

                public void onSuccess() {
                    HolidayModeFragment.this.showPleaseWaitDialog();
                    if (HolidayModeFragment.this.binding.switchHolidayMode.isChecked()) {
                        HolidayModeFragment.this.viewModel.requestForUpdateHolidayMode(HolidayModeFragment.this.holidayModeRequestData());
                    }
                }
            }, false);
        } else if (i != 404) {
            showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
        } else {
            showErrorPopUp(getString(holidayModeUpdateResponseDataV2.getErrorMessageStringId(String.valueOf(holidayModeUpdateResponseDataV2.statusCode))));
        }
    }

    public boolean checkForUpdateDataValue() {
        try {
            if (!this.viewModel.isCheckedStatusChanged() || this.viewModel.getSaveLastScheduledOfHolidayMode().get(0).isEnabled != this.binding.switchHolidayMode.isChecked() || this.viewModel.getSaveLastScheduledOfHolidayMode().get(0).temperature != Float.parseFloat(this.binding.textViewTempratureHolidayMode.getText().toString())) {
                return true;
            }
            if (this.binding.editTextEndDate.getText() == null || this.binding.editTextEndDate.length() <= 1 || this.viewModel.getSaveLastScheduledOfHolidayMode().get(0).endsAt.equalsIgnoreCase(DateAndTimeUtils.convertDateFormatAsPerServerRequest(this.binding.editTextEndDate.getText().toString()))) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    /* access modifiers changed from: private */
    public void setEndDate(String str) {
        if (str != null) {
            this.binding.editTextEndDate.setText(str);
        }
    }

    /* access modifiers changed from: private */
    public void setTemperature() {
        this.binding.textViewTempratureHolidayMode.setText(String.valueOf((int) TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings((double) this.mTemperature.temperature)));
        this.binding.textViewTempratureUnitHolidayMode.setText(TemperatureUnit.getTemperatureUnitFromSettings());
    }

    /* access modifiers changed from: private */
    public void enableDisableSaveButton() {
        if (this.viewModel.isHolidayModeDataExist()) {
            if (!checkForUpdateDataValue() || !this.viewModel.checkIfAtleastOneItemSelected()) {
                disableSaveButton();
            } else {
                enableSaveButton();
            }
        } else if (this.viewModel.checkIfAtleastOneItemSelected()) {
            enableSaveButton();
        } else {
            disableSaveButton();
        }
    }

    /* access modifiers changed from: private */
    public void disableSaveButton() {
        this.binding.textViewSave.setEnabled(false);
        this.binding.textViewSave.setAlpha(0.5f);
    }

    private void enableSaveButton() {
        this.binding.textViewSave.setEnabled(true);
        this.binding.textViewSave.setAlpha(1.0f);
    }

    private void enableAdapterUI() {
        this.adapter.holidayModeEnableDisableStatus(true);
    }

    private void disableAdapterUI() {
        this.adapter.holidayModeEnableDisableStatus(false);
    }

    public void switchButtonOnOff(boolean z) {
        this.binding.switchHolidayMode.setCheckedSilent(z);
        enableDisableRootView(z);
    }

    public void updateUI(boolean z) {
        if (z) {
            this.binding.imageButtonPickEndDate.setEnabled(true);
            this.binding.layoutSetTemprature.setEnabled(true);
            this.binding.cbAllDevices.setEnabled(true);
            return;
        }
        this.binding.imageButtonPickEndDate.setEnabled(false);
        this.binding.layoutSetTemprature.setEnabled(false);
        this.binding.cbAllDevices.setEnabled(false);
    }

    public void enableDisableRootView(boolean z) {
        if (z) {
            this.binding.layoutBottom.setAlpha(1.0f);
            this.binding.layoutBottom.setEnabled(true);
            this.binding.imageButtonPickEndDate.setColorFilter(getResources().getColor(R.color.colorRed));
            this.binding.textViewTempratureHolidayMode.setTextColor(getResources().getColor(R.color.colorRed));
            this.binding.textViewTempratureUnitHolidayMode.setTextColor(getResources().getColor(R.color.colorRed));
            this.binding.cbAllDevices.setVisibility(0);
            enableAdapterUI();
        } else {
            this.binding.layoutBottom.setAlpha(0.5f);
            this.binding.layoutBottom.setEnabled(false);
            this.binding.imageButtonPickEndDate.setColorFilter(getResources().getColor(R.color.textview_color_vd_light));
            this.binding.textViewTempratureHolidayMode.setTextColor(getResources().getColor(R.color.textview_color_vd_light));
            this.binding.textViewTempratureUnitHolidayMode.setTextColor(getResources().getColor(R.color.textview_color_vd_light));
            this.binding.cbAllDevices.setVisibility(4);
            disableAdapterUI();
        }
        updateUI(z);
    }

    private void initRecyclerView() {
        this.adapter = new DeviceRecyclerViewAdapter(getActivity(), this.viewModel.getHolidayModeModelList());
        this.binding.recyclerViewDevices.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.binding.recyclerViewDevices.setHasFixedSize(true);
        this.binding.recyclerViewDevices.setAdapter(this.adapter);
    }

    static class Temperature implements Parcelable {
        public static final Parcelable.Creator<Temperature> CREATOR = new Parcelable.Creator<Temperature>() {
            public Temperature createFromParcel(Parcel parcel) {
                return new Temperature(parcel);
            }

            public Temperature[] newArray(int i) {
                return new Temperature[i];
            }
        };
        public float temperature;

        public int describeContents() {
            return 0;
        }

        public Temperature(float f) {
            this.temperature = f;
        }

        protected Temperature(Parcel parcel) {
            this.temperature = parcel.readFloat();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeFloat(this.temperature);
        }

        public float getTemperature() {
            return this.temperature;
        }

        public void setTemperature(float f) {
            this.temperature = f;
        }
    }

    private void confirmationForSaveChanges() {
        ConfirmationDialog confirmationDialog = new ConfirmationDialog(getActivity());
        confirmationDialog.setMessageString(getActivity().getString(R.string.common_alert_saveChangesDesc));
        confirmationDialog.setTitleString(getActivity().getString(R.string.common_alert_saveChanges));
        confirmationDialog.setPositiveButton(getActivity().getString(R.string.common_btn_yes), new HolidayModeFragment$$ExternalSyntheticLambda5(this));
        confirmationDialog.setNegativeButton(getActivity().getString(R.string.common_btn_no), new HolidayModeFragment$$ExternalSyntheticLambda6(this));
        confirmationDialog.setParentView(this.binding.rootLayout);
        confirmationDialog.show();
    }

    /* renamed from: lambda$confirmationForSaveChanges$5$com-jch-racWiFi-iduManagement-view-HolidayModeFragment */
    public /* synthetic */ boolean mo30472xc5469b25(Dialog dialog, View view) {
        dialog.dismiss();
        if (DateAndTimeUtils.subtractDateFromCurrentDate(DateAndTimeUtils.convertDateFormatAsPerServerRequest(this.binding.editTextEndDate.getText().toString())) <= 0) {
            return false;
        }
        showPleaseWaitDialog();
        this.viewModel.requestForUpdateHolidayMode(holidayModeRequestData());
        return false;
    }

    /* renamed from: lambda$confirmationForSaveChanges$6$com-jch-racWiFi-iduManagement-view-HolidayModeFragment */
    public /* synthetic */ boolean mo30473xc67cee04(Dialog dialog, View view) {
        dialog.dismiss();
        backToMenu();
        return true;
    }

    private void copyScheduleFailedAlertDialog(String str, boolean z) {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(requireActivity());
        singleChoiceDialog.setTitleString(getActivity().getString(R.string.common_alert));
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setMessageString(str);
        singleChoiceDialog.setPositiveButton(getActivity().getString(R.string.common_btn_ok), new HolidayModeFragment$$ExternalSyntheticLambda7(this, z));
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            singleChoiceDialog.show();
        }
    }

    /* renamed from: lambda$copyScheduleFailedAlertDialog$7$com-jch-racWiFi-iduManagement-view-HolidayModeFragment */
    public /* synthetic */ boolean mo30474xa6446ef4(boolean z, Dialog dialog, View view) {
        dialog.dismiss();
        if (!z) {
            return false;
        }
        showPleaseWaitDialog();
        this.viewModel.getHolidayModeData(getActivity());
        return false;
    }

    private void errorAlertDialog(String str) {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(requireActivity());
        singleChoiceDialog.setTitleString(getActivity().getString(R.string.common_alert));
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setMessageString(str);
        singleChoiceDialog.setPositiveButton(getActivity().getString(R.string.common_btn_ok), HolidayModeFragment$$ExternalSyntheticLambda8.INSTANCE);
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            singleChoiceDialog.show();
        }
    }
}
