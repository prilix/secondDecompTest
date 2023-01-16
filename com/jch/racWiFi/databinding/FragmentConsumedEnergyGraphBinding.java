package com.jch.racWiFi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.github.mikephil.charting.charts.BarChart;
import com.jch.racWiFi.customViews.customWidgets.ImageButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch.racWiFi.energyConsumption.utility.ChartInfoTextView;
import com.jch.racWiFi.energyConsumption.utility.ChartLabels;
import com.jch_hitachi.aircloudglobal.R;

public abstract class FragmentConsumedEnergyGraphBinding extends ViewDataBinding {
    public final ConstraintLayout actionBarLayout;
    public final TextView availableDataDurationText;
    public final ImageButton backButton;
    public final BarChart barchartView;
    public final ConstraintLayout bottomLayout;
    public final TextView budgetAmountTextView;
    public final ConstraintLayout budgetPriceLayout;
    public final TextView budgetPriceTextView;
    public final ChartLabels chartLabelLayout;
    public final CheckBox compareWithLastYrCheckBox;
    public final RadioButton costRadioBtn;
    public final RadioGroup energyCostRadioGrp;
    public final RadioButton energyRadioBtn;
    public final RadioGroup energyUsageRadioGroup;
    public final Guideline guideLine1;
    public final Guideline guideLine8;
    public final Guideline guideline150;
    public final Guideline guideline151;
    public final Guideline guideline56;
    public final Guideline guideline58;
    public final Guideline guideline59;
    public final Guideline guideline60;
    public final BannerBudgetConsumedBinding include;
    public final TextView lastUpdatedOnHeaderTV;
    public final ConstraintLayout lastUpdatedOnLayout;
    public final TextView lastUpdatedOnTextView;
    public final ConstraintLayout lastYearBackButtonLayout;
    public final ConstraintLayout layoutBudgetBanner;
    public final ImageView leftBackArrow;
    public final TextView leftSideTextView;
    public final TextView middleTextView;
    public final TextView monthAndYearText;
    public final RadioButton monthlyRadioBtn;
    public final ConstraintLayout nextYearBackButtonLayout;
    public final ConstraintLayout racNameLayout;
    public final TextView racNameLebelTextView;
    public final TextView racNameTextView;
    public final ImageView rightArrowIcon;
    public final TextView rightSideTextView;
    public final TextView textViewAcName;
    public final TextView textViewAcTitle;
    public final ConstraintLayout thisYearTextLayout;
    public final ConstraintLayout topLayout;
    public final ConstraintLayout totalBudgetLayout;
    public final ChartInfoTextView tvChartXAxisName;
    public final ChartInfoTextView tvChartYAxisName;
    public final TextView unitPriceLebelTextView;
    public final RadioButton weeklyRadioBtn;
    public final RadioButton yearlyRadioBtn;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected FragmentConsumedEnergyGraphBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, TextView textView, ImageButton imageButton, BarChart barChart, ConstraintLayout constraintLayout2, TextView textView2, ConstraintLayout constraintLayout3, TextView textView3, ChartLabels chartLabels, CheckBox checkBox, RadioButton radioButton, RadioGroup radioGroup, RadioButton radioButton2, RadioGroup radioGroup2, Guideline guideline, Guideline guideline2, Guideline guideline3, Guideline guideline4, Guideline guideline5, Guideline guideline6, Guideline guideline7, Guideline guideline8, BannerBudgetConsumedBinding bannerBudgetConsumedBinding, TextView textView4, ConstraintLayout constraintLayout4, TextView textView5, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, ImageView imageView, TextView textView6, TextView textView7, TextView textView8, RadioButton radioButton3, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, TextView textView9, TextView textView10, ImageView imageView2, TextView textView11, TextView textView12, TextView textView13, ConstraintLayout constraintLayout9, ConstraintLayout constraintLayout10, ConstraintLayout constraintLayout11, ChartInfoTextView chartInfoTextView, ChartInfoTextView chartInfoTextView2, TextView textView14, RadioButton radioButton4, RadioButton radioButton5) {
        super(obj, view, i);
        this.actionBarLayout = constraintLayout;
        this.availableDataDurationText = textView;
        this.backButton = imageButton;
        this.barchartView = barChart;
        this.bottomLayout = constraintLayout2;
        this.budgetAmountTextView = textView2;
        this.budgetPriceLayout = constraintLayout3;
        this.budgetPriceTextView = textView3;
        this.chartLabelLayout = chartLabels;
        this.compareWithLastYrCheckBox = checkBox;
        this.costRadioBtn = radioButton;
        this.energyCostRadioGrp = radioGroup;
        this.energyRadioBtn = radioButton2;
        this.energyUsageRadioGroup = radioGroup2;
        this.guideLine1 = guideline;
        this.guideLine8 = guideline2;
        this.guideline150 = guideline3;
        this.guideline151 = guideline4;
        this.guideline56 = guideline5;
        this.guideline58 = guideline6;
        this.guideline59 = guideline7;
        this.guideline60 = guideline8;
        this.include = bannerBudgetConsumedBinding;
        this.lastUpdatedOnHeaderTV = textView4;
        this.lastUpdatedOnLayout = constraintLayout4;
        this.lastUpdatedOnTextView = textView5;
        this.lastYearBackButtonLayout = constraintLayout5;
        this.layoutBudgetBanner = constraintLayout6;
        this.leftBackArrow = imageView;
        this.leftSideTextView = textView6;
        this.middleTextView = textView7;
        this.monthAndYearText = textView8;
        this.monthlyRadioBtn = radioButton3;
        this.nextYearBackButtonLayout = constraintLayout7;
        this.racNameLayout = constraintLayout8;
        this.racNameLebelTextView = textView9;
        this.racNameTextView = textView10;
        this.rightArrowIcon = imageView2;
        this.rightSideTextView = textView11;
        this.textViewAcName = textView12;
        this.textViewAcTitle = textView13;
        this.thisYearTextLayout = constraintLayout9;
        this.topLayout = constraintLayout10;
        this.totalBudgetLayout = constraintLayout11;
        this.tvChartXAxisName = chartInfoTextView;
        this.tvChartYAxisName = chartInfoTextView2;
        this.unitPriceLebelTextView = textView14;
        this.weeklyRadioBtn = radioButton4;
        this.yearlyRadioBtn = radioButton5;
    }

    public static FragmentConsumedEnergyGraphBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentConsumedEnergyGraphBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentConsumedEnergyGraphBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_consumed_energy_graph, viewGroup, z, obj);
    }

    public static FragmentConsumedEnergyGraphBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentConsumedEnergyGraphBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentConsumedEnergyGraphBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_consumed_energy_graph, (ViewGroup) null, false, obj);
    }

    public static FragmentConsumedEnergyGraphBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentConsumedEnergyGraphBinding bind(View view, Object obj) {
        return (FragmentConsumedEnergyGraphBinding) bind(obj, view, R.layout.fragment_consumed_energy_graph);
    }
}
