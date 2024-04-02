package com.example.mad_el;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    Button login,cancel;
    EditText uname,pass;

    DbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);

        login=findViewById(R.id.button);
        cancel=findViewById(R.id.button2);
        uname=findViewById(R.id.editTextText);
        pass=findViewById(R.id.editTextText2);



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=uname.getText().toString();
                String password=pass.getText().toString();

                int id=checkUser(new User(name,password));
                if (id==-1){
                    Snackbar.make(v,"Account "+name+" does not exist",Snackbar.LENGTH_LONG).show();
                }else {
                    Snackbar.make(v,"Account "+name+" exists",Snackbar.LENGTH_LONG).show();
                    Intent i=new Intent(MainActivity.this,SecondActivity.class);
                    i.putExtra("account" , name);
                    startActivity(i);
                }
            }
        });

        db=new DbHandler(MainActivity.this);
        db.addUser(new User("google","ninad"));
        db.addUser(new User("microsoft","ninad"));


    }

    public int checkUser(User user){
        return db.checkUser(user);
    }
}