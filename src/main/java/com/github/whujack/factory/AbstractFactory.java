package com.github.whujack.factory;

/**
 * @author Created By LiJie at 2018/3/16
 */
public interface AbstractFactory<T> {

    /**
     *
     * @return 工厂对象
     */
    T produce();
}
