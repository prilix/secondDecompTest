package com.jch.racWiFi.customViews.customWidgets;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.github.mikephil.charting.utils.C1030Utils;
import com.jch.racWiFi.StatusCode;
import com.jch_hitachi.aircloudglobal.R;

public class CustomEditText extends ConstraintLayout {
    ImageButton btDecrease;
    ImageButton btIncrease;
    Double count;
    EditText etText;

    public void setCount(Double d) {
        this.count = d;
        this.etText.setText(String.valueOf(d));
    }

    public Double getCount() {
        return this.count;
    }

    public CustomEditText(Context context) {
        super(context);
        init(context);
    }

    public CustomEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public CustomEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.custom_edit_text, this, true);
        this.count = Double.valueOf(C1030Utils.DOUBLE_EPSILON);
        this.btDecrease = (ImageButton) findViewById(R.id.btDecrease);
        this.btIncrease = (ImageButton) findViewById(R.id.btIncrease);
        EditText editText = (EditText) findViewById(R.id.etText);
        this.etText = editText;
        editText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                CustomEditText.this.count = Double.valueOf(editable.toString());
            }
        });
        if (this.etText.getText() == null) {
            this.etText.setText(StatusCode.BUILTIN_WIRELESS);
        }
        if (this.etText.getText() != null) {
            this.etText.setText(this.count.toString());
        }
        this.btIncrease.setOnClickListener(new CustomEditText$$ExternalSyntheticLambda0(this));
        this.btDecrease.setOnClickListener(new CustomEditText$$ExternalSyntheticLambda1(this));
    }

    /* renamed from: lambda$init$0$com-jch-racWiFi-customViews-customWidgets-CustomEditText */
    public /* synthetic */ void mo28347x712634a0(View view) {
        Double valueOf = Double.valueOf(this.count.doubleValue() + 1.0d);
        this.count = valueOf;
        this.etText.setText(valueOf.toString());
    }

    /* renamed from: lambda$init$1$com-jch-racWiFi-customViews-customWidgets-CustomEditText */
    public /* synthetic */ void mo28348xfe134bbf(View view) {
        if (this.count.doubleValue() <= C1030Utils.DOUBLE_EPSILON) {
            this.count = Double.valueOf(C1030Utils.DOUBLE_EPSILON);
        } else {
            this.count = Double.valueOf(this.count.doubleValue() - 1.0d);
        }
        this.etText.setText(this.count.toString());
    }
}
