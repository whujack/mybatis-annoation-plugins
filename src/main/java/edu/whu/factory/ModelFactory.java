package edu.whu.factory;

import edu.whu.config.Configuration;
import edu.whu.config.TableConfiguration;
import edu.whu.constant.GlobalConstant;
import edu.whu.model.Clazz;
import edu.whu.model.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
        for (int i = 0; tableList != null && i < tableList.size(); i++) {
            createTableModel(path, tableList.get(i), getTableConfiguration(tableList.get(i), configuration.getTables()));
        }


        return true;
    }

    /**
     * 根据 table和config产生model文件
     *
     * @param basePath      基础path
     * @param table         table
     * @param configuration 配置文件
     */
    private void createTableModel(String basePath, Table table, TableConfiguration configuration) {
        File file = new File(basePath);
        logger.info(file.getAbsolutePath());
        //不存在时创建文件
        if (!file.exists()) {
            file.mkdirs();
        }
        Clazz clazz = new Clazz();
        clazz.setClassName(table.getClazzName());
        clazz.setPackageName(this.configuration.getModel());
        clazz.setColumnList(table.getColumns());
        List<String> importClazz = new ArrayList<>();
        for (int i = 0; i < table.getColumns().size(); i++) {
            Table.Column column = table.getColumns().get(i);
            if (column != null && column.getType() != null) {
                if(!isInArray(importClazz,column.getType().getPackageName())) {
                    importClazz.add(column.getType().getPackageName());
                }
            }
        }
        clazz.setImportClazz(importClazz);

        file = new File(basePath + "/" + table.getClazzName() + ".java");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
           // logger.info(clazz.toString());
            writer.append(clazz.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取table config
     *
     * @param table               table
     * @param tableConfigurations config
     * @return config
     */
    private TableConfiguration getTableConfiguration(final Table table, List<TableConfiguration> tableConfigurations) {
        if (table == null) {
            return null;
        }
        for (int i = 0; tableConfigurations != null && i < tableConfigurations.size(); i++) {
            if (tableConfigurations.get(i).getName().equals(table.getName())) {
                return tableConfigurations.get(i);
            }
        }
        return null;
    }

    private boolean isInArray(List<String> list, String tmp) {
        if (list == null || tmp == null) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(tmp)) {
                return true;
            }
        }
        return false;
    }

}
