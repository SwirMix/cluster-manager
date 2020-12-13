package org.manager.controllers.account.auth;

import org.cluster.Utils;

public class AuthRq {
    private String login;
    private String password;

    public AuthRq(){

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Utils.md5(password);
    }
}
