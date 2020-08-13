package com.chinasoft.mapper;

import com.chinasoft.domain.Account;

import java.util.List;

public interface AccountMapper {

    List<Account> findAll();

    void updateAccount(Account account);


   Account findAccountByName(String name);
}
