package com.chinasoft.service.impl;

import com.chinasoft.domain.Account;
import com.chinasoft.mapper.AccountMapper;
import com.chinasoft.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {


    @Autowired
    private AccountMapper mapper;

    @Override
    public List<Account> findAll() {
        return mapper.findAll();
    }

    @Override
    public void transferMoney(String formName, String toName, Integer money) {
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
    }

    @Override
    public void updateAccount(Account account) {
        System.out.println("修改成功");
    }

    @Override
    public void deleteAccount(Integer id) {
        System.out.println("删除成功");
    }

    @Override
    public void addAccount(Account account) {
        System.out.println("添加成功");
    }
}
