package com.dikiy.workshop.ui.views.android.expandableView.model;

public class Step {

    private final String header, description;
    private final int imageRes;

    public Step(String header, String description, int imageRes) {
        this.header = header;
        this.description = description;
        this.imageRes = imageRes;
    }

    public String getHeader() {
        return header;
    }

    public String getDescription() {
        return description;
    }

    public int getImageRes() {
        return imageRes;
    }
}
