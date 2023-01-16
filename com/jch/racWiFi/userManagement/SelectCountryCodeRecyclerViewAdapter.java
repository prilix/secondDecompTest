package com.jch.racWiFi.userManagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.userManagement.model.CountryCodeUIConfiguration;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Marker;

public class SelectCountryCodeRecyclerViewAdapter extends RecyclerView.Adapter<SelectCountryCodeViewHolder> {
    /* access modifiers changed from: private */
    public Context context;
    private CountryCodeErrorCallBack countryCodeErrorCallBack;
    private final List<CountryCodeUIConfiguration> menuItemList;
    /* access modifiers changed from: private */
    public OnItemSelectionListener onItemSelectionListener;
    private final List<CountryCodeUIConfiguration> permanentList;

    public interface CountryCodeErrorCallBack {
        void countryCodeSearchError();

        void countryCodeSearchSuccess();
    }

    public interface OnItemSelectionListener {
        void onItemSelected(View view, CountryCodeUIConfiguration countryCodeUIConfiguration);
    }

    public class SelectCountryCodeViewHolder_ViewBinding implements Unbinder {
        private SelectCountryCodeViewHolder target;

        public SelectCountryCodeViewHolder_ViewBinding(SelectCountryCodeViewHolder selectCountryCodeViewHolder, View view) {
            this.target = selectCountryCodeViewHolder;
            selectCountryCodeViewHolder.mCountryFlag = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_country_flag, "field 'mCountryFlag'", ImageView.class);
            selectCountryCodeViewHolder.mCountryCode = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_country_code, "field 'mCountryCode'", TextView.class);
            selectCountryCodeViewHolder.mCountryName = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_country_name, "field 'mCountryName'", TextView.class);
            selectCountryCodeViewHolder.mCountryNameShortForm = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.textview_country_name_short_form, "field 'mCountryNameShortForm'", TextView.class);
        }

        public void unbind() {
            SelectCountryCodeViewHolder selectCountryCodeViewHolder = this.target;
            if (selectCountryCodeViewHolder != null) {
                this.target = null;
                selectCountryCodeViewHolder.mCountryFlag = null;
                selectCountryCodeViewHolder.mCountryCode = null;
                selectCountryCodeViewHolder.mCountryName = null;
                selectCountryCodeViewHolder.mCountryNameShortForm = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public List<CountryCodeUIConfiguration> getMenuItemList() {
        return this.menuItemList;
    }

    public void setOnItemSelectionListener(OnItemSelectionListener onItemSelectionListener2) {
        this.onItemSelectionListener = onItemSelectionListener2;
    }

    public SelectCountryCodeRecyclerViewAdapter(Context context2, List<CountryCodeUIConfiguration> list, CountryCodeErrorCallBack countryCodeErrorCallBack2) {
        ArrayList arrayList = new ArrayList();
        this.menuItemList = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.permanentList = arrayList2;
        this.context = context2;
        arrayList.addAll(list);
        arrayList2.addAll(list);
        this.countryCodeErrorCallBack = countryCodeErrorCallBack2;
    }

    public SelectCountryCodeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SelectCountryCodeViewHolder(LayoutInflater.from(this.context).inflate(R.layout.recycle_view_items_country_code_new, viewGroup, false));
    }

    public void onBindViewHolder(SelectCountryCodeViewHolder selectCountryCodeViewHolder, int i) {
        selectCountryCodeViewHolder.bind(this.menuItemList.get(i));
    }

    public int getItemCount() {
        return this.menuItemList.size();
    }

    class SelectCountryCodeViewHolder extends RecyclerView.ViewHolder {
        @BindView(2131364064)
        TextView mCountryCode;
        @BindView(2131362882)
        ImageView mCountryFlag;
        @BindView(2131364066)
        TextView mCountryName;
        @BindView(2131364762)
        TextView mCountryNameShortForm;
        View mainView;

        public SelectCountryCodeViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
            this.mainView = view;
            ViewUtils.setOutlineCountryImage(this.mCountryFlag);
            this.mCountryFlag.setElevation(ViewUtils.convertDpToPixel(3.0f, SelectCountryCodeRecyclerViewAdapter.this.context));
            this.mainView.setOnClickListener(new View.OnClickListener(SelectCountryCodeRecyclerViewAdapter.this) {
                public void onClick(View view) {
                    if (SelectCountryCodeRecyclerViewAdapter.this.onItemSelectionListener != null && ((CountryCodeUIConfiguration) view.getTag()).isValid()) {
                        CountryCodeUIConfiguration.CURRENT = (CountryCodeUIConfiguration) view.getTag();
                        CountryCodeUIConfiguration.CURRENT.setCountryCodeString(SelectCountryCodeRecyclerViewAdapter.this.context);
                        SelectCountryCodeRecyclerViewAdapter.this.onItemSelectionListener.onItemSelected(view, (CountryCodeUIConfiguration) view.getTag());
                    }
                }
            });
        }

        public void bind(CountryCodeUIConfiguration countryCodeUIConfiguration) {
            if (countryCodeUIConfiguration.isValid()) {
                this.mCountryFlag.setImageResource(countryCodeUIConfiguration.getCountryFlag());
                TextView textView = this.mCountryCode;
                textView.setText(Marker.ANY_NON_NULL_MARKER + SelectCountryCodeRecyclerViewAdapter.this.context.getString(countryCodeUIConfiguration.getCountryCode()));
                this.mCountryName.setText(countryCodeUIConfiguration.getCountryName());
                this.mCountryNameShortForm.setText(SelectCountryCodeRecyclerViewAdapter.this.context.getString(countryCodeUIConfiguration.getCountryNameShortForm()).toUpperCase());
                this.mainView.setTag(countryCodeUIConfiguration);
                return;
            }
            this.mCountryFlag.setImageResource(R.drawable.circle_white);
            this.mCountryCode.setText("");
            this.mCountryName.setText("");
            this.mCountryNameShortForm.setText("");
            this.mainView.setTag(countryCodeUIConfiguration);
        }
    }

    public void search(String str) {
        this.menuItemList.clear();
        for (CountryCodeUIConfiguration next : this.permanentList) {
            String lowerCase = str.toLowerCase();
            if (next.isValid()) {
                String lowerCase2 = this.context.getString(next.getCountryName()).toLowerCase();
                String lowerCase3 = this.context.getString(next.getCountryNameShortForm()).toLowerCase();
                String str2 = Marker.ANY_NON_NULL_MARKER + this.context.getString(next.getCountryCode()).toLowerCase();
                if (str.isEmpty()) {
                    this.menuItemList.clear();
                    this.menuItemList.addAll(this.permanentList);
                } else if (lowerCase2.startsWith(lowerCase.trim()) || str2.contains(lowerCase.trim()) || lowerCase3.contains(lowerCase.trim())) {
                    this.menuItemList.add(next);
                }
            }
        }
        if (this.menuItemList.isEmpty()) {
            this.countryCodeErrorCallBack.countryCodeSearchError();
        } else {
            this.countryCodeErrorCallBack.countryCodeSearchSuccess();
        }
    }
}
