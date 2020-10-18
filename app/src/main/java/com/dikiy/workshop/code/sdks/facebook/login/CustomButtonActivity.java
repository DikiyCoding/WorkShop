package com.dikiy.workshop.code.sdks.facebook.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dikiy.workshop.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;

public class CustomButtonActivity extends AppCompatActivity {

    private LoginManager mLoginManager;
    private CallbackManager mCallbackManager;
    private ProfileTracker profileTracker;
    private AccessTokenTracker accessTokenTracker;
    private TextView mEmail, mLocale;
    private ImageButton mCustomButton;
    private ImageView mAvatar;
    private String TAG = "SignInEvent";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_fb_btn_custom);
        printKeyHash();
        init();
        updateProfile();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        profileTracker.stopTracking();
        accessTokenTracker.stopTracking();
    }

    private void init() {
        initCallbackManager();
        initLoginManager();
        initProfileTracker();
        initAccessTokenTracker();
        initViews();
        initListeners();
    }

    private void initCallbackManager() {
        mCallbackManager = CallbackManager.Factory.create();
    }

    private void initLoginManager() {
        mLoginManager = LoginManager.getInstance();
        mLoginManager.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                executeGraphRequest(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {
            }
        });
    }

    private void initProfileTracker() {
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if (oldProfile != null)
                    getProfileInfo("old profile", oldProfile);
                if (currentProfile != null)
                    getProfileInfo("current profile", currentProfile);
            }
        };
    }

    private void initAccessTokenTracker() {
        accessTokenTracker =
                new AccessTokenTracker() {
                    @Override
                    protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken,
                                                               AccessToken currentAccessToken) {
                        Profile.fetchProfileForCurrentAccessToken();
                        if (oldAccessToken != null)
                            getAccessTokenInfo("old access token", oldAccessToken);
                        if (currentAccessToken != null)
                            getAccessTokenInfo("current access token", currentAccessToken);
                    }
                };
    }

    private void initViews() {
        mAvatar = findViewById(R.id.iv_facebook_avatar);
        mEmail = findViewById(R.id.tv_facebook_email_field);
        mLocale = findViewById(R.id.tv_facebook_locale_field);
        mCustomButton = findViewById(R.id.btn_facebook_custom);
    }

    private void initListeners() {
        mCustomButton.setOnClickListener(v -> {
            AccessToken accessToken = AccessToken.getCurrentAccessToken();
            if (accessToken != null) executeGraphRequest(accessToken);
            else mLoginManager.logInWithReadPermissions(this,
                    Arrays.asList("public_profile", "email", "user_location"));
        });
    }

    private void updateProfile() {
        Profile.fetchProfileForCurrentAccessToken();
        if (Profile.getCurrentProfile() != null)
            getProfileInfo("current profile", Profile.getCurrentProfile());
    }

    private void getProfileInfo(String status, Profile profile) {
        Log.i(TAG, status + "'s id: " + profile.getId());
        Log.i(TAG, status + "'s name: " + profile.getName());
        Log.i(TAG, status + "'s first name: " + profile.getFirstName());
        Log.i(TAG, status + "'s middle name: " + profile.getMiddleName());
        Log.i(TAG, status + "'s last name: " + profile.getLastName());
        Log.i(TAG, status + "'s uri: " + profile.getLinkUri());
    }

    private void getAccessTokenInfo(String status, AccessToken token) {
        Log.i(TAG, status + "'s app id: " + token.getApplicationId());
        Log.i(TAG, status + "'s user id: " + token.getUserId());
        Log.i(TAG, status + "'s value: " + token.getToken());
        Log.i(TAG, status + "'s date of expiry: " + token.getExpires().toString());
        Log.i(TAG, status + "'s last refresh: " + token.getLastRefresh());
    }

    private void executeGraphRequest(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken,
                (object, response) -> getData(object));
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, email, location");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void getData(JSONObject object) {
        try {
            URL profile_picture = new URL("https://graph.facebook.com/"
                    + object.getString("id") + "/picture?width=250&height=250");
            Picasso.with(this).load(profile_picture.toString()).into(mAvatar);
            mEmail.setText(object.getString("email"));
            mLocale.setText(object.getJSONObject("location").getString("name"));
        } catch (MalformedURLException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void printKeyHash() {
        try {
            @SuppressLint("PackageManagerGetSignatures")
            PackageInfo info = Objects.requireNonNull(getPackageManager()
                    .getPackageInfo("com.dikiy.workshop",
                            PackageManager.GET_SIGNATURES));
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d(TAG, "KeyHash: " + Base64.encodeToString(md.digest(), Base64.DEFAULT));   // qEIEpO8R9hC9B+dcnfv8k2q3q2o=
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
