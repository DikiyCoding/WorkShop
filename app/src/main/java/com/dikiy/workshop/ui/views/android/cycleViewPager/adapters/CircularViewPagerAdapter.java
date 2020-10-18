package com.dikiy.workshop.ui.views.android.cycleViewPager.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.dikiy.workshop.ui.views.android.cycleViewPager.fragments.PageFragment;

import java.util.List;

public class CircularViewPagerAdapter extends BaseCircularViewPagerAdapter<String> {

    private final Context context;

    public CircularViewPagerAdapter(Context context, FragmentManager fragmentManager, List<String> values) {
        super(fragmentManager, values);
        this.context = context;
    }

    @Override
    protected Fragment getFragmentForItem(String value) {
        return PageFragment.newInstance(context, value);
    }
}
