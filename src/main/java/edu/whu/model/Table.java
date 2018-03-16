package edu.whu.model;

import edu.whu.syntax.SQLAnalyze;
import jdk.nashorn.internal.runtime.regexp.joni.Syntax;
import sun.tools.jconsole.Tab;

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


    public Table() {

    }

    public Table(String table) {

    }

    public Table setTable(String sql) {
        SQLAnalyze sqlAnalyze=new SQLAnalyze();
        String tableSql = sql.substring(0, sql.indexOf("("));
        String fields = sql.substring(sql.indexOf("(") + 1, sql.indexOf(")"));
        setName(sqlAnalyze.getTableName(tableSql));
        setTable(fields);

        String[] syntax = fields.split(",");
        for (String tmp : syntax) {
            Column column=new Column();
            sqlAnalyze.analyze(tmp,column);
        }

        return this;
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
    class Index {
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
        private String type;
        private String column;
        private Boolean isPrimaryKey;

        public String getName() {
            return name;
        }

        public Column setName(String name) {
            this.name = name;
            return this;
        }

        public String getType() {
            return type;
        }

        public Column setType(String type) {
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
