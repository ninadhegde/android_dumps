package com.example.myprogram6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText send_no,send_msg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.btn);
        send_no=findViewById(R.id.send_no);
        send_msg=findViewById(R.id.send_msg);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    SmsManager smsmanager=SmsManager.getDefault();
                    smsmanager.sendTextMessage(send_no.getText().toString(),null,null);
                    Snackbar.make(v,"SMS Sent",Snackbar.LENGTH_LONG);
                }
                catch (Exception e){
                    Snackbar.make(v,"SMS Sent",Snackbar.LENGTH_LONG);

                }
            }
        });
    }
}