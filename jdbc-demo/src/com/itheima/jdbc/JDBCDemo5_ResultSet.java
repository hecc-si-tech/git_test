package com.itheima.jdbc;

import org.testng.annotations.Test;
import pojo.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * lib文件夹一定要放在模块下面的文件夹里面，不要放在工程的文件夹下，否则就会报错！
 */

/**
 * JDBC API 详解:ResultSet
 */
public class JDBCDemo5_ResultSet {
    /**
     * 执行DQL语句  查询数据
     * @throws Exception
     */
    @Test
    public void testResultSet() throws Exception{
        // 1. 注册驱动  可以不写下面这行代码
        //Class.forName("com.mysql.cj.jdbc.Driver"); // 需要抛出异常 ,

        // 2.获取连接：如果连接的是本机mysql且端口是默认的3306 可以简化书写
        String url = "jdbc:mysql:///db1"; //可以在db1后面加上这段代码 ?serverTimezone=GMT
        String username = "root";
        String password = "111111";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 3. 定义sql
        String sql = "select * from account";

        // 4. 获取statement对象
        Statement stmt = conn.createStatement();

        // 5. 执行sql
        ResultSet rs = stmt.executeQuery(sql);

        // 6. 处理结果，遍历rs中的所有数据
        // 光标向下移动一行，并且判断当前行是否有数据
//        while(rs.next()){
//            // 获取数据
//            int id = rs.getInt(1); //括号里可以写列的编号，也可以写列的名称
//            String name = rs.getString(2);
//            double money = rs.getDouble(3);
//            System.out.println("id:" + id + "  姓名:" + name + "  工资: " + money);
//            System.out.println("--------------");
//        }
        while(rs.next()){
            // 获取数据
            int id = rs.getInt("id");
            String name = rs.getString("name");
            double money = rs.getDouble("money");
            System.out.println("id:" + id + "  姓名:" + name + "  工资: " + money);
            System.out.println("--------------");
        }

        // 7. 释放资源
        rs.close();
        stmt.close();
        conn.close();
    }

    /**
     * 查询account账户表数据，封装为Account对象中，并且存储到ArrayList集合中
     * 1. 定义一个实体类Account
     * 2. 查询数据，封装到Account对象中
     * 3. 将Account对象存入到ArrayList集合中
     * @throws Exception
     */
    @Test
    public void testResultSet2() throws Exception{
        // 1. 注册驱动  可以不写下面这行代码
        //Class.forName("com.mysql.cj.jdbc.Driver"); // 需要抛出异常 ,

        // 2.获取连接：如果连接的是本机mysql且端口是默认的3306 可以简化书写
        String url = "jdbc:mysql:///db1"; //可以在db1后面加上这段代码 ?serverTimezone=GMT
        String username = "root";
        String password = "111111";
        Connection conn = DriverManager.getConnection(url, username, password);

        // 3. 定义sql
        String sql = "select * from account";

        // 4. 获取statement对象
        Statement stmt = conn.createStatement();

        // 5. 执行sql
        ResultSet rs = stmt.executeQuery(sql);

        // 定义一个集合存储Account对象
        List<Account> list = new ArrayList<>();
        while(rs.next()){
            // 每查询出一条数据，就创建一个Account对象
            Account account = new Account();
            // 获取数据
            int id = rs.getInt("id");
            String name = rs.getString("name");
            double money = rs.getDouble("money");
            //  赋值
            account.setId(id);
            account.setName(name);
            account.setMoney(money);

            // 存入集合
            list.add(account);
        }
        System.out.println(list);
        // 7. 释放资源
        rs.close();
        stmt.close();
        conn.close();
    }
}
