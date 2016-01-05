package com.ixsans.simplemvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ixsans.simplemvp.home.HomeActivity;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{


    // UI references.
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private Button mSignInButton;

    LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mUsernameView = (AutoCompleteTextView) findViewById(R.id.username);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mSignInButton.setOnClickListener(onLoginClickListener);

        loginService = new LoginService();
    }
    

    OnClickListener onLoginClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            attemptLogin();
        }
    };

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        String username = mUsernameView.getText().toString();
        if(username.isEmpty()){
            mUsernameView.setError(getString(R.string.error_username_empty));
            return;
        }

        String password = mPasswordView.getText().toString();
        if(password.isEmpty()){
            mPasswordView.setError(getString(R.string.error_password_empty));
            return;
        }
        
        if(loginService.authenticate(username, password)){
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(getApplicationContext(), R.string.message_username_password_incorrect, Toast.LENGTH_SHORT).show();
        }
    }


}

