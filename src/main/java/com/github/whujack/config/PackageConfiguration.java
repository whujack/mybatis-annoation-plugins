package com.github.whujack.config;

/**
 * @author Created By LiJie at 2018/3/29
 */
public class PackageConfiguration {
    private FileConfiguration model;
    private FileConfiguration dao;
    private FileConfiguration mapper;

    public FileConfiguration getModel() {
        return model;
    }

    public PackageConfiguration setModel(FileConfiguration model) {
        this.model = model;
        return this;
    }

    public FileConfiguration getDao() {
        return dao;
    }

    public PackageConfiguration setDao(FileConfiguration dao) {
        this.dao = dao;
        return this;
    }

    public FileConfiguration getMapper() {
        return mapper;
    }

    public PackageConfiguration setMapper(FileConfiguration mapper) {
        this.mapper = mapper;
        return this;
    }
}
