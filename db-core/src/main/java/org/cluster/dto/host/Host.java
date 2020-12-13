package org.cluster.dto.host;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.cluster.dao.internal.HostRq;

import java.util.Date;

@DatabaseTable(tableName = "Host")
public class Host {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField
    private String host;
    @DatabaseField
    private String dnsName;
    @DatabaseField
    private String ssh_user;
    @DatabaseField
    private String ssh_pass;
    @DatabaseField
    private String description;
    @DatabaseField
    private long createDate;
    @DatabaseField
    private String creator;
    @DatabaseField
    private String used = "free";

    public Host(){
        this.createDate = new Date().getTime();
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public Date getCreateDate() {
        return new Date(createDate);
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getHost() {
        return host;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
