package com.example.administrator.outchecknewstandard;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.administrator.outchecknewstandard.base.NewlyBaseActivity;
import java.io.File;
import java.io.FileInputStream;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class PhotoActivity extends NewlyBaseActivity {
    int MY_PERMISSION_CODE = 100;
    private int PHOTO_REQUEST_CODE = 1000;
    private Button button;
    private ProgressDialog dialog;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(PhotoActivity.this, PhotoActivity.this.imgbese,  Toast.LENGTH_SHORT).show();
        }
    };
    private String imgbese = "";
    private String photoPath;
@Override
    protected int getContentViewResId() {
        return R.layout.activity_photo;
    }
@Override
    protected void initContent() {
        this.button = findViewById(R.id.btn);
        this.button.setOnClickListener(getOnClickListener());
    }
@Override
    protected void initData() {
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                if (isSDExist()) {
                    this.photoPath = setPhoto(Environment.getExternalStorageDirectory() + "/fdlphoto/", "fdl.jpg", this.PHOTO_REQUEST_CODE);
                    return;
                }
                return;
            default:
                return;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == this.PHOTO_REQUEST_CODE) {
            try {
                this.dialog = new ProgressDialog(this);
                this.dialog.setMessage("正在处理图片，请稍等...");
                this.dialog.setIndeterminate(false);
                this.dialog.setCancelable(false);
                this.dialog.show();
                Luban.with(this).load(new File(this.photoPath)).ignoreBy(400).setCompressListener(new OnCompressListener() {
                    public void onStart() {
                    }

                    public void onSuccess(File file) {
                        try {
                            PhotoActivity.this.imgbese = PhotoActivity.this.encodeBase64File(file);
                            PhotoActivity.this.imgbese = PhotoActivity.this.imgbese.replace(" ", "+");
                            PhotoActivity.this.imgbese = PhotoActivity.this.imgbese.replace("\n", "");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        PhotoActivity.this.dialog.cancel();
                        PhotoActivity.this.handler.sendEmptyMessage(1000);
                    }

                    public void onError(Throwable e) {
                        PhotoActivity.this.dialog.cancel();
                        Toast.makeText(PhotoActivity.this, "压缩失败，请重新拍照！", Toast.LENGTH_SHORT).show();
                    }
                }).launch();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
