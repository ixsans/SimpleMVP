package com.ixsans.simplemvp.login;

import com.ixsans.simplemvp.R;

/**
 * Originally written by Author Name ikhsanudinhakim, 1/6/16
 */
public class LoginPresenter {

    private final LoginContract.View view;
    private final LoginService loginService;

    public LoginPresenter(LoginContract.View view, LoginService loginService) {
        this.view = view;
        this.loginService = loginService;
    }


    public void onLoginClicked() {
        String username = view.getUsername();
        if(username.isEmpty()){
            view.showUsernameEmptyError(R.string.error_username_empty);
            return;
        }

        String password = view.getPassword();
        if(password.isEmpty()){
            view.showPasswordEmptyError(R.string.error_password_empty);
            return;
        }

        if(loginService.authenticate(username, password)){
           view.onLoginSuccess();
        }else{
            view.showWrongCredentialMessage(R.string.message_username_password_incorrect);
        }
    }
}
