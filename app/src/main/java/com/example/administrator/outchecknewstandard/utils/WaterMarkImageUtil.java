package com.example.administrator.outchecknewstandard.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

public class WaterMarkImageUtil {
    public static Bitmap createWaterMaskLeftTop(Context context, Bitmap src, Bitmap watermark, int paddingLeft, int paddingTop) {
        return createWaterMaskBitmap(src, watermark, dp2px(context, (float) paddingLeft), dp2px(context, (float) paddingTop));
    }

    private static Bitmap createWaterMaskBitmap(Bitmap src, Bitmap watermark, int paddingLeft, int paddingTop) {
        if (src == null) {
            return null;
        }
        Bitmap newb = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(newb);
        canvas.drawBitmap(src, 0.0f, 0.0f, null);
        canvas.drawBitmap(watermark, (float) paddingLeft, (float) paddingTop, null);
        canvas.save();
        canvas.restore();
        return newb;
    }

    public static Bitmap createWaterMaskRightBottom(Context context, Bitmap src, Bitmap watermark, int paddingRight, int paddingBottom) {
        return createWaterMaskBitmap(src, watermark, (src.getWidth() - watermark.getWidth()) - dp2px(context, (float) paddingRight), (src.getHeight() - watermark.getHeight()) - dp2px(context, (float) paddingBottom));
    }

    public static Bitmap createWaterMaskRightTop(Context context, Bitmap src, Bitmap watermark, int paddingRight, int paddingTop) {
        return createWaterMaskBitmap(src, watermark, (src.getWidth() - watermark.getWidth()) - dp2px(context, (float) paddingRight), dp2px(context, (float) paddingTop));
    }

    public static Bitmap createWaterMaskLeftBottom(Context context, Bitmap src, Bitmap watermark, int paddingLeft, int paddingBottom) {
        return createWaterMaskBitmap(src, watermark, dp2px(context, (float) paddingLeft), (src.getHeight() - watermark.getHeight()) - dp2px(context, (float) paddingBottom));
    }

    public static Bitmap createWaterMaskCenter(Bitmap src, Bitmap watermark) {
        return createWaterMaskBitmap(src, watermark, (src.getWidth() - watermark.getWidth()) / 2, (src.getHeight() - watermark.getHeight()) / 2);
    }

    public static Bitmap drawTextToLeftTop(Context context, Bitmap bitmap, String text, int size, int color, int paddingLeft, int paddingTop) {
        Paint paint = new Paint(1);
        paint.setColor(color);
        paint.setTextSize((float) dp2px(context, (float) size));
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return drawTextToBitmap(context, bitmap, text, paint, bounds, dp2px(context, (float) paddingLeft), dp2px(context, (float) paddingTop) + bounds.height());
    }

    public static Bitmap drawTextToRightBottom(Context context, Bitmap bitmap, String text, int size, int color, int paddingRight, int paddingBottom) {
        Paint paint = new Paint(1);
        paint.setColor(color);
        paint.setTextSize((float) dp2px(context, (float) size));
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return drawTextToBitmap(context, bitmap, text, paint, bounds, (bitmap.getWidth() - bounds.width()) - dp2px(context, (float) paddingRight), bitmap.getHeight() - dp2px(context, (float) paddingBottom));
    }

    public static Bitmap drawTextToRightTop(Context context, Bitmap bitmap, String text, int size, int color, int paddingRight, int paddingTop) {
        Paint paint = new Paint(1);
        paint.setColor(color);
        paint.setTextSize((float) dp2px(context, (float) size));
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return drawTextToBitmap(context, bitmap, text, paint, bounds, (bitmap.getWidth() - bounds.width()) - dp2px(context, (float) paddingRight), dp2px(context, (float) paddingTop) + bounds.height());
    }

    public static Bitmap drawTextToLeftBottom(Context context, Bitmap bitmap, String text, int size, int color, int paddingLeft, int paddingBottom) {
        Paint paint = new Paint(1);
        paint.setColor(color);
        paint.setTextSize((float) dp2px(context, (float) size));
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return drawTextToBitmap(context, bitmap, text, paint, bounds, dp2px(context, (float) paddingLeft), bitmap.getHeight() - dp2px(context, (float) paddingBottom));
    }

    public static Bitmap drawTextToCenter(Context context, Bitmap bitmap, String text, int size, int color) {
        Paint paint = new Paint(1);
        paint.setColor(color);
        paint.setTextSize((float) dp2px(context, (float) size));
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return drawTextToBitmap(context, bitmap, text, paint, bounds, (bitmap.getWidth() - bounds.width()) / 2, (bitmap.getHeight() + bounds.height()) / 2);
    }

    private static Bitmap drawTextToBitmap(Context context, Bitmap bitmap, String text, Paint paint, Rect bounds, int paddingLeft, int paddingTop) {
        Config bitmapConfig = bitmap.getConfig();
        paint.setDither(true);
        paint.setFilterBitmap(true);
        if (bitmapConfig == null) {
            bitmapConfig = Config.ARGB_8888;
        }
        bitmap = bitmap.copy(bitmapConfig, true);
        new Canvas(bitmap).drawText(text, (float) paddingLeft, (float) paddingTop, paint);
        return bitmap;
    }

    public static Bitmap scaleWithWH(Bitmap src, double w, double h) {
        if (w == 0.0d || h == 0.0d || src == null) {
            return src;
        }
        int width = src.getWidth();
        int height = src.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale((float) (w / ((double) width)), (float) (h / ((double) height)));
        return Bitmap.createBitmap(src, 0, 0, width, height, matrix, true);
    }

    public static int dp2px(Context context, float dp) {
        return (int) ((dp * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
