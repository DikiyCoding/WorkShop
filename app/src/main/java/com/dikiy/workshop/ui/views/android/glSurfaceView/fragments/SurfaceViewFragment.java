package com.dikiy.workshop.ui.views.android.glSurfaceView.fragments;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dikiy.workshop.R;
import com.dikiy.workshop.ui.views.android.glSurfaceView.renderers.OpenGLRenderer;

import java.util.Objects;

public class SurfaceViewFragment extends Fragment {

    private GLSurfaceView glSurfaceView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_surface_view, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        supportsEs2();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        glSurfaceView = view.findViewById(R.id.surface_view);
        glSurfaceView.setEGLContextClientVersion(2);
        glSurfaceView.setRenderer(new OpenGLRenderer(requireContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
        glSurfaceView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        glSurfaceView.onPause();
    }

    private void supportsEs2() {
        if (supportsEs2(requireActivity())) {
            Toast.makeText(requireActivity(), "OpenGl ES 2.0 is not supported", Toast.LENGTH_LONG).show();
            requireActivity().finish();
        }
    }

    private boolean supportsEs2(Activity activity) {
        return Objects.requireNonNull((ActivityManager) activity
                .getSystemService(Context.ACTIVITY_SERVICE))
                .getDeviceConfigurationInfo().reqGlEsVersion >= 0x00020000;
    }
}
