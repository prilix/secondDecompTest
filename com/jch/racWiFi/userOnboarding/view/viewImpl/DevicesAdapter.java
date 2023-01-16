package com.jch.racWiFi.userOnboarding.view.viewImpl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.userOnboarding.view.DeviceName;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.List;

public class DevicesAdapter extends RecyclerView.Adapter<DevicesViewHolder> implements Filterable {
    /* access modifiers changed from: private */
    public List<String> deviceList;
    public List<String> deviceListFiltered;
    /* access modifiers changed from: private */
    public DeviceName mDeviceName;

    public DevicesAdapter(List<String> list, DeviceName deviceName) {
        this.deviceList = list;
        this.deviceListFiltered = list;
        this.mDeviceName = deviceName;
    }

    public class DevicesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView device;
        public ConstraintLayout layoutManageDevices;

        public DevicesViewHolder(View view) {
            super(view);
            this.device = (TextView) view.findViewById(R.id.text_view_device_name);
            ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.layout_manage_devices);
            this.layoutManageDevices = constraintLayout;
            constraintLayout.setOnClickListener(this);
        }

        public void onClick(View view) {
            String str = DevicesAdapter.this.deviceListFiltered.get(getAdapterPosition());
            if (str != null && !str.isEmpty()) {
                DevicesAdapter.this.mDeviceName.getDeviceName(str);
            }
        }
    }

    public DevicesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new DevicesViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyler_view_items_devices_new, viewGroup, false));
    }

    public void onBindViewHolder(DevicesViewHolder devicesViewHolder, int i) {
        devicesViewHolder.device.setText(this.deviceListFiltered.get(i));
    }

    public int getItemCount() {
        return this.deviceListFiltered.size();
    }

    public Filter getFilter() {
        return new Filter() {
            /* access modifiers changed from: protected */
            public Filter.FilterResults performFiltering(CharSequence charSequence) {
                String charSequence2 = charSequence.toString();
                if (charSequence2.isEmpty()) {
                    DevicesAdapter devicesAdapter = DevicesAdapter.this;
                    devicesAdapter.deviceListFiltered = devicesAdapter.deviceList;
                } else {
                    ArrayList arrayList = new ArrayList();
                    for (String str : DevicesAdapter.this.deviceList) {
                        if (str.toLowerCase().contains(charSequence2.toLowerCase())) {
                            arrayList.add(str);
                        }
                    }
                    DevicesAdapter.this.deviceListFiltered = arrayList;
                }
                Filter.FilterResults filterResults = new Filter.FilterResults();
                filterResults.values = DevicesAdapter.this.deviceListFiltered;
                return filterResults;
            }

            /* access modifiers changed from: protected */
            public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
                DevicesAdapter.this.deviceListFiltered = (ArrayList) filterResults.values;
                DevicesAdapter.this.notifyDataSetChanged();
            }
        };
    }
}
