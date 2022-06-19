package com.itheima.jdbc;

import org.testng.annotations.Test;

import java.sql.*;

/**
 * API详解：PreparedStatement
 */
public class JDBCDemo7_PrepareStatement {

    @Test
    public void testPreparedStatement() throws Exception{
        // 1. 注册驱动  可以不写下面这行代码
        //Class.forName("com.mysql.cj.jdbc.Driver"); // 需要抛出异常 ,

        // 2.获取连接：如果连接的是本机mysql且端口是默认的3306 可以简化书写
        String url = "jdbc:mysql:///db1"; //可以在db1后面加上这段代码 ?serverTimezone=GMT
        String username = "root";
        String password = "111111";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 接受用户输入 用户名和密码
        String name = "zhangsan";
        String pwd = "123";

        // 定义sql
        String sql = "select * from tb_user where username = ? and password = ?";

        // 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        // 设置？的值
        pstmt.setString(1,name);
        pstmt.setString(2,pwd);

        // 执行sql
        ResultSet rs = pstmt.executeQuery();
        // 判断登录是否成功
        if(rs.next()){
            System.out.println("登录成功~");
        }else{
            System.out.println("登录失败~~");
        }
        // 7. 释放资源
        rs.close();
        pstmt.close();
        conn.close();
    }

    /**
     * PreparedStatement原理
     * @throws Exception
     */
    @Test
    public void testPreparedStatement2() throws Exception{
        // 1. 注册驱动  可以不写下面这行代码
        //Class.forName("com.mysql.cj.jdbc.Driver"); // 需要抛出异常 ,

        // 2.获取连接：如果连接的是本机mysql且端口是默认的3306 可以简化书写
        String url = "jdbc:mysql:///db1?useSSL=false&useServerPrepStmts=true"; //可以在db1后面加上这段代码 ?serverTimezone=GMT
        String username = "root";
        String password = "111111";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 接受用户输入 用户名和密码
        String name = "zhangsan";
        String pwd = "123";

        // 定义sql
        String sql = "select * from tb_user where username = ? and password = ?";

        // 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        // 设置？的值
        pstmt.setString(1,name);
        pstmt.setString(2,pwd);

        // 执行sql
        ResultSet rs = pstmt.executeQuery();
        // 判断登录是否成功
        if(rs.next()){
            System.out.println("登录成功~");
        }else{
            System.out.println("登录失败~~");
        }
        // 7. 释放资源
        rs.close();
        pstmt.close();
        conn.close();
    }


}
