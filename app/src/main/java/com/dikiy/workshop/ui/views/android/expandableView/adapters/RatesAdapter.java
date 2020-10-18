package com.dikiy.workshop.ui.views.android.expandableView.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dikiy.workshop.R;
import com.dikiy.workshop.ui.views.android.expandableView.model.Rate;

import java.util.List;

public class RatesAdapter extends RecyclerView.Adapter<RatesAdapter.ViewHolder> {

    private final List<Rate> listRates;

    public RatesAdapter(List<Rate> listRates) {
        this.listRates = listRates;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rates, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Rate itemRate = listRates.get(position);
        holder.tvRate.setText(itemRate.getSpRate());
        holder.tvDescription.setText(itemRate.getSpDescription());
    }

    @Override
    public int getItemCount() {
        return listRates.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvRate, tvDescription;

        ViewHolder(View view) {
            super(view);
            tvRate = view.findViewById(R.id.tv_rate);
            tvDescription = view.findViewById(R.id.tv_description);
        }
    }
}
