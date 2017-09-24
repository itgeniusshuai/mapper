package com.boot.config;

import com.boot.enums.SqlDialect;
import com.boot.utils.MapperUtils;

import java.io.IOException;
import java.util.Properties;

public class SqlDialectConfig {
    private static String sqlDialect;
    static {
        Properties properties = new Properties();
        try {
            properties.load(MapperUtils.class.getClassLoader().getResourceAsStream("dailect.properties"));
            sqlDialect = properties.getProperty("sqlDialect");
        } catch (IOException e) {
            e.printStackTrace();
            sqlDialect = "mysql";
        }
    }

    public static String getSqlDailect(){
        return sqlDialect;
    }

}
