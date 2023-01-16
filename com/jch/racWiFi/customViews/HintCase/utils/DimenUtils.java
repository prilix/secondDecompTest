package com.jch.racWiFi.customViews.HintCase.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class DimenUtils {
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int getActionBarHeight(Context context) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(16843499, typedValue, true)) {
            return TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
        }
        return 0;
    }

    public static int dipToPixels(Context context, float f) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public static Point getNavigationBarSizeIfExistAtTheBottom(Context context) {
        Point appUsableScreenSize = getAppUsableScreenSize(context);
        Point realScreenSize = getRealScreenSize(context);
        if (appUsableScreenSize.y < realScreenSize.y) {
            return new Point(appUsableScreenSize.x, realScreenSize.y - appUsableScreenSize.y);
        }
        return new Point();
    }

    public static Point getNavigationBarSizeIfExistOnTheRight(Context context) {
        Point appUsableScreenSize = getAppUsableScreenSize(context);
        Point realScreenSize = getRealScreenSize(context);
        if (appUsableScreenSize.x < realScreenSize.x) {
            return new Point(realScreenSize.x - appUsableScreenSize.x, appUsableScreenSize.y);
        }
        return new Point();
    }

    public static Point getAppUsableScreenSize(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point;
    }

    public static Point getRealScreenSize(Context context) {
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 23) {
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRealSize(point);
        } else {
            View view = null;
            if (context instanceof Activity) {
                view = ((Activity) context).getWindow().getDecorView();
            } else if (context instanceof ContextThemeWrapper) {
                Context baseContext = ((ContextThemeWrapper) context).getBaseContext();
                if (baseContext instanceof Activity) {
                    view = ((Activity) baseContext).getWindow().getDecorView();
                }
            }
            if (view != null) {
                point.x = view.getWidth();
                point.y = view.getHeight();
            }
        }
        return point;
    }
}
