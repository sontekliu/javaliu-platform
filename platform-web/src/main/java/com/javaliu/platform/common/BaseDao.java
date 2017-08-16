package com.javaliu.platform.common;

import com.javaliu.platform.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * DAO基类
 */
public class BaseDao<T> {

    /**
     * 日志记录器
     */
    private static final Logger log = LoggerFactory.getLogger(BaseDao.class);
    /**
     * 数据源
     */
    @Autowired
    private DataSource dataSource;

    /**
     * 执行查询，将查询结果保存到Bean中，然后再将Bean保存到list中
     * @param sql           执行的SQL语句（SQL语句中不要拼接条件，防止SQL注入）
     * @param entityClass   实体类对象
     * @param params        执行SQL语句时需要的参数列表
     * @return              查询结果
     * @author  javaliu
     * @date    2016.08.17
     */
    public <T> List<T> find(String sql, Class<T> entityClass, Object[] params) throws SQLException{
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = DBUtils.getConnection(dataSource);
        List<T> list = null;
        if(null == params || params.length == 0){
            list = (List<T>) queryRunner.query(connection, sql, new BeanListHandler(entityClass));
        }else{
            list = (List<T>) queryRunner.query(connection, sql, new BeanListHandler(entityClass), params);
        }
        return list;
    }

    /**
     * 执行查询，将查询结果保存到Bean中，然后再将Bean保存到list中
     * @param sql           执行的SQL语句（SQL语句中不要拼接条件，防止SQL注入）
     * @param entityClass   实体类对象
     * @param param         执行SQL语句时需要的参数列表
     * @return
     * @author  javaliu
     * @date    2016.08.17
     */
    public <T> List<T> find(String sql, Class<T> entityClass, Object param) throws SQLException{
        return find(sql, entityClass, new Object[] {param});
    }

    /**
     * 执行查询，将查询结果保存到Bean中，然后再将Bean保存到list中
     * @param sql           执行的SQL语句（SQL语句中不要拼接条件，防止SQL注入）
     * @param entityClass   实体类对象
     * @return
     * @author  javaliu
     * @date    2016.08.17
     */
    public <T> List<T> find(String sql, Class<T> entityClass) throws SQLException{
        return find(sql, entityClass, null);
    }

    /**
     * 执行查询，将查询结果保存到Bean中
     * @param sql           执行的SQL语句（SQL语句中不要拼接条件，防止SQL注入）
     * @param entityClass   实体类对象
     * @param params        执行SQL语句时需要的参数列表
     * @return
     * @author  javaliu
     * @date    2016.08.17
     */
    public <T> T findOne(String sql, Class<T> entityClass, Object[] params)throws SQLException{
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = DBUtils.getConnection(dataSource);
        T t = null;
        if(null == params || params.length == 0){
            t = (T)queryRunner.query(connection, sql, new BeanHandler(entityClass));
        }else{
            t = (T)queryRunner.query(connection, sql, new BeanHandler(entityClass), params);
        }
        return t;
    }

    /**
     * 执行查询，将查询结果保存到Bean中
     * @param sql           执行的SQL语句（SQL语句中不要拼接条件，防止SQL注入）
     * @param entityClass   实体类对象
     * @param param         执行SQL语句时需要的参数
     * @return
     * @author  javaliu
     * @date    2016.08.17
     */
    public <T> T findOne(String sql, Class<T> entityClass, Object param)throws SQLException{
        return findOne(sql, entityClass, new Object[] {param});
    }

    /**
     * 执行查询，将查询结果保存到Bean中
     * @param sql           执行的SQL语句（SQL语句中不要拼接条件，防止SQL注入）
     * @param entityClass   实体类对象
     * @return
     * @author  javaliu
     * @date    2016.08.17
     */
    public <T> T findOne(String sql, Class<T> entityClass) throws SQLException{
        return findOne(sql, entityClass, null);
    }

    /**
     *  执行查询，将查询结果封装到Map里面
     * @param sql       待执行的SQL语句
     * @param params    SQL执行需要的参数
     * @return          Map对象
     * @author          javaliu
     * @date            2016.08.24
     */
    public Map<String, Object> findMap(String sql, Object[] params)throws SQLException{
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = DBUtils.getConnection(dataSource);
        Map<String, Object> map = null;
        if(null == params || params.length == 0){
            map = queryRunner.query(connection, sql, new MapHandler());
        }else{
            map = queryRunner.query(connection, sql, new MapHandler(), params);
        }
        return map;
    }

    /**
     *  执行查询，将查询结果封装到Map里面
     * @param sql       待执行的SQL语句
     * @param param     SQL执行需要的参数
     * @return          Map对象
     * @author          javaliu
     * @date            2016.08.24
     */
    public Map<String, Object> findMap(String sql, Object param)throws SQLException{
        return this.findMap(sql, new Object[]{param});
    }

    /**
     *  执行查询，将查询结果封装到List<Map<String, Object>>里面
     * @param sql       待执行的SQL语句
     * @param params    SQL执行需要的参数
     * @return          List<Map>对象
     * @author          javaliu
     * @date            2016.08.24
     */
    public List<Map<String, Object>> findMapList(String sql, Object[] params) throws SQLException{
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = DBUtils.getConnection(dataSource);
        List<Map<String, Object>> mapList = null;
        if(null == params || params.length == 0){
            mapList = queryRunner.query(connection, sql, new MapListHandler());
        }else{
            mapList = queryRunner.query(connection, sql, new MapListHandler(), params);
        }
        return mapList;
    }

    /**
     *  执行查询，将查询结果封装到List<Map<String, Object>>里面
     * @param sql       待执行的SQL语句
     * @param param     SQL执行需要的参数
     * @return          List<Map>对象
     * @author          javaliu
     * @date            2016.08.24
     */
    public List<Map<String, Object>> findMapList(String sql, Object param) throws SQLException{
        return this.findMapList(sql, new Object[]{param});
    }

    /**
     *  执行查询，将查询结果封装到List<Map<String, Object>>里面
     * @param sql       待执行的SQL语句
     * @return          List<Map>对象
     * @author          javaliu
     * @date            2016.08.24
     */
    public List<Map<String, Object>> findMapList(String sql)throws SQLException{
        return this.findMapList(sql, null);
    }

    /**
     *  执行查询，将查询结果封装到Map里面
     * @param sql       待执行的SQL语句
     * @return          Map对象
     * @author          javaliu
     * @date            2016.08.24
     */
    public Map<String, Object> findMap(String sql)throws SQLException{
        return this.findMap(sql, null);
    }

    /**
     * 查询某一条记录，并将指定列的数据转换为Object返回
     * @param sql           待执行的SQL语句(SQL语句中不要拼接条件，防止SQL注入)
     * @param columnName    列名称
     * @param params        参数列表
     * @return
     * @author  javaliu
     * @date    2016.08.17
     */
    public Object findby(String sql, String columnName, Object[] params)throws SQLException{
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = DBUtils.getConnection(dataSource);
        Object object = null;
        if(null == params || params.length == 0){
            object = queryRunner.query(connection, sql, new ScalarHandler(columnName));
        }else{
            object = queryRunner.query(connection, sql, new ScalarHandler(columnName), params);
        }
        return object;
    }

    /**
     * 查询某一条记录，并将指定列的数据转换为Object返回
     * @param sql           待执行的SQL语句(SQL语句中不要拼接条件，防止SQL注入)
     * @param columnName    列名称
     * @param param         参数
     * @return
     * @author  javaliu
     * @date    2016.08.17
     */
    public Object findby(String sql, String columnName, Object param)throws SQLException{
        return findby(sql, columnName, new Object[]{param});
    }

    /**
     * 查询某一条记录，并将指定列的数据转换为Object返回
     * @param sql           待执行的SQL语句(SQL语句中不要拼接条件，防止SQL注入)
     * @param columnName    列名称
     * @return
     * @author  javaliu
     * @date    2016.08.17
     */
    public Object findby(String sql, String columnName)throws SQLException{
        return findby(sql, columnName, null);
    }

    /**
     * 执行SQL进行数据更新
     * @param sql       需要执行的SQL
     * @param params    参数
     * @return  受影响的行数
     * @author  javaliu
     * @date    2016.08.17
     */
    public int update(String sql, Object[] params)throws SQLException{
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = DBUtils.getConnection(dataSource);
        int index = 0;
        if(null == params || params.length == 0){
            index = queryRunner.update(connection, sql);
        }else{
            index = queryRunner.update(connection, sql, params);
        }
        return index;
    }

    /**
     * 执行SQL进行数据更新
     * @param sql       需要执行的SQL
     * @param param    参数
     * @return  受影响的行数
     * @author  javaliu
     * @date    2016.08.17
     */
    public int update(String sql, Object param) throws SQLException{
        return update(sql, new Object[]{param});
    }

    /**
     * 执行SQL进行数据更新
     * @param sql       需要执行的SQL
     * @return  受影响的行数
     * @author  javaliu
     * @date    2016.08.17
     */
    public int update(String sql) throws SQLException{
        return update(sql, null);
    }

    /**
     * 批量更新操作
     * @param sql   待执行的SQL
     * @param params    参数
     * @return
     * @author  javaliu
     * @date    2016.08.17
     */
    public int[] batchUpdate(String sql, Object[][] params)throws SQLException{
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = DBUtils.getConnection(dataSource);
        int[] index = null;
        if(null != params){
            index = queryRunner.batch(connection, sql, params);
        }
        return index;
    }


    /**
     * 执行SQL进行数据插入
     * @param sql       需要执行的SQL
     * @param entityClass 插入的实体对象
     * @param params    参数
     * @return          刚刚插入的对象
     * @author  javaliu
     * @date    2016.08.17
     */
    public <T> T insert(String sql, Class<T> entityClass, Object[] params)throws SQLException{
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = DBUtils.getConnection(dataSource);
        T object = null;
        if(null == params || params.length == 0){
            object = (T)queryRunner.insert(connection, sql, new BeanHandler<T>(entityClass));
        }else{
            object = (T)queryRunner.insert(connection, sql, new BeanHandler<T>(entityClass), params);
        }
        return object;
    }

    /**
     * 执行SQL进行数据插入
     * @param sql       需要执行的SQL
     * @param entityClass     插入的实体对象
     * @param param     参数
     * @return          刚刚插入的实体对象
     * @author  javaliu
     * @date    2016.08.17
     */
    public <T> T insert(String sql, Class<T> entityClass, Object param)throws SQLException{
        return insert(sql, entityClass, new Object[]{param});
    }

    /**
     * 执行SQL进行数据更新
     * @param sql       需要执行的SQL
     * @param entityClass 被插入的对象
     * @return            刚刚插入的对象
     * @author  javaliu
     * @date    2016.08.17
     */
    public <T> T insert(String sql, Class<T> entityClass) throws SQLException{
        return insert(sql, entityClass, null);
    }

    /**
     * 批量插入操作
     * @param sql   待执行的SQL
     * @param entityClass 要插入的实体对象
     * @param params    参数
     * @return
     * @author  javaliu
     * @date    2016.08.17
     */
    public <T> T batchInsert(String sql, Class<T> entityClass, Object[][] params)throws SQLException{
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = DBUtils.getConnection(dataSource);
        T obj = null;
        if(null != params){
            obj = (T) queryRunner.insertBatch(connection, sql, new BeanHandler<T>(entityClass), params);
        }
        return obj;
    }
}
