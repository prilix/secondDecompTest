package com.jch.racWiFi.customViews.customWidgets;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.AppCompatEditText;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.C1655R;
import com.jch_hitachi.aircloudglobal.R;

public class EditText extends AppCompatEditText implements ActionMode.Callback {
    public static ActionMode.Callback DISABLE_CLIPBOARD_OPTIONS = new ActionMode.Callback() {
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return false;
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }
    };
    private Context context;
    private boolean disableCopyPaste = false;
    private String fontName = null;

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return false;
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    public void onDestroyActionMode(ActionMode actionMode) {
    }

    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    public void setErrorBackgroundDrawable() {
        setBackground(getContext().getDrawable(R.drawable.red_border));
    }

    public void setNormalBackgroundDrawable() {
        setBackground(getContext().getDrawable(R.drawable.edit_text_background));
    }

    /* JADX INFO: finally extract failed */
    public EditText(Context context2, AttributeSet attributeSet, int i) {
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
            context2.obtainStyledAttributes(attributeSet, C1655R.styleable.fontjch);
            createFont();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public EditText(Context context2, AttributeSet attributeSet) {
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
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public EditText(Context context2) {
        super(context2);
        this.context = context2;
        createFont();
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

    public InputFilter setNameInputFilter() {
        return new InputFilter() {
            public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                StringBuilder sb = new StringBuilder(i2 - i);
                boolean z = true;
                for (int i5 = i; i5 < i2; i5++) {
                    Character valueOf = Character.valueOf(charSequence.charAt(i5));
                    if (!isCharAllowed(valueOf.charValue()) || valueOf.equals(960)) {
                        z = false;
                    } else {
                        sb.append(valueOf);
                    }
                }
                if (z) {
                    return null;
                }
                if (!(charSequence instanceof Spanned)) {
                    return sb;
                }
                SpannableString spannableString = new SpannableString(sb);
                TextUtils.copySpansFrom((Spanned) charSequence, i, sb.length(), (Class) null, spannableString, 0);
                return spannableString;
            }

            private boolean isCharAllowed(char c) {
                return Character.isLetter(c) || Character.isSpaceChar(c);
            }
        };
    }

    public InputFilter setAddressInputFilter() {
        return new InputFilter() {
            public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                StringBuilder sb = new StringBuilder(i2 - i);
                boolean z = true;
                for (int i5 = i; i5 < i2; i5++) {
                    char charAt = charSequence.charAt(i5);
                    if (isCharAllowed(charAt)) {
                        sb.append(charAt);
                    } else {
                        z = false;
                    }
                }
                if (z) {
                    return null;
                }
                if (!(charSequence instanceof Spanned)) {
                    return sb;
                }
                SpannableString spannableString = new SpannableString(sb);
                TextUtils.copySpansFrom((Spanned) charSequence, i, sb.length(), (Class) null, spannableString, 0);
                return spannableString;
            }

            private boolean isCharAllowed(char c) {
                return Character.isLetter(c) || Character.isSpaceChar(c);
            }
        };
    }

    public InputFilter setEmojisInputFilter() {
        return new InputFilter() {
            public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                StringBuilder sb = new StringBuilder(i2 - i);
                boolean z = true;
                for (int i5 = i; i5 < i2; i5++) {
                    int type = Character.getType(charSequence.charAt(i5));
                    if (type == 19 || type == 6 || type == 28) {
                        z = false;
                    } else {
                        sb.append(type);
                    }
                }
                if (z) {
                    return null;
                }
                if (!(charSequence instanceof Spanned)) {
                    return sb;
                }
                SpannableString spannableString = new SpannableString(sb);
                TextUtils.copySpansFrom((Spanned) charSequence, i, sb.length(), (Class) null, spannableString, 0);
                return spannableString;
            }
        };
    }

    public InputFilter.LengthFilter lengthFilter() {
        return new InputFilter.LengthFilter(30) {
        };
    }

    public InputFilter.LengthFilter lengthFilterAddressLine() {
        return new InputFilter.LengthFilter(60) {
        };
    }

    public InputFilter.LengthFilter lengthFilterZipCode() {
        return new InputFilter.LengthFilter(10) {
        };
    }

    public void disableCopyPaste() {
        this.disableCopyPaste = true;
        setLongClickable(false);
        setTextIsSelectable(false);
        setCustomSelectionActionModeCallback(DISABLE_CLIPBOARD_OPTIONS);
    }

    public boolean onTextContextMenuItem(int i) {
        if (!this.disableCopyPaste || (i != 16908322 && i != 16908337)) {
            return super.onTextContextMenuItem(i);
        }
        return false;
    }
}
