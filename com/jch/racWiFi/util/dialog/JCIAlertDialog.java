package com.jch.racWiFi.util.dialog;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.jch.racWiFi.util.listeners.AlertListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36737d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u000eJ@\u0010\u000f\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0017\u001a\u00020\u0018R\u001a\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, mo36738d2 = {"Lcom/jch/racWiFi/util/dialog/JCIAlertDialog;", "", "mApplication", "Landroid/app/Application;", "(Landroid/app/Application;)V", "mAlert", "Landroid/app/AlertDialog;", "getMAlert", "()Landroid/app/AlertDialog;", "setMAlert", "(Landroid/app/AlertDialog;)V", "dismiss", "", "isShowing", "", "showDialog", "context", "Landroid/content/Context;", "title", "", "message", "positiveButton", "negativeButton", "listener", "Lcom/jch/racWiFi/util/listeners/AlertListener;", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: JCIAlertDialog.kt */
public final class JCIAlertDialog {
    public AlertDialog mAlert;
    private final Application mApplication;

    public JCIAlertDialog(Application application) {
        this.mApplication = application;
    }

    public final AlertDialog getMAlert() {
        AlertDialog alertDialog = this.mAlert;
        if (alertDialog != null) {
            return alertDialog;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mAlert");
        return null;
    }

    public final void setMAlert(AlertDialog alertDialog) {
        Intrinsics.checkNotNullParameter(alertDialog, "<set-?>");
        this.mAlert = alertDialog;
    }

    public final void showDialog(Context context, String str, String str2, String str3, String str4, AlertListener alertListener) {
        Intrinsics.checkNotNullParameter(alertListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Intrinsics.checkNotNull(context);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(str);
        builder.setMessage(str2).setCancelable(false).setPositiveButton(str3, new JCIAlertDialog$$ExternalSyntheticLambda1(alertListener)).setNegativeButton(str4, new JCIAlertDialog$$ExternalSyntheticLambda0(alertListener));
        AlertDialog create = builder.create();
        Intrinsics.checkNotNullExpressionValue(create, "builder.create()");
        setMAlert(create);
        getMAlert().setCancelable(false);
        getMAlert().show();
    }

    /* access modifiers changed from: private */
    /* renamed from: showDialog$lambda-1  reason: not valid java name */
    public static final void m1573showDialog$lambda1(AlertListener alertListener, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(alertListener, "$listener");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialog");
        dialogInterface.cancel();
        alertListener.onPositive();
    }

    /* access modifiers changed from: private */
    /* renamed from: showDialog$lambda-2  reason: not valid java name */
    public static final void m1574showDialog$lambda2(AlertListener alertListener, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(alertListener, "$listener");
        Intrinsics.checkNotNullParameter(dialogInterface, "dialog");
        dialogInterface.cancel();
        alertListener.onNegative();
    }

    public final boolean isShowing() {
        if (this.mAlert != null) {
            return getMAlert().isShowing();
        }
        return false;
    }

    public final void dismiss() {
        if (this.mAlert != null) {
            getMAlert().dismiss();
        }
    }
}
