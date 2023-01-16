package com.jch.racWiFi.energyConsumption.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.databinding.SingleRowEnergyConsumptionBinding;
import com.jch.racWiFi.energyConsumption.callback.ItemClickListener;
import com.jch.racWiFi.energyConsumption.model.local.EnergyCostIndivisualRacData;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.tooltip.ClosePolicy;
import com.jch.racWiFi.tooltip.ToolTipGravity;
import com.jch.racWiFi.tooltip.Tooltip;
import com.jch.racWiFi.tooltip.Typefaces;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import kotlin.Unit;

public class EnergyConsumptionAcListingAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private final ArrayList<EnergyCostIndivisualRacData> indivisualRacDataList;
    private final ItemClickListener itemClickListener;
    private final Activity mActivity;

    static /* synthetic */ Unit lambda$showToolTip$2(Tooltip tooltip) {
        return null;
    }

    static /* synthetic */ Unit lambda$showToolTip$3(Tooltip tooltip) {
        return null;
    }

    static /* synthetic */ Unit lambda$showToolTip$4(Tooltip tooltip) {
        return null;
    }

    public EnergyConsumptionAcListingAdapter(Activity activity, ArrayList<EnergyCostIndivisualRacData> arrayList, ItemClickListener itemClickListener2) {
        this.mActivity = activity;
        this.indivisualRacDataList = arrayList;
        this.itemClickListener = itemClickListener2;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder((SingleRowEnergyConsumptionBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.single_row_energy_consumption, viewGroup, false));
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        EnergyCostIndivisualRacData energyCostIndivisualRacData = this.indivisualRacDataList.get(i);
        SingleRowEnergyConsumptionBinding singleRowEnergyConsumptionBinding = myViewHolder.mBinding;
        if (energyCostIndivisualRacData.isRacDisabled()) {
            singleRowEnergyConsumptionBinding.viewDisableLayout.setVisibility(0);
            singleRowEnergyConsumptionBinding.energyUsageTextView.setText("- -");
            TextView textView = singleRowEnergyConsumptionBinding.racUsageCostTextView;
            textView.setText(energyCostIndivisualRacData.getCurrenySymbol() + "- -");
        } else {
            singleRowEnergyConsumptionBinding.viewDisableLayout.setVisibility(8);
            singleRowEnergyConsumptionBinding.energyUsageTextView.setText(energyCostIndivisualRacData.getEnergyConsumed() + " " + this.mActivity.getString(R.string.energy_unit));
            singleRowEnergyConsumptionBinding.racUsageCostTextView.setText(energyCostIndivisualRacData.getCurrenySymbol() + " " + energyCostIndivisualRacData.getCost());
        }
        singleRowEnergyConsumptionBinding.racNameTextView.setText(energyCostIndivisualRacData.getRacName());
        myViewHolder.itemView.setOnClickListener(new EnergyConsumptionAcListingAdapter$$ExternalSyntheticLambda0(this, myViewHolder));
        singleRowEnergyConsumptionBinding.viewDisableLayout.setOnClickListener(new EnergyConsumptionAcListingAdapter$$ExternalSyntheticLambda1(this, energyCostIndivisualRacData, singleRowEnergyConsumptionBinding));
    }

    /* renamed from: lambda$onBindViewHolder$0$com-jch-racWiFi-energyConsumption-adapter-EnergyConsumptionAcListingAdapter */
    public /* synthetic */ void mo28588xcf339d8c(MyViewHolder myViewHolder, View view) {
        this.itemClickListener.itemClick(myViewHolder.getAdapterPosition(), false);
    }

    /* renamed from: lambda$onBindViewHolder$1$com-jch-racWiFi-energyConsumption-adapter-EnergyConsumptionAcListingAdapter */
    public /* synthetic */ void mo28589xd0021c0d(EnergyCostIndivisualRacData energyCostIndivisualRacData, SingleRowEnergyConsumptionBinding singleRowEnergyConsumptionBinding, View view) {
        if (!energyCostIndivisualRacData.isAllRacDisabled()) {
            showToolTip(singleRowEnergyConsumptionBinding);
        }
    }

    private void showToolTip(SingleRowEnergyConsumptionBinding singleRowEnergyConsumptionBinding) {
        Tooltip create = new Tooltip.Builder(singleRowEnergyConsumptionBinding.getRoot().getContext()).anchor(singleRowEnergyConsumptionBinding.viewDisableLayout, 0, 0, false).styleId(Integer.valueOf(R.style.ToolTipLayoutDefaultStyle)).text((CharSequence) Constants.CC.getResource(singleRowEnergyConsumptionBinding.getRoot().getContext()).getString(R.string.energyConsumption_lbl_model_is_not_supported)).typeface(Typefaces.INSTANCE.get(singleRowEnergyConsumptionBinding.getRoot().getContext(), "fonts/SansPro-Regular.ttc")).maxWidth(singleRowEnergyConsumptionBinding.getRoot().getContext().getResources().getDisplayMetrics().widthPixels).arrow(false).floatingAnimation(Tooltip.Animation.Companion.getDEFAULT()).closePolicy(getClosePolicy()).showDuration(3000).overlay(false).create();
        create.doOnHidden(EnergyConsumptionAcListingAdapter$$ExternalSyntheticLambda2.INSTANCE);
        create.doOnFailure(EnergyConsumptionAcListingAdapter$$ExternalSyntheticLambda3.INSTANCE);
        create.doOnShown(EnergyConsumptionAcListingAdapter$$ExternalSyntheticLambda4.INSTANCE);
        create.show(singleRowEnergyConsumptionBinding.viewDisableLayout, Tooltip.Gravity.valueOf(ToolTipGravity.CENTER.name()), true);
    }

    private ClosePolicy getClosePolicy() {
        return new ClosePolicy.Builder().inside(true).outside(true).consume(true).build();
    }

    public int getItemCount() {
        return this.indivisualRacDataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        SingleRowEnergyConsumptionBinding mBinding;

        public MyViewHolder(SingleRowEnergyConsumptionBinding singleRowEnergyConsumptionBinding) {
            super(singleRowEnergyConsumptionBinding.getRoot());
            this.mBinding = singleRowEnergyConsumptionBinding;
        }
    }
}
