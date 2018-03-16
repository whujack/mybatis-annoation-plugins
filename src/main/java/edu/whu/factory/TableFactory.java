package edu.whu.factory;

import edu.whu.model.Table;
import edu.whu.model.TableConfiguration;

/**
 * @author Created By LiJie at 2018/3/16
 */
public class TableFactory implements AbstractFactory<Table> {
    private TableConfiguration tableConfiguration;

    public TableFactory(TableConfiguration tableConfiguration) {
        this.tableConfiguration = tableConfiguration;
    }


    @Override
    public Table produce() {
        Table table = new Table();
        table.setTableConfiguration(tableConfiguration);
        table.setName(tableConfiguration.getName());

        return null;
    }

}
