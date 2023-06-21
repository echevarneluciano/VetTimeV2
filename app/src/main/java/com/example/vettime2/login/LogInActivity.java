package com.example.vettime2.login;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.Callback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;
import com.auth0.android.result.UserProfile;
import com.example.vettime2.MainActivity;
import com.example.vettime2.R;



public class LogInActivity extends AppCompatActivity {
    private Button signInButton;
    private Auth0 auth0;
    private String EXTRA_ACCESS_TOKEN = "com.auth0.ACCESS_TOKEN";
    private UserProfile user;
    private LogInViewModel logInViewModel;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public static Boolean permissionGranted = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        permissionGranted = CheckPermission();

        logInViewModel = new ViewModelProvider(this).get(LogInViewModel.class);

        signInButton = findViewById(R.id.login_button);
        auth0 = new Auth0("nfSz9pe3wm7k28X4oTEIxWWwU61G4kCQ",
                "dev-imbas1v3lp0rrnnp.us.auth0.com");

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
        }});
    }


    private void login() {
        WebAuthProvider.login(auth0)
                .withScheme("demo")
                .withScope("openid profile offline_access")
                .withAudience(getString(R.string.audit))
                .start(this, new Callback<Credentials, AuthenticationException>() {

                    @Override
                    public void onFailure(@NonNull final AuthenticationException exception) {
                        Toast.makeText(LogInActivity.this, "Error: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(@Nullable final Credentials credentials) {
                        EXTRA_ACCESS_TOKEN = credentials.getAccessToken();
                        user = credentials.getUser();
                        logInViewModel.login(EXTRA_ACCESS_TOKEN, user);
                        Log.d("TAG", "onSuccess: " + user);
                    }
                });
    }

    public boolean CheckPermission() {
        if (ContextCompat.checkSelfPermission(LogInActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(LogInActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(LogInActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(LogInActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(LogInActivity.this,
                    Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale(LogInActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                new AlertDialog.Builder(LogInActivity.this)
                        .setTitle("Permission")
                        .setMessage("Please accept the permissions")
                        .setPositiveButton("ok", (dialogInterface, i) -> {
                            //Prompt the user once explanation has been shown
                            ActivityCompat.requestPermissions(LogInActivity.this,
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    MY_PERMISSIONS_REQUEST_LOCATION);


                            startActivity(new Intent(LogInActivity
                                    .this, LogInActivity.class));
                            LogInActivity.this.overridePendingTransition(0, 0);
                        })
                        .create()
                        .show();


            } else {
                ActivityCompat.requestPermissions(LogInActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }

            return false;
        } else {

            return true;

        }
    }

}