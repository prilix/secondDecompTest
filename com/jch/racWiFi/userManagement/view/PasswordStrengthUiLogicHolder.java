package com.jch.racWiFi.userManagement.view;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.jch.racWiFi.Utils.ValidationUtils;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.userManagement.adapter.PasswordStrengthSuggestionAdapter;
import com.jch.racWiFi.userManagement.model.PasswordStrengthModel;
import com.jch.racWiFi.userManagement.model.PasswordStrengthSuggestionModel;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.Collections;

public class PasswordStrengthUiLogicHolder {
    @BindView(2131363161)
    ConstraintLayout constantPasswordStrengthView;
    private Context context;
    @BindView(2131364410)
    TextView mPasswordStrengthState;
    private PasswordStrengthSuggestionAdapter mPasswordStrengthSuggestionAdapter = new PasswordStrengthSuggestionAdapter();
    @BindView(2131364409)
    TextView mPasswordStrengthTitle;
    @BindView(2131364883)
    View mViewOk;
    @BindView(2131364886)
    View mViewStrong;
    @BindView(2131364892)
    View mViewWeak;
    @BindView(2131363619)
    RecyclerView passwordStrengthSuggestionRv;
    @BindView(2131363537)
    ConstraintLayout passwordStrengthUiLayout;
    private Unbinder unbinder;

    public PasswordStrengthUiLogicHolder(Activity activity, View view) {
        this.context = view.getContext();
        this.unbinder = ButterKnife.bind((Object) this, view);
        this.passwordStrengthSuggestionRv.setLayoutManager(new LinearLayoutManager(activity));
        this.passwordStrengthSuggestionRv.setAdapter(this.mPasswordStrengthSuggestionAdapter);
    }

    public void unbind() {
        this.unbinder.unbind();
    }

    /* renamed from: com.jch.racWiFi.userManagement.view.PasswordStrengthUiLogicHolder$1 */
    static /* synthetic */ class C25431 {

        /* renamed from: $SwitchMap$com$jch$racWiFi$Utils$ValidationUtils$PasswordStrength */
        static final /* synthetic */ int[] f486x3b3d5ace;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.jch.racWiFi.Utils.ValidationUtils$PasswordStrength[] r0 = com.jch.racWiFi.Utils.ValidationUtils.PasswordStrength.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f486x3b3d5ace = r0
                com.jch.racWiFi.Utils.ValidationUtils$PasswordStrength r1 = com.jch.racWiFi.Utils.ValidationUtils.PasswordStrength.EMPTY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f486x3b3d5ace     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.Utils.ValidationUtils$PasswordStrength r1 = com.jch.racWiFi.Utils.ValidationUtils.PasswordStrength.WEAK     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f486x3b3d5ace     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.Utils.ValidationUtils$PasswordStrength r1 = com.jch.racWiFi.Utils.ValidationUtils.PasswordStrength.FAIR     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f486x3b3d5ace     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.jch.racWiFi.Utils.ValidationUtils$PasswordStrength r1 = com.jch.racWiFi.Utils.ValidationUtils.PasswordStrength.GOOD     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f486x3b3d5ace     // Catch:{ NoSuchFieldError -> 0x003e }
                com.jch.racWiFi.Utils.ValidationUtils$PasswordStrength r1 = com.jch.racWiFi.Utils.ValidationUtils.PasswordStrength.STRONG     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f486x3b3d5ace     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.jch.racWiFi.Utils.ValidationUtils$PasswordStrength r1 = com.jch.racWiFi.Utils.ValidationUtils.PasswordStrength.VERY_STRONG     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.userManagement.view.PasswordStrengthUiLogicHolder.C25431.<clinit>():void");
        }
    }

    public void updatePasswordStrengthUI(ValidationUtils.PasswordStrength passwordStrength) {
        switch (C25431.f486x3b3d5ace[passwordStrength.ordinal()]) {
            case 1:
                this.mViewWeak.setBackgroundColor(this.context.getResources().getColor(R.color.color_light_grey));
                this.mViewOk.setBackgroundColor(this.context.getResources().getColor(R.color.color_light_grey));
                this.mViewStrong.setBackgroundColor(this.context.getResources().getColor(R.color.color_light_grey));
                this.mPasswordStrengthState.setText("");
                return;
            case 2:
            case 3:
                this.mViewWeak.setBackgroundColor(this.context.getResources().getColor(R.color.password_strength_weak));
                this.mViewOk.setBackgroundColor(this.context.getResources().getColor(R.color.color_light_grey));
                this.mViewStrong.setBackgroundColor(this.context.getResources().getColor(R.color.color_light_grey));
                this.mPasswordStrengthState.setText(this.context.getString(R.string.common_lbl_strengthWeak));
                this.mPasswordStrengthState.setTextColor(this.context.getResources().getColor(R.color.textview_color_vd_light));
                return;
            case 4:
                this.mViewWeak.setBackgroundColor(this.context.getResources().getColor(R.color.password_strength_medium));
                this.mViewOk.setBackgroundColor(this.context.getResources().getColor(R.color.password_strength_medium));
                this.mViewStrong.setBackgroundColor(this.context.getResources().getColor(R.color.color_light_grey));
                this.mPasswordStrengthState.setText(this.context.getString(R.string.common_lbl_strengthMedium));
                this.mPasswordStrengthState.setTextColor(this.context.getResources().getColor(R.color.textview_color_vd_light));
                return;
            case 5:
            case 6:
                this.mViewWeak.setBackgroundColor(this.context.getResources().getColor(R.color.password_strength_strong));
                this.mViewOk.setBackgroundColor(this.context.getResources().getColor(R.color.password_strength_strong));
                this.mViewStrong.setBackgroundColor(this.context.getResources().getColor(R.color.password_strength_strong));
                this.mPasswordStrengthState.setText(this.context.getString(R.string.common_lbl_strengthStrong));
                this.mPasswordStrengthState.setTextColor(this.context.getResources().getColor(R.color.textview_color_vd_light));
                return;
            default:
                return;
        }
    }

    public void hidePasswordStrengthMeter() {
        hideOrUnHide(8);
    }

    public void makeVisiblePasswordStrengthMeter() {
        hideOrUnHide(0);
    }

    private void hideOrUnHide(int i) {
        this.mViewWeak.setVisibility(i);
        this.mViewOk.setVisibility(i);
        this.mViewStrong.setVisibility(i);
        this.mPasswordStrengthTitle.setVisibility(i);
        this.mPasswordStrengthState.setVisibility(i);
    }

    public void updatedRecyclerViewWithSuggestions(PasswordStrengthModel.PasswordStrengthResponseModel passwordStrengthResponseModel) {
        ArrayList arrayList = new ArrayList();
        String[] strArr = (String[]) passwordStrengthResponseModel.suggestions.toArray(new String[0]);
        String[] stringArray = this.context.getResources().getStringArray(R.array.password_strength_suggessions);
        ArrayList arrayList2 = new ArrayList();
        Collections.addAll(arrayList2, stringArray);
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            arrayList.add(new PasswordStrengthSuggestionModel((String) arrayList2.get(Integer.parseInt(strArr[i].substring(4)) - 1)));
        }
        if (arrayList.size() > 4) {
            this.mPasswordStrengthSuggestionAdapter.updateAdapter(arrayList.subList(0, 4));
        } else {
            this.mPasswordStrengthSuggestionAdapter.updateAdapter(arrayList);
        }
    }

    private void makeConstantSuggestionLayoutVisible() {
        this.constantPasswordStrengthView.setVisibility(0);
    }

    private void hideConstantSuggestionLayout() {
        this.constantPasswordStrengthView.setVisibility(8);
    }

    private void makeDynamicSuggestionViewVisible() {
        this.passwordStrengthSuggestionRv.setVisibility(0);
    }

    private void hideDynamicSuggestionView() {
        this.passwordStrengthSuggestionRv.setVisibility(8);
    }

    public void showDynamicPasswordStrengthUi() {
        makeDynamicSuggestionViewVisible();
        hideConstantSuggestionLayout();
    }

    public void showInitialPasswordStrengthUi() {
        makeConstantSuggestionLayoutVisible();
        hideDynamicSuggestionView();
    }

    public void makeBackgroundRed() {
        this.passwordStrengthUiLayout.setBackgroundResource(R.drawable.ic_password_rectangle_red);
    }

    public void makeBackgroundNormal() {
        this.passwordStrengthUiLayout.setBackgroundResource(R.drawable.ic_password_rectangle);
    }

    public void makeVisiblePasswordStrengthUi() {
        this.passwordStrengthUiLayout.setVisibility(0);
    }

    public void hidePasswordStrengthUi() {
        this.passwordStrengthUiLayout.setVisibility(8);
    }
}
