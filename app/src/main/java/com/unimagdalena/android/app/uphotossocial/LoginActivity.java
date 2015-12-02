package com.unimagdalena.android.app.uphotossocial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private LoginButton facebookLoginButton;
    private ProgressBar progressBar;
    private TextView tvAppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        facebookLoginButton = (LoginButton) findViewById(R.id.login_button);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        tvAppName = (TextView) findViewById(R.id.tv_app_name);

        if (isUserLogged()) {
            Constants.showAnimationProgress(progressBar, facebookLoginButton, true);

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(mainIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            }, 2500);
        }

        callbackManager = CallbackManager.Factory.create();

        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                //tvAppName.setText(loginResult.getAccessToken().getUserId());
                //Toast.makeText(getApplicationContext(), "Entry", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), R.string.user_cancel_request, Toast.LENGTH_SHORT).show();
                //tvAppName.setText("User cancel request.");
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), R.string.facebook_error, Toast.LENGTH_SHORT).show();
                //tvAppName.setText("Facebook error.");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public boolean isUserLogged() {
        return AccessToken.getCurrentAccessToken() != null;
    }
}
