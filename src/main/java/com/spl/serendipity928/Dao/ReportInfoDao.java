package com.spl.serendipity928.Dao;

import com.spl.serendipity928.Pojo.ReportInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

//@Repository
@Mapper
public interface ReportInfoDao {
    ReportInfo selectByPrimaryKey(long id);

    int insertReportInfo(ReportInfo record);

}

// Spring Boot 连接MySql数据库 ： https://blog.csdn.net/java_2017_csdn/article/details/78467821
