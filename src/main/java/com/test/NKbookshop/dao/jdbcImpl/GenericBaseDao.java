package com.test.NKbookshop.dao.jdbcImpl;

import com.test.NKbookshop.utils.DBUtil;

import java.sql.*;

// 具体DAO实现的公有父类，数据库访问的共性操作
// 不处理异常抛出抛出异常给调用者（具体子类）处理
public class GenericBaseDao {
    //声明私有静态属性，获取|保存数据库连接配置参数
    private static String driver = DBUtil.diver;
    private static String url = DBUtil.url;
    private static String user = DBUtil.user;
    private static String password = DBUtil.password;

    //静态代码块首次执行并执行一次
    //借助static code 完成一次性的注册
    static {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //共性操作：获取连接，执行普适的增删改方法\执行普适的查询方法\关闭连接
    //声明JDBC基础接口对象，因为是父类中的属性可能被子类使用，声明为protected
    protected Connection conn;
    protected PreparedStatement pstmt;
    protected ResultSet rs;
    protected int result = -1;

    //2-1.获取连接方法，异常留给子类处理，谁调用谁处理
    public void getConnection() throws SQLException {
        conn = DriverManager.getConnection(url, user, password);
    }

    //2-2.关闭连接
    public void closeALL() throws SQLException {
        //先判断（只有在非空，并且没有关闭的情况下）再关闭
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
        if (pstmt != null && !pstmt.isClosed()) {
            pstmt.close();
        }
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
        if (result != -1) {
            result = -1;
        }
    }

    //2-3.普适的增删改方法
    //生成sql语句的容器对象，放入sql语句，执行获取返回结果
    public void executeUpdate(String sql, final Object... params) throws SQLException {
        //0- 生成sql语句的容器对象
        pstmt = conn.prepareStatement(sql);
        //1- 判断是否有参数，如果有，逐个遍历参数数组，将参数逐个传入sql语句对象中
        if (params != null) {//表示有参数传入，开始遍历，进行参数设置
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
        }
        //2- 执行sql语句（增删改的语句），将返回结果放入result中
        result = pstmt.executeUpdate();
    }

    //2-4，普适的查询方法，生成sql语句的容器对象，放入sql语句，执行并获取返回结果
    public void executeQuery(String sql, final Object... params) throws SQLException {
        //0- 生成sql语句的容器对象
        pstmt = conn.prepareStatement(sql);
        //1- 判断是否有参数，如果有，逐个遍历参数数组，将参数逐个传入sql语句对象中
        if (params != null) {//表示有参数传入，开始遍历，进行参数设置
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
        }
        //2- 执行sql语句（增删改的语句），将返回结果放入result中
        rs = pstmt.executeQuery();
    }
}
