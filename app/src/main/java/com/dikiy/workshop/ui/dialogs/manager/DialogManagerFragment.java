package com.dikiy.workshop.ui.dialogs.manager;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.dikiy.workshop.R;
import com.dikiy.workshop.ui.dialogs.examples.BottomDialogFragment;
import com.dikiy.workshop.ui.dialogs.examples.CustomBottomDialog;
import com.dikiy.workshop.ui.dialogs.examples.SimpleAlertDialogFragment;

public class DialogManagerFragment extends Fragment {

    private DialogFragment mDialogFirst;
    private DialogFragment mDialogSecond;
    private Dialog mDialogThird;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setupUI();
        return inflater.inflate(R.layout.fragment_manager_dialogs, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_dialog_first_example).setOnClickListener(button ->
                mDialogFirst.show(requireFragmentManager(), "FirstDialog"));
        view.findViewById(R.id.btn_dialog_second_example).setOnClickListener(button ->
                mDialogSecond.show(requireFragmentManager(), "SecondDialog"));
        view.findViewById(R.id.btn_dialog_third_example).setOnClickListener(button ->
                mDialogThird.show());
    }

    private void setupUI() {
        mDialogFirst = new SimpleAlertDialogFragment();
        mDialogSecond = new BottomDialogFragment();
        mDialogThird = new CustomBottomDialog(requireContext());
    }
}
