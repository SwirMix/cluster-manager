package org.cluster.dto.account;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.cluster.Utils;
import java.io.Serializable;

@DatabaseTable(tableName = "Account")
public class Account implements Serializable {

    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField
    private String login;
    @DatabaseField
    private String password;
    @DatabaseField
    private String token;

    public Account(){

    }

    public Account(String login, String password) {
        this.login = login;
        this.password = Utils.md5(password);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
