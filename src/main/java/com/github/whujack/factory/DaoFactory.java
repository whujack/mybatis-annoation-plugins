package com.github.whujack.factory;

import com.github.whujack.config.Configuration;
import com.github.whujack.constant.GlobalConstant;
import com.github.whujack.model.Clazz;
import com.github.whujack.model.Interface;
import com.github.whujack.model.Table;
import com.github.whujack.utils.StringUtils;
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
        List<String> importList = new ArrayList<>();
        importList.add("org.apache.ibatis.annotations.ResultMap");
        importList.add("org.apache.ibatis.annotations.SelectProvider");
        importList.add("org.apache.ibatis.annotations.Param");
        importList.add(configuration.getPackages().getModel().getName() + "." + table.getClazzName());
        Clazz sqlProvider = new Clazz();
        sqlProvider.setClassName("SqlProvider");
        sqlProvider.setClassType(null);
        List<String> interMethodList = new ArrayList<>();
        List<String> providerMethodList = new ArrayList<>();
        if (table.getTableConfiguration().getEnableSelect()) {
            String method = "@SelectProvider(type = SqlProvider.class, method = \"selectBy"+table.getClazzName()+"\")\n" +
                    "\t@ResultMap(\"BaseResultMap\")\n\tList<" + table.getClazzName() +
                    "> selectBy"+table.getClazzName()+"(@Param(\"" + StringUtils.toCamelCase(table.getName()) + "\")" + table.getClazzName() + " " + StringUtils.toCamelCase(table.getName()) + ");";
            interMethodList.add(method);
            String sqlMethod = "\tpublic String selectBy" + table.getClazzName() + "(" + table.getClazzName() + " " + StringUtils.toCamelCase(table.getName()) + "){\n" +
                    "\t\tString sql=\"SELECT * FROM " + table.getName() + " WHERE 1=1\";\n";
            String objectName=StringUtils.toCamelCase(table.getName());
            for (Table.Column column : table.getColumns()) {
                if(column.getName()!=null) {
                    sqlMethod += "\t\tif(" + objectName + "." + StringUtils.toGetterName(column.getName()) + "()!=null) sql+=\" AND "
                            + column.getName() + "=\"+" + objectName + "." + StringUtils.toGetterName(column.getName()) + "();\n";
                }
            }
            sqlMethod += "\n\t\treturn sql;\n\t}\n";
            providerMethodList.add(sqlMethod);
            importList.add("java.util.List");
        }
        inter.setImportPackage(importList);
        sqlProvider.setMethodList(providerMethodList);
        inter.setMethodList(interMethodList);
        inter.setSqlProvider(sqlProvider);
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