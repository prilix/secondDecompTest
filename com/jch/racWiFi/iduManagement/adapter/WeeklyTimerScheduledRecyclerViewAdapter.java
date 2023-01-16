package com.jch.racWiFi.iduManagement.adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.jch.racWiFi.customViews.customDialogs.ScheduleErrorDialog;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.OperationMode;
import com.jch.racWiFi.iduManagement.model.WeeklyTimerModelV2;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;

public class WeeklyTimerScheduledRecyclerViewAdapter extends RecyclerView.Adapter<DeviceViewHolder> {
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public ArrayList<WeeklyTimerModelV2.WeeklyTimerFactoryData> deviceRecyclerItemModelItemList;
    /* access modifiers changed from: private */
    public NavController mNavController;
    private DetailedIduModel mSelectedDevice;

    public class DeviceViewHolder_ViewBinding implements Unbinder {
        private DeviceViewHolder target;

        public DeviceViewHolder_ViewBinding(DeviceViewHolder deviceViewHolder, View view) {
            this.target = deviceViewHolder;
            deviceViewHolder.mDeviceName = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_time_weekly_timer, "field 'mDeviceName'", TextView.class);
            deviceViewHolder.mStartDay = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_start_day_weekly_timer, "field 'mStartDay'", TextView.class);
            deviceViewHolder.mEndDay = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_end_day_weekly_timer, "field 'mEndDay'", TextView.class);
            deviceViewHolder.mOperationMode = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_mode_weekly_timer, "field 'mOperationMode'", ImageView.class);
            deviceViewHolder.mTemperature = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_temprature_weekly_timer, "field 'mTemperature'", TextView.class);
            deviceViewHolder.mTemperatureUnit = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_temprature_unit_weekly_timer, "field 'mTemperatureUnit'", TextView.class);
            deviceViewHolder.mDeleteWeeklyTimer = (Button) C0840Utils.findRequiredViewAsType(view, R.id.button_delete_weekly_timer, "field 'mDeleteWeeklyTimer'", Button.class);
            deviceViewHolder.mConstraintLayout = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.layout_schedule_weekly_timer, "field 'mConstraintLayout'", ConstraintLayout.class);
        }

        public void unbind() {
            DeviceViewHolder deviceViewHolder = this.target;
            if (deviceViewHolder != null) {
                this.target = null;
                deviceViewHolder.mDeviceName = null;
                deviceViewHolder.mStartDay = null;
                deviceViewHolder.mEndDay = null;
                deviceViewHolder.mOperationMode = null;
                deviceViewHolder.mTemperature = null;
                deviceViewHolder.mTemperatureUnit = null;
                deviceViewHolder.mDeleteWeeklyTimer = null;
                deviceViewHolder.mConstraintLayout = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public DetailedIduModel getSelectedDevice() {
        return this.mSelectedDevice;
    }

    public WeeklyTimerScheduledRecyclerViewAdapter(Context context2, ArrayList<WeeklyTimerModelV2.WeeklyTimerFactoryData> arrayList, NavController navController) {
        this.context = context2;
        this.deviceRecyclerItemModelItemList = arrayList;
        this.mNavController = navController;
    }

    public DeviceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new DeviceViewHolder(LayoutInflater.from(this.context).inflate(R.layout.recycler_view_items_weekly_timer, viewGroup, false));
    }

    public void onBindViewHolder(DeviceViewHolder deviceViewHolder, int i) {
        deviceViewHolder.bind(this.deviceRecyclerItemModelItemList.get(i));
    }

    public int getItemCount() {
        return this.deviceRecyclerItemModelItemList.size();
    }

    class DeviceViewHolder extends RecyclerView.ViewHolder {
        @BindView(2131363261)
        ConstraintLayout mConstraintLayout;
        @BindView(2131362143)
        Button mDeleteWeeklyTimer;
        @BindView(2131364665)
        TextView mDeviceName;
        @BindView(2131364164)
        TextView mEndDay;
        @BindView(2131362957)
        ImageView mOperationMode;
        @BindView(2131364563)
        TextView mStartDay;
        @BindView(2131364647)
        TextView mTemperature;
        @BindView(2131364645)
        TextView mTemperatureUnit;

        public DeviceViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }

        public void bind(WeeklyTimerModelV2.WeeklyTimerFactoryData weeklyTimerFactoryData) {
            this.mDeviceName.setText(weeklyTimerFactoryData.startAt + " - " + weeklyTimerFactoryData.endAt);
            if (weeklyTimerFactoryData.day.equalsIgnoreCase(weeklyTimerFactoryData.startingDay)) {
                this.mStartDay.setText("");
            } else {
                this.mStartDay.setText(weeklyTimerFactoryData.startingDay);
            }
            if (weeklyTimerFactoryData.day.equalsIgnoreCase(weeklyTimerFactoryData.endingDay)) {
                this.mEndDay.setText("");
            } else {
                this.mEndDay.setText(weeklyTimerFactoryData.endingDay);
            }
            if (weeklyTimerFactoryData.power.equals(DetailedIduModel.POWER_ON)) {
                operationMode(weeklyTimerFactoryData.mode);
            } else {
                this.mOperationMode.setImageResource(R.drawable.circle_off);
            }
            this.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DeviceViewHolder.this.scheduleErrorDialog();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(WeeklyTimerModelV2.WeeklyTimerFactoryData.PARCEL_KEY, (WeeklyTimerModelV2.WeeklyTimerFactoryData) WeeklyTimerScheduledRecyclerViewAdapter.this.deviceRecyclerItemModelItemList.get(DeviceViewHolder.this.getAdapterPosition()));
                    bundle.putBoolean("ADD_BUTTON", false);
                    WeeklyTimerScheduledRecyclerViewAdapter.this.mNavController.navigate((int) R.id.action_weeklyTimerFragmentV2_to_weeklyTimerScheduleSettingsFragmentV3, bundle);
                }
            });
        }

        public void scheduleErrorDialog() {
            ScheduleErrorDialog scheduleErrorDialog = new ScheduleErrorDialog(WeeklyTimerScheduledRecyclerViewAdapter.this.context);
            scheduleErrorDialog.setTitleString(WeeklyTimerScheduledRecyclerViewAdapter.this.context.getString(R.string.weeklyTimer_alert_unableToOpenSchedule));
            scheduleErrorDialog.setMessageString((int) R.string.weeklyTimer_alert_unableToOpenScheduleDesc);
            scheduleErrorDialog.setPositiveButton(WeeklyTimerScheduledRecyclerViewAdapter.this.context.getString(R.string.common_btn_continue), new ScheduleErrorDialog.CustomOnClickListener() {
                public boolean onButtonClickListener(Dialog dialog, View view) {
                    dialog.dismiss();
                    return true;
                }
            });
            scheduleErrorDialog.setNegativeButton(WeeklyTimerScheduledRecyclerViewAdapter.this.context.getString(R.string.common_btn_cancel), new ScheduleErrorDialog.CustomOnClickListener() {
                public boolean onButtonClickListener(Dialog dialog, View view) {
                    dialog.dismiss();
                    return true;
                }
            });
            scheduleErrorDialog.setParentView(this.mConstraintLayout);
            scheduleErrorDialog.show();
        }

        private void operationMode(String str) {
            switch (C18431.$SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode[OperationMode.valueOf(str).ordinal()]) {
                case 1:
                    this.mOperationMode.setImageResource(R.drawable.ic_color_auto_mode_svg);
                    this.mTemperature.setTextColor(WeeklyTimerScheduledRecyclerViewAdapter.this.context.getResources().getColor(R.color.color_auto_global));
                    this.mTemperatureUnit.setTextColor(WeeklyTimerScheduledRecyclerViewAdapter.this.context.getResources().getColor(R.color.color_auto_global));
                    return;
                case 2:
                    this.mOperationMode.setImageResource(R.drawable.ic_color_cooling_mode_svg);
                    this.mTemperature.setTextColor(WeeklyTimerScheduledRecyclerViewAdapter.this.context.getResources().getColor(R.color.color_cooling_global));
                    this.mTemperatureUnit.setTextColor(WeeklyTimerScheduledRecyclerViewAdapter.this.context.getResources().getColor(R.color.color_cooling_global));
                    return;
                case 3:
                    this.mOperationMode.setImageResource(R.drawable.ic_color_heating_mode_svg);
                    this.mTemperature.setTextColor(WeeklyTimerScheduledRecyclerViewAdapter.this.context.getResources().getColor(R.color.color_heating_global));
                    this.mTemperatureUnit.setTextColor(WeeklyTimerScheduledRecyclerViewAdapter.this.context.getResources().getColor(R.color.color_heating_global));
                    return;
                case 4:
                    this.mOperationMode.setImageResource(R.drawable.ic_color_dehumidify_mode_svg);
                    this.mTemperature.setTextColor(WeeklyTimerScheduledRecyclerViewAdapter.this.context.getResources().getColor(R.color.color_dehumidify_global));
                    this.mTemperatureUnit.setTextColor(WeeklyTimerScheduledRecyclerViewAdapter.this.context.getResources().getColor(R.color.color_dehumidify_global));
                    return;
                case 5:
                    this.mOperationMode.setImageResource(R.drawable.ic_color_fan_mode_svg);
                    this.mTemperature.setTextColor(WeeklyTimerScheduledRecyclerViewAdapter.this.context.getResources().getColor(R.color.color_fan_global));
                    this.mTemperatureUnit.setTextColor(WeeklyTimerScheduledRecyclerViewAdapter.this.context.getResources().getColor(R.color.color_fan_global));
                    return;
                case 6:
                    this.mOperationMode.setImageResource(R.drawable.ic_color_dry_cool_mode_svg);
                    this.mTemperature.setTextColor(WeeklyTimerScheduledRecyclerViewAdapter.this.context.getResources().getColor(R.color.color_dry_cool_global));
                    this.mTemperatureUnit.setTextColor(WeeklyTimerScheduledRecyclerViewAdapter.this.context.getResources().getColor(R.color.color_dry_cool_global));
                    return;
                case 7:
                    this.mOperationMode.setImageResource(R.drawable.circle_off);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: com.jch.racWiFi.iduManagement.adapter.WeeklyTimerScheduledRecyclerViewAdapter$1 */
    static /* synthetic */ class C18431 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.jch.racWiFi.iduManagement.model.OperationMode[] r0 = com.jch.racWiFi.iduManagement.model.OperationMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode = r0
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.AUTO     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.COOLING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.HEATING     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.DRY     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x003e }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.FAN     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.DRY_COOL     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.adapter.WeeklyTimerScheduledRecyclerViewAdapter.C18431.<clinit>():void");
        }
    }
}
