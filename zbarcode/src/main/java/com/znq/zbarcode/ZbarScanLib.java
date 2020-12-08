package com.znq.zbarcode;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

/**
 * 描述：
 * 公司：四川星盾科技股份有限公司
 * 编写人：陈渝金-pc:chenyujin
 * 时间： 2020/8/20
 * 修改人：
 * 修改时间：
 */


public class ZbarScanLib {

    /**
     * @param activity 界面
     * @param itt      Intent
     */

    public static void openScan(Activity activity, Intent itt) {

        try {
            itt.setClass(activity, CaptureActivity.class);
            activity.startActivityForResult(itt,CaptureActivity.QR_CODE);

        } catch (Exception e) {
            Log.e("WebLib", e.getMessage());
        }
    }


}
