package com.dikiy.workshop.ui.views.android.cycleViewPager.listeners;

import androidx.viewpager.widget.ViewPager;

public class sCircularViewPagerListener implements ViewPager.OnPageChangeListener {

    private final int itemDelay;
    private final ViewPager viewPager;
    private final ViewPager.OnPageChangeListener listener;

    public sCircularViewPagerListener(ViewPager viewPager,
                                      int itemDelay,
                                      ViewPager.OnPageChangeListener listener) {
        this.itemDelay = itemDelay;
        this.viewPager = viewPager;
        this.viewPager.setCurrentItem(1, false);
        this.listener = listener;
    }

    @Override
    public void onPageSelected(int position) {
        handleSetCurrentItemWithDelay(position);
    }

    private void handleSetCurrentItemWithDelay(final int position) {
        viewPager.postDelayed(() -> handleSetCurrentItem(position), itemDelay);
    }

    private void handleSetCurrentItem(final int position) {
        final int lastPosition = viewPager.getAdapter().getCount() - 1;
        if (position == 0)
            viewPager.setCurrentItem(lastPosition - 1, false);
        else if (position == lastPosition)
            viewPager.setCurrentItem(1, false);
    }

    @Override
    public void onPageScrollStateChanged(final int state) {}

    @Override
    public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {}
}
