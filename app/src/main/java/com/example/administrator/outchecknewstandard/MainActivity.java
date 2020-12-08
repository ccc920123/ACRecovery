package com.example.administrator.outchecknewstandard;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.administrator.outchecknewstandard.accredit.ProjectActivity;
import com.example.administrator.outchecknewstandard.base.NewlyBaseActivity;
import com.example.administrator.outchecknewstandard.constant.Constant;
import com.example.administrator.outchecknewstandard.sp.base.PreferenceManager;
import com.example.administrator.outchecknewstandard.sp.i.IAccreditPreferences;
import com.example.administrator.outchecknewstandard.sp.i.IUserPreferences;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

import kr.co.namee.permissiongen.PermissionGen;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class MainActivity extends NewlyBaseActivity {
    private static final String TAG = "MainActivity";
    int MY_PERMISSION_CODE = 100;
    private int PHOTO_REQUEST_CODE = 1000;
    private String account;
    private IAccreditPreferences accreditPreferences;
    private FrameLayout container;
    private ProgressDialog dialog;
    private DrawerLayout drawer;
    private LinearLayout errorlay;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (MainActivity.this.dialog.isShowing()) {
                MainActivity.this.dialog.cancel();
            }
            try {
                JSONObject json = new JSONObject();
                json.put("zpbh", MainActivity.this.photoNumber);
                json.put("photo_date", MainActivity.this.photo_date);
                json.put("photo", MainActivity.this.imgbese);
                MainActivity.this.webView.loadUrl("javascript:cameraResult(" + json.toString() + ")");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
    private String imgbese;
    private String ip;
    private String jkxlh;
    private String password;
    private String photoNumber;
    private String photoPath;
    private String photo_date;
    private String port;
    private ProgressBar progressBar;
    private String stationNumber;
    private Toolbar titleBar;
    private String url = "";
    private IUserPreferences userPreferences;
    private WebView webView;
    private LinearLayout webviewlay;

    class JavaScriptinterface {
        Context context;

        public JavaScriptinterface(Context c) {
            this.context = c;
        }

        @JavascriptInterface
        public boolean authorization() {
            return MainActivity.this.accreditPreferences.getIsAccredit("isAccredit").booleanValue();
        }

        @JavascriptInterface
        public void openCamera(String zpbh) {
            if (MainActivity.this.isSDExist()) {
                MainActivity.this.photoNumber = zpbh;
                MainActivity.this.photo_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                MainActivity.this.photoPath = MainActivity.this.setPhoto(Environment.getExternalStorageDirectory() + "/outcheck/", zpbh + ".jpg", MainActivity.this.PHOTO_REQUEST_CODE);
            }
        }

        @JavascriptInterface
        public String getname() {
            return "{\"name\":\"" + MainActivity.this.userPreferences.getUserAccount("name") + "\"}";
        }
    }
@Override
    protected int getContentViewResId() {
        return R.layout.layout_drawer;
    }
@Override
    protected void initContent() {
        this.container = (FrameLayout) findViewById(R.id.container);
        this.titleBar = (Toolbar) findViewById(R.id.titleBar);
        this.drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.webviewlay = (LinearLayout) View.inflate(this, R.layout.webview_main, null);
        this.container.addView(this.webviewlay);
        this.webView = (WebView) this.webviewlay.findViewById(R.id.webview);
        this.errorlay = (LinearLayout) View.inflate(this, R.layout.webview_error, null);
        this.errorlay.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.webView.reload();
            }
        });
        this.accreditPreferences = PreferenceManager.getInstance().getAccreditPreferences();
        this.userPreferences = PreferenceManager.getInstance().getUserPreferences();
    }

    private void setupDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, this.drawer, this.titleBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        this.drawer.setDrawerListener(toggle);
        toggle.syncState();
        ((NavigationView) findViewById(R.id.nav_view)).setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.project) {
                    MainActivity.this.startActivity(new Intent(MainActivity.this, ProjectActivity.class));
                }
                ((DrawerLayout) MainActivity.this.findViewById(R.id.drawer_layout)).closeDrawer((int) GravityCompat.START);
                return true;
            }
        });
    }

    public void onBackPressed() {
        if (this.drawer.isDrawerOpen((int) GravityCompat.START)) {
            this.drawer.closeDrawer((int) GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    protected void initData() {
        setupDrawer();
        getPermission();
        this.ip = this.userPreferences.getIP(Constant.IP);
        this.port = this.userPreferences.getPort(Constant.port);
        this.stationNumber = this.userPreferences.getStationNumber(Constant.stationNumber);
        this.jkxlh = this.userPreferences.getAPI(Constant.jkxlh);
        this.account = this.userPreferences.getUserAccount("useraccount");
        this.password = this.userPreferences.getUserPwd("password");
        this.url = "http://" + this.ip + ":" + this.port + "/vepts/pda/openappmain/" + this.stationNumber + "/" + this.jkxlh + "/" + this.account + "/" + this.password;
        Log.i(TAG, "initData: url:" + this.url);
        setupWebView();
    }

    private void setupWebView() {
        WebSettings settings = this.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setDisplayZoomControls(false);
        settings.setAllowFileAccess(true);
        settings.setDomStorageEnabled(true);
        settings.setSupportMultipleWindows(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        this.webView.addJavascriptInterface(new JavaScriptinterface(this), "android");
        this.webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (VERSION.SDK_INT >= 26) {
                    return false;
                }
                view.loadUrl(url);
                return true;
            }

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                MainActivity.this.container.removeAllViews();
                MainActivity.this.container.addView(MainActivity.this.webviewlay);
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                MainActivity.this.container.removeAllViews();
                MainActivity.this.container.addView(MainActivity.this.errorlay);
            }

            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                if (request.isForMainFrame()) {
                    MainActivity.this.container.removeAllViews();
                    MainActivity.this.container.addView(MainActivity.this.errorlay);
                }
            }
        });
        this.webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int newProgress) {
                MainActivity.this.progressBar.setProgress(newProgress);
                if (newProgress == 80) {
                    MainActivity.this.progressBar.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        this.webView.loadUrl(this.url);
    }

    public void onClick(View view) {
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.webView != null) {
            this.webView.destroy();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4 || !this.webView.canGoBack()) {
            return super.onKeyDown(keyCode, event);
        }
        this.webView.goBack();
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == this.PHOTO_REQUEST_CODE) {
            try {
                this.dialog = new ProgressDialog(this);
                this.dialog.setMessage("正在处理图片，请稍等...");
                this.dialog.setIndeterminate(false);
                this.dialog.setCancelable(false);
                if (!isFinishing()) {
                    this.dialog.show();
                }
                Luban.with(this).load(new File(this.photoPath)).ignoreBy(400).setCompressListener(new OnCompressListener() {
                    public void onStart() {
                    }

                    public void onSuccess(File file) {
                        try {
                            MainActivity.this.imgbese = MainActivity.this.encodeBase64File(file);
                            MainActivity.this.imgbese = MainActivity.this.imgbese.replace(" ", "+");
                            MainActivity.this.imgbese = MainActivity.this.imgbese.replace("\n", "");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        MainActivity.this.handler.sendEmptyMessage(1000);
                    }

                    public void onError(Throwable e) {
                        MainActivity.this.dialog.cancel();
                        Toast.makeText(MainActivity.this, "压缩失败，请重新拍照！", Toast.LENGTH_SHORT).show();
                    }
                }).launch();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void getPermission() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.CALL_PHONE") != 0) {
            PermissionGen.with((Activity) this).addRequestCode(this.MY_PERMISSION_CODE).permissions("android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE").request();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode != this.MY_PERMISSION_CODE) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        } else if (grantResults[0] != 0) {
            Toast.makeText(this, "请在应用管理中打开“相机”访问权限！",  Toast.LENGTH_SHORT).show();
            finish();
        } else if (ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") == 0) {
        }
    }

    private String setPhoto(String path, String fileName, int requestCode) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        String photoPath = path + fileName;
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", Uri.fromFile(new File(path + fileName)));
        startActivityForResult(intent, requestCode);
        return photoPath;
    }

    public String encodeBase64File(File file) throws Exception {
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[((int) file.length())];
        inputFile.read(buffer);
        inputFile.close();
        return Base64.encodeToString(buffer, 0);
    }

    private boolean isSDExist() {
        return Environment.getExternalStorageState().equals("mounted");
    }
}
