package com.example.fragmentgame.ui.fragments;

import androidx.fragment.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fragmentgame.R;


public class ThirdFragment extends Fragment {

    public interface IOnAttachListener {
        String getData();
    }
    private String data;
    public ThirdFragment() {
        super(R.layout.fragment_main);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvData = view.findViewById(R.id.tvData);
        tvTitle.setText(getString(R.string.seccion, getClass().getSimpleName()));
        tvData.setText(data);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        SecondFragment.IOnAttachListener attachListener = (SecondFragment.IOnAttachListener) context;
        data = attachListener.getData();
    }
}
