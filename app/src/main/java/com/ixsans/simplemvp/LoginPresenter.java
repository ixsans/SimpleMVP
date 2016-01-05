package com.ixsans.simplemvp;

/**
 * Originally written by Author Name ikhsanudinhakim, 1/4/16
 */
public class LoginPresenter {

    private LoginView mView;
    private LoginService mService;

    public LoginPresenter(LoginView mView, LoginService mService){
        this.mView = mView;
        this.mService = mService;
    }


    public void onLoginClicked() {
        String username = mView.getUsername();
        if(username.isEmpty()){
            mView.showUsernameError(R.string.error_field_required);
        }
    }
}
