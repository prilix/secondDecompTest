package com.jch.racWiFi.tooltip;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.IBinder;
import android.text.Spannable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.text.HtmlCompat;
import com.jch.racWiFi.C1655R;
import com.jch.racWiFi.energyConsumption.model.request.ECSettings$$ExternalSyntheticBackport0;
import com.jch_hitachi.aircloudglobal.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo36737d1 = {"\u0000ð\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0018\u0018\u00002\u00020\u0001:\n\u0001\u0001\u0001\u0001\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010e\u001a\u00020\u001f2\u0006\u0010f\u001a\u00020\u001fH\u0002J\u0010\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020jH\u0003J\u0006\u0010k\u001a\u000200J+\u0010l\u001a\u00020\u00002#\u0010m\u001a\u001f\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0004\u0012\u000200\u0018\u00010,J+\u0010n\u001a\u00020\u00002#\u0010m\u001a\u001f\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0004\u0012\u000200\u0018\u00010,J+\u0010o\u001a\u00020\u00002#\u0010m\u001a\u001f\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0004\u0012\u000200\u0018\u00010,J+\u0010p\u001a\u00020\u00002#\u0010m\u001a\u001f\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0004\u0012\u000200\u0018\u00010,J\b\u0010q\u001a\u000200H\u0002J\b\u0010r\u001a\u000200H\u0002JD\u0010s\u001a\u0004\u0018\u00010&2\u0006\u0010t\u001a\u00020\n2\b\u0010u\u001a\u0004\u0018\u00010\n2\u0006\u0010v\u001a\u00020\u001b2\f\u0010w\u001a\b\u0012\u0004\u0012\u00020\u000e0x2\u0006\u0010y\u001a\u00020h2\b\b\u0002\u0010z\u001a\u00020\u0013H\u0002J\u0006\u0010{\u001a\u000200J\u0014\u0010|\u001a\u0004\u0018\u00010\u00002\b\u0010}\u001a\u0004\u0018\u00010&H\u0002J\u0017\u0010~\u001a\u0002002\u0006\u0010\u001a\u00020K2\u0007\u0010\u0001\u001a\u00020KJ\u0018\u0010\u0001\u001a\u0002002\u0006\u0010\u001a\u00020K2\u0007\u0010\u0001\u001a\u00020KJ\u0019\u0010\u0001\u001a\u0002002\u0006\u0010y\u001a\u00020h2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002J\t\u0010\u0001\u001a\u000200H\u0002J\u0014\u0010\u0001\u001a\u0002002\t\u0010\u0001\u001a\u0004\u0018\u00010\nH\u0002J\u0011\u0010\u0001\u001a\u0002002\u0006\u0010\u000f\u001a\u00020\u000eH\u0002J\u0012\u0010\u0001\u001a\u0002002\u0007\u0010\u0001\u001a\u00020\nH\u0002J!\u0010\u0001\u001a\u0002002\u0006\u0010t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010z\u001a\u00020\u0013J\u0011\u0010\u0001\u001a\u0002002\b\u0010`\u001a\u0004\u0018\u00010NJ\u0012\u0010\u0001\u001a\u0002002\t\b\u0001\u0010\u0001\u001a\u00020\u001fR\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R$\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n8F@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R$\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\t\u001a\u0004\u0018\u00010\u000e8F@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\u0013@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u001dX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u001fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u001fX\u000e¢\u0006\u0002\n\u0000R+\u0010+\u001a\u001f\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0004\u0012\u000200\u0018\u00010,X\u000e¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u0004\u0018\u000102X\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u0014\u00104\u001a\b\u0012\u0004\u0012\u00020\u000e05X\u0004¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000207X\u0004¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R+\u00109\u001a\u001f\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0004\u0012\u000200\u0018\u00010,X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u0013XD¢\u0006\u0002\n\u0000R\u0012\u0010<\u001a\u0004\u0018\u00010\u001fX\u000e¢\u0006\u0004\n\u0002\u0010=R\u000e\u0010>\u001a\u00020?X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010@\u001a\u0004\u0018\u00010?X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u001fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u001fX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010C\u001a\b\u0018\u00010DR\u00020\u0000X\u000e¢\u0006\u0002\n\u0000R+\u0010E\u001a\u001f\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0004\u0012\u000200\u0018\u00010,X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R+\u0010I\u001a\u001f\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0004\u0012\u000200\u0018\u00010,X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020KX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010L\u001a\u00020\u001fXD¢\u0006\u0002\n\u0000R\u0010\u0010M\u001a\u0004\u0018\u00010NX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020\u001fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020QX.¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020\u001fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020\u001fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010T\u001a\u0004\u0018\u00010UX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010V\u001a\u0004\u0018\u00010WX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010X\u001a\u00020\u001fXD¢\u0006\u0002\n\u0000R\u0011\u0010Y\u001a\u00020K8F¢\u0006\u0006\u001a\u0004\bZ\u0010[R\u0011\u0010\\\u001a\u00020K8F¢\u0006\u0006\u001a\u0004\b]\u0010[R\u000e\u0010^\u001a\u00020_X\u000e¢\u0006\u0002\n\u0000R$\u0010`\u001a\u0004\u0018\u00010N2\b\u0010\t\u001a\u0004\u0018\u00010N8F@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\ba\u0010bR\u000e\u0010c\u001a\u00020dX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0001"}, mo36738d2 = {"Lcom/jch/racWiFi/tooltip/Tooltip;", "", "context", "Landroid/content/Context;", "builder", "Lcom/jch/racWiFi/tooltip/Tooltip$Builder;", "(Landroid/content/Context;Lcom/jch/racWiFi/tooltip/Tooltip$Builder;)V", "activateRunnable", "Ljava/lang/Runnable;", "<set-?>", "Landroid/view/View;", "contentView", "getContentView", "()Landroid/view/View;", "Lcom/jch/racWiFi/tooltip/Tooltip$Gravity;", "gravity", "getGravity", "()Lcom/jch/racWiFi/tooltip/Tooltip$Gravity;", "hideRunnable", "", "isShowing", "()Z", "isVisible", "mActivateDelay", "", "mActivated", "mAnchorPoint", "Landroid/graphics/Point;", "mAnchorView", "Ljava/lang/ref/WeakReference;", "mAnimationStyleResId", "", "mAnimator", "Landroid/animation/ValueAnimator;", "mClosePolicy", "Lcom/jch/racWiFi/tooltip/ClosePolicy;", "mContentView", "mCurrentPosition", "Lcom/jch/racWiFi/tooltip/Tooltip$Positions;", "mDrawable", "Lcom/jch/racWiFi/tooltip/TooltipTextDrawable;", "mEnterAnimation", "mExitAnimation", "mFailureFunc", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "tooltip", "", "mFloatingAnimation", "Lcom/jch/racWiFi/tooltip/Tooltip$Animation;", "mFollowAnchor", "mGravities", "", "mHandler", "Landroid/os/Handler;", "mHasAnchorView", "mHiddenFunc", "mIsCustomView", "mLayoutInsetDecor", "mMaxWidth", "Ljava/lang/Integer;", "mNewLocation", "", "mOldLocation", "mOverlayStyle", "mPadding", "mPopupView", "Lcom/jch/racWiFi/tooltip/Tooltip$TooltipViewContainer;", "mPrepareFun", "mShowArrow", "mShowDuration", "mShowOverlay", "mShownFunc", "mSizeTolerance", "", "mSoftInputMode", "mText", "", "mTextStyleResId", "mTextView", "Landroid/widget/TextView;", "mTextViewIdRes", "mTooltipLayoutIdRes", "mTypeface", "Landroid/graphics/Typeface;", "mViewOverlay", "Lcom/jch/racWiFi/tooltip/TooltipOverlay;", "mWindowLayoutType", "offsetX", "getOffsetX", "()F", "offsetY", "getOffsetY", "predrawListener", "Landroid/view/ViewTreeObserver$OnPreDrawListener;", "text", "getText", "()Ljava/lang/CharSequence;", "windowManager", "Landroid/view/WindowManager;", "computeFlags", "curFlags", "createPopupLayoutParams", "Landroid/view/WindowManager$LayoutParams;", "token", "Landroid/os/IBinder;", "dismiss", "doOnFailure", "func", "doOnHidden", "doOnPrepare", "doOnShown", "fadeIn", "fadeOut", "findPosition", "parent", "anchor", "offset", "gravities", "Ljava/util/ArrayList;", "params", "fitToScreen", "hide", "invokePopup", "positions", "offsetBy", "xoff", "yoff", "offsetTo", "preparePopup", "removeCallbacks", "removeListeners", "anchorView", "setupAnimation", "setupListeners", "show", "update", "res", "Animation", "Builder", "Gravity", "Positions", "TooltipViewContainer", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
/* compiled from: Tooltip.kt */
public final class Tooltip {
    /* access modifiers changed from: private */
    public final Runnable activateRunnable;
    private View contentView;
    private final Context context;
    private Gravity gravity;
    /* access modifiers changed from: private */
    public final Runnable hideRunnable;
    private boolean isShowing;
    /* access modifiers changed from: private */
    public boolean isVisible;
    /* access modifiers changed from: private */
    public long mActivateDelay;
    /* access modifiers changed from: private */
    public boolean mActivated;
    private Point mAnchorPoint;
    private WeakReference<View> mAnchorView;
    private int mAnimationStyleResId;
    /* access modifiers changed from: private */
    public ValueAnimator mAnimator;
    /* access modifiers changed from: private */
    public ClosePolicy mClosePolicy;
    private View mContentView;
    private Positions mCurrentPosition;
    private TooltipTextDrawable mDrawable;
    private int mEnterAnimation;
    private int mExitAnimation;
    private Function1<? super Tooltip, Unit> mFailureFunc;
    private Animation mFloatingAnimation;
    private boolean mFollowAnchor;
    private final List<Gravity> mGravities;
    /* access modifiers changed from: private */
    public final Handler mHandler;
    private boolean mHasAnchorView;
    private Function1<? super Tooltip, Unit> mHiddenFunc;
    private boolean mIsCustomView;
    private final boolean mLayoutInsetDecor;
    private Integer mMaxWidth;
    private int[] mNewLocation;
    private int[] mOldLocation;
    private int mOverlayStyle;
    private int mPadding;
    private TooltipViewContainer mPopupView;
    private Function1<? super Tooltip, Unit> mPrepareFun;
    private boolean mShowArrow;
    /* access modifiers changed from: private */
    public long mShowDuration;
    private boolean mShowOverlay;
    private Function1<? super Tooltip, Unit> mShownFunc;
    private final float mSizeTolerance;
    private final int mSoftInputMode;
    private CharSequence mText;
    private int mTextStyleResId;
    /* access modifiers changed from: private */
    public TextView mTextView;
    private int mTextViewIdRes;
    private int mTooltipLayoutIdRes;
    private Typeface mTypeface;
    private TooltipOverlay mViewOverlay;
    private final int mWindowLayoutType;
    private ViewTreeObserver.OnPreDrawListener predrawListener;
    private CharSequence text;
    private final WindowManager windowManager;

    @Metadata(mo36737d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, mo36738d2 = {"Lcom/jch/racWiFi/tooltip/Tooltip$Gravity;", "", "(Ljava/lang/String;I)V", "LEFT", "RIGHT", "TOP", "BOTTOM", "CENTER", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
    /* compiled from: Tooltip.kt */
    public enum Gravity {
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        CENTER
    }

    @Metadata(mo36739k = 3, mo36740mv = {1, 5, 1}, mo36742xi = 48)
    /* compiled from: Tooltip.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Gravity.values().length];
            iArr[Gravity.LEFT.ordinal()] = 1;
            iArr[Gravity.RIGHT.ordinal()] = 2;
            iArr[Gravity.TOP.ordinal()] = 3;
            iArr[Gravity.BOTTOM.ordinal()] = 4;
            iArr[Gravity.CENTER.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ Tooltip(Context context2, Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(context2, builder);
    }

    private Tooltip(Context context2, Builder builder) {
        int i;
        Unit unit;
        this.context = context2;
        Object systemService = context2.getSystemService("window");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        this.windowManager = (WindowManager) systemService;
        Gravity[] values = Gravity.values();
        Collection arrayList = new ArrayList();
        int length = values.length;
        boolean z = false;
        int i2 = 0;
        while (true) {
            boolean z2 = true;
            if (i2 >= length) {
                break;
            }
            Gravity gravity2 = values[i2];
            if (gravity2 == Gravity.CENTER ? false : z2) {
                arrayList.add(gravity2);
            }
            i2++;
        }
        this.mGravities = (List) arrayList;
        this.mSizeTolerance = this.context.getResources().getDisplayMetrics().density * ((float) 10);
        this.mLayoutInsetDecor = true;
        this.mWindowLayoutType = 1000;
        this.mSoftInputMode = 2;
        this.mHandler = new Handler();
        this.mTooltipLayoutIdRes = R.layout.tooltip_textview;
        this.mTextViewIdRes = 16908308;
        this.hideRunnable = new Tooltip$$ExternalSyntheticLambda1(this);
        this.activateRunnable = new Tooltip$$ExternalSyntheticLambda2(this);
        this.predrawListener = new Tooltip$$ExternalSyntheticLambda0(this);
        Unit unit2 = null;
        TypedArray obtainStyledAttributes = this.context.getTheme().obtainStyledAttributes((AttributeSet) null, C1655R.styleable.TooltipLayout, builder.getDefStyleAttr$app_release(), builder.getDefStyleRes$app_release());
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.theme\n          …defStyleRes\n            )");
        this.mPadding = obtainStyledAttributes.getDimensionPixelSize(6, 30);
        this.mOverlayStyle = obtainStyledAttributes.getResourceId(5, R.style.ToolTipOverlayDefaultStyle);
        if (builder.getAnimationStyle$app_release() != null) {
            Integer animationStyle$app_release = builder.getAnimationStyle$app_release();
            Intrinsics.checkNotNull(animationStyle$app_release);
            i = animationStyle$app_release.intValue();
        } else {
            i = obtainStyledAttributes.getResourceId(0, 16973828);
        }
        this.mAnimationStyleResId = i;
        TypedArray obtainStyledAttributes2 = this.context.getTheme().obtainStyledAttributes(this.mAnimationStyleResId, new int[]{16842932, 16842933});
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes2, "context.theme.obtainStyl…ttr.windowExitAnimation))");
        this.mEnterAnimation = obtainStyledAttributes2.getResourceId(obtainStyledAttributes2.getIndex(0), 0);
        this.mExitAnimation = obtainStyledAttributes2.getResourceId(obtainStyledAttributes2.getIndex(1), 0);
        obtainStyledAttributes2.recycle();
        String string = obtainStyledAttributes.getString(4);
        this.mTextStyleResId = obtainStyledAttributes.getResourceId(9, 0);
        obtainStyledAttributes.recycle();
        this.mText = builder.getText$app_release();
        this.mActivateDelay = builder.getActivateDelay$app_release();
        Point point$app_release = builder.getPoint$app_release();
        Intrinsics.checkNotNull(point$app_release);
        this.mAnchorPoint = point$app_release;
        this.mClosePolicy = builder.getClosePolicy$app_release();
        this.mMaxWidth = builder.getMaxWidth$app_release();
        this.mFloatingAnimation = builder.getFloatingAnimation$app_release();
        this.mShowDuration = builder.getShowDuration$app_release();
        this.mShowOverlay = builder.getOverlay$app_release();
        if (builder.getShowArrow$app_release() && builder.getLayoutId$app_release() == null) {
            z = true;
        }
        this.mShowArrow = z;
        View anchorView$app_release = builder.getAnchorView$app_release();
        if (anchorView$app_release != null) {
            this.mAnchorView = new WeakReference<>(anchorView$app_release);
            this.mHasAnchorView = true;
            this.mFollowAnchor = builder.getFollowAnchor$app_release();
        }
        Integer layoutId$app_release = builder.getLayoutId$app_release();
        if (layoutId$app_release == null) {
            unit = null;
        } else {
            layoutId$app_release.intValue();
            Integer textId$app_release = builder.getTextId$app_release();
            Intrinsics.checkNotNull(textId$app_release);
            this.mTextViewIdRes = textId$app_release.intValue();
            Integer layoutId$app_release2 = builder.getLayoutId$app_release();
            Intrinsics.checkNotNull(layoutId$app_release2);
            this.mTooltipLayoutIdRes = layoutId$app_release2.intValue();
            this.mIsCustomView = true;
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            Tooltip tooltip = this;
            this.mDrawable = new TooltipTextDrawable(this.context, builder);
        }
        Typeface typeface$app_release = builder.getTypeface$app_release();
        if (typeface$app_release != null) {
            this.mTypeface = typeface$app_release;
            unit2 = Unit.INSTANCE;
        }
        if (unit2 == null) {
            Tooltip tooltip2 = this;
            if (string != null) {
                this.mTypeface = Typefaces.INSTANCE.get(this.context, string);
            }
        }
        this.mNewLocation = new int[]{0, 0};
    }

    public final boolean isShowing() {
        return this.isShowing;
    }

    /* access modifiers changed from: private */
    /* renamed from: hideRunnable$lambda-1  reason: not valid java name */
    public static final void m1394hideRunnable$lambda1(Tooltip tooltip) {
        Intrinsics.checkNotNullParameter(tooltip, "this$0");
        tooltip.hide();
    }

    /* access modifiers changed from: private */
    /* renamed from: activateRunnable$lambda-2  reason: not valid java name */
    public static final void m1393activateRunnable$lambda2(Tooltip tooltip) {
        Intrinsics.checkNotNullParameter(tooltip, "this$0");
        tooltip.mActivated = true;
    }

    public final Gravity getGravity() {
        Positions positions = this.mCurrentPosition;
        if (positions == null) {
            return null;
        }
        return positions.getGravity();
    }

    public final CharSequence getText() {
        return this.mText;
    }

    public final View getContentView() {
        View view = this.mContentView;
        if (view != null) {
            return view;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mContentView");
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v24, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: android.view.View} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0077, code lost:
        if (r0[1] != r5.mNewLocation[1]) goto L_0x0079;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: predrawListener$lambda-3  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean m1395predrawListener$lambda3(com.jch.racWiFi.tooltip.Tooltip r5) {
        /*
            java.lang.String r0 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            boolean r0 = r5.mHasAnchorView
            r1 = 1
            if (r0 == 0) goto L_0x00ac
            java.lang.ref.WeakReference<android.view.View> r0 = r5.mAnchorView
            r2 = 0
            if (r0 != 0) goto L_0x0012
            r0 = r2
            goto L_0x0018
        L_0x0012:
            java.lang.Object r0 = r0.get()
            android.view.View r0 = (android.view.View) r0
        L_0x0018:
            if (r0 == 0) goto L_0x00ac
            java.lang.ref.WeakReference<android.view.View> r0 = r5.mAnchorView
            if (r0 != 0) goto L_0x001f
            goto L_0x0026
        L_0x001f:
            java.lang.Object r0 = r0.get()
            r2 = r0
            android.view.View r2 = (android.view.View) r2
        L_0x0026:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            java.lang.String r0 = "mAnchorView?.get()!!"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)
            android.view.ViewTreeObserver r0 = r2.getViewTreeObserver()
            boolean r0 = r0.isAlive()
            if (r0 != 0) goto L_0x003c
            r5.removeListeners(r2)
            goto L_0x00ac
        L_0x003c:
            boolean r0 = r5.isShowing()
            if (r0 == 0) goto L_0x00ac
            com.jch.racWiFi.tooltip.Tooltip$TooltipViewContainer r0 = r5.mPopupView
            if (r0 == 0) goto L_0x00ac
            int[] r0 = r5.mNewLocation
            r2.getLocationOnScreen(r0)
            int[] r0 = r5.mOldLocation
            r2 = 0
            if (r0 != 0) goto L_0x005f
            r0 = 2
            int[] r0 = new int[r0]
            int[] r3 = r5.mNewLocation
            r4 = r3[r2]
            r0[r2] = r4
            r3 = r3[r1]
            r0[r1] = r3
            r5.mOldLocation = r0
        L_0x005f:
            int[] r0 = r5.mOldLocation
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r0 = r0[r2]
            int[] r3 = r5.mNewLocation
            r3 = r3[r1]
            if (r0 != r3) goto L_0x0079
            int[] r0 = r5.mOldLocation
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r0 = r0[r1]
            int[] r3 = r5.mNewLocation
            r3 = r3[r1]
            if (r0 == r3) goto L_0x0096
        L_0x0079:
            int[] r0 = r5.mNewLocation
            r0 = r0[r2]
            int[] r3 = r5.mOldLocation
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            r3 = r3[r2]
            int r0 = r0 - r3
            float r0 = (float) r0
            int[] r3 = r5.mNewLocation
            r3 = r3[r1]
            int[] r4 = r5.mOldLocation
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            r4 = r4[r1]
            int r3 = r3 - r4
            float r3 = (float) r3
            r5.offsetBy(r0, r3)
        L_0x0096:
            int[] r0 = r5.mOldLocation
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            int[] r3 = r5.mNewLocation
            r3 = r3[r2]
            r0[r2] = r3
            int[] r0 = r5.mOldLocation
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            int[] r5 = r5.mNewLocation
            r5 = r5[r1]
            r0[r1] = r5
        L_0x00ac:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.tooltip.Tooltip.m1395predrawListener$lambda3(com.jch.racWiFi.tooltip.Tooltip):boolean");
    }

    public final Tooltip doOnFailure(Function1<? super Tooltip, Unit> function1) {
        this.mFailureFunc = function1;
        return this;
    }

    public final Tooltip doOnShown(Function1<? super Tooltip, Unit> function1) {
        this.mShownFunc = function1;
        return this;
    }

    public final Tooltip doOnPrepare(Function1<? super Tooltip, Unit> function1) {
        this.mPrepareFun = function1;
        return this;
    }

    public final Tooltip doOnHidden(Function1<? super Tooltip, Unit> function1) {
        this.mHiddenFunc = function1;
        return this;
    }

    public final void update(CharSequence charSequence) {
        this.mText = charSequence;
        if (this.isShowing && this.mPopupView != null) {
            TextView textView = this.mTextView;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTextView");
                textView = null;
            }
            if (!(charSequence instanceof Spannable)) {
                Objects.requireNonNull(charSequence, "null cannot be cast to non-null type kotlin.String");
                charSequence = HtmlCompat.fromHtml((String) charSequence, 63);
            }
            textView.setText(charSequence);
        }
    }

    public final void update(int i) {
        update((CharSequence) this.context.getResources().getString(i));
    }

    private final WindowManager.LayoutParams createPopupLayoutParams(IBinder iBinder) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.gravity = 51;
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.format = -3;
        layoutParams.flags = computeFlags(layoutParams.flags);
        layoutParams.type = this.mWindowLayoutType;
        layoutParams.token = iBinder;
        layoutParams.softInputMode = this.mSoftInputMode;
        layoutParams.setTitle(Intrinsics.stringPlus("ToolTip:", Integer.toHexString(hashCode())));
        return layoutParams;
    }

    private final int computeFlags(int i) {
        int i2 = i | 32;
        int i3 = (this.mClosePolicy.inside() || this.mClosePolicy.outside()) ? i2 & -9 : i2 | 8;
        if (!this.mClosePolicy.consume()) {
            i3 |= 16;
        }
        return i3 | 131072 | 262144 | 512 | 256 | 65536;
    }

    private final void preparePopup(WindowManager.LayoutParams layoutParams, Gravity gravity2) {
        Unit unit;
        TooltipViewContainer tooltipViewContainer = this.mPopupView;
        TextView textView = null;
        if (tooltipViewContainer == null) {
            unit = null;
        } else {
            if (this.mViewOverlay != null && gravity2 == Gravity.CENTER) {
                tooltipViewContainer.removeView(this.mViewOverlay);
                this.mViewOverlay = null;
            }
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            Tooltip tooltip = this;
            TooltipViewContainer tooltipViewContainer2 = new TooltipViewContainer(this, this.context);
            if (this.mShowOverlay && this.mViewOverlay == null) {
                TooltipOverlay tooltipOverlay = new TooltipOverlay(this.context, 0, this.mOverlayStyle);
                this.mViewOverlay = tooltipOverlay;
                Intrinsics.checkNotNull(tooltipOverlay);
                tooltipOverlay.setAdjustViewBounds(true);
                tooltipOverlay.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            View inflate = LayoutInflater.from(this.context).inflate(this.mTooltipLayoutIdRes, tooltipViewContainer2, false);
            if (!this.mIsCustomView) {
                TextView appCompatTextView = new AppCompatTextView(new ContextThemeWrapper(this.context, this.mTextStyleResId));
                this.mTextView = appCompatTextView;
                appCompatTextView.setId(16908308);
                Objects.requireNonNull(inflate, "null cannot be cast to non-null type android.view.ViewGroup");
                ViewGroup viewGroup = (ViewGroup) inflate;
                TextView textView2 = this.mTextView;
                if (textView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mTextView");
                    textView2 = null;
                }
                viewGroup.addView(textView2);
            }
            Animation animation = this.mFloatingAnimation;
            if (animation != null) {
                Intrinsics.checkNotNullExpressionValue(inflate, "contentView");
                int radius = animation.getRadius();
                inflate.setPadding(radius, radius, radius, radius);
            }
            View findViewById = inflate.findViewById(this.mTextViewIdRes);
            Intrinsics.checkNotNullExpressionValue(findViewById, "contentView.findViewById(mTextViewIdRes)");
            TextView textView3 = (TextView) findViewById;
            this.mTextView = textView3;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTextView");
                textView3 = null;
            }
            TooltipTextDrawable tooltipTextDrawable = this.mDrawable;
            if (tooltipTextDrawable != null) {
                textView3.setBackground(tooltipTextDrawable);
            }
            if (this.mShowArrow) {
                int i = this.mPadding;
                textView3.setPadding(i, i, i, i);
            } else {
                int i2 = this.mPadding;
                textView3.setPadding(i2 / 2, i2 / 2, i2 / 2, i2 / 2);
            }
            CharSequence charSequence = this.mText;
            if (!(charSequence instanceof Spannable)) {
                CharSequence charSequence2 = this.mText;
                Objects.requireNonNull(charSequence2, "null cannot be cast to non-null type kotlin.String");
                charSequence = HtmlCompat.fromHtml((String) charSequence2, 63);
            }
            textView3.setText(charSequence);
            Integer num = this.mMaxWidth;
            if (num != null) {
                textView3.setMaxWidth(num.intValue());
            }
            Typeface typeface = this.mTypeface;
            if (typeface != null) {
                textView3.setTypeface(typeface);
            }
            TooltipOverlay tooltipOverlay2 = this.mViewOverlay;
            if (tooltipOverlay2 != null) {
                tooltipViewContainer2.addView(tooltipOverlay2, new FrameLayout.LayoutParams(-2, -2));
            }
            tooltipViewContainer2.addView(inflate, new FrameLayout.LayoutParams(-2, -2));
            tooltipViewContainer2.setMeasureAllChildren(true);
            tooltipViewContainer2.measure(0, 0);
            TextView textView4 = this.mTextView;
            if (textView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTextView");
            } else {
                textView = textView4;
            }
            AttachStateChangeListener attachStateChangeListener = new AttachStateChangeListener();
            attachStateChangeListener.onViewAttachedToWindow((Function2<? super View, ? super View.OnAttachStateChangeListener, Unit>) new Tooltip$preparePopup$2$4$1(this));
            attachStateChangeListener.onViewDetachedFromWindow((Function2<? super View, ? super View.OnAttachStateChangeListener, Unit>) new Tooltip$preparePopup$2$4$2(this));
            textView.addOnAttachStateChangeListener(attachStateChangeListener);
            Intrinsics.checkNotNullExpressionValue(inflate, "contentView");
            this.mContentView = inflate;
            this.mPopupView = tooltipViewContainer2;
        }
    }

    static /* synthetic */ Positions findPosition$default(Tooltip tooltip, View view, View view2, Point point, ArrayList arrayList, WindowManager.LayoutParams layoutParams, boolean z, int i, Object obj) {
        return tooltip.findPosition(view, view2, point, arrayList, layoutParams, (i & 32) != 0 ? false : z);
    }

    private final Positions findPosition(View view, View view2, Point point, ArrayList<Gravity> arrayList, WindowManager.LayoutParams layoutParams, boolean z) {
        int i;
        View view3 = view2;
        Point point2 = point;
        if (this.mPopupView == null || arrayList.isEmpty()) {
            return null;
        }
        char c = 0;
        Gravity remove = arrayList.remove(0);
        Intrinsics.checkNotNullExpressionValue(remove, "gravities.removeAt(0)");
        Gravity gravity2 = remove;
        Rect rect = new Rect();
        int[] iArr = {0, 0};
        PointF pointF = new PointF(point2);
        view.getWindowVisibleDisplayFrame(rect);
        if (view3 != null) {
            view3.getLocationOnScreen(iArr);
            pointF.x += (float) (iArr[0] + (view2.getWidth() / 2));
            pointF.y += (float) (iArr[1] + (view2.getHeight() / 2));
            int i2 = WhenMappings.$EnumSwitchMapping$0[gravity2.ordinal()];
            if (i2 == 1) {
                iArr[1] = iArr[1] + (view2.getHeight() / 2);
            } else if (i2 == 2) {
                iArr[0] = iArr[0] + view2.getWidth();
                iArr[1] = iArr[1] + (view2.getHeight() / 2);
            } else if (i2 == 3) {
                iArr[0] = iArr[0] + (view2.getWidth() / 2);
            } else if (i2 == 4) {
                iArr[0] = iArr[0] + (view2.getWidth() / 2);
                iArr[1] = iArr[1] + view2.getHeight();
            } else if (i2 == 5) {
                iArr[0] = iArr[0] + (view2.getWidth() / 2);
                iArr[1] = iArr[1] + (view2.getHeight() / 2);
            }
            c = 0;
        }
        iArr[c] = iArr[c] + point2.x;
        iArr[1] = iArr[1] + point2.y;
        View view4 = this.mContentView;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContentView");
            view4 = null;
        }
        int measuredWidth = view4.getMeasuredWidth();
        View view5 = this.mContentView;
        if (view5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContentView");
            view5 = null;
        }
        int measuredHeight = view5.getMeasuredHeight();
        Point point3 = new Point();
        Point point4 = new Point();
        Animation animation = this.mFloatingAnimation;
        if (animation == null) {
            Tooltip tooltip = this;
            i = 0;
        } else {
            i = animation.getRadius();
        }
        int i3 = WhenMappings.$EnumSwitchMapping$0[gravity2.ordinal()];
        if (i3 == 1) {
            point3.x = iArr[0] - measuredWidth;
            int i4 = measuredHeight / 2;
            point3.y = iArr[1] - i4;
            point4.y = (i4 - (this.mPadding / 2)) - i;
        } else if (i3 == 2) {
            point3.x = iArr[0];
            int i5 = measuredHeight / 2;
            point3.y = iArr[1] - i5;
            point4.y = (i5 - (this.mPadding / 2)) - i;
        } else if (i3 == 3) {
            int i6 = measuredWidth / 2;
            point3.x = iArr[0] - i6;
            point3.y = iArr[1] - measuredHeight;
            point4.x = (i6 - (this.mPadding / 2)) - i;
        } else if (i3 == 4) {
            int i7 = measuredWidth / 2;
            point3.x = iArr[0] - i7;
            point3.y = iArr[1];
            point4.x = (i7 - (this.mPadding / 2)) - i;
        } else if (i3 == 5) {
            point3.x = iArr[0] - (measuredWidth / 2);
            point3.y = iArr[1] - (measuredHeight / 2);
        }
        if ((view3 == null ? null : Unit.INSTANCE) == null) {
            Tooltip tooltip2 = this;
            TooltipOverlay tooltipOverlay = this.mViewOverlay;
            if (tooltipOverlay != null) {
                int i8 = WhenMappings.$EnumSwitchMapping$0[gravity2.ordinal()];
                if (i8 == 1) {
                    point3.x -= tooltipOverlay.getMeasuredWidth() / 2;
                } else if (i8 == 2) {
                    point3.x += tooltipOverlay.getMeasuredWidth() / 2;
                } else if (i8 == 3) {
                    point3.y -= tooltipOverlay.getMeasuredHeight() / 2;
                } else if (i8 == 4) {
                    point3.y += tooltipOverlay.getMeasuredHeight() / 2;
                }
            }
        }
        if (z && !UtilsKt.rectContainsWithTolerance(rect, new Rect(point3.x, point3.y, point3.x + measuredWidth, point3.y + measuredHeight), (int) this.mSizeTolerance)) {
            return findPosition(view, view2, point, arrayList, layoutParams, z);
        }
        return new Positions(rect, new PointF(point4), pointF, new PointF(point3), gravity2, layoutParams);
    }

    private final Tooltip invokePopup(Positions positions) {
        PointF pointF = null;
        if (positions == null) {
            Tooltip tooltip = this;
            Function1<? super Tooltip, Unit> function1 = this.mFailureFunc;
            if (function1 != null) {
                function1.invoke(this);
            }
            return null;
        }
        this.isShowing = true;
        this.mCurrentPosition = positions;
        setupAnimation(positions.getGravity());
        if (this.mHasAnchorView) {
            WeakReference<View> weakReference = this.mAnchorView;
            if ((weakReference == null ? null : (View) weakReference.get()) != null) {
                WeakReference<View> weakReference2 = this.mAnchorView;
                Intrinsics.checkNotNull(weakReference2);
                Object obj = weakReference2.get();
                Intrinsics.checkNotNull(obj);
                Intrinsics.checkNotNullExpressionValue(obj, "mAnchorView!!.get()!!");
                setupListeners((View) obj);
            }
        }
        TooltipTextDrawable tooltipTextDrawable = this.mDrawable;
        if (tooltipTextDrawable != null) {
            Gravity gravity2 = positions.getGravity();
            boolean z = this.mShowArrow;
            int i = !z ? 0 : this.mPadding / 2;
            if (z) {
                pointF = new PointF(positions.getArrowPointX(), positions.getArrowPointY());
            }
            tooltipTextDrawable.setAnchor(gravity2, i, pointF);
        }
        offsetBy(0.0f, 0.0f);
        positions.getParams().packageName = this.context.getPackageName();
        TooltipViewContainer tooltipViewContainer = this.mPopupView;
        if (tooltipViewContainer != null) {
            tooltipViewContainer.setFitsSystemWindows(this.mLayoutInsetDecor);
        }
        this.windowManager.addView(this.mPopupView, positions.getParams());
        fadeIn();
        return this;
    }

    public final void offsetBy(float f, float f2) {
        Positions positions;
        if (this.isShowing && this.mPopupView != null && (positions = this.mCurrentPosition) != null) {
            Intrinsics.checkNotNull(positions);
            positions.offsetBy(f, f2);
            View view = this.mContentView;
            View view2 = null;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContentView");
                view = null;
            }
            Positions positions2 = this.mCurrentPosition;
            Intrinsics.checkNotNull(positions2);
            view.setTranslationX(positions2.getContentPointX());
            View view3 = this.mContentView;
            if (view3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContentView");
            } else {
                view2 = view3;
            }
            Positions positions3 = this.mCurrentPosition;
            Intrinsics.checkNotNull(positions3);
            view2.setTranslationY(positions3.getContentPointY());
            TooltipOverlay tooltipOverlay = this.mViewOverlay;
            if (tooltipOverlay != null) {
                Positions positions4 = this.mCurrentPosition;
                Intrinsics.checkNotNull(positions4);
                tooltipOverlay.setTranslationX(positions4.getCenterPointX() - ((float) (tooltipOverlay.getMeasuredWidth() / 2)));
                Positions positions5 = this.mCurrentPosition;
                Intrinsics.checkNotNull(positions5);
                tooltipOverlay.setTranslationY(positions5.getCenterPointY() - ((float) (tooltipOverlay.getMeasuredHeight() / 2)));
            }
        }
    }

    public final void offsetTo(float f, float f2) {
        Positions positions;
        if (this.isShowing && this.mPopupView != null && (positions = this.mCurrentPosition) != null) {
            Intrinsics.checkNotNull(positions);
            positions.offsetTo(f, f2);
            View view = this.mContentView;
            View view2 = null;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContentView");
                view = null;
            }
            Positions positions2 = this.mCurrentPosition;
            Intrinsics.checkNotNull(positions2);
            view.setTranslationX(positions2.getContentPointX());
            View view3 = this.mContentView;
            if (view3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContentView");
            } else {
                view2 = view3;
            }
            Positions positions3 = this.mCurrentPosition;
            Intrinsics.checkNotNull(positions3);
            view2.setTranslationY(positions3.getContentPointY());
            TooltipOverlay tooltipOverlay = this.mViewOverlay;
            if (tooltipOverlay != null) {
                Positions positions4 = this.mCurrentPosition;
                Intrinsics.checkNotNull(positions4);
                tooltipOverlay.setTranslationX(positions4.getCenterPointX() - ((float) (tooltipOverlay.getMeasuredWidth() / 2)));
                Positions positions5 = this.mCurrentPosition;
                Intrinsics.checkNotNull(positions5);
                tooltipOverlay.setTranslationY(positions5.getCenterPointY() - ((float) (tooltipOverlay.getMeasuredHeight() / 2)));
            }
        }
    }

    public final float getOffsetX() {
        Positions positions = this.mCurrentPosition;
        if (positions == null) {
            return 0.0f;
        }
        return positions.getMOffsetX();
    }

    public final float getOffsetY() {
        Positions positions = this.mCurrentPosition;
        if (positions == null) {
            return 0.0f;
        }
        return positions.getMOffsetY();
    }

    private final void removeListeners(View view) {
        ViewTreeObserver viewTreeObserver;
        if (this.mFollowAnchor && view != null && (viewTreeObserver = view.getViewTreeObserver()) != null) {
            viewTreeObserver.removeOnPreDrawListener(this.predrawListener);
        }
    }

    private final void setupAnimation(Gravity gravity2) {
        Animation animation;
        int i;
        TextView textView = this.mTextView;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTextView");
            textView = null;
        }
        View view = this.mContentView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContentView");
            view = null;
        }
        if (textView != view && (animation = this.mFloatingAnimation) != null) {
            Intrinsics.checkNotNull(animation);
            int radius = animation.getRadius();
            Animation animation2 = this.mFloatingAnimation;
            Intrinsics.checkNotNull(animation2);
            long duration = animation2.getDuration();
            Animation animation3 = this.mFloatingAnimation;
            Intrinsics.checkNotNull(animation3);
            if (animation3.getDirection() == 0) {
                i = (gravity2 == Gravity.TOP || gravity2 == Gravity.BOTTOM) ? 2 : 1;
            } else {
                Animation animation4 = this.mFloatingAnimation;
                Intrinsics.checkNotNull(animation4);
                i = animation4.getDirection();
            }
            String str = i == 2 ? "translationY" : "translationX";
            TextView textView3 = this.mTextView;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTextView");
            } else {
                textView2 = textView3;
            }
            float f = (float) radius;
            ValueAnimator ofFloat = ObjectAnimator.ofFloat(textView2, str, new float[]{-f, f});
            this.mAnimator = ofFloat;
            Intrinsics.checkNotNull(ofFloat);
            ofFloat.setDuration(duration);
            ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
            ofFloat.setRepeatCount(-1);
            ofFloat.setRepeatMode(2);
        }
    }

    public static /* synthetic */ void show$default(Tooltip tooltip, View view, Gravity gravity2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        tooltip.show(view, gravity2, z);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: android.view.View} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void show(android.view.View r12, com.jch.racWiFi.tooltip.Tooltip.Gravity r13, boolean r14) {
        /*
            r11 = this;
            java.lang.String r0 = "parent"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.String r0 = "gravity"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            boolean r0 = r11.isShowing
            if (r0 != 0) goto L_0x006e
            boolean r0 = r11.mHasAnchorView
            r1 = 0
            if (r0 == 0) goto L_0x0022
            java.lang.ref.WeakReference<android.view.View> r0 = r11.mAnchorView
            if (r0 != 0) goto L_0x0019
            r0 = r1
            goto L_0x001f
        L_0x0019:
            java.lang.Object r0 = r0.get()
            android.view.View r0 = (android.view.View) r0
        L_0x001f:
            if (r0 != 0) goto L_0x0022
            goto L_0x006e
        L_0x0022:
            r0 = 0
            r11.isVisible = r0
            android.os.IBinder r2 = r12.getWindowToken()
            java.lang.String r3 = "parent.windowToken"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            android.view.WindowManager$LayoutParams r9 = r11.createPopupLayoutParams(r2)
            r11.preparePopup(r9, r13)
            java.util.List<com.jch.racWiFi.tooltip.Tooltip$Gravity> r2 = r11.mGravities
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Collection r3 = (java.util.Collection) r3
            java.util.Collection r2 = kotlin.collections.CollectionsKt.toCollection(r2, r3)
            r8 = r2
            java.util.ArrayList r8 = (java.util.ArrayList) r8
            r8.remove(r13)
            r8.add(r0, r13)
            kotlin.jvm.functions.Function1<? super com.jch.racWiFi.tooltip.Tooltip, kotlin.Unit> r13 = r11.mPrepareFun
            if (r13 != 0) goto L_0x0052
            goto L_0x0055
        L_0x0052:
            r13.invoke(r11)
        L_0x0055:
            java.lang.ref.WeakReference<android.view.View> r13 = r11.mAnchorView
            if (r13 != 0) goto L_0x005a
            goto L_0x0061
        L_0x005a:
            java.lang.Object r13 = r13.get()
            r1 = r13
            android.view.View r1 = (android.view.View) r1
        L_0x0061:
            r6 = r1
            android.graphics.Point r7 = r11.mAnchorPoint
            r4 = r11
            r5 = r12
            r10 = r14
            com.jch.racWiFi.tooltip.Tooltip$Positions r12 = r4.findPosition(r5, r6, r7, r8, r9, r10)
            r11.invokePopup(r12)
        L_0x006e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jch.racWiFi.tooltip.Tooltip.show(android.view.View, com.jch.racWiFi.tooltip.Tooltip$Gravity, boolean):void");
    }

    public final void hide() {
        if (this.isShowing) {
            fadeOut();
        }
    }

    public final void dismiss() {
        if (this.isShowing && this.mPopupView != null) {
            WeakReference<View> weakReference = this.mAnchorView;
            removeListeners(weakReference == null ? null : (View) weakReference.get());
            removeCallbacks();
            this.windowManager.removeView(this.mPopupView);
            this.mPopupView = null;
            this.isShowing = false;
            this.isVisible = false;
            Function1<? super Tooltip, Unit> function1 = this.mHiddenFunc;
            if (function1 != null) {
                function1.invoke(this);
            }
        }
    }

    /* access modifiers changed from: private */
    public final void removeCallbacks() {
        this.mHandler.removeCallbacks(this.hideRunnable);
        this.mHandler.removeCallbacks(this.activateRunnable);
    }

    private final void fadeIn() {
        if (this.isShowing && !this.isVisible) {
            if (this.mEnterAnimation != 0) {
                TextView textView = this.mTextView;
                TextView textView2 = null;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mTextView");
                    textView = null;
                }
                textView.clearAnimation();
                TextView textView3 = this.mTextView;
                if (textView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mTextView");
                } else {
                    textView2 = textView3;
                }
                textView2.startAnimation(AnimationUtils.loadAnimation(this.context, this.mEnterAnimation));
            }
            this.isVisible = true;
            Function1<? super Tooltip, Unit> function1 = this.mShownFunc;
            if (function1 != null) {
                function1.invoke(this);
            }
        }
    }

    private final void fadeOut() {
        if (this.isShowing && this.isVisible) {
            int i = this.mExitAnimation;
            if (i != 0) {
                android.view.animation.Animation loadAnimation = AnimationUtils.loadAnimation(this.context, i);
                Intrinsics.checkNotNullExpressionValue(loadAnimation, "animation");
                AnimationListener animationListener = new AnimationListener();
                animationListener.onAnimationEnd((Function1<? super android.view.animation.Animation, Unit>) new Tooltip$fadeOut$1$1(this));
                loadAnimation.setAnimationListener(animationListener);
                loadAnimation.start();
                TextView textView = this.mTextView;
                TextView textView2 = null;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mTextView");
                    textView = null;
                }
                textView.clearAnimation();
                TextView textView3 = this.mTextView;
                if (textView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mTextView");
                } else {
                    textView2 = textView3;
                }
                textView2.startAnimation(loadAnimation);
                return;
            }
            this.isVisible = false;
            removeCallbacks();
            dismiss();
        }
    }

    @Metadata(mo36737d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J0\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0007H\u0014J(\u0010\u0017\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0007H\u0014J\u0010\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u001bH\u0017R@\u0010\u0005\u001a4\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, mo36738d2 = {"Lcom/jch/racWiFi/tooltip/Tooltip$TooltipViewContainer;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Lcom/jch/racWiFi/tooltip/Tooltip;Landroid/content/Context;)V", "sizeChange", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "w", "h", "", "dispatchKeyEvent", "", "event", "Landroid/view/KeyEvent;", "onLayout", "changed", "left", "top", "right", "bottom", "onSizeChanged", "oldw", "oldh", "onTouchEvent", "Landroid/view/MotionEvent;", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
    /* compiled from: Tooltip.kt */
    public final class TooltipViewContainer extends FrameLayout {
        private Function2<? super Integer, ? super Integer, Unit> sizeChange;
        final /* synthetic */ Tooltip this$0;

        public void _$_clearFindViewByIdCache() {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public TooltipViewContainer(Tooltip tooltip, Context context) {
            super(context);
            Intrinsics.checkNotNullParameter(tooltip, "this$0");
            Intrinsics.checkNotNullParameter(context, "context");
            this.this$0 = tooltip;
            setClipChildren(false);
            setClipToPadding(false);
        }

        /* access modifiers changed from: protected */
        public void onSizeChanged(int i, int i2, int i3, int i4) {
            super.onSizeChanged(i, i2, i3, i4);
            Function2<? super Integer, ? super Integer, Unit> function2 = this.sizeChange;
            if (function2 != null) {
                function2.invoke(Integer.valueOf(i), Integer.valueOf(i2));
            }
        }

        /* access modifiers changed from: protected */
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            if (z) {
                int[] iArr = {-1, -1};
                getLocationOnScreen(iArr);
                offsetTopAndBottom(-iArr[1]);
            }
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            KeyEvent.DispatcherState keyDispatcherState;
            Intrinsics.checkNotNullParameter(keyEvent, "event");
            if (!this.this$0.isShowing() || !this.this$0.isVisible || !this.this$0.mActivated) {
                return super.dispatchKeyEvent(keyEvent);
            }
            if (keyEvent.getKeyCode() != 4) {
                return super.dispatchKeyEvent(keyEvent);
            }
            if (getKeyDispatcherState() == null) {
                return super.dispatchKeyEvent(keyEvent);
            }
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                if (keyDispatcherState2 != null) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                }
                return true;
            } else if (keyEvent.getAction() != 1 || (keyDispatcherState = getKeyDispatcherState()) == null || !keyDispatcherState.isTracking(keyEvent) || keyEvent.isCanceled()) {
                return super.dispatchKeyEvent(keyEvent);
            } else {
                this.this$0.hide();
                return true;
            }
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(motionEvent, "event");
            if (!this.this$0.isShowing() || !this.this$0.isVisible || !this.this$0.mActivated) {
                return false;
            }
            Rect rect = new Rect();
            TextView access$getMTextView$p = this.this$0.mTextView;
            if (access$getMTextView$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTextView");
                access$getMTextView$p = null;
            }
            access$getMTextView$p.getGlobalVisibleRect(rect);
            boolean contains = rect.contains((int) motionEvent.getX(), (int) motionEvent.getY());
            if (this.this$0.mClosePolicy.anywhere()) {
                this.this$0.hide();
            } else if (this.this$0.mClosePolicy.inside() && contains) {
                this.this$0.hide();
            } else if (this.this$0.mClosePolicy.outside() && !contains) {
                this.this$0.hide();
            }
            return this.this$0.mClosePolicy.consume();
        }
    }

    @Metadata(mo36737d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b#\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\t\u0010.\u001a\u00020\u0005HÆ\u0003J\t\u0010/\u001a\u00020\u0005HÆ\u0003J\t\u00100\u001a\u00020\tHÆ\u0003J\t\u00101\u001a\u00020\u000bHÆ\u0003JE\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00106\u001a\u000207HÖ\u0001J\u0016\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u0010J\u0016\u0010<\u001a\u0002092\u0006\u0010:\u001a\u00020\u00102\u0006\u0010;\u001a\u00020\u0010J\t\u0010=\u001a\u00020>HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0011\u0010\u0016\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0012R\u0011\u0010\u0018\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u000eR\u0011\u0010\u001b\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0012R\u0011\u0010\u001d\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0012\"\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0012\"\u0004\b)\u0010&R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+¨\u0006?"}, mo36738d2 = {"Lcom/jch/racWiFi/tooltip/Tooltip$Positions;", "", "displayFrame", "Landroid/graphics/Rect;", "arrowPoint", "Landroid/graphics/PointF;", "centerPoint", "contentPoint", "gravity", "Lcom/jch/racWiFi/tooltip/Tooltip$Gravity;", "params", "Landroid/view/WindowManager$LayoutParams;", "(Landroid/graphics/Rect;Landroid/graphics/PointF;Landroid/graphics/PointF;Landroid/graphics/PointF;Lcom/jch/racWiFi/tooltip/Tooltip$Gravity;Landroid/view/WindowManager$LayoutParams;)V", "getArrowPoint", "()Landroid/graphics/PointF;", "arrowPointX", "", "getArrowPointX", "()F", "arrowPointY", "getArrowPointY", "getCenterPoint", "centerPointX", "getCenterPointX", "centerPointY", "getCenterPointY", "getContentPoint", "contentPointX", "getContentPointX", "contentPointY", "getContentPointY", "getDisplayFrame", "()Landroid/graphics/Rect;", "getGravity", "()Lcom/jch/racWiFi/tooltip/Tooltip$Gravity;", "mOffsetX", "getMOffsetX", "setMOffsetX", "(F)V", "mOffsetY", "getMOffsetY", "setMOffsetY", "getParams", "()Landroid/view/WindowManager$LayoutParams;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "offsetBy", "", "x", "y", "offsetTo", "toString", "", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
    /* compiled from: Tooltip.kt */
    private static final class Positions {
        private final PointF arrowPoint;
        private final PointF centerPoint;
        private final PointF contentPoint;
        private final Rect displayFrame;
        private final Gravity gravity;
        private float mOffsetX;
        private float mOffsetY;
        private final WindowManager.LayoutParams params;

        public static /* synthetic */ Positions copy$default(Positions positions, Rect rect, PointF pointF, PointF pointF2, PointF pointF3, Gravity gravity2, WindowManager.LayoutParams layoutParams, int i, Object obj) {
            if ((i & 1) != 0) {
                rect = positions.displayFrame;
            }
            if ((i & 2) != 0) {
                pointF = positions.arrowPoint;
            }
            PointF pointF4 = pointF;
            if ((i & 4) != 0) {
                pointF2 = positions.centerPoint;
            }
            PointF pointF5 = pointF2;
            if ((i & 8) != 0) {
                pointF3 = positions.contentPoint;
            }
            PointF pointF6 = pointF3;
            if ((i & 16) != 0) {
                gravity2 = positions.gravity;
            }
            Gravity gravity3 = gravity2;
            if ((i & 32) != 0) {
                layoutParams = positions.params;
            }
            return positions.copy(rect, pointF4, pointF5, pointF6, gravity3, layoutParams);
        }

        public final Rect component1() {
            return this.displayFrame;
        }

        public final PointF component2() {
            return this.arrowPoint;
        }

        public final PointF component3() {
            return this.centerPoint;
        }

        public final PointF component4() {
            return this.contentPoint;
        }

        public final Gravity component5() {
            return this.gravity;
        }

        public final WindowManager.LayoutParams component6() {
            return this.params;
        }

        public final Positions copy(Rect rect, PointF pointF, PointF pointF2, PointF pointF3, Gravity gravity2, WindowManager.LayoutParams layoutParams) {
            Intrinsics.checkNotNullParameter(rect, "displayFrame");
            Intrinsics.checkNotNullParameter(pointF, "arrowPoint");
            Intrinsics.checkNotNullParameter(pointF2, "centerPoint");
            Intrinsics.checkNotNullParameter(pointF3, "contentPoint");
            Intrinsics.checkNotNullParameter(gravity2, "gravity");
            Intrinsics.checkNotNullParameter(layoutParams, "params");
            return new Positions(rect, pointF, pointF2, pointF3, gravity2, layoutParams);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Positions)) {
                return false;
            }
            Positions positions = (Positions) obj;
            return Intrinsics.areEqual((Object) this.displayFrame, (Object) positions.displayFrame) && Intrinsics.areEqual((Object) this.arrowPoint, (Object) positions.arrowPoint) && Intrinsics.areEqual((Object) this.centerPoint, (Object) positions.centerPoint) && Intrinsics.areEqual((Object) this.contentPoint, (Object) positions.contentPoint) && this.gravity == positions.gravity && Intrinsics.areEqual((Object) this.params, (Object) positions.params);
        }

        public int hashCode() {
            return (((((((((this.displayFrame.hashCode() * 31) + this.arrowPoint.hashCode()) * 31) + this.centerPoint.hashCode()) * 31) + this.contentPoint.hashCode()) * 31) + this.gravity.hashCode()) * 31) + this.params.hashCode();
        }

        public String toString() {
            return "Positions(displayFrame=" + this.displayFrame + ", arrowPoint=" + this.arrowPoint + ", centerPoint=" + this.centerPoint + ", contentPoint=" + this.contentPoint + ", gravity=" + this.gravity + ", params=" + this.params + ')';
        }

        public Positions(Rect rect, PointF pointF, PointF pointF2, PointF pointF3, Gravity gravity2, WindowManager.LayoutParams layoutParams) {
            Intrinsics.checkNotNullParameter(rect, "displayFrame");
            Intrinsics.checkNotNullParameter(pointF, "arrowPoint");
            Intrinsics.checkNotNullParameter(pointF2, "centerPoint");
            Intrinsics.checkNotNullParameter(pointF3, "contentPoint");
            Intrinsics.checkNotNullParameter(gravity2, "gravity");
            Intrinsics.checkNotNullParameter(layoutParams, "params");
            this.displayFrame = rect;
            this.arrowPoint = pointF;
            this.centerPoint = pointF2;
            this.contentPoint = pointF3;
            this.gravity = gravity2;
            this.params = layoutParams;
        }

        public final Rect getDisplayFrame() {
            return this.displayFrame;
        }

        public final PointF getArrowPoint() {
            return this.arrowPoint;
        }

        public final PointF getCenterPoint() {
            return this.centerPoint;
        }

        public final PointF getContentPoint() {
            return this.contentPoint;
        }

        public final Gravity getGravity() {
            return this.gravity;
        }

        public final WindowManager.LayoutParams getParams() {
            return this.params;
        }

        public final float getMOffsetX() {
            return this.mOffsetX;
        }

        public final void setMOffsetX(float f) {
            this.mOffsetX = f;
        }

        public final float getMOffsetY() {
            return this.mOffsetY;
        }

        public final void setMOffsetY(float f) {
            this.mOffsetY = f;
        }

        public final void offsetBy(float f, float f2) {
            this.mOffsetX += f;
            this.mOffsetY += f2;
        }

        public final void offsetTo(float f, float f2) {
            this.mOffsetX = f;
            this.mOffsetY = f2;
        }

        public final float getCenterPointX() {
            return this.centerPoint.x + this.mOffsetX;
        }

        public final float getCenterPointY() {
            return this.centerPoint.y + this.mOffsetY;
        }

        public final float getArrowPointX() {
            return this.arrowPoint.x + this.mOffsetX;
        }

        public final float getArrowPointY() {
            return this.arrowPoint.y + this.mOffsetY;
        }

        public final float getContentPointX() {
            return this.contentPoint.x + this.mOffsetX;
        }

        public final float getContentPointY() {
            return this.contentPoint.y + this.mOffsetY;
        }
    }

    @Metadata(mo36737d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, mo36738d2 = {"Lcom/jch/racWiFi/tooltip/Tooltip$Animation;", "", "radius", "", "direction", "duration", "", "(IIJ)V", "getDirection", "()I", "getDuration", "()J", "getRadius", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "Companion", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
    /* compiled from: Tooltip.kt */
    public static final class Animation {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        /* access modifiers changed from: private */
        public static final Animation DEFAULT = new Animation(8, 0, 400);
        /* access modifiers changed from: private */
        public static final Animation SLOW = new Animation(4, 0, 600);
        private final int direction;
        private final long duration;
        private final int radius;

        public static /* synthetic */ Animation copy$default(Animation animation, int i, int i2, long j, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = animation.radius;
            }
            if ((i3 & 2) != 0) {
                i2 = animation.direction;
            }
            if ((i3 & 4) != 0) {
                j = animation.duration;
            }
            return animation.copy(i, i2, j);
        }

        public final int component1() {
            return this.radius;
        }

        public final int component2() {
            return this.direction;
        }

        public final long component3() {
            return this.duration;
        }

        public final Animation copy(int i, int i2, long j) {
            return new Animation(i, i2, j);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Animation)) {
                return false;
            }
            Animation animation = (Animation) obj;
            return this.radius == animation.radius && this.direction == animation.direction && this.duration == animation.duration;
        }

        public int hashCode() {
            return (((this.radius * 31) + this.direction) * 31) + ECSettings$$ExternalSyntheticBackport0.m148m(this.duration);
        }

        public String toString() {
            return "Animation(radius=" + this.radius + ", direction=" + this.direction + ", duration=" + this.duration + ')';
        }

        public Animation(int i, int i2, long j) {
            this.radius = i;
            this.direction = i2;
            this.duration = j;
        }

        public final int getDirection() {
            return this.direction;
        }

        public final long getDuration() {
            return this.duration;
        }

        public final int getRadius() {
            return this.radius;
        }

        @Metadata(mo36737d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, mo36738d2 = {"Lcom/jch/racWiFi/tooltip/Tooltip$Animation$Companion;", "", "()V", "DEFAULT", "Lcom/jch/racWiFi/tooltip/Tooltip$Animation;", "getDEFAULT", "()Lcom/jch/racWiFi/tooltip/Tooltip$Animation;", "SLOW", "getSLOW", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
        /* compiled from: Tooltip.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final Animation getDEFAULT() {
                return Animation.DEFAULT;
            }

            public final Animation getSLOW() {
                return Animation.SLOW;
            }
        }
    }

    @Metadata(mo36737d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\r\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010V\u001a\u00020\u0006J,\u0010W\u001a\u00020\u00002\u0006\u0010X\u001a\u00020\f2\b\b\u0002\u0010Y\u001a\u00020\u00122\b\b\u0002\u0010Z\u001a\u00020\u00122\b\b\u0002\u0010[\u001a\u00020-J\u0016\u0010W\u001a\u00020\u00002\u0006\u0010\\\u001a\u00020\u00122\u0006\u0010]\u001a\u00020\u0012J\u0010\u0010\u0011\u001a\u00020\u00002\b\b\u0001\u0010^\u001a\u00020\u0012J\u000e\u0010_\u001a\u00020\u00002\u0006\u0010V\u001a\u00020-J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010`\u001a\u00020\u0019J\u0006\u0010a\u001a\u00020bJ\u001a\u0010c\u001a\u00020\u00002\b\b\u0001\u00102\u001a\u00020\u00122\b\b\u0001\u0010M\u001a\u00020\u0012J\u0010\u0010&\u001a\u00020\u00002\b\u0010V\u001a\u0004\u0018\u00010'J\u000e\u00105\u001a\u00020\u00002\u0006\u0010d\u001a\u00020\u0012J\u000e\u00108\u001a\u00020\u00002\u0006\u0010V\u001a\u00020-J\u000e\u0010D\u001a\u00020\u00002\u0006\u0010V\u001a\u00020\u0006J\u0017\u0010e\u001a\u00020\u00002\n\b\u0001\u0010e\u001a\u0004\u0018\u00010\u0012¢\u0006\u0002\u0010fJ\u000e\u0010G\u001a\u00020\u00002\u0006\u0010G\u001a\u00020HJ\u0010\u0010G\u001a\u00020\u00002\b\b\u0001\u0010G\u001a\u00020\u0012J)\u0010G\u001a\u00020\u00002\b\b\u0001\u0010G\u001a\u00020\u00122\u0012\u0010g\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010h\"\u00020\u0001¢\u0006\u0002\u0010iJ\u0010\u0010P\u001a\u00020\u00002\b\u0010V\u001a\u0004\u0018\u00010QR\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0018\u001a\u00020\u0019X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010 \"\u0004\b%\u0010\"R\u001c\u0010&\u001a\u0004\u0018\u00010'X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010,\u001a\u00020-X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\"\u00102\u001a\u0004\u0018\u00010\u00128\u0000@\u0000X\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b3\u0010\u0014\"\u0004\b4\u0010\u0016R\u001e\u00105\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b6\u0010\u0014\"\u0004\b7\u0010\u0016R\u001a\u00108\u001a\u00020-X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010/\"\u0004\b:\u00101R\u001c\u0010;\u001a\u0004\u0018\u00010<X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u001a\u0010A\u001a\u00020-X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010/\"\u0004\bC\u00101R\u001a\u0010D\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010\b\"\u0004\bF\u0010\nR\u001c\u0010G\u001a\u0004\u0018\u00010HX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR\"\u0010M\u001a\u0004\u0018\u00010\u00128\u0000@\u0000X\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\bN\u0010\u0014\"\u0004\bO\u0010\u0016R\u001c\u0010P\u001a\u0004\u0018\u00010QX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010S\"\u0004\bT\u0010U¨\u0006j"}, mo36738d2 = {"Lcom/jch/racWiFi/tooltip/Tooltip$Builder;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "activateDelay", "", "getActivateDelay$app_release", "()J", "setActivateDelay$app_release", "(J)V", "anchorView", "Landroid/view/View;", "getAnchorView$app_release", "()Landroid/view/View;", "setAnchorView$app_release", "(Landroid/view/View;)V", "animationStyle", "", "getAnimationStyle$app_release", "()Ljava/lang/Integer;", "setAnimationStyle$app_release", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "closePolicy", "Lcom/jch/racWiFi/tooltip/ClosePolicy;", "getClosePolicy$app_release", "()Lcom/jch/racWiFi/tooltip/ClosePolicy;", "setClosePolicy$app_release", "(Lcom/jch/racWiFi/tooltip/ClosePolicy;)V", "defStyleAttr", "getDefStyleAttr$app_release", "()I", "setDefStyleAttr$app_release", "(I)V", "defStyleRes", "getDefStyleRes$app_release", "setDefStyleRes$app_release", "floatingAnimation", "Lcom/jch/racWiFi/tooltip/Tooltip$Animation;", "getFloatingAnimation$app_release", "()Lcom/jch/racWiFi/tooltip/Tooltip$Animation;", "setFloatingAnimation$app_release", "(Lcom/jch/racWiFi/tooltip/Tooltip$Animation;)V", "followAnchor", "", "getFollowAnchor$app_release", "()Z", "setFollowAnchor$app_release", "(Z)V", "layoutId", "getLayoutId$app_release", "setLayoutId$app_release", "maxWidth", "getMaxWidth$app_release", "setMaxWidth$app_release", "overlay", "getOverlay$app_release", "setOverlay$app_release", "point", "Landroid/graphics/Point;", "getPoint$app_release", "()Landroid/graphics/Point;", "setPoint$app_release", "(Landroid/graphics/Point;)V", "showArrow", "getShowArrow$app_release", "setShowArrow$app_release", "showDuration", "getShowDuration$app_release", "setShowDuration$app_release", "text", "", "getText$app_release", "()Ljava/lang/CharSequence;", "setText$app_release", "(Ljava/lang/CharSequence;)V", "textId", "getTextId$app_release", "setTextId$app_release", "typeface", "Landroid/graphics/Typeface;", "getTypeface$app_release", "()Landroid/graphics/Typeface;", "setTypeface$app_release", "(Landroid/graphics/Typeface;)V", "value", "anchor", "view", "xoff", "yoff", "follow", "x", "y", "id", "arrow", "policy", "create", "Lcom/jch/racWiFi/tooltip/Tooltip;", "customView", "w", "styleId", "(Ljava/lang/Integer;)Lcom/jch/racWiFi/tooltip/Tooltip$Builder;", "args", "", "(I[Ljava/lang/Object;)Lcom/jch/racWiFi/tooltip/Tooltip$Builder;", "app_release"}, mo36739k = 1, mo36740mv = {1, 5, 1}, mo36742xi = 48)
    /* compiled from: Tooltip.kt */
    public static final class Builder {
        private long activateDelay;
        private View anchorView;
        private Integer animationStyle;
        private ClosePolicy closePolicy = ClosePolicy.Companion.getTOUCH_INSIDE_CONSUME();
        private final Context context;
        private int defStyleAttr = R.attr.ttlm_defaultStyle;
        private int defStyleRes = R.style.ToolTipLayoutDefaultStyle;
        private Animation floatingAnimation;
        private boolean followAnchor;
        private Integer layoutId;
        private Integer maxWidth;
        private boolean overlay = true;
        private Point point;
        private boolean showArrow = true;
        private long showDuration;
        private CharSequence text;
        private Integer textId;
        private Typeface typeface;

        public Builder(Context context2) {
            Intrinsics.checkNotNullParameter(context2, "context");
            this.context = context2;
        }

        public final Point getPoint$app_release() {
            return this.point;
        }

        public final void setPoint$app_release(Point point2) {
            this.point = point2;
        }

        public final ClosePolicy getClosePolicy$app_release() {
            return this.closePolicy;
        }

        public final void setClosePolicy$app_release(ClosePolicy closePolicy2) {
            Intrinsics.checkNotNullParameter(closePolicy2, "<set-?>");
            this.closePolicy = closePolicy2;
        }

        public final CharSequence getText$app_release() {
            return this.text;
        }

        public final void setText$app_release(CharSequence charSequence) {
            this.text = charSequence;
        }

        public final View getAnchorView$app_release() {
            return this.anchorView;
        }

        public final void setAnchorView$app_release(View view) {
            this.anchorView = view;
        }

        public final Integer getMaxWidth$app_release() {
            return this.maxWidth;
        }

        public final void setMaxWidth$app_release(Integer num) {
            this.maxWidth = num;
        }

        public final int getDefStyleRes$app_release() {
            return this.defStyleRes;
        }

        public final void setDefStyleRes$app_release(int i) {
            this.defStyleRes = i;
        }

        public final int getDefStyleAttr$app_release() {
            return this.defStyleAttr;
        }

        public final void setDefStyleAttr$app_release(int i) {
            this.defStyleAttr = i;
        }

        public final Typeface getTypeface$app_release() {
            return this.typeface;
        }

        public final void setTypeface$app_release(Typeface typeface2) {
            this.typeface = typeface2;
        }

        public final boolean getOverlay$app_release() {
            return this.overlay;
        }

        public final void setOverlay$app_release(boolean z) {
            this.overlay = z;
        }

        public final Animation getFloatingAnimation$app_release() {
            return this.floatingAnimation;
        }

        public final void setFloatingAnimation$app_release(Animation animation) {
            this.floatingAnimation = animation;
        }

        public final long getShowDuration$app_release() {
            return this.showDuration;
        }

        public final void setShowDuration$app_release(long j) {
            this.showDuration = j;
        }

        public final boolean getShowArrow$app_release() {
            return this.showArrow;
        }

        public final void setShowArrow$app_release(boolean z) {
            this.showArrow = z;
        }

        public final long getActivateDelay$app_release() {
            return this.activateDelay;
        }

        public final void setActivateDelay$app_release(long j) {
            this.activateDelay = j;
        }

        public final boolean getFollowAnchor$app_release() {
            return this.followAnchor;
        }

        public final void setFollowAnchor$app_release(boolean z) {
            this.followAnchor = z;
        }

        public final Integer getAnimationStyle$app_release() {
            return this.animationStyle;
        }

        public final void setAnimationStyle$app_release(Integer num) {
            this.animationStyle = num;
        }

        public final Integer getLayoutId$app_release() {
            return this.layoutId;
        }

        public final void setLayoutId$app_release(Integer num) {
            this.layoutId = num;
        }

        public final Integer getTextId$app_release() {
            return this.textId;
        }

        public final void setTextId$app_release(Integer num) {
            this.textId = num;
        }

        public final Builder typeface(Typeface typeface2) {
            this.typeface = typeface2;
            return this;
        }

        public final Builder styleId(Integer num) {
            Unit unit;
            if (num == null) {
                unit = null;
            } else {
                setDefStyleRes$app_release(num.intValue());
                unit = Unit.INSTANCE;
            }
            if (unit == null) {
                Builder builder = this;
                setDefStyleRes$app_release(R.style.ToolTipLayoutDefaultStyle);
            }
            this.defStyleAttr = R.attr.ttlm_defaultStyle;
            return this;
        }

        public final Builder customView(int i, int i2) {
            this.layoutId = Integer.valueOf(i);
            this.textId = Integer.valueOf(i2);
            return this;
        }

        public final Builder activateDelay(long j) {
            this.activateDelay = j;
            return this;
        }

        public final Builder arrow(boolean z) {
            this.showArrow = z;
            return this;
        }

        public final Builder showDuration(long j) {
            this.showDuration = j;
            return this;
        }

        public final Builder floatingAnimation(Animation animation) {
            this.floatingAnimation = animation;
            return this;
        }

        public final Builder maxWidth(int i) {
            this.maxWidth = Integer.valueOf(i);
            return this;
        }

        public final Builder overlay(boolean z) {
            this.overlay = z;
            return this;
        }

        public final Builder anchor(int i, int i2) {
            this.anchorView = null;
            this.point = new Point(i, i2);
            return this;
        }

        public static /* synthetic */ Builder anchor$default(Builder builder, View view, int i, int i2, boolean z, int i3, Object obj) {
            if ((i3 & 2) != 0) {
                i = 0;
            }
            if ((i3 & 4) != 0) {
                i2 = 0;
            }
            if ((i3 & 8) != 0) {
                z = false;
            }
            return builder.anchor(view, i, i2, z);
        }

        public final Builder anchor(View view, int i, int i2, boolean z) {
            Intrinsics.checkNotNullParameter(view, "view");
            this.anchorView = view;
            this.followAnchor = z;
            this.point = new Point(i, i2);
            return this;
        }

        public final Builder text(CharSequence charSequence) {
            Intrinsics.checkNotNullParameter(charSequence, "text");
            this.text = charSequence;
            return this;
        }

        public final Builder text(int i) {
            this.text = this.context.getString(i);
            return this;
        }

        public final Builder text(int i, Object... objArr) {
            Intrinsics.checkNotNullParameter(objArr, "args");
            this.text = this.context.getString(i, new Object[]{objArr});
            return this;
        }

        public final Builder closePolicy(ClosePolicy closePolicy2) {
            Intrinsics.checkNotNullParameter(closePolicy2, "policy");
            this.closePolicy = closePolicy2;
            return this;
        }

        public final Builder animationStyle(int i) {
            this.animationStyle = Integer.valueOf(i);
            return this;
        }

        public final Tooltip create() {
            if (this.anchorView != null || this.point != null) {
                return new Tooltip(this.context, this, (DefaultConstructorMarker) null);
            }
            throw new IllegalArgumentException("missing anchor point or anchor view");
        }
    }

    private final void setupListeners(View view) {
        AttachStateChangeListener attachStateChangeListener = new AttachStateChangeListener();
        attachStateChangeListener.onViewDetachedFromWindow((Function2<? super View, ? super View.OnAttachStateChangeListener, Unit>) new Tooltip$setupListeners$1$1(this));
        view.addOnAttachStateChangeListener(attachStateChangeListener);
        if (this.mFollowAnchor) {
            view.getViewTreeObserver().addOnPreDrawListener(this.predrawListener);
        }
    }
}
