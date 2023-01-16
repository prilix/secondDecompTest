package com.jch.racWiFi;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.jch.racWiFi.SharedPreference.SharedPref;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.userManagement.model.PrivacyPolicyModel;
import com.jch.racWiFi.userManagement.view.PrivacyPolicyFragment;
import com.jch.racWiFi.userManagement.view.SignUpFlow.CreateAccountStep5SuccessFragment;
import com.jch_hitachi.aircloudglobal.R;
import p015me.relex.circleindicator.CircleIndicator;

public class TutorialFragment extends GenericFragment {
    private static final String TUTORIAL_SHOWN = "TUTORIAL_SHOWN";
    private int[] imageTutorials = {R.drawable.img_access_anytime_anywhere, R.drawable.img_intutive_optimized, R.drawable.img_smart_energy_efficient};
    @BindView(2131364893)
    CircleIndicator indicator;
    @BindView(2131362137)
    Button mContinue;
    @BindView(2131364544)
    ImageButton mSkip;
    @BindView(2131364675)
    TextView mTextViewTutorialSubHeadingOne;
    @BindView(2131364674)
    TextView mTextViewTutorialSubHeadingTwo;
    @BindArray(2130903041)
    String[] mTutorialSubHeadingOne;
    @BindArray(2130903040)
    String[] mTutorialSubHeadingTwo;
    private Unbinder mUnbinder;
    @BindView(2131364800)
    ViewPager viewPager;

    public static TutorialFragment newInstance() {
        TutorialFragment tutorialFragment = new TutorialFragment();
        tutorialFragment.setNewBundleAsArgument();
        return tutorialFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.tutorial_layout, viewGroup, false);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        loadData();
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                TutorialFragment.this.mTextViewTutorialSubHeadingOne.setText(TutorialFragment.this.mTutorialSubHeadingOne[i]);
                TutorialFragment.this.mTextViewTutorialSubHeadingTwo.setText(TutorialFragment.this.mTutorialSubHeadingTwo[i]);
                if (i == 2) {
                    TutorialFragment.this.mContinue.setText(TutorialFragment.this.getResources().getString(R.string.appLaunch_btn_getStarted));
                } else {
                    TutorialFragment.this.mContinue.setText(TutorialFragment.this.getResources().getString(R.string.common_btn_continue));
                }
            }
        });
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (TutorialFragment.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
                    TutorialFragment.this.mFragmentToActivityCallback.changeStatusBarColor(R.color.white);
                }
            }
        }, 500);
        return inflate;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mFragmentToActivityCallback.changeStatusBarColor(R.color.colorRed);
        this.mUnbinder.unbind();
    }

    @OnClick({2131362137})
    public void onClickContinue(Button button) {
        if (this.viewPager.getCurrentItem() == 2) {
            getActivity().getSupportFragmentManager().popBackStack();
            if (Constants.privacyPolicyShown) {
                return;
            }
            if (!CreateAccountStep5SuccessFragment.atleastOneAccountWasCreatedAfterAppInstallation() || PrivacyPolicyModel.PrivacyPolicyData.PRIVACY_POLICY_UPDATED) {
                final PrivacyPolicyFragment privacyPolicyFragment = new PrivacyPolicyFragment();
                privacyPolicyFragment.setHideTitle(true);
                Bundle bundle = new Bundle();
                bundle.putParcelable(PrivacyPolicyModel.PrivacyPolicyData.PREF_KEY, PrivacyPolicyModel.PrivacyPolicyData.CURRENT);
                privacyPolicyFragment.setArguments(bundle);
                privacyPolicyFragment.setCancelable(false);
                privacyPolicyFragment.show(getFragmentManager(), "PrivacyPolicyFragment");
                privacyPolicyFragment.setOnClickAgree(new View.OnClickListener() {
                    public void onClick(View view) {
                        Constants.isTutorialFragmentShowing = false;
                        Constants.privacyPolicyShown = true;
                        TutorialFragment.setTutorialShown();
                        privacyPolicyFragment.dismiss();
                    }
                });
                PrivacyPolicyFragment.setPrivacyPolicyShown();
                Constants.privacyPolicyShown = true;
                return;
            }
            return;
        }
        ViewPager viewPager2 = this.viewPager;
        viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
    }

    @OnClick({2131364544})
    public void onClickSkip(ImageButton imageButton) {
        getActivity().getSupportFragmentManager().popBackStack();
        if (Constants.privacyPolicyShown) {
            return;
        }
        if (!CreateAccountStep5SuccessFragment.atleastOneAccountWasCreatedAfterAppInstallation() || PrivacyPolicyModel.PrivacyPolicyData.PRIVACY_POLICY_UPDATED) {
            final PrivacyPolicyFragment privacyPolicyFragment = new PrivacyPolicyFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(PrivacyPolicyModel.PrivacyPolicyData.PREF_KEY, PrivacyPolicyModel.PrivacyPolicyData.CURRENT);
            privacyPolicyFragment.setArguments(bundle);
            privacyPolicyFragment.setCancelable(false);
            privacyPolicyFragment.show(getFragmentManager(), "PrivacyPolicyFragment");
            privacyPolicyFragment.setOnClickAgree(new View.OnClickListener() {
                public void onClick(View view) {
                    Constants.isTutorialFragmentShowing = false;
                    TutorialFragment.setTutorialShown();
                    privacyPolicyFragment.dismiss();
                }
            });
            PrivacyPolicyFragment.setPrivacyPolicyShown();
            Constants.privacyPolicyShown = true;
        }
    }

    private void loadData() {
        this.viewPager.setAdapter(new ViewPagerAdapter(getActivity(), this.imageTutorials));
        this.indicator.setViewPager(this.viewPager);
    }

    public static void setTutorialShown() {
        SharedPref.getInstance().getSharedPreferenceEditor().putBoolean(TUTORIAL_SHOWN, true).commit();
    }

    public static boolean isTutorialShown() {
        return SharedPref.getInstance().getSharedPreferencesReader().getBoolean(TUTORIAL_SHOWN, false);
    }
}
