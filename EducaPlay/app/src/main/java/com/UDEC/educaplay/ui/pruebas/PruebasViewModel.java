package com.UDEC.educaplay.ui.pruebas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PruebasViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PruebasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}