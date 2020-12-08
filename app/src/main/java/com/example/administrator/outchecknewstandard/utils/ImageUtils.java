package com.example.administrator.outchecknewstandard.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Environment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtils {
    public static String savePhoto(Bitmap photoBitmap, String path, String photoName) throws IOException {
        FileNotFoundException e;
        IOException e2;
        Throwable th;
        String localPath = null;
        if (Environment.getExternalStorageState().equals("mounted")) {
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File photoFile = new File(path, photoName + ".png");
            FileOutputStream fileOutputStream = null;
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(photoFile);
                if (photoBitmap != null) {
                    try {
                        if (photoBitmap.compress(CompressFormat.PNG, 100, fileOutputStream2)) {
                            localPath = photoFile.getPath();
                            fileOutputStream2.flush();
                        }
                    } catch (FileNotFoundException e3) {
                        e = e3;
                        fileOutputStream = fileOutputStream2;
                        try {
                            photoFile.delete();
                            localPath = null;
                            e.printStackTrace();
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e22) {
                                    e22.printStackTrace();
                                }
                            }
                            return localPath;
                        } catch (Throwable th2) {
                            th = th2;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e222) {
                                    e222.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (IOException e4) {
                        e2 = e4;
                        fileOutputStream = fileOutputStream2;
                        photoFile.delete();
                        localPath = null;
                        e2.printStackTrace();
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e2222) {
                                e2222.printStackTrace();
                            }
                        }
                        return localPath;
                    } catch (Throwable th3) {
                        th = th3;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                }
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e22222) {
                        e22222.printStackTrace();
                    }
                }
            } catch (Throwable e5) {

                photoFile.delete();
                localPath = null;
                e5.printStackTrace();
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return localPath;
            }
        }
        return localPath;
    }

    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        float roundPx;
        float top;
        float bottom;
        float left;
        float right;
        float dst_left;
        float dst_top;
        float dst_right;
        float dst_bottom;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= height) {
            roundPx = (float) (width / 2);
            top = 0.0f;
            bottom = (float) width;
            left = 0.0f;
            right = (float) width;
            height = width;
            dst_left = 0.0f;
            dst_top = 0.0f;
            dst_right = (float) width;
            dst_bottom = (float) width;
        } else {
            roundPx = (float) (height / 2);
            float clip = (float) ((width - height) / 2);
            left = clip;
            right = ((float) width) - clip;
            top = 0.0f;
            bottom = (float) height;
            width = height;
            dst_left = 0.0f;
            dst_top = 0.0f;
            dst_right = (float) height;
            dst_bottom = (float) height;
        }
        Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        Rect rect = new Rect((int) left, (int) top, (int) right, (int) bottom);
        Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);
        RectF rectF = new RectF(dst);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, dst, paint);
        return output;
    }
}
