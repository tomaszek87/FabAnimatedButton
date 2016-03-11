package com.tomek.fabbutton.utils.tools;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

import com.tomek.fabbutton.R;
import com.tomek.fabbutton.fab.activity.MainActivity;

/**
 * Created by Tomek on 10.03.2016.
 */
public class FabAnimator {

    private static float tmp;

    public static void showAnimation(boolean visibility, final FloatingActionButton fab, final MainActivity context) {
        tmp = fab.getX();
        if (visibility)
            toogleHideAnimation(fab, context);
        else
            toogleShowAnimation(fab, context);
    }

    public static void toogleShowAnimation(final FloatingActionButton fab, final MainActivity context) {
        final float tmp = fab.getX();
        System.out.println(tmp);
        AnimationSet animationSet = new AnimationSet(true);
        RotateAnimation r = new RotateAnimation(0f, 270f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        r.setDuration(700);
        // r.setInterpolator(new DecelerateInterpolator());
        r.setFillAfter(true); //HERE
        animationSet.addAnimation(r);
        animationSet.setFillAfter(true);
        ValueAnimator anim = new ValueAnimator();
        anim.setIntValues(Color.parseColor("#ff0091ea"), Color.parseColor("#000091ea"));
        anim.setEvaluator(new ArgbEvaluator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                fab.setBackgroundTintList(ColorStateList.valueOf((Integer) valueAnimator.getAnimatedValue()));
            }
        });

        anim.setDuration(700);
        anim.start();
        ValueAnimator anim2 = new ValueAnimator();
        anim2.setIntValues(Color.parseColor("#FFFFFF"), Color.parseColor("#ffb71c1c"));
        anim2.setEvaluator(new ArgbEvaluator());
        anim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                fab.getDrawable().setColorFilter((Integer) valueAnimator.getAnimatedValue(), PorterDuff.Mode.MULTIPLY);
            }
        });

        anim2.setDuration(700);
        anim2.start();
        TranslateAnimation a = new TranslateAnimation(0, -400, 0, 0);
        a.setDuration(700);
        a.setFillAfter(true); //HERE
        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                fab.setDrawingCacheEnabled(true);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();

                layoutParams.rightMargin = (int) (450);
                System.out.println(fab.getX());

                fab.setLayoutParams(layoutParams);
                fab.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorPrimaryTransparent));
                animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                animation.setDuration(1);
                fab.startAnimation(animation);

                //       fab.setX(tmp - 350);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                context.popupMenu(context.fab1);
            }

        }, 200);
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                context.popupMenu(context.fab2);
            }

        }, 250);
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                context.popupMenu(context.fab3);
            }

        }, 300);
        animationSet.addAnimation(a);
        fab.startAnimation(animationSet);
    }

    public static void toogleHideAnimation(final FloatingActionButton fab, final MainActivity context) {
        final float tmp = fab.getX();
        AnimationSet animationSet = new AnimationSet(true);
        RotateAnimation r = new RotateAnimation(0f, 270f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        r.setDuration(700);
        // r.setInterpolator(new DecelerateInterpolator());
        r.setFillAfter(true); //HERE
        animationSet.addAnimation(r);
        animationSet.setFillAfter(true);
        ValueAnimator anim = new ValueAnimator();
        anim.setIntValues(Color.parseColor("#ffb71c1c"), Color.parseColor("#ff0091ea"));
        anim.setEvaluator(new ArgbEvaluator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                fab.setBackgroundTintList(ColorStateList.valueOf((Integer) valueAnimator.getAnimatedValue()));

            }
        });
        anim.setDuration(700);
        anim.start();
        ValueAnimator anim2 = new ValueAnimator();
        anim2.setIntValues(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        anim2.setEvaluator(new ArgbEvaluator());
        anim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                fab.getDrawable().setColorFilter((Integer) valueAnimator.getAnimatedValue(), PorterDuff.Mode.MULTIPLY);
            }
        });

        anim2.setDuration(700);
        anim2.start();
        TranslateAnimation a = new TranslateAnimation(0, 400, 0, 0);
        a.setDuration(700);
        a.setFillAfter(true); //HERE
        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                fab.getDrawable().setColorFilter(null);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //       fab.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorPrimary));
                animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f);
                animation.setDuration(1);
                fab.startAnimation(animation);
                CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();

                layoutParams.rightMargin = (int) (48);
                System.out.println(fab.getX());

                fab.setLayoutParams(layoutParams);
                fab.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                context.hideMenu(context.fab1);
            }

        }, 50);
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                context.hideMenu(context.fab2);
            }

        }, 100);
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                context.hideMenu(context.fab3);
            }

        }, 150);
        animationSet.addAnimation(a);
        fab.startAnimation(animationSet);
    }


}
