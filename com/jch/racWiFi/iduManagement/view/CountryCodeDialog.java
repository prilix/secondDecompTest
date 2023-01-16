package com.jch.racWiFi.iduManagement.view;

import android.app.AlertDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jch.racWiFi.customViews.customWidgets.EditText;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.userManagement.SelectCountryCodeRecyclerViewAdapter;
import com.jch.racWiFi.userManagement.model.CountryCodeUIConfiguration;
import com.jch_hitachi.aircloudglobal.R;

public class CountryCodeDialog extends AlertDialog implements SelectCountryCodeRecyclerViewAdapter.CountryCodeErrorCallBack {
    TextView countryCodeOrnameNotFoundError;
    String editTextSearchData;
    private AlertDialog mCountryCodeDialog;
    /* access modifiers changed from: private */
    public final SelectCountryCodeRecyclerViewAdapter mSelectCountryCodeRecyclerViewAdapter;

    public SelectCountryCodeRecyclerViewAdapter getCountryCodeRecyclerViewAdapter() {
        return this.mSelectCountryCodeRecyclerViewAdapter;
    }

    public CountryCodeDialog(Context context) {
        super(context);
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialogue_country_code_select, (ViewGroup) null, false);
        setView(inflate);
        ((ImageButton) inflate.findViewById(R.id.image_button_clear)).setOnClickListener(new CountryCodeDialog$$ExternalSyntheticLambda0(this));
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_view_select_country_by_name_or_isd_code);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        SelectCountryCodeRecyclerViewAdapter selectCountryCodeRecyclerViewAdapter = new SelectCountryCodeRecyclerViewAdapter(context, CountryCodeUIConfiguration.getCountryCodeUIConfigurationList(context), this);
        this.mSelectCountryCodeRecyclerViewAdapter = selectCountryCodeRecyclerViewAdapter;
        recyclerView.setAdapter(selectCountryCodeRecyclerViewAdapter);
        final EditText editText = (EditText) inflate.findViewById(R.id.edit_text_search_by_country_name_or_isd_code);
        this.countryCodeOrnameNotFoundError = (TextView) inflate.findViewById(R.id.country_dialog_error_message);
        editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                CountryCodeDialog.this.editTextSearchData = editText.getText().toString();
                CountryCodeDialog.this.mSelectCountryCodeRecyclerViewAdapter.search(CountryCodeDialog.this.editTextSearchData);
                CountryCodeDialog.this.mSelectCountryCodeRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }

    /* renamed from: lambda$new$0$com-jch-racWiFi-iduManagement-view-CountryCodeDialog */
    public /* synthetic */ void mo30441x45bbd396(View view) {
        dismiss();
    }

    public void countryCodeSearchSuccess() {
        this.countryCodeOrnameNotFoundError.setVisibility(8);
    }

    public void countryCodeSearchError() {
        this.countryCodeOrnameNotFoundError.setVisibility(0);
    }
}
