package com.jch.racWiFi.iduManagement.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavOptions;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.iduManagement.model.CleaningModeEnum;
import com.jch_hitachi.aircloudglobal.R;

public class CleaningDoneFragment extends GenericFragment implements LifecycleOwner {
    private CleaningModeEnum.CleaningModeDisplayInfoModel mCleaningModeDisplayInfoMode;
    @BindView(2131364233)
    TextView mSubTitle;
    @BindView(2131364232)
    TextView mSubTitleBottom;
    @BindView(2131363997)
    TextView mTitle;
    private Unbinder mUnbinder;

    public static CleaningDoneFragment newInstance() {
        CleaningDoneFragment cleaningDoneFragment = new CleaningDoneFragment();
        cleaningDoneFragment.setNewBundleAsArgument();
        return cleaningDoneFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
            }
        });
        this.mCleaningModeDisplayInfoMode = ((CleaningModeEnum) getArguments().getSerializable(CleaningModeEnum.PARCEL_KEY)).getCleaningModeDisplayInfoModel();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.clean_filter_frame_done, viewGroup, false);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        this.mTitle.setText(this.mCleaningModeDisplayInfoMode.heading);
        this.mSubTitle.setText(this.mCleaningModeDisplayInfoMode.subTitle);
        this.mSubTitle.setText(this.mCleaningModeDisplayInfoMode.subTitleBottom);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                CleaningDoneFragment.this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_cleaningDoneFragment_to_individualIDUControlFragment, CleaningDoneFragment.this.getArguments(), new NavOptions.Builder().setPopUpTo(R.id.individualIDUControlFragment, true).build());
            }
        }, 3000);
        return inflate;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mUnbinder.unbind();
        this.mUnbinder = null;
    }
}
