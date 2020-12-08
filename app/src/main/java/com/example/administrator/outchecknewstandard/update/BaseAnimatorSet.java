package com.example.administrator.outchecknewstandard.update;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.View;
import android.view.animation.Interpolator;
import com.nineoldandroids.view.ViewHelper;

public abstract class BaseAnimatorSet {
    protected AnimatorSet animatorSet = new AnimatorSet();
    private long delay;
    protected long duration = 500;
    private Interpolator interpolator;
    private AnimatorListener listener;

    public interface AnimatorListener {
        void onAnimationCancel(Animator animator);

        void onAnimationEnd(Animator animator);

        void onAnimationRepeat(Animator animator);

        void onAnimationStart(Animator animator);
    }

    public abstract void setAnimation(View view);

    protected void start(View view) {
        reset(view);
        setAnimation(view);
        this.animatorSet.setDuration(this.duration);
        if (this.interpolator != null) {
            this.animatorSet.setInterpolator(this.interpolator);
        }
        if (this.delay > 0) {
            this.animatorSet.setStartDelay(this.delay);
        }
        if (this.listener != null) {
            this.animatorSet.addListener(new Animator.AnimatorListener() {
                public void onAnimationStart(Animator animator) {
                    BaseAnimatorSet.this.listener.onAnimationStart(animator);
                }

                public void onAnimationRepeat(Animator animator) {
                    BaseAnimatorSet.this.listener.onAnimationRepeat(animator);
                }

                public void onAnimationEnd(Animator animator) {
                    BaseAnimatorSet.this.listener.onAnimationEnd(animator);
                }

                public void onAnimationCancel(Animator animator) {
                    BaseAnimatorSet.this.listener.onAnimationCancel(animator);
                }
            });
        }
        this.animatorSet.start();
    }

    public static void reset(View view) {
        ViewHelper.setAlpha(view, 1.0f);
        ViewHelper.setScaleX(view, 1.0f);
        ViewHelper.setScaleY(view, 1.0f);
        ViewHelper.setTranslationX(view, 0.0f);
        ViewHelper.setTranslationY(view, 0.0f);
        ViewHelper.setRotation(view, 0.0f);
        ViewHelper.setRotationY(view, 0.0f);
        ViewHelper.setRotationX(view, 0.0f);
    }

    public BaseAnimatorSet duration(long duration) {
        this.duration = duration;
        return this;
    }

    public BaseAnimatorSet delay(long delay) {
        this.delay = delay;
        return this;
    }

    public BaseAnimatorSet interpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
        return this;
    }

    public BaseAnimatorSet listener(AnimatorListener listener) {
        this.listener = listener;
        return this;
    }

    public void playOn(View view) {
        start(view);
    }
}
