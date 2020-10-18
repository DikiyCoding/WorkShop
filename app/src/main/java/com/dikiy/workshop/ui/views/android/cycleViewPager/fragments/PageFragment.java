package com.dikiy.workshop.ui.views.android.cycleViewPager.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dikiy.workshop.R;

public class PageFragment extends Fragment {

    private String value;

    public static PageFragment newInstance(Context context, String value) {
        PageFragment page = (PageFragment) instantiate(context, PageFragment.class.getName());
        Bundle args = new Bundle();
        args.putString("value", value);
        page.setArguments(args);
        return page;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null)
            value = getArguments().getString("value", "Default Page");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView) view.findViewById(R.id.tv_value)).setText(value);
    }
}
