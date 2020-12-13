package org.manager.controllers.info;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value= ElementType.METHOD)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface EndPointInfo {
    String name();
    String endPoint();
    RestInfo.Security secureType();
    RestInfo.RequestType requestType();
    String requestInfo();
    String responseInfo();
    String description();
}
