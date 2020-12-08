package com.example.administrator.outchecknewstandard.update;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

public class CornerUtils {
    public static Drawable cornerDrawable(int bgColor, float cornerradius) {
        GradientDrawable bg = new GradientDrawable();
        bg.setCornerRadius(cornerradius);
        bg.setColor(bgColor);
        return bg;
    }

    public static Drawable cornerDrawable(int bgColor, float[] cornerradius) {
        GradientDrawable bg = new GradientDrawable();
        bg.setCornerRadii(cornerradius);
        bg.setColor(bgColor);
        return bg;
    }

    public static Drawable cornerDrawable(int bgColor, float[] cornerradius, int borderwidth, int bordercolor) {
        GradientDrawable bg = new GradientDrawable();
        bg.setCornerRadii(cornerradius);
        bg.setStroke(borderwidth, bordercolor);
        bg.setColor(bgColor);
        return bg;
    }

    public static StateListDrawable btnSelector(float radius, int normalColor, int pressColor, int postion) {
        StateListDrawable bg = new StateListDrawable();
        Drawable normal = null;
        Drawable pressed = null;
        if (postion == 0) {
            normal = cornerDrawable(normalColor, new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, radius, radius});
            pressed = cornerDrawable(pressColor, new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, radius, radius});
        } else if (postion == 1) {
            normal = cornerDrawable(normalColor, new float[]{0.0f, 0.0f, 0.0f, 0.0f, radius, radius, 0.0f, 0.0f});
            pressed = cornerDrawable(pressColor, new float[]{0.0f, 0.0f, 0.0f, 0.0f, radius, radius, 0.0f, 0.0f});
        } else if (postion == -1) {
            normal = cornerDrawable(normalColor, new float[]{0.0f, 0.0f, 0.0f, 0.0f, radius, radius, radius, radius});
            pressed = cornerDrawable(pressColor, new float[]{0.0f, 0.0f, 0.0f, 0.0f, radius, radius, radius, radius});
        } else if (postion == -2) {
            normal = cornerDrawable(normalColor, radius);
            pressed = cornerDrawable(pressColor, radius);
        }
        bg.addState(new int[]{-16842919}, normal);
        bg.addState(new int[]{16842919}, pressed);
        return bg;
    }

    public static StateListDrawable listItemSelector(float radius, int normalColor, int pressColor, boolean isLastPostion) {
        Drawable normal;
        Drawable pressed;
        StateListDrawable bg = new StateListDrawable();
        if (isLastPostion) {
            normal = cornerDrawable(normalColor, new float[]{0.0f, 0.0f, 0.0f, 0.0f, radius, radius, radius, radius});
            pressed = cornerDrawable(pressColor, new float[]{0.0f, 0.0f, 0.0f, 0.0f, radius, radius, radius, radius});
        } else {
            normal = new ColorDrawable(normalColor);
            pressed = new ColorDrawable(pressColor);
        }
        bg.addState(new int[]{-16842919}, normal);
        bg.addState(new int[]{16842919}, pressed);
        return bg;
    }

    public static StateListDrawable listItemSelector(float radius, int normalColor, int pressColor, int itemTotalSize, int itemPosition) {
        Drawable normal;
        Drawable pressed;
        StateListDrawable bg = new StateListDrawable();
        if (itemPosition == 0 && itemPosition == itemTotalSize - 1) {
            normal = cornerDrawable(normalColor, new float[]{radius, radius, radius, radius, radius, radius, radius, radius});
            pressed = cornerDrawable(pressColor, new float[]{radius, radius, radius, radius, radius, radius, radius, radius});
        } else if (itemPosition == 0) {
            normal = cornerDrawable(normalColor, new float[]{radius, radius, radius, radius, 0.0f, 0.0f, 0.0f, 0.0f});
            pressed = cornerDrawable(pressColor, new float[]{radius, radius, radius, radius, 0.0f, 0.0f, 0.0f, 0.0f});
        } else if (itemPosition == itemTotalSize - 1) {
            normal = cornerDrawable(normalColor, new float[]{0.0f, 0.0f, 0.0f, 0.0f, radius, radius, radius, radius});
            pressed = cornerDrawable(pressColor, new float[]{0.0f, 0.0f, 0.0f, 0.0f, radius, radius, radius, radius});
        } else {
            normal = new ColorDrawable(normalColor);
            pressed = new ColorDrawable(pressColor);
        }
        bg.addState(new int[]{-16842919}, normal);
        bg.addState(new int[]{16842919}, pressed);
        return bg;
    }
}
