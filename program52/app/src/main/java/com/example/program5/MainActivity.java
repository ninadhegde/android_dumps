package com.example.program5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText uname,pass;
    Button btn;

    DbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        uname = findViewById(R.id.editTextText);
        pass = findViewById(R.id.editTextText2);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = uname.getText().toString();
                String password = pass.getText().toString();
                int id = checkUser(new User(username,password));
                if (id == -1){
                    Snackbar.make(v,"User "+username+" does not exists",Snackbar.LENGTH_LONG).show();
                }else{
                    Snackbar.make(v,"User "+username+" exists",Snackbar.LENGTH_LONG).show();
                    Intent i = new Intent(MainActivity.this,second_activity.class);
                    startActivity(i);
                }
            }
        });
        db = new DbHandler(MainActivity.this);
        db.addUser(new User("User1","pss1"));
    }
    public int checkUser(User user){
        return db.checkUSer(user);
    }
}