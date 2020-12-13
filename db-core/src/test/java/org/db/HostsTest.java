package org.db;

import org.cluster.dto.host.Host;
import org.cluster.dto.host.HostsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class HostsTest {
    String dbUrl = "jdbc:sqlite:/home/ponchick/Рабочий стол/hobby/clusterManager/db-core/db/cluster-manager.db";
    HostsService hostsService;

    @Before
    public void beforeTest() throws SQLException {
        hostsService = new HostsService(dbUrl);
    }

    @Test
    public void saveTest() throws SQLException {
        String host_ip = "192.168.0.1";
        Host host = new Host();
        host.setHost(host_ip);
        host.setDnsName(host_ip);
        hostsService.save(host);

        Host internalHost = hostsService.getUniqueDataFor("host", host_ip);
        hostsService.delete(internalHost);
    }

    @Test
    public void loadData() throws SQLException {
        String host_ip = "test-";
        int size = 300;

        for (int i = 0; i < size; i++) {
            Host host = new Host();
            host.setHost(host_ip+i);
            host.setDnsName(host_ip+i);
            hostsService.save(host);
        }

    }

    @Test
    public void updateTest() throws SQLException {
        String host_ip = "192.168.0.1";
        Host host = new Host();
        host.setHost(host_ip);
        host.setDnsName(host_ip);
        hostsService.save(host);

        String newDns = "test_host";
        Host internalHost = hostsService.getUniqueDataFor("host", host_ip);
        internalHost.setDnsName(newDns);
        hostsService.save(internalHost);

        Host checkHost = hostsService.getUniqueDataFor("host", host_ip);
        Assert.assertEquals(newDns, checkHost.getDnsName());
        hostsService.delete(checkHost);
    }
}
