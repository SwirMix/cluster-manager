package org.manager.controllers.cluster;

import com.j256.ormlite.stmt.*;
import org.cluster.dao.DataStorageConfiguration;
import org.cluster.dto.cluster.Cluster;
import org.cluster.dto.cluster.ClusterService;
import org.cluster.dto.host.Host;
import org.cluster.dto.host.HostsService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ClustersController {
    @Autowired(required=true)
    private DataStorageConfiguration sqlite;
    @Autowired
    private JwtValidatorService jwtService;

    @EndPointInfo(
            name = "Запрос на создание кластера",
            endPoint = "GET /api/v1/cluster/create",
            secureType = RestInfo.Security.SECURE,
            requestType = RestInfo.RequestType.GET,
            requestInfo = "{\n" +
                    "\t\"size\":\"1\",\n" +
                    "\t\"name\":\"cluster31\",\n" +
                    "\t\"desc\":\"Тестовый кластер\",\n" +
                    "\t\"hosts\": [],\n" +
                    "\t\"time\": \"1000\",\n" +
                    "\t\"type\":\"DAYS\"\n" +
                    "}",
            responseInfo = "Ответом является список машин.",
            description = "Создание кластера и закрепление машин за данной группой. " +
                    "type: MINUTEST, HOURS, DAYS"
    )
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/v1/cluster/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody ClusterCreateRq clusterCreateRq, @RequestHeader String token) throws SQLException {
        TokenObject tokenObject;
        HostsService hostsService = sqlite.getHostsService();
        ClusterService clusterService = sqlite.getClusterService();
        Cluster cluster = clusterService.getUniqueDataFor("clusterName", clusterCreateRq.getName());
        if (cluster!=null) {
            Map<String, Object> response = new HashMap<>();
            response.put("msg", "cluster with name - " + clusterCreateRq.getName() + " already exist.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            tokenObject = jwtService.checkToken(token);
        } catch (Exception e){
            Map<String, Object> response = new HashMap<>();
            response.put("msg", "invalid token");
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
        ArrayList<String> hosts = clusterCreateRq.getHosts();
        if (hosts.size()!=0){
            QueryBuilder queryBuilder = hostsService.getDao().queryBuilder();
            Where where = queryBuilder.where().eq("host",hosts.get(0));
            for (int i = 1; i < hosts.size(); i++) {
                where = where.or().eq("host", hosts.get(i));
            }
            PreparedQuery query = where.prepare();
            ArrayList<Host> resultHosts = (ArrayList<Host>) hostsService.getDao().query(query);

            for (Host item : resultHosts){
                if (!item.getUsed().equals("free")) {
                    Map<String, Object> response = new HashMap<>();
                    response.put("msg", "host with ip: " + item.getHost() + " used in cluster with name: " + item.getUsed() +
                            " pls try later");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
                item.setUsed(clusterCreateRq.getName());
                hostsService.save(item);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("hosts", resultHosts);
            response.put("clusterName", clusterCreateRq.getName());
            response.put("size",clusterCreateRq.getSize());


            cluster = new Cluster();
            cluster.setClusterName(clusterCreateRq.getName());
            cluster.setCreator(tokenObject.getLogin());
            cluster.setHosts(resultHosts);
            cluster.setDescription(clusterCreateRq.getDesc());

            Date create = cluster.getCreateDate();
            Date endDate = new Date(create.getTime() + time(clusterCreateRq.getTime(), clusterCreateRq.getType()));
            cluster.setDestroyDate(endDate.getTime());
            clusterService.save(cluster);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {

            QueryBuilder queryBuilder = hostsService.getDao().queryBuilder();
            PreparedQuery query = queryBuilder.limit(clusterCreateRq.getSize()).where().eq("used","free").prepare();

            ArrayList<Host> result_cluster = (ArrayList<Host>) hostsService.getDao().query(query);

            if (result_cluster.size() == clusterCreateRq.getSize()){
                for (Host node : result_cluster){
                    node.setUsed(clusterCreateRq.getName());
                    hostsService.save(node);
                }

                clusterService = sqlite.getClusterService();

                cluster = new Cluster();
                cluster.setClusterName(clusterCreateRq.getName());
                cluster.setCreator(tokenObject.getLogin());
                cluster.setHosts(result_cluster);
                cluster.setDescription(clusterCreateRq.getDesc());

                Date create = cluster.getCreateDate();
                long reserveTime = time(clusterCreateRq.getTime(), clusterCreateRq.getType());
                Date endDate = new Date(create.getTime() + reserveTime);
                cluster.setDestroyDate(endDate.getTime());
                clusterService.save(cluster);

                clusterService.save(cluster);

                Map<String, Object> response = new HashMap<>();
                response.put("hosts", result_cluster);
                response.put("clusterName", clusterCreateRq.getName());
                response.put("size",result_cluster.size());
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {

                Map<String, Object> response = new HashMap<>();
                response.put("msg", "Not enough hosts. Current free: " + result_cluster.size());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

            }

        }
    }



    @EndPointInfo(
            name = "Запрос на разрушение кластера",
            endPoint = "POST /api/v1/cluster/destroy/{clusterName}",
            secureType = RestInfo.Security.SECURE,
            requestType = RestInfo.RequestType.POST,
            requestInfo = "",
            responseInfo = "Ответом является оповещение о разрушении кластера с заданным именем",
            description = "Метод защищен jwt токеном. Выполняет удаление кластера с заданным именем и возврат машин в пул free."
    )
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/api/v1/cluster/destroy/{clusterName}")
    public ResponseEntity<Map<String, Object>> destroy(@PathVariable String clusterName, @RequestHeader String token) throws SQLException {
        TokenObject tokenObject;
        HostsService hostsService = sqlite.getHostsService();
        ClusterService clusterService = sqlite.getClusterService();

        try {
            tokenObject = jwtService.checkToken(token);
        } catch (Exception e){
            Map<String, Object> response = new HashMap<>();
            response.put("msg", "invalid token");
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }

        UpdateBuilder queryBuilder = hostsService.getDao().updateBuilder();
        queryBuilder.updateColumnValue("used", "free").where().eq("used",clusterName);

        PreparedUpdate query = queryBuilder.prepare();
        hostsService.getDao().update(query);

        clusterService.deleteByUniqueFiled("clusterName",clusterName);
        Map<String, Object> response = new HashMap<>();
        response.put("msg", "the cluster with name: " + clusterName +
                " was destroyed");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @EndPointInfo(
            name = "Запрос списка всех кластеров",
            endPoint = "GET /api/v1/clusters/all",
            secureType = RestInfo.Security.OPEN,
            requestType = RestInfo.RequestType.GET,
            requestInfo = "",
            responseInfo = "ответом является полный список кластеров",
            description = "Метод возвращает все что есть в БД. Пагинация на стороне клиента."
    )
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/v1/clusters/all")
    public ArrayList<Cluster> getAllClusters() throws SQLException {
        return (ArrayList<Cluster>) sqlite.getClusterService().getAll();
    }


    public long time(long time, ClusterCreateRq.Time type){
        switch (type){
            case MINUTES:
                return time*1000*60;
            case HOURS:
                return time*1000*60*60;
            case DAYS:
                return time*1000*60*60*24;
            default:
                return 1000*60;
        }
    }

}
