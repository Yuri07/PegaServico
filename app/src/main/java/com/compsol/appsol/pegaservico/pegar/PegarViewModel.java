package com.compsol.appsol.pegaservico.pegar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PegarViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PegarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Esse é o fragment Pegar");
    }

    public LiveData<String> getText() {
        return mText;
    }
}