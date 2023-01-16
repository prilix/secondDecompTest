package com.jch.racWiFi.energyConsumption.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.databinding.GridViewItemPeakHoursMonthsBinding;
import com.jch.racWiFi.energyConsumption.callback.ItemClickListener;
import com.jch.racWiFi.energyConsumption.model.local.TimeOrMonthModel;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;

public class SelectTimeOrSeasonBillingRvAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private ItemClickListener mItemClickListener;
    private ArrayList<TimeOrMonthModel> timeOrSeasonList;

    public SelectTimeOrSeasonBillingRvAdapter(ArrayList<TimeOrMonthModel> arrayList, ItemClickListener itemClickListener) {
        this.timeOrSeasonList = arrayList;
        this.mItemClickListener = itemClickListener;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder((GridViewItemPeakHoursMonthsBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.grid_view_item_peak_hours_months, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        TimeOrMonthModel timeOrMonthModel = this.timeOrSeasonList.get(i);
        myViewHolder.mBinding.layoutPeakHoursMonth.setBackgroundResource(timeOrMonthModel.isSelected() ? R.drawable.button_pressed_red_border : R.drawable.edit_text_background);
        myViewHolder.mBinding.textViewHoursMonth.setText(timeOrMonthModel.getTimeOrSeasonName());
        myViewHolder.itemView.setOnClickListener(new SelectTimeOrSeasonBillingRvAdapter$$ExternalSyntheticLambda0(this, timeOrMonthModel, myViewHolder));
    }

    /* renamed from: lambda$onBindViewHolder$0$com-jch-racWiFi-energyConsumption-adapter-SelectTimeOrSeasonBillingRvAdapter */
    public /* synthetic */ void mo28608x8180bcf3(TimeOrMonthModel timeOrMonthModel, MyViewHolder myViewHolder, View view) {
        timeOrMonthModel.setSelected(!timeOrMonthModel.isSelected());
        myViewHolder.mBinding.layoutPeakHoursMonth.setBackgroundResource(timeOrMonthModel.isSelected() ? R.drawable.button_pressed_red_border : R.drawable.edit_text_background);
        this.mItemClickListener.itemClick(myViewHolder.getAdapterPosition(), false);
    }

    public int getItemCount() {
        return this.timeOrSeasonList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public GridViewItemPeakHoursMonthsBinding mBinding;

        public MyViewHolder(GridViewItemPeakHoursMonthsBinding gridViewItemPeakHoursMonthsBinding) {
            super(gridViewItemPeakHoursMonthsBinding.getRoot());
            this.mBinding = gridViewItemPeakHoursMonthsBinding;
        }
    }
}
