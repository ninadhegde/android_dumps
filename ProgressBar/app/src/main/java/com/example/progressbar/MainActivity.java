package com.example.progressbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ProgressBar pb;

    TextView tv;
    Handler handler = new Handler();
    int progressStatus=0;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb=findViewById(R.id.progressBar);
        tv = findViewById(R.id.textView2);
        btn=findViewById(R.id.button);



        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus<100){
                    progressStatus++;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            pb.setProgress(progressStatus);
                            tv.setText(progressStatus+"%");
                        }
                    });
                    try{
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }

                }
            }
        }).start();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Alert!!!");
                builder.setPositiveButton("Accept",(dialog, which) -> {
                    finish();
                });
                builder.setIcon(R.drawable.warning);
                builder.setNegativeButton("Reject",(dialog, which) ->
                {
                    dialog.cancel();
                });
                builder.setMessage("Are you sure you want to exit");
                builder.setCancelable(false);
//                builder.setIcon(R.drawable.alert);
                AlertDialog ad =builder.create();
                ad.show();
            }
        });
    }
}