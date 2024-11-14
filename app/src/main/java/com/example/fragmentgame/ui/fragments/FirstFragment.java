package com.example.fragmentgame.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fragmentgame.R;

public class FirstFragment extends Fragment {
    public interface IOnAttachListener {
        String getData();
    }
    private String data;
    public FirstFragment() {
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
        Log.i("Nuevo", "Nuevo");
        super.onAttach(context);
        SecondFragment.IOnAttachListener attachListener = (SecondFragment.IOnAttachListener) context;
        data = attachListener.getData();
    }

}
