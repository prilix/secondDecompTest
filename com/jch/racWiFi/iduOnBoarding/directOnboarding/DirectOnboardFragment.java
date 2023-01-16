package com.jch.racWiFi.iduOnBoarding.directOnboarding;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.iduManagement.model.DetailedIduModel;
import com.jch_hitachi.aircloudglobal.R;
import java.util.List;
import p015me.everything.android.p016ui.overscroll.OverScrollDecoratorHelper;

public class DirectOnboardFragment extends GenericFragment implements View.OnClickListener {
    private List<DirectOnBoardingModel> directOnBoardingModels;
    @BindView(2131362326)
    public RecyclerView mRecyclerView;
    private Unbinder unbinder;

    public void onClick(View view) {
    }

    static class DeviceRecyclerViewAdapter extends RecyclerView.Adapter<DeviceViewHolder> {
        private Context context;
        private List<DirectOnBoardingModel> deviceRecyclerItemModelItemList;
        /* access modifiers changed from: private */
        public NavController mNavController;
        private DetailedIduModel mSelectedDevice;

        public class DeviceViewHolder_ViewBinding implements Unbinder {
            private DeviceViewHolder target;
            private View view7f0a0597;

            public DeviceViewHolder_ViewBinding(final DeviceViewHolder deviceViewHolder, View view) {
                this.target = deviceViewHolder;
                deviceViewHolder.mDeviceName = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_device_name, "field 'mDeviceName'", TextView.class);
                View findRequiredView = C0840Utils.findRequiredView(view, R.id.layout_manage_devices, "field 'mOuterLayout' and method 'onClickItem'");
                deviceViewHolder.mOuterLayout = (ConstraintLayout) C0840Utils.castView(findRequiredView, R.id.layout_manage_devices, "field 'mOuterLayout'", ConstraintLayout.class);
                this.view7f0a0597 = findRequiredView;
                findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                    public void doClick(View view) {
                        deviceViewHolder.onClickItem((ConstraintLayout) C0840Utils.castParam(view, "doClick", 0, "onClickItem", 0, ConstraintLayout.class));
                    }
                });
            }

            public void unbind() {
                DeviceViewHolder deviceViewHolder = this.target;
                if (deviceViewHolder != null) {
                    this.target = null;
                    deviceViewHolder.mDeviceName = null;
                    deviceViewHolder.mOuterLayout = null;
                    this.view7f0a0597.setOnClickListener((View.OnClickListener) null);
                    this.view7f0a0597 = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        public DetailedIduModel getSelectedDevice() {
            return this.mSelectedDevice;
        }

        private DeviceRecyclerViewAdapter(Context context2, List<DirectOnBoardingModel> list, NavController navController) {
            this.context = context2;
            this.deviceRecyclerItemModelItemList = list;
            this.mNavController = navController;
        }

        public DeviceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new DeviceViewHolder(LayoutInflater.from(this.context).inflate(R.layout.recycler_view_items_manage_devices, viewGroup, false));
        }

        public void onBindViewHolder(DeviceViewHolder deviceViewHolder, int i) {
            deviceViewHolder.bind(this.deviceRecyclerItemModelItemList.get(i));
        }

        public int getItemCount() {
            return this.deviceRecyclerItemModelItemList.size();
        }

        class DeviceViewHolder extends RecyclerView.ViewHolder {
            @BindView(2131364111)
            TextView mDeviceName;
            @BindView(2131363223)
            ConstraintLayout mOuterLayout;

            public DeviceViewHolder(View view) {
                super(view);
                ButterKnife.bind((Object) this, view);
            }

            @OnClick({2131363223})
            public void onClickItem(ConstraintLayout constraintLayout) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(DirectOnBoardingModel.PARCEL_KEY, (DirectOnBoardingModel) constraintLayout.getTag());
                DeviceRecyclerViewAdapter.this.mNavController.navigate((int) R.id.action_directOnboardingFragment_to_qrScanFragment, bundle);
            }

            public void bind(DirectOnBoardingModel directOnBoardingModel) {
                this.mDeviceName.setText(directOnBoardingModel.getVendorThing());
                this.mOuterLayout.setTag(directOnBoardingModel);
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.direct_onboard_main_frame, viewGroup, false);
        this.unbinder = ButterKnife.bind((Object) this, inflate);
        this.directOnBoardingModels = DirectOnBoardingModel.getDirectOnBoardingModelList();
        return inflate;
    }

    public void onStart() {
        super.onStart();
        initRecyclerView();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    private void initRecyclerView() {
        DeviceRecyclerViewAdapter deviceRecyclerViewAdapter = new DeviceRecyclerViewAdapter(getActivity(), this.directOnBoardingModels, this.mFragmentToActivityCallback.getNavController());
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setAdapter(deviceRecyclerViewAdapter);
        OverScrollDecoratorHelper.setUpOverScroll(this.mRecyclerView, 0);
    }
}
