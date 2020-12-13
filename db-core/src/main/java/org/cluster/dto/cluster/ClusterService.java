package org.cluster.dto.cluster;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.cluster.AbstractDaoService;

import java.sql.SQLException;

public class ClusterService extends AbstractDaoService<Cluster> {

    public ClusterService(String dbUrl) throws SQLException {
        super(dbUrl);
    }

    @Override
    public Dao<Cluster, String> daoInit(ConnectionSource source) throws SQLException {
        return DaoManager.createDao(source, Cluster.class);
    }
}
