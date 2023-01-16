package com.jch.racWiFi.energyConsumption.adapter;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.databinding.RecyclerViewItemsAddProgressiveUnitBinding;
import com.jch.racWiFi.energyConsumption.callback.EditTextValueChangeListener;
import com.jch.racWiFi.energyConsumption.model.request.budgetSetUp.ProgressiveUnitModel;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;

public class ProgressiveBillUnitRvAdapter extends RecyclerView.Adapter<MyViewHolder> {
    /* access modifiers changed from: private */
    public EditTextValueChangeListener mChangeListener;
    /* access modifiers changed from: private */
    public InputFilter[] mFilters;
    private ArrayList<ProgressiveUnitModel> progressiveUnitBillList;
    TextWatcher textWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            ProgressiveBillUnitRvAdapter.this.mChangeListener.valueChange();
        }
    };

    public ProgressiveBillUnitRvAdapter(ArrayList<ProgressiveUnitModel> arrayList, InputFilter[] inputFilterArr, EditTextValueChangeListener editTextValueChangeListener) {
        this.progressiveUnitBillList = arrayList;
        this.mFilters = inputFilterArr;
        this.mChangeListener = editTextValueChangeListener;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder((RecyclerViewItemsAddProgressiveUnitBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.recycler_view_items_add_progressive_unit, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        RecyclerViewItemsAddProgressiveUnitBinding recyclerViewItemsAddProgressiveUnitBinding = myViewHolder.mBinding;
        ProgressiveUnitModel progressiveUnitModel = this.progressiveUnitBillList.get(i);
        recyclerViewItemsAddProgressiveUnitBinding.textViewFromUnitPrice.setText(String.valueOf(progressiveUnitModel.getFrom()));
        recyclerViewItemsAddProgressiveUnitBinding.editTextToUnit.setText(String.valueOf((int) progressiveUnitModel.getTo()));
        recyclerViewItemsAddProgressiveUnitBinding.editTextToUnit.addTextChangedListener(this.textWatcher);
        recyclerViewItemsAddProgressiveUnitBinding.editTextUnitPrice.setText(String.valueOf(progressiveUnitModel.getUnitPrice()));
        recyclerViewItemsAddProgressiveUnitBinding.editTextUnitPrice.addTextChangedListener(this.textWatcher);
        recyclerViewItemsAddProgressiveUnitBinding.textViewCurrencyCode.setText(progressiveUnitModel.getCurrencyCode());
    }

    public int getItemCount() {
        return this.progressiveUnitBillList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        RecyclerViewItemsAddProgressiveUnitBinding mBinding;

        public MyViewHolder(RecyclerViewItemsAddProgressiveUnitBinding recyclerViewItemsAddProgressiveUnitBinding) {
            super(recyclerViewItemsAddProgressiveUnitBinding.getRoot());
            this.mBinding = recyclerViewItemsAddProgressiveUnitBinding;
            recyclerViewItemsAddProgressiveUnitBinding.editTextToUnit.setFilters(ProgressiveBillUnitRvAdapter.this.mFilters);
            this.mBinding.editTextUnitPrice.setFilters(ProgressiveBillUnitRvAdapter.this.mFilters);
        }
    }
}
