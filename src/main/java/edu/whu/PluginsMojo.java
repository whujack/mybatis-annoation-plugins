package edu.whu;

import com.alibaba.fastjson.JSON;
import edu.whu.config.Configuration;
import edu.whu.constant.GlobalConstant;
import edu.whu.factory.Factory;
import edu.whu.factory.TableFactory;
import edu.whu.model.Table;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Created By LiJie at 2018/03/14
 * @goal 生成mybatis annotation、model、mapper
 */
@Mojo(name = "generator")
public class PluginsMojo extends AbstractMojo {

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
        Configuration configuration = new Configuration(configurationFile);

        getLog().info("mybatis-annotation-plugins读取table配置文件");

        //工厂模式读取table
        List<Table> tableList = new ArrayList<>();
        for (int i = 0; i < configuration.getTables().size(); i++) {
            TableFactory tableFactory = new TableFactory(configuration.getTables().get(i), configuration);
            Table table = tableFactory.produce();
            tableList.add(table);
        }
        getLog().info("mybatis-annotation-plugins产生model文件");

        Factory factory = new Factory(configuration,tableList);
        factory.produce();


        getLog().info(JSON.toJSONString(configuration));

    }
}
