package com.example.first;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = findViewById(R.id.button);
        Switch switch1=findViewById(R.id.switch1);
// it will give you the reference to the view in XML layouts by searching its ID

        ConstraintLayout layout = findViewById(R.id.Layout);

        CalendarView cal = new CalendarView(this);
// create the calenderview dynamically
        layout.addView(cal);
//adding the calenderView dyanamically to the constraintLayout
        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switch1.isChecked())
                { //if the switch is checked the calender view is removed
                    // and button is enabled
                    button.setEnabled(true);
                    layout.removeView(cal);
                    Toast.makeText(MainActivity.this,"Button is Enabled",Toast.LENGTH_LONG).show();



                }
                else
                {//if the switch is unchecked the calender view is removed
                    // and button is disabled

                    Toast.makeText(MainActivity.this,"Button is Disabled",Toast.LENGTH_LONG).show();


                    button.setEnabled(false);
                    layout.addView(cal);
                }
            }
        });

    }
}