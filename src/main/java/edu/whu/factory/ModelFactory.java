package edu.whu.factory;

import edu.whu.config.Configuration;
import edu.whu.constant.GlobalConstant;
import edu.whu.model.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

/**
 * @author Created By LiJie at 2018/3/16
 */
public class ModelFactory implements AbstractFactory {
    private final static Logger logger = LoggerFactory.getLogger(ModelFactory.class);
    private Configuration configuration;
    private List<Table> tableList;

    public ModelFactory(Configuration configuration, List<Table> tableList) {
        this.configuration = configuration;
        this.tableList = tableList;
    }


    @Override
    public Object produce() {
        String pack = configuration.getModel();
        File baseFile = GlobalConstant.BASE_DIR_FILE;
        String path = baseFile.getAbsolutePath() + "/src/main/java/" + pack.replaceAll("\\.", "/");
        for (int i = 0; configuration.getTables() != null && i < configuration.getTables().size(); i++) {
            // createTableModel(path,configuration);
        }


        return true;
    }

    private void createTableModel(String basePath, Table table) {


    }

}
