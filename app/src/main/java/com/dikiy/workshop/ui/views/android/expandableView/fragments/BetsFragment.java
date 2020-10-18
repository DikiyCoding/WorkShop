package com.dikiy.workshop.ui.views.android.expandableView.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dikiy.workshop.R;
import com.dikiy.workshop.ui.views.android.expandableView.adapters.RatesAdapter;
import com.dikiy.workshop.ui.views.android.expandableView.model.Rate;

import java.util.ArrayList;
import java.util.List;

public class BetsFragment extends Fragment {

    private List<Rate> rates;
    private RatesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bets, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        initListRates();
        initViews();
        hardCodeExample();
    }

    private void initListRates() {
        rates = new ArrayList<>();
    }

    private void initViews() {
        RecyclerView rvRates = requireView().findViewById(R.id.rv_rates);
        adapter = new RatesAdapter(rates);
        rvRates.setAdapter(adapter);
    }

    private void hardCodeExample() {
//        if (rates == null) return;
        rates.add(new Rate("до 90%", "Товары из раздела Специальные предложения"));
        rates.add(new Rate("6%", "Аксессуары для мобильных телефонов, Предметы интерьера, Категория мужская, женская и детская одежда, Товары для сада и огорода"));
        rates.add(new Rate("4%", "Мобильные телефоны, Ноутбуки и планшеты"));
        rates.add(new Rate("2%", "Остальные товары"));
        adapter.notifyDataSetChanged();
    }
}
