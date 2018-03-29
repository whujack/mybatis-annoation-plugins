package com.github.whujack.config;

/**
 * @author Created By LiJie at 2018/3/29
 */
public class DataBaseConfiguration {
    private String driver;
    private String uri;
    private String username;
    private String password;


    public String getDriver() {
        return driver;
    }

    public DataBaseConfiguration setDriver(String driver) {
        this.driver = driver;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public DataBaseConfiguration setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public DataBaseConfiguration setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public DataBaseConfiguration setPassword(String password) {
        this.password = password;
        return this;
    }
}
