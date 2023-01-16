package com.jch.racWiFi.customViews.TimerPicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import com.jch.racWiFi.C1655R;
import com.jch.racWiFi.StatusCode;
import com.jch_hitachi.aircloudglobal.R;

public class TimeDurationPicker extends FrameLayout {
    public static final int HH_MM = 1;
    public static final int HH_MM_SS = 0;
    public static final int MM_SS = 2;
    private final ImageButton backspaceButton;
    private OnDurationChangedListener changeListener;
    private final ImageButton clearButton;
    private final View displayRow;
    private final TextView[] displayViews;
    private final View durationView;
    private TextView hoursLabel;
    private final TextView hoursView;
    private final TimeDurationString input;
    private TextView minutesLabel;
    private final TextView minutesView;
    private final View numPad;
    private final Button[] numPadButtons;
    private final Button numPadMeasureButton;
    private TextView secondsLabel;
    private final TextView secondsView;
    private final View separatorView;
    private int timeUnits;
    private final TextView[] unitLabelViews;

    public interface OnDurationChangedListener {
        void onDurationChanged(TimeDurationPicker timeDurationPicker, long j);
    }

    public void setClearIcon(Drawable drawable) {
    }

    public TimeDurationPicker(Context context) {
        this(context, (AttributeSet) null);
    }

    public TimeDurationPicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.timeDurationPickerStyle);
    }

    /* JADX INFO: finally extract failed */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TimeDurationPicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Context context2 = context;
        this.timeUnits = 0;
        this.input = new TimeDurationString();
        this.changeListener = null;
        inflate(context2, R.layout.time_duration_picker, this);
        View findViewById = findViewById(R.id.displayRow);
        this.displayRow = findViewById;
        this.durationView = findViewById(R.id.duration);
        TextView textView = (TextView) findViewById(R.id.hours);
        this.hoursView = textView;
        TextView textView2 = (TextView) findViewById(R.id.minutes);
        this.minutesView = textView2;
        TextView textView3 = (TextView) findViewById(R.id.seconds);
        this.secondsView = textView3;
        TextView[] textViewArr = {textView, textView2, textView3};
        this.displayViews = textViewArr;
        this.hoursLabel = (TextView) findViewById(R.id.hoursLabel);
        this.minutesLabel = (TextView) findViewById(R.id.minutesLabel);
        TextView textView4 = (TextView) findViewById(R.id.secondsLabel);
        this.secondsLabel = textView4;
        TextView[] textViewArr2 = {this.hoursLabel, this.minutesLabel, textView4};
        this.unitLabelViews = textViewArr2;
        ImageButton imageButton = (ImageButton) findViewById(R.id.backspace);
        this.backspaceButton = imageButton;
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.clear);
        this.clearButton = imageButton2;
        imageButton2.setVisibility(4);
        View findViewById2 = findViewById(R.id.separator);
        this.separatorView = findViewById2;
        this.numPad = findViewById(R.id.numPad);
        this.numPadMeasureButton = (Button) findViewById(R.id.numPadMeasure);
        Button[] buttonArr = {(Button) findViewById(R.id.numPad1), (Button) findViewById(R.id.numPad2), (Button) findViewById(R.id.numPad3), (Button) findViewById(R.id.numPad4), (Button) findViewById(R.id.numPad5), (Button) findViewById(R.id.numPad6), (Button) findViewById(R.id.numPad7), (Button) findViewById(R.id.numPad8), (Button) findViewById(R.id.numPad9), (Button) findViewById(R.id.numPad0), (Button) findViewById(R.id.numPad00)};
        this.numPadButtons = buttonArr;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C1655R.styleable.TimeDurationPicker, i, 0);
        try {
            applyPadding(obtainStyledAttributes, 3, buttonArr);
            applyTextAppearance(context2, obtainStyledAttributes, 6, textViewArr);
            applyTextAppearance(context2, obtainStyledAttributes, 5, buttonArr);
            applyTextAppearance(context2, obtainStyledAttributes, 7, textViewArr2);
            applyIcon(obtainStyledAttributes, 0, imageButton);
            applyBackgroundColor(obtainStyledAttributes, 4, findViewById2);
            applyBackgroundColor(obtainStyledAttributes, 2, findViewById);
            applyUnits(obtainStyledAttributes, 8);
            obtainStyledAttributes.recycle();
            updateUnits();
            imageButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    TimeDurationPicker.this.onBackspace();
                }
            });
            C17052 r0 = new View.OnClickListener() {
                public void onClick(View view) {
                    TimeDurationPicker.this.onNumberClick(((Button) view).getText());
                }
            };
            for (Button onClickListener : buttonArr) {
                onClickListener.setOnClickListener(r0);
            }
            updateHoursMinutesSeconds();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    private void updateUnits() {
        TextView textView = this.hoursView;
        int i = this.timeUnits;
        int i2 = 8;
        textView.setVisibility((i == 0 || i == 1) ? 0 : 8);
        TextView textView2 = this.hoursLabel;
        int i3 = this.timeUnits;
        textView2.setVisibility((i3 == 0 || i3 == 1) ? 0 : 8);
        TextView textView3 = this.secondsView;
        int i4 = this.timeUnits;
        textView3.setVisibility((i4 == 0 || i4 == 2) ? 0 : 8);
        TextView textView4 = this.secondsLabel;
        int i5 = this.timeUnits;
        if (i5 == 0 || i5 == 2) {
            i2 = 0;
        }
        textView4.setVisibility(i2);
        this.input.updateTimeUnits(this.timeUnits);
    }

    private void applyUnits(TypedArray typedArray, int i) {
        if (typedArray.hasValue(i)) {
            this.timeUnits = typedArray.getInt(i, 0);
        }
    }

    public long getDuration() {
        return this.input.getDuration();
    }

    public void setDuration(long j) {
        this.input.setDuration(j);
        updateHoursMinutesSeconds();
    }

    public void setTimeUnits(int i) {
        this.timeUnits = i;
        updateUnits();
    }

    public void setOnDurationChangeListener(OnDurationChangedListener onDurationChangedListener) {
        this.changeListener = onDurationChangedListener;
    }

    public void setDisplayTextAppearance(int i) {
        applyTextAppearance(getContext(), i, this.displayViews);
    }

    public void setUnitTextAppearance(int i) {
        applyTextAppearance(getContext(), i, this.unitLabelViews);
    }

    public void setButtonTextAppearance(int i) {
        applyTextAppearance(getContext(), i, this.numPadButtons);
    }

    public void setBackspaceIcon(Drawable drawable) {
        this.backspaceButton.setImageDrawable(drawable);
    }

    public void setSeparatorColor(int i) {
        this.separatorView.setBackgroundColor(i);
    }

    public void setDurationDisplayBackgroundColor(int i) {
        this.displayRow.setBackgroundColor(i);
    }

    public void setNumPadButtonPadding(int i) {
        applyPadding(i, this.numPadButtons);
    }

    private void applyPadding(TypedArray typedArray, int i, View[] viewArr) {
        int dimensionPixelSize = typedArray.getDimensionPixelSize(i, -1);
        if (dimensionPixelSize > -1) {
            applyPadding(dimensionPixelSize, viewArr);
        }
    }

    private void applyPadding(int i, View[] viewArr) {
        for (View padding : viewArr) {
            padding.setPadding(i, i, i, i);
        }
    }

    private void applyTextAppearance(Context context, TypedArray typedArray, int i, TextView[] textViewArr) {
        int resourceId = typedArray.getResourceId(i, 0);
        if (resourceId != 0) {
            applyTextAppearance(context, resourceId, textViewArr);
        }
    }

    private void applyTextAppearance(Context context, int i, TextView[] textViewArr) {
        for (TextView textAppearance : textViewArr) {
            textAppearance.setTextAppearance(context, i);
        }
    }

    private void applyIcon(TypedArray typedArray, int i, ImageView imageView) {
        Drawable drawable = typedArray.getDrawable(i);
        if (drawable != null) {
            imageView.setImageDrawable(drawable);
        }
    }

    private void applyBackgroundColor(TypedArray typedArray, int i, View view) {
        if (typedArray.hasValue(i)) {
            view.setBackgroundColor(typedArray.getColor(i, 0));
        }
    }

    private void applyLeftMargin(int i, View... viewArr) {
        for (View view : viewArr) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
            layoutParams.setMargins(i, layoutParams.topMargin, layoutParams.rightMargin, layoutParams.bottomMargin);
            view.setLayoutParams(layoutParams);
        }
    }

    /* access modifiers changed from: private */
    public void onBackspace() {
        this.input.popDigit();
        updateHoursMinutesSeconds();
    }

    private void onClear() {
        this.input.clear();
        updateHoursMinutesSeconds();
    }

    /* access modifiers changed from: private */
    public void onNumberClick(CharSequence charSequence) {
        this.input.pushNumber(charSequence);
        updateHoursMinutesSeconds();
    }

    private void updateHoursMinutesSeconds() {
        this.hoursView.setText(this.input.getHoursString());
        this.minutesView.setText(this.input.getMinutesString());
        this.secondsView.setText(this.input.getSecondsString());
        fireDurationChangeListener();
    }

    private void fireDurationChangeListener() {
        OnDurationChangedListener onDurationChangedListener = this.changeListener;
        if (onDurationChangedListener != null) {
            onDurationChangedListener.onDurationChanged(this, this.input.getDuration());
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.touchable);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.hoursView.measure(makeMeasureSpec, makeMeasureSpec);
        TextView textView = this.unitLabelViews[2];
        textView.measure(makeMeasureSpec, makeMeasureSpec);
        applyLeftMargin(Math.max(this.hoursView.getMeasuredWidth() / 3, (int) (((float) textView.getMeasuredWidth()) * 1.2f)), this.minutesView, this.secondsView);
        this.durationView.measure(makeMeasureSpec, makeMeasureSpec);
        int measuredWidth = this.durationView.getMeasuredWidth() + (dimensionPixelSize * 2);
        int max = Math.max(this.durationView.getMeasuredHeight(), dimensionPixelSize);
        this.numPadMeasureButton.measure(makeMeasureSpec, makeMeasureSpec);
        int max2 = Math.max(Math.max(this.numPadMeasureButton.getMeasuredHeight(), this.numPadMeasureButton.getMeasuredWidth()), dimensionPixelSize);
        int i3 = max2 * 3;
        int i4 = max2 * 4;
        int max3 = Math.max(measuredWidth, i3);
        int i5 = max + i4;
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode == 1073741824) {
            max3 = size;
        }
        if (mode2 == 1073741824) {
            i5 = size2;
        }
        int max4 = Math.max(measuredWidth, max3);
        this.displayRow.measure(View.MeasureSpec.makeMeasureSpec(max4, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(max, BasicMeasure.EXACTLY));
        int max5 = Math.max(i4, max4);
        int max6 = Math.max(i4, i5 - max);
        this.numPad.measure(View.MeasureSpec.makeMeasureSpec(max5, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(max6, BasicMeasure.EXACTLY));
        setMeasuredDimension(Math.max(max4, max5), max + max6);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int measuredWidth = this.displayRow.getMeasuredWidth();
        int measuredHeight = this.displayRow.getMeasuredHeight();
        int i6 = (i5 - measuredWidth) / 2;
        this.displayRow.layout(i6, 0, measuredWidth + i6, measuredHeight);
        int measuredWidth2 = this.numPad.getMeasuredWidth();
        int i7 = (i5 - measuredWidth2) / 2;
        this.numPad.layout(i7, measuredHeight, measuredWidth2 + i7, this.numPad.getMeasuredHeight() + measuredHeight);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), this.input.getInputString());
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            this.input.clear();
            this.input.pushNumber(savedState.durationInput);
            updateHoursMinutesSeconds();
            return;
        }
        throw new IllegalArgumentException("Expected state of class " + SavedState.class.getName() + " but received state of class " + parcelable.getClass().getName());
    }

    private static class TimeDurationString {
        private long duration = 0;
        private final StringBuilder input = new StringBuilder(6);
        private int maxDigits = 6;
        private int timeUnits;

        public TimeDurationString() {
            padWithZeros();
        }

        /* access modifiers changed from: private */
        public void updateTimeUnits(int i) {
            this.timeUnits = i;
            setMaxDigits(i);
        }

        private void setMaxDigits(int i) {
            if (i == 0) {
                this.maxDigits = 6;
            } else {
                this.maxDigits = 4;
            }
            setDuration(this.duration);
        }

        public void pushNumber(CharSequence charSequence) {
            for (int i = 0; i < charSequence.length(); i++) {
                pushDigit(charSequence.charAt(i));
            }
        }

        public void pushDigit(char c) {
            if (Character.isDigit(c)) {
                removeLeadingZeros();
                if (this.input.length() < this.maxDigits && (this.input.length() > 0 || c != '0')) {
                    this.input.append(c);
                }
                padWithZeros();
                return;
            }
            throw new IllegalArgumentException("Only numbers are allowed");
        }

        public void popDigit() {
            if (this.input.length() > 0) {
                StringBuilder sb = this.input;
                sb.deleteCharAt(sb.length() - 1);
            }
            padWithZeros();
        }

        public void clear() {
            this.input.setLength(0);
            padWithZeros();
        }

        public String getHoursString() {
            int i = this.timeUnits;
            return (i == 0 || i == 1) ? this.input.substring(0, 2) : "00";
        }

        public String getMinutesString() {
            int i = this.timeUnits;
            if (i == 0 || i == 1) {
                return this.input.substring(2, 4);
            }
            return i == 2 ? this.input.substring(0, 2) : "00";
        }

        public String getSecondsString() {
            int i = this.timeUnits;
            if (i == 0) {
                return this.input.substring(4, 6);
            }
            return i == 2 ? this.input.substring(2, 4) : "00";
        }

        public String getInputString() {
            return this.input.toString();
        }

        public long getDuration() {
            return TimeDurationUtil.durationOf(Integer.parseInt(getHoursString()), Integer.parseInt(getMinutesString()), Integer.parseInt(getSecondsString()));
        }

        public void setDuration(long j) {
            this.duration = j;
            setDuration((long) TimeDurationUtil.hoursOf(j), (long) (this.timeUnits == 2 ? TimeDurationUtil.minutesOf(j) : TimeDurationUtil.minutesInHourOf(j)), (long) TimeDurationUtil.secondsInMinuteOf(j));
        }

        private void setDuration(long j, long j2, long j3) {
            if (j > 99 || j2 > 99) {
                setDurationString("99", "99", "99");
            } else {
                setDurationString(stringFragment(j), stringFragment(j2), stringFragment(j3));
            }
        }

        private void setDurationString(String str, String str2, String str3) {
            this.input.setLength(0);
            int i = this.timeUnits;
            if (i == 1 || i == 0) {
                this.input.append(str);
            }
            this.input.append(str2);
            int i2 = this.timeUnits;
            if (i2 == 0 || i2 == 2) {
                this.input.append(str3);
            }
        }

        private void removeLeadingZeros() {
            while (this.input.length() > 0 && this.input.charAt(0) == '0') {
                this.input.deleteCharAt(0);
            }
        }

        private void padWithZeros() {
            while (this.input.length() < this.maxDigits) {
                this.input.insert(0, '0');
            }
        }

        private String stringFragment(long j) {
            StringBuilder sb = new StringBuilder();
            sb.append(j < 10 ? StatusCode.BUILTIN_WIRELESS : "");
            sb.append(Long.toString(j));
            return sb.toString();
        }
    }

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        final String durationInput;

        public SavedState(Parcelable parcelable, String str) {
            super(parcelable);
            this.durationInput = str;
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.durationInput = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.durationInput);
        }
    }
}
