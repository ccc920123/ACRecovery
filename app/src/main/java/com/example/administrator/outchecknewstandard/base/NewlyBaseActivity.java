package com.example.administrator.outchecknewstandard.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.administrator.outchecknewstandard.R;

public abstract class NewlyBaseActivity extends AppCompatActivity implements OnClickListener, Callback {
    private Handler handler;

    protected abstract int getContentViewResId();

    protected abstract void initContent();

    protected abstract void initData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        if (handleIntent()) {
            initView();
            initData();
            return;
        }
        finish();
    }

    public Context getActivity() {
        return this;
    }
@Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
//        setupToolbar();
    }

    private void initView() {
        int contentViewId = getContentViewResId();
        if (contentViewId != 0) {
            setContentView(contentViewId);
//            setupToolbar();
            initContent();
            return;
        }
        throw new RuntimeException("content view not found");
    }

    public void setupToolbar() {

//        getActionBar().hide();
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        if (toolbar != null) {
//            setSupportActionBar(toolbar);
//            try {
//                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            } catch (NullPointerException nullException) {
//                nullException.printStackTrace();
//                Toast.makeText(this, "获取标题栏失败...", Toast.LENGTH_SHORT).show();
//            }
//        }
    }

    protected boolean handleIntent() {
        return true;
    }

    protected OnClickListener getOnClickListener() {
        return this;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.activity_scroll_to_left, R.anim.activity_fadeout);
    }
    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.activity_scroll_to_left, R.anim.activity_fadeout);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_fadein, R.anim.activity_scroll_to_right);
    }

    public Handler getHandler() {
        this.handler = new Handler(this);
        return this.handler;
    }

    public boolean handleMessage(Message msg) {
        return false;
    }
}
