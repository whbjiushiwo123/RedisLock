package com.whb.util;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

public interface DbConfig {
    /**
     * 定义数据源
     *
     * @return
     * @throws Exception
     */


    /**
     * 定义session工厂
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    SqlSessionFactory sqlSessionFactory(DataSource dataSource)throws Exception;

    /**
     * 定义事务管理
     * @param dataSource
     * @return
     * @throws Exception
     */
    DataSourceTransactionManager tansacationManaer(DataSource dataSource) throws Exception;
}
