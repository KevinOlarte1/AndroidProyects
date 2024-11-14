package com.example.fragmentgame;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fragmentgame.models.parsers.WordParser;
import com.example.fragmentgame.ui.fragments.AhorcadoFragment;
import com.example.fragmentgame.ui.fragments.MyFragmentStateAdapter;
import com.example.fragmentgame.ui.fragments.FirstFragment;
import com.example.fragmentgame.ui.fragments.SecondFragment;
import com.example.fragmentgame.ui.fragments.ThirdFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        FirstFragment.IOnAttachListener, SecondFragment.IOnAttachListener,
        ThirdFragment.IOnAttachListener, AhorcadoFragment.IOnAttachListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MyFragmentStateAdapter myFragmentStateAdapter = new MyFragmentStateAdapter(this);
        ViewPager2 mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(myFragmentStateAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        TabLayoutMediator tlm = new TabLayoutMediator(tabLayout, mViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                String text = switch (position) {
                    case 0 -> getString(R.string.AHORCADO);
                    case 1 -> getString(R.string.TRES_EN_RALLA);
                    default -> "";
                };
                tab.setText(text);
            }
        });
        tlm.attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public String getData() {
        return "ASD";
    }

    @Override
    public List<String> getWords() {
        return WordParser.parserWords(this);
    }
}
