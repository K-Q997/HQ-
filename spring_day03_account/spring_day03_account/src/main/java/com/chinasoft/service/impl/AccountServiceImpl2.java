package com.chinasoft.service.impl;

import com.chinasoft.domain.Account;
import com.chinasoft.mapper.AccountMapper;
import com.chinasoft.service.AccountService;
import com.chinasoft.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public class AccountServiceImpl2 implements AccountService {

    @Autowired
    private AccountMapper mapper;

    @Autowired
    private TransactionManager manager;

    @Override
    public List<Account> findAll() {
        return mapper.findAll();
    }

    @Override
    public void transferMoney(String formName, String toName, Integer money) {
        try {
//            开启事务
            manager.startTransaction();
//        查询对应的信息
            Account from = mapper.findAccountByName(formName);
            Account to = mapper.findAccountByName(toName);
//        设置转账金额
            from.setMoney(from.getMoney()-money);
            to.setMoney(to.getMoney()+money);
//        进行转账操作
            mapper.updateAccount(from);
            int i=1/0;
            mapper.updateAccount(to);
//            运行无异常 提交事务
            manager.commit();
        } catch (Exception e) {
            e.printStackTrace();
//            出现报错回滚事务
            manager.rollback();
        } finally {
//            释放资源
            manager.closeTransation();
        }
    }

    @Override
    public void updateAccount(Account account) {

        try {
            manager.startTransaction();
            System.out.println("修改成功");
            manager.commit();
        } catch (Exception e) {
            e.printStackTrace();
            manager.rollback();
        } finally {
            manager.closeTransation();
        }
    }

    @Override
    public void deleteAccount(Integer id) {
        try {
            manager.startTransaction();
            System.out.println("删除成功");
            manager.commit();
        } catch (Exception e) {
            e.printStackTrace();
            manager.rollback();
        } finally {
            manager.closeTransation();
        }

    }

    @Override
    public void addAccount(Account account) {
        try {
            manager.startTransaction();
            System.out.println("添加成功");
            manager.commit();
        } catch (Exception e) {
            e.printStackTrace();
            manager.rollback();
        } finally {
            manager.closeTransation();
        }

    }


}
