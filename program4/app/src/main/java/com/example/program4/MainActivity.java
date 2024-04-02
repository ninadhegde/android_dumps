package com.example.program4;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    FragmentOne fragmentOne=new FragmentOne();
    FragmentTwo fragmentTwo=new FragmentTwo();
    int showFragment=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.clayout,fragmentOne);
        showFragment=1;
        fragmentTransaction.commit();
    }
    public void switchFragment(View view){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        if (showFragment==1){
            fragmentTransaction.replace(R.id.clayout,fragmentTwo);
            showFragment=2;
        }else {
            fragmentTransaction.replace(R.id.clayout,fragmentOne);
            showFragment=1;
        }
        fragmentTransaction.commit();
    }
}