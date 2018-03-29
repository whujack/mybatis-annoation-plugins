package com.github.whujack.config;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import com.github.whujack.utils.ReflectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
