package com.dikiy.workshop.code.libraries.moxy.presenters;

import android.os.AsyncTask;

import com.dikiy.workshop.R;
import com.dikiy.workshop.code.libraries.moxy.mvpviews.TimerView;

import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class TimerPresenter extends MvpPresenter<TimerView> {

    public TimerPresenter() {
        new AsyncTask<Void, Integer, Void>() {
            @Override
            protected void onPreExecute() {
                getViewState().showTimer();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                for(int i = 5 ; i > 0 ; i--) {
                    publishProgress(i);
                    sleepSecond();
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                getViewState().setTimer(values[0]);
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                getViewState().hideTimer();
                getViewState().showMessage(R.string.moxy_message);
            }
        }.execute();
    }

    private void sleepSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
