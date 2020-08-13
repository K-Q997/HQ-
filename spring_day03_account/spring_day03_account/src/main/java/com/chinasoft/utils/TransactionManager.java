package com.chinasoft.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class TransactionManager {

    @Autowired
    private ConnectionUtils utils;

    /**
     * 开启事务
     */
    public void startTransaction(){
        try {
            utils.getConnection().setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    public void commit(){
        try {
            utils.getConnection().commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
    public void rollback(){
        try {
            utils.getConnection().rollback();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 释放资源
     */
    public void closeTransation(){
        try {
            utils.getConnection().close();
            utils.remove();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
