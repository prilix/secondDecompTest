package com.jch.racWiFi.settings.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.jch.racWiFi.DemoMode.DemoModeModel;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Toast.Toaster;
import com.jch.racWiFi.UserInfo;
import com.jch.racWiFi.customViews.customDialogs.SingleChoiceDialog;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.fcm.standard.CallBackListener;
import com.jch.racWiFi.genericModels.GenericResponse;
import com.jch.racWiFi.settings.model.SettingsDataModels;
import com.jch.racWiFi.settings.model.TemperatureUnit;
import com.jch.racWiFi.settings.presenter.TemperatureFragmentPresenter;
import com.jch_hitachi.aircloudglobal.R;
import info.hoang8f.android.segmented.SegmentedGroup;

public class TemperaturePreferenceFragment extends GenericFragment implements TemperatureFragmentPresenter.ITemperatureFragmentPresenter {
    private int currentDemoScriptIndex = 0;
    @BindView(2131363569)
    RadioButton mCelsiusSelection;
    @BindView(2131363573)
    RadioButton mFahrenheitSelection;
    @BindView(2131363530)
    ConstraintLayout mParent;
    @BindView(2131364471)
    ImageButton mSave;
    /* access modifiers changed from: private */
    public TemperatureFragmentPresenter mTemperatureFragmentPresenter;
    @BindView(2131363692)
    SegmentedGroup mTemperatureSelection;
    /* access modifiers changed from: private */
    public TemperatureUnit mTemperatureUnit;
    @BindView(2131364644)
    TextView mTemperatureUnitText;
    private Unbinder mUnbinder;

    public void onNetworkCallSuccess() {
    }

    public void serverException() {
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public static TemperaturePreferenceFragment newInstance() {
        TemperaturePreferenceFragment temperaturePreferenceFragment = new TemperaturePreferenceFragment();
        temperaturePreferenceFragment.setNewBundleAsArgument();
        return temperaturePreferenceFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.temprature_unit_frame, viewGroup, false);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        TemperatureFragmentPresenter temperatureFragmentPresenter = new TemperatureFragmentPresenter(this);
        this.mTemperatureFragmentPresenter = temperatureFragmentPresenter;
        temperatureFragmentPresenter.registerEventBus();
        this.mTemperatureSelection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radio_button_celsius) {
                    TemperaturePreferenceFragment.this.mCelsiusSelection.setBackgroundColor(TemperaturePreferenceFragment.this.getActivity().getResources().getColor(R.color.colorRed));
                    TemperaturePreferenceFragment.this.mCelsiusSelection.setTextColor(TemperaturePreferenceFragment.this.getActivity().getResources().getColor(R.color.white));
                    TemperaturePreferenceFragment.this.mFahrenheitSelection.setBackground(TemperaturePreferenceFragment.this.getActivity().getResources().getDrawable(R.drawable.black_border));
                    TemperaturePreferenceFragment.this.mFahrenheitSelection.setTextColor(TemperaturePreferenceFragment.this.getActivity().getResources().getColor(R.color.textview_color_vd_dark));
                    TemperaturePreferenceFragment.this.mTemperatureUnit = TemperatureUnit.CELSIUS;
                    TemperaturePreferenceFragment.this.mTemperatureUnitText.setText(TemperatureUnit.CELSIUS.toStringUnit());
                } else if (i == R.id.radio_button_fahrenheit) {
                    TemperaturePreferenceFragment.this.mCelsiusSelection.setBackground(TemperaturePreferenceFragment.this.getActivity().getResources().getDrawable(R.drawable.black_border));
                    TemperaturePreferenceFragment.this.mCelsiusSelection.setTextColor(TemperaturePreferenceFragment.this.getActivity().getResources().getColor(R.color.textview_color_vd_dark));
                    TemperaturePreferenceFragment.this.mFahrenheitSelection.setBackgroundColor(TemperaturePreferenceFragment.this.getActivity().getResources().getColor(R.color.colorRed));
                    TemperaturePreferenceFragment.this.mFahrenheitSelection.setTextColor(TemperaturePreferenceFragment.this.getActivity().getResources().getColor(R.color.white));
                    TemperaturePreferenceFragment.this.mTemperatureUnit = TemperatureUnit.FAHRENHEIT;
                    TemperaturePreferenceFragment.this.mTemperatureUnitText.setText(TemperatureUnit.FAHRENHEIT.toStringUnit());
                }
            }
        });
        this.mTemperatureSelection.check(UserInfo.getCurrentUserInfo(getCoreActivity()).settingsData.temperatureUnit.equals(TemperatureUnit.SERVER_DATA_CELSIUS) ? R.id.radio_button_celsius : R.id.radio_button_fahrenheit);
        this.mTemperatureUnitText.setText((UserInfo.getCurrentUserInfo(getCoreActivity()).settingsData.temperatureUnit.equals(TemperatureUnit.SERVER_DATA_CELSIUS) ? TemperatureUnit.CELSIUS : TemperatureUnit.FAHRENHEIT).toStringUnit());
        return inflate;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mTemperatureFragmentPresenter.unregisterEventBus();
        this.mTemperatureFragmentPresenter.removeCallbacks();
        this.mUnbinder.unbind();
        this.mUnbinder = null;
    }

    @OnClick({2131362078})
    public void onClickMenu(ImageButton imageButton) {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    @OnClick({2131364471})
    public void onClickSave() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true).setView(R.layout.temprature_unit_dialog);
        final AlertDialog create = builder.create();
        create.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                TemperaturePreferenceFragment.this.mParent.setBackgroundResource(R.drawable.white_blur);
            }
        });
        create.show();
        create.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                if (TemperaturePreferenceFragment.this.mParent != null) {
                    TemperaturePreferenceFragment.this.mParent.setBackgroundResource(R.drawable.transparent);
                }
            }
        });
        WindowManager.LayoutParams attributes = create.getWindow().getAttributes();
        attributes.dimAmount = 0.2f;
        create.getWindow().setAttributes(attributes);
        create.getWindow().addFlags(2);
        View decorView = create.getWindow().getDecorView();
        TextView textView = (TextView) decorView.findViewById(R.id.text_view_temprature_unit_dialog);
        Button button = (Button) decorView.findViewById(R.id.button_yes);
        Button button2 = (Button) decorView.findViewById(R.id.button_no);
        if (this.mCelsiusSelection.isChecked()) {
            textView.setText(getString(R.string.temp_lbl_celcius));
        } else {
            textView.setText(getString(R.string.temp_lbl_forenheat));
        }
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TemperaturePreferenceFragment.this.showPleaseWaitDialog();
                TemperaturePreferenceFragment.this.mTemperatureFragmentPresenter.saveTemperaturePreference(TemperaturePreferenceFragment.this.getCoreActivity(), TemperaturePreferenceFragment.this.mTemperatureUnit);
                create.dismiss();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                create.dismiss();
            }
        });
    }

    public void onNetworkCallFailure(Throwable th) {
        if (!DemoModeModel.DEMO_MODE_ON) {
            SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog(getActivity());
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

    public void onTemperaturePreferenceUpdateSuccess(SettingsDataModels.SettingsData settingsData) {
        dismissPleaseWaitDialog();
        UserInfo.getCurrentUserInfo(getCoreActivity()).settingsData.copy(settingsData);
        Toaster.makeToast(getActivity(), getString(R.string.common_alert_updatedSuccessfully), 0);
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    public void onTemperaturePreferenceUpdateFailure(GenericResponse genericResponse) {
        dismissPleaseWaitDialog();
        int i = genericResponse.getGenericErrorResponse().code;
        if (i == 400) {
            showErrorPopUp(getString(R.string.errorCode_alert_somethingWentWorng));
        } else if (i == 401) {
            showPleaseWaitDialog();
            getCoreActivity().refreshToken(new CallBackListener() {
                public void onFailure() {
                }

                public void onSuccess() {
                    TemperaturePreferenceFragment.this.showPleaseWaitDialog();
                    TemperaturePreferenceFragment.this.mTemperatureFragmentPresenter.saveTemperaturePreference(TemperaturePreferenceFragment.this.getCoreActivity(), TemperaturePreferenceFragment.this.mTemperatureUnit);
                }
            }, false);
        } else if (i != 404) {
            Toaster.makeToast(getActivity(), getString(R.string.userPefrence_alert_updateFailed), 0);
        } else {
            showErrorPopUp(getString(genericResponse.getErrorMessageStringId(String.valueOf(genericResponse.getGenericErrorResponse().code))));
        }
    }
}
