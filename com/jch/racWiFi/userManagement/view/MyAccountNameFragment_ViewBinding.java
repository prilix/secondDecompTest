package com.jch.racWiFi.userManagement.view;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch_hitachi.aircloudglobal.R;

public class MyAccountNameFragment_ViewBinding implements Unbinder {
    private MyAccountNameFragment target;
    private View view7f0a011e;
    private View view7f0a0a77;

    public MyAccountNameFragment_ViewBinding(final MyAccountNameFragment myAccountNameFragment, View view) {
        this.target = myAccountNameFragment;
        myAccountNameFragment.mParent = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.parent, "field 'mParent'", ConstraintLayout.class);
        myAccountNameFragment.mFirstName = (EditText) C0840Utils.findRequiredViewAsType(view, R.id.edit_text_enter_first_name, "field 'mFirstName'", EditText.class);
        myAccountNameFragment.mMiddleName = (EditText) C0840Utils.findRequiredViewAsType(view, R.id.edit_text_enter_middle_name, "field 'mMiddleName'", EditText.class);
        myAccountNameFragment.mLastName = (EditText) C0840Utils.findRequiredViewAsType(view, R.id.edit_text_enter_last_name, "field 'mLastName'", EditText.class);
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.text_view_save, "field 'mSave' and method 'OnClickSave'");
        myAccountNameFragment.mSave = (ImageButton) C0840Utils.castView(findRequiredView, R.id.text_view_save, "field 'mSave'", ImageButton.class);
        this.view7f0a0a77 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myAccountNameFragment.OnClickSave((ImageButton) C0840Utils.castParam(view, "doClick", 0, "OnClickSave", 0, ImageButton.class));
            }
        });
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.back_button, "field 'mBack' and method 'OnClickBack'");
        myAccountNameFragment.mBack = (ImageButton) C0840Utils.castView(findRequiredView2, R.id.back_button, "field 'mBack'", ImageButton.class);
        this.view7f0a011e = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                myAccountNameFragment.OnClickBack((ImageButton) C0840Utils.castParam(view, "doClick", 0, "OnClickBack", 0, ImageButton.class));
            }
        });
        myAccountNameFragment.mFirstNameBubbleLayout = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.enter_first_name_bubble_layout, "field 'mFirstNameBubbleLayout'", ConstraintLayout.class);
        myAccountNameFragment.mLastNameBubbleLayout = (ConstraintLayout) C0840Utils.findRequiredViewAsType(view, R.id.enter_last_name_bubble_layout, "field 'mLastNameBubbleLayout'", ConstraintLayout.class);
    }

    public void unbind() {
        MyAccountNameFragment myAccountNameFragment = this.target;
        if (myAccountNameFragment != null) {
            this.target = null;
            myAccountNameFragment.mParent = null;
            myAccountNameFragment.mFirstName = null;
            myAccountNameFragment.mMiddleName = null;
            myAccountNameFragment.mLastName = null;
            myAccountNameFragment.mSave = null;
            myAccountNameFragment.mBack = null;
            myAccountNameFragment.mFirstNameBubbleLayout = null;
            myAccountNameFragment.mLastNameBubbleLayout = null;
            this.view7f0a0a77.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0a77 = null;
            this.view7f0a011e.setOnClickListener((View.OnClickListener) null);
            this.view7f0a011e = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
