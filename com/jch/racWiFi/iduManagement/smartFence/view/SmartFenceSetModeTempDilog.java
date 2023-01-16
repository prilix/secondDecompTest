package com.jch.racWiFi.iduManagement.smartFence.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.github.mikephil.charting.utils.C1030Utils;
import com.jch.racWiFi.CoreActivity;
import com.jch.racWiFi.FragmentToActivityCallback;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.customViews.customDialogs.BigLayoutDialog;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.databinding.SmartFenceDialogSetModeTempLocationControlsBinding;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.OperationMode;
import com.jch.racWiFi.iduManagement.model.OperationModeUIConfigration;
import com.jch.racWiFi.iduManagement.model.RacModelWiseData;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFenceData;
import com.jch.racWiFi.iduManagement.smartFence.model.GeoFencePair;
import com.jch.racWiFi.settings.model.TemperatureUnit;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import org.slf4j.Marker;

public class SmartFenceSetModeTempDilog extends BigLayoutDialog {
    public static boolean isModeTempSettingChange;
    Context context;
    public String currentOperationMode;
    public double currentTempInCelsius;
    private FragmentToActivityCallback fragmentToActivityCallback;
    public long humidityGlobal;
    public boolean humiditySelected = false;
    boolean isModeSelected = false;
    /* access modifiers changed from: private */
    public final SmartFenceDialogSetModeTempLocationControlsBinding mBinding;
    private AlertDialog mModeSelectDialog;
    Map<OperationMode, ModeData> mModeToModeDataMap;
    /* access modifiers changed from: private */
    public List<OperationMode> menuItemsSetMode = new ArrayList();
    ArrayList<OperationMode> modeArrayListForRecyclerView;
    private Map<String, List<RacModelWiseData.RacModeDetail>> racListCloudIDToModeMap = new HashMap();
    SelectModeRecyclerViewAdapter selectModeRecyclerViewAdapter;
    SingleLiveEvent<SettingsModel> singleLiveEvent = new SingleLiveEvent<>();

    class SelectModeRecyclerViewAdapter extends RecyclerView.Adapter<SelectModeViewHolder> {
        private Context context;
        private List<OperationMode> menuItemList;

        public class SelectModeViewHolder_ViewBinding implements Unbinder {
            private SelectModeViewHolder target;
            private View view7f0a05c1;

            public SelectModeViewHolder_ViewBinding(final SelectModeViewHolder selectModeViewHolder, View view) {
                this.target = selectModeViewHolder;
                selectModeViewHolder.mMenuImageSetMode = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_mode_item, "field 'mMenuImageSetMode'", ImageView.class);
                selectModeViewHolder.mMenuInfoSetMode = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_mode_item, "field 'mMenuInfoSetMode'", TextView.class);
                View findRequiredView = C0840Utils.findRequiredView(view, R.id.layout_select_mode, "field 'mOuterLayout' and method 'onClickItem'");
                selectModeViewHolder.mOuterLayout = (ConstraintLayout) C0840Utils.castView(findRequiredView, R.id.layout_select_mode, "field 'mOuterLayout'", ConstraintLayout.class);
                this.view7f0a05c1 = findRequiredView;
                findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                    public void doClick(View view) {
                        selectModeViewHolder.onClickItem((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "onClickItem", 0, ConstraintLayout.class));
                    }
                });
            }

            public void unbind() {
                SelectModeViewHolder selectModeViewHolder = this.target;
                if (selectModeViewHolder != null) {
                    this.target = null;
                    selectModeViewHolder.mMenuImageSetMode = null;
                    selectModeViewHolder.mMenuInfoSetMode = null;
                    selectModeViewHolder.mOuterLayout = null;
                    this.view7f0a05c1.setOnClickListener((View.OnClickListener) null);
                    this.view7f0a05c1 = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        public SelectModeRecyclerViewAdapter(Context context2, List<OperationMode> list) {
            this.context = context2;
            this.menuItemList = list;
        }

        public SelectModeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new SelectModeViewHolder(LayoutInflater.from(this.context).inflate(R.layout.recycler_view_items_mode, viewGroup, false));
        }

        public void onBindViewHolder(SelectModeViewHolder selectModeViewHolder, int i) {
            selectModeViewHolder.bind(this.menuItemList.get(i));
        }

        public int getItemCount() {
            return this.menuItemList.size();
        }

        class SelectModeViewHolder extends RecyclerView.ViewHolder {
            @BindView(2131362950)
            ImageView mMenuImageSetMode;
            @BindView(2131364344)
            TextView mMenuInfoSetMode;
            @BindView(2131363265)
            ConstraintLayout mOuterLayout;

            public SelectModeViewHolder(View view) {
                super(view);
                ButterKnife.bind((Object) this, view);
            }

            @OnClick({2131363265})
            public void onClickItem(ConstraintLayout constraintLayout) {
                SmartFenceSetModeTempDilog.this.isModeSelected = true;
                SmartFenceSetModeTempDilog.this.showLayoutsModeSelected();
                OperationMode operationMode = (OperationMode) constraintLayout.getTag();
                SmartFenceSetModeTempDilog.this.currentOperationMode = operationMode.name();
                OperationModeUIConfigration.getOperationModeUIConfigrationBasedOnMode(operationMode);
                switch (C193812.$SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode[operationMode.ordinal()]) {
                    case 1:
                        SmartFenceSetModeTempDilog.this.setCooling();
                        SmartFenceSetModeTempDilog.this.dimissAlertDialog();
                        SmartFenceSetModeTempDilog.this.setTempAfterSelectingMode(OperationMode.COOLING);
                        SmartFenceSetModeTempDilog smartFenceSetModeTempDilog = SmartFenceSetModeTempDilog.this;
                        ModeData modeData = SmartFenceSetModeTempDilog.this.mModeToModeDataMap.get(OperationMode.COOLING);
                        Objects.requireNonNull(modeData);
                        ModeData modeData2 = modeData;
                        smartFenceSetModeTempDilog.humidityGlobal = modeData.defaultHumidity;
                        TextView textView = SmartFenceSetModeTempDilog.this.mBinding.textViewHumidityPercentageIduControl;
                        StringBuilder sb = new StringBuilder();
                        ModeData modeData3 = SmartFenceSetModeTempDilog.this.mModeToModeDataMap.get(OperationMode.COOLING);
                        Objects.requireNonNull(modeData3);
                        ModeData modeData4 = modeData3;
                        sb.append(modeData3.defaultHumidity);
                        sb.append("");
                        textView.setText(sb.toString());
                        return;
                    case 2:
                        SmartFenceSetModeTempDilog.this.setHeating();
                        SmartFenceSetModeTempDilog.this.dimissAlertDialog();
                        SmartFenceSetModeTempDilog.this.setTempAfterSelectingMode(OperationMode.HEATING);
                        SmartFenceSetModeTempDilog smartFenceSetModeTempDilog2 = SmartFenceSetModeTempDilog.this;
                        ModeData modeData5 = SmartFenceSetModeTempDilog.this.mModeToModeDataMap.get(OperationMode.HEATING);
                        Objects.requireNonNull(modeData5);
                        ModeData modeData6 = modeData5;
                        smartFenceSetModeTempDilog2.humidityGlobal = modeData5.defaultHumidity;
                        TextView textView2 = SmartFenceSetModeTempDilog.this.mBinding.textViewHumidityPercentageIduControl;
                        StringBuilder sb2 = new StringBuilder();
                        ModeData modeData7 = SmartFenceSetModeTempDilog.this.mModeToModeDataMap.get(OperationMode.HEATING);
                        Objects.requireNonNull(modeData7);
                        ModeData modeData8 = modeData7;
                        sb2.append(modeData7.defaultHumidity);
                        sb2.append("");
                        textView2.setText(sb2.toString());
                        return;
                    case 3:
                        SmartFenceSetModeTempDilog.this.setDry();
                        SmartFenceSetModeTempDilog.this.dimissAlertDialog();
                        SmartFenceSetModeTempDilog.this.setTempAfterSelectingMode(OperationMode.DRY);
                        SmartFenceSetModeTempDilog smartFenceSetModeTempDilog3 = SmartFenceSetModeTempDilog.this;
                        ModeData modeData9 = SmartFenceSetModeTempDilog.this.mModeToModeDataMap.get(OperationMode.DRY);
                        Objects.requireNonNull(modeData9);
                        ModeData modeData10 = modeData9;
                        smartFenceSetModeTempDilog3.humidityGlobal = modeData9.defaultHumidity;
                        TextView textView3 = SmartFenceSetModeTempDilog.this.mBinding.textViewHumidityPercentageIduControl;
                        StringBuilder sb3 = new StringBuilder();
                        ModeData modeData11 = SmartFenceSetModeTempDilog.this.mModeToModeDataMap.get(OperationMode.DRY);
                        Objects.requireNonNull(modeData11);
                        ModeData modeData12 = modeData11;
                        sb3.append(modeData11.defaultHumidity);
                        sb3.append("");
                        textView3.setText(sb3.toString());
                        return;
                    case 4:
                        SmartFenceSetModeTempDilog.this.setFan();
                        SmartFenceSetModeTempDilog.this.dimissAlertDialog();
                        SmartFenceSetModeTempDilog.this.setTempAfterSelectingMode(OperationMode.FAN);
                        SmartFenceSetModeTempDilog smartFenceSetModeTempDilog4 = SmartFenceSetModeTempDilog.this;
                        ModeData modeData13 = SmartFenceSetModeTempDilog.this.mModeToModeDataMap.get(OperationMode.FAN);
                        Objects.requireNonNull(modeData13);
                        ModeData modeData14 = modeData13;
                        smartFenceSetModeTempDilog4.humidityGlobal = modeData13.defaultHumidity;
                        TextView textView4 = SmartFenceSetModeTempDilog.this.mBinding.textViewHumidityPercentageIduControl;
                        StringBuilder sb4 = new StringBuilder();
                        ModeData modeData15 = SmartFenceSetModeTempDilog.this.mModeToModeDataMap.get(OperationMode.FAN);
                        Objects.requireNonNull(modeData15);
                        ModeData modeData16 = modeData15;
                        sb4.append(modeData15.defaultHumidity);
                        sb4.append("");
                        textView4.setText(sb4.toString());
                        return;
                    case 5:
                        ModeData modeData17 = SmartFenceSetModeTempDilog.this.mModeToModeDataMap.get(OperationMode.AUTO);
                        Objects.requireNonNull(modeData17);
                        ModeData modeData18 = modeData17;
                        if (modeData17.tempSettings.equals("ABSOLUTE")) {
                            selectAutoModeAbsoluteSettings();
                            return;
                        }
                        ModeData modeData19 = SmartFenceSetModeTempDilog.this.mModeToModeDataMap.get(OperationMode.AUTO);
                        Objects.requireNonNull(modeData19);
                        ModeData modeData20 = modeData19;
                        if (modeData19.tempSettings.equals("RELATIVE")) {
                            ModeData modeData21 = SmartFenceSetModeTempDilog.this.mModeToModeDataMap.get(OperationMode.AUTO);
                            Objects.requireNonNull(modeData21);
                            ModeData modeData22 = modeData21;
                            if (modeData21.visibilitySettings.equals("ABSOLUTE")) {
                                selectAutoModeRelativeAbsoluteSettings();
                                return;
                            }
                        }
                        selectAutoModeRelativeSettings();
                        return;
                    case 6:
                        SmartFenceSetModeTempDilog.this.setDryCool();
                        SmartFenceSetModeTempDilog.this.dimissAlertDialog();
                        SmartFenceSetModeTempDilog.this.setTempAfterSelectingMode(OperationMode.DRY_COOL);
                        SmartFenceSetModeTempDilog smartFenceSetModeTempDilog5 = SmartFenceSetModeTempDilog.this;
                        ModeData modeData23 = SmartFenceSetModeTempDilog.this.mModeToModeDataMap.get(OperationMode.DRY_COOL);
                        Objects.requireNonNull(modeData23);
                        ModeData modeData24 = modeData23;
                        smartFenceSetModeTempDilog5.humidityGlobal = modeData23.defaultHumidity;
                        TextView textView5 = SmartFenceSetModeTempDilog.this.mBinding.textViewHumidityPercentageIduControl;
                        StringBuilder sb5 = new StringBuilder();
                        ModeData modeData25 = SmartFenceSetModeTempDilog.this.mModeToModeDataMap.get(OperationMode.DRY_COOL);
                        Objects.requireNonNull(modeData25);
                        ModeData modeData26 = modeData25;
                        sb5.append(modeData25.defaultHumidity);
                        sb5.append("");
                        textView5.setText(sb5.toString());
                        return;
                    default:
                        SmartFenceSetModeTempDilog.this.setTemperatureUI();
                        return;
                }
            }

            private void selectAutoModeRelativeAbsoluteSettings() {
                SmartFenceSetModeTempDilog.this.setAuto();
                SmartFenceSetModeTempDilog.this.dimissAlertDialog();
                ModeData modeData = SmartFenceSetModeTempDilog.this.mModeToModeDataMap.get(OperationMode.AUTO);
                Objects.requireNonNull(modeData);
                ModeData modeData2 = modeData;
                double doubleValue = modeData.defaultTemp.doubleValue();
                ModeData modeData3 = SmartFenceSetModeTempDilog.this.mModeToModeDataMap.get(OperationMode.AUTO);
                Objects.requireNonNull(modeData3);
                ModeData modeData4 = modeData3;
                double d = modeData3.referenceTemp;
                SmartFenceSetModeTempDilog.this.currentTempInCelsius = doubleValue;
                String format = String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings(doubleValue + d))});
                SmartFenceSetModeTempDilog.this.mBinding.textViewTemprature.setText(String.format("%s", new Object[]{format}));
                SmartFenceSetModeTempDilog smartFenceSetModeTempDilog = SmartFenceSetModeTempDilog.this;
                ModeData modeData5 = SmartFenceSetModeTempDilog.this.mModeToModeDataMap.get(OperationMode.AUTO);
                Objects.requireNonNull(modeData5);
                ModeData modeData6 = modeData5;
                smartFenceSetModeTempDilog.humidityGlobal = modeData5.defaultHumidity;
                TextView textView = SmartFenceSetModeTempDilog.this.mBinding.textViewHumidityPercentageIduControl;
                StringBuilder sb = new StringBuilder();
                ModeData modeData7 = SmartFenceSetModeTempDilog.this.mModeToModeDataMap.get(OperationMode.AUTO);
                Objects.requireNonNull(modeData7);
                ModeData modeData8 = modeData7;
                sb.append(modeData7.defaultHumidity);
                sb.append("");
                textView.setText(sb.toString());
            }

            private void selectAutoModeRelativeSettings() {
                SmartFenceSetModeTempDilog.this.setAuto();
                SmartFenceSetModeTempDilog.this.dimissAlertDialog();
                ModeData modeData = SmartFenceSetModeTempDilog.this.mModeToModeDataMap.get(OperationMode.AUTO);
                Objects.requireNonNull(modeData);
                ModeData modeData2 = modeData;
                double d = modeData.referenceTemp;
                SmartFenceSetModeTempDilog.this.currentTempInCelsius = d;
                String format = String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettingsSmartFence(d))});
                int i = (d > C1030Utils.DOUBLE_EPSILON ? 1 : (d == C1030Utils.DOUBLE_EPSILON ? 0 : -1));
                if (i > 0) {
                    SmartFenceSetModeTempDilog.this.mBinding.textViewTemprature.setText(String.format("+%s", new Object[]{format}));
                } else if (i == 0) {
                    SmartFenceSetModeTempDilog.this.mBinding.textViewTemprature.setText(String.format("±%s", new Object[]{format}));
                } else {
                    SmartFenceSetModeTempDilog.this.mBinding.textViewTemprature.setText(String.format("%s", new Object[]{format}));
                }
                SmartFenceSetModeTempDilog smartFenceSetModeTempDilog = SmartFenceSetModeTempDilog.this;
                ModeData modeData3 = SmartFenceSetModeTempDilog.this.mModeToModeDataMap.get(OperationMode.AUTO);
                Objects.requireNonNull(modeData3);
                ModeData modeData4 = modeData3;
                smartFenceSetModeTempDilog.humidityGlobal = modeData3.defaultHumidity;
                TextView textView = SmartFenceSetModeTempDilog.this.mBinding.textViewHumidityPercentageIduControl;
                StringBuilder sb = new StringBuilder();
                ModeData modeData5 = SmartFenceSetModeTempDilog.this.mModeToModeDataMap.get(OperationMode.AUTO);
                Objects.requireNonNull(modeData5);
                ModeData modeData6 = modeData5;
                sb.append(modeData5.defaultHumidity);
                sb.append("");
                textView.setText(sb.toString());
            }

            private void selectAutoModeAbsoluteSettings() {
                SmartFenceSetModeTempDilog.this.setAuto();
                SmartFenceSetModeTempDilog.this.dimissAlertDialog();
                ModeData modeData = SmartFenceSetModeTempDilog.this.mModeToModeDataMap.get(OperationMode.AUTO);
                Objects.requireNonNull(modeData);
                ModeData modeData2 = modeData;
                double doubleValue = modeData.defaultTemp.doubleValue();
                SmartFenceSetModeTempDilog.this.currentTempInCelsius = doubleValue;
                SmartFenceSetModeTempDilog.this.mBinding.textViewTemprature.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings(doubleValue))}));
                SmartFenceSetModeTempDilog smartFenceSetModeTempDilog = SmartFenceSetModeTempDilog.this;
                ModeData modeData3 = SmartFenceSetModeTempDilog.this.mModeToModeDataMap.get(OperationMode.AUTO);
                Objects.requireNonNull(modeData3);
                ModeData modeData4 = modeData3;
                smartFenceSetModeTempDilog.humidityGlobal = modeData3.defaultHumidity;
                TextView textView = SmartFenceSetModeTempDilog.this.mBinding.textViewHumidityPercentageIduControl;
                StringBuilder sb = new StringBuilder();
                ModeData modeData5 = SmartFenceSetModeTempDilog.this.mModeToModeDataMap.get(OperationMode.AUTO);
                Objects.requireNonNull(modeData5);
                ModeData modeData6 = modeData5;
                sb.append(modeData5.defaultHumidity);
                sb.append("");
                textView.setText(sb.toString());
            }

            public void bind(OperationMode operationMode) {
                this.mMenuImageSetMode.setImageResource(operationMode.getDrawableRes());
                this.mMenuInfoSetMode.setText(operationMode.getStringRes());
                this.mOuterLayout.setTag(operationMode);
            }
        }
    }

    public SingleLiveEvent<SettingsModel> getSingleLiveEvent() {
        return this.singleLiveEvent;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SmartFenceSetModeTempDilog(Context context2, int i, int i2, int i3, FragmentToActivityCallback fragmentToActivityCallback2, GeoFencePair geoFencePair, ArrayList<OperationMode> arrayList, Map<String, List<RacModelWiseData.RacModeDetail>> map, boolean z, CoreActivity coreActivity) {
        super(context2, i);
        final Context context3 = context2;
        FragmentToActivityCallback fragmentToActivityCallback3 = fragmentToActivityCallback2;
        GeoFencePair geoFencePair2 = geoFencePair;
        final ArrayList<OperationMode> arrayList2 = arrayList;
        this.context = context3;
        this.modeArrayListForRecyclerView = arrayList2;
        this.racListCloudIDToModeMap = map;
        this.fragmentToActivityCallback = fragmentToActivityCallback3;
        Collections.sort(arrayList2, new Comparator<OperationMode>() {
            public int compare(OperationMode operationMode, OperationMode operationMode2) {
                return operationMode.getDisplayOrder() - operationMode2.getDisplayOrder();
            }
        });
        this.mModeToModeDataMap = new HashMap();
        Iterator<OperationMode> it = arrayList.iterator();
        while (it.hasNext()) {
            OperationMode next = it.next();
            ModeData modeData = new ModeData();
            modeData.minTemp = -1L;
            modeData.maxTemp = -1L;
            modeData.pitchValue = -1.0f;
            modeData.minHumidity = -1;
            modeData.maxHumidity = -1;
            Iterator<Map.Entry<String, List<RacModelWiseData.RacModeDetail>>> it2 = map.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry next2 = it2.next();
                RacModelWiseData.RacModeDetail racModeDetailBasedOnMode = fragmentToActivityCallback3.getRacModelWiseDataBasedOnRacTypeId((String) next2.getKey()).getRacModeDetails().getRacModeDetailBasedOnMode(next);
                DetailedIduModel detailedIduModelFromCloudId = coreActivity.getGlobalViewModelRepository().getIDUsUpdateViewModel().getIduList().getDetailedIduModelFromCloudId((String) next2.getKey());
                Objects.requireNonNull(detailedIduModelFromCloudId);
                DetailedIduModel detailedIduModel = detailedIduModelFromCloudId;
                modeData.relativeTemp = detailedIduModelFromCloudId.relativeTemperature;
                Iterator<Map.Entry<String, List<RacModelWiseData.RacModeDetail>>> it3 = it2;
                modeData.referenceTemp = (double) racModeDetailBasedOnMode.getReferenceTemperature();
                Objects.requireNonNull(racModeDetailBasedOnMode);
                RacModelWiseData.RacModeDetail racModeDetail = racModeDetailBasedOnMode;
                modeData.isHumidityEnabled = racModeDetailBasedOnMode.getEnableSettings().getHumidity();
                modeData.defaultHumidity = racModeDetailBasedOnMode.getDefaultHumidity();
                modeData.pitchValueHumidity = racModeDetailBasedOnMode.getHumiditySettingPitchType();
                modeData.defaultTemp = Double.valueOf((double) racModeDetailBasedOnMode.getDefaultTemperature());
                if (modeData.minTemp.longValue() == -1 && modeData.maxTemp.longValue() == -1) {
                    modeData.minTemp = Long.valueOf(racModeDetailBasedOnMode.getMinTemperature());
                    modeData.maxTemp = Long.valueOf(racModeDetailBasedOnMode.getMaxTemperature());
                } else {
                    if (modeData.minTemp.longValue() < racModeDetailBasedOnMode.getMinTemperature()) {
                        modeData.minTemp = Long.valueOf(racModeDetailBasedOnMode.getMinTemperature());
                    }
                    if (modeData.maxTemp.longValue() > racModeDetailBasedOnMode.getMaxTemperature()) {
                        modeData.maxTemp = Long.valueOf(racModeDetailBasedOnMode.getMaxTemperature());
                    }
                }
                if (modeData.minHumidity == -1 && modeData.maxHumidity == -1) {
                    modeData.minHumidity = racModeDetailBasedOnMode.getMinHumidity();
                    modeData.maxHumidity = racModeDetailBasedOnMode.getMaxHumidity();
                } else {
                    if (modeData.minHumidity < racModeDetailBasedOnMode.getMinHumidity()) {
                        modeData.minHumidity = racModeDetailBasedOnMode.getMinHumidity();
                    }
                    if (modeData.maxHumidity > racModeDetailBasedOnMode.getMaxHumidity()) {
                        modeData.maxHumidity = racModeDetailBasedOnMode.getMaxHumidity();
                    }
                }
                if (modeData.pitchValue == -1.0f) {
                    modeData.pitchValue = racModeDetailBasedOnMode.getTemperatureSettingPitchType();
                } else if (modeData.pitchValue < racModeDetailBasedOnMode.getTemperatureSettingPitchType()) {
                    modeData.pitchValue = racModeDetailBasedOnMode.getTemperatureSettingPitchType();
                }
                modeData.tempSettings = racModeDetailBasedOnMode.getTemperatureSetting();
                modeData.visibilitySettings = racModeDetailBasedOnMode.getVisibleTemperatureSetting();
                it2 = it3;
            }
            this.mModeToModeDataMap.put(next, modeData);
        }
        SmartFenceDialogSetModeTempLocationControlsBinding smartFenceDialogSetModeTempLocationControlsBinding = (SmartFenceDialogSetModeTempLocationControlsBinding) DataBindingUtil.inflate(LayoutInflater.from(context2), i, (ViewGroup) null, false);
        this.mBinding = smartFenceDialogSetModeTempLocationControlsBinding;
        setContentView(smartFenceDialogSetModeTempLocationControlsBinding.getRoot());
        smartFenceDialogSetModeTempLocationControlsBinding.textViewTempratureUnit.setText(getContext().getResources().getString(TemperatureUnit.getTemperatureUnitFromSettings()));
        smartFenceDialogSetModeTempLocationControlsBinding.switchMainHome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    SmartFenceSetModeTempDilog.this.showSettingsUI();
                    if (!SmartFenceSetModeTempDilog.this.isModeSelected) {
                        SmartFenceSetModeTempDilog.this.greyOutTheLayouts();
                        return;
                    }
                    return;
                }
                SmartFenceSetModeTempDilog.this.hideSettingsUI();
            }
        });
        smartFenceDialogSetModeTempLocationControlsBinding.selectModeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ArrayList arrayList = arrayList2;
                if (arrayList != null && !arrayList.isEmpty()) {
                    SmartFenceSetModeTempDilog smartFenceSetModeTempDilog = SmartFenceSetModeTempDilog.this;
                    SmartFenceSetModeTempDilog smartFenceSetModeTempDilog2 = SmartFenceSetModeTempDilog.this;
                    smartFenceSetModeTempDilog.selectModeRecyclerViewAdapter = new SelectModeRecyclerViewAdapter(context3, smartFenceSetModeTempDilog2.menuItemsSetMode);
                    SmartFenceSetModeTempDilog.this.menuItemsSetMode.clear();
                    SmartFenceSetModeTempDilog.this.menuItemsSetMode.addAll(arrayList2);
                    SmartFenceSetModeTempDilog.this.selectModeRecyclerViewAdapter.notifyDataSetChanged();
                    SmartFenceSetModeTempDilog.this.selectedMode(true);
                }
            }
        });
        smartFenceDialogSetModeTempLocationControlsBinding.textViewArrivingLeavingInsideGeorange.setText(getContext().getString(i2));
        smartFenceDialogSetModeTempLocationControlsBinding.imageViewAarivingLeaving.setImageDrawable(getContext().getDrawable(i3));
        smartFenceDialogSetModeTempLocationControlsBinding.layoutModeRoomDeviceControl.setOnClickListener(new SmartFenceSetModeTempDilog$$ExternalSyntheticLambda3(this, arrayList2, context3));
        smartFenceDialogSetModeTempLocationControlsBinding.buttonNegative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmartFenceSetModeTempDilog.this.dismiss();
            }
        });
        smartFenceDialogSetModeTempLocationControlsBinding.buttonPositive.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!SmartFenceSetModeTempDilog.this.isModeSelected) {
                    SmartFenceSetModeTempDilog.this.dismiss();
                    return;
                }
                SettingsModel settingsModel = new SettingsModel();
                settingsModel.mode = SmartFenceSetModeTempDilog.this.currentOperationMode;
                if (SmartFenceSetModeTempDilog.this.mBinding.switchMainHome.isChecked()) {
                    settingsModel.onOffStatus = DetailedIduModel.POWER_ON;
                } else {
                    settingsModel.onOffStatus = DetailedIduModel.POWER_OFF;
                }
                SmartFenceSetModeTempDilog.this.setRelativeTemp(settingsModel);
                SmartFenceSetModeTempDilog.this.setTemp(settingsModel);
                settingsModel.defaultHumidity = (double) SmartFenceSetModeTempDilog.this.humidityGlobal;
                SmartFenceSetModeTempDilog.isModeTempSettingChange = true;
                SmartFenceSetModeTempDilog.this.singleLiveEvent.postValue(settingsModel);
                SmartFenceSetModeTempDilog.this.dismiss();
            }
        });
        smartFenceDialogSetModeTempLocationControlsBinding.imageButtonIncreaseTemprature.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmartFenceSetModeTempDilog smartFenceSetModeTempDilog = SmartFenceSetModeTempDilog.this;
                smartFenceSetModeTempDilog.changeTemp(smartFenceSetModeTempDilog.currentOperationMode, true);
            }
        });
        smartFenceDialogSetModeTempLocationControlsBinding.imageButtonDecreaseTemprature.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmartFenceSetModeTempDilog smartFenceSetModeTempDilog = SmartFenceSetModeTempDilog.this;
                smartFenceSetModeTempDilog.changeTemp(smartFenceSetModeTempDilog.currentOperationMode, false);
            }
        });
        smartFenceDialogSetModeTempLocationControlsBinding.textViewHumidityTitleRoomdeviceControl.setOnClickListener(new SmartFenceSetModeTempDilog$$ExternalSyntheticLambda0(this));
        smartFenceDialogSetModeTempLocationControlsBinding.textViewSetTemp.setOnClickListener(new SmartFenceSetModeTempDilog$$ExternalSyntheticLambda1(this));
        if (z) {
            setDefaultForArriving(geoFencePair2);
        } else {
            setDefaultForLeaving(geoFencePair2);
        }
        smartFenceDialogSetModeTempLocationControlsBinding.imageButtonIncreaseHumidity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmartFenceSetModeTempDilog smartFenceSetModeTempDilog = SmartFenceSetModeTempDilog.this;
                smartFenceSetModeTempDilog.changeHumidity(smartFenceSetModeTempDilog.currentOperationMode, true);
            }
        });
        smartFenceDialogSetModeTempLocationControlsBinding.imageButtonDecreaseHumidity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmartFenceSetModeTempDilog smartFenceSetModeTempDilog = SmartFenceSetModeTempDilog.this;
                smartFenceSetModeTempDilog.changeHumidity(smartFenceSetModeTempDilog.currentOperationMode, false);
            }
        });
    }

    /* renamed from: lambda$new$0$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceSetModeTempDilog */
    public /* synthetic */ void mo30378x1996df84(ArrayList arrayList, Context context2, View view) {
        if (arrayList != null && !arrayList.isEmpty()) {
            this.selectModeRecyclerViewAdapter = new SelectModeRecyclerViewAdapter(context2, this.menuItemsSetMode);
            this.menuItemsSetMode.clear();
            this.menuItemsSetMode.addAll(arrayList);
            this.selectModeRecyclerViewAdapter.notifyDataSetChanged();
            selectedMode(false);
        }
    }

    /* renamed from: lambda$new$1$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceSetModeTempDilog */
    public /* synthetic */ void mo30379x3407d8a3(View view) {
        setHumidityUI();
    }

    /* renamed from: lambda$new$2$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceSetModeTempDilog */
    public /* synthetic */ void mo30380x4e78d1c2(View view) {
        setTemperatureUI();
    }

    /* access modifiers changed from: private */
    public void setTemp(SettingsModel settingsModel) {
        if (OperationMode.valueOf(settingsModel.mode) == OperationMode.FAN) {
            ModeData modeData = this.mModeToModeDataMap.get(OperationMode.FAN);
            Objects.requireNonNull(modeData);
            ModeData modeData2 = modeData;
            settingsModel.temp = modeData.defaultTemp.doubleValue();
        } else if (OperationMode.valueOf(settingsModel.mode) == OperationMode.AUTO) {
            ModeData modeData3 = this.mModeToModeDataMap.get(OperationMode.AUTO);
            Objects.requireNonNull(modeData3);
            ModeData modeData4 = modeData3;
            if (modeData3.tempSettings.equals("RELATIVE")) {
                ModeData modeData5 = this.mModeToModeDataMap.get(OperationMode.AUTO);
                Objects.requireNonNull(modeData5);
                ModeData modeData6 = modeData5;
                settingsModel.temp = modeData5.defaultTemp.doubleValue();
                return;
            }
            settingsModel.temp = this.currentTempInCelsius;
        } else {
            settingsModel.temp = this.currentTempInCelsius;
        }
    }

    /* access modifiers changed from: private */
    public void setRelativeTemp(SettingsModel settingsModel) {
        if (OperationMode.valueOf(settingsModel.mode) == OperationMode.AUTO) {
            ModeData modeData = this.mModeToModeDataMap.get(OperationMode.AUTO);
            Objects.requireNonNull(modeData);
            ModeData modeData2 = modeData;
            if (modeData.tempSettings.equals("RELATIVE")) {
                settingsModel.relativeTemp = this.currentTempInCelsius;
                return;
            }
            double d = settingsModel.temp;
            ModeData modeData3 = this.mModeToModeDataMap.get(OperationMode.valueOf(settingsModel.mode));
            Objects.requireNonNull(modeData3);
            ModeData modeData4 = modeData3;
            settingsModel.relativeTemp = d - modeData3.referenceTemp;
            return;
        }
        ModeData modeData5 = this.mModeToModeDataMap.get(OperationMode.valueOf(settingsModel.mode));
        Objects.requireNonNull(modeData5);
        ModeData modeData6 = modeData5;
        settingsModel.relativeTemp = (double) modeData5.relativeTemp;
    }

    /* access modifiers changed from: private */
    public void hideSettingsUI() {
        this.mBinding.layoutSetTempratureRoomDeviceControl.setVisibility(8);
        this.mBinding.layoutModeRoomDeviceControl.setVisibility(8);
        this.mBinding.selectModeButton.setVisibility(4);
    }

    /* access modifiers changed from: private */
    public void showSettingsUI() {
        this.mBinding.layoutSetTempratureRoomDeviceControl.setVisibility(0);
        this.mBinding.layoutModeRoomDeviceControl.setVisibility(0);
    }

    public void show() {
        ArrayList<OperationMode> arrayList;
        super.show();
        if (!this.isModeSelected && (arrayList = this.modeArrayListForRecyclerView) != null && !arrayList.isEmpty()) {
            this.selectModeRecyclerViewAdapter = new SelectModeRecyclerViewAdapter(this.context, this.menuItemsSetMode);
            this.menuItemsSetMode.clear();
            this.menuItemsSetMode.addAll(this.modeArrayListForRecyclerView);
            this.selectModeRecyclerViewAdapter.notifyDataSetChanged();
            selectedMode(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void setDefaultForArriving(GeoFencePair geoFencePair) {
        if (geoFencePair.getArrivingGeoFence().getModeTempSettings().powerMode == null) {
            this.isModeSelected = false;
            greyOutTheLayouts();
            return;
        }
        this.currentOperationMode = geoFencePair.getArrivingGeoFence().getModeTempSettings().mode;
        this.isModeSelected = true;
        this.mBinding.selectModeButton.setVisibility(4);
        this.mBinding.layoutModeRoomDeviceControl.setVisibility(0);
        if (OperationMode.valueOf(geoFencePair.getArrivingGeoFence().getModeTempSettings().mode) == OperationMode.AUTO) {
            ModeData modeData = this.mModeToModeDataMap.get(OperationMode.AUTO);
            Objects.requireNonNull(modeData);
            ModeData modeData2 = modeData;
            if (modeData.tempSettings.equals("ABSOLUTE")) {
                setTempForAutoModeAbsolute(geoFencePair.getArrivingGeoFence());
            } else {
                ModeData modeData3 = this.mModeToModeDataMap.get(OperationMode.AUTO);
                Objects.requireNonNull(modeData3);
                ModeData modeData4 = modeData3;
                if (modeData3.tempSettings.equals("RELATIVE")) {
                    ModeData modeData5 = this.mModeToModeDataMap.get(OperationMode.AUTO);
                    Objects.requireNonNull(modeData5);
                    ModeData modeData6 = modeData5;
                    if (modeData5.visibilitySettings.equals("ABSOLUTE")) {
                        setTempForAutoModeRelativeAbsolute(geoFencePair.getArrivingGeoFence());
                    }
                }
                setTempForAutoMode(geoFencePair.getArrivingGeoFence());
            }
        } else {
            this.currentTempInCelsius = geoFencePair.getArrivingGeoFence().getModeTempSettings().temperature;
            String format = String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings(this.currentTempInCelsius))});
            this.mBinding.textViewTemprature.setText(String.format("%s", new Object[]{format}));
        }
        if (geoFencePair.getArrivingGeoFence().getModeTempSettings().powerMode.equalsIgnoreCase(DetailedIduModel.POWER_ON)) {
            this.mBinding.switchMainHome.setCheckedSilent(true);
            showSettingsUI();
        } else {
            this.mBinding.switchMainHome.setCheckedSilent(false);
            hideSettingsUI();
        }
        ModeData modeData7 = this.mModeToModeDataMap.get(OperationMode.valueOf(geoFencePair.getArrivingGeoFence().getModeTempSettings().mode));
        Objects.requireNonNull(modeData7);
        ModeData modeData8 = modeData7;
        this.humidityGlobal = modeData7.defaultHumidity;
        TextView textView = this.mBinding.textViewHumidityPercentageIduControl;
        textView.setText(this.humidityGlobal + "");
        setDefaullModeUi(geoFencePair.getArrivingGeoFence().getModeTempSettings().mode);
    }

    /* access modifiers changed from: private */
    public void greyOutTheLayouts() {
        this.mBinding.switchMainHome.setCheckedSilent(true);
        this.mBinding.selectModeButton.setVisibility(0);
        this.mBinding.layoutModeRoomDeviceControl.setVisibility(4);
        this.mBinding.imageButtonDecreaseTemprature.setEnabled(false);
        this.mBinding.imageButtonIncreaseTemprature.setEnabled(false);
        this.mBinding.textViewSetTemp.setEnabled(false);
        this.mBinding.textViewHumidityTitleRoomdeviceControl.setVisibility(4);
        this.mBinding.imageButtonDecreaseTemprature.setColorFilter(this.context.getResources().getColor(R.color.grey_medium));
        this.mBinding.imageButtonIncreaseTemprature.setColorFilter(this.context.getResources().getColor(R.color.grey_medium));
        this.mBinding.textViewTempratureUnit.setTextColor(this.context.getResources().getColor(R.color.grey_medium));
        this.mBinding.textViewTemprature.setTextColor(this.context.getResources().getColor(R.color.grey_medium));
        this.mBinding.textViewSetTemp.setTextColor(this.context.getResources().getColor(R.color.grey_medium));
    }

    /* access modifiers changed from: private */
    public void showLayoutsModeSelected() {
        this.mBinding.selectModeButton.setVisibility(4);
        this.mBinding.layoutModeRoomDeviceControl.setVisibility(0);
        this.mBinding.imageButtonDecreaseTemprature.setEnabled(true);
        this.mBinding.imageButtonIncreaseTemprature.setEnabled(true);
        this.mBinding.textViewSetTemp.setEnabled(true);
        this.mBinding.imageButtonDecreaseTemprature.setColorFilter(this.context.getResources().getColor(R.color.color_text_dark));
        this.mBinding.imageButtonIncreaseTemprature.setColorFilter(this.context.getResources().getColor(R.color.color_text_dark));
        this.mBinding.textViewTempratureUnit.setTextColor(this.context.getResources().getColor(R.color.color_text_dark));
        this.mBinding.textViewTemprature.setTextColor(this.context.getResources().getColor(R.color.color_text_dark));
        this.mBinding.textViewSetTemp.setTextColor(this.context.getResources().getColor(R.color.color_text_dark));
    }

    /* access modifiers changed from: package-private */
    public void setDefaultForLeaving(GeoFencePair geoFencePair) {
        if (geoFencePair.getLeavingGeoFence().getModeTempSettings().mode == null) {
            this.isModeSelected = false;
            greyOutTheLayouts();
            return;
        }
        this.currentOperationMode = geoFencePair.getLeavingGeoFence().getModeTempSettings().mode;
        this.isModeSelected = true;
        this.mBinding.selectModeButton.setVisibility(4);
        this.mBinding.layoutModeRoomDeviceControl.setVisibility(0);
        if (OperationMode.valueOf(geoFencePair.getLeavingGeoFence().getModeTempSettings().mode) == OperationMode.AUTO) {
            ModeData modeData = this.mModeToModeDataMap.get(OperationMode.AUTO);
            Objects.requireNonNull(modeData);
            ModeData modeData2 = modeData;
            if (modeData.tempSettings.equals("ABSOLUTE")) {
                setTempForAutoModeAbsolute(geoFencePair.getLeavingGeoFence());
            } else {
                ModeData modeData3 = this.mModeToModeDataMap.get(OperationMode.AUTO);
                Objects.requireNonNull(modeData3);
                ModeData modeData4 = modeData3;
                if (modeData3.tempSettings.equals("RELATIVE")) {
                    ModeData modeData5 = this.mModeToModeDataMap.get(OperationMode.AUTO);
                    Objects.requireNonNull(modeData5);
                    ModeData modeData6 = modeData5;
                    if (modeData5.visibilitySettings.equals("ABSOLUTE")) {
                        setTempForAutoModeRelativeAbsolute(geoFencePair.getLeavingGeoFence());
                    }
                }
                setTempForAutoMode(geoFencePair.getLeavingGeoFence());
            }
        } else {
            this.currentTempInCelsius = geoFencePair.getLeavingGeoFence().getModeTempSettings().temperature;
            String format = String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings(this.currentTempInCelsius))});
            TextView textView = this.mBinding.textViewTemprature;
            textView.setText(format + "");
        }
        if (geoFencePair.getLeavingGeoFence().getModeTempSettings().powerMode.equalsIgnoreCase(DetailedIduModel.POWER_ON)) {
            this.mBinding.switchMainHome.setCheckedSilent(true);
            showSettingsUI();
        } else {
            this.mBinding.switchMainHome.setCheckedSilent(false);
            hideSettingsUI();
        }
        ModeData modeData7 = this.mModeToModeDataMap.get(OperationMode.valueOf(geoFencePair.getLeavingGeoFence().getModeTempSettings().mode));
        Objects.requireNonNull(modeData7);
        ModeData modeData8 = modeData7;
        this.humidityGlobal = modeData7.defaultHumidity;
        TextView textView2 = this.mBinding.textViewHumidityPercentageIduControl;
        textView2.setText(this.humidityGlobal + "");
        setDefaullModeUi(geoFencePair.getLeavingGeoFence().getModeTempSettings().mode);
    }

    private void setTempForAutoMode(GeoFenceData geoFenceData) {
        double d = geoFenceData.getModeTempSettings().relativeTemperature;
        this.currentTempInCelsius = d;
        String format = String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettingsSmartFence(d))});
        int i = (d > C1030Utils.DOUBLE_EPSILON ? 1 : (d == C1030Utils.DOUBLE_EPSILON ? 0 : -1));
        if (i > 0) {
            TextView textView = this.mBinding.textViewTemprature;
            textView.setText(Marker.ANY_NON_NULL_MARKER + format);
        } else if (i == 0) {
            TextView textView2 = this.mBinding.textViewTemprature;
            textView2.setText("±" + format);
        } else {
            TextView textView3 = this.mBinding.textViewTemprature;
            textView3.setText(format + "");
        }
    }

    private void setTempForAutoModeRelativeAbsolute(GeoFenceData geoFenceData) {
        double d = geoFenceData.getModeTempSettings().relativeTemperature;
        this.currentTempInCelsius = d;
        ModeData modeData = this.mModeToModeDataMap.get(OperationMode.AUTO);
        Objects.requireNonNull(modeData);
        ModeData modeData2 = modeData;
        double d2 = d + modeData.referenceTemp;
        this.mBinding.textViewTemprature.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings(d2))}));
    }

    private void setTempForAutoModeAbsolute(GeoFenceData geoFenceData) {
        double d = geoFenceData.getModeTempSettings().temperature;
        this.currentTempInCelsius = d;
        this.mBinding.textViewTemprature.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings(d))}));
    }

    private void setDefaullModeUi(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 67979:
                if (str.equals("DRY")) {
                    c = 0;
                    break;
                }
                break;
            case 69363:
                if (str.equals("FAN")) {
                    c = 1;
                    break;
                }
                break;
            case 2020783:
                if (str.equals("AUTO")) {
                    c = 2;
                    break;
                }
                break;
            case 650679677:
                if (str.equals("DRY_COOL")) {
                    c = 3;
                    break;
                }
                break;
            case 1513770962:
                if (str.equals("HEATING")) {
                    c = 4;
                    break;
                }
                break;
            case 1670202329:
                if (str.equals("COOLING")) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                setDry();
                dimissAlertDialog();
                return;
            case 1:
                setFan();
                dimissAlertDialog();
                return;
            case 2:
                setAuto();
                dimissAlertDialog();
                return;
            case 3:
                setDryCool();
                dimissAlertDialog();
                return;
            case 4:
                setHeating();
                dimissAlertDialog();
                return;
            case 5:
                setCooling();
                dimissAlertDialog();
                return;
            default:
                setTemperatureUI();
                return;
        }
    }

    /* access modifiers changed from: private */
    public void changeTemp(String str, boolean z) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 67979:
                if (str.equals("DRY")) {
                    c = 0;
                    break;
                }
                break;
            case 69363:
                if (str.equals("FAN")) {
                    c = 1;
                    break;
                }
                break;
            case 2020783:
                if (str.equals("AUTO")) {
                    c = 2;
                    break;
                }
                break;
            case 650679677:
                if (str.equals("DRY_COOL")) {
                    c = 3;
                    break;
                }
                break;
            case 1513770962:
                if (str.equals("HEATING")) {
                    c = 4;
                    break;
                }
                break;
            case 1670202329:
                if (str.equals("COOLING")) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                callIncreseDecreseTemp(z, OperationMode.DRY);
                return;
            case 1:
                callIncreseDecreseTemp(z, OperationMode.FAN);
                return;
            case 2:
                callIncreseDecreseTemp(z, OperationMode.AUTO);
                return;
            case 3:
                callIncreseDecreseTemp(z, OperationMode.DRY_COOL);
                return;
            case 4:
                callIncreseDecreseTemp(z, OperationMode.HEATING);
                return;
            case 5:
                callIncreseDecreseTemp(z, OperationMode.COOLING);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void changeHumidity(String str, boolean z) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 67979:
                if (str.equals("DRY")) {
                    c = 0;
                    break;
                }
                break;
            case 69363:
                if (str.equals("FAN")) {
                    c = 1;
                    break;
                }
                break;
            case 2020783:
                if (str.equals("AUTO")) {
                    c = 2;
                    break;
                }
                break;
            case 650679677:
                if (str.equals("DRY_COOL")) {
                    c = 3;
                    break;
                }
                break;
            case 1513770962:
                if (str.equals("HEATING")) {
                    c = 4;
                    break;
                }
                break;
            case 1670202329:
                if (str.equals("COOLING")) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                callIncreaseDecreaseHumidity(z, OperationMode.DRY);
                return;
            case 1:
                callIncreaseDecreaseHumidity(z, OperationMode.FAN);
                return;
            case 2:
                callIncreaseDecreaseHumidity(z, OperationMode.AUTO);
                return;
            case 3:
                callIncreaseDecreaseHumidity(z, OperationMode.DRY_COOL);
                return;
            case 4:
                callIncreaseDecreaseHumidity(z, OperationMode.HEATING);
                return;
            case 5:
                callIncreaseDecreaseHumidity(z, OperationMode.COOLING);
                return;
            default:
                return;
        }
    }

    private void callIncreseDecreseTemp(boolean z, OperationMode operationMode) {
        if (operationMode.equals(OperationMode.AUTO)) {
            if (z) {
                ModeData modeData = this.mModeToModeDataMap.get(OperationMode.AUTO);
                Objects.requireNonNull(modeData);
                ModeData modeData2 = modeData;
                if (modeData.tempSettings.equals("ABSOLUTE")) {
                    ModeData modeData3 = this.mModeToModeDataMap.get(operationMode);
                    Objects.requireNonNull(modeData3);
                    ModeData modeData4 = modeData3;
                    float f = modeData3.pitchValue;
                    ModeData modeData5 = this.mModeToModeDataMap.get(operationMode);
                    Objects.requireNonNull(modeData5);
                    ModeData modeData6 = modeData5;
                    increaseTemperature(f, (double) modeData5.maxTemp.longValue());
                    return;
                }
                ModeData modeData7 = this.mModeToModeDataMap.get(OperationMode.AUTO);
                Objects.requireNonNull(modeData7);
                ModeData modeData8 = modeData7;
                if (modeData7.tempSettings.equals("RELATIVE")) {
                    ModeData modeData9 = this.mModeToModeDataMap.get(OperationMode.AUTO);
                    Objects.requireNonNull(modeData9);
                    ModeData modeData10 = modeData9;
                    if (modeData9.visibilitySettings.equals("ABSOLUTE")) {
                        ModeData modeData11 = this.mModeToModeDataMap.get(operationMode);
                        Objects.requireNonNull(modeData11);
                        ModeData modeData12 = modeData11;
                        increaseTemperatureForAutoRelativeAbsolute(modeData11.pitchValue, C1030Utils.DOUBLE_EPSILON);
                        return;
                    }
                }
                ModeData modeData13 = this.mModeToModeDataMap.get(operationMode);
                Objects.requireNonNull(modeData13);
                ModeData modeData14 = modeData13;
                float f2 = modeData13.pitchValue;
                ModeData modeData15 = this.mModeToModeDataMap.get(operationMode);
                Objects.requireNonNull(modeData15);
                ModeData modeData16 = modeData15;
                increaseTemperatureForAuto(f2, modeData15.referenceTemp);
                return;
            }
            ModeData modeData17 = this.mModeToModeDataMap.get(OperationMode.AUTO);
            Objects.requireNonNull(modeData17);
            ModeData modeData18 = modeData17;
            if (modeData17.tempSettings.equals("ABSOLUTE")) {
                ModeData modeData19 = this.mModeToModeDataMap.get(operationMode);
                Objects.requireNonNull(modeData19);
                ModeData modeData20 = modeData19;
                float f3 = modeData19.pitchValue;
                ModeData modeData21 = this.mModeToModeDataMap.get(operationMode);
                Objects.requireNonNull(modeData21);
                ModeData modeData22 = modeData21;
                decreaseTemperature(f3, (double) modeData21.minTemp.longValue());
                return;
            }
            ModeData modeData23 = this.mModeToModeDataMap.get(OperationMode.AUTO);
            Objects.requireNonNull(modeData23);
            ModeData modeData24 = modeData23;
            if (modeData23.tempSettings.equals("RELATIVE")) {
                ModeData modeData25 = this.mModeToModeDataMap.get(OperationMode.AUTO);
                Objects.requireNonNull(modeData25);
                ModeData modeData26 = modeData25;
                if (modeData25.visibilitySettings.equals("ABSOLUTE")) {
                    ModeData modeData27 = this.mModeToModeDataMap.get(operationMode);
                    Objects.requireNonNull(modeData27);
                    ModeData modeData28 = modeData27;
                    decreaseTemperatureForAutoRelativeAbsolute(modeData27.pitchValue, C1030Utils.DOUBLE_EPSILON);
                    return;
                }
            }
            ModeData modeData29 = this.mModeToModeDataMap.get(operationMode);
            Objects.requireNonNull(modeData29);
            ModeData modeData30 = modeData29;
            float f4 = modeData29.pitchValue;
            ModeData modeData31 = this.mModeToModeDataMap.get(operationMode);
            Objects.requireNonNull(modeData31);
            ModeData modeData32 = modeData31;
            decreaseTemperatureForAuto(f4, modeData31.referenceTemp);
        } else if (z) {
            ModeData modeData33 = this.mModeToModeDataMap.get(operationMode);
            Objects.requireNonNull(modeData33);
            ModeData modeData34 = modeData33;
            float f5 = modeData33.pitchValue;
            ModeData modeData35 = this.mModeToModeDataMap.get(operationMode);
            Objects.requireNonNull(modeData35);
            ModeData modeData36 = modeData35;
            increaseTemperature(f5, (double) modeData35.maxTemp.longValue());
        } else {
            ModeData modeData37 = this.mModeToModeDataMap.get(operationMode);
            Objects.requireNonNull(modeData37);
            ModeData modeData38 = modeData37;
            float f6 = modeData37.pitchValue;
            ModeData modeData39 = this.mModeToModeDataMap.get(operationMode);
            Objects.requireNonNull(modeData39);
            ModeData modeData40 = modeData39;
            decreaseTemperature(f6, (double) modeData39.minTemp.longValue());
        }
    }

    private void callIncreaseDecreaseHumidity(boolean z, OperationMode operationMode) {
        if (z) {
            ModeData modeData = this.mModeToModeDataMap.get(operationMode);
            Objects.requireNonNull(modeData);
            ModeData modeData2 = modeData;
            long j = modeData.pitchValueHumidity;
            ModeData modeData3 = this.mModeToModeDataMap.get(operationMode);
            Objects.requireNonNull(modeData3);
            ModeData modeData4 = modeData3;
            increaseHumidity(j, modeData3.maxHumidity);
            return;
        }
        ModeData modeData5 = this.mModeToModeDataMap.get(operationMode);
        Objects.requireNonNull(modeData5);
        ModeData modeData6 = modeData5;
        long j2 = modeData5.pitchValueHumidity;
        ModeData modeData7 = this.mModeToModeDataMap.get(operationMode);
        Objects.requireNonNull(modeData7);
        ModeData modeData8 = modeData7;
        decreaseHumidity(j2, modeData7.minHumidity);
    }

    public class SettingsModel {
        public double defaultHumidity = C1030Utils.DOUBLE_EPSILON;
        public String mode;
        public String onOffStatus;
        public double relativeTemp;
        public boolean switchState;
        public double temp;
        public String tempType = "RELATIVE";

        public SettingsModel() {
        }
    }

    /* renamed from: com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceSetModeTempDilog$12 */
    static /* synthetic */ class C193812 {
        static final /* synthetic */ int[] $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
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
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.COOLING     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.HEATING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.DRY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.FAN     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x003e }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.AUTO     // Catch:{ NoSuchFieldError -> 0x003e }
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
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.smartFence.view.SmartFenceSetModeTempDilog.C193812.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public void setTempAfterSelectingMode(OperationMode operationMode) {
        ModeData modeData = this.mModeToModeDataMap.get(operationMode);
        Objects.requireNonNull(modeData);
        ModeData modeData2 = modeData;
        this.currentTempInCelsius = modeData.defaultTemp.doubleValue();
        this.mBinding.textViewTemprature.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings(this.currentTempInCelsius))}));
    }

    /* access modifiers changed from: private */
    public void selectedMode(boolean z) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setCancelable(false).setView(R.layout.select_mode_dialog);
        AlertDialog alertDialog = this.mModeSelectDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.mModeSelectDialog.dismiss();
        }
        AlertDialog create = builder.create();
        this.mModeSelectDialog = create;
        create.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
            }
        });
        this.mModeSelectDialog.show();
        this.mModeSelectDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
            }
        });
        Window window = this.mModeSelectDialog.getWindow();
        Objects.requireNonNull(window);
        Window window2 = window;
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = 0.2f;
        this.mModeSelectDialog.getWindow().setAttributes(attributes);
        this.mModeSelectDialog.getWindow().addFlags(2);
        View decorView = this.mModeSelectDialog.getWindow().getDecorView();
        ImageButton imageButton = (ImageButton) decorView.findViewById(R.id.image_button_clear);
        if (z) {
            imageButton.setVisibility(4);
        } else {
            imageButton.setVisibility(0);
        }
        imageButton.setOnClickListener(new SmartFenceSetModeTempDilog$$ExternalSyntheticLambda2(this));
        RecyclerView recyclerView = (RecyclerView) decorView.findViewById(R.id.recycler_view_select_mode);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context));
        ViewUtils.convertDpToPixel(60.0f, this.context);
        recyclerView.setAdapter(this.selectModeRecyclerViewAdapter);
    }

    /* renamed from: lambda$selectedMode$3$com-jch-racWiFi-iduManagement-smartFence-view-SmartFenceSetModeTempDilog */
    public /* synthetic */ void mo30381x9dd55689(View view) {
        this.mModeSelectDialog.dismiss();
    }

    /* access modifiers changed from: private */
    public void dimissAlertDialog() {
        AlertDialog alertDialog = this.mModeSelectDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.mModeSelectDialog.dismiss();
        }
    }

    public void setHumidityUI() {
        this.humiditySelected = true;
        this.mBinding.textViewHumidityPercentageIduControl.setVisibility(0);
        this.mBinding.textViewPercent.setVisibility(0);
        this.mBinding.textViewTemprature.setVisibility(4);
        this.mBinding.textViewTempratureUnit.setVisibility(4);
        this.mBinding.imageButtonIncreaseTemprature.setVisibility(4);
        this.mBinding.imageButtonDecreaseTemprature.setVisibility(4);
        this.mBinding.imageButtonIncreaseHumidity.setVisibility(0);
        this.mBinding.imageButtonDecreaseHumidity.setVisibility(0);
        this.mBinding.textViewHumidityTitleRoomdeviceControl.setTextColor(getContext().getResources().getColor(R.color.color_text_dark));
        this.mBinding.textViewSetTemp.setTextColor(getContext().getResources().getColor(R.color.grey_medium));
        this.mBinding.textViewSetTemp.setVisibility(0);
    }

    public void setTemperatureUI() {
        this.humiditySelected = false;
        this.mBinding.textViewHumidityPercentageIduControl.setVisibility(4);
        this.mBinding.textViewPercent.setVisibility(4);
        this.mBinding.textViewTemprature.setVisibility(0);
        this.mBinding.textViewTempratureUnit.setVisibility(0);
        this.mBinding.imageButtonIncreaseTemprature.setVisibility(0);
        this.mBinding.imageButtonDecreaseTemprature.setVisibility(0);
        this.mBinding.textViewSetTemp.setVisibility(0);
        this.mBinding.textViewSetTemp.setTextColor(getContext().getResources().getColor(R.color.color_text_dark));
        this.mBinding.textViewHumidityTitleRoomdeviceControl.setTextColor(getContext().getResources().getColor(R.color.grey_medium));
        this.mBinding.imageButtonIncreaseHumidity.setVisibility(4);
        this.mBinding.imageButtonDecreaseHumidity.setVisibility(4);
    }

    public void setFanModeUI() {
        this.mBinding.textViewHumidityPercentageIduControl.setVisibility(4);
        this.mBinding.textViewPercent.setVisibility(4);
        this.mBinding.textViewTemprature.setVisibility(4);
        this.mBinding.textViewTempratureUnit.setVisibility(4);
        this.mBinding.imageButtonIncreaseTemprature.setVisibility(4);
        this.mBinding.imageButtonDecreaseTemprature.setVisibility(4);
        this.mBinding.imageButtonIncreaseHumidity.setVisibility(4);
        this.mBinding.imageButtonDecreaseHumidity.setVisibility(4);
        this.mBinding.textViewSetTemp.setVisibility(4);
    }

    public void setAutoModeUI() {
        this.mBinding.textViewHumidityPercentageIduControl.setVisibility(4);
        this.mBinding.textViewPercent.setVisibility(4);
        this.mBinding.textViewTemprature.setVisibility(4);
        this.mBinding.textViewTempratureUnit.setVisibility(4);
        this.mBinding.imageButtonIncreaseTemprature.setVisibility(4);
        this.mBinding.imageButtonDecreaseTemprature.setVisibility(4);
        this.mBinding.imageButtonIncreaseHumidity.setVisibility(4);
        this.mBinding.imageButtonDecreaseHumidity.setVisibility(4);
        this.mBinding.textViewSetTemp.setVisibility(4);
    }

    public void makeHumidityLabelVisible() {
        this.mBinding.textViewHumidityTitleRoomdeviceControl.setVisibility(0);
    }

    public void removeHumidityLabel() {
        this.mBinding.textViewHumidityTitleRoomdeviceControl.setVisibility(4);
    }

    class ModeData {
        long defaultHumidity = -1;
        Double defaultTemp = Double.valueOf(C1030Utils.DOUBLE_EPSILON);
        boolean isHumidityEnabled;
        long maxHumidity = -1;
        Long maxTemp = -1L;
        long minHumidity = -1;
        Long minTemp = -1L;
        float pitchValue = 0.0f;
        long pitchValueHumidity = -1;
        double referenceTemp;
        float relativeTemp = -1.0f;
        String tempSettings = null;
        String visibilitySettings = null;

        ModeData() {
        }
    }

    public void increaseTemperature(float f, double d) {
        double d2 = this.currentTempInCelsius;
        if (d2 < d) {
            d2 += (double) f;
        }
        this.currentTempInCelsius = d2;
        String format = String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings(d2))});
        this.mBinding.textViewTemprature.setText(String.format("%s", new Object[]{format}));
    }

    public void decreaseTemperature(float f, double d) {
        double d2 = this.currentTempInCelsius;
        if (d2 > d) {
            d2 -= (double) f;
        }
        this.currentTempInCelsius = d2;
        String format = String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings(d2))});
        this.mBinding.textViewTemprature.setText(String.format("%s", new Object[]{format}));
    }

    public void increaseTemperatureForAuto(float f, double d) {
        double d2 = this.currentTempInCelsius;
        if (d2 < d + 3.0d) {
            d2 += (double) f;
        }
        this.currentTempInCelsius = d2;
        String format = String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettingsSmartFence(d2))});
        int i = (d2 > C1030Utils.DOUBLE_EPSILON ? 1 : (d2 == C1030Utils.DOUBLE_EPSILON ? 0 : -1));
        if (i > 0) {
            this.mBinding.textViewTemprature.setText(String.format("+%s", new Object[]{format}));
        } else if (i == 0) {
            this.mBinding.textViewTemprature.setText(String.format("±%s", new Object[]{format}));
        } else {
            this.mBinding.textViewTemprature.setText(String.format("%s", new Object[]{format}));
        }
    }

    public void increaseTemperatureForAutoRelativeAbsolute(float f, double d) {
        double d2 = this.currentTempInCelsius;
        if (d2 < d + 3.0d) {
            d2 += (double) f;
        }
        ModeData modeData = this.mModeToModeDataMap.get(OperationMode.AUTO);
        Objects.requireNonNull(modeData);
        ModeData modeData2 = modeData;
        double d3 = modeData.referenceTemp;
        this.currentTempInCelsius = d2;
        double d4 = d2 + d3;
        this.mBinding.textViewTemprature.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings(d4))}));
    }

    public void decreaseTemperatureForAuto(float f, double d) {
        double d2 = this.currentTempInCelsius;
        if (d2 > d - 3.0d) {
            d2 -= (double) f;
        }
        this.currentTempInCelsius = d2;
        String format = String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettingsSmartFence(d2))});
        int i = (d2 > C1030Utils.DOUBLE_EPSILON ? 1 : (d2 == C1030Utils.DOUBLE_EPSILON ? 0 : -1));
        if (i > 0) {
            this.mBinding.textViewTemprature.setText(String.format("+%s", new Object[]{format}));
        } else if (i == 0) {
            this.mBinding.textViewTemprature.setText(String.format("±%s", new Object[]{format}));
        } else {
            this.mBinding.textViewTemprature.setText(String.format("%s", new Object[]{format}));
        }
    }

    public void decreaseTemperatureForAutoRelativeAbsolute(float f, double d) {
        double d2 = this.currentTempInCelsius;
        if (d2 > d - 3.0d) {
            d2 -= (double) f;
        }
        ModeData modeData = this.mModeToModeDataMap.get(OperationMode.AUTO);
        Objects.requireNonNull(modeData);
        ModeData modeData2 = modeData;
        double d3 = modeData.referenceTemp;
        this.currentTempInCelsius = d2;
        this.mBinding.textViewTemprature.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings(d2 + d3))}));
    }

    public void increaseHumidity(long j, long j2) {
        long parseLong = Long.parseLong(this.mBinding.textViewHumidityPercentageIduControl.getText().toString());
        if (parseLong < j2) {
            parseLong += j;
        }
        this.humidityGlobal = parseLong;
        TextView textView = this.mBinding.textViewHumidityPercentageIduControl;
        textView.setText(parseLong + "");
    }

    public void decreaseHumidity(long j, long j2) {
        long parseLong = Long.parseLong(this.mBinding.textViewHumidityPercentageIduControl.getText().toString());
        if (parseLong > j2) {
            parseLong -= j;
        }
        this.humidityGlobal = parseLong;
        TextView textView = this.mBinding.textViewHumidityPercentageIduControl;
        textView.setText(parseLong + "");
    }

    public void setCooling() {
        this.mBinding.textViewSelectedModeHome.setText(R.string.common_lbl_cooling);
        this.mBinding.imageViewSelectedModeHome.setImageResource(R.drawable.ic_cooling_svg);
        this.mBinding.layoutModeRoomDeviceControl.setBackgroundColor(this.context.getResources().getColor(R.color.color_cooling_global));
        this.mBinding.imageViewArrowDownMode.setColorFilter(ContextCompat.getColor(this.context, R.color.white), PorterDuff.Mode.SRC_IN);
        this.mBinding.textViewSelectedModeHome.setTextColor(this.context.getResources().getColor(R.color.white));
        setTemperatureUI();
        ModeData modeData = this.mModeToModeDataMap.get(OperationMode.COOLING);
        Objects.requireNonNull(modeData);
        ModeData modeData2 = modeData;
        if (modeData.isHumidityEnabled) {
            makeHumidityLabelVisible();
        } else {
            removeHumidityLabel();
        }
    }

    /* access modifiers changed from: private */
    public void setDryCool() {
        this.mBinding.textViewSelectedModeHome.setText(R.string.common_lbl_dryCool);
        this.mBinding.imageViewSelectedModeHome.setImageResource(R.drawable.ic_white_dry_cool_svg_new);
        this.mBinding.layoutModeRoomDeviceControl.setBackgroundColor(this.context.getResources().getColor(R.color.color_dry_cool_global));
        this.mBinding.imageViewArrowDownMode.setColorFilter(ContextCompat.getColor(this.context, R.color.white), PorterDuff.Mode.SRC_IN);
        this.mBinding.textViewSelectedModeHome.setTextColor(this.context.getResources().getColor(R.color.white));
        setTemperatureUI();
        ModeData modeData = this.mModeToModeDataMap.get(OperationMode.DRY_COOL);
        Objects.requireNonNull(modeData);
        ModeData modeData2 = modeData;
        if (modeData.isHumidityEnabled) {
            makeHumidityLabelVisible();
        } else {
            removeHumidityLabel();
        }
    }

    /* access modifiers changed from: private */
    public void setAuto() {
        this.mBinding.textViewSelectedModeHome.setText(R.string.common_lbl_auto);
        this.mBinding.imageViewSelectedModeHome.setImageResource(R.drawable.ic_grey_auto_mode_svg);
        this.mBinding.layoutModeRoomDeviceControl.setBackgroundColor(this.context.getResources().getColor(R.color.color_auto_global));
        this.mBinding.imageViewArrowDownMode.setColorFilter(ContextCompat.getColor(this.context, R.color.textview_color_vd_light), PorterDuff.Mode.SRC_IN);
        this.mBinding.textViewSelectedModeHome.setTextColor(this.context.getResources().getColor(R.color.textview_color_vd_light));
        setTemperatureUI();
        for (Map.Entry<String, List<RacModelWiseData.RacModeDetail>> key : this.racListCloudIDToModeMap.entrySet()) {
            if (!this.fragmentToActivityCallback.getRacModelWiseDataBasedOnRacTypeId((String) key.getKey()).getRacModeDetails().getRacModeDetailBasedOnMode(OperationMode.AUTO).getVisibleSettings().getTemperature()) {
                setAutoModeUI();
            }
        }
        ModeData modeData = this.mModeToModeDataMap.get(OperationMode.AUTO);
        Objects.requireNonNull(modeData);
        ModeData modeData2 = modeData;
        if (modeData.isHumidityEnabled) {
            makeHumidityLabelVisible();
        } else {
            removeHumidityLabel();
        }
    }

    /* access modifiers changed from: private */
    public void setFan() {
        this.mBinding.textViewSelectedModeHome.setText(R.string.common_lbl_fan);
        this.mBinding.imageViewSelectedModeHome.setImageResource(R.drawable.ic_white_fan_mode_svg);
        this.mBinding.layoutModeRoomDeviceControl.setBackgroundColor(this.context.getResources().getColor(R.color.color_fan_global));
        this.mBinding.imageViewArrowDownMode.setColorFilter(ContextCompat.getColor(this.context, R.color.white), PorterDuff.Mode.SRC_IN);
        this.mBinding.textViewSelectedModeHome.setTextColor(this.context.getResources().getColor(R.color.white));
        setFanModeUI();
        ModeData modeData = this.mModeToModeDataMap.get(OperationMode.FAN);
        Objects.requireNonNull(modeData);
        ModeData modeData2 = modeData;
        if (modeData.isHumidityEnabled) {
            removeHumidityLabel();
        } else {
            removeHumidityLabel();
        }
    }

    /* access modifiers changed from: private */
    public void setDry() {
        this.mBinding.textViewSelectedModeHome.setText(R.string.common_lbl_dry);
        this.mBinding.imageViewSelectedModeHome.setImageResource(R.drawable.ic_white_dehumidify_mode_svg);
        this.mBinding.layoutModeRoomDeviceControl.setBackgroundColor(this.context.getResources().getColor(R.color.color_dehumidify_global));
        this.mBinding.imageViewArrowDownMode.setColorFilter(ContextCompat.getColor(this.context, R.color.white), PorterDuff.Mode.SRC_IN);
        this.mBinding.textViewSelectedModeHome.setTextColor(this.context.getResources().getColor(R.color.white));
        setTemperatureUI();
        ModeData modeData = this.mModeToModeDataMap.get(OperationMode.DRY);
        Objects.requireNonNull(modeData);
        ModeData modeData2 = modeData;
        if (modeData.isHumidityEnabled) {
            makeHumidityLabelVisible();
        } else {
            removeHumidityLabel();
        }
    }

    /* access modifiers changed from: private */
    public void setHeating() {
        this.mBinding.textViewSelectedModeHome.setText(R.string.common_lbl_heating);
        this.mBinding.imageViewSelectedModeHome.setImageResource(R.drawable.ic_white_heat_mode_svg);
        this.mBinding.layoutModeRoomDeviceControl.setBackgroundColor(this.context.getResources().getColor(R.color.color_heating_global));
        this.mBinding.imageViewArrowDownMode.setColorFilter(ContextCompat.getColor(this.context, R.color.white), PorterDuff.Mode.SRC_IN);
        this.mBinding.textViewSelectedModeHome.setTextColor(this.context.getResources().getColor(R.color.white));
        setTemperatureUI();
        ModeData modeData = this.mModeToModeDataMap.get(OperationMode.HEATING);
        Objects.requireNonNull(modeData);
        ModeData modeData2 = modeData;
        if (modeData.isHumidityEnabled) {
            makeHumidityLabelVisible();
        } else {
            removeHumidityLabel();
        }
    }
}
