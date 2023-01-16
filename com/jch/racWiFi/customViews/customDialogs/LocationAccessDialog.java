package com.jch.racWiFi.customViews.customDialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import com.jch.racWiFi.databinding.LocationControlDialogBinding;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.List;

public class LocationAccessDialog extends BigLayoutDialog {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private LocationControlDialogBinding locationControlDialogBinding;
    private List<View> rationaleInfo;

    public void setDimensions(int i) {
        setOnShowListener(new LocationAccessDialog$$ExternalSyntheticLambda0(this, i));
    }

    /* renamed from: lambda$setDimensions$0$com-jch-racWiFi-customViews-customDialogs-LocationAccessDialog */
    public /* synthetic */ void mo28190xce0f5e98(int i, DialogInterface dialogInterface) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        if (displayMetrics != null) {
            getWindow().setLayout(i, (int) (((float) getContext().getResources().getDisplayMetrics().heightPixels) * Constants.CC.getHeight(displayMetrics)));
        }
    }

    public LocationAccessDialog(Context context) {
        super(context, R.layout.location_control_dialog, false);
        LocationControlDialogBinding locationControlDialogBinding2 = (LocationControlDialogBinding) DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.location_control_dialog, (ViewGroup) null, false);
        this.locationControlDialogBinding = locationControlDialogBinding2;
        setContentView(locationControlDialogBinding2.getRoot());
        setCancelable(false);
        ArrayList arrayList = new ArrayList();
        this.rationaleInfo = arrayList;
        arrayList.add(this.locationControlDialogBinding.textViewRealtimeWeatherInfo);
        this.rationaleInfo.add(this.locationControlDialogBinding.textViewScanWifi);
        this.rationaleInfo.add(this.locationControlDialogBinding.textViewRunOperationBasedOnGeoLocation);
    }

    public void setLocationAccessRationale(LocationAccessRationale... locationAccessRationaleArr) {
        int length = locationAccessRationaleArr.length;
        if (length == 1) {
            int i = C17271.f427xbd520afa[locationAccessRationaleArr[0].ordinal()];
            if (i == 1) {
                this.locationControlDialogBinding.textViewRealtimeWeatherInfo.setVisibility(0);
                updateMyAccountVisibility(0);
                updateNoteVisibility(8);
            } else if (i == 2) {
                this.locationControlDialogBinding.textViewScanWifi.setVisibility(0);
                updateNoteVisibility(8);
                updateMyAccountVisibility(8);
            } else if (i == 3) {
                this.locationControlDialogBinding.textViewRunOperationBasedOnGeoLocation.setVisibility(0);
                updateNoteVisibility(0);
                updateMyAccountVisibility(8);
            } else if (i == 4) {
                this.locationControlDialogBinding.textViewRunOperationBasedOnGeoLocation.setVisibility(0);
                updateNoteVisibility(8);
                updateMyAccountVisibility(0);
            }
        } else if (length == 2 && locationAccessRationaleArr[0].equals(LocationAccessRationale.WEATHER) && locationAccessRationaleArr[1].equals(LocationAccessRationale.SMART_FENCE)) {
            this.locationControlDialogBinding.textViewRealtimeWeatherInfo.setVisibility(0);
            updateMyAccountVisibility(0);
            updateNoteVisibility(8);
            this.locationControlDialogBinding.textViewRunOperationBasedOnGeoLocation.setVisibility(0);
        }
    }

    /* renamed from: com.jch.racWiFi.customViews.customDialogs.LocationAccessDialog$1 */
    static /* synthetic */ class C17271 {

        /* renamed from: $SwitchMap$com$jch$racWiFi$customViews$customDialogs$LocationAccessRationale */
        static final /* synthetic */ int[] f427xbd520afa;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.jch.racWiFi.customViews.customDialogs.LocationAccessRationale[] r0 = com.jch.racWiFi.customViews.customDialogs.LocationAccessRationale.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f427xbd520afa = r0
                com.jch.racWiFi.customViews.customDialogs.LocationAccessRationale r1 = com.jch.racWiFi.customViews.customDialogs.LocationAccessRationale.WEATHER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f427xbd520afa     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.customViews.customDialogs.LocationAccessRationale r1 = com.jch.racWiFi.customViews.customDialogs.LocationAccessRationale.WIFI_SCANNING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f427xbd520afa     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.customViews.customDialogs.LocationAccessRationale r1 = com.jch.racWiFi.customViews.customDialogs.LocationAccessRationale.SMART_FENCE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f427xbd520afa     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.jch.racWiFi.customViews.customDialogs.LocationAccessRationale r1 = com.jch.racWiFi.customViews.customDialogs.LocationAccessRationale.ACCOUNT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.customViews.customDialogs.LocationAccessDialog.C17271.<clinit>():void");
        }
    }

    public void setOnLocationEnableClickListener(View.OnClickListener onClickListener) {
        this.locationControlDialogBinding.buttonEnableLocationAccess.setOnClickListener(onClickListener);
    }

    public void setOnCloseClickListener(View.OnClickListener onClickListener) {
        this.locationControlDialogBinding.imageButtonClear.setOnClickListener(onClickListener);
    }

    public void updateNoteVisibility(int i) {
        this.locationControlDialogBinding.locationControlNote.setVisibility(i);
    }

    public void updateMyAccountVisibility(int i) {
        this.locationControlDialogBinding.locationControlNoteMyAccountAddress.setVisibility(i);
    }
}
