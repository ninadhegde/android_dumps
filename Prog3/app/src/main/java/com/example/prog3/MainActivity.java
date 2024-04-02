package com.example.prog3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment1 fragment1=new Fragment1();
        Fragment2 fragment2=new Fragment2();
        FragmentManager managerF = getSupportFragmentManager();
        // FragmentManager is the class responsible for performing actions
        // on your app's fragments,
        // such as adding, removing, or replacing them
        FragmentTransaction Ftransaction = managerF.beginTransaction();
        //a FragmentManager can add, remove, replace, and perform other actions with fragments
        // in response to user interaction. Each set of fragment changes that you
        // commit is called a transaction
        Ftransaction.add(R.id.frameLayout,fragment1);
        //add the  Fragment
        Button b1 = findViewById(R.id.button);
        Button b2 = findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager managerF = getSupportFragmentManager();
                FragmentTransaction Ftransaction = managerF.beginTransaction();

                Ftransaction.replace(R.id.frameLayout,fragment1);

                //replace the  Fragment
                Toast.makeText(MainActivity.this,"Changed to Fragment 1",Toast.LENGTH_LONG).show();
                Ftransaction.commit();
                //Commit the changes in the Activity Main.
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager managerF = getSupportFragmentManager();
                FragmentTransaction Ftransaction = managerF.beginTransaction();

                Ftransaction.replace(R.id.frameLayout,fragment2);

                Toast.makeText(MainActivity.this,"Changed to Fragment 2",Toast.LENGTH_LONG).show();
                Ftransaction.commit();
            }
        });
    }
}