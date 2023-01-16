package com.jch.racWiFi.energyConsumption.fragment;

import androidx.activity.OnBackPressedCallback;
import kotlin.Metadata;

@Metadata(mo36737d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, mo36738d2 = {"com/jch/racWiFi/energyConsumption/fragment/ECSelectACFragment$onStart$1", "Landroidx/activity/OnBackPressedCallback;", "handleOnBackPressed", "", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: ECSelectACFragment.kt */
public final class ECSelectACFragment$onStart$1 extends OnBackPressedCallback {
    final /* synthetic */ ECSelectACFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ECSelectACFragment$onStart$1(ECSelectACFragment eCSelectACFragment) {
        super(true);
        this.this$0 = eCSelectACFragment;
    }

    public void handleOnBackPressed() {
        this.this$0.onBackClick();
    }
}
