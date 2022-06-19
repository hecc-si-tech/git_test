package com.itheima.jdbc;

import org.testng.annotations.Test;
import pojo.Account;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * 用户登录
 */
public class JDBCDemo6_UserLogin {

    @Test
    public void testLogin() throws Exception{
        // 1. 注册驱动  可以不写下面这行代码
        //Class.forName("com.mysql.cj.jdbc.Driver"); // 需要抛出异常 ,

        // 2.获取连接：如果连接的是本机mysql且端口是默认的3306 可以简化书写
        String url = "jdbc:mysql:///db1"; //可以在db1后面加上这段代码 ?serverTimezone=GMT
        String username = "root";
        String password = "111111";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 接受用户输入 用户名和密码
        String name = "zhangsan";
        String pwd = "1233";

        String sql = "select * from tb_user where username = '"+name+"' and password = '"+pwd+"'";

        // 获取stmt对象
        Statement stmt = conn.createStatement();

        // 执行ssql
        ResultSet rs = stmt.executeQuery(sql);

        // 判断登录是否成功
        if(rs.next()){
            System.out.println("登录成功~");
        }else{
            System.out.println("登录失败~~");
        }
        // 7. 释放资源
        rs.close();
        stmt.close();
        conn.close();
    }

    /**
     * 演示sql注入
     * @throws Exception
     */
    @Test
    public void testLogin_Inject() throws Exception{
        // 1. 注册驱动  可以不写下面这行代码
        //Class.forName("com.mysql.cj.jdbc.Driver"); // 需要抛出异常 ,

        // 2.获取连接：如果连接的是本机mysql且端口是默认的3306 可以简化书写
        String url = "jdbc:mysql:///db1"; //可以在db1后面加上这段代码 ?serverTimezone=GMT
        String username = "root";
        String password = "111111";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 接受用户输入 用户名和密码
        String name = "zhfsdgssan";
        String pwd = "' or '1' = '1";

        String sql = "select * from tb_user where username = '"+name+"' and password = '"+pwd+"'";
        System.out.println(sql);
        // 获取stmt对象
        Statement stmt = conn.createStatement();

        // 执行ssql
        ResultSet rs = stmt.executeQuery(sql);

        // 判断登录是否成功
        if(rs.next()){
            System.out.println("登录成功~");
        }else{
            System.out.println("登录失败~~");
        }
        // 7. 释放资源
        rs.close();
        stmt.close();
        conn.close();
    }
}
