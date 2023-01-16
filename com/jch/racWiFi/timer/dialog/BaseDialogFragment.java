package com.jch.racWiFi.timer.dialog;

import android.app.Dialog;
import android.graphics.Point;
import android.view.Window;
import androidx.fragment.app.DialogFragment;

public class BaseDialogFragment extends DialogFragment {
    public void onResume() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            Point point = new Point();
            if (window != null) {
                window.getWindowManager().getDefaultDisplay().getSize(point);
                window.setLayout((int) (((double) point.x) * 0.9d), -2);
                window.setGravity(17);
                super.onResume();
            }
        }
    }
}
