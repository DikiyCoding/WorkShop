package com.dikiy.workshop.code.libraries.moxy.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dikiy.workshop.R;
import com.dikiy.workshop.code.libraries.moxy.mvpviews.TimerView;
import com.dikiy.workshop.code.libraries.moxy.presenters.TimerPresenter;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class TimerActivity extends MvpAppCompatActivity implements TimerView {

    private TextView mTimer;

    @InjectPresenter
    TimerPresenter timerPresenter;

    @ProvidePresenter
    TimerPresenter provideTimerPresenter() {
        return new TimerPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        mTimer = findViewById(R.id.tv_timer);
    }

    @Override
    public void showTimer() {
        mTimer.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTimer() {
        mTimer.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setTimer(Integer seconds) {
        mTimer.setText(seconds.toString());
    }

    @Override
    public void showMessage(Integer message) {

    }
}
