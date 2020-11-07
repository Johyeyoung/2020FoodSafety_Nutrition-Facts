package com.example.newfacts;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity{

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentMain fragmentMain = new FragmentMain();
    private FragmentCategory fragmentCategory = new FragmentCategory();
    private FragmentLocation fragmentLocation = new FragmentLocation();
    private FragmentMyinfo fragmentMyinfo = new FragmentMyinfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentMain).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch(menuItem.getItemId())
            {
                case R.id.Main:
                    transaction.replace(R.id.frameLayout, fragmentMain).commitAllowingStateLoss();

                    break;
                case R.id.Category:
                    transaction.replace(R.id.frameLayout, fragmentCategory).commitAllowingStateLoss();
                    break;
                case R.id.Location:
                    transaction.replace(R.id.frameLayout, fragmentLocation).commitAllowingStateLoss();
                    break;
                case R.id.MyInfo:
                    transaction.replace(R.id.frameLayout, fragmentMyinfo).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }
}