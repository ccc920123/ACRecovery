package com.example.administrator.outchecknewstandard.regist;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.administrator.outchecknewstandard.R;
import com.example.administrator.outchecknewstandard.base.NewlyBaseActivity;
import com.example.administrator.outchecknewstandard.callback.WebServiceCallBack;
import com.example.administrator.outchecknewstandard.constant.Constant;
import com.example.administrator.outchecknewstandard.login.LoginActivity;
import com.example.administrator.outchecknewstandard.sp.base.PreferenceManager;
import com.example.administrator.outchecknewstandard.sp.i.IUserPreferences;
import com.example.administrator.outchecknewstandard.utils.MapValueUtils;
import com.example.administrator.outchecknewstandard.utils.MobileConfigureUtils;
import com.example.administrator.outchecknewstandard.utils.WebserviceInit;
import com.example.administrator.outchecknewstandard.utils.XMLTools;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Map;

import kr.co.namee.permissiongen.PermissionGen;

public class RegisterActivity extends NewlyBaseActivity {
    private static final String TAG = "RegisterActivity";
    private String IMEI;
    private int MY_PERMISSION_CODE = 100;
    private String androidVersion;
    private ProgressDialog dialog;
    private String ip;
    private String modelBrand;
    private String port;
    private Button registerBtn;
    private String stationNumber;
    private EditText stationNumberEd;
    private IUserPreferences userPreferences;
    private String webAPI;
    private EditText webServiceIPEd;
    private EditText webServicePortEd;

    protected int getContentViewResId() {
        return R.layout.activity_register;
    }
    protected void initContent() {
        this.stationNumberEd = (EditText) findViewById(R.id.monitoringStationNumberEd);
        this.webServiceIPEd = (EditText) findViewById(R.id.webServiceIPEd);
        this.webServicePortEd = (EditText) findViewById(R.id.webServicePortEd);
        this.registerBtn = (Button) findViewById(R.id.register_button);
        this.registerBtn.setOnClickListener(this);
        this.userPreferences = PreferenceManager.getInstance().getUserPreferences();
    }
    protected void initData() {
        getPermission();
        this.IMEI = MobileConfigureUtils.getPdaSBM(this);
        this.androidVersion = MobileConfigureUtils.getAndroidVersion();
        this.modelBrand = MobileConfigureUtils.getMobileBrand() + "-" + MobileConfigureUtils.getPhoneModel();
        Log.i(TAG, "initData: ++++++++++++++++++++++++++++===IMEI:" + this.IMEI);
        Log.i(TAG, "initData: ++++++++++++++++++++++++++++===androidVersion:" + this.androidVersion);
        Log.i(TAG, "initData: ++++++++++++++++++++++++++++===modelBrand:" + this.modelBrand);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.register_button) {
            this.stationNumber = this.stationNumberEd.getText().toString().trim();
            this.ip = this.webServiceIPEd.getText().toString().trim();
            this.port = this.webServicePortEd.getText().toString().trim();
            if (this.stationNumber == null || this.stationNumber.length() <= 0 || this.ip == null || this.ip.length() <= 0 || this.port == null || this.port.length() <= 0) {
                Toast.makeText(this, R.string.register_msg_null, Toast.LENGTH_LONG).show();
            } else {
                queryAPIData();
            }
        }
    }

    private void queryAPIData() {
        this.dialog = new ProgressDialog(this);
        this.dialog.setMessage("正在获取接口序列号...");
        this.dialog.setProgressStyle(0);
        this.dialog.show();
        this.userPreferences = PreferenceManager.getInstance().getUserPreferences();
        System.out.println("http://" + this.ip + ":" + this.port + "/vepts/services/VeptsOutAccess?wsdl");
        new WebserviceInit().setContext(this).setNameSpace("http://webservice.ajxm.anche.com/").setMethodName("write").setEndPoint("http://" + this.ip + ":" + this.port + "/vepts/services/VeptsOutAccess?wsdl").setSoapAction("http://webservice.ajxm.anche.com/write").addParameter("arg0", "<?xml version=\"1.0\" encoding=\"utf-8\"?><root><head><organ>" + this.stationNumber + "</organ><jkid>ZC001</jkid></head><body><vehispara><cellphone_imei>" + this.IMEI + "</cellphone_imei><cellphone_system>android " + this.androidVersion + "</cellphone_system><cellphone_model>" + this.modelBrand + "</cellphone_model></vehispara></body></root>").setCallBackData(new WebServiceCallBack() {
            public void callBackData(Exception e, String data) {
                RegisterActivity.this.dialog.cancel();
                if (e != null) {
                    if (e instanceof SocketTimeoutException) {
                        Toast.makeText(RegisterActivity.this, R.string.socketTimeoutException, Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } else if (e instanceof UnknownHostException) {
                        Toast.makeText(RegisterActivity.this, R.string.unknownHostException,  Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } else {
                        e.printStackTrace();
                        Toast.makeText(RegisterActivity.this, R.string.access_network_failure,  Toast.LENGTH_SHORT).show();
                    }
                } else if (!data.endsWith("101") && !data.equals("")) {
                    Map<String, String> xmlHead = XMLTools.getXmlHead(data);
                    Map<String, String> xmlBody = XMLTools.getXmlBody(data);
                    Log.i(RegisterActivity.TAG, "callBackData: ===========================data:" + data);
                    if (xmlBody == null) {
                        return;
                    }
                    if (((String) xmlHead.get("code")).equals("1")) {
                        RegisterActivity.this.userPreferences.saveStationNumber(Constant.stationNumber, RegisterActivity.this.stationNumber);
                        RegisterActivity.this.userPreferences.saveIP(Constant.IP, RegisterActivity.this.ip);
                        RegisterActivity.this.userPreferences.savePort(Constant.port, RegisterActivity.this.port);
                        RegisterActivity.this.userPreferences.saveAPI(Constant.jkxlh, MapValueUtils.getMapValue(Constant.jkxlh, xmlBody));
                        RegisterActivity.this.getSharedPreferences("first", 0).edit().putInt("count", 1).commit();
                        RegisterActivity.this.startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        RegisterActivity.this.finish();
                        return;
                    }
                    Toast.makeText(RegisterActivity.this, R.string.register_import_error, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void getPermission() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.CALL_PHONE") != 0) {
            PermissionGen.with((Activity) this).addRequestCode(this.MY_PERMISSION_CODE).permissions("android.permission.CALL_PHONE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.CAMERA", "android.permission.CALL_PHONE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_PHONE_STATE", "android.permission.ACCESS_WIFI_STATE").request();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode != this.MY_PERMISSION_CODE) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        } else if (grantResults[0] != 0) {
            Toast.makeText(this, "请在应用管理中打开“识别码”访问权限！", 1).show();
            finish();
        } else if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_WIFI_STATE") == 0) {
        }
    }
}
