package org.manager.controllers.hosts;

public class HostRq {
    private String host;
    private String dnsName;
    private String ssh_user;
    private String ssh_pass;
    private String description;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDnsName() {
        return dnsName;
    }

    public void setDnsName(String dnsName) {
        this.dnsName = dnsName;
    }

    public String getSsh_user() {
        return ssh_user;
    }

    public void setSsh_user(String ssh_user) {
        this.ssh_user = ssh_user;
    }

    public String getSsh_pass() {
        return ssh_pass;
    }

    public void setSsh_pass(String ssh_pass) {
        this.ssh_pass = ssh_pass;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
