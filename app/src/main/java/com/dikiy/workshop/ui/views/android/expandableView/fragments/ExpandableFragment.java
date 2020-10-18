package com.dikiy.workshop.ui.views.android.expandableView.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.dikiy.workshop.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ExpandableFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_offers_info, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        initViews();
    }

    private void initViews() {
        ViewPager viewPager = requireView().findViewById(R.id.vp_offers_info);
        viewPager.setAdapter(initAdapter(initListFragments()));
        TabLayout tabs = requireView().findViewById(R.id.tabs_offers_info);
        tabs.setupWithViewPager(viewPager);
    }

    private PagerAdapter initAdapter(List<Fragment> fragments) {
        return new FragmentPagerAdapter(requireFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            String[] tabs = getResources().getStringArray(R.array.offers_info_tabs);

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tabs[position];
            }
        };
    }

    private List<Fragment> initListFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new BetsFragment());
        fragments.add(new ConditionsFragment());
        return fragments;
    }
}
