package com.jch.racWiFi.userManagement.view.viewImpl;

import android.view.View;
import com.jch.racWiFi.iduManagement.view.CountryCodeDialog;
import com.jch.racWiFi.userManagement.SelectCountryCodeRecyclerViewAdapter;
import com.jch.racWiFi.userManagement.model.CountryCodeUIConfiguration;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class LoginFragment$$ExternalSyntheticLambda9 implements SelectCountryCodeRecyclerViewAdapter.OnItemSelectionListener {
    public final /* synthetic */ LoginFragment f$0;
    public final /* synthetic */ CountryCodeDialog f$1;

    public /* synthetic */ LoginFragment$$ExternalSyntheticLambda9(LoginFragment loginFragment, CountryCodeDialog countryCodeDialog) {
        this.f$0 = loginFragment;
        this.f$1 = countryCodeDialog;
    }

    public final void onItemSelected(View view, CountryCodeUIConfiguration countryCodeUIConfiguration) {
        this.f$0.mo33209xcbc0a0d(this.f$1, view, countryCodeUIConfiguration);
    }
}
