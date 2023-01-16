package p015me.everything.android.p016ui.overscroll;

import android.view.View;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.ScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import p015me.everything.android.p016ui.overscroll.adapters.AbsListViewOverScrollDecorAdapter;
import p015me.everything.android.p016ui.overscroll.adapters.HorizontalScrollViewOverScrollDecorAdapter;
import p015me.everything.android.p016ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter;
import p015me.everything.android.p016ui.overscroll.adapters.ScrollViewOverScrollDecorAdapter;
import p015me.everything.android.p016ui.overscroll.adapters.StaticOverScrollDecorAdapter;
import p015me.everything.android.p016ui.overscroll.adapters.ViewPagerOverScrollDecorAdapter;

/* renamed from: me.everything.android.ui.overscroll.OverScrollDecoratorHelper */
public class OverScrollDecoratorHelper {
    public static final int ORIENTATION_HORIZONTAL = 1;
    public static final int ORIENTATION_VERTICAL = 0;

    public static IOverScrollDecor setUpOverScroll(RecyclerView recyclerView, int i) {
        if (i == 0) {
            return new VerticalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter(recyclerView));
        }
        if (i == 1) {
            return new HorizontalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter(recyclerView));
        }
        throw new IllegalArgumentException("orientation");
    }

    public static IOverScrollDecor setUpOverScroll(ListView listView) {
        return new VerticalOverScrollBounceEffectDecorator(new AbsListViewOverScrollDecorAdapter(listView));
    }

    public static IOverScrollDecor setUpOverScroll(GridView gridView) {
        return new VerticalOverScrollBounceEffectDecorator(new AbsListViewOverScrollDecorAdapter(gridView));
    }

    public static IOverScrollDecor setUpOverScroll(ScrollView scrollView) {
        return new VerticalOverScrollBounceEffectDecorator(new ScrollViewOverScrollDecorAdapter(scrollView));
    }

    public static IOverScrollDecor setUpOverScroll(HorizontalScrollView horizontalScrollView) {
        return new HorizontalOverScrollBounceEffectDecorator(new HorizontalScrollViewOverScrollDecorAdapter(horizontalScrollView));
    }

    public static IOverScrollDecor setUpStaticOverScroll(View view, int i) {
        if (i == 0) {
            return new VerticalOverScrollBounceEffectDecorator(new StaticOverScrollDecorAdapter(view));
        }
        if (i == 1) {
            return new HorizontalOverScrollBounceEffectDecorator(new StaticOverScrollDecorAdapter(view));
        }
        throw new IllegalArgumentException("orientation");
    }

    public static IOverScrollDecor setUpOverScroll(ViewPager viewPager) {
        return new HorizontalOverScrollBounceEffectDecorator(new ViewPagerOverScrollDecorAdapter(viewPager));
    }
}
