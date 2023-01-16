package com.jch.racWiFi;

import android.content.res.Resources;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;
import p015me.relex.circleindicator.CircleIndicator;

public class TutorialFragment_ViewBinding implements Unbinder {
    private TutorialFragment target;
    private View view7f0a0159;
    private View view7f0a0ac0;

    public TutorialFragment_ViewBinding(final TutorialFragment tutorialFragment, View view) {
        this.target = tutorialFragment;
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.button_continue, "field 'mContinue' and method 'onClickContinue'");
        tutorialFragment.mContinue = (Button) C0840Utils.castView(findRequiredView, R.id.button_continue, "field 'mContinue'", Button.class);
        this.view7f0a0159 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                tutorialFragment.onClickContinue((Button) C0840Utils.castParam(view, "doClick", 0, "onClickContinue", 0, Button.class));
            }
        });
        tutorialFragment.viewPager = (ViewPager) C0840Utils.findRequiredViewAsType(view, R.id.tutorialViewPager, "field 'viewPager'", ViewPager.class);
        tutorialFragment.indicator = (CircleIndicator) C0840Utils.findRequiredViewAsType(view, R.id.viewpager_indicator, "field 'indicator'", CircleIndicator.class);
        tutorialFragment.mTextViewTutorialSubHeadingOne = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_tutorial_sub_heading_one, "field 'mTextViewTutorialSubHeadingOne'", TextView.class);
        tutorialFragment.mTextViewTutorialSubHeadingTwo = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_tutorial_ssub_heading_two, "field 'mTextViewTutorialSubHeadingTwo'", TextView.class);
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.text_view_skip, "field 'mSkip' and method 'onClickSkip'");
        tutorialFragment.mSkip = (ImageButton) C0840Utils.castView(findRequiredView2, R.id.text_view_skip, "field 'mSkip'", ImageButton.class);
        this.view7f0a0ac0 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                tutorialFragment.onClickSkip((ImageButton) C0840Utils.castParam(view, "doClick", 0, "onClickSkip", 0, ImageButton.class));
            }
        });
        Resources resources = view.getContext().getResources();
        tutorialFragment.mTutorialSubHeadingOne = resources.getStringArray(R.array.appLaunch_array_title);
        tutorialFragment.mTutorialSubHeadingTwo = resources.getStringArray(R.array.appLaunch_array_subTitle);
    }

    public void unbind() {
        TutorialFragment tutorialFragment = this.target;
        if (tutorialFragment != null) {
            this.target = null;
            tutorialFragment.mContinue = null;
            tutorialFragment.viewPager = null;
            tutorialFragment.indicator = null;
            tutorialFragment.mTextViewTutorialSubHeadingOne = null;
            tutorialFragment.mTextViewTutorialSubHeadingTwo = null;
            tutorialFragment.mSkip = null;
            this.view7f0a0159.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0159 = null;
            this.view7f0a0ac0.setOnClickListener((View.OnClickListener) null);
            this.view7f0a0ac0 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
