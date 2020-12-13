package org.cluster.dto.account;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.cluster.AbstractDaoService;

import java.sql.SQLException;

public class AccountService extends AbstractDaoService<Account> {

    public AccountService(String dbUrl) throws SQLException {
        super(dbUrl);
    }

    @Override
    public Dao<Account, String> daoInit(ConnectionSource source) throws SQLException {
        return DaoManager.createDao(source, Account.class);
    }
}