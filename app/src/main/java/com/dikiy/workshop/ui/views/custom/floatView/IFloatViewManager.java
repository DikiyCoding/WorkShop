package com.dikiy.workshop.ui.views.custom.floatView;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;

public interface IFloatViewManager {

    void bindIFloatView(@NonNull IFloatView iFloatView, @NonNull NestedScrollView scrollView);

    void bindIFloatView(@NonNull IFloatView iFloatView);

    void unbindIFloatView(@NonNull IFloatView iFloatView);
}
