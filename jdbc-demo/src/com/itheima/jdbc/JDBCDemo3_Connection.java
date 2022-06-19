package com.itheima.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
/**
 * lib文件夹一定要放在模块下面的文件夹里面，不要放在工程的文件夹下，否则就会报错！
 */

/**
 * JDBC快速入门
 */
public class JDBCDemo3_Connection {
    public static void main(String[] args) throws Exception{
        // 1. 注册驱动  可以不写下面这行代码
        //Class.forName("com.mysql.cj.jdbc.Driver"); // 需要抛出异常 ,

        // 2.获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/db1?serverTimezone=GMT";
        String username = "root";
        String password = "111111";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 3. 定义sql语句
        String sql1 = "update account set money = 3000 where id = 1";
        String sql2 = "update account set money = 3000 where id = 2";

        // 4. 获取执行sql的对象
        Statement stmt = conn.createStatement();

        try {
            //开启事务
            conn.setAutoCommit(false);

            // 5. 执行sql
            int count1 = stmt.executeUpdate(sql1);

            // 6. 处理结果
            System.out.println("受影响行数：" + count1);

            // 制造异常，来判断事务的特性：ACID原子性一致性隔离性持久性，如果这里出现异常，name修改的第一条数据不会生效，也就是原子性
            // 事务中的操作，要么全部成功，要么全部失败，出现异常就调用conn.rollback()回滚事务。
//            int i = 3/0;

            // 5. 执行sql
            int count2 = stmt.executeUpdate(sql2);

            // 6. 处理结果
            System.out.println("受影响行数：" + count2);

            // 提交事务
            conn.commit();

        } catch (Exception e) {
            // 回滚事务
            conn.rollback();

            e.printStackTrace();
        }



        // 7. 释放资源
        stmt.close();
        conn.close();
    }
}
