package edu.whu.model;

import java.util.List;

/**
 * @author Created By LiJie at 2018/3/19
 */
public class Clazz {
    private String packageName;
    private String className;
    private List<String> importClazz;
    private List<Table.Column> columnList;

    @Override
    public String toString() {
        StringBuffer clazzBuff = new StringBuffer();
        clazzBuff.append("package " + packageName + ";\n");
        for (int i = 0; importClazz != null && i < importClazz.size(); i++) {
            clazzBuff.append("import " + importClazz.get(i) + ";\n");
        }
        for (int i = 0; columnList != null && i < columnList.size(); i++) {


        }

        return clazzBuff.toString();
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
