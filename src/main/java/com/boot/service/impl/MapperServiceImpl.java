package com.boot.service.impl;

import com.boot.domain.Column;
import com.boot.domain.Table;
import com.boot.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.dc.pr.PRError;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class MapperServiceImpl implements MapperService {

    @Value("${packageEntityPath}")
    private String packageEntityPath;
    @Value("${packageMapperPath}")
    private String packageMapperPath;
    @Value("${targetPath}")
    private String targetPath;

    @Override
    public void generate(Table table) {
        table.flushJavaProperties();
        generateEntity(table);
        generateMapperInterface(table);
        generateMapperFile(table);
    }

    @Override
    public void generateMapperInterface(Table table) {
        String baseName = table.getBaseName();
        String smallCamelName = MapperUtils.toCamelCase(baseName,false);
        File parentDir = new File(targetPath);
        if(!parentDir.exists()){
            try {
                parentDir.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        File file = new File(parentDir,baseName+"Mapper.java");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write("package "+packageMapperPath+";");
            writer.newLine();
            writer.newLine();
            writer.write("import "+packageEntityPath+";");
            writer.newLine();
            writer.newLine();
            writer.write("public Interface "+baseName+"Mapper {");
            writer.newLine();
            writer.newLine();
            String entityStr = baseName+"Entity "+smallCamelName+"Entity";
            writer.write("\tpublic insertOne("+entityStr+")");
            writer.newLine();
            writer.newLine();
            List<Column> primaryKey = table.getPrimaryKey();
            StringBuilder sb = new StringBuilder();
            for(Column column : primaryKey){
                sb.append(column.getJavaPropertyTypeName()+" "+column.getPropertyName());
                sb.append(",");
            }
            String primaryKeyStr = sb.substring(0,sb.length()-1);
            writer.write("\tpublic deleteByPrimaryKey("+primaryKeyStr+")");
            writer.newLine();
            writer.newLine();
            writer.write("\tpublic updateByPrimaryKey("+entityStr+")");
            writer.newLine();
            writer.newLine();
            writer.write("\tpublic selectByPrimaryKey("+primaryKeyStr+")");
            writer.newLine();
            writer.newLine();
            writer.write("\tpublic selectBySelective("+entityStr+")");
            writer.newLine();
            writer.newLine();
            writer.write("\tpublic insertSelective("+entityStr+")");
            writer.newLine();
            writer.newLine();
            writer.write("}");
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void generateEntity(Table table) {
        String baseName = table.getBaseName();
        File parentDir = new File(targetPath);
        if(!parentDir.exists()){
            try {
                parentDir.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        File file = new File(parentDir,baseName+"Entity.java");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write("package "+packageEntityPath+";");
            writer.newLine();
            writer.newLine();
            List<String> importPackages = table.getImportPackage();
            if(importPackages != null){
                for(String importPackage : importPackages){
                    writer.write("import "+importPackage+";");
                }
            }
            writer.newLine();
            writer.newLine();
            writer.write("public class "+baseName+"Entity {");
            writer.newLine();
            writer.newLine();
            for(Column column : table.getColumnList()){
                writer.write("\t");
                writer.write("private "+column.getJavaPropertyTypeName() +" "+ MapperUtils.toCamelCase(column.getColumnName(),false)+";");
                writer.newLine();
                writer.newLine();
            }
            for(Column column : table.getColumnList()){
                String propertyName = column.getPropertyName();
                writer.write("\tpublic void "+column.getSetName()+"("+column.getJavaPropertyTypeName()+" "+propertyName+"){");
                writer.newLine();
                writer.write("\t\tthis."+propertyName+" = "+propertyName+";");
                writer.newLine();
                writer.write("\t}");
                writer.newLine();
                writer.newLine();
                writer.write("\tpublic "+column.getJavaPropertyTypeName()+" "+column.getGetName()+" ("+column.getJavaPropertyTypeName()+" "+propertyName+"){");
                writer.newLine();
                writer.write("\t\treturn this."+propertyName+";");
                writer.newLine();
                writer.write("\t}");
                writer.newLine();
                writer.newLine();
            }
            writer.write("}");
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void generateMapperFile(Table table) {
        String baseName = table.getBaseName();
        File parentDir = new File(targetPath);
        String mapperPackageEntityPath = packageEntityPath.replaceAll("/",".")+"."+baseName+"Entity";
        String mapperPackageMapperPath = packageMapperPath.replaceAll("/",".")+"."+baseName+"Mapper";
        if(!parentDir.exists()){
            try {
                parentDir.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        File file = new File(parentDir,baseName+"Mapper.xml");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>  \n" +
                    "  \n" +
                    "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"   \n" +
                    "\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">  ");
            writer.newLine();
            writer.newLine();
            writer.write("<mapper namespace=\""+mapperPackageMapperPath+"\">  ");
            writer.newLine();
            writer.newLine();
            writer.write("\t<resultMap type=\""+mapperPackageEntityPath+"\" id=\"baseResult\">");
            writer.newLine();
            for(Column column : table.getColumnList()){
                writer.write("\t\t");
                writer.write(column.getMapperStr());
                writer.newLine();
            }
            writer.write("\t</resultMap>");
            writer.newLine();
            writer.write("\t<sql id=\"baseColums\">");
            writer.newLine();
            StringBuilder sb = new StringBuilder();
            boolean isAutoIncrement = false;
            for(Column column : table.getColumnList()){
                if(column.isFlagPrimaryKey()){
                    isAutoIncrement = true;
                    continue;
                }
                sb.append("\t\t");
                sb.append(column.getColumnName()).append(",");
                sb.append("\n");
            }
            String baseColumns = sb.substring(0,sb.length()-2);
            writer.write(baseColumns);
            writer.newLine();
            writer.write("\t</sql>");
            writer.newLine();
            writer.write("\t<select id=\"selectByPrimaryKey\" resultMap=\"baseResult\">");
            writer.newLine();
            StringBuilder primaryKeyColumns = new StringBuilder();
            if(isAutoIncrement){
                for(Column column : table.getPrimaryKey()){
                    primaryKeyColumns.append(column.getColumnName()).append(",");
                }
            }
            writer.write("\t\tselect "+primaryKeyColumns+" <include refid=\"baseColums\">from "+table.getTableName()+" where ");
            StringBuilder primaryKeyWhereSb = new StringBuilder();
            for(Column column : table.getPrimaryKey()){
                primaryKeyWhereSb.append("and").append(column.getColumnName()).append("=").append("#{").append(column.getPropertyName()).append("}");
            }
            String primaryKeyWhereStr = primaryKeyWhereSb.toString().replace("and", "");
            writer.write(primaryKeyWhereStr);
            writer.newLine();
            writer.write("\t</select>");
            writer.newLine();
            writer.write("\t<select id=\"selectBySelective\" >");
            writer.newLine();
            writer.write("\t\tselect "+primaryKeyColumns+" <include refid=\"baseColums\">from "+table.getTableName()+"\n\t\t<where>");
            writer.newLine();
            for(Column column : table.getColumnList()){
                writer.write("\t\t\t");
                writer.write(String.format(column.getIfNullStr()," and",""));
                writer.newLine();
            }
            writer.write("\t\t</where>");
            writer.newLine();
            writer.write("\t</select>");



            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
