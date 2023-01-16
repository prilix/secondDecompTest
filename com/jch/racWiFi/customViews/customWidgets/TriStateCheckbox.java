package com.jch.racWiFi.customViews.customWidgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jch_hitachi.aircloudglobal.R;

public class TriStateCheckbox extends FrameLayout implements CompoundButton.OnCheckedChangeListener {
    @BindView(2131362196)
    CheckBox checkBox;
    @BindView(2131363096)
    ImageView partialImage;
    private boolean partiallySelected;
    private CompoundButton.OnCheckedChangeListener userCheckedChangeListener = null;

    public TriStateCheckbox(Context context) {
        super(context);
        init(context);
    }

    public TriStateCheckbox(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public TriStateCheckbox(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public void init(Context context) {
        ButterKnife.bind(((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.tristate_checkbox, this, true));
        this.checkBox.setOnCheckedChangeListener(new TriStateCheckbox$$ExternalSyntheticLambda0(this));
        this.partialImage.setVisibility(8);
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.userCheckedChangeListener = onCheckedChangeListener;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (this.partialImage.getVisibility() == 0) {
            this.partialImage.setVisibility(8);
        }
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = this.userCheckedChangeListener;
        if (onCheckedChangeListener != null) {
            onCheckedChangeListener.onCheckedChanged(this.checkBox, z);
        }
    }

    public void setPartiallySelected() {
        this.checkBox.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        this.checkBox.setChecked(false);
        this.partialImage.setVisibility(0);
        this.checkBox.setOnCheckedChangeListener(this);
    }

    public boolean isPartiallySelected() {
        return this.partiallySelected;
    }

    public void setChecked(Boolean bool) {
        if (bool == null) {
            setPartiallySelected();
            this.partiallySelected = true;
            return;
        }
        this.partiallySelected = false;
        this.partialImage.setVisibility(8);
        this.checkBox.setChecked(bool.booleanValue());
    }

    public Boolean isChecked() {
        return Boolean.valueOf(this.checkBox.isChecked());
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.partialImage.setEnabled(z);
        this.checkBox.setEnabled(z);
    }

    public void setPartialImage() {
        this.partialImage.setImageResource(R.drawable.grey_tick);
    }

    public void setAlpha(float f) {
        super.setAlpha(f);
    }
}
