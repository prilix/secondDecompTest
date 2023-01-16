package com.jch.racWiFi.userManagement.view.activate_user;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.jch_hitachi.aircloudglobal.R;

public class AccountActivatedSuccessFragment_ViewBinding implements Unbinder {
    private AccountActivatedSuccessFragment target;

    public AccountActivatedSuccessFragment_ViewBinding(AccountActivatedSuccessFragment accountActivatedSuccessFragment, View view) {
        this.target = accountActivatedSuccessFragment;
        accountActivatedSuccessFragment.mSubTitle = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.textView44, "field 'mSubTitle'", TextView.class);
        accountActivatedSuccessFragment.mTitle = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.tv_appbar_title, "field 'mTitle'", TextView.class);
        accountActivatedSuccessFragment.mSubTitleTwo = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.textView45, "field 'mSubTitleTwo'", TextView.class);
    }

    public void unbind() {
        AccountActivatedSuccessFragment accountActivatedSuccessFragment = this.target;
        if (accountActivatedSuccessFragment != null) {
            this.target = null;
            accountActivatedSuccessFragment.mSubTitle = null;
            accountActivatedSuccessFragment.mTitle = null;
            accountActivatedSuccessFragment.mSubTitleTwo = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
