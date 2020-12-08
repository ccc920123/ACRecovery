package com.example.administrator.outchecknewstandard.update;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.administrator.outchecknewstandard.R;
import java.text.NumberFormat;

public class CommonProgressDialog extends AlertDialog {
    private String TAG = "CommonProgressDialog";
    private boolean mHasStarted;
    private int mMax;
    private CharSequence mMessage;
    private ProgressBar mProgress;
    private TextView mProgressMessage;
    private TextView mProgressNumber;
    private String mProgressNumberFormat;
    private TextView mProgressPercent;
    private NumberFormat mProgressPercentFormat;
    private int mProgressVal;
    private Handler mViewUpdateHandler;

    public CommonProgressDialog(Context context) {
        super(context);
        initFormats();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_progress_dialog);
        this.mProgress = (ProgressBar) findViewById(R.id.progress);
        this.mProgressNumber = (TextView) findViewById(R.id.progress_number);
        this.mProgressPercent = (TextView) findViewById(R.id.progress_percent);
        this.mProgressMessage = (TextView) findViewById(R.id.progress_message);
        this.mViewUpdateHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int progress = CommonProgressDialog.this.mProgress.getProgress();
                int max = CommonProgressDialog.this.mProgress.getMax();
                double dProgress = ((double) progress) / 1048576.0d;
                double dMax = ((double) max) / 1048576.0d;
                if (CommonProgressDialog.this.mProgressNumberFormat != null) {
                    String format = CommonProgressDialog.this.mProgressNumberFormat;
                    CommonProgressDialog.this.mProgressNumber.setText(String.format(format, new Object[]{Double.valueOf(dProgress), Double.valueOf(dMax)}));
                } else {
                    CommonProgressDialog.this.mProgressNumber.setText("");
                }
                if (CommonProgressDialog.this.mProgressPercentFormat != null) {
                    SpannableString tmp = new SpannableString(CommonProgressDialog.this.mProgressPercentFormat.format(((double) progress) / ((double) max)));
                    tmp.setSpan(new StyleSpan(1), 0, tmp.length(), 33);
                    CommonProgressDialog.this.mProgressPercent.setText(tmp);
                    return;
                }
                CommonProgressDialog.this.mProgressPercent.setText("");
            }
        };
        onProgressChanged();
        if (this.mMessage != null) {
            setMessage(this.mMessage);
        }
        if (this.mMax > 0) {
            setMax(this.mMax);
        }
        if (this.mProgressVal > 0) {
            setProgress(this.mProgressVal);
        }
    }

    private void initFormats() {
        this.mProgressNumberFormat = "%1.2fM/%2.2fM";
        this.mProgressPercentFormat = NumberFormat.getPercentInstance();
        this.mProgressPercentFormat.setMaximumFractionDigits(0);
    }

    private void onProgressChanged() {
        this.mViewUpdateHandler.sendEmptyMessage(0);
    }

    public void setProgressStyle(int style) {
    }

    public int getMax() {
        if (this.mProgress != null) {
            return this.mProgress.getMax();
        }
        return this.mMax;
    }

    public void setMax(int max) {
        if (this.mProgress != null) {
            this.mProgress.setMax(max);
            onProgressChanged();
            return;
        }
        this.mMax = max;
    }

    public void setIndeterminate(boolean indeterminate) {
        if (this.mProgress != null) {
            this.mProgress.setIndeterminate(indeterminate);
        }
    }

    public void setProgress(int value) {
        if (this.mHasStarted) {
            this.mProgress.setProgress(value);
            onProgressChanged();
            return;
        }
        this.mProgressVal = value;
    }

    public void setMessage(CharSequence message) {
        if (this.mProgressMessage != null) {
            this.mProgressMessage.setText(message);
        } else {
            this.mMessage = message;
        }
    }

    protected void onStart() {
        super.onStart();
        this.mHasStarted = true;
    }

    protected void onStop() {
        super.onStop();
        this.mHasStarted = false;
    }
}
