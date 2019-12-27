package com.wfm.servicecommon.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * description: JdbcUtil
 * date: 2019-12-26 19:30
 *
 * @author: wfm
 * @version: 1.0
 */
@Slf4j
public class JdbcUtil {



    private static  String driverClassName;
    private static  String url;
    private static  String userName;
    private static  String password;

    private static Connection conn=null;
    private static PreparedStatement pstmt=null;
    private static ResultSet rs=null;


    private static void loadConfig(){
        DBConfig config=new DBConfig();
        driverClassName=config.getDriverClassName();
        url=config.getUrl();
        userName=config.getUserName();
        password=config.getPassword();

    }



    // 静态代码块 在程序加载中static是先于构造方法加载的，并且只会加载一次
    static{
        try {
            loadConfig();
            // 注册驱动程序
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            log.error("数据库驱动类加载异常："+e.getMessage(),e);
        }
    }

    public JdbcUtil(String db){
        if(!StringUtils.isEmpty((db))) {
            DBConfig config = new DBConfig(db);
            driverClassName = config.getDriverClassName();
            url = config.getUrl();
            userName = config.getUserName();
            password = config.getPassword();
        }
    }

    /**
     * 获取连接方
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException{
        try{
            conn=DriverManager.getConnection(url,userName,password);
        }catch(RuntimeException re){
            log.error("获取数据库连接异常："+re.getMessage(),re);
            throw re;
        }
        return conn;
    }

    private static PreparedStatement getStmt(String sql) throws SQLException {
        try {
            pstmt = conn.prepareStatement(sql);
        } catch (RuntimeException re) {
            log.error("获取数据库处理命令异常：" + re.getMessage(), re);
            throw re;
        }
        return pstmt;
    }

    private static void setParams(List<Object>params) throws SQLException {
        try {
            //if(params!=null&&params.size()>0){
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }
            //}
        } catch (RuntimeException re) {
            log.error("设置查询参数发生异常：" + re.getMessage(), re);
            throw re;
        }
    }

    private static ResultSetMetaData prepareRsmd(String sql, List<Object> params) throws SQLException {
        prepareRs(sql, params);
        ResultSetMetaData rsmd = rs.getMetaData();
        return rsmd;
    }

    private static void prepareRs(String sql, List<Object> params) throws SQLException {
        preparePstmt(sql, params);
        getRs();
    }

    private static void preparePstmt(String sql, List<Object> params) throws SQLException {
        getConnection();
        getStmt(sql);
        if (params != null && params.size() > 0)
            setParams(params);
    }

    private static ResultSet getRs() throws SQLException{
        try{
            rs=pstmt.executeQuery();
        }catch(RuntimeException re){
            log.error("获取查询结果发生异常："+re.getMessage(),re);
            throw re;
        }
        return rs;
    }

    /**
     * 释放资源
     * @throws SQLException
     */
    public static void closeAll() throws SQLException {
        try {
            if (rs != null)
                rs.close();
            if (pstmt != null)
                pstmt.close();
            if (conn != null)
                conn.close();
        } catch (RuntimeException re) {
            log.error("数据库关闭项异常：" + re.getMessage(), re);
            throw re;
        }
    }



    /**
     * 封装结果集为：List<HashMap<String,Object>>
     * @param sql
     * @param params   List<Object>
     * @return  list<HashMap<String,Object>>
     * @throws SQLException
     */
    public static List<HashMap<String,Object>> getListHashMap(String sql, List<Object>params) throws SQLException {
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        try {
            HashMap<String, Object> hashMap = null;
            ResultSetMetaData rsmd = prepareRsmd(sql, params);

            int columnCount = rsmd.getColumnCount();

            while (rs.next()) {
                hashMap = new HashMap<String, Object>();
                for (int i = 0; i < columnCount; i++) {
                    hashMap.put((rsmd.getColumnLabel(i + 1)).toLowerCase(), rs.getObject(i + 1));
                }
                list.add(hashMap);
            }
        } catch (RuntimeException e) {
            log.error("获取查询结果集异常：" + e.getMessage(), e);
            throw e;
        } finally {
            closeAll();
        }
        return list;
    }

    /**
     * 查询实体类的list结果集
     * @param sql  查询语句
     * @param clazz  实体类的Class
     * @param params 查询参数值：List<Object>
     * @return  List<T>  T 实体类
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SQLException
     */
    public static <T>List<T> getListCommBean(Class<T> clazz,String sql,List<Object>params) throws  InstantiationException, IllegalAccessException, SQLException {
        List<T> list = new ArrayList<T>();
        T t = null;
        try {
            ResultSetMetaData rsmd = prepareRsmd(sql, params);
            int columnCount = rsmd.getColumnCount();

            Field[] fields = clazz.getDeclaredFields();
            while (rs.next()) {
                t = (T) clazz.newInstance();

                for (int i = 0; i < columnCount; i++) {
                    String columnName = rsmd.getColumnLabel(i + 1);

                    for (int j = 0; j < fields.length; j++) {
                        Field field = fields[j];
                        // 取得成员变量的数据类型的 Class
                        Class<?> fieldType = field.getType();
                        // 成员变量数据类型的class的字符串只，并小写化
                        String fieldTypeString = fieldType.toString().toLowerCase();
                        // 取得成员变量名
                        String fieldName = field.getName();
                        //成员变量名，不等于查询出的字段的标签名称（忽略大小写），继续下次循环
                        if (!fieldName.equalsIgnoreCase(columnName)) continue;
                        //设置值
                        setBeanValue(t, i, field, fieldTypeString);
                    }
                }
                list.add(t);
            }

        } catch (RuntimeException re) {
            log.error("获取查询结果集异常：" + re.getMessage(), re);
            throw re;
        } finally {
            closeAll();
        }
        return list;
    }

    /**
     * 同用实体类查询方法 ：只返回一条记录的sql语句，如果有多条，只返回第一条记录
     * @param clazz  实体类的Class
     * @param sql  查询语句，只返回一条记录的sql语句，如果有多条，只返回第一条记录
     * @param params  查询语句的参数：list<Object>
     * @return  实体类：只返回一条记录的sql语句，如果有多条，只返回第一条记录
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IntrospectionException
     * @throws InvocationTargetException
     */
    public static  <T> T getResultBean(Class<T> clazz,String sql,List<Object>params) throws SQLException, InstantiationException, IllegalAccessException, IntrospectionException, InvocationTargetException {
        T t = null;
        try {
            // 取得实体类成员变量对象
            Field[] fields = clazz.getDeclaredFields();
            ResultSetMetaData rsmd = prepareRsmd(sql, params);
            // 取得数据列数
            int columnCount = rsmd.getColumnCount();
            while (rs.next()) {
                t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    // 取得查询数据类的标签名：即as 后面的别名，如果没有别名，则为字段名称，取出的为大写格式
                    String columnLabelName = rsmd.getColumnLabel(i + 1);
                    for (Field field : fields) {
                        // 取得实体类成员变量的名称
                        String propertyName = field.getName();
                        // 通过Field取得实体类变量 类型
                        String propertyType = field.getType().toString().toLowerCase();
                        if (!propertyName.equalsIgnoreCase(columnLabelName)) continue;
                        //对带有指定参数的指定对象调用由此 Method 对象表示的底层方法invoke初始化值。t 实体类对象 - 从中调用底层方法的对象 args - 用于方法调用的参数
                        invokeBeanValue(t, i, propertyType, propertyName);
                    }
                }
                break;//此处，直接终止本次循环，只返回第一条结果。
            }

        } catch (RuntimeException re) {
            throw re;
        } finally {
            closeAll();
        }
        return t;
    }

    /**
     * 对带有指定参数的指定对象调用由此 Method 对象表示的底层方法invoke初始化值。
     * @param t  t 实体类对象
     * @param i
     * @param propertyType
     * @param propertyName
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws IntrospectionException
     * @throws SQLException
     */
    private static <T> void invokeBeanValue(T t, int i, String propertyType,String propertyName) throws IllegalAccessException, InvocationTargetException, IntrospectionException, SQLException {
        // 对带有指定参数的指定对象调用由此 Method 对象表示的底层方法invoke初始化值。t 实体类对象 - 从中调用底层方法的对象 args - 用于方法调用的参数
        // 通过调用 get 和 set 存取方法，为符合标准 Java 约定的属性构造一个 PropertyDescriptor,成员变量aab001,则标准为：getAab001,setAab001。
        PropertyDescriptor propdscrp = new PropertyDescriptor(propertyName, t.getClass());
        // 读取写入方法，即：set方法
        Method method = propdscrp.getWriteMethod();
        if (propertyType.indexOf("byte") > 0) {
            method.invoke(t, rs.getByte(i + 1));
        } else if (propertyType.indexOf("boolean") > 0) {
            method.invoke(t, rs.getBoolean(i + 1));
        } else if (propertyType.indexOf("short") > 0) {
            method.invoke(t, rs.getShort(i + 1));
        } else if (propertyType.indexOf("int") > 0 || propertyType.indexOf("integer") > 0) {
            method.invoke(t, rs.getInt(i + 1));
        } else if (propertyType.indexOf("long") > 0) {
            method.invoke(t, rs.getLong(i + 1));
        } else if (propertyType.indexOf("float") > 0) {
            method.invoke(t, rs.getFloat(i + 1));
        } else if (propertyType.indexOf("double") > 0) {
            method.invoke(t, rs.getDouble(i + 1));
        } else if (propertyType.indexOf("string") > 0) {
            method.invoke(t, rs.getString(i + 1));
        } else if (propertyType.indexOf("date") > 0) {
            method.invoke(t, rs.getDate(i + 1));
        } else if (propertyType.indexOf("time") > 0) {
            method.invoke(t, rs.getTime(i + 1));
        } else if (propertyType.indexOf("timestamp") > 0) {
            method.invoke(t, rs.getTimestamp(i + 1));
        } else if (propertyType.indexOf("bigdecimal") > 0) {
            method.invoke(t, rs.getBigDecimal(i + 1));
        } else if (propertyType.indexOf("clob") > 0) {
            method.invoke(t, rs.getClob(i + 1));
        } else if (propertyType.indexOf("blob") > 0) {
            // 一般用：rs.getBinaryStream(i+1);
            method.invoke(t, rs.getBlob(i + 1));
        } else {
            method.invoke(t, rs.getObject(i + 1));
        }
    }

    /**
     * 对带有指定参数的指定对象调用由此 Method ,实体类对象属性Field 的set方法。
     * @param t
     * @param i
     * @param field
     * @param fieldTypeString
     * @throws IllegalAccessException
     * @throws SQLException
     */
    private static <T> void setBeanValue(T t, int i, Field field,String fieldTypeString) throws IllegalAccessException, SQLException {
        boolean bFlag = field.isAccessible();
        // 打开javabean的访问权限
        field.setAccessible(true);
        if (fieldTypeString.indexOf("byte") > 0) {
            field.set(t, rs.getByte(i + 1));
        } else if (fieldTypeString.indexOf("boolean") > 0) {
            field.set(t, rs.getBoolean(i + 1));
        } else if (fieldTypeString.indexOf("short") > 0) {
            field.set(t, rs.getShort(i + 1));
        } else if (fieldTypeString.indexOf("int") > 0 || fieldTypeString.indexOf("integer") > 0) {
            field.set(t, rs.getInt(i + 1));
        } else if (fieldTypeString.indexOf("long") > 0) {
            field.set(t, rs.getLong(i + 1));
        } else if (fieldTypeString.indexOf("float") > 0) {
            field.set(t, rs.getFloat(i + 1));
        } else if (fieldTypeString.indexOf("double") > 0) {
            field.set(t, rs.getDouble(i + 1));
        } else if (fieldTypeString.indexOf("string") > 0) {
            field.set(t, rs.getString(i + 1));
        } else if (fieldTypeString.indexOf("date") > 0) {
            field.set(t, rs.getDate(i + 1));
        } else if (fieldTypeString.indexOf("time") > 0) {
            field.set(t, rs.getTime(i + 1));
        } else if (fieldTypeString.indexOf("timestamp") > 0) {
            field.set(t, rs.getTimestamp(i + 1));
        } else if (fieldTypeString.indexOf("bigdecimal") > 0) {
            field.set(t, rs.getBigDecimal(i + 1));
        } else if (fieldTypeString.indexOf("clob") > 0) {
            field.set(t, rs.getClob(i + 1));
        } else if (fieldTypeString.indexOf("blob") > 0) {
            // 一般用：rs.getBinaryStream(i+1);
            field.set(t, rs.getBlob(i + 1));
        } else {
            field.set(t, rs.getObject(i + 1));
        }
        field.setAccessible(bFlag);
    }


    /**
     * INSERT, UPDATE or DELETE  ,增、删、改 通用查询方法
     * @param sql 数据库操作语句： SQL Data Manipulation Language (DML) statement,必须是数据库DML 语句
     * @param params 查询语句参数：List<Object>
     * @return 1、有数据被更新：则返回更新的记录数；2、没有记录被更新：返回0；
     * @throws SQLException
     */
    public static int updateRecord(String sql,List<Object>params) throws SQLException {
        int iNum = 0;
        try {
            preparePstmt(sql, params);
            // 更新成功，返回更新的记录数，没有更新返回0
            iNum = pstmt.executeUpdate();
        } catch (RuntimeException re) {
            log.error("更新数据库记录异常:" + re.getMessage());
            throw re;
        } finally {
            closeAll();
        }
        return iNum;
    }
}
