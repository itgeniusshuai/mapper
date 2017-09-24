package com.boot.enums;

import com.boot.domain.Table;

public enum SqlType {
    TABLES("TABLES"),
    COLUMNS("COLUMNS");

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    SqlType(String value) {

        this.value = value;
    }
}
