package com.dikiy.workshop.ui.views.android.cycleViewPager.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.dikiy.workshop.R;
import com.dikiy.workshop.ui.views.android.cycleViewPager.adapters.CircularViewPagerAdapter;
import com.dikiy.workshop.ui.views.android.cycleViewPager.listeners.sCircularViewPagerListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CycleViewPagerFragment extends Fragment {

    private ViewPager viewPager;
    private sCircularViewPagerListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cycle_view_pager, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        initViews();
        initValues();
    }

    private void initViews() {
        viewPager = requireView().findViewById(R.id.circular_view_pager);
        viewPager.setAdapter(new CircularViewPagerAdapter(requireContext(), requireFragmentManager(), initListValues()));
    }

    private void initValues() {
        int itemAnimDelay = 200;
        int itemScrollDelay = 6000;

        Observable swapAuto = Observable.interval(itemScrollDelay, itemScrollDelay, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(aLong -> {
                    int iterable = viewPager.getCurrentItem() + 1;
                    viewPager.setCurrentItem(iterable);
                    Log.d("Logs", "Page selected");
                });
        Disposable disposable = swapAuto.subscribe();

        Observable swapHand = Observable.create(emitter ->
                viewPager.setOnTouchListener((view, motionEvent) -> {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        disposable.dispose();
                        emitter.onNext(motionEvent);
                    }
                    viewPager.onTouchEvent(motionEvent);
                    return true;
                })).switchMap(ignored -> swapAuto.startWith(0L))
                .observeOn(AndroidSchedulers.mainThread());

        swapHand.subscribe();

        listener = new sCircularViewPagerListener(viewPager, itemAnimDelay, listener);
        viewPager.addOnPageChangeListener(listener);
    }

    private List<String> initListValues() {
        List<String> values = new ArrayList<>();
        values.add("First Page");
        values.add("Second Page");
        values.add("Third Page");
        values.add("Forth Page");
        return values;
    }
}
