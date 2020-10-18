package com.dikiy.workshop.ui.views.android.expandableView.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dikiy.workshop.R;
import com.dikiy.workshop.ui.views.android.expandableView.model.Step;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {

    private final List<Step> listSteps;

    public StepsAdapter(List<Step> items) {
        listSteps = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_steps, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Step itemStep = listSteps.get(position);
        holder.tvHeader.setText(itemStep.getHeader());
        holder.tvDescription.setText(itemStep.getDescription());
        holder.ivIcon.setImageResource(itemStep.getImageRes());
    }

    @Override
    public int getItemCount() {
        return listSteps.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvHeader, tvDescription;
        private final ImageView ivIcon;

        ViewHolder(View view) {
            super(view);
            tvHeader = view.findViewById(R.id.step_header);
            tvDescription = view.findViewById(R.id.step_description);
            ivIcon = view.findViewById(R.id.step_icon);
        }
    }
}
