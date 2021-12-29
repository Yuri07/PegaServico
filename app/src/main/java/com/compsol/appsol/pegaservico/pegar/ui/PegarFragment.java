package com.compsol.appsol.pegaservico.pegar.ui;

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
import com.compsol.appsol.pegaservico.databinding.FragmentPegarBinding;
import com.compsol.appsol.pegaservico.entities.ServiceItem;
import com.compsol.appsol.pegaservico.oferecer.ui.OferecerViewModel;
import com.compsol.appsol.pegaservico.oferecer.ui.OferecerViewModelFactory;

import java.util.ArrayList;

public class PegarFragment extends Fragment implements LifecycleOwner {

    private PegarViewModel pegarViewModel;
    private FragmentPegarBinding binding;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        PegaServicoApp app = (PegaServicoApp) getActivity().getApplication();

        //pegarViewModel = new ViewModelProvider(this).get(PegarViewModel.class);
        pegarViewModel =
                    new ViewModelProvider(this,
                                new PegarViewModelFactory(this, app)).get(PegarViewModel.class);

        pegarViewModel.onCreate();

        binding = FragmentPegarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.rvPegar;


        pegarViewModel.getServiceMutableLiveData()
                .observe(getViewLifecycleOwner(), new Observer<ArrayList<ServiceItem>>() {
            @Override
            public void onChanged(ArrayList<ServiceItem> servicesArrayList) {
                if(servicesArrayList!=null)
                    pegarViewModel.getRecyclerViewAdapter().setServicesList(servicesArrayList);
                //recyclerViewAdapter.setServicesList(servicesArrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                //recyclerView.setAdapter(recyclerViewAdapter);
                recyclerView.setAdapter(pegarViewModel.getRecyclerViewAdapter());
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        binding = null;
        if(pegarViewModel!=null)
            pegarViewModel.onDestroy();
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(pegarViewModel!=null)
            pegarViewModel.subscribeForServicesOfferedUpdates();
    }

    @Override
    public void onPause() {
        if(pegarViewModel!=null)
            pegarViewModel.unsubscribeForServicesOfferedUpdate();
        super.onPause();
    }

}