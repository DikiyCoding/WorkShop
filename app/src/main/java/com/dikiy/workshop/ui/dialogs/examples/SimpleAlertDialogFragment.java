package com.dikiy.workshop.ui.dialogs.examples;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.dikiy.workshop.R;

public class SimpleAlertDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder//.setMessage(R.string.dialog_first_example_title)
                .setView(inflater.inflate(R.layout.dialog_example_first, null))
                .setPositiveButton(R.string.dialog_btn_ok, (dialog, id) -> {})
                .setNegativeButton(R.string.dialog_btn_cancel, (dialog, id) -> {});
//                .setItems(R.array.colors_array, (DialogInterface.OnClickListener) (dialog, which) -> {})
//                .setMultiChoiceItems(R.array.toppings, null, (DialogInterface.OnMultiChoiceClickListener) (dialog, which, isChecked) -> {
//                    if (isChecked) mSelectedItems.add(which);
//                    else if (mSelectedItems.contains(which)) mSelectedItems.remove(which);
//                })
//                .setSingleChoiceItems(R.array.toppings, null, (dialogInterface, i) -> {});
        Dialog dialog = builder.create();
        /*Window window = dialog.getWindow();
        if(window != null) window.setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));*/
        return dialog;
    }
}
