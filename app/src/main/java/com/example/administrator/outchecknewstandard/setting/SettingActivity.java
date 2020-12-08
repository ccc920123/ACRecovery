package com.example.administrator.outchecknewstandard.setting;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.administrator.outchecknewstandard.R;
import com.example.administrator.outchecknewstandard.base.NewlyBaseActivity;
import com.example.administrator.outchecknewstandard.constant.Constant;
import com.example.administrator.outchecknewstandard.login.LoginActivity;
import com.example.administrator.outchecknewstandard.sp.base.PreferenceManager;
import com.example.administrator.outchecknewstandard.sp.i.IUserPreferences;

public class SettingActivity extends NewlyBaseActivity {
    private static final String TAG = "SettingActivity";
    private Button cancelBtn;
    private String ip;
    private EditText ipEd;
    private EditText pdaSBMEd;
    private EditText pdaSQMEd;
    private String port;
    private EditText portEd;
    private Button saveBtn;
    private IUserPreferences userPreferences;

    protected int getContentViewResId() {
        return R.layout.activity_setting;
    }

    protected void initContent() {
        this.ipEd = (EditText) findViewById(R.id.pdaServIPEditText);
        this.portEd = (EditText) findViewById(R.id.pdaServPortEditText);
        this.cancelBtn = (Button) findViewById(R.id.cancel_button);
        this.saveBtn = (Button) findViewById(R.id.save_button);
        this.cancelBtn.setOnClickListener(this);
        this.saveBtn.setOnClickListener(this);
        this.userPreferences = PreferenceManager.getInstance().getUserPreferences();
    }

    protected void initData() {
        this.ip = this.userPreferences.getIP(Constant.IP);
        this.port = this.userPreferences.getPort(Constant.port);
        Log.i(TAG, "initData: " + this.port);
        Log.i(TAG, "initData: " + this.ip);
        this.ipEd.setText(this.ip);
        this.portEd.setText(this.port);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_button:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                return;
            case R.id.save_button:
                toNext();
                return;
            default:
                return;
        }
    }

    private boolean validate(String ip, String port) {
        if (ip.equals("") || port.equals("")) {
            Toast.makeText(this, R.string.msg_content_null, 0).show();
            return false;
        } else if (ip.contains(",") || ip.contains("，") || ip.contains("。")) {
            Toast.makeText(this, "请输入正确的IP格式！", 0).show();
            return false;
        } else if (!ip.replace(".", "").matches("\\d+")) {
            Toast.makeText(this, "IP存在非法字符！", 0).show();
            return false;
        } else if (port.matches("\\d+")) {
            return true;
        } else {
            Toast.makeText(this, "端口号存在非法字符！", 0).show();
            return false;
        }
    }

    private void toNext() {
        String pda_ip = this.ipEd.getText().toString().replaceAll("\\s*", "");
        String pda_port = this.portEd.getText().toString().replaceAll("\\s*", "");
        if (validate(pda_ip, pda_port)) {
            this.userPreferences.saveIP(Constant.IP, pda_ip);
            this.userPreferences.savePort(Constant.port, pda_port);
            if (this.userPreferences.getIP(Constant.IP) == null || this.userPreferences.getPort(Constant.port) == null) {
                Toast.makeText(this, R.string.msg_content_false, 0).show();
                return;
            }
            Toast.makeText(this, R.string.msg_save_success, 0).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }
}
