package org.token;

import java.util.Date;

public class TokenObject {
    private String login;
    private Date createDate;
    private String support;

    public TokenObject(String id, String lol){
        this.createDate = new Date();
        this.support = lol;
        this.login = id;
    }

    public TokenObject(String id, String lol, Date date){
        this.createDate = date;
        this.support = lol;
        this.login = id;
    }

    public TokenObject(){

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }
}
