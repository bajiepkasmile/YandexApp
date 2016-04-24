package com.domain.yandexapp.utils.animation;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class FabHidingBehavior extends FloatingActionButton.Behavior {

    private static final boolean SCROLL_UP = false;
    private static final boolean SCROLL_DOWN = true;

    private boolean lastScroll = SCROLL_DOWN;

    public FabHidingBehavior(Context context, AttributeSet attrs) {
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                                       View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target,
                               int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

        int bottomMargin = ((CoordinatorLayout.LayoutParams) child.getLayoutParams()).bottomMargin;

        if (Math.abs(dyConsumed) > 2) { //чтобы кнопка не дёргалась, когда список прокручивают очень-очень медленно
            if (dyConsumed > 0) {
                if (lastScroll != SCROLL_UP) { //если lastScroll == SCROLL_UP значит анимация уже была задана ранее и заново задавать её не нужно
                    child.animate()
                            .translationY(child.getHeight() + bottomMargin)
                            .setDuration((long) ((((child.getHeight() + bottomMargin) - child.getTranslationY()) / (child.getHeight() + bottomMargin)) * 200))
                            .setInterpolator(new LinearInterpolator())
                            .start();
                    lastScroll = SCROLL_UP;
                }
            } else {
                if (lastScroll != SCROLL_DOWN) {
                    child.animate()
                            .translationY(0)
                            .setDuration((long) ((child.getTranslationY() / (child.getHeight() + bottomMargin)) * 200))
                            .setInterpolator(new LinearInterpolator())
                            .start();

                    lastScroll = SCROLL_DOWN;
                }
            }
        }
    }

}
