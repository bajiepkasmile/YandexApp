package com.domain.yandexapp.utils.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

public class UpdatingBarAnimation {

    public static void animateIn(final LinearLayout updatingBar) {
        Animation translateDown = new TranslateAnimation(
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, -updatingBar.getLayoutParams().height,
                Animation.ABSOLUTE, 0);
        translateDown.setDuration(550);
        translateDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                updatingBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        updatingBar.startAnimation(translateDown);
    }

    public static void animateOut(final LinearLayout updatingBar) {
        Animation translateUp = new TranslateAnimation(
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, -updatingBar.getLayoutParams().height);
        translateUp.setDuration(550);
        translateUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                updatingBar.setTranslationY(0);
                updatingBar.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        updatingBar.startAnimation(translateUp);
    }

}
