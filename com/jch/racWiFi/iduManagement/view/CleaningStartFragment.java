package com.jch.racWiFi.iduManagement.view;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.iduManagement.JpRegulationConstants;
import com.jch.racWiFi.iduManagement.model.CleaningModeEnum;
import com.jch.racWiFi.iduManagement.model.CommandStatus;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.presenter.CleaningModePresenter;
import com.jch_hitachi.aircloudglobal.R;

public class CleaningStartFragment extends GenericFragment implements CleaningModePresenter.ICleaningModePresenter {
    @BindView(2131362078)
    ImageButton mBack;
    @BindView(2131362134)
    Button mCleanFilter;
    private CleaningModeEnum mCleaningModeEnum;
    @BindView(2131363997)
    TextView mCleaningModeHeading;
    private CleaningModePresenter mCleaningModePresenter;
    @BindView(2131364275)
    TextView mCleaningModeSubHeading;
    private DetailedIduModel mDetailedIduModel;
    private Unbinder mUnbinder;
    /* access modifiers changed from: private */
    public SingleChoiceDialog singleChoiceDialog;

    static /* synthetic */ boolean lambda$onFrostWashCommandFailure$0(Dialog dialog, View view) {
        return true;
    }

    public void onNetworkCallFailure(Throwable th) {
    }

    public void onNetworkCallSuccess() {
    }

    public void serverException() {
    }

    public static CleaningStartFragment newInstance() {
        CleaningStartFragment cleaningStartFragment = new CleaningStartFragment();
        cleaningStartFragment.setNewBundleAsArgument();
        return cleaningStartFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mDetailedIduModel = (DetailedIduModel) getArguments().getParcelable(DetailedIduModel.PARCEL_KEY);
        this.mCleaningModeEnum = (CleaningModeEnum) getArguments().getSerializable(CleaningModeEnum.PARCEL_KEY);
        this.singleChoiceDialog = new SingleChoiceDialog(getActivity());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.clean_filter_frame_start, viewGroup, false);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        CleaningModeEnum.CleaningModeDisplayInfoModel cleaningModeDisplayInfoModel = this.mCleaningModeEnum.getCleaningModeDisplayInfoModel();
        if (cleaningModeDisplayInfoModel != null) {
            updateUI(cleaningModeDisplayInfoModel);
        }
        CleaningModePresenter cleaningModePresenter = new CleaningModePresenter(this, getViewLifecycleOwner());
        this.mCleaningModePresenter = cleaningModePresenter;
        cleaningModePresenter.registerEventBus();
        return inflate;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mCleaningModePresenter.removeCallbacks();
        dismissPleaseWaitDialog();
        this.mUnbinder.unbind();
        this.mUnbinder = null;
    }

    public void onDestroy() {
        super.onDestroy();
        this.mCleaningModePresenter.unregisterEventBus();
    }

    @OnClick({2131362078})
    public void OnClickBack(ImageButton imageButton) {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    @OnClick({2131362134})
    public void OnClickCleanFilter(Button button) {
        showPleaseWaitDialog((long) JpRegulationConstants.JP_COMMAND_EXECUTION_TIMEOUT);
        if (C19592.$SwitchMap$com$jch$racWiFi$iduManagement$model$CleaningModeEnum[this.mCleaningModeEnum.ordinal()] == 1) {
            this.mCleaningModePresenter.startFrostWash(this.mDetailedIduModel);
        }
    }

    /* renamed from: com.jch.racWiFi.iduManagement.view.CleaningStartFragment$2 */
    static /* synthetic */ class C19592 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$iduManagement$model$CleaningModeEnum;

        static {
            int[] iArr = new int[CleaningModeEnum.values().length];
            $SwitchMap$com$jch$racWiFi$iduManagement$model$CleaningModeEnum = iArr;
            try {
                iArr[CleaningModeEnum.FROST_WASH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private void updateUI(CleaningModeEnum.CleaningModeDisplayInfoModel cleaningModeDisplayInfoModel) {
        this.mCleaningModeHeading.setText(cleaningModeDisplayInfoModel.heading);
        this.mCleanFilter.setText(cleaningModeDisplayInfoModel.cleanButtonStringResource);
        this.mCleaningModeSubHeading.setText(cleaningModeDisplayInfoModel.warning);
    }

    public void onFrostWashCommandSuccess() {
        dismissPleaseWaitDialog();
        getArguments().putBoolean(CleaningModeEnum.FROST_WASH_COMMAND_SUCCESS, true);
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_cleaningStartFragment_to_cleaningModeInProgressFragment, getArguments());
    }

    public void onFrostWashCommandFailure(CommandStatus.CommandStatusEnum commandStatusEnum, int i) {
        SingleChoiceDialog singleChoiceDialog2;
        dismissPleaseWaitDialog();
        if (i == 412) {
            showErrorMessage(getString(R.string.common_alert_currentlyOffline, this.mDetailedIduModel.name));
        } else if (i == 423) {
            showErrorMessage(getString(R.string.common_alert_anotherUserOpertRac, this.mDetailedIduModel.name));
        } else if (i == 429) {
            showErrorMessage(getString(R.string.common_alert_prevComndIsUnderExecution));
        } else if (commandStatusEnum != null && commandStatusEnum.equals(CommandStatus.CommandStatusEnum.INCOMPLETE) && (singleChoiceDialog2 = this.singleChoiceDialog) != null) {
            if (singleChoiceDialog2.isShowing()) {
                this.singleChoiceDialog.dismiss();
                return;
            }
            this.singleChoiceDialog.setTitleString(getString(R.string.common_alert));
            this.singleChoiceDialog.setMessageString(getString(R.string.alert_lbl_canNotStartFrostWashIndoor));
            this.singleChoiceDialog.setCancelable(false);
            this.singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), CleaningStartFragment$$ExternalSyntheticLambda0.INSTANCE);
            this.singleChoiceDialog.show();
        }
    }

    private void showErrorMessage(String str) {
        this.singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        this.singleChoiceDialog.setMessageString(str);
        this.singleChoiceDialog.setCancelable(false);
        this.singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                if (CleaningStartFragment.this.mFragmentToActivityCallback.getNavController() == null) {
                    return false;
                }
                CleaningStartFragment.this.singleChoiceDialog.dismiss();
                return false;
            }
        });
        if (!this.singleChoiceDialog.isShowing()) {
            this.singleChoiceDialog.show();
        }
    }
}
