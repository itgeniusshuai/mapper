package com.boot.service.impl;

import com.boot.domain.Column;
import com.boot.domain.Table;

import java.util.List;

public interface MapperService {
    public void generate(Table table);
    public void generateEntity(Table table);
    public void generateMapperInterface(Table table);
    public void generateMapperFile(Table table);
}
