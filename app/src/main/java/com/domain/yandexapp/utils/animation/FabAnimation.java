package com.domain.yandexapp.utils.animation;

import android.animation.Animator;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

public class FabAnimation {

    public static void animateOut(final FloatingActionButton fab) {
        fab.animate().rotation(-360).scaleX(0).scaleY(0).alpha(0.5f).setDuration(550).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                fab.setRotation(0);
                fab.setScaleX(1);
                fab.setScaleY(1);
                fab.setAlpha(1.0f);
                fab.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        }).start();
    }

    public static void animateIn(final FloatingActionButton fab) {
        fab.setScaleX(0);
        fab.setScaleY(0);
        fab.setAlpha(0.0f);
        fab.animate().rotation(-360).scaleX(1).scaleY(1).alpha(1.0f).setDuration(550).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                fab.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                fab.setRotation(0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        }).start();
    }

}
