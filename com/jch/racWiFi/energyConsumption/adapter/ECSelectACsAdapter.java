package com.jch.racWiFi.energyConsumption.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.C1655R;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.customViews.customWidgets.TriStateCheckbox;
import com.jch.racWiFi.energyConsumption.model.response.AllRac;
import com.jch.racWiFi.energyConsumption.viewModel.EnergyConsumptionViewModel;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.tooltip.ClosePolicy;
import com.jch.racWiFi.tooltip.ToolTipGravity;
import com.jch.racWiFi.tooltip.Tooltip;
import com.jch.racWiFi.tooltip.Typefaces;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36737d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001bB\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0012H\u0016J\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0012H\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R.\u0010\t\u001a\"\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\nj\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0018\u0001`\rX\u000e¢\u0006\u0002\n\u0000R\"\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u000fj\n\u0012\u0004\u0012\u00020\u000b\u0018\u0001`\u0010X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, mo36738d2 = {"Lcom/jch/racWiFi/energyConsumption/adapter/ECSelectACsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/jch/racWiFi/energyConsumption/adapter/ECSelectACsAdapter$ECSelectACsViewHolder;", "allRacList", "", "Lcom/jch/racWiFi/energyConsumption/model/response/AllRac;", "energyConsumptionViewModel", "Lcom/jch/racWiFi/energyConsumption/viewModel/EnergyConsumptionViewModel;", "(Ljava/util/List;Lcom/jch/racWiFi/energyConsumption/viewModel/EnergyConsumptionViewModel;)V", "mHashMap", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "mRacIds", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getItemCount", "", "onBindViewHolder", "", "holderEC", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ECSelectACsViewHolder", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: ECSelectACsAdapter.kt */
public final class ECSelectACsAdapter extends RecyclerView.Adapter<ECSelectACsViewHolder> {
    private final List<AllRac> allRacList;
    private final EnergyConsumptionViewModel energyConsumptionViewModel;
    private HashMap<Long, Boolean> mHashMap = new HashMap<>();
    private ArrayList<Long> mRacIds = new ArrayList<>();

    public ECSelectACsAdapter(List<AllRac> list, EnergyConsumptionViewModel energyConsumptionViewModel2) {
        ArrayList<Long> arrayList;
        Intrinsics.checkNotNullParameter(list, "allRacList");
        Intrinsics.checkNotNullParameter(energyConsumptionViewModel2, "energyConsumptionViewModel");
        this.allRacList = list;
        this.energyConsumptionViewModel = energyConsumptionViewModel2;
        for (AllRac next : list) {
            HashMap<Long, Boolean> hashMap = this.mHashMap;
            Intrinsics.checkNotNull(hashMap);
            hashMap.put(Long.valueOf((long) next.getRacId()), Boolean.valueOf(next.isSelected()));
            if (next.isSelected() && (arrayList = this.mRacIds) != null) {
                arrayList.add(Long.valueOf((long) next.getRacId()));
            }
        }
    }

    public ECSelectACsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_select_acs, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "view");
        return new ECSelectACsViewHolder(inflate);
    }

    public void onBindViewHolder(ECSelectACsViewHolder eCSelectACsViewHolder, int i) {
        Intrinsics.checkNotNullParameter(eCSelectACsViewHolder, "holderEC");
        AllRac allRac = this.allRacList.get(i);
        ((TextView) eCSelectACsViewHolder.itemView.findViewById(C1655R.C1658id.text_view_ac_name)).setText(allRac.getRacName());
        ((TriStateCheckbox) eCSelectACsViewHolder.itemView.findViewById(C1655R.C1658id.checkBoxSelectACs)).setChecked(Boolean.valueOf(allRac.isSelected()));
        if (allRac.isEnergyConsumptionSupported()) {
            eCSelectACsViewHolder.itemView.findViewById(C1655R.C1658id.disableViewSelectACs).setVisibility(8);
        } else {
            eCSelectACsViewHolder.itemView.findViewById(C1655R.C1658id.disableViewSelectACs).setVisibility(0);
        }
        ((TriStateCheckbox) eCSelectACsViewHolder.itemView.findViewById(C1655R.C1658id.checkBoxSelectACs)).setOnCheckedChangeListener(new ECSelectACsAdapter$$ExternalSyntheticLambda0(this, allRac));
    }

    /* access modifiers changed from: private */
    /* renamed from: onBindViewHolder$lambda-0  reason: not valid java name */
    public static final void m897onBindViewHolder$lambda0(ECSelectACsAdapter eCSelectACsAdapter, AllRac allRac, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(eCSelectACsAdapter, "this$0");
        Intrinsics.checkNotNullParameter(allRac, "$allRac");
        HashMap<Long, Boolean> hashMap = eCSelectACsAdapter.mHashMap;
        if (hashMap != null) {
            Boolean put = hashMap.put(Long.valueOf((long) allRac.getRacId()), Boolean.valueOf(z));
        }
        if (z) {
            ArrayList<Long> arrayList = eCSelectACsAdapter.mRacIds;
            if (arrayList != null) {
                arrayList.add(Long.valueOf((long) allRac.getRacId()));
            }
        } else {
            ArrayList<Long> arrayList2 = eCSelectACsAdapter.mRacIds;
            if (arrayList2 != null) {
                arrayList2.remove(Long.valueOf((long) allRac.getRacId()));
            }
        }
        eCSelectACsAdapter.energyConsumptionViewModel.setRacIds(eCSelectACsAdapter.mRacIds);
        eCSelectACsAdapter.energyConsumptionViewModel.setRacIdsMap(eCSelectACsAdapter.mHashMap);
    }

    public int getItemCount() {
        return this.allRacList.size();
    }

    @Metadata(mo36737d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¨\u0006\t"}, mo36738d2 = {"Lcom/jch/racWiFi/energyConsumption/adapter/ECSelectACsAdapter$ECSelectACsViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "getClosePolicy", "Lcom/jch/racWiFi/tooltip/ClosePolicy;", "showTooltip", "", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
    /* compiled from: ECSelectACsAdapter.kt */
    public static final class ECSelectACsViewHolder extends RecyclerView.ViewHolder {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ECSelectACsViewHolder(View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View findViewById = view.findViewById(R.id.disableViewSelectACs);
            Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.disableViewSelectACs)");
            findViewById.setOnClickListener(new C1793xbc8bed8b(this, view));
        }

        /* access modifiers changed from: private */
        /* renamed from: _init_$lambda-0  reason: not valid java name */
        public static final void m899_init_$lambda0(ECSelectACsViewHolder eCSelectACsViewHolder, View view, View view2) {
            Intrinsics.checkNotNullParameter(eCSelectACsViewHolder, "this$0");
            Intrinsics.checkNotNullParameter(view, "$view");
            eCSelectACsViewHolder.showTooltip(view);
        }

        private final ClosePolicy getClosePolicy() {
            return new ClosePolicy.Builder().inside(true).outside(true).consume(true).build();
        }

        private final void showTooltip(View view) {
            Context context = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "view.context");
            Tooltip.Builder styleId = new Tooltip.Builder(context).anchor(view, 0, 0, false).styleId(Integer.valueOf(R.style.ToolTipLayoutDefaultStyle));
            String string = Constants.CC.getResource(view.getContext()).getString(R.string.energyConsumption_lbl_model_is_not_supported);
            Intrinsics.checkNotNullExpressionValue(string, "getResource(view.context…l_model_is_not_supported)");
            Tooltip.Builder text = styleId.text((CharSequence) string);
            Typefaces typefaces = Typefaces.INSTANCE;
            Context context2 = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "view.context");
            text.typeface(typefaces.get(context2, "fonts/SansPro-Regular.ttc")).maxWidth((int) (((float) view.getContext().getResources().getDisplayMetrics().widthPixels) * 0.8f)).arrow(false).floatingAnimation(Tooltip.Animation.Companion.getSLOW()).closePolicy(getClosePolicy()).showDuration(com.jch.racWiFi.Constants.VALID_WIFI_CONNECTION_CHECK_TIMEOUT).overlay(false).create().show(view, Tooltip.Gravity.valueOf(ToolTipGravity.CENTER.name()), true);
        }
    }
}
