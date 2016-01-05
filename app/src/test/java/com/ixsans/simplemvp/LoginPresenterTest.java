package com.ixsans.simplemvp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Originally written by Author Name ikhsanudinhakim, 1/4/16
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Mock
    private LoginPresenter presenter;
    @Mock
    private LoginView view;

    private LoginService service;

    @Before
    public void setUp() throws Exception {
        presenter = new LoginPresenter(view, service);

    }

    @Test
    public void shouldShowErrorWhenUsernameEmpty() throws Exception {
        when(view.getUsername()).thenReturn("");
        presenter.onLoginClicked();

        verify(view).showUsernameError(R.string.error_field_required);

    }
}