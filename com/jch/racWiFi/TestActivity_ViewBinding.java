package com.jch.racWiFi;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.jch_hitachi.aircloudglobal.R;

public class TestActivity_ViewBinding implements Unbinder {
    private TestActivity target;

    public TestActivity_ViewBinding(TestActivity testActivity) {
        this(testActivity, testActivity.getWindow().getDecorView());
    }

    public TestActivity_ViewBinding(TestActivity testActivity, View view) {
        this.target = testActivity;
        testActivity.mTextViewRefreshing = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.layout_device_name, "field 'mTextViewRefreshing'", ConstraintLayout.class);
    }

    public void unbind() {
        TestActivity testActivity = this.target;
        if (testActivity != null) {
            this.target = null;
            testActivity.mTextViewRefreshing = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
