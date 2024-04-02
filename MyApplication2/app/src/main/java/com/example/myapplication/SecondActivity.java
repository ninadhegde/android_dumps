package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView recive_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        recive_text = findViewById(R.id.textCatch);
        Intent intent = getIntent();
        String str = intent.getStringExtra("msg");
        recive_text.setText("Wlecome "+str);



    }
}