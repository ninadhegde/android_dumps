package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText uname;

    EditText password;

    Button login;
    Button cancel;
    User u;
    DbHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname=findViewById(R.id.uname);
        password = findViewById(R.id.password);

        login = findViewById(R.id.login);
        cancel = findViewById(R.id.cancel);
//        db = new DbHandler(MainActivity.this);
        db = new DbHandler(MainActivity.this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=uname.getText().toString();
                String pwd = password.getText().toString();
                int id = checkUser(new User(name,pwd));

                if(id==-1){
                    Snackbar.make(v,"User "+name+ " does not exist",Snackbar.LENGTH_LONG);
                }
                else {
                    Snackbar.make(v,"User "+name+ " does exist",Snackbar.LENGTH_LONG);
                    Intent i = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(i);
                }
            }


        });
        User usr=new User("abc","password");
        db.addUser(usr);
        db.addUser(new User("root","password"));

        db.addUser(new User("xyz","xyz"));
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private int checkUser(User user) {
        return db.checkUser(user);
    }
}