package com.example.components.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.components.interfaces.OnCommunicationFragmentListner;
import com.example.components.R;
import com.example.components.viewmodel.SharedViewModel;


public class FirstFragment extends Fragment {

    private OnCommunicationFragmentListner callBack;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Fragment Lifecycle", "onCreate");
        setRetainInstance(true);
//        setHasOptionsMenu(true);
    }

    SharedViewModel viewModel;
    TextView firstText;
    EditText etFirst;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container,false);
        firstText = view.findViewById(R.id.firstText);
        etFirst = view.findViewById(R.id.etFirst);

        firstText.setOnClickListener(v->{
//            viewModel.setText(etFirst.getText());
            callBack.msgFromFragment(etFirst.getText().toString());
        });

        Bundle result = new Bundle();
        result.putString("bundleKey", "result");
        getParentFragmentManager().setFragmentResult("requestKey", result);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("Fragment Lifecycle", "onViewCreated");

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.getText().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
            @Override
            public void onChanged(CharSequence charSequence) {
//                etFirst.setText(charSequence);
            }
        });

    }

    public void setMessage(String message) {
        etFirst.setText(message);
    }

//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.sample_menu, menu);
//
//        super.onCreateOptionsMenu(menu, inflater);
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_settings:  {
//                Toast.makeText(getContext(), "Setting", Toast.LENGTH_SHORT).show();
//                return true;
//            }
//            case R.id.action_done: {
//                Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
//                new PurchaseConfirmationDialogFragment().show(
//                        getChildFragmentManager(), PurchaseConfirmationDialogFragment.TAG);
//                return true;
//            }
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d("Fragment Lifecycle", "onViewStateRestored");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("Fragment Lifecycle", "onSaveInstanceState");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Fragment Lifecycle", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Fragment Lifecycle", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callBack = null;
    }
}