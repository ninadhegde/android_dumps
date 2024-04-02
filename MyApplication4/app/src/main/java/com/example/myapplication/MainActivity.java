package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {


    EditText message;
    EditText phone;

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message=findViewById(R.id.msg);
        phone=findViewById(R.id.phone);

        btn=findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phone.getText().toString(),null,message.getText().toString(),null,null);
                    Snackbar.make(v,"SMS Sent Successfully",Snackbar.LENGTH_LONG).show();

                }
                catch (Exception e){
                    Snackbar.make(v,"SMS not Sent",Snackbar.LENGTH_LONG).show();

                }

            }
        });


    }

}