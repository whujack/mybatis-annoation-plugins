package com.github.whujack.model;

import com.github.whujack.utils.StringUtils;

import java.util.List;

/**
 * @author Created By LiJie at 2018/3/19
 */
public class Xml {
    private static String HEADER_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    private static String MAPPER_NAMESPACE = "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">";
    private static String NAMESPACE_TEMPLATE = "<mapper namespace=\"#{}\">";
    private static String RESULT_MAP_TEMPLATE = "<resultMap id=\"#{}\" type=\"#{}\">";
    private static String REAULT_TEMPLATE = "<result column=\"#{}\"  property=\"#{}\" />";
    private String namespace;
    private String resultMapId;
    private String resultMapType;
    private List<Table.Column> columnList;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(HEADER_XML);
        builder.append("\n" + MAPPER_NAMESPACE);
        builder.append("\n" + StringUtils.replace(NAMESPACE_TEMPLATE, namespace));
        builder.append("\n\t" + StringUtils.replace(RESULT_MAP_TEMPLATE, resultMapId, resultMapType));
        for (int i = 0; columnList != null && i < columnList.size(); i++) {
            Table.Column column = columnList.get(i);
            if (column.getType() != null && column.getName() != null) {
                builder.append("\n\t\t" + StringUtils.replace(REAULT_TEMPLATE, column.getName(), StringUtils.toCamelCase(column.getName())));
            }
        }
        //结束符
        builder.append("\n</resultMap>\n</mapper>");

        return builder.toString();
    }


    public String getNamespace() {
        return namespace;
    }

    public Xml setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    public String getResultMapId() {
        return resultMapId;
    }

    public Xml setResultMapId(String resultMapId) {
        this.resultMapId = resultMapId;
        return this;
    }

    public String getResultMapType() {
        return resultMapType;
    }

    public Xml setResultMapType(String resultMapType) {
        this.resultMapType = resultMapType;
        return this;
    }

    public List<Table.Column> getColumnList() {
        return columnList;
    }

    public Xml setColumnList(List<Table.Column> columnList) {
        this.columnList = columnList;
        return this;
    }
}
