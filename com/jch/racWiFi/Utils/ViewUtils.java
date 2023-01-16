package com.jch.racWiFi.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.style.BulletSpan;
import android.text.style.LeadingMarginSpan;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.inputmethod.InputMethodManager;
import android.widget.RadioButton;
import androidx.core.content.ContextCompat;
import com.accord.common.utils.Logger;
import com.jch.racWiFi.customViews.customWidgets.CustomSwitchButton;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch.racWiFi.customViews.customWidgets.TextView;
import com.suke.widget.SwitchButton;
import java.util.Stack;
import org.apache.commons.lang3.StringUtils;
import org.xml.sax.XMLReader;

public class ViewUtils {
    private static ViewOutlineProvider viewOutlineProviderCountry;
    private static ViewOutlineProvider viewOutlineProviderSwitch;

    public static void setErrorBackgroundEditText() {
    }

    public static float convertDpToPixel(float f, Context context) {
        return f * (((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f);
    }

    public static float convertPixelsToDp(float f, Context context) {
        return f / (((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f);
    }

    public static void setOutlineProviderSwitch(SwitchButton switchButton) {
        C16771 r0 = new ViewOutlineProvider() {
            public void getOutline(View view, Outline outline) {
                outline.setOval(10, 10, view.getWidth() - 10, view.getHeight() - 10);
            }
        };
        viewOutlineProviderSwitch = r0;
        switchButton.setOutlineProvider(r0);
    }

    public static void setOutlineCountryImage(ImageView imageView) {
        C16782 r0 = new ViewOutlineProvider() {
            public void getOutline(View view, Outline outline) {
                outline.setRect(8, 8, view.getWidth(), view.getHeight());
            }
        };
        viewOutlineProviderCountry = r0;
        imageView.setOutlineProvider(r0);
    }

    public static void setCheckedSilent(boolean z, final CustomSwitchButton customSwitchButton, final SwitchButton.OnCheckedChangeListener onCheckedChangeListener) {
        customSwitchButton.setOnCheckedChangeListener((SwitchButton.OnCheckedChangeListener) null);
        customSwitchButton.setChecked(z);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                CustomSwitchButton.this.setOnCheckedChangeListener(onCheckedChangeListener);
            }
        }, 100);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus == null) {
            currentFocus = new View(activity);
        }
        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }

    public static void setTextViewDrawableColor(TextView textView, int i) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(textView.getContext(), i), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    public static void setTextViewDrawableColor(RadioButton radioButton, int i) {
        for (Drawable drawable : radioButton.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(radioButton.getContext(), i), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    public static class ViewTouchUtil {
        static Pair<Integer, Integer> getLengthAndWidth(View view) {
            return new Pair<>(Integer.valueOf(view.getWidth()), Integer.valueOf(view.getHeight()));
        }

        static Pair<Integer, Integer> getPositionOnExpandedLayout(View view) {
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            return new Pair<>(Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]));
        }

        static Rect getViewRect(View view) {
            Pair<Integer, Integer> positionOnExpandedLayout = getPositionOnExpandedLayout(view);
            Pair<Integer, Integer> lengthAndWidth = getLengthAndWidth(view);
            Pair pair = new Pair(Integer.valueOf(((Integer) positionOnExpandedLayout.first).intValue() + ((Integer) lengthAndWidth.first).intValue()), Integer.valueOf(((Integer) positionOnExpandedLayout.second).intValue() + ((Integer) lengthAndWidth.second).intValue()));
            Rect rect = new Rect();
            rect.top = ((Integer) positionOnExpandedLayout.second).intValue();
            rect.bottom = ((Integer) pair.second).intValue();
            rect.left = ((Integer) positionOnExpandedLayout.first).intValue();
            rect.right = ((Integer) pair.first).intValue();
            return rect;
        }

        private static boolean touchedOnView(View view, MotionEvent motionEvent) {
            return getViewRect(view).contains((int) motionEvent.getX(), (int) motionEvent.getY());
        }

        public static boolean touchedOnThisViewArea(View view, MotionEvent motionEvent) {
            return touchedOnView(view, motionEvent);
        }
    }

    public static class ListTagHandler implements Html.TagHandler {
        /* access modifiers changed from: private */
        public static final BulletSpan BULLET_SPAN = new BulletSpan(10);
        private static final int INDENT_PX = 10;
        private static final int LIST_ITEM_INDENT_PX = 20;
        private static final String LI_TAG = "li";
        private static final String OL_TAG = "ol";
        private static final String UL_TAG = "ul";
        private final Stack<ListTag> lists = new Stack<>();

        public void handleTag(boolean z, String str, Editable editable, XMLReader xMLReader) {
            if (UL_TAG.equalsIgnoreCase(str)) {
                if (z) {
                    this.lists.push(new C1681Ul());
                } else {
                    this.lists.pop();
                }
            } else if (OL_TAG.equalsIgnoreCase(str)) {
                if (z) {
                    this.lists.push(new C1680Ol());
                } else {
                    this.lists.pop();
                }
            } else if (!LI_TAG.equalsIgnoreCase(str)) {
                Logger.m45d("TagHandler", "Found an unsupported tag " + str);
            } else if (z) {
                this.lists.peek().openItem(editable);
            } else {
                this.lists.peek().closeItem(editable, this.lists.size());
            }
        }

        private static abstract class ListTag {
            /* access modifiers changed from: protected */
            public abstract Object[] getReplaces(Editable editable, int i);

            private ListTag() {
            }

            public void openItem(Editable editable) {
                if (editable.length() > 0 && editable.charAt(editable.length() - 1) != 10) {
                    editable.append(StringUtils.f715LF);
                }
                int length = editable.length();
                editable.setSpan(this, length, length, 17);
            }

            public final void closeItem(Editable editable, int i) {
                if (editable.length() > 0 && editable.charAt(editable.length() - 1) != 10) {
                    editable.append(StringUtils.f715LF);
                }
                Object[] replaces = getReplaces(editable, i);
                int length = editable.length();
                ListTag last = getLast(editable);
                int spanStart = editable.getSpanStart(last);
                editable.removeSpan(last);
                if (spanStart != length) {
                    for (Object span : replaces) {
                        editable.setSpan(span, spanStart, length, 33);
                    }
                }
            }

            private ListTag getLast(Spanned spanned) {
                ListTag[] listTagArr = (ListTag[]) spanned.getSpans(0, spanned.length(), ListTag.class);
                if (listTagArr.length == 0) {
                    return null;
                }
                return listTagArr[listTagArr.length - 1];
            }
        }

        /* renamed from: com.jch.racWiFi.Utils.ViewUtils$ListTagHandler$Ul */
        private static class C1681Ul extends ListTag {
            private C1681Ul() {
                super();
            }

            /* access modifiers changed from: protected */
            public Object[] getReplaces(Editable editable, int i) {
                int i2 = 10;
                if (i > 1) {
                    i2 = 10 - ListTagHandler.BULLET_SPAN.getLeadingMargin(true);
                    if (i > 2) {
                        i2 -= (i - 2) * 20;
                    }
                }
                return new Object[]{new LeadingMarginSpan.Standard((i - 1) * 20), new BulletSpan(i2)};
            }
        }

        /* renamed from: com.jch.racWiFi.Utils.ViewUtils$ListTagHandler$Ol */
        private static class C1680Ol extends ListTag {
            private int nextIdx;

            public C1680Ol() {
                this(1);
            }

            public C1680Ol(int i) {
                super();
                this.nextIdx = i;
            }

            public void openItem(Editable editable) {
                super.openItem(editable);
                int i = this.nextIdx;
                this.nextIdx = i + 1;
                editable.append(Integer.toString(i)).append(". ");
            }

            /* access modifiers changed from: protected */
            public Object[] getReplaces(Editable editable, int i) {
                int i2 = (i - 1) * 20;
                if (i > 2) {
                    i2 -= (i - 2) * 20;
                }
                return new Object[]{new LeadingMarginSpan.Standard(i2)};
            }
        }
    }
}
