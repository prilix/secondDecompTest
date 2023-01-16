package com.jch.racWiFi.energyConsumption.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import com.jch.racWiFi.C1655R;
import com.jch.racWiFi.GenericFragment;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.databinding.FragmentEcHomeBinding;
import com.jch.racWiFi.p010di.util.Constants;
import com.jch.racWiFi.tooltip.ClosePolicy;
import com.jch.racWiFi.tooltip.ToolTipGravity;
import com.jch.racWiFi.tooltip.Tooltip;
import com.jch.racWiFi.tooltip.Typefaces;
import com.jch.racWiFi.userManagement.model.FamilyGroupList;
import com.jch_hitachi.aircloudglobal.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36737d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J$\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u001a\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006H\u0002¨\u0006\u0011"}, mo36738d2 = {"Lcom/jch/racWiFi/energyConsumption/fragment/ECHomeFragment;", "Lcom/jch/racWiFi/GenericFragment;", "()V", "getClosePolicy", "Lcom/jch/racWiFi/tooltip/ClosePolicy;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "showTooltip", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: ECHomeFragment.kt */
public final class ECHomeFragment extends GenericFragment {
    public void _$_clearFindViewByIdCache() {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        View root = ((FragmentEcHomeBinding) DataBindingUtil.inflate(layoutInflater, R.layout.fragment_ec_home, viewGroup, false)).getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        View view2 = getView();
        View view3 = null;
        ((ImageButton) (view2 == null ? null : view2.findViewById(C1655R.C1658id.back_button))).setVisibility(4);
        View view4 = getView();
        boolean z = false;
        ((ImageButton) (view4 == null ? null : view4.findViewById(C1655R.C1658id.image_button_menu))).setVisibility(0);
        View view5 = getView();
        Button button = (Button) (view5 == null ? null : view5.findViewById(C1655R.C1658id.button_select_acs));
        if (FamilyGroupList.getCurrentFamily().role.f480id == 1) {
            z = true;
        }
        button.setEnabled(z);
        View view6 = getView();
        ((ImageButton) (view6 == null ? null : view6.findViewById(C1655R.C1658id.image_button_menu))).setOnClickListener(new ECHomeFragment$$ExternalSyntheticLambda1(this));
        View view7 = getView();
        ((ImageButton) (view7 == null ? null : view7.findViewById(C1655R.C1658id.back_button))).setOnClickListener(new ECHomeFragment$$ExternalSyntheticLambda2(this));
        View view8 = getView();
        ((Button) (view8 == null ? null : view8.findViewById(C1655R.C1658id.button_select_acs))).setOnDisableClickListener(new ECHomeFragment$$ExternalSyntheticLambda3(this, view));
        View view9 = getView();
        if (view9 != null) {
            view3 = view9.findViewById(C1655R.C1658id.button_select_acs);
        }
        ((Button) view3).setOnClickListener(new ECHomeFragment$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    /* renamed from: onViewCreated$lambda-0  reason: not valid java name */
    public static final void m915onViewCreated$lambda0(ECHomeFragment eCHomeFragment, View view) {
        Intrinsics.checkNotNullParameter(eCHomeFragment, "this$0");
        eCHomeFragment.iDrawerMenuFunctions.openMenuDrawer();
    }

    /* access modifiers changed from: private */
    /* renamed from: onViewCreated$lambda-1  reason: not valid java name */
    public static final void m916onViewCreated$lambda1(ECHomeFragment eCHomeFragment, View view) {
        Intrinsics.checkNotNullParameter(eCHomeFragment, "this$0");
        eCHomeFragment.mFragmentToActivityCallback.getNavController().navigateUp();
    }

    /* access modifiers changed from: private */
    /* renamed from: onViewCreated$lambda-2  reason: not valid java name */
    public static final void m917onViewCreated$lambda2(ECHomeFragment eCHomeFragment, View view, View view2) {
        Intrinsics.checkNotNullParameter(eCHomeFragment, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        eCHomeFragment.showTooltip(view);
    }

    /* access modifiers changed from: private */
    /* renamed from: onViewCreated$lambda-3  reason: not valid java name */
    public static final void m918onViewCreated$lambda3(ECHomeFragment eCHomeFragment, View view) {
        Intrinsics.checkNotNullParameter(eCHomeFragment, "this$0");
        Bundle bundle = new Bundle();
        bundle.putInt("from", 1);
        eCHomeFragment.mFragmentToActivityCallback.getNavController().navigate((int) R.id.action_ecHomeFragment_to_ecSelectACsFragment, bundle);
    }

    private final void showTooltip(View view) {
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "view.context");
        Tooltip.Builder styleId = new Tooltip.Builder(context).anchor(view, 0, 0, false).styleId(Integer.valueOf(R.style.ToolTipLayoutDefaultStyle));
        String string = Constants.CC.getResource(view.getContext()).getString(R.string.ec_you_do_not_have);
        Intrinsics.checkNotNullExpressionValue(string, "getResource(view.context…tring.ec_you_do_not_have)");
        Tooltip.Builder text = styleId.text((CharSequence) string);
        Typefaces typefaces = Typefaces.INSTANCE;
        Context context2 = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "view.context");
        text.typeface(typefaces.get(context2, "fonts/SansPro-Regular.ttc")).maxWidth((int) (((float) view.getContext().getResources().getDisplayMetrics().widthPixels) * 0.8f)).arrow(false).floatingAnimation(Tooltip.Animation.Companion.getSLOW()).closePolicy(getClosePolicy()).showDuration(com.jch.racWiFi.Constants.VALID_WIFI_CONNECTION_CHECK_TIMEOUT).overlay(false).create().show(view, Tooltip.Gravity.valueOf(ToolTipGravity.CENTER.name()), true);
    }

    private final ClosePolicy getClosePolicy() {
        return new ClosePolicy.Builder().inside(true).outside(true).consume(true).build();
    }
}
