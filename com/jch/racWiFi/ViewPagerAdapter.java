package com.jch.racWiFi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import com.jch.racWiFi.customViews.customWidgets.ImageView;
import com.jch_hitachi.aircloudglobal.R;

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private int[] imageId;
    private LayoutInflater inflater;

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public ViewPagerAdapter(Context context2, int[] iArr) {
        this.context = context2;
        this.imageId = iArr;
    }

    public int getCount() {
        return this.imageId.length;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((ConstraintLayout) obj);
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        this.inflater = layoutInflater;
        View inflate = layoutInflater.inflate(R.layout.tutorial_view_pager_adapter_item, viewGroup, false);
        ((ImageView) inflate.findViewById(R.id.image_view_view_pager)).setImageResource(this.imageId[i]);
        viewGroup.addView(inflate);
        return inflate;
    }
}
