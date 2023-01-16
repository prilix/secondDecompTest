package com.jch.racWiFi.energyConsumption.fragment;

import com.jch.racWiFi.fcm.standard.CallBackListener;
import kotlin.Metadata;

@Metadata(mo36737d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0005"}, mo36738d2 = {"com/jch/racWiFi/energyConsumption/fragment/ECSelectACFragment$saveEcSettings$1$1", "Lcom/jch/racWiFi/fcm/standard/CallBackListener;", "onFailure", "", "onSuccess", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: ECSelectACFragment.kt */
public final class ECSelectACFragment$saveEcSettings$1$1 implements CallBackListener {
    final /* synthetic */ ECSelectACFragment this$0;

    public void onFailure() {
    }

    ECSelectACFragment$saveEcSettings$1$1(ECSelectACFragment eCSelectACFragment) {
        this.this$0 = eCSelectACFragment;
    }

    public void onSuccess() {
        this.this$0.saveEcSettings();
    }
}
