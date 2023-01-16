package com.jch.racWiFi.customViews.customWidgets;

import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.C1655R;
import com.jch_hitachi.aircloudglobal.R;

public class Button extends AppCompatButton {
    private Context context;
    private String fontName = null;
    private boolean isEnabled;
    private View.OnClickListener onDisableClickListener;

    private void init() {
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        View.OnClickListener onClickListener;
        if (!this.isEnabled && motionEvent.getAction() == 0 && (onClickListener = this.onDisableClickListener) != null) {
            onClickListener.onClick(this);
        }
        return super.onTouchEvent(motionEvent);
    }

    /* JADX INFO: finally extract failed */
    public Button(Context context2, AttributeSet attributeSet, int i) {
        super(context2, attributeSet, i);
        this.context = context2;
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, C1655R.styleable.fontjch);
        try {
            String string = obtainStyledAttributes.getString(0);
            if (string != null) {
                this.fontName = string;
            } else {
                Logger.m47e(getClass().getSimpleName(), "defstyle Supplied fond not found Roboto Medium font would be found at default");
            }
            obtainStyledAttributes.recycle();
            createFont();
            init();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public Button(Context context2, AttributeSet attributeSet) {
        super(context2, attributeSet);
        this.context = context2;
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, C1655R.styleable.fontjch);
        try {
            String string = obtainStyledAttributes.getString(0);
            if (string != null) {
                this.fontName = string;
            } else {
                Logger.m47e(getClass().getSimpleName(), "Supplied fond not found Roboto Medium font would be found at default");
            }
            obtainStyledAttributes.recycle();
            createFont();
            init();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public Button(Context context2) {
        super(context2);
        this.context = context2;
        createFont();
        init();
    }

    private void createFont() {
        String str = this.fontName;
        if (str == null) {
            return;
        }
        if (str.equals(this.context.getResources().getString(R.string.font_sanpro_regular)) || this.fontName.equals(this.context.getResources().getString(R.string.font_sanpro_bold)) || this.fontName.equals(this.context.getResources().getString(R.string.font_sanpro_semi_bold))) {
            AssetManager assets = getContext().getAssets();
            setTypeface(Typeface.createFromAsset(assets, "fonts/" + this.fontName + ".ttf"));
        }
    }

    public void setEnabled(boolean z) {
        this.isEnabled = z;
        if (z) {
            setBackground(getResources().getDrawable(R.drawable.button_filled));
        } else {
            setBackground(getResources().getDrawable(R.drawable.button_disabled));
        }
        super.setEnabled(z);
    }

    public void setEnabledHollowButton(boolean z) {
        this.isEnabled = z;
        if (z) {
            setTextColor(getContext().getResources().getColor(R.color.textview_color_vd_light));
        } else {
            setTextColor(getContext().getResources().getColor(R.color.color_disabled_views));
        }
        super.setEnabled(z);
        setStateListAnimator(z ? AnimatorInflater.loadStateListAnimator(getContext(), R.animator.selector_animator) : null);
    }

    public void setEnabledLoginButton(boolean z) {
        this.isEnabled = z;
        if (z) {
            setBackground(getContext().getResources().getDrawable(R.drawable.button_enabled_red));
        } else {
            setBackground(getContext().getResources().getDrawable(R.drawable.button_disabled));
        }
        super.setEnabled(z);
        setStateListAnimator(z ? AnimatorInflater.loadStateListAnimator(getContext(), R.animator.selector_animator) : null);
    }

    public void setOnDisableClickListener(View.OnClickListener onClickListener) {
        this.onDisableClickListener = onClickListener;
    }
}
