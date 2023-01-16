package com.jch.racWiFi.energyConsumption.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.databinding.RecyclerViewItemCurrencyBinding;
import com.jch.racWiFi.energyConsumption.model.local.CurrencyModel;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.Iterator;

public class SelectCurrencyRvAdapter extends RecyclerView.Adapter<MyViewHolder> implements Filterable {
    /* access modifiers changed from: private */
    public int checkedPosition = -1;
    /* access modifiers changed from: private */
    public ArrayList<CurrencyModel> currencyList;
    public ArrayList<CurrencyModel> currencyListFiltered;
    private Filter fRecords;

    public SelectCurrencyRvAdapter(ArrayList<CurrencyModel> arrayList) {
        this.currencyList = arrayList;
        this.currencyListFiltered = arrayList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder((RecyclerViewItemCurrencyBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.recycler_view_item_currency, viewGroup, false));
    }

    public void onBindViewHolder(final MyViewHolder myViewHolder, int i) {
        final RecyclerViewItemCurrencyBinding recyclerViewItemCurrencyBinding = myViewHolder.mBinding;
        int i2 = this.checkedPosition;
        if (i2 == -1) {
            recyclerItemSelection(recyclerViewItemCurrencyBinding.imageViewTickMark, 4, myViewHolder.itemView, R.color.transparent_white_color);
        } else if (i2 == myViewHolder.getAdapterPosition()) {
            recyclerItemSelection(recyclerViewItemCurrencyBinding.imageViewTickMark, 0, myViewHolder.itemView, R.color.dark_grey);
        } else {
            recyclerItemSelection(recyclerViewItemCurrencyBinding.imageViewTickMark, 4, myViewHolder.itemView, R.color.transparent_white_color);
        }
        CurrencyModel currencyModel = this.currencyListFiltered.get(i);
        recyclerViewItemCurrencyBinding.textViewCurrencyCode.setText(currencyModel.getCode());
        recyclerViewItemCurrencyBinding.textViewCurrencyValue.setText(currencyModel.getName());
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                recyclerViewItemCurrencyBinding.imageViewTickMark.setVisibility(0);
                myViewHolder.itemView.setBackgroundResource(R.color.dark_grey);
                if (SelectCurrencyRvAdapter.this.checkedPosition != myViewHolder.getAdapterPosition()) {
                    SelectCurrencyRvAdapter selectCurrencyRvAdapter = SelectCurrencyRvAdapter.this;
                    selectCurrencyRvAdapter.notifyItemChanged(selectCurrencyRvAdapter.checkedPosition);
                    SelectCurrencyRvAdapter.this.checkedPosition = myViewHolder.getAdapterPosition();
                }
            }
        });
    }

    public int getItemCount() {
        return this.currencyListFiltered.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        RecyclerViewItemCurrencyBinding mBinding;

        public MyViewHolder(RecyclerViewItemCurrencyBinding recyclerViewItemCurrencyBinding) {
            super(recyclerViewItemCurrencyBinding.getRoot());
            this.mBinding = recyclerViewItemCurrencyBinding;
        }
    }

    private void recyclerItemSelection(ImageView imageView, int i, View view, int i2) {
        imageView.setVisibility(i);
        view.setBackgroundResource(i2);
    }

    public Filter getFilter() {
        if (this.fRecords == null) {
            this.fRecords = new RecordFilter();
        }
        return this.fRecords;
    }

    private class RecordFilter extends Filter {
        private RecordFilter() {
        }

        /* access modifiers changed from: protected */
        public Filter.FilterResults performFiltering(CharSequence charSequence) {
            if (charSequence.toString().isEmpty()) {
                SelectCurrencyRvAdapter selectCurrencyRvAdapter = SelectCurrencyRvAdapter.this;
                selectCurrencyRvAdapter.currencyListFiltered = selectCurrencyRvAdapter.currencyList;
            } else {
                ArrayList<CurrencyModel> arrayList = new ArrayList<>();
                Iterator it = SelectCurrencyRvAdapter.this.currencyList.iterator();
                while (it.hasNext()) {
                    CurrencyModel currencyModel = (CurrencyModel) it.next();
                    String trim = charSequence.toString().toLowerCase().trim();
                    if (currencyModel.getName().toLowerCase().trim().contains(trim) || currencyModel.getCode().toLowerCase().trim().contains(trim)) {
                        arrayList.add(currencyModel);
                    }
                }
                SelectCurrencyRvAdapter.this.currencyListFiltered = arrayList;
            }
            Filter.FilterResults filterResults = new Filter.FilterResults();
            filterResults.values = SelectCurrencyRvAdapter.this.currencyListFiltered;
            return filterResults;
        }

        /* access modifiers changed from: protected */
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            SelectCurrencyRvAdapter.this.currencyListFiltered = (ArrayList) filterResults.values;
            SelectCurrencyRvAdapter.this.notifyDataSetChanged();
        }
    }

    public int getCheckedPosition() {
        return this.checkedPosition;
    }

    public void setCheckedPosition(int i) {
        this.checkedPosition = i;
    }
}
