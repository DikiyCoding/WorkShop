package com.dikiy.workshop.utils.keyboard;

public interface IKeyboardVisionManager {

    int KEYBOARD_VISIBLE_THRESHOLD_DP = 100;

    void addKeyboardListener(OnKeyboardVisionListener listener);

    void removeKeyboardListener(OnKeyboardVisionListener listener);
}
