package com.example.administrator.outchecknewstandard.accredit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.administrator.outchecknewstandard.MainActivity;
import com.example.administrator.outchecknewstandard.R;
import com.example.administrator.outchecknewstandard.base.NewlyBaseActivity;
import com.example.administrator.outchecknewstandard.sp.base.PreferenceManager;
import com.example.administrator.outchecknewstandard.sp.i.IAccreditPreferences;
import com.example.administrator.outchecknewstandard.utils.CheckMachineCode;
import com.example.administrator.outchecknewstandard.utils.MobileConfigureUtils;
import com.znq.zbarcode.CaptureActivity;
import com.znq.zbarcode.ZbarScanLib;

import java.text.SimpleDateFormat;
import java.util.Date;

import kr.co.namee.permissiongen.PermissionGen;

public class ProjectActivity extends NewlyBaseActivity {
    public static final int REQUEST_CODE = 222;
    private static final String TAG = "ProjectActivity";
    int MY_PERMISSION_CODE = 100;
    private IAccreditPreferences accreditPreferences;
    private TextView androidVersionTx;
    private Button cancelBtn;
    private TextView imeiTx;
    private TextView projectVersionTx;
    private Button saveBtn;
    private Button scanBtn;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    private EditText sqmEd;

    protected int getContentViewResId() {
        return R.layout.activity_project;
    }

    protected void initContent() {
        this.sqmEd = (EditText) findViewById(R.id.sqmEd);
        this.scanBtn = (Button) findViewById(R.id.scan);
        this.cancelBtn = (Button) findViewById(R.id.cancel_button);
        this.saveBtn = (Button) findViewById(R.id.save_button);
        this.imeiTx = (TextView) findViewById(R.id.imeiTx);
        this.androidVersionTx = (TextView) findViewById(R.id.androidVersionTx);
        this.projectVersionTx = (TextView) findViewById(R.id.projectVersionTx);
        this.cancelBtn.setOnClickListener(this);
        this.saveBtn.setOnClickListener(this);
        this.scanBtn.setOnClickListener(this);
        this.accreditPreferences = PreferenceManager.getInstance().getAccreditPreferences();
    }

    protected void initData() {
        String accreditNumber = this.accreditPreferences.getAccreditNumber("accreditNumber");
        if (accreditNumber != null) {
            this.sqmEd.setText(accreditNumber);
        }
        this.imeiTx.setText("识别码：" + MobileConfigureUtils.getPdaSBM(this));
        this.androidVersionTx.setText("系统版本：" + MobileConfigureUtils.getAndroidVersion());
        try {
            this.projectVersionTx.setText("项目版本：" + MobileConfigureUtils.getVersionName(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
        getPermission();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.scan:
                startActivityForResult(new Intent(this, CaptureActivity.class), REQUEST_CODE);

                ZbarScanLib.openScan(ProjectActivity.this, new Intent());

                return;
            case R.id.cancel_button:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                return;
            case R.id.save_button:
                if (this.sqmEd.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "未获取到授权码！", Toast.LENGTH_SHORT).show();
                    this.accreditPreferences.saveAccreditNumber("accreditNumber", "");
                    this.accreditPreferences.saveIsAccredit("isAccredit", Boolean.valueOf(false));
                    toNext();
                    return;
                }
                String machineCode = CheckMachineCode.checkLicence(this.sqmEd.getText().toString().trim());
                this.accreditPreferences.saveAccreditNumber("accreditNumber", this.sqmEd.getText().toString().trim());
                String[] machines = machineCode.split("\\*");
                Log.i(TAG, "onClick: ========================machineCode:" + machineCode);
                String macheine = machines[0];
                String yxrq = machines[4];
                String lock = machines[7];
                Log.i(TAG, "onClick: ========================:" + macheine + "====" + yxrq + "===" + lock);
                String dt = this.sdf.format(new Date());
                if (!macheine.equals(MobileConfigureUtils.getPdaSBM(this))) {
                    this.accreditPreferences.saveIsAccredit("isAccredit", Boolean.valueOf(false));
                    this.accreditPreferences.saveAccreditNumber("accreditNumber", "");
                    Toast.makeText(this, "授权码错误！", Toast.LENGTH_SHORT).show();
                    return;
                } else if (lock.equals("1")) {
                    if (Integer.valueOf(yxrq.replace("-", "").trim()).intValue() > Integer.valueOf(dt).intValue()) {
                        this.accreditPreferences.saveIsAccredit("isAccredit", Boolean.valueOf(true));
                        this.accreditPreferences.saveLock("lock", lock);
                        this.accreditPreferences.saveYxrq("yxrq", yxrq);
                        Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();
                        toNext();
                    } else {
                        this.accreditPreferences.saveIsAccredit("isAccredit", Boolean.valueOf(false));
                        this.accreditPreferences.saveAccreditNumber("accreditNumber", "");
                        Toast.makeText(this, "该授权码已超期，请重新申请！", Toast.LENGTH_SHORT).show();
                    }
                    Log.i(TAG, "onClick: ========================machineCode:" + this.sqmEd.getText().toString().trim());
                    return;
                } else {
                    this.accreditPreferences.saveIsAccredit("isAccredit", Boolean.valueOf(true));
                    Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();
                    toNext();
                    return;
                }
            default:
                return;
        }
    }

    private void toNext() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
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
            Toast.makeText(this, "请在应用管理中打开“相机”访问权限！", Toast.LENGTH_SHORT).show();
            finish();
        } else if (ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") == 0) {
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CaptureActivity.QR_CODE && resultCode == RESULT_OK && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {

                String result = bundle.getString(CaptureActivity.EXTRA_STRING);
                Log.i(TAG, "onActivityResult: ======================:" + result);
                this.sqmEd.setText(result);
                this.accreditPreferences.saveAccreditNumber("accreditNumber", result);
            } else {
                Toast.makeText(this, "解析二维码失败", Toast.LENGTH_LONG).show();
            }
        }
    }
}
