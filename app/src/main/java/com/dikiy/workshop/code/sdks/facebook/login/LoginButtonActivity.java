package com.dikiy.workshop.code.sdks.facebook.login;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dikiy.workshop.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class LoginButtonActivity extends AppCompatActivity {

    private CallbackManager mCallbackManager;
    private TextView mEmail, mLocale;
    private ProgressDialog mDialog;
    private LoginButton mLoginButton;
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
        setContentView(R.layout.activity_sign_in_fb_btn_login);
        printKeyHash();

        mCallbackManager = CallbackManager.Factory.create();

        mAvatar = findViewById(R.id.iv_facebook_avatar);
        mEmail = findViewById(R.id.tv_facebook_email_field);
        mLocale = findViewById(R.id.tv_facebook_locale_field);
        mLoginButton = findViewById(R.id.btn_facebook_login);

        //   If using in a fragment
//        loginButton.setFragment(this);
        mLoginButton.setPermissions("public_profile", "email", "user_location");
        mLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mDialog = new ProgressDialog(LoginButtonActivity.this);
                mDialog.setMessage("Retrieving data...");
                mDialog.show();

                String accessToken = loginResult.getAccessToken().getToken();

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), (object, response) -> {
                    mDialog.dismiss();
                    Log.d(TAG, "onCompleted: " + object.toString());
                    getData(object);
                });

                //Request Graph API
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, email, location");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {
            }
        });

        // If already login
        if (AccessToken.getCurrentAccessToken() != null) {
            //Just set User ID
            GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                    (object, response) -> getData(object));
            //Request Graph API
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id, email, location");
            request.setParameters(parameters);
            request.executeAsync();
        }
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
