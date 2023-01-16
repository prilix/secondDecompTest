package com.jch.racWiFi.customViews.customWidgets;

import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class SearchLayout {
    private ImageButton mSearchButton;
    /* access modifiers changed from: private */
    public EditText mSearchEditText;
    private TextView mTitleTextView;
    private boolean textWatcherAdded = false;

    public void addTextWatcher(TextWatcher textWatcher) {
        if (!this.textWatcherAdded) {
            this.mSearchEditText.addTextChangedListener(textWatcher);
            this.textWatcherAdded = true;
        }
    }

    public void removeTextWatcher(TextWatcher textWatcher) {
        this.mSearchEditText.removeTextChangedListener(textWatcher);
    }

    public SearchLayout(View view) {
        this.mSearchEditText = (EditText) view.findViewById(R.id.et_password_field);
        this.mSearchButton = (ImageButton) view.findViewById(R.id.clear);
        this.mSearchEditText.setVisibility(0);
        init();
    }

    private void init() {
        this.mSearchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SearchLayout.this.mSearchEditText.setText("");
            }
        });
    }
}
