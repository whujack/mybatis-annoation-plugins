package edu.whu.factory;

import edu.whu.config.Configuration;
import edu.whu.constant.GlobalConstant;
import edu.whu.model.Interface;
import edu.whu.model.Table;
import edu.whu.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Created By LiJie at 2018/3/16
 */
public class DaoFactory implements AbstractFactory {
    private List<Table> tableList;
    private Configuration configuration;

    public DaoFactory(Configuration configuration, List<Table> tableList) {
        this.configuration = configuration;
        this.tableList = tableList;
    }

    @Override
    public Object produce() {
        File file = GlobalConstant.BASE_DIR_FILE;
        String path = file.getAbsolutePath() + "/" + configuration.getDao().replaceAll("\\.", "/") + "/";
        file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        for (int i = 0; tableList != null && i < tableList.size(); i++) {
            createDao(path, tableList.get(i));
        }


        return null;
    }

    private void createDao(String basePath, Table table) {
        String name = StringUtils.firstToUpper(StringUtils.toCamelCase(table.getName())) + "Mapper";
        File file = new File(basePath + name + ".java");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Interface inter = new Interface();
        inter.setName(name);
        inter.setPackageName(configuration.getDao());

    }
}
