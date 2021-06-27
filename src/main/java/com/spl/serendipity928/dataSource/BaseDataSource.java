package com.spl.serendipity928.dataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

//@Configuration
//@MapperScan(basePackages = "com.springboot.data1.mapper",sqlSessionFactoryRef = "data1SqlSessionFactory")
//https://www.cnblogs.com/lijianda/p/11022892.html springBoot配置多个数据源
//https://www.jianshu.com/p/6d31b8476f6d
//https://www.jianshu.com/c/f4e04ac84c2c
public class BaseDataSource {

    /**
     *
     * @return 返回主数据库的数据源
     */
    @Bean(name="BaseSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.base")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     *
     * @param ba 主数据源
     * @return 返回主数据库的会话工厂
     */
    @Bean(name = "baseSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("BaseSource") DataSource ba) throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(ba);
        return bean.getObject();
    }

    /**
     *
     * @param sessionFactory 主数据库会话工厂
     * @return 返回主数据库会话模板
     */
    @Bean(name = "baseSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("baseSqlSessionFactory") SqlSessionFactory sessionFactory) {
        return new SqlSessionTemplate(sessionFactory);
    }

    /**
     *
     * @param ba 主数据源
     * @return 返回主数据库管理器
     */
    @Bean(name = "baseTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("BaseSource") DataSource ba){
        return new DataSourceTransactionManager(ba);
    }

}
