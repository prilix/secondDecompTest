package com.jch.racWiFi.userOnboarding.network.wifiHelper;

import android.net.wifi.ScanResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.List;

public class WifiScanAdapter extends ListAdapter<ScanResult, WifiViewHolder> {
    public static DiffUtil.ItemCallback<ScanResult> SCAN_ITEM_DIFF_CALLBACK = new DiffUtil.ItemCallback<ScanResult>() {
        public boolean areItemsTheSame(ScanResult scanResult, ScanResult scanResult2) {
            return scanResult.SSID.equals(scanResult2.SSID);
        }

        public boolean areContentsTheSame(ScanResult scanResult, ScanResult scanResult2) {
            return scanResult.SSID.equals(scanResult2.SSID);
        }
    };
    List<ScanResult> scanResults = new ArrayList();
    WifiNetworkSelectionCallBack wifiNetworkSelectionCallBack;

    public interface WifiNetworkSelectionCallBack {
        void onWifiNetworkSelected(ScanResult scanResult);
    }

    public WifiScanAdapter(WifiNetworkSelectionCallBack wifiNetworkSelectionCallBack2) {
        super(SCAN_ITEM_DIFF_CALLBACK);
        this.wifiNetworkSelectionCallBack = wifiNetworkSelectionCallBack2;
    }

    public void setScanResults(List<ScanResult> list) {
        this.scanResults = list;
        submitList(list);
    }

    public WifiViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new WifiViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_wifi_item, viewGroup, false));
    }

    public void onBindViewHolder(WifiViewHolder wifiViewHolder, int i) {
        ScanResult scanResult = this.scanResults.get(i);
        wifiViewHolder.scanResult = scanResult;
        wifiViewHolder.wifiName.setText(scanResult.SSID);
    }

    public int getItemCount() {
        return this.scanResults.size();
    }

    public class WifiViewHolder extends RecyclerView.ViewHolder {
        public ScanResult scanResult;
        public TextView wifiName;

        public WifiViewHolder(View view) {
            super(view);
            view.setOnClickListener(new WifiScanAdapter$WifiViewHolder$$ExternalSyntheticLambda0(this));
            this.wifiName = (TextView) view.findViewById(R.id.tv_wifi_network_name);
        }

        /* renamed from: lambda$new$0$com-jch-racWiFi-userOnboarding-network-wifiHelper-WifiScanAdapter$WifiViewHolder */
        public /* synthetic */ void mo33419x86e70a54(View view) {
            WifiScanAdapter.this.wifiNetworkSelectionCallBack.onWifiNetworkSelected(this.scanResult);
        }
    }
}
