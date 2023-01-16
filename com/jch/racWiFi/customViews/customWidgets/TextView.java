package com.jch.racWiFi.customViews.customWidgets;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.C1655R;
import com.jch_hitachi.aircloudglobal.R;

public class TextView extends AppCompatTextView {
    private Context context;
    private String fontName = null;

    private void init() {
    }

    /* JADX INFO: finally extract failed */
    public TextView(Context context2, AttributeSet attributeSet, int i) {
        super(context2, attributeSet, i);
        init();
        this.context = context2;
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, C1655R.styleable.fontjch);
        try {
            String string = obtainStyledAttributes.getString(0);
            if (string != null) {
                this.fontName = string;
            } else {
                Logger.m47e("Error", "defstyle Supplied fond not found Roboto Medium font would be found at default");
            }
            obtainStyledAttributes.recycle();
            createFont();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public TextView(Context context2, AttributeSet attributeSet) {
        super(context2, attributeSet);
        init();
        this.context = context2;
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, C1655R.styleable.fontjch);
        try {
            String string = obtainStyledAttributes.getString(0);
            if (string != null) {
                this.fontName = string;
            } else {
                Logger.m47e("Error", "defstyle Supplied fond not found Roboto Medium font would be found at default");
            }
            obtainStyledAttributes.recycle();
            createFont();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public TextView(Context context2) {
        super(context2);
        init();
        this.context = context2;
        createFont();
    }

    private void createFont() {
        String str = this.fontName;
        if (str == null) {
            return;
        }
        if (str.equals(this.context.getResources().getString(R.string.font_sanpro_regular)) || this.fontName.equals(this.context.getResources().getString(R.string.font_sanpro_bold)) || this.fontName.equals(this.context.getResources().getString(R.string.font_sanpro_semi_bold)) || this.fontName.equals(this.context.getResources().getString(R.string.font_helvetica_bold)) || this.fontName.equals(this.context.getResources().getString(R.string.font_banksmiles_bold))) {
            AssetManager assets = getContext().getAssets();
            setTypeface(Typeface.createFromAsset(assets, "fonts/" + this.fontName + ".ttf"));
        }
    }
}
