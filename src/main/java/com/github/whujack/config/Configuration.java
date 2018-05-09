package com.github.whujack.config;

/**
 * @author Created By LiJie at 2018/03/14
 */
public class Configuration {
   private DataBaseConfiguration database;
   private PackageConfiguration packages;
   private TableConfiguration[] tableConfigurations;


    public DataBaseConfiguration getDatabase() {
        return database;
    }

    public Configuration setDatabase(DataBaseConfiguration database) {
        this.database = database;
        return this;
    }

    public PackageConfiguration getPackages() {
        return packages;
    }

    public Configuration setPackages(PackageConfiguration packages) {
        this.packages = packages;
        return this;
    }

    public TableConfiguration[] getTableConfigurations() {
        return tableConfigurations;
    }

    public Configuration setTableConfigurations(TableConfiguration[] tableConfigurations) {
        this.tableConfigurations = tableConfigurations;
        return this;
    }
}
