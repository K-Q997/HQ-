package com.chinasoft.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *  用于从数据源中获取一个连接，并且实现线程和连接的绑定和解绑功能
 */
@Component
public class ConnectionUtils {

    private ThreadLocal<Connection> tl = new ThreadLocal<>();

    @Autowired
    private DataSource dataSource;

    /**
     * 创建连接并进行绑定
     */
    public Connection getConnection()  {
        try {
            //1.从线程中取连接
            Connection connection = tl.get();
            //2.判断连接是否是空的
            if (connection == null) {
//                如果为空 则在数据源中获取一个连接
                connection = dataSource.getConnection();
                tl.set(connection);
                return connection;
            }
//            如果不为空则 直接返回连接
            return connection;
        } catch (SQLException throwables) {
            throw new RuntimeException("获取连接失败");
        }
    }

    /**
     * 解绑线程
     */
    public void remove(){
        tl.remove();
    }




}
