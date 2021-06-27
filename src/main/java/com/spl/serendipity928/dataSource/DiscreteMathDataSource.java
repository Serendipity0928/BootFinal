package com.spl.serendipity928.dataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

//@Configuration
public class DiscreteMathDataSource {

    /**
     *
     * @return 返回dm数据库的数据源
     */
    @Bean(name="mathSource")
    @ConfigurationProperties(prefix = "spring.datasource.math")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     *
     * @param dm dm数据源
     * @return 返回dm数据库的会话工厂
     */
    @Bean(name = "mathSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("mathSource") DataSource dm) throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dm);
        return bean.getObject();
    }

    /**
     *
     * @param sessionFactory dm数据库会话工厂
     * @return 返回dm数据库会话模板
     */
    @Bean(name = "mathSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("mathSqlSessionFactory") SqlSessionFactory sessionFactory) {
        return new SqlSessionTemplate(sessionFactory);
    }

    /**
     *
     * @param dm dm数据源
     * @return 返回dm数据库管理器
     */
    @Bean(name = "mathTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("mathSource") DataSource dm){
        return new DataSourceTransactionManager(dm);
    }


}
