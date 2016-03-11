package com.tomek.fabbutton.utils.tools;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

/**
 * Created by Tomek on 20.02.2016.
 */
public class CustomAnimation {

    private static SupportAnimator animator;

    private void prepareAnimation(final RelativeLayout llToReveal, final boolean b, int i, FloatingActionButton fab) {
        int cx, cy;

        int radius = Math.max(llToReveal.getWidth(), llToReveal.getHeight());
        if (b) {
            cx = ((fab.getLeft() + fab.getRight()) / 2);
            cy = (fab.getBottom() + fab.getTop())/2 - 220;
            animator = ViewAnimationUtils.createCircularReveal(llToReveal, cx, cy, 0, radius);
        }
        else {
            cx = ((fab.getLeft() + fab.getRight()) / 2 + 350);
            cy = (fab.getBottom() + fab.getTop())/2 - 220;
            animator = ViewAnimationUtils.createCircularReveal(llToReveal, cx, cy, radius, 0);
        }
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(600);
        animator.addListener(new SupportAnimator.AnimatorListener() {
            @Override
            public void onAnimationStart() {

            }

            @Override
            public void onAnimationEnd() {
                llToReveal.clearAnimation();
                if (!b) {
                    llToReveal.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onAnimationCancel() {

            }

            @Override
            public void onAnimationRepeat() {

            }
        });
    }

    public void toggleAnimation(final RelativeLayout llToReveal, boolean hidden, int i, FloatingActionButton fab) {
        if (hidden) {
            prepareAnimation(llToReveal, hidden, i, fab);
            llToReveal.setVisibility(View.VISIBLE);
            animator.start();
        } else {
            prepareAnimation(llToReveal, hidden, i, fab);
            animator.start();
        }
    }

}
