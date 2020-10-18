package com.dikiy.workshop.code.sdks.facebook.samples;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dikiy.workshop.R;

public class SwitchUserActivity extends AppCompatActivity {

    private static final String SHOWING_SETTINGS_KEY = "Showing settings";

    private ProfileFragment profileFragment;
    private SettingsFragment settingsFragment;
    private boolean isShowingSettings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_switch_user);

        restoreFragments(savedInstanceState);

        if (savedInstanceState != null) {
            if (savedInstanceState.getBoolean(SHOWING_SETTINGS_KEY)) {
                showSettings();
            } else {
                showProfile();
            }
        } else {
            showProfile();
        }
    }

    @Override
    public void onBackPressed() {
        if (isShowingSettings()) {
            // This back is from the settings fragment
            showProfile();
        } else {
            // Allow the user to back out of the app as well.
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SHOWING_SETTINGS_KEY, isShowingSettings());

        FragmentManager manager = getSupportFragmentManager();
        manager.putFragment(outState, SettingsFragment.TAG, settingsFragment);
        manager.putFragment(outState, ProfileFragment.TAG, profileFragment);
    }

    @Override
    protected void onResume() {
        super.onResume();

        profileFragment.setOnOptionsItemSelectedListener(
                this::handleOptionsItemSelected);
    }

    @Override
    protected void onPause() {
        super.onPause();

        profileFragment.setOnOptionsItemSelectedListener(null);
    }

    private void restoreFragments(Bundle savedInstanceState) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        if (savedInstanceState != null) {
            profileFragment =
                    (ProfileFragment) manager.getFragment(savedInstanceState, ProfileFragment.TAG);
            settingsFragment =
                    (SettingsFragment) manager.getFragment(savedInstanceState, SettingsFragment.TAG);
        }

        if (profileFragment == null) {
            profileFragment = new ProfileFragment();
            transaction.add(R.id.fragmentContainer, profileFragment, ProfileFragment.TAG);
        }

        if (settingsFragment == null) {
            settingsFragment = new SettingsFragment();
            transaction.add(R.id.fragmentContainer, settingsFragment, SettingsFragment.TAG);
        }

        transaction.commit();
    }

    private void showSettings() {
        isShowingSettings = true;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(profileFragment).show(settingsFragment).commit();
    }

    private boolean isShowingSettings() {
        return isShowingSettings;
    }

    private void showProfile() {
        isShowingSettings = false;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(settingsFragment).show(profileFragment).commit();
    }

    private boolean handleOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_switch_user) {
            showSettings();
            return true;
        } else {
            return false;
        }
    }
}
