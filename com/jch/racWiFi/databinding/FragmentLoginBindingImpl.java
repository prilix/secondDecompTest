package com.jch.racWiFi.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.jch_hitachi.aircloudglobal.R;

public class FragmentLoginBindingImpl extends FragmentLoginBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final SocialLoginGoogleLayoutBinding mboundView1;
    private final SocialLoginFacebookLayoutBinding mboundView11;
    private final SocialLoginTwitterLayoutBinding mboundView12;
    private final SocialLoginLinkedinLayoutBinding mboundView13;
    private final SocialLoginWechatLayoutBinding mboundView14;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(51);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"social_login_google_layout", "social_login_facebook_layout", "social_login_twitter_layout", "social_login_linkedin_layout", "social_login_wechat_layout"}, new int[]{2, 3, 4, 5, 6}, new int[]{R.layout.social_login_google_layout, R.layout.social_login_facebook_layout, R.layout.social_login_twitter_layout, R.layout.social_login_linkedin_layout, R.layout.social_login_wechat_layout});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.fragment_attach_layout, 7);
        sparseIntArray.put(R.id.guideline140, 8);
        sparseIntArray.put(R.id.constraintLayout10, 9);
        sparseIntArray.put(R.id.button_demo_mode, 10);
        sparseIntArray.put(R.id.image_view_hitachi_logo_white, 11);
        sparseIntArray.put(R.id.constarint1, 12);
        sparseIntArray.put(R.id.text_view_mobile_number_forgot_password, 13);
        sparseIntArray.put(R.id.text_view_email_forgot_password, 14);
        sparseIntArray.put(R.id.guideline42, 15);
        sparseIntArray.put(R.id.guideline43, 16);
        sparseIntArray.put(R.id.mobile_number_selection_highlight, 17);
        sparseIntArray.put(R.id.email_selection_highlight, 18);
        sparseIntArray.put(R.id.guideline44, 19);
        sparseIntArray.put(R.id.guideline152, 20);
        sparseIntArray.put(R.id.guideline153, 21);
        sparseIntArray.put(R.id.layout_number_of_login_attempts, 22);
        sparseIntArray.put(R.id.text_view_incorrect_user_name_or_password, 23);
        sparseIntArray.put(R.id.text_number_of_log_in_attempts, 24);
        sparseIntArray.put(R.id.mobile_number_layout, 25);
        sparseIntArray.put(R.id.layout_country_code, 26);
        sparseIntArray.put(R.id.image_view_flag_login, 27);
        sparseIntArray.put(R.id.text_view_country_number_code_login, 28);
        sparseIntArray.put(R.id.guideline196, 29);
        sparseIntArray.put(R.id.guideline198, 30);
        sparseIntArray.put(R.id.edit_text_mobile_number, 31);
        sparseIntArray.put(R.id.enter_mobile_number_bubble_layout, 32);
        sparseIntArray.put(R.id.edit_text_enter_email, 33);
        sparseIntArray.put(R.id.enter_email_bubble_layout, 34);
        sparseIntArray.put(R.id.guideline192, 35);
        sparseIntArray.put(R.id.textInputLayout, 36);
        sparseIntArray.put(R.id.edit_text_enter_password, 37);
        sparseIntArray.put(R.id.enter_password_bubble_layout, 38);
        sparseIntArray.put(R.id.guideline194, 39);
        sparseIntArray.put(R.id.text_view_forgot_password, 40);
        sparseIntArray.put(R.id.button_login, 41);
        sparseIntArray.put(R.id.text_view_or_login_with, 42);
        sparseIntArray.put(R.id.guideline144, 43);
        sparseIntArray.put(R.id.button_create_account, 44);
        sparseIntArray.put(R.id.layout_language_selection, 45);
        sparseIntArray.put(R.id.image_view_country_flag_item, 46);
        sparseIntArray.put(R.id.text_view_language_item, 47);
        sparseIntArray.put(R.id.text_view_country_name_login, 48);
        sparseIntArray.put(R.id.image_view_arrow_down, 49);
        sparseIntArray.put(R.id.parent, 50);
    }

    public FragmentLoginBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 51, sIncludes, sViewsWithIds));
    }

    private FragmentLoginBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[44], objArr[10], objArr[41], objArr[12], objArr[9], objArr[33], objArr[37], objArr[31], objArr[18], objArr[34], objArr[32], objArr[38], objArr[7], objArr[8], objArr[43], objArr[20], objArr[21], objArr[35], objArr[39], objArr[29], objArr[30], objArr[15], objArr[16], objArr[19], objArr[49], objArr[46], objArr[27], objArr[11], objArr[26], objArr[45], objArr[22], objArr[1], objArr[25], objArr[17], objArr[50], objArr[36], objArr[24], objArr[48], objArr[28], objArr[14], objArr[40], objArr[23], objArr[47], objArr[13], objArr[42]);
        this.mDirtyFlags = -1;
        this.linearLayout6.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        SocialLoginGoogleLayoutBinding socialLoginGoogleLayoutBinding = objArr[2];
        this.mboundView1 = socialLoginGoogleLayoutBinding;
        setContainedBinding(socialLoginGoogleLayoutBinding);
        SocialLoginFacebookLayoutBinding socialLoginFacebookLayoutBinding = objArr[3];
        this.mboundView11 = socialLoginFacebookLayoutBinding;
        setContainedBinding(socialLoginFacebookLayoutBinding);
        SocialLoginTwitterLayoutBinding socialLoginTwitterLayoutBinding = objArr[4];
        this.mboundView12 = socialLoginTwitterLayoutBinding;
        setContainedBinding(socialLoginTwitterLayoutBinding);
        SocialLoginLinkedinLayoutBinding socialLoginLinkedinLayoutBinding = objArr[5];
        this.mboundView13 = socialLoginLinkedinLayoutBinding;
        setContainedBinding(socialLoginLinkedinLayoutBinding);
        SocialLoginWechatLayoutBinding socialLoginWechatLayoutBinding = objArr[6];
        this.mboundView14 = socialLoginWechatLayoutBinding;
        setContainedBinding(socialLoginWechatLayoutBinding);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
        }
        this.mboundView1.invalidateAll();
        this.mboundView11.invalidateAll();
        this.mboundView12.invalidateAll();
        this.mboundView13.invalidateAll();
        this.mboundView14.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r6.mboundView11.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        if (r6.mboundView12.hasPendingBindings() == false) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        if (r6.mboundView13.hasPendingBindings() == false) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0037, code lost:
        if (r6.mboundView14.hasPendingBindings() == false) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0039, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003a, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r6.mboundView1.hasPendingBindings() == false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r6 = this;
            monitor-enter(r6)
            long r0 = r6.mDirtyFlags     // Catch:{ all -> 0x003c }
            r2 = 0
            r4 = 1
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 == 0) goto L_0x000c
            monitor-exit(r6)     // Catch:{ all -> 0x003c }
            return r4
        L_0x000c:
            monitor-exit(r6)     // Catch:{ all -> 0x003c }
            com.jch.racWiFi.databinding.SocialLoginGoogleLayoutBinding r0 = r6.mboundView1
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r4
        L_0x0016:
            com.jch.racWiFi.databinding.SocialLoginFacebookLayoutBinding r0 = r6.mboundView11
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001f
            return r4
        L_0x001f:
            com.jch.racWiFi.databinding.SocialLoginTwitterLayoutBinding r0 = r6.mboundView12
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0028
            return r4
        L_0x0028:
            com.jch.racWiFi.databinding.SocialLoginLinkedinLayoutBinding r0 = r6.mboundView13
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0031
            return r4
        L_0x0031:
            com.jch.racWiFi.databinding.SocialLoginWechatLayoutBinding r0 = r6.mboundView14
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x003a
            return r4
        L_0x003a:
            r0 = 0
            return r0
        L_0x003c:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x003c }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.databinding.FragmentLoginBindingImpl.hasPendingBindings():boolean");
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.mboundView1.setLifecycleOwner(lifecycleOwner);
        this.mboundView11.setLifecycleOwner(lifecycleOwner);
        this.mboundView12.setLifecycleOwner(lifecycleOwner);
        this.mboundView13.setLifecycleOwner(lifecycleOwner);
        this.mboundView14.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0;
        }
        executeBindingsOn(this.mboundView1);
        executeBindingsOn(this.mboundView11);
        executeBindingsOn(this.mboundView12);
        executeBindingsOn(this.mboundView13);
        executeBindingsOn(this.mboundView14);
    }
}
