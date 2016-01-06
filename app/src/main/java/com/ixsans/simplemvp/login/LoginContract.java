package com.ixsans.simplemvp.login;

/**
 * Originally written by Author Name ikhsanudinhakim, 1/6/16
 */
public class LoginContract {

    public interface View{

        String getUsername();

        void showUsernameEmptyError(int resId);

        String getPassword();

        void showPasswordEmptyError(int resId);

        void showWrongCredentialMessage(int resId);

        void onLoginSuccess();
    }
}
