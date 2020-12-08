package com.example.administrator.outchecknewstandard.login;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.administrator.outchecknewstandard.MainActivity;
import com.example.administrator.outchecknewstandard.R;
import com.example.administrator.outchecknewstandard.callback.WebServiceCallBack;
import com.example.administrator.outchecknewstandard.constant.Constant;
import com.example.administrator.outchecknewstandard.regist.RegisterActivity;
import com.example.administrator.outchecknewstandard.setting.SettingActivity;
import com.example.administrator.outchecknewstandard.sp.base.PreferenceManager;
import com.example.administrator.outchecknewstandard.sp.i.IAccreditPreferences;
import com.example.administrator.outchecknewstandard.sp.i.IUserPreferences;
import com.example.administrator.outchecknewstandard.utils.WebserviceInit;
import com.example.administrator.outchecknewstandard.utils.XMLTools;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Map;
import kr.co.namee.permissiongen.PermissionGen;

public class LoginActivity extends Activity {
    private static final String TAG = "LoginActivity";
    private int MY_PERMISSION_CODE = 100;
    private IAccreditPreferences accreditPreferences;
    private FrameLayout container;
    private Contact cs;
    private ProgressDialog dialog;
    private LinearLayout errorlay;
    private String ip;
    private JavaScriptObject js;
    private Button loginBtn;
    private String password;
    private EditText passwordEd;
    private String port;
    private ProgressBar progressBar;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    private String url;
    private String userName;
    private EditText userNameEd;
    private IUserPreferences userPreferences;
    private WebView view;

    public class Contact {
        public void showcontacts() {
            LoginActivity.this.view.loadUrl("javascript:show('" + "[{\"name\":\"zxx\", \"amount\":\"9999999\", \"phone\":\"18600012345\"}]" + "')");
        }
    }

    public class JavaScriptObject {
        Context mContxt;

        public JavaScriptObject(Context mContxt) {
            this.mContxt = mContxt;
        }

        @JavascriptInterface
        public void save(String name, String psd) {
            LoginActivity.this.password = psd;
            LoginActivity.this.userName = name;
            LoginActivity.this.dialog = new ProgressDialog(LoginActivity.this);
            LoginActivity.this.dialog.setProgressStyle(0);
            LoginActivity.this.dialog.setMessage("正在验证用户信息，请稍等...");
            LoginActivity.this.dialog.setIndeterminate(false);
            LoginActivity.this.dialog.setCancelable(false);
            LoginActivity.this.dialog.show();
            Log.i(LoginActivity.TAG, "queryAPIData: http://" + LoginActivity.this.ip + ":" + LoginActivity.this.port + "/vepts/services/VeptsOutAccess?wsdl");
            String stationNumber = LoginActivity.this.userPreferences.getStationNumber(Constant.stationNumber);
            String ip = LoginActivity.this.userPreferences.getIP(Constant.IP);
            String port = LoginActivity.this.userPreferences.getPort(Constant.port);
            String api = LoginActivity.this.userPreferences.getAPI(Constant.jkxlh);
            String sbm = LoginActivity.this.userPreferences.getPdaSbm("pdasbm");
            String bbh = LoginActivity.this.userPreferences.getVersionNumber("pdabbh");
            Log.i(LoginActivity.TAG, "queryAPIData: http://" + ip + ":" + port + "/vepts/services/VeptsOutAccess?wsdl");
            new WebserviceInit().setContext(LoginActivity.this).setNameSpace("http://webservice.ajxm.anche.com/").setMethodName("query").setEndPoint("http://" + ip + ":" + port + "/vepts/services/VeptsOutAccess?wsdl").setSoapAction("http://webservice.ajxm.anche.com/query").addParameter("arg0", "<?xml version=\"1.0\" encoding=\"utf-8\"?><root><head><organ>" + stationNumber + "</organ><jkxlh>" + api + "</jkxlh><pdaid>" + sbm + "</pdaid><pdaversion>" + bbh + "</pdaversion><jkid>LG001</jkid></head><body><vehispara><loginname>" + name + "</loginname><password>" + psd + "</password><organ>" + stationNumber + "</organ></vehispara></body></root>").setCallBackData(new WebServiceCallBack() {
                public void callBackData(Exception e, String data) {
                    LoginActivity.this.dialog.cancel();
                    if (e != null) {
                        if (e instanceof SocketTimeoutException) {
                            Toast.makeText(LoginActivity.this, R.string.socketTimeoutException, Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        } else if (e instanceof UnknownHostException) {
                            Toast.makeText(LoginActivity.this, R.string.unknownHostException, Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        } else {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, R.string.access_network_failure, Toast.LENGTH_SHORT).show();
                        }
                    } else if (!data.endsWith("101") && !data.equals("")) {
                        Map<String, String> xml = XMLTools.getXmlHead(data);
                        Map<String, String> xmlBody = XMLTools.getXmlBody(data);
                        Log.i(LoginActivity.TAG, "callBackData: " + data);
                        if (xml == null) {
                            return;
                        }
                        if (((String) xml.get("code")).equals("1")) {
                            LoginActivity.this.userPreferences.saveUserName("username", JavaScriptObject.this.getMapValue("username", xmlBody));
                            LoginActivity.this.userPreferences.saveJczmc("jczmc", JavaScriptObject.this.getMapValue("jczmc", xmlBody));
                            LoginActivity.this.userPreferences.saveUserAccount("useraccount", LoginActivity.this.userName);
                            LoginActivity.this.userPreferences.saveUserPwd("password", LoginActivity.this.password);
                            LoginActivity.this.userPreferences.saveJjygwh("jcgwh", JavaScriptObject.this.getMapValue("jcgwh", xmlBody));
                            LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            return;
                        }
                        Toast.makeText(LoginActivity.this, "用户名或密码输入有误，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        @JavascriptInterface
        public void error(String msg) {
            Toast.makeText(this.mContxt, msg, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public String get() {
            String name = LoginActivity.this.userPreferences.getUserAccount("useraccount");
            return "{\"name\":\"" + name + "\",\"password\":\"" + LoginActivity.this.userPreferences.getUserPwd("password") + "\"}";
        }

        @JavascriptInterface
        public void setting() {
            LoginActivity.this.startActivity(new Intent(LoginActivity.this, SettingActivity.class));
        }

        private String getMapValue(String key, Map<String, String> map) {
            String ret = "";
            if (!map.containsKey(key) || WebserviceInit.getURLDecoderString((String) map.get(key)).equals("")) {
                return ret;
            }
            return WebserviceInit.getURLDecoderString((String) map.get(key));
        }
    }
@Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "login: ===========================data:");
        SharedPreferences sharedPreferences = getSharedPreferences("first", 0);
        this.userPreferences = PreferenceManager.getInstance().getUserPreferences();
        if (sharedPreferences.getInt("count", 0) == 0) {
            startActivity(new Intent(this, RegisterActivity.class));
        }
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.activity_login);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.view = (WebView) findViewById(R.id.web);
        this.view.getSettings().setDefaultTextEncodingName("utf-8");
        this.view.getSettings().setJavaScriptEnabled(true);
        this.view.setVerticalScrollbarOverlay(true);
        this.ip = this.userPreferences.getIP(Constant.IP);
        this.port = this.userPreferences.getPort(Constant.port);
        this.errorlay = (LinearLayout) View.inflate(this, R.layout.webview_error, null);
        this.errorlay.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                LoginActivity.this.setupWebView();
            }
        });
        this.js = new JavaScriptObject(this);
        this.url = "http://" + this.ip + ":" + this.port + "/vepts/pda/login";
        getPermission();
        setupWebView();
    }

    private void setupWebView() {
        WebSettings settings = this.view.getSettings();
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
        this.view.addJavascriptInterface(this.js, "myObj");
        this.view.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (VERSION.SDK_INT >= 26) {
                    return false;
                }
                view.loadUrl(url);
                return true;
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                view.loadUrl("about:blank");
                new Builder(LoginActivity.this).setTitle("网络连接失败...").setMessage("请检查网络配置，和当前网络连接情况...").setNegativeButton("刷新", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        LoginActivity.this.setupWebView();
                    }
                }).setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        LoginActivity.this.startActivity(new Intent(LoginActivity.this, SettingActivity.class));
                    }
                }).create().show();
            }

            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                view.loadUrl("about:blank");
                new Builder(LoginActivity.this).setTitle("网络连接失败...").setMessage("请检查网络配置，和当前网络连接情况...").setNegativeButton("刷新", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        LoginActivity.this.setupWebView();
                    }
                }).setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        LoginActivity.this.startActivity(new Intent(LoginActivity.this, SettingActivity.class));
                    }
                }).create().show();
            }
        });
        this.view.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int newProgress) {
                LoginActivity.this.progressBar.setProgress(newProgress);
                if (newProgress == 80) {
                    LoginActivity.this.progressBar.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        this.view.loadUrl(this.url);
    }

    private String getPdaSBM() {
        return ((TelephonyManager) getBaseContext().getSystemService("phone")).getDeviceId() + ((WifiManager) getApplicationContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
    }

    private void getPermission() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.CALL_PHONE") != 0) {
            PermissionGen.with((Activity) this).addRequestCode(this.MY_PERMISSION_CODE).permissions("android.permission.CALL_PHONE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.CAMERA", "android.permission.CALL_PHONE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_PHONE_STATE").request();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode != this.MY_PERMISSION_CODE) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        } else if (grantResults[0] != 0) {
            Toast.makeText(this, "请在应用管理中打开“读写SD卡”访问权限！", Toast.LENGTH_SHORT).show();
            finish();
        } else if (ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == 0) {
        }
    }
}
