package com.jch.racWiFi.iduManagement.viewModel;

import androidx.lifecycle.C0534ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.jch.racWiFi.Listeners.SingleLiveEvent;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch.racWiFi.iduManagement.model.RacModelWiseData;

public class TemperatureChangeViewModel extends C0534ViewModel {
    private DetailedIduModel mDetailedIduModel;
    private RacModelWiseData mRacModelWiseData;
    private SingleLiveEvent<String> temperatureDecrementLiveEvent = new SingleLiveEvent<>();
    private SingleLiveEvent<String> temperatureIncrementLiveEvent = new SingleLiveEvent<>();

    public TemperatureChangeViewModel(DetailedIduModel detailedIduModel, RacModelWiseData racModelWiseData) {
        this.mDetailedIduModel = detailedIduModel;
        this.mRacModelWiseData = racModelWiseData;
    }

    public SingleLiveEvent<String> getTemperatureIncrementLiveEvent() {
        return this.temperatureIncrementLiveEvent;
    }

    public SingleLiveEvent<String> getTemperatureDecrementLiveEvent() {
        return this.temperatureDecrementLiveEvent;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x017a  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x018b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void increaseTemperature() {
        /*
            r16 = this;
            r0 = r16
            com.jch.racWiFi.iduManagement.model.RacModelWiseData r1 = r0.mRacModelWiseData
            if (r1 == 0) goto L_0x019e
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            com.jch.racWiFi.iduManagement.model.OperationMode r1 = r1.getOperationModeEnum()
            com.jch.racWiFi.iduManagement.model.RacModelWiseData r2 = r0.mRacModelWiseData
            com.jch.racWiFi.iduManagement.model.RacModelWiseData$RacModeDetailList r2 = r2.getRacModeDetails()
            com.jch.racWiFi.iduManagement.model.RacModelWiseData$RacModeDetail r1 = r2.getRacModeDetailBasedOnMode(r1)
            if (r1 == 0) goto L_0x019e
            com.jch.racWiFi.iduManagement.model.RacModelWiseData$TemperatureSettingType r2 = r1.getTemperatureSettingType()
            if (r2 == 0) goto L_0x019e
            int[] r3 = com.jch.racWiFi.iduManagement.viewModel.TemperatureChangeViewModel.C21921.f474xf7673082
            int r2 = r2.ordinal()
            r2 = r3[r2]
            r3 = 1
            r6 = 1098383360(0x41780000, float:15.5)
            r7 = 1104150528(0x41d00000, float:26.0)
            r8 = 1097334784(0x41680000, float:14.5)
            r10 = 1056964608(0x3f000000, float:0.5)
            r11 = 1103101952(0x41c00000, float:24.0)
            r12 = 1103888384(0x41cc0000, float:25.5)
            r13 = 1103364096(0x41c40000, float:24.5)
            r14 = 2139095039(0x7f7fffff, float:3.4028235E38)
            r15 = 1065353216(0x3f800000, float:1.0)
            if (r2 == r3) goto L_0x00d9
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            float r2 = r2.iduTemperature
            int r3 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1))
            if (r3 == 0) goto L_0x019e
            long r4 = r1.getMaxTemperature()
            float r4 = (float) r4
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 >= 0) goto L_0x019e
            float r1 = r1.getTemperatureSettingPitchType()
            com.jch.racWiFi.settings.model.TemperatureUnit r4 = com.jch.racWiFi.settings.model.TemperatureUnit.CURRENT
            com.jch.racWiFi.settings.model.TemperatureUnit r5 = com.jch.racWiFi.settings.model.TemperatureUnit.FAHRENHEIT
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x006f
            boolean r2 = com.jch.racWiFi.settings.model.TemperatureUnit.ConversionUtil.isTemperatureAtSpecialScenarioWhileIncrement(r2)
            if (r2 == 0) goto L_0x006f
            int r2 = (r1 > r15 ? 1 : (r1 == r15 ? 0 : -1))
            if (r2 != 0) goto L_0x0068
            r9 = 1073741824(0x40000000, float:2.0)
            goto L_0x0070
        L_0x0068:
            int r2 = (r1 > r10 ? 1 : (r1 == r10 ? 0 : -1))
            if (r2 != 0) goto L_0x006f
            r9 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0070
        L_0x006f:
            r9 = r1
        L_0x0070:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            float r2 = r1.iduTemperature
            float r2 = r2 + r9
            r1.iduTemperature = r2
            com.jch.racWiFi.settings.model.TemperatureUnit r1 = com.jch.racWiFi.settings.model.TemperatureUnit.CURRENT
            com.jch.racWiFi.settings.model.TemperatureUnit r2 = com.jch.racWiFi.settings.model.TemperatureUnit.FAHRENHEIT
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x00c0
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            float r1 = r1.iduTemperature
            int r1 = java.lang.Float.compare(r1, r13)
            if (r1 != 0) goto L_0x0090
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            r1.iduTemperature = r11
            goto L_0x00c0
        L_0x0090:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            float r1 = r1.iduTemperature
            int r1 = java.lang.Float.compare(r1, r12)
            if (r1 != 0) goto L_0x009f
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            r1.iduTemperature = r7
            goto L_0x00c0
        L_0x009f:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            float r1 = r1.iduTemperature
            int r1 = java.lang.Float.compare(r1, r8)
            if (r1 != 0) goto L_0x00b0
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            r2 = 1096810496(0x41600000, float:14.0)
            r1.iduTemperature = r2
            goto L_0x00c0
        L_0x00b0:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            float r1 = r1.iduTemperature
            int r1 = java.lang.Float.compare(r1, r6)
            if (r1 != 0) goto L_0x00c0
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            r2 = 1098907648(0x41800000, float:16.0)
            r1.iduTemperature = r2
        L_0x00c0:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            float r1 = r1.iduTemperature
            java.lang.Float r1 = java.lang.Float.valueOf(r1)
            com.jch.racWiFi.iduManagement.model.RacModelWiseData r2 = r0.mRacModelWiseData
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r3 = r0.mDetailedIduModel
            java.lang.String r1 = com.jch.racWiFi.Utils.TemperatureValueFormatter.getConvertedTemperature((java.lang.Float) r1, (com.jch.racWiFi.iduManagement.model.RacModelWiseData) r2, (com.jch.racWiFi.iduManagement.model.DetailedIduModel) r3)
            com.jch.racWiFi.Listeners.SingleLiveEvent r2 = r16.getTemperatureIncrementLiveEvent()
            r2.postValue(r1)
            goto L_0x019e
        L_0x00d9:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            float r2 = r2.relativeTemperature
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r4 = r0.mDetailedIduModel
            float r4 = r4.iduTemperature
            int r5 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1))
            if (r5 == 0) goto L_0x019e
            long r8 = r1.getMaxTemperature()
            float r8 = (float) r8
            int r2 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r2 >= 0) goto L_0x019e
            float r2 = r1.getTemperatureSettingPitchType()
            com.jch.racWiFi.settings.model.TemperatureUnit r8 = com.jch.racWiFi.settings.model.TemperatureUnit.CURRENT
            com.jch.racWiFi.settings.model.TemperatureUnit r9 = com.jch.racWiFi.settings.model.TemperatureUnit.FAHRENHEIT
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x0110
            boolean r4 = com.jch.racWiFi.settings.model.TemperatureUnit.ConversionUtil.isTemperatureAtSpecialScenarioWhileIncrement(r4)
            if (r4 == 0) goto L_0x0110
            int r4 = (r2 > r15 ? 1 : (r2 == r15 ? 0 : -1))
            if (r4 != 0) goto L_0x0109
            r9 = 1073741824(0x40000000, float:2.0)
            goto L_0x0111
        L_0x0109:
            int r4 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r4 != 0) goto L_0x0110
            r9 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0111
        L_0x0110:
            r9 = r2
        L_0x0111:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            float r4 = r2.relativeTemperature
            float r4 = r4 + r9
            r2.relativeTemperature = r4
            com.jch.racWiFi.settings.model.TemperatureUnit r2 = com.jch.racWiFi.settings.model.TemperatureUnit.CURRENT
            com.jch.racWiFi.settings.model.TemperatureUnit r4 = com.jch.racWiFi.settings.model.TemperatureUnit.FAHRENHEIT
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0163
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            float r2 = r2.relativeTemperature
            int r2 = java.lang.Float.compare(r2, r13)
            if (r2 != 0) goto L_0x0131
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            r2.relativeTemperature = r11
            goto L_0x0163
        L_0x0131:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            float r2 = r2.relativeTemperature
            int r2 = java.lang.Float.compare(r2, r12)
            if (r2 != 0) goto L_0x0140
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            r2.relativeTemperature = r7
            goto L_0x0163
        L_0x0140:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            float r2 = r2.relativeTemperature
            r4 = 1097334784(0x41680000, float:14.5)
            int r2 = java.lang.Float.compare(r2, r4)
            if (r2 != 0) goto L_0x0153
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            r3 = 1096810496(0x41600000, float:14.0)
            r2.relativeTemperature = r3
            goto L_0x0163
        L_0x0153:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            float r2 = r2.relativeTemperature
            int r2 = java.lang.Float.compare(r2, r6)
            if (r2 != 0) goto L_0x0163
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            r3 = 1098907648(0x41800000, float:16.0)
            r2.relativeTemperature = r3
        L_0x0163:
            float r2 = r1.getReferenceTemperature()
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r3 = r0.mDetailedIduModel
            float r3 = r3.relativeTemperature
            float r2 = r2 + r3
            com.jch.racWiFi.iduManagement.model.RacModelWiseData$TemperatureSettingType r1 = r1.getVisibleTemperatureSettingType()
            if (r1 == 0) goto L_0x018b
            com.jch.racWiFi.iduManagement.model.RacModelWiseData$TemperatureSettingType r3 = com.jch.racWiFi.iduManagement.model.RacModelWiseData.TemperatureSettingType.RELATIVE
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x018b
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            float r1 = r1.relativeTemperature
            java.lang.Float r1 = java.lang.Float.valueOf(r1)
            com.jch.racWiFi.iduManagement.model.RacModelWiseData r2 = r0.mRacModelWiseData
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r3 = r0.mDetailedIduModel
            java.lang.String r1 = com.jch.racWiFi.Utils.TemperatureValueFormatter.getConvertedTemperatureAuto(r1, r2, r3)
            goto L_0x0197
        L_0x018b:
            java.lang.Float r1 = java.lang.Float.valueOf(r2)
            com.jch.racWiFi.iduManagement.model.RacModelWiseData r2 = r0.mRacModelWiseData
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r3 = r0.mDetailedIduModel
            java.lang.String r1 = com.jch.racWiFi.Utils.TemperatureValueFormatter.getConvertedTemperature((java.lang.Float) r1, (com.jch.racWiFi.iduManagement.model.RacModelWiseData) r2, (com.jch.racWiFi.iduManagement.model.DetailedIduModel) r3)
        L_0x0197:
            com.jch.racWiFi.Listeners.SingleLiveEvent r2 = r16.getTemperatureIncrementLiveEvent()
            r2.postValue(r1)
        L_0x019e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.viewModel.TemperatureChangeViewModel.increaseTemperature():void");
    }

    /* renamed from: com.jch.racWiFi.iduManagement.viewModel.TemperatureChangeViewModel$1 */
    static /* synthetic */ class C21921 {

        /* renamed from: $SwitchMap$com$jch$racWiFi$iduManagement$model$RacModelWiseData$TemperatureSettingType */
        static final /* synthetic */ int[] f474xf7673082;

        static {
            int[] iArr = new int[RacModelWiseData.TemperatureSettingType.values().length];
            f474xf7673082 = iArr;
            try {
                iArr[RacModelWiseData.TemperatureSettingType.RELATIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x017a  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x018b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void increaseTemperatureSmartFence() {
        /*
            r16 = this;
            r0 = r16
            com.jch.racWiFi.iduManagement.model.RacModelWiseData r1 = r0.mRacModelWiseData
            if (r1 == 0) goto L_0x019e
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            com.jch.racWiFi.iduManagement.model.OperationMode r1 = r1.getOperationModeEnum()
            com.jch.racWiFi.iduManagement.model.RacModelWiseData r2 = r0.mRacModelWiseData
            com.jch.racWiFi.iduManagement.model.RacModelWiseData$RacModeDetailList r2 = r2.getRacModeDetails()
            com.jch.racWiFi.iduManagement.model.RacModelWiseData$RacModeDetail r1 = r2.getRacModeDetailBasedOnMode(r1)
            if (r1 == 0) goto L_0x019e
            com.jch.racWiFi.iduManagement.model.RacModelWiseData$TemperatureSettingType r2 = r1.getTemperatureSettingType()
            if (r2 == 0) goto L_0x019e
            int[] r3 = com.jch.racWiFi.iduManagement.viewModel.TemperatureChangeViewModel.C21921.f474xf7673082
            int r2 = r2.ordinal()
            r2 = r3[r2]
            r3 = 1
            r6 = 1098383360(0x41780000, float:15.5)
            r7 = 1104150528(0x41d00000, float:26.0)
            r8 = 1097334784(0x41680000, float:14.5)
            r10 = 1056964608(0x3f000000, float:0.5)
            r11 = 1103101952(0x41c00000, float:24.0)
            r12 = 1103888384(0x41cc0000, float:25.5)
            r13 = 1103364096(0x41c40000, float:24.5)
            r14 = 2139095039(0x7f7fffff, float:3.4028235E38)
            r15 = 1065353216(0x3f800000, float:1.0)
            if (r2 == r3) goto L_0x00d9
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            float r2 = r2.iduTemperature
            int r3 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1))
            if (r3 == 0) goto L_0x019e
            long r4 = r1.getMaxTemperature()
            float r4 = (float) r4
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 >= 0) goto L_0x019e
            float r1 = r1.getTemperatureSettingPitchType()
            com.jch.racWiFi.settings.model.TemperatureUnit r4 = com.jch.racWiFi.settings.model.TemperatureUnit.CURRENT
            com.jch.racWiFi.settings.model.TemperatureUnit r5 = com.jch.racWiFi.settings.model.TemperatureUnit.FAHRENHEIT
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x006f
            boolean r2 = com.jch.racWiFi.settings.model.TemperatureUnit.ConversionUtil.isTemperatureAtSpecialScenarioWhileIncrement(r2)
            if (r2 == 0) goto L_0x006f
            int r2 = (r1 > r15 ? 1 : (r1 == r15 ? 0 : -1))
            if (r2 != 0) goto L_0x0068
            r9 = 1073741824(0x40000000, float:2.0)
            goto L_0x0070
        L_0x0068:
            int r2 = (r1 > r10 ? 1 : (r1 == r10 ? 0 : -1))
            if (r2 != 0) goto L_0x006f
            r9 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0070
        L_0x006f:
            r9 = r1
        L_0x0070:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            float r2 = r1.iduTemperature
            float r2 = r2 + r9
            r1.iduTemperature = r2
            com.jch.racWiFi.settings.model.TemperatureUnit r1 = com.jch.racWiFi.settings.model.TemperatureUnit.CURRENT
            com.jch.racWiFi.settings.model.TemperatureUnit r2 = com.jch.racWiFi.settings.model.TemperatureUnit.FAHRENHEIT
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x00c0
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            float r1 = r1.iduTemperature
            int r1 = java.lang.Float.compare(r1, r13)
            if (r1 != 0) goto L_0x0090
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            r1.iduTemperature = r11
            goto L_0x00c0
        L_0x0090:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            float r1 = r1.iduTemperature
            int r1 = java.lang.Float.compare(r1, r12)
            if (r1 != 0) goto L_0x009f
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            r1.iduTemperature = r7
            goto L_0x00c0
        L_0x009f:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            float r1 = r1.iduTemperature
            int r1 = java.lang.Float.compare(r1, r8)
            if (r1 != 0) goto L_0x00b0
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            r2 = 1096810496(0x41600000, float:14.0)
            r1.iduTemperature = r2
            goto L_0x00c0
        L_0x00b0:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            float r1 = r1.iduTemperature
            int r1 = java.lang.Float.compare(r1, r6)
            if (r1 != 0) goto L_0x00c0
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            r2 = 1098907648(0x41800000, float:16.0)
            r1.iduTemperature = r2
        L_0x00c0:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            float r1 = r1.iduTemperature
            java.lang.Float r1 = java.lang.Float.valueOf(r1)
            com.jch.racWiFi.iduManagement.model.RacModelWiseData r2 = r0.mRacModelWiseData
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r3 = r0.mDetailedIduModel
            java.lang.String r1 = com.jch.racWiFi.Utils.TemperatureValueFormatter.getConvertedTemperature((java.lang.Float) r1, (com.jch.racWiFi.iduManagement.model.RacModelWiseData) r2, (com.jch.racWiFi.iduManagement.model.DetailedIduModel) r3)
            com.jch.racWiFi.Listeners.SingleLiveEvent r2 = r16.getTemperatureIncrementLiveEvent()
            r2.postValue(r1)
            goto L_0x019e
        L_0x00d9:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            float r2 = r2.relativeTemperature
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r4 = r0.mDetailedIduModel
            float r4 = r4.iduTemperature
            int r5 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1))
            if (r5 == 0) goto L_0x019e
            long r8 = r1.getMaxTemperature()
            float r8 = (float) r8
            int r2 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r2 >= 0) goto L_0x019e
            float r2 = r1.getTemperatureSettingPitchType()
            com.jch.racWiFi.settings.model.TemperatureUnit r8 = com.jch.racWiFi.settings.model.TemperatureUnit.CURRENT
            com.jch.racWiFi.settings.model.TemperatureUnit r9 = com.jch.racWiFi.settings.model.TemperatureUnit.FAHRENHEIT
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x0110
            boolean r4 = com.jch.racWiFi.settings.model.TemperatureUnit.ConversionUtil.isTemperatureAtSpecialScenarioWhileIncrement(r4)
            if (r4 == 0) goto L_0x0110
            int r4 = (r2 > r15 ? 1 : (r2 == r15 ? 0 : -1))
            if (r4 != 0) goto L_0x0109
            r9 = 1073741824(0x40000000, float:2.0)
            goto L_0x0111
        L_0x0109:
            int r4 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r4 != 0) goto L_0x0110
            r9 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0111
        L_0x0110:
            r9 = r2
        L_0x0111:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            float r4 = r2.relativeTemperature
            float r4 = r4 + r9
            r2.relativeTemperature = r4
            com.jch.racWiFi.settings.model.TemperatureUnit r2 = com.jch.racWiFi.settings.model.TemperatureUnit.CURRENT
            com.jch.racWiFi.settings.model.TemperatureUnit r4 = com.jch.racWiFi.settings.model.TemperatureUnit.FAHRENHEIT
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0163
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            float r2 = r2.relativeTemperature
            int r2 = java.lang.Float.compare(r2, r13)
            if (r2 != 0) goto L_0x0131
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            r2.relativeTemperature = r11
            goto L_0x0163
        L_0x0131:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            float r2 = r2.relativeTemperature
            int r2 = java.lang.Float.compare(r2, r12)
            if (r2 != 0) goto L_0x0140
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            r2.relativeTemperature = r7
            goto L_0x0163
        L_0x0140:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            float r2 = r2.relativeTemperature
            r4 = 1097334784(0x41680000, float:14.5)
            int r2 = java.lang.Float.compare(r2, r4)
            if (r2 != 0) goto L_0x0153
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            r3 = 1096810496(0x41600000, float:14.0)
            r2.relativeTemperature = r3
            goto L_0x0163
        L_0x0153:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            float r2 = r2.relativeTemperature
            int r2 = java.lang.Float.compare(r2, r6)
            if (r2 != 0) goto L_0x0163
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            r3 = 1098907648(0x41800000, float:16.0)
            r2.relativeTemperature = r3
        L_0x0163:
            float r2 = r1.getReferenceTemperature()
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r3 = r0.mDetailedIduModel
            float r3 = r3.relativeTemperature
            float r2 = r2 + r3
            com.jch.racWiFi.iduManagement.model.RacModelWiseData$TemperatureSettingType r1 = r1.getVisibleTemperatureSettingType()
            if (r1 == 0) goto L_0x018b
            com.jch.racWiFi.iduManagement.model.RacModelWiseData$TemperatureSettingType r3 = com.jch.racWiFi.iduManagement.model.RacModelWiseData.TemperatureSettingType.RELATIVE
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x018b
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            float r1 = r1.relativeTemperature
            java.lang.Float r1 = java.lang.Float.valueOf(r1)
            com.jch.racWiFi.iduManagement.model.RacModelWiseData r2 = r0.mRacModelWiseData
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r3 = r0.mDetailedIduModel
            java.lang.String r1 = com.jch.racWiFi.Utils.TemperatureValueFormatter.getConvertedTemperatureAuto(r1, r2, r3)
            goto L_0x0197
        L_0x018b:
            java.lang.Float r1 = java.lang.Float.valueOf(r2)
            com.jch.racWiFi.iduManagement.model.RacModelWiseData r2 = r0.mRacModelWiseData
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r3 = r0.mDetailedIduModel
            java.lang.String r1 = com.jch.racWiFi.Utils.TemperatureValueFormatter.getConvertedTemperature((java.lang.Float) r1, (com.jch.racWiFi.iduManagement.model.RacModelWiseData) r2, (com.jch.racWiFi.iduManagement.model.DetailedIduModel) r3)
        L_0x0197:
            com.jch.racWiFi.Listeners.SingleLiveEvent r2 = r16.getTemperatureIncrementLiveEvent()
            r2.postValue(r1)
        L_0x019e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.viewModel.TemperatureChangeViewModel.increaseTemperatureSmartFence():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0126  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x017a  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x018b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void decrementTemperature() {
        /*
            r16 = this;
            r0 = r16
            com.jch.racWiFi.iduManagement.model.RacModelWiseData r1 = r0.mRacModelWiseData
            if (r1 == 0) goto L_0x019e
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            com.jch.racWiFi.iduManagement.model.OperationMode r1 = r1.getOperationModeEnum()
            com.jch.racWiFi.iduManagement.model.RacModelWiseData r2 = r0.mRacModelWiseData
            com.jch.racWiFi.iduManagement.model.RacModelWiseData$RacModeDetailList r2 = r2.getRacModeDetails()
            com.jch.racWiFi.iduManagement.model.RacModelWiseData$RacModeDetail r1 = r2.getRacModeDetailBasedOnMode(r1)
            if (r1 == 0) goto L_0x019e
            com.jch.racWiFi.iduManagement.model.RacModelWiseData$TemperatureSettingType r2 = r1.getTemperatureSettingType()
            if (r2 == 0) goto L_0x019e
            int[] r3 = com.jch.racWiFi.iduManagement.viewModel.TemperatureChangeViewModel.C21921.f474xf7673082
            int r2 = r2.ordinal()
            r2 = r3[r2]
            r3 = 1
            r6 = 1098383360(0x41780000, float:15.5)
            r7 = 1104150528(0x41d00000, float:26.0)
            r8 = 1097334784(0x41680000, float:14.5)
            r10 = 1056964608(0x3f000000, float:0.5)
            r11 = 1103101952(0x41c00000, float:24.0)
            r12 = 1103888384(0x41cc0000, float:25.5)
            r13 = 1103364096(0x41c40000, float:24.5)
            r14 = 2139095039(0x7f7fffff, float:3.4028235E38)
            r15 = 1065353216(0x3f800000, float:1.0)
            if (r2 == r3) goto L_0x00d9
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            float r2 = r2.iduTemperature
            int r3 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1))
            if (r3 == 0) goto L_0x019e
            long r4 = r1.getMinTemperature()
            float r4 = (float) r4
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 <= 0) goto L_0x019e
            float r1 = r1.getTemperatureSettingPitchType()
            com.jch.racWiFi.settings.model.TemperatureUnit r4 = com.jch.racWiFi.settings.model.TemperatureUnit.CURRENT
            com.jch.racWiFi.settings.model.TemperatureUnit r5 = com.jch.racWiFi.settings.model.TemperatureUnit.FAHRENHEIT
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x006f
            boolean r2 = com.jch.racWiFi.settings.model.TemperatureUnit.ConversionUtil.isTemperatureAtSpecialScenarioWhileDecrement(r2)
            if (r2 == 0) goto L_0x006f
            int r2 = (r1 > r15 ? 1 : (r1 == r15 ? 0 : -1))
            if (r2 != 0) goto L_0x0068
            r9 = 1073741824(0x40000000, float:2.0)
            goto L_0x0070
        L_0x0068:
            int r2 = (r1 > r10 ? 1 : (r1 == r10 ? 0 : -1))
            if (r2 != 0) goto L_0x006f
            r9 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0070
        L_0x006f:
            r9 = r1
        L_0x0070:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            float r2 = r1.iduTemperature
            float r2 = r2 - r9
            r1.iduTemperature = r2
            com.jch.racWiFi.settings.model.TemperatureUnit r1 = com.jch.racWiFi.settings.model.TemperatureUnit.CURRENT
            com.jch.racWiFi.settings.model.TemperatureUnit r2 = com.jch.racWiFi.settings.model.TemperatureUnit.FAHRENHEIT
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x00c0
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            float r1 = r1.iduTemperature
            int r1 = java.lang.Float.compare(r1, r13)
            if (r1 != 0) goto L_0x0090
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            r1.iduTemperature = r11
            goto L_0x00c0
        L_0x0090:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            float r1 = r1.iduTemperature
            int r1 = java.lang.Float.compare(r1, r12)
            if (r1 != 0) goto L_0x009f
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            r1.iduTemperature = r7
            goto L_0x00c0
        L_0x009f:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            float r1 = r1.iduTemperature
            int r1 = java.lang.Float.compare(r1, r8)
            if (r1 != 0) goto L_0x00b0
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            r2 = 1096810496(0x41600000, float:14.0)
            r1.iduTemperature = r2
            goto L_0x00c0
        L_0x00b0:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            float r1 = r1.iduTemperature
            int r1 = java.lang.Float.compare(r1, r6)
            if (r1 != 0) goto L_0x00c0
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            r2 = 1098907648(0x41800000, float:16.0)
            r1.iduTemperature = r2
        L_0x00c0:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            float r1 = r1.iduTemperature
            java.lang.Float r1 = java.lang.Float.valueOf(r1)
            com.jch.racWiFi.iduManagement.model.RacModelWiseData r2 = r0.mRacModelWiseData
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r3 = r0.mDetailedIduModel
            java.lang.String r1 = com.jch.racWiFi.Utils.TemperatureValueFormatter.getConvertedTemperature((java.lang.Float) r1, (com.jch.racWiFi.iduManagement.model.RacModelWiseData) r2, (com.jch.racWiFi.iduManagement.model.DetailedIduModel) r3)
            com.jch.racWiFi.Listeners.SingleLiveEvent r2 = r16.getTemperatureDecrementLiveEvent()
            r2.postValue(r1)
            goto L_0x019e
        L_0x00d9:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            float r2 = r2.relativeTemperature
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r4 = r0.mDetailedIduModel
            float r4 = r4.iduTemperature
            int r5 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1))
            if (r5 == 0) goto L_0x019e
            long r8 = r1.getMinTemperature()
            float r8 = (float) r8
            int r2 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r2 <= 0) goto L_0x019e
            float r2 = r1.getTemperatureSettingPitchType()
            float r8 = r1.getReferenceTemperature()
            com.jch.racWiFi.settings.model.TemperatureUnit r9 = com.jch.racWiFi.settings.model.TemperatureUnit.CURRENT
            com.jch.racWiFi.settings.model.TemperatureUnit r3 = com.jch.racWiFi.settings.model.TemperatureUnit.FAHRENHEIT
            boolean r3 = r9.equals(r3)
            if (r3 == 0) goto L_0x0114
            boolean r3 = com.jch.racWiFi.settings.model.TemperatureUnit.ConversionUtil.isTemperatureAtSpecialScenarioWhileDecrement(r4)
            if (r3 == 0) goto L_0x0114
            int r3 = (r2 > r15 ? 1 : (r2 == r15 ? 0 : -1))
            if (r3 != 0) goto L_0x010d
            r9 = 1073741824(0x40000000, float:2.0)
            goto L_0x0115
        L_0x010d:
            int r3 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r3 != 0) goto L_0x0114
            r9 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0115
        L_0x0114:
            r9 = r2
        L_0x0115:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            float r3 = r2.relativeTemperature
            float r3 = r3 - r9
            r2.relativeTemperature = r3
            com.jch.racWiFi.settings.model.TemperatureUnit r2 = com.jch.racWiFi.settings.model.TemperatureUnit.CURRENT
            com.jch.racWiFi.settings.model.TemperatureUnit r3 = com.jch.racWiFi.settings.model.TemperatureUnit.FAHRENHEIT
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0167
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            float r2 = r2.relativeTemperature
            int r2 = java.lang.Float.compare(r2, r13)
            if (r2 != 0) goto L_0x0135
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            r2.relativeTemperature = r11
            goto L_0x0167
        L_0x0135:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            float r2 = r2.relativeTemperature
            int r2 = java.lang.Float.compare(r2, r12)
            if (r2 != 0) goto L_0x0144
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            r2.relativeTemperature = r7
            goto L_0x0167
        L_0x0144:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            float r2 = r2.relativeTemperature
            r3 = 1097334784(0x41680000, float:14.5)
            int r2 = java.lang.Float.compare(r2, r3)
            if (r2 != 0) goto L_0x0157
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            r3 = 1096810496(0x41600000, float:14.0)
            r2.relativeTemperature = r3
            goto L_0x0167
        L_0x0157:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            float r2 = r2.relativeTemperature
            int r2 = java.lang.Float.compare(r2, r6)
            if (r2 != 0) goto L_0x0167
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            r3 = 1098907648(0x41800000, float:16.0)
            r2.relativeTemperature = r3
        L_0x0167:
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r2 = r0.mDetailedIduModel
            float r2 = r2.relativeTemperature
            float r8 = r8 + r2
            com.jch.racWiFi.iduManagement.model.RacModelWiseData$TemperatureSettingType r1 = r1.getVisibleTemperatureSettingType()
            if (r1 == 0) goto L_0x018b
            com.jch.racWiFi.iduManagement.model.RacModelWiseData$TemperatureSettingType r2 = com.jch.racWiFi.iduManagement.model.RacModelWiseData.TemperatureSettingType.RELATIVE
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x018b
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r1 = r0.mDetailedIduModel
            float r1 = r1.relativeTemperature
            java.lang.Float r1 = java.lang.Float.valueOf(r1)
            com.jch.racWiFi.iduManagement.model.RacModelWiseData r2 = r0.mRacModelWiseData
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r3 = r0.mDetailedIduModel
            java.lang.String r1 = com.jch.racWiFi.Utils.TemperatureValueFormatter.getConvertedTemperatureAuto(r1, r2, r3)
            goto L_0x0197
        L_0x018b:
            java.lang.Float r1 = java.lang.Float.valueOf(r8)
            com.jch.racWiFi.iduManagement.model.RacModelWiseData r2 = r0.mRacModelWiseData
            com.jch.racWiFi.iduManagement.model.DetailedIduModel r3 = r0.mDetailedIduModel
            java.lang.String r1 = com.jch.racWiFi.Utils.TemperatureValueFormatter.getConvertedTemperature((java.lang.Float) r1, (com.jch.racWiFi.iduManagement.model.RacModelWiseData) r2, (com.jch.racWiFi.iduManagement.model.DetailedIduModel) r3)
        L_0x0197:
            com.jch.racWiFi.Listeners.SingleLiveEvent r2 = r16.getTemperatureDecrementLiveEvent()
            r2.postValue(r1)
        L_0x019e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.iduManagement.viewModel.TemperatureChangeViewModel.decrementTemperature():void");
    }

    public static class TemperatureChangeViewModelFactory extends ViewModelProvider.NewInstanceFactory {
        private DetailedIduModel detailedIduModel;
        private RacModelWiseData mRacModelWiseData;

        public TemperatureChangeViewModelFactory(DetailedIduModel detailedIduModel2, RacModelWiseData racModelWiseData) {
            this.detailedIduModel = detailedIduModel2;
            this.mRacModelWiseData = racModelWiseData;
        }

        public <T extends C0534ViewModel> T create(Class<T> cls) {
            return new TemperatureChangeViewModel(this.detailedIduModel, this.mRacModelWiseData);
        }
    }
}
