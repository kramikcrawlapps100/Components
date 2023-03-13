package com.example.components.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.components.interfaces.OnCommunicationFragmentListner;
import com.example.components.R;

import java.util.Objects;


public class PurchaseConfirmationDialogFragment extends DialogFragment {

    private OnCommunicationFragmentListner callBack;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCommunicationFragmentListner) {
            callBack = (OnCommunicationFragmentListner) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCommunicationFragmentListner");
        }
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.my_dialog_layout, null);
        builder.setView(view);
        builder.setTitle("My Custom Dialog");
        builder.setPositiveButton("OK", (dialog, id) -> {
            EditText editText = Objects.requireNonNull(getDialog()).findViewById(R.id.edit_text);
            String userInput = editText.getText().toString();

            callBack.msgFromFragment(userInput);
        });
        builder.setNegativeButton("Cancel", (dialog, id) -> {
        });
        return builder.create();
    }

    public static String TAG = "PurchaseConfirmationDialog";
}