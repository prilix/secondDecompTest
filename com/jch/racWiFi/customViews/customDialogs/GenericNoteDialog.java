package com.jch.racWiFi.customViews.customDialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.text.HtmlCompat;
import com.jch.racWiFi.C1655R;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.timer.dialog.BaseDialogFragment;
import com.jch_hitachi.aircloudglobal.R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36737d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J&\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u001a\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0010"}, mo36738d2 = {"Lcom/jch/racWiFi/customViews/customDialogs/GenericNoteDialog;", "Lcom/jch/racWiFi/timer/dialog/BaseDialogFragment;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", "view", "Companion", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: GenericNoteDialog.kt */
public final class GenericNoteDialog extends BaseDialogFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public void _$_clearFindViewByIdCache() {
    }

    @Metadata(mo36737d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, mo36738d2 = {"Lcom/jch/racWiFi/customViews/customDialogs/GenericNoteDialog$Companion;", "", "()V", "newInstance", "Lcom/jch/racWiFi/customViews/customDialogs/GenericNoteDialog;", "description", "", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
    /* compiled from: GenericNoteDialog.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final GenericNoteDialog newInstance(String str) {
            GenericNoteDialog genericNoteDialog = new GenericNoteDialog();
            Bundle bundle = new Bundle();
            bundle.putString("description", str);
            genericNoteDialog.setArguments(bundle);
            return genericNoteDialog;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCancelable(false);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.dialog_generic_note, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Bundle arguments = getArguments();
        View view2 = null;
        String string = arguments == null ? null : arguments.getString("description");
        View view3 = getView();
        ((TextView) (view3 == null ? null : view3.findViewById(C1655R.C1658id.descriptionText))).setText(string == null ? null : HtmlCompat.fromHtml(string, 0));
        View view4 = getView();
        if (view4 != null) {
            view2 = view4.findViewById(C1655R.C1658id.crossImageView);
        }
        ((AppCompatImageView) view2).setOnClickListener(new GenericNoteDialog$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    /* renamed from: onViewCreated$lambda-1  reason: not valid java name */
    public static final void m866onViewCreated$lambda1(GenericNoteDialog genericNoteDialog, View view) {
        Intrinsics.checkNotNullParameter(genericNoteDialog, "this$0");
        genericNoteDialog.dismiss();
    }
}
