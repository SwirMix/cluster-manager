package org.db;

import org.cluster.dao.DataStorageConfiguration;
import org.cluster.dao.Result;
import org.cluster.dao.api.InternalHostsApi;
import org.cluster.dao.internal.HostRq;
import org.cluster.dto.host.Host;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class InternalHostsApiTest {
    InternalHostsApi hostsApi;
    String dbUrl = "jdbc:sqlite:/home/ponchick/Рабочий стол/hobby/clusterManager/db-core/db/cluster-manager.db";
    String login = "test";

    @BeforeTest
    public void prepare() throws SQLException {
        DataStorageConfiguration dataStorageConfiguration = new DataStorageConfiguration(dbUrl);
        this.hostsApi = new InternalHostsApi(dataStorageConfiguration);
    }

    @Test
    public void createHost(){
        String host = "192.168.0.";
        int size = 26;
        for (int i = 0; i < size; i++) {
            HostRq hostRq = new HostRq();
            hostRq.setHostIp(host + i);
            hostRq.setDnsName(host + i);
            hostsApi.saveHost(hostRq, login);
        }
    }
    @Test
    public void createCluster() throws Exception {
        String job = UUID.randomUUID().toString();
        long size = 100;
        ArrayList<Host> hosts = hostsApi.getFreeHosts(100);
        Result result = hostsApi.lockHostsForCluster(hosts, job);
        System.out.println(result.getMessage());
    }
    @Test
    public void unlockCluster(){
        String job = "ducker";

        ArrayList<Host> hosts = hostsApi.getHostsByJob(job);
        Result result = hostsApi.unlockHosts(hosts);
        System.out.println("fin");
    }
}
