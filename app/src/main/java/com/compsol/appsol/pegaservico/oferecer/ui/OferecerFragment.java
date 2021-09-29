package com.compsol.appsol.pegaservico.oferecer.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import com.compsol.appsol.pegaservico.oferecer.OferecerPresenter;
import com.compsol.appsol.pegaservico.oferecer.adapters.MyServiceListAdapter;
import com.compsol.appsol.pegaservico.oferecer.ui.OferecerView;

import java.util.ArrayList;

import javax.inject.Inject;

public class OferecerFragment extends Fragment implements LifecycleOwner, OferecerView {

    private OferecerViewModel oferecerViewModel;
    private FragmentOferecerBinding binding;
    RecyclerView recyclerView;

    @Inject
    MyServiceListAdapter recyclerViewAdapter;
    //@Inject
    //OferecerPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupInjection();
        //presenter.onCreate();
    }

    private void setupInjection() {
        PegaServicoApp app = (PegaServicoApp) getActivity().getApplication();
        app.getOferecerComponent(this, this).inject(this);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        oferecerViewModel = new ViewModelProvider(this).get(OferecerViewModel.class);

        binding = FragmentOferecerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.rvOferecer;

        oferecerViewModel.getServiceMutableLiveData()
                .observe(getViewLifecycleOwner(), new Observer<ArrayList<ServiceItem>>() {
            @Override
            public void onChanged(ArrayList<ServiceItem> servicesArrayList) {
                //recyclerViewAdapter = new MyServiceListAdapter(servicesArrayList,null);
                if(servicesArrayList!=null)
                    recyclerViewAdapter.setServicesList(servicesArrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        });



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onRatingAdded(ServiceItem rating) {

    }

    @Override
    public void onRatingError(String error) {

    }
}