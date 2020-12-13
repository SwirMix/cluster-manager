package org.cluster.dao;

import org.cluster.dto.account.AccountService;
import org.cluster.dto.cluster.ClusterService;
import org.cluster.dto.host.HostsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class DataStorageConfiguration {

    @Value("${db.path}")
    protected String dbUrl;
    protected AccountService accountService;
    protected HostsService hostsService;
    protected ClusterService clusterService;

    public DataStorageConfiguration() throws SQLException {
    }

    public void init() throws SQLException {
        this.hostsService = new HostsService(dbUrl);
        this.accountService = new AccountService(dbUrl);
        this.clusterService = new ClusterService(dbUrl);
    }

    public DataStorageConfiguration(String dbUrl) throws SQLException {
        this.dbUrl = dbUrl;
        this.hostsService = new HostsService(dbUrl);
        this.accountService = new AccountService(dbUrl);
        this.clusterService = new ClusterService(dbUrl);
    }

    public ClusterService getClusterService() {
        if (clusterService==null) {
            try {
                clusterService = new ClusterService(dbUrl);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return clusterService;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public AccountService getAccountService(){
        if (accountService==null) {
            try {
                accountService = new AccountService(dbUrl);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public HostsService getHostsService(){
        if (hostsService==null) {
            try {
                hostsService = new HostsService(dbUrl);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return hostsService;
    }

    public void setHostsService(HostsService hostsService) {
        this.hostsService = hostsService;
    }
}
