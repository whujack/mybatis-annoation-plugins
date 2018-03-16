package edu.whu;

import com.alibaba.fastjson.JSON;
import edu.whu.factory.TableFactory;
import edu.whu.model.Configuration;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

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


    @Override
    public void execute() throws MojoExecutionException {
        //读取配置文件
        Configuration configuration = new Configuration(configurationFile);

        //工厂模式读取table
        for (int i = 0; i < configuration.getTables().size(); i++) {
            TableFactory tableFactory = new TableFactory(configuration.getTables().get(i), configuration);
            tableFactory.produce();
        }


        getLog().info(JSON.toJSONString(configuration));

    }
}
