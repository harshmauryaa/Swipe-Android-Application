package com.harsh.swipeandroidassignment.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.harsh.swipeandroidassignment.NetworkChangeReceiver;
import com.harsh.swipeandroidassignment.R;
import com.harsh.swipeandroidassignment.ui.fragments.AddProductFragment;
import com.harsh.swipeandroidassignment.ui.fragments.ProductListFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigation;

    private NetworkChangeReceiver networkChangeReceiver;
    private TextView networkStatusTextView;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new ProductListFragment());
        fragmentList.add(new AddProductFragment());

        FragmentStateAdapter pagerAdapter = new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getItemCount() {
                return fragmentList.size();
            }
        };
        viewPager.setAdapter(pagerAdapter);


        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                bottomNavigation.getMenu().getItem(position).setChecked(true);
            }
        });


        bottomNavigation.setOnItemSelectedListener(item -> {

            int id = item.getItemId();
            int position = 0;
            if (id == R.id.navigation_add_product) {
                position = 1;
            } else {
                /* Else condition is not required but added for better understanding.
                Also used if-else ladder instead of switch case as there was only two fragments.
                * */
                position = 0;
            }

            viewPager.setCurrentItem(position, false);
            return true;
        });

        // Internet Indicator
        networkStatusTextView = findViewById(R.id.networkStatusTextView);
        networkChangeReceiver = new NetworkChangeReceiver(this,networkStatusTextView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(networkChangeReceiver);
    }
}