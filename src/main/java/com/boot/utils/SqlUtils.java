package com.boot.utils;

import java.io.IOException;
import java.util.Properties;

public class SqlUtils {
    private static Properties properties;
    static {
        properties = new Properties();
        try {
            properties.load(SqlUtils.class.getClassLoader().getResourceAsStream("sql.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getSql(String type,String sqlDialect){
        String sql = properties.getProperty(sqlDialect + "." + type + ".sql");
        return sql;
    }


}
