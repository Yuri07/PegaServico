package com.compsol.appsol.pegaservico.oferecer.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.compsol.appsol.pegaservico.entities.ServiceItem;

import java.util.ArrayList;

public class OferecerViewModel extends ViewModel {

    //private MutableLiveData<String> mText;

    private MutableLiveData<ArrayList<ServiceItem>> serviceLiveData;
    ArrayList<ServiceItem> serviceArrayList;

    public OferecerViewModel() {

        serviceLiveData = new MutableLiveData<>();

        // call your Rest API in init method
        init();

    }

    public MutableLiveData<ArrayList<ServiceItem>> getServiceMutableLiveData() {
        return serviceLiveData;
    }

    public void init(){
        populateList();
        serviceLiveData.setValue(serviceArrayList);
    }

    public void populateList(){

        ServiceItem user = new ServiceItem();
        user.setNome("Darknight");
        user.setData("10/10;2021");
        user.setEntrada("08:00");
        user.setPeriodo(12);
        user.setStatus(1);
        user.setValor(80.0);
        user.setUrlPhotoUser("url_foto");


        serviceArrayList = new ArrayList<>();
        serviceArrayList.add(user);
        serviceArrayList.add(user);
        serviceArrayList.add(user);
        serviceArrayList.add(user);
        serviceArrayList.add(user);
        serviceArrayList.add(user);
        serviceArrayList.add(user);
        serviceArrayList.add(user);
        serviceArrayList.add(user);
        serviceArrayList.add(user);

    }

}