package org.manager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Configuration {
    @Value("$(app.jwtSecret)")
    private String jwtSecret;
    @Value("$(db.path)")
    private String sqlite;

    public String getSqlite() {
        return sqlite;
    }

    public void setSqlite(String sqlite) {
        this.sqlite = sqlite;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }
}
