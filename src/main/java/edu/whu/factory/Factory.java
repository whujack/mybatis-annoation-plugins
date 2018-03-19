package edu.whu.factory;

import edu.whu.config.Configuration;
import edu.whu.model.Table;

import java.util.List;

/**
 * @author Created By LiJie at 2018/3/17
 */
public class Factory implements AbstractFactory {
    private Configuration configuration;
    private List<Table> tableList;

    public Factory(Configuration configuration, List<Table> tableList) {
        this.configuration = configuration;
        this.tableList = tableList;
    }


    @Override
    public Object produce() {
        AbstractFactory modelFactory = new ModelFactory(configuration, tableList);
        modelFactory.produce();
        AbstractFactory mapperFactory = new MapperFactory(configuration, tableList);
        mapperFactory.produce();
        AbstractFactory daoFactory = new DaoFactory(configuration, tableList);
        daoFactory.produce();
        return null;
    }


}
