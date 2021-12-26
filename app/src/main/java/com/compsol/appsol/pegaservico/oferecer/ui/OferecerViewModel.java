package com.compsol.appsol.pegaservico.oferecer.ui;

import static com.google.firebase.messaging.Constants.MessageNotificationKeys.TAG;

import android.app.Application;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.compsol.appsol.pegaservico.PegaServicoApp;
import com.compsol.appsol.pegaservico.entities.ServiceItem;
import com.compsol.appsol.pegaservico.oferecer.OferecerPresenter;
import com.compsol.appsol.pegaservico.oferecer.adapters.MyServiceListAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

public class OferecerViewModel extends ViewModel implements OferecerView {

    //private MutableLiveData<String> mText;

    private MutableLiveData<ArrayList<ServiceItem>> serviceLiveData;
    ArrayList<ServiceItem> serviceArrayList;
    private OferecerView view = null;
    private Fragment fragment = null;
    private PegaServicoApp app = null;

    @Inject
    MyServiceListAdapter recyclerViewAdapter;
    @Inject
    OferecerPresenter presenter;

    public OferecerViewModel( Fragment fragment, PegaServicoApp app) {

        serviceLiveData = new MutableLiveData<>();

        // call your Rest API in init method
        //init();

        setupInjection( fragment, app );


        serviceArrayList = new ArrayList<>();



    }

    public void setupInjection(Fragment fragment, PegaServicoApp app) {
        //PegaServicoApp app = (PegaServicoApp) getActivity().getApplication();
        app.getOferecerComponent(this, fragment).inject(this);
    }

    public MutableLiveData<ArrayList<ServiceItem>> getServiceMutableLiveData() {
        return serviceLiveData;
    }

    public void init(){
        populateListMockData();
        serviceLiveData.setValue(serviceArrayList);
    }

    public void populateListMockData(){

        ServiceItem user = new ServiceItem();
        user.setNome("Darknight");
        user.setData("10/10;2021");
        user.setEntrada("08:00");
        user.setPeriodo(12);
        user.setStatus(ServiceItem.waitingAcceptStatus);
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
        serviceArrayList.add(user);
        serviceArrayList.add(user);
        serviceArrayList.add(user);
        serviceArrayList.add(user);
        serviceArrayList.add(user);
        serviceArrayList.add(user);

    }

    public MyServiceListAdapter getRecyclerViewAdapter() {
        return recyclerViewAdapter;

    }

    @Override
    public void onServiceReceived(ServiceItem service) {
        serviceArrayList.add(service);
        serviceLiveData.setValue(serviceArrayList);
    }

    @Override
    public void onServiceReceivedError(String error) {
        Log.e(TAG, "onServiceReceivedError: ", null);

    }

    public void onCreate() {
        presenter.registerInEventBus();
    }

    public void onDestroy() {
        presenter.unregisterInEventBus();
    }



    public void subscribeForServicesOfferedUpdates() {
        //if(presenter!=null)
            presenter.subscribeForServicesOfferedUpdates();
    }

    public void unsubscribeForServicesOfferedUpdate() {
        //if(presenter!=null)
            presenter.unsubscribeForServicesOfferedUpdates();
    }


}