package org.manager.controllers.info;

import org.manager.controllers.account.AuthController;
import org.manager.controllers.cluster.ClustersController;
import org.manager.controllers.hosts.HostController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class InfoController {
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/v1/info")
    public ResponseEntity<Map<String, Object>> info(){
        ArrayList<Class> controllers = new ArrayList();
        controllers.add(AuthController.class);
        controllers.add(ClustersController.class);
        controllers.add(HostController.class);

        Method[] methods = AuthController.class.getMethods();
        ArrayList<RestInfo> endpoints = new ArrayList<>();

        for (Class clazz : controllers) {
            for (Method method : clazz.getMethods()) {
                if (method.isAnnotationPresent(EndPointInfo.class)){
                    EndPointInfo info = method.getAnnotation(EndPointInfo.class);
                    RestInfo restInfo = new RestInfo();
                    restInfo.setEndpoint(info.endPoint());
                    restInfo.setDescription(info.description());
                    restInfo.setRequestExample(info.requestInfo());
                    restInfo.setResponseExample(info.responseInfo());
                    restInfo.setSecurity(info.secureType());
                    restInfo.setRequestType(info.requestType());
                    endpoints.add(restInfo);
                }
            }
        }


        Map<String, Object> response = new HashMap<>();
        response.put("API",endpoints);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
