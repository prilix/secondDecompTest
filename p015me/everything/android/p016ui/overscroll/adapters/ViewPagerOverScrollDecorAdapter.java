package p015me.everything.android.p016ui.overscroll.adapters;

import android.view.View;
import androidx.viewpager.widget.ViewPager;

/* renamed from: me.everything.android.ui.overscroll.adapters.ViewPagerOverScrollDecorAdapter */
public class ViewPagerOverScrollDecorAdapter implements IOverScrollDecoratorAdapter, ViewPager.OnPageChangeListener {
    protected int mLastPagerPosition = 0;
    protected float mLastPagerScrollOffset;
    protected final ViewPager mViewPager;

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageSelected(int i) {
    }

    public ViewPagerOverScrollDecorAdapter(ViewPager viewPager) {
        this.mViewPager = viewPager;
        viewPager.addOnPageChangeListener(this);
        this.mLastPagerPosition = viewPager.getCurrentItem();
        this.mLastPagerScrollOffset = 0.0f;
    }

    public View getView() {
        return this.mViewPager;
    }

    public boolean isInAbsoluteStart() {
        return this.mLastPagerPosition == 0 && this.mLastPagerScrollOffset == 0.0f;
    }

    public boolean isInAbsoluteEnd() {
        return this.mLastPagerPosition == this.mViewPager.getAdapter().getCount() - 1 && this.mLastPagerScrollOffset == 0.0f;
    }

    public void onPageScrolled(int i, float f, int i2) {
        this.mLastPagerPosition = i;
        this.mLastPagerScrollOffset = f;
    }
}
