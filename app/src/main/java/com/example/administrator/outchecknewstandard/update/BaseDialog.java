package com.example.administrator.outchecknewstandard.update;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.example.administrator.outchecknewstandard.update.BaseAnimatorSet.AnimatorListener;

public abstract class BaseDialog<T extends BaseDialog<T>> extends Dialog {
    protected String TAG;
    protected boolean cancel;
    protected Context context;
    private BaseAnimatorSet dismissAnim;
    protected DisplayMetrics dm;
    protected float heightScale;
    private boolean isDismissAnim;
    private boolean isShowAnim;
    protected LinearLayout ll_control_height;
    protected LinearLayout ll_top;
    protected float maxHeight;
    private BaseAnimatorSet showAnim;
    protected float widthScale = 1.0f;

    public abstract View onCreateView();

    public abstract void setUiBeforShow();

    public BaseDialog(Context context) {
        super(context);
        setDialogTheme();
        this.context = context;
        this.TAG = getClass().getSimpleName();
        Log.d(this.TAG, "constructor");
    }

    private void setDialogTheme() {
        requestWindowFeature(1);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().addFlags(2);
    }

    protected void onCreate(Bundle savedInstanceState) {
        Log.d(this.TAG, "onCreate");
        this.dm = this.context.getResources().getDisplayMetrics();
        this.ll_top = new LinearLayout(this.context);
        this.ll_top.setGravity(17);
        this.ll_control_height = new LinearLayout(this.context);
        this.ll_control_height.setOrientation(LinearLayout.VERTICAL);
        this.ll_control_height.addView(onCreateView());
        this.ll_top.addView(this.ll_control_height);
        this.maxHeight = (float) (this.dm.heightPixels - StatusBarUtils.getHeight(this.context));
        setContentView(this.ll_top, new LayoutParams(this.dm.widthPixels, (int) this.maxHeight));
        setCanceledOnTouchOutside(true);
        this.ll_top.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (BaseDialog.this.cancel) {
                    BaseDialog.this.dismiss();
                }
            }
        });
    }

    public void onAttachedToWindow() {
        int width;
        int height;
        super.onAttachedToWindow();
        Log.d(this.TAG, "onAttachedToWindow");
        setUiBeforShow();
        if (this.widthScale == 0.0f) {
            width = -2;
        } else {
            width = (int) (((float) this.dm.widthPixels) * this.widthScale);
        }
        if (this.heightScale == 0.0f) {
            height = -2;
        } else if (this.heightScale == 1.0f) {
            height = -1;
        } else {
            height = (int) (this.maxHeight * this.heightScale);
        }
        this.ll_control_height.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        if (this.showAnim != null) {
            this.showAnim.listener(new AnimatorListener() {
                public void onAnimationStart(Animator animator) {
                    BaseDialog.this.isShowAnim = true;
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    BaseDialog.this.isShowAnim = false;
                }

                public void onAnimationCancel(Animator animator) {
                    BaseDialog.this.isShowAnim = false;
                }
            }).playOn(this.ll_control_height);
        } else {
            BaseAnimatorSet.reset(this.ll_control_height);
        }
    }

    public void setCanceledOnTouchOutside(boolean cancel) {
        this.cancel = cancel;
        super.setCanceledOnTouchOutside(cancel);
    }

    public void show() {
        super.show();
        Log.d(this.TAG, "show");
    }

    protected void onStart() {
        super.onStart();
        Log.d(this.TAG, "onStart");
    }

    protected void onStop() {
        super.onStop();
        Log.d(this.TAG, "onStop");
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(this.TAG, "onDetachedFromWindow");
    }

    public void dismiss() {
        Log.d(this.TAG, "dismiss");
        if (this.dismissAnim != null) {
            this.dismissAnim.listener(new AnimatorListener() {
                public void onAnimationStart(Animator animator) {
                    BaseDialog.this.isDismissAnim = true;
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    BaseDialog.this.isDismissAnim = false;
                    BaseDialog.this.superDismiss();
                }

                public void onAnimationCancel(Animator animator) {
                    BaseDialog.this.isDismissAnim = false;
                    BaseDialog.this.superDismiss();
                }
            }).playOn(this.ll_control_height);
        } else {
            superDismiss();
        }
    }

    public void superDismiss() {
        super.dismiss();
    }

    public void show(int animStyle) {
        getWindow().setWindowAnimations(animStyle);
        show();
    }

    public T dimEnabled(boolean isDimEnabled) {
        if (isDimEnabled) {
            getWindow().addFlags(2);
        } else {
            getWindow().clearFlags(2);
        }
        return (T) this;
    }

    public T widthScale(float widthScale) {
        this.widthScale = widthScale;
        return (T)this;
    }

    public T heightScale(float heightScale) {
        this.heightScale = heightScale;
        return (T)this;
    }

    public T showAnim(BaseAnimatorSet showAnim) {
        this.showAnim = showAnim;
        return (T)this;
    }

    public T dismissAnim(BaseAnimatorSet dismissAnim) {
        this.dismissAnim = dismissAnim;
        return (T)this;
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (this.isDismissAnim || this.isShowAnim) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    public void onBackPressed() {
        if (!this.isDismissAnim && !this.isShowAnim) {
            super.onBackPressed();
        }
    }

    protected int dp2px(float dp) {
        return (int) ((dp * this.context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
