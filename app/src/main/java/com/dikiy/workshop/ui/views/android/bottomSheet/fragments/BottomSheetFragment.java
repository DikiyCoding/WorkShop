package com.dikiy.workshop.ui.views.android.bottomSheet.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dikiy.workshop.R;
import com.dikiy.workshop.ui.views.android.bottomSheet.adapters.DetailsAdapter;
import com.dikiy.workshop.utils.decoration.ItemDecoration;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom_sheet, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior
                .from(requireView().findViewById(R.id.bottom_sheet));
        bottomSheetBehavior.setFitToContents(false);
        bottomSheetBehavior.setHalfExpandedRatio(0.275f);

        List<String> listDetails = new ArrayList<>();
        listDetails.add("body");
        listDetails.add("head");
        listDetails.add("left ankle");
        listDetails.add("right ankle");
        listDetails.add("left high arm");
        listDetails.add("right high arm");
        listDetails.add("left low arm");
        listDetails.add("right low arm");
        listDetails.add("left tibia");
        listDetails.add("right tibia");
        RecyclerView rvDetails = requireView().findViewById(R.id.list_details);
        rvDetails.setAdapter(new DetailsAdapter(listDetails));
//        rvDetails.addItemDecoration(new ItemDecoration(0, 0));
    }
}