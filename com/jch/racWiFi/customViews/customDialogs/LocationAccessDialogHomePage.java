package com.jch.racWiFi.customViews.customDialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import com.jch.racWiFi.databinding.LocationServicesDisabledDialogBinding;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.List;

public class LocationAccessDialogHomePage extends BigLayoutDialog {
    private LocationServicesDisabledDialogBinding locationControlDialogBinding;
    private List<View> rationaleInfo;

    public void setDimensions(int i, int i2) {
        setOnShowListener(new LocationAccessDialogHomePage$$ExternalSyntheticLambda0(this, i, i2));
    }

    /* renamed from: lambda$setDimensions$0$com-jch-racWiFi-customViews-customDialogs-LocationAccessDialogHomePage */
    public /* synthetic */ void mo28198x5f50eec6(int i, int i2, DialogInterface dialogInterface) {
        getWindow().setLayout(i, i2);
    }

    public LocationAccessDialogHomePage(Context context) {
        super(context, R.layout.location_services_disabled_dialog, false);
        LocationServicesDisabledDialogBinding locationServicesDisabledDialogBinding = (LocationServicesDisabledDialogBinding) DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.location_services_disabled_dialog, (ViewGroup) null, false);
        this.locationControlDialogBinding = locationServicesDisabledDialogBinding;
        if (locationServicesDisabledDialogBinding != null) {
            setContentView(locationServicesDisabledDialogBinding.getRoot());
        }
        setCancelable(false);
        ArrayList arrayList = new ArrayList();
        this.rationaleInfo = arrayList;
        arrayList.add(this.locationControlDialogBinding.textViewLocationAccessDescOne);
    }

    public void setLocationAccessRationale(LocationAccessRationale... locationAccessRationaleArr) {
        int length = locationAccessRationaleArr.length;
        if (length == 1) {
            if (C17281.f428xbd520afa[locationAccessRationaleArr[0].ordinal()] == 1) {
                this.locationControlDialogBinding.textViewLocationAccessDescOne.setVisibility(0);
            }
        } else if (length == 2 && locationAccessRationaleArr[0].equals(LocationAccessRationale.WEATHER) && locationAccessRationaleArr[1].equals(LocationAccessRationale.SMART_FENCE)) {
            this.locationControlDialogBinding.textViewLocationAccessDescOne.setVisibility(0);
        }
    }

    /* renamed from: com.jch.racWiFi.customViews.customDialogs.LocationAccessDialogHomePage$1 */
    static /* synthetic */ class C17281 {

        /* renamed from: $SwitchMap$com$jch$racWiFi$customViews$customDialogs$LocationAccessRationale */
        static final /* synthetic */ int[] f428xbd520afa;

        static {
            int[] iArr = new int[LocationAccessRationale.values().length];
            f428xbd520afa = iArr;
            try {
                iArr[LocationAccessRationale.SMART_FENCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public void setOnLocationEnableClickListener(View.OnClickListener onClickListener) {
        this.locationControlDialogBinding.buttonEnableLocationAccess.setOnClickListener(onClickListener);
    }

    public void setOnCloseClickListener(View.OnClickListener onClickListener) {
        this.locationControlDialogBinding.textViewNotNow.setOnClickListener(onClickListener);
    }
}
