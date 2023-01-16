package com.jch.racWiFi.settings.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.amplitude.util.Screens;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.databinding.CustomerCareDevicesBinding;
import com.jch.racWiFi.databinding.CustomerCareVdGlobalBinding;
import com.jch.racWiFi.databinding.LayoutErrorDisplayBinding;
import com.jch.racWiFi.fcm.standard.BannerListener;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.fcm.util.Binder;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.iduManagement.IduList;
import com.jch.racWiFi.iduManagement.model.CustomerCareInfoModelResponse;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.settings.presenter.CustomerCareInfoPresenter;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch_hitachi.aircloudglobal.R;
import dagger.android.support.AndroidSupportInjection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;

public class CustomerCareFragmentGlobal extends GenericFragment implements CustomerCareInfoPresenter.CustomerCareInfoInterface {
    private View customView;
    private IduList iduList;
    private boolean isFromIDU;
    @Inject
    Binder mBinder;
    /* access modifiers changed from: private */
    public CustomerCareVdGlobalBinding mBinding;
    private RacListItemModel mCurrentSelectedRacListItemModel;
    private CustomerCareInfoModelResponse.CustomerCareInfo mCustomerCareInfo;
    private CustomerCareInfoPresenter mCustomerCareInfoPresenter;
    private int mHeightValue;
    private PopupWindow mPopupWindow;
    private RacListRecyclerViewAdapter racListRecyclerViewAdapter;
    private int widthvalue;

    public interface OnItemClickListener {
        void onClickOfRac(RacListItemModel racListItemModel);
    }

    static /* synthetic */ boolean lambda$showCustomerCareWebPortalNotAvailableDialog$15(Dialog dialog, View view) {
        return true;
    }

    static /* synthetic */ boolean lambda$showEmailNotAvailableDialog$13(Dialog dialog, View view) {
        return true;
    }

    static /* synthetic */ boolean lambda$showPhoneNumberNotAvailableDialog$14(Dialog dialog, View view) {
        return true;
    }

    static /* synthetic */ boolean lambda$showPleaseSelectAirConditionerDialog$12(Dialog dialog, View view) {
        return true;
    }

    public void onNetworkCallSuccess() {
    }

    public void serverException() {
    }

    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    private void showError(String str, String str2) {
        DetailedIduModel.IduErrorStatus iduErrorStatus = new DetailedIduModel.IduErrorStatus();
        iduErrorStatus.subCategory = str;
        iduErrorStatus.errorCode = str2;
        View root = this.mBinder.getBanner(iduErrorStatus, new BannerListener<DetailedIduModel.IduErrorStatus, LayoutErrorDisplayBinding>() {
            public void onItemClick(View view, DetailedIduModel.IduErrorStatus iduErrorStatus, LayoutErrorDisplayBinding layoutErrorDisplayBinding) {
            }

            public void onClick(View view, DetailedIduModel.IduErrorStatus iduErrorStatus, LayoutErrorDisplayBinding layoutErrorDisplayBinding) {
                CustomerCareFragmentGlobal.this.mBinding.containerLayout.removeAllViews();
            }
        }, false).getViewDataBinding().getRoot();
        if (root.getParent() != null) {
            ((ViewGroup) root.getParent()).removeView(root);
        }
        this.mBinding.containerLayout.addView(root);
    }

    public static CustomerCareFragmentGlobal newInstance() {
        CustomerCareFragmentGlobal customerCareFragmentGlobal = new CustomerCareFragmentGlobal();
        customerCareFragmentGlobal.setNewBundleAsArgument();
        return customerCareFragmentGlobal;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mBinding = (CustomerCareVdGlobalBinding) DataBindingUtil.inflate(layoutInflater, R.layout.customer_care_vd_global, viewGroup, false);
        IduList iduList2 = getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduList();
        this.iduList = iduList2;
        if (iduList2.isEmpty()) {
            this.mBinding.textViewSelectedDeviceName.setText(R.string.common_lbl_noAc);
            this.mBinding.layoutDeviceName.setBackgroundColor(ContextCompat.getColor(this.mBinding.getRoot().getContext(), R.color.button_disabled));
            this.mBinding.layoutDeviceName.setEnabled(false);
            this.mBinding.textViewSelectedDeviceName.setTextColor(ContextCompat.getColor(this.mBinding.getRoot().getContext(), R.color.white));
            this.mBinding.imageViewArrowDown.setColorFilter(ContextCompat.getColor(this.mBinding.getRoot().getContext(), R.color.white));
            this.mBinding.textViewSubtitleFour.setVisibility(4);
            this.mBinding.textViewVendorThingId.setVisibility(4);
        } else {
            this.mBinding.layoutDeviceName.setBackground(ContextCompat.getDrawable(this.mBinding.getRoot().getContext(), R.drawable.black_border_filled));
            this.mBinding.layoutDeviceName.setEnabled(true);
            this.mBinding.textViewSelectedDeviceName.setTextColor(ContextCompat.getColor(this.mBinding.getRoot().getContext(), R.color.textview_color_vd_light));
            this.mBinding.imageViewArrowDown.setColorFilter(ContextCompat.getColor(this.mBinding.getRoot().getContext(), R.color.colorRed));
            this.mBinding.textViewSubtitleFour.setVisibility(0);
            this.mBinding.textViewVendorThingId.setVisibility(0);
        }
        this.mCustomerCareInfo = null;
        this.mCurrentSelectedRacListItemModel = null;
        this.mCustomerCareInfoPresenter = new CustomerCareInfoPresenter(this);
        this.customView = layoutInflater.inflate(R.layout.popup_help_and_cutomercare, (ViewGroup) null);
        this.mBinding.layoutBottom.setVisibility(8);
        this.mBinding.layoutEuropeRegion.setVisibility(8);
        this.mBinding.layoutBottomGlobalLink.setVisibility(0);
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString("vendorThingId");
            String string2 = arguments.getString(Constants.FCM.RAC_NAME);
            long j = arguments.getLong("racId");
            String string3 = arguments.getString(Constants.FCM.SUB_CATEGORY);
            String string4 = arguments.getString(Constants.FCM.ERROR_CODE);
            int i = arguments.getInt(Constants.FCM.FAMILY_ID);
            if (!(string == null || string3 == null || i <= 0 || string4 == null || string2 == null || j <= 0)) {
                this.isFromIDU = true;
                this.mBinding.textViewSelectedDeviceName.setText(string2);
                this.mBinding.imageViewArrowDown.setVisibility(4);
                this.mBinding.layoutDeviceName.setEnabled(false);
                this.mBinding.imageButtonMenu.setImageDrawable(R.drawable.ic_back_button_svg);
                this.mBinding.textViewCustomerCareSubTitle1.setText(getString(R.string.serviceRequest_lbl_pleaseSelectMethodForRaisingServiceRequest));
                this.mBinding.textViewVendorThingId.setText(string);
                this.mBinding.view.setVisibility(0);
                this.mBinding.textViewCustomerCareSubTitleThree.setVisibility(8);
                showError(string3, string4);
                showPleaseWaitDialog();
                this.mCustomerCareInfoPresenter.getCustomerCareInfo(getViewLifecycleOwner(), i, (int) j);
            }
        }
        logEvent(Screens.SCREENS.name(), 11);
        logEvents(Events.CUSTOMER_CARE_FREQUENCY.name(), 0);
        return this.mBinding.getRoot();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.imageButtonMenu.setOnClickListener(new CustomerCareFragmentGlobal$$ExternalSyntheticLambda0(this));
        this.mBinding.buttonGlobalLink.setOnClickListener(new CustomerCareFragmentGlobal$$ExternalSyntheticLambda7(this));
        this.mBinding.buttonCustomerCarePortal.setOnClickListener(new CustomerCareFragmentGlobal$$ExternalSyntheticLambda8(this));
        this.mBinding.buttonCustomerCarePortalEurope.setOnClickListener(new CustomerCareFragmentGlobal$$ExternalSyntheticLambda9(this));
        this.mBinding.buttonCallCustomerCare.setOnClickListener(new CustomerCareFragmentGlobal$$ExternalSyntheticLambda10(this));
        this.mBinding.buttonEmailCustomerCare.setOnClickListener(new CustomerCareFragmentGlobal$$ExternalSyntheticLambda11(this));
        this.mBinding.layoutDeviceName.setOnClickListener(new CustomerCareFragmentGlobal$$ExternalSyntheticLambda12(this));
    }

    /* renamed from: lambda$onViewCreated$0$com-jch-racWiFi-settings-view-CustomerCareFragmentGlobal */
    public /* synthetic */ void mo31837xd97790bf(View view) {
        onClickMenu();
    }

    /* renamed from: lambda$onViewCreated$1$com-jch-racWiFi-settings-view-CustomerCareFragmentGlobal */
    public /* synthetic */ void mo31838x6664a7de(View view) {
        onClickGlobalLinkButton();
    }

    /* renamed from: lambda$onViewCreated$2$com-jch-racWiFi-settings-view-CustomerCareFragmentGlobal */
    public /* synthetic */ void mo31839xf351befd(View view) {
        onClickCustomerCarePortal();
    }

    /* renamed from: lambda$onViewCreated$3$com-jch-racWiFi-settings-view-CustomerCareFragmentGlobal */
    public /* synthetic */ void mo31840x803ed61c(View view) {
        onClickCustomerCarePortal();
    }

    /* renamed from: lambda$onViewCreated$4$com-jch-racWiFi-settings-view-CustomerCareFragmentGlobal */
    public /* synthetic */ void mo31841xd2bed3b(View view) {
        onClickCallCustomerCare();
    }

    /* renamed from: lambda$onViewCreated$5$com-jch-racWiFi-settings-view-CustomerCareFragmentGlobal */
    public /* synthetic */ void mo31842x9a19045a(View view) {
        onClickEmailCustomerCare();
    }

    /* renamed from: lambda$onViewCreated$6$com-jch-racWiFi-settings-view-CustomerCareFragmentGlobal */
    public /* synthetic */ void mo31843x27061b79(View view) {
        onClickLayoutName();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mCustomerCareInfoPresenter.removeCallbacks();
    }

    public void onClickMenu() {
        if (this.isFromIDU) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        } else {
            this.iDrawerMenuFunctions.openMenuDrawer();
        }
    }

    public void onClickGlobalLinkButton() {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.hitachiaircon.com/")));
    }

    /* access modifiers changed from: package-private */
    public void onClickCustomerCarePortal() {
        if (this.isFromIDU || this.mCurrentSelectedRacListItemModel != null) {
            CustomerCareInfoModelResponse.CustomerCareInfo customerCareInfo = this.mCustomerCareInfo;
            if (customerCareInfo != null) {
                String[] websiteUrl = customerCareInfo.getWebsiteUrl();
                if (websiteUrl == null) {
                    showCustomerCareWebPortalNotAvailableDialog();
                } else if (websiteUrl.length > 1) {
                    ListView listView = new ListView(getActivity());
                    listView.setAdapter(new ArrayAdapter(getActivity(), 17367043, websiteUrl));
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setView(listView);
                    builder.setCancelable(true);
                    builder.setTitle(getString(R.string.customerCare_lbl_seletectContact));
                    AlertDialog create = builder.create();
                    create.show();
                    listView.setOnItemClickListener(new CustomerCareFragmentGlobal$$ExternalSyntheticLambda15(this, create, websiteUrl));
                } else if (websiteUrl.length == 1) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(websiteUrl[0])));
                } else {
                    showCustomerCareWebPortalNotAvailableDialog();
                }
            } else {
                showCustomerCareWebPortalNotAvailableDialog();
            }
        } else {
            showPleaseSelectAirConditionerDialog();
        }
    }

    /* renamed from: lambda$onClickCustomerCarePortal$7$com-jch-racWiFi-settings-view-CustomerCareFragmentGlobal */
    public /* synthetic */ void mo31833x8eace418(AlertDialog alertDialog, String[] strArr, AdapterView adapterView, View view, int i, long j) {
        alertDialog.dismiss();
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(strArr[i])));
    }

    /* access modifiers changed from: package-private */
    public void onClickCallCustomerCare() {
        if (this.isFromIDU || this.mCurrentSelectedRacListItemModel != null) {
            CustomerCareInfoModelResponse.CustomerCareInfo customerCareInfo = this.mCustomerCareInfo;
            if (customerCareInfo == null) {
                showPhoneNumberNotAvailableDialog();
            } else if (customerCareInfo.getContactNumber().length > 1) {
                ListView listView = new ListView(getActivity());
                listView.setAdapter(new ArrayAdapter(getActivity(), 17367043, this.mCustomerCareInfo.getContactNumber()));
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(listView);
                builder.setCancelable(true);
                builder.setTitle(getString(R.string.customerCare_lbl_seletectContact));
                AlertDialog create = builder.create();
                create.show();
                listView.setOnItemClickListener(new CustomerCareFragmentGlobal$$ExternalSyntheticLambda13(this, create));
            } else if (this.mCustomerCareInfo.getContactNumber().length == 1) {
                String replace = this.mCustomerCareInfo.getContactNumber()[0].replace("-", "");
                Intent intent = new Intent("android.intent.action.DIAL");
                intent.setData(Uri.parse("tel:" + replace));
                startActivity(intent);
            } else {
                showPhoneNumberNotAvailableDialog();
            }
            dismissPleaseWaitDialog();
            return;
        }
        showPleaseSelectAirConditionerDialog();
    }

    /* renamed from: lambda$onClickCallCustomerCare$8$com-jch-racWiFi-settings-view-CustomerCareFragmentGlobal */
    public /* synthetic */ void mo31832x2bf7f4c5(AlertDialog alertDialog, AdapterView adapterView, View view, int i, long j) {
        alertDialog.dismiss();
        String replace = this.mCustomerCareInfo.getContactNumber()[i].replace("-", "");
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:" + replace));
        startActivity(intent);
    }

    /* access modifiers changed from: package-private */
    public void onClickEmailCustomerCare() {
        if (this.isFromIDU || this.mCurrentSelectedRacListItemModel != null) {
            CustomerCareInfoModelResponse.CustomerCareInfo customerCareInfo = this.mCustomerCareInfo;
            if (customerCareInfo == null) {
                showEmailNotAvailableDialog();
            } else if (customerCareInfo.getEmailId() == null) {
                showEmailNotAvailableDialog();
            } else if (this.mCustomerCareInfo.getEmailId().length > 1) {
                ListView listView = new ListView(getActivity());
                listView.setAdapter(new ArrayAdapter(getActivity(), 17367043, this.mCustomerCareInfo.getEmailId()));
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(listView);
                builder.setCancelable(true);
                builder.setTitle(getString(R.string.customerCare_lbl_seletectContact));
                AlertDialog create = builder.create();
                create.show();
                listView.setOnItemClickListener(new CustomerCareFragmentGlobal$$ExternalSyntheticLambda14(this, create));
            } else if (this.mCustomerCareInfo.getEmailId().length == 1) {
                Intent intent = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", this.mCustomerCareInfo.getEmailId()[0], (String) null));
                intent.putExtra("android.intent.extra.SUBJECT", "Subject");
                intent.putExtra("android.intent.extra.TEXT", "Body");
                startActivity(Intent.createChooser(intent, "Send email..."));
            } else {
                showEmailNotAvailableDialog();
            }
            dismissPleaseWaitDialog();
            return;
        }
        showPleaseSelectAirConditionerDialog();
    }

    /* renamed from: lambda$onClickEmailCustomerCare$9$com-jch-racWiFi-settings-view-CustomerCareFragmentGlobal */
    public /* synthetic */ void mo31834xd26850be(AlertDialog alertDialog, AdapterView adapterView, View view, int i, long j) {
        alertDialog.dismiss();
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", this.mCustomerCareInfo.getEmailId()[i], (String) null));
        intent.putExtra("android.intent.extra.SUBJECT", "Subject");
        intent.putExtra("android.intent.extra.TEXT", "Body");
        startActivity(Intent.createChooser(intent, "Send email..."));
    }

    public void onClickLayoutName() {
        IduList iduList2 = getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduList();
        this.iduList = iduList2;
        if (!iduList2.isEmpty()) {
            float dimension = getResources().getDimension(R.dimen.popup_height);
            float dimension2 = getResources().getDimension(R.dimen.popup_width);
            this.mHeightValue = Math.round(dimension);
            this.widthvalue = Math.round(dimension2);
            Display defaultDisplay = getActivity().getWindowManager().getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getSize(point);
            int round = point.x - Math.round(ViewUtils.convertDpToPixel(80.0f, getActivity()));
            RacListRecyclerViewAdapter racListRecyclerViewAdapter2 = new RacListRecyclerViewAdapter(this.mBinding.getRoot().getContext());
            this.racListRecyclerViewAdapter = racListRecyclerViewAdapter2;
            List<RacListItemModel> devices = racListRecyclerViewAdapter2.getDevices();
            Iterator it = this.iduList.iterator();
            while (it.hasNext()) {
                RacListItemModel racListItemModel = new RacListItemModel();
                racListItemModel.copyFromDetailIduModel((DetailedIduModel) it.next());
                racListItemModel.setOnItemClickListener(new CustomerCareFragmentGlobal$$ExternalSyntheticLambda6(this));
                devices.add(racListItemModel);
            }
            this.racListRecyclerViewAdapter.notifyDataSetChanged();
            if (this.racListRecyclerViewAdapter.getDevices().size() >= 6) {
                this.mPopupWindow = new PopupWindow(this.customView, round, this.mHeightValue);
            } else {
                this.mPopupWindow = new PopupWindow(this.customView, round, -2);
            }
            RecyclerView recyclerView = (RecyclerView) this.customView.findViewById(R.id.recycler_view_users_name);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(this.racListRecyclerViewAdapter);
            if (Build.VERSION.SDK_INT >= 21) {
                this.mPopupWindow.setElevation(5.0f);
            }
            this.mPopupWindow.setOutsideTouchable(true);
            this.mPopupWindow.setFocusable(true);
            this.mPopupWindow.setOnDismissListener(new CustomerCareFragmentGlobal$$ExternalSyntheticLambda1(this));
            if (!this.mPopupWindow.isShowing()) {
                int[] iArr = new int[2];
                this.mBinding.layoutDeviceName.getLocationInWindow(iArr);
                this.mPopupWindow.showAtLocation(this.mBinding.getRoot(), 48, 0, iArr[1] + this.mBinding.layoutDeviceName.getHeight());
                this.mBinding.parent.setBackgroundResource(R.drawable.transparent);
                this.mBinding.imageViewArrowDown.setRotation(180.0f);
                return;
            }
            this.mPopupWindow.dismiss();
            this.mBinding.parent.setBackgroundResource(R.drawable.transparent);
            return;
        }
        this.mBinding.textViewSelectedDeviceName.setText(R.string.common_lbl_noAc);
    }

    /* renamed from: lambda$onClickLayoutName$10$com-jch-racWiFi-settings-view-CustomerCareFragmentGlobal */
    public /* synthetic */ void mo31835x434ae0d0(RacListItemModel racListItemModel) {
        PopupWindow popupWindow = this.mPopupWindow;
        if (popupWindow != null && popupWindow.isShowing()) {
            this.mPopupWindow.dismiss();
        }
        showPleaseWaitDialog();
        this.mCustomerCareInfoPresenter.getCustomerCareInfo(getViewLifecycleOwner(), FamilyGroupList.getCurrentFamily().familyId, racListItemModel.racID);
        this.mBinding.textViewSelectedDeviceName.setText(racListItemModel.name);
        this.mBinding.textViewVendorThingId.setText(racListItemModel.vendorThingID);
        this.mCurrentSelectedRacListItemModel = racListItemModel;
    }

    /* renamed from: lambda$onClickLayoutName$11$com-jch-racWiFi-settings-view-CustomerCareFragmentGlobal */
    public /* synthetic */ void mo31836xd037f7ef() {
        this.mBinding.parent.setBackgroundResource(R.drawable.transparent);
        this.mBinding.imageViewArrowDown.setRotation(0.0f);
    }

    public void onCustomerCareInfoFetchSuccess(ArrayList<CustomerCareInfoModelResponse.CustomerCareInfo> arrayList) {
        dismissPleaseWaitDialog();
        if (arrayList != null && arrayList.size() > 0) {
            CustomerCareInfoModelResponse.CustomerCareInfo customerCareInfo = arrayList.get(0);
            this.mCustomerCareInfo = customerCareInfo;
            if (customerCareInfo != null) {
                StringBuilder sb = new StringBuilder();
                ArrayList<CustomerCareInfoModelResponse.WorkingHoursModel> arrayList2 = this.mCustomerCareInfo.workingHoursModels;
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    sb.append(arrayList2.get(i).getWorkingHoursString(getActivity()));
                    if (i < size - 1) {
                        sb.append("\n\n");
                    }
                }
                CustomerCareInfoModelResponse.CustomerCareInfo customerCareInfo2 = this.mCustomerCareInfo;
                if (customerCareInfo2 != null) {
                    if (customerCareInfo2.getRegion().equalsIgnoreCase("EU")) {
                        this.mBinding.layoutEuropeRegion.setVisibility(0);
                        this.mBinding.layoutBottom.setVisibility(8);
                        this.mBinding.layoutBottomGlobalLink.setVisibility(8);
                    } else {
                        this.mBinding.layoutBottom.setVisibility(0);
                        this.mBinding.layoutEuropeRegion.setVisibility(8);
                        this.mBinding.layoutBottomGlobalLink.setVisibility(8);
                    }
                }
                this.mBinding.textViewWorkingHoursValue.setText(sb);
                if (this.mCustomerCareInfo.getEmailId().length > 0) {
                    this.mBinding.constraintLayout12.setVisibility(0);
                } else {
                    this.mBinding.constraintLayout12.setVisibility(8);
                }
                if (this.mCustomerCareInfo.getContactNumber().length > 0) {
                    this.mBinding.buttonCallCustomerCare.setVisibility(0);
                } else {
                    this.mBinding.buttonCallCustomerCare.setVisibility(8);
                }
            }
        }
    }

    public void onCustomerCareInfoFetchFailure(GenericResponse genericResponse) {
        dismissPleaseWaitDialog();
        if (genericResponse.getResponse().code() != 401) {
            showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
            return;
        }
        showPleaseWaitDialog();
        getCoreActivity().refreshToken(new CallBackListener() {
            public void onFailure() {
            }

            public void onSuccess() {
                CustomerCareFragmentGlobal.this.callCutomerCareAPI();
            }
        }, false);
    }

    /* access modifiers changed from: private */
    public void callCutomerCareAPI() {
        dismissPleaseWaitDialog();
        this.mCustomerCareInfoPresenter.getCustomerCareInfo(getViewLifecycleOwner(), FamilyGroupList.getCurrentFamily().familyId, this.mCurrentSelectedRacListItemModel.racID);
    }

    public void onNetworkCallFailure(Throwable th) {
        dismissPleaseWaitDialog();
    }

    static class RacListRecyclerViewAdapter extends RecyclerView.Adapter<RacListViewHolder> {
        /* access modifiers changed from: private */
        public final Context context;
        /* access modifiers changed from: private */
        public final List<RacListItemModel> devices = new ArrayList();
        private RacListItemModel mSelectedUser;

        public List<RacListItemModel> getDevices() {
            return this.devices;
        }

        public RacListItemModel getUserName() {
            return this.mSelectedUser;
        }

        public void setSelectedLanguage(RacListItemModel racListItemModel) {
            this.mSelectedUser = racListItemModel;
        }

        public RacListRecyclerViewAdapter(Context context2) {
            this.context = context2;
        }

        public RacListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new RacListViewHolder((CustomerCareDevicesBinding) DataBindingUtil.inflate(LayoutInflater.from(this.context), R.layout.customer_care_devices, viewGroup, false));
        }

        public void onBindViewHolder(RacListViewHolder racListViewHolder, int i) {
            racListViewHolder.bind(this.devices.get(i));
        }

        public int getItemCount() {
            return this.devices.size();
        }

        class RacListViewHolder extends RecyclerView.ViewHolder {
            CustomerCareDevicesBinding binding;

            public RacListViewHolder(CustomerCareDevicesBinding customerCareDevicesBinding) {
                super(customerCareDevicesBinding.getRoot());
                this.binding = customerCareDevicesBinding;
                customerCareDevicesBinding.layoutManageDevices.setOnClickListener(new C2331x693525c9(this, customerCareDevicesBinding));
            }

            /* renamed from: lambda$new$0$com-jch-racWiFi-settings-view-CustomerCareFragmentGlobal$RacListRecyclerViewAdapter$RacListViewHolder */
            public /* synthetic */ void mo31865x4caf5039(CustomerCareDevicesBinding customerCareDevicesBinding, View view) {
                onClickItem(customerCareDevicesBinding.layoutManageDevices);
            }

            public void onClickItem(ConstraintLayout constraintLayout) {
                RacListItemModel racListItemModel = (RacListItemModel) constraintLayout.getTag();
                racListItemModel.onItemClickListener.onClickOfRac(racListItemModel);
                for (RacListItemModel racListItemModel2 : RacListRecyclerViewAdapter.this.devices) {
                    if (racListItemModel2.equals(racListItemModel)) {
                        racListItemModel2.setSelected(true);
                    } else {
                        racListItemModel2.setSelected(false);
                    }
                }
                RacListRecyclerViewAdapter.this.notifyDataSetChanged();
            }

            public void bind(RacListItemModel racListItemModel) {
                this.binding.textViewDeviceName.setText(racListItemModel.getName());
                if (racListItemModel.isSelected()) {
                    this.binding.layoutManageDevices.setBackgroundColor(ContextCompat.getColor(RacListRecyclerViewAdapter.this.context, R.color.lyt_grey));
                } else {
                    this.binding.layoutManageDevices.setBackgroundColor(ContextCompat.getColor(RacListRecyclerViewAdapter.this.context, R.color.white));
                }
                this.binding.layoutManageDevices.setTag(racListItemModel);
            }
        }
    }

    public static class RacListItemModel {
        /* access modifiers changed from: private */
        public String name;
        /* access modifiers changed from: private */
        public OnItemClickListener onItemClickListener;
        /* access modifiers changed from: private */
        public int racID;
        private boolean selected;
        /* access modifiers changed from: private */
        public String vendorThingID;

        public void setOnItemClickListener(OnItemClickListener onItemClickListener2) {
            this.onItemClickListener = onItemClickListener2;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public boolean isSelected() {
            return this.selected;
        }

        public void setSelected(boolean z) {
            this.selected = z;
        }

        public boolean equals(Object obj) {
            return (obj instanceof RacListItemModel) && ((RacListItemModel) obj).name.equals(this.name);
        }

        public void copyFromDetailIduModel(DetailedIduModel detailedIduModel) {
            this.name = detailedIduModel.name;
            this.racID = detailedIduModel.f454id.intValue();
            this.vendorThingID = detailedIduModel.vendorThingId;
        }
    }

    private void showPleaseSelectAirConditionerDialog() {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mBinding.getRoot().getContext());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(getString(R.string.customerCare_alert_selectAcToContactCustomerCare));
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), CustomerCareFragmentGlobal$$ExternalSyntheticLambda5.INSTANCE);
        singleChoiceDialog.show();
    }

    private void showEmailNotAvailableDialog() {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mBinding.getRoot().getContext());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(getString(R.string.customerCare_alert_emailAddressNotAvailable));
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), CustomerCareFragmentGlobal$$ExternalSyntheticLambda3.INSTANCE);
        singleChoiceDialog.show();
    }

    private void showPhoneNumberNotAvailableDialog() {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mBinding.getRoot().getContext());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(getString(R.string.customerCare_alert_phnNotAvailable));
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), CustomerCareFragmentGlobal$$ExternalSyntheticLambda4.INSTANCE);
        singleChoiceDialog.show();
    }

    private void showCustomerCareWebPortalNotAvailableDialog() {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(this.mBinding.getRoot().getContext());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(getString(R.string.customerCare_alert_webPortalNotAvailable));
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), CustomerCareFragmentGlobal$$ExternalSyntheticLambda2.INSTANCE);
        singleChoiceDialog.show();
    }
}
