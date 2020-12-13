package org.cluster;

import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utils {
    public static String md5(String st) {
        String md5Hex = DigestUtils.md5Hex(st);
        return md5Hex;
    }
    public static Connection getConnection(String url) throws SQLException {
        // create a connection to the database
        return DriverManager.getConnection(url);
    }
}
