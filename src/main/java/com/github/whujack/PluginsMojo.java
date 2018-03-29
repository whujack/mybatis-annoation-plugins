package com.github.whujack;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import com.github.whujack.config.Configuration;
import com.github.whujack.config.TableConfiguration;
import com.github.whujack.constant.GlobalConstant;
import com.github.whujack.factory.Factory;
import com.github.whujack.model.Table;
import com.github.whujack.utils.DBUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Created By LiJie at 2018/03/14
 * <p>
 * 生成mybatis annotation、model、mapper
 */
@Mojo(name = "generator")
public class PluginsMojo extends AbstractMojo {
    private Configuration configuration;
    private List<Table> tableList;

    /**
     * 配置文件地址
     */
    @Parameter(property = "configurationFile", defaultValue = "${project.basedir}/src/main/resources/mybatis-annotation-config.yml", required = true)
    private File configurationFile;

    @Parameter(property = "baseDirFile", defaultValue = "${project.basedir}")
    private File baseDirFIle;


    @Override
    public void execute() throws MojoExecutionException {
        //读取配置文件
        getLog().info("mybatis-annotation-plugins读取全局配置文件");
        GlobalConstant.BASE_DIR_FILE = baseDirFIle;
        InputStream inputStream = null;
        try {
            YAMLFactory yamlFactory = new YAMLFactory();
            inputStream = new FileInputStream(configurationFile);
            YAMLParser yamlParser = yamlFactory.createParser(inputStream);
            ObjectMapper objectMapper = new ObjectMapper();
            configuration = objectMapper.readValue(yamlParser, Configuration.class);
        } catch (IOException e) {
            e.printStackTrace();
        }


        getLog().info(JSON.toJSONString(configuration));

        getLog().info("mybatis-annotation-plugins读取table配置文件");

        //工厂模式读取table
        List<Table> tableList = new ArrayList<>();
        for (int i = 0; i < configuration.getTableConfigurations().length; i++) {
            TableConfiguration tableConfiguration = configuration.getTableConfigurations()[i];
            tableList.add(new Table().setTable(tableConfiguration,configuration));
        }
        getLog().info(JSON.toJSONString(tableList));
        getLog().info("mybatis-annotation-plugins产生model文件");
        Factory factory = new Factory(configuration,tableList);
        factory.produce();
    }
}
