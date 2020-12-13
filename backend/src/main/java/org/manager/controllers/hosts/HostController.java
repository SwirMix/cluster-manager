package org.manager.controllers.hosts;

import org.cluster.dao.DataStorageConfiguration;
import org.cluster.dao.pagination.Pagination;
import org.cluster.dto.host.Host;
import org.cluster.dto.host.HostsService;
import org.manager.Utils;
import org.manager.controllers.JwtValidatorService;
import org.manager.controllers.info.EndPointInfo;
import org.manager.controllers.info.RestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.token.TokenObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HostController {

    @Autowired(required=true)
    private DataStorageConfiguration sqlite;
    @Autowired
    private JwtValidatorService jwtService;
    private final int pageSize = 20;
    private final String CONSTRAIN_FAIL = "UNIQUE constraint failed";

    @EndPointInfo(
            name = "Запрос на полный список машин. Реквест предполагающий пагинацию.",
            endPoint = "GET /api/v1/hosts?page=1",
            secureType = RestInfo.Security.OPEN,
            requestType = RestInfo.RequestType.GET,
            requestInfo = "",
            responseInfo = "Реквест имеет параметр page. Механизм пагиназии на стороне сервера.",
            description = "Постранично возвращает список серверов."
    )
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/v1/hosts")
    public ResponseEntity<Map<String, Object>> getHosts(
            @RequestParam int page
    )throws SQLException {
        try {

            HostsService hostsService = sqlite.getHostsService();
            Pagination<Host> pagination = hostsService.getPageData(page, pageSize);

            Map<String, Object> response = new HashMap<>();
            response.put("hosts", pagination.getData());
            response.put("currentPage", pagination.getCurrent());
            response.put("totalPages", pagination.getTotalPages());
            response.put("totalItems",pagination.getTotalItems());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @EndPointInfo(
            name = "Запрос на полный список машин.",
            endPoint = "GET /api/v1/hosts/all",
            secureType = RestInfo.Security.OPEN,
            requestType = RestInfo.RequestType.GET,
            requestInfo = "",
            responseInfo = "Полный список машин в виде json",
            description = "Выгружает абсолютно весь список машин."
    )
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/v1/hosts/all")
    public ResponseEntity<Map<String, Object>> getHosts(
    )throws SQLException {
        try {

            HostsService hostsService = sqlite.getHostsService();
            ArrayList<Host> pagination = (ArrayList<Host>) hostsService.getAll();

            Map<String, Object> response = new HashMap<>();
            response.put("hosts", pagination);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @EndPointInfo(
            name = "Запрос на полный список машин.",
            endPoint = "POST /api/v1/hosts/add",
            secureType = RestInfo.Security.SECURE,
            requestType = RestInfo.RequestType.POST,
            requestInfo = "header: token",
            responseInfo = "{\n" +
                    "\t\"host\":\"192.168.33.101\",\n" +
                    "\t\"dnsName\":\"grid425\",\n" +
                    "\t\"ssh_user\":\"pprbusr\",\n" +
                    "\t\"ssh_pass\":\"pprbusr\",\n" +
                    "\t\"description\":\"simple node\"\n" +
                    "}",
            description = "Создание сущности сервера в БД."
    )
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/api/v1/hosts/add")
    public ResponseEntity<Map<String, Object>> addNewHost(@RequestBody HostRq host, @RequestHeader String token){
        TokenObject tokenObject;
        try {
            tokenObject = jwtService.checkToken(token);
        } catch (Exception e){
            Map<String, Object> response = new HashMap<>();
            response.put("msg", "invalid token");
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }

        if (Utils.firstLineValidate(host)){
            HostsService hostsService = sqlite.getHostsService();

            Host newHost = new Host();
            newHost.setCreator(tokenObject.getLogin());
            newHost.setHost(host.getHost());
            newHost.setSsh_user(host.getSsh_user());
            newHost.setSsh_pass(host.getSsh_pass());
            newHost.setUsed("free");
            newHost.setDnsName(host.getDnsName());
            newHost.setDescription(host.getDescription());

            try {
                hostsService.save(newHost);
                Map<String, Object> response = new HashMap<>();
                response.put("msg", "success");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                if (throwables.getMessage().contains(CONSTRAIN_FAIL)){
                    Map<String, Object> response = new HashMap<>();
                    response.put("msg", "not unique data. Host or dns already exist");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                } else {
                    Map<String, Object> response = new HashMap<>();
                    response.put("msg", "internal error: " + throwables.getMessage());
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("msg", "null or empty host/dns");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @EndPointInfo(
            name = "Запрос на полный список машин.",
            endPoint = "DELETE /api/v1/hosts/{id}",
            secureType = RestInfo.Security.SECURE,
            requestType = RestInfo.RequestType.DELETE,
            requestInfo = "header: token, id хоста который хотим удалить",
            responseInfo = "",
            description = "Удаление сервера из БД."
    )
    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/api/v1/hosts/{id}")
    public ResponseEntity<Map<String, Object>> deleteHost(@PathVariable String id, @RequestHeader String token){
        TokenObject tokenObject;
        try {
            tokenObject = jwtService.checkToken(token);
        } catch (Exception e){
            Map<String, Object> response = new HashMap<>();
            response.put("msg", "invalid token");
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
        HostsService hostsService = sqlite.getHostsService();
        try {
            hostsService.deleteById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("msg", "host with current id was deleted");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Map<String, Object> response = new HashMap<>();
            response.put("msg", "Internal error: " + throwables.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @EndPointInfo(
            name = "Запрос на полный список машин.",
            endPoint = "GET /api/v1/hosts/{id}",
            secureType = RestInfo.Security.OPEN,
            requestType = RestInfo.RequestType.GET,
            requestInfo = "",
            responseInfo = "подробная информация о сущности с заданным id",
            description = "Запрос подробностей о id"
    )
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/v1/hosts/{id}")
    public Host getHost(@PathVariable String id) throws SQLException {
        HostsService hostsService = sqlite.getHostsService();
        return hostsService.getUniqueDataFor("id", id);
    }

    @EndPointInfo(
            name = "Запрос на полный список машин.",
            endPoint = "POST /api/v1/hosts/{id}",
            secureType = RestInfo.Security.SECURE,
            requestType = RestInfo.RequestType.POST,
            requestInfo = "{\n" +
                    "\t\"host\":\"192.168.33.101\",\n" +
                    "\t\"dnsName\":\"grid425\",\n" +
                    "\t\"ssh_user\":\"pprbusr\",\n" +
                    "\t\"ssh_pass\":\"pprbusr\",\n" +
                    "\t\"description\":\"simple node\"\n" +
                    "}",
            responseInfo = "обновление сущности с заданным id",
            description = "обновление данных в хранилище"
    )
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/api/v1/hosts/")
    public ResponseEntity<Map<String, Object>> updateData(@RequestBody Host host, @RequestHeader String token){
        TokenObject tokenObject;
        try {
            tokenObject = jwtService.checkToken(token);
        } catch (Exception e){
            Map<String, Object> response = new HashMap<>();
            response.put("msg", "invalid token");
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }

        HostsService hostsService = sqlite.getHostsService();
        try {
            hostsService.save(host);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Map<String, Object> response = new HashMap<>();
            response.put("msg", "host or dnsName already exist: " + throwables.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("msg", "success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @EndPointInfo(
            name = "Запрос на полный список машин.",
            endPoint = "GET /api/v1/hosts/used/{name}",
            secureType = RestInfo.Security.OPEN,
            requestType = RestInfo.RequestType.GET,
            requestInfo = "name - имя кластера",
            responseInfo = "обновление сущности с заданным id",
            description = "получаем список машин которые закреплены за группой с name из url"
    )
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/v1/hosts/used/{name}")
    public ResponseEntity<Map<String, Object>> getHostsByClusterType(@PathVariable String name)throws SQLException {
        try {

            HostsService hostsService = sqlite.getHostsService();
            ArrayList<Host> hosts = (ArrayList<Host>) hostsService.getDataFor("used", name);

            Map<String, Object> response = new HashMap<>();
            response.put("hosts", hosts);
            response.put("totalItems",hosts.size());
            response.put("clusterName", name);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
