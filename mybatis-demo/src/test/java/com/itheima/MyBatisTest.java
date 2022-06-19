package com.itheima;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisTest {

    @Test
    public void testSelectAll() throws IOException {
        // 1. 加载mybaits的核心配置文件， 获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 执行方法
        List<Brand> brands = brandMapper.selectAll();
        System.out.println(brands);

        // 5. 释放资源
        sqlSession.close();
    }

    @Test
    public void testSelectById() throws IOException {
        // 接受参数
        int id = 1;

        // 1. 加载mybaits的核心配置文件， 获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 执行方法
        Brand brands = brandMapper.selectById(id);
        System.out.println(brands);

        // 5. 释放资源
        sqlSession.close();
    }

    @Test
    public void testSelectByCondition() throws IOException {
        // 接受参数
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";

        // 处理参数
        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";

        // 第二种方法：封装对象
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);

        // 第三种方法：Map
        Map map = new HashMap<>();
//        map.put("status",status);
        map.put("companyName", companyName);
//        map.put("brandName",brandName);

        // 1. 加载mybaits的核心配置文件， 获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 执行方法
//        List<Brand> brands = brandMapper.selectByCondition(status,companyName,brandName);
//        List<Brand> brands = brandMapper.selectByCondition(brand);
        List<Brand> brands = brandMapper.selectByCondition(map);
        System.out.println(brands);

        // 5. 释放资源
        sqlSession.close();
    }

    @Test
    public void testSelectByConditionSingle() throws IOException {
        // 接受参数
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";

        // 处理参数
        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";

        // 第二种方法：封装对象
        Brand brand = new Brand();
//        brand.setStatus(status);
//        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);

        // 1. 加载mybaits的核心配置文件， 获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 执行方法
        List<Brand> brands = brandMapper.selectByConditionSingle(brand);
        System.out.println(brands);

        // 5. 释放资源
        sqlSession.close();
    }

    @Test
    public void testAdd() throws IOException {
        // 接受参数
        int status = 1;
        String companyName = "波导手机";
        String brandName = "波导";
        String description = "手机中的战斗机";
        int ordered = 100;

        // 第二种方法：封装对象
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        brand.setDescription(description);
        brand.setOrdered(ordered);

        // 1. 加载mybaits的核心配置文件， 获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession对象，用它来执行sql
//        SqlSession sqlSession = sqlSessionFactory.openSession();
        // openSession()默认开启事务，传递的参数为true代表自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        // 3. 获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 执行方法
        brandMapper.add(brand);
        // 手动提交事务，否则数据修改不成功，若在获取sqlsession时就添加了参数true，则不需要下面这行代码
//        sqlSession.commit();
        // 5. 释放资源
        sqlSession.close();
    }

    @Test
    public void testAdd2() throws IOException {
        // 接受参数
        int status = 1;
        String companyName = "波导手机";
        String brandName = "波导";
        String description = "手机中的战斗机";
        int ordered = 100;

        // 第二种方法：封装对象
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        brand.setDescription(description);
        brand.setOrdered(ordered);

        // 1. 加载mybaits的核心配置文件， 获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession对象，用它来执行sql
//        SqlSession sqlSession = sqlSessionFactory.openSession();
        // openSession()默认开启事务，传递的参数为true代表自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        // 3. 获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 执行方法
        brandMapper.add(brand);
        System.out.println(brand.getId());
        // 手动提交事务，否则数据修改不成功，若在获取sqlsession时就添加了参数true，则不需要下面这行代码
//        sqlSession.commit();
        // 5. 释放资源
        sqlSession.close();
    }

    @Test
    public void testUpdate() throws IOException {
        // 接受参数
        int status = 0;
        String companyName = "波导手机";
        String brandName = "波导";
        String description = "波导手机，手机中的战斗机";
        int ordered = 200;
        int id = 8;

        // 第二种方法：封装对象
        Brand brand = new Brand();
        brand.setStatus(status);
//        brand.setCompanyName(companyName);
//        brand.setBrandName(brandName);
//        brand.setDescription(description);
//        brand.setOrdered(ordered);
        brand.setId(id);

        // 1. 加载mybaits的核心配置文件， 获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession对象，用它来执行sql
//        SqlSession sqlSession = sqlSessionFactory.openSession();
        // openSession()默认开启事务，传递的参数为true代表自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        // 3. 获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 执行方法
        int count = brandMapper.update(brand);
        System.out.println("影响的行数：" + count);
        // 手动提交事务，否则数据修改不成功，若在获取sqlsession时就添加了参数true，则不需要下面这行代码
//        sqlSession.commit();
        // 5. 释放资源
        sqlSession.close();
    }

    @Test
    public void testDeleteById() throws IOException {
        // 接受参数
        int id = 9;

        // 1. 加载mybaits的核心配置文件， 获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession对象，用它来执行sql
//        SqlSession sqlSession = sqlSessionFactory.openSession();
        // openSession()默认开启事务，传递的参数为true代表自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        // 3. 获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        brandMapper.deleteById(id);
        // 手动提交事务，否则数据修改不成功，若在获取sqlsession时就添加了参数true，则不需要下面这行代码
//        sqlSession.commit();
        // 5. 释放资源
        sqlSession.close();
    }

    @Test
    public void testDeleteByIds() throws IOException {
        // 接受参数
        int[] ids = {6, 7, 8};

        // 1. 加载mybaits的核心配置文件， 获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession对象，用它来执行sql
//        SqlSession sqlSession = sqlSessionFactory.openSession();
        // openSession()默认开启事务，传递的参数为true代表自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        // 3. 获取Mapper接口的代理对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        brandMapper.deleteByIds(ids);
        // 手动提交事务，否则数据修改不成功，若在获取sqlsession时就添加了参数true，则不需要下面这行代码
//        sqlSession.commit();
        // 5. 释放资源
        sqlSession.close();
    }
}
