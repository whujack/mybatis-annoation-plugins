package edu.whu.syntax;

import com.mysql.jdbc.StringUtils;
import edu.whu.model.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Created By LiJie at 2018/3/16
 */
public class SQLAnalyze {
    private static final Logger logger = LoggerFactory.getLogger(SQLAnalyze.class);
    private static Pattern ID_PATTERN = Pattern.compile("`(\\w*|\\d*)`");
    private String sql;

    public SQLAnalyze(String sql) {
        this.sql = sql;
    }

    public SQLAnalyze() {

    }


    /**
     * 分析创建表的sql，产生table数据结构
     *
     * @return 返回table
     */
    public Table analyzeCreateTable(String sql) {
        if (StringUtils.isNullOrEmpty(sql)) {
            return null;
        }
        Table table = new Table();

        return table;
    }

    /**
     * 语句分析
     *
     * @param sql 行语句
     */
    public Table.Column analyze(String sql, Table.Column column) {
        if (sql.trim().indexOf('`') == 0) {
            column.setName(getValue(sql));
            column.setType(getType(sql));
            return column;
        }
        return null;
    }

    /**
     * 获取数据库的表面
     *
     * @param sql sql语句
     * @return 表名字
     */
    public String getTableName(String sql) {
        sql = sql.toLowerCase();
        if (!sql.contains("create")) {
            return null;
        }
        return getValue(sql);
    }

    /**
     * 获取mysql字段的字段
     *
     * @param sql mysql语句
     * @return 返回命名符号
     */
    private String getValue(String sql) {
        Matcher matcher = ID_PATTERN.matcher(sql);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    /**
     * 获取数据类型
     *
     * @param sql sql语句
     * @return 返回类型
     */
    private Type getType(String sql) {
        sql = sql.toLowerCase();
        if (sql.contains("int") && !sql.contains("bigint") && !sql.contains("tinyint")) {
            return Type.Integer;
        }
        if (sql.contains("tinyint")) {
            return Type.Short;
        }
        if (sql.contains("bigint")) {
            return Type.Long;
        }
        if (sql.contains("varchar")) {
            return Type.String;
        }
        if (sql.contains("timestamp") || sql.contains("date")) {
            return Type.Date;
        }
        return null;
    }


    public enum Type {
        Byte("Byte", "java.lana.Byte"),
        Character("Character", "java.lang.Character"),
        Short("Short", "java.lang.Short"),
        Integer("Integer", "java.lang.Integer"),
        Long("Long", "java.lang.Long"),
        Float("Float", "java.lang.Float"),
        Double("Double", "java.lang.Double"),
        String("String", "java.lang.String"),
        Date("Date", "java.util.Date");

        private String name;
        private String packageName;

        Type(String name, String packageName) {
            this.name = name;
            this.packageName = packageName;
        }

        public java.lang.String getPackageName() {
            return packageName;
        }

        public java.lang.String getName() {
            return name;
        }
    }
}
