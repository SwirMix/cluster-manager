package org.cluster.dto.host;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.cluster.AbstractDaoService;

import java.sql.SQLException;

public class HostsService extends AbstractDaoService<Host> {
    public HostsService(String dbUrl) throws SQLException {
        super(dbUrl);
    }

    @Override
    public Dao<Host, String> daoInit(ConnectionSource source) throws SQLException {
        return DaoManager.createDao(source, Host.class);
    }
}
