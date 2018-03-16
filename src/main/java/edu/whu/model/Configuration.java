package edu.whu.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Created By LiJie at 2018/03/14
 */
public class Configuration {
    private final static Logger logger = LoggerFactory.getLogger(Configuration.class);
    private YAMLFactory yamlFactory = new YAMLFactory();
    private JsonNode jsonNode;

    /**
     * jdbc mysql部分
     */
    private String driver;
    private String url;
    private String username;
    private String password;
    /**
     * package部分
     */
    private String model;
    private String dao;
    private String mapper;

    /**
     * table部分
     */
    private List<TableConfiguration> tables;

    public Configuration() {

    }

    public Configuration(File configurationFile) {
        try {
            InputStream inputStream = new FileInputStream(configurationFile);
            YAMLParser yamlParser = yamlFactory.createParser(inputStream);
            ObjectMapper objectMapper = new ObjectMapper();
            jsonNode = objectMapper.readTree(yamlParser);
            setDataBase(jsonNode.get("database"));
            setPackage(jsonNode.get("package"));
            setTables(jsonNode.get("tables"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setTables(JsonNode jsonNode) {
        Iterator<JsonNode> it = jsonNode.iterator();
        while (it.hasNext()) {
            JsonNode tmp = it.next();
            setTable(tmp);
        }
    }

    private void setTable(JsonNode jsonNode) {
        String names = jsonNode.get("name").asText();
        String[] nameArray = names.split(",");
        List<TableConfiguration> tableList = new ArrayList<>();
        for (int i = 0; i < nameArray.length; i++) {
            TableConfiguration table = new TableConfiguration();

        }
    }

    private boolean setOfNull(JsonNode jsonNode, String name) {
        if (jsonNode == null) {
            return false;
        }
        if (jsonNode.get(name) == null) {
            return false;
        }
        try {
            return jsonNode.get(name).asBoolean();
        } catch (Exception e) {
            return false;
        }
    }

    private void setPackage(JsonNode jsonNode) {
        setModel(jsonNode.get("model").asText());
        setDao(jsonNode.get("dao").asText());
        setMapper(jsonNode.get("mapper").asText());
    }

    private void setDataBase(JsonNode jsonNode) {
        setDriver(jsonNode.get("driver").asText());
        setUrl(jsonNode.get("url").asText());
        setUsername(jsonNode.get("username").asText());
        setPassword(jsonNode.get("password").asText());

    }


    public String getDriver() {
        return driver;
    }

    public Configuration setDriver(String driver) {
        this.driver = driver;
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

}
