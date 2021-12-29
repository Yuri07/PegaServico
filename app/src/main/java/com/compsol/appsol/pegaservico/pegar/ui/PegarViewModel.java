package com.compsol.appsol.pegaservico.pegar.ui;

import static com.google.firebase.messaging.Constants.MessageNotificationKeys.TAG;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.compsol.appsol.pegaservico.PegaServicoApp;
import com.compsol.appsol.pegaservico.entities.ServiceItem;
import com.compsol.appsol.pegaservico.oferecer.OferecerPresenter;
import com.compsol.appsol.pegaservico.oferecer.adapters.MyServiceListAdapter;
import com.compsol.appsol.pegaservico.oferecer.ui.OferecerView;
import com.compsol.appsol.pegaservico.pegar.PegarPresenter;
import com.compsol.appsol.pegaservico.pegar.adapters.OfferedServicesListAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

public class PegarViewModel extends ViewModel implements PegarView{

    private MutableLiveData<ArrayList<ServiceItem>> serviceLiveData;
    ArrayList<ServiceItem> serviceArrayList;
    private PegarView view = null;
    private Fragment fragment = null;
    private PegaServicoApp app = null;

    @Inject
    OfferedServicesListAdapter recyclerViewAdapter;
    @Inject
    PegarPresenter presenter;

    public PegarViewModel(Fragment fragment, PegaServicoApp app) {
        serviceLiveData = new MutableLiveData<>();

        // call your Rest API in init method
        //init();

        setupInjection( fragment, app );


        serviceArrayList = new ArrayList<>();
    }

    public void setupInjection(Fragment fragment, PegaServicoApp app) {
        //PegaServicoApp app = (PegaServicoApp) getActivity().getApplication();
        app.getPegarComponent(this, fragment).inject(this);
    }

    public MutableLiveData<ArrayList<ServiceItem>> getServiceMutableLiveData() {
        return serviceLiveData;
    }

    public OfferedServicesListAdapter getRecyclerViewAdapter() {
        return recyclerViewAdapter;
    }

    public LiveData<String> getText() {
        return null;//mText;
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
        presenter.subscribeForOfferedServicesUpdates();
    }

    public void unsubscribeForServicesOfferedUpdate() {
        //if(presenter!=null)
        presenter.unsubscribeForOfferedServicesUpdates();
    }

}