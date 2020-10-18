package com.dikiy.workshop.ui.views.custom.floatView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class FloatView extends LinearLayout implements IFloatView {

    public FloatView(Context context) {
        super(context);
    }

    public FloatView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onFloatIn() {
//        setVisibility(View.VISIBLE);
        animate().alpha(1.0f)
                .setListener(null);
    }

    @Override
    public void onFloatOut() {
        animate().alpha(0.0f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
//                        setVisibility(View.GONE);
                    }
                });
    }
}