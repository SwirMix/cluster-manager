package org.cluster.dao.api;

import org.cluster.dao.DataStorageConfiguration;
import org.cluster.dao.Result;
import org.cluster.dao.Status;
import org.cluster.dto.account.Account;

import java.sql.SQLException;
import java.util.UUID;

public class InternalAccountApi implements AccountService{

    private DataStorageConfiguration dataStorageConfiguration;

    public InternalAccountApi(){

    }

    public InternalAccountApi(DataStorageConfiguration dataStorageConfiguration) {
        this.dataStorageConfiguration = dataStorageConfiguration;
    }

    public DataStorageConfiguration getDataStorageConfiguration() {
        return dataStorageConfiguration;
    }

    public void setDataStorageConfiguration(DataStorageConfiguration dataStorageConfiguration) {
        this.dataStorageConfiguration = dataStorageConfiguration;
    }

    @Override
    public Result createAccount(String login, String pass) {
        org.cluster.dto.account.AccountService accountService = dataStorageConfiguration.getAccountService();
        Account account = new Account();
        account.setLogin(login);
        account.setPassword(pass);
        try {
            accountService.save(account);
            Account result = accountService.getUniqueDataFor("login", account.getLogin());
            return new Result(Status.SUCCESS, Long.toString(result.getId()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result(Status.ERROR, throwables.getMessage());
        }
    }

    @Override
    public Result createToken(String login, String pass) {
        org.cluster.dto.account.AccountService accountService = dataStorageConfiguration.getAccountService();
        try {
            Account account = accountService.getUniqueDataFor("login", login);
            if (account==null){
                return new Result(Status.ERROR, "аккаунта с login - " + login + " не существует");
            }
            String token = UUID.randomUUID().toString();
            if (account.getPassword().equals(pass)) {
                account.setToken(token);
                accountService.save(account);
                return new Result(Status.SUCCESS, token);
            } else {
                return new Result(Status.ERROR, "не верный пароль от аккаунта");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result(Status.ERROR, "внутренняя ошибка. Попробуйте еще раз.");
        }
    }

    @Override
    public Result getToken(long accountId) {
        org.cluster.dto.account.AccountService accountService = dataStorageConfiguration.getAccountService();
        try {
            Account account = accountService.getUniqueDataFor("id", Long.toString(accountId));
            if (account==null){
                return new Result(Status.ERROR, "аккаунт с данным id не найден");
            }
            return new Result(Status.SUCCESS, account.getToken());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result(Status.ERROR, "внутренняя ошибка запроса. Попробуйте еще раз.");
        }
    }

    @Override
    public Account getAccountByToken(String token) {
        org.cluster.dto.account.AccountService accountService = dataStorageConfiguration.getAccountService();
        try {
            Account account = accountService.getUniqueDataFor("token", token);
            return account;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean auth(String login, String pass) {
        org.cluster.dto.account.AccountService accountService = dataStorageConfiguration.getAccountService();
        try {
            Account account = accountService.getUniqueDataFor("login", login);
            if (account==null){
                return false;
            }
            String token = UUID.randomUUID().toString();
            if (account.getPassword().equals(pass)) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public Result deleteAccount(Account account) {
        org.cluster.dto.account.AccountService accountService = dataStorageConfiguration.getAccountService();
        try {
            accountService.delete(account);
            return new Result(Status.SUCCESS, "Аккаунт был удален.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Result(Status.ERROR, "Внутренняя ошибка. Повторите операцию.");
        }
    }
}
