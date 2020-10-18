package com.dikiy.workshop.code.google.smartlock.experimental;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dikiy.workshop.R;
import com.google.android.gms.auth.api.credentials.Credential;

public class ExperimentalActivity extends AppCompatActivity {

    final static int RC_SAVE = 1;
    final static int RC_READ = 2;
    final static String TAG = "Logs";
    private CredentialsManager credentialsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experimental);
        initValues();
    }

    private void initValues() {
        credentialsManager = new CredentialsManager(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult:" + requestCode + ":" + resultCode + ":" + data);

        switch (requestCode) {
            case RC_READ:
                if (resultCode == RESULT_OK) {
                    Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                    Log.i(TAG, "User's id is: " + credential.getId());
                    Log.i(TAG, "User's password is: " + credential.getPassword());
                } else {
                    Log.e(TAG, "Fail in reading the credential");
                    showToast("Fail in reading the credential");
                }
                break;
            case RC_SAVE:
                if (resultCode == RESULT_OK) {
                    Log.d(TAG, "Credential is saved successfully");
                    showToast("Credential is saved successfully");
                } else {
                    Log.e(TAG, "Fail in saving the credential");
                    showToast("Fail in saving the credential");
                }
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void onButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                credentialsManager.saveCredentialsByListener();
                break;
            case R.id.btn_load:
                credentialsManager.requestCredentialsByListener();
        }
    }
}
