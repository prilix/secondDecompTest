package info.hoang8f.android.segmented;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class SegmentedGroup extends RadioGroup {
    private int mCheckedTextColor = -1;
    private Float mCornerRadius;
    private LayoutSelector mLayoutSelector;
    private int mMarginDp;
    private int mTintColor;
    private Resources resources;

    public SegmentedGroup(Context context) {
        super(context);
        Resources resources2 = getResources();
        this.resources = resources2;
        this.mTintColor = resources2.getColor(C2783R.C2784color.radio_button_selected_color);
        this.mMarginDp = (int) getResources().getDimension(C2783R.dimen.radio_button_stroke_border);
        Float valueOf = Float.valueOf(getResources().getDimension(C2783R.dimen.radio_button_conner_radius));
        this.mCornerRadius = valueOf;
        this.mLayoutSelector = new LayoutSelector(valueOf.floatValue());
    }

    private void initAttrs(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, C2783R.styleable.SegmentedGroup, 0, 0);
        try {
            this.mMarginDp = (int) obtainStyledAttributes.getDimension(C2783R.styleable.SegmentedGroup_sc_border_width, getResources().getDimension(C2783R.dimen.radio_button_stroke_border));
            this.mCornerRadius = Float.valueOf(obtainStyledAttributes.getDimension(C2783R.styleable.SegmentedGroup_sc_corner_radius, getResources().getDimension(C2783R.dimen.radio_button_conner_radius)));
            this.mTintColor = obtainStyledAttributes.getColor(C2783R.styleable.SegmentedGroup_sc_tint_color, getResources().getColor(C2783R.C2784color.radio_button_selected_color));
            this.mCheckedTextColor = obtainStyledAttributes.getColor(C2783R.styleable.SegmentedGroup_sc_checked_text_color, getResources().getColor(17170443));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public SegmentedGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Resources resources2 = getResources();
        this.resources = resources2;
        this.mTintColor = resources2.getColor(C2783R.C2784color.radio_button_selected_color);
        this.mMarginDp = (int) getResources().getDimension(C2783R.dimen.radio_button_stroke_border);
        this.mCornerRadius = Float.valueOf(getResources().getDimension(C2783R.dimen.radio_button_conner_radius));
        initAttrs(attributeSet);
        this.mLayoutSelector = new LayoutSelector(this.mCornerRadius.floatValue());
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        updateBackground();
    }

    public void setTintColor(int i) {
        this.mTintColor = i;
        updateBackground();
    }

    public void setTintColor(int i, int i2) {
        this.mTintColor = i;
        this.mCheckedTextColor = i2;
        updateBackground();
    }

    public void updateBackground() {
        int childCount = super.getChildCount();
        int i = 0;
        while (i < childCount) {
            View childAt = getChildAt(i);
            updateBackground(childAt);
            if (i != childCount - 1) {
                RadioGroup.LayoutParams layoutParams = (RadioGroup.LayoutParams) childAt.getLayoutParams();
                RadioGroup.LayoutParams layoutParams2 = new RadioGroup.LayoutParams(layoutParams.width, layoutParams.height, layoutParams.weight);
                if (getOrientation() == 0) {
                    layoutParams2.setMargins(0, 0, -this.mMarginDp, 0);
                } else {
                    layoutParams2.setMargins(0, 0, 0, -this.mMarginDp);
                }
                childAt.setLayoutParams(layoutParams2);
                i++;
            } else {
                return;
            }
        }
    }

    private void updateBackground(View view) {
        int selected = this.mLayoutSelector.getSelected();
        int unselected = this.mLayoutSelector.getUnselected();
        ((Button) view).setTextColor(new ColorStateList(new int[][]{new int[]{16842919}, new int[]{-16842919, -16842912}, new int[]{-16842919, 16842912}}, new int[]{-7829368, this.mTintColor, this.mCheckedTextColor}));
        Drawable mutate = this.resources.getDrawable(selected).mutate();
        Drawable mutate2 = this.resources.getDrawable(unselected).mutate();
        GradientDrawable gradientDrawable = (GradientDrawable) mutate;
        gradientDrawable.setColor(this.mTintColor);
        gradientDrawable.setStroke(this.mMarginDp, this.mTintColor);
        GradientDrawable gradientDrawable2 = (GradientDrawable) mutate2;
        gradientDrawable2.setStroke(this.mMarginDp, this.mTintColor);
        gradientDrawable.setCornerRadii(this.mLayoutSelector.getChildRadii(view));
        gradientDrawable2.setCornerRadii(this.mLayoutSelector.getChildRadii(view));
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{-16842912}, mutate2);
        stateListDrawable.addState(new int[]{16842912}, mutate);
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(stateListDrawable);
        } else {
            view.setBackgroundDrawable(stateListDrawable);
        }
    }

    private class LayoutSelector {
        private final int SELECTED_LAYOUT = C2783R.C2785drawable.radio_checked;
        private final int UNSELECTED_LAYOUT = C2783R.C2785drawable.radio_unchecked;
        private int child;
        private int children;

        /* renamed from: r */
        private float f496r;

        /* renamed from: r1 */
        private final float f497r1;
        private final float[] rBot;
        private final float[] rDefault;
        private final float[] rLeft;
        private final float[] rMiddle;
        private final float[] rRight;
        private final float[] rTop;
        private float[] radii;

        public LayoutSelector(float f) {
            float applyDimension = TypedValue.applyDimension(1, 0.1f, SegmentedGroup.this.getResources().getDisplayMetrics());
            this.f497r1 = applyDimension;
            this.children = -1;
            this.child = -1;
            this.f496r = f;
            this.rLeft = new float[]{f, f, applyDimension, applyDimension, applyDimension, applyDimension, f, f};
            this.rRight = new float[]{applyDimension, applyDimension, f, f, f, f, applyDimension, applyDimension};
            this.rMiddle = new float[]{applyDimension, applyDimension, applyDimension, applyDimension, applyDimension, applyDimension, applyDimension, applyDimension};
            this.rDefault = new float[]{f, f, f, f, f, f, f, f};
            this.rTop = new float[]{f, f, f, f, applyDimension, applyDimension, applyDimension, applyDimension};
            this.rBot = new float[]{applyDimension, applyDimension, applyDimension, applyDimension, f, f, f, f};
        }

        private int getChildren() {
            return SegmentedGroup.this.getChildCount();
        }

        private int getChildIndex(View view) {
            return SegmentedGroup.this.indexOfChild(view);
        }

        private void setChildRadii(int i, int i2) {
            if (this.children != i || this.child != i2) {
                this.children = i;
                this.child = i2;
                if (i == 1) {
                    this.radii = this.rDefault;
                } else if (i2 == 0) {
                    this.radii = SegmentedGroup.this.getOrientation() == 0 ? this.rLeft : this.rTop;
                } else if (i2 == i - 1) {
                    this.radii = SegmentedGroup.this.getOrientation() == 0 ? this.rRight : this.rBot;
                } else {
                    this.radii = this.rMiddle;
                }
            }
        }

        public int getSelected() {
            return this.SELECTED_LAYOUT;
        }

        public int getUnselected() {
            return this.UNSELECTED_LAYOUT;
        }

        public float[] getChildRadii(View view) {
            setChildRadii(getChildren(), getChildIndex(view));
            return this.radii;
        }
    }
}
