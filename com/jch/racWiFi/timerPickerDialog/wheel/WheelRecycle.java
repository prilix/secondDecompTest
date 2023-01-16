package com.jch.racWiFi.timerPickerDialog.wheel;

import android.view.View;
import android.widget.LinearLayout;
import java.util.LinkedList;
import java.util.List;

public class WheelRecycle {
    private List<View> emptyItems;
    private List<View> items;
    private WheelView wheel;

    public WheelRecycle(WheelView wheelView) {
        this.wheel = wheelView;
    }

    public int recycleItems(LinearLayout linearLayout, int i, ItemsRange itemsRange, int i2) {
        int i3 = i;
        int i4 = 0;
        while (i4 < linearLayout.getChildCount()) {
            if (!itemsRange.contains(i3)) {
                recycleView(linearLayout.getChildAt(i4), i3, i2);
                linearLayout.removeViewAt(i4);
                if (i4 == 0) {
                    i++;
                }
            } else {
                i4++;
            }
            i3++;
        }
        return i;
    }

    public View getItem() {
        return getCachedView(this.items);
    }

    public View getEmptyItem() {
        return getCachedView(this.emptyItems);
    }

    public void clearAll() {
        List<View> list = this.items;
        if (list != null) {
            list.clear();
        }
        List<View> list2 = this.emptyItems;
        if (list2 != null) {
            list2.clear();
        }
    }

    private List<View> addView(View view, List<View> list) {
        if (list == null) {
            list = new LinkedList<>();
        }
        list.add(view);
        return list;
    }

    private void recycleView(View view, int i, int i2) {
        int itemsCount = this.wheel.getViewAdapter().getItemsCount();
        if ((i < 0 || i >= itemsCount) && !this.wheel.isCyclic()) {
            this.emptyItems = addView(view, this.emptyItems);
            return;
        }
        while (i < 0) {
            i += itemsCount;
        }
        int i3 = i % itemsCount;
        this.items = addView(view, this.items);
    }

    private View getCachedView(List<View> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        View view = list.get(0);
        list.remove(0);
        return view;
    }
}
