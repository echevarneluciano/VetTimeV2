package com.example.vettime2.login;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.Callback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;
import com.example.vettime2.MainActivity;
import com.example.vettime2.R;



public class LogInActivity extends AppCompatActivity {
     private Button signInButton;
    private Auth0 auth0;

    public static final String EXTRA_CLEAR_CREDENTIALS = "com.auth0.CLEAR_CREDENTIALS";
    public static final String EXTRA_ACCESS_TOKEN = "com.auth0.ACCESS_TOKEN";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

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
                        Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                        intent.putExtra(EXTRA_ACCESS_TOKEN, credentials.getAccessToken());
                        startActivity(intent);
                        finish();
                    }
                });
    }

}