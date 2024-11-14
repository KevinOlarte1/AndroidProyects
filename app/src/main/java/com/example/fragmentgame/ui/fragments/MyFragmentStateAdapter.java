package com.example.fragmentgame.ui.fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyFragmentStateAdapter extends FragmentStateAdapter {
    private final int NUM_TABS = 3;
    public MyFragmentStateAdapter(FragmentActivity fa) {
        super(fa);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return switch (position) {
            case 0 -> new AhorcadoFragment();
            case 1 -> new SecondFragment();
            case 2 -> new ThirdFragment();
            default -> throw new RuntimeException("Invalid Fragment");
        };
    }
    @Override
    public int getItemCount() {
        return NUM_TABS;
    }
}
