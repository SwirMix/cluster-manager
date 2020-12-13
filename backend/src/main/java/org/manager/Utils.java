package org.manager;

import org.manager.controllers.account.auth.AuthRq;
import org.manager.controllers.hosts.HostRq;

public class Utils {
    public static boolean firstLineValidate(AuthRq authRq) {
        if (authRq.getLogin().equals(null)|authRq.getLogin().equals("")|authRq.getPassword().equals("")|authRq.getPassword().equals(null)){
            return false;
        } else {
            return true;
        }
    }

    public static boolean firstLineValidate(HostRq hostRq) {
        if (notNullEmptyValidation(hostRq.getHost())){
            if (notNullEmptyValidation(hostRq.getDnsName())){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean notNullEmptyValidation(String data){
        if (data.equals("")|data.equals(null)){
            return false;
        } else {
            return true;
        }
    }

}
