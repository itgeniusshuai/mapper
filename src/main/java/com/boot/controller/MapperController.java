package com.boot.controller;

import com.boot.common.CommonResult;
import com.boot.config.SqlDialectConfig;
import com.boot.domain.Column;
import com.boot.domain.GenetorParam;
import com.boot.domain.Table;
import com.boot.service.impl.MapperService;
import com.boot.utils.SqlUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/chart")
public class MapperController {

    @Autowired
    private QueryRunner queryRunner;
    @Autowired
    private MapperService mapperService;



    @RequestMapping("/tables")
    @ResponseBody
    public CommonResult getTables(String dbName){
        try {
            String sql = SqlUtils.getSql("tables", SqlDialectConfig.getSqlDailect());
            List<Table> tableList = queryRunner.query(sql, new BeanListHandler<Table>(Table.class),dbName);
            return CommonResult.ok(tableList);
        } catch (SQLException e) {
            e.printStackTrace();
            return CommonResult.error(1,"error");
        }
    }

    @RequestMapping("/generation")
    @ResponseBody
    public CommonResult generateMapper(Table table){
        try {
            String sql = SqlUtils.getSql("columns",SqlDialectConfig.getSqlDailect());
            List<Column> columns = queryRunner.query(sql, new BeanListHandler<Column>(Column.class),table.getTableName(),table.getDbName());
            table.setColumnList(columns);
            mapperService.generate(table);
            return CommonResult.ok("success");
        } catch (SQLException e) {
            e.printStackTrace();
            return CommonResult.error(1,"error");
        }
    }

}
