package com.github.whujack.model;

import com.github.whujack.syntax.SQLAnalyze;
import com.github.whujack.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created By LiJie at 2018/3/19
 */
public class Clazz {
    private String packageName;
    private String className;
    private List<String> importClazz;
    private List<Table.Column> columnList;
    private List<String> methodList;

    @Override
    public String toString() {
        StringBuffer clazzBuff = new StringBuffer();
        clazzBuff.append("package " + packageName + ";\n\n");
        for (int i = 0; importClazz != null && i < importClazz.size(); i++) {
            clazzBuff.append("import " + importClazz.get(i) + ";\n");
        }
        clazzBuff.append("\n");
        //设置class
        clazzBuff.append("public class " + className + "{\n");


        methodList = new ArrayList<>();
        //设置field
        for (int i = 0; columnList != null && i < columnList.size(); i++) {
            Table.Column column = columnList.get(i);
            if (column.getName() != null && column.getType() != null) {
                clazzBuff.append("\t");
                String fieldName = StringUtils.toCamelCase(column.getName());
                clazzBuff.append("private " + column.getType() + " " + fieldName + ";\n");
                methodList.add(getGetterAndSetter(fieldName, column.getType()));
            }
        }
        clazzBuff.append("\n");
        for (int i = 0; i < methodList.size(); i++) {
            clazzBuff.append(methodList.get(i));
        }
        clazzBuff.append("}\n");

        return clazzBuff.toString();
    }

    private String getGetterAndSetter(String name, SQLAnalyze.Type type) {
        StringBuilder builder = new StringBuilder();
        //field getter
        builder.append("\tpublic " + type.getName() + " get" + StringUtils.firstToUpper(name) + "(){\n");
        builder.append("\t\t" + "return this." + name+";");
        builder.append("\n\t}\n\n");
        //field setter
        builder.append("\tpublic void set" + StringUtils.firstToUpper(name) + "(" + type.getName() + " " + name + "){\n");
        builder.append("\t\tthis." + name + "=" + name+";");
        builder.append("\n\t}\n\n");
        return builder.toString();
    }

    public String getPackageName() {
        return packageName;
    }

    public Clazz setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public Clazz setClassName(String className) {
        this.className = className;
        return this;
    }

    public List<Table.Column> getColumnList() {
        return columnList;
    }

    public Clazz setColumnList(List<Table.Column> columnList) {
        this.columnList = columnList;
        return this;
    }

    public List<String> getImportClazz() {
        return importClazz;
    }

    public Clazz setImportClazz(List<String> importClazz) {
        this.importClazz = importClazz;
        return this;
    }
}
