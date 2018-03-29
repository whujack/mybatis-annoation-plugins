package com.github.whujack.factory;

import com.github.whujack.config.Configuration;
import com.github.whujack.constant.GlobalConstant;
import com.github.whujack.model.Interface;
import com.github.whujack.model.Table;
import com.github.whujack.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author Created By LiJie at 2018/3/16
 */
public class DaoFactory implements AbstractFactory {
    private static final Logger logger = LoggerFactory.getLogger(DaoFactory.class);
    private List<Table> tableList;
    private Configuration configuration;

    public DaoFactory(Configuration configuration, List<Table> tableList) {
        this.configuration = configuration;
        this.tableList = tableList;
    }

    @Override
    public Object produce() {
        File file = GlobalConstant.BASE_DIR_FILE;
        String path = file.getAbsolutePath() + "/" + configuration.getPackages().getDao().getTarget() + "/" +
                configuration.getPackages().getDao().getName().replaceAll("\\.", "/") + "/";
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
        logger.info(file.getAbsolutePath());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Interface inter = new Interface();
        inter.setName(name);
        inter.setPackageName(configuration.getPackages().getDao().getName());
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(inter.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}