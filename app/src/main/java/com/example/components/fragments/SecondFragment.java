package com.example.components.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.components.interfaces.OnCommunicationFragmentListner;
import com.example.components.R;
import com.example.components.viewmodel.SharedViewModel;


public class SecondFragment extends Fragment {

    SharedViewModel viewModel;
    TextView secondText;
    EditText etSecond;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Fragment Lifecycle", "onCreate");
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container,false);
        secondText = view.findViewById(R.id.secondText);
        etSecond = view.findViewById(R.id.etSecond);

        secondText.setOnClickListener(v->{
//            viewModel.setText(etSecond.getText());
            callBack.msgFromFragment(etSecond.getText().toString());
        });

        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                String result = bundle.getString("bundleKey");
                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
            }
        });

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
//                etSecond.setText(charSequence);
            }
        });
    }

    public void setMessage(String message) {
        etSecond.setText(message);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.sample_menu, menu);
    }

//    @Override
//    public void onPrepareOptionsMenu(@NonNull Menu menu) {
//        super.onPrepareOptionsMenu(menu);
//        MenuItem item = menu.findItem(R.id.action_done);
//        item.setVisible(false);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_settings:  {
//                // navigate to settings screen
//                Toast.makeText(getContext(), "Setting", Toast.LENGTH_SHORT).show();
//                return true;
//            }
//            case R.id.action_done: {
//                // save profile changes
//                Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
//                new PurchaseConfirmationDialogFragment().show(
//                        getChildFragmentManager(), PurchaseConfirmationDialogFragment.TAG);
//                return true;
//            }
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        callBack = null;
    }

}