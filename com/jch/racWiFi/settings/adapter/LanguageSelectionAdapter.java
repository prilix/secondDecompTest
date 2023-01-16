package com.jch.racWiFi.settings.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.Localization.LocaleConfiguration;
import com.jch.racWiFi.Utils.ViewUtils;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.settings.model.LanguageModel;
import com.jch_hitachi.aircloudglobal.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LanguageSelectionAdapter extends RecyclerView.Adapter<LanguageViewHolder> {
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public List<LanguageModel> languageModelList = new ArrayList();
    /* access modifiers changed from: private */
    public LanguageModel mSelectedLanguageModel = null;

    public class LanguageViewHolder_ViewBinding implements Unbinder {
        private LanguageViewHolder target;
        private View view7f0a05c0;

        public LanguageViewHolder_ViewBinding(final LanguageViewHolder languageViewHolder, View view) {
            this.target = languageViewHolder;
            languageViewHolder.mCountryFlag = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_country_flag_item, "field 'mCountryFlag'", ImageView.class);
            languageViewHolder.mLanguageName = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_language_item, "field 'mLanguageName'", TextView.class);
            languageViewHolder.mLanguageSelection = (ImageView) C0840Utils.findRequiredViewAsType(view, R.id.image_view_selected_langauge, "field 'mLanguageSelection'", ImageView.class);
            View findRequiredView = C0840Utils.findRequiredView(view, R.id.layout_select_language, "field 'mOuterLayout' and method 'onClickOfLanguage'");
            languageViewHolder.mOuterLayout = findRequiredView;
            this.view7f0a05c0 = findRequiredView;
            findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    languageViewHolder.onClickOfLanguage(view);
                }
            });
            languageViewHolder.mLanguageSelectionView = C0840Utils.findRequiredView(view, R.id.language_selection_view, "field 'mLanguageSelectionView'");
            languageViewHolder.mLanguageEnglishName = (TextView) C0840Utils.findRequiredViewAsType(view, R.id.text_view_language_english_item, "field 'mLanguageEnglishName'", TextView.class);
        }

        public void unbind() {
            LanguageViewHolder languageViewHolder = this.target;
            if (languageViewHolder != null) {
                this.target = null;
                languageViewHolder.mCountryFlag = null;
                languageViewHolder.mLanguageName = null;
                languageViewHolder.mLanguageSelection = null;
                languageViewHolder.mOuterLayout = null;
                languageViewHolder.mLanguageSelectionView = null;
                languageViewHolder.mLanguageEnglishName = null;
                this.view7f0a05c0.setOnClickListener((View.OnClickListener) null);
                this.view7f0a05c0 = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public LanguageModel getSelectedLanguageModel() {
        return this.mSelectedLanguageModel;
    }

    public LanguageSelectionAdapter(Context context2) {
        this.context = context2;
        for (LanguageModel languageModel : LocaleConfiguration.LanguageSelectionUtils.getLanguageModelList()) {
            this.languageModelList.add(new LanguageModel(languageModel));
        }
        int indexOf = this.languageModelList.indexOf(new LanguageModel(LocaleConfiguration.getCurrentAppLocale()));
        if (indexOf == -1) {
            this.languageModelList.get(this.languageModelList.indexOf(new LanguageModel(Locale.US))).setSelected(true);
            return;
        }
        this.languageModelList.get(indexOf).setSelected(true);
    }

    public LanguageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new LanguageViewHolder(LayoutInflater.from(this.context).inflate(R.layout.language_selection_item_view, viewGroup, false));
    }

    public void onBindViewHolder(LanguageViewHolder languageViewHolder, int i) {
        languageViewHolder.bind(this.languageModelList.get(i));
    }

    public int getItemCount() {
        return this.languageModelList.size();
    }

    class LanguageViewHolder extends RecyclerView.ViewHolder {
        @BindView(2131362883)
        public ImageView mCountryFlag;
        @BindView(2131364302)
        public TextView mLanguageEnglishName;
        @BindView(2131364303)
        public TextView mLanguageName;
        @BindView(2131363005)
        public ImageView mLanguageSelection;
        @BindView(2131363103)
        public View mLanguageSelectionView;
        @BindView(2131363264)
        public View mOuterLayout;

        @OnClick({2131363264})
        public void onClickOfLanguage(View view) {
            LanguageModel languageModel = (LanguageModel) view.getTag();
            if (!languageModel.getLocale().getLanguage().equals("zz")) {
                List<LanguageModel> r0 = LanguageSelectionAdapter.this.languageModelList;
                if (r0.contains(languageModel)) {
                    for (LanguageModel selected : r0) {
                        selected.setSelected(false);
                    }
                    languageModel.setSelected(true);
                }
                LanguageSelectionAdapter.this.mSelectedLanguageModel = new LanguageModel(languageModel);
                LanguageSelectionAdapter.this.notifyDataSetChanged();
            }
        }

        public LanguageViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }

        public void bind(LanguageModel languageModel) {
            this.mOuterLayout.setTag(languageModel);
            ViewUtils.convertDpToPixel(2.0f, LanguageSelectionAdapter.this.context);
            if (languageModel.isSelected()) {
                selectLang();
                LanguageSelectionAdapter.this.mSelectedLanguageModel = new LanguageModel(languageModel);
            } else {
                unSelectLang();
            }
            if (languageModel.getLocale().getLanguage().equals("zz")) {
                this.mLanguageName.setText("");
                this.mLanguageSelection.setVisibility(4);
                this.mLanguageSelectionView.setVisibility(8);
                this.mLanguageEnglishName.setVisibility(4);
                return;
            }
            this.mLanguageName.setText(languageModel.getLanguageStringRes());
            this.mLanguageEnglishName.setText(languageModel.getEnglishStringRes());
            this.mLanguageSelection.setVisibility(0);
            this.mLanguageSelectionView.setVisibility(0);
            this.mLanguageEnglishName.setVisibility(0);
        }

        private void selectLang() {
            this.mLanguageSelection.setImageResource(R.drawable.ic_red_tick_svg);
        }

        private void unSelectLang() {
            this.mLanguageSelection.setImageResource(17170445);
        }
    }
}
