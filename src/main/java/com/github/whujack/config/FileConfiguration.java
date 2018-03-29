package com.github.whujack.config;

/**
 * @author Created By LiJie at 2018/3/29
 */
public class FileConfiguration {
    private String name;
    private String target;

    public String getName() {
        return name;
    }

    public FileConfiguration setName(String name) {
        this.name = name;
        return this;
    }

    public String getTarget() {
        return target;
    }

    public FileConfiguration setTarget(String target) {
        this.target = target;
        return this;
    }
}
