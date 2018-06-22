package br.com.escala.app.view;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import br.com.escala.app.R;

public class WebActivity extends AppCompatActivity {

    private WebView webView;
    private String urlOnline;
    private ProgressBar progressBarWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Bundle extra = getIntent().getExtras();

        if (extra != null){
            urlOnline = getIntent().getStringExtra("URL_ONLINE");
        }

        webView = findViewById(R.id.web_view);
        progressBarWeb = findViewById(R.id.progress_bar_web);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);

        webView.loadUrl(urlOnline);

        /*METODO PARA ACESSAR O PROGRESSBAR*/
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                progressBarWeb.setVisibility(View.VISIBLE);

            }

            @Override
            public void onPageFinished(WebView view, String url){
                progressBarWeb.setVisibility(View.INVISIBLE);
                webView.setVisibility(View.VISIBLE);

            }

        });

    }
}
