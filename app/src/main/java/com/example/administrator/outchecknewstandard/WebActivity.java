package com.example.administrator.outchecknewstandard;

import android.os.Build.VERSION;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.example.administrator.outchecknewstandard.base.NewlyBaseActivity;

public class WebActivity extends NewlyBaseActivity {
    String url = "http://172.0.1.216:8080/vepts/pda/openappmain/15020301/C5439485AADB451DADED75FD4FF8DE57/test/123";
    private WebView webView;

    protected int getContentViewResId() {
        return R.layout.activity_web;
    }

    protected void initContent() {
        this.webView = (WebView) findViewById(R.id.web_view_test);
    }

    protected void initData() {
        setupWebView();
    }

    private void setupWebView() {
        WebSettings settings = this.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setDisplayZoomControls(false);
        settings.setAllowContentAccess(true);
        settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        this.webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (VERSION.SDK_INT >= 26) {
                    return false;
                }
                view.loadUrl(url);
                return true;
            }
        });
        this.webView.setWebChromeClient(new WebChromeClient());
        this.webView.loadUrl(this.url);
    }

    public void onClick(View view) {
    }
}
