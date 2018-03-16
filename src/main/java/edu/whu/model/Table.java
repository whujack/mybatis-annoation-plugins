package edu.whu.model;

import java.util.List;

/**
 * @author Created By LiJie at 2018/03/15
 */
public class Table {
    private Column primaryKey;
    private List<Column> columns;
    private List<Index> indexList;


    public Table(){

    }
    public Table(String table){

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
    class Column {
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
