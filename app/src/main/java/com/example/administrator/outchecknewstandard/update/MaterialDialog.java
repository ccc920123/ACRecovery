package com.example.administrator.outchecknewstandard.update;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;

public class MaterialDialog extends BaseAlertDialog<MaterialDialog> {
    public MaterialDialog(Context context) {
        super(context);
        this.titleTextColor = Color.parseColor("#DE000000");
        this.titleTextSize_SP = 22.0f;
        this.contentTextColor = Color.parseColor("#8a000000");
        this.contentTextSize_SP = 16.0f;
        this.leftBtnTextColor = Color.parseColor("#383838");
        this.rightBtnTextColor = Color.parseColor("#eb2127");
        this.middleBtnTextColor = Color.parseColor("#00796B");
    }

    public View onCreateView() {
        this.tv_title.setGravity(16);
        this.tv_title.setPadding(dp2px(20.0f), dp2px(20.0f), dp2px(20.0f), dp2px(0.0f));
        this.tv_title.setLayoutParams(new LayoutParams(-1, -2));
        this.ll_container.addView(this.tv_title);
        this.tv_content.setPadding(dp2px(20.0f), dp2px(20.0f), dp2px(20.0f), dp2px(20.0f));
        this.tv_content.setLayoutParams(new LayoutParams(-1, -2));
        this.ll_container.addView(this.tv_content);
        this.ll_btns.setGravity(5);
        this.ll_btns.addView(this.tv_btn_left);
        this.ll_btns.addView(this.tv_btn_middle);
        this.ll_btns.addView(this.tv_btn_right);
        this.tv_btn_left.setPadding(dp2px(15.0f), dp2px(8.0f), dp2px(15.0f), dp2px(8.0f));
        this.tv_btn_right.setPadding(dp2px(15.0f), dp2px(8.0f), dp2px(15.0f), dp2px(8.0f));
        this.tv_btn_middle.setPadding(dp2px(15.0f), dp2px(8.0f), dp2px(15.0f), dp2px(8.0f));
        this.ll_btns.setPadding(dp2px(20.0f), dp2px(0.0f), dp2px(10.0f), dp2px(10.0f));
        this.ll_container.addView(this.ll_btns);
        return this.ll_container;
    }

    public void setUiBeforShow() {
        super.setUiBeforShow();
        float radius = (float) dp2px(this.cornerRadius_DP);
        this.ll_container.setBackgroundDrawable(CornerUtils.cornerDrawable(this.bgColor, radius));
        this.tv_btn_left.setBackgroundDrawable(CornerUtils.btnSelector(radius, this.bgColor, this.btnPressColor, -2));
        this.tv_btn_right.setBackgroundDrawable(CornerUtils.btnSelector(radius, this.bgColor, this.btnPressColor, -2));
        this.tv_btn_middle.setBackgroundDrawable(CornerUtils.btnSelector(radius, this.bgColor, this.btnPressColor, -2));
    }
}
