package com.boot.domain;

import java.util.List;

public class GenetorParam {

    private int dbType = 0;
    private List<String> tableNameList;

    public int getDbType() {
        return dbType;
    }

    public void setDbType(int dbType) {
        this.dbType = dbType;
    }

    public List<String> getTableNameList() {
        return tableNameList;
    }

    public void setTableNameList(List<String> tableNameList) {
        this.tableNameList = tableNameList;
    }
}
