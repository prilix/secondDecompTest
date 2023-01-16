package com.jch.racWiFi.userOnboarding.view.uiComponents.recyclerAdapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.NavigationTransitionKeyValues;
import com.jch.racWiFi.Values;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.userOnboarding.model.IduDetailsModel;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.Collections;

public class IduDetailsAdapter extends ListAdapter<IduDetailsModel, IduDetailsViewHolder> {
    private static final DiffUtil.ItemCallback<IduDetailsModel> IDU_DETAILS_DIFF_CALLBACK = new DiffUtil.ItemCallback<IduDetailsModel>() {
        public boolean areItemsTheSame(IduDetailsModel iduDetailsModel, IduDetailsModel iduDetailsModel2) {
            return iduDetailsModel.getId().equals(iduDetailsModel2.getId());
        }

        public boolean areContentsTheSame(IduDetailsModel iduDetailsModel, IduDetailsModel iduDetailsModel2) {
            return iduDetailsModel.equals(iduDetailsModel2);
        }
    };
    /* access modifiers changed from: private */
    public final Fragment fragment;
    ArrayList<IduDetailsModel> iduDetailsModels = new ArrayList<>();

    public IduDetailsAdapter(Fragment fragment2) {
        super(IDU_DETAILS_DIFF_CALLBACK);
        this.fragment = fragment2;
    }

    public IduDetailsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new IduDetailsViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_items_manage_devices, viewGroup, false));
    }

    public void setIduDetailsModels(ArrayList<IduDetailsModel> arrayList) {
        this.iduDetailsModels.clear();
        this.iduDetailsModels.addAll(arrayList);
        Collections.sort(this.iduDetailsModels, IduDetailsAdapter$$ExternalSyntheticLambda0.INSTANCE);
        notifyDataSetChanged();
    }

    public void onBindViewHolder(IduDetailsViewHolder iduDetailsViewHolder, int i) {
        iduDetailsViewHolder.setIduDetailsModel(this.iduDetailsModels.get(i));
    }

    public int getItemCount() {
        return this.iduDetailsModels.size();
    }

    public class IduDetailsViewHolder extends RecyclerView.ViewHolder {
        IduDetailsModel iduDetailsModel;
        NavController navController;
        TextView tvVendorThingID;

        public void setIduDetailsModel(IduDetailsModel iduDetailsModel2) {
            this.iduDetailsModel = iduDetailsModel2;
            this.tvVendorThingID.setText(iduDetailsModel2.getName());
        }

        public IduDetailsViewHolder(View view) {
            super(view);
            this.navController = Navigation.findNavController(IduDetailsAdapter.this.fragment.requireActivity(), R.id.nav_host_fragment);
            this.tvVendorThingID = (TextView) view.findViewById(R.id.text_view_device_name);
            view.setOnClickListener(new IduDetailsAdapter$IduDetailsViewHolder$$ExternalSyntheticLambda0(this));
        }

        /* renamed from: lambda$new$0$com-jch-racWiFi-userOnboarding-view-uiComponents-recyclerAdapters-IduDetailsAdapter$IduDetailsViewHolder */
        public /* synthetic */ void mo33499x391d1221(View view) {
            this.navController.getGraph().addArgument(Values.IDU_DETAILS_KEY, new NavArgument.Builder().setDefaultValue(this.iduDetailsModel).build());
            Bundle bundle = new Bundle();
            bundle.putInt(NavigationTransitionKeyValues.NAVIGATION_FROM, 1008);
            bundle.putSerializable(IduDetailsModel.LIST_ARCEL_KEY, IduDetailsAdapter.this.iduDetailsModels);
            this.navController.navigate((int) R.id.action_manageDevicesFragment_to_deviceDetailsFragment, bundle);
        }
    }
}
