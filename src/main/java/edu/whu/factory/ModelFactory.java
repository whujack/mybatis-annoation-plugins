package edu.whu.factory;

import edu.whu.config.Configuration;
import edu.whu.constant.GlobalConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Created By LiJie at 2018/3/16
 */
public class ModelFactory implements AbstractFactory {
    private final static Logger logger = LoggerFactory.getLogger(ModelFactory.class);
    private Configuration configuration;

    public ModelFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Object produce() {
        String pack = configuration.getModel();
        //String path= GlobalConstant.BASE_DIR+"/"+pack.replaceAll(".","\\");
        //logger.info(GlobalConstant.BASE_DIR);
        return null;
    }

}
