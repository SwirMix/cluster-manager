package org.db;

import org.cluster.Utils;
import org.cluster.dto.account.AccountService;
import org.cluster.dto.account.Account;
import org.cluster.dao.pagination.Pagination;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;
import java.util.UUID;

public class AccountTests {
    String dbUrl = "jdbc:sqlite:/home/ponchick/Рабочий стол/hobby/clusterManager/db-core/db/cluster-manager.db";
    AccountService accountRepository;

    @Before
    public void beforeTest() throws SQLException {
        accountRepository = new AccountService(dbUrl);
    }

    @Test
    public void getCount() throws SQLException {
        long count = accountRepository.getCount();
        System.out.println(count);
        accountRepository.getPageCount(10);
    }
    @Test
    public void dataLoader() throws SQLException {
        String sample = "login-";
        long count = 10000;

        for (int i = 0; i < count; i++) {
            Account account = new Account();
            account.setToken(sample + i);
            account.setLogin(sample + i);
            account.setPassword(sample + i);
            accountRepository.save(account);
        }
    }

    @Test
    public void pagination() throws SQLException {
        Pagination accounts = accountRepository.getPageData(4, 10);
        System.out.println("fin");
    }

    @Test
    public void save() throws Exception {
        Account account = new Account();
        String login = "test-save";
        account.setToken(login);
        account.setLogin(login);
        account.setPassword(login);
        accountRepository.save(account);
        Thread.sleep(1000);
        account = accountRepository.getUniqueDataFor("login", login);
        if (account==null) throw new Exception();
        accountRepository.delete(account);
    }

    @Test
    public void updateTest() throws Exception {
        Account account = new Account();
        String login = UUID.randomUUID().toString();
        account.setToken(login);
        account.setLogin(login);
        account.setPassword(login);
        accountRepository.save(account);
        account = accountRepository.getUniqueDataFor("login", login);

        String password = "123456";
        account.setPassword(password);
        password = Utils.md5(password);
        accountRepository.save(account);
        Account account1 = accountRepository.getUniqueDataFor("login", login);
        Assert.assertEquals(account1.getPassword(), password);
        accountRepository.delete(account1);
    }

}
