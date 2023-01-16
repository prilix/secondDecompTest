package com.jch.racWiFi.iduManagement.smartFence.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.activity.OnBackPressedCallback;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customToolTip.CreateTooltipContentHolder;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.customViews.customWidgets.TriStateCheckbox;
import com.jch.racWiFi.databinding.SmartFenceSelectAcsBinding;
import com.jch.racWiFi.iduManagement.IduList;
import com.jch.racWiFi.iduManagement.JpRegulationConstants;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.OperationMode;
import com.jch.racWiFi.iduManagement.model.RacModelWiseData;
import com.jch.racWiFi.iduManagement.smartFence.geoFenceApi.FamilyIdGeoFenceDataMap;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFencePair;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SmartFenceSelectAcFragment extends GenericFragment implements View.OnClickListener {
    public static boolean isAcSettingChange;
    public AcListRecyclerViewAdapter acListRecyclerViewAdapter;
    CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (z) {
                for (int i = 0; i < SmartFenceSelectAcFragment.this.recyclerAcList.size(); i++) {
                    if (((AcListModel) SmartFenceSelectAcFragment.this.recyclerAcList.get(i)).isSupportFeature()) {
                        ((AcListModel) SmartFenceSelectAcFragment.this.recyclerAcList.get(i)).isSelected = true;
                    } else {
                        ((AcListModel) SmartFenceSelectAcFragment.this.recyclerAcList.get(i)).isSelected = false;
                    }
                }
            } else {
                for (int i2 = 0; i2 < SmartFenceSelectAcFragment.this.recyclerAcList.size(); i2++) {
                    ((AcListModel) SmartFenceSelectAcFragment.this.recyclerAcList.get(i2)).isSelected = false;
                }
            }
            SmartFenceSelectAcFragment.this.isAllChecked = true;
            SmartFenceSelectAcFragment.this.acListRecyclerViewAdapter.notifyDataSetChanged();
            SmartFenceSelectAcFragment.this.changeSaveButtonState();
        }
    };
    /* access modifiers changed from: private */
    public CreateTooltipContentHolder createTooltipContentHolder;
    Long familyId;
    /* access modifiers changed from: private */
    public FamilyIdGeoFenceDataMap familyIdGeoFenceDataMap;
    /* access modifiers changed from: private */
    public boolean isAllChecked = false;
    private boolean isSilentChecked = false;
    /* access modifiers changed from: private */
    public List<Long> listAssociatedWithFamily;
    SmartFenceSelectAcsBinding mBinding;
    private GeoFencePair mGeoFencePair;
    private RacModelWiseData mRacModelWiseData;
    private List<String> oldCloudListId = new ArrayList();
    private List<AcListModel> oldRecyclerAcList;
    List<String> racListByCloudId = new ArrayList();
    List<Long> racListByRacId = new ArrayList();
    Map<String, List<RacModelWiseData.RacModeDetail>> racListCloudIDToModeMap = new HashMap();
    /* access modifiers changed from: private */
    public List<AcListModel> recyclerAcList;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (SmartFenceSelectAcsBinding) DataBindingUtil.inflate(layoutInflater, R.layout.smart_fence_select_acs, viewGroup, false);
        this.familyId = Long.valueOf((long) FamilyGroupList.getCurrentFamily().familyId);
        this.recyclerAcList = new ArrayList();
        initAcListRecyclerView();
        initClickListeners();
        this.mGeoFencePair = (GeoFencePair) getCoreActivity().getGlobalViewModelRepository().getGeoFenceListViewModel().getRacIdToGeoFenceDataMapMutableLiveData().getValue().get(Long.valueOf((long) FamilyGroupList.getCurrentFamily().familyId));
        SingleLiveEvent<FamilyIdGeoFenceDataMap> racIdToGeoFenceDataMapMutableLiveData = getCoreActivity().getGlobalViewModelRepository().getGeoFenceListViewModel().getRacIdToGeoFenceDataMapMutableLiveData();
        this.oldCloudListId.clear();
        GeoFencePair geoFencePair = this.mGeoFencePair;
        if (geoFencePair != null) {
            this.oldCloudListId.addAll(geoFencePair.getAssociatedRacCloudId());
        }
        racIdToGeoFenceDataMapMutableLiveData.observe(getViewLifecycleOwner(), new Observer<FamilyIdGeoFenceDataMap>() {
            public void onChanged(FamilyIdGeoFenceDataMap familyIdGeoFenceDataMap) {
                SmartFenceSelectAcFragment.this.familyIdGeoFenceDataMap = familyIdGeoFenceDataMap;
                if (SmartFenceSelectAcFragment.this.familyIdGeoFenceDataMap.get(SmartFenceSelectAcFragment.this.familyId) != null) {
                    SmartFenceSelectAcFragment smartFenceSelectAcFragment = SmartFenceSelectAcFragment.this;
                    smartFenceSelectAcFragment.listAssociatedWithFamily = ((GeoFencePair) smartFenceSelectAcFragment.familyIdGeoFenceDataMap.get(SmartFenceSelectAcFragment.this.familyId)).getAssociatedRacs();
                    return;
                }
                SmartFenceSelectAcFragment.this.listAssociatedWithFamily = null;
            }
        });
        this.mBinding.cbAllDevices.setOnCheckedChangeListener(this.checkedChangeListener);
        getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduListSingleLiveEvent().observeWithCachedTrigger(getViewLifecycleOwner(), new Observer<IduList>() {
            public void onChanged(IduList iduList) {
                Log.e("RAC_LIST", iduList.toString());
                SmartFenceSelectAcFragment.this.autoPopulate(iduList);
            }
        });
        this.createTooltipContentHolder = new CreateTooltipContentHolder((Context) requireActivity(), (View) this.mBinding.imageButtonHelp, (int) R.string.smartFence_lbl_selectAcsHelp);
        return this.mBinding.getRoot();
    }

    /* access modifiers changed from: private */
    public void autoPopulate(IduList iduList) {
        this.recyclerAcList.clear();
        Iterator it = iduList.iterator();
        while (it.hasNext()) {
            DetailedIduModel detailedIduModel = (DetailedIduModel) it.next();
            AcListModel acListModel = new AcListModel();
            acListModel.setAcName(detailedIduModel.name);
            acListModel.setRacId(Long.valueOf((long) detailedIduModel.f454id.intValue()));
            acListModel.setCloudId(detailedIduModel.cloudId);
            RacModelWiseData racModelWiseDataBasedOnRacTypeId = this.mFragmentToActivityCallback.getRacModelWiseDataBasedOnRacTypeId(detailedIduModel.cloudId);
            if (racModelWiseDataBasedOnRacTypeId != null) {
                if (racModelWiseDataBasedOnRacTypeId.getEnableOptions().getEnableOption0().isOutOfHomeReminder()) {
                    acListModel.setSupportFeature(true);
                    List<Long> list = this.listAssociatedWithFamily;
                    if (list == null) {
                        acListModel.setSelected(true);
                    } else if (list.contains(acListModel.getRacId())) {
                        acListModel.setSelected(true);
                    } else {
                        acListModel.setSelected(false);
                    }
                } else {
                    acListModel.setSupportFeature(false);
                }
            }
            this.recyclerAcList.add(acListModel);
        }
        Collections.sort(this.recyclerAcList, new Comparator<Object>() {
            public int compare(Object obj, Object obj2) {
                return ((AcListModel) obj).racId.intValue() - ((AcListModel) obj2).racId.intValue();
            }
        });
        if (this.recyclerAcList.isEmpty()) {
            this.mBinding.layoutAllAcs.setVisibility(4);
            this.mBinding.textViewSelectAcsSubtitle.setVisibility(4);
            this.mBinding.imageButtonHelp.setVisibility(4);
            this.mBinding.textViewNoRacFound.setVisibility(0);
            this.mBinding.textViewSave.setVisibility(4);
        } else if (this.recyclerAcList.size() == 1) {
            this.mBinding.listConstraintLayout.setVisibility(0);
            this.mBinding.layoutAllAcs.setVisibility(8);
            this.mBinding.textViewSelectAcsSubtitle.setVisibility(0);
            this.mBinding.imageButtonHelp.setVisibility(0);
            this.mBinding.textViewSave.setVisibility(0);
            this.mBinding.textViewNoRacFound.setVisibility(4);
        } else {
            this.mBinding.listConstraintLayout.setVisibility(0);
            this.mBinding.layoutAllAcs.setVisibility(0);
            this.mBinding.textViewSelectAcsSubtitle.setVisibility(0);
            this.mBinding.imageButtonHelp.setVisibility(0);
            this.mBinding.textViewSave.setVisibility(0);
            this.mBinding.textViewNoRacFound.setVisibility(4);
        }
        this.acListRecyclerViewAdapter.notifyDataSetChanged();
        this.oldRecyclerAcList = parcelClone(this.recyclerAcList);
        changeState(this.recyclerAcList);
    }

    /* access modifiers changed from: package-private */
    public List<AcListModel> parcelClone(List<AcListModel> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            AcListModel acListModel = new AcListModel();
            acListModel.isSelected = list.get(i).isSelected;
            arrayList.add(acListModel);
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void changeSaveButtonState() {
        boolean z;
        boolean z2;
        Iterator<AcListModel> it = this.recyclerAcList.iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().isSelected) {
                    z = false;
                    break;
                }
            } else {
                z = true;
                break;
            }
        }
        if (z) {
            this.mBinding.textViewSave.setEnabled(false);
            this.mBinding.textViewSave.setColorFilter(getResources().getColor(R.color.color_disabled_views));
            return;
        }
        int i = 0;
        while (true) {
            if (i >= this.oldRecyclerAcList.size()) {
                z2 = false;
                break;
            } else if (this.oldRecyclerAcList.get(i).isSelected != this.recyclerAcList.get(i).isSelected) {
                z2 = true;
                break;
            } else {
                Log.e("Smart", "");
                i++;
            }
        }
        if (z2) {
            this.mBinding.textViewSave.setEnabled(true);
            this.mBinding.textViewSave.setColorFilter(getResources().getColor(R.color.dark_red));
            return;
        }
        this.mBinding.textViewSave.setEnabled(false);
        this.mBinding.textViewSave.setColorFilter(getResources().getColor(R.color.color_disabled_views));
    }

    private void dummyList() {
        AcListModel acListModel = new AcListModel();
        acListModel.setAcName("Room1");
        acListModel.setSelected(true);
        AcListModel acListModel2 = new AcListModel();
        acListModel2.setAcName("Room2");
        acListModel2.setSelected(false);
        AcListModel acListModel3 = new AcListModel();
        acListModel3.setAcName("Room3");
        acListModel3.setSelected(false);
        AcListModel acListModel4 = new AcListModel();
        acListModel4.setAcName("Room4");
        acListModel4.setSelected(true);
        AcListModel acListModel5 = new AcListModel();
        acListModel5.setAcName("Room5");
        acListModel5.setSelected(false);
        AcListModel acListModel6 = new AcListModel();
        acListModel6.setAcName("Room6");
        acListModel6.setSelected(true);
    }

    private void initAcListRecyclerView() {
        this.acListRecyclerViewAdapter = new AcListRecyclerViewAdapter(this.recyclerAcList);
        this.mBinding.recyclerViewDevices.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.mBinding.recyclerViewDevices.setAdapter(this.acListRecyclerViewAdapter);
    }

    private void initClickListeners() {
        this.mBinding.backButton.setOnClickListener(this);
        this.mBinding.textViewSave.setOnClickListener(this);
        this.mBinding.imageButtonHelp.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.back_button) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        } else if (id == R.id.image_button_help) {
            if (!this.createTooltipContentHolder.isShowing()) {
                this.createTooltipContentHolder.setHintCasePosition(3);
                this.createTooltipContentHolder.setBorderRes(R.drawable.tool_tip_without_radius);
                this.createTooltipContentHolder.setBordercolor(R.color.colorRed);
                this.createTooltipContentHolder.setHitCaseYOffset(R.dimen.hint_case_y_offset);
                this.createTooltipContentHolder.setContainerOffsetXAxis(R.dimen.container_block_x_offset_center_horizontal);
                this.createTooltipContentHolder.buildWithNoDimensions();
                this.createTooltipContentHolder.show();
            } else {
                this.createTooltipContentHolder.dismiss();
            }
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (SmartFenceSelectAcFragment.this.createTooltipContentHolder != null && SmartFenceSelectAcFragment.this.createTooltipContentHolder.isShowing()) {
                        SmartFenceSelectAcFragment.this.createTooltipContentHolder.dismiss();
                    }
                }
            }, JpRegulationConstants.JP_COMMAND_EXECUTION_TIMEOUT);
        } else if (id == R.id.text_view_save) {
            boolean z = false;
            for (int i = 0; i < this.recyclerAcList.size(); i++) {
                if (this.recyclerAcList.get(i).isSupportFeature() && this.recyclerAcList.get(i).isSelected) {
                    z = true;
                }
            }
            if (z) {
                showPleaseWaitDialog();
                GeoFencePair geoFencePair = this.mGeoFencePair;
                if (geoFencePair != null) {
                    geoFencePair.getAssociatedRacs().clear();
                    this.mGeoFencePair.getAssociatedRacCloudId().clear();
                    for (int i2 = 0; i2 < this.recyclerAcList.size(); i2++) {
                        if (this.recyclerAcList.get(i2).isSupportFeature() && this.recyclerAcList.get(i2).isSelected) {
                            this.mGeoFencePair.getAssociatedRacs().add(this.recyclerAcList.get(i2).racId);
                            this.mGeoFencePair.getAssociatedRacCloudId().add(this.recyclerAcList.get(i2).cloudId);
                        }
                    }
                } else {
                    this.mGeoFencePair = GeoFencePair.DEFAULT;
                    for (int i3 = 0; i3 < this.recyclerAcList.size(); i3++) {
                        if (this.recyclerAcList.get(i3).isSupportFeature() && this.recyclerAcList.get(i3).isSelected) {
                            this.mGeoFencePair.getAssociatedRacs().add(this.recyclerAcList.get(i3).racId);
                            this.mGeoFencePair.getAssociatedRacCloudId().add(this.recyclerAcList.get(i3).cloudId);
                        }
                    }
                }
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(commonModeSelectedV2(this.mGeoFencePair));
                if (this.mGeoFencePair != null) {
                    if (checkConfigChangedWithArrayList(SmartFenceFragment.modeArrayListForRecyclerViewCopy, arrayList).booleanValue()) {
                        this.mGeoFencePair.racListChanged = true;
                        this.mGeoFencePair.getLeavingGeoFence().getModeTempSettings().isSettingsEnabled = true;
                        this.mGeoFencePair.getArrivingGeoFence().getModeTempSettings().isSettingsEnabled = true;
                    } else {
                        this.mGeoFencePair.racListChanged = false;
                        isAcSettingChange = true;
                    }
                }
                SmartFenceFragment.isAnySettingsChanged = true;
                dismissPleaseWaitDialog();
                FamilyIdGeoFenceDataMap value = getCoreActivity().getGlobalViewModelRepository().getGeoFenceListViewModel().getRacIdToGeoFenceDataMapMutableLiveData().getValue();
                if (value != null) {
                    value.put(Long.valueOf((long) FamilyGroupList.getCurrentFamily().familyId), this.mGeoFencePair);
                }
                this.mFragmentToActivityCallback.getNavController().navigateUp();
                return;
            }
            showSingleChiocePopUp(getString(R.string.common_alert), getString(R.string.smartFence_alert_pleaseSelectAtleastOneAc));
        }
    }

    public void onStart() {
        super.onStart();
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
                if (SmartFenceSelectAcFragment.this.createTooltipContentHolder != null && SmartFenceSelectAcFragment.this.createTooltipContentHolder.isShowing()) {
                    SmartFenceSelectAcFragment.this.createTooltipContentHolder.dismiss();
                }
                SmartFenceSelectAcFragment.this.mFragmentToActivityCallback.getNavController().navigateUp();
            }
        });
    }

    private class AcListRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
        List<AcListModel> list;

        AcListRecyclerViewAdapter(List<AcListModel> list2) {
            this.list = list2;
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(SmartFenceSelectAcFragment.this.getContext()).inflate(R.layout.recycle_view_items_smart_fence_multiple_devices, viewGroup, false));
        }

        public void onBindViewHolder(ViewHolder viewHolder, final int i) {
            viewHolder.mAcNameTextView.setText(this.list.get(i).acName);
            if (!this.list.get(i).isSelected) {
                viewHolder.triStateCheckbox.setChecked(false);
                if (!this.list.get(i).isSupportFeature()) {
                    viewHolder.constraintLayout.setAlpha(0.4f);
                    viewHolder.doesNotSupportTextView.setVisibility(0);
                    viewHolder.triStateCheckbox.setEnabled(false);
                } else {
                    viewHolder.constraintLayout.setAlpha(1.0f);
                    viewHolder.doesNotSupportTextView.setVisibility(8);
                    viewHolder.triStateCheckbox.setEnabled(true);
                }
            } else if (!this.list.get(i).isSupportFeature()) {
                viewHolder.triStateCheckbox.setChecked(false);
                viewHolder.constraintLayout.setAlpha(0.4f);
                viewHolder.doesNotSupportTextView.setVisibility(0);
                viewHolder.triStateCheckbox.setEnabled(false);
            } else {
                viewHolder.triStateCheckbox.setChecked(true);
                viewHolder.constraintLayout.setAlpha(1.0f);
                viewHolder.doesNotSupportTextView.setVisibility(8);
                viewHolder.triStateCheckbox.setEnabled(true);
            }
            if (this.list.size() - 1 == i) {
                SmartFenceSelectAcFragment.this.isAllChecked = false;
            }
            viewHolder.triStateCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (!SmartFenceSelectAcFragment.this.isAllChecked) {
                        if (!z) {
                            AcListRecyclerViewAdapter.this.list.get(i).setSelected(false);
                        } else if (AcListRecyclerViewAdapter.this.list.get(i).isSupportFeature()) {
                            AcListRecyclerViewAdapter.this.list.get(i).setSelected(true);
                        } else {
                            AcListRecyclerViewAdapter.this.list.get(i).setSelected(false);
                        }
                        SmartFenceSelectAcFragment.this.changeState(AcListRecyclerViewAdapter.this.list);
                        SmartFenceSelectAcFragment.this.changeSaveButtonState();
                    }
                }
            });
        }

        public int getItemCount() {
            return this.list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            /* access modifiers changed from: private */
            public ConstraintLayout constraintLayout;
            /* access modifiers changed from: private */
            public TextView doesNotSupportTextView;
            private ImageView mAcIcon;
            /* access modifiers changed from: private */
            public TextView mAcNameTextView;
            /* access modifiers changed from: private */
            public TriStateCheckbox triStateCheckbox;

            public ViewHolder(View view) {
                super(view);
                this.mAcNameTextView = (TextView) view.findViewById(R.id.text_view_device_name);
                this.mAcIcon = (ImageView) view.findViewById(R.id.image_view_acs);
                this.triStateCheckbox = (TriStateCheckbox) view.findViewById(R.id.cb_member_permission);
                this.constraintLayout = (ConstraintLayout) view.findViewById(R.id.layout_manage_devices);
                this.doesNotSupportTextView = (TextView) view.findViewById(R.id.text_view_does_not_support_featue);
            }
        }
    }

    private class AcListModel {
        /* access modifiers changed from: private */
        public String acName;
        /* access modifiers changed from: private */
        public String cloudId;
        /* access modifiers changed from: private */
        public boolean isSelected;
        /* access modifiers changed from: private */
        public Long racId;
        private boolean supportFeature;

        private AcListModel() {
        }

        public String getCloudId() {
            return this.cloudId;
        }

        public void setCloudId(String str) {
            this.cloudId = str;
        }

        public boolean isSupportFeature() {
            return this.supportFeature;
        }

        public void setSupportFeature(boolean z) {
            this.supportFeature = z;
        }

        public String getAcName() {
            return this.acName;
        }

        public void setAcName(String str) {
            this.acName = str;
        }

        public boolean isSelected() {
            return this.isSelected;
        }

        public void setSelected(boolean z) {
            this.isSelected = z;
        }

        public Long getRacId() {
            return this.racId;
        }

        public void setRacId(Long l) {
            this.racId = l;
        }
    }

    private void showSingleChiocePopUp(String str, String str2) {
        final SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
        singleChoiceDialog.setTitleString(str);
        singleChoiceDialog.setMessageString(str2);
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                singleChoiceDialog.dismiss();
                return false;
            }
        });
        singleChoiceDialog.show();
    }

    /* access modifiers changed from: private */
    public void changeState(List<AcListModel> list) {
        int i = 0;
        for (AcListModel isSupportFeature : list) {
            if (isSupportFeature.isSupportFeature()) {
                i++;
            }
        }
        int i2 = 0;
        for (AcListModel next : list) {
            if (next.isSupportFeature() && next.isSelected) {
                i2++;
            }
        }
        if (i == i2) {
            this.isSilentChecked = true;
            this.mBinding.cbAllDevices.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
            this.mBinding.cbAllDevices.setChecked(true);
            this.mBinding.cbAllDevices.setOnCheckedChangeListener(this.checkedChangeListener);
        } else if (i2 == 0) {
            this.mBinding.cbAllDevices.setChecked(false);
        } else {
            this.mBinding.cbAllDevices.setChecked((Boolean) null);
        }
    }

    public ArrayList<OperationMode> commonModeSelected(GeoFencePair geoFencePair) {
        ArrayList<OperationMode> arrayList = new ArrayList<>();
        this.racListByRacId.clear();
        this.racListByCloudId.clear();
        if (geoFencePair != null) {
            this.racListByCloudId.addAll(geoFencePair.getAssociatedRacCloudId());
            this.racListByRacId.addAll(geoFencePair.getAssociatedRacs());
        }
        this.racListCloudIDToModeMap.clear();
        if (!this.racListByCloudId.isEmpty()) {
            for (int i = 0; i < this.racListByCloudId.size(); i++) {
                this.mRacModelWiseData = this.mFragmentToActivityCallback.getRacModelWiseConfigurationList().getRacModelWiseDataBasedOnRacTypeId(this.racListByCloudId.get(i));
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(this.mRacModelWiseData.getRacModeDetails());
                this.racListCloudIDToModeMap.put(this.racListByCloudId.get(i), arrayList2);
            }
            for (Map.Entry next : this.racListCloudIDToModeMap.entrySet()) {
                if (arrayList.size() <= 0) {
                    for (int i2 = 0; i2 < ((List) next.getValue()).size(); i2++) {
                        arrayList.add(((RacModelWiseData.RacModeDetail) ((List) next.getValue()).get(i2)).getMode());
                    }
                } else {
                    ArrayList arrayList3 = new ArrayList();
                    for (int i3 = 0; i3 < ((List) next.getValue()).size(); i3++) {
                        arrayList3.add(((RacModelWiseData.RacModeDetail) ((List) next.getValue()).get(i3)).getMode());
                    }
                    arrayList.retainAll(arrayList3);
                }
            }
            arrayList.size();
            Long l = null;
            boolean z = false;
            for (Map.Entry next2 : this.racListCloudIDToModeMap.entrySet()) {
                int i4 = 0;
                while (true) {
                    if (i4 >= ((List) next2.getValue()).size()) {
                        break;
                    }
                    if (((RacModelWiseData.RacModeDetail) ((List) next2.getValue()).get(i4)).getMode() == OperationMode.DRY) {
                        if (l == null) {
                            l = Long.valueOf(((RacModelWiseData.RacModeDetail) ((List) next2.getValue()).get(i4)).getDefaultHumidity());
                        } else if (l.longValue() != ((RacModelWiseData.RacModeDetail) ((List) next2.getValue()).get(i4)).getDefaultHumidity()) {
                            z = true;
                            continue;
                            break;
                        }
                    }
                    i4++;
                }
                if (z) {
                    break;
                }
            }
            if (z && arrayList.contains(OperationMode.DRY)) {
                arrayList.remove(OperationMode.DRY);
            }
        }
        return arrayList;
    }

    public ArrayList<OperationMode> commonModeSelectedV2(GeoFencePair geoFencePair) {
        ArrayList<OperationMode> arrayList = new ArrayList<>();
        this.racListByRacId.clear();
        this.racListByCloudId.clear();
        if (geoFencePair != null) {
            this.racListByCloudId.addAll(geoFencePair.getAssociatedRacCloudId());
            this.racListByRacId.addAll(geoFencePair.getAssociatedRacs());
        }
        this.racListCloudIDToModeMap.clear();
        if (!this.racListByCloudId.isEmpty()) {
            for (int i = 0; i < this.racListByCloudId.size(); i++) {
                this.mRacModelWiseData = this.mFragmentToActivityCallback.getRacModelWiseConfigurationList().getRacModelWiseDataBasedOnRacTypeId(this.racListByCloudId.get(i));
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(this.mRacModelWiseData.getRacModeDetails());
                this.racListCloudIDToModeMap.put(this.racListByCloudId.get(i), arrayList2);
            }
            for (Map.Entry next : this.racListCloudIDToModeMap.entrySet()) {
                if (arrayList.size() <= 0) {
                    for (int i2 = 0; i2 < ((List) next.getValue()).size(); i2++) {
                        arrayList.add(((RacModelWiseData.RacModeDetail) ((List) next.getValue()).get(i2)).getMode());
                    }
                } else {
                    ArrayList arrayList3 = new ArrayList();
                    for (int i3 = 0; i3 < ((List) next.getValue()).size(); i3++) {
                        arrayList3.add(((RacModelWiseData.RacModeDetail) ((List) next.getValue()).get(i3)).getMode());
                    }
                    arrayList.retainAll(arrayList3);
                }
            }
            arrayList.size();
            Iterator it = new ArrayList(arrayList).iterator();
            while (it.hasNext()) {
                OperationMode operationMode = (OperationMode) it.next();
                Iterator<Map.Entry<String, List<RacModelWiseData.RacModeDetail>>> it2 = this.racListCloudIDToModeMap.entrySet().iterator();
                Long l = null;
                String str = null;
                boolean z = true;
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = this.mFragmentToActivityCallback.getRacModelWiseConfigurationList().getRacModelWiseDataBasedOnRacTypeId((String) it2.next().getKey()).getRacModeDetails().getRacModeDetailBasedOnMode(operationMode);
                    if (z) {
                        l = Long.valueOf(racModeDetailBasedOnMode.getDefaultHumidity());
                        str = racModeDetailBasedOnMode.getTemperatureSettingType().name();
                        z = false;
                    } else if (l.longValue() != racModeDetailBasedOnMode.getDefaultHumidity() || !str.equalsIgnoreCase(racModeDetailBasedOnMode.getTemperatureSettingType().name())) {
                        arrayList.remove(operationMode);
                    }
                }
                arrayList.remove(operationMode);
            }
        }
        return arrayList;
    }

    private Boolean checkConfigChangedWithArrayList(ArrayList<OperationMode> arrayList, ArrayList<OperationMode> arrayList2) {
        boolean z;
        boolean z2 = true;
        if (arrayList.size() == arrayList2.size()) {
            Iterator<OperationMode> it = arrayList.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (!arrayList2.contains(it.next())) {
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            z = true;
        } else {
            z = true;
        }
        if (!z) {
            Iterator<String> it2 = this.oldCloudListId.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                if (!this.mGeoFencePair.getAssociatedRacCloudId().contains(it2.next())) {
                    break;
                }
            }
            return Boolean.valueOf(z2);
        }
        z2 = z;
        return Boolean.valueOf(z2);
    }
}
