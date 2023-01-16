package com.jch.racWiFi.customViews.HintCase.customBlock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class TriangleShapeView extends View {
    private static Direction DEFAULT_TRIANGLE_DIRECTION = Direction.UP;
    private int backgroundColor;
    private int borderColor;
    private int borderWith;
    private Direction direction;
    private Paint paintBackground = new Paint();
    private Paint paintLines = new Paint(1);
    private Path pathBackground = new Path();
    private Path pathLines = new Path();
    private int shadowSize;
    private boolean useBorder;

    public enum Direction {
        UP,
        DOWN,
        RIGHT,
        LEFT
    }

    public TriangleShapeView(Context context) {
        super(context);
        init();
    }

    public TriangleShapeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public TriangleShapeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        this.direction = DEFAULT_TRIANGLE_DIRECTION;
        this.useBorder = false;
        this.pathBackground = new Path();
        this.paintBackground = new Paint();
        this.pathLines = new Path();
        Paint paint = new Paint(1);
        this.paintLines = paint;
        paint.setStyle(Paint.Style.STROKE);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = (float) getWidth();
        float height = (float) getHeight();
        drawBackground(canvas, width, height);
        if (this.useBorder) {
            drawBorder(canvas, width, height);
        }
        if (this.direction != Direction.UP) {
            rotateView(this.direction);
        }
    }

    private void drawBorder(Canvas canvas, float f, float f2) {
        this.paintLines.setColor(this.borderColor);
        this.paintLines.setStrokeWidth((float) this.borderWith);
        this.pathLines.reset();
        this.pathLines.moveTo(0.0f, f2);
        float f3 = f / 2.0f;
        float f4 = f2 / 3.0f;
        this.pathLines.lineTo(f3, f4);
        this.pathLines.lineTo(f, f2);
        this.pathLines.lineTo(f3, f4);
        this.pathLines.lineTo(0.0f, f2);
        this.pathLines.close();
        canvas.drawPath(this.pathLines, this.paintLines);
    }

    private void drawBackground(Canvas canvas, float f, float f2) {
        this.paintBackground.setColor(this.backgroundColor);
        this.pathBackground.reset();
        this.pathBackground.moveTo(0.0f, f2);
        this.pathBackground.lineTo(f / 2.0f, f2 / 3.0f);
        this.pathBackground.lineTo(f, f2);
        this.pathBackground.close();
        this.paintBackground.setShadowLayer((float) this.shadowSize, 1.0f, 1.0f, -1);
        setLayerType(1, (Paint) null);
        canvas.drawPath(this.pathBackground, this.paintBackground);
    }

    /* renamed from: com.jch.racWiFi.customViews.HintCase.customBlock.TriangleShapeView$1 */
    static /* synthetic */ class C16961 {

        /* renamed from: $SwitchMap$com$jch$racWiFi$customViews$HintCase$customBlock$TriangleShapeView$Direction */
        static final /* synthetic */ int[] f425x7c0a4e1e;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.jch.racWiFi.customViews.HintCase.customBlock.TriangleShapeView$Direction[] r0 = com.jch.racWiFi.customViews.HintCase.customBlock.TriangleShapeView.Direction.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f425x7c0a4e1e = r0
                com.jch.racWiFi.customViews.HintCase.customBlock.TriangleShapeView$Direction r1 = com.jch.racWiFi.customViews.HintCase.customBlock.TriangleShapeView.Direction.RIGHT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f425x7c0a4e1e     // Catch:{ NoSuchFieldError -> 0x001d }
                com.jch.racWiFi.customViews.HintCase.customBlock.TriangleShapeView$Direction r1 = com.jch.racWiFi.customViews.HintCase.customBlock.TriangleShapeView.Direction.DOWN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f425x7c0a4e1e     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.jch.racWiFi.customViews.HintCase.customBlock.TriangleShapeView$Direction r1 = com.jch.racWiFi.customViews.HintCase.customBlock.TriangleShapeView.Direction.LEFT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.customViews.HintCase.customBlock.TriangleShapeView.C16961.<clinit>():void");
        }
    }

    private void rotateView(Direction direction2) {
        int i = C16961.f425x7c0a4e1e[direction2.ordinal()];
        if (i == 1) {
            setRotation(90.0f);
        } else if (i == 2) {
            setRotation(180.0f);
        } else if (i == 3) {
            setRotation(270.0f);
        }
    }

    public void setBackgroundColor(int i) {
        this.backgroundColor = i;
    }

    public void setBorder(int i, int i2) {
        this.useBorder = true;
        this.borderWith = i;
        this.borderColor = i2;
    }

    public void setDirection(Direction direction2) {
        this.direction = direction2;
    }

    public void setShadowSize(int i) {
        this.shadowSize = i;
    }
}
