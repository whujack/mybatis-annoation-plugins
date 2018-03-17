package edu.whu.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import edu.whu.model.TableConfiguration;
import edu.whu.utils.ReflectUtils;
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
    private final static Logger logger = LoggerFactory.getLogger(Configuration.class);
    private YAMLFactory yamlFactory = new YAMLFactory();
    private JsonNode jsonNode;

    /**
     * package配置文件
     */
    private final static String[] PACKAGE_CONFIG = {"model", "dao", "mapper"};
    private final static String[] TABLE_CONFIG = {"enableSelect", "enableInsert", "enableDelete", "enableUpdate"};
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
        this.tables=new ArrayList<>();
        Iterator<JsonNode> it = jsonNode.iterator();
        while (it.hasNext()) {
            JsonNode tmp = it.next();
            this.tables.addAll(setTable(tmp));
        }
    }

    private List<TableConfiguration> setTable(JsonNode jsonNode) {
        String names = jsonNode.get("name").asText();
        String[] nameArray = names.split(",");
        List<TableConfiguration> tableList = new ArrayList<>();
        for (int i = 0; i < nameArray.length; i++) {
            TableConfiguration table = new TableConfiguration();
            table.setName(nameArray[i]);
            for (String tmp : TABLE_CONFIG) {
                try {
                    ReflectUtils.invokeSetter(table, "set" + tmp.substring(0, 1).toUpperCase() +
                            tmp.substring(1), Boolean.class, setOfNull(jsonNode, tmp));
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            tableList.add(table);
        }
        return tableList;
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

    private String setString(JsonNode jsonNode, String name) {
        if (jsonNode == null) {
            return null;
        }
        if (jsonNode.get(name) == null) {
            return null;
        }
        return jsonNode.get(name).asText();

    }

    private void setPackage(JsonNode jsonNode) {
        for (String tmp : PACKAGE_CONFIG) {
            try {
                ReflectUtils.invokeSetter(this, "set" + tmp.substring(0, 1).toUpperCase() +
                        tmp.substring(1), String.class, setString(jsonNode, tmp));
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
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

    public List<TableConfiguration> getTables() {
        return tables;
    }

    public Configuration setTables(List<TableConfiguration> tables) {
        this.tables = tables;
        return this;
    }
}
