package com.example.administrator.outchecknewstandard.update;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class BaseAlertDialog<T extends BaseAlertDialog<T>> extends BaseDialog {
    protected int bgColor = Color.parseColor("#ffffff");
    protected String btnLeftText = "取消";
    protected String btnMiddleText = "继续";
    protected int btnNum = 2;
    protected int btnPressColor = Color.parseColor("#E3E3E3");
    protected String btnRightText = "确定";
    protected String content;
    protected int contentGravity = 16;
    protected int contentTextColor;
    protected float contentTextSize_SP;
    protected float cornerRadius_DP = 3.0f;
    protected boolean isTitleShow = true;
    protected int leftBtnTextColor;
    protected float leftBtnTextSize_SP = 15.0f;
    protected LinearLayout ll_btns;
    protected LinearLayout ll_container;
    protected int middleBtnTextColor;
    protected float middleBtnTextSize_SP = 15.0f;
    protected OnBtnClickL onBtnLeftClickL;
    protected OnBtnClickL onBtnMiddleClickL;
    protected OnBtnClickL onBtnRightClickL;
    protected int rightBtnTextColor;
    protected float rightBtnTextSize_SP = 15.0f;
    protected String title;
    protected int titleTextColor;
    protected float titleTextSize_SP;
    protected TextView tv_btn_left;
    protected TextView tv_btn_middle;
    protected TextView tv_btn_right;
    protected TextView tv_content;
    protected TextView tv_title;

    public BaseAlertDialog(Context context) {
        super(context);
        widthScale(0.88f);
        this.ll_container = new LinearLayout(context);
        this.ll_container.setOrientation(LinearLayout.VERTICAL);
        this.tv_title = new TextView(context);
        this.tv_content = new TextView(context);
        this.ll_btns = new LinearLayout(context);
        this.ll_btns.setOrientation(LinearLayout.HORIZONTAL);
        this.tv_btn_left = new TextView(context);
        this.tv_btn_left.setGravity(17);
        this.tv_btn_middle = new TextView(context);
        this.tv_btn_middle.setGravity(17);
        this.tv_btn_right = new TextView(context);
        this.tv_btn_right.setGravity(17);
    }

    public void setUiBeforShow() {
        this.tv_title.setVisibility(this.isTitleShow ? View.VISIBLE : View.GONE);
        this.tv_title.setText(TextUtils.isEmpty(this.title) ? "温馨提示" : this.title);
        this.tv_title.setTextColor(this.titleTextColor);
        this.tv_title.setTextSize(2, this.titleTextSize_SP);
        this.tv_content.setGravity(this.contentGravity);
        this.tv_content.setText(this.content);
        this.tv_content.setTextColor(this.contentTextColor);
        this.tv_content.setTextSize(2, this.contentTextSize_SP);
        this.tv_content.setLineSpacing(0.0f, 1.3f);
        this.tv_btn_left.setText(this.btnLeftText);
        this.tv_btn_right.setText(this.btnRightText);
        this.tv_btn_middle.setText(this.btnMiddleText);
        this.tv_btn_left.setTextColor(this.leftBtnTextColor);
        this.tv_btn_right.setTextColor(this.rightBtnTextColor);
        this.tv_btn_middle.setTextColor(this.middleBtnTextColor);
        this.tv_btn_left.setTextSize(2, this.leftBtnTextSize_SP);
        this.tv_btn_right.setTextSize(2, this.rightBtnTextSize_SP);
        this.tv_btn_middle.setTextSize(2, this.middleBtnTextSize_SP);
        if (this.btnNum == 1) {
            this.tv_btn_left.setVisibility(View.GONE);
            this.tv_btn_right.setVisibility(View.GONE);
        } else if (this.btnNum == 2) {
            this.tv_btn_middle.setVisibility(View.GONE);
        }
        this.tv_btn_left.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (BaseAlertDialog.this.onBtnLeftClickL != null) {
                    BaseAlertDialog.this.onBtnLeftClickL.onBtnClick();
                } else {
                    BaseAlertDialog.this.dismiss();
                }
            }
        });
        this.tv_btn_right.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (BaseAlertDialog.this.onBtnRightClickL != null) {
                    BaseAlertDialog.this.onBtnRightClickL.onBtnClick();
                } else {
                    BaseAlertDialog.this.dismiss();
                }
            }
        });
        this.tv_btn_middle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (BaseAlertDialog.this.onBtnMiddleClickL != null) {
                    BaseAlertDialog.this.onBtnMiddleClickL.onBtnClick();
                } else {
                    BaseAlertDialog.this.dismiss();
                }
            }
        });
    }

    public T title(String title) {
        this.title = title;
        return (T)this;
    }

    public T titleTextColor(int titleTextColor) {
        this.titleTextColor = titleTextColor;
        return (T)this;
    }

    public T titleTextSize(float titleTextSize_SP) {
        this.titleTextSize_SP = titleTextSize_SP;
        return (T)this;
    }

    public T isTitleShow(boolean isTitleShow) {
        this.isTitleShow = isTitleShow;
        return (T)this;
    }

    public T content(String content) {
        this.content = content;
        return (T)this;
    }

    public T contentGravity(int contentGravity) {
        this.contentGravity = contentGravity;
        return (T)this;
    }

    public T contentTextColor(int contentTextColor) {
        this.contentTextColor = contentTextColor;
        return (T)this;
    }

    public T contentTextSize(float contentTextSize_SP) {
        this.contentTextSize_SP = contentTextSize_SP;
        return (T)this;
    }

    public T btnNum(int btnNum) {
        if (btnNum < 1 || btnNum > 3) {
            throw new IllegalStateException("btnNum is [1,3]!");
        }
        this.btnNum = btnNum;
        return (T)this;
    }

    public T btnText(String... btnTexts) {
        if (btnTexts.length < 1 || btnTexts.length > 3) {
            throw new IllegalStateException(" range of param btnTexts length is [1,3]!");
        }
        if (btnTexts.length == 1) {
            this.btnMiddleText = btnTexts[0];
        } else if (btnTexts.length == 2) {
            this.btnLeftText = btnTexts[0];
            this.btnRightText = btnTexts[1];
        } else if (btnTexts.length == 3) {
            this.btnLeftText = btnTexts[0];
            this.btnRightText = btnTexts[1];
            this.btnMiddleText = btnTexts[2];
        }
        return (T)this;
    }

    public T btnTextColor(int... btnTextColors) {
        if (btnTextColors.length < 1 || btnTextColors.length > 3) {
            throw new IllegalStateException(" range of param textColors length is [1,3]!");
        }
        if (btnTextColors.length == 1) {
            this.middleBtnTextColor = btnTextColors[0];
        } else if (btnTextColors.length == 2) {
            this.leftBtnTextColor = btnTextColors[0];
            this.rightBtnTextColor = btnTextColors[1];
        } else if (btnTextColors.length == 3) {
            this.leftBtnTextColor = btnTextColors[0];
            this.rightBtnTextColor = btnTextColors[1];
            this.middleBtnTextColor = btnTextColors[2];
        }
        return (T)this;
    }

    public T btnTextSize(float... btnTextSizes) {
        if (btnTextSizes.length < 1 || btnTextSizes.length > 3) {
            throw new IllegalStateException(" range of param btnTextSizes length is [1,3]!");
        }
        if (btnTextSizes.length == 1) {
            this.middleBtnTextSize_SP = btnTextSizes[0];
        } else if (btnTextSizes.length == 2) {
            this.leftBtnTextSize_SP = btnTextSizes[0];
            this.rightBtnTextSize_SP = btnTextSizes[1];
        } else if (btnTextSizes.length == 3) {
            this.leftBtnTextSize_SP = btnTextSizes[0];
            this.rightBtnTextSize_SP = btnTextSizes[1];
            this.middleBtnTextSize_SP = btnTextSizes[2];
        }
        return (T)this;
    }

    public T btnPressColor(int btnPressColor) {
        this.btnPressColor = btnPressColor;
        return (T)this;
    }

    public T cornerRadius(float cornerRadius_DP) {
        this.cornerRadius_DP = cornerRadius_DP;
        return (T)this;
    }

    public T bgColor(int bgColor) {
        this.bgColor = bgColor;
        return (T)this;
    }

    public void setOnBtnClickL(OnBtnClickL... onBtnClickLs) {
        if (onBtnClickLs.length < 1 || onBtnClickLs.length > 3) {
            throw new IllegalStateException(" range of param onBtnClickLs length is [1,3]!");
        } else if (onBtnClickLs.length == 1) {
            this.onBtnMiddleClickL = onBtnClickLs[0];
        } else if (onBtnClickLs.length == 2) {
            this.onBtnLeftClickL = onBtnClickLs[0];
            this.onBtnRightClickL = onBtnClickLs[1];
        } else if (onBtnClickLs.length == 3) {
            this.onBtnLeftClickL = onBtnClickLs[0];
            this.onBtnRightClickL = onBtnClickLs[1];
            this.onBtnMiddleClickL = onBtnClickLs[2];
        }
    }
}
