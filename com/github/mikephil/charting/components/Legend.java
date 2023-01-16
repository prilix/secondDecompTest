package com.github.mikephil.charting.components;

import android.graphics.DashPathEffect;
import android.graphics.Paint;
import com.github.mikephil.charting.utils.C1030Utils;
import com.github.mikephil.charting.utils.FSize;
import java.util.ArrayList;
import java.util.List;

public class Legend extends ComponentBase {
    private List<Boolean> mCalculatedLabelBreakPoints;
    private List<FSize> mCalculatedLabelSizes;
    private List<FSize> mCalculatedLineSizes;
    private LegendDirection mDirection;
    private boolean mDrawInside;
    private LegendEntry[] mEntries;
    private LegendEntry[] mExtraEntries;
    private DashPathEffect mFormLineDashEffect;
    private float mFormLineWidth;
    private float mFormSize;
    private float mFormToTextSpace;
    private LegendHorizontalAlignment mHorizontalAlignment;
    private boolean mIsLegendCustom;
    private float mMaxSizePercent;
    public float mNeededHeight;
    public float mNeededWidth;
    private LegendOrientation mOrientation;
    private LegendForm mShape;
    private float mStackSpace;
    public float mTextHeightMax;
    public float mTextWidthMax;
    private LegendVerticalAlignment mVerticalAlignment;
    private boolean mWordWrapEnabled;
    private float mXEntrySpace;
    private float mYEntrySpace;

    public enum LegendDirection {
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT
    }

    public enum LegendForm {
        NONE,
        EMPTY,
        DEFAULT,
        SQUARE,
        CIRCLE,
        LINE
    }

    public enum LegendHorizontalAlignment {
        LEFT,
        CENTER,
        RIGHT
    }

    public enum LegendOrientation {
        HORIZONTAL,
        VERTICAL
    }

    public enum LegendVerticalAlignment {
        TOP,
        CENTER,
        BOTTOM
    }

    public Legend() {
        this.mEntries = new LegendEntry[0];
        this.mIsLegendCustom = false;
        this.mHorizontalAlignment = LegendHorizontalAlignment.LEFT;
        this.mVerticalAlignment = LegendVerticalAlignment.BOTTOM;
        this.mOrientation = LegendOrientation.HORIZONTAL;
        this.mDrawInside = false;
        this.mDirection = LegendDirection.LEFT_TO_RIGHT;
        this.mShape = LegendForm.SQUARE;
        this.mFormSize = 8.0f;
        this.mFormLineWidth = 3.0f;
        this.mFormLineDashEffect = null;
        this.mXEntrySpace = 6.0f;
        this.mYEntrySpace = 0.0f;
        this.mFormToTextSpace = 5.0f;
        this.mStackSpace = 3.0f;
        this.mMaxSizePercent = 0.95f;
        this.mNeededWidth = 0.0f;
        this.mNeededHeight = 0.0f;
        this.mTextHeightMax = 0.0f;
        this.mTextWidthMax = 0.0f;
        this.mWordWrapEnabled = false;
        this.mCalculatedLabelSizes = new ArrayList(16);
        this.mCalculatedLabelBreakPoints = new ArrayList(16);
        this.mCalculatedLineSizes = new ArrayList(16);
        this.mTextSize = C1030Utils.convertDpToPixel(10.0f);
        this.mXOffset = C1030Utils.convertDpToPixel(5.0f);
        this.mYOffset = C1030Utils.convertDpToPixel(3.0f);
    }

    public Legend(LegendEntry[] legendEntryArr) {
        this();
        if (legendEntryArr != null) {
            this.mEntries = legendEntryArr;
            return;
        }
        throw new IllegalArgumentException("entries array is NULL");
    }

    public void setEntries(List<LegendEntry> list) {
        this.mEntries = (LegendEntry[]) list.toArray(new LegendEntry[list.size()]);
    }

    public LegendEntry[] getEntries() {
        return this.mEntries;
    }

    public float getMaximumEntryWidth(Paint paint) {
        float convertDpToPixel = C1030Utils.convertDpToPixel(this.mFormToTextSpace);
        float f = 0.0f;
        float f2 = 0.0f;
        for (LegendEntry legendEntry : this.mEntries) {
            float convertDpToPixel2 = C1030Utils.convertDpToPixel(Float.isNaN(legendEntry.formSize) ? this.mFormSize : legendEntry.formSize);
            if (convertDpToPixel2 > f2) {
                f2 = convertDpToPixel2;
            }
            String str = legendEntry.label;
            if (str != null) {
                float calcTextWidth = (float) C1030Utils.calcTextWidth(paint, str);
                if (calcTextWidth > f) {
                    f = calcTextWidth;
                }
            }
        }
        return f + f2 + convertDpToPixel;
    }

    public float getMaximumEntryHeight(Paint paint) {
        float f = 0.0f;
        for (LegendEntry legendEntry : this.mEntries) {
            String str = legendEntry.label;
            if (str != null) {
                float calcTextHeight = (float) C1030Utils.calcTextHeight(paint, str);
                if (calcTextHeight > f) {
                    f = calcTextHeight;
                }
            }
        }
        return f;
    }

    public LegendEntry[] getExtraEntries() {
        return this.mExtraEntries;
    }

    public void setExtra(List<LegendEntry> list) {
        this.mExtraEntries = (LegendEntry[]) list.toArray(new LegendEntry[list.size()]);
    }

    public void setExtra(LegendEntry[] legendEntryArr) {
        if (legendEntryArr == null) {
            legendEntryArr = new LegendEntry[0];
        }
        this.mExtraEntries = legendEntryArr;
    }

    public void setExtra(int[] iArr, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < Math.min(iArr.length, strArr.length); i++) {
            LegendEntry legendEntry = new LegendEntry();
            legendEntry.formColor = iArr[i];
            legendEntry.label = strArr[i];
            if (legendEntry.formColor == 1122868 || legendEntry.formColor == 0) {
                legendEntry.form = LegendForm.NONE;
            } else if (legendEntry.formColor == 1122867) {
                legendEntry.form = LegendForm.EMPTY;
            }
            arrayList.add(legendEntry);
        }
        this.mExtraEntries = (LegendEntry[]) arrayList.toArray(new LegendEntry[arrayList.size()]);
    }

    public void setCustom(LegendEntry[] legendEntryArr) {
        this.mEntries = legendEntryArr;
        this.mIsLegendCustom = true;
    }

    public void setCustom(List<LegendEntry> list) {
        this.mEntries = (LegendEntry[]) list.toArray(new LegendEntry[list.size()]);
        this.mIsLegendCustom = true;
    }

    public void resetCustom() {
        this.mIsLegendCustom = false;
    }

    public boolean isLegendCustom() {
        return this.mIsLegendCustom;
    }

    public LegendHorizontalAlignment getHorizontalAlignment() {
        return this.mHorizontalAlignment;
    }

    public void setHorizontalAlignment(LegendHorizontalAlignment legendHorizontalAlignment) {
        this.mHorizontalAlignment = legendHorizontalAlignment;
    }

    public LegendVerticalAlignment getVerticalAlignment() {
        return this.mVerticalAlignment;
    }

    public void setVerticalAlignment(LegendVerticalAlignment legendVerticalAlignment) {
        this.mVerticalAlignment = legendVerticalAlignment;
    }

    public LegendOrientation getOrientation() {
        return this.mOrientation;
    }

    public void setOrientation(LegendOrientation legendOrientation) {
        this.mOrientation = legendOrientation;
    }

    public boolean isDrawInsideEnabled() {
        return this.mDrawInside;
    }

    public void setDrawInside(boolean z) {
        this.mDrawInside = z;
    }

    public LegendDirection getDirection() {
        return this.mDirection;
    }

    public void setDirection(LegendDirection legendDirection) {
        this.mDirection = legendDirection;
    }

    public LegendForm getForm() {
        return this.mShape;
    }

    public void setForm(LegendForm legendForm) {
        this.mShape = legendForm;
    }

    public void setFormSize(float f) {
        this.mFormSize = f;
    }

    public float getFormSize() {
        return this.mFormSize;
    }

    public void setFormLineWidth(float f) {
        this.mFormLineWidth = f;
    }

    public float getFormLineWidth() {
        return this.mFormLineWidth;
    }

    public void setFormLineDashEffect(DashPathEffect dashPathEffect) {
        this.mFormLineDashEffect = dashPathEffect;
    }

    public DashPathEffect getFormLineDashEffect() {
        return this.mFormLineDashEffect;
    }

    public float getXEntrySpace() {
        return this.mXEntrySpace;
    }

    public void setXEntrySpace(float f) {
        this.mXEntrySpace = f;
    }

    public float getYEntrySpace() {
        return this.mYEntrySpace;
    }

    public void setYEntrySpace(float f) {
        this.mYEntrySpace = f;
    }

    public float getFormToTextSpace() {
        return this.mFormToTextSpace;
    }

    public void setFormToTextSpace(float f) {
        this.mFormToTextSpace = f;
    }

    public float getStackSpace() {
        return this.mStackSpace;
    }

    public void setStackSpace(float f) {
        this.mStackSpace = f;
    }

    public void setWordWrapEnabled(boolean z) {
        this.mWordWrapEnabled = z;
    }

    public boolean isWordWrapEnabled() {
        return this.mWordWrapEnabled;
    }

    public float getMaxSizePercent() {
        return this.mMaxSizePercent;
    }

    public void setMaxSizePercent(float f) {
        this.mMaxSizePercent = f;
    }

    public List<FSize> getCalculatedLabelSizes() {
        return this.mCalculatedLabelSizes;
    }

    public List<Boolean> getCalculatedLabelBreakPoints() {
        return this.mCalculatedLabelBreakPoints;
    }

    public List<FSize> getCalculatedLineSizes() {
        return this.mCalculatedLineSizes;
    }

    /* JADX WARNING: Removed duplicated region for block: B:87:0x01db  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void calculateDimensions(android.graphics.Paint r28, com.github.mikephil.charting.utils.ViewPortHandler r29) {
        /*
            r27 = this;
            r0 = r27
            r1 = r28
            float r2 = r0.mFormSize
            float r2 = com.github.mikephil.charting.utils.C1030Utils.convertDpToPixel(r2)
            float r3 = r0.mStackSpace
            float r3 = com.github.mikephil.charting.utils.C1030Utils.convertDpToPixel(r3)
            float r4 = r0.mFormToTextSpace
            float r4 = com.github.mikephil.charting.utils.C1030Utils.convertDpToPixel(r4)
            float r5 = r0.mXEntrySpace
            float r5 = com.github.mikephil.charting.utils.C1030Utils.convertDpToPixel(r5)
            float r6 = r0.mYEntrySpace
            float r6 = com.github.mikephil.charting.utils.C1030Utils.convertDpToPixel(r6)
            boolean r7 = r0.mWordWrapEnabled
            com.github.mikephil.charting.components.LegendEntry[] r8 = r0.mEntries
            int r9 = r8.length
            float r10 = r27.getMaximumEntryWidth(r28)
            r0.mTextWidthMax = r10
            float r10 = r27.getMaximumEntryHeight(r28)
            r0.mTextHeightMax = r10
            int[] r10 = com.github.mikephil.charting.components.Legend.C10231.f175x9c9dbef
            com.github.mikephil.charting.components.Legend$LegendOrientation r11 = r0.mOrientation
            int r11 = r11.ordinal()
            r10 = r10[r11]
            r12 = 1
            if (r10 == r12) goto L_0x017a
            r14 = 2
            if (r10 == r14) goto L_0x0045
            goto L_0x01f7
        L_0x0045:
            float r10 = com.github.mikephil.charting.utils.C1030Utils.getLineHeight(r28)
            float r14 = com.github.mikephil.charting.utils.C1030Utils.getLineSpacing(r28)
            float r14 = r14 + r6
            float r6 = r29.contentWidth()
            float r15 = r0.mMaxSizePercent
            float r6 = r6 * r15
            java.util.List<java.lang.Boolean> r15 = r0.mCalculatedLabelBreakPoints
            r15.clear()
            java.util.List<com.github.mikephil.charting.utils.FSize> r15 = r0.mCalculatedLabelSizes
            r15.clear()
            java.util.List<com.github.mikephil.charting.utils.FSize> r15 = r0.mCalculatedLineSizes
            r15.clear()
            r12 = 0
            r13 = -1
            r17 = 0
            r19 = 0
            r20 = 0
        L_0x006d:
            if (r12 >= r9) goto L_0x014f
            r15 = r8[r12]
            com.github.mikephil.charting.components.Legend$LegendForm r11 = r15.form
            r22 = r2
            com.github.mikephil.charting.components.Legend$LegendForm r2 = com.github.mikephil.charting.components.Legend.LegendForm.NONE
            if (r11 == r2) goto L_0x007b
            r2 = 1
            goto L_0x007c
        L_0x007b:
            r2 = 0
        L_0x007c:
            float r11 = r15.formSize
            boolean r11 = java.lang.Float.isNaN(r11)
            if (r11 == 0) goto L_0x0087
            r11 = r22
            goto L_0x008d
        L_0x0087:
            float r11 = r15.formSize
            float r11 = com.github.mikephil.charting.utils.C1030Utils.convertDpToPixel(r11)
        L_0x008d:
            java.lang.String r15 = r15.label
            r23 = r5
            java.util.List<java.lang.Boolean> r5 = r0.mCalculatedLabelBreakPoints
            r24 = r8
            r21 = 0
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r21)
            r5.add(r8)
            r5 = -1
            if (r13 != r5) goto L_0x00a3
            r5 = 0
            goto L_0x00a5
        L_0x00a3:
            float r5 = r19 + r3
        L_0x00a5:
            if (r15 == 0) goto L_0x00c5
            java.util.List<com.github.mikephil.charting.utils.FSize> r8 = r0.mCalculatedLabelSizes
            r19 = r3
            com.github.mikephil.charting.utils.FSize r3 = com.github.mikephil.charting.utils.C1030Utils.calcTextSize(r1, r15)
            r8.add(r3)
            if (r2 == 0) goto L_0x00b7
            float r2 = r4 + r11
            goto L_0x00b8
        L_0x00b7:
            r2 = 0
        L_0x00b8:
            float r5 = r5 + r2
            java.util.List<com.github.mikephil.charting.utils.FSize> r2 = r0.mCalculatedLabelSizes
            java.lang.Object r2 = r2.get(r12)
            com.github.mikephil.charting.utils.FSize r2 = (com.github.mikephil.charting.utils.FSize) r2
            float r2 = r2.width
            float r5 = r5 + r2
            goto L_0x00de
        L_0x00c5:
            r19 = r3
            java.util.List<com.github.mikephil.charting.utils.FSize> r3 = r0.mCalculatedLabelSizes
            r25 = r11
            r8 = 0
            com.github.mikephil.charting.utils.FSize r11 = com.github.mikephil.charting.utils.FSize.getInstance(r8, r8)
            r3.add(r11)
            if (r2 == 0) goto L_0x00d8
            r11 = r25
            goto L_0x00d9
        L_0x00d8:
            r11 = 0
        L_0x00d9:
            float r5 = r5 + r11
            r2 = -1
            if (r13 != r2) goto L_0x00de
            r13 = r12
        L_0x00de:
            if (r15 != 0) goto L_0x00e4
            int r2 = r9 + -1
            if (r12 != r2) goto L_0x013e
        L_0x00e4:
            r2 = r20
            r8 = 0
            int r3 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r3 != 0) goto L_0x00ed
            r11 = 0
            goto L_0x00ef
        L_0x00ed:
            r11 = r23
        L_0x00ef:
            if (r7 == 0) goto L_0x011f
            if (r3 == 0) goto L_0x011f
            float r3 = r6 - r2
            float r18 = r11 + r5
            int r3 = (r3 > r18 ? 1 : (r3 == r18 ? 0 : -1))
            if (r3 < 0) goto L_0x00fc
            goto L_0x011f
        L_0x00fc:
            java.util.List<com.github.mikephil.charting.utils.FSize> r3 = r0.mCalculatedLineSizes
            com.github.mikephil.charting.utils.FSize r11 = com.github.mikephil.charting.utils.FSize.getInstance(r2, r10)
            r3.add(r11)
            r3 = r17
            float r2 = java.lang.Math.max(r3, r2)
            java.util.List<java.lang.Boolean> r3 = r0.mCalculatedLabelBreakPoints
            r11 = -1
            if (r13 <= r11) goto L_0x0112
            r8 = r13
            goto L_0x0113
        L_0x0112:
            r8 = r12
        L_0x0113:
            r16 = 1
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r16)
            r3.set(r8, r11)
            r3 = r5
            r8 = -1
            goto L_0x0128
        L_0x011f:
            r3 = r17
            r8 = -1
            float r11 = r11 + r5
            float r20 = r2 + r11
            r2 = r3
            r3 = r20
        L_0x0128:
            int r11 = r9 + -1
            if (r12 != r11) goto L_0x013a
            java.util.List<com.github.mikephil.charting.utils.FSize> r11 = r0.mCalculatedLineSizes
            com.github.mikephil.charting.utils.FSize r8 = com.github.mikephil.charting.utils.FSize.getInstance(r3, r10)
            r11.add(r8)
            float r17 = java.lang.Math.max(r2, r3)
            goto L_0x013c
        L_0x013a:
            r17 = r2
        L_0x013c:
            r20 = r3
        L_0x013e:
            if (r15 == 0) goto L_0x0141
            r13 = -1
        L_0x0141:
            int r12 = r12 + 1
            r3 = r19
            r2 = r22
            r8 = r24
            r19 = r5
            r5 = r23
            goto L_0x006d
        L_0x014f:
            r3 = r17
            r21 = 0
            r0.mNeededWidth = r3
            java.util.List<com.github.mikephil.charting.utils.FSize> r1 = r0.mCalculatedLineSizes
            int r1 = r1.size()
            float r1 = (float) r1
            float r10 = r10 * r1
            java.util.List<com.github.mikephil.charting.utils.FSize> r1 = r0.mCalculatedLineSizes
            int r1 = r1.size()
            if (r1 != 0) goto L_0x0168
            r11 = 0
            goto L_0x0172
        L_0x0168:
            java.util.List<com.github.mikephil.charting.utils.FSize> r1 = r0.mCalculatedLineSizes
            int r1 = r1.size()
            r16 = 1
            int r11 = r1 + -1
        L_0x0172:
            float r1 = (float) r11
            float r14 = r14 * r1
            float r10 = r10 + r14
            r0.mNeededHeight = r10
            goto L_0x01f7
        L_0x017a:
            r22 = r2
            r19 = r3
            r24 = r8
            r16 = 1
            r21 = 0
            float r2 = com.github.mikephil.charting.utils.C1030Utils.getLineHeight(r28)
            r3 = 0
            r5 = 0
            r7 = 0
            r8 = 0
            r10 = 0
        L_0x018d:
            if (r7 >= r9) goto L_0x01f3
            r11 = r24[r7]
            com.github.mikephil.charting.components.Legend$LegendForm r12 = r11.form
            com.github.mikephil.charting.components.Legend$LegendForm r13 = com.github.mikephil.charting.components.Legend.LegendForm.NONE
            if (r12 == r13) goto L_0x0199
            r12 = 1
            goto L_0x019a
        L_0x0199:
            r12 = 0
        L_0x019a:
            float r13 = r11.formSize
            boolean r13 = java.lang.Float.isNaN(r13)
            if (r13 == 0) goto L_0x01a5
            r13 = r22
            goto L_0x01ab
        L_0x01a5:
            float r13 = r11.formSize
            float r13 = com.github.mikephil.charting.utils.C1030Utils.convertDpToPixel(r13)
        L_0x01ab:
            java.lang.String r11 = r11.label
            if (r10 != 0) goto L_0x01b0
            r5 = 0
        L_0x01b0:
            if (r12 == 0) goto L_0x01b7
            if (r10 == 0) goto L_0x01b6
            float r5 = r5 + r19
        L_0x01b6:
            float r5 = r5 + r13
        L_0x01b7:
            if (r11 == 0) goto L_0x01e4
            if (r12 == 0) goto L_0x01bf
            if (r10 != 0) goto L_0x01bf
            float r5 = r5 + r4
            goto L_0x01cc
        L_0x01bf:
            if (r10 == 0) goto L_0x01cc
            float r8 = java.lang.Math.max(r8, r5)
            float r5 = r2 + r6
            float r3 = r3 + r5
            r5 = r8
            r8 = 0
            r10 = 0
            goto L_0x01d1
        L_0x01cc:
            r26 = r8
            r8 = r5
            r5 = r26
        L_0x01d1:
            int r11 = com.github.mikephil.charting.utils.C1030Utils.calcTextWidth(r1, r11)
            float r11 = (float) r11
            float r8 = r8 + r11
            int r11 = r9 + -1
            if (r7 >= r11) goto L_0x01de
            float r11 = r2 + r6
            float r3 = r3 + r11
        L_0x01de:
            r26 = r8
            r8 = r5
            r5 = r26
            goto L_0x01ec
        L_0x01e4:
            float r5 = r5 + r13
            int r10 = r9 + -1
            if (r7 >= r10) goto L_0x01eb
            float r5 = r5 + r19
        L_0x01eb:
            r10 = 1
        L_0x01ec:
            float r8 = java.lang.Math.max(r8, r5)
            int r7 = r7 + 1
            goto L_0x018d
        L_0x01f3:
            r0.mNeededWidth = r8
            r0.mNeededHeight = r3
        L_0x01f7:
            float r1 = r0.mNeededHeight
            float r2 = r0.mYOffset
            float r1 = r1 + r2
            r0.mNeededHeight = r1
            float r1 = r0.mNeededWidth
            float r2 = r0.mXOffset
            float r1 = r1 + r2
            r0.mNeededWidth = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.components.Legend.calculateDimensions(android.graphics.Paint, com.github.mikephil.charting.utils.ViewPortHandler):void");
    }

    /* renamed from: com.github.mikephil.charting.components.Legend$1 */
    static /* synthetic */ class C10231 {

        /* renamed from: $SwitchMap$com$github$mikephil$charting$components$Legend$LegendOrientation */
        static final /* synthetic */ int[] f175x9c9dbef;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.github.mikephil.charting.components.Legend$LegendOrientation[] r0 = com.github.mikephil.charting.components.Legend.LegendOrientation.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f175x9c9dbef = r0
                com.github.mikephil.charting.components.Legend$LegendOrientation r1 = com.github.mikephil.charting.components.Legend.LegendOrientation.VERTICAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f175x9c9dbef     // Catch:{ NoSuchFieldError -> 0x001d }
                com.github.mikephil.charting.components.Legend$LegendOrientation r1 = com.github.mikephil.charting.components.Legend.LegendOrientation.HORIZONTAL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.components.Legend.C10231.<clinit>():void");
        }
    }
}
