package com.boot.enums;

public enum  SqlDialect {
    MYSQL("mysql"),
    SQLSERVER("sqlServer");

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    SqlDialect(String value) {

        this.value = value;
    }
}
