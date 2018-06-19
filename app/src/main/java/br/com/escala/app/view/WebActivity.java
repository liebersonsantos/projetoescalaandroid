package br.com.escala.app.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import br.com.escala.app.R;

public class WebActivity extends AppCompatActivity {

    private WebView webView;
    private String urlOnline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Bundle extra = getIntent().getExtras();

        if (extra != null){
            urlOnline = getIntent().getStringExtra("URL_ONLINE");
        }

        webView = findViewById(R.id.web_view);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);

        webView.loadUrl(urlOnline);


    }
}
