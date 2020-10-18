package com.dikiy.workshop.code.sdks.manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dikiy.workshop.R;
import com.dikiy.workshop.code.sdks.facebook.login.CustomButtonActivity;
import com.dikiy.workshop.code.sdks.facebook.login.LoginButtonActivity;
import com.dikiy.workshop.code.sdks.facebook.samples.SwitchUserActivity;

public class SignInManagerFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_manager_sign_in, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.btn_sign_in_facebook_login).setOnClickListener(this);
        view.findViewById(R.id.btn_sign_in_facebook_custom).setOnClickListener(this);
        view.findViewById(R.id.btn_sign_in_facebook_swith_user).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_sign_in_facebook_login:
                intent = new Intent(requireContext(), LoginButtonActivity.class);
                break;
            case R.id.btn_sign_in_facebook_custom:
                intent = new Intent(requireContext(), CustomButtonActivity.class);
                break;
            case R.id.btn_sign_in_facebook_swith_user:
                intent = new Intent(requireContext(), SwitchUserActivity.class);
                break;
            default:
        }
        if(intent != null) startActivity(intent);
    }
}