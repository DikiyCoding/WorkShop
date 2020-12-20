package com.dikiy.workshop.ui.views.android.bottomSheet.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.viewpager2.widget.ViewPager2;

import com.dikiy.workshop.R;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final List<String> listDetails;

    public DetailsAdapter(List<String> listDetails) {
        this.listDetails = listDetails;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 0;
        return 1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) return new ViewHolderItemHeader(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_details_header, parent, false));
        else return new ViewHolderItemMain(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_details_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        if (this.getItemViewType(position) == 1)
            ((ViewHolderItemMain) holder).bind(listDetails.get(position));
    }

    @Override
    public int getItemCount() {
        return listDetails.size();
    }

    private class HeaderAdapter extends RecyclerView.Adapter<ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_details_main, parent, false);
            view.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
            return new ViewHolderItemMain(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            ((ViewHolderItemMain) holder).bind(listDetails.get(position));
        }

        @Override
        public int getItemCount() {
            return listDetails.size();
        }
    }

    private class ViewHolderItemHeader extends ViewHolder {

        public ViewHolderItemHeader(@NonNull View view) {
            super(view);
            ((ViewPager2) view.findViewById(R.id.pager_details))
                    .setAdapter(new HeaderAdapter());
        }
    }

    private static class ViewHolderItemMain extends ViewHolder {
        private final TextView tvDetail;

        ViewHolderItemMain(View view) {
            super(view);
            tvDetail = view.findViewById(R.id.tv_detail);
        }

        private void bind(String detail) {
            tvDetail.setText(detail);
        }
    }
}
