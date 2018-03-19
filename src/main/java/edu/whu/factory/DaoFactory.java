package edu.whu.factory;

import edu.whu.config.Configuration;
import edu.whu.model.Table;

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
        return null;
    }
}
