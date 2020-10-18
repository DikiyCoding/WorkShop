package com.dikiy.workshop.ui.views.android.expandableView.model;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;

public class Rate {

    private String rate, description;
    private Spannable spRate, spDescription;

    public Rate(String rate, String description) {
        this.rate = rate;
        this.description = description;
    }

    public String getRate() {
        return rate;
    }

    public String getDescription() {
        return description;
    }

    public Spannable getSpRate() {
        if (spRate == null)
            handleRate();
        return spRate;
    }

    public Spannable getSpDescription() {
        if (spDescription == null)
            handleDescription();
        return spDescription;
    }

    private void handleRate() {
        String[] words = rate.split(" ");
        switch (words.length) {
            case 0:
                spRate = new SpannableString("");
                break;
            case 2:
                Spannable wordFirst = new SpannableString(words[0]);
                Spannable wordSecond = new SpannableString(words[1]);
                wordFirst.setSpan(new RelativeSizeSpan(0.66f), 0,
                        wordFirst.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                spRate = new SpannableString(TextUtils.concat(wordFirst, " ",  wordSecond));
                break;
            default:
                spRate = new SpannableString(rate);
        }
    }

    private void handleDescription() {
        spDescription = new SpannableString(description);
    }
}
