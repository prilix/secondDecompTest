package com.jch.racWiFi.energyConsumption.utility;

import android.content.Context;
import com.accord.common.utils.Logger;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.C1030Utils;
import com.github.mikephil.charting.utils.MPPointF;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.jch_hitachi.aircloudglobal.R;

public class MyMarkerView extends MarkerView {
    private static final String TAG = "MyMarkerView";
    private final String currencySymbol;
    private String currentMonth;
    private final ImageView energy = ((ImageView) findViewById(R.id.energy_icon));
    private boolean lastYearIncluded;
    private final Context mContext;
    private String mFilterType;
    private String mMode;
    private String mMonth;
    private final String[] monthArrayFullName;
    private final TextView monthText = ((TextView) findViewById(R.id.monthText));
    private final TextView tvContent = ((TextView) findViewById(R.id.tvContent1));
    private final String[] weekArrayFullName;
    private String[] xAxisValues;

    public MyMarkerView(Context context, int i, String str) {
        super(context, i);
        this.mContext = context;
        this.currencySymbol = str;
        this.weekArrayFullName = getResources().getStringArray(R.array.week_arry);
        this.monthArrayFullName = getResources().getString(R.string.energyConsumption_list_monthsInYr).split(",");
    }

    public void setxAxisValues(String[] strArr, String str, String str2, String str3, String str4, boolean z) {
        this.xAxisValues = strArr;
        this.mFilterType = str;
        this.mMonth = str3;
        this.currentMonth = str2;
        this.mMode = str4;
        this.lastYearIncluded = z;
    }

    public void refreshContent(Entry entry, Highlight highlight) {
        String str;
        String str2;
        if (entry instanceof CandleEntry) {
            this.tvContent.setText("" + C1030Utils.formatNumber(((CandleEntry) entry).getHigh(), 0, true));
        } else {
            String[] strArr = this.xAxisValues;
            if (strArr == null || ((float) strArr.length) <= entry.getX()) {
                this.monthText.setText(this.mContext.getString(R.string.triple_dash));
            } else {
                int x = (int) entry.getX();
                int i = C18021.f435xeb78465e[QueryFilterType.valueOf(this.mFilterType).ordinal()];
                if (i == 1) {
                    str2 = getDayNameOnIndex(x);
                } else if (i != 2) {
                    String[] strArr2 = this.monthArrayFullName;
                    str2 = strArr2[x] != null ? strArr2[x] : this.xAxisValues[x];
                } else {
                    String str3 = (!this.lastYearIncluded || highlight.getDataSetIndex() != 1) ? this.mMonth : this.currentMonth;
                    if (str3 != null) {
                        str2 = str3 + " " + this.xAxisValues[x];
                    } else {
                        str2 = this.xAxisValues[x];
                    }
                }
                if (str2 != null) {
                    this.monthText.setText(str2);
                }
            }
            String str4 = this.mMode;
            if (str4 != null) {
                if (str4.equals(QueryFilterType.ENERGY.name())) {
                    str = entry.getY() + " " + this.mContext.getString(R.string.energy_unit);
                    this.energy.setVisibility(0);
                } else {
                    str = this.currencySymbol + " " + entry.getY();
                    this.energy.setVisibility(8);
                }
                this.tvContent.setText(str);
            }
        }
        Logger.m45d(TAG, "  x = " + ((int) entry.getX()) + " y = " + entry.getY() + " e = " + entry + " highlight = " + highlight.getDataSetIndex());
        super.refreshContent(entry, highlight);
    }

    /* renamed from: com.jch.racWiFi.energyConsumption.utility.MyMarkerView$1 */
    static /* synthetic */ class C18021 {

        /* renamed from: $SwitchMap$com$jch$racWiFi$energyConsumption$utility$QueryFilterType */
        static final /* synthetic */ int[] f435xeb78465e;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.jch.racWiFi.energyConsumption.utility.QueryFilterType[] r0 = com.jch.racWiFi.energyConsumption.utility.QueryFilterType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f435xeb78465e = r0
                com.jch.racWiFi.energyConsumption.utility.QueryFilterType r1 = com.jch.racWiFi.energyConsumption.utility.QueryFilterType.WEEKLY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f435xeb78465e     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.energyConsumption.utility.QueryFilterType r1 = com.jch.racWiFi.energyConsumption.utility.QueryFilterType.MONTHLY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.energyConsumption.utility.MyMarkerView.C18021.<clinit>():void");
        }
    }

    public MPPointF getOffset() {
        return new MPPointF((float) (-(getWidth() / 2)), (float) (-getHeight()));
    }

    private String getDayNameOnIndex(int i) {
        String[] strArr = this.weekArrayFullName;
        if (strArr == null || strArr.length <= 0) {
            return this.xAxisValues[i];
        }
        return strArr[i];
    }
}
