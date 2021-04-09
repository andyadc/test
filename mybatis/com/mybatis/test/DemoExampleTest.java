package com.mybatis.test;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.jupiter.api.Test;
import com.mybatis.test.example.Demo;
import com.mybatis.test.example.DemoMapper;

import java.io.InputStream;

/**
 * @author andy.an
 * @since 2019/4/10
 */
class DemoExampleTest {

    @Test
    void testQueryWithXML() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        DemoMapper demoMapper = sqlSession.getMapper(DemoMapper.class);
        Demo demo = demoMapper.selectByPrimaryKey(220L);
        System.out.println(demo.getId() + ", " + demo.getName() + ", " + demo.getCreateTime());

        sqlSession.close();
    }

    /**
     * Java编码方式配置mybatis
     */
    @Test
    void testQueryWithoutXML() {
        // 构建数据库连接池
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://www.qq-server.com:3307/idea?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("andyadC7,./!");

        // 构建数据库事务
        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        // 构建mybatis环境
        Environment environment = new Environment("development", transactionFactory, dataSource);

        //构建配置环境
        Configuration configuration  = new Configuration(environment);

        //注册别名
        configuration.getTypeAliasRegistry().registerAlias("demo", Demo.class);

        //加入映射器对象, 如果存在一个同名 XML 配置文件，MyBatis 会自动查找并加载它
        configuration.addMapper(DemoMapper.class);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        DemoMapper demoMapper = sqlSession.getMapper(DemoMapper.class);
        Demo demo = demoMapper.queryByPrimaryKey(220L);
        System.out.println(demo.getId() + ", " + demo.getName() + ", " + demo.getCreateTime());

        sqlSession.close();
    }
}
