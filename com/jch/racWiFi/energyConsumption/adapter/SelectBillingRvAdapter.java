package com.jch.racWiFi.energyConsumption.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.databinding.RecyclerViewItemBillingStructureBinding;
import com.jch_hitachi.aircloudglobal.R;

public class SelectBillingRvAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private String[] billingStructureArray;
    /* access modifiers changed from: private */
    public int checkedPosition = -1;

    public SelectBillingRvAdapter(String[] strArr) {
        this.billingStructureArray = strArr;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder((RecyclerViewItemBillingStructureBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.recycler_view_item_billing_structure, viewGroup, false));
    }

    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        final RecyclerViewItemBillingStructureBinding recyclerViewItemBillingStructureBinding = myViewHolder.mBinding;
        int i2 = this.checkedPosition;
        if (i2 == -1) {
            recyclerItemSelection(recyclerViewItemBillingStructureBinding.imageViewTickIcon, 4, myViewHolder.itemView, R.color.transparent_white_color);
        } else if (i2 == myViewHolder.getAdapterPosition()) {
            recyclerItemSelection(recyclerViewItemBillingStructureBinding.imageViewTickIcon, 0, myViewHolder.itemView, R.color.dark_grey);
        } else {
            recyclerItemSelection(recyclerViewItemBillingStructureBinding.imageViewTickIcon, 4, myViewHolder.itemView, R.color.transparent_white_color);
        }
        String str = this.billingStructureArray[i];
        if (str != null && !str.isEmpty()) {
            recyclerViewItemBillingStructureBinding.textViewBillingName.setText(str);
        }
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                recyclerViewItemBillingStructureBinding.imageViewTickIcon.setVisibility(0);
                myViewHolder.itemView.setBackgroundResource(R.color.dark_grey);
                if (SelectBillingRvAdapter.this.checkedPosition != myViewHolder.getAdapterPosition()) {
                    SelectBillingRvAdapter selectBillingRvAdapter = SelectBillingRvAdapter.this;
                    selectBillingRvAdapter.notifyItemChanged(selectBillingRvAdapter.checkedPosition);
                    SelectBillingRvAdapter.this.checkedPosition = myViewHolder.getAdapterPosition();
                }
            }
        });
    }

    private void recyclerItemSelection(ImageView imageView, int i, View view, int i2) {
        imageView.setVisibility(i);
        view.setBackgroundResource(i2);
    }

    public int getItemCount() {
        return this.billingStructureArray.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RecyclerViewItemBillingStructureBinding mBinding;

        public MyViewHolder(RecyclerViewItemBillingStructureBinding recyclerViewItemBillingStructureBinding) {
            super(recyclerViewItemBillingStructureBinding.getRoot());
            this.mBinding = recyclerViewItemBillingStructureBinding;
        }
    }

    public int getCheckedPosition() {
        return this.checkedPosition;
    }

    public void setCheckedPosition(int i) {
        this.checkedPosition = i;
    }
}
