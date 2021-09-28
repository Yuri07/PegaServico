package com.compsol.appsol.pegaservico.pegar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.compsol.appsol.pegaservico.databinding.FragmentPegarBinding;

public class PegarFragment extends Fragment {

    private PegarViewModel pegarViewModel;
    private FragmentPegarBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pegarViewModel =
                new ViewModelProvider(this).get(PegarViewModel.class);

        binding = FragmentPegarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textPegar;
        pegarViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}