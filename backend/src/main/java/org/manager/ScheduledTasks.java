package org.manager;

import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.stmt.UpdateBuilder;
import org.cluster.dao.DataStorageConfiguration;
import org.cluster.dto.cluster.Cluster;
import org.cluster.dto.cluster.ClusterService;
import org.cluster.dto.host.HostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Component
@EnableScheduling
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private DataStorageConfiguration dataStorageConfiguration;

    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        ClusterService service = dataStorageConfiguration.getClusterService();
        System.out.println("The time is now " + dateFormat.format(new Date()));
        try {
            ArrayList<Cluster> clusters = (ArrayList<Cluster>) service.getAll();
            for (Cluster cluster : clusters) {
                if (cluster.longDestroy() < System.currentTimeMillis()){
                    destroyProcedure(cluster.getClusterName());
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void destroyProcedure(String clusterName) throws SQLException {
        HostsService hostsService = dataStorageConfiguration.getHostsService();
        ClusterService clusterService = dataStorageConfiguration.getClusterService();

        UpdateBuilder queryBuilder = hostsService.getDao().updateBuilder();
        queryBuilder.updateColumnValue("used", "free").where().eq("used",clusterName);

        PreparedUpdate query = queryBuilder.prepare();
        hostsService.getDao().update(query);

        clusterService.deleteByUniqueFiled("clusterName",clusterName);
        System.out.println("the cluster with name: " + clusterName +
                " was destroyed");
    }
}