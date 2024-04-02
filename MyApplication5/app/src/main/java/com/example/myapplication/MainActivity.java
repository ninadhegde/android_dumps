package com.example.myapplication;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable JavaScript if your website requires it
        webSettings.setDomStorageEnabled(true); // Enable DOM Storage to store data locally

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // Load URL in WebView
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                // Show error message if page fails to load
                Toast.makeText(MainActivity.this, "Error: " + description, Toast.LENGTH_SHORT).show();
            }
        });

        webView.setWebChromeClient(new WebChromeClient());

        // Check network connectivity before loading URL
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() {
                @Override
                public void onAvailable(@NonNull Network network) {
                    // Load initial URL when network is available
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            webView.loadUrl("https://junction.cj.com/"); // Replace with your website URL
                        }
                    });
                }

                @Override
                public void onLost(@NonNull Network network) {
                    // Handle network loss
                    Toast.makeText(MainActivity.this, "Network connection lost.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Show error message if no network connection
            Toast.makeText(this, "No network connection. Please try again later.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        // Handle back button press to navigate back in WebView
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
