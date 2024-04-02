package com.example.program2;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btn;
    ProgressBar progressBar;
    TextView tv;
    int progressStatus = 0;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);
        tv = findViewById(R.id.textView);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressStatus<100){
                    progressStatus+=1;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            tv.setText(progressStatus+"%");
                        }
                    });
                    try {
                        Thread.sleep(500);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Warning");
                builder.setMessage("Are you sure to exit?");
                builder.setPositiveButton("yes", (dialog, which) -> {
                    finish();
                });
                builder.setNegativeButton("no",(dialog, which) -> {
                    dialog.cancel();
                });
                builder.setNeutralButton("cancel",(dialog, which) -> {
                    dialog.cancel();
                });
                builder.setCancelable(false);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });
    }
}