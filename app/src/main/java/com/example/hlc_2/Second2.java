package com.example.hlc_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.hlc_2.databinding.ActivityMain3Binding;
import com.example.hlc_2.databinding.ActivitySecond2Binding;


public class Second2 extends AppCompatActivity implements View.OnClickListener {
    private @NonNull ActivitySecond2Binding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);
        binding = ActivitySecond2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Bundle extras = getIntent().getExtras();
        String url = extras.getString("web");

        binding.webView.setWebViewClient(new MyWebViewClient());
        WebSettings settings = binding.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        binding.webView.loadUrl(url);

    }

    private class MyWebViewClient extends WebViewClient
    {
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }
    }
    @Override
    public void onClick(View view) {

    }
}
