package com.boot.domain;

import com.boot.config.SqlDialectConfig;
import com.boot.enums.SqlDialect;
import com.boot.utils.MapperUtils;
import com.boot.utils.PropertyUtils;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private String tableName;
    private String dbName;
    private String baseName;
    private List<Column> columnList;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    public void flushJavaProperties(){
        this.baseName = MapperUtils.toCamelCase(this.tableName,true);
        for(Column column : columnList){
            String columnName = column.getColumnName();
            String propertyName = MapperUtils.toCamelCase(columnName,false);
            column.setPropertyName(propertyName);
            column.setJavaPropertyTypePackageName(PropertyUtils.getJavaPropertyTypeByDialect(column.getDataType(), SqlDialectConfig.getSqlDailect()));
            int index = column.getJavaPropertyTypePackageName().lastIndexOf(".");
            column.setJavaPropertyTypeName(index>0?column.getJavaPropertyTypePackageName().substring(index+1):column.getJavaPropertyTypePackageName());
            String s = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
            column.setSetName("set"+s);
            column.setGetName("get"+s);
            column.setIfNullStr("<if test=\""+propertyName+" != null\" > %s "+columnName+"= #{"+propertyName+"}%s </if>");
            column.setMapperStr("<result column=\""+columnName+"\" property=\""+propertyName+"\"></result>");
        }
    }

    public List<Column> getPrimaryKey(){
        if(columnList == null || columnList.size() <= 0 ){
            return null;
        }
        List<Column> list = new ArrayList<>();
        for(Column column : columnList){
            if(column.isFlagAutoIncrement()){
                list.add(column);
            }
        }
        if(list.size()==0){
            throw  new RuntimeException("必须有主键");
        }
        return list;
    }

    public List<String> getImportPackage(){
        if(columnList == null || columnList.size() <= 0 ){
            return null;
        }
        List<String> list = new ArrayList<>();
        for(Column column : columnList){
            String javaPropertyTypePackageName = column.getJavaPropertyTypePackageName();
            if(javaPropertyTypePackageName.indexOf(".")>0){
                if(!list.contains(javaPropertyTypePackageName)){
                    list.add(javaPropertyTypePackageName);
                }
            }
        }
        return list;
    }
}
