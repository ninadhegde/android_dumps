package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    OneFragment oneFragment = new OneFragment();
    TwoFragment twoFragment = new TwoFragment();
    int showFragment=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager() ;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.clayout,oneFragment);
        showFragment=1;
        fragmentTransaction.commit();


    }
    public void swichFragment(View view){
        FragmentManager fragmentManager = getSupportFragmentManager() ;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(showFragment==1) {
            fragmentTransaction.replace(R.id.clayout, twoFragment);
            showFragment=2;
        }
        else {
            fragmentTransaction.replace(R.id.clayout, oneFragment);
            showFragment=1;
        }
        fragmentTransaction.commit();
    }
}