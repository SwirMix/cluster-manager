package org.manager.controllers;

import org.cluster.dao.DataStorageConfiguration;
import org.cluster.dto.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.token.TokenObject;
import org.token.impl.JwtService;

@Service
public class JwtValidatorService {
    @Autowired
    private JwtService jwtService;
    @Autowired(required=true)
    private DataStorageConfiguration sqlite;

    public TokenObject checkToken(String token) throws Exception {
        Account account = sqlite.getAccountService().getUniqueDataFor("token",token);
        if(account==null) throw new Exception();
        return jwtService.decryptToken(token);
    }
}
