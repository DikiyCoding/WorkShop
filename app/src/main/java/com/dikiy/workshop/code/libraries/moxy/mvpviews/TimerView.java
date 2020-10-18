package com.dikiy.workshop.code.libraries.moxy.mvpviews;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface TimerView extends MvpView {

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showTimer();

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void hideTimer();

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void setTimer(Integer value);

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showMessage(Integer message);
}
