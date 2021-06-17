package com.spl.serendipity928.dao;

import com.spl.serendipity928.pojo.ReportInfo;
import org.apache.ibatis.annotations.Mapper;

//@Repository
@Mapper
public interface ReportInfoDao {
    /**
     * 根据主键查询记录，可废弃
     * @param id
     * @return
     */
    ReportInfo selectByPrimaryKey(long id);

    /**
     * 可能会废弃掉
     * @param record
     * @return
     */
    int insertReportInfo(ReportInfo record);

    /**
     * 更新数据接口
     * @param record
     * @return
     */
    int updateReportInfo(ReportInfo record);

}

// Spring Boot 连接MySql数据库 ： https://blog.csdn.net/java_2017_csdn/article/details/78467821
