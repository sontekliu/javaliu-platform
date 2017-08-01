package com.javaliu.platform.utils;

import com.javaliu.platform.threadlocal.ConnectionContextHook;
import org.apache.commons.dbutils.DbUtils;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库工具类
 */
public class DBUtils {

    /**
     * 获取数据库连接
     * @param dataSource
     * @return
     */
    public static Connection getConnection(DataSource dataSource){
        Connection connection = ConnectionContextHook.get();
        try {
            if(null == connection || connection.isClosed()){
                connection = DataSourceUtils.getConnection(dataSource);
                ConnectionContextHook.set(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭数据库连接,并释放资源
     */
    public static void releaseConnection(){
        closeConnection();
        ConnectionContextHook.remove();
    }

    /**
     * 关闭数据库连接
     */
    private static void closeConnection(){
        Connection connection = ConnectionContextHook.get();
        DbUtils.closeQuietly(connection);
    }

}
