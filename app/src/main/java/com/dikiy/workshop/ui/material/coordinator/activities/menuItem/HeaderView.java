package com.dikiy.workshop.ui.material.coordinator.activities.menuItem;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dikiy.workshop.R;

public class HeaderView extends RelativeLayout {

    private TextView tvName;
    private TextView tvDescription;

    public HeaderView(Context context) {
        super(context);
    }

    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvName = this.findViewById(R.id.name);
        tvDescription = this.findViewById(R.id.description);
    }

    public void setTextSize(float size) {
        tvName.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    public void bindTo(String name, String lastSeen) {
        this.tvName.setText(name);
        this.tvDescription.setText(lastSeen);
    }
}