package com.dikiy.workshop.utils.keyboard;

import android.app.Activity;
import android.app.Application;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;

import com.google.android.gms.common.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class KeyboardVisionManager implements IKeyboardVisionManager {

    private Activity mActivity;
    private List<OnKeyboardVisionListener> mKeyboardVisionListeners = new ArrayList<>();
    private ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener;

    public KeyboardVisionManager(@NonNull Activity activity) {
        mActivity = activity;
        initKeyboardListener(activity);
    }

    private void initKeyboardListener(@NonNull Activity activity) {
        final View activityRoot = activity.findViewById(android.R.id.content);

        mOnGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {

            Rect rect = new Rect();
            Resources res = activity.getResources();
            DisplayMetrics displayMetrics = res.getDisplayMetrics();
            int densityDpi = displayMetrics.densityDpi;
            float a = KEYBOARD_VISIBLE_THRESHOLD_DP * (densityDpi / 160f);
            int visibleThreshold = Math.round(a);

            boolean isOpened = false;

            @Override
            public void onGlobalLayout() {

                activityRoot.getWindowVisibleDisplayFrame(rect);
                View rootView = activityRoot.getRootView();
                int heightDiff = rootView.getHeight() - rect.height();

                boolean isOpen = heightDiff > visibleThreshold;

                if (isOpen == isOpened) return;

                isOpened = isOpen;

                if (!CollectionUtils.isEmpty(mKeyboardVisionListeners))
                    for (OnKeyboardVisionListener listener : mKeyboardVisionListeners)
                        if (isOpen) listener.onShowKeyboard();
                        else listener.onHideKeyboard();
            }
        };
        ViewTreeObserver viewTreeObserver = activityRoot.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(mOnGlobalLayoutListener);

        Application application = activity.getApplication();
        application.registerActivityLifecycleCallbacks(
                new Application.ActivityLifecycleCallbacks() {
                    @Override
                    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    }

                    @Override
                    public void onActivityStarted(Activity activity) {
                    }

                    @Override
                    public void onActivityResumed(Activity activity) {
                    }

                    @Override
                    public void onActivityPaused(Activity activity) {
                    }

                    @Override
                    public void onActivityStopped(Activity activity) {
                    }

                    @Override
                    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                    }

                    @Override
                    public void onActivityDestroyed(Activity activity) {
                        if (activity == mActivity) {
                            ViewTreeObserver viewTreeObserver1 = activityRoot.getViewTreeObserver();
                            viewTreeObserver1.removeOnGlobalLayoutListener(mOnGlobalLayoutListener);
                            mOnGlobalLayoutListener = null;
                            mKeyboardVisionListeners.clear();
                            Application application1 = mActivity.getApplication();
                            application1.unregisterActivityLifecycleCallbacks(this);
                        }
                    }
                }
        );
    }

    @Override
    public void addKeyboardListener(OnKeyboardVisionListener listener) {
        mKeyboardVisionListeners.add(listener);
    }

    @Override
    public void removeKeyboardListener(OnKeyboardVisionListener listener) {
        mKeyboardVisionListeners.remove(listener);
    }
}
