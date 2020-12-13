package org.cluster.dao.internal;

public class HostRq {
    private String hostIp;
    private String dnsName;
    private String description;
    private String ssh_user;
    private String ssh_pass;

    public HostRq(){

    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getDnsName() {
        return dnsName;
    }

    public void setDnsName(String dnsName) {
        this.dnsName = dnsName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
