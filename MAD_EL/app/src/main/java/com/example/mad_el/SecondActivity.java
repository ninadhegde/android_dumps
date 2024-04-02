package com.example.mad_el;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    private TextView codeTextView;
    private ProgressBar progressBar;
    private CountDownTimer countDownTimer;
    private String currentCode;
    private String accountName;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        EdgeToEdge.enable(this);

        Button copyButton = findViewById(R.id.copyButton);
        Intent intent=getIntent();
        accountName = intent.getStringExtra("account");
        codeTextView = findViewById(R.id.codeTextView);
        progressBar = findViewById(R.id.progressBar6);
        progressBar.setIndeterminate(false);
        progressBar.setMax(15000); // 30 seconds
        TextView display=findViewById(R.id.accountDisplay);

        display.setText("Code for "+accountName+" : ");
        generateAndDisplayCode();

        // Add touch listener to codeTextView

        countDownTimer = new CountDownTimer(15000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress((int) millisUntilFinished);
            }
            @Override
            public void onFinish() {
                progressBar.setProgress(0);
                // Generate new code
                generateAndDisplayCode();
                // Send API request with the code
                sendCall(currentCode,accountName);
                // Restart countdown
                start();
            }
        };
        countDownTimer.start();
        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    // Copy currentCode to clipboard

                    copyCurrentCode();
                Snackbar.make(v,"Code copied to clipboard",Snackbar.LENGTH_LONG).show();

            }
        });
        codeTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    copyCurrentCode();
                    Toast.makeText(SecondActivity.this, "Code copied to clipboard", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        codeTextView.setTextSize(codeTextView.getTextSizeUnit()*2);
        highlightText(Color.YELLOW);

    }

    public  void copyCurrentCode() {

        // Copy currentCode to clipboard
        System.out.println("in copy to ClipBoard method");
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("code", currentCode);
        clipboard.setPrimaryClip(clip);
    }

    private void generateAndDisplayCode() {
        // Generate new code
        currentCode = generateRandomCode();

        // Display the code
        codeTextView.setText(currentCode);
    }

    private String generateRandomCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }

    private void sendCall(String code, String accountName) {
        // Your code to send API request with the generated code and account name
        // Example of sending an API request using HttpURLConnection:
        Toast.makeText(SecondActivity.this, "Code Expired, check new code", Toast.LENGTH_SHORT).show();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Example URL
                    String apiUrl = "https://localhost:5699/authenticate"+accountName;

                    // Create JSON request body
                    JSONObject jsonBody = new JSONObject();
                    jsonBody.put("code", code);
                    jsonBody.put("accountName", accountName);

                    // Create HttpURLConnection
                    URL url = new URL(apiUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoOutput(true);

                    // Write data to the connection output stream
                    OutputStream os = conn.getOutputStream();
                    os.write(jsonBody.toString().getBytes());
                    os.flush();
                    os.close();

                    // Get response code
                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // Read response from input stream if needed
                        InputStream inputStream = conn.getInputStream();
                        // Process the response...
                    } else {
                        // Handle error response...
                    }

                    // Disconnect the connection
                    conn.disconnect();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }


    private void highlightText(int highlightColor) {
        // Create a SpannableString for applying different styles
        SpannableString text = new SpannableString(codeTextView.getText());

        // Apply background color to the entire text for highlighting effect
        text.setSpan(new BackgroundColorSpan(highlightColor), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Animate the highlight color change (optional)
        ValueAnimator animator = ValueAnimator.ofInt(Color.TRANSPARENT, highlightColor);
        animator.setDuration(300); // Adjust duration as desired (in milliseconds)
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int color = (int) animation.getAnimatedValue();
                text.setSpan(new BackgroundColorSpan(color), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                codeTextView.setText(text);
            }
        });
        animator.start();
    }

}