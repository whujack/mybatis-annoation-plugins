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

        }
        return null;
    }

    public String getTableName(String sql) {
        sql = sql.toLowerCase();
        if (!sql.contains("create")) {
            return null;
        }
        return getValue(sql);
    }

    private String getValue(String sql) {
        Pattern pattern = Pattern.compile("`(\\w*|\\d*)`");
        Matcher matcher = pattern.matcher(sql);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
    private Class getType(String sql){
        return null;
    }
}
