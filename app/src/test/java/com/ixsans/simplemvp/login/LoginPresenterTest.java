package com.ixsans.simplemvp.login;

import com.ixsans.simplemvp.R;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Originally written by Author Name ikhsanudinhakim, 1/6/16
 */
public class LoginPresenterTest {

    private LoginPresenter mPresenter;
    private LoginContract.View view;
    private LoginService loginService;

    /**
     * Mockito docs: http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html
     * Tutorial: https://dzone.com/refcardz/mockito
     */

    @Before
    public void setUp() throws Exception {
        view = Mockito.mock(LoginContract.View.class);
        loginService = Mockito.mock(LoginService.class);

        mPresenter = new LoginPresenter(view, loginService);

    }

    @Test
    public void shouldShowErrorWhenUsernameEmpty() throws Exception {
        Mockito.when(view.getUsername()).thenReturn(""); //1.username field is empty
        mPresenter.onLoginClicked();    //2. user clicked login button

        Mockito.verify(view).showUsernameEmptyError(R.string.error_username_empty); //3. verify the correct behaviour!
    }

    @Test
    public void shouldShowErrorWhenPasswordEmpty() throws Exception {
        Mockito.when(view.getUsername()).thenReturn("ixsans");
        Mockito.when(view.getPassword()).thenReturn("");
        mPresenter.onLoginClicked();

        Mockito.verify(view).showPasswordEmptyError(R.string.error_password_empty);

    }

    @Test
    public void shouldShowErrorWhenAuthenticateWrongUsernameAndPassword() throws Exception {
        Mockito.when(view.getUsername()).thenReturn("ixsan");
        Mockito.when(view.getPassword()).thenReturn("test");
        Mockito.when(loginService.authenticate("ixsans","qwerty")).thenReturn(false); //parameter: "ixsan" and "qwerty" although it's true,it'll be ignored, since it just mock value, so

        mPresenter.onLoginClicked();

        Mockito.verify(view).showWrongCredentialMessage(R.string.message_username_password_incorrect);
    }

    @Test
    public void shouldNavigateToHomeWhenAuthenticateCorrectUsernameAndPassword() throws Exception {
        Mockito.when(view.getUsername()).thenReturn("ixsans"); //1. user fill username
        Mockito.when(view.getPassword()).thenReturn("qwerty"); //2. user fill password

        /**
         * call this command before mPresenter.onLoginClicked();
         * because we have to mock behaviour before onLoginClicked (before reach state that will be verified, in this case is loginSuccess)
         */
        Mockito.when(loginService.authenticate("ixsans","qwerty")).thenReturn(true); //3. system authenticate credentials

        mPresenter.onLoginClicked();

        Mockito.verify(view).onLoginSuccess();

    }
}