package com.ixsans.simplemvp.login;

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

import com.ixsans.simplemvp.R;
import com.ixsans.simplemvp.home.HomeActivity;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.View{


    // UI references.
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private Button mSignInButton;

    private LoginPresenter mPresenter;

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

        mPresenter = new LoginPresenter(this, new LoginService());
    }
    

    OnClickListener onLoginClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            attemptLogin();
        }
    };

    private void attemptLogin() {
        mPresenter.onLoginClicked();
    }


    @Override
    public String getUsername() {
        return mUsernameView.getText().toString();
    }

    @Override
    public void showUsernameEmptyError(int resId) {
        mUsernameView.setError(getString(resId));
    }

    @Override
    public String getPassword() {
        return mPasswordView.getText().toString();
    }

    @Override
    public void showPasswordEmptyError(int resId) {
        mPasswordView.setError(getString(resId));
    }

    @Override
    public void showWrongCredentialMessage(int resId) {
        Toast.makeText(getApplicationContext(), getString(resId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginSuccess() {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        finish();
    }
}

