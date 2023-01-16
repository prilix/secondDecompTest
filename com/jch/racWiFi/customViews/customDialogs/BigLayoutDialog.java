package com.jch.racWiFi.customViews.customDialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.View;

public class BigLayoutDialog extends Dialog {
    private int layoutRes;

    public BigLayoutDialog(Context context, int i) {
        super(context);
        this.layoutRes = i;
        setContentView(i);
    }

    public BigLayoutDialog(Context context, int i, boolean z) {
        super(context);
        this.layoutRes = i;
        if (z) {
            setContentView(i);
        }
    }

    public void setParentView(View view) {
        setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                DisplayMetrics displayMetrics = BigLayoutDialog.this.getContext().getResources().getDisplayMetrics();
                int i = (int) (((double) displayMetrics.heightPixels) * 0.85d);
                BigLayoutDialog.this.getWindow().setLayout((int) (((double) displayMetrics.widthPixels) * 0.99d), i);
            }
        });
        setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
            }
        });
    }

    public void setParentViewFullHeight(View view) {
        setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                DisplayMetrics displayMetrics = BigLayoutDialog.this.getContext().getResources().getDisplayMetrics();
                BigLayoutDialog.this.getWindow().setLayout(displayMetrics.widthPixels, displayMetrics.heightPixels);
            }
        });
        setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
            }
        });
    }

    public void setParentView(View view, final boolean z) {
        setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                DisplayMetrics displayMetrics = BigLayoutDialog.this.getContext().getResources().getDisplayMetrics();
                BigLayoutDialog.this.getWindow().setLayout((int) (((double) displayMetrics.widthPixels) * 0.99d), !z ? (int) (((double) displayMetrics.heightPixels) * 0.85d) : -2);
            }
        });
        setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
            }
        });
    }
}
