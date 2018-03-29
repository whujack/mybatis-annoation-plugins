package com.github.whujack.factory;

import com.github.whujack.config.Configuration;
import com.github.whujack.constant.GlobalConstant;
import com.github.whujack.model.Table;
import com.github.whujack.model.Xml;
import com.github.whujack.utils.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author Created By LiJie at 2018/3/16
 */
public class MapperFactory implements AbstractFactory {
    private List<Table> tableList;
    private Configuration configuration;

    public MapperFactory(Configuration configuration, List<Table> tableList) {
        this.configuration = configuration;
        this.tableList = tableList;
    }

    @Override
    public Object produce() {
        String mapperDir = configuration.getPackages().getMapper().getName();
        File file = GlobalConstant.BASE_DIR_FILE;
        String path = file.getAbsolutePath() + "/" + "src/main/resources/" + mapperDir;
        file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        for (int i = 0; i < tableList.size(); i++) {
            createMapper(path, tableList.get(i));
        }

        return null;
    }

    public void createMapper(String path, Table table) {
        File file = new File(path + "/" + StringUtils.firstToUpper(StringUtils.toCamelCase(table.getName())) + "Mapper.xml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String name = StringUtils.firstToUpper(StringUtils.toCamelCase(table.getName()));
        Xml xml = new Xml();
        xml.setColumnList(table.getColumns());
        xml.setNamespace(configuration.getPackages().getDao().getName() + "." + name+"Mapper");
        xml.setResultMapId("BaseResultMap");
        xml.setResultMapType(configuration.getPackages().getModel().getName() + "." + name);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(xml.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}