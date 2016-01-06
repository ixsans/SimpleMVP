package com.ixsans.simplemvp.login;

import android.support.annotation.StringRes;

/**
 * Originally written by Author Name ikhsanudinhakim, 1/5/16
 */
public class LoginContract {

    public interface View{

        String getUsername();


        void showErrorUsernameEmpty(@StringRes int resId);

        String getPassword();

        void showErrorPasswordEmpty(int error_password_empty);

        void loginSuccess();

        void loginFailed();
    }
}
