package com.itheima.jdbc;

import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
/**
 * lib文件夹一定要放在模块下面的文件夹里面，不要放在工程的文件夹下，否则就会报错！
 */

/**
 * JDBC API 详解:Statement
 */
public class JDBCDemo4_Statement {
    /**
     * 执行DML语句  对表中的数据进行操作
     * @throws Exception
     */
    @Test
    public void testDML() throws Exception{
        // 1. 注册驱动  可以不写下面这行代码
        //Class.forName("com.mysql.cj.jdbc.Driver"); // 需要抛出异常 ,

        // 2.获取连接：如果连接的是本机mysql且端口是默认的3306 可以简化书写
        String url = "jdbc:mysql:///db1"; //可以在db1后面加上这段代码 ?serverTimezone=GMT
        String username = "root";
        String password = "111111";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 3. 定义sql语句
        String sql = "update account set money = 4000 where id = 1";

        // 4. 获取执行sql的对象
        Statement stmt = conn.createStatement();

        // 5. 执行sql
        int count = stmt.executeUpdate(sql); // 执行完DML语句，受影响的行数

        // 6. 处理结果
//        System.out.println("受影响行数：" + count);
        if(count > 0){
            System.out.println("修改成功~~");
        }else{
            System.out.println("修改失败~~");
        }

        // 7. 释放资源
        stmt.close();
        conn.close();
    }

    /**
     * 执行DDL语句 对数据库和表进行操作
     * @throws Exception
     */
    @Test
    public void testDDL() throws Exception{
        // 1. 注册驱动  可以不写下面这行代码
        //Class.forName("com.mysql.cj.jdbc.Driver"); // 需要抛出异常 ,

        // 2.获取连接：如果连接的是本机mysql且端口是默认的3306 可以简化书写
        String url = "jdbc:mysql:///db1"; //可以在db1后面加上这段代码 ?serverTimezone=GMT
        String username = "root";
        String password = "111111";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 3. 定义sql语句
        String sql = "drop database db2";

        // 4. 获取执行sql的对象
        Statement stmt = conn.createStatement();

        // 5. 执行sql
        int count = stmt.executeUpdate(sql); // 执行完DDL语句，受影响的行数

        // 6. 处理结果
        System.out.println("受影响行数：" + count); // 执行DDL语句，可能是0，例如删除数据库，若执行DML，大于0一定是成功，反之失败
//        if(count > 0){
//            System.out.println("修改成功~~");
//        }else{
//            System.out.println("修改失败~~");
//        }

        // 7. 释放资源
        stmt.close();
        conn.close();
    }
}
