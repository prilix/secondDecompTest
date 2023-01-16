package com.jch.racWiFi.userManagement.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.accord.common.utils.Logger;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.jch.racWiFi.CoreActivity;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.Utils.ValidationUtils;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.iduManagement.view.CountryCodeDialog;
import com.jch.racWiFi.userManagement.SelectCountryCodeRecyclerViewAdapter;
import com.jch.racWiFi.userManagement.countryCodeManager.CountryUtils;
import com.jch.racWiFi.userManagement.model.CountryCodeUIConfiguration;
import com.jch_hitachi.aircloudglobal.R;

public class InvalidEmailAndMobileNumberDialog extends Dialog implements DialogInterface.OnShowListener {
    private ConstraintLayout countryCodeLayout;
    IReceiveCorrectedEmailOrPhoneNum iReceiveCorrectedEmailOrPhoneNum;
    private TextView mContactNameTV = ((TextView) findViewById(R.id.text_view_contact_name_dialog));
    private ConstraintLayout mCountryCodeLayout = ((ConstraintLayout) findViewById(R.id.layout_country_code));
    /* access modifiers changed from: private */
    public TextView mCountryCodeTextView = ((TextView) findViewById(R.id.text_view_country_number_code_login));
    private ImageView mCountryFlag = ((ImageView) findViewById(R.id.image_view_flag_login));
    /* access modifiers changed from: private */
    public EditText mEmailET = ((EditText) findViewById(R.id.edit_text_enter_email));
    /* access modifiers changed from: private */
    public EditText mMobileNUmberET = ((EditText) findViewById(R.id.edit_text_mobile_number));
    private ConstraintLayout mMobileNumberLayout = ((ConstraintLayout) findViewById(R.id.mobile_number_layout));
    private Button mRemoveButton = ((Button) findViewById(R.id.button_remove));
    private TextView mSubTitleTv = ((TextView) findViewById(R.id.text_view_confirm_dialog_sub_title));
    private TextView mTitleTv = ((TextView) findViewById(R.id.text_view_confirm_dialog_title));
    private Button mUpdateButton = ((Button) findViewById(R.id.button_update));
    /* access modifiers changed from: private */
    public String name;

    public interface IReceiveCorrectedEmailOrPhoneNum {
        void onClickRemove();

        void onReceivedCorrectedEmailOrPhoneNum(String str, String str2, String str3);
    }

    public void onShow(DialogInterface dialogInterface) {
    }

    InvalidEmailAndMobileNumberDialog(final Context context, final IReceiveCorrectedEmailOrPhoneNum iReceiveCorrectedEmailOrPhoneNum2) {
        super(context);
        setContentView(LayoutInflater.from(context).inflate(R.layout.dialog_invite_users, (ViewGroup) null, false));
        ViewUtils.setOutlineCountryImage(this.mCountryFlag);
        this.mCountryFlag.setElevation(ViewUtils.convertDpToPixel(3.0f, context));
        this.iReceiveCorrectedEmailOrPhoneNum = iReceiveCorrectedEmailOrPhoneNum2;
        CountryCodeUIConfiguration countryCodeUIConfigurationFromCountryObject = CountryCodeUIConfiguration.getCountryCodeUIConfigurationFromCountryObject(getContext(), CountryUtils.getByCode(getContext(), CountryUtils.getAllCountries(getContext()), "+91"));
        countryCodeUIConfigurationFromCountryObject.setCountryCodeString(getContext());
        updateCountryCodeSelectionOnCountryCodeChange(countryCodeUIConfigurationFromCountryObject);
        this.mUpdateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                InvalidEmailAndMobileNumberDialog.this.validateEmailAndPhoneNumber(UserInfo.getCurrentUserInfo((CoreActivity) context).phoneNumber, InvalidEmailAndMobileNumberDialog.this.name, InvalidEmailAndMobileNumberDialog.this.mEmailET.getText().toString(), InvalidEmailAndMobileNumberDialog.this.mMobileNUmberET.getText().toString(), InvalidEmailAndMobileNumberDialog.this.mCountryCodeTextView.getText().toString());
            }
        });
        this.mRemoveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                InvalidEmailAndMobileNumberDialog.this.dismiss();
                iReceiveCorrectedEmailOrPhoneNum2.onClickRemove();
            }
        });
        this.mCountryCodeLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                InvalidEmailAndMobileNumberDialog.this.showCountryCodeSelectionDialog();
            }
        });
    }

    public void setText(String str, String str2, String str3, String str4, String str5, String str6, Bitmap bitmap) {
        this.mTitleTv.setText(str2);
        this.mSubTitleTv.setText(str3);
        if (str4 != null && !str4.isEmpty()) {
            this.mMobileNUmberET.setVisibility(0);
            this.mMobileNUmberET.setText(str4);
            this.mEmailET.setVisibility(4);
        }
        if (str5 != null && !str5.isEmpty()) {
            this.mEmailET.setVisibility(0);
            this.mEmailET.setText(str5);
            this.mMobileNumberLayout.setVisibility(4);
        }
        if (str == null || str.isEmpty()) {
            this.mContactNameTV.setText("______");
            this.name = str;
        } else {
            this.mContactNameTV.setText(str);
            this.name = str;
        }
        this.mCountryFlag.setImageBitmap(bitmap);
        this.mCountryCodeTextView.setText(str6);
    }

    public void setParentView(final View view) {
        setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(InvalidEmailAndMobileNumberDialog.this.getWindow().getAttributes());
                layoutParams.dimAmount = 0.2f;
                layoutParams.width = -1;
                layoutParams.height = -2;
                InvalidEmailAndMobileNumberDialog.this.getWindow().addFlags(2);
                InvalidEmailAndMobileNumberDialog.this.getWindow().setAttributes(layoutParams);
                View view = view;
                if (view != null) {
                    view.setBackgroundResource(R.drawable.white_blur);
                }
            }
        });
        setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                View view = view;
                if (view != null) {
                    view.setBackgroundResource(R.drawable.transparent);
                }
            }
        });
    }

    public void validateEmailAndPhoneNumber(String str, String str2, String str3, String str4, String str5) {
        if (str4 != null && !str4.isEmpty() && str5 != null && !str5.isEmpty()) {
            if (PhoneNumberUtil.getInstance().isNANPACountry(getContext().getString(CountryUtils.getByCode(getContext(), CountryUtils.getAllCountries(getContext()), str5).getIso()).toUpperCase()) && str5.length() == 5 && str4.length() == 10) {
                str4 = str4.substring(3);
            }
        }
        String str6 = str5 + str4;
        String str7 = null;
        if (!ValidationUtils.isEmailAddressValid(str3)) {
            if (ValidationUtils.isMobileNumberValidInviteUser(str, str6)) {
                str3 = null;
                str7 = ValidationUtils.formatMobileNumberValidInviteUser(str, str6);
            } else {
                try {
                    Long.parseLong(str6);
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger.m47e("EXCEPTION", e.getMessage());
                }
                if (this.mMobileNumberLayout.getVisibility() == 0) {
                    if (!str4.isEmpty()) {
                        showSingleChiocePopUp(getContext().getResources().getString(R.string.common_alert), getContext().getResources().getString(R.string.common_alert_invalidMobNo));
                    } else {
                        showSingleChiocePopUp(getContext().getResources().getString(R.string.common_alert), getContext().getResources().getString(R.string.common_alert_phnNoFieldCannotBeEmpty));
                    }
                }
                if (this.mEmailET.getVisibility() == 0) {
                    if (!str3.isEmpty()) {
                        showSingleChiocePopUp(getContext().getResources().getString(R.string.common_alert), getContext().getResources().getString(R.string.common_alert_invalidEmailFormat));
                    } else {
                        showSingleChiocePopUp(getContext().getResources().getString(R.string.common_alert), getContext().getResources().getString(R.string.common_alert_emailFieldCannotBeEmpty));
                    }
                }
                str3 = null;
            }
        }
        if (str7 != null) {
            dismiss();
            this.iReceiveCorrectedEmailOrPhoneNum.onReceivedCorrectedEmailOrPhoneNum(str7, str3, str2);
        }
        if (str3 != null) {
            dismiss();
            this.iReceiveCorrectedEmailOrPhoneNum.onReceivedCorrectedEmailOrPhoneNum(str7, str3, str2);
        }
    }

    /* access modifiers changed from: private */
    public void showCountryCodeSelectionDialog() {
        final CountryCodeDialog countryCodeDialog = new CountryCodeDialog(getContext());
        countryCodeDialog.getCountryCodeRecyclerViewAdapter().setOnItemSelectionListener(new SelectCountryCodeRecyclerViewAdapter.OnItemSelectionListener() {
            public void onItemSelected(View view, CountryCodeUIConfiguration countryCodeUIConfiguration) {
                InvalidEmailAndMobileNumberDialog.this.updateCountryCodeSelectionOnCountryCodeChange(countryCodeUIConfiguration);
                countryCodeDialog.dismiss();
            }
        });
        countryCodeDialog.show();
    }

    public void updateCountryCodeSelectionOnCountryCodeChange(CountryCodeUIConfiguration countryCodeUIConfiguration) {
        this.mCountryCodeTextView.setText(countryCodeUIConfiguration.getCountryCodeString());
        this.mCountryFlag.setImageResource(countryCodeUIConfiguration.getCountryFlag());
        this.mMobileNUmberET.setFilters(new InputFilter[]{new InputFilter.LengthFilter(ValidationUtils.getMaxLengthOfMobileNumberBasedOnCountryCode(CountryCodeUIConfiguration.CURRENT.getCountryCodeString()))});
    }

    private void showSingleChiocePopUp(String str, String str2) {
        final SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getContext());
        singleChoiceDialog.setTitleString(str);
        singleChoiceDialog.setMessageString(str2);
        singleChoiceDialog.setCancelable(false);
        singleChoiceDialog.setPositiveButton(getContext().getResources().getString(R.string.common_btn_ok), new SingleChoiceDialog.CustomOnClickListener() {
            public boolean onButtonClickListener(Dialog dialog, View view) {
                singleChoiceDialog.dismiss();
                return false;
            }
        });
        singleChoiceDialog.show();
    }
}
