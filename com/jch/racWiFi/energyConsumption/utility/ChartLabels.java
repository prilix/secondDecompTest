package com.jch.racWiFi.energyConsumption.utility;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.jch_hitachi.aircloudglobal.R;

public class ChartLabels extends LinearLayout {
    public ChartLabels(Context context) {
        super(context);
    }

    public ChartLabels(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ChartLabels(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ChartLabels(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void setMode(boolean z, String str, String str2) {
        int i;
        Context context;
        int i2;
        int i3;
        LinearLayout linearLayout;
        removeAllViews();
        int i4 = str.equals(QueryFilterType.COST.name()) ? R.string.energyConsumption_lbl_cost : R.string.energyConsumption_lbl_energy;
        if (str.equals(QueryFilterType.ENERGY.name())) {
            context = getContext();
            i = R.color.energy_bar_color;
        } else {
            context = getContext();
            i = R.color.cost_bar_color;
        }
        int color = ContextCompat.getColor(context, i);
        int color2 = ContextCompat.getColor(getContext(), R.color.axis_line_color);
        if (str2.equals(QueryFilterType.WEEKLY.name())) {
            i2 = R.string.common_lbl_lastWeek;
            i3 = R.string.common_lbl_thisWeek;
        } else if (str2.equals(QueryFilterType.MONTHLY.name())) {
            i2 = R.string.common_lbl_lastMonth;
            i3 = R.string.common_lbl_thisMonth;
        } else {
            i2 = R.string.common_lbl_lastYear;
            i3 = R.string.common_lbl_thisYear;
        }
        if (z) {
            addView(getBarLabel(color2, getResources().getString(i4) + " (" + getResources().getString(i2) + ")", false));
        }
        if (z) {
            linearLayout = getBarLabel(color, getResources().getString(i4) + " (" + getResources().getString(i3) + ")", false);
        } else {
            linearLayout = getBarLabel(color, getResources().getString(i4), false);
        }
        addView(linearLayout);
        if (str.equals(QueryFilterType.COST.name())) {
            addView(getBarLabel(0, (String) null, true));
        }
    }

    private LinearLayout getBarLabel(int i, String str, boolean z) {
        LinearLayout linearLayout = (LinearLayout) LinearLayout.inflate(getContext(), R.layout.energy_consumption_bar_chart_label_view, (ViewGroup) null);
        ImageView imageView = (ImageView) linearLayout.findViewById(R.id.image_view_bar_indicator);
        TextView textView = (TextView) linearLayout.findViewById(R.id.text_view_bar_label);
        LinearLayout linearLayout2 = (LinearLayout) linearLayout.findViewById(R.id.budgetLayout);
        if (getChildCount() > 0) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins((int) getResources().getDimension(R.dimen.dp_8), 0, 0, 0);
            imageView.setLayoutParams(layoutParams);
        }
        if (z) {
            linearLayout2.setVisibility(0);
            imageView.setVisibility(8);
            textView.setVisibility(8);
        } else {
            linearLayout2.setVisibility(8);
            imageView.setVisibility(0);
            textView.setVisibility(0);
            changeDrawableShapeColor(imageView, i);
            textView.setText(str);
        }
        return linearLayout;
    }

    private void changeDrawableShapeColor(ImageView imageView, int i) {
        Drawable background = imageView.getBackground();
        if (background instanceof ShapeDrawable) {
            ((ShapeDrawable) background).getPaint().setColor(i);
        } else if (background instanceof GradientDrawable) {
            ((GradientDrawable) background).setColor(i);
        } else if (background instanceof ColorDrawable) {
            ((ColorDrawable) background).setColor(i);
        }
    }
}
