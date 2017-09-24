package com.boot.utils;

import com.boot.domain.Column;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class MapperUtils {

    private static  List<String> unUseredPres = Arrays.asList("t_","tab_");

    // 将name转换成标准驼峰
    public static String toCamelCase(String name, boolean isBig){
        if(StringUtils.isBlank(name)){
            return null;
        }
        String baseName = name;
        for(String str : unUseredPres){
            if(name.startsWith(str)){
                baseName = name.replace(str,"");
                break;
            }
        }
        String[] split = baseName.split("_");
        if(split.length == 1){
            if(isBig)
                return split[0].substring(0,1).toUpperCase()+split[0].substring(1);
            else
                return split[0].substring(0,1).toLowerCase()+split[0].substring(1);
        }
        StringBuilder sb = new StringBuilder();
        for(String str : split){
            if(isBig)
                sb.append(str.substring(0,1).toUpperCase()+str.substring(1));
            else
                sb.append(str.substring(0,1).toLowerCase()+str.substring(1));
        }
        return sb.toString();
    }





}
