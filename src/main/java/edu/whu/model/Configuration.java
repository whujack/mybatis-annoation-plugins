package edu.whu.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Created By LiJie at 2018/03/14
 */
public class Configuration {
    private YAMLFactory yamlFactory = new YAMLFactory();
    private JsonNode jsonNode;

    /**
     *jdbc mysql部分
     */
    private String jdbc;
    private String url;
    private String username;
    private String password;
    /**
     *  package部分
     */
    private String model;
    private String dao;
    private String mapper;

    /**
     *  table部分
     */
    private List<String> tables;
    public Configuration(){

    }
    public Configuration(File configurationFile) {
        try {
            InputStream inputStream=new FileInputStream(configurationFile);
            YAMLParser yamlParser = yamlFactory.createParser(inputStream);
            ObjectMapper objectMapper=new ObjectMapper();
            jsonNode=objectMapper.readTree(yamlParser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getJdbc() {
        return jdbc;
    }

    public Configuration setJdbc(String jdbc) {
        this.jdbc = jdbc;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Configuration setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Configuration setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Configuration setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Configuration setModel(String model) {
        this.model = model;
        return this;
    }

    public String getDao() {
        return dao;
    }

    public Configuration setDao(String dao) {
        this.dao = dao;
        return this;
    }

    public String getMapper() {
        return mapper;
    }

    public Configuration setMapper(String mapper) {
        this.mapper = mapper;
        return this;
    }

    public List<String> getTables() {
        return tables;
    }

    public Configuration setTables(List<String> tables) {
        this.tables = tables;
        return this;
    }
}
