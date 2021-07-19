package com.test.NKbookshop.utils;

import java.io.IOException;
import java.util.Properties;

public class DBUtil {

    private static Properties prop = new Properties();

    static {
        try {
            prop.load(DBUtil.class.getClassLoader().getResourceAsStream("dbconfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //声明公有属性
    public static String diver = prop.getProperty("jdbc.driver");
    public static String url =prop.getProperty("jdbc.url") ;
    public static String user = prop.getProperty("jdbc.user");
    public static String password =prop.getProperty("jdbc.password") ;
}
