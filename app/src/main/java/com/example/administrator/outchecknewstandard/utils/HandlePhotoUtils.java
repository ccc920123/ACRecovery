package com.example.administrator.outchecknewstandard.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Environment;

import androidx.recyclerview.widget.ItemTouchHelper;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class HandlePhotoUtils {
    public static void openCamera(Activity activity, String path, String fileName, int requestCode) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", Uri.fromFile(new File(path + fileName)));
        activity.startActivityForResult(intent, requestCode);
    }

    public static boolean isSDExist() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static Bitmap getImageBitmap(String srcPath) {
        System.out.println(srcPath + "----------imagesrcPath-----------------------------------");
        Options newOpts = new Options();
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        int be;
        if (h > w) {
            be = 1;
            if (w > h && ((float) w) > 250.0f) {
                be = (int) (((float) newOpts.outWidth) / 250.0f);
            } else if (w < h && ((float) h) > 480.0f) {
                be = (int) (((float) newOpts.outHeight) / 480.0f);
            }
            if (be <= 0) {
                be = 1;
            }
            newOpts.inSampleSize = be;
        } else if (w > h) {
            be = 1;
            if (w > h && ((float) w) > 480.0f) {
                be = (int) (((float) newOpts.outWidth) / 480.0f);
            } else if (w < h && ((float) h) > 250.0f) {
                be = (int) (((float) newOpts.outHeight) / 250.0f);
            }
            if (be <= 0) {
                be = 1;
            }
            newOpts.inSampleSize = be;
        }
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        System.out.println(bitmap + "----------bitmap-----------------------------------");
        return compressImage(bitmap);
    }

    public static Bitmap compressImage(Bitmap image) {
        System.out.println(image + "----------image-----------------------------------");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(CompressFormat.JPEG, 100, baos);
        int options = 90;
        while (baos.toByteArray().length / 1024 > ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION) {
            baos.reset();
            image.compress(CompressFormat.JPEG, options, baos);
            options -= 10;
        }
        return BitmapFactory.decodeStream(new ByteArrayInputStream(baos.toByteArray()), null, null);
    }

    public static void saveFile(Bitmap bm, String fileName) throws Exception {
        File dirFile = new File(fileName);
        if (dirFile.exists()) {
            System.out.println("----------shanchu-----------------------------------");
            dirFile.delete();
        }
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(fileName)));
        bm.compress(CompressFormat.JPEG, 100, bos);
        bos.flush();
        bos.close();
    }
}
