package com.jch.racWiFi.energyConsumption.fragment;

import android.view.View;
import androidx.core.content.ContextCompat;
import com.jch.racWiFi.C1655R;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.tooltip.Tooltip;
import com.jch_hitachi.aircloudglobal.R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(mo36737d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, mo36738d2 = {"<anonymous>", "", "it", "Lcom/jch/racWiFi/tooltip/Tooltip;"}, mo36739k = 3, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: ECSelectACFragment.kt */
final class ECSelectACFragment$showToolTip$2 extends Lambda implements Function1<Tooltip, Unit> {
    final /* synthetic */ View $view;
    final /* synthetic */ ECSelectACFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ECSelectACFragment$showToolTip$2(ECSelectACFragment eCSelectACFragment, View view) {
        super(1);
        this.this$0 = eCSelectACFragment;
        this.$view = view;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Tooltip) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Tooltip tooltip) {
        Intrinsics.checkNotNullParameter(tooltip, "it");
        View view = this.this$0.getView();
        ((ImageView) (view == null ? null : view.findViewById(C1655R.C1658id.ecQuestionMarkImage))).setImageDrawable(ContextCompat.getDrawable(this.$view.getContext(), R.drawable.ic_grey_question_mark));
    }
}
