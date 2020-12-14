package org.manager.controllers.account;

import org.cluster.dao.DataStorageConfiguration;
import org.cluster.dto.account.Account;
import org.cluster.dto.account.AccountService;
import org.manager.Utils;
import org.manager.controllers.JwtValidatorService;
import org.manager.controllers.account.auth.AuthRq;
import org.manager.controllers.info.EndPointInfo;
import org.manager.controllers.info.RestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.token.TokenObject;
import org.token.impl.JwtService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class AuthController {
    @Autowired(required=true)
    private DataStorageConfiguration sqlite;
    @Autowired
    private JwtValidatorService jwtValidatorService;
    @Autowired
    private JwtService jwtService;

    @EndPointInfo(
            name = "token generation api",
            endPoint = "POST /api/v1/generate",
            secureType = RestInfo.Security.OPEN,
            requestType = RestInfo.RequestType.POST,
            requestInfo = "{\"login\":\"login\",\"password\":\"password\"}",
            responseInfo = "{\"token\":\"token\"}",
            description = "запрос на генериацию токена. Токен сохраняется в БД и используется для аутентификации в дальнейшем."
    )
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/api/v1/generate")
    public ResponseEntity<Map<String, Object>> auth(@RequestBody AuthRq authRq){
        if (Utils.firstLineValidate(authRq)){
            try {
                AccountService accountService = sqlite.getAccountService();
                Account account = sqlite.getAccountService().getUniqueDataFor("login", authRq.getLogin());
                if (account==null){
                    Map<String, Object> response = new HashMap<>();
                    response.put("msg", "Login: " + authRq.getLogin() + " not exist!");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                } else {
                    if (account.getPassword().equals(authRq.getPassword())){
                        String token = generateToken(account);
                        account.setToken(token);
                        accountService.save(account);
                        Map<String, Object> response = new HashMap<>();
                        response.put("token", token);
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    } else {
                        Map<String, Object> response = new HashMap<>();
                        response.put("msg", "invalid password");
                        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                Map<String, Object> response = new HashMap<>();
                response.put("msg", "internal error in AccountService. SQL");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("msg", "empty auth data");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @EndPointInfo(
            name = "Авторизация в системе",
            endPoint = "POST /api/v1/auth",
            secureType = RestInfo.Security.OPEN,
            requestType = RestInfo.RequestType.POST,
            requestInfo = "{\"login\":\"login\",\"password\":\"password\"}",
            responseInfo = "{\"token\":\"token\"}",
            description = "Авторизация в системе. В качестве ответа возвращается токен, который закреплен за пользователем. " +
                    "Если токен не создан, он создается и сохраняется в БД."
    )
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/api/v1/auth")
    public ResponseEntity<Map<String, Object>> geToken(@RequestBody AuthRq authRq){
        if (Utils.firstLineValidate(authRq)){
            try {
                AccountService accountService = sqlite.getAccountService();
                Account account = sqlite.getAccountService().getUniqueDataFor("login", authRq.getLogin());
                if (account==null){
                    Map<String, Object> response = new HashMap<>();
                    response.put("msg", "Login: " + authRq.getLogin() + " not exist!");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                } else {
                    if (account.getPassword().equals(authRq.getPassword())){
                        if (account.getToken().equals(null)| account.getToken().equals("")){
                            String token = generateToken(account);
                            account.setToken(token);
                            accountService.save(account);
                            Map<String, Object> response = new HashMap<>();
                            response.put("token", token);
                            return new ResponseEntity<>(response, HttpStatus.OK);
                        } else {

                            Map<String, Object> response = new HashMap<>();
                            response.put("token", account.getToken());
                            return new ResponseEntity<>(response, HttpStatus.OK);
                        }

                    } else {
                        Map<String, Object> response = new HashMap<>();
                        response.put("msg", "invalid password");
                        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                Map<String, Object> response = new HashMap<>();
                response.put("msg", "internal error in AccountService. SQL");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("msg", "empty auth data");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    private String generateToken(Account account){
        TokenObject tokenObject = new TokenObject();
        tokenObject.setLogin(account.getLogin());
        tokenObject.setSupport(UUID.randomUUID().toString());
        return jwtService.createToken(tokenObject);
    }

    @EndPointInfo(
            name = "Создание нового аккаунта",
            endPoint = "POST /api/vi/account/create",
            secureType = RestInfo.Security.SECURE,
            requestType = RestInfo.RequestType.POST,
            requestInfo = "{\"login\":\"login\",\"password\":\"password\"}",
            responseInfo = "",
            description = "Создание нового аккаунта. К POST запросу необходимо прикладывать токен учетной записи. Защищенное API"
    )
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/api/v1/account/create")
    public ResponseEntity<Map<String, Object>> addNewAccount(@RequestBody AuthRq authRq, @RequestHeader String token){
        TokenObject tokenObject;
        if(Utils.firstLineValidate(authRq)){
            try {
                tokenObject = jwtValidatorService.checkToken(token);
            } catch (Exception e){
                Map<String, Object> response = new HashMap<>();
                response.put("msg", "invalid token");
                return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
            }
            Account account = new Account();
            account.setPassword(authRq.getPassword());
            account.setLogin(authRq.getLogin());
            try {
                sqlite.getAccountService().save(account);

                Map<String, Object> response = new HashMap<>();
                response.put("msg", "new account was created");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (SQLException throwables) {
                throwables.printStackTrace();

                Map<String, Object> response = new HashMap<>();
                response.put("msg", "fail create new account. Login exist?");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("msg", "empty data");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
