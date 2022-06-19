package com.itheima.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
/**
 * lib文件夹一定要放在模块下面的文件夹里面，不要放在工程的文件夹下，否则就会报错！
 */

/**
 * JDBC API 详解
 */
public class JDBCDemo2_DriverManager {
    public static void main(String[] args) throws Exception{
        // 1. 注册驱动  可以不写下面这行代码
        //Class.forName("com.mysql.cj.jdbc.Driver"); // 需要抛出异常 ,

        // 2.获取连接：如果连接的是本机mysql且端口是默认的3306 可以简化书写
        String url = "jdbc:mysql:///db1"; //可以在db1后面加上这段代码 ?serverTimezone=GMT
        String username = "root";
        String password = "111111";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 3. 定义sql语句
        String sql = "update account set money = 3000 where id = 1";

        // 4. 获取执行sql的对象
        Statement stmt = conn.createStatement();

        // 5. 执行sql
        int count = stmt.executeUpdate(sql);

        // 6. 处理结果
        System.out.println("受影响行数：" + count);

        // 7. 释放资源
        stmt.close();
        conn.close();
    }
}
