package com.example.mybooklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class WebsiteActivity extends AppCompatActivity {

//    private WebView webView;
    private TextView txtAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);

//        Intent intent= getIntent();
//        if (intent!=null){
//            String url= intent.getStringExtra("url");
//            webView=findViewById(R.id.webView);
//            webView.loadUrl(url);
//            webView.setWebViewClient(new WebViewClient());
////            webView.getSettings().setJavaScriptEnabled(true);
//        }

        txtAbout=findViewById(R.id.txtAbout);
        txtAbout.setText("This Application is for learning purposes only All the books shown are just demonstration and not actual books");

    }

//    @Override
//    public void onBackPressed() {
//        if(webView.canGoBack()){
//            webView.goBack();
//        }
//        else {
//            super.onBackPressed();
//        }
//
//    }
}