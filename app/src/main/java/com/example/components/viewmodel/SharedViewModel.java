package com.example.components.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<CharSequence> text= new MutableLiveData<>();

    public MutableLiveData<CharSequence> getText() {
        return text;
    }

    public void setText(CharSequence text) {
        this.text.setValue(text);
    }
}
