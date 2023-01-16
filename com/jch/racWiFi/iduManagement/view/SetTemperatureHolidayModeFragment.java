package com.jch.racWiFi.iduManagement.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import butterknife.BindView;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.Listeners.RepeatListener;
import com.jch.racWiFi.databinding.DialogSetModeAndTempHolidaymodeBinding;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.view.HolidayModeFragment;
import com.jch.racWiFi.settings.model.TemperatureUnit;
import com.jch_hitachi.aircloudglobal.R;
import java.util.Locale;

public class SetTemperatureHolidayModeFragment extends GenericFragment implements View.OnClickListener {
    private final float MAX_RANGE = 16.0f;
    private final float MIN_RANGE = 10.0f;
    private final float PITCH_VALUE = 1.0f;
    private DialogSetModeAndTempHolidaymodeBinding binding;
    private RepeatListener mDecreaseTemperatureRepeatListener = new RepeatListener(200, 200, new View.OnClickListener() {
        public void onClick(View view) {
            SetTemperatureHolidayModeFragment.this.decreaseTemperature();
        }
    });
    private RepeatListener mIncreaseTemperatureRepeateListener = new RepeatListener(200, 200, new View.OnClickListener() {
        public void onClick(View view) {
            SetTemperatureHolidayModeFragment.this.increaseTemperature();
        }
    });
    @BindView(2131363530)
    ConstraintLayout mParent;
    private HolidayModeFragment.Temperature mTemperature;
    private HolidayModeFragment.Temperature referenceTemperature;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.referenceTemperature = (HolidayModeFragment.Temperature) getArguments().getParcelable(DetailedIduModel.PARCEL_KEY);
        this.mTemperature = new HolidayModeFragment.Temperature(this.referenceTemperature.temperature);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        DialogSetModeAndTempHolidaymodeBinding dialogSetModeAndTempHolidaymodeBinding = (DialogSetModeAndTempHolidaymodeBinding) DataBindingUtil.inflate(layoutInflater, R.layout.dialog_set_mode_and_temp_holidaymode, viewGroup, false);
        this.binding = dialogSetModeAndTempHolidaymodeBinding;
        dialogSetModeAndTempHolidaymodeBinding.buttonNegative.setOnClickListener(this);
        this.binding.buttonPositive.setOnClickListener(this);
        this.binding.imageButtonIncreaseTemprature.setOnTouchListener(this.mIncreaseTemperatureRepeateListener);
        this.binding.imageButtonDecreaseTemprature.setOnTouchListener(this.mDecreaseTemperatureRepeatListener);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
                SetTemperatureHolidayModeFragment.this.onGoBack();
            }
        });
        updateTemperatureUI();
        return this.binding.getRoot();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_negative) {
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        } else if (id == R.id.button_positive) {
            this.referenceTemperature.temperature = this.mTemperature.temperature;
            this.mFragmentToActivityCallback.getNavController().navigateUp();
        }
    }

    /* access modifiers changed from: private */
    public void increaseTemperature() {
        if (this.mTemperature.temperature < 16.0f) {
            this.mTemperature.temperature += 1.0f;
            this.binding.textViewTemprature.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings((double) this.mTemperature.temperature))}));
            setTemperatureUnit();
        }
    }

    /* access modifiers changed from: private */
    public void decreaseTemperature() {
        if (this.mTemperature.temperature > 10.0f) {
            this.mTemperature.temperature -= 1.0f;
            this.binding.textViewTemprature.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings((double) this.mTemperature.temperature))}));
            setTemperatureUnit();
        }
    }

    private void updateTemperatureUI() {
        this.binding.textViewTemprature.setText(String.valueOf(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings((double) this.mTemperature.temperature))})));
        setTemperatureUnit();
    }

    private void setTemperatureUnit() {
        this.binding.textViewTempratureUnit.setText(TemperatureUnit.getTemperatureUnitFromSettings());
    }

    public void onStart() {
        super.onStart();
    }

    public boolean onBackPressed() {
        return super.onBackPressed();
    }

    public void onGoBack() {
        this.mFragmentToActivityCallback.getNavController().navigateUp();
    }
}
