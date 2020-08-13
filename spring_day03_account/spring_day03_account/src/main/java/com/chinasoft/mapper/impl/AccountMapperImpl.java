package com.chinasoft.mapper.impl;

import com.chinasoft.domain.Account;
import com.chinasoft.mapper.AccountMapper;
import com.chinasoft.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository("accountMapper")
public class AccountMapperImpl implements AccountMapper {


    @Autowired
    private QueryRunner runner;

    @Autowired
    private ConnectionUtils utils;

    /**
     * 修改用户列表
     * @param account
     */
    @Override
    public void updateAccount(Account account) {
        try {
            String sql = "update account set money=? where id=?";
            runner.update(utils.getConnection(),sql,account.getMoney(),account.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 根据用户名查询用户信息
     * @param name
     * @return
     */
    @Override
    public Account findAccountByName(String name) {
        try {
            String sql = "select * from account where username = ?";
            List<Account> accounts = runner.query(utils.getConnection(),sql,new BeanListHandler<>(Account.class),name);
            if (accounts.size()>1){
                throw new RuntimeException("查询结果不唯一");
            }else if (accounts.size() ==0 ||accounts == null){
                return null;
            }
            return accounts.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Account> findAll() {
        try {
            return runner.query(utils.getConnection(),"select * from account",new BeanListHandler<>(Account.class));
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }



}
