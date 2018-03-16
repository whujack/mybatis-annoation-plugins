package edu.whu.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Created By LiJie at 2018/03/14
 */
public class DBUtils {

    /**
     * 获取数据库连接
     * @param driver 驱动
     * @param url url
     * @param username 用户名
     * @param password 密码
     * @return 数据库连接
     * @throws Exception 异常
     */
    public static Connection getConnection(String driver, String url, String username, String password) throws Exception {
        Class.forName(driver).newInstance();
        return DriverManager.getConnection(url, username, password);
    }


    public static void main(String[] args){
        String driver="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://10.10.196.7:3306/focus_sale_dev";
        String username="root";
        String password="dev#pass";
        try {
            Connection connection=getConnection(driver,url,username,password);
            PreparedStatement preparedStatement=connection.prepareStatement("show tables;");
            ResultSet resultSet=preparedStatement.executeQuery();
            System.out.print(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
