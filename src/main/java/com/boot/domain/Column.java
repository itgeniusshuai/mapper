package com.boot.domain;

public class Column {
    private String tableName;
    private String columnName;
    private String propertyName;
    private String dataType;
    private boolean flagPrimaryKey;
    private boolean flagAutoIncrement;
    private String javaPropertyTypeName;
    private String javaPropertyTypePackageName;
    private String setName;
    private String getName;
    private String mapperStr;
    private String ifNullStr;

    public boolean isFlagPrimaryKey() {
        return flagPrimaryKey;
    }

    public void setFlagPrimaryKey(boolean flagPrimaryKey) {
        this.flagPrimaryKey = flagPrimaryKey;
    }

    public boolean isFlagAutoIncrement() {
        return flagAutoIncrement;
    }

    public void setFlagAutoIncrement(boolean flagAutoIncrement) {
        this.flagAutoIncrement = flagAutoIncrement;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getJavaPropertyTypeName() {
        return javaPropertyTypeName;
    }

    public void setJavaPropertyTypeName(String javaPropertyTypeName) {
        this.javaPropertyTypeName = javaPropertyTypeName;
    }

    public String getJavaPropertyTypePackageName() {
        return javaPropertyTypePackageName;
    }

    public void setJavaPropertyTypePackageName(String javaPropertyTypePackageName) {
        this.javaPropertyTypePackageName = javaPropertyTypePackageName;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getGetName() {
        return getName;
    }

    public void setGetName(String getName) {
        this.getName = getName;
    }

    public String getMapperStr() {
        return mapperStr;
    }

    public void setMapperStr(String mapperStr) {
        this.mapperStr = mapperStr;
    }

    public String getIfNullStr() {
        return ifNullStr;
    }

    public void setIfNullStr(String ifNullStr) {
        this.ifNullStr = ifNullStr;
    }
}
