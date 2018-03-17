package edu.whu.factory;

import edu.whu.config.Configuration;

/**
 * @author Created By LiJie at 2018/3/17
 */
public class Factory implements AbstractFactory {
    private Configuration configuration;

    public Factory(Configuration configuration) {
        this.configuration = configuration;
    }


    @Override
    public Object produce() {
        ModelFactory modelFactory = new ModelFactory(configuration);
        modelFactory.produce();
        return null;
    }


}
