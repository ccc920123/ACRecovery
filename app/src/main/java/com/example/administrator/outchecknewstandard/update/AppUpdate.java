package com.example.administrator.outchecknewstandard.update;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;
import com.example.administrator.outchecknewstandard.R;
import com.example.administrator.outchecknewstandard.callback.WebServiceCallBack;
import com.example.administrator.outchecknewstandard.constant.Constant;
import com.example.administrator.outchecknewstandard.sp.base.PreferenceManager;
import com.example.administrator.outchecknewstandard.sp.i.IUserPreferences;
import com.example.administrator.outchecknewstandard.utils.MapValueUtils;
import com.example.administrator.outchecknewstandard.utils.WebserviceInit;
import com.example.administrator.outchecknewstandard.utils.XMLTools;
import java.io.File;
import java.util.Map;

public class AppUpdate {
    public static final String QUERY_END_STR = "</QueryCondition></root>";
    public static final String QUERY_START_STR = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><QueryCondition>";
    private static final int REQUEST_CODE_PERMISSION_SD = 101;
    private static final int REQUEST_CODE_SETTING = 300;
    private static final String TAG = "AppUpdate";
    public static final String WRITE_END_STR = "</vehispara></root>";
    public static final String WRITE_START_STR = "<?xml version=\"1.0\" encoding=\"GBK\"?><root><vehispara>";
    public static String apkName;
    public static String appContent;
    public static String appUrl;
    public static CommonProgressDialog pBar;
    public static String serverVersionCode;
    public static String serverVersionName;
    public static IUserPreferences userPreferences;

    static class DownloadTask extends AsyncTask<String, Integer, String> {
        private Context context;
        private WakeLock mWakeLock;

        public DownloadTask(Context context) {
            this.context = context;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected String doInBackground(String... r23) {
            /*
            r22 = this;
            r9 = 0;
            r10 = 0;
            r2 = 0;
            r6 = 0;
            r14 = new java.net.URL;	 Catch:{ Exception -> 0x0154 }
            r15 = 0;
            r15 = r23[r15];	 Catch:{ Exception -> 0x0154 }
            r14.<init>(r15);	 Catch:{ Exception -> 0x0154 }
            r15 = r14.openConnection();	 Catch:{ Exception -> 0x0154 }
            r0 = r15;
            r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x0154 }
            r2 = r0;
            r2.connect();	 Catch:{ Exception -> 0x0154 }
            r15 = r2.getResponseCode();	 Catch:{ Exception -> 0x0154 }
            r16 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
            r0 = r16;
            if (r15 == r0) goto L_0x0056;
        L_0x0021:
            r15 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0154 }
            r15.<init>();	 Catch:{ Exception -> 0x0154 }
            r16 = "Server returned HTTP ";
            r15 = r15.append(r16);	 Catch:{ Exception -> 0x0154 }
            r16 = r2.getResponseCode();	 Catch:{ Exception -> 0x0154 }
            r15 = r15.append(r16);	 Catch:{ Exception -> 0x0154 }
            r16 = " ";
            r15 = r15.append(r16);	 Catch:{ Exception -> 0x0154 }
            r16 = r2.getResponseMessage();	 Catch:{ Exception -> 0x0154 }
            r15 = r15.append(r16);	 Catch:{ Exception -> 0x0154 }
            r15 = r15.toString();	 Catch:{ Exception -> 0x0154 }
            if (r10 == 0) goto L_0x004b;
        L_0x0048:
            r10.close();	 Catch:{ IOException -> 0x015e }
        L_0x004b:
            if (r9 == 0) goto L_0x0050;
        L_0x004d:
            r9.close();	 Catch:{ IOException -> 0x015e }
        L_0x0050:
            if (r2 == 0) goto L_0x0055;
        L_0x0052:
            r2.disconnect();
        L_0x0055:
            return r15;
        L_0x0056:
            r8 = r2.getContentLength();	 Catch:{ Exception -> 0x0154 }
            r15 = android.os.Environment.getExternalStorageState();	 Catch:{ Exception -> 0x0154 }
            r16 = "mounted";
            r15 = r15.equals(r16);	 Catch:{ Exception -> 0x0154 }
            if (r15 == 0) goto L_0x00aa;
        L_0x0066:
            r7 = new java.io.File;	 Catch:{ Exception -> 0x0154 }
            r15 = android.os.Environment.getExternalStorageDirectory();	 Catch:{ Exception -> 0x0154 }
            r16 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0154 }
            r16.<init>();	 Catch:{ Exception -> 0x0154 }
            r17 = com.example.administrator.outchecknewstandard.update.AppUpdate.apkName;	 Catch:{ Exception -> 0x0154 }
            r16 = r16.append(r17);	 Catch:{ Exception -> 0x0154 }
            r17 = "V";
            r16 = r16.append(r17);	 Catch:{ Exception -> 0x0154 }
            r17 = com.example.administrator.outchecknewstandard.update.AppUpdate.serverVersionName;	 Catch:{ Exception -> 0x0154 }
            r16 = r16.append(r17);	 Catch:{ Exception -> 0x0154 }
            r17 = ".apk";
            r16 = r16.append(r17);	 Catch:{ Exception -> 0x0154 }
            r16 = r16.toString();	 Catch:{ Exception -> 0x0154 }
            r0 = r16;
            r7.<init>(r15, r0);	 Catch:{ Exception -> 0x0154 }
            r15 = r7.exists();	 Catch:{ Exception -> 0x0156, all -> 0x014c }
            if (r15 != 0) goto L_0x00a9;
        L_0x0098:
            r15 = r7.getParentFile();	 Catch:{ Exception -> 0x0156, all -> 0x014c }
            r15 = r15.exists();	 Catch:{ Exception -> 0x0156, all -> 0x014c }
            if (r15 != 0) goto L_0x00a9;
        L_0x00a2:
            r15 = r7.getParentFile();	 Catch:{ Exception -> 0x0156, all -> 0x014c }
            r15.mkdirs();	 Catch:{ Exception -> 0x0156, all -> 0x014c }
        L_0x00a9:
            r6 = r7;
        L_0x00aa:
            r9 = r2.getInputStream();	 Catch:{ Exception -> 0x0154 }
            r11 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x0154 }
            r11.<init>(r6);	 Catch:{ Exception -> 0x0154 }
            r15 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
            r4 = new byte[r15];	 Catch:{ Exception -> 0x0106, all -> 0x014f }
            r12 = 0;
        L_0x00b9:
            r3 = r9.read(r4);	 Catch:{ Exception -> 0x0106, all -> 0x014f }
            r15 = -1;
            if (r3 == r15) goto L_0x0126;
        L_0x00c0:
            r15 = r22.isCancelled();	 Catch:{ Exception -> 0x0106, all -> 0x014f }
            if (r15 == 0) goto L_0x00dc;
        L_0x00c6:
            r9.close();	 Catch:{ Exception -> 0x0106, all -> 0x014f }
            r15 = 0;
            if (r11 == 0) goto L_0x00cf;
        L_0x00cc:
            r11.close();	 Catch:{ IOException -> 0x015b }
        L_0x00cf:
            if (r9 == 0) goto L_0x00d4;
        L_0x00d1:
            r9.close();	 Catch:{ IOException -> 0x015b }
        L_0x00d4:
            if (r2 == 0) goto L_0x00d9;
        L_0x00d6:
            r2.disconnect();
        L_0x00d9:
            r10 = r11;
            goto L_0x0055;
        L_0x00dc:
            r0 = (long) r3;
            r16 = r0;
            r12 = r12 + r16;
            if (r8 <= 0) goto L_0x0101;
        L_0x00e3:
            r15 = 1;
            r15 = new java.lang.Integer[r15];	 Catch:{ Exception -> 0x0106, all -> 0x014f }
            r16 = 0;
            r18 = 100;
            r18 = r18 * r12;
            r0 = (long) r8;	 Catch:{ Exception -> 0x0106, all -> 0x014f }
            r20 = r0;
            r18 = r18 / r20;
            r0 = r18;
            r0 = (int) r0;	 Catch:{ Exception -> 0x0106, all -> 0x014f }
            r17 = r0;
            r17 = java.lang.Integer.valueOf(r17);	 Catch:{ Exception -> 0x0106, all -> 0x014f }
            r15[r16] = r17;	 Catch:{ Exception -> 0x0106, all -> 0x014f }
            r0 = r22;
            r0.publishProgress(r15);	 Catch:{ Exception -> 0x0106, all -> 0x014f }
        L_0x0101:
            r15 = 0;
            r11.write(r4, r15, r3);	 Catch:{ Exception -> 0x0106, all -> 0x014f }
            goto L_0x00b9;
        L_0x0106:
            r5 = move-exception;
            r10 = r11;
        L_0x0108:
            r15 = java.lang.System.out;	 Catch:{ all -> 0x0139 }
            r16 = r5.toString();	 Catch:{ all -> 0x0139 }
            r15.println(r16);	 Catch:{ all -> 0x0139 }
            r15 = r5.toString();	 Catch:{ all -> 0x0139 }
            if (r10 == 0) goto L_0x011a;
        L_0x0117:
            r10.close();	 Catch:{ IOException -> 0x0152 }
        L_0x011a:
            if (r9 == 0) goto L_0x011f;
        L_0x011c:
            r9.close();	 Catch:{ IOException -> 0x0152 }
        L_0x011f:
            if (r2 == 0) goto L_0x0055;
        L_0x0121:
            r2.disconnect();
            goto L_0x0055;
        L_0x0126:
            if (r11 == 0) goto L_0x012b;
        L_0x0128:
            r11.close();	 Catch:{ IOException -> 0x0159 }
        L_0x012b:
            if (r9 == 0) goto L_0x0130;
        L_0x012d:
            r9.close();	 Catch:{ IOException -> 0x0159 }
        L_0x0130:
            if (r2 == 0) goto L_0x0135;
        L_0x0132:
            r2.disconnect();
        L_0x0135:
            r15 = 0;
            r10 = r11;
            goto L_0x0055;
        L_0x0139:
            r15 = move-exception;
        L_0x013a:
            if (r10 == 0) goto L_0x013f;
        L_0x013c:
            r10.close();	 Catch:{ IOException -> 0x014a }
        L_0x013f:
            if (r9 == 0) goto L_0x0144;
        L_0x0141:
            r9.close();	 Catch:{ IOException -> 0x014a }
        L_0x0144:
            if (r2 == 0) goto L_0x0149;
        L_0x0146:
            r2.disconnect();
        L_0x0149:
            throw r15;
        L_0x014a:
            r16 = move-exception;
            goto L_0x0144;
        L_0x014c:
            r15 = move-exception;
            r6 = r7;
            goto L_0x013a;
        L_0x014f:
            r15 = move-exception;
            r10 = r11;
            goto L_0x013a;
        L_0x0152:
            r16 = move-exception;
            goto L_0x011f;
        L_0x0154:
            r5 = move-exception;
            goto L_0x0108;
        L_0x0156:
            r5 = move-exception;
            r6 = r7;
            goto L_0x0108;
        L_0x0159:
            r15 = move-exception;
            goto L_0x0130;
        L_0x015b:
            r16 = move-exception;
            goto L_0x00d4;
        L_0x015e:
            r16 = move-exception;
            goto L_0x0050;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.example.administrator.outchecknewstandard.update.AppUpdate.DownloadTask.doInBackground(java.lang.String[]):java.lang.String");
        }

        protected void onPreExecute() {
            super.onPreExecute();
            this.mWakeLock = ((PowerManager) this.context.getSystemService(Context.POWER_SERVICE)).newWakeLock(1, getClass().getName());
            this.mWakeLock.acquire();
            AppUpdate.pBar.show();
        }

        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            AppUpdate.pBar.setIndeterminate(false);
            AppUpdate.pBar.setMax(100);
            AppUpdate.pBar.setProgress(progress[0].intValue());
        }

        protected void onPostExecute(String result) {
            this.mWakeLock.release();
            AppUpdate.pBar.dismiss();
            update(this.context);
        }

        private void update(Context context) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory(), AppUpdate.apkName + "V" + AppUpdate.serverVersionName + ".apk")), "application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    public static int getVersionCode(Context context) {
        int i = 0;
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }

    public static void getServerVersionCode(final Context context) {
        userPreferences = PreferenceManager.getInstance().getUserPreferences();
        String ip = userPreferences.getIP(Constant.IP);
        String port = userPreferences.getPort(Constant.port);
        String stationNumber = userPreferences.getStationNumber(Constant.stationNumber);
        String api = userPreferences.getAPI(Constant.jkxlh);
        Log.i(TAG, "getServerVersionCode: ip:" + ip);
        Log.i(TAG, "getServerVersionCode: port:" + port);
        Log.i(TAG, "getServerVersionCode: stationNumber:" + stationNumber);
        Log.i(TAG, "getServerVersionCode: jkxlh:" + api);
        new WebserviceInit().setNameSpace("http://webservice.ajxm.anche.com/").setMethodName("query").setEndPoint("http://" + ip + ":" + port + "/vepts/services/VeptsOutAccess?wsdl").setSoapAction("http://webservice.ajxm.anche.com/query").addParameter("arg0", "<?xml version=\"1.0\" encoding=\"utf-8\"?><root><head><organ>" + stationNumber + "</organ><jkxlh>" + api + "</jkxlh><jkid>WR034</jkid></head><body><vehispara></vehispara></body></root>").setCallBackData(new WebServiceCallBack() {
            public void callBackData(Exception e, String data) {
                if (e != null) {
                    e.printStackTrace();
                    Toast.makeText(context, "访问服务器失败...", Toast.LENGTH_SHORT).show();
                }
                if (data.endsWith("101")) {
                    Toast.makeText(context, "服务端返回：xmlHeadMap为null...", Toast.LENGTH_SHORT).show();
                }
                if (data.equals("")) {
                    Toast.makeText(context, "服务端返回：数据为空...", Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String, String> xmlHeadMap = XMLTools.getXmlHead(data);
                if (xmlHeadMap != null) {
                    String message = (String) xmlHeadMap.get("message");
                    if ("1".equals((String) xmlHeadMap.get("code"))) {
                        Map<String, String> xmlBodyMap = XMLTools.getXmlBody(data);
                        AppUpdate.serverVersionCode = MapValueUtils.getMapValue("versionCode", xmlBodyMap);
                        AppUpdate.appUrl = MapValueUtils.getMapValue("url", xmlBodyMap);
                        AppUpdate.serverVersionName = MapValueUtils.getMapValue("versionName", xmlBodyMap);
                        AppUpdate.apkName = MapValueUtils.getMapValue("apkName", xmlBodyMap);
                        if (AppUpdate.appUrl.contains("127.0.0.1")) {
                            AppUpdate.appUrl = AppUpdate.appUrl.replace("127.0.0.1", Constant.IP);
                        } else if (AppUpdate.appUrl.contains("localhost")) {
                            AppUpdate.appUrl = AppUpdate.appUrl.replace("localhost", Constant.port);
                        }
                        AppUpdate.appContent = MapValueUtils.getMapValue("content", xmlBodyMap);
                        if (((long) AppUpdate.getVersionCode(context)) < Long.valueOf(AppUpdate.serverVersionCode).longValue()) {
                            AppUpdate.showDialog(context, AppUpdate.appContent, AppUpdate.appUrl);
                        }
                    } else if (!"".equals(message)) {
                        Toast.makeText(context, WebserviceInit.getURLDecoderString(message), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static void showDialog(final Context context, String content, final String url) {
        final MaterialDialog dialog = new MaterialDialog(context);
        ((MaterialDialog) ((MaterialDialog) ((MaterialDialog) ((MaterialDialog) dialog.content(content)).btnText("取消", "更新")).title("版本更新 ")).titleTextSize(15.0f)).show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnBtnClickL(new OnBtnClickL() {
            public void onBtnClick() {
                dialog.dismiss();
            }
        }, new OnBtnClickL() {
            public void onBtnClick() {
                dialog.dismiss();
                AppUpdate.pBar = new CommonProgressDialog(context);
                AppUpdate.pBar.setCanceledOnTouchOutside(false);
                AppUpdate.pBar.setTitle("正在下载");
                AppUpdate.pBar.setCustomTitle(LayoutInflater.from(context).inflate(R.layout.title_dialog, null));
                AppUpdate.pBar.setMessage("正在下载");
                AppUpdate.pBar.setIndeterminate(true);
                AppUpdate.pBar.setProgressStyle(1);
                AppUpdate.pBar.setCancelable(true);
                final DownloadTask downloadTask = new DownloadTask(context);
                downloadTask.execute(new String[]{url});
                AppUpdate.pBar.setOnCancelListener(new OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        downloadTask.cancel(true);
                    }
                });
            }
        });
    }
}
