package com.dikiy.workshop.ui.material.manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dikiy.workshop.R;
import com.dikiy.workshop.ui.material.coordinator.activities.custom.CustomCoordinatorActivity;
import com.dikiy.workshop.ui.material.coordinator.activities.menuItem.CollapsingMenuItemActivity;
import com.dikiy.workshop.ui.material.coordinator.activities.simple.SimpleCoordinatorActivity;

public class CoordinatorManagerFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_manager_coordinator, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.btn_coordinator_first_example).setOnClickListener(this);
        view.findViewById(R.id.btn_coordinator_second_example).setOnClickListener(this);
        view.findViewById(R.id.btn_coordinator_third_example).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_coordinator_first_example:
                intent = new Intent(requireContext(), SimpleCoordinatorActivity.class);
                break;
            case R.id.btn_coordinator_second_example:
                intent = new Intent(requireContext(), CustomCoordinatorActivity.class);
                break;
            case R.id.btn_coordinator_third_example:
                intent = new Intent(requireContext(), CollapsingMenuItemActivity.class);
                break;
            default:
        }
        if (intent != null) startActivity(intent);
    }
}