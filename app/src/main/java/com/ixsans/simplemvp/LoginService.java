package com.ixsans.simplemvp;

/**
 *
 * Originally written by Author Name ikhsanudinhakim, 1/4/16
 */
public class LoginService {

    private final String USERNAME = "ixsans";
    private final String PASSWORD = "qwerty";

    public boolean authenticate(String username, String password){
        return (username.equalsIgnoreCase(USERNAME) && password.equalsIgnoreCase(PASSWORD));
    }
}
