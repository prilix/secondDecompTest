package com.jch.racWiFi.userManagement.view;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.jch.racWiFi.DemoMode.DemoModeModel;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Listeners.TextWatchers.GenericEmptyEditTextWatcher;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.userManagement.model.UpdateNameModels;
import com.jch.racWiFi.userManagement.presenter.MyAccountNamePresenter;
import com.jch_hitachi.aircloudglobal.R;

public class MyAccountNameFragment extends GenericFragment implements MyAccountNamePresenter.IMyAccountNamePresenter, TextWatcher {
    @BindView(2131362078)
    ImageButton mBack;
    @BindView(2131362373)
    EditText mFirstName;
    @BindView(2131362428)
    ConstraintLayout mFirstNameBubbleLayout;
    @BindView(2131362374)
    EditText mLastName;
    @BindView(2131362429)
    ConstraintLayout mLastNameBubbleLayout;
    @BindView(2131362375)
    EditText mMiddleName;
    private MyAccountNamePresenter mMyAccountNamePresenter;
    @BindView(2131363530)
    ConstraintLayout mParent;
    @BindView(2131364471)
    ImageButton mSave;
    private Unbinder mUnbinder;

    public void afterTextChanged(Editable editable) {
    }

    public void allFieldsValidated(UpdateNameModels.UpdateName updateName) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onNetworkCallSuccess() {
    }

    public static MyAccountNameFragment newInstance() {
        MyAccountNameFragment myAccountNameFragment = new MyAccountNameFragment();
        myAccountNameFragment.setNewBundleAsArgument();
        return myAccountNameFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.my_account_name_frame_with_bubble, viewGroup, false);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        this.mMyAccountNamePresenter = new MyAccountNamePresenter(this);
        String str = UserInfo.getCurrentUserInfo(getCoreActivity()).firstName;
        String str2 = UserInfo.getCurrentUserInfo(getCoreActivity()).middleName;
        String str3 = UserInfo.getCurrentUserInfo(getCoreActivity()).lastName;
        this.mFirstName.setText(str);
        this.mMiddleName.setText(str2);
        this.mLastName.setText(str3);
        GenericEmptyEditTextWatcher genericEmptyEditTextWatcher = new GenericEmptyEditTextWatcher(this.mFirstName);
        genericEmptyEditTextWatcher.setLayoutToolTip(this.mFirstNameBubbleLayout);
        genericEmptyEditTextWatcher.setErrorMessage(getString(R.string.common_alert_pleaseFillThisField));
        this.mFirstName.addTextChangedListener(genericEmptyEditTextWatcher);
        GenericEmptyEditTextWatcher genericEmptyEditTextWatcher2 = new GenericEmptyEditTextWatcher(this.mLastName);
        genericEmptyEditTextWatcher2.setLayoutToolTip(this.mLastNameBubbleLayout);
        genericEmptyEditTextWatcher2.setErrorMessage(getString(R.string.common_alert_pleaseFillThisField));
        this.mLastName.addTextChangedListener(genericEmptyEditTextWatcher2);
        EditText editText = this.mFirstName;
        editText.setFilters(new InputFilter[]{editText.setNameInputFilter(), this.mFirstName.lengthFilter()});
        EditText editText2 = this.mMiddleName;
        editText2.setFilters(new InputFilter[]{editText2.setNameInputFilter(), this.mMiddleName.lengthFilter()});
        EditText editText3 = this.mLastName;
        editText3.setFilters(new InputFilter[]{editText3.setNameInputFilter(), this.mLastName.lengthFilter()});
        return inflate;
    }

    /* access modifiers changed from: package-private */
    @OnClick({2131364471})
    public void OnClickSave(ImageButton imageButton) {
        if (this.mMyAccountNamePresenter.validateFields(this.mFirstName.getText().toString().trim(), this.mMiddleName.getText().toString().trim(), this.mLastName.getText().toString().trim())) {
            showPleaseWaitDialog();
            this.mMyAccountNamePresenter.updateNameOnServer(getViewLifecycleOwner());
        }
    }

    @OnClick({2131362078})
    public void OnClickBack(ImageButton imageButton) {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mMyAccountNamePresenter.removeCallbacks();
        this.mUnbinder.unbind();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void firstNameFieldEmptyCallback() {
        this.mFirstName.setErrorBackgroundDrawable();
        showSingleChiocePopUp(getString(R.string.common_alert_firstNameCannotBeEmpty));
    }

    public void lastNameFieldEmptyCallback() {
        this.mLastName.setErrorBackgroundDrawable();
        showSingleChiocePopUp(getString(R.string.common_alert_lastNameCannotBeEmpty));
    }

    public void onUpdateNameSuccess(UpdateNameModels.UpdateName updateName) {
        UserInfo.getCurrentUserInfo(getCoreActivity()).firstName = updateName.firstName;
        UserInfo.getCurrentUserInfo(getCoreActivity()).middleName = updateName.middleName;
        UserInfo.getCurrentUserInfo(getCoreActivity()).lastName = updateName.lastName;
        dismissPleaseWaitDialog();
        showSingleChiocePopUp2(getString(R.string.myAcc_alert_nameUpdatedSuccessflly)).setPositiveButton(getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                MyAccountNameFragment.this.mFragmentToActivityCallback.getNavController().navigateUp();
                dialog.dismiss();
                return false;
            }
        });
    }

    public void onUpdateNameFailure(GenericResponse genericResponse) {
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
                MyAccountNameFragment.this.callDisableAPI();
            }
        }, false);
    }

    /* access modifiers changed from: private */
    public void callDisableAPI() {
        dismissPleaseWaitDialog();
        this.mMyAccountNamePresenter.updateNameOnServer(getViewLifecycleOwner());
    }

    public void onNetworkCallFailure(Throwable th) {
        if (!DemoModeModel.DEMO_MODE_ON) {
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(requireActivity());
            singleChoiceDialog.setTitleString(getString(R.string.common_alert));
            singleChoiceDialog.setMessageString(getString(R.string.common_alert_unableToConnectToNw));
            singleChoiceDialog.setCancelable(false);
            singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
                public boolean onButtonClickListener(Dialog dialog, View view) {
                    return true;
                }
            });
            singleChoiceDialog.show();
        }
        dismissPleaseWaitDialog();
    }

    public void serverException() {
        dismissPleaseWaitDialog();
        showSingleChiocePopUp(getString(R.string.common_alert_somethingWentWrong));
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        String trim = this.mFirstName.getText().toString().trim();
        String trim2 = this.mLastName.getText().toString().trim();
        if (!trim.isEmpty()) {
            this.mFirstName.setNormalBackgroundDrawable();
        }
        if (!trim2.isEmpty()) {
            this.mLastName.setNormalBackgroundDrawable();
        }
    }

    private void showSingleChiocePopUp(String str) {
        final SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(requireActivity());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(str);
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                singleChoiceDialog.dismiss();
                return false;
            }
        });
        singleChoiceDialog.show();
    }

    private SingleChoiceDialog showSingleChiocePopUp2(String str) {
        SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(requireActivity());
        singleChoiceDialog.setTitleString(getString(R.string.common_alert));
        singleChoiceDialog.setMessageString(str);
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.show();
        return singleChoiceDialog;
    }
}
