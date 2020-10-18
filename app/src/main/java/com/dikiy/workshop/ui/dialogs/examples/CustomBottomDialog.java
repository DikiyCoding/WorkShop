package com.dikiy.workshop.ui.dialogs.examples;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.airbnb.lottie.LottieAnimationView;
import com.dikiy.workshop.R;
import com.willy.ratingbar.ScaleRatingBar;

public class CustomBottomDialog extends Dialog {

    public CustomBottomDialog(@NonNull Context context) {
        super(context);
        buildDialog();
        setListeners();
    }

    private void buildDialog() {
        this.setContentView(R.layout.dialog_example_third);
        this.setCanceledOnTouchOutside(false);
        Window window = this.getWindow();
        assert window != null;
        // Задаём прозрачный фон окну, для того чтобы можно было отобразить элемент вне границ диалога
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        // Получаем атрибуты и добавляем параматры расположения диалогового окна (внизу экрана)
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        windowAttributes.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        windowAttributes.gravity = Gravity.BOTTOM;
        windowAttributes.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        // Добавляем анимацию появления диалогового окна
        windowAttributes.windowAnimations = R.style.DialogAnimation;
        // Задаём изменённые параметры
        window.setAttributes(windowAttributes);
    }

    private void setListeners() {
        ScaleRatingBar ratingBar = findViewById(R.id.rb_rate);
        LottieAnimationView lottie = findViewById(R.id.lottie_rate);
        Button btnCancel = findViewById(R.id.btn_rate_cancel);
        Button btnOk = findViewById(R.id.btn_rate_ok);
        btnCancel.setOnClickListener(view -> dismiss());
        btnOk.setOnClickListener(view -> dismiss());
        ratingBar.setOnRatingChangeListener((view, rating, fromUser) -> {
            switch ((int) rating) {
                case 1:
                    lottie.setAnimation(R.raw.onestars);
                    break;
                case 2:
                    lottie.setAnimation(R.raw.twostars);
                    break;
                case 3:
                    lottie.setAnimation(R.raw.threestars);
                    break;
                case 4:
                    lottie.setAnimation(R.raw.fourstars);
                    break;
                case 5:
                    lottie.setAnimation(R.raw.fivestars);
            }
            lottie.playAnimation();
        });
    }
}
