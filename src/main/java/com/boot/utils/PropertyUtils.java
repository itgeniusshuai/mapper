package com.boot.utils;

import com.boot.enums.SqlDialect;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    private static Properties properties;
    static {
        properties = new Properties();
        try {
            properties.load(MapperUtils.class.getClassLoader().getResourceAsStream("Property.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getJavaPropertyTypeByDialect(String mysqlPropertyName, String sqlDialect){
        String propertyValue = properties.getProperty(sqlDialect+"." + mysqlPropertyName.toUpperCase());
        return StringUtils.isBlank(propertyValue)?"Object":propertyValue;
    }
}
