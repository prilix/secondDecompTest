package com.jch.racWiFi.userManagement.view;

import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class UserPreferencesFragment_ViewBinding implements Unbinder {
    private UserPreferencesFragment target;
    private View view7f0a0170;
    private View view7f0a018a;
    private View view7f0a0ac0;

    public UserPreferencesFragment_ViewBinding(final UserPreferencesFragment userPreferencesFragment, View view) {
        this.target = userPreferencesFragment;
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.button_no, "field 'mNo' and method 'onClick'");
        userPreferencesFragment.mNo = (Button) C0840Utils.castView(findRequiredView, R.id.button_no, "field 'mNo'", Button.class);
        this.view7f0a0170 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                userPreferencesFragment.onClick(view);
            }
        });
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.button_yes, "field 'mYes' and method 'onClick'");
        userPreferencesFragment.mYes = (Button) C0840Utils.castView(findRequiredView2, R.id.button_yes, "field 'mYes'", Button.class);
        this.view7f0a018a = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                userPreferencesFragment.onClick(view);
            }
        });
        userPreferencesFragment.linearLayout = (LinearLayout) C0840Utils.findRequiredViewAsType(view, R.id.root_layout, "field 'linearLayout'", LinearLayout.class);
        View findRequiredView3 = C0840Utils.findRequiredView(view, R.id.text_view_skip, "field 'mSkip' and method 'onClick'");
        userPreferencesFragment.mSkip = (ImageButton) C0840Utils.castView(findRequiredView3, R.id.text_view_skip, "field 'mSkip'", ImageButton.class);
        this.view7f0a0ac0 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                userPreferencesFragment.onClick(view);
            }
        });
        userPreferencesFragment.mStepsTextView = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_step_1, "field 'mStepsTextView'", TextView.class);
        userPreferencesFragment.mUserPreferenceQuestionsTextView = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_user_preference_question, "field 'mUserPreferenceQuestionsTextView'", TextView.class);
        userPreferencesFragment.mBackgroundImageLayout = (LinearLayout) C0840Utils.findRequiredViewAsType(view, R.id.user_preference_image_layout, "field 'mBackgroundImageLayout'", LinearLayout.class);
        Resources resources = view.getContext().getResources();
        userPreferencesFragment.mUserPreferenceQuestions = resources.getStringArray(R.array.user_preference_questions_array);
        userPreferencesFragment.mStepsArray = resources.getStringArray(R.array.steps_1_to_3);
    }

    public void unbind() {
        UserPreferencesFragment userPreferencesFragment = this.target;
        if (userPreferencesFragment != null) {
            this.target = null;
            userPreferencesFragment.mNo = null;
            userPreferencesFragment.mYes = null;
            userPreferencesFragment.linearLayout = null;
            userPreferencesFragment.mSkip = null;
            userPreferencesFragment.mStepsTextView = null;
            userPreferencesFragment.mUserPreferenceQuestionsTextView = null;
            userPreferencesFragment.mBackgroundImageLayout = null;
            this.view7f0a0170.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0170 = null;
            this.view7f0a018a.setOnClickListener((View.OnClickListener) null);
            this.view7f0a018a = null;
            this.view7f0a0ac0.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0ac0 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
