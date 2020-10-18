package com.dikiy.workshop.ui.views.custom.floatView;

import android.app.Activity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;

import com.dikiy.workshop.utils.keyboard.IKeyboardVisionManager;
import com.dikiy.workshop.utils.keyboard.KeyboardVisionManager;
import com.dikiy.workshop.utils.keyboard.OnKeyboardVisionListener;
import com.google.android.gms.common.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class FloatViewManager implements IFloatViewManager, OnKeyboardVisionListener {

    private List<IFloatView> mIFloatViewList = new ArrayList<>();
    private LinkedHashMap<IFloatView, NestedScrollView> mScrollViewLinkedHashMap = new LinkedHashMap<>();

    public FloatViewManager(@NonNull Activity activity) {
        IKeyboardVisionManager keyboardVisionManager = new KeyboardVisionManager(activity);
        keyboardVisionManager.addKeyboardListener(this);
    }

    @Override
    public void bindIFloatView(@NonNull IFloatView iFloatView, @NonNull NestedScrollView scrollView) {
        mIFloatViewList.add(iFloatView);
        mScrollViewLinkedHashMap.put(iFloatView, scrollView);
        bindScrollListener(iFloatView);
    }

    @Override
    public void bindIFloatView(@NonNull IFloatView iFloatView) {
        mIFloatViewList.add(iFloatView);
    }

    @Override
    public void unbindIFloatView(@NonNull IFloatView iFloatView) {
        mIFloatViewList.remove(iFloatView);
        NestedScrollView scrollView = mScrollViewLinkedHashMap.get(iFloatView);
        if (scrollView != null) {
            scrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) null);
            mScrollViewLinkedHashMap.remove(iFloatView);
        }
    }

    private void bindScrollListener(@NonNull IFloatView iFloatView) {
        NestedScrollView scrollView = mScrollViewLinkedHashMap.get(iFloatView);
        if (scrollView != null) {
            ScrollListener listener = new ScrollListener(iFloatView);
            scrollView.setOnScrollChangeListener(listener);
        }
    }

    private void unbindScrollListener(@NonNull IFloatView iFloatView) {
        NestedScrollView scrollView = mScrollViewLinkedHashMap.get(iFloatView);
        if (scrollView != null) {
            scrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) null);
        }
    }

    @Override
    public void onShowKeyboard() {
        if (!CollectionUtils.isEmpty(mIFloatViewList)) {
            for (IFloatView iFloatView : mIFloatViewList) {
                unbindScrollListener(iFloatView);
                iFloatView.onFloatOut();
            }
        }
    }

    @Override
    public void onHideKeyboard() {
        if (!CollectionUtils.isEmpty(mIFloatViewList)) {
            for (IFloatView iFloatView : mIFloatViewList) {
                bindScrollListener(iFloatView);
                iFloatView.onFloatIn();
            }
        }
    }

    private class ScrollListener implements NestedScrollView.OnScrollChangeListener {

        private IFloatView mIFloatView;

        ScrollListener(@NonNull IFloatView iFloatView) {
            mIFloatView = iFloatView;
        }

        @Override
        public void onScrollChange(NestedScrollView v, int scrollX,
                                   int scrollY, int oldScrollX, int oldScrollY) {
            int diffY = oldScrollY - scrollY;
            if (Math.abs(diffY) < 10 && scrollY != 0) return;


            /*int visibility = ((View) mIFloatView).getVisibility();
            if (diffY < 0) {
                if (visibility == View.VISIBLE) {
//                    ((View) mIFloatView).animate()
//                            .alpha(0.0f)
//                            .setListener(new AnimatorListenerAdapter() {
//                                @Override
//                                public void onAnimationEnd(Animator animation) {
//                                    super.onAnimationEnd(animation);
//                                    ((View) mIFloatView).setVisibility(View.GONE);
//                                }
//                            });
                    ((View) mIFloatView).setVisibility(View.GONE);
                }
            } else {
                if (visibility == View.GONE) {
                    ((View) mIFloatView).setVisibility(View.VISIBLE);
//                    ((View) mIFloatView).animate()
//                            .alpha(1.0f)
//                            .setListener(null);
                }
            }*/
        }
    }
}
