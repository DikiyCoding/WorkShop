package com.dikiy.workshop.code.google.smartlock.experimental;

import android.app.Activity;
import android.content.IntentSender;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequestResponse;
import com.google.android.gms.auth.api.credentials.Credentials;
import com.google.android.gms.auth.api.credentials.CredentialsClient;
import com.google.android.gms.auth.api.credentials.CredentialsOptions;
import com.google.android.gms.auth.api.credentials.IdentityProviders;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class CredentialsManager {

    private Activity activity;
    private Credential credentials;
    private CredentialsOptions credentialsOptions;
    private CredentialsClient credentialsClient;
    private CredentialRequest credentialsRequest;
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions googleSignInOptions;
    private GoogleSignInClient signInClient;

    private boolean isResolving;
    final static String TAG;
    final static int RC_SAVE, RC_READ;

    static {
        RC_SAVE = ExperimentalActivity.RC_SAVE;
        RC_READ = ExperimentalActivity.RC_READ;
        TAG = ExperimentalActivity.TAG;
    }

    CredentialsManager(final Activity activity) {
        init(activity);
    }

    private void init(Activity activity) {
        initValues(activity);
        initCredentials();
        initCredentialsOptions();
        initCredentialsRequest();
        initCredentialsClient();
        initGoogleApiClient();
    }

    /**
     * Inits
     */

    private void initValues(Activity activity) {
        isResolving = false;
        this.activity = activity;
    }

    private void initCredentials() {
        credentials = new Credential.Builder("aleksandrSergeevich@mail.ru")
                .setPassword("myPassword")
                .build();
    }

    private void initCredentialsOptions() {
        credentialsOptions = new CredentialsOptions.Builder()
                .forceEnableSaveDialog()
                .build();
    }

    private void initCredentialsClient() {
        credentialsClient = Credentials.getClient(activity, credentialsOptions);
    }

    private void initCredentialsRequest() {
        credentialsRequest = new CredentialRequest.Builder()
                .setPasswordLoginSupported(true)
                .setAccountTypes(IdentityProviders.GOOGLE)
                .build();
    }

    private void initGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(activity)
                .addApi(Auth.CREDENTIALS_API).build();
        googleApiClient.connect();
    }

    private void initGoogleSignInOptions() {
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
    }

    private void initGoogleSignInClient() {
        signInClient = GoogleSignIn.getClient(activity, googleSignInOptions);
        Task<GoogleSignInAccount> task = signInClient.silentSignIn();
        Log.i(TAG, "" + task.getResult().getAccount().name);
    }

    /**
     * Methods - private
     */

    private void onCredentialRetrieved(Credential credential) {
        String accountType = credential.getAccountType();
        if (accountType == null) {
            // Sign the user in with information from the credential.
            Log.d(TAG, "Authenticated account type is unknown");
            signInWithPassword(credential.getId(), credential.getPassword());
        } else if (accountType.equals(IdentityProviders.GOOGLE)) {
            // The user has previously signed in with Google Sign-In.
            // Silently sign in the user with the same ID.
            // See https://developers.google.com/identity/sign-in/android/
            Log.d(TAG, "Authenticated account type is Google");
            initGoogleSignInOptions();
            initGoogleSignInClient();
        }
    }

    private void signInWithPassword(String id, String password) {
        Log.i(TAG, "User's id: " + id);
        Log.i(TAG, "User's password: " + password);
    }

    private void resolveResult(ResolvableApiException rae, int requestCode) {
        // We don't want to fire multiple resolutions at once since that can result
        // in stacked dialogs after rotation or another similar event.
        /*if (isResolving) {
            Log.w(TAG, "Result is already resolved");
            return;
        }*/
        Log.d(TAG, "Resolving: " + rae);
        try {
            rae.startResolutionForResult(activity, requestCode);
//            isResolving = true;
        } catch (IntentSender.SendIntentException exception) {
            Log.e(TAG, "Failed to send resolution.", exception);
        }
    }

    /**
     * Methods - public
     */

    public void requestCredentialsByListener() {
        credentialsClient.request(credentialsRequest).addOnCompleteListener(
                new OnCompleteListener<CredentialRequestResponse>() {
                    @Override
                    public void onComplete(@NonNull Task<CredentialRequestResponse> task) {
                        if (task.isSuccessful()) {
                            onCredentialRetrieved(task.getResult().getCredential());
                            return;
                        }
                        Exception exception = task.getException();
                        if (exception instanceof ResolvableApiException) {
                            // This is most likely the case where the user has multiple saved
                            // credentials and needs to pick one. This requires showing UI to
                            // resolve the read request.
                            ResolvableApiException rae = (ResolvableApiException) exception;
                            resolveResult(rae, RC_READ);
                        } else if (exception instanceof ApiException) {
                            // The user must create an account or sign in manually.
                            Log.e(TAG, "Unsuccessful credential request.", exception);
                            ApiException ae = (ApiException) exception;
                            Log.e(TAG, "Status Code is " + ae.getStatusCode());
                        }
                    }
                });
    }

    public void saveCredentialsByListener() {
        credentialsClient.save(credentials).addOnCompleteListener(
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Credentials were saved successfully");
                            return;
                        }
                        Exception exception = task.getException();
                        if (exception instanceof ResolvableApiException) {
                            // The first time a credentials is saved, the user is shown UI
                            // to confirm the action. This requires resolution.
                            ResolvableApiException rae = (ResolvableApiException) exception;
                            resolveResult(rae, RC_SAVE);
                        } else {
                            // Save failure cannot be resolved.
                            Log.w(TAG, "Save failed.", exception);
                        }
                    }
                });
    }

    @Deprecated
    public void saveCredentialsByCallback() {
        Auth.CredentialsApi
                .save(googleApiClient, credentials)
                .setResultCallback(new ResultCallback<Result>() {
                    @Override
                    public void onResult(@NonNull Result result) {
                        if (result.getStatus().isSuccess()) {
                            // Credentials were saved
                            Log.d(TAG, "Credentials were saved successfully");
                        } else {
                            if (result.getStatus().hasResolution()) {
                                // Try to resolve the save request. This will prompt the user if
                                // the credentials is new.
                                Log.d(TAG, "Credentials were not saved successfully");
                                try {
                                    result.getStatus().startResolutionForResult(activity, RC_SAVE);
                                } catch (IntentSender.SendIntentException e) {
                                    // Could not resolve the request
                                }
                            }
                        }
                    }
                });

    }
}
