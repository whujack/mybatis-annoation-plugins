package edu.whu.utils;


import edu.whu.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * @author Created By LiJie at 2018/03/14
 */
public class DBUtils {
    private static final Logger logger = LoggerFactory.getLogger(DBUtils.class);

    /**
     * 获取数据库连接
     *
     * @param driver   驱动
     * @param url      url
     * @param username 用户名
     * @param password 密码
     * @return 数据库连接
     * @throws Exception 异常
     */
    public static Connection getConnection(String driver, String url, String username, String password) {
        Connection connection = null;
        try {
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url, username, password);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    /**
     * 执行sql语句
     *
     * @param configuration db配置
     * @param sql           sql语句
     * @return 返回结果集
     */
    public static ResultSet execute(Configuration configuration, String sql) {
        Connection connection = getConnection(configuration.getDriver(), configuration.getUrl(), configuration.getUsername(), configuration.getPassword());
        if (connection == null) {
            logger.error("获取数据库连接失败，请检查配置文件！");
            System.exit(1);
        }
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void main(String[] args) {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://10.10.196.7:3306/focus_sale_dev";
        String username = "root";
        String password = "dev#pass";
        try {
            Connection connection = getConnection(driver, url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement("show tables;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.print(resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
