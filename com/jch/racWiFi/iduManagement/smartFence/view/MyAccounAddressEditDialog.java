package com.jch.racWiFi.iduManagement.smartFence.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import com.jch.racWiFi.CoreActivity;
import com.jch.racWiFi.NetworkConnectivity;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.customViews.customDialogs.BigLayoutDialog;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.databinding.DialogAddressUpdateBinding;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.userManagement.model.UserAddress;
import com.jch_hitachi.aircloudglobal.R;
import java.io.IOException;
import java.util.List;

public class MyAccounAddressEditDialog extends BigLayoutDialog implements TextWatcher {
    Activity activity;
    AllFieldsValidation allFieldsValidation;
    private CoreActivity coreActivity;
    private final DialogAddressUpdateBinding dialogAddressUpdateBinding;
    Lifecycle lifecycle;
    private Handler locationCheckHandler = new Handler();
    UserAddress userAddress = new UserAddress();
    private final View view = getWindow().getDecorView();

    public interface AllFieldsValidation {
        void onAllFieldsAreValidate(UserAddress userAddress);
    }

    static /* synthetic */ boolean lambda$validate$2(Dialog dialog, View view2) {
        return true;
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public DialogAddressUpdateBinding getDialogAddressUpdateBinding() {
        return this.dialogAddressUpdateBinding;
    }

    public MyAccounAddressEditDialog(Context context, int i, CoreActivity coreActivity2, Lifecycle lifecycle2, Activity activity2, AllFieldsValidation allFieldsValidation2) {
        super(context, i);
        this.coreActivity = coreActivity2;
        this.lifecycle = lifecycle2;
        this.activity = activity2;
        this.allFieldsValidation = allFieldsValidation2;
        DialogAddressUpdateBinding dialogAddressUpdateBinding2 = (DialogAddressUpdateBinding) DataBindingUtil.inflate(LayoutInflater.from(context), i, (ViewGroup) null, false);
        this.dialogAddressUpdateBinding = dialogAddressUpdateBinding2;
        setContentView(dialogAddressUpdateBinding2.getRoot());
        addTextWatcherListener();
        dialogAddressUpdateBinding2.buttonPositive.setOnClickListener(new MyAccounAddressEditDialog$$ExternalSyntheticLambda0(this));
        dialogAddressUpdateBinding2.buttonCancel.setOnClickListener(new MyAccounAddressEditDialog$$ExternalSyntheticLambda1(this));
        dialogAddressUpdateBinding2.editTextAddressLine1.setFilters(new InputFilter[]{dialogAddressUpdateBinding2.editTextAddressLine1.setEmojisInputFilter(), dialogAddressUpdateBinding2.editTextAddressLine1.lengthFilterAddressLine()});
        dialogAddressUpdateBinding2.editTextStreetArea.setFilters(new InputFilter[]{dialogAddressUpdateBinding2.editTextStreetArea.setEmojisInputFilter(), dialogAddressUpdateBinding2.editTextStreetArea.lengthFilter()});
        dialogAddressUpdateBinding2.editTextCity.setFilters(new InputFilter[]{dialogAddressUpdateBinding2.editTextCity.setEmojisInputFilter(), dialogAddressUpdateBinding2.editTextCity.lengthFilter()});
        dialogAddressUpdateBinding2.editTextCity.setFilters(new InputFilter[]{dialogAddressUpdateBinding2.editTextCity.setAddressInputFilter(), dialogAddressUpdateBinding2.editTextCity.lengthFilter()});
        dialogAddressUpdateBinding2.editTextState.setFilters(new InputFilter[]{dialogAddressUpdateBinding2.editTextState.setEmojisInputFilter(), dialogAddressUpdateBinding2.editTextState.lengthFilter()});
        dialogAddressUpdateBinding2.editTextState.setFilters(new InputFilter[]{dialogAddressUpdateBinding2.editTextState.setAddressInputFilter(), dialogAddressUpdateBinding2.editTextState.lengthFilter()});
        dialogAddressUpdateBinding2.editTextZipCode.setFilters(new InputFilter[]{dialogAddressUpdateBinding2.editTextZipCode.setEmojisInputFilter(), dialogAddressUpdateBinding2.editTextZipCode.lengthFilterZipCode()});
    }

    /* renamed from: lambda$new$0$com-jch-racWiFi-iduManagement-smartFence-view-MyAccounAddressEditDialog */
    public /* synthetic */ void mo30268xae7c0a9d(View view2) {
        this.userAddress.setAddressLine(this.dialogAddressUpdateBinding.editTextAddressLine1.getText().toString().trim());
        this.userAddress.setStreet(this.dialogAddressUpdateBinding.editTextStreetArea.getText().toString().trim());
        this.userAddress.setCity(this.dialogAddressUpdateBinding.editTextCity.getText().toString().trim());
        this.userAddress.setState(this.dialogAddressUpdateBinding.editTextState.getText().toString().trim());
        this.userAddress.setZipCode(this.dialogAddressUpdateBinding.editTextZipCode.getText().toString().trim());
        if (allFieldsValidated(this.userAddress.getStreet(), this.userAddress.getCity(), this.userAddress.getState(), this.userAddress.getZipCode())) {
            validate(this.userAddress);
        }
    }

    /* renamed from: lambda$new$1$com-jch-racWiFi-iduManagement-smartFence-view-MyAccounAddressEditDialog */
    public /* synthetic */ void mo30269x7587f19e(View view2) {
        dismiss();
    }

    private boolean allFieldsValidated(String str, String str2, String str3, String str4) {
        if (str == null || str2 == null || str3 == null || str4 == null) {
            if (str == null) {
                updateEditText(this.dialogAddressUpdateBinding.editTextStreetArea, this.dialogAddressUpdateBinding.enterStreetAreaBubbleLayout);
            }
            if (str2 == null) {
                updateEditText(this.dialogAddressUpdateBinding.editTextCity, this.dialogAddressUpdateBinding.enterCityBubbleLayout);
            }
            if (str3 == null) {
                updateEditText(this.dialogAddressUpdateBinding.editTextState, this.dialogAddressUpdateBinding.enterStateBubbleLayout);
            }
            if (str4 == null) {
                updateEditText(this.dialogAddressUpdateBinding.editTextZipCode, this.dialogAddressUpdateBinding.enterZipCodeBubbleLayout);
            }
            return false;
        } else if (!str.isEmpty() && !str2.isEmpty() && !str3.isEmpty() && !str4.isEmpty()) {
            return true;
        } else {
            if (str.isEmpty()) {
                updateEditText(this.dialogAddressUpdateBinding.editTextStreetArea, this.dialogAddressUpdateBinding.enterStreetAreaBubbleLayout);
            }
            if (str2.isEmpty()) {
                updateEditText(this.dialogAddressUpdateBinding.editTextCity, this.dialogAddressUpdateBinding.enterCityBubbleLayout);
            }
            if (str3.isEmpty()) {
                updateEditText(this.dialogAddressUpdateBinding.editTextState, this.dialogAddressUpdateBinding.enterStateBubbleLayout);
            }
            if (str4.isEmpty()) {
                updateEditText(this.dialogAddressUpdateBinding.editTextZipCode, this.dialogAddressUpdateBinding.enterZipCodeBubbleLayout);
            }
            return false;
        }
    }

    private void addTextWatcherListener() {
        this.dialogAddressUpdateBinding.editTextStreetArea.addTextChangedListener(this);
        this.dialogAddressUpdateBinding.editTextCity.addTextChangedListener(this);
        this.dialogAddressUpdateBinding.editTextState.addTextChangedListener(this);
        this.dialogAddressUpdateBinding.editTextZipCode.addTextChangedListener(this);
    }

    public void updateAddressView(UserAddress userAddress2) {
        this.userAddress.copy(userAddress2);
        this.dialogAddressUpdateBinding.editTextAddressLine1.setText(userAddress2.getAddressLine());
        this.dialogAddressUpdateBinding.editTextStreetArea.setText(userAddress2.getStreet());
        this.dialogAddressUpdateBinding.editTextCity.setText(userAddress2.getCity());
        this.dialogAddressUpdateBinding.editTextState.setText(userAddress2.getState());
        this.dialogAddressUpdateBinding.editTextZipCode.setText(userAddress2.getZipCode());
    }

    public void unbind() {
        this.dialogAddressUpdateBinding.unbind();
    }

    public void validate(UserAddress userAddress2) {
        if (NetworkConnectivity.isNetworkAvailable(getContext())) {
            Geocoder geocoder = new Geocoder(getContext());
            if (UserInfo.getCurrentUserInfo(this.coreActivity).userAddress.getCountryCode() != null) {
                if (verifyZipCodeV2(geocoder, userAddress2)) {
                    dismiss();
                    this.allFieldsValidation.onAllFieldsAreValidate(userAddress2);
                }
            } else if (verifyZipCodeV2(geocoder, userAddress2)) {
                dismiss();
                this.allFieldsValidation.onAllFieldsAreValidate(userAddress2);
            }
        } else {
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getContext());
            singleChoiceDialog.setTitleString(getContext().getResources().getString(R.string.common_alert));
            singleChoiceDialog.setMessageString(getContext().getResources().getString(R.string.common_alert_unableToConnectToNw));
            singleChoiceDialog.setCancelable(false);
            singleChoiceDialog.setPositiveButton(getContext().getResources().getString(R.string.common_btn_ok), MyAccounAddressEditDialog$$ExternalSyntheticLambda2.INSTANCE);
            singleChoiceDialog.show();
        }
    }

    private boolean verifyZipCodeV2(Geocoder geocoder, UserAddress userAddress2) {
        List<Address> list;
        userAddress2.setZipCode(addHypenForJapanRegionV2(userAddress2.getZipCode()));
        try {
            list = geocoder.getFromLocationName(userAddress2.getZipCode(), 1);
        } catch (IOException e) {
            e.printStackTrace();
            list = null;
        }
        if (list == null || list.isEmpty()) {
            Toast.makeText(getContext(), getContext().getResources().getString(R.string.common_alert_invalidZipCode), 1).show();
            return false;
        }
        userAddress2.setCountryCode(list.get(0).getCountryCode());
        return true;
    }

    private String addHypenForJapanRegionV2(String str) {
        if (str.length() != 7) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        sb.insert(3, Constants.FCM.DASH);
        return sb.toString();
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        updateEditText(this.dialogAddressUpdateBinding.editTextStreetArea, this.dialogAddressUpdateBinding.enterStreetAreaBubbleLayout);
        updateEditText(this.dialogAddressUpdateBinding.editTextCity, this.dialogAddressUpdateBinding.enterCityBubbleLayout);
        updateEditText(this.dialogAddressUpdateBinding.editTextState, this.dialogAddressUpdateBinding.enterStateBubbleLayout);
        updateEditText(this.dialogAddressUpdateBinding.editTextZipCode, this.dialogAddressUpdateBinding.enterZipCodeBubbleLayout);
    }

    private void updateEditText(EditText editText, ConstraintLayout constraintLayout) {
        if (editText.getText().toString().isEmpty()) {
            editText.setErrorBackgroundDrawable();
            constraintLayout.setVisibility(0);
            return;
        }
        editText.setNormalBackgroundDrawable();
        constraintLayout.setVisibility(4);
    }
}
