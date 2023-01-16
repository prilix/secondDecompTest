package com.jch.racWiFi.energyConsumption.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.C1655R;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.customViews.customDialogs.ConfirmationDialog;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.databinding.FragmentEcSelectAcBinding;
import com.jch.racWiFi.energyConsumption.adapter.ECSelectACsAdapter;
import com.jch.racWiFi.energyConsumption.model.request.ECSettings;
import com.jch.racWiFi.energyConsumption.model.response.AllRac;
import com.jch.racWiFi.energyConsumption.model.response.AllRacResponseBody;
import com.jch.racWiFi.energyConsumption.viewModel.EnergyConsumptionViewModel;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.tooltip.ClosePolicy;
import com.jch.racWiFi.tooltip.ToolTipGravity;
import com.jch.racWiFi.tooltip.Tooltip;
import com.jch.racWiFi.tooltip.Typefaces;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36737d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0010H\u0002J\b\u0010\u0014\u001a\u00020\u0010H\u0002J\b\u0010\u0015\u001a\u00020\u0010H\u0002J$\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0010H\u0016J\b\u0010\u001f\u001a\u00020\u0010H\u0016J\u001a\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u00172\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010\"\u001a\u00020\u0010H\u0002J\b\u0010#\u001a\u00020\u0010H\u0002J \u0010$\u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u00172\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0002J\u0016\u0010)\u001a\u00020\u00102\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X.¢\u0006\u0002\n\u0000R&\u0010\u0006\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\b0\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\r0\u0007X\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, mo36738d2 = {"Lcom/jch/racWiFi/energyConsumption/fragment/ECSelectACFragment;", "Lcom/jch/racWiFi/GenericFragment;", "()V", "mAllRacList", "", "Lcom/jch/racWiFi/energyConsumption/model/response/AllRac;", "mECRacListStateObserver", "Landroidx/lifecycle/Observer;", "Ljava/util/HashMap;", "", "", "mIsChanged", "mRacIds", "Ljava/util/ArrayList;", "mRacIdsObserver", "getAllRacData", "", "getClosePolicy", "Lcom/jch/racWiFi/tooltip/ClosePolicy;", "handleNo", "handleYes", "onBackClick", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "onStop", "onViewCreated", "view", "saveEcSettings", "showConfirmation", "showToolTip", "factor", "", "gravity", "", "updateAdapter", "allRacList", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: ECSelectACFragment.kt */
public final class ECSelectACFragment extends GenericFragment {
    private List<AllRac> mAllRacList;
    private Observer<HashMap<Long, Boolean>> mECRacListStateObserver = new ECSelectACFragment$$ExternalSyntheticLambda9(this);
    private boolean mIsChanged;
    private ArrayList<Long> mRacIds;
    private Observer<ArrayList<Long>> mRacIdsObserver = new ECSelectACFragment$$ExternalSyntheticLambda8(this);

    public void _$_clearFindViewByIdCache() {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        View root = ((FragmentEcSelectAcBinding) DataBindingUtil.inflate(layoutInflater, R.layout.fragment_ec_select_ac, viewGroup, false)).getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        View view2 = getView();
        View view3 = null;
        ((TextView) (view2 == null ? null : view2.findViewById(C1655R.C1658id.toolbarTitle))).setText(getString(R.string.select_acs));
        View view4 = getView();
        ((ImageButton) (view4 == null ? null : view4.findViewById(C1655R.C1658id.back_button))).setVisibility(0);
        View view5 = getView();
        ((ImageButton) (view5 == null ? null : view5.findViewById(C1655R.C1658id.image_button_menu))).setVisibility(4);
        View view6 = getView();
        ((ImageButton) (view6 == null ? null : view6.findViewById(C1655R.C1658id.image_button_menu))).setOnClickListener(new ECSelectACFragment$$ExternalSyntheticLambda0(this));
        View view7 = getView();
        ((ImageButton) (view7 == null ? null : view7.findViewById(C1655R.C1658id.back_button))).setOnClickListener(new ECSelectACFragment$$ExternalSyntheticLambda5(this));
        View view8 = getView();
        ((ConstraintLayout) (view8 == null ? null : view8.findViewById(C1655R.C1658id.ecHeaderQuestionMark))).setOnClickListener(new ECSelectACFragment$$ExternalSyntheticLambda2(this));
        View view9 = getView();
        ((Button) (view9 == null ? null : view9.findViewById(C1655R.C1658id.layoutRacList)).findViewById(C1655R.C1658id.text_view_select_ac_save)).setOnClickListener(new ECSelectACFragment$$ExternalSyntheticLambda4(this));
        View view10 = getView();
        if (view10 != null) {
            view3 = view10.findViewById(C1655R.C1658id.layoutEcNoData);
        }
        ((ImageButton) view3.findViewById(C1655R.C1658id.ecNoDataAddImageButton)).setOnClickListener(new ECSelectACFragment$$ExternalSyntheticLambda3(this));
        getAllRacData();
    }

    /* access modifiers changed from: private */
    /* renamed from: onViewCreated$lambda-0  reason: not valid java name */
    public static final void m924onViewCreated$lambda0(ECSelectACFragment eCSelectACFragment, View view) {
        Intrinsics.checkNotNullParameter(eCSelectACFragment, "this$0");
        eCSelectACFragment.iDrawerMenuFunctions.openMenuDrawer();
    }

    /* access modifiers changed from: private */
    /* renamed from: onViewCreated$lambda-1  reason: not valid java name */
    public static final void m925onViewCreated$lambda1(ECSelectACFragment eCSelectACFragment, View view) {
        Intrinsics.checkNotNullParameter(eCSelectACFragment, "this$0");
        eCSelectACFragment.onBackClick();
    }

    /* access modifiers changed from: private */
    /* renamed from: onViewCreated$lambda-2  reason: not valid java name */
    public static final void m926onViewCreated$lambda2(ECSelectACFragment eCSelectACFragment, View view) {
        Intrinsics.checkNotNullParameter(eCSelectACFragment, "this$0");
        Intrinsics.checkNotNullExpressionValue(view, "it");
        eCSelectACFragment.showToolTip(view, 0.8f, ToolTipGravity.BOTTOM.name());
    }

    /* access modifiers changed from: private */
    /* renamed from: onViewCreated$lambda-3  reason: not valid java name */
    public static final void m927onViewCreated$lambda3(ECSelectACFragment eCSelectACFragment, View view) {
        Intrinsics.checkNotNullParameter(eCSelectACFragment, "this$0");
        eCSelectACFragment.saveEcSettings();
    }

    /* access modifiers changed from: private */
    /* renamed from: onViewCreated$lambda-4  reason: not valid java name */
    public static final void m928onViewCreated$lambda4(ECSelectACFragment eCSelectACFragment, View view) {
        Intrinsics.checkNotNullParameter(eCSelectACFragment, "this$0");
        if (FamilyGroupList.getCurrentFamily().role.f480id == 1) {
            eCSelectACFragment.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_EcSelectACsFragment_to_qrScanFragment);
            return;
        }
        Intrinsics.checkNotNullExpressionValue(view, "it");
        eCSelectACFragment.showToolTip(view, 0.7f, ToolTipGravity.TOP.name());
    }

    /* access modifiers changed from: private */
    public final void onBackClick() {
        View view = getView();
        if (((Button) (view == null ? null : view.findViewById(C1655R.C1658id.layoutRacList)).findViewById(C1655R.C1658id.text_view_select_ac_save)).isEnabled()) {
            showConfirmation();
            return;
        }
        Bundle arguments = getArguments();
        boolean z = true;
        if (arguments != null && arguments.getInt("from") == 1) {
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_EcSelectACsFragment_to_ECHomeFragment);
            return;
        }
        Bundle arguments2 = getArguments();
        if (arguments2 == null || arguments2.getInt("from") != 2) {
            z = false;
        }
        if (z) {
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_EcSelectACsFragment_to_EnergyConsumptionFragment);
        } else {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        }
    }

    /* access modifiers changed from: private */
    public final void saveEcSettings() {
        if (NetworkConnectivity.isNetworkAvailable(requireActivity())) {
            showPleaseWaitDialog();
            getCoreActivity().getGlobalViewModelRepository().getEnergyConsumptionViewModel().saveEcSettings(new ECSettings(this.mRacIds, (long) FamilyGroupList.getCurrentFamily().familyId)).observeWithCachedTrigger(getViewLifecycleOwner(), new ECSelectACFragment$$ExternalSyntheticLambda6(this));
            return;
        }
        showErrorPopUp(getString(R.string.common_alert_unableToConnectToNw));
    }

    /* access modifiers changed from: private */
    /* renamed from: saveEcSettings$lambda-5  reason: not valid java name */
    public static final void m929saveEcSettings$lambda5(ECSelectACFragment eCSelectACFragment, GenericResponse genericResponse) {
        String str;
        Intrinsics.checkNotNullParameter(eCSelectACFragment, "this$0");
        if (genericResponse == null || genericResponse.unableToReachServer()) {
            eCSelectACFragment.showErrorPopUp(eCSelectACFragment.getString(R.string.common_alert_unableToReachServer));
            return;
        }
        boolean z = false;
        if (genericResponse.isApiSuccessful()) {
            eCSelectACFragment.dismissPleaseWaitDialog();
            Collection collection = eCSelectACFragment.mRacIds;
            if (collection == null || collection.isEmpty()) {
                z = true;
            }
            if (z) {
                eCSelectACFragment.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_EcSelectACsFragment_to_ECHomeFragment);
            } else {
                eCSelectACFragment.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_EcSelectACsFragment_to_EnergyConsumptionFragment);
            }
        } else {
            int code = genericResponse.getResponse().code();
            if (code == 401) {
                eCSelectACFragment.showPleaseWaitDialog();
                eCSelectACFragment.getCoreActivity().refreshToken(new ECSelectACFragment$saveEcSettings$1$1(eCSelectACFragment), false);
            } else if (code != 404) {
                eCSelectACFragment.showErrorPopUp(eCSelectACFragment.getString(R.string.errorCode_alert_somethingWentWorng));
            } else {
                GenericResponse.GenericErrorBody genericErrorBody = (GenericResponse.GenericErrorBody) genericResponse.getErrorBodyOfType(GenericResponse.GenericErrorBody.class);
                EnergyConsumptionViewModel energyConsumptionViewModel = eCSelectACFragment.getCoreActivity().getGlobalViewModelRepository().getEnergyConsumptionViewModel();
                if (genericErrorBody == null) {
                    str = null;
                } else {
                    str = genericErrorBody.code;
                }
                eCSelectACFragment.showErrorPopUp(eCSelectACFragment.getString(energyConsumptionViewModel.getErrorMessageStringId(str)));
            }
        }
    }

    private final void getAllRacData() {
        showPleaseWaitDialog();
        getCoreActivity().getGlobalViewModelRepository().getEnergyConsumptionViewModel().getAllRacData(FamilyGroupList.getCurrentFamily().familyId).observeWithCachedTrigger(getViewLifecycleOwner(), new ECSelectACFragment$$ExternalSyntheticLambda7(this));
    }

    /* access modifiers changed from: private */
    /* renamed from: getAllRacData$lambda-7  reason: not valid java name */
    public static final void m921getAllRacData$lambda7(ECSelectACFragment eCSelectACFragment, GenericResponse genericResponse) {
        Intrinsics.checkNotNullParameter(eCSelectACFragment, "this$0");
        if (genericResponse == null || genericResponse.unableToReachServer()) {
            eCSelectACFragment.showErrorPopUp(eCSelectACFragment.getString(R.string.common_alert_unableToReachServer));
        } else if (genericResponse.isApiSuccessful()) {
            AllRacResponseBody allRacResponseBody = (AllRacResponseBody) genericResponse.getBodyOfType(AllRacResponseBody.class);
            if (allRacResponseBody != null) {
                eCSelectACFragment.updateAdapter(allRacResponseBody.getAllRacList());
            }
            eCSelectACFragment.dismissPleaseWaitDialog();
        }
    }

    private final void updateAdapter(List<AllRac> list) {
        Collection collection = list;
        View view = null;
        if (!(collection == null || collection.isEmpty())) {
            View view2 = getView();
            (view2 == null ? null : view2.findViewById(C1655R.C1658id.layoutEcNoData)).setVisibility(8);
            View view3 = getView();
            (view3 == null ? null : view3.findViewById(C1655R.C1658id.layoutRacList)).setVisibility(0);
            this.mAllRacList = list;
            EnergyConsumptionViewModel energyConsumptionViewModel = getCoreActivity().getGlobalViewModelRepository().getEnergyConsumptionViewModel();
            Intrinsics.checkNotNullExpressionValue(energyConsumptionViewModel, "coreActivity.globalViewM…nergyConsumptionViewModel");
            ECSelectACsAdapter eCSelectACsAdapter = new ECSelectACsAdapter(list, energyConsumptionViewModel);
            View view4 = getView();
            ((RecyclerView) (view4 == null ? null : view4.findViewById(C1655R.C1658id.layoutRacList)).findViewById(C1655R.C1658id.recyclerViewECSelectACs)).setLayoutManager(new LinearLayoutManager(getContext()));
            View view5 = getView();
            ((RecyclerView) (view5 == null ? null : view5.findViewById(C1655R.C1658id.layoutRacList)).findViewById(C1655R.C1658id.recyclerViewECSelectACs)).setAdapter(eCSelectACsAdapter);
            View view6 = getView();
            RecyclerView recyclerView = (RecyclerView) (view6 == null ? null : view6.findViewById(C1655R.C1658id.layoutRacList)).findViewById(C1655R.C1658id.recyclerViewECSelectACs);
            View view7 = getView();
            if (view7 != null) {
                view = view7.findViewById(C1655R.C1658id.layoutRacList);
            }
            recyclerView.addItemDecoration(new DividerItemDecoration(((RecyclerView) view.findViewById(C1655R.C1658id.recyclerViewECSelectACs)).getContext(), 1));
            return;
        }
        if (FamilyGroupList.getCurrentFamily().role.f480id == 1) {
            View view8 = getView();
            ((ImageButton) (view8 == null ? null : view8.findViewById(C1655R.C1658id.layoutEcNoData)).findViewById(C1655R.C1658id.ecNoDataAddImageButton)).setVisibility(0);
        } else {
            View view9 = getView();
            ((ImageButton) (view9 == null ? null : view9.findViewById(C1655R.C1658id.layoutEcNoData)).findViewById(C1655R.C1658id.ecNoDataAddImageButton)).setVisibility(4);
        }
        View view10 = getView();
        (view10 == null ? null : view10.findViewById(C1655R.C1658id.layoutEcNoData)).setVisibility(0);
        View view11 = getView();
        if (view11 != null) {
            view = view11.findViewById(C1655R.C1658id.layoutRacList);
        }
        view.setVisibility(8);
    }

    /* access modifiers changed from: private */
    /* renamed from: mRacIdsObserver$lambda-8  reason: not valid java name */
    public static final void m923mRacIdsObserver$lambda8(ECSelectACFragment eCSelectACFragment, ArrayList arrayList) {
        Intrinsics.checkNotNullParameter(eCSelectACFragment, "this$0");
        eCSelectACFragment.mRacIds = arrayList;
    }

    /* access modifiers changed from: private */
    /* renamed from: mECRacListStateObserver$lambda-9  reason: not valid java name */
    public static final void m922mECRacListStateObserver$lambda9(ECSelectACFragment eCSelectACFragment, HashMap hashMap) {
        Intrinsics.checkNotNullParameter(eCSelectACFragment, "this$0");
        List<AllRac> list = eCSelectACFragment.mAllRacList;
        View view = null;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAllRacList");
            list = null;
        }
        for (AllRac next : list) {
            boolean z = !(hashMap == null ? false : Intrinsics.areEqual((Object) Boolean.valueOf(next.isSelected()), hashMap.get(Long.valueOf((long) next.getRacId()))));
            eCSelectACFragment.mIsChanged = z;
            if (z) {
                break;
            }
        }
        View view2 = eCSelectACFragment.getView();
        if (view2 != null) {
            view = view2.findViewById(C1655R.C1658id.layoutRacList);
        }
        ((Button) view.findViewById(C1655R.C1658id.text_view_select_ac_save)).setEnabled(eCSelectACFragment.mIsChanged);
    }

    public void onStart() {
        super.onStart();
        getCoreActivity().getGlobalViewModelRepository().getEnergyConsumptionViewModel().getRacIdsMap().observeSingleEvent(getViewLifecycleOwner(), this.mECRacListStateObserver);
        getCoreActivity().getGlobalViewModelRepository().getEnergyConsumptionViewModel().getRacIds().observeSingleEvent(getViewLifecycleOwner(), this.mRacIdsObserver);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new ECSelectACFragment$onStart$1(this));
    }

    public void onStop() {
        super.onStop();
        getCoreActivity().getGlobalViewModelRepository().getEnergyConsumptionViewModel().getRacIdsMap().removeObserver(this.mECRacListStateObserver);
        getCoreActivity().getGlobalViewModelRepository().getEnergyConsumptionViewModel().getRacIds().removeObserver(this.mRacIdsObserver);
    }

    private final void showConfirmation() {
        Context context = getContext();
        ConfirmationDialog confirmationDialog = context == null ? null : new ConfirmationDialog(context);
        if (confirmationDialog != null) {
            confirmationDialog.setMessageString(getString(R.string.common_alert_saveChangesDesc));
        }
        if (confirmationDialog != null) {
            confirmationDialog.setTitleString(getString(R.string.common_alert_saveChanges));
        }
        if (confirmationDialog != null) {
            confirmationDialog.setPositiveButton(getString(R.string.common_btn_yes), new ECSelectACFragment$$ExternalSyntheticLambda10(this));
        }
        if (confirmationDialog != null) {
            confirmationDialog.setNegativeButton(getString(R.string.common_btn_no), new ECSelectACFragment$$ExternalSyntheticLambda1(this));
        }
        if (confirmationDialog != null) {
            confirmationDialog.show();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: showConfirmation$lambda-11  reason: not valid java name */
    public static final boolean m930showConfirmation$lambda11(ECSelectACFragment eCSelectACFragment, Dialog dialog, View view) {
        Intrinsics.checkNotNullParameter(eCSelectACFragment, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
        eCSelectACFragment.handleYes();
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: showConfirmation$lambda-12  reason: not valid java name */
    public static final boolean m931showConfirmation$lambda12(ECSelectACFragment eCSelectACFragment, Dialog dialog, View view) {
        Intrinsics.checkNotNullParameter(eCSelectACFragment, "this$0");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        dialog.dismiss();
        eCSelectACFragment.handleNo();
        return true;
    }

    private final void handleYes() {
        saveEcSettings();
    }

    private final void handleNo() {
        Bundle arguments = getArguments();
        boolean z = true;
        if (arguments != null && arguments.getInt("from") == 1) {
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_EcSelectACsFragment_to_ECHomeFragment);
            return;
        }
        Bundle arguments2 = getArguments();
        if (arguments2 == null || arguments2.getInt("from") != 2) {
            z = false;
        }
        if (z) {
            this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_EcSelectACsFragment_to_EnergyConsumptionFragment);
        } else {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        }
    }

    private final void showToolTip(View view, float f, String str) {
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "view.context");
        Tooltip.Builder styleId = new Tooltip.Builder(context).anchor(view, 0, 0, false).styleId(Integer.valueOf(R.style.ToolTipLayoutDefaultStyle1));
        String string = Constants.CC.getResource(view.getContext()).getString(R.string.ec_for_multi_split_connection);
        Intrinsics.checkNotNullExpressionValue(string, "getResource(view.context…r_multi_split_connection)");
        Tooltip.Builder text = styleId.text((CharSequence) string);
        Typefaces typefaces = Typefaces.INSTANCE;
        Context context2 = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "view.context");
        Tooltip create = text.typeface(typefaces.get(context2, "fonts/SansPro-Regular.ttc")).maxWidth((int) (((float) view.getContext().getResources().getDisplayMetrics().widthPixels) * f)).arrow(true).floatingAnimation(Tooltip.Animation.Companion.getSLOW()).closePolicy(getClosePolicy()).showDuration(com.jch.racWiFi.Constants.VALID_WIFI_CONNECTION_CHECK_TIMEOUT).overlay(false).create();
        create.doOnShown(new ECSelectACFragment$showToolTip$1(this, view));
        create.doOnHidden(new ECSelectACFragment$showToolTip$2(this, view));
        create.show(view, Tooltip.Gravity.valueOf(str), true);
    }

    private final ClosePolicy getClosePolicy() {
        return new ClosePolicy.Builder().inside(true).outside(true).consume(true).build();
    }
}
