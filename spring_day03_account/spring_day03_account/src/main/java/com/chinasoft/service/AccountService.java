package com.chinasoft.service;

import com.chinasoft.domain.Account;

import java.util.List;

public interface AccountService {


    /**
     * 查询所有账户
     * @return
     */
    List<Account> findAll();


    /**
     * 转账
     * @param formName
     * @param toName
     * @param money
     */
    void transferMoney(String formName,String toName,Integer money);


    /**
     * 修改
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 删除操作
     * @param id
     */
    void deleteAccount(Integer id);


    /**
     *
     * @param account
     */
    void addAccount(Account account);
}
