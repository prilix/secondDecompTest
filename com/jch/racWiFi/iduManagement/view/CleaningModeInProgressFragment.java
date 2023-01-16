package com.jch.racWiFi.iduManagement.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.navigation.NavOptions;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.iduManagement.model.CleaningModeEnum;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch_hitachi.aircloudglobal.R;

public class CleaningModeInProgressFragment extends GenericFragment {
    private Observer<DetailedIduModel> individualDetailedIduModelObserver = new Observer<DetailedIduModel>() {
        public void onChanged(DetailedIduModel detailedIduModel) {
            if (CleaningModeInProgressFragment.this.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED) && CleaningModeInProgressFragment.this.mDetailedIduModel != null && detailedIduModel != null && detailedIduModel.equals(CleaningModeInProgressFragment.this.mDetailedIduModel) && !detailedIduModel.iduFrostWashStatus.active) {
                CleaningModeInProgressFragment.this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_cleaningModeInProgressFragment_to_individualIDUControlFragment, CleaningModeInProgressFragment.this.getArguments(), new NavOptions.Builder().setPopUpTo(R.id.individualIDUControlFragment, true).build());
            }
        }
    };
    @BindView(2131362078)
    ImageButton mBack;
    private CleaningModeEnum mCleaningModeEnum;
    @BindView(2131363997)
    TextView mCleaningModeHeading;
    @BindView(2131364004)
    TextView mCleaningModeProgress;
    @BindView(2131364275)
    TextView mCleaningModeWarning;
    /* access modifiers changed from: private */
    public DetailedIduModel mDetailedIduModel;
    @BindView(2131362154)
    Button mHide;
    private Unbinder mUnbinder;

    public static CleaningModeInProgressFragment newInstance() {
        CleaningModeInProgressFragment cleaningModeInProgressFragment = new CleaningModeInProgressFragment();
        cleaningModeInProgressFragment.setNewBundleAsArgument();
        return cleaningModeInProgressFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCleaningModeEnum = (CleaningModeEnum) getArguments().getSerializable(CleaningModeEnum.PARCEL_KEY);
        this.mDetailedIduModel = (DetailedIduModel) getArguments().getParcelable(DetailedIduModel.PARCEL_KEY);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            public void handleOnBackPressed() {
                CleaningModeInProgressFragment.this.OnClickHide((Button) null);
            }
        });
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.clean_filter_frame_progess, viewGroup, false);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        CleaningModeEnum.CleaningModeDisplayInfoModel cleaningModeDisplayInfoModel = this.mCleaningModeEnum.getCleaningModeDisplayInfoModel();
        this.mCleaningModeHeading.setText(cleaningModeDisplayInfoModel.heading);
        this.mCleaningModeWarning.setText(cleaningModeDisplayInfoModel.warning);
        this.mCleaningModeProgress.setText(cleaningModeDisplayInfoModel.progressBottomSubTitle);
        return inflate;
    }

    public void onStart() {
        super.onStart();
        getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIndividualIduUpdateSingleLiveEvent().observe(getViewLifecycleOwner(), this.individualDetailedIduModelObserver);
    }

    public void onStop() {
        super.onStop();
        getCoreActivity().getGlobalViewModelRepository().getIDUsUpdateViewModel().getIndividualIduUpdateSingleLiveEvent().removeObserver(this.individualDetailedIduModelObserver);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mUnbinder.unbind();
        this.mUnbinder = null;
    }

    @OnClick({2131362078})
    public void OnClickBack(ImageButton imageButton) {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_cleaningModeInProgressFragment_to_individualIDUControlFragment, getArguments(), new NavOptions.Builder().setPopUpTo(R.id.individualIDUControlFragment, true).build());
    }

    @OnClick({2131362154})
    public void OnClickHide(Button button) {
        this.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_cleaningModeInProgressFragment_to_individualIDUControlFragment, getArguments(), new NavOptions.Builder().setPopUpTo(R.id.individualIDUControlFragment, true).build());
    }
}
