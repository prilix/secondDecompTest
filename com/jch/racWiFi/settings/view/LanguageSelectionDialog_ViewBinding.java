package com.jch.racWiFi.settings.view;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.C0840Utils;
import butterknife.internal.DebouncingOnClickListener;
import com.jch.racWiFi.customViews.customWidgets.Button;
import com.jch_hitachi.aircloudglobal.R;

public class LanguageSelectionDialog_ViewBinding implements Unbinder {
    private LanguageSelectionDialog target;
    private View view7f0a014b;
    private View view7f0a01c9;

    public LanguageSelectionDialog_ViewBinding(LanguageSelectionDialog languageSelectionDialog) {
        this(languageSelectionDialog, languageSelectionDialog.getWindow().getDecorView());
    }

    public LanguageSelectionDialog_ViewBinding(final LanguageSelectionDialog languageSelectionDialog, View view) {
        this.target = languageSelectionDialog;
        languageSelectionDialog.mRecyclerView = (RecyclerView) C0840Utils.findRequiredViewAsType(view, R.id.langauges_recycler_view, "field 'mRecyclerView'", RecyclerView.class);
        View findRequiredView = C0840Utils.findRequiredView(view, R.id.button_apply_language_selection, "field 'mApplyLanguage' and method 'onClickApply'");
        languageSelectionDialog.mApplyLanguage = (Button) C0840Utils.castView(findRequiredView, R.id.button_apply_language_selection, "field 'mApplyLanguage'", Button.class);
        this.view7f0a014b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                languageSelectionDialog.onClickApply();
            }
        });
        View findRequiredView2 = C0840Utils.findRequiredView(view, R.id.close_lang_selection, "field 'mCloseButton' and method 'onClickClose'");
        languageSelectionDialog.mCloseButton = findRequiredView2;
        this.view7f0a01c9 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                languageSelectionDialog.onClickClose();
            }
        });
    }

    public void unbind() {
        LanguageSelectionDialog languageSelectionDialog = this.target;
        if (languageSelectionDialog != null) {
            this.target = null;
            languageSelectionDialog.mRecyclerView = null;
            languageSelectionDialog.mApplyLanguage = null;
            languageSelectionDialog.mCloseButton = null;
            this.view7f0a014b.setOnClickListener((View.OnClickListener) null);
            this.view7f0a014b = null;
            this.view7f0a01c9.setOnClickListener((View.OnClickListener) null);
            this.view7f0a01c9 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
