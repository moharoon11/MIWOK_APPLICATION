package com.example.miwok_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ViewPager viewPager = (ViewPager) findViewById(R.id.miwok_view_pager);

        PositionAdapter pa = new PositionAdapter(this,getSupportFragmentManager());

        viewPager.setAdapter(pa);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.miwok_tab_layout);

        tabLayout.setupWithViewPager(viewPager);



    }
}