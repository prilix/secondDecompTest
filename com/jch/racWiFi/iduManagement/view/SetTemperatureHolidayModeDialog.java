package com.jch.racWiFi.iduManagement.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.jch.racWiFi.Listeners.RepeatListener;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.iduManagement.view.HolidayModeFragment;
import com.jch.racWiFi.settings.model.TemperatureUnit;
import com.jch_hitachi.aircloudglobal.R;

public class SetTemperatureHolidayModeDialog extends Dialog {
    private final float MAX_RANGE = 16.0f;
    private final float MIN_RANGE = 10.0f;
    private final float PITCH_VALUE = 1.0f;
    private Button buttonNegative;
    private Button buttonPositive;
    private Context context;
    private ImageButton imageButtonDecreaseTemprature;
    private ImageButton imageButtonIncreaseTemprature;
    private RepeatListener mDecreaseTemperatureRepeatListener = new RepeatListener(200, 200, new View.OnClickListener() {
        public void onClick(View view) {
            SetTemperatureHolidayModeDialog.this.decreaseTemperature();
        }
    });
    private RepeatListener mIncreaseTemperatureRepeateListener = new RepeatListener(200, 200, new View.OnClickListener() {
        public void onClick(View view) {
            SetTemperatureHolidayModeDialog.this.increaseTemperature();
        }
    });
    /* access modifiers changed from: private */
    public HolidayModeFragment.Temperature mTemperature;
    /* access modifiers changed from: private */
    public SetOnSaveClickListener onClickListener;
    /* access modifiers changed from: private */
    public HolidayModeFragment.Temperature referenceTemperature;
    private TextView textViewTemperature;
    private TextView textViewTemperatureUnit;
    private View view;

    public interface SetOnSaveClickListener {
        void onClick();
    }

    public SetTemperatureHolidayModeDialog(Context context2, HolidayModeFragment.Temperature temperature) {
        super(context2);
        this.referenceTemperature = temperature;
        this.mTemperature = new HolidayModeFragment.Temperature(temperature.temperature);
        init(context2);
        updateTemperatureUI();
    }

    private void init(Context context2) {
        this.context = context2;
        setCancelable(false);
        View inflate = LayoutInflater.from(context2).inflate(R.layout.dialog_set_mode_and_temp_holidaymode, (ViewGroup) null);
        this.view = inflate;
        setContentView(inflate);
        this.textViewTemperature = (TextView) this.view.findViewById(R.id.text_view_temprature);
        this.textViewTemperatureUnit = (TextView) this.view.findViewById(R.id.text_view_temprature_unit);
        this.imageButtonIncreaseTemprature = (ImageButton) this.view.findViewById(R.id.image_button_increase_temprature);
        this.imageButtonDecreaseTemprature = (ImageButton) this.view.findViewById(R.id.image_button_decrease_temprature);
        this.buttonNegative = (Button) this.view.findViewById(R.id.button_negative);
        this.buttonPositive = (Button) this.view.findViewById(R.id.button_positive);
        this.imageButtonIncreaseTemprature.setOnTouchListener(this.mIncreaseTemperatureRepeateListener);
        this.imageButtonDecreaseTemprature.setOnTouchListener(this.mDecreaseTemperatureRepeatListener);
        this.buttonNegative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SetTemperatureHolidayModeDialog.this.dismiss();
            }
        });
        this.buttonPositive.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SetTemperatureHolidayModeDialog.this.referenceTemperature.temperature = SetTemperatureHolidayModeDialog.this.mTemperature.temperature;
                SetTemperatureHolidayModeDialog.this.onClickListener.onClick();
                SetTemperatureHolidayModeDialog.this.dismiss();
            }
        });
    }

    public void setOnSaveClickListener(SetOnSaveClickListener setOnSaveClickListener) {
        this.onClickListener = setOnSaveClickListener;
    }

    /* access modifiers changed from: private */
    public void increaseTemperature() {
        if (this.mTemperature.temperature < 16.0f) {
            this.mTemperature.temperature += 1.0f;
            this.textViewTemperature.setText(String.valueOf((int) TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings((double) this.mTemperature.temperature)));
            setTemperatureUnit();
        }
    }

    /* access modifiers changed from: private */
    public void decreaseTemperature() {
        if (this.mTemperature.temperature > 10.0f) {
            this.mTemperature.temperature -= 1.0f;
            this.textViewTemperature.setText(String.valueOf((int) TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings((double) this.mTemperature.temperature)));
            setTemperatureUnit();
        }
    }

    private void updateTemperatureUI() {
        this.textViewTemperature.setText(String.valueOf((int) TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings((double) this.mTemperature.temperature)));
        setTemperatureUnit();
    }

    private void setTemperatureUnit() {
        this.textViewTemperatureUnit.setText(TemperatureUnit.getTemperatureUnitFromSettings());
    }

    public void setParentView(final View view2) {
        setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(SetTemperatureHolidayModeDialog.this.getWindow().getAttributes());
                layoutParams.dimAmount = 0.2f;
                layoutParams.width = -1;
                layoutParams.height = -2;
                SetTemperatureHolidayModeDialog.this.getWindow().addFlags(2);
                SetTemperatureHolidayModeDialog.this.getWindow().setAttributes(layoutParams);
                View view = view2;
                if (view != null) {
                    view.setBackgroundResource(R.drawable.white_blur);
                }
            }
        });
        setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                View view = view2;
                if (view != null) {
                    view.setBackgroundResource(R.drawable.transparent);
                }
            }
        });
    }

    public void show() {
        super.show();
    }
}
