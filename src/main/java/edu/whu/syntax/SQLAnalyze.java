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
        String tableSql = sql.substring(0, sql.indexOf("("));
        String fields = sql.substring(sql.indexOf("(") + 1, sql.indexOf(")"));
        logger.info("table={}   fields={}", tableSql, fields);
        table.setName(getTableName(tableSql));
        analyze(fields);
        return null;
    }

    /**
     * 语句分析
     *
     * @param sql 行语句
     */
    private void analyze(String sql) {


    }

    private String getTableName(String sql) {
        sql = sql.toLowerCase();
        if (!sql.contains("create")) {
            return null;
        }
        Pattern pattern = Pattern.compile("`(\\w*|\\d*)`");
        Matcher matcher = pattern.matcher(sql);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
