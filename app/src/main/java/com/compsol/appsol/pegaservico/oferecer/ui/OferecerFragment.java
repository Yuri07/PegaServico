package com.compsol.appsol.pegaservico.oferecer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.compsol.appsol.pegaservico.PegaServicoApp;
import com.compsol.appsol.pegaservico.databinding.FragmentOferecerBinding;
import com.compsol.appsol.pegaservico.entities.ServiceItem;
import com.compsol.appsol.pegaservico.main.ui.MainActivity;
import com.compsol.appsol.pegaservico.oferecer.adapters.MyServiceListAdapter;
import com.compsol.appsol.pegaservico.servico.ui.ServicoActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import javax.inject.Inject;

public class OferecerFragment extends Fragment implements LifecycleOwner{//}, OferecerView {

    private OferecerViewModel oferecerViewModel;
    private FragmentOferecerBinding binding;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    MyServiceListAdapter recyclerViewAdapter;
    //@Inject
    //MyServiceListAdapter recyclerViewAdapter;
    //@Inject
    //OferecerPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setupInjection();
        //presenter.onCreate();
    }

    /*private void setupInjection() {
        PegaServicoApp app = (PegaServicoApp) getActivity().getApplication();
        app.getOferecerComponent(this, this).inject(this);
    }*/

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //oferecerViewModel = new ViewModelProvider(this).get(OferecerViewModel.class);

        PegaServicoApp app = (PegaServicoApp) getActivity().getApplication();

        oferecerViewModel = new ViewModelProvider(this, new OferecerViewModelFactory(this, app)).get(OferecerViewModel.class);
        //    MyViewModel myViewModel = ViewModelProvider(this, new MyViewModelFactory(this.getApplication(), "my awesome param")).get(MyViewModel.class);
        //https://stackoverflow.com/questions/46283981/android-viewmodel-additional-arguments

        oferecerViewModel.onCreate();

        //recyclerViewAdapter = oferecerViewModel.getRecyclerViewAdapter();
        binding = FragmentOferecerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.rvOferecer;
        fab = binding.floatingActionButton;

        oferecerViewModel.getServiceMutableLiveData()
                .observe(getViewLifecycleOwner(), new Observer<ArrayList<ServiceItem>>() {
            @Override
            public void onChanged(ArrayList<ServiceItem> servicesArrayList) {
                //recyclerViewAdapter = new MyServiceListAdapter(servicesArrayList,null);
                if(servicesArrayList!=null)
                    oferecerViewModel.getRecyclerViewAdapter().setServicesList(servicesArrayList);
                    //recyclerViewAdapter.setServicesList(servicesArrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                //recyclerView.setAdapter(recyclerViewAdapter);
                recyclerView.setAdapter(oferecerViewModel.getRecyclerViewAdapter());
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivityForResult(new Intent(getContext(), ServicoActivity.class),
                                                MainActivity.INTENT_REQUEST_CODE_ADD_SERVICO);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        binding = null;
        if(oferecerViewModel!=null)
            oferecerViewModel.onDestroy();
        super.onDestroyView();

    }

    @Override
    public void onResume() {
        super.onResume();
        if(oferecerViewModel!=null)
            oferecerViewModel.subscribeForServicesOfferedUpdates();
    }

    @Override
    public void onPause() {
        if(oferecerViewModel!=null)
            oferecerViewModel.unsubscribeForServicesOfferedUpdate();
        super.onPause();
    }



}