package com.compsol.appsol.pegaservico.oferecer;

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
import androidx.recyclerview.widget.RecyclerView;

import com.compsol.appsol.pegaservico.databinding.FragmentOferecerBinding;
import com.compsol.appsol.pegaservico.entities.ServiceItem;
import com.compsol.appsol.pegaservico.oferecer.adapters.MyServiceListAdapter;
import com.compsol.appsol.pegaservico.oferecer.ui.OferecerView;

public class OferecerFragment extends Fragment implements LifecycleOwner, OferecerView {

    private OferecerViewModel oferecerViewModel;
    private FragmentOferecerBinding binding;
    RecyclerView recyclerView;
    MyServiceListAdapter recyclerViewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        oferecerViewModel = new ViewModelProvider(this).get(OferecerViewModel.class);

        binding = FragmentOferecerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*final TextView textView = binding.textOferecer;
        oferecerViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        recyclerView = binding.rvOferecer;



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