package com.jch.racWiFi.customViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.FragmentToActivityCallback;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.RacModelWiseData;
import com.jch.racWiFi.iduManagement.model.WeeklyTimerModelV2;
import com.jch.racWiFi.settings.model.TemperatureUnit;
import java.util.ArrayList;
import java.util.Locale;
import org.slf4j.Marker;

public class DynamicScheduledTimerList {
    private Context context;
    private ArrayList<WeeklyTimerModelV2.WeeklyTimerFactoryData> dataArrayList;
    /* access modifiers changed from: private */
    public DynamicViewClickListener dynamicViewClickListener;
    private LayoutInflater inflater;
    private RacModelWiseData mRacModelWiseData;
    private DetailedIduModel orgDetailedIduModel;
    private LinearLayout parentLayout;
    private RacModelWiseData.RacModeDetail racModeDetail;

    public interface DynamicViewClickListener {
        void onDeleteItemClickListener(View view, int i);

        void onItemClickListener(View view, int i);
    }

    public DynamicScheduledTimerList(LinearLayout linearLayout, Context context2) {
        this.parentLayout = linearLayout;
        linearLayout.removeAllViews();
        this.context = context2;
        this.inflater = (LayoutInflater) context2.getSystemService("layout_inflater");
    }

    public void initArrayList(ArrayList<WeeklyTimerModelV2.WeeklyTimerFactoryData> arrayList, DynamicViewClickListener dynamicViewClickListener2) {
        this.dataArrayList = arrayList;
        this.dynamicViewClickListener = dynamicViewClickListener2;
    }

    public void setRacModelWise(FragmentToActivityCallback fragmentToActivityCallback, DetailedIduModel detailedIduModel) {
        this.orgDetailedIduModel = detailedIduModel;
        this.mRacModelWiseData = fragmentToActivityCallback.getRacModelWiseConfigurationList().getRacModelWiseDataBasedOnRacTypeId(detailedIduModel.cloudId);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00ed  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0132  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0136  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x017b  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x021b  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x02ec  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void showList() {
        /*
            r17 = this;
            r9 = r17
            r0 = 0
            r10 = 0
        L_0x0004:
            java.util.ArrayList<com.jch.racWiFi.iduManagement.model.WeeklyTimerModelV2$WeeklyTimerFactoryData> r0 = r9.dataArrayList
            int r0 = r0.size()
            if (r10 >= r0) goto L_0x031c
            android.view.LayoutInflater r0 = r9.inflater
            r1 = 2131558719(0x7f0d013f, float:1.8742762E38)
            r2 = 0
            android.view.View r11 = r0.inflate(r1, r2)
            r0 = 2131363261(0x7f0a05bd, float:1.8346326E38)
            android.view.View r0 = r11.findViewById(r0)
            r12 = r0
            androidx.constraintlayout.widget.ConstraintLayout r12 = (androidx.constraintlayout.widget.ConstraintLayout) r12
            r0 = 2131362143(0x7f0a015f, float:1.8344058E38)
            android.view.View r0 = r11.findViewById(r0)
            r13 = r0
            com.jch.racWiFi.customViews.customWidgets.Button r13 = (com.jch.racWiFi.customViews.customWidgets.Button) r13
            r0 = 2131364571(0x7f0a0adb, float:1.8348983E38)
            android.view.View r0 = r11.findViewById(r0)
            r1 = r0
            com.jch.racWiFi.customViews.customWidgets.TextView r1 = (com.jch.racWiFi.customViews.customWidgets.TextView) r1
            r0 = 2131364171(0x7f0a094b, float:1.8348172E38)
            android.view.View r0 = r11.findViewById(r0)
            r2 = r0
            com.jch.racWiFi.customViews.customWidgets.TextView r2 = (com.jch.racWiFi.customViews.customWidgets.TextView) r2
            r0 = 2131364563(0x7f0a0ad3, float:1.8348967E38)
            android.view.View r0 = r11.findViewById(r0)
            r3 = r0
            com.jch.racWiFi.customViews.customWidgets.TextView r3 = (com.jch.racWiFi.customViews.customWidgets.TextView) r3
            r0 = 2131364164(0x7f0a0944, float:1.8348157E38)
            android.view.View r0 = r11.findViewById(r0)
            r4 = r0
            com.jch.racWiFi.customViews.customWidgets.TextView r4 = (com.jch.racWiFi.customViews.customWidgets.TextView) r4
            r0 = 2131364647(0x7f0a0b27, float:1.8349137E38)
            android.view.View r0 = r11.findViewById(r0)
            r7 = r0
            com.jch.racWiFi.customViews.customWidgets.TextView r7 = (com.jch.racWiFi.customViews.customWidgets.TextView) r7
            r0 = 2131362957(0x7f0a048d, float:1.834571E38)
            android.view.View r0 = r11.findViewById(r0)
            r6 = r0
            android.widget.ImageView r6 = (android.widget.ImageView) r6
            r0 = 2131362860(0x7f0a042c, float:1.8345513E38)
            android.view.View r0 = r11.findViewById(r0)
            r5 = r0
            android.widget.ImageView r5 = (android.widget.ImageView) r5
            java.lang.Integer r0 = java.lang.Integer.valueOf(r10)
            r12.setTag(r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r10)
            r13.setTag(r0)
            java.util.ArrayList<com.jch.racWiFi.iduManagement.model.WeeklyTimerModelV2$WeeklyTimerFactoryData> r0 = r9.dataArrayList
            java.lang.Object r0 = r0.get(r10)
            r8 = r0
            com.jch.racWiFi.iduManagement.model.WeeklyTimerModelV2$WeeklyTimerFactoryData r8 = (com.jch.racWiFi.iduManagement.model.WeeklyTimerModelV2.WeeklyTimerFactoryData) r8
            java.lang.String r0 = r8.startAt
            java.lang.String r14 = com.jch.racWiFi.Utils.DateAndTimeUtils.convert12HoursFormat(r0)
            java.lang.String r0 = r8.endAt
            java.lang.String r0 = com.jch.racWiFi.Utils.DateAndTimeUtils.convert12HoursFormat(r0)
            com.jch.racWiFi.Listeners.SingleLiveEvent r15 = com.jch.racWiFi.iduManagement.viewModel.WeeklyTimerViewModel.getWeeklyTimerUpdatedDataItems()     // Catch:{ Exception -> 0x00c8 }
            java.lang.Object r15 = r15.getValue()     // Catch:{ Exception -> 0x00c8 }
            com.jch.racWiFi.iduManagement.model.WeeklyTimerModelV2$WeeklyTimerFetchResponse r15 = (com.jch.racWiFi.iduManagement.model.WeeklyTimerModelV2.WeeklyTimerFetchResponse) r15     // Catch:{ Exception -> 0x00c8 }
            com.jch.racWiFi.Listeners.SingleLiveEvent r16 = com.jch.racWiFi.iduManagement.viewModel.WeeklyTimerViewModel.getWeeklyTimerUpdatedDataItems()     // Catch:{ Exception -> 0x00c8 }
            java.lang.Object r16 = r16.getValue()     // Catch:{ Exception -> 0x00c8 }
            if (r16 == 0) goto L_0x00c3
            r16 = r0
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ Exception -> 0x00c8 }
            r0.<init>()     // Catch:{ Exception -> 0x00c8 }
            java.lang.Object r15 = r15.response     // Catch:{ Exception -> 0x00c8 }
            com.jch.racWiFi.iduManagement.model.WeeklyTimerModelV2$WeeklyTimerResponseData[] r15 = (com.jch.racWiFi.iduManagement.model.WeeklyTimerModelV2.WeeklyTimerResponseData[]) r15     // Catch:{ Exception -> 0x00c8 }
            java.util.List r15 = java.util.Arrays.asList(r15)     // Catch:{ Exception -> 0x00c8 }
            r0.addAll(r15)     // Catch:{ Exception -> 0x00c8 }
            int r0 = r0.size()     // Catch:{ Exception -> 0x00c8 }
            r15 = 1
            if (r0 != r15) goto L_0x00c5
            java.lang.String r0 = "   --:--"
            goto L_0x00ce
        L_0x00c3:
            r16 = r0
        L_0x00c5:
            r0 = r16
            goto L_0x00ce
        L_0x00c8:
            r0 = move-exception
            r0.printStackTrace()
            java.lang.String r0 = "00.00"
        L_0x00ce:
            java.lang.String r15 = "12.00 AM"
            boolean r16 = r0.equals(r15)
            if (r16 == 0) goto L_0x00d7
            r0 = r15
        L_0x00d7:
            r1.setText(r14)
            r2.setText(r0)
            java.lang.String r0 = r8.day
            java.lang.String r1 = r8.startingDay
            boolean r0 = r0.equalsIgnoreCase(r1)
            java.lang.String r1 = ")"
            java.lang.String r2 = "("
            java.lang.String r14 = ""
            if (r0 == 0) goto L_0x00f3
            r3.setText(r14)
            r16 = r10
            goto L_0x0128
        L_0x00f3:
            java.lang.String r0 = r8.startingDay
            boolean r0 = com.jch.racWiFi.iduManagement.Weekday.contains(r0)
            if (r0 == 0) goto L_0x0123
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r2)
            android.content.Context r15 = r9.context
            r16 = r10
            java.lang.String r10 = r8.startingDay
            com.jch.racWiFi.iduManagement.Weekday r10 = com.jch.racWiFi.iduManagement.Weekday.valueOf(r10)
            int r10 = r10.getStringRes()
            java.lang.String r10 = r15.getString(r10)
            r0.append(r10)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r3.setText(r0)
            goto L_0x0128
        L_0x0123:
            r16 = r10
            r3.setText(r14)
        L_0x0128:
            java.lang.String r0 = r8.day
            java.lang.String r3 = r8.endingDay
            boolean r0 = r0.equalsIgnoreCase(r3)
            if (r0 == 0) goto L_0x0136
            r4.setText(r14)
            goto L_0x0167
        L_0x0136:
            java.lang.String r0 = r8.endingDay
            boolean r0 = com.jch.racWiFi.iduManagement.Weekday.contains(r0)
            if (r0 == 0) goto L_0x0164
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r2)
            android.content.Context r2 = r9.context
            java.lang.String r3 = r8.endingDay
            com.jch.racWiFi.iduManagement.Weekday r3 = com.jch.racWiFi.iduManagement.Weekday.valueOf(r3)
            int r3 = r3.getStringRes()
            java.lang.String r2 = r2.getString(r3)
            r0.append(r2)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r4.setText(r0)
            goto L_0x0167
        L_0x0164:
            r4.setText(r14)
        L_0x0167:
            java.lang.String r0 = r8.mode
            com.jch.racWiFi.iduManagement.model.OperationMode r0 = com.jch.racWiFi.iduManagement.model.OperationMode.valueOf(r0)
            com.jch.racWiFi.iduManagement.model.RacModelWiseData r1 = r9.mRacModelWiseData
            com.jch.racWiFi.iduManagement.model.RacModelWiseData$RacModeDetailList r1 = r1.getRacModeDetails()
            com.jch.racWiFi.iduManagement.model.RacModelWiseData$RacModeDetail r1 = r1.getRacModeDetailBasedOnMode(r0)
            r9.racModeDetail = r1
            if (r1 == 0) goto L_0x020b
            com.jch.racWiFi.iduManagement.model.RacModelWiseData$TemperatureSettingType r1 = r1.getTemperatureSettingType()
            if (r1 == 0) goto L_0x01d7
            com.jch.racWiFi.iduManagement.model.RacModelWiseData$TemperatureSettingType r2 = com.jch.racWiFi.iduManagement.model.RacModelWiseData.TemperatureSettingType.RELATIVE
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x01d7
            com.jch.racWiFi.iduManagement.model.RacModelWiseData$RacModeDetail r1 = r9.racModeDetail
            com.jch.racWiFi.iduManagement.model.RacModelWiseData$TemperatureSettingType r1 = r1.getVisibleTemperatureSettingType()
            double r2 = r8.temperature
            com.jch.racWiFi.iduManagement.model.RacModelWiseData$RacModeDetail r4 = r9.racModeDetail
            float r4 = r4.getReferenceTemperature()
            double r14 = (double) r4
            double r2 = r2 + r14
            float r2 = (float) r2
            if (r1 == 0) goto L_0x01b0
            com.jch.racWiFi.iduManagement.model.RacModelWiseData$TemperatureSettingType r3 = com.jch.racWiFi.iduManagement.model.RacModelWiseData.TemperatureSettingType.RELATIVE
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x01b0
            double r0 = r8.temperature
            float r0 = (float) r0
            java.lang.Float r0 = java.lang.Float.valueOf(r0)
            java.lang.String r0 = r9.getConvertedTemperatureAuto(r0)
            goto L_0x01ba
        L_0x01b0:
            java.lang.Float r1 = java.lang.Float.valueOf(r2)
            com.jch.racWiFi.iduManagement.model.RacModelWiseData r2 = r9.mRacModelWiseData
            java.lang.String r0 = com.jch.racWiFi.Utils.TemperatureValueFormatter.getConvertedTemperature((java.lang.Float) r1, (com.jch.racWiFi.iduManagement.model.RacModelWiseData) r2, (com.jch.racWiFi.iduManagement.model.OperationMode) r0)
        L_0x01ba:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            android.content.Context r0 = r9.context
            int r2 = com.jch.racWiFi.settings.model.TemperatureUnit.getTemperatureUnitFromSettings()
            java.lang.String r0 = r0.getString(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r7.setText(r0)
            goto L_0x020b
        L_0x01d7:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r9.orgDetailedIduModel
            float r1 = r1.iduTemperature
            r2 = 2139095039(0x7f7fffff, float:3.4028235E38)
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 == 0) goto L_0x020b
            double r1 = r8.temperature
            float r1 = (float) r1
            java.lang.Float r1 = java.lang.Float.valueOf(r1)
            com.jch.racWiFi.iduManagement.model.RacModelWiseData r2 = r9.mRacModelWiseData
            java.lang.String r0 = com.jch.racWiFi.Utils.TemperatureValueFormatter.getConvertedTemperature((java.lang.Float) r1, (com.jch.racWiFi.iduManagement.model.RacModelWiseData) r2, (com.jch.racWiFi.iduManagement.model.OperationMode) r0)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            android.content.Context r0 = r9.context
            int r2 = com.jch.racWiFi.settings.model.TemperatureUnit.getTemperatureUnitFromSettings()
            java.lang.String r0 = r0.getString(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r7.setText(r0)
        L_0x020b:
            java.lang.String r0 = r8.power
            java.lang.String r1 = "ON"
            boolean r0 = r0.equals(r1)
            r1 = 2131951908(0x7f130124, float:1.9540244E38)
            r2 = 2131231412(0x7f0802b4, float:1.8078904E38)
            if (r0 == 0) goto L_0x02ec
            java.lang.String r0 = r8.mode
            com.jch.racWiFi.iduManagement.model.OperationMode r0 = com.jch.racWiFi.iduManagement.model.OperationMode.valueOf(r0)
            int[] r3 = com.jch.racWiFi.customViews.DynamicScheduledTimerList.C16863.$SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode
            int r0 = r0.ordinal()
            r0 = r3[r0]
            switch(r0) {
                case 1: goto L_0x02c5;
                case 2: goto L_0x02ae;
                case 3: goto L_0x0297;
                case 4: goto L_0x0280;
                case 5: goto L_0x0268;
                case 6: goto L_0x0240;
                case 7: goto L_0x022e;
                default: goto L_0x022c;
            }
        L_0x022c:
            goto L_0x02fc
        L_0x022e:
            r6.setImageResource(r2)
            android.content.Context r0 = r9.context
            android.content.res.Resources r0 = r0.getResources()
            java.lang.String r0 = r0.getString(r1)
            r7.setText(r0)
            goto L_0x02fc
        L_0x0240:
            r0 = 2131231409(0x7f0802b1, float:1.8078898E38)
            r6.setImageResource(r0)
            android.content.Context r0 = r9.context
            android.content.res.Resources r0 = r0.getResources()
            r1 = 2131099723(0x7f06004b, float:1.7811807E38)
            int r0 = r0.getColor(r1)
            r7.setTextColor(r0)
            android.content.Context r0 = r9.context
            android.content.res.Resources r0 = r0.getResources()
            r1 = 2131951875(0x7f130103, float:1.9540177E38)
            java.lang.String r0 = r0.getString(r1)
            r7.setText(r0)
            goto L_0x02fc
        L_0x0268:
            r0 = 2131231208(0x7f0801e8, float:1.807849E38)
            r6.setImageResource(r0)
            android.content.Context r0 = r9.context
            android.content.res.Resources r0 = r0.getResources()
            r1 = 2131099722(0x7f06004a, float:1.7811805E38)
            int r0 = r0.getColor(r1)
            r7.setTextColor(r0)
            goto L_0x02fc
        L_0x0280:
            r0 = 2131231408(0x7f0802b0, float:1.8078896E38)
            r6.setImageResource(r0)
            android.content.Context r0 = r9.context
            android.content.res.Resources r0 = r0.getResources()
            r1 = 2131099718(0x7f060046, float:1.7811797E38)
            int r0 = r0.getColor(r1)
            r7.setTextColor(r0)
            goto L_0x02fc
        L_0x0297:
            r0 = 2131231410(0x7f0802b2, float:1.80789E38)
            r6.setImageResource(r0)
            android.content.Context r0 = r9.context
            android.content.res.Resources r0 = r0.getResources()
            r1 = 2131099725(0x7f06004d, float:1.7811811E38)
            int r0 = r0.getColor(r1)
            r7.setTextColor(r0)
            goto L_0x02fc
        L_0x02ae:
            r0 = 2131231406(0x7f0802ae, float:1.8078892E38)
            r6.setImageResource(r0)
            android.content.Context r0 = r9.context
            android.content.res.Resources r0 = r0.getResources()
            r1 = 2131099716(0x7f060044, float:1.7811793E38)
            int r0 = r0.getColor(r1)
            r7.setTextColor(r0)
            goto L_0x02fc
        L_0x02c5:
            r0 = 2131231403(0x7f0802ab, float:1.8078886E38)
            r6.setImageResource(r0)
            android.content.Context r0 = r9.context
            android.content.res.Resources r0 = r0.getResources()
            r1 = 2131099714(0x7f060042, float:1.781179E38)
            int r0 = r0.getColor(r1)
            r7.setTextColor(r0)
            android.content.Context r0 = r9.context
            android.content.res.Resources r0 = r0.getResources()
            r1 = 2131951860(0x7f1300f4, float:1.9540146E38)
            java.lang.String r0 = r0.getString(r1)
            r7.setText(r0)
            goto L_0x02fc
        L_0x02ec:
            r6.setImageResource(r2)
            android.content.Context r0 = r9.context
            android.content.res.Resources r0 = r0.getResources()
            java.lang.String r0 = r0.getString(r1)
            r7.setText(r0)
        L_0x02fc:
            com.jch.racWiFi.customViews.DynamicScheduledTimerList$1 r0 = new com.jch.racWiFi.customViews.DynamicScheduledTimerList$1
            android.content.Context r3 = r9.context
            r1 = r0
            r2 = r17
            r4 = r13
            r8 = r12
            r1.<init>(r3, r4, r5, r6, r7, r8)
            r12.setOnTouchListener(r0)
            com.jch.racWiFi.customViews.DynamicScheduledTimerList$2 r0 = new com.jch.racWiFi.customViews.DynamicScheduledTimerList$2
            r0.<init>()
            r13.setOnClickListener(r0)
            android.widget.LinearLayout r0 = r9.parentLayout
            r0.addView(r11)
            int r10 = r16 + 1
            goto L_0x0004
        L_0x031c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.customViews.DynamicScheduledTimerList.showList():void");
    }

    /* renamed from: com.jch.racWiFi.customViews.DynamicScheduledTimerList$3 */
    static /* synthetic */ class C16863 {
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
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.DRY_COOL     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$jch$racWiFi$iduManagement$model$OperationMode     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.jch.racWiFi.iduManagement.model.OperationMode r1 = com.jch.racWiFi.iduManagement.model.OperationMode.FAN     // Catch:{ NoSuchFieldError -> 0x0049 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.customViews.DynamicScheduledTimerList.C16863.<clinit>():void");
        }
    }

    public void notifyDataSetChanged() {
        this.parentLayout.removeAllViews();
        showList();
    }

    private String getConvertedTemperatureAuto(Float f) {
        StringBuilder sb;
        String str;
        boolean z = true;
        String format = String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(TemperatureUnit.convertTemperaureUnitFromCelsiusAccordingToSettings((double) f.floatValue()))});
        Logger.m47e("TEMPERATURE_UNIT", "Not Converted : " + f + " Converted Temp : " + format);
        boolean z2 = f.floatValue() < 0.0f;
        if (f.floatValue() != 0.0f) {
            z = false;
        }
        if (z) {
            sb = new StringBuilder();
            str = "±";
        } else if (z2) {
            return format;
        } else {
            sb = new StringBuilder();
            str = Marker.ANY_NON_NULL_MARKER;
        }
        sb.append(str);
        sb.append(format);
        return sb.toString();
    }
}
