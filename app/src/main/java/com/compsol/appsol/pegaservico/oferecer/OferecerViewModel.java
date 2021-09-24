package com.compsol.appsol.pegaservico.oferecer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OferecerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public OferecerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Esse é o fragment Oferecer");
    }

    public LiveData<String> getText() {
        return mText;
    }
}