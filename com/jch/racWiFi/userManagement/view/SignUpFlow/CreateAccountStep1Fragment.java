package com.jch.racWiFi.userManagement.view.SignUpFlow;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import com.jch.racWiFi.Constants;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Listeners.TextWatchers.GenericEmptyEditTextWatcher;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.amplitude.util.Events;
import com.jch.racWiFi.amplitude.util.Screens;
import com.jch.racWiFi.databinding.EnterNameFrameWithBubbleBinding;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userManagement.model.UserRegistrationModels;
import com.jch.racWiFi.userManagement.presenter.EnterNameCreateAccountPresenterV2;
import com.jch_hitachi.aircloudglobal.R;

public class CreateAccountStep1Fragment extends GenericFragment implements EnterNameCreateAccountPresenterV2.IEnterNameCreateAccountPresenter, TextWatcher {
    private EnterNameFrameWithBubbleBinding mBinding;
    private EnterNameCreateAccountPresenterV2 mEnterNameCreateAccountPresenter;
    private long mEntryTime;
    private boolean mIsHasFocusFirstName;
    private boolean mIsHasFocusLastName;
    private boolean mIsHasFocusMiddleName;
    private long mStartTimeFirstName;
    private long mStartTimeLastName;
    private long mStartTimeMiddleName;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public static CreateAccountStep1Fragment newInstance() {
        return new CreateAccountStep1Fragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mEntryTime = System.currentTimeMillis();
        this.mBinding = (EnterNameFrameWithBubbleBinding) DataBindingUtil.inflate(layoutInflater, R.layout.enter_name_frame_with_bubble, viewGroup, false);
        this.mEnterNameCreateAccountPresenter = new EnterNameCreateAccountPresenterV2(this);
        this.mBinding.includedLayout.buttonContinue.setEnabled(false);
        GenericEmptyEditTextWatcher genericEmptyEditTextWatcher = new GenericEmptyEditTextWatcher(this.mBinding.includedLayout.editTextEnterFirstName);
        genericEmptyEditTextWatcher.setLayoutToolTip(this.mBinding.includedLayout.enterFirstNameBubbleLayout);
        genericEmptyEditTextWatcher.setErrorMessage(getString(R.string.common_alert_pleaseFillThisField));
        this.mBinding.includedLayout.editTextEnterFirstName.addTextChangedListener(genericEmptyEditTextWatcher);
        GenericEmptyEditTextWatcher genericEmptyEditTextWatcher2 = new GenericEmptyEditTextWatcher(this.mBinding.includedLayout.editTextEnterLastName);
        genericEmptyEditTextWatcher2.setLayoutToolTip(this.mBinding.includedLayout.enterLastNameBubbleLayout);
        genericEmptyEditTextWatcher2.setErrorMessage(getString(R.string.common_alert_pleaseFillThisField));
        this.mBinding.includedLayout.editTextEnterLastName.addTextChangedListener(genericEmptyEditTextWatcher2);
        this.mBinding.includedLayout.editTextEnterFirstName.setFilters(new InputFilter[]{this.mBinding.includedLayout.editTextEnterFirstName.setNameInputFilter(), this.mBinding.includedLayout.editTextEnterFirstName.lengthFilter()});
        this.mBinding.includedLayout.editTextEnterMiddleName.setFilters(new InputFilter[]{this.mBinding.includedLayout.editTextEnterMiddleName.setNameInputFilter(), this.mBinding.includedLayout.editTextEnterMiddleName.lengthFilter()});
        this.mBinding.includedLayout.editTextEnterLastName.setFilters(new InputFilter[]{this.mBinding.includedLayout.editTextEnterLastName.setNameInputFilter(), this.mBinding.includedLayout.editTextEnterLastName.lengthFilter()});
        this.mBinding.includedLayout.editTextEnterFirstName.addTextChangedListener(this);
        this.mBinding.includedLayout.editTextEnterLastName.addTextChangedListener(this);
        logEvent(Screens.SCREENS.name(), 15);
        return this.mBinding.getRoot();
    }

    public void onResume() {
        super.onResume();
        if (Constants._INVITE_) {
            this.mBinding.includedLayout.textViewStep1Of4.setText(R.string.common_lbl_step1of3);
        } else {
            this.mBinding.includedLayout.textViewStep1Of4.setText(R.string.common_lbl_step1of4);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mBinding.includedLayout.buttonContinue.setOnClickListener(new CreateAccountStep1Fragment$$ExternalSyntheticLambda0(this));
        this.mBinding.backButton.setOnClickListener(new CreateAccountStep1Fragment$$ExternalSyntheticLambda1(this));
        this.mBinding.includedLayout.editTextEnterFirstName.setOnFocusChangeListener(new CreateAccountStep1Fragment$$ExternalSyntheticLambda2(this));
        this.mBinding.includedLayout.editTextEnterMiddleName.setOnFocusChangeListener(new CreateAccountStep1Fragment$$ExternalSyntheticLambda3(this));
        this.mBinding.includedLayout.editTextEnterLastName.setOnFocusChangeListener(new CreateAccountStep1Fragment$$ExternalSyntheticLambda4(this));
    }

    /* renamed from: lambda$onViewCreated$0$com-jch-racWiFi-userManagement-view-SignUpFlow-CreateAccountStep1Fragment */
    public /* synthetic */ void mo33034x62c88943(View view) {
        onClickContinue();
    }

    /* renamed from: lambda$onViewCreated$1$com-jch-racWiFi-userManagement-view-SignUpFlow-CreateAccountStep1Fragment */
    public /* synthetic */ void mo33035x9676b404(View view) {
        onBackButtonPressed();
    }

    /* renamed from: lambda$onViewCreated$2$com-jch-racWiFi-userManagement-view-SignUpFlow-CreateAccountStep1Fragment */
    public /* synthetic */ void mo33036xca24dec5(View view, boolean z) {
        if (!z) {
            logEvents(Events.CREATE_ACCOUNT_FIRST_NAME.name(), Constants.CC.getSeconds(System.currentTimeMillis() - this.mStartTimeFirstName));
        } else if (!this.mIsHasFocusFirstName) {
            this.mIsHasFocusFirstName = true;
            this.mStartTimeFirstName = System.currentTimeMillis();
        }
    }

    /* renamed from: lambda$onViewCreated$3$com-jch-racWiFi-userManagement-view-SignUpFlow-CreateAccountStep1Fragment */
    public /* synthetic */ void mo33037xfdd30986(View view, boolean z) {
        if (!z) {
            logEvents(Events.CREATE_ACCOUNT_MIDDLE_NAME.name(), Constants.CC.getSeconds(System.currentTimeMillis() - this.mStartTimeMiddleName));
        } else if (!this.mIsHasFocusMiddleName) {
            this.mIsHasFocusMiddleName = true;
            this.mStartTimeMiddleName = System.currentTimeMillis();
        }
    }

    /* renamed from: lambda$onViewCreated$4$com-jch-racWiFi-userManagement-view-SignUpFlow-CreateAccountStep1Fragment */
    public /* synthetic */ void mo33038x31813447(View view, boolean z) {
        if (!z) {
            logEvents(Events.CREATE_ACCOUNT_LAST_NAME.name(), Constants.CC.getSeconds(System.currentTimeMillis() - this.mStartTimeLastName));
        } else if (!this.mIsHasFocusLastName) {
            this.mIsHasFocusLastName = true;
            this.mStartTimeLastName = System.currentTimeMillis();
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mEnterNameCreateAccountPresenter.removeCallbacks();
    }

    public void onClickContinue() {
        this.mEnterNameCreateAccountPresenter.validateFields(this.mBinding.includedLayout.editTextEnterFirstName.getText().toString().trim(), this.mBinding.includedLayout.editTextEnterMiddleName.getText().toString().trim(), this.mBinding.includedLayout.editTextEnterLastName.getText().toString().trim());
    }

    public void onBackButtonPressed() {
        logEvents(Events.QUIT_REGISTRATION_STEP_1.name(), 0);
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    public void firstNameFieldEmptyCallback() {
        this.mBinding.includedLayout.editTextEnterFirstName.setErrorBackgroundDrawable();
        Toaster.makeToast(getActivity(), getString(R.string.common_alert_firstNameCannotBeEmpty), 0);
    }

    public void middleNameFieldEmptyCallback() {
        this.mBinding.includedLayout.editTextEnterMiddleName.setErrorBackgroundDrawable();
    }

    public void lastNameFieldEmptyCallback() {
        this.mBinding.includedLayout.editTextEnterLastName.setErrorBackgroundDrawable();
        Toaster.makeToast(getActivity(), getString(R.string.common_alert_lastNameCannotBeEmpty), 0);
    }

    public void allFieldsValidated(UserRegistrationModels.UserRegistration userRegistration) {
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.Keys.ENTRY_TIME, this.mEntryTime);
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.createAccountManualEntrySignUp2, bundle);
        logEvents(Events.CREATE_ACCOUNT_STEP_1_COMPLETION.name(), Constants.CC.getSeconds(System.currentTimeMillis() - this.mEntryTime));
    }

    private void initDebugData() {
        this.mBinding.includedLayout.editTextEnterFirstName.setText("hgvdfhujsdgr");
        this.mBinding.includedLayout.editTextEnterLastName.setText("djfgifrtghvj");
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.mBinding.includedLayout.buttonContinue.setEnabled(!this.mBinding.includedLayout.editTextEnterFirstName.getText().toString().trim().isEmpty() && !this.mBinding.includedLayout.editTextEnterLastName.getText().toString().trim().isEmpty());
    }
}
