package com.github.whujack.model;

import com.github.whujack.config.TableConfiguration;
import com.github.whujack.syntax.SQLAnalyze;
import com.github.whujack.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created By LiJie at 2018/03/15
 */
public class Table {
    private String name;
    private Column primaryKey;
    private List<Column> columns;
    private List<Index> indexList;
    private TableConfiguration tableConfiguration;

    private static final Logger logger = LoggerFactory.getLogger(Table.class);


    public Table() {

    }


    public Table setTable(String sql) {
        SQLAnalyze sqlAnalyze = new SQLAnalyze();
        String tableSql = sql.substring(0, sql.indexOf("("));
        String fields = sql.substring(sql.indexOf("(") + 1, sql.lastIndexOf(")"));
        setName(sqlAnalyze.getTableName(tableSql));

        String[] syntax = fields.split(",");
        this.columns = new ArrayList<>();
        for (String tmp : syntax) {
            Column column = new Column();
            sqlAnalyze.analyze(tmp, column);
            this.columns.add(column);
        }
        for (String tmp : syntax) {
            Column column = analyzePrimary(tmp, this.columns);
            if (column != null) {
                this.primaryKey = column;
                break;
            }
        }

        return this;
    }

    private Column analyzePrimary(String sql, List<Column> columns) {
        sql = sql.toLowerCase();
        if (columns == null || columns.size() == 0) {
            return null;
        }
        if (sql.contains("primary") && sql.contains("key")) {
            for (Column column : columns) {
                if (sql.contains(column.getName())) {
                    column.isPrimaryKey = true;
                    return column;
                }
            }
        }
        return null;
    }

    public String getClazzName() {
        String clazzName = StringUtils.toCamelCase(name);
        return clazzName.substring(0, 1).toUpperCase() + clazzName.substring(1);
    }


    public Column getPrimaryKey() {
        return primaryKey;
    }

    public Table setPrimaryKey(Column primaryKey) {
        this.primaryKey = primaryKey;
        return this;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public Table setColumns(List<Column> columns) {
        this.columns = columns;
        return this;
    }

    public List<Index> getIndexs() {
        return indexList;
    }

    public Table setIndexs(List<Index> indexList) {
        this.indexList = indexList;
        return this;
    }

    public TableConfiguration getTableConfiguration() {
        return tableConfiguration;
    }

    public Table setTableConfiguration(TableConfiguration tableConfiguration) {
        this.tableConfiguration = tableConfiguration;
        return this;
    }

    public String getName() {
        return name;
    }

    public Table setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 数据库索引
     */
    public class Index {
        private String name;
        private List<Column> columns;

        public String getName() {
            return name;
        }

        public Index setName(String name) {
            this.name = name;
            return this;
        }

        public List<Column> getColumns() {
            return columns;
        }

        public Index setColumns(List<Column> columns) {
            this.columns = columns;
            return this;
        }
    }

    /**
     * 数据库column
     */
    public class Column {
        private String name;
        private SQLAnalyze.Type type;
        private String column;
        private Boolean isPrimaryKey;

        public String getName() {
            return name;
        }

        public Column setName(String name) {
            this.name = name;
            return this;
        }

        public SQLAnalyze.Type getType() {
            return type;
        }

        public Column setType(SQLAnalyze.Type type) {
            this.type = type;
            return this;
        }

        public String getColumn() {
            return column;
        }

        public Column setColumn(String column) {
            this.column = column;
            return this;
        }

        public Boolean getPrimaryKey() {
            return isPrimaryKey;
        }

        public Column setPrimaryKey(Boolean primaryKey) {
            isPrimaryKey = primaryKey;
            return this;
        }
    }

}
