package org.cluster.dao.api;

import org.cluster.dao.Result;
import org.cluster.dto.account.Account;

public interface AccountService {
    /**
     * @param login - логин аккаунта
     * @param pass - пароль аккаунта
     * @return
     */
    public Result createAccount(String login, String pass);
    /**
     * @param login - логин аккаунта
     * @param pass - пароль аккаунта
     * @return возвращает сгенерированный токен для api
     */
    public Result createToken(String login, String pass);

    /**
     * @param accountId - id аккаунта из таблицы Account
     * @return возвращает токен закрепленный за данным аккаунтом
     */
    public Result getToken(long accountId);

    /**
     * @param token - токен
     * @return возвращает аккаунт за которым закреплен данный токен
     */
    public Account getAccountByToken(String token);

    /**
     * @param login - логин аккаунта
     * @param pass - пароль аккаунта
     * @return в случае если данным авторизации соответствует введенная пара, возвращается True. Во всех остальных случаях False.
     */
    public boolean auth(String login, String pass);

    /**
     * @param account
     * @return статус операции
     */
    public Result deleteAccount(Account account);

}
